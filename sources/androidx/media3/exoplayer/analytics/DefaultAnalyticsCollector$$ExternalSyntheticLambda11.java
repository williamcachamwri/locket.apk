package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda11 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda11(AnalyticsListener.EventTime eventTime, long j) {
        this.f$0 = eventTime;
        this.f$1 = j;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onMaxSeekToPreviousPositionChanged(this.f$0, this.f$1);
    }
}
