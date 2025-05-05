package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;

public interface AudioSink {
    public static final long CURRENT_POSITION_NOT_SET = Long.MIN_VALUE;
    public static final int OFFLOAD_MODE_DISABLED = 0;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_NOT_REQUIRED = 2;
    public static final int OFFLOAD_MODE_ENABLED_GAPLESS_REQUIRED = 1;
    public static final int SINK_FORMAT_SUPPORTED_DIRECTLY = 2;
    public static final int SINK_FORMAT_SUPPORTED_WITH_TRANSCODING = 1;
    public static final int SINK_FORMAT_UNSUPPORTED = 0;

    public interface Listener {
        void onAudioCapabilitiesChanged() {
        }

        void onAudioSinkError(Exception exc) {
        }

        void onAudioTrackInitialized(AudioTrackConfig audioTrackConfig) {
        }

        void onAudioTrackReleased(AudioTrackConfig audioTrackConfig) {
        }

        void onOffloadBufferEmptying() {
        }

        void onOffloadBufferFull() {
        }

        void onPositionAdvancing(long j) {
        }

        void onPositionDiscontinuity();

        void onSilenceSkipped() {
        }

        void onSkipSilenceEnabledChanged(boolean z);

        void onUnderrun(int i, long j, long j2);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OffloadMode {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SinkFormatSupport {
    }

    void configure(Format format, int i, int[] iArr) throws ConfigurationException;

    void disableTunneling();

    void enableTunnelingV21();

    void flush();

    AudioAttributes getAudioAttributes();

    long getCurrentPositionUs(boolean z);

    int getFormatSupport(Format format);

    PlaybackParameters getPlaybackParameters();

    boolean getSkipSilenceEnabled();

    boolean handleBuffer(ByteBuffer byteBuffer, long j, int i) throws InitializationException, WriteException;

    void handleDiscontinuity();

    boolean hasPendingData();

    boolean isEnded();

    void pause();

    void play();

    void playToEndOfStream() throws WriteException;

    void release() {
    }

    void reset();

    void setAudioAttributes(AudioAttributes audioAttributes);

    void setAudioSessionId(int i);

    void setAuxEffectInfo(AuxEffectInfo auxEffectInfo);

    void setClock(Clock clock) {
    }

    void setListener(Listener listener);

    void setOffloadDelayPadding(int i, int i2) {
    }

    void setOffloadMode(int i) {
    }

    void setOutputStreamOffsetUs(long j) {
    }

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    void setPlayerId(PlayerId playerId) {
    }

    void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
    }

    void setSkipSilenceEnabled(boolean z);

    void setVolume(float f);

    boolean supportsFormat(Format format);

    public static final class AudioTrackConfig {
        public final int bufferSize;
        public final int channelConfig;
        public final int encoding;
        public final boolean offload;
        public final int sampleRate;
        public final boolean tunneling;

        public AudioTrackConfig(int i, int i2, int i3, boolean z, boolean z2, int i4) {
            this.encoding = i;
            this.sampleRate = i2;
            this.channelConfig = i3;
            this.tunneling = z;
            this.offload = z2;
            this.bufferSize = i4;
        }
    }

    public static final class ConfigurationException extends Exception {
        public final Format format;

        public ConfigurationException(Throwable th, Format format2) {
            super(th);
            this.format = format2;
        }

        public ConfigurationException(String str, Format format2) {
            super(str);
            this.format = format2;
        }
    }

    public static final class InitializationException extends Exception {
        public final int audioTrackState;
        public final Format format;
        public final boolean isRecoverable;

        public InitializationException(String str, int i, Format format2, boolean z, Throwable th) {
            super(str, th);
            this.audioTrackState = i;
            this.isRecoverable = z;
            this.format = format2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public InitializationException(int i, int i2, int i3, int i4, Format format2, boolean z, Exception exc) {
            this("AudioTrack init failed " + i + " Config(" + i2 + ", " + i3 + ", " + i4 + ") " + format2 + (z ? " (recoverable)" : ""), i, format2, z, exc);
        }
    }

    public static final class WriteException extends Exception {
        public final int errorCode;
        public final Format format;
        public final boolean isRecoverable;

        public WriteException(int i, Format format2, boolean z) {
            super("AudioTrack write failed: " + i);
            this.isRecoverable = z;
            this.errorCode = i;
            this.format = format2;
        }
    }

    public static final class UnexpectedDiscontinuityException extends Exception {
        public final long actualPresentationTimeUs;
        public final long expectedPresentationTimeUs;

        public UnexpectedDiscontinuityException(long j, long j2) {
            super("Unexpected audio track timestamp discontinuity: expected " + j2 + ", got " + j);
            this.actualPresentationTimeUs = j;
            this.expectedPresentationTimeUs = j2;
        }
    }

    AudioOffloadSupport getFormatOffloadSupport(Format format) {
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    }
}
