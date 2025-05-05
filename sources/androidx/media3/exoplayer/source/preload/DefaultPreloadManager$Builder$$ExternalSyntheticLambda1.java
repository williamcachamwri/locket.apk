package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultPreloadManager$Builder$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ BandwidthMeter f$0;

    public /* synthetic */ DefaultPreloadManager$Builder$$ExternalSyntheticLambda1(BandwidthMeter bandwidthMeter) {
        this.f$0 = bandwidthMeter;
    }

    public final Object get() {
        return DefaultPreloadManager.Builder.lambda$setBandwidthMeter$6(this.f$0);
    }
}
