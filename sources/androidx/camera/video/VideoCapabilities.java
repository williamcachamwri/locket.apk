package androidx.camera.video;

import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface VideoCapabilities {
    public static final VideoCapabilities EMPTY = new VideoCapabilities() {
        public boolean isQualitySupported(Quality quality, DynamicRange dynamicRange) {
            return false;
        }

        public boolean isStabilizationSupported() {
            return false;
        }

        public Set<DynamicRange> getSupportedDynamicRanges() {
            return new HashSet();
        }

        public List<Quality> getSupportedQualities(DynamicRange dynamicRange) {
            return new ArrayList();
        }
    };

    VideoValidatedEncoderProfilesProxy findNearestHigherSupportedEncoderProfilesFor(Size size, DynamicRange dynamicRange) {
        return null;
    }

    VideoValidatedEncoderProfilesProxy getProfiles(Quality quality, DynamicRange dynamicRange) {
        return null;
    }

    Set<DynamicRange> getSupportedDynamicRanges();

    List<Quality> getSupportedQualities(DynamicRange dynamicRange);

    boolean isQualitySupported(Quality quality, DynamicRange dynamicRange);

    boolean isStabilizationSupported() {
        return false;
    }

    Quality findNearestHigherSupportedQualityFor(Size size, DynamicRange dynamicRange) {
        return Quality.NONE;
    }
}
