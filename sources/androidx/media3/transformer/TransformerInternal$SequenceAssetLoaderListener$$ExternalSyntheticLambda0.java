package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.transformer.TransformerInternal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda0 implements OnMediaItemChangedListener {
    public final /* synthetic */ TransformerInternal.SequenceAssetLoaderListener f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ GraphInput f$2;

    public /* synthetic */ TransformerInternal$SequenceAssetLoaderListener$$ExternalSyntheticLambda0(TransformerInternal.SequenceAssetLoaderListener sequenceAssetLoaderListener, int i, GraphInput graphInput) {
        this.f$0 = sequenceAssetLoaderListener;
        this.f$1 = i;
        this.f$2 = graphInput;
    }

    public final void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, Format format, boolean z) {
        this.f$0.m1137lambda$onOutputFormat$0$androidxmedia3transformerTransformerInternal$SequenceAssetLoaderListener(this.f$1, this.f$2, editedMediaItem, j, format, z);
    }
}
