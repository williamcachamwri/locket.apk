package androidx.media3.transformer;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.transformer.Transformer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FallbackListener$$ExternalSyntheticLambda0 implements ListenerSet.Event {
    public final /* synthetic */ FallbackListener f$0;
    public final /* synthetic */ TransformationRequest f$1;

    public /* synthetic */ FallbackListener$$ExternalSyntheticLambda0(FallbackListener fallbackListener, TransformationRequest transformationRequest) {
        this.f$0 = fallbackListener;
        this.f$1 = transformationRequest;
    }

    public final void invoke(Object obj) {
        this.f$0.m1123lambda$onTransformationRequestFinalized$0$androidxmedia3transformerFallbackListener(this.f$1, (Transformer.Listener) obj);
    }
}
