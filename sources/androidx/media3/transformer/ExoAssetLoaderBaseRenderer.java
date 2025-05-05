package androidx.media3.transformer;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.MediaClock;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.transformer.AssetLoader;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

abstract class ExoAssetLoaderBaseRenderer extends BaseRenderer {
    private final AssetLoader.Listener assetLoaderListener;
    protected Codec decoder;
    private final DecoderInputBuffer decoderInputBuffer = new DecoderInputBuffer(0);
    private boolean hasPendingConsumerInput;
    private Format inputFormat;
    protected boolean isEnded;
    private boolean isRunning;
    private final TransformerMediaClock mediaClock;
    private Format outputFormat;
    protected SampleConsumer sampleConsumer;
    private boolean shouldInitDecoder;
    protected long streamStartPositionUs;

    /* access modifiers changed from: protected */
    @RequiresNonNull({"sampleConsumer", "decoder"})
    public abstract boolean feedConsumerFromDecoder() throws ExportException;

    /* access modifiers changed from: protected */
    @EnsuresNonNull({"decoder"})
    public abstract void initDecoder(Format format) throws ExportException;

    public boolean isReady() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDecoderInputReady(DecoderInputBuffer decoderInputBuffer2) {
    }

    /* access modifiers changed from: protected */
    public void onInputFormatRead(Format format) {
    }

    /* access modifiers changed from: protected */
    public Format overrideInputFormat(Format format) {
        return format;
    }

    /* access modifiers changed from: protected */
    public Format overrideOutputFormat(Format format) {
        return format;
    }

    /* access modifiers changed from: protected */
    public abstract boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer2);

    public ExoAssetLoaderBaseRenderer(int i, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener) {
        super(i);
        this.mediaClock = transformerMediaClock;
        this.assetLoaderListener = listener;
    }

    public int supportsFormat(Format format) {
        return RendererCapabilities.create(MimeTypes.getTrackType(format.sampleMimeType) == getTrackType() ? 4 : 0);
    }

    public MediaClock getMediaClock() {
        return this.mediaClock;
    }

    public boolean isEnded() {
        return this.isEnded;
    }

    public void render(long j, long j2) {
        try {
            if (this.isRunning && !isEnded()) {
                if (readInputFormatAndInitDecoderIfNeeded()) {
                    if (this.decoder != null) {
                        do {
                        } while ((ensureSampleConsumerInitialized() ? feedConsumerFromDecoder() : false) | feedDecoderFromInput());
                    } else if (ensureSampleConsumerInitialized()) {
                        do {
                        } while (feedConsumerFromInput());
                    }
                }
            }
        } catch (ExportException e) {
            this.isRunning = false;
            this.assetLoaderListener.onError(e);
        }
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        this.streamStartPositionUs = j;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) {
        this.mediaClock.updateTimeForTrackType(getTrackType(), 0);
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        this.isRunning = true;
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.isRunning = false;
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        Codec codec = this.decoder;
        if (codec != null) {
            codec.release();
        }
    }

    @EnsuresNonNullIf(expression = {"inputFormat"}, result = true)
    private boolean readInputFormatAndInitDecoderIfNeeded() throws ExportException {
        Format format = this.inputFormat;
        if (format != null && !this.shouldInitDecoder) {
            return true;
        }
        if (format == null) {
            FormatHolder formatHolder = getFormatHolder();
            if (readSource(formatHolder, this.decoderInputBuffer, 2) != -5) {
                return false;
            }
            Format overrideInputFormat = overrideInputFormat((Format) Assertions.checkNotNull(formatHolder.format));
            this.inputFormat = overrideInputFormat;
            onInputFormatRead(overrideInputFormat);
            this.shouldInitDecoder = this.assetLoaderListener.onTrackAdded(this.inputFormat, 3);
        }
        if (this.shouldInitDecoder) {
            if (TransformerUtil.getProcessedTrackType(this.inputFormat.sampleMimeType) == 2 && !ensureSampleConsumerInitialized()) {
                return false;
            }
            initDecoder(this.inputFormat);
            this.shouldInitDecoder = false;
        }
        return true;
    }

    @RequiresNonNull({"inputFormat"})
    @EnsuresNonNullIf(expression = {"sampleConsumer"}, result = true)
    private boolean ensureSampleConsumerInitialized() throws ExportException {
        if (this.sampleConsumer != null) {
            return true;
        }
        if (this.outputFormat == null) {
            if (this.decoder == null || TransformerUtil.getProcessedTrackType(this.inputFormat.sampleMimeType) != 1) {
                this.outputFormat = overrideOutputFormat(this.inputFormat);
            } else {
                Format outputFormat2 = this.decoder.getOutputFormat();
                if (outputFormat2 == null) {
                    return false;
                }
                this.outputFormat = overrideOutputFormat(outputFormat2);
            }
        }
        SampleConsumer onOutputFormat = this.assetLoaderListener.onOutputFormat(this.outputFormat);
        if (onOutputFormat == null) {
            return false;
        }
        this.sampleConsumer = onOutputFormat;
        return true;
    }

    @RequiresNonNull({"decoder"})
    private boolean feedDecoderFromInput() throws ExportException {
        if (!this.decoder.maybeDequeueInputBuffer(this.decoderInputBuffer) || !readInput(this.decoderInputBuffer)) {
            return false;
        }
        if (shouldDropInputBuffer(this.decoderInputBuffer)) {
            return true;
        }
        onDecoderInputReady(this.decoderInputBuffer);
        this.decoder.queueInputBuffer(this.decoderInputBuffer);
        return true;
    }

    @RequiresNonNull({"sampleConsumer"})
    private boolean feedConsumerFromInput() {
        DecoderInputBuffer inputBuffer = this.sampleConsumer.getInputBuffer();
        if (inputBuffer == null) {
            return false;
        }
        if (!this.hasPendingConsumerInput) {
            if (!readInput(inputBuffer)) {
                return false;
            }
            if (shouldDropInputBuffer(inputBuffer)) {
                return true;
            }
            this.hasPendingConsumerInput = true;
        }
        boolean isEndOfStream = inputBuffer.isEndOfStream();
        if (!this.sampleConsumer.queueInputBuffer()) {
            return false;
        }
        this.hasPendingConsumerInput = false;
        this.isEnded = isEndOfStream;
        return !isEndOfStream;
    }

    private boolean readInput(DecoderInputBuffer decoderInputBuffer2) {
        int readSource = readSource(getFormatHolder(), decoderInputBuffer2, 0);
        if (readSource == -5) {
            throw new IllegalStateException("Format changes are not supported.");
        } else if (readSource != -4) {
            return false;
        } else {
            decoderInputBuffer2.flip();
            if (decoderInputBuffer2.isEndOfStream()) {
                return true;
            }
            this.mediaClock.updateTimeForTrackType(getTrackType(), decoderInputBuffer2.timeUs);
            return true;
        }
    }
}
