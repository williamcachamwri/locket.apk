package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsynchronousMediaCodecAdapter$$ExternalSyntheticLambda0 implements MediaCodec.OnFrameRenderedListener {
    public final /* synthetic */ AsynchronousMediaCodecAdapter f$0;
    public final /* synthetic */ MediaCodecAdapter.OnFrameRenderedListener f$1;

    public /* synthetic */ AsynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(AsynchronousMediaCodecAdapter asynchronousMediaCodecAdapter, MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener) {
        this.f$0 = asynchronousMediaCodecAdapter;
        this.f$1 = onFrameRenderedListener;
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        this.f$0.m529lambda$setOnFrameRenderedListener$0$androidxmedia3exoplayermediacodecAsynchronousMediaCodecAdapter(this.f$1, mediaCodec, j, j2);
    }
}
