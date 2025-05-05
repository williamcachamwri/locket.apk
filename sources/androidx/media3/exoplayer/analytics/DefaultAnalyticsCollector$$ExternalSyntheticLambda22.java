package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda22 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda22(AnalyticsListener.EventTime eventTime, long j, int i) {
        this.f$0 = eventTime;
        this.f$1 = j;
        this.f$2 = i;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onVideoFrameProcessingOffset(this.f$0, this.f$1, this.f$2);
    }
}
