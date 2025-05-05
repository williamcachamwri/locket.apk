package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda14 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ float f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda14(float f) {
        this.f$0 = f;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setPlaybackSpeed(this.f$0);
    }
}
