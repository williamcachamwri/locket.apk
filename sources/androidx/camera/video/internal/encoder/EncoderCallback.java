package androidx.camera.video.internal.encoder;

public interface EncoderCallback {
    public static final EncoderCallback EMPTY = new EncoderCallback() {
        public void onEncodeError(EncodeException encodeException) {
        }

        public void onEncodeStart() {
        }

        public void onEncodeStop() {
        }

        public void onEncodedData(EncodedData encodedData) {
        }

        public void onOutputConfigUpdate(OutputConfig outputConfig) {
        }
    };

    void onEncodeError(EncodeException encodeException);

    void onEncodePaused() {
    }

    void onEncodeStart();

    void onEncodeStop();

    void onEncodedData(EncodedData encodedData);

    void onOutputConfigUpdate(OutputConfig outputConfig);
}
