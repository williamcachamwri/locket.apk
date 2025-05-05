package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.GlTextureProducer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MultipleInputVideoGraph$$ExternalSyntheticLambda2 implements GlTextureProducer.Listener {
    public final /* synthetic */ MultipleInputVideoGraph f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MultipleInputVideoGraph$$ExternalSyntheticLambda2(MultipleInputVideoGraph multipleInputVideoGraph, int i) {
        this.f$0 = multipleInputVideoGraph;
        this.f$1 = i;
    }

    public final void onTextureRendered(GlTextureProducer glTextureProducer, GlTextureInfo glTextureInfo, long j, long j2) {
        this.f$0.m445lambda$registerInput$0$androidxmedia3effectMultipleInputVideoGraph(this.f$1, glTextureProducer, glTextureInfo, j, j2);
    }
}
