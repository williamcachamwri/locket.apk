package androidx.media3.transformer;

import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Clock;
import com.google.common.collect.ImmutableBiMap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class ExportException extends Exception {
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
    public static final int ERROR_CODE_MUXING_APPEND = 7003;
    public static final int ERROR_CODE_MUXING_FAILED = 7001;
    public static final int ERROR_CODE_MUXING_TIMEOUT = 7002;
    public static final int ERROR_CODE_UNSPECIFIED = 1000;
    public static final int ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED = 5001;
    static final ImmutableBiMap<String, Integer> NAME_TO_ERROR_CODE = new ImmutableBiMap.Builder().put("ERROR_CODE_FAILED_RUNTIME_CHECK", 1001).put("ERROR_CODE_IO_UNSPECIFIED", 2000).put("ERROR_CODE_IO_NETWORK_CONNECTION_FAILED", 2001).put("ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT", 2002).put("ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE", 2003).put("ERROR_CODE_IO_BAD_HTTP_STATUS", 2004).put("ERROR_CODE_IO_FILE_NOT_FOUND", 2005).put("ERROR_CODE_IO_NO_PERMISSION", 2006).put("ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED", 2007).put("ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE", 2008).put("ERROR_CODE_DECODER_INIT_FAILED", 3001).put("ERROR_CODE_DECODING_FAILED", 3002).put("ERROR_CODE_DECODING_FORMAT_UNSUPPORTED", 3003).put("ERROR_CODE_ENCODER_INIT_FAILED", 4001).put("ERROR_CODE_ENCODING_FAILED", 4002).put("ERROR_CODE_ENCODING_FORMAT_UNSUPPORTED", 4003).put("ERROR_CODE_VIDEO_FRAME_PROCESSING_FAILED", 5001).put("ERROR_CODE_AUDIO_PROCESSING_FAILED", 6001).put("ERROR_CODE_MUXING_FAILED", 7001).put("ERROR_CODE_MUXING_TIMEOUT", Integer.valueOf(ERROR_CODE_MUXING_TIMEOUT)).put("ERROR_CODE_MUXING_APPEND", Integer.valueOf(ERROR_CODE_MUXING_APPEND)).buildOrThrow();
    public final CodecInfo codecInfo;
    public final int errorCode;
    public final long timestampMs;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    public static final class CodecInfo {
        public final String configurationFormat;
        public final boolean isDecoder;
        public final boolean isVideo;
        public final String name;

        public CodecInfo(String str, boolean z, boolean z2, String str2) {
            this.configurationFormat = str;
            this.isVideo = z;
            this.isDecoder = z2;
            this.name = str2;
        }

        public String toString() {
            return "CodecInfo{type=" + (this.isVideo ? "Video" : "Audio").concat(this.isDecoder ? "Decoder" : "Encoder") + ", configurationFormat=" + this.configurationFormat + ", name=" + this.name + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public static String getErrorCodeName(int i) {
        return (String) NAME_TO_ERROR_CODE.inverse().getOrDefault(Integer.valueOf(i), "invalid error code");
    }

    public String getErrorCodeName() {
        return getErrorCodeName(this.errorCode);
    }

    public static ExportException createForAssetLoader(Throwable th, int i) {
        return new ExportException("Asset loader error", th, i);
    }

    public static ExportException createForCodec(Throwable th, int i, CodecInfo codecInfo2) {
        return new ExportException("Codec exception: " + codecInfo2, th, i, codecInfo2);
    }

    public static ExportException createForAudioProcessing(AudioProcessor.UnhandledAudioFormatException unhandledAudioFormatException, String str) {
        return new ExportException("Audio error: " + str + ", audioFormat=" + unhandledAudioFormatException.inputAudioFormat, unhandledAudioFormatException, 6001);
    }

    static ExportException createForVideoFrameProcessingException(VideoFrameProcessingException videoFrameProcessingException) {
        return new ExportException("Video frame processing error", videoFrameProcessingException, 5001);
    }

    static ExportException createForMuxer(Throwable th, int i) {
        return new ExportException("Muxer error", th, i);
    }

    public static ExportException createForUnexpected(Exception exc) {
        if (exc instanceof RuntimeException) {
            return new ExportException("Unexpected runtime error", exc, 1001);
        }
        return new ExportException("Unexpected error", exc, 1000);
    }

    private ExportException(String str, Throwable th, int i) {
        this(str, th, i, (CodecInfo) null);
    }

    private ExportException(String str, Throwable th, int i, CodecInfo codecInfo2) {
        super(str, th);
        this.errorCode = i;
        this.timestampMs = Clock.DEFAULT.elapsedRealtime();
        this.codecInfo = codecInfo2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r3 == null) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean errorInfoEquals(androidx.media3.transformer.ExportException r7) {
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.ExportException.errorInfoEquals(androidx.media3.transformer.ExportException):boolean");
    }
}
