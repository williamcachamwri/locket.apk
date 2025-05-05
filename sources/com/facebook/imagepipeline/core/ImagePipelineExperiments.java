package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.platform.PlatformDecoderOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 Y2\u00020\u0001:\u0004XYZ[B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0017\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0011\u0010\u001b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0011\u0010\u001f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\bR\u0011\u0010\"\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\bR\u0011\u0010$\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\bR\u0011\u0010%\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\bR\u0011\u0010&\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u0011\u0010'\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\bR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060)¢\u0006\b\n\u0000\u001a\u0004\b(\u0010*R\u0011\u0010+\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\bR\u0011\u0010,\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u0011\u0010-\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\bR\u0011\u0010.\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\bR\u0011\u00100\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u000eR\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u000207¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010:\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\bR\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010@\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\bR\u0011\u0010B\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bC\u0010\bR\u0011\u0010D\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bE\u0010\bR\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00060)¢\u0006\b\n\u0000\u001a\u0004\bG\u0010*R\u0011\u0010H\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u000eR\u0011\u0010J\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\bR\u0011\u0010L\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\bR\u0011\u0010N\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\bO\u0010\bR\u0013\u0010P\u001a\u0004\u0018\u00010Q¢\u0006\b\n\u0000\u001a\u0004\bR\u0010SR\u0013\u0010T\u001a\u0004\u0018\u00010U¢\u0006\b\n\u0000\u001a\u0004\bV\u0010W¨\u0006\\"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "", "builder", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "(Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;)V", "allowDelay", "", "getAllowDelay", "()Z", "allowProgressiveOnPrefetch", "getAllowProgressiveOnPrefetch", "animatedCacheMemoryPercentage", "", "getAnimatedCacheMemoryPercentage", "()I", "animationRenderFpsLimit", "getAnimationRenderFpsLimit", "balancedStrategyPreparationMs", "getBalancedStrategyPreparationMs", "bitmapPrepareToDrawForPrefetch", "getBitmapPrepareToDrawForPrefetch", "bitmapPrepareToDrawMaxSizeBytes", "getBitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawMinSizeBytes", "getBitmapPrepareToDrawMinSizeBytes", "cancelDecodeOnCacheMiss", "getCancelDecodeOnCacheMiss", "downsampleIfLargeBitmap", "getDownsampleIfLargeBitmap", "downscaleFrameToDrawableDimensions", "getDownscaleFrameToDrawableDimensions", "handOffOnUiThreadOnly", "getHandOffOnUiThreadOnly", "isDecodeCancellationEnabled", "isDiskCacheProbingEnabled", "isEncodedCacheEnabled", "isEncodedMemoryCacheProbingEnabled", "isEnsureTranscoderLibraryLoaded", "isExperimentalThreadHandoffQueueEnabled", "isGingerbreadDecoderEnabled", "isLazyDataSource", "Lcom/facebook/common/internal/Supplier;", "()Lcom/facebook/common/internal/Supplier;", "isNativeCodeDisabled", "isPartialImageCachingEnabled", "isWebpSupportEnabled", "keepCancelledFetchAsLowPriority", "getKeepCancelledFetchAsLowPriority", "maxBitmapSize", "getMaxBitmapSize", "memoryType", "", "getMemoryType", "()J", "platformDecoderOptions", "Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "getPlatformDecoderOptions", "()Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "prefetchShortcutEnabled", "getPrefetchShortcutEnabled", "producerFactoryMethod", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "getProducerFactoryMethod", "()Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "shouldIgnoreCacheSizeMismatch", "getShouldIgnoreCacheSizeMismatch", "shouldStoreCacheEntrySize", "getShouldStoreCacheEntrySize", "shouldUseDecodingBufferHelper", "getShouldUseDecodingBufferHelper", "suppressBitmapPrefetchingSupplier", "getSuppressBitmapPrefetchingSupplier", "trackedKeysSize", "getTrackedKeysSize", "useBalancedAnimationStrategy", "getUseBalancedAnimationStrategy", "useBitmapPrepareToDraw", "getUseBitmapPrepareToDraw", "useDownsamplingRatioForResizing", "getUseDownsamplingRatioForResizing", "webpBitmapFactory", "Lcom/facebook/common/webp/WebpBitmapFactory;", "getWebpBitmapFactory", "()Lcom/facebook/common/webp/WebpBitmapFactory;", "webpErrorLogger", "Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "getWebpErrorLogger", "()Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "Builder", "Companion", "DefaultProducerFactoryMethod", "ProducerFactoryMethod", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImagePipelineExperiments.kt */
public final class ImagePipelineExperiments {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean allowDelay;
    private final boolean allowProgressiveOnPrefetch;
    private final int animatedCacheMemoryPercentage;
    private final int animationRenderFpsLimit;
    private final int balancedStrategyPreparationMs;
    private final boolean bitmapPrepareToDrawForPrefetch;
    private final int bitmapPrepareToDrawMaxSizeBytes;
    private final int bitmapPrepareToDrawMinSizeBytes;
    private final boolean cancelDecodeOnCacheMiss;
    private final boolean downsampleIfLargeBitmap;
    private final boolean downscaleFrameToDrawableDimensions;
    private final boolean handOffOnUiThreadOnly;
    private final boolean isDecodeCancellationEnabled;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedCacheEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;
    private final boolean isEnsureTranscoderLibraryLoaded;
    private final boolean isExperimentalThreadHandoffQueueEnabled;
    private final boolean isGingerbreadDecoderEnabled;
    private final Supplier<Boolean> isLazyDataSource;
    private final boolean isNativeCodeDisabled;
    private final boolean isPartialImageCachingEnabled;
    private final boolean isWebpSupportEnabled;
    private final boolean keepCancelledFetchAsLowPriority;
    private final int maxBitmapSize;
    private final long memoryType;
    private final PlatformDecoderOptions platformDecoderOptions;
    private final boolean prefetchShortcutEnabled;
    private final ProducerFactoryMethod producerFactoryMethod;
    private final boolean shouldIgnoreCacheSizeMismatch;
    private final boolean shouldStoreCacheEntrySize;
    private final boolean shouldUseDecodingBufferHelper;
    private final Supplier<Boolean> suppressBitmapPrefetchingSupplier;
    private final int trackedKeysSize;
    private final boolean useBalancedAnimationStrategy;
    private final boolean useBitmapPrepareToDraw;
    private final boolean useDownsamplingRatioForResizing;
    private final WebpBitmapFactory webpBitmapFactory;
    private final WebpBitmapFactory.WebpErrorLogger webpErrorLogger;

    @Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001Jà\u0001\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00172\u0016\u0010\u001a\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\r2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020$H&¨\u0006,"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "", "createProducerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", "context", "Landroid/content/Context;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleEnabled", "", "resizeAndRotateEnabledForNetwork", "decodeCancellationEnabled", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "defaultBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapPrepareToDrawMinSizeBytes", "", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawForPrefetch", "maxBitmapSize", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "keepCancelledFetchAsLowPriority", "trackedKeysSize", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImagePipelineExperiments.kt */
    public interface ProducerFactoryMethod {
        ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4, int i3, CloseableReferenceFactory closeableReferenceFactory, boolean z5, int i4);
    }

    public /* synthetic */ ImagePipelineExperiments(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @JvmStatic
    public static final Builder newBuilder(ImagePipelineConfig.Builder builder) {
        return Companion.newBuilder(builder);
    }

    private ImagePipelineExperiments(Builder builder) {
        this.isWebpSupportEnabled = builder.webpSupportEnabled;
        this.webpErrorLogger = builder.webpErrorLogger;
        this.isDecodeCancellationEnabled = builder.decodeCancellationEnabled;
        this.webpBitmapFactory = builder.webpBitmapFactory;
        this.useDownsamplingRatioForResizing = builder.useDownsamplingRatioForResizing;
        this.useBitmapPrepareToDraw = builder.useBitmapPrepareToDraw;
        this.useBalancedAnimationStrategy = builder.useBalancedAnimationStrategy;
        this.balancedStrategyPreparationMs = builder.balancedStrategyPreparationMs;
        this.animatedCacheMemoryPercentage = builder.animatedCacheMemoryPercentage;
        this.bitmapPrepareToDrawMinSizeBytes = builder.bitmapPrepareToDrawMinSizeBytes;
        this.bitmapPrepareToDrawMaxSizeBytes = builder.bitmapPrepareToDrawMaxSizeBytes;
        this.bitmapPrepareToDrawForPrefetch = builder.bitmapPrepareToDrawForPrefetch;
        this.maxBitmapSize = builder.maxBitmapSize;
        this.isNativeCodeDisabled = builder.nativeCodeDisabled;
        this.isPartialImageCachingEnabled = builder.isPartialImageCachingEnabled;
        ProducerFactoryMethod producerFactoryMethod2 = builder.producerFactoryMethod;
        this.producerFactoryMethod = producerFactoryMethod2 == null ? new DefaultProducerFactoryMethod() : producerFactoryMethod2;
        Supplier<Boolean> supplier = builder.lazyDataSource;
        if (supplier == null) {
            supplier = Suppliers.BOOLEAN_FALSE;
            Intrinsics.checkNotNullExpressionValue(supplier, "BOOLEAN_FALSE");
        }
        this.isLazyDataSource = supplier;
        this.isGingerbreadDecoderEnabled = builder.gingerbreadDecoderEnabled;
        this.downscaleFrameToDrawableDimensions = builder.downscaleFrameToDrawableDimensions;
        this.suppressBitmapPrefetchingSupplier = builder.suppressBitmapPrefetchingSupplier;
        this.isExperimentalThreadHandoffQueueEnabled = builder.experimentalThreadHandoffQueueEnabled;
        this.memoryType = builder.memoryType;
        this.keepCancelledFetchAsLowPriority = builder.keepCancelledFetchAsLowPriority;
        this.downsampleIfLargeBitmap = builder.downsampleIfLargeBitmap;
        this.isEncodedCacheEnabled = builder.encodedCacheEnabled;
        this.isEnsureTranscoderLibraryLoaded = builder.ensureTranscoderLibraryLoaded;
        this.isEncodedMemoryCacheProbingEnabled = builder.isEncodedMemoryCacheProbingEnabled;
        this.isDiskCacheProbingEnabled = builder.isDiskCacheProbingEnabled;
        this.trackedKeysSize = builder.trackedKeysSize;
        this.allowProgressiveOnPrefetch = builder.allowProgressiveOnPrefetch;
        this.animationRenderFpsLimit = builder.animationRenderFpsLimit;
        this.allowDelay = builder.allowDelay;
        this.handOffOnUiThreadOnly = builder.handOffOnUiThreadOnly;
        this.shouldStoreCacheEntrySize = builder.shouldStoreCacheEntrySize;
        this.shouldIgnoreCacheSizeMismatch = builder.shouldIgnoreCacheSizeMismatch;
        this.shouldUseDecodingBufferHelper = builder.shouldUseDecodingBufferHelper;
        this.cancelDecodeOnCacheMiss = builder.cancelDecodeOnCacheMiss;
        this.prefetchShortcutEnabled = builder.prefetchShortcutEnabled;
        this.platformDecoderOptions = builder.platformDecoderOptions;
    }

    public final boolean isWebpSupportEnabled() {
        return this.isWebpSupportEnabled;
    }

    public final WebpBitmapFactory.WebpErrorLogger getWebpErrorLogger() {
        return this.webpErrorLogger;
    }

    public final boolean isDecodeCancellationEnabled() {
        return this.isDecodeCancellationEnabled;
    }

    public final WebpBitmapFactory getWebpBitmapFactory() {
        return this.webpBitmapFactory;
    }

    public final boolean getUseDownsamplingRatioForResizing() {
        return this.useDownsamplingRatioForResizing;
    }

    public final boolean getUseBitmapPrepareToDraw() {
        return this.useBitmapPrepareToDraw;
    }

    public final boolean getUseBalancedAnimationStrategy() {
        return this.useBalancedAnimationStrategy;
    }

    public final int getBalancedStrategyPreparationMs() {
        return this.balancedStrategyPreparationMs;
    }

    public final int getBitmapPrepareToDrawMinSizeBytes() {
        return this.bitmapPrepareToDrawMinSizeBytes;
    }

    public final int getAnimatedCacheMemoryPercentage() {
        return this.animatedCacheMemoryPercentage;
    }

    public final int getBitmapPrepareToDrawMaxSizeBytes() {
        return this.bitmapPrepareToDrawMaxSizeBytes;
    }

    public final boolean getBitmapPrepareToDrawForPrefetch() {
        return this.bitmapPrepareToDrawForPrefetch;
    }

    public final int getMaxBitmapSize() {
        return this.maxBitmapSize;
    }

    public final boolean isNativeCodeDisabled() {
        return this.isNativeCodeDisabled;
    }

    public final boolean isPartialImageCachingEnabled() {
        return this.isPartialImageCachingEnabled;
    }

    public final ProducerFactoryMethod getProducerFactoryMethod() {
        return this.producerFactoryMethod;
    }

    public final Supplier<Boolean> isLazyDataSource() {
        return this.isLazyDataSource;
    }

    public final boolean isGingerbreadDecoderEnabled() {
        return this.isGingerbreadDecoderEnabled;
    }

    public final boolean getDownscaleFrameToDrawableDimensions() {
        return this.downscaleFrameToDrawableDimensions;
    }

    public final Supplier<Boolean> getSuppressBitmapPrefetchingSupplier() {
        return this.suppressBitmapPrefetchingSupplier;
    }

    public final boolean isExperimentalThreadHandoffQueueEnabled() {
        return this.isExperimentalThreadHandoffQueueEnabled;
    }

    public final long getMemoryType() {
        return this.memoryType;
    }

    public final boolean getKeepCancelledFetchAsLowPriority() {
        return this.keepCancelledFetchAsLowPriority;
    }

    public final boolean getDownsampleIfLargeBitmap() {
        return this.downsampleIfLargeBitmap;
    }

    public final boolean isEncodedCacheEnabled() {
        return this.isEncodedCacheEnabled;
    }

    public final boolean isEnsureTranscoderLibraryLoaded() {
        return this.isEnsureTranscoderLibraryLoaded;
    }

    public final boolean isEncodedMemoryCacheProbingEnabled() {
        return this.isEncodedMemoryCacheProbingEnabled;
    }

    public final boolean isDiskCacheProbingEnabled() {
        return this.isDiskCacheProbingEnabled;
    }

    public final int getTrackedKeysSize() {
        return this.trackedKeysSize;
    }

    public final boolean getAllowDelay() {
        return this.allowDelay;
    }

    public final boolean getHandOffOnUiThreadOnly() {
        return this.handOffOnUiThreadOnly;
    }

    public final boolean getShouldStoreCacheEntrySize() {
        return this.shouldStoreCacheEntrySize;
    }

    public final boolean getShouldIgnoreCacheSizeMismatch() {
        return this.shouldIgnoreCacheSizeMismatch;
    }

    public final boolean getShouldUseDecodingBufferHelper() {
        return this.shouldUseDecodingBufferHelper;
    }

    public final boolean getAllowProgressiveOnPrefetch() {
        return this.allowProgressiveOnPrefetch;
    }

    public final boolean getCancelDecodeOnCacheMiss() {
        return this.cancelDecodeOnCacheMiss;
    }

    public final int getAnimationRenderFpsLimit() {
        return this.animationRenderFpsLimit;
    }

    public final boolean getPrefetchShortcutEnabled() {
        return this.prefetchShortcutEnabled;
    }

    public final PlatformDecoderOptions getPlatformDecoderOptions() {
        return this.platformDecoderOptions;
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u00105\u001a\u00020\u00002\f\u00106\u001a\b\u0012\u0004\u0012\u00020807H\u0002J\u0006\u00109\u001a\u00020:J\u000e\u0010;\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010<\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010?\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0006J\u000e\u0010@\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ&\u0010A\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\t2\u0006\u0010D\u001a\u00020\u0006J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010F\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010G\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010H\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010I\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0006J\u000e\u0010J\u001a\u00020\u00002\u0006\u0010K\u001a\u00020 J\u000e\u0010L\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010M\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010N\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0006J\u000e\u0010O\u001a\u00020\u00002\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010P\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0006J\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0006J\u000e\u0010R\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0006J\u0016\u0010S\u001a\u00020\u00002\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001dJ\u000e\u0010T\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\tJ\u000e\u0010U\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0006J\u000e\u0010V\u001a\u00020\u00002\u0006\u0010W\u001a\u00020\u0006J\u000e\u0010X\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#J\u000e\u0010Y\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0006J\u0010\u0010Z\u001a\u00020\u00002\b\u0010%\u001a\u0004\u0018\u00010&J\u000e\u0010[\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0006J\u000e\u0010]\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0006J\u0014\u0010^\u001a\u00020\u00002\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u001dJ\u000e\u0010_\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\tJ\u000e\u0010`\u001a\u00020\u00002\u0006\u0010/\u001a\u00020\u0006J\u0010\u0010a\u001a\u00020\u00002\b\u00100\u001a\u0004\u0018\u000101J\u0010\u0010b\u001a\u00020\u00002\b\u00102\u001a\u0004\u0018\u000103J\u000e\u0010c\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u0006J\u0006\u0010)\u001a\u00020\u0006R\u0012\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0015\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001d8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001e\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\"\u001a\u00020#8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010$\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010'\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010(\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010)\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R \u0010*\u001a\u0010\u0012\f\u0012\n +*\u0004\u0018\u00010\u00060\u00060\u001d8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010,\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010.\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010/\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\u0004\u0018\u0001018\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u0004\u0018\u0001038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u00104\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "", "configBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;", "(Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;)V", "allowDelay", "", "allowProgressiveOnPrefetch", "animatedCacheMemoryPercentage", "", "animationRenderFpsLimit", "balancedStrategyPreparationMs", "bitmapPrepareToDrawForPrefetch", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawMinSizeBytes", "cancelDecodeOnCacheMiss", "decodeCancellationEnabled", "downsampleIfLargeBitmap", "downscaleFrameToDrawableDimensions", "encodedCacheEnabled", "ensureTranscoderLibraryLoaded", "experimentalThreadHandoffQueueEnabled", "gingerbreadDecoderEnabled", "handOffOnUiThreadOnly", "isDiskCacheProbingEnabled", "isEncodedMemoryCacheProbingEnabled", "isPartialImageCachingEnabled", "keepCancelledFetchAsLowPriority", "lazyDataSource", "Lcom/facebook/common/internal/Supplier;", "maxBitmapSize", "memoryType", "", "nativeCodeDisabled", "platformDecoderOptions", "Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "prefetchShortcutEnabled", "producerFactoryMethod", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "shouldIgnoreCacheSizeMismatch", "shouldStoreCacheEntrySize", "shouldUseDecodingBufferHelper", "suppressBitmapPrefetchingSupplier", "kotlin.jvm.PlatformType", "trackedKeysSize", "useBalancedAnimationStrategy", "useBitmapPrepareToDraw", "useDownsamplingRatioForResizing", "webpBitmapFactory", "Lcom/facebook/common/webp/WebpBitmapFactory;", "webpErrorLogger", "Lcom/facebook/common/webp/WebpBitmapFactory$WebpErrorLogger;", "webpSupportEnabled", "asBuilder", "block", "Lkotlin/Function0;", "", "build", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments;", "setAllowDelay", "setAllowProgressiveOnPrefetch", "setAnimatedCacheMemoryPercentage", "setAnimationRenderFpsLimit", "setBalancedAnimationStrategy", "setBalancedStrategyPreparationMs", "setBitmapPrepareToDraw", "minBitmapSizeBytes", "maxBitmapSizeBytes", "preparePrefetch", "setCancelDecodeOnCacheMiss", "setDecodeCancellationEnabled", "setDownsampleIfLargeBitmap", "setEncodedCacheEnabled", "setEnsureTranscoderLibraryLoaded", "setExperimentalMemoryType", "MemoryType", "setExperimentalThreadHandoffQueueEnabled", "setGingerbreadDecoderEnabled", "setHandOffOnUiThreadOnly", "setIgnoreCacheSizeMismatch", "setIsDiskCacheProbingEnabled", "setIsEncodedMemoryCacheProbingEnabled", "setKeepCancelledFetchAsLowPriority", "setLazyDataSource", "setMaxBitmapSize", "setNativeCodeDisabled", "setPartialImageCachingEnabled", "partialImageCachingEnabled", "setPlatformDecoderOptions", "setPrefetchShortcutEnabled", "setProducerFactoryMethod", "setShouldDownscaleFrameToDrawableDimensions", "setShouldUseDecodingBufferHelper", "setStoreCacheEntrySize", "setSuppressBitmapPrefetchingSupplier", "setTrackedKeysSize", "setUseDownsampligRatioForResizing", "setWebpBitmapFactory", "setWebpErrorLogger", "setWebpSupportEnabled", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImagePipelineExperiments.kt */
    public static final class Builder {
        public boolean allowDelay;
        public boolean allowProgressiveOnPrefetch;
        public int animatedCacheMemoryPercentage = 40;
        public int animationRenderFpsLimit;
        public int balancedStrategyPreparationMs = 10000;
        public boolean bitmapPrepareToDrawForPrefetch;
        public int bitmapPrepareToDrawMaxSizeBytes;
        public int bitmapPrepareToDrawMinSizeBytes;
        public boolean cancelDecodeOnCacheMiss;
        private final ImagePipelineConfig.Builder configBuilder;
        public boolean decodeCancellationEnabled;
        public boolean downsampleIfLargeBitmap;
        public boolean downscaleFrameToDrawableDimensions;
        public boolean encodedCacheEnabled;
        public boolean ensureTranscoderLibraryLoaded;
        public boolean experimentalThreadHandoffQueueEnabled;
        public boolean gingerbreadDecoderEnabled;
        public boolean handOffOnUiThreadOnly;
        public boolean isDiskCacheProbingEnabled;
        public boolean isEncodedMemoryCacheProbingEnabled;
        public boolean isPartialImageCachingEnabled;
        public boolean keepCancelledFetchAsLowPriority;
        public Supplier<Boolean> lazyDataSource;
        public int maxBitmapSize = 2048;
        public long memoryType;
        public boolean nativeCodeDisabled;
        public PlatformDecoderOptions platformDecoderOptions;
        public boolean prefetchShortcutEnabled;
        public ProducerFactoryMethod producerFactoryMethod;
        public boolean shouldIgnoreCacheSizeMismatch;
        public boolean shouldStoreCacheEntrySize;
        public boolean shouldUseDecodingBufferHelper;
        public Supplier<Boolean> suppressBitmapPrefetchingSupplier;
        public int trackedKeysSize;
        public boolean useBalancedAnimationStrategy;
        public boolean useBitmapPrepareToDraw;
        public boolean useDownsamplingRatioForResizing;
        public WebpBitmapFactory webpBitmapFactory;
        public WebpBitmapFactory.WebpErrorLogger webpErrorLogger;
        public boolean webpSupportEnabled;

        public Builder(ImagePipelineConfig.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "configBuilder");
            this.configBuilder = builder;
            Supplier<Boolean> of = Suppliers.of(false);
            Intrinsics.checkNotNullExpressionValue(of, "of(false)");
            this.suppressBitmapPrefetchingSupplier = of;
            this.encodedCacheEnabled = true;
            this.ensureTranscoderLibraryLoaded = true;
            this.trackedKeysSize = 20;
            this.animationRenderFpsLimit = 30;
            this.platformDecoderOptions = new PlatformDecoderOptions(false, false, 3, (DefaultConstructorMarker) null);
        }

        private final Builder asBuilder(Function0<Unit> function0) {
            function0.invoke();
            return this;
        }

        public final Builder setHandOffOnUiThreadOnly(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setHandOffOnUiThreadOnly$1(this, z));
        }

        public final Builder setStoreCacheEntrySize(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setStoreCacheEntrySize$1(this, z));
        }

        public final Builder setIgnoreCacheSizeMismatch(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setIgnoreCacheSizeMismatch$1(this, z));
        }

        public final Builder setWebpSupportEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setWebpSupportEnabled$1(this, z));
        }

        public final Builder setPrefetchShortcutEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setPrefetchShortcutEnabled$1(this, z));
        }

        public final boolean shouldUseDecodingBufferHelper() {
            return this.shouldUseDecodingBufferHelper;
        }

        public final Builder setShouldUseDecodingBufferHelper(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setShouldUseDecodingBufferHelper$1(this, z));
        }

        public final Builder setUseDownsampligRatioForResizing(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setUseDownsampligRatioForResizing$1(this, z));
        }

        public final Builder setPartialImageCachingEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setPartialImageCachingEnabled$1(this, z));
        }

        public final Builder setDecodeCancellationEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setDecodeCancellationEnabled$1(this, z));
        }

        public final Builder setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger2) {
            return asBuilder(new ImagePipelineExperiments$Builder$setWebpErrorLogger$1(this, webpErrorLogger2));
        }

        public final Builder setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory2) {
            return asBuilder(new ImagePipelineExperiments$Builder$setWebpBitmapFactory$1(this, webpBitmapFactory2));
        }

        public final Builder setBitmapPrepareToDraw(boolean z, int i, int i2, boolean z2) {
            return asBuilder(new ImagePipelineExperiments$Builder$setBitmapPrepareToDraw$1(this, z, i, i2, z2));
        }

        public final Builder setBalancedAnimationStrategy(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setBalancedAnimationStrategy$1(this, z));
        }

        public final Builder setBalancedStrategyPreparationMs(int i) {
            return asBuilder(new ImagePipelineExperiments$Builder$setBalancedStrategyPreparationMs$1(this, i));
        }

        public final Builder setAnimatedCacheMemoryPercentage(int i) {
            return asBuilder(new ImagePipelineExperiments$Builder$setAnimatedCacheMemoryPercentage$1(this, i));
        }

        public final Builder setMaxBitmapSize(int i) {
            return asBuilder(new ImagePipelineExperiments$Builder$setMaxBitmapSize$1(this, i));
        }

        public final Builder setNativeCodeDisabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setNativeCodeDisabled$1(this, z));
        }

        public final Builder setProducerFactoryMethod(ProducerFactoryMethod producerFactoryMethod2) {
            return asBuilder(new ImagePipelineExperiments$Builder$setProducerFactoryMethod$1(this, producerFactoryMethod2));
        }

        public final Builder setLazyDataSource(Supplier<Boolean> supplier) {
            return asBuilder(new ImagePipelineExperiments$Builder$setLazyDataSource$1(this, supplier));
        }

        public final Builder setGingerbreadDecoderEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setGingerbreadDecoderEnabled$1(this, z));
        }

        public final Builder setShouldDownscaleFrameToDrawableDimensions(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setShouldDownscaleFrameToDrawableDimensions$1(this, z));
        }

        public final Builder setSuppressBitmapPrefetchingSupplier(Supplier<Boolean> supplier) {
            Intrinsics.checkNotNullParameter(supplier, "suppressBitmapPrefetchingSupplier");
            return asBuilder(new ImagePipelineExperiments$Builder$setSuppressBitmapPrefetchingSupplier$1(this, supplier));
        }

        public final Builder setExperimentalThreadHandoffQueueEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setExperimentalThreadHandoffQueueEnabled$1(this, z));
        }

        public final Builder setExperimentalMemoryType(long j) {
            return asBuilder(new ImagePipelineExperiments$Builder$setExperimentalMemoryType$1(this, j));
        }

        public final Builder setKeepCancelledFetchAsLowPriority(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setKeepCancelledFetchAsLowPriority$1(this, z));
        }

        public final Builder setDownsampleIfLargeBitmap(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setDownsampleIfLargeBitmap$1(this, z));
        }

        public final Builder setEncodedCacheEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setEncodedCacheEnabled$1(this, z));
        }

        public final Builder setEnsureTranscoderLibraryLoaded(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setEnsureTranscoderLibraryLoaded$1(this, z));
        }

        public final Builder setIsDiskCacheProbingEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setIsDiskCacheProbingEnabled$1(this, z));
        }

        public final Builder setIsEncodedMemoryCacheProbingEnabled(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setIsEncodedMemoryCacheProbingEnabled$1(this, z));
        }

        public final Builder setTrackedKeysSize(int i) {
            return asBuilder(new ImagePipelineExperiments$Builder$setTrackedKeysSize$1(this, i));
        }

        public final Builder setAllowDelay(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setAllowDelay$1(this, z));
        }

        public final Builder setAllowProgressiveOnPrefetch(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setAllowProgressiveOnPrefetch$1(this, z));
        }

        public final Builder setAnimationRenderFpsLimit(int i) {
            return asBuilder(new ImagePipelineExperiments$Builder$setAnimationRenderFpsLimit$1(this, i));
        }

        public final Builder setCancelDecodeOnCacheMiss(boolean z) {
            return asBuilder(new ImagePipelineExperiments$Builder$setCancelDecodeOnCacheMiss$1(this, z));
        }

        public final Builder setPlatformDecoderOptions(PlatformDecoderOptions platformDecoderOptions2) {
            Intrinsics.checkNotNullParameter(platformDecoderOptions2, "platformDecoderOptions");
            return asBuilder(new ImagePipelineExperiments$Builder$setPlatformDecoderOptions$1(this, platformDecoderOptions2));
        }

        public final ImagePipelineExperiments build() {
            return new ImagePipelineExperiments(this, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jà\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0016\u0010\u0017\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020%H\u0016¨\u0006-"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$DefaultProducerFactoryMethod;", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$ProducerFactoryMethod;", "()V", "createProducerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", "context", "Landroid/content/Context;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleEnabled", "", "resizeAndRotateEnabledForNetwork", "decodeCancellationEnabled", "executorSupplier", "Lcom/facebook/imagepipeline/core/ExecutorSupplier;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "defaultBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapPrepareToDrawMinSizeBytes", "", "bitmapPrepareToDrawMaxSizeBytes", "bitmapPrepareToDrawForPrefetch", "maxBitmapSize", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "keepCancelledFetchAsLowPriority", "trackedKeysSize", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImagePipelineExperiments.kt */
    public static final class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
        public ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4, int i3, CloseableReferenceFactory closeableReferenceFactory, boolean z5, int i4) {
            Context context2 = context;
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(byteArrayPool, "byteArrayPool");
            Intrinsics.checkNotNullParameter(imageDecoder, "imageDecoder");
            Intrinsics.checkNotNullParameter(progressiveJpegConfig, "progressiveJpegConfig");
            Intrinsics.checkNotNullParameter(executorSupplier, "executorSupplier");
            Intrinsics.checkNotNullParameter(pooledByteBufferFactory, "pooledByteBufferFactory");
            Intrinsics.checkNotNullParameter(pooledByteStreams, "pooledByteStreams");
            Intrinsics.checkNotNullParameter(memoryCache, "bitmapMemoryCache");
            Intrinsics.checkNotNullParameter(memoryCache2, "encodedMemoryCache");
            Intrinsics.checkNotNullParameter(bufferedDiskCache, "defaultBufferedDiskCache");
            Intrinsics.checkNotNullParameter(bufferedDiskCache2, "smallImageBufferedDiskCache");
            Intrinsics.checkNotNullParameter(cacheKeyFactory, "cacheKeyFactory");
            Intrinsics.checkNotNullParameter(platformBitmapFactory, "platformBitmapFactory");
            Intrinsics.checkNotNullParameter(closeableReferenceFactory, "closeableReferenceFactory");
            return new ProducerFactory(context2, byteArrayPool, imageDecoder, progressiveJpegConfig, z, z2, z3, executorSupplier, pooledByteBufferFactory, memoryCache, memoryCache2, bufferedDiskCache, bufferedDiskCache2, cacheKeyFactory, platformBitmapFactory, i, i2, z4, i3, closeableReferenceFactory, z5, i4);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Companion;", "", "()V", "newBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineExperiments$Builder;", "configBuilder", "Lcom/facebook/imagepipeline/core/ImagePipelineConfig$Builder;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ImagePipelineExperiments.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Builder newBuilder(ImagePipelineConfig.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "configBuilder");
            return new Builder(builder);
        }
    }
}
