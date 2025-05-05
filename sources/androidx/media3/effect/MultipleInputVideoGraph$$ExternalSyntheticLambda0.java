package androidx.media3.effect;

import androidx.media3.common.OnInputFrameProcessedListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MultipleInputVideoGraph$$ExternalSyntheticLambda0 implements OnInputFrameProcessedListener {
    public final /* synthetic */ MultipleInputVideoGraph f$0;

    public /* synthetic */ MultipleInputVideoGraph$$ExternalSyntheticLambda0(MultipleInputVideoGraph multipleInputVideoGraph) {
        this.f$0 = multipleInputVideoGraph;
    }

    public final void onInputFrameProcessed(int i, long j) {
        this.f$0.onCompositionVideoFrameProcessorInputFrameProcessed(i, j);
    }
}
