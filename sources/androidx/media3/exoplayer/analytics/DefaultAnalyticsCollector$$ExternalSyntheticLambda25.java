package androidx.media3.exoplayer.analytics;

import androidx.media3.common.DeviceInfo;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda25 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ DeviceInfo f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda25(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
        this.f$0 = eventTime;
        this.f$1 = deviceInfo;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onDeviceInfoChanged(this.f$0, this.f$1);
    }
}
