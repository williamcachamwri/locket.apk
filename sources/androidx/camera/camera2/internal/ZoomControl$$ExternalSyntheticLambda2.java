package androidx.camera.camera2.internal;

import androidx.camera.core.ZoomState;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ZoomControl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ZoomControl f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ ZoomState f$2;

    public /* synthetic */ ZoomControl$$ExternalSyntheticLambda2(ZoomControl zoomControl, CallbackToFutureAdapter.Completer completer, ZoomState zoomState) {
        this.f$0 = zoomControl;
        this.f$1 = completer;
        this.f$2 = zoomState;
    }

    public final void run() {
        this.f$0.m99lambda$setZoomRatio$0$androidxcameracamera2internalZoomControl(this.f$1, this.f$2);
    }
}
