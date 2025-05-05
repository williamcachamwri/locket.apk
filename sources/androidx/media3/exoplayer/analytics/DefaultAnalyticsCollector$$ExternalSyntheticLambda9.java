package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda9 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda9(AnalyticsListener.EventTime eventTime, float f) {
        this.f$0 = eventTime;
        this.f$1 = f;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onVolumeChanged(this.f$0, this.f$1);
    }
}
