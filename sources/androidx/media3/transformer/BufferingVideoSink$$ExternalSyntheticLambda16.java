package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda16 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ VideoFrameMetadataListener f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda16(VideoFrameMetadataListener videoFrameMetadataListener) {
        this.f$0 = videoFrameMetadataListener;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setVideoFrameMetadataListener(this.f$0);
    }
}
