package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda34 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda34(AnalyticsListener.EventTime eventTime, int i, int i2, boolean z) {
        this.f$0 = eventTime;
        this.f$1 = i;
        this.f$2 = i2;
        this.f$3 = z;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onRendererReadyChanged(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
