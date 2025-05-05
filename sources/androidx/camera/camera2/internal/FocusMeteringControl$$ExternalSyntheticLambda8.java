package androidx.camera.camera2.internal;

import androidx.camera.core.FocusMeteringAction;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FocusMeteringControl$$ExternalSyntheticLambda8 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ FocusMeteringControl f$0;
    public final /* synthetic */ FocusMeteringAction f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ FocusMeteringControl$$ExternalSyntheticLambda8(FocusMeteringControl focusMeteringControl, FocusMeteringAction focusMeteringAction, long j) {
        this.f$0 = focusMeteringControl;
        this.f$1 = focusMeteringAction;
        this.f$2 = j;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m78lambda$startFocusAndMetering$1$androidxcameracamera2internalFocusMeteringControl(this.f$1, this.f$2, completer);
    }
}
