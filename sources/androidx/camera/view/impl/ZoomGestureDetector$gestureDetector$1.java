package androidx.camera.view.impl;

import android.view.GestureDetector;
import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/camera/view/impl/ZoomGestureDetector$gestureDetector$1", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "onDoubleTap", "", "e", "Landroid/view/MotionEvent;", "camera-view_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ZoomGestureDetector.kt */
public final class ZoomGestureDetector$gestureDetector$1 extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ ZoomGestureDetector this$0;

    ZoomGestureDetector$gestureDetector$1(ZoomGestureDetector zoomGestureDetector) {
        this.this$0 = zoomGestureDetector;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        this.this$0.anchoredZoomStartX = motionEvent.getX();
        this.this$0.anchoredZoomStartY = motionEvent.getY();
        this.this$0.anchoredZoomMode = 1;
        return true;
    }
}
