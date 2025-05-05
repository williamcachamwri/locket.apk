package androidx.media3.exoplayer.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.extractor.text.CueDecoder;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;

public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    private static final int MSG_UPDATE_OUTPUT = 1;
    private static final int REPLACEMENT_STATE_NONE = 0;
    private static final int REPLACEMENT_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REPLACEMENT_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "TextRenderer";
    private final CueDecoder cueDecoder;
    private final DecoderInputBuffer cueDecoderInputBuffer;
    private CuesResolver cuesResolver;
    private int decoderReplacementState;
    private long finalStreamEndPositionUs;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private long lastRendererPositionUs;
    private boolean legacyDecodingEnabled;
    private SubtitleOutputBuffer nextSubtitle;
    private int nextSubtitleEventIndex;
    private SubtitleInputBuffer nextSubtitleInputBuffer;
    private final TextOutput output;
    private final Handler outputHandler;
    private boolean outputStreamEnded;
    private IOException streamError;
    private Format streamFormat;
    private SubtitleOutputBuffer subtitle;
    private SubtitleDecoder subtitleDecoder;
    private final SubtitleDecoderFactory subtitleDecoderFactory;
    private boolean waitingForKeyFrame;

    public String getName() {
        return TAG;
    }

    public TextRenderer(TextOutput textOutput, Looper looper) {
        this(textOutput, looper, SubtitleDecoderFactory.DEFAULT);
    }

    public TextRenderer(TextOutput textOutput, Looper looper, SubtitleDecoderFactory subtitleDecoderFactory2) {
        super(3);
        Handler handler;
        this.output = (TextOutput) Assertions.checkNotNull(textOutput);
        if (looper == null) {
            handler = null;
        } else {
            handler = Util.createHandler(looper, this);
        }
        this.outputHandler = handler;
        this.subtitleDecoderFactory = subtitleDecoderFactory2;
        this.cueDecoder = new CueDecoder();
        this.cueDecoderInputBuffer = new DecoderInputBuffer(1);
        this.formatHolder = new FormatHolder();
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        this.lastRendererPositionUs = C.TIME_UNSET;
        this.legacyDecodingEnabled = false;
    }

    public int supportsFormat(Format format) {
        if (isCuesWithTiming(format) || this.subtitleDecoderFactory.supportsFormat(format)) {
            return RendererCapabilities.create(format.cryptoType == 0 ? 4 : 2);
        } else if (MimeTypes.isText(format.sampleMimeType)) {
            return RendererCapabilities.create(1);
        } else {
            return RendererCapabilities.create(0);
        }
    }

    public void setFinalStreamEndPositionUs(long j) {
        Assertions.checkState(isCurrentStreamFinal());
        this.finalStreamEndPositionUs = j;
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        CuesResolver cuesResolver2;
        Format format = formatArr[0];
        this.streamFormat = format;
        if (!isCuesWithTiming(format)) {
            assertLegacyDecodingEnabledIfRequired();
            if (this.subtitleDecoder != null) {
                this.decoderReplacementState = 1;
            } else {
                initSubtitleDecoder();
            }
        } else {
            if (this.streamFormat.cueReplacementBehavior == 1) {
                cuesResolver2 = new MergingCuesResolver();
            } else {
                cuesResolver2 = new ReplacingCuesResolver();
            }
            this.cuesResolver = cuesResolver2;
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) {
        this.lastRendererPositionUs = j;
        CuesResolver cuesResolver2 = this.cuesResolver;
        if (cuesResolver2 != null) {
            cuesResolver2.clear();
        }
        clearOutput();
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        Format format = this.streamFormat;
        if (format != null && !isCuesWithTiming(format)) {
            if (this.decoderReplacementState != 0) {
                replaceSubtitleDecoder();
                return;
            }
            releaseSubtitleBuffers();
            SubtitleDecoder subtitleDecoder2 = (SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder);
            subtitleDecoder2.flush();
            subtitleDecoder2.setOutputStartTimeUs(getLastResetPositionUs());
        }
    }

    public void render(long j, long j2) {
        if (isCurrentStreamFinal()) {
            long j3 = this.finalStreamEndPositionUs;
            if (j3 != C.TIME_UNSET && j >= j3) {
                releaseSubtitleBuffers();
                this.outputStreamEnded = true;
            }
        }
        if (!this.outputStreamEnded) {
            if (isCuesWithTiming((Format) Assertions.checkNotNull(this.streamFormat))) {
                Assertions.checkNotNull(this.cuesResolver);
                renderFromCuesWithTiming(j);
                return;
            }
            assertLegacyDecodingEnabledIfRequired();
            renderFromSubtitles(j);
        }
    }

    @Deprecated
    public void experimentalSetLegacyDecodingEnabled(boolean z) {
        this.legacyDecodingEnabled = z;
    }

    @RequiresNonNull({"this.cuesResolver"})
    private void renderFromCuesWithTiming(long j) {
        boolean readAndDecodeCuesWithTiming = readAndDecodeCuesWithTiming(j);
        long nextCueChangeTimeUs = this.cuesResolver.getNextCueChangeTimeUs(this.lastRendererPositionUs);
        int i = (nextCueChangeTimeUs > Long.MIN_VALUE ? 1 : (nextCueChangeTimeUs == Long.MIN_VALUE ? 0 : -1));
        if (i == 0 && this.inputStreamEnded && !readAndDecodeCuesWithTiming) {
            this.outputStreamEnded = true;
        }
        if (i != 0 && nextCueChangeTimeUs <= j) {
            readAndDecodeCuesWithTiming = true;
        }
        if (readAndDecodeCuesWithTiming) {
            ImmutableList<Cue> cuesAtTimeUs = this.cuesResolver.getCuesAtTimeUs(j);
            long previousCueChangeTimeUs = this.cuesResolver.getPreviousCueChangeTimeUs(j);
            updateOutput(new CueGroup(cuesAtTimeUs, getPresentationTimeUs(previousCueChangeTimeUs)));
            this.cuesResolver.discardCuesBeforeTimeUs(previousCueChangeTimeUs);
        }
        this.lastRendererPositionUs = j;
    }

    @RequiresNonNull({"this.cuesResolver"})
    private boolean readAndDecodeCuesWithTiming(long j) {
        if (this.inputStreamEnded || readSource(this.formatHolder, this.cueDecoderInputBuffer, 0) != -4) {
            return false;
        }
        if (this.cueDecoderInputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            return false;
        }
        this.cueDecoderInputBuffer.flip();
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(this.cueDecoderInputBuffer.data);
        CuesWithTiming decode = this.cueDecoder.decode(this.cueDecoderInputBuffer.timeUs, byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
        this.cueDecoderInputBuffer.clear();
        return this.cuesResolver.addCues(decode, j);
    }

    private void renderFromSubtitles(long j) {
        boolean z;
        this.lastRendererPositionUs = j;
        if (this.nextSubtitle == null) {
            ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).setPositionUs(j);
            try {
                this.nextSubtitle = (SubtitleOutputBuffer) ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).dequeueOutputBuffer();
            } catch (SubtitleDecoderException e) {
                handleDecoderError(e);
                return;
            }
        }
        if (getState() == 2) {
            if (this.subtitle != null) {
                long nextEventTime = getNextEventTime();
                z = false;
                while (nextEventTime <= j) {
                    this.nextSubtitleEventIndex++;
                    nextEventTime = getNextEventTime();
                    z = true;
                }
            } else {
                z = false;
            }
            SubtitleOutputBuffer subtitleOutputBuffer = this.nextSubtitle;
            if (subtitleOutputBuffer != null) {
                if (subtitleOutputBuffer.isEndOfStream()) {
                    if (!z && getNextEventTime() == Long.MAX_VALUE) {
                        if (this.decoderReplacementState == 2) {
                            replaceSubtitleDecoder();
                        } else {
                            releaseSubtitleBuffers();
                            this.outputStreamEnded = true;
                        }
                    }
                } else if (subtitleOutputBuffer.timeUs <= j) {
                    SubtitleOutputBuffer subtitleOutputBuffer2 = this.subtitle;
                    if (subtitleOutputBuffer2 != null) {
                        subtitleOutputBuffer2.release();
                    }
                    this.nextSubtitleEventIndex = subtitleOutputBuffer.getNextEventTimeIndex(j);
                    this.subtitle = subtitleOutputBuffer;
                    this.nextSubtitle = null;
                    z = true;
                }
            }
            if (z) {
                Assertions.checkNotNull(this.subtitle);
                updateOutput(new CueGroup(this.subtitle.getCues(j), getPresentationTimeUs(getCurrentEventTimeUs(j))));
            }
            if (this.decoderReplacementState != 2) {
                while (!this.inputStreamEnded) {
                    try {
                        SubtitleInputBuffer subtitleInputBuffer = this.nextSubtitleInputBuffer;
                        if (subtitleInputBuffer == null) {
                            subtitleInputBuffer = (SubtitleInputBuffer) ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).dequeueInputBuffer();
                            if (subtitleInputBuffer != null) {
                                this.nextSubtitleInputBuffer = subtitleInputBuffer;
                            } else {
                                return;
                            }
                        }
                        if (this.decoderReplacementState == 1) {
                            subtitleInputBuffer.setFlags(4);
                            ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).queueInputBuffer(subtitleInputBuffer);
                            this.nextSubtitleInputBuffer = null;
                            this.decoderReplacementState = 2;
                            return;
                        }
                        int readSource = readSource(this.formatHolder, subtitleInputBuffer, 0);
                        if (readSource == -4) {
                            if (subtitleInputBuffer.isEndOfStream()) {
                                this.inputStreamEnded = true;
                                this.waitingForKeyFrame = false;
                            } else {
                                Format format = this.formatHolder.format;
                                if (format != null) {
                                    subtitleInputBuffer.subsampleOffsetUs = format.subsampleOffsetUs;
                                    subtitleInputBuffer.flip();
                                    this.waitingForKeyFrame &= !subtitleInputBuffer.isKeyFrame();
                                } else {
                                    return;
                                }
                            }
                            if (!this.waitingForKeyFrame) {
                                ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).queueInputBuffer(subtitleInputBuffer);
                                this.nextSubtitleInputBuffer = null;
                            }
                        } else if (readSource == -3) {
                            return;
                        }
                    } catch (SubtitleDecoderException e2) {
                        handleDecoderError(e2);
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.streamFormat = null;
        this.finalStreamEndPositionUs = C.TIME_UNSET;
        clearOutput();
        this.lastRendererPositionUs = C.TIME_UNSET;
        if (this.subtitleDecoder != null) {
            releaseSubtitleDecoder();
        }
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        if (this.streamFormat == null) {
            return true;
        }
        if (this.streamError == null) {
            try {
                maybeThrowStreamError();
            } catch (IOException e) {
                this.streamError = e;
            }
        }
        if (this.streamError != null) {
            if (isCuesWithTiming((Format) Assertions.checkNotNull(this.streamFormat))) {
                if (((CuesResolver) Assertions.checkNotNull(this.cuesResolver)).getNextCueChangeTimeUs(this.lastRendererPositionUs) != Long.MIN_VALUE) {
                    return true;
                }
                return false;
            } else if (this.outputStreamEnded || (this.inputStreamEnded && hasNoEventsAfter(this.subtitle, this.lastRendererPositionUs) && hasNoEventsAfter(this.nextSubtitle, this.lastRendererPositionUs) && this.nextSubtitleInputBuffer != null)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasNoEventsAfter(Subtitle subtitle2, long j) {
        return subtitle2 == null || subtitle2.getEventTime(subtitle2.getEventTimeCount() - 1) <= j;
    }

    private void releaseSubtitleBuffers() {
        this.nextSubtitleInputBuffer = null;
        this.nextSubtitleEventIndex = -1;
        SubtitleOutputBuffer subtitleOutputBuffer = this.subtitle;
        if (subtitleOutputBuffer != null) {
            subtitleOutputBuffer.release();
            this.subtitle = null;
        }
        SubtitleOutputBuffer subtitleOutputBuffer2 = this.nextSubtitle;
        if (subtitleOutputBuffer2 != null) {
            subtitleOutputBuffer2.release();
            this.nextSubtitle = null;
        }
    }

    private void releaseSubtitleDecoder() {
        releaseSubtitleBuffers();
        ((SubtitleDecoder) Assertions.checkNotNull(this.subtitleDecoder)).release();
        this.subtitleDecoder = null;
        this.decoderReplacementState = 0;
    }

    private void initSubtitleDecoder() {
        this.waitingForKeyFrame = true;
        SubtitleDecoder createDecoder = this.subtitleDecoderFactory.createDecoder((Format) Assertions.checkNotNull(this.streamFormat));
        this.subtitleDecoder = createDecoder;
        createDecoder.setOutputStartTimeUs(getLastResetPositionUs());
    }

    private void replaceSubtitleDecoder() {
        releaseSubtitleDecoder();
        initSubtitleDecoder();
    }

    private long getNextEventTime() {
        if (this.nextSubtitleEventIndex == -1) {
            return Long.MAX_VALUE;
        }
        Assertions.checkNotNull(this.subtitle);
        if (this.nextSubtitleEventIndex >= this.subtitle.getEventTimeCount()) {
            return Long.MAX_VALUE;
        }
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    private void updateOutput(CueGroup cueGroup) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(1, cueGroup).sendToTarget();
        } else {
            invokeUpdateOutputInternal(cueGroup);
        }
    }

    private void clearOutput() {
        updateOutput(new CueGroup(ImmutableList.of(), getPresentationTimeUs(this.lastRendererPositionUs)));
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            invokeUpdateOutputInternal((CueGroup) message.obj);
            return true;
        }
        throw new IllegalStateException();
    }

    private void invokeUpdateOutputInternal(CueGroup cueGroup) {
        this.output.onCues((List<Cue>) cueGroup.cues);
        this.output.onCues(cueGroup);
    }

    private void handleDecoderError(SubtitleDecoderException subtitleDecoderException) {
        Log.e(TAG, "Subtitle decoding failed. streamFormat=" + this.streamFormat, subtitleDecoderException);
        clearOutput();
        replaceSubtitleDecoder();
    }

    @SideEffectFree
    @RequiresNonNull({"subtitle"})
    private long getCurrentEventTimeUs(long j) {
        int nextEventTimeIndex = this.subtitle.getNextEventTimeIndex(j);
        if (nextEventTimeIndex == 0 || this.subtitle.getEventTimeCount() == 0) {
            return this.subtitle.timeUs;
        }
        if (nextEventTimeIndex != -1) {
            return this.subtitle.getEventTime(nextEventTimeIndex - 1);
        }
        SubtitleOutputBuffer subtitleOutputBuffer = this.subtitle;
        return subtitleOutputBuffer.getEventTime(subtitleOutputBuffer.getEventTimeCount() - 1);
    }

    @SideEffectFree
    private long getPresentationTimeUs(long j) {
        Assertions.checkState(j != C.TIME_UNSET);
        return j - getStreamOffsetUs();
    }

    @RequiresNonNull({"streamFormat"})
    private void assertLegacyDecodingEnabledIfRequired() {
        Assertions.checkState(this.legacyDecodingEnabled || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_CEA608) || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_MP4CEA608) || Objects.equals(this.streamFormat.sampleMimeType, MimeTypes.APPLICATION_CEA708), "Legacy decoding is disabled, can't handle " + this.streamFormat.sampleMimeType + " samples (expected application/x-media3-cues).");
    }

    @SideEffectFree
    private static boolean isCuesWithTiming(Format format) {
        return Objects.equals(format.sampleMimeType, MimeTypes.APPLICATION_MEDIA3_CUES);
    }
}
