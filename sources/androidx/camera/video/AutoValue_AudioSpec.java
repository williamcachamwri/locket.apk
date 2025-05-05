package androidx.camera.video;

import android.util.Range;
import androidx.camera.video.AudioSpec;

final class AutoValue_AudioSpec extends AudioSpec {
    private final Range<Integer> bitrate;
    private final int channelCount;
    private final Range<Integer> sampleRate;
    private final int source;
    private final int sourceFormat;

    private AutoValue_AudioSpec(Range<Integer> range, int i, int i2, Range<Integer> range2, int i3) {
        this.bitrate = range;
        this.sourceFormat = i;
        this.source = i2;
        this.sampleRate = range2;
        this.channelCount = i3;
    }

    public Range<Integer> getBitrate() {
        return this.bitrate;
    }

    public int getSourceFormat() {
        return this.sourceFormat;
    }

    public int getSource() {
        return this.source;
    }

    public Range<Integer> getSampleRate() {
        return this.sampleRate;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public String toString() {
        return "AudioSpec{bitrate=" + this.bitrate + ", sourceFormat=" + this.sourceFormat + ", source=" + this.source + ", sampleRate=" + this.sampleRate + ", channelCount=" + this.channelCount + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioSpec)) {
            return false;
        }
        AudioSpec audioSpec = (AudioSpec) obj;
        if (this.bitrate.equals(audioSpec.getBitrate()) && this.sourceFormat == audioSpec.getSourceFormat() && this.source == audioSpec.getSource() && this.sampleRate.equals(audioSpec.getSampleRate()) && this.channelCount == audioSpec.getChannelCount()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.bitrate.hashCode() ^ 1000003) * 1000003) ^ this.sourceFormat) * 1000003) ^ this.source) * 1000003) ^ this.sampleRate.hashCode()) * 1000003) ^ this.channelCount;
    }

    public AudioSpec.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends AudioSpec.Builder {
        private Range<Integer> bitrate;
        private Integer channelCount;
        private Range<Integer> sampleRate;
        private Integer source;
        private Integer sourceFormat;

        Builder() {
        }

        private Builder(AudioSpec audioSpec) {
            this.bitrate = audioSpec.getBitrate();
            this.sourceFormat = Integer.valueOf(audioSpec.getSourceFormat());
            this.source = Integer.valueOf(audioSpec.getSource());
            this.sampleRate = audioSpec.getSampleRate();
            this.channelCount = Integer.valueOf(audioSpec.getChannelCount());
        }

        public AudioSpec.Builder setBitrate(Range<Integer> range) {
            if (range != null) {
                this.bitrate = range;
                return this;
            }
            throw new NullPointerException("Null bitrate");
        }

        public AudioSpec.Builder setSourceFormat(int i) {
            this.sourceFormat = Integer.valueOf(i);
            return this;
        }

        public AudioSpec.Builder setSource(int i) {
            this.source = Integer.valueOf(i);
            return this;
        }

        public AudioSpec.Builder setSampleRate(Range<Integer> range) {
            if (range != null) {
                this.sampleRate = range;
                return this;
            }
            throw new NullPointerException("Null sampleRate");
        }

        public AudioSpec.Builder setChannelCount(int i) {
            this.channelCount = Integer.valueOf(i);
            return this;
        }

        public AudioSpec build() {
            String str = this.bitrate == null ? " bitrate" : "";
            if (this.sourceFormat == null) {
                str = str + " sourceFormat";
            }
            if (this.source == null) {
                str = str + " source";
            }
            if (this.sampleRate == null) {
                str = str + " sampleRate";
            }
            if (this.channelCount == null) {
                str = str + " channelCount";
            }
            if (str.isEmpty()) {
                return new AutoValue_AudioSpec(this.bitrate, this.sourceFormat.intValue(), this.source.intValue(), this.sampleRate, this.channelCount.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
