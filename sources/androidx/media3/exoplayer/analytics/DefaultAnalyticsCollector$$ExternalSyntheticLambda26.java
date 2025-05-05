package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Tracks;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda26 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Tracks f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda26(AnalyticsListener.EventTime eventTime, Tracks tracks) {
        this.f$0 = eventTime;
        this.f$1 = tracks;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onTracksChanged(this.f$0, this.f$1);
    }
}
