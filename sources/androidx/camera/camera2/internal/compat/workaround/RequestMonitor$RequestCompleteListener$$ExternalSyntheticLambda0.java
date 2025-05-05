package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.workaround.RequestMonitor;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RequestMonitor$RequestCompleteListener$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ RequestMonitor.RequestCompleteListener f$0;

    public /* synthetic */ RequestMonitor$RequestCompleteListener$$ExternalSyntheticLambda0(RequestMonitor.RequestCompleteListener requestCompleteListener) {
        this.f$0 = requestCompleteListener;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m124lambda$new$0$androidxcameracamera2internalcompatworkaroundRequestMonitor$RequestCompleteListener(completer);
    }
}
