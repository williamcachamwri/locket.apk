package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda38 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ DecoderCounters f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda38(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.f$0 = eventTime;
        this.f$1 = decoderCounters;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onVideoEnabled(this.f$0, this.f$1);
    }
}
