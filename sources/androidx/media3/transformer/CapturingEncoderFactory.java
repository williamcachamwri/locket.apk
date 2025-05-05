package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.transformer.Codec;

final class CapturingEncoderFactory implements Codec.EncoderFactory {
    private String audioEncoderName;
    private final Codec.EncoderFactory encoderFactory;
    private String videoEncoderName;

    public CapturingEncoderFactory(Codec.EncoderFactory encoderFactory2) {
        this.encoderFactory = encoderFactory2;
    }

    public Codec createForAudioEncoding(Format format) throws ExportException {
        Codec createForAudioEncoding = this.encoderFactory.createForAudioEncoding(format);
        this.audioEncoderName = createForAudioEncoding.getName();
        return createForAudioEncoding;
    }

    public Codec createForVideoEncoding(Format format) throws ExportException {
        Codec createForVideoEncoding = this.encoderFactory.createForVideoEncoding(format);
        this.videoEncoderName = createForVideoEncoding.getName();
        return createForVideoEncoding;
    }

    public boolean audioNeedsEncoding() {
        return this.encoderFactory.audioNeedsEncoding();
    }

    public boolean videoNeedsEncoding() {
        return this.encoderFactory.videoNeedsEncoding();
    }

    public String getAudioEncoderName() {
        return this.audioEncoderName;
    }

    public String getVideoEncoderName() {
        return this.videoEncoderName;
    }
}
