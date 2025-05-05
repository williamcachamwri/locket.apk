package androidx.media3.transformer;

import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.muxer.Muxer;
import androidx.media3.transformer.MuxerWrapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.nio.ByteBuffer;
import java.util.List;

abstract class SampleExporter {
    private final Metadata metadata;
    private final MuxerWrapper muxerWrapper;
    private boolean muxerWrapperTrackAdded;
    private final int outputTrackType;

    public abstract GraphInput getInput(EditedMediaItem editedMediaItem, Format format, int i) throws ExportException;

    /* access modifiers changed from: protected */
    public abstract DecoderInputBuffer getMuxerInputBuffer() throws ExportException;

    /* access modifiers changed from: protected */
    public abstract Format getMuxerInputFormat() throws ExportException;

    /* access modifiers changed from: protected */
    public abstract boolean isMuxerInputEnded();

    /* access modifiers changed from: protected */
    public boolean processDataUpToMuxer() throws ExportException {
        return false;
    }

    public abstract void release();

    /* access modifiers changed from: protected */
    public abstract void releaseMuxerInputBuffer() throws ExportException;

    public SampleExporter(Format format, MuxerWrapper muxerWrapper2) {
        this.muxerWrapper = muxerWrapper2;
        this.metadata = format.metadata;
        this.outputTrackType = TransformerUtil.getProcessedTrackType(format.sampleMimeType);
    }

    public final boolean processData() throws ExportException {
        return feedMuxer() || (!isMuxerInputEnded() && processDataUpToMuxer());
    }

    private boolean feedMuxer() throws ExportException {
        if (!this.muxerWrapperTrackAdded) {
            Format muxerInputFormat = getMuxerInputFormat();
            if (muxerInputFormat == null) {
                return false;
            }
            if (this.metadata != null) {
                muxerInputFormat = muxerInputFormat.buildUpon().setMetadata(this.metadata).build();
            }
            try {
                this.muxerWrapper.addTrackFormat(muxerInputFormat);
                this.muxerWrapperTrackAdded = true;
            } catch (Muxer.MuxerException e) {
                throw ExportException.createForMuxer(e, 7001);
            } catch (MuxerWrapper.AppendTrackFormatException e2) {
                throw ExportException.createForMuxer(e2, ExportException.ERROR_CODE_MUXING_APPEND);
            }
        }
        if (isMuxerInputEnded()) {
            this.muxerWrapper.endTrack(this.outputTrackType);
            return false;
        }
        DecoderInputBuffer muxerInputBuffer = getMuxerInputBuffer();
        if (muxerInputBuffer == null) {
            return false;
        }
        try {
            if (!this.muxerWrapper.writeSample(this.outputTrackType, (ByteBuffer) Assertions.checkStateNotNull(muxerInputBuffer.data), muxerInputBuffer.isKeyFrame(), muxerInputBuffer.timeUs)) {
                return false;
            }
            releaseMuxerInputBuffer();
            return true;
        } catch (Muxer.MuxerException e3) {
            throw ExportException.createForMuxer(e3, 7001);
        }
    }

    protected static String findSupportedMimeTypeForEncoderAndMuxer(Format format, List<String> list) {
        boolean isVideo = MimeTypes.isVideo((String) Assertions.checkNotNull(format.sampleMimeType));
        ImmutableSet.Builder add = new ImmutableSet.Builder().add((Object) format.sampleMimeType);
        if (isVideo) {
            add.add((Object) MimeTypes.VIDEO_H265).add((Object) MimeTypes.VIDEO_H264);
        }
        add.addAll((Iterable) list);
        ImmutableList asList = add.build().asList();
        for (int i = 0; i < asList.size(); i++) {
            String str = (String) asList.get(i);
            if (list.contains(str)) {
                if (!isVideo || !ColorInfo.isTransferHdr(format.colorInfo)) {
                    if (!EncoderUtil.getSupportedEncoders(str).isEmpty()) {
                        return str;
                    }
                } else if (!EncoderUtil.getSupportedEncodersForHdrEditing(str, format.colorInfo).isEmpty()) {
                    return str;
                }
            }
        }
        return null;
    }
}
