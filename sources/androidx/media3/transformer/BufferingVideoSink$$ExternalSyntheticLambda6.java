package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda6 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda6(boolean z) {
        this.f$0 = z;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.join(this.f$0);
    }
}
