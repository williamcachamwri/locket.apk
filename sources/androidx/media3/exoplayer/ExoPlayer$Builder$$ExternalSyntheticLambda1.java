package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayer$Builder$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ BandwidthMeter f$0;

    public /* synthetic */ ExoPlayer$Builder$$ExternalSyntheticLambda1(BandwidthMeter bandwidthMeter) {
        this.f$0 = bandwidthMeter;
    }

    public final Object get() {
        return ExoPlayer.Builder.lambda$setBandwidthMeter$20(this.f$0);
    }
}
