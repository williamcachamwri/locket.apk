package androidx.media3.transformer;

import androidx.media3.common.SimpleBasePlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CompositionPlayer$$ExternalSyntheticLambda2 implements SimpleBasePlayer.PositionSupplier {
    public final /* synthetic */ CompositionPlayer f$0;

    public /* synthetic */ CompositionPlayer$$ExternalSyntheticLambda2(CompositionPlayer compositionPlayer) {
        this.f$0 = compositionPlayer;
    }

    public final long get() {
        return this.f$0.getBufferedPositionMs();
    }
}
