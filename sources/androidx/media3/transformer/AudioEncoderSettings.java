package androidx.media3.transformer;

public final class AudioEncoderSettings {
    public static final AudioEncoderSettings DEFAULT = new Builder().build();
    public static final int NO_VALUE = -1;
    public final int bitrate;
    public final int profile;

    public static final class Builder {
        private int bitrate = -1;
        private int profile = -1;

        public Builder setProfile(int i) {
            this.profile = i;
            return this;
        }

        public Builder setBitrate(int i) {
            this.bitrate = i;
            return this;
        }

        public AudioEncoderSettings build() {
            return new AudioEncoderSettings(this.profile, this.bitrate);
        }
    }

    private AudioEncoderSettings(int i, int i2) {
        this.profile = i;
        this.bitrate = i2;
    }
}
