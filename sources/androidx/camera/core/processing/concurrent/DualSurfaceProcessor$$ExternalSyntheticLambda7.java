package androidx.camera.core.processing.concurrent;

import androidx.camera.core.DynamicRange;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda7 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ DynamicRange f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda7(DualSurfaceProcessor dualSurfaceProcessor, DynamicRange dynamicRange, Map map) {
        this.f$0 = dualSurfaceProcessor;
        this.f$1 = dynamicRange;
        this.f$2 = map;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m216lambda$initGlRenderer$6$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(this.f$1, this.f$2, completer);
    }
}
