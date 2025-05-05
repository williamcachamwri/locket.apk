package androidx.camera.core.processing;

import androidx.camera.core.SurfaceRequest;
import androidx.core.util.Consumer;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SurfaceProcessorNode$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ SurfaceProcessorNode$$ExternalSyntheticLambda0(Map map) {
        this.f$0 = map;
    }

    public final void accept(Object obj) {
        SurfaceProcessorNode.lambda$setUpRotationUpdates$1(this.f$0, (SurfaceRequest.TransformationInfo) obj);
    }
}
