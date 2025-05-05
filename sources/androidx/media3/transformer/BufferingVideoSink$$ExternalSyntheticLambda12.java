package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda12 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ List f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda12(List list) {
        this.f$0 = list;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setVideoEffects(this.f$0);
    }
}
