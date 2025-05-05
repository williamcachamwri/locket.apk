package androidx.media3.exoplayer.analytics;

import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda14 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ TrackSelectionParameters f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda14(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
        this.f$0 = eventTime;
        this.f$1 = trackSelectionParameters;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onTrackSelectionParametersChanged(this.f$0, this.f$1);
    }
}
