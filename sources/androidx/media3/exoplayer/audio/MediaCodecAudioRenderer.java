package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import androidx.media3.exoplayer.mediacodec.LoudnessCodecController;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.extractor.VorbisUtil;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.SentryThread;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Objects;

public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    private static final String TAG = "MediaCodecAudioRenderer";
    private static final String VIVO_BITS_PER_SAMPLE_KEY = "v-bits-per-sample";
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioSinkNeedsReset;
    private int codecMaxInputSize;
    private boolean codecNeedsDiscardChannelsWorkaround;
    private boolean codecNeedsVorbisToAndroidChannelMappingWorkaround;
    private final Context context;
    private long currentPositionUs;
    private Format decryptOnlyCodecFormat;
    /* access modifiers changed from: private */
    public final AudioRendererEventListener.EventDispatcher eventDispatcher;
    /* access modifiers changed from: private */
    public boolean hasPendingReportedSkippedSilence;
    private Format inputFormat;
    private boolean isStarted;
    private final LoudnessCodecController loudnessCodecController;
    private long nextBufferToWritePresentationTimeUs;
    private int rendererPriority;

    public MediaClock getMediaClock() {
        return this;
    }

    public String getName() {
        return TAG;
    }

    public MediaCodecAudioRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, (Handler) null, (AudioRendererEventListener) null);
    }

    public MediaCodecAudioRenderer(Context context2, MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener) {
        this(context2, mediaCodecSelector, handler, audioRendererEventListener, new DefaultAudioSink.Builder(context2).build());
    }

    @Deprecated
    public MediaCodecAudioRenderer(Context context2, MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioCapabilities audioCapabilities, AudioProcessor... audioProcessorArr) {
        this(context2, mediaCodecSelector, handler, audioRendererEventListener, new DefaultAudioSink.Builder().setAudioCapabilities((AudioCapabilities) MoreObjects.firstNonNull(audioCapabilities, AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES)).setAudioProcessors(audioProcessorArr).build());
    }

    public MediaCodecAudioRenderer(Context context2, MediaCodecSelector mediaCodecSelector, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink2) {
        this(context2, MediaCodecAdapter.Factory.getDefault(context2), mediaCodecSelector, false, handler, audioRendererEventListener, audioSink2);
    }

    public MediaCodecAudioRenderer(Context context2, MediaCodecSelector mediaCodecSelector, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink2) {
        this(context2, MediaCodecAdapter.Factory.getDefault(context2), mediaCodecSelector, z, handler, audioRendererEventListener, audioSink2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaCodecAudioRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink2) {
        this(context2, factory, mediaCodecSelector, z, handler, audioRendererEventListener, audioSink2, Util.SDK_INT >= 35 ? new LoudnessCodecController() : null);
    }

    public MediaCodecAudioRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink2, LoudnessCodecController loudnessCodecController2) {
        super(1, factory, mediaCodecSelector, z, 44100.0f);
        this.context = context2.getApplicationContext();
        this.audioSink = audioSink2;
        this.loudnessCodecController = loudnessCodecController2;
        this.rendererPriority = -1000;
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        this.nextBufferToWritePresentationTimeUs = C.TIME_UNSET;
        audioSink2.setListener(new AudioSinkListener());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int supportsFormat(androidx.media3.exoplayer.mediacodec.MediaCodecSelector r11, androidx.media3.common.Format r12) throws androidx.media3.exoplayer.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            r10 = this;
            java.lang.String r0 = r12.sampleMimeType
            boolean r0 = androidx.media3.common.MimeTypes.isAudio(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000e
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r1)
            return r11
        L_0x000e:
            int r0 = r12.cryptoType
            r2 = 1
            if (r0 == 0) goto L_0x0015
            r0 = r2
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            boolean r3 = supportsFormatDrm(r12)
            r4 = 8
            r5 = 4
            if (r3 == 0) goto L_0x003c
            if (r0 == 0) goto L_0x0027
            androidx.media3.exoplayer.mediacodec.MediaCodecInfo r0 = androidx.media3.exoplayer.mediacodec.MediaCodecUtil.getDecryptOnlyDecoderInfo()
            if (r0 == 0) goto L_0x003c
        L_0x0027:
            int r0 = r10.getAudioOffloadSupport(r12)
            androidx.media3.exoplayer.audio.AudioSink r6 = r10.audioSink
            boolean r6 = r6.supportsFormat(r12)
            if (r6 == 0) goto L_0x003a
            r11 = 32
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r5, r4, r11, r0)
            return r11
        L_0x003a:
            r6 = r0
            goto L_0x003d
        L_0x003c:
            r6 = r1
        L_0x003d:
            java.lang.String r0 = "audio/raw"
            java.lang.String r7 = r12.sampleMimeType
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0054
            androidx.media3.exoplayer.audio.AudioSink r0 = r10.audioSink
            boolean r0 = r0.supportsFormat(r12)
            if (r0 != 0) goto L_0x0054
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r2)
            return r11
        L_0x0054:
            androidx.media3.exoplayer.audio.AudioSink r0 = r10.audioSink
            int r7 = r12.channelCount
            int r8 = r12.sampleRate
            r9 = 2
            androidx.media3.common.Format r7 = androidx.media3.common.util.Util.getPcmFormat(r9, r7, r8)
            boolean r0 = r0.supportsFormat(r7)
            if (r0 != 0) goto L_0x006a
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r2)
            return r11
        L_0x006a:
            androidx.media3.exoplayer.audio.AudioSink r0 = r10.audioSink
            java.util.List r11 = getDecoderInfos(r11, r12, r1, r0)
            boolean r0 = r11.isEmpty()
            if (r0 == 0) goto L_0x007b
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r2)
            return r11
        L_0x007b:
            if (r3 != 0) goto L_0x0082
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r9)
            return r11
        L_0x0082:
            java.lang.Object r0 = r11.get(r1)
            androidx.media3.exoplayer.mediacodec.MediaCodecInfo r0 = (androidx.media3.exoplayer.mediacodec.MediaCodecInfo) r0
            boolean r3 = r0.isFormatSupported(r12)
            if (r3 != 0) goto L_0x00a7
            r7 = r2
        L_0x008f:
            int r8 = r11.size()
            if (r7 >= r8) goto L_0x00a7
            java.lang.Object r8 = r11.get(r7)
            androidx.media3.exoplayer.mediacodec.MediaCodecInfo r8 = (androidx.media3.exoplayer.mediacodec.MediaCodecInfo) r8
            boolean r9 = r8.isFormatSupported(r12)
            if (r9 == 0) goto L_0x00a4
            r11 = r1
            r0 = r8
            goto L_0x00a9
        L_0x00a4:
            int r7 = r7 + 1
            goto L_0x008f
        L_0x00a7:
            r11 = r2
            r2 = r3
        L_0x00a9:
            if (r2 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            r5 = 3
        L_0x00ad:
            if (r2 == 0) goto L_0x00b7
            boolean r12 = r0.isSeamlessAdaptationSupported(r12)
            if (r12 == 0) goto L_0x00b7
            r4 = 16
        L_0x00b7:
            boolean r12 = r0.hardwareAccelerated
            if (r12 == 0) goto L_0x00bf
            r12 = 64
            r3 = r12
            goto L_0x00c0
        L_0x00bf:
            r3 = r1
        L_0x00c0:
            if (r11 == 0) goto L_0x00c4
            r1 = 128(0x80, float:1.794E-43)
        L_0x00c4:
            r11 = r1
            r2 = 32
            r0 = r5
            r1 = r4
            r4 = r11
            r5 = r6
            int r11 = androidx.media3.exoplayer.RendererCapabilities.create(r0, r1, r2, r3, r4, r5)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.audio.MediaCodecAudioRenderer.supportsFormat(androidx.media3.exoplayer.mediacodec.MediaCodecSelector, androidx.media3.common.Format):int");
    }

    private int getAudioOffloadSupport(Format format) {
        AudioOffloadSupport formatOffloadSupport = this.audioSink.getFormatOffloadSupport(format);
        if (!formatOffloadSupport.isFormatSupported) {
            return 0;
        }
        int i = formatOffloadSupport.isGaplessSupported ? 1536 : 512;
        return formatOffloadSupport.isSpeedChangeSupported ? i | 2048 : i;
    }

    /* access modifiers changed from: protected */
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(mediaCodecSelector, format, z, this.audioSink), format);
    }

    private static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z, AudioSink audioSink2) throws MediaCodecUtil.DecoderQueryException {
        MediaCodecInfo decryptOnlyDecoderInfo;
        if (format.sampleMimeType == null) {
            return ImmutableList.of();
        }
        if (!audioSink2.supportsFormat(format) || (decryptOnlyDecoderInfo = MediaCodecUtil.getDecryptOnlyDecoderInfo()) == null) {
            return MediaCodecUtil.getDecoderInfosSoftMatch(mediaCodecSelector, format, z, false);
        }
        return ImmutableList.of(decryptOnlyDecoderInfo);
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseBypass(Format format) {
        if (getConfiguration().offloadModePreferred != 0) {
            int audioOffloadSupport = getAudioOffloadSupport(format);
            if ((audioOffloadSupport & 512) != 0) {
                if (getConfiguration().offloadModePreferred == 2 || (audioOffloadSupport & 1024) != 0) {
                    return true;
                }
                if (format.encoderDelay == 0 && format.encoderPadding == 0) {
                    return true;
                }
            }
        }
        return this.audioSink.supportsFormat(format);
    }

    /* access modifiers changed from: protected */
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f) {
        this.codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format, getStreamFormats());
        this.codecNeedsDiscardChannelsWorkaround = codecNeedsDiscardChannelsWorkaround(mediaCodecInfo.name);
        this.codecNeedsVorbisToAndroidChannelMappingWorkaround = codecNeedsVorbisToAndroidChannelMappingWorkaround(mediaCodecInfo.name);
        MediaFormat mediaFormat = getMediaFormat(format, mediaCodecInfo.codecMimeType, this.codecMaxInputSize, f);
        this.decryptOnlyCodecFormat = MimeTypes.AUDIO_RAW.equals(mediaCodecInfo.mimeType) && !MimeTypes.AUDIO_RAW.equals(format.sampleMimeType) ? format : null;
        return MediaCodecAdapter.Configuration.createForAudioDecoding(mediaCodecInfo, mediaFormat, format, mediaCrypto, this.loudnessCodecController);
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        int i;
        DecoderReuseEvaluation canReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i2 = canReuseCodec.discardReasons;
        if (isBypassPossible(format2)) {
            i2 |= 32768;
        }
        if (getCodecMaxInputSize(mediaCodecInfo, format2) > this.codecMaxInputSize) {
            i2 |= 64;
        }
        int i3 = i2;
        String str = mediaCodecInfo.name;
        if (i3 != 0) {
            i = 0;
        } else {
            i = canReuseCodec.result;
        }
        return new DecoderReuseEvaluation(str, format, format2, i, i3);
    }

    public long getDurationToProgressUs(boolean z, long j, long j2) {
        long j3 = this.nextBufferToWritePresentationTimeUs;
        if (j3 == C.TIME_UNSET) {
            return super.getDurationToProgressUs(z, j, j2);
        }
        long j4 = (long) ((((float) (j3 - j)) / (getPlaybackParameters() != null ? getPlaybackParameters().speed : 1.0f)) / 2.0f);
        if (this.isStarted) {
            j4 -= Util.msToUs(getClock().elapsedRealtime()) - j2;
        }
        return Math.max(10000, j4);
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        int i = -1;
        for (Format format2 : formatArr) {
            int i2 = format2.sampleRate;
            if (i2 != -1) {
                i = Math.max(i, i2);
            }
        }
        if (i == -1) {
            return -1.0f;
        }
        return f * ((float) i);
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    /* access modifiers changed from: protected */
    public void onCodecError(Exception exc) {
        Log.e(TAG, "Audio codec error", exc);
        this.eventDispatcher.audioCodecError(exc);
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        Format format = (Format) Assertions.checkNotNull(formatHolder.format);
        this.inputFormat = format;
        DecoderReuseEvaluation onInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged(format, onInputFormatChanged);
        return onInputFormatChanged;
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(Format format, MediaFormat mediaFormat) throws ExoPlaybackException {
        int i;
        Format format2 = this.decryptOnlyCodecFormat;
        int[] iArr = null;
        if (format2 != null) {
            format = format2;
        } else if (getCodec() != null) {
            Assertions.checkNotNull(mediaFormat);
            if (MimeTypes.AUDIO_RAW.equals(format.sampleMimeType)) {
                i = format.pcmEncoding;
            } else if (Util.SDK_INT < 24 || !mediaFormat.containsKey("pcm-encoding")) {
                i = mediaFormat.containsKey(VIVO_BITS_PER_SAMPLE_KEY) ? Util.getPcmEncoding(mediaFormat.getInteger(VIVO_BITS_PER_SAMPLE_KEY)) : 2;
            } else {
                i = mediaFormat.getInteger("pcm-encoding");
            }
            Format build = new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_RAW).setPcmEncoding(i).setEncoderDelay(format.encoderDelay).setEncoderPadding(format.encoderPadding).setMetadata(format.metadata).setCustomData(format.customData).setId(format.id).setLabel(format.label).setLabels(format.labels).setLanguage(format.language).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).setChannelCount(mediaFormat.getInteger("channel-count")).setSampleRate(mediaFormat.getInteger("sample-rate")).build();
            if (this.codecNeedsDiscardChannelsWorkaround && build.channelCount == 6 && format.channelCount < 6) {
                iArr = new int[format.channelCount];
                for (int i2 = 0; i2 < format.channelCount; i2++) {
                    iArr[i2] = i2;
                }
            } else if (this.codecNeedsVorbisToAndroidChannelMappingWorkaround) {
                iArr = VorbisUtil.getVorbisToAndroidChannelLayoutMapping(build.channelCount);
            }
            format = build;
        }
        try {
            if (Util.SDK_INT >= 29) {
                if (!isBypassEnabled() || getConfiguration().offloadModePreferred == 0) {
                    this.audioSink.setOffloadMode(0);
                } else {
                    this.audioSink.setOffloadMode(getConfiguration().offloadModePreferred);
                }
            }
            this.audioSink.configure(format, 0, iArr);
        } catch (AudioSink.ConfigurationException e) {
            throw createRendererException(e, e.format, 5001);
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionDiscontinuity() {
        this.allowPositionDiscontinuity = true;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        this.eventDispatcher.enabled(this.decoderCounters);
        if (getConfiguration().tunneling) {
            this.audioSink.enableTunnelingV21();
        } else {
            this.audioSink.disableTunneling();
        }
        this.audioSink.setPlayerId(getPlayerId());
        this.audioSink.setClock(getClock());
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        this.audioSink.flush();
        this.currentPositionUs = j;
        this.hasPendingReportedSkippedSilence = false;
        this.allowPositionDiscontinuity = true;
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.audioSink.play();
        this.isStarted = true;
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        updateCurrentPosition();
        this.isStarted = false;
        this.audioSink.pause();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.audioSinkNeedsReset = true;
        this.inputFormat = null;
        try {
            this.audioSink.flush();
            try {
                super.onDisabled();
            } finally {
                this.eventDispatcher.disabled(this.decoderCounters);
            }
        } catch (Throwable th) {
            super.onDisabled();
            throw th;
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.hasPendingReportedSkippedSilence = false;
        try {
            super.onReset();
        } finally {
            if (this.audioSinkNeedsReset) {
                this.audioSinkNeedsReset = false;
                this.audioSink.reset();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRelease() {
        LoudnessCodecController loudnessCodecController2;
        this.audioSink.release();
        if (Util.SDK_INT >= 35 && (loudnessCodecController2 = this.loudnessCodecController) != null) {
            loudnessCodecController2.release();
        }
    }

    public boolean isEnded() {
        return super.isEnded() && this.audioSink.isEnded();
    }

    public boolean isReady() {
        return this.audioSink.hasPendingData() || super.isReady();
    }

    public long getPositionUs() {
        if (getState() == 2) {
            updateCurrentPosition();
        }
        return this.currentPositionUs;
    }

    public boolean hasSkippedSilenceSinceLastCall() {
        boolean z = this.hasPendingReportedSkippedSilence;
        this.hasPendingReportedSkippedSilence = false;
        return z;
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.audioSink.setPlaybackParameters(playbackParameters);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    /* access modifiers changed from: protected */
    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        this.audioSink.handleDiscontinuity();
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        Assertions.checkNotNull(byteBuffer);
        this.nextBufferToWritePresentationTimeUs = C.TIME_UNSET;
        if (this.decryptOnlyCodecFormat != null && (i2 & 2) != 0) {
            ((MediaCodecAdapter) Assertions.checkNotNull(mediaCodecAdapter)).releaseOutputBuffer(i, false);
            return true;
        } else if (z) {
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.releaseOutputBuffer(i, false);
            }
            this.decoderCounters.skippedOutputBufferCount += i3;
            this.audioSink.handleDiscontinuity();
            return true;
        } else {
            try {
                if (this.audioSink.handleBuffer(byteBuffer, j3, i3)) {
                    if (mediaCodecAdapter != null) {
                        mediaCodecAdapter.releaseOutputBuffer(i, false);
                    }
                    this.decoderCounters.renderedOutputBufferCount += i3;
                    return true;
                }
                this.nextBufferToWritePresentationTimeUs = j3;
                return false;
            } catch (AudioSink.InitializationException e) {
                throw createRendererException(e, this.inputFormat, e.isRecoverable, (!isBypassEnabled() || getConfiguration().offloadModePreferred == 0) ? 5001 : PlaybackException.ERROR_CODE_AUDIO_TRACK_OFFLOAD_INIT_FAILED);
            } catch (AudioSink.WriteException e2) {
                throw createRendererException(e2, format, e2.isRecoverable, (!isBypassEnabled() || getConfiguration().offloadModePreferred == 0) ? PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED : PlaybackException.ERROR_CODE_AUDIO_TRACK_OFFLOAD_WRITE_FAILED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void renderToEndOfStream() throws ExoPlaybackException {
        try {
            this.audioSink.playToEndOfStream();
            if (getLastBufferInStreamPresentationTimeUs() != C.TIME_UNSET) {
                this.nextBufferToWritePresentationTimeUs = getLastBufferInStreamPresentationTimeUs();
            }
        } catch (AudioSink.WriteException e) {
            throw createRendererException(e, e.format, e.isRecoverable, isBypassEnabled() ? PlaybackException.ERROR_CODE_AUDIO_TRACK_OFFLOAD_WRITE_FAILED : PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED);
        }
    }

    /* access modifiers changed from: protected */
    public void onOutputStreamOffsetUsChanged(long j) {
        this.audioSink.setOutputStreamOffsetUs(j);
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 2) {
            this.audioSink.setVolume(((Float) Assertions.checkNotNull(obj)).floatValue());
        } else if (i == 3) {
            this.audioSink.setAudioAttributes((AudioAttributes) Assertions.checkNotNull((AudioAttributes) obj));
        } else if (i == 6) {
            this.audioSink.setAuxEffectInfo((AuxEffectInfo) Assertions.checkNotNull((AuxEffectInfo) obj));
        } else if (i != 12) {
            if (i == 16) {
                this.rendererPriority = ((Integer) Assertions.checkNotNull(obj)).intValue();
                updateCodecImportance();
            } else if (i == 9) {
                this.audioSink.setSkipSilenceEnabled(((Boolean) Assertions.checkNotNull(obj)).booleanValue());
            } else if (i != 10) {
                super.handleMessage(i, obj);
            } else {
                setAudioSessionId(((Integer) Assertions.checkNotNull(obj)).intValue());
            }
        } else if (Util.SDK_INT >= 23) {
            Api23.setAudioSinkPreferredDevice(this.audioSink, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) {
        if (Util.SDK_INT >= 29 && decoderInputBuffer.format != null && Objects.equals(decoderInputBuffer.format.sampleMimeType, MimeTypes.AUDIO_OPUS) && isBypassEnabled()) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            int i = ((Format) Assertions.checkNotNull(decoderInputBuffer.format)).encoderDelay;
            if (byteBuffer.remaining() == 8) {
                this.audioSink.setOffloadDelayPadding(i, (int) ((byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getLong() * 48000) / C.NANOS_PER_SECOND));
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize2 = getCodecMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            return codecMaxInputSize2;
        }
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                codecMaxInputSize2 = Math.max(codecMaxInputSize2, getCodecMaxInputSize(mediaCodecInfo, format2));
            }
        }
        return codecMaxInputSize2;
    }

    private int getCodecMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (!"OMX.google.raw.decoder".equals(mediaCodecInfo.name) || Util.SDK_INT >= 24 || (Util.SDK_INT == 23 && Util.isTv(this.context))) {
            return format.maxInputSize;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public MediaFormat getMediaFormat(Format format, String str, int i, float f) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("channel-count", format.channelCount);
        mediaFormat.setInteger("sample-rate", format.sampleRate);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", i);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger(SentryThread.JsonKeys.PRIORITY, 0);
            if (f != -1.0f && !deviceDoesntSupportOperatingRate()) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (Util.SDK_INT <= 28 && MimeTypes.AUDIO_AC4.equals(format.sampleMimeType)) {
            mediaFormat.setInteger("ac4-is-sync", 1);
        }
        if (Util.SDK_INT >= 24 && this.audioSink.getFormatSupport(Util.getPcmFormat(4, format.channelCount, format.sampleRate)) == 2) {
            mediaFormat.setInteger("pcm-encoding", 4);
        }
        if (Util.SDK_INT >= 32) {
            mediaFormat.setInteger("max-output-channel-count", 99);
        }
        if (Util.SDK_INT >= 35) {
            mediaFormat.setInteger(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.rendererPriority));
        }
        return mediaFormat;
    }

    private void setAudioSessionId(int i) {
        LoudnessCodecController loudnessCodecController2;
        this.audioSink.setAudioSessionId(i);
        if (Util.SDK_INT >= 35 && (loudnessCodecController2 = this.loudnessCodecController) != null) {
            loudnessCodecController2.setAudioSessionId(i);
        }
    }

    private void updateCodecImportance() {
        MediaCodecAdapter codec = getCodec();
        if (codec != null && Util.SDK_INT >= 35) {
            Bundle bundle = new Bundle();
            bundle.putInt(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.rendererPriority));
            codec.setParameters(bundle);
        }
    }

    private void updateCurrentPosition() {
        long currentPositionUs2 = this.audioSink.getCurrentPositionUs(isEnded());
        if (currentPositionUs2 != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs2 = Math.max(this.currentPositionUs, currentPositionUs2);
            }
            this.currentPositionUs = currentPositionUs2;
            this.allowPositionDiscontinuity = false;
        }
    }

    private static boolean deviceDoesntSupportOperatingRate() {
        return Util.SDK_INT == 23 && ("ZTE B2017G".equals(Util.MODEL) || "AXON 7 mini".equals(Util.MODEL));
    }

    private static boolean codecNeedsDiscardChannelsWorkaround(String str) {
        return Util.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("herolte") || Util.DEVICE.startsWith("heroqlte"));
    }

    private static boolean codecNeedsVorbisToAndroidChannelMappingWorkaround(String str) {
        return str.equals("OMX.google.opus.decoder") || str.equals("c2.android.opus.decoder") || str.equals("OMX.google.vorbis.decoder") || str.equals("c2.android.vorbis.decoder");
    }

    private final class AudioSinkListener implements AudioSink.Listener {
        private AudioSinkListener() {
        }

        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onPositionDiscontinuity();
        }

        public void onSilenceSkipped() {
            boolean unused = MediaCodecAudioRenderer.this.hasPendingReportedSkippedSilence = true;
        }

        public void onPositionAdvancing(long j) {
            MediaCodecAudioRenderer.this.eventDispatcher.positionAdvancing(j);
        }

        public void onUnderrun(int i, long j, long j2) {
            MediaCodecAudioRenderer.this.eventDispatcher.underrun(i, j, j2);
        }

        public void onSkipSilenceEnabledChanged(boolean z) {
            MediaCodecAudioRenderer.this.eventDispatcher.skipSilenceEnabledChanged(z);
        }

        public void onOffloadBufferEmptying() {
            Renderer.WakeupListener access$300 = MediaCodecAudioRenderer.this.getWakeupListener();
            if (access$300 != null) {
                access$300.onWakeup();
            }
        }

        public void onOffloadBufferFull() {
            Renderer.WakeupListener access$400 = MediaCodecAudioRenderer.this.getWakeupListener();
            if (access$400 != null) {
                access$400.onSleep();
            }
        }

        public void onAudioSinkError(Exception exc) {
            Log.e(MediaCodecAudioRenderer.TAG, "Audio sink error", exc);
            MediaCodecAudioRenderer.this.eventDispatcher.audioSinkError(exc);
        }

        public void onAudioCapabilitiesChanged() {
            MediaCodecAudioRenderer.this.onRendererCapabilitiesChanged();
        }

        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig audioTrackConfig) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackInitialized(audioTrackConfig);
        }

        public void onAudioTrackReleased(AudioSink.AudioTrackConfig audioTrackConfig) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackReleased(audioTrackConfig);
        }
    }

    private static final class Api23 {
        private Api23() {
        }

        public static void setAudioSinkPreferredDevice(AudioSink audioSink, Object obj) {
            audioSink.setPreferredDevice((AudioDeviceInfo) obj);
        }
    }
}
