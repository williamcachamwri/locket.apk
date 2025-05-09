package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import java.util.Map;
import javax.annotation.Nullable;

public class PostprocessedBitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String PRODUCER_NAME = "PostprocessedBitmapMemoryCacheProducer";
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return PRODUCER_NAME;
    }

    public PostprocessedBitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        ImageRequest imageRequest = producerContext.getImageRequest();
        Object callerContext = producerContext.getCallerContext();
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor == null || postprocessor.getPostprocessorCacheKey() == null) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        producerListener.onProducerStart(producerContext, getProducerName());
        CacheKey postprocessedBitmapCacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
        Map map = null;
        CloseableReference<CloseableImage> closeableReference = producerContext.getImageRequest().isCacheEnabled(1) ? this.mMemoryCache.get(postprocessedBitmapCacheKey) : null;
        if (closeableReference != null) {
            String producerName = getProducerName();
            if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
                map = ImmutableMap.of("cached_value_found", "true");
            }
            producerListener.onProducerFinishWithSuccess(producerContext, producerName, map);
            producerListener.onUltimateProducerReached(producerContext, PRODUCER_NAME, true);
            producerContext.putOriginExtra("memory_bitmap", "postprocessed");
            consumer.onProgressUpdate(1.0f);
            consumer.onNewResult(closeableReference, 1);
            closeableReference.close();
            return;
        }
        CachedPostprocessorConsumer cachedPostprocessorConsumer = new CachedPostprocessorConsumer(consumer, postprocessedBitmapCacheKey, postprocessor instanceof RepeatedPostprocessor, this.mMemoryCache, producerContext.getImageRequest().isCacheEnabled(2));
        String producerName2 = getProducerName();
        if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
            map = ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE);
        }
        producerListener.onProducerFinishWithSuccess(producerContext, producerName2, map);
        this.mInputProducer.produceResults(cachedPostprocessorConsumer, producerContext);
    }

    public static class CachedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private final CacheKey mCacheKey;
        private final boolean mIsBitmapCacheEnabledForWrite;
        private final boolean mIsRepeatedProcessor;
        private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

        public CachedPostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, CacheKey cacheKey, boolean z, MemoryCache<CacheKey, CloseableImage> memoryCache, boolean z2) {
            super(consumer);
            this.mCacheKey = cacheKey;
            this.mIsRepeatedProcessor = z;
            this.mMemoryCache = memoryCache;
            this.mIsBitmapCacheEnabledForWrite = z2;
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> closeableReference, int i) {
            CloseableReference<CloseableImage> closeableReference2 = null;
            if (closeableReference == null) {
                if (isLast(i)) {
                    getConsumer().onNewResult(closeableReference2, i);
                }
            } else if (!isNotLast(i) || this.mIsRepeatedProcessor) {
                if (this.mIsBitmapCacheEnabledForWrite) {
                    closeableReference2 = this.mMemoryCache.cache(this.mCacheKey, closeableReference);
                }
                try {
                    getConsumer().onProgressUpdate(1.0f);
                    Consumer consumer = getConsumer();
                    if (closeableReference2 != null) {
                        closeableReference = closeableReference2;
                    }
                    consumer.onNewResult(closeableReference, i);
                } finally {
                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
                }
            }
        }
    }
}
