package com.canhub.cropper;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0001H\u0016J\u0016\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/canhub/cropper/CropImageAnimation;", "Landroid/view/animation/Animation;", "Landroid/view/animation/Animation$AnimationListener;", "imageView", "Landroid/widget/ImageView;", "cropOverlayView", "Lcom/canhub/cropper/CropOverlayView;", "(Landroid/widget/ImageView;Lcom/canhub/cropper/CropOverlayView;)V", "endBoundPoints", "", "endCropWindowRect", "Landroid/graphics/RectF;", "endImageMatrix", "startBoundPoints", "startCropWindowRect", "startImageMatrix", "applyTransformation", "", "interpolatedTime", "", "t", "Landroid/view/animation/Transformation;", "onAnimationEnd", "animation", "onAnimationRepeat", "onAnimationStart", "setEndState", "boundPoints", "imageMatrix", "Landroid/graphics/Matrix;", "setStartState", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageAnimation.kt */
public final class CropImageAnimation extends Animation implements Animation.AnimationListener {
    private final CropOverlayView cropOverlayView;
    private final float[] endBoundPoints = new float[8];
    private final RectF endCropWindowRect = new RectF();
    private final float[] endImageMatrix = new float[9];
    private final ImageView imageView;
    private final float[] startBoundPoints = new float[8];
    private final RectF startCropWindowRect = new RectF();
    private final float[] startImageMatrix = new float[9];

    public void onAnimationRepeat(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    public void onAnimationStart(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    public CropImageAnimation(ImageView imageView2, CropOverlayView cropOverlayView2) {
        Intrinsics.checkNotNullParameter(imageView2, "imageView");
        Intrinsics.checkNotNullParameter(cropOverlayView2, "cropOverlayView");
        this.imageView = imageView2;
        this.cropOverlayView = cropOverlayView2;
        setDuration(300);
        setFillAfter(true);
        setInterpolator(new AccelerateDecelerateInterpolator());
        setAnimationListener(this);
    }

    public final void setStartState(float[] fArr, Matrix matrix) {
        Intrinsics.checkNotNullParameter(fArr, "boundPoints");
        Intrinsics.checkNotNullParameter(matrix, "imageMatrix");
        reset();
        System.arraycopy(fArr, 0, this.startBoundPoints, 0, 8);
        this.startCropWindowRect.set(this.cropOverlayView.getCropWindowRect());
        matrix.getValues(this.startImageMatrix);
    }

    public final void setEndState(float[] fArr, Matrix matrix) {
        Intrinsics.checkNotNullParameter(fArr, "boundPoints");
        Intrinsics.checkNotNullParameter(matrix, "imageMatrix");
        System.arraycopy(fArr, 0, this.endBoundPoints, 0, 8);
        this.endCropWindowRect.set(this.cropOverlayView.getCropWindowRect());
        matrix.getValues(this.endImageMatrix);
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        Intrinsics.checkNotNullParameter(transformation, "t");
        RectF rectF = new RectF();
        rectF.left = this.startCropWindowRect.left + ((this.endCropWindowRect.left - this.startCropWindowRect.left) * f);
        rectF.top = this.startCropWindowRect.top + ((this.endCropWindowRect.top - this.startCropWindowRect.top) * f);
        rectF.right = this.startCropWindowRect.right + ((this.endCropWindowRect.right - this.startCropWindowRect.right) * f);
        rectF.bottom = this.startCropWindowRect.bottom + ((this.endCropWindowRect.bottom - this.startCropWindowRect.bottom) * f);
        float[] fArr = new float[8];
        for (int i = 0; i < 8; i++) {
            float f2 = this.startBoundPoints[i];
            fArr[i] = f2 + ((this.endBoundPoints[i] - f2) * f);
        }
        CropOverlayView cropOverlayView2 = this.cropOverlayView;
        cropOverlayView2.setCropWindowRect(rectF);
        cropOverlayView2.setBounds(fArr, this.imageView.getWidth(), this.imageView.getHeight());
        cropOverlayView2.invalidate();
        float[] fArr2 = new float[9];
        for (int i2 = 0; i2 < 9; i2++) {
            float f3 = this.startImageMatrix[i2];
            fArr2[i2] = f3 + ((this.endImageMatrix[i2] - f3) * f);
        }
        ImageView imageView2 = this.imageView;
        imageView2.getImageMatrix().setValues(fArr2);
        imageView2.invalidate();
    }

    public void onAnimationEnd(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.imageView.clearAnimation();
    }
}
