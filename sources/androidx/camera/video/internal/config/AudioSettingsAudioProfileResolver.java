package androidx.camera.video.internal.config;

import android.util.Range;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.video.AudioSpec;
import androidx.camera.video.internal.audio.AudioSettings;
import androidx.core.util.Supplier;

public final class AudioSettingsAudioProfileResolver implements Supplier<AudioSettings> {
    private static final String TAG = "AudioSrcAdPrflRslvr";
    private final EncoderProfilesProxy.AudioProfileProxy mAudioProfile;
    private final AudioSpec mAudioSpec;

    public AudioSettingsAudioProfileResolver(AudioSpec audioSpec, EncoderProfilesProxy.AudioProfileProxy audioProfileProxy) {
        this.mAudioSpec = audioSpec;
        this.mAudioProfile = audioProfileProxy;
    }

    public AudioSettings get() {
        int resolveAudioSource = AudioConfigUtil.resolveAudioSource(this.mAudioSpec);
        int resolveAudioSourceFormat = AudioConfigUtil.resolveAudioSourceFormat(this.mAudioSpec);
        int channelCount = this.mAudioSpec.getChannelCount();
        Range<Integer> sampleRate = this.mAudioSpec.getSampleRate();
        int channels = this.mAudioProfile.getChannels();
        if (channelCount == -1) {
            Logger.d(TAG, "Resolved AUDIO channel count from AudioProfile: " + channels);
            channelCount = channels;
        } else {
            Logger.d(TAG, "Media spec AUDIO channel count overrides AudioProfile [AudioProfile channel count: " + channels + ", Resolved Channel Count: " + channelCount + "]");
        }
        int sampleRate2 = this.mAudioProfile.getSampleRate();
        int selectSampleRateOrNearestSupported = AudioConfigUtil.selectSampleRateOrNearestSupported(sampleRate, channelCount, resolveAudioSourceFormat, sampleRate2);
        Logger.d(TAG, "Using resolved AUDIO sample rate or nearest supported from AudioProfile: " + selectSampleRateOrNearestSupported + "Hz. [AudioProfile sample rate: " + sampleRate2 + "Hz]");
        return AudioSettings.builder().setAudioSource(resolveAudioSource).setAudioFormat(resolveAudioSourceFormat).setChannelCount(channelCount).setSampleRate(selectSampleRateOrNearestSupported).build();
    }
}
