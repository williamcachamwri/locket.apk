package androidx.media3.extractor.text;

import com.google.common.base.Function;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CuesWithTimingSubtitle$$ExternalSyntheticLambda0 implements Function {
    public final Object apply(Object obj) {
        return Long.valueOf(CuesWithTimingSubtitle.normalizeUnsetStartTimeToZero(((CuesWithTiming) obj).startTimeUs));
    }
}
