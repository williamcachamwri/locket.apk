package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda67 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda67(AnalyticsListener.EventTime eventTime, Object obj, long j) {
        this.f$0 = eventTime;
        this.f$1 = obj;
        this.f$2 = j;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onRenderedFirstFrame(this.f$0, this.f$1, this.f$2);
    }
}
