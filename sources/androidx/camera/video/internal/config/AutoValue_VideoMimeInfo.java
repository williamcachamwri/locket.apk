package androidx.camera.video.internal.config;

import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.internal.config.VideoMimeInfo;

final class AutoValue_VideoMimeInfo extends VideoMimeInfo {
    private final EncoderProfilesProxy.VideoProfileProxy compatibleVideoProfile;
    private final String mimeType;
    private final int profile;

    private AutoValue_VideoMimeInfo(String str, int i, EncoderProfilesProxy.VideoProfileProxy videoProfileProxy) {
        this.mimeType = str;
        this.profile = i;
        this.compatibleVideoProfile = videoProfileProxy;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getProfile() {
        return this.profile;
    }

    public EncoderProfilesProxy.VideoProfileProxy getCompatibleVideoProfile() {
        return this.compatibleVideoProfile;
    }

    public String toString() {
        return "VideoMimeInfo{mimeType=" + this.mimeType + ", profile=" + this.profile + ", compatibleVideoProfile=" + this.compatibleVideoProfile + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoMimeInfo)) {
            return false;
        }
        VideoMimeInfo videoMimeInfo = (VideoMimeInfo) obj;
        if (this.mimeType.equals(videoMimeInfo.getMimeType()) && this.profile == videoMimeInfo.getProfile()) {
            EncoderProfilesProxy.VideoProfileProxy videoProfileProxy = this.compatibleVideoProfile;
            if (videoProfileProxy == null) {
                if (videoMimeInfo.getCompatibleVideoProfile() == null) {
                    return true;
                }
            } else if (videoProfileProxy.equals(videoMimeInfo.getCompatibleVideoProfile())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.mimeType.hashCode() ^ 1000003) * 1000003) ^ this.profile) * 1000003;
        EncoderProfilesProxy.VideoProfileProxy videoProfileProxy = this.compatibleVideoProfile;
        return hashCode ^ (videoProfileProxy == null ? 0 : videoProfileProxy.hashCode());
    }

    static final class Builder extends VideoMimeInfo.Builder {
        private EncoderProfilesProxy.VideoProfileProxy compatibleVideoProfile;
        private String mimeType;
        private Integer profile;

        Builder() {
        }

        /* access modifiers changed from: protected */
        public VideoMimeInfo.Builder setMimeType(String str) {
            if (str != null) {
                this.mimeType = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        public VideoMimeInfo.Builder setProfile(int i) {
            this.profile = Integer.valueOf(i);
            return this;
        }

        public VideoMimeInfo.Builder setCompatibleVideoProfile(EncoderProfilesProxy.VideoProfileProxy videoProfileProxy) {
            this.compatibleVideoProfile = videoProfileProxy;
            return this;
        }

        public VideoMimeInfo build() {
            String str = this.mimeType == null ? " mimeType" : "";
            if (this.profile == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new AutoValue_VideoMimeInfo(this.mimeType, this.profile.intValue(), this.compatibleVideoProfile);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
