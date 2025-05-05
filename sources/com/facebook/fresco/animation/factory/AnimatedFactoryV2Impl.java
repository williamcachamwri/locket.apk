package com.facebook.fresco.animation.factory;

import android.content.Context;
import android.graphics.Rect;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.DefaultSerialExecutorService;
import com.facebook.common.executors.SerialExecutorService;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactoryImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendImpl;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.AnimatedCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

public class AnimatedFactoryV2Impl implements AnimatedFactory {
    private static final int BALANCED_STRATEGY_PREPARATION_MS = 10000;
    private static final int NUMBER_OF_FRAMES_TO_PREPARE = 3;
    private final AnimatedCache mAnimatedCache;
    @Nullable
    private AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    @Nullable
    private DrawableFactory mAnimatedDrawableFactory;
    @Nullable
    private AnimatedDrawableUtil mAnimatedDrawableUtil;
    @Nullable
    private AnimatedImageFactory mAnimatedImageFactory;
    private int mAnimationFpsLimit;
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final int mBalancedStrategyPreparationMs;
    /* access modifiers changed from: private */
    public final boolean mDownscaleFrameToDrawableDimensions;
    private final ExecutorSupplier mExecutorSupplier;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    @Nullable
    private SerialExecutorService mSerialExecutorService;
    private final boolean mUseBalancedAnimationStrategy;

    public AnimatedFactoryV2Impl(PlatformBitmapFactory platformBitmapFactory, ExecutorSupplier executorSupplier, CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, AnimatedCache animatedCache, boolean z, boolean z2, int i, int i2, SerialExecutorService serialExecutorService) {
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mExecutorSupplier = executorSupplier;
        this.mBackingCache = countingMemoryCache;
        this.mAnimatedCache = animatedCache;
        this.mAnimationFpsLimit = i2;
        this.mUseBalancedAnimationStrategy = z2;
        this.mDownscaleFrameToDrawableDimensions = z;
        this.mSerialExecutorService = serialExecutorService;
        this.mBalancedStrategyPreparationMs = i;
    }

    @Nullable
    public DrawableFactory getAnimatedDrawableFactory(@Nullable Context context) {
        if (this.mAnimatedDrawableFactory == null) {
            this.mAnimatedDrawableFactory = createDrawableFactory();
        }
        return this.mAnimatedDrawableFactory;
    }

    public ImageDecoder getGifDecoder() {
        return new ImageDecoder() {
            @Nullable
            public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
                return AnimatedFactoryV2Impl.this.getAnimatedImageFactory().decodeGif(encodedImage, imageDecodeOptions, imageDecodeOptions.animatedBitmapConfig);
            }
        };
    }

    public ImageDecoder getWebPDecoder() {
        return new AnimatedFactoryV2Impl$$ExternalSyntheticLambda0(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getWebPDecoder$0$com-facebook-fresco-animation-factory-AnimatedFactoryV2Impl  reason: not valid java name */
    public /* synthetic */ CloseableImage m1210lambda$getWebPDecoder$0$comfacebookfrescoanimationfactoryAnimatedFactoryV2Impl(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        return getAnimatedImageFactory().decodeWebP(encodedImage, imageDecodeOptions, imageDecodeOptions.animatedBitmapConfig);
    }

    private DefaultBitmapAnimationDrawableFactory createDrawableFactory() {
        AnimatedFactoryV2Impl$$ExternalSyntheticLambda1 animatedFactoryV2Impl$$ExternalSyntheticLambda1 = new AnimatedFactoryV2Impl$$ExternalSyntheticLambda1();
        ExecutorService executorService = this.mSerialExecutorService;
        if (executorService == null) {
            executorService = new DefaultSerialExecutorService(this.mExecutorSupplier.forDecode());
        }
        AnimatedFactoryV2Impl$$ExternalSyntheticLambda2 animatedFactoryV2Impl$$ExternalSyntheticLambda2 = new AnimatedFactoryV2Impl$$ExternalSyntheticLambda2();
        Supplier<Boolean> supplier = Suppliers.BOOLEAN_FALSE;
        AnimatedFactoryV2Impl$$ExternalSyntheticLambda3 animatedFactoryV2Impl$$ExternalSyntheticLambda3 = new AnimatedFactoryV2Impl$$ExternalSyntheticLambda3(this);
        return new DefaultBitmapAnimationDrawableFactory(getAnimatedDrawableBackendProvider(), UiThreadImmediateExecutorService.getInstance(), executorService, RealtimeSinceBootClock.get(), this.mPlatformBitmapFactory, this.mBackingCache, animatedFactoryV2Impl$$ExternalSyntheticLambda3, animatedFactoryV2Impl$$ExternalSyntheticLambda1, animatedFactoryV2Impl$$ExternalSyntheticLambda2, supplier, Suppliers.of(Boolean.valueOf(this.mUseBalancedAnimationStrategy)), Suppliers.of(Boolean.valueOf(this.mDownscaleFrameToDrawableDimensions)), Suppliers.of(Integer.valueOf(this.mAnimationFpsLimit)), Suppliers.of(Integer.valueOf(this.mBalancedStrategyPreparationMs)));
    }

    static /* synthetic */ Integer lambda$createDrawableFactory$1() {
        return 2;
    }

    static /* synthetic */ Integer lambda$createDrawableFactory$2() {
        return 3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createDrawableFactory$3$com-facebook-fresco-animation-factory-AnimatedFactoryV2Impl  reason: not valid java name */
    public /* synthetic */ AnimatedCache m1209lambda$createDrawableFactory$3$comfacebookfrescoanimationfactoryAnimatedFactoryV2Impl() {
        return this.mAnimatedCache;
    }

    /* access modifiers changed from: private */
    public AnimatedDrawableUtil getAnimatedDrawableUtil() {
        if (this.mAnimatedDrawableUtil == null) {
            this.mAnimatedDrawableUtil = new AnimatedDrawableUtil();
        }
        return this.mAnimatedDrawableUtil;
    }

    /* access modifiers changed from: private */
    public AnimatedImageFactory getAnimatedImageFactory() {
        if (this.mAnimatedImageFactory == null) {
            this.mAnimatedImageFactory = buildAnimatedImageFactory();
        }
        return this.mAnimatedImageFactory;
    }

    private AnimatedDrawableBackendProvider getAnimatedDrawableBackendProvider() {
        if (this.mAnimatedDrawableBackendProvider == null) {
            this.mAnimatedDrawableBackendProvider = new AnimatedDrawableBackendProvider() {
                public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, @Nullable Rect rect) {
                    return new AnimatedDrawableBackendImpl(AnimatedFactoryV2Impl.this.getAnimatedDrawableUtil(), animatedImageResult, rect, AnimatedFactoryV2Impl.this.mDownscaleFrameToDrawableDimensions);
                }
            };
        }
        return this.mAnimatedDrawableBackendProvider;
    }

    private AnimatedImageFactory buildAnimatedImageFactory() {
        return new AnimatedImageFactoryImpl(new AnimatedDrawableBackendProvider() {
            public AnimatedDrawableBackend get(AnimatedImageResult animatedImageResult, @Nullable Rect rect) {
                return new AnimatedDrawableBackendImpl(AnimatedFactoryV2Impl.this.getAnimatedDrawableUtil(), animatedImageResult, rect, AnimatedFactoryV2Impl.this.mDownscaleFrameToDrawableDimensions);
            }
        }, this.mPlatformBitmapFactory, this.mUseBalancedAnimationStrategy);
    }
}
