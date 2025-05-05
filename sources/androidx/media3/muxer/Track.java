package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.muxer.Muxer;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

final class Track implements Muxer.TrackToken {
    public long endOfStreamTimestampUs;
    public final Format format;
    public boolean hadKeyframe;
    public final Deque<MediaCodec.BufferInfo> pendingSamplesBufferInfo;
    public final Deque<ByteBuffer> pendingSamplesByteBuffer;
    private final boolean sampleCopyEnabled;
    public final int sortKey;
    public final List<Long> writtenChunkOffsets;
    public final List<Integer> writtenChunkSampleCounts;
    public final List<MediaCodec.BufferInfo> writtenSamples;

    public Track(Format format2, boolean z) {
        this(format2, 1, z);
    }

    public Track(Format format2, int i, boolean z) {
        this.format = format2;
        this.sortKey = i;
        this.sampleCopyEnabled = z;
        this.writtenSamples = new ArrayList();
        this.writtenChunkOffsets = new ArrayList();
        this.writtenChunkSampleCounts = new ArrayList();
        this.pendingSamplesBufferInfo = new ArrayDeque();
        this.pendingSamplesByteBuffer = new ArrayDeque();
        this.endOfStreamTimestampUs = C.TIME_UNSET;
    }

    public void writeSampleData(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        Assertions.checkArgument(this.endOfStreamTimestampUs == C.TIME_UNSET, "Samples can not be written after writing a sample with MediaCodec.BUFFER_FLAG_END_OF_STREAM flag");
        if (bufferInfo.size != 0 && byteBuffer.remaining() != 0) {
            if ((bufferInfo.flags & 1) > 0) {
                this.hadKeyframe = true;
            }
            if (this.hadKeyframe || !MimeTypes.isVideo(this.format.sampleMimeType)) {
                if (this.sampleCopyEnabled) {
                    ByteBuffer allocateDirect = ByteBuffer.allocateDirect(byteBuffer.remaining());
                    allocateDirect.put(byteBuffer);
                    allocateDirect.rewind();
                    MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
                    bufferInfo2.set(allocateDirect.position(), allocateDirect.remaining(), bufferInfo.presentationTimeUs, bufferInfo.flags);
                    bufferInfo = bufferInfo2;
                    byteBuffer = allocateDirect;
                }
                this.pendingSamplesBufferInfo.addLast(bufferInfo);
                this.pendingSamplesByteBuffer.addLast(byteBuffer);
            }
        } else if ((bufferInfo.flags & 4) != 0) {
            this.endOfStreamTimestampUs = bufferInfo.presentationTimeUs;
        }
    }

    public int videoUnitTimebase() {
        if (MimeTypes.isAudio(this.format.sampleMimeType)) {
            return OpusUtil.SAMPLE_RATE;
        }
        return 90000;
    }
}
