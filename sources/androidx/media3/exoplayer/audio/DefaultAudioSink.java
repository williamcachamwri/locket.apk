package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.SonicAudioProcessor;
import androidx.media3.common.audio.ToInt16PcmAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;
import androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultAudioSink implements AudioSink {
    private static final int AUDIO_TRACK_SMALLER_BUFFER_RETRY_SIZE = 1000000;
    private static final int AUDIO_TRACK_VOLUME_RAMP_TIME_MS = 20;
    public static final float DEFAULT_PLAYBACK_SPEED = 1.0f;
    private static final boolean DEFAULT_SKIP_SILENCE = false;
    private static final int ERROR_NATIVE_DEAD_OBJECT = -32;
    public static final float MAX_PITCH = 8.0f;
    public static final float MAX_PLAYBACK_SPEED = 8.0f;
    private static final int MINIMUM_REPORT_SKIPPED_SILENCE_DURATION_US = 300000;
    public static final float MIN_PITCH = 0.1f;
    public static final float MIN_PLAYBACK_SPEED = 0.1f;
    public static final int OUTPUT_MODE_OFFLOAD = 1;
    public static final int OUTPUT_MODE_PASSTHROUGH = 2;
    public static final int OUTPUT_MODE_PCM = 0;
    private static final int REPORT_SKIPPED_SILENCE_DELAY_MS = 100;
    private static final String TAG = "DefaultAudioSink";
    public static boolean failOnSpuriousAudioTimestamp = false;
    private static int pendingReleaseCount;
    private static ScheduledExecutorService releaseExecutor;
    private static final Object releaseExecutorLock = new Object();
    private long accumulatedSkippedSilenceDurationUs;
    private MediaPositionParameters afterDrainParameters;
    private AudioAttributes audioAttributes;
    private AudioCapabilities audioCapabilities;
    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private final ExoPlayer.AudioOffloadListener audioOffloadListener;
    private final AudioOffloadSupportProvider audioOffloadSupportProvider;
    private AudioProcessingPipeline audioProcessingPipeline;
    private final androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    /* access modifiers changed from: private */
    public AudioTrack audioTrack;
    private final AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private final AudioTrackProvider audioTrackProvider;
    private AuxEffectInfo auxEffectInfo;
    private ByteBuffer avSyncHeader;
    private int bytesUntilNextAvSync;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private Configuration configuration;
    private final Context context;
    private final boolean enableFloatOutput;
    private boolean externalAudioSessionIdProvided;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    /* access modifiers changed from: private */
    public boolean handledOffloadOnPresentationEnded;
    private final PendingExceptionHolder<AudioSink.InitializationException> initializationExceptionPendingExceptionHolder;
    private ByteBuffer inputBuffer;
    private int inputBufferAccessUnitCount;
    private boolean isWaitingForOffloadEndOfStreamHandled;
    /* access modifiers changed from: private */
    public long lastFeedElapsedRealtimeMs;
    private long lastTunnelingAvSyncPresentationTimeUs;
    /* access modifiers changed from: private */
    public AudioSink.Listener listener;
    private MediaPositionParameters mediaPositionParameters;
    private final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    private boolean offloadDisabledUntilNextConfiguration;
    private int offloadMode;
    private StreamEventCallbackV29 offloadStreamEventCallbackV29;
    private OnRoutingChangedListenerApi24 onRoutingChangedListener;
    private ByteBuffer outputBuffer;
    private Configuration pendingConfiguration;
    private Looper playbackLooper;
    private PlaybackParameters playbackParameters;
    private PlayerId playerId;
    /* access modifiers changed from: private */
    public boolean playing;
    private final boolean preferAudioTrackPlaybackParams;
    private AudioDeviceInfoApi23 preferredDevice;
    private Handler reportSkippedSilenceHandler;
    private boolean skipSilenceEnabled;
    private long skippedOutputFrameCountAtLastPosition;
    private long startMediaTimeUs;
    private boolean startMediaTimeUsNeedsInit;
    private boolean startMediaTimeUsNeedsSync;
    private boolean stoppedAudioTrack;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final ImmutableList<AudioProcessor> toFloatPcmAvailableAudioProcessors;
    private final ImmutableList<AudioProcessor> toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    public interface AudioOffloadSupportProvider {
        AudioOffloadSupport getAudioOffloadSupport(Format format, AudioAttributes audioAttributes);
    }

    @Deprecated
    public interface AudioProcessorChain extends androidx.media3.common.audio.AudioProcessorChain {
    }

    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = new DefaultAudioTrackBufferSizeProvider.Builder().build();

        int getBufferSizeInBytes(int i, int i2, int i3, int i4, int i5, int i6, double d);
    }

    public interface AudioTrackProvider {
        public static final AudioTrackProvider DEFAULT = new DefaultAudioTrackProvider();

        AudioTrack getAudioTrack(AudioSink.AudioTrackConfig audioTrackConfig, AudioAttributes audioAttributes, int i);
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OutputMode {
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... audioProcessorArr) {
            this(audioProcessorArr, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        public DefaultAudioProcessorChain(AudioProcessor[] audioProcessorArr, SilenceSkippingAudioProcessor silenceSkippingAudioProcessor2, SonicAudioProcessor sonicAudioProcessor2) {
            AudioProcessor[] audioProcessorArr2 = new AudioProcessor[(audioProcessorArr.length + 2)];
            this.audioProcessors = audioProcessorArr2;
            System.arraycopy(audioProcessorArr, 0, audioProcessorArr2, 0, audioProcessorArr.length);
            this.silenceSkippingAudioProcessor = silenceSkippingAudioProcessor2;
            this.sonicAudioProcessor = sonicAudioProcessor2;
            audioProcessorArr2[audioProcessorArr.length] = silenceSkippingAudioProcessor2;
            audioProcessorArr2[audioProcessorArr.length + 1] = sonicAudioProcessor2;
        }

        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        public PlaybackParameters applyPlaybackParameters(PlaybackParameters playbackParameters) {
            this.sonicAudioProcessor.setSpeed(playbackParameters.speed);
            this.sonicAudioProcessor.setPitch(playbackParameters.pitch);
            return playbackParameters;
        }

        public boolean applySkipSilenceEnabled(boolean z) {
            this.silenceSkippingAudioProcessor.setEnabled(z);
            return z;
        }

        public long getMediaDuration(long j) {
            return this.sonicAudioProcessor.isActive() ? this.sonicAudioProcessor.getMediaDuration(j) : j;
        }

        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public AudioCapabilities audioCapabilities;
        /* access modifiers changed from: private */
        public ExoPlayer.AudioOffloadListener audioOffloadListener;
        /* access modifiers changed from: private */
        public AudioOffloadSupportProvider audioOffloadSupportProvider;
        /* access modifiers changed from: private */
        public androidx.media3.common.audio.AudioProcessorChain audioProcessorChain;
        /* access modifiers changed from: private */
        public AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
        /* access modifiers changed from: private */
        public AudioTrackProvider audioTrackProvider;
        private boolean buildCalled;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean enableAudioTrackPlaybackParams;
        /* access modifiers changed from: private */
        public boolean enableFloatOutput;

        @Deprecated
        public Builder() {
            this.context = null;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
            this.audioTrackProvider = AudioTrackProvider.DEFAULT;
        }

        public Builder(Context context2) {
            this.context = context2;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
            this.audioTrackProvider = AudioTrackProvider.DEFAULT;
        }

        @Deprecated
        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities2) {
            Assertions.checkNotNull(audioCapabilities2);
            this.audioCapabilities = audioCapabilities2;
            return this;
        }

        public Builder setAudioProcessors(AudioProcessor[] audioProcessorArr) {
            Assertions.checkNotNull(audioProcessorArr);
            return setAudioProcessorChain(new DefaultAudioProcessorChain(audioProcessorArr));
        }

        public Builder setAudioProcessorChain(androidx.media3.common.audio.AudioProcessorChain audioProcessorChain2) {
            Assertions.checkNotNull(audioProcessorChain2);
            this.audioProcessorChain = audioProcessorChain2;
            return this;
        }

        public Builder setEnableFloatOutput(boolean z) {
            this.enableFloatOutput = z;
            return this;
        }

        public Builder setEnableAudioTrackPlaybackParams(boolean z) {
            this.enableAudioTrackPlaybackParams = z;
            return this;
        }

        public Builder setAudioTrackBufferSizeProvider(AudioTrackBufferSizeProvider audioTrackBufferSizeProvider2) {
            this.audioTrackBufferSizeProvider = audioTrackBufferSizeProvider2;
            return this;
        }

        public Builder setAudioOffloadSupportProvider(AudioOffloadSupportProvider audioOffloadSupportProvider2) {
            this.audioOffloadSupportProvider = audioOffloadSupportProvider2;
            return this;
        }

        public Builder setExperimentalAudioOffloadListener(ExoPlayer.AudioOffloadListener audioOffloadListener2) {
            this.audioOffloadListener = audioOffloadListener2;
            return this;
        }

        public Builder setAudioTrackProvider(AudioTrackProvider audioTrackProvider2) {
            this.audioTrackProvider = audioTrackProvider2;
            return this;
        }

        public DefaultAudioSink build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.audioProcessorChain == null) {
                this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
            }
            if (this.audioOffloadSupportProvider == null) {
                this.audioOffloadSupportProvider = new DefaultAudioOffloadSupportProvider(this.context);
            }
            return new DefaultAudioSink(this);
        }
    }

    @RequiresNonNull({"#1.audioProcessorChain"})
    private DefaultAudioSink(Builder builder) {
        AudioCapabilities audioCapabilities2;
        Context access$100 = builder.context;
        this.context = access$100;
        AudioAttributes audioAttributes2 = AudioAttributes.DEFAULT;
        this.audioAttributes = audioAttributes2;
        if (access$100 != null) {
            audioCapabilities2 = AudioCapabilities.getCapabilities(access$100, audioAttributes2, (AudioDeviceInfo) null);
        } else {
            audioCapabilities2 = builder.audioCapabilities;
        }
        this.audioCapabilities = audioCapabilities2;
        this.audioProcessorChain = builder.audioProcessorChain;
        this.enableFloatOutput = builder.enableFloatOutput;
        this.preferAudioTrackPlaybackParams = Util.SDK_INT >= 23 && builder.enableAudioTrackPlaybackParams;
        this.offloadMode = 0;
        this.audioTrackBufferSizeProvider = builder.audioTrackBufferSizeProvider;
        this.audioOffloadSupportProvider = (AudioOffloadSupportProvider) Assertions.checkNotNull(builder.audioOffloadSupportProvider);
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener());
        ChannelMappingAudioProcessor channelMappingAudioProcessor2 = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = channelMappingAudioProcessor2;
        TrimmingAudioProcessor trimmingAudioProcessor2 = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = trimmingAudioProcessor2;
        this.toIntPcmAvailableAudioProcessors = ImmutableList.of(new ToInt16PcmAudioProcessor(), channelMappingAudioProcessor2, trimmingAudioProcessor2);
        this.toFloatPcmAvailableAudioProcessors = ImmutableList.of(new ToFloatPcmAudioProcessor());
        this.volume = 1.0f;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        this.mediaPositionParameters = new MediaPositionParameters(PlaybackParameters.DEFAULT, 0, 0);
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.skipSilenceEnabled = false;
        this.mediaPositionParametersCheckpoints = new ArrayDeque<>();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder<>();
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder<>();
        this.audioOffloadListener = builder.audioOffloadListener;
        this.audioTrackProvider = builder.audioTrackProvider;
    }

    public void setListener(AudioSink.Listener listener2) {
        this.listener = listener2;
    }

    public void setPlayerId(PlayerId playerId2) {
        this.playerId = playerId2;
    }

    public void setClock(Clock clock) {
        this.audioTrackPositionTracker.setClock(clock);
    }

    public boolean supportsFormat(Format format) {
        return getFormatSupport(format) != 0;
    }

    public int getFormatSupport(Format format) {
        maybeStartAudioCapabilitiesReceiver();
        if (!MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
            return this.audioCapabilities.isPassthroughPlaybackSupported(format, this.audioAttributes) ? 2 : 0;
        }
        if (!Util.isEncodingLinearPcm(format.pcmEncoding)) {
            Log.w(TAG, "Invalid PCM encoding: " + format.pcmEncoding);
            return 0;
        } else if (format.pcmEncoding == 2 || (this.enableFloatOutput && format.pcmEncoding == 4)) {
            return 2;
        } else {
            return 1;
        }
    }

    public AudioOffloadSupport getFormatOffloadSupport(Format format) {
        if (this.offloadDisabledUntilNextConfiguration) {
            return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        }
        return this.audioOffloadSupportProvider.getAudioOffloadSupport(format, this.audioAttributes);
    }

    public long getCurrentPositionUs(boolean z) {
        if (!isAudioTrackInitialized() || this.startMediaTimeUsNeedsInit) {
            return Long.MIN_VALUE;
        }
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(z), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    public void configure(Format format, int i, int[] iArr) throws AudioSink.ConfigurationException {
        boolean z;
        boolean z2;
        AudioProcessingPipeline audioProcessingPipeline2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        AudioOffloadSupport audioOffloadSupport;
        Format format2 = format;
        maybeStartAudioCapabilitiesReceiver();
        if (MimeTypes.AUDIO_RAW.equals(format2.sampleMimeType)) {
            Assertions.checkArgument(Util.isEncodingLinearPcm(format2.pcmEncoding));
            i7 = Util.getPcmFrameSize(format2.pcmEncoding, format2.channelCount);
            ImmutableList.Builder builder = new ImmutableList.Builder();
            if (shouldUseFloatOutput(format2.pcmEncoding)) {
                builder.addAll((Iterable) this.toFloatPcmAvailableAudioProcessors);
            } else {
                builder.addAll((Iterable) this.toIntPcmAvailableAudioProcessors);
                builder.add((Object[]) this.audioProcessorChain.getAudioProcessors());
            }
            AudioProcessingPipeline audioProcessingPipeline3 = new AudioProcessingPipeline(builder.build());
            if (audioProcessingPipeline3.equals(this.audioProcessingPipeline)) {
                audioProcessingPipeline3 = this.audioProcessingPipeline;
            }
            this.trimmingAudioProcessor.setTrimFrameCount(format2.encoderDelay, format2.encoderPadding);
            this.channelMappingAudioProcessor.setChannelMap(iArr);
            try {
                AudioProcessor.AudioFormat configure = audioProcessingPipeline3.configure(new AudioProcessor.AudioFormat(format2));
                int i13 = configure.encoding;
                int i14 = configure.sampleRate;
                int audioTrackChannelConfig = Util.getAudioTrackChannelConfig(configure.channelCount);
                i2 = 0;
                z = false;
                i4 = Util.getPcmFrameSize(i13, configure.channelCount);
                audioProcessingPipeline2 = audioProcessingPipeline3;
                i3 = i14;
                i5 = audioTrackChannelConfig;
                z2 = this.preferAudioTrackPlaybackParams;
                i6 = i13;
            } catch (AudioProcessor.UnhandledAudioFormatException e) {
                throw new AudioSink.ConfigurationException((Throwable) e, format2);
            }
        } else {
            AudioProcessingPipeline audioProcessingPipeline4 = new AudioProcessingPipeline(ImmutableList.of());
            int i15 = format2.sampleRate;
            if (this.offloadMode != 0) {
                audioOffloadSupport = getFormatOffloadSupport(format);
            } else {
                audioOffloadSupport = AudioOffloadSupport.DEFAULT_UNSUPPORTED;
            }
            if (this.offloadMode == 0 || !audioOffloadSupport.isFormatSupported) {
                Pair<Integer, Integer> encodingAndChannelConfigForPassthrough = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format2, this.audioAttributes);
                if (encodingAndChannelConfigForPassthrough != null) {
                    int intValue = ((Integer) encodingAndChannelConfigForPassthrough.first).intValue();
                    audioProcessingPipeline2 = audioProcessingPipeline4;
                    i7 = -1;
                    i4 = -1;
                    z = false;
                    i3 = i15;
                    i5 = ((Integer) encodingAndChannelConfigForPassthrough.second).intValue();
                    i6 = intValue;
                    z2 = this.preferAudioTrackPlaybackParams;
                    i2 = 2;
                } else {
                    throw new AudioSink.ConfigurationException("Unable to configure passthrough for: " + format2, format2);
                }
            } else {
                int encoding = MimeTypes.getEncoding((String) Assertions.checkNotNull(format2.sampleMimeType), format2.codecs);
                int audioTrackChannelConfig2 = Util.getAudioTrackChannelConfig(format2.channelCount);
                audioProcessingPipeline2 = audioProcessingPipeline4;
                i2 = 1;
                z2 = true;
                i7 = -1;
                i4 = -1;
                i3 = i15;
                z = audioOffloadSupport.isGaplessSupported;
                i6 = encoding;
                i5 = audioTrackChannelConfig2;
            }
        }
        if (i6 == 0) {
            throw new AudioSink.ConfigurationException("Invalid output encoding (mode=" + i2 + ") for: " + format2, format2);
        } else if (i5 != 0) {
            int i16 = format2.bitrate;
            if (MimeTypes.AUDIO_DTS_EXPRESS.equals(format2.sampleMimeType) && i16 == -1) {
                i16 = 768000;
            }
            int i17 = i16;
            if (i != 0) {
                i12 = i;
                i10 = i6;
                i9 = i5;
                i8 = i4;
                i11 = i3;
            } else {
                AudioTrackBufferSizeProvider audioTrackBufferSizeProvider2 = this.audioTrackBufferSizeProvider;
                int audioTrackMinBufferSize = getAudioTrackMinBufferSize(i3, i5, i6);
                i10 = i6;
                i9 = i5;
                int i18 = i17;
                i8 = i4;
                i11 = i3;
                i12 = audioTrackBufferSizeProvider2.getBufferSizeInBytes(audioTrackMinBufferSize, i6, i2, i4 != -1 ? i4 : 1, i3, i18, z2 ? 8.0d : 1.0d);
            }
            this.offloadDisabledUntilNextConfiguration = false;
            Configuration configuration2 = r2;
            Configuration configuration3 = new Configuration(format, i7, i2, i8, i11, i9, i10, i12, audioProcessingPipeline2, z2, z, this.tunneling);
            if (isAudioTrackInitialized()) {
                this.pendingConfiguration = configuration2;
            } else {
                this.configuration = configuration2;
            }
        } else {
            throw new AudioSink.ConfigurationException("Invalid output channel config (mode=" + i2 + ") for: " + format2, format2);
        }
    }

    private void setupAudioProcessors() {
        AudioProcessingPipeline audioProcessingPipeline2 = this.configuration.audioProcessingPipeline;
        this.audioProcessingPipeline = audioProcessingPipeline2;
        audioProcessingPipeline2.flush();
    }

    private boolean initializeAudioTrack() throws AudioSink.InitializationException {
        PlayerId playerId2;
        if (this.initializationExceptionPendingExceptionHolder.shouldWaitBeforeRetry()) {
            return false;
        }
        AudioTrack buildAudioTrackWithRetry = buildAudioTrackWithRetry();
        this.audioTrack = buildAudioTrackWithRetry;
        if (isOffloadedPlayback(buildAudioTrackWithRetry)) {
            registerStreamEventCallbackV29(this.audioTrack);
            if (this.configuration.enableOffloadGapless) {
                this.audioTrack.setOffloadDelayPadding(this.configuration.inputFormat.encoderDelay, this.configuration.inputFormat.encoderPadding);
            }
        }
        if (Util.SDK_INT >= 31 && (playerId2 = this.playerId) != null) {
            Api31.setLogSessionIdOnAudioTrack(this.audioTrack, playerId2);
        }
        this.audioSessionId = this.audioTrack.getAudioSessionId();
        this.audioTrackPositionTracker.setAudioTrack(this.audioTrack, this.configuration.outputMode == 2, this.configuration.outputEncoding, this.configuration.outputPcmFrameSize, this.configuration.bufferSize);
        setVolumeInternal();
        if (this.auxEffectInfo.effectId != 0) {
            this.audioTrack.attachAuxEffect(this.auxEffectInfo.effectId);
            this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
        }
        if (this.preferredDevice != null && Util.SDK_INT >= 23) {
            Api23.setPreferredDeviceOnAudioTrack(this.audioTrack, this.preferredDevice);
            AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = this.audioCapabilitiesReceiver;
            if (audioCapabilitiesReceiver2 != null) {
                audioCapabilitiesReceiver2.setRoutedDevice(this.preferredDevice.audioDeviceInfo);
            }
        }
        if (Util.SDK_INT >= 24 && this.audioCapabilitiesReceiver != null) {
            this.onRoutingChangedListener = new OnRoutingChangedListenerApi24(this.audioTrack, this.audioCapabilitiesReceiver);
        }
        this.startMediaTimeUsNeedsInit = true;
        AudioSink.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onAudioTrackInitialized(this.configuration.buildAudioTrackConfig());
        }
        return true;
    }

    public void play() {
        this.playing = true;
        if (isAudioTrackInitialized()) {
            this.audioTrackPositionTracker.start();
            this.audioTrack.play();
        }
    }

    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    public boolean handleBuffer(ByteBuffer byteBuffer, long j, int i) throws AudioSink.InitializationException, AudioSink.WriteException {
        ByteBuffer byteBuffer2 = byteBuffer;
        long j2 = j;
        int i2 = i;
        ByteBuffer byteBuffer3 = this.inputBuffer;
        Assertions.checkArgument(byteBuffer3 == null || byteBuffer2 == byteBuffer3);
        if (this.pendingConfiguration != null) {
            if (!drainToEndOfStream()) {
                return false;
            }
            if (!this.pendingConfiguration.canReuseAudioTrack(this.configuration)) {
                playPendingData();
                if (hasPendingData()) {
                    return false;
                }
                flush();
            } else {
                this.configuration = this.pendingConfiguration;
                this.pendingConfiguration = null;
                AudioTrack audioTrack2 = this.audioTrack;
                if (audioTrack2 != null && isOffloadedPlayback(audioTrack2) && this.configuration.enableOffloadGapless) {
                    if (this.audioTrack.getPlayState() == 3) {
                        this.audioTrack.setOffloadEndOfStream();
                        this.audioTrackPositionTracker.expectRawPlaybackHeadReset();
                    }
                    this.audioTrack.setOffloadDelayPadding(this.configuration.inputFormat.encoderDelay, this.configuration.inputFormat.encoderPadding);
                    this.isWaitingForOffloadEndOfStreamHandled = true;
                }
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j2);
        }
        if (!isAudioTrackInitialized()) {
            try {
                if (!initializeAudioTrack()) {
                    return false;
                }
            } catch (AudioSink.InitializationException e) {
                AudioSink.InitializationException initializationException = e;
                if (!initializationException.isRecoverable) {
                    this.initializationExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(initializationException);
                    return false;
                }
                throw initializationException;
            }
        }
        this.initializationExceptionPendingExceptionHolder.clear();
        if (this.startMediaTimeUsNeedsInit) {
            this.startMediaTimeUs = Math.max(0, j2);
            this.startMediaTimeUsNeedsSync = false;
            this.startMediaTimeUsNeedsInit = false;
            if (useAudioTrackPlaybackParams()) {
                setAudioTrackPlaybackParametersV23();
            }
            applyAudioProcessorPlaybackParametersAndSkipSilence(j2);
            if (this.playing) {
                play();
            }
        }
        if (!this.audioTrackPositionTracker.mayHandleBuffer(getWrittenFrames())) {
            return false;
        }
        if (this.inputBuffer == null) {
            Assertions.checkArgument(byteBuffer.order() == ByteOrder.LITTLE_ENDIAN);
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.configuration.outputMode != 0 && this.framesPerEncodedSample == 0) {
                int framesPerEncodedSample2 = getFramesPerEncodedSample(this.configuration.outputEncoding, byteBuffer2);
                this.framesPerEncodedSample = framesPerEncodedSample2;
                if (framesPerEncodedSample2 == 0) {
                    return true;
                }
            }
            if (this.afterDrainParameters != null) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                applyAudioProcessorPlaybackParametersAndSkipSilence(j2);
                this.afterDrainParameters = null;
            }
            long inputFramesToDurationUs = this.startMediaTimeUs + this.configuration.inputFramesToDurationUs(getSubmittedFrames() - this.trimmingAudioProcessor.getTrimmedFrameCount());
            if (!this.startMediaTimeUsNeedsSync && Math.abs(inputFramesToDurationUs - j2) > 200000) {
                AudioSink.Listener listener2 = this.listener;
                if (listener2 != null) {
                    listener2.onAudioSinkError(new AudioSink.UnexpectedDiscontinuityException(j2, inputFramesToDurationUs));
                }
                this.startMediaTimeUsNeedsSync = true;
            }
            if (this.startMediaTimeUsNeedsSync) {
                if (!drainToEndOfStream()) {
                    return false;
                }
                long j3 = j2 - inputFramesToDurationUs;
                this.startMediaTimeUs += j3;
                this.startMediaTimeUsNeedsSync = false;
                applyAudioProcessorPlaybackParametersAndSkipSilence(j2);
                AudioSink.Listener listener3 = this.listener;
                if (!(listener3 == null || j3 == 0)) {
                    listener3.onPositionDiscontinuity();
                }
            }
            if (this.configuration.outputMode == 0) {
                this.submittedPcmBytes += (long) byteBuffer.remaining();
            } else {
                this.submittedEncodedFrames += ((long) this.framesPerEncodedSample) * ((long) i2);
            }
            this.inputBuffer = byteBuffer2;
            this.inputBufferAccessUnitCount = i2;
        }
        processBuffers(j2);
        if (!this.inputBuffer.hasRemaining()) {
            this.inputBuffer = null;
            this.inputBufferAccessUnitCount = 0;
            return true;
        } else if (!this.audioTrackPositionTracker.isStalled(getWrittenFrames())) {
            return false;
        } else {
            Log.w(TAG, "Resetting stalled audio track");
            flush();
            return true;
        }
    }

    private AudioTrack buildAudioTrackWithRetry() throws AudioSink.InitializationException {
        try {
            return buildAudioTrack((Configuration) Assertions.checkNotNull(this.configuration));
        } catch (AudioSink.InitializationException e) {
            if (this.configuration.bufferSize > 1000000) {
                Configuration copyWithBufferSize = this.configuration.copyWithBufferSize(1000000);
                try {
                    AudioTrack buildAudioTrack = buildAudioTrack(copyWithBufferSize);
                    this.configuration = copyWithBufferSize;
                    return buildAudioTrack;
                } catch (AudioSink.InitializationException e2) {
                    e.addSuppressed(e2);
                    maybeDisableOffload();
                    throw e;
                }
            }
            maybeDisableOffload();
            throw e;
        }
    }

    private AudioTrack buildAudioTrack(Configuration configuration2) throws AudioSink.InitializationException {
        try {
            AudioTrack buildAudioTrack = buildAudioTrack(configuration2.buildAudioTrackConfig(), this.audioAttributes, this.audioSessionId, configuration2.inputFormat);
            ExoPlayer.AudioOffloadListener audioOffloadListener2 = this.audioOffloadListener;
            if (audioOffloadListener2 != null) {
                audioOffloadListener2.onOffloadedPlayback(isOffloadedPlayback(buildAudioTrack));
            }
            return buildAudioTrack;
        } catch (AudioSink.InitializationException e) {
            AudioSink.Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onAudioSinkError(e);
            }
            throw e;
        }
    }

    private AudioTrack buildAudioTrack(AudioSink.AudioTrackConfig audioTrackConfig, AudioAttributes audioAttributes2, int i, Format format) throws AudioSink.InitializationException {
        try {
            AudioTrack audioTrack2 = this.audioTrackProvider.getAudioTrack(audioTrackConfig, audioAttributes2, i);
            int state = audioTrack2.getState();
            if (state == 1) {
                return audioTrack2;
            }
            try {
                audioTrack2.release();
            } catch (Exception unused) {
            }
            throw new AudioSink.InitializationException(state, audioTrackConfig.sampleRate, audioTrackConfig.channelConfig, audioTrackConfig.encoding, format, audioTrackConfig.offload, (Exception) null);
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            throw new AudioSink.InitializationException(0, audioTrackConfig.sampleRate, audioTrackConfig.channelConfig, audioTrackConfig.encoding, format, audioTrackConfig.offload, e);
        }
    }

    private void registerStreamEventCallbackV29(AudioTrack audioTrack2) {
        if (this.offloadStreamEventCallbackV29 == null) {
            this.offloadStreamEventCallbackV29 = new StreamEventCallbackV29();
        }
        this.offloadStreamEventCallbackV29.register(audioTrack2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        r0 = r2.inputBuffer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003c, code lost:
        if (r0 == null) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r0.hasRemaining() != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        r2.audioProcessingPipeline.queueInput(r2.inputBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processBuffers(long r3) throws androidx.media3.exoplayer.audio.AudioSink.WriteException {
        /*
            r2 = this;
            r2.drainOutputBuffer(r3)
            java.nio.ByteBuffer r0 = r2.outputBuffer
            if (r0 == 0) goto L_0x0008
            return
        L_0x0008:
            androidx.media3.common.audio.AudioProcessingPipeline r0 = r2.audioProcessingPipeline
            boolean r0 = r0.isOperational()
            if (r0 != 0) goto L_0x001b
            java.nio.ByteBuffer r0 = r2.inputBuffer
            if (r0 == 0) goto L_0x001a
            r2.setOutputBuffer(r0)
            r2.drainOutputBuffer(r3)
        L_0x001a:
            return
        L_0x001b:
            androidx.media3.common.audio.AudioProcessingPipeline r0 = r2.audioProcessingPipeline
            boolean r0 = r0.isEnded()
            if (r0 != 0) goto L_0x004d
        L_0x0023:
            androidx.media3.common.audio.AudioProcessingPipeline r0 = r2.audioProcessingPipeline
            java.nio.ByteBuffer r0 = r0.getOutput()
            boolean r1 = r0.hasRemaining()
            if (r1 == 0) goto L_0x003a
            r2.setOutputBuffer(r0)
            r2.drainOutputBuffer(r3)
            java.nio.ByteBuffer r0 = r2.outputBuffer
            if (r0 == 0) goto L_0x0023
            return
        L_0x003a:
            java.nio.ByteBuffer r0 = r2.inputBuffer
            if (r0 == 0) goto L_0x004d
            boolean r0 = r0.hasRemaining()
            if (r0 != 0) goto L_0x0045
            goto L_0x004d
        L_0x0045:
            androidx.media3.common.audio.AudioProcessingPipeline r0 = r2.audioProcessingPipeline
            java.nio.ByteBuffer r1 = r2.inputBuffer
            r0.queueInput(r1)
            goto L_0x001b
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.processBuffers(long):void");
    }

    private boolean drainToEndOfStream() throws AudioSink.WriteException {
        ByteBuffer byteBuffer;
        if (!this.audioProcessingPipeline.isOperational()) {
            drainOutputBuffer(Long.MIN_VALUE);
            if (this.outputBuffer == null) {
                return true;
            }
            return false;
        }
        this.audioProcessingPipeline.queueEndOfStream();
        processBuffers(Long.MIN_VALUE);
        if (!this.audioProcessingPipeline.isEnded() || ((byteBuffer = this.outputBuffer) != null && byteBuffer.hasRemaining())) {
            return false;
        }
        return true;
    }

    private void setOutputBuffer(ByteBuffer byteBuffer) {
        Assertions.checkState(this.outputBuffer == null);
        if (byteBuffer.hasRemaining()) {
            this.outputBuffer = maybeRampUpVolume(byteBuffer);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drainOutputBuffer(long r10) throws androidx.media3.exoplayer.audio.AudioSink.WriteException {
        /*
            r9 = this;
            java.nio.ByteBuffer r0 = r9.outputBuffer
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r0 = r9.writeExceptionPendingExceptionHolder
            boolean r0 = r0.shouldWaitBeforeRetry()
            if (r0 == 0) goto L_0x000e
            return
        L_0x000e:
            java.nio.ByteBuffer r0 = r9.outputBuffer
            int r0 = r0.remaining()
            boolean r1 = r9.tunneling
            r7 = 1
            r8 = 0
            if (r1 == 0) goto L_0x0040
            r1 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0025
            r1 = r7
            goto L_0x0026
        L_0x0025:
            r1 = r8
        L_0x0026:
            androidx.media3.common.util.Assertions.checkState(r1)
            r1 = -9223372036854775808
            int r1 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0032
            long r10 = r9.lastTunnelingAvSyncPresentationTimeUs
            goto L_0x0034
        L_0x0032:
            r9.lastTunnelingAvSyncPresentationTimeUs = r10
        L_0x0034:
            r5 = r10
            android.media.AudioTrack r2 = r9.audioTrack
            java.nio.ByteBuffer r3 = r9.outputBuffer
            r1 = r9
            r4 = r0
            int r10 = r1.writeNonBlockingWithAvSync(r2, r3, r4, r5)
            goto L_0x0048
        L_0x0040:
            android.media.AudioTrack r10 = r9.audioTrack
            java.nio.ByteBuffer r11 = r9.outputBuffer
            int r10 = writeNonBlocking(r10, r11, r0)
        L_0x0048:
            long r1 = android.os.SystemClock.elapsedRealtime()
            r9.lastFeedElapsedRealtimeMs = r1
            r1 = 0
            if (r10 >= 0) goto L_0x008d
            boolean r11 = isAudioTrackDeadObject(r10)
            if (r11 == 0) goto L_0x006d
            long r3 = r9.getWrittenFrames()
            int r11 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r11 <= 0) goto L_0x0061
            goto L_0x006e
        L_0x0061:
            android.media.AudioTrack r11 = r9.audioTrack
            boolean r11 = isOffloadedPlayback(r11)
            if (r11 == 0) goto L_0x006d
            r9.maybeDisableOffload()
            goto L_0x006e
        L_0x006d:
            r7 = r8
        L_0x006e:
            androidx.media3.exoplayer.audio.AudioSink$WriteException r11 = new androidx.media3.exoplayer.audio.AudioSink$WriteException
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r0 = r9.configuration
            androidx.media3.common.Format r0 = r0.inputFormat
            r11.<init>(r10, r0, r7)
            androidx.media3.exoplayer.audio.AudioSink$Listener r10 = r9.listener
            if (r10 == 0) goto L_0x007e
            r10.onAudioSinkError(r11)
        L_0x007e:
            boolean r10 = r11.isRecoverable
            if (r10 != 0) goto L_0x0088
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r10 = r9.writeExceptionPendingExceptionHolder
            r10.throwExceptionIfDeadlineIsReached(r11)
            return
        L_0x0088:
            androidx.media3.exoplayer.audio.AudioCapabilities r10 = androidx.media3.exoplayer.audio.AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES
            r9.audioCapabilities = r10
            throw r11
        L_0x008d:
            androidx.media3.exoplayer.audio.DefaultAudioSink$PendingExceptionHolder<androidx.media3.exoplayer.audio.AudioSink$WriteException> r11 = r9.writeExceptionPendingExceptionHolder
            r11.clear()
            android.media.AudioTrack r11 = r9.audioTrack
            boolean r11 = isOffloadedPlayback(r11)
            if (r11 == 0) goto L_0x00b3
            long r3 = r9.writtenEncodedFrames
            int r11 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r11 <= 0) goto L_0x00a2
            r9.isWaitingForOffloadEndOfStreamHandled = r8
        L_0x00a2:
            boolean r11 = r9.playing
            if (r11 == 0) goto L_0x00b3
            androidx.media3.exoplayer.audio.AudioSink$Listener r11 = r9.listener
            if (r11 == 0) goto L_0x00b3
            if (r10 >= r0) goto L_0x00b3
            boolean r1 = r9.isWaitingForOffloadEndOfStreamHandled
            if (r1 != 0) goto L_0x00b3
            r11.onOffloadBufferFull()
        L_0x00b3:
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r11 = r9.configuration
            int r11 = r11.outputMode
            if (r11 != 0) goto L_0x00bf
            long r1 = r9.writtenPcmBytes
            long r3 = (long) r10
            long r1 = r1 + r3
            r9.writtenPcmBytes = r1
        L_0x00bf:
            if (r10 != r0) goto L_0x00e1
            androidx.media3.exoplayer.audio.DefaultAudioSink$Configuration r10 = r9.configuration
            int r10 = r10.outputMode
            if (r10 == 0) goto L_0x00de
            java.nio.ByteBuffer r10 = r9.outputBuffer
            java.nio.ByteBuffer r11 = r9.inputBuffer
            if (r10 != r11) goto L_0x00ce
            goto L_0x00cf
        L_0x00ce:
            r7 = r8
        L_0x00cf:
            androidx.media3.common.util.Assertions.checkState(r7)
            long r10 = r9.writtenEncodedFrames
            int r0 = r9.framesPerEncodedSample
            long r0 = (long) r0
            int r2 = r9.inputBufferAccessUnitCount
            long r2 = (long) r2
            long r0 = r0 * r2
            long r10 = r10 + r0
            r9.writtenEncodedFrames = r10
        L_0x00de:
            r10 = 0
            r9.outputBuffer = r10
        L_0x00e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.drainOutputBuffer(long):void");
    }

    public void playToEndOfStream() throws AudioSink.WriteException {
        if (!this.handledEndOfStream && isAudioTrackInitialized() && drainToEndOfStream()) {
            playPendingData();
            this.handledEndOfStream = true;
        }
    }

    private void maybeDisableOffload() {
        if (this.configuration.outputModeIsOffload()) {
            this.offloadDisabledUntilNextConfiguration = true;
        }
    }

    private static boolean isAudioTrackDeadObject(int i) {
        return (Util.SDK_INT >= 24 && i == -6) || i == ERROR_NATIVE_DEAD_OBJECT;
    }

    public boolean isEnded() {
        return !isAudioTrackInitialized() || (this.handledEndOfStream && !hasPendingData());
    }

    public boolean hasPendingData() {
        return isAudioTrackInitialized() && (Util.SDK_INT < 29 || !this.audioTrack.isOffloadedPlayback() || !this.handledOffloadOnPresentationEnded) && this.audioTrackPositionTracker.hasPendingData(getWrittenFrames());
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters2) {
        this.playbackParameters = new PlaybackParameters(Util.constrainValue(playbackParameters2.speed, 0.1f, 8.0f), Util.constrainValue(playbackParameters2.pitch, 0.1f, 8.0f));
        if (useAudioTrackPlaybackParams()) {
            setAudioTrackPlaybackParametersV23();
        } else {
            setAudioProcessorPlaybackParameters(playbackParameters2);
        }
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public void setSkipSilenceEnabled(boolean z) {
        this.skipSilenceEnabled = z;
        setAudioProcessorPlaybackParameters(useAudioTrackPlaybackParams() ? PlaybackParameters.DEFAULT : this.playbackParameters);
    }

    public boolean getSkipSilenceEnabled() {
        return this.skipSilenceEnabled;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes2) {
        if (!this.audioAttributes.equals(audioAttributes2)) {
            this.audioAttributes = audioAttributes2;
            if (!this.tunneling) {
                AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = this.audioCapabilitiesReceiver;
                if (audioCapabilitiesReceiver2 != null) {
                    audioCapabilitiesReceiver2.setAudioAttributes(audioAttributes2);
                }
                flush();
            }
        }
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public void setAudioSessionId(int i) {
        if (this.audioSessionId != i) {
            this.audioSessionId = i;
            this.externalAudioSessionIdProvided = i != 0;
            flush();
        }
    }

    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo2) {
        if (!this.auxEffectInfo.equals(auxEffectInfo2)) {
            int i = auxEffectInfo2.effectId;
            float f = auxEffectInfo2.sendLevel;
            if (this.audioTrack != null) {
                if (this.auxEffectInfo.effectId != i) {
                    this.audioTrack.attachAuxEffect(i);
                }
                if (i != 0) {
                    this.audioTrack.setAuxEffectSendLevel(f);
                }
            }
            this.auxEffectInfo = auxEffectInfo2;
        }
    }

    public void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        this.preferredDevice = audioDeviceInfo == null ? null : new AudioDeviceInfoApi23(audioDeviceInfo);
        AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver2 != null) {
            audioCapabilitiesReceiver2.setRoutedDevice(audioDeviceInfo);
        }
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            Api23.setPreferredDeviceOnAudioTrack(audioTrack2, this.preferredDevice);
        }
    }

    public void enableTunnelingV21() {
        Assertions.checkState(this.externalAudioSessionIdProvided);
        if (!this.tunneling) {
            this.tunneling = true;
            flush();
        }
    }

    public void disableTunneling() {
        if (this.tunneling) {
            this.tunneling = false;
            flush();
        }
    }

    public void setOffloadMode(int i) {
        Assertions.checkState(Util.SDK_INT >= 29);
        this.offloadMode = i;
    }

    public void setOffloadDelayPadding(int i, int i2) {
        Configuration configuration2;
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null && isOffloadedPlayback(audioTrack2) && (configuration2 = this.configuration) != null && configuration2.enableOffloadGapless) {
            this.audioTrack.setOffloadDelayPadding(i, i2);
        }
    }

    public void setVolume(float f) {
        if (this.volume != f) {
            this.volume = f;
            setVolumeInternal();
        }
    }

    private void setVolumeInternal() {
        if (isAudioTrackInitialized()) {
            this.audioTrack.setVolume(this.volume);
        }
    }

    public void pause() {
        this.playing = false;
        if (!isAudioTrackInitialized()) {
            return;
        }
        if (this.audioTrackPositionTracker.pause() || isOffloadedPlayback(this.audioTrack)) {
            this.audioTrack.pause();
        }
    }

    public void flush() {
        OnRoutingChangedListenerApi24 onRoutingChangedListenerApi24;
        if (isAudioTrackInitialized()) {
            resetSinkStateForFlush();
            if (this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }
            if (isOffloadedPlayback(this.audioTrack)) {
                ((StreamEventCallbackV29) Assertions.checkNotNull(this.offloadStreamEventCallbackV29)).unregister(this.audioTrack);
            }
            AudioSink.AudioTrackConfig buildAudioTrackConfig = this.configuration.buildAudioTrackConfig();
            Configuration configuration2 = this.pendingConfiguration;
            if (configuration2 != null) {
                this.configuration = configuration2;
                this.pendingConfiguration = null;
            }
            this.audioTrackPositionTracker.reset();
            if (Util.SDK_INT >= 24 && (onRoutingChangedListenerApi24 = this.onRoutingChangedListener) != null) {
                onRoutingChangedListenerApi24.release();
                this.onRoutingChangedListener = null;
            }
            releaseAudioTrackAsync(this.audioTrack, this.listener, buildAudioTrackConfig);
            this.audioTrack = null;
        }
        this.writeExceptionPendingExceptionHolder.clear();
        this.initializationExceptionPendingExceptionHolder.clear();
        this.skippedOutputFrameCountAtLastPosition = 0;
        this.accumulatedSkippedSilenceDurationUs = 0;
        Handler handler = this.reportSkippedSilenceHandler;
        if (handler != null) {
            ((Handler) Assertions.checkNotNull(handler)).removeCallbacksAndMessages((Object) null);
        }
    }

    public void reset() {
        flush();
        UnmodifiableIterator<AudioProcessor> it = this.toIntPcmAvailableAudioProcessors.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
        UnmodifiableIterator<AudioProcessor> it2 = this.toFloatPcmAvailableAudioProcessors.iterator();
        while (it2.hasNext()) {
            it2.next().reset();
        }
        AudioProcessingPipeline audioProcessingPipeline2 = this.audioProcessingPipeline;
        if (audioProcessingPipeline2 != null) {
            audioProcessingPipeline2.reset();
        }
        this.playing = false;
        this.offloadDisabledUntilNextConfiguration = false;
    }

    public void release() {
        AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver2 != null) {
            audioCapabilitiesReceiver2.unregister();
        }
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities2) {
        String str;
        Looper myLooper = Looper.myLooper();
        Looper looper = this.playbackLooper;
        if (looper != myLooper) {
            String str2 = "null";
            if (looper == null) {
                str = str2;
            } else {
                str = looper.getThread().getName();
            }
            if (myLooper != null) {
                str2 = myLooper.getThread().getName();
            }
            throw new IllegalStateException("Current looper (" + str2 + ") is not the playback looper (" + str + ")");
        } else if (!audioCapabilities2.equals(this.audioCapabilities)) {
            this.audioCapabilities = audioCapabilities2;
            AudioSink.Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onAudioCapabilitiesChanged();
            }
        }
    }

    private void resetSinkStateForFlush() {
        this.submittedPcmBytes = 0;
        this.submittedEncodedFrames = 0;
        this.writtenPcmBytes = 0;
        this.writtenEncodedFrames = 0;
        this.isWaitingForOffloadEndOfStreamHandled = false;
        this.framesPerEncodedSample = 0;
        this.mediaPositionParameters = new MediaPositionParameters(this.playbackParameters, 0, 0);
        this.startMediaTimeUs = 0;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioTrack = false;
        this.handledEndOfStream = false;
        this.handledOffloadOnPresentationEnded = false;
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.trimmingAudioProcessor.resetTrimmedFrameCount();
        setupAudioProcessors();
    }

    private void setAudioTrackPlaybackParametersV23() {
        if (isAudioTrackInitialized()) {
            try {
                this.audioTrack.setPlaybackParams(new PlaybackParams().allowDefaults().setSpeed(this.playbackParameters.speed).setPitch(this.playbackParameters.pitch).setAudioFallbackMode(2));
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Failed to set playback params", e);
            }
            PlaybackParameters playbackParameters2 = new PlaybackParameters(this.audioTrack.getPlaybackParams().getSpeed(), this.audioTrack.getPlaybackParams().getPitch());
            this.playbackParameters = playbackParameters2;
            this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(playbackParameters2.speed);
        }
    }

    private void setAudioProcessorPlaybackParameters(PlaybackParameters playbackParameters2) {
        MediaPositionParameters mediaPositionParameters2 = new MediaPositionParameters(playbackParameters2, C.TIME_UNSET, C.TIME_UNSET);
        if (isAudioTrackInitialized()) {
            this.afterDrainParameters = mediaPositionParameters2;
        } else {
            this.mediaPositionParameters = mediaPositionParameters2;
        }
    }

    private void applyAudioProcessorPlaybackParametersAndSkipSilence(long j) {
        PlaybackParameters playbackParameters2;
        if (!useAudioTrackPlaybackParams()) {
            if (shouldApplyAudioProcessorPlaybackParameters()) {
                playbackParameters2 = this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters);
            } else {
                playbackParameters2 = PlaybackParameters.DEFAULT;
            }
            this.playbackParameters = playbackParameters2;
        } else {
            playbackParameters2 = PlaybackParameters.DEFAULT;
        }
        PlaybackParameters playbackParameters3 = playbackParameters2;
        this.skipSilenceEnabled = shouldApplyAudioProcessorPlaybackParameters() ? this.audioProcessorChain.applySkipSilenceEnabled(this.skipSilenceEnabled) : false;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(playbackParameters3, Math.max(0, j), this.configuration.framesToDurationUs(getWrittenFrames())));
        setupAudioProcessors();
        AudioSink.Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onSkipSilenceEnabledChanged(this.skipSilenceEnabled);
        }
    }

    private boolean shouldApplyAudioProcessorPlaybackParameters() {
        return !this.tunneling && this.configuration.outputMode == 0 && !shouldUseFloatOutput(this.configuration.inputFormat.pcmEncoding);
    }

    private boolean useAudioTrackPlaybackParams() {
        Configuration configuration2 = this.configuration;
        return configuration2 != null && configuration2.enableAudioTrackPlaybackParams && Util.SDK_INT >= 23;
    }

    private boolean shouldUseFloatOutput(int i) {
        return this.enableFloatOutput && Util.isEncodingHighResolutionPcm(i);
    }

    private long applyMediaPositionParameters(long j) {
        while (!this.mediaPositionParametersCheckpoints.isEmpty() && j >= this.mediaPositionParametersCheckpoints.getFirst().audioTrackPositionUs) {
            this.mediaPositionParameters = this.mediaPositionParametersCheckpoints.remove();
        }
        long j2 = j - this.mediaPositionParameters.audioTrackPositionUs;
        long mediaDurationForPlayoutDuration = Util.getMediaDurationForPlayoutDuration(j2, this.mediaPositionParameters.playbackParameters.speed);
        if (!this.mediaPositionParametersCheckpoints.isEmpty()) {
            return this.mediaPositionParameters.mediaTimeUs + mediaDurationForPlayoutDuration + this.mediaPositionParameters.mediaPositionDriftUs;
        }
        long mediaDuration = this.audioProcessorChain.getMediaDuration(j2);
        long j3 = this.mediaPositionParameters.mediaTimeUs + mediaDuration;
        this.mediaPositionParameters.mediaPositionDriftUs = mediaDuration - mediaDurationForPlayoutDuration;
        return j3;
    }

    private long applySkipping(long j) {
        long skippedOutputFrameCount = this.audioProcessorChain.getSkippedOutputFrameCount();
        long framesToDurationUs = j + this.configuration.framesToDurationUs(skippedOutputFrameCount);
        long j2 = this.skippedOutputFrameCountAtLastPosition;
        if (skippedOutputFrameCount > j2) {
            long framesToDurationUs2 = this.configuration.framesToDurationUs(skippedOutputFrameCount - j2);
            this.skippedOutputFrameCountAtLastPosition = skippedOutputFrameCount;
            handleSkippedSilence(framesToDurationUs2);
        }
        return framesToDurationUs;
    }

    private void handleSkippedSilence(long j) {
        this.accumulatedSkippedSilenceDurationUs += j;
        if (this.reportSkippedSilenceHandler == null) {
            this.reportSkippedSilenceHandler = new Handler(Looper.myLooper());
        }
        this.reportSkippedSilenceHandler.removeCallbacksAndMessages((Object) null);
        this.reportSkippedSilenceHandler.postDelayed(new DefaultAudioSink$$ExternalSyntheticLambda0(this), 100);
    }

    private boolean isAudioTrackInitialized() {
        return this.audioTrack != null;
    }

    /* access modifiers changed from: private */
    public long getSubmittedFrames() {
        if (this.configuration.outputMode == 0) {
            return this.submittedPcmBytes / ((long) this.configuration.inputPcmFrameSize);
        }
        return this.submittedEncodedFrames;
    }

    /* access modifiers changed from: private */
    public long getWrittenFrames() {
        if (this.configuration.outputMode == 0) {
            return Util.ceilDivide(this.writtenPcmBytes, (long) this.configuration.outputPcmFrameSize);
        }
        return this.writtenEncodedFrames;
    }

    private void maybeStartAudioCapabilitiesReceiver() {
        if (this.audioCapabilitiesReceiver == null && this.context != null) {
            this.playbackLooper = Looper.myLooper();
            AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = new AudioCapabilitiesReceiver(this.context, (AudioCapabilitiesReceiver.Listener) new DefaultAudioSink$$ExternalSyntheticLambda3(this), this.audioAttributes, this.preferredDevice);
            this.audioCapabilitiesReceiver = audioCapabilitiesReceiver2;
            this.audioCapabilities = audioCapabilitiesReceiver2.register();
        }
    }

    private static boolean isOffloadedPlayback(AudioTrack audioTrack2) {
        return Util.SDK_INT >= 29 && audioTrack2.isOffloadedPlayback();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
        return androidx.media3.extractor.Ac3Util.parseAc3SyncframeAudioSampleCount(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getFramesPerEncodedSample(int r2, java.nio.ByteBuffer r3) {
        /*
            r0 = 20
            if (r2 == r0) goto L_0x0061
            r0 = 30
            if (r2 == r0) goto L_0x005c
            r0 = 1024(0x400, float:1.435E-42)
            r1 = -1
            switch(r2) {
                case 5: goto L_0x0057;
                case 6: goto L_0x0057;
                case 7: goto L_0x005c;
                case 8: goto L_0x005c;
                case 9: goto L_0x0042;
                case 10: goto L_0x0041;
                case 11: goto L_0x003e;
                case 12: goto L_0x003e;
                default: goto L_0x000e;
            }
        L_0x000e:
            switch(r2) {
                case 14: goto L_0x002f;
                case 15: goto L_0x002c;
                case 16: goto L_0x002b;
                case 17: goto L_0x0026;
                case 18: goto L_0x0057;
                default: goto L_0x0011;
            }
        L_0x0011:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected audio encoding: "
            r0.<init>(r1)
            java.lang.StringBuilder r2 = r0.append(r2)
            java.lang.String r2 = r2.toString()
            r3.<init>(r2)
            throw r3
        L_0x0026:
            int r2 = androidx.media3.extractor.Ac4Util.parseAc4SyncframeAudioSampleCount(r3)
            return r2
        L_0x002b:
            return r0
        L_0x002c:
            r2 = 512(0x200, float:7.175E-43)
            return r2
        L_0x002f:
            int r2 = androidx.media3.extractor.Ac3Util.findTrueHdSyncframeOffset(r3)
            if (r2 != r1) goto L_0x0037
            r2 = 0
            goto L_0x003d
        L_0x0037:
            int r2 = androidx.media3.extractor.Ac3Util.parseTrueHdSyncframeAudioSampleCount(r3, r2)
            int r2 = r2 * 16
        L_0x003d:
            return r2
        L_0x003e:
            r2 = 2048(0x800, float:2.87E-42)
            return r2
        L_0x0041:
            return r0
        L_0x0042:
            int r2 = r3.position()
            int r2 = androidx.media3.common.util.Util.getBigEndianInt(r3, r2)
            int r2 = androidx.media3.extractor.MpegAudioUtil.parseMpegAudioFrameSampleCount(r2)
            if (r2 == r1) goto L_0x0051
            return r2
        L_0x0051:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            r2.<init>()
            throw r2
        L_0x0057:
            int r2 = androidx.media3.extractor.Ac3Util.parseAc3SyncframeAudioSampleCount(r3)
            return r2
        L_0x005c:
            int r2 = androidx.media3.extractor.DtsUtil.parseDtsAudioSampleCount((java.nio.ByteBuffer) r3)
            return r2
        L_0x0061:
            int r2 = androidx.media3.extractor.OpusUtil.parseOggPacketAudioSampleCount(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.DefaultAudioSink.getFramesPerEncodedSample(int, java.nio.ByteBuffer):int");
    }

    private static int writeNonBlocking(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i) {
        return audioTrack2.write(byteBuffer, i, 1);
    }

    private int writeNonBlockingWithAvSync(AudioTrack audioTrack2, ByteBuffer byteBuffer, int i, long j) {
        if (Util.SDK_INT >= 26) {
            return audioTrack2.write(byteBuffer, i, 1, j * 1000);
        }
        if (this.avSyncHeader == null) {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            this.avSyncHeader = allocate;
            allocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, i);
            this.avSyncHeader.putLong(8, j * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = i;
        }
        int remaining = this.avSyncHeader.remaining();
        if (remaining > 0) {
            int write = audioTrack2.write(this.avSyncHeader, remaining, 1);
            if (write < 0) {
                this.bytesUntilNextAvSync = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int writeNonBlocking = writeNonBlocking(audioTrack2, byteBuffer, i);
        if (writeNonBlocking < 0) {
            this.bytesUntilNextAvSync = 0;
            return writeNonBlocking;
        }
        this.bytesUntilNextAvSync -= writeNonBlocking;
        return writeNonBlocking;
    }

    private void playPendingData() {
        if (!this.stoppedAudioTrack) {
            this.stoppedAudioTrack = true;
            this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
            if (isOffloadedPlayback(this.audioTrack)) {
                this.handledOffloadOnPresentationEnded = false;
            }
            this.audioTrack.stop();
            this.bytesUntilNextAvSync = 0;
        }
    }

    private ByteBuffer maybeRampUpVolume(ByteBuffer byteBuffer) {
        if (this.configuration.outputMode != 0) {
            return byteBuffer;
        }
        int durationUsToSampleCount = (int) Util.durationUsToSampleCount(Util.msToUs(20), this.configuration.outputSampleRate);
        long writtenFrames = getWrittenFrames();
        if (writtenFrames >= ((long) durationUsToSampleCount)) {
            return byteBuffer;
        }
        return PcmAudioUtil.rampUpVolume(byteBuffer, this.configuration.outputEncoding, this.configuration.outputPcmFrameSize, (int) writtenFrames, durationUsToSampleCount);
    }

    private static void releaseAudioTrackAsync(AudioTrack audioTrack2, AudioSink.Listener listener2, AudioSink.AudioTrackConfig audioTrackConfig) {
        Handler handler = new Handler(Looper.myLooper());
        synchronized (releaseExecutorLock) {
            if (releaseExecutor == null) {
                releaseExecutor = Util.newSingleThreadScheduledExecutor("ExoPlayer:AudioTrackReleaseThread");
            }
            pendingReleaseCount++;
            releaseExecutor.schedule(new DefaultAudioSink$$ExternalSyntheticLambda2(audioTrack2, listener2, handler, audioTrackConfig), 20, TimeUnit.MILLISECONDS);
        }
    }

    static /* synthetic */ void lambda$releaseAudioTrackAsync$1(AudioTrack audioTrack2, AudioSink.Listener listener2, Handler handler, AudioSink.AudioTrackConfig audioTrackConfig) {
        try {
            audioTrack2.flush();
            audioTrack2.release();
            if (listener2 != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new DefaultAudioSink$$ExternalSyntheticLambda1(listener2, audioTrackConfig));
            }
            synchronized (releaseExecutorLock) {
                int i = pendingReleaseCount - 1;
                pendingReleaseCount = i;
                if (i == 0) {
                    releaseExecutor.shutdown();
                    releaseExecutor = null;
                }
            }
        } catch (Throwable th) {
            if (listener2 != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new DefaultAudioSink$$ExternalSyntheticLambda1(listener2, audioTrackConfig));
            }
            synchronized (releaseExecutorLock) {
                int i2 = pendingReleaseCount - 1;
                pendingReleaseCount = i2;
                if (i2 == 0) {
                    releaseExecutor.shutdown();
                    releaseExecutor = null;
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean hasPendingAudioTrackReleases() {
        boolean z;
        synchronized (releaseExecutorLock) {
            z = pendingReleaseCount > 0;
        }
        return z;
    }

    private static final class OnRoutingChangedListenerApi24 {
        private final AudioTrack audioTrack;
        private final AudioCapabilitiesReceiver capabilitiesReceiver;
        private AudioRouting.OnRoutingChangedListener listener = new DefaultAudioSink$OnRoutingChangedListenerApi24$$ExternalSyntheticLambda0(this);

        public OnRoutingChangedListenerApi24(AudioTrack audioTrack2, AudioCapabilitiesReceiver audioCapabilitiesReceiver) {
            this.audioTrack = audioTrack2;
            this.capabilitiesReceiver = audioCapabilitiesReceiver;
            audioTrack2.addOnRoutingChangedListener(this.listener, new Handler(Looper.myLooper()));
        }

        public void release() {
            this.audioTrack.removeOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) Assertions.checkNotNull(this.listener));
            this.listener = null;
        }

        /* access modifiers changed from: private */
        public void onRoutingChanged(AudioRouting audioRouting) {
            if (this.listener != null && audioRouting.getRoutedDevice() != null) {
                this.capabilitiesReceiver.setRoutedDevice(audioRouting.getRoutedDevice());
            }
        }
    }

    private final class StreamEventCallbackV29 {
        private final AudioTrack.StreamEventCallback callback;
        private final Handler handler = new Handler(Looper.myLooper());

        public StreamEventCallbackV29() {
            this.callback = new AudioTrack.StreamEventCallback(DefaultAudioSink.this) {
                public void onDataRequest(AudioTrack audioTrack, int i) {
                    if (audioTrack.equals(DefaultAudioSink.this.audioTrack) && DefaultAudioSink.this.listener != null && DefaultAudioSink.this.playing) {
                        DefaultAudioSink.this.listener.onOffloadBufferEmptying();
                    }
                }

                public void onPresentationEnded(AudioTrack audioTrack) {
                    if (audioTrack.equals(DefaultAudioSink.this.audioTrack)) {
                        boolean unused = DefaultAudioSink.this.handledOffloadOnPresentationEnded = true;
                    }
                }

                public void onTearDown(AudioTrack audioTrack) {
                    if (audioTrack.equals(DefaultAudioSink.this.audioTrack) && DefaultAudioSink.this.listener != null && DefaultAudioSink.this.playing) {
                        DefaultAudioSink.this.listener.onOffloadBufferEmptying();
                    }
                }
            };
        }

        public void register(AudioTrack audioTrack) {
            Handler handler2 = this.handler;
            Objects.requireNonNull(handler2);
            audioTrack.registerStreamEventCallback(new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(handler2), this.callback);
        }

        public void unregister(AudioTrack audioTrack) {
            audioTrack.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages((Object) null);
        }
    }

    private static final class MediaPositionParameters {
        public final long audioTrackPositionUs;
        public long mediaPositionDriftUs;
        public final long mediaTimeUs;
        public final PlaybackParameters playbackParameters;

        private MediaPositionParameters(PlaybackParameters playbackParameters2, long j, long j2) {
            this.playbackParameters = playbackParameters2;
            this.mediaTimeUs = j;
            this.audioTrackPositionUs = j2;
        }
    }

    private static int getAudioTrackMinBufferSize(int i, int i2, int i3) {
        int minBufferSize = AudioTrack.getMinBufferSize(i, i2, i3);
        Assertions.checkState(minBufferSize != -2);
        return minBufferSize;
    }

    private final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        private PositionTrackerListener() {
        }

        public void onPositionFramesMismatch(long j, long j2, long j3, long j4) {
            String str = "Spurious audio timestamp (frame position mismatch): " + j + ", " + j2 + ", " + j3 + ", " + j4 + ", " + DefaultAudioSink.this.getSubmittedFrames() + ", " + DefaultAudioSink.this.getWrittenFrames();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w(DefaultAudioSink.TAG, str);
                return;
            }
            throw new InvalidAudioTrackTimestampException(str);
        }

        public void onSystemTimeUsMismatch(long j, long j2, long j3, long j4) {
            String str = "Spurious audio timestamp (system clock mismatch): " + j + ", " + j2 + ", " + j3 + ", " + j4 + ", " + DefaultAudioSink.this.getSubmittedFrames() + ", " + DefaultAudioSink.this.getWrittenFrames();
            if (!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w(DefaultAudioSink.TAG, str);
                return;
            }
            throw new InvalidAudioTrackTimestampException(str);
        }

        public void onInvalidLatency(long j) {
            Log.w(DefaultAudioSink.TAG, "Ignoring impossibly large audio latency: " + j);
        }

        public void onPositionAdvancing(long j) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onPositionAdvancing(j);
            }
        }

        public void onUnderrun(int i, long j) {
            if (DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onUnderrun(i, j, SystemClock.elapsedRealtime() - DefaultAudioSink.this.lastFeedElapsedRealtimeMs);
            }
        }
    }

    private static final class Configuration {
        public final AudioProcessingPipeline audioProcessingPipeline;
        public final int bufferSize;
        public final boolean enableAudioTrackPlaybackParams;
        public final boolean enableOffloadGapless;
        public final Format inputFormat;
        public final int inputPcmFrameSize;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputMode;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;
        public final boolean tunneling;

        public Configuration(Format format, int i, int i2, int i3, int i4, int i5, int i6, int i7, AudioProcessingPipeline audioProcessingPipeline2, boolean z, boolean z2, boolean z3) {
            this.inputFormat = format;
            this.inputPcmFrameSize = i;
            this.outputMode = i2;
            this.outputPcmFrameSize = i3;
            this.outputSampleRate = i4;
            this.outputChannelConfig = i5;
            this.outputEncoding = i6;
            this.bufferSize = i7;
            this.audioProcessingPipeline = audioProcessingPipeline2;
            this.enableAudioTrackPlaybackParams = z;
            this.enableOffloadGapless = z2;
            this.tunneling = z3;
        }

        public Configuration copyWithBufferSize(int i) {
            return new Configuration(this.inputFormat, this.inputPcmFrameSize, this.outputMode, this.outputPcmFrameSize, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, i, this.audioProcessingPipeline, this.enableAudioTrackPlaybackParams, this.enableOffloadGapless, this.tunneling);
        }

        public boolean canReuseAudioTrack(Configuration configuration) {
            return configuration.outputMode == this.outputMode && configuration.outputEncoding == this.outputEncoding && configuration.outputSampleRate == this.outputSampleRate && configuration.outputChannelConfig == this.outputChannelConfig && configuration.outputPcmFrameSize == this.outputPcmFrameSize && configuration.enableAudioTrackPlaybackParams == this.enableAudioTrackPlaybackParams && configuration.enableOffloadGapless == this.enableOffloadGapless;
        }

        public long inputFramesToDurationUs(long j) {
            return Util.sampleCountToDurationUs(j, this.inputFormat.sampleRate);
        }

        public long framesToDurationUs(long j) {
            return Util.sampleCountToDurationUs(j, this.outputSampleRate);
        }

        public AudioSink.AudioTrackConfig buildAudioTrackConfig() {
            int i = this.outputEncoding;
            int i2 = this.outputSampleRate;
            int i3 = this.outputChannelConfig;
            boolean z = this.tunneling;
            boolean z2 = true;
            if (this.outputMode != 1) {
                z2 = false;
            }
            return new AudioSink.AudioTrackConfig(i, i2, i3, z, z2, this.bufferSize);
        }

        public boolean outputModeIsOffload() {
            return this.outputMode == 1;
        }
    }

    private static final class PendingExceptionHolder<T extends Exception> {
        private static final int RETRY_DELAY_MS = 50;
        private static final int RETRY_DURATION_MS = 200;
        private long earliestNextRetryTimeMs = C.TIME_UNSET;
        private T pendingException;
        private long throwDeadlineMs = C.TIME_UNSET;

        public void throwExceptionIfDeadlineIsReached(T t) throws Exception {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (this.pendingException == null) {
                this.pendingException = t;
            }
            if (this.throwDeadlineMs == C.TIME_UNSET && !DefaultAudioSink.hasPendingAudioTrackReleases()) {
                this.throwDeadlineMs = 200 + elapsedRealtime;
            }
            long j = this.throwDeadlineMs;
            if (j == C.TIME_UNSET || elapsedRealtime < j) {
                this.earliestNextRetryTimeMs = elapsedRealtime + 50;
                return;
            }
            T t2 = this.pendingException;
            if (t2 != t) {
                t2.addSuppressed(t);
            }
            T t3 = this.pendingException;
            clear();
            throw t3;
        }

        public boolean shouldWaitBeforeRetry() {
            if (this.pendingException == null) {
                return false;
            }
            if (DefaultAudioSink.hasPendingAudioTrackReleases()) {
                return true;
            }
            if (SystemClock.elapsedRealtime() < this.earliestNextRetryTimeMs) {
                return true;
            }
            return false;
        }

        public void clear() {
            this.pendingException = null;
            this.throwDeadlineMs = C.TIME_UNSET;
            this.earliestNextRetryTimeMs = C.TIME_UNSET;
        }
    }

    /* access modifiers changed from: private */
    public void maybeReportSkippedSilence() {
        if (this.accumulatedSkippedSilenceDurationUs >= 300000) {
            this.listener.onSilenceSkipped();
            this.accumulatedSkippedSilenceDurationUs = 0;
        }
    }

    private static final class Api23 {
        private Api23() {
        }

        public static void setPreferredDeviceOnAudioTrack(AudioTrack audioTrack, AudioDeviceInfoApi23 audioDeviceInfoApi23) {
            audioTrack.setPreferredDevice(audioDeviceInfoApi23 == null ? null : audioDeviceInfoApi23.audioDeviceInfo);
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static void setLogSessionIdOnAudioTrack(AudioTrack audioTrack, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                audioTrack.setLogSessionId(logSessionId);
            }
        }
    }
}
