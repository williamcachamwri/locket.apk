package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imagepipeline.animated.impl.AnimatedFrameCache;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imageutils.BitmapUtil;
import java.util.Map;
import javax.annotation.Nullable;

public class FrescoFrameCache implements BitmapFrameCache {
    private static final Class<?> TAG = FrescoFrameCache.class;
    private final AnimatedFrameCache mAnimatedFrameCache;
    private final boolean mEnableBitmapReusing;
    @Nullable
    private CloseableReference<CloseableImage> mLastRenderedItem;
    private final SparseArray<CloseableReference<CloseableImage>> mPreparedPendingFrames = new SparseArray<>();

    public boolean isAnimationReady() {
        return false;
    }

    public boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        return true;
    }

    public void setFrameCacheListener(@Nullable BitmapFrameCache.FrameCacheListener frameCacheListener) {
    }

    public FrescoFrameCache(AnimatedFrameCache animatedFrameCache, boolean z) {
        this.mAnimatedFrameCache = animatedFrameCache;
        this.mEnableBitmapReusing = z;
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getCachedFrame(int i) {
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.get(i));
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getFallbackFrame(int i) {
        return convertToBitmapReferenceAndClose(CloseableReference.cloneOrNull(this.mLastRenderedItem));
    }

    @Nullable
    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3) {
        if (!this.mEnableBitmapReusing) {
            return null;
        }
        return convertToBitmapReferenceAndClose(this.mAnimatedFrameCache.getForReuse());
    }

    public synchronized boolean contains(int i) {
        return this.mAnimatedFrameCache.contains(i);
    }

    public synchronized int getSizeInBytes() {
        return getBitmapSizeBytes(this.mLastRenderedItem) + getPreparedPendingFramesSizeBytes();
    }

    public synchronized void clear() {
        CloseableReference.closeSafely((CloseableReference<?>) this.mLastRenderedItem);
        this.mLastRenderedItem = null;
        for (int i = 0; i < this.mPreparedPendingFrames.size(); i++) {
            CloseableReference.closeSafely((CloseableReference<?>) this.mPreparedPendingFrames.valueAt(i));
        }
        this.mPreparedPendingFrames.clear();
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:15:0x0024=Splitter:B:15:0x0024, B:9:0x001d=Splitter:B:9:0x001d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onFrameRendered(int r1, com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r2, int r3) {
        /*
            r0 = this;
            monitor-enter(r0)
            com.facebook.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0028 }
            r0.removePreparedReference(r1)     // Catch:{ all -> 0x0028 }
            com.facebook.common.references.CloseableReference r2 = createImageReference(r2)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x001d
            com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r3 = r0.mLastRenderedItem     // Catch:{ all -> 0x001b }
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r3)     // Catch:{ all -> 0x001b }
            com.facebook.imagepipeline.animated.impl.AnimatedFrameCache r3 = r0.mAnimatedFrameCache     // Catch:{ all -> 0x001b }
            com.facebook.common.references.CloseableReference r1 = r3.cache(r1, r2)     // Catch:{ all -> 0x001b }
            r0.mLastRenderedItem = r1     // Catch:{ all -> 0x001b }
            goto L_0x001d
        L_0x001b:
            r1 = move-exception
            goto L_0x0024
        L_0x001d:
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r2)     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)
            return
        L_0x0022:
            r1 = move-exception
            r2 = 0
        L_0x0024:
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r2)     // Catch:{ all -> 0x0028 }
            throw r1     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.cache.FrescoFrameCache.onFrameRendered(int, com.facebook.common.references.CloseableReference, int):void");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0041=Splitter:B:21:0x0041, B:14:0x0038=Splitter:B:14:0x0038} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onFramePrepared(int r3, com.facebook.common.references.CloseableReference<android.graphics.Bitmap> r4, int r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.facebook.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0045 }
            com.facebook.common.references.CloseableReference r4 = createImageReference(r4)     // Catch:{ all -> 0x003f }
            if (r4 != 0) goto L_0x000f
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r4)     // Catch:{ all -> 0x0045 }
            monitor-exit(r2)
            return
        L_0x000f:
            com.facebook.imagepipeline.animated.impl.AnimatedFrameCache r5 = r2.mAnimatedFrameCache     // Catch:{ all -> 0x003d }
            com.facebook.common.references.CloseableReference r5 = r5.cache(r3, r4)     // Catch:{ all -> 0x003d }
            boolean r0 = com.facebook.common.references.CloseableReference.isValid(r5)     // Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x0038
            android.util.SparseArray<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r0 = r2.mPreparedPendingFrames     // Catch:{ all -> 0x003d }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x003d }
            com.facebook.common.references.CloseableReference r0 = (com.facebook.common.references.CloseableReference) r0     // Catch:{ all -> 0x003d }
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r0)     // Catch:{ all -> 0x003d }
            android.util.SparseArray<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r0 = r2.mPreparedPendingFrames     // Catch:{ all -> 0x003d }
            r0.put(r3, r5)     // Catch:{ all -> 0x003d }
            java.lang.Class<?> r5 = TAG     // Catch:{ all -> 0x003d }
            java.lang.String r0 = "cachePreparedFrame(%d) cached. Pending frames: %s"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x003d }
            android.util.SparseArray<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r1 = r2.mPreparedPendingFrames     // Catch:{ all -> 0x003d }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r5, (java.lang.String) r0, (java.lang.Object) r3, (java.lang.Object) r1)     // Catch:{ all -> 0x003d }
        L_0x0038:
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r4)     // Catch:{ all -> 0x0045 }
            monitor-exit(r2)
            return
        L_0x003d:
            r3 = move-exception
            goto L_0x0041
        L_0x003f:
            r3 = move-exception
            r4 = 0
        L_0x0041:
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r4)     // Catch:{ all -> 0x0045 }
            throw r3     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.animation.bitmap.cache.FrescoFrameCache.onFramePrepared(int, com.facebook.common.references.CloseableReference, int):void");
    }

    private synchronized int getPreparedPendingFramesSizeBytes() {
        int i;
        i = 0;
        for (int i2 = 0; i2 < this.mPreparedPendingFrames.size(); i2++) {
            i += getBitmapSizeBytes(this.mPreparedPendingFrames.valueAt(i2));
        }
        return i;
    }

    private synchronized void removePreparedReference(int i) {
        CloseableReference closeableReference = this.mPreparedPendingFrames.get(i);
        if (closeableReference != null) {
            this.mPreparedPendingFrames.delete(i);
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            FLog.v(TAG, "removePreparedReference(%d) removed. Pending frames: %s", (Object) Integer.valueOf(i), (Object) this.mPreparedPendingFrames);
        }
    }

    @Nullable
    static CloseableReference<Bitmap> convertToBitmapReferenceAndClose(@Nullable CloseableReference<CloseableImage> closeableReference) {
        CloseableStaticBitmap closeableStaticBitmap;
        try {
            if (CloseableReference.isValid(closeableReference) && (closeableReference.get() instanceof CloseableStaticBitmap) && (closeableStaticBitmap = (CloseableStaticBitmap) closeableReference.get()) != null) {
                return closeableStaticBitmap.cloneUnderlyingBitmapReference();
            }
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            return null;
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
    }

    private static int getBitmapSizeBytes(@Nullable CloseableReference<CloseableImage> closeableReference) {
        if (!CloseableReference.isValid(closeableReference)) {
            return 0;
        }
        return getBitmapSizeBytes(closeableReference.get());
    }

    private static int getBitmapSizeBytes(@Nullable CloseableImage closeableImage) {
        if (!(closeableImage instanceof CloseableBitmap)) {
            return 0;
        }
        return BitmapUtil.getSizeInBytes(((CloseableBitmap) closeableImage).getUnderlyingBitmap());
    }

    @Nullable
    private static CloseableReference<CloseableImage> createImageReference(CloseableReference<Bitmap> closeableReference) {
        return CloseableReference.of(CloseableStaticBitmap.of(closeableReference, ImmutableQualityInfo.FULL_QUALITY, 0));
    }
}
