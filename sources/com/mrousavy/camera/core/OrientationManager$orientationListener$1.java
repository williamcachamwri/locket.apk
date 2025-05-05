package com.mrousavy.camera.core;

import android.content.Context;
import android.view.OrientationEventListener;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/mrousavy/camera/core/OrientationManager$orientationListener$1", "Landroid/view/OrientationEventListener;", "onOrientationChanged", "", "rotationDegrees", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OrientationManager.kt */
public final class OrientationManager$orientationListener$1 extends OrientationEventListener {
    final /* synthetic */ OrientationManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrientationManager$orientationListener$1(OrientationManager orientationManager, Context context) {
        super(context);
        this.this$0 = orientationManager;
    }

    public void onOrientationChanged(int i) {
        if (i != -1) {
            OrientationManager orientationManager = this.this$0;
            orientationManager.deviceRotation = orientationManager.degreesToSurfaceRotation(i);
            this.this$0.maybeNotifyOrientationChanged();
        }
    }
}
