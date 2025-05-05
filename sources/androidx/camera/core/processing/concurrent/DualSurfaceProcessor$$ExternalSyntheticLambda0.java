package androidx.camera.core.processing.concurrent;

import androidx.camera.core.DynamicRange;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DualSurfaceProcessor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DualSurfaceProcessor f$0;
    public final /* synthetic */ DynamicRange f$1;
    public final /* synthetic */ Map f$2;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$3;

    public /* synthetic */ DualSurfaceProcessor$$ExternalSyntheticLambda0(DualSurfaceProcessor dualSurfaceProcessor, DynamicRange dynamicRange, Map map, CallbackToFutureAdapter.Completer completer) {
        this.f$0 = dualSurfaceProcessor;
        this.f$1 = dynamicRange;
        this.f$2 = map;
        this.f$3 = completer;
    }

    public final void run() {
        this.f$0.m215lambda$initGlRenderer$5$androidxcameracoreprocessingconcurrentDualSurfaceProcessor(this.f$1, this.f$2, this.f$3);
    }
}
