package com.facebook.fresco.animation.bitmap.preparation;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.bitmap.BitmapFrameCache;
import com.facebook.fresco.animation.bitmap.BitmapFrameRenderer;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001aB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer;", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;Landroid/graphics/Bitmap$Config;Ljava/util/concurrent/ExecutorService;)V", "TAG", "Ljava/lang/Class;", "pendingFrameDecodeJobs", "Landroid/util/SparseArray;", "Ljava/lang/Runnable;", "getUniqueId", "", "backend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "frameNumber", "prepareFrame", "", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationBackend", "FrameDecodeRunnable", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultBitmapFramePreparer.kt */
public final class DefaultBitmapFramePreparer implements BitmapFramePreparer {
    /* access modifiers changed from: private */
    public final Class<DefaultBitmapFramePreparer> TAG = DefaultBitmapFramePreparer.class;
    /* access modifiers changed from: private */
    public final Bitmap.Config bitmapConfig;
    /* access modifiers changed from: private */
    public final BitmapFrameRenderer bitmapFrameRenderer;
    private final ExecutorService executorService;
    /* access modifiers changed from: private */
    public final SparseArray<Runnable> pendingFrameDecodeJobs = new SparseArray<>();
    /* access modifiers changed from: private */
    public final PlatformBitmapFactory platformBitmapFactory;

    public DefaultBitmapFramePreparer(PlatformBitmapFactory platformBitmapFactory2, BitmapFrameRenderer bitmapFrameRenderer2, Bitmap.Config config, ExecutorService executorService2) {
        Intrinsics.checkNotNullParameter(platformBitmapFactory2, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer2, "bitmapFrameRenderer");
        Intrinsics.checkNotNullParameter(config, "bitmapConfig");
        Intrinsics.checkNotNullParameter(executorService2, "executorService");
        this.platformBitmapFactory = platformBitmapFactory2;
        this.bitmapFrameRenderer = bitmapFrameRenderer2;
        this.bitmapConfig = config;
        this.executorService = executorService2;
    }

    public boolean prepareFrame(BitmapFrameCache bitmapFrameCache, AnimationBackend animationBackend, int i) {
        Intrinsics.checkNotNullParameter(bitmapFrameCache, "bitmapFrameCache");
        Intrinsics.checkNotNullParameter(animationBackend, "animationBackend");
        int uniqueId = getUniqueId(animationBackend, i);
        synchronized (this.pendingFrameDecodeJobs) {
            if (this.pendingFrameDecodeJobs.get(uniqueId) != null) {
                FLog.v((Class<?>) this.TAG, "Already scheduled decode job for frame %d", (Object) Integer.valueOf(i));
                return true;
            } else if (bitmapFrameCache.contains(i)) {
                FLog.v((Class<?>) this.TAG, "Frame %d is cached already.", (Object) Integer.valueOf(i));
                return true;
            } else {
                FrameDecodeRunnable frameDecodeRunnable = new FrameDecodeRunnable(this, animationBackend, bitmapFrameCache, i, uniqueId);
                this.pendingFrameDecodeJobs.put(uniqueId, frameDecodeRunnable);
                this.executorService.execute(frameDecodeRunnable);
                Unit unit = Unit.INSTANCE;
                return true;
            }
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002J(\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer$FrameDecodeRunnable;", "Ljava/lang/Runnable;", "animationBackend", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "frameNumber", "", "frameId", "(Lcom/facebook/fresco/animation/bitmap/preparation/DefaultBitmapFramePreparer;Lcom/facebook/fresco/animation/backend/AnimationBackend;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;II)V", "prepareFrameAndCache", "", "frameType", "renderFrameAndCache", "bitmapReference", "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "run", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultBitmapFramePreparer.kt */
    private final class FrameDecodeRunnable implements Runnable {
        private final AnimationBackend animationBackend;
        private final BitmapFrameCache bitmapFrameCache;
        private final int frameId;
        private final int frameNumber;
        final /* synthetic */ DefaultBitmapFramePreparer this$0;

        public FrameDecodeRunnable(DefaultBitmapFramePreparer defaultBitmapFramePreparer, AnimationBackend animationBackend2, BitmapFrameCache bitmapFrameCache2, int i, int i2) {
            Intrinsics.checkNotNullParameter(animationBackend2, "animationBackend");
            Intrinsics.checkNotNullParameter(bitmapFrameCache2, "bitmapFrameCache");
            this.this$0 = defaultBitmapFramePreparer;
            this.animationBackend = animationBackend2;
            this.bitmapFrameCache = bitmapFrameCache2;
            this.frameNumber = i;
            this.frameId = i2;
        }

        public void run() {
            try {
                if (this.bitmapFrameCache.contains(this.frameNumber)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Frame %d is cached already.", (Object) Integer.valueOf(this.frameNumber));
                    SparseArray access$getPendingFrameDecodeJobs$p = this.this$0.pendingFrameDecodeJobs;
                    DefaultBitmapFramePreparer defaultBitmapFramePreparer = this.this$0;
                    synchronized (access$getPendingFrameDecodeJobs$p) {
                        defaultBitmapFramePreparer.pendingFrameDecodeJobs.remove(this.frameId);
                        Unit unit = Unit.INSTANCE;
                    }
                    return;
                }
                if (prepareFrameAndCache(this.frameNumber, 1)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Prepared frame %d.", (Object) Integer.valueOf(this.frameNumber));
                } else {
                    FLog.e((Class<?>) this.this$0.TAG, "Could not prepare frame %d.", Integer.valueOf(this.frameNumber));
                }
                SparseArray access$getPendingFrameDecodeJobs$p2 = this.this$0.pendingFrameDecodeJobs;
                DefaultBitmapFramePreparer defaultBitmapFramePreparer2 = this.this$0;
                synchronized (access$getPendingFrameDecodeJobs$p2) {
                    defaultBitmapFramePreparer2.pendingFrameDecodeJobs.remove(this.frameId);
                    Unit unit2 = Unit.INSTANCE;
                }
            } catch (Throwable th) {
                SparseArray access$getPendingFrameDecodeJobs$p3 = this.this$0.pendingFrameDecodeJobs;
                DefaultBitmapFramePreparer defaultBitmapFramePreparer3 = this.this$0;
                synchronized (access$getPendingFrameDecodeJobs$p3) {
                    defaultBitmapFramePreparer3.pendingFrameDecodeJobs.remove(this.frameId);
                    Unit unit3 = Unit.INSTANCE;
                    throw th;
                }
            }
        }

        private final boolean prepareFrameAndCache(int i, int i2) {
            CloseableReference<Bitmap> closeableReference;
            int i3 = 2;
            if (i2 == 1) {
                closeableReference = this.bitmapFrameCache.getBitmapToReuseForFrame(i, this.animationBackend.getIntrinsicWidth(), this.animationBackend.getIntrinsicHeight());
            } else if (i2 != 2) {
                CloseableReference.closeSafely((CloseableReference<?>) null);
                return false;
            } else {
                try {
                    closeableReference = this.this$0.platformBitmapFactory.createBitmap(this.animationBackend.getIntrinsicWidth(), this.animationBackend.getIntrinsicHeight(), this.this$0.bitmapConfig);
                    i3 = -1;
                } catch (RuntimeException e) {
                    FLog.w((Class<?>) this.this$0.TAG, "Failed to create frame bitmap", (Throwable) e);
                    CloseableReference.closeSafely((CloseableReference<?>) null);
                    return false;
                } catch (Throwable th) {
                    CloseableReference.closeSafely((CloseableReference<?>) null);
                    throw th;
                }
            }
            boolean renderFrameAndCache = renderFrameAndCache(i, closeableReference, i2);
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            if (renderFrameAndCache || i3 == -1) {
                return renderFrameAndCache;
            }
            return prepareFrameAndCache(i, i3);
        }

        private final boolean renderFrameAndCache(int i, CloseableReference<Bitmap> closeableReference, int i2) {
            if (CloseableReference.isValid(closeableReference) && closeableReference != null) {
                BitmapFrameRenderer access$getBitmapFrameRenderer$p = this.this$0.bitmapFrameRenderer;
                Bitmap bitmap = closeableReference.get();
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
                if (access$getBitmapFrameRenderer$p.renderFrame(i, bitmap)) {
                    FLog.v((Class<?>) this.this$0.TAG, "Frame %d ready.", (Object) Integer.valueOf(i));
                    synchronized (this.this$0.pendingFrameDecodeJobs) {
                        this.bitmapFrameCache.onFramePrepared(i, closeableReference, i2);
                        Unit unit = Unit.INSTANCE;
                    }
                    return true;
                }
            }
            return false;
        }
    }

    private final int getUniqueId(AnimationBackend animationBackend, int i) {
        return (animationBackend.hashCode() * 31) + i;
    }
}
