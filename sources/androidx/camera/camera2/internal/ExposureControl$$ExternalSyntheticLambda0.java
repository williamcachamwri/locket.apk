package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExposureControl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ExposureControl f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ ExposureControl$$ExternalSyntheticLambda0(ExposureControl exposureControl, CallbackToFutureAdapter.Completer completer, int i) {
        this.f$0 = exposureControl;
        this.f$1 = completer;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.m64lambda$setExposureCompensationIndex$1$androidxcameracamera2internalExposureControl(this.f$1, this.f$2);
    }
}
