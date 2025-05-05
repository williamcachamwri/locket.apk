package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda33 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ LoadEventInfo f$1;
    public final /* synthetic */ MediaLoadData f$2;
    public final /* synthetic */ IOException f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda33(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.f$0 = eventTime;
        this.f$1 = loadEventInfo;
        this.f$2 = mediaLoadData;
        this.f$3 = iOException;
        this.f$4 = z;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onLoadError(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
