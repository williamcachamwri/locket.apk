package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.StreamSpec;

final class AutoValue_StreamSpec extends StreamSpec {
    private final DynamicRange dynamicRange;
    private final Range<Integer> expectedFrameRateRange;
    private final Config implementationOptions;
    private final Size resolution;
    private final boolean zslDisabled;

    private AutoValue_StreamSpec(Size size, DynamicRange dynamicRange2, Range<Integer> range, Config config, boolean z) {
        this.resolution = size;
        this.dynamicRange = dynamicRange2;
        this.expectedFrameRateRange = range;
        this.implementationOptions = config;
        this.zslDisabled = z;
    }

    public Size getResolution() {
        return this.resolution;
    }

    public DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    public Range<Integer> getExpectedFrameRateRange() {
        return this.expectedFrameRateRange;
    }

    public Config getImplementationOptions() {
        return this.implementationOptions;
    }

    public boolean getZslDisabled() {
        return this.zslDisabled;
    }

    public String toString() {
        return "StreamSpec{resolution=" + this.resolution + ", dynamicRange=" + this.dynamicRange + ", expectedFrameRateRange=" + this.expectedFrameRateRange + ", implementationOptions=" + this.implementationOptions + ", zslDisabled=" + this.zslDisabled + "}";
    }

    public boolean equals(Object obj) {
        Config config;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StreamSpec)) {
            return false;
        }
        StreamSpec streamSpec = (StreamSpec) obj;
        if (!this.resolution.equals(streamSpec.getResolution()) || !this.dynamicRange.equals(streamSpec.getDynamicRange()) || !this.expectedFrameRateRange.equals(streamSpec.getExpectedFrameRateRange()) || ((config = this.implementationOptions) != null ? !config.equals(streamSpec.getImplementationOptions()) : streamSpec.getImplementationOptions() != null) || this.zslDisabled != streamSpec.getZslDisabled()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((((this.resolution.hashCode() ^ 1000003) * 1000003) ^ this.dynamicRange.hashCode()) * 1000003) ^ this.expectedFrameRateRange.hashCode()) * 1000003;
        Config config = this.implementationOptions;
        return ((hashCode ^ (config == null ? 0 : config.hashCode())) * 1000003) ^ (this.zslDisabled ? 1231 : 1237);
    }

    public StreamSpec.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder extends StreamSpec.Builder {
        private DynamicRange dynamicRange;
        private Range<Integer> expectedFrameRateRange;
        private Config implementationOptions;
        private Size resolution;
        private Boolean zslDisabled;

        Builder() {
        }

        private Builder(StreamSpec streamSpec) {
            this.resolution = streamSpec.getResolution();
            this.dynamicRange = streamSpec.getDynamicRange();
            this.expectedFrameRateRange = streamSpec.getExpectedFrameRateRange();
            this.implementationOptions = streamSpec.getImplementationOptions();
            this.zslDisabled = Boolean.valueOf(streamSpec.getZslDisabled());
        }

        public StreamSpec.Builder setResolution(Size size) {
            if (size != null) {
                this.resolution = size;
                return this;
            }
            throw new NullPointerException("Null resolution");
        }

        public StreamSpec.Builder setDynamicRange(DynamicRange dynamicRange2) {
            if (dynamicRange2 != null) {
                this.dynamicRange = dynamicRange2;
                return this;
            }
            throw new NullPointerException("Null dynamicRange");
        }

        public StreamSpec.Builder setExpectedFrameRateRange(Range<Integer> range) {
            if (range != null) {
                this.expectedFrameRateRange = range;
                return this;
            }
            throw new NullPointerException("Null expectedFrameRateRange");
        }

        public StreamSpec.Builder setImplementationOptions(Config config) {
            this.implementationOptions = config;
            return this;
        }

        public StreamSpec.Builder setZslDisabled(boolean z) {
            this.zslDisabled = Boolean.valueOf(z);
            return this;
        }

        public StreamSpec build() {
            String str = this.resolution == null ? " resolution" : "";
            if (this.dynamicRange == null) {
                str = str + " dynamicRange";
            }
            if (this.expectedFrameRateRange == null) {
                str = str + " expectedFrameRateRange";
            }
            if (this.zslDisabled == null) {
                str = str + " zslDisabled";
            }
            if (str.isEmpty()) {
                return new AutoValue_StreamSpec(this.resolution, this.dynamicRange, this.expectedFrameRateRange, this.implementationOptions, this.zslDisabled.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
