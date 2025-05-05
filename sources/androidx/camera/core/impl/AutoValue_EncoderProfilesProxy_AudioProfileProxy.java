package androidx.camera.core.impl;

import androidx.camera.core.impl.EncoderProfilesProxy;

final class AutoValue_EncoderProfilesProxy_AudioProfileProxy extends EncoderProfilesProxy.AudioProfileProxy {
    private final int bitrate;
    private final int channels;
    private final int codec;
    private final String mediaType;
    private final int profile;
    private final int sampleRate;

    AutoValue_EncoderProfilesProxy_AudioProfileProxy(int i, String str, int i2, int i3, int i4, int i5) {
        this.codec = i;
        if (str != null) {
            this.mediaType = str;
            this.bitrate = i2;
            this.sampleRate = i3;
            this.channels = i4;
            this.profile = i5;
            return;
        }
        throw new NullPointerException("Null mediaType");
    }

    public int getCodec() {
        return this.codec;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getChannels() {
        return this.channels;
    }

    public int getProfile() {
        return this.profile;
    }

    public String toString() {
        return "AudioProfileProxy{codec=" + this.codec + ", mediaType=" + this.mediaType + ", bitrate=" + this.bitrate + ", sampleRate=" + this.sampleRate + ", channels=" + this.channels + ", profile=" + this.profile + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EncoderProfilesProxy.AudioProfileProxy)) {
            return false;
        }
        EncoderProfilesProxy.AudioProfileProxy audioProfileProxy = (EncoderProfilesProxy.AudioProfileProxy) obj;
        if (this.codec == audioProfileProxy.getCodec() && this.mediaType.equals(audioProfileProxy.getMediaType()) && this.bitrate == audioProfileProxy.getBitrate() && this.sampleRate == audioProfileProxy.getSampleRate() && this.channels == audioProfileProxy.getChannels() && this.profile == audioProfileProxy.getProfile()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((this.codec ^ 1000003) * 1000003) ^ this.mediaType.hashCode()) * 1000003) ^ this.bitrate) * 1000003) ^ this.sampleRate) * 1000003) ^ this.channels) * 1000003) ^ this.profile;
    }
}
