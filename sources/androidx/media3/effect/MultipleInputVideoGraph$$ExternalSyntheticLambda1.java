package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.GlTextureProducer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MultipleInputVideoGraph$$ExternalSyntheticLambda1 implements GlTextureProducer.Listener {
    public final /* synthetic */ MultipleInputVideoGraph f$0;

    public /* synthetic */ MultipleInputVideoGraph$$ExternalSyntheticLambda1(MultipleInputVideoGraph multipleInputVideoGraph) {
        this.f$0 = multipleInputVideoGraph;
    }

    public final void onTextureRendered(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, long j2) {
        this.f$0.processCompositorOutputTexture(glTextureProducer, glTextureInfo, j, j2);
    }
}
