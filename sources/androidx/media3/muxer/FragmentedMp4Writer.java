package androidx.media3.muxer;

import android.media.MediaCodec;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.muxer.Muxer;
import com.google.common.collect.ImmutableList;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

final class FragmentedMp4Writer {
    private final AnnexBToAvccConverter annexBToAvccConverter;
    private int currentFragmentSequenceNumber = 1;
    private final long fragmentDurationUs;
    private boolean headerCreated;
    private final int lastSampleDurationBehavior = 1;
    private long maxTrackDurationUs;
    private final MetadataCollector metadataCollector;
    private long minInputPresentationTimeUs = Long.MAX_VALUE;
    private final FileChannel output;
    private final FileOutputStream outputStream;
    private final boolean sampleCopyEnabled;
    private final List<Track> tracks = new ArrayList();
    private Track videoTrack;

    public static class SampleMetadata {
        public final int compositionTimeOffsetVu;
        public final int durationVu;
        public final int flags;
        public final int size;

        public SampleMetadata(int i, int i2, int i3, int i4) {
            this.durationVu = i;
            this.size = i2;
            this.flags = i3;
            this.compositionTimeOffsetVu = i4;
        }
    }

    public FragmentedMp4Writer(FileOutputStream fileOutputStream, MetadataCollector metadataCollector2, AnnexBToAvccConverter annexBToAvccConverter2, long j, boolean z) {
        this.outputStream = fileOutputStream;
        this.output = fileOutputStream.getChannel();
        this.metadataCollector = metadataCollector2;
        this.annexBToAvccConverter = annexBToAvccConverter2;
        this.fragmentDurationUs = j * 1000;
        this.sampleCopyEnabled = z;
    }

    public Muxer.TrackToken addTrack(int i, Format format) {
        Track track = new Track(format, this.sampleCopyEnabled);
        this.tracks.add(track);
        if (MimeTypes.isVideo(format.sampleMimeType)) {
            this.videoTrack = track;
        }
        return track;
    }

    public void writeSampleData(Muxer.TrackToken trackToken, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException {
        Assertions.checkArgument(trackToken instanceof Track);
        if (!this.headerCreated) {
            createHeader();
            this.headerCreated = true;
        }
        Track track = (Track) trackToken;
        if (shouldFlushPendingSamples(track, bufferInfo)) {
            createFragment();
        }
        track.writeSampleData(byteBuffer, bufferInfo);
        MediaCodec.BufferInfo bufferInfo2 = (MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekFirst());
        this.minInputPresentationTimeUs = Math.min(this.minInputPresentationTimeUs, bufferInfo2.presentationTimeUs);
        this.maxTrackDurationUs = Math.max(this.maxTrackDurationUs, ((MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekLast())).presentationTimeUs - bufferInfo2.presentationTimeUs);
    }

    public void close() throws IOException {
        try {
            createFragment();
        } finally {
            this.output.close();
            this.outputStream.close();
        }
    }

    private static ImmutableList<ByteBuffer> createTrafBoxes(List<ProcessedTrackInfo> list, long j) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        int calculateMoofBoxSize = calculateMoofBoxSize(list) + 8;
        for (int i = 0; i < list.size(); i++) {
            ProcessedTrackInfo processedTrackInfo = list.get(i);
            builder.add((Object) Boxes.traf(Boxes.tfhd(processedTrackInfo.trackId, j), Boxes.trun(processedTrackInfo.pendingSamplesMetadata, calculateMoofBoxSize, processedTrackInfo.hasBFrame)));
            calculateMoofBoxSize += processedTrackInfo.totalSamplesSize;
        }
        return builder.build();
    }

    private static int calculateMoofBoxSize(List<ProcessedTrackInfo> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ProcessedTrackInfo processedTrackInfo = list.get(i2);
            i += 32 + 8 + Boxes.getTrunBoxContentSize(processedTrackInfo.pendingSamplesMetadata.size(), processedTrackInfo.hasBFrame);
        }
        return 24 + i;
    }

    private void createHeader() throws IOException {
        this.output.position(0);
        this.output.write(Boxes.ftyp());
        this.output.write(Boxes.moov(this.tracks, this.metadataCollector, true, this.lastSampleDurationBehavior));
    }

    private boolean shouldFlushPendingSamples(Track track, MediaCodec.BufferInfo bufferInfo) {
        Track track2 = this.videoTrack;
        if (track2 != null) {
            if (!track.equals(track2) || !track.hadKeyframe || (bufferInfo.flags & 1) <= 0) {
                return false;
            }
            if (((MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekLast())).presentationTimeUs - ((MediaCodec.BufferInfo) Assertions.checkNotNull(track.pendingSamplesBufferInfo.peekFirst())).presentationTimeUs >= this.fragmentDurationUs) {
                return true;
            }
            return false;
        } else if (this.maxTrackDurationUs >= this.fragmentDurationUs) {
            return true;
        } else {
            return false;
        }
    }

    private void createFragment() throws IOException {
        ImmutableList<ProcessedTrackInfo> processAllTracks = processAllTracks();
        ImmutableList<ByteBuffer> createTrafBoxes = createTrafBoxes(processAllTracks, this.output.position());
        if (!createTrafBoxes.isEmpty()) {
            this.output.write(Boxes.moof(Boxes.mfhd(this.currentFragmentSequenceNumber), createTrafBoxes));
            writeMdatBox(processAllTracks);
            this.currentFragmentSequenceNumber++;
        }
    }

    private void writeMdatBox(List<ProcessedTrackInfo> list) throws IOException {
        long position = this.output.position();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putInt(8);
        allocate.put(Util.getUtf8Bytes("mdat"));
        allocate.flip();
        this.output.write(allocate);
        long j = 0;
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            ProcessedTrackInfo processedTrackInfo = list.get(i);
            for (int i2 = 0; i2 < processedTrackInfo.pendingSamplesByteBuffer.size(); i2++) {
                j += (long) this.output.write((ByteBuffer) processedTrackInfo.pendingSamplesByteBuffer.get(i2));
            }
        }
        long position2 = this.output.position();
        this.output.position(position);
        ByteBuffer allocate2 = ByteBuffer.allocate(4);
        long j2 = j + ((long) 8);
        if (j2 <= MuxerUtil.UNSIGNED_INT_MAX_VALUE) {
            z = true;
        }
        Assertions.checkArgument(z, "Only 32-bit long mdat size supported in the fragmented MP4");
        allocate2.putInt((int) j2);
        allocate2.flip();
        this.output.write(allocate2);
        this.output.position(position2);
    }

    private ImmutableList<ProcessedTrackInfo> processAllTracks() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < this.tracks.size(); i++) {
            if (!this.tracks.get(i).pendingSamplesBufferInfo.isEmpty()) {
                builder.add((Object) processTrack(i + 1, this.tracks.get(i)));
            }
        }
        return builder.build();
    }

    private ProcessedTrackInfo processTrack(int i, Track track) {
        Assertions.checkState(track.pendingSamplesByteBuffer.size() == track.pendingSamplesBufferInfo.size());
        ImmutableList.Builder builder = new ImmutableList.Builder();
        ImmutableList.Builder builder2 = new ImmutableList.Builder();
        if (AnnexBUtils.doesSampleContainAnnexBNalUnits((String) Assertions.checkNotNull(track.format.sampleMimeType))) {
            while (!track.pendingSamplesByteBuffer.isEmpty()) {
                ByteBuffer process = this.annexBToAvccConverter.process(track.pendingSamplesByteBuffer.removeFirst());
                builder.add((Object) process);
                MediaCodec.BufferInfo removeFirst = track.pendingSamplesBufferInfo.removeFirst();
                removeFirst.set(process.position(), process.remaining(), removeFirst.presentationTimeUs, removeFirst.flags);
                builder2.add((Object) removeFirst);
            }
        } else {
            builder.addAll((Iterable) track.pendingSamplesByteBuffer);
            track.pendingSamplesByteBuffer.clear();
            builder2.addAll((Iterable) track.pendingSamplesBufferInfo);
            track.pendingSamplesBufferInfo.clear();
        }
        ImmutableList build = builder2.build();
        List<Integer> convertPresentationTimestampsToDurationsVu = Boxes.convertPresentationTimestampsToDurationsVu(build, track.videoUnitTimebase(), 1, track.endOfStreamTimestampUs);
        List<Integer> calculateSampleCompositionTimeOffsets = Boxes.calculateSampleCompositionTimeOffsets(build, convertPresentationTimestampsToDurationsVu, track.videoUnitTimebase());
        boolean z = !calculateSampleCompositionTimeOffsets.isEmpty();
        ImmutableList.Builder builder3 = new ImmutableList.Builder();
        int i2 = 0;
        for (int i3 = 0; i3 < build.size(); i3++) {
            i2 += ((MediaCodec.BufferInfo) build.get(i3)).size;
            builder3.add((Object) new SampleMetadata(convertPresentationTimestampsToDurationsVu.get(i3).intValue(), ((MediaCodec.BufferInfo) build.get(i3)).size, ((MediaCodec.BufferInfo) build.get(i3)).flags, z ? calculateSampleCompositionTimeOffsets.get(i3).intValue() : 0));
        }
        return new ProcessedTrackInfo(i, i2, z, builder.build(), builder3.build());
    }

    private static class ProcessedTrackInfo {
        public final boolean hasBFrame;
        public final ImmutableList<ByteBuffer> pendingSamplesByteBuffer;
        public final ImmutableList<SampleMetadata> pendingSamplesMetadata;
        public final int totalSamplesSize;
        public final int trackId;

        public ProcessedTrackInfo(int i, int i2, boolean z, ImmutableList<ByteBuffer> immutableList, ImmutableList<SampleMetadata> immutableList2) {
            this.trackId = i;
            this.totalSamplesSize = i2;
            this.hasBFrame = z;
            this.pendingSamplesByteBuffer = immutableList;
            this.pendingSamplesMetadata = immutableList2;
        }
    }
}
