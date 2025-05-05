package androidx.camera.core;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceRequest$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ SurfaceRequest f$0;
    public final /* synthetic */ AtomicReference f$1;

    public /* synthetic */ SurfaceRequest$$ExternalSyntheticLambda0(SurfaceRequest surfaceRequest, AtomicReference atomicReference) {
        this.f$0 = surfaceRequest;
        this.f$1 = atomicReference;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m154lambda$initialSurfaceRecreationCompleter$6$androidxcameracoreSurfaceRequest(this.f$1, completer);
    }
}
