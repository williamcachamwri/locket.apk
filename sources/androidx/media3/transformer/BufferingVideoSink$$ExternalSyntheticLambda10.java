package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda10 implements BufferingVideoSink.ThrowingVideoSinkOperation {
    public final /* synthetic */ Format f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda10(Format format) {
        this.f$0 = format;
    }

    public final void execute(VideoSink videoSink) {
        BufferingVideoSink.lambda$initialize$2(this.f$0, videoSink);
    }
}
