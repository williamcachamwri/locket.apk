package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$2;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda0(FocusMeteringControl focusMeteringControl, boolean z, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = focusMeteringControl;
        this.f$1 = z;
        this.f$2 = completer;
    }

    public final void run() {
        this.f$0.m69lambda$enableExternalFlashAeMode$4$androidxcameracamera2internalFocusMeteringControl(this.f$1, this.f$2);
    }
}
