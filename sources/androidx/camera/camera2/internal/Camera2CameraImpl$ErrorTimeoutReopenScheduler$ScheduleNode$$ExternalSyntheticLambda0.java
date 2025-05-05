package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.Camera2CameraImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraImpl$ErrorTimeoutReopenScheduler$ScheduleNode$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Camera2CameraImpl.ErrorTimeoutReopenScheduler.ScheduleNode f$0;

    public /* synthetic */ Camera2CameraImpl$ErrorTimeoutReopenScheduler$ScheduleNode$$ExternalSyntheticLambda0(Camera2CameraImpl.ErrorTimeoutReopenScheduler.ScheduleNode scheduleNode) {
        this.f$0 = scheduleNode;
    }

    public final void run() {
        this.f$0.executeInternal();
    }
}
