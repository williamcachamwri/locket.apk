package androidx.camera.core.impl;

import androidx.camera.core.impl.EncoderProfilesProxy;
import java.util.List;

final class AutoValue_EncoderProfilesProxy_ImmutableEncoderProfilesProxy extends EncoderProfilesProxy.ImmutableEncoderProfilesProxy {
    private final List<EncoderProfilesProxy.AudioProfileProxy> audioProfiles;
    private final int defaultDurationSeconds;
    private final int recommendedFileFormat;
    private final List<EncoderProfilesProxy.VideoProfileProxy> videoProfiles;

    AutoValue_EncoderProfilesProxy_ImmutableEncoderProfilesProxy(int i, int i2, List<EncoderProfilesProxy.AudioProfileProxy> list, List<EncoderProfilesProxy.VideoProfileProxy> list2) {
        this.defaultDurationSeconds = i;
        this.recommendedFileFormat = i2;
        if (list != null) {
            this.audioProfiles = list;
            if (list2 != null) {
                this.videoProfiles = list2;
                return;
            }
            throw new NullPointerException("Null videoProfiles");
        }
        throw new NullPointerException("Null audioProfiles");
    }

    public int getDefaultDurationSeconds() {
        return this.defaultDurationSeconds;
    }

    public int getRecommendedFileFormat() {
        return this.recommendedFileFormat;
    }

    public List<EncoderProfilesProxy.AudioProfileProxy> getAudioProfiles() {
        return this.audioProfiles;
    }

    public List<EncoderProfilesProxy.VideoProfileProxy> getVideoProfiles() {
        return this.videoProfiles;
    }

    public String toString() {
        return "ImmutableEncoderProfilesProxy{defaultDurationSeconds=" + this.defaultDurationSeconds + ", recommendedFileFormat=" + this.recommendedFileFormat + ", audioProfiles=" + this.audioProfiles + ", videoProfiles=" + this.videoProfiles + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EncoderProfilesProxy.ImmutableEncoderProfilesProxy)) {
            return false;
        }
        EncoderProfilesProxy.ImmutableEncoderProfilesProxy immutableEncoderProfilesProxy = (EncoderProfilesProxy.ImmutableEncoderProfilesProxy) obj;
        if (this.defaultDurationSeconds != immutableEncoderProfilesProxy.getDefaultDurationSeconds() || this.recommendedFileFormat != immutableEncoderProfilesProxy.getRecommendedFileFormat() || !this.audioProfiles.equals(immutableEncoderProfilesProxy.getAudioProfiles()) || !this.videoProfiles.equals(immutableEncoderProfilesProxy.getVideoProfiles())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.defaultDurationSeconds ^ 1000003) * 1000003) ^ this.recommendedFileFormat) * 1000003) ^ this.audioProfiles.hashCode()) * 1000003) ^ this.videoProfiles.hashCode();
    }
}
