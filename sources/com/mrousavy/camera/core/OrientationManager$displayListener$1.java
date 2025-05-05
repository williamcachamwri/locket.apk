package com.mrousavy.camera.core;

import android.hardware.display.DisplayManager;
import android.view.Display;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"com/mrousavy/camera/core/OrientationManager$displayListener$1", "Landroid/hardware/display/DisplayManager$DisplayListener;", "onDisplayAdded", "", "displayId", "", "onDisplayChanged", "onDisplayRemoved", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OrientationManager.kt */
public final class OrientationManager$displayListener$1 implements DisplayManager.DisplayListener {
    final /* synthetic */ OrientationManager this$0;

    public void onDisplayAdded(int i) {
    }

    public void onDisplayRemoved(int i) {
    }

    OrientationManager$displayListener$1(OrientationManager orientationManager) {
        this.this$0 = orientationManager;
    }

    public void onDisplayChanged(int i) {
        Display display = this.this$0.displayManager.getDisplay(i);
        if (display != null) {
            this.this$0.screenRotation = display.getRotation();
            this.this$0.maybeNotifyOrientationChanged();
        }
    }
}
