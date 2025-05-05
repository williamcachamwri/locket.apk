package androidx.camera.video;

import android.util.Range;
import androidx.camera.video.VideoSpec;

final class AutoValue_VideoSpec extends VideoSpec {
    private final int aspectRatio;
    private final Range<Integer> bitrate;
    private final Range<Integer> frameRate;
    private final QualitySelector qualitySelector;

    private AutoValue_VideoSpec(QualitySelector qualitySelector2, Range<Integer> range, Range<Integer> range2, int i) {
        this.qualitySelector = qualitySelector2;
        this.frameRate = range;
        this.bitrate = range2;
        this.aspectRatio = i;
    }

    public QualitySelector getQualitySelector() {
        return this.qualitySelector;
    }

    public Range<Integer> getFrameRate() {
        return this.frameRate;
    }

    public Range<Integer> getBitrate() {
        return this.bitrate;
    }

    /* access modifiers changed from: package-private */
    public int getAspectRatio() {
        return this.aspectRatio;
    }

    public String toString() {
        return "VideoSpec{qualitySelector=" + this.qualitySelector + ", frameRate=" + this.frameRate + ", bitrate=" + this.bitrate + ", aspectRatio=" + this.aspectRatio + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoSpec)) {
            return false;
        }
        VideoSpec videoSpec = (VideoSpec) obj;
        if (!this.qualitySelector.equals(videoSpec.getQualitySelector()) || !this.frameRate.equals(videoSpec.getFrameRate()) || !this.bitrate.equals(videoSpec.getBitrate()) || this.aspectRatio != videoSpec.getAspectRatio()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.qualitySelector.hashCode() ^ 1000003) * 1000003) ^ this.frameRate.hashCode()) * 1000003) ^ this.bitrate.hashCode()) * 1000003) ^ this.aspectRatio;
    }

    public VideoSpec.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends VideoSpec.Builder {
        private Integer aspectRatio;
        private Range<Integer> bitrate;
        private Range<Integer> frameRate;
        private QualitySelector qualitySelector;

        Builder() {
        }

        private Builder(VideoSpec videoSpec) {
            this.qualitySelector = videoSpec.getQualitySelector();
            this.frameRate = videoSpec.getFrameRate();
            this.bitrate = videoSpec.getBitrate();
            this.aspectRatio = Integer.valueOf(videoSpec.getAspectRatio());
        }

        public VideoSpec.Builder setQualitySelector(QualitySelector qualitySelector2) {
            if (qualitySelector2 != null) {
                this.qualitySelector = qualitySelector2;
                return this;
            }
            throw new NullPointerException("Null qualitySelector");
        }

        public VideoSpec.Builder setFrameRate(Range<Integer> range) {
            if (range != null) {
                this.frameRate = range;
                return this;
            }
            throw new NullPointerException("Null frameRate");
        }

        public VideoSpec.Builder setBitrate(Range<Integer> range) {
            if (range != null) {
                this.bitrate = range;
                return this;
            }
            throw new NullPointerException("Null bitrate");
        }

        /* access modifiers changed from: package-private */
        public VideoSpec.Builder setAspectRatio(int i) {
            this.aspectRatio = Integer.valueOf(i);
            return this;
        }

        public VideoSpec build() {
            String str = this.qualitySelector == null ? " qualitySelector" : "";
            if (this.frameRate == null) {
                str = str + " frameRate";
            }
            if (this.bitrate == null) {
                str = str + " bitrate";
            }
            if (this.aspectRatio == null) {
                str = str + " aspectRatio";
            }
            if (str.isEmpty()) {
                return new AutoValue_VideoSpec(this.qualitySelector, this.frameRate, this.bitrate, this.aspectRatio.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
