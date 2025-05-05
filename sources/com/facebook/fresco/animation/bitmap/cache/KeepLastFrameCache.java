package com.facebook.fresco.animation.bitmap.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.imageutils.BitmapUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0002J(\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0016J\u0018\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\tH\u0016J&\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\tH\u0016J&\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\t2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001a\u001a\u00020\tH\u0016J\u0012\u0010\u001c\u001a\u00020\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/cache/KeepLastFrameCache;", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "()V", "frameCacheListener", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache$FrameCacheListener;", "lastBitmapReference", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "lastFrameNumber", "", "sizeInBytes", "getSizeInBytes", "()I", "clear", "", "closeAndResetLastBitmapReference", "contains", "", "frameNumber", "getBitmapToReuseForFrame", "width", "height", "getCachedFrame", "getFallbackFrame", "onFramePrepared", "bitmapReference", "frameType", "onFrameRendered", "setFrameCacheListener", "Companion", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KeepLastFrameCache.kt */
public final class KeepLastFrameCache implements BitmapFrameCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int FRAME_NUMBER_UNSET = -1;
    private BitmapFrameCache.FrameCacheListener frameCacheListener;
    private CloseableReference<Bitmap> lastBitmapReference;
    private int lastFrameNumber = -1;

    public void onFramePrepared(int i, CloseableReference<Bitmap> closeableReference, int i2) {
        Intrinsics.checkNotNullParameter(closeableReference, "bitmapReference");
    }

    public boolean isAnimationReady() {
        return BitmapFrameCache.DefaultImpls.isAnimationReady(this);
    }

    public boolean onAnimationPrepared(Map<Integer, ? extends CloseableReference<Bitmap>> map) {
        return BitmapFrameCache.DefaultImpls.onAnimationPrepared(this, map);
    }

    public synchronized CloseableReference<Bitmap> getCachedFrame(int i) {
        return this.lastFrameNumber == i ? CloseableReference.cloneOrNull(this.lastBitmapReference) : null;
    }

    public synchronized CloseableReference<Bitmap> getFallbackFrame(int i) {
        return CloseableReference.cloneOrNull(this.lastBitmapReference);
    }

    public synchronized CloseableReference<Bitmap> getBitmapToReuseForFrame(int i, int i2, int i3) {
        CloseableReference<Bitmap> cloneOrNull;
        try {
            cloneOrNull = CloseableReference.cloneOrNull(this.lastBitmapReference);
            closeAndResetLastBitmapReference();
        } catch (Throwable th) {
            closeAndResetLastBitmapReference();
            throw th;
        }
        return cloneOrNull;
    }

    public synchronized boolean contains(int i) {
        return i == this.lastFrameNumber && CloseableReference.isValid(this.lastBitmapReference);
    }

    public synchronized int getSizeInBytes() {
        int i;
        CloseableReference<Bitmap> closeableReference = this.lastBitmapReference;
        if (closeableReference == null) {
            i = 0;
        } else {
            Intrinsics.checkNotNull(closeableReference);
            i = BitmapUtil.getSizeInBytes(closeableReference.get());
        }
        return i;
    }

    public synchronized void clear() {
        closeAndResetLastBitmapReference();
    }

    public synchronized void onFrameRendered(int i, CloseableReference<Bitmap> closeableReference, int i2) {
        BitmapFrameCache.FrameCacheListener frameCacheListener2;
        Intrinsics.checkNotNullParameter(closeableReference, "bitmapReference");
        if (this.lastBitmapReference != null) {
            Bitmap bitmap = closeableReference.get();
            CloseableReference<Bitmap> closeableReference2 = this.lastBitmapReference;
            if (Intrinsics.areEqual((Object) bitmap, (Object) closeableReference2 != null ? closeableReference2.get() : null)) {
                return;
            }
        }
        CloseableReference.closeSafely((CloseableReference<?>) this.lastBitmapReference);
        int i3 = this.lastFrameNumber;
        if (!(i3 == -1 || (frameCacheListener2 = this.frameCacheListener) == null)) {
            frameCacheListener2.onFrameEvicted(this, i3);
        }
        this.lastBitmapReference = CloseableReference.cloneOrNull(closeableReference);
        BitmapFrameCache.FrameCacheListener frameCacheListener3 = this.frameCacheListener;
        if (frameCacheListener3 != null) {
            frameCacheListener3.onFrameCached(this, i);
        }
        this.lastFrameNumber = i;
    }

    public void setFrameCacheListener(BitmapFrameCache.FrameCacheListener frameCacheListener2) {
        this.frameCacheListener = frameCacheListener2;
    }

    private final synchronized void closeAndResetLastBitmapReference() {
        BitmapFrameCache.FrameCacheListener frameCacheListener2;
        int i = this.lastFrameNumber;
        if (!(i == -1 || (frameCacheListener2 = this.frameCacheListener) == null)) {
            frameCacheListener2.onFrameEvicted(this, i);
        }
        CloseableReference.closeSafely((CloseableReference<?>) this.lastBitmapReference);
        this.lastBitmapReference = null;
        this.lastFrameNumber = -1;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/cache/KeepLastFrameCache$Companion;", "", "()V", "FRAME_NUMBER_UNSET", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KeepLastFrameCache.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
