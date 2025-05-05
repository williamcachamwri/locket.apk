package androidx.media3.transformer;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.transformer.Transformer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Transformer$$ExternalSyntheticLambda1 implements ListenerSet.Event {
    public final /* synthetic */ Transformer f$0;
    public final /* synthetic */ ExportException f$1;

    public /* synthetic */ Transformer$$ExternalSyntheticLambda1(Transformer transformer, ExportException exportException) {
        this.f$0 = transformer;
        this.f$1 = exportException;
    }

    public final void invoke(Object obj) {
        this.f$0.m1133lambda$onExportCompletedWithError$1$androidxmedia3transformerTransformer(this.f$1, (Transformer.Listener) obj);
    }
}
