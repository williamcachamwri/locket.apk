package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda8 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ List f$0;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda8(List list) {
        this.f$0 = list;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setPendingVideoEffects(this.f$0);
    }
}
