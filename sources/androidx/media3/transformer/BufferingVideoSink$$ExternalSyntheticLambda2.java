package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda2 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Format f$1;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda2(int i, Format format) {
        this.f$0 = i;
        this.f$1 = format;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.onInputStreamChanged(this.f$0, this.f$1);
    }
}
