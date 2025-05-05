package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Clock;
import com.google.common.collect.ImmutableBiMap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
public final class TransformationException extends Exception {
    public static final int ERROR_CODE_AUDIO_PROCESSING_FAILED = 6001;
    public static final int ERROR_CODE_DECODER_INIT_FAILED = 3001;
    public static final int ERROR_CODE_DECODING_FAILED = 3002;
    public static final int ERROR_CODE_DECODING_FORMAT_UNSUPPORTED = 3003;
    public static final int ERROR_CODE_ENCODER_INIT_FAILED = 4001;
    public static final int ERROR_CODE_ENCODING_FAILED = 4002;
    public static final int ERROR_CODE_ENCODING_FORMAT_UNSUPPORTED = 4003;
    public static final int ERROR_CODE_FAILED_RUNTIME_CHECK = 1001;
    public static final int ERROR_CODE_IO_BAD_HTTP_STATUS = 2004;
    public static final int ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED = 2007;
    public static final int ERROR_CODE_IO_FILE_NOT_FOUND = 2005;
    public static final int ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE = 2003;
    public static final int ERROR_CODE_IO_NETWORK_CONNECTION_FAILED = 2001;
    public static final int ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT = 2002;
    public static final int ERROR_CODE_IO_NO_PERMISSION = 2006;
    public static final int ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE = 2008;
    public static final int ERROR_CODE_IO_UNSPECIFIED = 2000;
    public static final int ERROR_CODE_MUXING_FAILED = 7001;
    public static final int ERROR_CODE_UNSPECIFIED = 1000;
    public static final int ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED = 5001;
    static final ImmutableBiMap<String, Integer> NAME_TO_ERROR_CODE = new ImmutableBiMap.Builder().put("ERROR_CODE_FAILED_RUNTIME_CHECK", 1001).put("ERROR_CODE_IO_UNSPECIFIED", 2000).put("ERROR_CODE_IO_NETWORK_CONNECTION_FAILED", 2001).put("ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT", 2002).put("ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE", 2003).put("ERROR_CODE_IO_BAD_HTTP_STATUS", 2004).put("ERROR_CODE_IO_FILE_NOT_FOUND", 2005).put("ERROR_CODE_IO_NO_PERMISSION", 2006).put("ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED", 2007).put("ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE", 2008).put("ERROR_CODE_DECODER_INIT_FAILED", 3001).put("ERROR_CODE_DECODING_FAILED", 3002).put("ERROR_CODE_DECODING_FORMAT_UNSUPPORTED", 3003).put("ERROR_CODE_ENCODER_INIT_FAILED", 4001).put("ERROR_CODE_ENCODING_FAILED", 4002).put("ERROR_CODE_ENCODING_FORMAT_UNSUPPORTED", 4003).put("ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED", 5001).put("ERROR_CODE_AUDIO_PROCESSING_FAILED", 6001).put("ERROR_CODE_MUXING_FAILED", 7001).buildOrThrow();
    public final int errorCode;
    public final long timestampMs;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    public static String getErrorCodeName(int i) {
        return (String) NAME_TO_ERROR_CODE.inverse().getOrDefault(Integer.valueOf(i), "invalid error code");
    }

    public String getErrorCodeName() {
        return getErrorCodeName(this.errorCode);
    }

    public static TransformationException createForAssetLoader(Throwable th, int i) {
        return new TransformationException("Asset loader error", th, i);
    }

    public static TransformationException createForCodec(Throwable th, int i, boolean z, boolean z2, Format format) {
        return createForCodec(th, i, z, z2, "format=" + format);
    }

    public static TransformationException createForCodec(Throwable th, int i, boolean z, boolean z2, String str) {
        return new TransformationException((z ? "Video" : "Audio").concat(z2 ? "Decoder" : "Encoder") + " error: " + str, th, i);
    }

    public static TransformationException createForAudioProcessing(Throwable th, AudioProcessor.AudioFormat audioFormat) {
        return new TransformationException("Audio processing error, audio_format=" + audioFormat, th, 6001);
    }

    static TransformationException createForVideoFrameProcessingException(VideoFrameProcessingException videoFrameProcessingException, int i) {
        return new TransformationException("Video frame processing error", videoFrameProcessingException, i);
    }

    static TransformationException createForMuxer(Throwable th, int i) {
        return new TransformationException("Muxer error", th, i);
    }

    public static TransformationException createForUnexpected(Exception exc) {
        if (exc instanceof RuntimeException) {
            return new TransformationException("Unexpected runtime error", exc, 1001);
        }
        return new TransformationException("Unexpected error", exc, 1000);
    }

    private TransformationException(String str, Throwable th, int i) {
        super(str, th);
        this.errorCode = i;
        this.timestampMs = Clock.DEFAULT.elapsedRealtime();
    }

    TransformationException(ExportException exportException) {
        super(exportException.getMessage(), exportException.getCause());
        this.errorCode = exportException.errorCode;
        this.timestampMs = exportException.timestampMs;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r3 == null) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean errorInfoEquals(androidx.media3.transformer.TransformationException r7) {
        /*
            r6 = this;
            r0 = 1
            if (r6 != r7) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r7 == 0) goto L_0x0060
            java.lang.Class r2 = r6.getClass()
            java.lang.Class r3 = r7.getClass()
            if (r2 == r3) goto L_0x0012
            goto L_0x0060
        L_0x0012:
            java.lang.Throwable r2 = r6.getCause()
            java.lang.Throwable r3 = r7.getCause()
            if (r2 == 0) goto L_0x003c
            if (r3 == 0) goto L_0x003c
            java.lang.String r4 = r2.getMessage()
            java.lang.String r5 = r3.getMessage()
            boolean r4 = androidx.media3.common.util.Util.areEqual(r4, r5)
            if (r4 != 0) goto L_0x002d
            return r1
        L_0x002d:
            java.lang.Class r2 = r2.getClass()
            java.lang.Class r3 = r3.getClass()
            boolean r2 = androidx.media3.common.util.Util.areEqual(r2, r3)
            if (r2 != 0) goto L_0x0041
            return r1
        L_0x003c:
            if (r2 != 0) goto L_0x0060
            if (r3 == 0) goto L_0x0041
            goto L_0x0060
        L_0x0041:
            int r2 = r6.errorCode
            int r3 = r7.errorCode
            if (r2 != r3) goto L_0x005e
            java.lang.String r2 = r6.getMessage()
            java.lang.String r3 = r7.getMessage()
            boolean r2 = androidx.media3.common.util.Util.areEqual(r2, r3)
            if (r2 == 0) goto L_0x005e
            long r2 = r6.timestampMs
            long r4 = r7.timestampMs
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r0 = r1
        L_0x005f:
            return r0
        L_0x0060:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransformationException.errorInfoEquals(androidx.media3.transformer.TransformationException):boolean");
    }
}
