package androidx.media3.exoplayer.image;

import androidx.media3.decoder.DecoderException;

public final class ImageDecoderException extends DecoderException {
    public ImageDecoderException(String str) {
        super(str);
    }

    public ImageDecoderException(Throwable th) {
        super(th);
    }

    public ImageDecoderException(String str, Throwable th) {
        super(str, th);
    }
}
