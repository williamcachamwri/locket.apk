package androidx.camera.video.internal.audio;

import androidx.camera.video.internal.audio.AudioSettings;

final class AutoValue_AudioSettings extends AudioSettings {
    private final int audioFormat;
    private final int audioSource;
    private final int channelCount;
    private final int sampleRate;

    private AutoValue_AudioSettings(int i, int i2, int i3, int i4) {
        this.audioSource = i;
        this.sampleRate = i2;
        this.channelCount = i3;
        this.audioFormat = i4;
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getAudioFormat() {
        return this.audioFormat;
    }

    public String toString() {
        return "AudioSettings{audioSource=" + this.audioSource + ", sampleRate=" + this.sampleRate + ", channelCount=" + this.channelCount + ", audioFormat=" + this.audioFormat + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioSettings)) {
            return false;
        }
        AudioSettings audioSettings = (AudioSettings) obj;
        if (this.audioSource == audioSettings.getAudioSource() && this.sampleRate == audioSettings.getSampleRate() && this.channelCount == audioSettings.getChannelCount() && this.audioFormat == audioSettings.getAudioFormat()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.audioSource ^ 1000003) * 1000003) ^ this.sampleRate) * 1000003) ^ this.channelCount) * 1000003) ^ this.audioFormat;
    }

    public AudioSettings.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends AudioSettings.Builder {
        private Integer audioFormat;
        private Integer audioSource;
        private Integer channelCount;
        private Integer sampleRate;

        Builder() {
        }

        private Builder(AudioSettings audioSettings) {
            this.audioSource = Integer.valueOf(audioSettings.getAudioSource());
            this.sampleRate = Integer.valueOf(audioSettings.getSampleRate());
            this.channelCount = Integer.valueOf(audioSettings.getChannelCount());
            this.audioFormat = Integer.valueOf(audioSettings.getAudioFormat());
        }

        public AudioSettings.Builder setAudioSource(int i) {
            this.audioSource = Integer.valueOf(i);
            return this;
        }

        public AudioSettings.Builder setSampleRate(int i) {
            this.sampleRate = Integer.valueOf(i);
            return this;
        }

        public AudioSettings.Builder setChannelCount(int i) {
            this.channelCount = Integer.valueOf(i);
            return this;
        }

        public AudioSettings.Builder setAudioFormat(int i) {
            this.audioFormat = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: package-private */
        public AudioSettings autoBuild() {
            String str = this.audioSource == null ? " audioSource" : "";
            if (this.sampleRate == null) {
                str = str + " sampleRate";
            }
            if (this.channelCount == null) {
                str = str + " channelCount";
            }
            if (this.audioFormat == null) {
                str = str + " audioFormat";
            }
            if (str.isEmpty()) {
                return new AutoValue_AudioSettings(this.audioSource.intValue(), this.sampleRate.intValue(), this.channelCount.intValue(), this.audioFormat.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
