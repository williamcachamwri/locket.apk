package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda42 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Metadata f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda42(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        this.f$0 = eventTime;
        this.f$1 = metadata;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onMetadata(this.f$0, this.f$1);
    }
}
