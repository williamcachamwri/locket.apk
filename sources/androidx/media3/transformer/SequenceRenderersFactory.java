package androidx.media3.transformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConstantRateTimestampIterator;
import androidx.media3.common.util.TimestampIterator;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.MediaCodecAudioRenderer;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.image.ImageOutput;
import androidx.media3.exoplayer.image.ImageRenderer;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.VideoSink;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

final class SequenceRenderersFactory implements RenderersFactory {
    private static final int DEFAULT_FRAME_RATE = 30;
    private final Context context;
    private final ImageDecoder.Factory imageDecoderFactory;
    private final int inputIndex;
    private final PlaybackAudioGraphWrapper playbackAudioGraphWrapper;
    private final EditedMediaItemSequence sequence;
    private final VideoSink videoSink;

    public static SequenceRenderersFactory create(Context context2, EditedMediaItemSequence editedMediaItemSequence, PlaybackAudioGraphWrapper playbackAudioGraphWrapper2, VideoSink videoSink2, ImageDecoder.Factory factory, int i) {
        return new SequenceRenderersFactory(context2, editedMediaItemSequence, playbackAudioGraphWrapper2, videoSink2, factory, i);
    }

    public static SequenceRenderersFactory createForAudio(Context context2, EditedMediaItemSequence editedMediaItemSequence, PlaybackAudioGraphWrapper playbackAudioGraphWrapper2, int i) {
        return new SequenceRenderersFactory(context2, editedMediaItemSequence, playbackAudioGraphWrapper2, (VideoSink) null, (ImageDecoder.Factory) null, i);
    }

    private SequenceRenderersFactory(Context context2, EditedMediaItemSequence editedMediaItemSequence, PlaybackAudioGraphWrapper playbackAudioGraphWrapper2, VideoSink videoSink2, ImageDecoder.Factory factory, int i) {
        this.context = context2;
        this.sequence = editedMediaItemSequence;
        this.playbackAudioGraphWrapper = playbackAudioGraphWrapper2;
        this.videoSink = videoSink2;
        this.imageDecoderFactory = factory;
        this.inputIndex = i;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SequenceAudioRenderer(this.context, handler, audioRendererEventListener, this.sequence, this.playbackAudioGraphWrapper.createInput(this.inputIndex), this.playbackAudioGraphWrapper));
        if (this.videoSink != null) {
            arrayList.add(new SequenceVideoRenderer((Context) Assertions.checkStateNotNull(this.context), handler, videoRendererEventListener, this.sequence, this.videoSink));
            arrayList.add(new SequenceImageRenderer(this.sequence, (ImageDecoder.Factory) Assertions.checkStateNotNull(this.imageDecoderFactory), this.videoSink));
        }
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }

    /* access modifiers changed from: private */
    public static long getOffsetToCompositionTimeUs(EditedMediaItemSequence editedMediaItemSequence, int i, long j) {
        long j2 = -j;
        if (i == 0) {
            j2 -= ((EditedMediaItem) editedMediaItemSequence.editedMediaItems.get(0)).mediaItem.clippingConfiguration.startPositionUs;
        }
        for (int i2 = 0; i2 < i; i2++) {
            j2 += getRepeatedEditedMediaItem(editedMediaItemSequence, i2).getPresentationDurationUs();
        }
        return j2;
    }

    /* access modifiers changed from: private */
    public static EditedMediaItem getRepeatedEditedMediaItem(EditedMediaItemSequence editedMediaItemSequence, int i) {
        if (editedMediaItemSequence.isLooping) {
            i %= editedMediaItemSequence.editedMediaItems.size();
        }
        return (EditedMediaItem) editedMediaItemSequence.editedMediaItems.get(i);
    }

    private static final class SequenceAudioRenderer extends MediaCodecAudioRenderer {
        private final AudioGraphInputAudioSink audioSink;
        private EditedMediaItem pendingEditedMediaItem;
        private long pendingOffsetToCompositionTimeUs;
        private final PlaybackAudioGraphWrapper playbackAudioGraphWrapper;
        private final EditedMediaItemSequence sequence;

        public SequenceAudioRenderer(Context context, Handler handler, AudioRendererEventListener audioRendererEventListener, EditedMediaItemSequence editedMediaItemSequence, AudioGraphInputAudioSink audioGraphInputAudioSink, PlaybackAudioGraphWrapper playbackAudioGraphWrapper2) {
            super(context, MediaCodecSelector.DEFAULT, handler, audioRendererEventListener, audioGraphInputAudioSink);
            this.sequence = editedMediaItemSequence;
            this.audioSink = audioGraphInputAudioSink;
            this.playbackAudioGraphWrapper = playbackAudioGraphWrapper2;
        }

        public void render(long j, long j2) throws ExoPlaybackException {
            super.render(j, j2);
            do {
                try {
                } catch (AudioSink.ConfigurationException | AudioSink.InitializationException | AudioSink.WriteException | ExportException e) {
                    throw createRendererException(e, (Format) null, PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED);
                }
            } while (this.playbackAudioGraphWrapper.processData());
        }

        /* access modifiers changed from: protected */
        public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
            boolean z = true;
            if (getTimeline().getWindowCount() != 1) {
                z = false;
            }
            Assertions.checkState(z);
            int indexOfPeriod = getTimeline().getIndexOfPeriod(mediaPeriodId.periodUid);
            this.pendingEditedMediaItem = SequenceRenderersFactory.getRepeatedEditedMediaItem(this.sequence, indexOfPeriod);
            this.pendingOffsetToCompositionTimeUs = SequenceRenderersFactory.getOffsetToCompositionTimeUs(this.sequence, indexOfPeriod, j2);
            super.onStreamChanged(formatArr, j, j2, mediaPeriodId);
        }

        /* access modifiers changed from: protected */
        public void onProcessedStreamChange() {
            super.onProcessedStreamChange();
            onMediaItemChanged();
        }

        /* access modifiers changed from: protected */
        public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
            super.onPositionReset(j, z);
            onMediaItemChanged();
        }

        private void onMediaItemChanged() {
            EditedMediaItem editedMediaItem = (EditedMediaItem) Assertions.checkStateNotNull(this.pendingEditedMediaItem);
            boolean z = true;
            if (editedMediaItem != SequenceRenderersFactory.getRepeatedEditedMediaItem(this.sequence, getTimeline().getPeriodCount() - 1)) {
                z = false;
            }
            this.audioSink.onMediaItemChanged(editedMediaItem, this.pendingOffsetToCompositionTimeUs, z);
        }
    }

    private static final class SequenceVideoRenderer extends MediaCodecVideoRenderer {
        private long offsetToCompositionTimeUs;
        private ImmutableList<Effect> pendingEffect;
        private final EditedMediaItemSequence sequence;
        private final VideoSink videoSink;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SequenceVideoRenderer(Context context, Handler handler, VideoRendererEventListener videoRendererEventListener, EditedMediaItemSequence editedMediaItemSequence, VideoSink videoSink2) {
            super(context, MediaCodecAdapter.Factory.getDefault(context), MediaCodecSelector.DEFAULT, 5000, false, handler, videoRendererEventListener, 50, 30.0f, videoSink2);
            this.sequence = editedMediaItemSequence;
            this.videoSink = videoSink2;
            experimentalEnableProcessedStreamChangedAtStart();
        }

        /* access modifiers changed from: protected */
        public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
            boolean z = true;
            if (getTimeline().getWindowCount() != 1) {
                z = false;
            }
            Assertions.checkState(z);
            super.onStreamChanged(formatArr, j, j2, mediaPeriodId);
            int indexOfPeriod = getTimeline().getIndexOfPeriod(mediaPeriodId.periodUid);
            this.offsetToCompositionTimeUs = SequenceRenderersFactory.getOffsetToCompositionTimeUs(this.sequence, indexOfPeriod, j2);
            this.pendingEffect = ((EditedMediaItem) this.sequence.editedMediaItems.get(indexOfPeriod)).effects.videoEffects;
        }

        /* access modifiers changed from: protected */
        public long getBufferTimestampAdjustmentUs() {
            return this.offsetToCompositionTimeUs;
        }

        /* access modifiers changed from: protected */
        public void onReadyToChangeVideoSinkInputStream() {
            ImmutableList<Effect> immutableList = this.pendingEffect;
            if (immutableList != null) {
                this.videoSink.setPendingVideoEffects(immutableList);
                this.pendingEffect = null;
            }
        }
    }

    private static final class SequenceImageRenderer extends ImageRenderer {
        private EditedMediaItem editedMediaItem;
        private boolean inputStreamPending;
        private boolean mayRenderStartOfStream;
        private long offsetToCompositionTimeUs;
        private ExoPlaybackException pendingExoPlaybackException;
        private final EditedMediaItemSequence sequence;
        private long streamStartPositionUs = C.TIME_UNSET;
        private ConstantRateTimestampIterator timestampIterator;
        private ImmutableList<Effect> videoEffects = ImmutableList.of();
        private final VideoSink videoSink;

        static /* synthetic */ void lambda$onEnabled$0(Runnable runnable) {
        }

        public SequenceImageRenderer(EditedMediaItemSequence editedMediaItemSequence, ImageDecoder.Factory factory, VideoSink videoSink2) {
            super(factory, ImageOutput.NO_OP);
            this.sequence = editedMediaItemSequence;
            this.videoSink = videoSink2;
        }

        /* access modifiers changed from: protected */
        public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
            super.onEnabled(z, z2);
            this.mayRenderStartOfStream = z2;
            this.videoSink.onRendererEnabled(z2);
            if (!this.videoSink.isInitialized()) {
                Format build = new Format.Builder().build();
                try {
                    this.videoSink.initialize(build);
                } catch (VideoSink.VideoSinkException e) {
                    throw createRendererException(e, build, 7000);
                }
            }
            this.videoSink.setListener(VideoSink.Listener.NO_OP, new SequenceRenderersFactory$SequenceImageRenderer$$ExternalSyntheticLambda0());
        }

        /* access modifiers changed from: protected */
        public void onDisabled() {
            super.onDisabled();
            this.videoSink.onRendererDisabled();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
            r0 = r1.timestampIterator;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isEnded() {
            /*
                r1 = this;
                boolean r0 = super.isEnded()
                if (r0 == 0) goto L_0x001a
                androidx.media3.exoplayer.video.VideoSink r0 = r1.videoSink
                boolean r0 = r0.isEnded()
                if (r0 == 0) goto L_0x001a
                androidx.media3.common.util.ConstantRateTimestampIterator r0 = r1.timestampIterator
                if (r0 == 0) goto L_0x0018
                boolean r0 = r0.hasNext()
                if (r0 != 0) goto L_0x001a
            L_0x0018:
                r0 = 1
                goto L_0x001b
            L_0x001a:
                r0 = 0
            L_0x001b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.SequenceRenderersFactory.SequenceImageRenderer.isEnded():boolean");
        }

        public boolean isReady() {
            if (this.mayRenderStartOfStream) {
                return this.videoSink.isReady(super.isReady());
            }
            return super.isReady();
        }

        /* access modifiers changed from: protected */
        public void onReset() {
            super.onReset();
            this.pendingExoPlaybackException = null;
        }

        /* access modifiers changed from: protected */
        public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
            this.videoSink.flush(true);
            super.onPositionReset(j, z);
            this.timestampIterator = createTimestampIterator(j);
        }

        /* access modifiers changed from: protected */
        public void onStarted() throws ExoPlaybackException {
            super.onStarted();
            this.videoSink.onRendererStarted();
        }

        /* access modifiers changed from: protected */
        public void onStopped() {
            super.onStopped();
            this.videoSink.onRendererStopped();
        }

        /* access modifiers changed from: protected */
        public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) throws ExoPlaybackException {
            Assertions.checkState(getTimeline().getWindowCount() == 1);
            super.onStreamChanged(formatArr, j, j2, mediaPeriodId);
            this.streamStartPositionUs = j;
            int indexOfPeriod = getTimeline().getIndexOfPeriod(mediaPeriodId.periodUid);
            this.editedMediaItem = (EditedMediaItem) this.sequence.editedMediaItems.get(indexOfPeriod);
            this.offsetToCompositionTimeUs = SequenceRenderersFactory.getOffsetToCompositionTimeUs(this.sequence, indexOfPeriod, j2);
            this.timestampIterator = createTimestampIterator(j);
            this.videoEffects = this.editedMediaItem.effects.videoEffects;
            this.inputStreamPending = true;
        }

        public void render(long j, long j2) throws ExoPlaybackException {
            ExoPlaybackException exoPlaybackException = this.pendingExoPlaybackException;
            if (exoPlaybackException == null) {
                super.render(j, j2);
                try {
                    this.videoSink.render(j, j2);
                } catch (VideoSink.VideoSinkException e) {
                    throw createRendererException(e, e.format, 7001);
                }
            } else {
                this.pendingExoPlaybackException = null;
                throw exoPlaybackException;
            }
        }

        /* access modifiers changed from: protected */
        public boolean processOutputBuffer(long j, long j2, Bitmap bitmap, long j3) {
            if (this.inputStreamPending) {
                Assertions.checkState(this.streamStartPositionUs != C.TIME_UNSET);
                this.videoSink.setPendingVideoEffects(this.videoEffects);
                this.videoSink.setStreamTimestampInfo(this.streamStartPositionUs, getStreamOffsetUs(), this.offsetToCompositionTimeUs, getLastResetPositionUs());
                this.videoSink.onInputStreamChanged(2, new Format.Builder().setSampleMimeType(MimeTypes.IMAGE_RAW).setWidth(bitmap.getWidth()).setHeight(bitmap.getHeight()).setColorInfo(ColorInfo.SRGB_BT709_FULL).setFrameRate(30.0f).build());
                this.inputStreamPending = false;
            }
            return this.videoSink.handleInputBitmap(bitmap, (TimestampIterator) Assertions.checkStateNotNull(this.timestampIterator));
        }

        private ConstantRateTimestampIterator createTimestampIterator(long j) {
            long streamOffsetUs = getStreamOffsetUs();
            long j2 = this.offsetToCompositionTimeUs + streamOffsetUs;
            return new ConstantRateTimestampIterator(j2 + (j - streamOffsetUs), j2 + ((EditedMediaItem) Assertions.checkNotNull(this.editedMediaItem)).getPresentationDurationUs(), 30.0f);
        }
    }
}
