package androidx.media3.exoplayer.analytics;

import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda46 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ CueGroup f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda46(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
        this.f$0 = eventTime;
        this.f$1 = cueGroup;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onCues(this.f$0, this.f$1);
    }
}
