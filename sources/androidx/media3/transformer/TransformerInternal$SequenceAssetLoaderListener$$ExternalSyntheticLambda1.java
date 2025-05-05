package androidx.media3.transformer;

import androidx.media3.common.util.Consumer;
import androidx.media3.transformer.TransformerInternal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ TransformerInternal.SequenceAssetLoaderListener f$0;

    public /* synthetic */ TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda1(TransformerInternal.SequenceAssetLoaderListener sequenceAssetLoaderListener) {
        this.f$0 = sequenceAssetLoaderListener;
    }

    public final void accept(Object obj) {
        this.f$0.onError((ExportException) obj);
    }
}
