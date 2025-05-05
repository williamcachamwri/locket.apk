package androidx.media3.transformer;

import android.media.MediaCodec;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.transformer.AssetLoader;
import androidx.media3.transformer.Codec;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

final class ExoAssetLoaderVideoRenderer extends ExoAssetLoaderBaseRenderer {
    private static final String TAG = "ExoAssetLoaderVideoRenderer";
    private final List<Long> decodeOnlyPresentationTimestamps = new ArrayList();
    private final Codec.DecoderFactory decoderFactory;
    private final boolean flattenForSlowMotion;
    private final int hdrMode;
    private int maxDecoderPendingFrameCount = -1;
    private SefSlowMotionFlattener sefVideoSlowMotionFlattener;

    public String getName() {
        return TAG;
    }

    public ExoAssetLoaderVideoRenderer(boolean z, Codec.DecoderFactory decoderFactory2, int i, TransformerMediaClock transformerMediaClock, AssetLoader.Listener listener) {
        super(2, transformerMediaClock, listener);
        this.flattenForSlowMotion = z;
        this.decoderFactory = decoderFactory2;
        this.hdrMode = i;
    }

    public long getDurationToProgressUs(long j, long j2) {
        int i = this.maxDecoderPendingFrameCount;
        if (i == -1) {
            return 10000;
        }
        return ((long) i) * 2000;
    }

    /* access modifiers changed from: protected */
    public Format overrideInputFormat(Format format) {
        return (this.hdrMode != 3 || !ColorInfo.isTransferHdr(format.colorInfo)) ? format : format.buildUpon().setColorInfo(ColorInfo.SDR_BT709_LIMITED).build();
    }

    /* access modifiers changed from: protected */
    public Format overrideOutputFormat(Format format) {
        ColorInfo validColor = TransformerUtil.getValidColor(format.colorInfo);
        boolean z = true;
        if (this.hdrMode != 1) {
            z = false;
        }
        return format.buildUpon().setColorInfo(TransformerUtil.getDecoderOutputColor(validColor, z)).build();
    }

    /* access modifiers changed from: protected */
    public void onInputFormatRead(Format format) {
        if (this.flattenForSlowMotion) {
            this.sefVideoSlowMotionFlattener = new SefSlowMotionFlattener(format);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r3.hdrMode == 1) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initDecoder(androidx.media3.common.Format r4) throws androidx.media3.transformer.ExportException {
        /*
            r3 = this;
            androidx.media3.transformer.SampleConsumer r0 = r3.sampleConsumer
            androidx.media3.common.util.Assertions.checkStateNotNull(r0)
            androidx.media3.common.ColorInfo r0 = r4.colorInfo
            boolean r0 = androidx.media3.common.ColorInfo.isTransferHdr(r0)
            if (r0 == 0) goto L_0x0013
            int r0 = r3.hdrMode
            r1 = 1
            if (r0 != r1) goto L_0x0013
            goto L_0x0014
        L_0x0013:
            r1 = 0
        L_0x0014:
            androidx.media3.transformer.Codec$DecoderFactory r0 = r3.decoderFactory
            androidx.media3.transformer.SampleConsumer r2 = r3.sampleConsumer
            android.view.Surface r2 = r2.getInputSurface()
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            android.view.Surface r2 = (android.view.Surface) r2
            androidx.media3.transformer.Codec r4 = r0.createForVideoDecoding(r4, r2, r1)
            r3.decoder = r4
            androidx.media3.transformer.Codec r4 = r3.decoder
            int r4 = r4.getMaxPendingFrameCount()
            r3.maxDecoderPendingFrameCount = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.ExoAssetLoaderVideoRenderer.initDecoder(androidx.media3.common.Format):void");
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.isEndOfStream()) {
            return false;
        }
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.data);
        if (this.sefVideoSlowMotionFlattener != null) {
            long streamOffsetUs = getStreamOffsetUs();
            if (this.sefVideoSlowMotionFlattener.dropOrTransformSample(byteBuffer, decoderInputBuffer.timeUs - streamOffsetUs)) {
                byteBuffer.clear();
                return true;
            }
            decoderInputBuffer.timeUs = streamOffsetUs + this.sefVideoSlowMotionFlattener.getSamplePresentationTimeUs();
        }
        if (this.decoder == null) {
            decoderInputBuffer.timeUs -= this.streamStartPositionUs;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDecoderInputReady(DecoderInputBuffer decoderInputBuffer) {
        if (decoderInputBuffer.timeUs < getLastResetPositionUs()) {
            this.decodeOnlyPresentationTimestamps.add(Long.valueOf(decoderInputBuffer.timeUs));
        }
    }

    /* access modifiers changed from: protected */
    @RequiresNonNull({"sampleConsumer", "decoder"})
    public boolean feedConsumerFromDecoder() throws ExportException {
        if (this.decoder.isEnded()) {
            this.sampleConsumer.signalEndOfVideoInput();
            this.isEnded = true;
            return false;
        }
        MediaCodec.BufferInfo outputBufferInfo = this.decoder.getOutputBufferInfo();
        if (outputBufferInfo == null) {
            return false;
        }
        long j = outputBufferInfo.presentationTimeUs - this.streamStartPositionUs;
        if (j < 0 || isDecodeOnlyBuffer(outputBufferInfo.presentationTimeUs)) {
            this.decoder.releaseOutputBuffer(false);
            return true;
        } else if (this.sampleConsumer.getPendingVideoFrameCount() == this.maxDecoderPendingFrameCount || !this.sampleConsumer.registerVideoFrame(j)) {
            return false;
        } else {
            this.decoder.releaseOutputBuffer(j);
            return true;
        }
    }

    private boolean isDecodeOnlyBuffer(long j) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i = 0; i < size; i++) {
            if (this.decodeOnlyPresentationTimestamps.get(i).longValue() == j) {
                this.decodeOnlyPresentationTimestamps.remove(i);
                return true;
            }
        }
        return false;
    }
}
