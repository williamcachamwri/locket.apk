package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderException;

public class MediaCodecDecoderException extends DecoderException {
    public final MediaCodecInfo codecInfo;
    public final String diagnosticInfo;
    public final int errorCode;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaCodecDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        super("Decoder failed: " + (mediaCodecInfo == null ? null : mediaCodecInfo.name), th);
        int i;
        String str = null;
        this.codecInfo = mediaCodecInfo;
        str = th instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException) th).getDiagnosticInfo() : str;
        this.diagnosticInfo = str;
        if (Util.SDK_INT >= 23) {
            i = getErrorCodeV23(th);
        } else {
            i = Util.getErrorCodeFromPlatformDiagnosticsInfo(str);
        }
        this.errorCode = i;
    }

    private static int getErrorCodeV23(Throwable th) {
        if (th instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) th).getErrorCode();
        }
        return 0;
    }
}
