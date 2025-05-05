package androidx.media3.transformer;

import androidx.media3.common.util.Assertions;
import com.facebook.imageutils.JfifUtil;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class VideoEncoderSettings {
    public static final VideoEncoderSettings DEFAULT = new Builder().build();
    public static final float DEFAULT_I_FRAME_INTERVAL_SECONDS = 1.0f;
    public static final int NO_VALUE = -1;
    public static final int RATE_UNSET = -2;
    public final int bitrate;
    public final int bitrateMode;
    public final boolean enableHighQualityTargeting;
    public final float iFrameIntervalSeconds;
    public final int level;
    public final int operatingRate;
    public final int priority;
    public final int profile;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BitrateMode {
    }

    public static final class Builder {
        private int bitrate;
        private int bitrateMode;
        private boolean enableHighQualityTargeting;
        private float iFrameIntervalSeconds;
        private int level;
        private int operatingRate;
        private int priority;
        private int profile;

        public Builder() {
            this.bitrate = -1;
            this.bitrateMode = 1;
            this.profile = -1;
            this.level = -1;
            this.iFrameIntervalSeconds = 1.0f;
            this.operatingRate = -1;
            this.priority = -1;
        }

        private Builder(VideoEncoderSettings videoEncoderSettings) {
            this.bitrate = videoEncoderSettings.bitrate;
            this.bitrateMode = videoEncoderSettings.bitrateMode;
            this.profile = videoEncoderSettings.profile;
            this.level = videoEncoderSettings.level;
            this.iFrameIntervalSeconds = videoEncoderSettings.iFrameIntervalSeconds;
            this.operatingRate = videoEncoderSettings.operatingRate;
            this.priority = videoEncoderSettings.priority;
            this.enableHighQualityTargeting = videoEncoderSettings.enableHighQualityTargeting;
        }

        public Builder setBitrate(int i) {
            this.bitrate = i;
            return this;
        }

        public Builder setBitrateMode(int i) {
            boolean z = true;
            if (!(i == 1 || i == 2)) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.bitrateMode = i;
            return this;
        }

        public Builder setEncodingProfileLevel(int i, int i2) {
            this.profile = i;
            this.level = i2;
            return this;
        }

        public Builder setiFrameIntervalSeconds(float f) {
            this.iFrameIntervalSeconds = f;
            return this;
        }

        public Builder setEncoderPerformanceParameters(int i, int i2) {
            boolean z = true;
            if ((i == -2) != (i2 == -2)) {
                z = false;
            }
            Assertions.checkArgument(z);
            this.operatingRate = i;
            this.priority = i2;
            return this;
        }

        public Builder experimentalSetEnableHighQualityTargeting(boolean z) {
            this.enableHighQualityTargeting = z;
            return this;
        }

        public VideoEncoderSettings build() {
            boolean z = false;
            Assertions.checkState(!this.enableHighQualityTargeting || this.bitrate == -1, "Bitrate can not be set if enabling high quality targeting.");
            if (!this.enableHighQualityTargeting || this.bitrateMode == 1) {
                z = true;
            }
            Assertions.checkState(z, "Bitrate mode must be VBR if enabling high quality targeting.");
            return new VideoEncoderSettings(this.bitrate, this.bitrateMode, this.profile, this.level, this.iFrameIntervalSeconds, this.operatingRate, this.priority, this.enableHighQualityTargeting);
        }
    }

    private VideoEncoderSettings(int i, int i2, int i3, int i4, float f, int i5, int i6, boolean z) {
        this.bitrate = i;
        this.bitrateMode = i2;
        this.profile = i3;
        this.level = i4;
        this.iFrameIntervalSeconds = f;
        this.operatingRate = i5;
        this.priority = i6;
        this.enableHighQualityTargeting = z;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoEncoderSettings)) {
            return false;
        }
        VideoEncoderSettings videoEncoderSettings = (VideoEncoderSettings) obj;
        if (this.bitrate == videoEncoderSettings.bitrate && this.bitrateMode == videoEncoderSettings.bitrateMode && this.profile == videoEncoderSettings.profile && this.level == videoEncoderSettings.level && this.iFrameIntervalSeconds == videoEncoderSettings.iFrameIntervalSeconds && this.operatingRate == videoEncoderSettings.operatingRate && this.priority == videoEncoderSettings.priority && this.enableHighQualityTargeting == videoEncoderSettings.enableHighQualityTargeting) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((JfifUtil.MARKER_EOI + this.bitrate) * 31) + this.bitrateMode) * 31) + this.profile) * 31) + this.level) * 31) + Float.floatToIntBits(this.iFrameIntervalSeconds)) * 31) + this.operatingRate) * 31) + this.priority) * 31) + (this.enableHighQualityTargeting ? 1 : 0);
    }
}
