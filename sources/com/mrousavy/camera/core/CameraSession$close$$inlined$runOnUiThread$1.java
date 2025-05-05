package com.mrousavy.camera.core;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "com/mrousavy/camera/core/utils/RunOnUiThreadKt$runOnUiThread$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: runOnUiThread.kt */
public final class CameraSession$close$$inlined$runOnUiThread$1 implements Runnable {
    final /* synthetic */ CameraSession this$0;

    public CameraSession$close$$inlined$runOnUiThread$1(CameraSession cameraSession) {
        this.this$0 = cameraSession;
    }

    public final void run() {
        this.this$0.getLifecycleRegistry$react_native_vision_camera_release().setCurrentState(Lifecycle.State.DESTROYED);
    }
}
