package androidx.media3.exoplayer.image;

import android.graphics.Bitmap;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.image.ImageDecoder;
import java.util.ArrayDeque;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class ImageRenderer extends BaseRenderer {
    private static final long IMAGE_PRESENTATION_WINDOW_THRESHOLD_US = 30000;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM_THEN_WAIT = 2;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 3;
    private static final String TAG = "ImageRenderer";
    private int currentTileIndex;
    private ImageDecoder decoder;
    private final ImageDecoder.Factory decoderFactory;
    private int decoderReinitializationState = 0;
    private int firstFrameState = 1;
    private final DecoderInputBuffer flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
    private ImageOutput imageOutput;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private long largestQueuedPresentationTimeUs = C.TIME_UNSET;
    private long lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
    private TileInfo nextTileInfo;
    private Bitmap outputBitmap;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo = OutputStreamInfo.UNSET;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges = new ArrayDeque<>();
    private boolean readyToOutputTiles;
    private TileInfo tileInfo;

    public String getName() {
        return TAG;
    }

    public ImageRenderer(ImageDecoder.Factory factory, ImageOutput imageOutput2) {
        super(4);
        this.decoderFactory = factory;
        this.imageOutput = getImageOutput(imageOutput2);
    }

    public int supportsFormat(Format format) {
        return this.decoderFactory.supportsFormat(format);
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (!this.outputStreamEnded) {
            if (this.inputFormat == null) {
                FormatHolder formatHolder = getFormatHolder();
                this.flagsOnlyBuffer.clear();
                int readSource = readSource(formatHolder, this.flagsOnlyBuffer, 2);
                if (readSource == -5) {
                    this.inputFormat = (Format) Assertions.checkStateNotNull(formatHolder.format);
                    initDecoder();
                } else if (readSource == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    this.outputStreamEnded = true;
                    return;
                } else {
                    return;
                }
            }
            try {
                TraceUtil.beginSection("drainAndFeedDecoder");
                while (drainOutput(j, j2)) {
                }
                while (feedInputBuffer(j)) {
                }
                TraceUtil.endSection();
            } catch (ImageDecoderException e) {
                throw createRendererException(e, (Format) null, 4003);
            }
        }
    }

    public boolean isReady() {
        int i = this.firstFrameState;
        return i == 3 || (i == 0 && this.readyToOutputTiles);
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        this.firstFrameState = z2 ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r2 >= r5) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onStreamChanged(androidx.media3.common.Format[] r5, long r6, long r8, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r10) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r4 = this;
            super.onStreamChanged(r5, r6, r8, r10)
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r5 = r4.outputStreamInfo
            long r5 = r5.streamOffsetUs
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 == 0) goto L_0x0036
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r5 = r4.pendingOutputStreamChanges
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0029
            long r5 = r4.largestQueuedPresentationTimeUs
            int r7 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r7 == 0) goto L_0x0036
            long r2 = r4.lastProcessedOutputBufferTimeUs
            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r7 == 0) goto L_0x0029
            int r5 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x0029
            goto L_0x0036
        L_0x0029:
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r5 = r4.pendingOutputStreamChanges
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r6 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            long r0 = r4.largestQueuedPresentationTimeUs
            r6.<init>(r0, r8)
            r5.add(r6)
            goto L_0x003d
        L_0x0036:
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r5 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            r5.<init>(r0, r8)
            r4.outputStreamInfo = r5
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.onStreamChanged(androidx.media3.common.Format[], long, long, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId):void");
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        lowerFirstFrameState(1);
        this.outputStreamEnded = false;
        this.inputStreamEnded = false;
        this.outputBitmap = null;
        this.tileInfo = null;
        this.nextTileInfo = null;
        this.readyToOutputTiles = false;
        this.inputBuffer = null;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.flush();
        }
        this.pendingOutputStreamChanges.clear();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.inputFormat = null;
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges.clear();
        releaseDecoderResources();
        this.imageOutput.onDisabled();
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        releaseDecoderResources();
        lowerFirstFrameState(1);
    }

    /* access modifiers changed from: protected */
    public void onRelease() {
        releaseDecoderResources();
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i != 15) {
            super.handleMessage(i, obj);
        } else {
            setImageOutput(obj instanceof ImageOutput ? (ImageOutput) obj : null);
        }
    }

    private boolean drainOutput(long j, long j2) throws ImageDecoderException, ExoPlaybackException {
        Bitmap bitmap;
        if (this.outputBitmap != null && this.tileInfo == null) {
            return false;
        }
        if (this.firstFrameState == 0 && getState() != 2) {
            return false;
        }
        if (this.outputBitmap == null) {
            Assertions.checkStateNotNull(this.decoder);
            ImageOutputBuffer dequeueOutputBuffer = this.decoder.dequeueOutputBuffer();
            if (dequeueOutputBuffer == null) {
                return false;
            }
            if (((ImageOutputBuffer) Assertions.checkStateNotNull(dequeueOutputBuffer)).isEndOfStream()) {
                if (this.decoderReinitializationState == 3) {
                    releaseDecoderResources();
                    Assertions.checkStateNotNull(this.inputFormat);
                    initDecoder();
                } else {
                    ((ImageOutputBuffer) Assertions.checkStateNotNull(dequeueOutputBuffer)).release();
                    if (this.pendingOutputStreamChanges.isEmpty()) {
                        this.outputStreamEnded = true;
                    }
                }
                return false;
            }
            Assertions.checkStateNotNull(dequeueOutputBuffer.bitmap, "Non-EOS buffer came back from the decoder without bitmap.");
            this.outputBitmap = dequeueOutputBuffer.bitmap;
            ((ImageOutputBuffer) Assertions.checkStateNotNull(dequeueOutputBuffer)).release();
        }
        if (!this.readyToOutputTiles || this.outputBitmap == null || this.tileInfo == null) {
            return false;
        }
        Assertions.checkStateNotNull(this.inputFormat);
        boolean z = ((this.inputFormat.tileCountHorizontal == 1 && this.inputFormat.tileCountVertical == 1) || this.inputFormat.tileCountHorizontal == -1 || this.inputFormat.tileCountVertical == -1) ? false : true;
        if (!this.tileInfo.hasTileBitmap()) {
            TileInfo tileInfo2 = this.tileInfo;
            if (z) {
                bitmap = cropTileFromImageGrid(tileInfo2.getTileIndex());
            } else {
                bitmap = (Bitmap) Assertions.checkStateNotNull(this.outputBitmap);
            }
            tileInfo2.setTileBitmap(bitmap);
        }
        if (!processOutputBuffer(j, j2, (Bitmap) Assertions.checkStateNotNull(this.tileInfo.getTileBitmap()), this.tileInfo.getPresentationTimeUs())) {
            return false;
        }
        onProcessedOutputBuffer(((TileInfo) Assertions.checkStateNotNull(this.tileInfo)).getPresentationTimeUs());
        this.firstFrameState = 3;
        if (!z || ((TileInfo) Assertions.checkStateNotNull(this.tileInfo)).getTileIndex() == (((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical * ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal) - 1) {
            this.outputBitmap = null;
        }
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
        return true;
    }

    private boolean shouldForceRender() {
        boolean z = getState() == 2;
        int i = this.firstFrameState;
        if (i == 0) {
            return z;
        }
        if (i == 1) {
            return true;
        }
        if (i == 3) {
            return false;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, Bitmap bitmap, long j3) throws ExoPlaybackException {
        long j4 = j3 - j;
        if (!shouldForceRender() && j4 >= 30000) {
            return false;
        }
        this.imageOutput.onImageAvailable(j3 - this.outputStreamInfo.streamOffsetUs, bitmap);
        return true;
    }

    private void onProcessedOutputBuffer(long j) {
        this.lastProcessedOutputBufferTimeUs = j;
        while (!this.pendingOutputStreamChanges.isEmpty() && j >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            this.outputStreamInfo = this.pendingOutputStreamChanges.removeFirst();
        }
    }

    private boolean feedInputBuffer(long j) throws ImageDecoderException {
        if (this.readyToOutputTiles && this.tileInfo != null) {
            return false;
        }
        FormatHolder formatHolder = getFormatHolder();
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder == null || this.decoderReinitializationState == 3 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputBuffer == null) {
            DecoderInputBuffer decoderInputBuffer = (DecoderInputBuffer) imageDecoder.dequeueInputBuffer();
            this.inputBuffer = decoderInputBuffer;
            if (decoderInputBuffer == null) {
                return false;
            }
        }
        if (this.decoderReinitializationState == 2) {
            Assertions.checkStateNotNull(this.inputBuffer);
            this.inputBuffer.setFlags(4);
            ((ImageDecoder) Assertions.checkStateNotNull(this.decoder)).queueInputBuffer(this.inputBuffer);
            this.inputBuffer = null;
            this.decoderReinitializationState = 3;
            return false;
        }
        int readSource = readSource(formatHolder, this.inputBuffer, 0);
        if (readSource == -5) {
            this.inputFormat = (Format) Assertions.checkStateNotNull(formatHolder.format);
            this.decoderReinitializationState = 2;
            return true;
        } else if (readSource == -4) {
            this.inputBuffer.flip();
            boolean z = (this.inputBuffer.data != null && this.inputBuffer.data.remaining() > 0) || ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).isEndOfStream();
            if (z) {
                ((ImageDecoder) Assertions.checkStateNotNull(this.decoder)).queueInputBuffer((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer));
                this.currentTileIndex = 0;
            }
            maybeAdvanceTileInfo(j, (DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer));
            if (((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).isEndOfStream()) {
                this.inputStreamEnded = true;
                this.inputBuffer = null;
                return false;
            }
            this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).timeUs);
            if (z) {
                this.inputBuffer = null;
            } else {
                ((DecoderInputBuffer) Assertions.checkStateNotNull(this.inputBuffer)).clear();
            }
            return !this.readyToOutputTiles;
        } else if (readSource == -3) {
            return false;
        } else {
            throw new IllegalStateException();
        }
    }

    @RequiresNonNull({"inputFormat"})
    @EnsuresNonNull({"decoder"})
    private void initDecoder() throws ExoPlaybackException {
        if (canCreateDecoderForFormat(this.inputFormat)) {
            ImageDecoder imageDecoder = this.decoder;
            if (imageDecoder != null) {
                imageDecoder.release();
            }
            this.decoder = this.decoderFactory.createImageDecoder();
            return;
        }
        throw createRendererException(new ImageDecoderException("Provided decoder factory can't create decoder for format."), this.inputFormat, PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED);
    }

    private boolean canCreateDecoderForFormat(Format format) {
        int supportsFormat = this.decoderFactory.supportsFormat(format);
        return supportsFormat == RendererCapabilities.create(4) || supportsFormat == RendererCapabilities.create(3);
    }

    private void lowerFirstFrameState(int i) {
        this.firstFrameState = Math.min(this.firstFrameState, i);
    }

    private void releaseDecoderResources() {
        this.inputBuffer = null;
        this.decoderReinitializationState = 0;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.release();
            this.decoder = null;
        }
    }

    private void setImageOutput(ImageOutput imageOutput2) {
        this.imageOutput = getImageOutput(imageOutput2);
    }

    private void maybeAdvanceTileInfo(long j, DecoderInputBuffer decoderInputBuffer) {
        boolean z = true;
        if (decoderInputBuffer.isEndOfStream()) {
            this.readyToOutputTiles = true;
            return;
        }
        TileInfo tileInfo2 = new TileInfo(this.currentTileIndex, decoderInputBuffer.timeUs);
        this.nextTileInfo = tileInfo2;
        this.currentTileIndex++;
        if (!this.readyToOutputTiles) {
            long presentationTimeUs = tileInfo2.getPresentationTimeUs();
            boolean z2 = presentationTimeUs - 30000 <= j && j <= 30000 + presentationTimeUs;
            TileInfo tileInfo3 = this.tileInfo;
            boolean z3 = tileInfo3 != null && tileInfo3.getPresentationTimeUs() <= j && j < presentationTimeUs;
            boolean isTileLastInGrid = isTileLastInGrid((TileInfo) Assertions.checkStateNotNull(this.nextTileInfo));
            if (!z2 && !z3 && !isTileLastInGrid) {
                z = false;
            }
            this.readyToOutputTiles = z;
            if (z3 && !z2) {
                return;
            }
        }
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
    }

    private boolean isTileLastInGrid(TileInfo tileInfo2) {
        if (((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal == -1 || this.inputFormat.tileCountVertical == -1 || tileInfo2.getTileIndex() == (((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical * this.inputFormat.tileCountHorizontal) - 1) {
            return true;
        }
        return false;
    }

    private Bitmap cropTileFromImageGrid(int i) {
        Assertions.checkStateNotNull(this.outputBitmap);
        int width = this.outputBitmap.getWidth() / ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountHorizontal;
        int height = this.outputBitmap.getHeight() / ((Format) Assertions.checkStateNotNull(this.inputFormat)).tileCountVertical;
        return Bitmap.createBitmap(this.outputBitmap, (i % this.inputFormat.tileCountHorizontal) * width, (i / this.inputFormat.tileCountHorizontal) * height, width, height);
    }

    private static ImageOutput getImageOutput(ImageOutput imageOutput2) {
        return imageOutput2 == null ? ImageOutput.NO_OP : imageOutput2;
    }

    private static class TileInfo {
        private final long presentationTimeUs;
        private Bitmap tileBitmap;
        private final int tileIndex;

        public TileInfo(int i, long j) {
            this.tileIndex = i;
            this.presentationTimeUs = j;
        }

        public int getTileIndex() {
            return this.tileIndex;
        }

        public long getPresentationTimeUs() {
            return this.presentationTimeUs;
        }

        public Bitmap getTileBitmap() {
            return this.tileBitmap;
        }

        public void setTileBitmap(Bitmap bitmap) {
            this.tileBitmap = bitmap;
        }

        public boolean hasTileBitmap() {
            return this.tileBitmap != null;
        }
    }

    private static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(C.TIME_UNSET, C.TIME_UNSET);
        public final long previousStreamLastBufferTimeUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long j, long j2) {
            this.previousStreamLastBufferTimeUs = j;
            this.streamOffsetUs = j2;
        }
    }
}
