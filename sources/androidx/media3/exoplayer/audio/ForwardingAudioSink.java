package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioSink;
import java.nio.ByteBuffer;

public class ForwardingAudioSink implements AudioSink {
    private final AudioSink sink;

    public ForwardingAudioSink(AudioSink audioSink) {
        this.sink = audioSink;
    }

    public void setListener(AudioSink.Listener listener) {
        this.sink.setListener(listener);
    }

    public void setPlayerId(PlayerId playerId) {
        this.sink.setPlayerId(playerId);
    }

    public void setClock(Clock clock) {
        this.sink.setClock(clock);
    }

    public boolean supportsFormat(Format format) {
        return this.sink.supportsFormat(format);
    }

    public int getFormatSupport(Format format) {
        return this.sink.getFormatSupport(format);
    }

    public AudioOffloadSupport getFormatOffloadSupport(Format format) {
        return this.sink.getFormatOffloadSupport(format);
    }

    public long getCurrentPositionUs(boolean z) {
        return this.sink.getCurrentPositionUs(z);
    }

    public void configure(Format format, int i, int[] iArr) throws AudioSink.ConfigurationException {
        this.sink.configure(format, i, iArr);
    }

    public void play() {
        this.sink.play();
    }

    public void handleDiscontinuity() {
        this.sink.handleDiscontinuity();
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j, int i) throws AudioSink.InitializationException, AudioSink.WriteException {
        return this.sink.handleBuffer(byteBuffer, j, i);
    }

    public void playToEndOfStream() throws AudioSink.WriteException {
        this.sink.playToEndOfStream();
    }

    public boolean isEnded() {
        return this.sink.isEnded();
    }

    public boolean hasPendingData() {
        return this.sink.hasPendingData();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.sink.setPlaybackParameters(playbackParameters);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.sink.getPlaybackParameters();
    }

    public void setSkipSilenceEnabled(boolean z) {
        this.sink.setSkipSilenceEnabled(z);
    }

    public boolean getSkipSilenceEnabled() {
        return this.sink.getSkipSilenceEnabled();
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.sink.setAudioAttributes(audioAttributes);
    }

    public AudioAttributes getAudioAttributes() {
        return this.sink.getAudioAttributes();
    }

    public void setAudioSessionId(int i) {
        this.sink.setAudioSessionId(i);
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        this.sink.setAuxEffectInfo(auxEffectInfo);
    }

    public void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        this.sink.setPreferredDevice(audioDeviceInfo);
    }

    public void setOutputStreamOffsetUs(long j) {
        this.sink.setOutputStreamOffsetUs(j);
    }

    public void enableTunnelingV21() {
        this.sink.enableTunnelingV21();
    }

    public void disableTunneling() {
        this.sink.disableTunneling();
    }

    public void setOffloadMode(int i) {
        this.sink.setOffloadMode(i);
    }

    public void setOffloadDelayPadding(int i, int i2) {
        this.sink.setOffloadDelayPadding(i, i2);
    }

    public void setVolume(float f) {
        this.sink.setVolume(f);
    }

    public void pause() {
        this.sink.pause();
    }

    public void flush() {
        this.sink.flush();
    }

    public void reset() {
        this.sink.reset();
    }

    public void release() {
        this.sink.release();
    }
}
