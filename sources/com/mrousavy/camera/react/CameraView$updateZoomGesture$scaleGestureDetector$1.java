package com.mrousavy.camera.react;

import android.view.ScaleGestureDetector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/mrousavy/camera/react/CameraView$updateZoomGesture$scaleGestureDetector$1", "Landroid/view/ScaleGestureDetector$SimpleOnScaleGestureListener;", "onScale", "", "detector", "Landroid/view/ScaleGestureDetector;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView.kt */
public final class CameraView$updateZoomGesture$scaleGestureDetector$1 extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    final /* synthetic */ CameraView this$0;

    CameraView$updateZoomGesture$scaleGestureDetector$1(CameraView cameraView) {
        this.this$0 = cameraView;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        Intrinsics.checkNotNullParameter(scaleGestureDetector, "detector");
        CameraView cameraView = this.this$0;
        cameraView.setZoom(cameraView.getZoom() * scaleGestureDetector.getScaleFactor());
        this.this$0.update();
        return true;
    }
}
