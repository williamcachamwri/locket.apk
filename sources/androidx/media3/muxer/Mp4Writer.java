package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import com.google.common.collect.Range;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

final class Mp4Writer {
    private static final int DEFAULT_MOOV_BOX_SIZE_BYTES = 400000;
    private static final String FREE_BOX_TYPE = "free";
    private static final long INTERLEAVE_DURATION_US = 1000000;
    private static final long MOOV_BOX_UPDATE_INTERVAL_US = 1000000;
    private final AnnexBToAvccConverter annexBToAvccConverter;
    private boolean canWriteMoovAtStart;
    private final List<Track> editableVideoTracks = new ArrayList();
    private final AtomicBoolean hasWrittenSamples = new AtomicBoolean(false);
    private Range<Long> lastMoovWritten;
    private long lastMoovWrittenAtSampleTimestampUs;
    private final int lastSampleDurationBehavior;
    private long mdatDataEnd;
    private long mdatEnd;
    private long mdatStart;
    private final MetadataCollector metadataCollector;
    private final FileChannel outputFileChannel;
    private long reservedMoovSpaceEnd;
    private long reservedMoovSpaceStart;
    private final boolean sampleBatchingEnabled;
    private final boolean sampleCopyEnabled;
    private final List<Track> tracks = new ArrayList();

    public Mp4Writer(FileChannel fileChannel, MetadataCollector metadataCollector2, AnnexBToAvccConverter annexBToAvccConverter2, int i, boolean z, boolean z2, boolean z3) {
        this.outputFileChannel = fileChannel;
        this.metadataCollector = metadataCollector2;
        this.annexBToAvccConverter = annexBToAvccConverter2;
        this.lastSampleDurationBehavior = i;
        this.sampleCopyEnabled = z;
        this.sampleBatchingEnabled = z2;
        this.canWriteMoovAtStart = z3;
        this.lastMoovWritten = Range.closed(0L, 0L);
        this.lastMoovWrittenAtSampleTimestampUs = 0;
    }

    public Track addTrack(int i, Format format) {
        Track track = new Track(format, i, this.sampleCopyEnabled);
        this.tracks.add(track);
        Collections.sort(this.tracks, new Mp4Writer$$ExternalSyntheticLambda0());
        return track;
    }

    public Track addEditableVideoTrack(int i, Format format) {
        Track track = new Track(format, i, this.sampleCopyEnabled);
        this.editableVideoTracks.add(track);
        Collections.sort(this.editableVideoTracks, new Mp4Writer$$ExternalSyntheticLambda1());
        return track;
    }

    public void writeSampleData(Track track, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        track.writeSampleData(byteBuffer, bufferInfo);
        if (this.sampleBatchingEnabled) {
            doInterleave();
            return;
        }
        writePendingTrackSamples(track);
        boolean contains = this.tracks.contains(track);
        long j = bufferInfo.presentationTimeUs;
        if (contains && this.canWriteMoovAtStart && j - this.lastMoovWrittenAtSampleTimestampUs >= 1000000) {
            maybeWriteMoovAtStart();
            this.lastMoovWrittenAtSampleTimestampUs = j;
        }
    }

    public void finishWritingSamplesAndFinalizeMoovBox() throws IOException {
        for (int i = 0; i < this.tracks.size(); i++) {
            writePendingTrackSamples(this.tracks.get(i));
        }
        for (int i2 = 0; i2 < this.editableVideoTracks.size(); i2++) {
            writePendingTrackSamples(this.editableVideoTracks.get(i2));
        }
        if (this.hasWrittenSamples.get()) {
            finalizeMoovBox();
            if (!this.editableVideoTracks.isEmpty()) {
                writeEdvdBox();
            }
        }
    }

    private void writeEdvdBox() throws IOException {
        MdtaMetadataEntry editableTracksOffsetMetadata = MuxerUtil.getEditableTracksOffsetMetadata(0);
        this.metadataCollector.addMetadata(editableTracksOffsetMetadata);
        ByteBuffer edvdBox = getEdvdBox();
        this.metadataCollector.addMetadata(MuxerUtil.getEditableTracksLengthMetadata((long) edvdBox.remaining()));
        finalizeMoovBox();
        this.metadataCollector.removeMdtaMetadataEntry(editableTracksOffsetMetadata);
        this.metadataCollector.addMetadata(MuxerUtil.getEditableTracksOffsetMetadata(this.outputFileChannel.size()));
        long size = this.outputFileChannel.size();
        finalizeMoovBox();
        Assertions.checkState(size == this.outputFileChannel.size());
        FileChannel fileChannel = this.outputFileChannel;
        fileChannel.position(fileChannel.size());
        this.outputFileChannel.write(edvdBox);
    }

    private ByteBuffer getEdvdBox() {
        ByteBuffer ftyp = Boxes.ftyp();
        MetadataCollector metadataCollector2 = new MetadataCollector();
        MuxerUtil.populateEditableVideoTracksMetadata(metadataCollector2, this.metadataCollector.timestampData, true, this.editableVideoTracks);
        ByteBuffer moov = Boxes.moov(this.editableVideoTracks, metadataCollector2, false, this.lastSampleDurationBehavior);
        return BoxUtils.concatenateBuffers(Boxes.getEdvdBoxHeader((long) (ftyp.remaining() + moov.remaining())), ftyp, moov);
    }

    public void finalizeMoovBox() throws IOException {
        if (this.canWriteMoovAtStart) {
            maybeWriteMoovAtStart();
            return;
        }
        ByteBuffer assembleCurrentMoovData = assembleCurrentMoovData();
        int remaining = assembleCurrentMoovData.remaining();
        long j = (long) (remaining + 8);
        boolean z = true;
        if (this.mdatEnd - this.mdatDataEnd < j) {
            safelyReplaceMoovAtEnd(this.lastMoovWritten.upperEndpoint().longValue() + j, assembleCurrentMoovData);
            Assertions.checkState(this.mdatEnd - this.mdatDataEnd >= j);
        }
        long j2 = this.mdatDataEnd;
        this.outputFileChannel.position(j2);
        this.outputFileChannel.write(assembleCurrentMoovData);
        long j3 = ((long) remaining) + j2;
        long longValue = this.lastMoovWritten.upperEndpoint().longValue() - j3;
        if (longValue >= 2147483647L) {
            z = false;
        }
        Assertions.checkState(z);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt((int) longValue);
        allocate.put(Util.getUtf8Bytes(FREE_BOX_TYPE));
        allocate.flip();
        this.outputFileChannel.write(allocate);
        this.mdatEnd = j2;
        updateMdatSize(j2 - this.mdatStart);
        this.lastMoovWritten = Range.closed(Long.valueOf(j2), Long.valueOf(j2 + ((long) assembleCurrentMoovData.limit())));
        this.outputFileChannel.truncate(j3);
    }

    private void writeHeader() throws IOException {
        this.outputFileChannel.position(0);
        this.outputFileChannel.write(Boxes.ftyp());
        if (this.canWriteMoovAtStart) {
            this.reservedMoovSpaceStart = this.outputFileChannel.position();
            this.outputFileChannel.write(BoxUtils.wrapIntoBox(FREE_BOX_TYPE, ByteBuffer.allocate(DEFAULT_MOOV_BOX_SIZE_BYTES)));
            this.reservedMoovSpaceEnd = this.outputFileChannel.position();
        }
        this.mdatStart = this.outputFileChannel.position();
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.putInt(1);
        allocate.put(Util.getUtf8Bytes("mdat"));
        allocate.putLong(16);
        allocate.flip();
        this.outputFileChannel.write(allocate);
        long j = this.mdatStart + 16;
        this.mdatDataEnd = j;
        if (this.canWriteMoovAtStart) {
            j = Long.MAX_VALUE;
        }
        this.mdatEnd = j;
    }

    private ByteBuffer assembleCurrentMoovData() {
        return Boxes.moov(this.tracks, this.metadataCollector, false, this.lastSampleDurationBehavior);
    }

    private void safelyReplaceMoovAtEnd(long j, ByteBuffer byteBuffer) throws IOException {
        boolean z = true;
        Assertions.checkState(j >= this.lastMoovWritten.upperEndpoint().longValue());
        if (j < this.mdatEnd) {
            z = false;
        }
        Assertions.checkState(z);
        this.outputFileChannel.position(j);
        this.outputFileChannel.write(BoxUtils.wrapIntoBox(FREE_BOX_TYPE, byteBuffer.duplicate()));
        long j2 = 8 + j;
        this.mdatEnd = j2;
        updateMdatSize(j2 - this.mdatStart);
        this.lastMoovWritten = Range.closed(Long.valueOf(j), Long.valueOf(j + ((long) byteBuffer.remaining())));
    }

    private void maybeWriteMoovAtStart() throws IOException {
        ByteBuffer assembleCurrentMoovData = assembleCurrentMoovData();
        int remaining = assembleCurrentMoovData.remaining();
        long j = this.reservedMoovSpaceEnd;
        long j2 = this.reservedMoovSpaceStart;
        if (((long) (assembleCurrentMoovData.remaining() + 8)) <= j - j2) {
            this.outputFileChannel.position(j2);
            this.outputFileChannel.write(assembleCurrentMoovData);
            this.outputFileChannel.write(BoxUtils.wrapIntoBox(FREE_BOX_TYPE, ByteBuffer.allocate((int) ((this.reservedMoovSpaceEnd - this.outputFileChannel.position()) - 8))));
        } else {
            this.canWriteMoovAtStart = false;
            long j3 = this.mdatDataEnd;
            this.mdatEnd = j3;
            this.outputFileChannel.position(j3);
            this.outputFileChannel.write(assembleCurrentMoovData);
            this.lastMoovWritten = Range.closed(Long.valueOf(this.mdatEnd), Long.valueOf(this.mdatEnd + ((long) remaining)));
            this.outputFileChannel.write(BoxUtils.wrapIntoBox(FREE_BOX_TYPE, ByteBuffer.allocate((int) ((this.reservedMoovSpaceEnd - this.reservedMoovSpaceStart) - 8))), this.reservedMoovSpaceStart);
        }
        updateMdatSize(this.mdatDataEnd - this.mdatStart);
    }

    private void rewriteMoovWithMdatEmptySpace(long j) throws IOException {
        safelyReplaceMoovAtEnd(Math.max(this.mdatEnd + j, this.lastMoovWritten.upperEndpoint().longValue()), assembleCurrentMoovData());
    }

    private boolean maybeWritePendingTrackSamples(List<Track> list) throws IOException {
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            Track track = list.get(i);
            if (track.pendingSamplesBufferInfo.size() > 2 && ((MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekLast())).presentationTimeUs - ((MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekFirst())).presentationTimeUs > 1000000) {
                writePendingTrackSamples(track);
                z = true;
            }
        }
        return z;
    }

    private void writePendingTrackSamples(Track track) throws IOException {
        boolean z = false;
        Assertions.checkState(track.pendingSamplesByteBuffer.size() == track.pendingSamplesBufferInfo.size());
        if (!track.pendingSamplesBufferInfo.isEmpty()) {
            if (!this.hasWrittenSamples.getAndSet(true)) {
                writeHeader();
            }
            long j = 0;
            for (ByteBuffer limit : track.pendingSamplesByteBuffer) {
                j += (long) limit.limit();
            }
            maybeExtendMdatAndRewriteMoov(j);
            track.writtenChunkOffsets.add(Long.valueOf(this.mdatDataEnd));
            track.writtenChunkSampleCounts.add(Integer.valueOf(track.pendingSamplesBufferInfo.size()));
            do {
                MediaCodec.BufferInfo removeFirst = track.pendingSamplesBufferInfo.removeFirst();
                ByteBuffer removeFirst2 = track.pendingSamplesByteBuffer.removeFirst();
                if (AnnexBUtils.doesSampleContainAnnexBNalUnits((String) Assertions.checkNotNull(track.format.sampleMimeType))) {
                    removeFirst2 = this.annexBToAvccConverter.process(removeFirst2);
                    removeFirst.set(removeFirst2.position(), removeFirst2.remaining(), removeFirst.presentationTimeUs, removeFirst.flags);
                }
                maybeExtendMdatAndRewriteMoov((long) removeFirst2.remaining());
                long j2 = this.mdatDataEnd;
                this.mdatDataEnd = j2 + ((long) this.outputFileChannel.write(removeFirst2, j2));
                track.writtenSamples.add(removeFirst);
            } while (!track.pendingSamplesBufferInfo.isEmpty());
            if (this.mdatDataEnd <= this.mdatEnd) {
                z = true;
            }
            Assertions.checkState(z);
        }
    }

    private void maybeExtendMdatAndRewriteMoov(long j) throws IOException {
        if (!this.canWriteMoovAtStart) {
            long j2 = this.mdatDataEnd;
            if (j2 + j >= this.mdatEnd) {
                rewriteMoovWithMdatEmptySpace(getMdatExtensionAmount(j2) + j);
            }
        }
    }

    private void updateMdatSize(long j) throws IOException {
        this.outputFileChannel.position(this.mdatStart + 8);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(j);
        allocate.flip();
        this.outputFileChannel.write(allocate);
    }

    private void doInterleave() throws IOException {
        boolean maybeWritePendingTrackSamples = maybeWritePendingTrackSamples(this.tracks);
        maybeWritePendingTrackSamples(this.editableVideoTracks);
        if (maybeWritePendingTrackSamples && this.canWriteMoovAtStart) {
            maybeWriteMoovAtStart();
        }
    }

    private long getMdatExtensionAmount(long j) {
        return Math.min(C.NANOS_PER_SECOND, Math.max(500000, (long) (((float) j) * 0.2f)));
    }
}
