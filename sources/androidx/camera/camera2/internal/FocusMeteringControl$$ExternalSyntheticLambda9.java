package androidx.camera.camera2.internal;

import androidx.camera.core.FocusMeteringAction;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$1;
    public final /* synthetic */ FocusMeteringAction f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda9(FocusMeteringControl focusMeteringControl, CallbackToFutureAdapter.Completer completer, FocusMeteringAction focusMeteringAction, long j) {
        this.f$0 = focusMeteringControl;
        this.f$1 = completer;
        this.f$2 = focusMeteringAction;
        this.f$3 = j;
    }

    public final void run() {
        this.f$0.m77lambda$startFocusAndMetering$0$androidxcameracamera2internalFocusMeteringControl(this.f$1, this.f$2, this.f$3);
    }
}
