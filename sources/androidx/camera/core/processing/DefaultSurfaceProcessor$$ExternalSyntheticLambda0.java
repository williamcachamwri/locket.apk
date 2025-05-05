package androidx.camera.core.processing;

import androidx.camera.core.DynamicRange;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultSurfaceProcessor$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ DefaultSurfaceProcessor f$0;
    public final /* synthetic */ DynamicRange f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ DefaultSurfaceProcessor$$ExternalSyntheticLambda0(DefaultSurfaceProcessor defaultSurfaceProcessor, DynamicRange dynamicRange, Map map) {
        this.f$0 = defaultSurfaceProcessor;
        this.f$1 = dynamicRange;
        this.f$2 = map;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m190lambda$initGlRenderer$10$androidxcameracoreprocessingDefaultSurfaceProcessor(this.f$1, this.f$2, completer);
    }
}
