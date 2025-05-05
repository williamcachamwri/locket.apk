package com.facebook.fresco.animation.factory;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.time.MonotonicClock;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.fresco.animation.bitmap.cache.AnimationFrameCacheKey;
import com.facebook.fresco.animation.bitmap.cache.FrescoFpsCache;
import com.facebook.fresco.animation.bitmap.cache.FrescoFrameCache;
import com.facebook.fresco.animation.bitmap.cache.KeepLastFrameCache;
import com.facebook.fresco.animation.bitmap.cache.NoOpCache;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer;
import com.facebook.fresco.animation.bitmap.preparation.DefaultBitmapFramePreparer;
import com.facebook.fresco.animation.bitmap.preparation.loadframe.FpsCompressorInfo;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.KAnimatedDrawable2;
import com.facebook.fresco.vito.options.ImageOptions;
import com.facebook.fresco.vito.options.ImageOptionsDrawableFactory;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedImage;
import com.facebook.imagepipeline.animated.base.AnimatedImageResult;
import com.facebook.imagepipeline.animated.impl.AnimatedDrawableBackendProvider;
import com.facebook.imagepipeline.animated.impl.AnimatedFrameCache;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.AnimatedCache;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableAnimatedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

public class DefaultBitmapAnimationDrawableFactory implements DrawableFactory, ImageOptionsDrawableFactory {
    public static final int CACHING_STRATEGY_FRESCO_CACHE = 1;
    public static final int CACHING_STRATEGY_FRESCO_CACHE_NO_REUSING = 2;
    public static final int CACHING_STRATEGY_KEEP_LAST_CACHE = 3;
    public static final int CACHING_STRATEGY_NO_CACHE = 0;
    private final AnimatedDrawableBackendProvider mAnimatedDrawableBackendProvider;
    private final Supplier<AnimatedCache> mAnimatedDrawableCache;
    private final Supplier<Integer> mAnimationFpsLimit;
    private final CountingMemoryCache<CacheKey, CloseableImage> mBackingCache;
    private final Supplier<Integer> mBalancedStrategyPreparationMs;
    private final Supplier<Integer> mCachingStrategySupplier;
    private final Supplier<Boolean> mDownscaleFrameToDrawableDimensions;
    private final ExecutorService mExecutorServiceForFramePreparing;
    private final MonotonicClock mMonotonicClock;
    private final Supplier<Integer> mNumberOfFramesToPrepareSupplier;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final ScheduledExecutorService mScheduledExecutorServiceForUiThread;
    private final Supplier<Boolean> mUseDeepEqualsForCacheKey;
    private final Supplier<Boolean> mUseNewBitmapRender;
    private final Supplier<Boolean> useRendererAnimatedDrawable = Suppliers.BOOLEAN_FALSE;

    public DefaultBitmapAnimationDrawableFactory(AnimatedDrawableBackendProvider animatedDrawableBackendProvider, ScheduledExecutorService scheduledExecutorService, ExecutorService executorService, MonotonicClock monotonicClock, PlatformBitmapFactory platformBitmapFactory, CountingMemoryCache<CacheKey, CloseableImage> countingMemoryCache, Supplier<AnimatedCache> supplier, Supplier<Integer> supplier2, Supplier<Integer> supplier3, Supplier<Boolean> supplier4, Supplier<Boolean> supplier5, Supplier<Boolean> supplier6, Supplier<Integer> supplier7, Supplier<Integer> supplier8) {
        this.mAnimatedDrawableBackendProvider = animatedDrawableBackendProvider;
        this.mScheduledExecutorServiceForUiThread = scheduledExecutorService;
        this.mExecutorServiceForFramePreparing = executorService;
        this.mMonotonicClock = monotonicClock;
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mBackingCache = countingMemoryCache;
        this.mCachingStrategySupplier = supplier2;
        this.mNumberOfFramesToPrepareSupplier = supplier3;
        this.mUseDeepEqualsForCacheKey = supplier4;
        this.mUseNewBitmapRender = supplier5;
        this.mAnimatedDrawableCache = supplier;
        this.mAnimationFpsLimit = supplier7;
        this.mBalancedStrategyPreparationMs = supplier8;
        this.mDownscaleFrameToDrawableDimensions = supplier6;
    }

    public boolean supportsImageType(CloseableImage closeableImage) {
        return closeableImage instanceof CloseableAnimatedImage;
    }

    public Drawable createDrawable(CloseableImage closeableImage) {
        CloseableAnimatedImage closeableAnimatedImage = (CloseableAnimatedImage) closeableImage;
        AnimatedImage image = closeableAnimatedImage.getImage();
        AnimationBackend createAnimationBackend = createAnimationBackend((AnimatedImageResult) Preconditions.checkNotNull(closeableAnimatedImage.getImageResult()), image != null ? image.getAnimatedBitmapConfig() : null, (ImageOptions) null);
        if (this.useRendererAnimatedDrawable.get().booleanValue()) {
            return new KAnimatedDrawable2(createAnimationBackend);
        }
        return new AnimatedDrawable2(createAnimationBackend);
    }

    public Drawable createDrawable(Resources resources, CloseableImage closeableImage, ImageOptions imageOptions) {
        CloseableAnimatedImage closeableAnimatedImage = (CloseableAnimatedImage) closeableImage;
        AnimatedImage image = closeableAnimatedImage.getImage();
        AnimationBackend createAnimationBackend = createAnimationBackend((AnimatedImageResult) Preconditions.checkNotNull(closeableAnimatedImage.getImageResult()), image != null ? image.getAnimatedBitmapConfig() : null, imageOptions);
        if (this.useRendererAnimatedDrawable.get().booleanValue()) {
            return new KAnimatedDrawable2(createAnimationBackend);
        }
        return new AnimatedDrawable2(createAnimationBackend);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.facebook.fresco.animation.bitmap.preparation.FrameLoaderStrategy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy} */
    /* JADX WARNING: type inference failed for: r2v5, types: [com.facebook.fresco.animation.bitmap.preparation.FixedNumberBitmapFramePreparationStrategy] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.fresco.animation.backend.AnimationBackend createAnimationBackend(com.facebook.imagepipeline.animated.base.AnimatedImageResult r11, @javax.annotation.Nullable android.graphics.Bitmap.Config r12, @javax.annotation.Nullable com.facebook.fresco.vito.options.ImageOptions r13) {
        /*
            r10 = this;
            com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend r0 = r10.createAnimatedDrawableBackend(r11)
            com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendAnimationInformation r7 = new com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendAnimationInformation
            r7.<init>(r0)
            com.facebook.fresco.animation.bitmap.BitmapFrameCache r8 = r10.createBitmapFrameCache(r11)
            com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendFrameRenderer r9 = new com.facebook.fresco.animation.bitmap.wrapper.AnimatedDrawableBackendFrameRenderer
            com.facebook.common.internal.Supplier<java.lang.Boolean> r1 = r10.mUseNewBitmapRender
            java.lang.Object r1 = r1.get()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r9.<init>(r8, r0, r1)
            com.facebook.common.internal.Supplier<java.lang.Integer> r0 = r10.mNumberOfFramesToPrepareSupplier
            java.lang.Object r0 = r0.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r1 = 0
            if (r0 <= 0) goto L_0x0037
            com.facebook.fresco.animation.bitmap.preparation.FixedNumberBitmapFramePreparationStrategy r2 = new com.facebook.fresco.animation.bitmap.preparation.FixedNumberBitmapFramePreparationStrategy
            r2.<init>(r0)
            com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer r12 = r10.createBitmapFramePreparer(r9, r12)
            goto L_0x0039
        L_0x0037:
            r12 = r1
            r2 = r12
        L_0x0039:
            if (r13 == 0) goto L_0x0040
            com.facebook.fresco.vito.options.RoundingOptions r13 = r13.getRoundingOptions()
            goto L_0x0041
        L_0x0040:
            r13 = r1
        L_0x0041:
            com.facebook.common.internal.Supplier<java.lang.Boolean> r0 = r10.mUseNewBitmapRender
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00b2
            com.facebook.common.internal.Supplier<java.lang.Integer> r0 = r10.mBalancedStrategyPreparationMs
            java.lang.Object r0 = r0.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            if (r0 == 0) goto L_0x0085
            com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy r11 = new com.facebook.fresco.animation.bitmap.preparation.BalancedAnimationStrategy
            com.facebook.common.internal.Supplier<java.lang.Integer> r0 = r10.mBalancedStrategyPreparationMs
            java.lang.Object r0 = r0.get()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r3 = r0.intValue()
            com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTaskFactory r4 = new com.facebook.fresco.animation.bitmap.preparation.loadframe.LoadFrameTaskFactory
            com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory r0 = r10.mPlatformBitmapFactory
            r4.<init>(r0, r9)
            com.facebook.common.internal.Supplier<java.lang.Boolean> r0 = r10.mDownscaleFrameToDrawableDimensions
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r6 = r0.booleanValue()
            r1 = r11
            r2 = r7
            r5 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x00b3
        L_0x0085:
            com.facebook.fresco.animation.bitmap.preparation.FrameLoaderStrategy r0 = new com.facebook.fresco.animation.bitmap.preparation.FrameLoaderStrategy
            java.lang.String r2 = r11.getSource()
            com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoaderFactory r5 = new com.facebook.fresco.animation.bitmap.preparation.ondemandanimation.FrameLoaderFactory
            com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory r11 = r10.mPlatformBitmapFactory
            com.facebook.common.internal.Supplier<java.lang.Integer> r1 = r10.mAnimationFpsLimit
            java.lang.Object r1 = r1.get()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r5.<init>(r11, r1)
            com.facebook.common.internal.Supplier<java.lang.Boolean> r11 = r10.mDownscaleFrameToDrawableDimensions
            java.lang.Object r11 = r11.get()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r6 = r11.booleanValue()
            r1 = r0
            r3 = r7
            r4 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r11 = r0
            goto L_0x00b3
        L_0x00b2:
            r11 = r2
        L_0x00b3:
            com.facebook.fresco.animation.bitmap.BitmapAnimationBackend r0 = new com.facebook.fresco.animation.bitmap.BitmapAnimationBackend
            com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory r2 = r10.mPlatformBitmapFactory
            com.facebook.common.internal.Supplier<java.lang.Boolean> r1 = r10.mUseNewBitmapRender
            java.lang.Object r1 = r1.get()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r6 = r1.booleanValue()
            r1 = r0
            r3 = r8
            r4 = r7
            r5 = r9
            r7 = r11
            r8 = r12
            r9 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            com.facebook.common.time.MonotonicClock r11 = r10.mMonotonicClock
            java.util.concurrent.ScheduledExecutorService r12 = r10.mScheduledExecutorServiceForUiThread
            com.facebook.fresco.animation.backend.AnimationBackendDelegate r11 = com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck.createForBackend(r0, r11, r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.factory.DefaultBitmapAnimationDrawableFactory.createAnimationBackend(com.facebook.imagepipeline.animated.base.AnimatedImageResult, android.graphics.Bitmap$Config, com.facebook.fresco.vito.options.ImageOptions):com.facebook.fresco.animation.backend.AnimationBackend");
    }

    private BitmapFramePreparer createBitmapFramePreparer(BitmapFrameRenderer bitmapFrameRenderer, @Nullable Bitmap.Config config) {
        PlatformBitmapFactory platformBitmapFactory = this.mPlatformBitmapFactory;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return new DefaultBitmapFramePreparer(platformBitmapFactory, bitmapFrameRenderer, config, this.mExecutorServiceForFramePreparing);
    }

    private AnimatedDrawableBackend createAnimatedDrawableBackend(AnimatedImageResult animatedImageResult) {
        AnimatedImage image = animatedImageResult.getImage();
        return this.mAnimatedDrawableBackendProvider.get(animatedImageResult, new Rect(0, 0, image.getWidth(), image.getHeight()));
    }

    private BitmapFrameCache createBitmapFrameCache(AnimatedImageResult animatedImageResult) {
        if (this.mUseNewBitmapRender.get().booleanValue()) {
            return new FrescoFpsCache(animatedImageResult, new FpsCompressorInfo(this.mAnimationFpsLimit.get().intValue()), this.mAnimatedDrawableCache.get());
        }
        int intValue = this.mCachingStrategySupplier.get().intValue();
        if (intValue == 1) {
            return new FrescoFrameCache(createAnimatedFrameCache(animatedImageResult), true);
        }
        if (intValue == 2) {
            return new FrescoFrameCache(createAnimatedFrameCache(animatedImageResult), false);
        }
        if (intValue != 3) {
            return new NoOpCache();
        }
        return new KeepLastFrameCache();
    }

    private AnimatedFrameCache createAnimatedFrameCache(AnimatedImageResult animatedImageResult) {
        return new AnimatedFrameCache(new AnimationFrameCacheKey(animatedImageResult.hashCode(), this.mUseDeepEqualsForCacheKey.get().booleanValue()), this.mBackingCache);
    }
}
