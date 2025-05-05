package androidx.media3.transformer;

import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda3 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ VideoSink.Listener f$0;
    public final /* synthetic */ Executor f$1;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda3(VideoSink.Listener listener, Executor executor) {
        this.f$0 = listener;
        this.f$1 = executor;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setListener(this.f$0, this.f$1);
    }
}
