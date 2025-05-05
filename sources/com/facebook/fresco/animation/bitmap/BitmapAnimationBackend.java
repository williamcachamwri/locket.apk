package com.facebook.fresco.animation.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck;
import com.facebook.fresco.animation.backend.AnimationInformation;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparationStrategy;
import com.facebook.fresco.animation.bitmap.preparation.BitmapFramePreparer;
import com.facebook.fresco.vito.options.RoundingOptions;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u0000 U2\u00020\u00012\u00020\u0002:\u0003UVWBM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J\b\u0010*\u001a\u00020+H\u0016J \u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0002J0\u00102\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020/\u0018\u0001042\u0006\u00100\u001a\u0002012\u0006\u00105\u001a\u00020\u0019H\u0002J \u00106\u001a\u00020\f2\u0006\u00107\u001a\u0002082\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u0019H\u0016J \u00109\u001a\u00020\f2\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u00192\u0006\u00105\u001a\u00020\u0019H\u0002J\b\u0010:\u001a\u00020\u0019H\u0016J\u0010\u0010;\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u0019H\u0016J\b\u0010<\u001a\u00020\u0019H\u0016J\b\u0010=\u001a\u00020\u0019H\u0016J\b\u0010>\u001a\u00020\u0019H\u0016J\b\u0010?\u001a\u00020\u0019H\u0016J\b\u0010@\u001a\u00020\u0019H\u0016J\b\u0010A\u001a\u00020\u0019H\u0016J\b\u0010B\u001a\u00020+H\u0016J\b\u0010C\u001a\u00020+H\u0016J \u0010D\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u000e\u0010E\u001a\n\u0012\u0004\u0012\u00020/\u0018\u000104H\u0002J\u0012\u0010F\u001a\u00020+2\b\b\u0001\u0010G\u001a\u00020\u0019H\u0016J\u0012\u0010H\u001a\u00020+2\b\u0010I\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010J\u001a\u00020+2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0012\u0010K\u001a\u00020+2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0010\u0010N\u001a\u00020+2\b\u0010!\u001a\u0004\u0018\u00010\"J\b\u0010O\u001a\u00020+H\u0002J(\u0010P\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020/2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020RH\u0002J\b\u0010T\u001a\u00020\u0019H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend;", "Lcom/facebook/fresco/animation/backend/AnimationBackend;", "Lcom/facebook/fresco/animation/backend/AnimationBackendDelegateWithInactivityCheck$InactivityListener;", "platformBitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "bitmapFrameCache", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;", "animationInformation", "Lcom/facebook/fresco/animation/backend/AnimationInformation;", "bitmapFrameRenderer", "Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;", "isNewRenderImplementation", "", "bitmapFramePreparationStrategy", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;", "bitmapFramePreparer", "Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;", "roundingOptions", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "(Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;Lcom/facebook/fresco/animation/bitmap/BitmapFrameCache;Lcom/facebook/fresco/animation/backend/AnimationInformation;Lcom/facebook/fresco/animation/bitmap/BitmapFrameRenderer;ZLcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparationStrategy;Lcom/facebook/fresco/animation/bitmap/preparation/BitmapFramePreparer;Lcom/facebook/fresco/vito/options/RoundingOptions;)V", "animationListener", "Lcom/facebook/fresco/animation/backend/AnimationBackend$Listener;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "bitmapHeight", "", "bitmapWidth", "bounds", "Landroid/graphics/Rect;", "cornerRadii", "", "getCornerRadii", "()[F", "frameListener", "Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameListener;", "matrix", "Landroid/graphics/Matrix;", "paint", "Landroid/graphics/Paint;", "path", "Landroid/graphics/Path;", "pathFrameNumber", "clear", "", "drawBitmap", "frameNumber", "bitmap", "Landroid/graphics/Bitmap;", "canvas", "Landroid/graphics/Canvas;", "drawBitmapAndCache", "bitmapReference", "Lcom/facebook/common/references/CloseableReference;", "frameType", "drawFrame", "parent", "Landroid/graphics/drawable/Drawable;", "drawFrameOrFallback", "getFrameCount", "getFrameDurationMs", "getIntrinsicHeight", "getIntrinsicWidth", "getLoopCount", "getLoopDurationMs", "getSizeInBytes", "height", "onInactive", "preloadAnimation", "renderFrameInBitmap", "targetBitmap", "setAlpha", "alpha", "setAnimationListener", "listener", "setBounds", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "setFrameListener", "updateBitmapDimensions", "updatePath", "currentBoundsWidth", "", "currentBoundsHeight", "width", "Companion", "FrameListener", "FrameType", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BitmapAnimationBackend.kt */
public final class BitmapAnimationBackend implements AnimationBackend, AnimationBackendDelegateWithInactivityCheck.InactivityListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int FRAME_TYPE_CACHED = 0;
    public static final int FRAME_TYPE_CREATED = 2;
    public static final int FRAME_TYPE_FALLBACK = 3;
    public static final int FRAME_TYPE_REUSED = 1;
    public static final int FRAME_TYPE_UNKNOWN = -1;
    private static final Class<BitmapAnimationBackend> TAG = BitmapAnimationBackend.class;
    private final AnimationInformation animationInformation;
    /* access modifiers changed from: private */
    public AnimationBackend.Listener animationListener;
    private final Bitmap.Config bitmapConfig;
    private final BitmapFrameCache bitmapFrameCache;
    private final BitmapFramePreparationStrategy bitmapFramePreparationStrategy;
    private final BitmapFramePreparer bitmapFramePreparer;
    private final BitmapFrameRenderer bitmapFrameRenderer;
    private int bitmapHeight;
    private int bitmapWidth;
    private Rect bounds;
    private final float[] cornerRadii;
    private FrameListener frameListener;
    private final boolean isNewRenderImplementation;
    private final Matrix matrix;
    private final Paint paint;
    private final Path path;
    private int pathFrameNumber;
    private final PlatformBitmapFactory platformBitmapFactory;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameListener;", "", "onDrawFrameStart", "", "backend", "Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend;", "frameNumber", "", "onFrameDrawn", "frameType", "onFrameDropped", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BitmapAnimationBackend.kt */
    public interface FrameListener {
        void onDrawFrameStart(BitmapAnimationBackend bitmapAnimationBackend, int i);

        void onFrameDrawn(BitmapAnimationBackend bitmapAnimationBackend, int i, int i2);

        void onFrameDropped(BitmapAnimationBackend bitmapAnimationBackend, int i);
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$FrameType;", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(AnnotationRetention.SOURCE)
    @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
    /* compiled from: BitmapAnimationBackend.kt */
    public @interface FrameType {
    }

    public BitmapAnimationBackend(PlatformBitmapFactory platformBitmapFactory2, BitmapFrameCache bitmapFrameCache2, AnimationInformation animationInformation2, BitmapFrameRenderer bitmapFrameRenderer2, boolean z, BitmapFramePreparationStrategy bitmapFramePreparationStrategy2, BitmapFramePreparer bitmapFramePreparer2, RoundingOptions roundingOptions) {
        float[] fArr;
        Intrinsics.checkNotNullParameter(platformBitmapFactory2, "platformBitmapFactory");
        Intrinsics.checkNotNullParameter(bitmapFrameCache2, "bitmapFrameCache");
        Intrinsics.checkNotNullParameter(animationInformation2, "animationInformation");
        Intrinsics.checkNotNullParameter(bitmapFrameRenderer2, "bitmapFrameRenderer");
        this.platformBitmapFactory = platformBitmapFactory2;
        this.bitmapFrameCache = bitmapFrameCache2;
        this.animationInformation = animationInformation2;
        this.bitmapFrameRenderer = bitmapFrameRenderer2;
        this.isNewRenderImplementation = z;
        this.bitmapFramePreparationStrategy = bitmapFramePreparationStrategy2;
        this.bitmapFramePreparer = bitmapFramePreparer2;
        if (roundingOptions != null) {
            if (!(roundingOptions.getCornerRadius() == 0.0f)) {
                fArr = new float[8];
                ArraysKt.fill$default(fArr, roundingOptions.getCornerRadius(), 0, 0, 6, (Object) null);
            } else {
                fArr = roundingOptions.getCornerRadii();
            }
        } else {
            fArr = null;
        }
        this.cornerRadii = fArr;
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.paint = new Paint(6);
        this.path = new Path();
        this.matrix = new Matrix();
        this.pathFrameNumber = -1;
        updateBitmapDimensions();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BitmapAnimationBackend(PlatformBitmapFactory platformBitmapFactory2, BitmapFrameCache bitmapFrameCache2, AnimationInformation animationInformation2, BitmapFrameRenderer bitmapFrameRenderer2, boolean z, BitmapFramePreparationStrategy bitmapFramePreparationStrategy2, BitmapFramePreparer bitmapFramePreparer2, RoundingOptions roundingOptions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(platformBitmapFactory2, bitmapFrameCache2, animationInformation2, bitmapFrameRenderer2, z, bitmapFramePreparationStrategy2, bitmapFramePreparer2, (i & 128) != 0 ? null : roundingOptions);
    }

    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    public final void setFrameListener(FrameListener frameListener2) {
        this.frameListener = frameListener2;
    }

    public int getFrameCount() {
        return this.animationInformation.getFrameCount();
    }

    public int getFrameDurationMs(int i) {
        return this.animationInformation.getFrameDurationMs(i);
    }

    public int width() {
        return this.animationInformation.width();
    }

    public int height() {
        return this.animationInformation.height();
    }

    public int getLoopDurationMs() {
        return this.animationInformation.getLoopDurationMs();
    }

    public int getLoopCount() {
        return this.animationInformation.getLoopCount();
    }

    public boolean drawFrame(Drawable drawable, Canvas canvas, int i) {
        BitmapFramePreparer bitmapFramePreparer2;
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy2;
        FrameListener frameListener2;
        Intrinsics.checkNotNullParameter(drawable, "parent");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        FrameListener frameListener3 = this.frameListener;
        if (frameListener3 != null) {
            frameListener3.onDrawFrameStart(this, i);
        }
        boolean drawFrameOrFallback = drawFrameOrFallback(canvas, i, 0);
        if (!drawFrameOrFallback && (frameListener2 = this.frameListener) != null) {
            frameListener2.onFrameDropped(this, i);
        }
        if (!(this.isNewRenderImplementation || (bitmapFramePreparer2 = this.bitmapFramePreparer) == null || (bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy) == null)) {
            BitmapFramePreparationStrategy.DefaultImpls.prepareFrames$default(bitmapFramePreparationStrategy2, bitmapFramePreparer2, this.bitmapFrameCache, this, i, (Function0) null, 16, (Object) null);
        }
        return drawFrameOrFallback;
    }

    private final boolean drawFrameOrFallback(Canvas canvas, int i, int i2) {
        boolean z;
        CloseableReference<Bitmap> closeableReference = null;
        try {
            boolean z2 = false;
            int i3 = 1;
            if (this.isNewRenderImplementation) {
                BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
                CloseableReference<Bitmap> bitmapFrame = bitmapFramePreparationStrategy2 != null ? bitmapFramePreparationStrategy2.getBitmapFrame(i, canvas.getWidth(), canvas.getHeight()) : null;
                if (bitmapFrame != null) {
                    try {
                        if (bitmapFrame.isValid()) {
                            Bitmap bitmap = bitmapFrame.get();
                            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
                            drawBitmap(i, bitmap, canvas);
                            CloseableReference.closeSafely((CloseableReference<?>) bitmapFrame);
                            return true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        closeableReference = bitmapFrame;
                        CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                        throw th;
                    }
                }
                BitmapFramePreparationStrategy bitmapFramePreparationStrategy3 = this.bitmapFramePreparationStrategy;
                if (bitmapFramePreparationStrategy3 != null) {
                    bitmapFramePreparationStrategy3.prepareFrames(canvas.getWidth(), canvas.getHeight(), (Function0<Unit>) null);
                }
                CloseableReference.closeSafely((CloseableReference<?>) bitmapFrame);
                return false;
            }
            if (i2 == 0) {
                closeableReference = this.bitmapFrameCache.getCachedFrame(i);
                z = drawBitmapAndCache(i, closeableReference, canvas, 0);
            } else if (i2 == 1) {
                closeableReference = this.bitmapFrameCache.getBitmapToReuseForFrame(i, this.bitmapWidth, this.bitmapHeight);
                if (renderFrameInBitmap(i, closeableReference) && drawBitmapAndCache(i, closeableReference, canvas, 1)) {
                    z2 = true;
                }
                z = z2;
                i3 = 2;
            } else if (i2 == 2) {
                closeableReference = this.platformBitmapFactory.createBitmap(this.bitmapWidth, this.bitmapHeight, this.bitmapConfig);
                if (renderFrameInBitmap(i, closeableReference) && drawBitmapAndCache(i, closeableReference, canvas, 2)) {
                    z2 = true;
                }
                z = z2;
                i3 = 3;
            } else if (i2 != 3) {
                CloseableReference.closeSafely((CloseableReference<?>) null);
                return false;
            } else {
                closeableReference = this.bitmapFrameCache.getFallbackFrame(i);
                z = drawBitmapAndCache(i, closeableReference, canvas, 3);
                i3 = -1;
            }
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            if (z || i3 == -1) {
                return z;
            }
            return drawFrameOrFallback(canvas, i, i3);
        } catch (RuntimeException e) {
            FLog.w((Class<?>) TAG, "Failed to create frame bitmap", (Throwable) e);
            CloseableReference.closeSafely((CloseableReference<?>) null);
            return false;
        } catch (Throwable th2) {
            th = th2;
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            throw th;
        }
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void setBounds(Rect rect) {
        this.bounds = rect;
        this.bitmapFrameRenderer.setBounds(rect);
        updateBitmapDimensions();
    }

    public int getIntrinsicWidth() {
        return this.bitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.bitmapHeight;
    }

    public int getSizeInBytes() {
        return this.bitmapFrameCache.getSizeInBytes();
    }

    public void clear() {
        if (this.isNewRenderImplementation) {
            BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
            if (bitmapFramePreparationStrategy2 != null) {
                bitmapFramePreparationStrategy2.clearFrames();
                return;
            }
            return;
        }
        this.bitmapFrameCache.clear();
    }

    public void preloadAnimation() {
        BitmapFramePreparer bitmapFramePreparer2;
        if (this.isNewRenderImplementation || (bitmapFramePreparer2 = this.bitmapFramePreparer) == null) {
            BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
            if (bitmapFramePreparationStrategy2 != null) {
                bitmapFramePreparationStrategy2.prepareFrames(this.animationInformation.width(), this.animationInformation.height(), new BitmapAnimationBackend$preloadAnimation$2(this));
                return;
            }
            return;
        }
        BitmapFramePreparationStrategy bitmapFramePreparationStrategy3 = this.bitmapFramePreparationStrategy;
        if (bitmapFramePreparationStrategy3 != null) {
            bitmapFramePreparationStrategy3.prepareFrames(bitmapFramePreparer2, this.bitmapFrameCache, this, 0, new BitmapAnimationBackend$preloadAnimation$1(this));
        }
    }

    public void onInactive() {
        if (this.isNewRenderImplementation) {
            BitmapFramePreparationStrategy bitmapFramePreparationStrategy2 = this.bitmapFramePreparationStrategy;
            if (bitmapFramePreparationStrategy2 != null) {
                bitmapFramePreparationStrategy2.onStop();
                return;
            }
            return;
        }
        clear();
    }

    public void setAnimationListener(AnimationBackend.Listener listener) {
        this.animationListener = listener;
    }

    private final void updateBitmapDimensions() {
        int intrinsicWidth = this.bitmapFrameRenderer.getIntrinsicWidth();
        this.bitmapWidth = intrinsicWidth;
        int i = -1;
        if (intrinsicWidth == -1) {
            Rect rect = this.bounds;
            this.bitmapWidth = rect != null ? rect.width() : -1;
        }
        int intrinsicHeight = this.bitmapFrameRenderer.getIntrinsicHeight();
        this.bitmapHeight = intrinsicHeight;
        if (intrinsicHeight == -1) {
            Rect rect2 = this.bounds;
            if (rect2 != null) {
                i = rect2.height();
            }
            this.bitmapHeight = i;
        }
    }

    private final boolean renderFrameInBitmap(int i, CloseableReference<Bitmap> closeableReference) {
        if (closeableReference == null || !closeableReference.isValid()) {
            return false;
        }
        BitmapFrameRenderer bitmapFrameRenderer2 = this.bitmapFrameRenderer;
        Bitmap bitmap = closeableReference.get();
        Intrinsics.checkNotNullExpressionValue(bitmap, "targetBitmap.get()");
        boolean renderFrame = bitmapFrameRenderer2.renderFrame(i, bitmap);
        if (!renderFrame) {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
        return renderFrame;
    }

    private final boolean updatePath(int i, Bitmap bitmap, float f, float f2) {
        if (this.cornerRadii == null) {
            return false;
        }
        if (i == this.pathFrameNumber) {
            return true;
        }
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        this.matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight), new RectF(0.0f, 0.0f, f, f2), Matrix.ScaleToFit.FILL);
        bitmapShader.setLocalMatrix(this.matrix);
        this.paint.setShader(bitmapShader);
        this.path.addRoundRect(new RectF(0.0f, 0.0f, f, f2), this.cornerRadii, Path.Direction.CW);
        this.pathFrameNumber = i;
        return true;
    }

    private final void drawBitmap(int i, Bitmap bitmap, Canvas canvas) {
        Rect rect = this.bounds;
        if (rect == null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.paint);
        } else if (updatePath(i, bitmap, (float) rect.width(), (float) rect.height())) {
            canvas.drawPath(this.path, this.paint);
        } else {
            canvas.drawBitmap(bitmap, (Rect) null, rect, this.paint);
        }
    }

    private final boolean drawBitmapAndCache(int i, CloseableReference<Bitmap> closeableReference, Canvas canvas, int i2) {
        if (closeableReference == null || !CloseableReference.isValid(closeableReference)) {
            return false;
        }
        Bitmap bitmap = closeableReference.get();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmapReference.get()");
        drawBitmap(i, bitmap, canvas);
        if (i2 != 3 && !this.isNewRenderImplementation) {
            this.bitmapFrameCache.onFrameRendered(i, closeableReference, i2);
        }
        FrameListener frameListener2 = this.frameListener;
        if (frameListener2 == null) {
            return true;
        }
        frameListener2.onFrameDrawn(this, i, i2);
        return true;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend$Companion;", "", "()V", "FRAME_TYPE_CACHED", "", "FRAME_TYPE_CREATED", "FRAME_TYPE_FALLBACK", "FRAME_TYPE_REUSED", "FRAME_TYPE_UNKNOWN", "TAG", "Ljava/lang/Class;", "Lcom/facebook/fresco/animation/bitmap/BitmapAnimationBackend;", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BitmapAnimationBackend.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
