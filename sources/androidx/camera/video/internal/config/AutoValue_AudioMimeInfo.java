package androidx.camera.video.internal.config;

import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.internal.config.AudioMimeInfo;

final class AutoValue_AudioMimeInfo extends AudioMimeInfo {
    private final EncoderProfilesProxy.AudioProfileProxy compatibleAudioProfile;
    private final String mimeType;
    private final int profile;

    private AutoValue_AudioMimeInfo(String str, int i, EncoderProfilesProxy.AudioProfileProxy audioProfileProxy) {
        this.mimeType = str;
        this.profile = i;
        this.compatibleAudioProfile = audioProfileProxy;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getProfile() {
        return this.profile;
    }

    public EncoderProfilesProxy.AudioProfileProxy getCompatibleAudioProfile() {
        return this.compatibleAudioProfile;
    }

    public String toString() {
        return "AudioMimeInfo{mimeType=" + this.mimeType + ", profile=" + this.profile + ", compatibleAudioProfile=" + this.compatibleAudioProfile + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AudioMimeInfo)) {
            return false;
        }
        AudioMimeInfo audioMimeInfo = (AudioMimeInfo) obj;
        if (this.mimeType.equals(audioMimeInfo.getMimeType()) && this.profile == audioMimeInfo.getProfile()) {
            EncoderProfilesProxy.AudioProfileProxy audioProfileProxy = this.compatibleAudioProfile;
            if (audioProfileProxy == null) {
                if (audioMimeInfo.getCompatibleAudioProfile() == null) {
                    return true;
                }
            } else if (audioProfileProxy.equals(audioMimeInfo.getCompatibleAudioProfile())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.mimeType.hashCode() ^ 1000003) * 1000003) ^ this.profile) * 1000003;
        EncoderProfilesProxy.AudioProfileProxy audioProfileProxy = this.compatibleAudioProfile;
        return hashCode ^ (audioProfileProxy == null ? 0 : audioProfileProxy.hashCode());
    }

    static final class Builder extends AudioMimeInfo.Builder {
        private EncoderProfilesProxy.AudioProfileProxy compatibleAudioProfile;
        private String mimeType;
        private Integer profile;

        Builder() {
        }

        /* access modifiers changed from: protected */
        public AudioMimeInfo.Builder setMimeType(String str) {
            if (str != null) {
                this.mimeType = str;
                return this;
            }
            throw new NullPointerException("Null mimeType");
        }

        public AudioMimeInfo.Builder setProfile(int i) {
            this.profile = Integer.valueOf(i);
            return this;
        }

        public AudioMimeInfo.Builder setCompatibleAudioProfile(EncoderProfilesProxy.AudioProfileProxy audioProfileProxy) {
            this.compatibleAudioProfile = audioProfileProxy;
            return this;
        }

        public AudioMimeInfo build() {
            String str = this.mimeType == null ? " mimeType" : "";
            if (this.profile == null) {
                str = str + " profile";
            }
            if (str.isEmpty()) {
                return new AutoValue_AudioMimeInfo(this.mimeType, this.profile.intValue(), this.compatibleAudioProfile);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
