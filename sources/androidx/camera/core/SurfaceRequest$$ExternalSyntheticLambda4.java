package androidx.camera.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceRequest$$ExternalSyntheticLambda4 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ AtomicReference f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ SurfaceRequest$$ExternalSyntheticLambda4(AtomicReference atomicReference, String str) {
        this.f$0 = atomicReference;
        this.f$1 = str;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.set(completer);
    }
}
