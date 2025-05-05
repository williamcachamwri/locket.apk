package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class SynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private final MediaCodec codec;
    private final LoudnessCodecController loudnessCodecController;

    public boolean needsReconfiguration() {
        return false;
    }

    public static class Factory implements MediaCodecAdapter.Factory {
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0047  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media3.exoplayer.mediacodec.MediaCodecAdapter createAdapter(androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Configuration r7) throws java.io.IOException {
            /*
                r6 = this;
                r0 = 0
                android.media.MediaCodec r1 = r6.createCodec(r7)     // Catch:{ IOException -> 0x0044, RuntimeException -> 0x0042 }
                java.lang.String r2 = "configureCodec"
                androidx.media3.common.util.TraceUtil.beginSection(r2)     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                android.view.Surface r2 = r7.surface     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                if (r2 != 0) goto L_0x001d
                androidx.media3.exoplayer.mediacodec.MediaCodecInfo r2 = r7.codecInfo     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                boolean r2 = r2.detachedSurfaceSupported     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                if (r2 == 0) goto L_0x001d
                int r2 = androidx.media3.common.util.Util.SDK_INT     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                r3 = 35
                if (r2 < r3) goto L_0x001d
                r2 = 8
                goto L_0x001e
            L_0x001d:
                r2 = 0
            L_0x001e:
                android.media.MediaFormat r3 = r7.mediaFormat     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                android.view.Surface r4 = r7.surface     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                android.media.MediaCrypto r5 = r7.crypto     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                r1.configure(r3, r4, r5, r2)     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                androidx.media3.common.util.TraceUtil.endSection()     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                java.lang.String r2 = "startCodec"
                androidx.media3.common.util.TraceUtil.beginSection(r2)     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                r1.start()     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                androidx.media3.common.util.TraceUtil.endSection()     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter r2 = new androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                androidx.media3.exoplayer.mediacodec.LoudnessCodecController r7 = r7.loudnessCodecController     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                r2.<init>(r1, r7)     // Catch:{ IOException -> 0x003f, RuntimeException -> 0x003d }
                return r2
            L_0x003d:
                r7 = move-exception
                goto L_0x0040
            L_0x003f:
                r7 = move-exception
            L_0x0040:
                r0 = r1
                goto L_0x0045
            L_0x0042:
                r7 = move-exception
                goto L_0x0045
            L_0x0044:
                r7 = move-exception
            L_0x0045:
                if (r0 == 0) goto L_0x004a
                r0.release()
            L_0x004a:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter.Factory.createAdapter(androidx.media3.exoplayer.mediacodec.MediaCodecAdapter$Configuration):androidx.media3.exoplayer.mediacodec.MediaCodecAdapter");
        }

        /* access modifiers changed from: protected */
        public MediaCodec createCodec(MediaCodecAdapter.Configuration configuration) throws IOException {
            Assertions.checkNotNull(configuration.codecInfo);
            String str = configuration.codecInfo.name;
            TraceUtil.beginSection("createCodec:" + str);
            MediaCodec createByCodecName = MediaCodec.createByCodecName(str);
            TraceUtil.endSection();
            return createByCodecName;
        }
    }

    private SynchronousMediaCodecAdapter(MediaCodec mediaCodec, LoudnessCodecController loudnessCodecController2) {
        this.codec = mediaCodec;
        this.loudnessCodecController = loudnessCodecController2;
        if (Util.SDK_INT >= 35 && loudnessCodecController2 != null) {
            loudnessCodecController2.addMediaCodec(mediaCodec);
        }
    }

    public int dequeueInputBufferIndex() {
        return this.codec.dequeueInputBuffer(0);
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        int dequeueOutputBuffer;
        do {
            dequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, 0);
        } while (dequeueOutputBuffer == -3);
        return dequeueOutputBuffer;
    }

    public MediaFormat getOutputFormat() {
        return this.codec.getOutputFormat();
    }

    public ByteBuffer getInputBuffer(int i) {
        return this.codec.getInputBuffer(i);
    }

    public ByteBuffer getOutputBuffer(int i) {
        return this.codec.getOutputBuffer(i);
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        this.codec.queueInputBuffer(i, i2, i3, j, i4);
    }

    public void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3) {
        this.codec.queueSecureInputBuffer(i, i2, cryptoInfo.getFrameworkCryptoInfo(), j, i3);
    }

    public void releaseOutputBuffer(int i, boolean z) {
        this.codec.releaseOutputBuffer(i, z);
    }

    public void releaseOutputBuffer(int i, long j) {
        this.codec.releaseOutputBuffer(i, j);
    }

    public void flush() {
        this.codec.flush();
    }

    /* JADX INFO: finally extract failed */
    public void release() {
        LoudnessCodecController loudnessCodecController2;
        LoudnessCodecController loudnessCodecController3;
        try {
            if (Util.SDK_INT >= 30 && Util.SDK_INT < 33) {
                this.codec.stop();
            }
            if (Util.SDK_INT >= 35 && (loudnessCodecController3 = this.loudnessCodecController) != null) {
                loudnessCodecController3.removeMediaCodec(this.codec);
            }
            this.codec.release();
        } catch (Throwable th) {
            if (Util.SDK_INT >= 35 && (loudnessCodecController2 = this.loudnessCodecController) != null) {
                loudnessCodecController2.removeMediaCodec(this.codec);
            }
            this.codec.release();
            throw th;
        }
    }

    public void setOnFrameRenderedListener(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, Handler handler) {
        this.codec.setOnFrameRenderedListener(new SynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(this, onFrameRenderedListener), handler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnFrameRenderedListener$0$androidx-media3-exoplayer-mediacodec-SynchronousMediaCodecAdapter  reason: not valid java name */
    public /* synthetic */ void m531lambda$setOnFrameRenderedListener$0$androidxmedia3exoplayermediacodecSynchronousMediaCodecAdapter(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, MediaCodec mediaCodec, long j, long j2) {
        onFrameRenderedListener.onFrameRendered(this, j, j2);
    }

    public void setOutputSurface(Surface surface) {
        this.codec.setOutputSurface(surface);
    }

    public void detachOutputSurface() {
        this.codec.detachOutputSurface();
    }

    public void setParameters(Bundle bundle) {
        this.codec.setParameters(bundle);
    }

    public void setVideoScalingMode(int i) {
        this.codec.setVideoScalingMode(i);
    }

    public PersistableBundle getMetrics() {
        return this.codec.getMetrics();
    }
}
