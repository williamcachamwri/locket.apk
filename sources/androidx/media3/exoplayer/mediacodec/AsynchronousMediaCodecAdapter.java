package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import com.google.common.base.Supplier;
import java.nio.ByteBuffer;

final class AsynchronousMediaCodecAdapter implements MediaCodecAdapter {
    private static final int STATE_CREATED = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_SHUT_DOWN = 2;
    private final AsynchronousMediaCodecCallback asynchronousMediaCodecCallback;
    private final MediaCodecBufferEnqueuer bufferEnqueuer;
    private final MediaCodec codec;
    private boolean codecReleased;
    private final LoudnessCodecController loudnessCodecController;
    private int state;

    public boolean needsReconfiguration() {
        return false;
    }

    public static final class Factory implements MediaCodecAdapter.Factory {
        private final Supplier<HandlerThread> callbackThreadSupplier;
        private boolean enableSynchronousBufferQueueingWithAsyncCryptoFlag;
        private final Supplier<HandlerThread> queueingThreadSupplier;

        public Factory(int i) {
            this(new AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda0(i), new AsynchronousMediaCodecAdapter$Factory$$ExternalSyntheticLambda1(i));
        }

        static /* synthetic */ HandlerThread lambda$new$0(int i) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createCallbackThreadLabel(i));
        }

        static /* synthetic */ HandlerThread lambda$new$1(int i) {
            return new HandlerThread(AsynchronousMediaCodecAdapter.createQueueingThreadLabel(i));
        }

        Factory(Supplier<HandlerThread> supplier, Supplier<HandlerThread> supplier2) {
            this.callbackThreadSupplier = supplier;
            this.queueingThreadSupplier = supplier2;
            this.enableSynchronousBufferQueueingWithAsyncCryptoFlag = false;
        }

        public void experimentalSetAsyncCryptoFlagEnabled(boolean z) {
            this.enableSynchronousBufferQueueingWithAsyncCryptoFlag = z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x0079  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x007f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter createAdapter(androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Configuration r11) throws java.io.IOException {
            /*
                r10 = this;
                java.lang.String r0 = "createCodec:"
                androidx.media3.exoplayer.mediacodec.MediaCodecInfo r1 = r11.codecInfo
                java.lang.String r1 = r1.name
                r2 = 0
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0075 }
                r3.<init>(r0)     // Catch:{ Exception -> 0x0075 }
                java.lang.StringBuilder r0 = r3.append(r1)     // Catch:{ Exception -> 0x0075 }
                java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0075 }
                androidx.media3.common.util.TraceUtil.beginSection(r0)     // Catch:{ Exception -> 0x0075 }
                android.media.MediaCodec r0 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ Exception -> 0x0075 }
                boolean r1 = r10.enableSynchronousBufferQueueingWithAsyncCryptoFlag     // Catch:{ Exception -> 0x0073 }
                if (r1 == 0) goto L_0x002e
                androidx.media3.common.Format r1 = r11.format     // Catch:{ Exception -> 0x0073 }
                boolean r1 = useSynchronousBufferQueueingWithAsyncCryptoFlag(r1)     // Catch:{ Exception -> 0x0073 }
                if (r1 == 0) goto L_0x002e
                androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecBufferEnqueuer r1 = new androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecBufferEnqueuer     // Catch:{ Exception -> 0x0073 }
                r1.<init>(r0)     // Catch:{ Exception -> 0x0073 }
                r3 = 4
                goto L_0x003c
            L_0x002e:
                androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer r1 = new androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer     // Catch:{ Exception -> 0x0073 }
                com.google.common.base.Supplier<android.os.HandlerThread> r3 = r10.queueingThreadSupplier     // Catch:{ Exception -> 0x0073 }
                java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0073 }
                android.os.HandlerThread r3 = (android.os.HandlerThread) r3     // Catch:{ Exception -> 0x0073 }
                r1.<init>(r0, r3)     // Catch:{ Exception -> 0x0073 }
                r3 = 0
            L_0x003c:
                r6 = r1
                r1 = r3
                androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter r9 = new androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter     // Catch:{ Exception -> 0x0073 }
                com.google.common.base.Supplier<android.os.HandlerThread> r3 = r10.callbackThreadSupplier     // Catch:{ Exception -> 0x0073 }
                java.lang.Object r3 = r3.get()     // Catch:{ Exception -> 0x0073 }
                r5 = r3
                android.os.HandlerThread r5 = (android.os.HandlerThread) r5     // Catch:{ Exception -> 0x0073 }
                androidx.media3.exoplayer.mediacodec.LoudnessCodecController r7 = r11.loudnessCodecController     // Catch:{ Exception -> 0x0073 }
                r8 = 0
                r3 = r9
                r4 = r0
                r3.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0073 }
                androidx.media3.common.util.TraceUtil.endSection()     // Catch:{ Exception -> 0x0070 }
                android.view.Surface r2 = r11.surface     // Catch:{ Exception -> 0x0070 }
                if (r2 != 0) goto L_0x0066
                androidx.media3.exoplayer.mediacodec.MediaCodecInfo r2 = r11.codecInfo     // Catch:{ Exception -> 0x0070 }
                boolean r2 = r2.detachedSurfaceSupported     // Catch:{ Exception -> 0x0070 }
                if (r2 == 0) goto L_0x0066
                int r2 = androidx.media3.common.util.Util.SDK_INT     // Catch:{ Exception -> 0x0070 }
                r3 = 35
                if (r2 < r3) goto L_0x0066
                r1 = r1 | 8
            L_0x0066:
                android.media.MediaFormat r2 = r11.mediaFormat     // Catch:{ Exception -> 0x0070 }
                android.view.Surface r3 = r11.surface     // Catch:{ Exception -> 0x0070 }
                android.media.MediaCrypto r11 = r11.crypto     // Catch:{ Exception -> 0x0070 }
                r9.initialize(r2, r3, r11, r1)     // Catch:{ Exception -> 0x0070 }
                return r9
            L_0x0070:
                r11 = move-exception
                r2 = r9
                goto L_0x0077
            L_0x0073:
                r11 = move-exception
                goto L_0x0077
            L_0x0075:
                r11 = move-exception
                r0 = r2
            L_0x0077:
                if (r2 != 0) goto L_0x007f
                if (r0 == 0) goto L_0x0082
                r0.release()
                goto L_0x0082
            L_0x007f:
                r2.release()
            L_0x0082:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter.Factory.createAdapter(androidx.media3.exoplayer.mediacodec.MediaCodecAdapter$Configuration):androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter");
        }

        private static boolean useSynchronousBufferQueueingWithAsyncCryptoFlag(Format format) {
            if (Util.SDK_INT < 34) {
                return false;
            }
            if (Util.SDK_INT >= 35 || MimeTypes.isVideo(format.sampleMimeType)) {
                return true;
            }
            return false;
        }
    }

    private AsynchronousMediaCodecAdapter(MediaCodec mediaCodec, HandlerThread handlerThread, MediaCodecBufferEnqueuer mediaCodecBufferEnqueuer, LoudnessCodecController loudnessCodecController2) {
        this.codec = mediaCodec;
        this.asynchronousMediaCodecCallback = new AsynchronousMediaCodecCallback(handlerThread);
        this.bufferEnqueuer = mediaCodecBufferEnqueuer;
        this.loudnessCodecController = loudnessCodecController2;
        this.state = 0;
    }

    /* access modifiers changed from: private */
    public void initialize(MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int i) {
        LoudnessCodecController loudnessCodecController2;
        this.asynchronousMediaCodecCallback.initialize(this.codec);
        TraceUtil.beginSection("configureCodec");
        this.codec.configure(mediaFormat, surface, mediaCrypto, i);
        TraceUtil.endSection();
        this.bufferEnqueuer.start();
        TraceUtil.beginSection("startCodec");
        this.codec.start();
        TraceUtil.endSection();
        if (Util.SDK_INT >= 35 && (loudnessCodecController2 = this.loudnessCodecController) != null) {
            loudnessCodecController2.addMediaCodec(this.codec);
        }
        this.state = 1;
    }

    public void queueInputBuffer(int i, int i2, int i3, long j, int i4) {
        this.bufferEnqueuer.queueInputBuffer(i, i2, i3, j, i4);
    }

    public void queueSecureInputBuffer(int i, int i2, CryptoInfo cryptoInfo, long j, int i3) {
        this.bufferEnqueuer.queueSecureInputBuffer(i, i2, cryptoInfo, j, i3);
    }

    public void releaseOutputBuffer(int i, boolean z) {
        this.codec.releaseOutputBuffer(i, z);
    }

    public void releaseOutputBuffer(int i, long j) {
        this.codec.releaseOutputBuffer(i, j);
    }

    public int dequeueInputBufferIndex() {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueInputBufferIndex();
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        this.bufferEnqueuer.maybeThrowException();
        return this.asynchronousMediaCodecCallback.dequeueOutputBufferIndex(bufferInfo);
    }

    public MediaFormat getOutputFormat() {
        return this.asynchronousMediaCodecCallback.getOutputFormat();
    }

    public ByteBuffer getInputBuffer(int i) {
        return this.codec.getInputBuffer(i);
    }

    public ByteBuffer getOutputBuffer(int i) {
        return this.codec.getOutputBuffer(i);
    }

    public void flush() {
        this.bufferEnqueuer.flush();
        this.codec.flush();
        this.asynchronousMediaCodecCallback.flush();
        this.codec.start();
    }

    public void release() {
        LoudnessCodecController loudnessCodecController2;
        LoudnessCodecController loudnessCodecController3;
        LoudnessCodecController loudnessCodecController4;
        try {
            if (this.state == 1) {
                this.bufferEnqueuer.shutdown();
                this.asynchronousMediaCodecCallback.shutdown();
            }
            this.state = 2;
            if (!this.codecReleased) {
                try {
                    if (Util.SDK_INT >= 30 && Util.SDK_INT < 33) {
                        this.codec.stop();
                    }
                } finally {
                    if (Util.SDK_INT >= 35 && (loudnessCodecController4 = this.loudnessCodecController) != null) {
                        loudnessCodecController4.removeMediaCodec(this.codec);
                    }
                    this.codec.release();
                    this.codecReleased = true;
                }
            }
        } catch (Throwable th) {
            if (Util.SDK_INT >= 35 && (loudnessCodecController2 = this.loudnessCodecController) != null) {
                loudnessCodecController2.removeMediaCodec(this.codec);
            }
            this.codec.release();
            this.codecReleased = true;
            throw th;
        }
    }

    public void setOnFrameRenderedListener(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, Handler handler) {
        this.codec.setOnFrameRenderedListener(new AsynchronousMediaCodecAdapter$$ExternalSyntheticLambda0(this, onFrameRenderedListener), handler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnFrameRenderedListener$0$androidx-media3-exoplayer-mediacodec-AsynchronousMediaCodecAdapter  reason: not valid java name */
    public /* synthetic */ void m529lambda$setOnFrameRenderedListener$0$androidxmedia3exoplayermediacodecAsynchronousMediaCodecAdapter(MediaCodecAdapter.OnFrameRenderedListener onFrameRenderedListener, MediaCodec mediaCodec, long j, long j2) {
        onFrameRenderedListener.onFrameRendered(this, j, j2);
    }

    public boolean registerOnBufferAvailableListener(MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener) {
        this.asynchronousMediaCodecCallback.setOnBufferAvailableListener(onBufferAvailableListener);
        return true;
    }

    public void setOutputSurface(Surface surface) {
        this.codec.setOutputSurface(surface);
    }

    public void detachOutputSurface() {
        this.codec.detachOutputSurface();
    }

    public void setParameters(Bundle bundle) {
        this.bufferEnqueuer.setParameters(bundle);
    }

    public void setVideoScalingMode(int i) {
        this.codec.setVideoScalingMode(i);
    }

    public PersistableBundle getMetrics() {
        return this.codec.getMetrics();
    }

    /* access modifiers changed from: package-private */
    public void onError(MediaCodec.CodecException codecException) {
        this.asynchronousMediaCodecCallback.onError(this.codec, codecException);
    }

    /* access modifiers changed from: package-private */
    public void onOutputFormatChanged(MediaFormat mediaFormat) {
        this.asynchronousMediaCodecCallback.onOutputFormatChanged(this.codec, mediaFormat);
    }

    /* access modifiers changed from: private */
    public static String createCallbackThreadLabel(int i) {
        return createThreadLabel(i, "ExoPlayer:MediaCodecAsyncAdapter:");
    }

    /* access modifiers changed from: private */
    public static String createQueueingThreadLabel(int i) {
        return createThreadLabel(i, "ExoPlayer:MediaCodecQueueingThread:");
    }

    private static String createThreadLabel(int i, String str) {
        StringBuilder sb = new StringBuilder(str);
        if (i == 1) {
            sb.append("Audio");
        } else if (i == 2) {
            sb.append("Video");
        } else {
            sb.append("Unknown(").append(i).append(")");
        }
        return sb.toString();
    }
}
