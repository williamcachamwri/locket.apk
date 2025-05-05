package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda13 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda13(long j, long j2, long j3, long j4) {
        this.f$0 = j;
        this.f$1 = j2;
        this.f$2 = j3;
        this.f$3 = j4;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setStreamTimestampInfo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
