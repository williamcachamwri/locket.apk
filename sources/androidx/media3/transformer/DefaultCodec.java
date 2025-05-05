package androidx.media3.transformer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.effect.DebugTraceUtil;
import androidx.media3.transformer.ExportException;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class DefaultCodec implements Codec {
    public static final int DEFAULT_PCM_ENCODING = 2;
    private static final String TAG = "DefaultCodec";
    private final Format configurationFormat;
    private final MediaFormat configurationMediaFormat;
    private int inputBufferIndex = -1;
    private boolean inputStreamEnded;
    private final Surface inputSurface;
    private final boolean isDecoder;
    private final boolean isVideo;
    private final int maxPendingFrameCount;
    private final MediaCodec mediaCodec;
    private ByteBuffer outputBuffer;
    private int outputBufferIndex = -1;
    private final MediaCodec.BufferInfo outputBufferInfo = new MediaCodec.BufferInfo();
    private Format outputFormat;
    private boolean outputStreamEnded;
    private final AtomicBoolean videoOutputStarted = new AtomicBoolean();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.media.MediaCodec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.media.MediaCodec} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.view.Surface} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.media.MediaCodec} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultCodec(android.content.Context r9, androidx.media3.common.Format r10, android.media.MediaFormat r11, java.lang.String r12, boolean r13, android.view.Surface r14) throws androidx.media3.transformer.ExportException {
        /*
            r8 = this;
            r8.<init>()
            r8.configurationFormat = r10
            r8.configurationMediaFormat = r11
            r8.isDecoder = r13
            java.lang.String r0 = r10.sampleMimeType
            java.lang.Object r0 = androidx.media3.common.util.Assertions.checkNotNull(r0)
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = androidx.media3.common.MimeTypes.isVideo(r0)
            r8.isVideo = r0
            android.media.MediaCodec$BufferInfo r1 = new android.media.MediaCodec$BufferInfo
            r1.<init>()
            r8.outputBufferInfo = r1
            r1 = -1
            r8.inputBufferIndex = r1
            r8.outputBufferIndex = r1
            java.util.concurrent.atomic.AtomicBoolean r1 = new java.util.concurrent.atomic.AtomicBoolean
            r1.<init>()
            r8.videoOutputStarted = r1
            java.lang.String r3 = "InputFormat"
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            java.lang.String r6 = "%s"
            java.lang.Object[] r7 = new java.lang.Object[]{r10}
            r1 = r13
            r2 = r0
            androidx.media3.effect.DebugTraceUtil.logCodecEvent(r1, r2, r3, r4, r6, r7)
            boolean r10 = isSdrToneMappingEnabled(r11)
            r1 = 0
            android.media.MediaCodec r2 = android.media.MediaCodec.createByCodecName(r12)     // Catch:{ Exception -> 0x0071 }
            configureCodec(r2, r11, r13, r14)     // Catch:{ Exception -> 0x006e }
            if (r10 == 0) goto L_0x0057
            android.media.MediaFormat r10 = r2.getInputFormat()     // Catch:{ Exception -> 0x006e }
            boolean r10 = isSdrToneMappingEnabled(r10)     // Catch:{ Exception -> 0x006e }
            java.lang.String r14 = "Tone-mapping requested but not supported by the decoder."
            androidx.media3.common.util.Assertions.checkArgument(r10, r14)     // Catch:{ Exception -> 0x006e }
        L_0x0057:
            if (r0 == 0) goto L_0x0060
            if (r13 != 0) goto L_0x0060
            android.view.Surface r10 = r2.createInputSurface()     // Catch:{ Exception -> 0x006e }
            r1 = r10
        L_0x0060:
            startCodec(r2)     // Catch:{ Exception -> 0x006e }
            r8.mediaCodec = r2
            r8.inputSurface = r1
            int r9 = androidx.media3.common.util.Util.getMaxPendingFramesCountForMediaCodecDecoders(r9)
            r8.maxPendingFrameCount = r9
            return
        L_0x006e:
            r9 = move-exception
            r5 = r9
            goto L_0x0074
        L_0x0071:
            r9 = move-exception
            r5 = r9
            r2 = r1
        L_0x0074:
            java.lang.String r9 = "DefaultCodec"
            java.lang.String r10 = "MediaCodec error"
            androidx.media3.common.util.Log.d(r9, r10, r5)
            if (r1 == 0) goto L_0x0080
            r1.release()
        L_0x0080:
            if (r2 == 0) goto L_0x0085
            r2.release()
        L_0x0085:
            boolean r9 = r5 instanceof java.io.IOException
            if (r9 != 0) goto L_0x009d
            boolean r9 = r5 instanceof android.media.MediaCodec.CodecException
            if (r9 == 0) goto L_0x008e
            goto L_0x009d
        L_0x008e:
            boolean r9 = r5 instanceof java.lang.IllegalArgumentException
            if (r9 == 0) goto L_0x009a
            if (r13 == 0) goto L_0x0097
            r9 = 3003(0xbbb, float:4.208E-42)
            goto L_0x00a4
        L_0x0097:
            r9 = 4003(0xfa3, float:5.61E-42)
            goto L_0x00a4
        L_0x009a:
            r9 = 1001(0x3e9, float:1.403E-42)
            goto L_0x00a4
        L_0x009d:
            if (r13 == 0) goto L_0x00a2
            r9 = 3001(0xbb9, float:4.205E-42)
            goto L_0x00a4
        L_0x00a2:
            r9 = 4001(0xfa1, float:5.607E-42)
        L_0x00a4:
            r6 = r9
            boolean r3 = r8.isVideo
            r2 = r11
            r4 = r13
            r7 = r12
            androidx.media3.transformer.ExportException r9 = createExportException(r2, r3, r4, r5, r6, r7)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.DefaultCodec.<init>(android.content.Context, androidx.media3.common.Format, android.media.MediaFormat, java.lang.String, boolean, android.view.Surface):void");
    }

    public Format getConfigurationFormat() {
        return this.configurationFormat;
    }

    public Surface getInputSurface() {
        return (Surface) Assertions.checkStateNotNull(this.inputSurface);
    }

    public int getMaxPendingFrameCount() {
        return this.maxPendingFrameCount;
    }

    @EnsuresNonNullIf(expression = {"#1.data"}, result = true)
    public boolean maybeDequeueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExportException {
        if (this.inputStreamEnded) {
            return false;
        }
        if (this.inputBufferIndex < 0) {
            try {
                int dequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(0);
                this.inputBufferIndex = dequeueInputBuffer;
                if (dequeueInputBuffer < 0) {
                    return false;
                }
                try {
                    decoderInputBuffer.data = this.mediaCodec.getInputBuffer(dequeueInputBuffer);
                    decoderInputBuffer.clear();
                } catch (RuntimeException e) {
                    Log.d(TAG, "MediaCodec error", e);
                    throw createExportException(e);
                }
            } catch (RuntimeException e2) {
                Log.d(TAG, "MediaCodec error", e2);
                throw createExportException(e2);
            }
        }
        Assertions.checkNotNull(decoderInputBuffer.data);
        return true;
    }

    public void queueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExportException {
        int i;
        int i2;
        int i3;
        int i4;
        DecoderInputBuffer decoderInputBuffer2 = decoderInputBuffer;
        boolean z = true;
        Assertions.checkState(!this.inputStreamEnded, "Input buffer can not be queued after the input stream has ended.");
        int i5 = 0;
        if (decoderInputBuffer2.data == null || !decoderInputBuffer2.data.hasRemaining()) {
            i2 = 0;
            i = 0;
        } else {
            i2 = decoderInputBuffer2.data.position();
            i = decoderInputBuffer2.data.remaining();
        }
        long j = decoderInputBuffer2.timeUs;
        if (decoderInputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            debugTraceLogEvent(DebugTraceUtil.EVENT_INPUT_ENDED, Long.MIN_VALUE);
            if (this.isDecoder) {
                if (decoderInputBuffer2.data != null && decoderInputBuffer2.data.hasRemaining()) {
                    z = false;
                }
                Assertions.checkState(z);
                j = 0;
                i4 = 0;
            } else {
                i4 = i2;
                i5 = i;
            }
            i3 = 4;
        } else {
            i4 = i2;
            i3 = 0;
            i5 = i;
        }
        try {
            this.mediaCodec.queueInputBuffer(this.inputBufferIndex, i4, i5, j, i3);
            debugTraceLogEvent(DebugTraceUtil.EVENT_ACCEPTED_INPUT, j, "bytes=%s", Integer.valueOf(i5));
            this.inputBufferIndex = -1;
            decoderInputBuffer2.data = null;
        } catch (RuntimeException e) {
            Log.d(TAG, "MediaCodec error", e);
            throw createExportException(e);
        }
    }

    public void signalEndOfInputStream() throws ExportException {
        if (!this.videoOutputStarted.get()) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        debugTraceLogEvent(DebugTraceUtil.EVENT_INPUT_ENDED, Long.MIN_VALUE);
        try {
            this.mediaCodec.signalEndOfInputStream();
        } catch (RuntimeException e) {
            Log.d(TAG, "MediaCodec error", e);
            throw createExportException(e);
        }
    }

    public Format getOutputFormat() throws ExportException {
        maybeDequeueOutputBuffer(false);
        return this.outputFormat;
    }

    public ByteBuffer getOutputBuffer() throws ExportException {
        if (!maybeDequeueOutputBuffer(true)) {
            return null;
        }
        debugTraceLogEvent(DebugTraceUtil.EVENT_PRODUCED_OUTPUT, this.outputBufferInfo.presentationTimeUs, "bytesOutput=%s", Integer.valueOf(this.outputBufferInfo.size));
        return this.outputBuffer;
    }

    public MediaCodec.BufferInfo getOutputBufferInfo() throws ExportException {
        if (maybeDequeueOutputBuffer(false)) {
            return this.outputBufferInfo;
        }
        return null;
    }

    public void releaseOutputBuffer(boolean z) throws ExportException {
        releaseOutputBuffer(z, ((MediaCodec.BufferInfo) Assertions.checkStateNotNull(this.outputBufferInfo)).presentationTimeUs);
    }

    public void releaseOutputBuffer(long j) throws ExportException {
        releaseOutputBuffer(true, j);
    }

    /* access modifiers changed from: protected */
    public void releaseOutputBuffer(boolean z, long j) throws ExportException {
        this.outputBuffer = null;
        if (z) {
            try {
                this.mediaCodec.releaseOutputBuffer(this.outputBufferIndex, 1000 * j);
                debugTraceLogEvent(DebugTraceUtil.EVENT_PRODUCED_OUTPUT, j);
            } catch (RuntimeException e) {
                Log.d(TAG, "MediaCodec error", e);
                throw createExportException(e);
            }
        } else {
            this.mediaCodec.releaseOutputBuffer(this.outputBufferIndex, false);
        }
        this.outputBufferIndex = -1;
    }

    public boolean isEnded() {
        return this.outputStreamEnded && this.outputBufferIndex == -1;
    }

    public void release() {
        this.outputBuffer = null;
        Surface surface = this.inputSurface;
        if (surface != null) {
            surface.release();
        }
        this.mediaCodec.release();
    }

    public String getName() {
        return Util.SDK_INT >= 29 ? Api29.getCanonicalName(this.mediaCodec) : this.mediaCodec.getName();
    }

    /* access modifiers changed from: package-private */
    public MediaFormat getConfigurationMediaFormat() {
        return this.configurationMediaFormat;
    }

    private boolean maybeDequeueOutputBuffer(boolean z) throws ExportException {
        if (this.outputBufferIndex >= 0) {
            return true;
        }
        if (this.outputStreamEnded) {
            return false;
        }
        try {
            int dequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(this.outputBufferInfo, 0);
            this.outputBufferIndex = dequeueOutputBuffer;
            if (dequeueOutputBuffer < 0) {
                if (dequeueOutputBuffer == -2) {
                    this.outputFormat = convertToFormat(this.mediaCodec.getOutputFormat(), this.isDecoder, this.configurationFormat.metadata);
                    if (this.isDecoder && Objects.equals(this.configurationFormat.sampleMimeType, MimeTypes.AUDIO_RAW)) {
                        this.outputFormat = this.outputFormat.buildUpon().setChannelCount(this.configurationFormat.channelCount).setPcmEncoding(this.configurationFormat.pcmEncoding).build();
                    }
                    if (!this.isDecoder && this.isVideo) {
                        this.videoOutputStarted.set(true);
                    }
                    debugTraceLogEvent(DebugTraceUtil.EVENT_OUTPUT_FORMAT, this.outputBufferInfo.presentationTimeUs, "%s", this.outputFormat);
                }
                return false;
            }
            if ((this.outputBufferInfo.flags & 4) != 0) {
                this.outputStreamEnded = true;
                debugTraceLogEvent(DebugTraceUtil.EVENT_OUTPUT_ENDED, Long.MIN_VALUE);
                if (this.outputBufferInfo.size == 0) {
                    releaseOutputBuffer(false);
                    return false;
                }
                this.outputBufferInfo.flags &= -5;
            }
            if ((this.outputBufferInfo.flags & 2) != 0) {
                releaseOutputBuffer(false);
                return false;
            }
            if (z) {
                try {
                    ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(this.mediaCodec.getOutputBuffer(this.outputBufferIndex));
                    this.outputBuffer = byteBuffer;
                    byteBuffer.position(this.outputBufferInfo.offset);
                    this.outputBuffer.limit(this.outputBufferInfo.offset + this.outputBufferInfo.size);
                } catch (RuntimeException e) {
                    Log.d(TAG, "MediaCodec error", e);
                    throw createExportException(e);
                }
            }
            return true;
        } catch (RuntimeException e2) {
            Log.d(TAG, "MediaCodec error", e2);
            throw createExportException(e2);
        }
    }

    private ExportException createExportException(Exception exc) {
        MediaFormat mediaFormat = this.configurationMediaFormat;
        boolean z = this.isVideo;
        boolean z2 = this.isDecoder;
        return createExportException(mediaFormat, z, z2, exc, z2 ? 3002 : 4002, getName());
    }

    private static ExportException createExportException(MediaFormat mediaFormat, boolean z, boolean z2, Exception exc, int i, String str) {
        return ExportException.createForCodec(exc, i, new ExportException.CodecInfo(mediaFormat.toString(), z, z2, str));
    }

    private static Format convertToFormat(MediaFormat mediaFormat, boolean z, Metadata metadata) {
        Format createFormatFromMediaFormat = MediaFormatUtil.createFormatFromMediaFormat(mediaFormat);
        Format.Builder metadata2 = createFormatFromMediaFormat.buildUpon().setMetadata(metadata);
        if (z && createFormatFromMediaFormat.pcmEncoding == -1 && Objects.equals(createFormatFromMediaFormat.sampleMimeType, MimeTypes.AUDIO_RAW)) {
            metadata2.setPcmEncoding(2);
        }
        return metadata2.build();
    }

    private static void configureCodec(MediaCodec mediaCodec2, MediaFormat mediaFormat, boolean z, Surface surface) {
        TraceUtil.beginSection("configureCodec");
        mediaCodec2.configure(mediaFormat, surface, (MediaCrypto) null, z ^ true ? 1 : 0);
        TraceUtil.endSection();
    }

    private static void startCodec(MediaCodec mediaCodec2) {
        TraceUtil.beginSection("startCodec");
        mediaCodec2.start();
        TraceUtil.endSection();
    }

    private static boolean isSdrToneMappingEnabled(MediaFormat mediaFormat) {
        if (Util.SDK_INT < 31 || MediaFormatUtil.getInteger(mediaFormat, "color-transfer-request", 0) != 3) {
            return false;
        }
        return true;
    }

    private void debugTraceLogEvent(String str, long j) {
        debugTraceLogEvent(str, j, "", new Object[0]);
    }

    private void debugTraceLogEvent(String str, long j, String str2, Object... objArr) {
        DebugTraceUtil.logCodecEvent(this.isDecoder, this.isVideo, str, j, str2, objArr);
    }

    private static final class Api29 {
        private Api29() {
        }

        public static String getCanonicalName(MediaCodec mediaCodec) {
            return mediaCodec.getCanonicalName();
        }
    }
}
