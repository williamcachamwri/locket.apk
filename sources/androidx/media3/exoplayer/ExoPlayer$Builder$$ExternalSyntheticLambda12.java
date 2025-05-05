package androidx.media3.exoplayer;

import android.content.Context;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayer$Builder$$ExternalSyntheticLambda12 implements Supplier {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ ExoPlayer$Builder$$ExternalSyntheticLambda12(Context context) {
        this.f$0 = context;
    }

    public final Object get() {
        return DefaultBandwidthMeter.getSingletonInstance(this.f$0);
    }
}
