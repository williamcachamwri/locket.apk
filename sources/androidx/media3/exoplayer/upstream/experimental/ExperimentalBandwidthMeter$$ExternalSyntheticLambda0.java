package androidx.media3.exoplayer.upstream.experimental;

import androidx.media3.common.util.NetworkTypeObserver;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExperimentalBandwidthMeter$$ExternalSyntheticLambda0 implements NetworkTypeObserver.Listener {
    public final /* synthetic */ ExperimentalBandwidthMeter f$0;

    public /* synthetic */ ExperimentalBandwidthMeter$$ExternalSyntheticLambda0(ExperimentalBandwidthMeter experimentalBandwidthMeter) {
        this.f$0 = experimentalBandwidthMeter;
    }

    public final void onNetworkTypeChanged(int i) {
        this.f$0.onNetworkTypeChanged(i);
    }
}
