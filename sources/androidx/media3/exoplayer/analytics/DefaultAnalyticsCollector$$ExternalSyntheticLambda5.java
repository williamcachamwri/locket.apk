package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Format;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda5 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ Format f$1;
    public final /* synthetic */ DecoderReuseEvaluation f$2;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda5(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        this.f$0 = eventTime;
        this.f$1 = format;
        this.f$2 = decoderReuseEvaluation;
    }

    public final void invoke(Object obj) {
        ((AnalyticsListener) obj).onVideoInputFormatChanged(this.f$0, this.f$1, this.f$2);
    }
}
