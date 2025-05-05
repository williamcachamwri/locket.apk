package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda5 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ int f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda5(int i) {
        this.f$0 = i;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setChangeFrameRateStrategy(this.f$0);
    }
}
