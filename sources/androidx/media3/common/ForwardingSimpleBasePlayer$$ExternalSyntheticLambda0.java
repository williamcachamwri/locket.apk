package androidx.media3.common;

import androidx.media3.common.ForwardingSimpleBasePlayer;
import androidx.media3.common.SimpleBasePlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ForwardingSimpleBasePlayer$$ExternalSyntheticLambda0 implements SimpleBasePlayer.PositionSupplier {
    public final /* synthetic */ ForwardingSimpleBasePlayer.ForwardingPositionSupplier f$0;

    public /* synthetic */ ForwardingSimpleBasePlayer$$ExternalSyntheticLambda0(ForwardingSimpleBasePlayer.ForwardingPositionSupplier forwardingPositionSupplier) {
        this.f$0 = forwardingPositionSupplier;
    }

    public final long get() {
        return this.f$0.getBufferedPositionMs();
    }
}
