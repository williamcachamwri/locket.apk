package androidx.camera.camera2.internal;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda10 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda10(FocusMeteringControl focusMeteringControl, boolean z) {
        this.f$0 = focusMeteringControl;
        this.f$1 = z;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m70lambda$enableExternalFlashAeMode$5$androidxcameracamera2internalFocusMeteringControl(this.f$1, completer);
    }
}
