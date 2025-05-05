package androidx.media3.exoplayer;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import com.google.common.base.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayer$Builder$$ExternalSyntheticLambda23 implements Function {
    public final /* synthetic */ AnalyticsCollector f$0;

    public /* synthetic */ ExoPlayer$Builder$$ExternalSyntheticLambda23(AnalyticsCollector analyticsCollector) {
        this.f$0 = analyticsCollector;
    }

    public final Object apply(Object obj) {
        return ExoPlayer.Builder.lambda$new$13(this.f$0, (Clock) obj);
    }
}
