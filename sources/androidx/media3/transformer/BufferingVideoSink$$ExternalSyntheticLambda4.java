package androidx.media3.transformer;

import android.view.Surface;
import androidx.media3.common.util.Size;
import androidx.media3.exoplayer.video.VideoSink;
import androidx.media3.transformer.BufferingVideoSink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BufferingVideoSink$$ExternalSyntheticLambda4 implements BufferingVideoSink.VideoSinkOperation {
    public final /* synthetic */ Surface f$0;
    public final /* synthetic */ Size f$1;

    public /* synthetic */ BufferingVideoSink$$ExternalSyntheticLambda4(Surface surface, Size size) {
        this.f$0 = surface;
        this.f$1 = size;
    }

    public final void execute(VideoSink videoSink) {
        videoSink.setOutputSurfaceInfo(this.f$0, this.f$1);
    }
}
