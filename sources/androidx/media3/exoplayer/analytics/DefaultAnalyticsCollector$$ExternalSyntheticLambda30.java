package androidx.media3.exoplayer.analytics;

import androidx.media3.common.text.Cue;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda30 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda30(AnalyticsListener.EventTime eventTime, List list) {
        this.f$0 = eventTime;
        this.f$1 = list;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onCues(this.f$0, (List<Cue>) this.f$1);
    }
}
