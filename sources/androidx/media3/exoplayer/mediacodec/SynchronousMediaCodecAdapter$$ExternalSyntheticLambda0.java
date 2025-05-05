package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0 implements MediaCodec.OnFrameRenderedListener {
    public final /* synthetic */ SynchronousMediaCodecAdapter f$0;
    public final /* synthetic */ MediaCodecAdapter.OnFrameRenderedListener f$1;

    public /* synthetic */ SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(SynchronousMediaCodecAdapter synchronousMediaCodecAdapter, MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener) {
        this.f$0 = synchronousMediaCodecAdapter;
        this.f$1 = onFrameRenderedListener;
    }

    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        this.f$0.m531lambda$setOnFrameRenderedListener$0$androidxmedia3exoplayermediacodecSynchronousMediaCodecAdapter(this.f$1, mediaCodec, j, j2);
    }
}
