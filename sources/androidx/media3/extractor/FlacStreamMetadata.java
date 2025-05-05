package androidx.media3.extractor;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.flac.PictureFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FlacStreamMetadata {
    public static final int NOT_IN_LOOKUP_TABLE = -1;
    private static final String TAG = "FlacStreamMetadata";
    public final int bitsPerSample;
    public final int bitsPerSampleLookupKey;
    public final int channels;
    public final int maxBlockSizeSamples;
    public final int maxFrameSize;
    private final Metadata metadata;
    public final int minBlockSizeSamples;
    public final int minFrameSize;
    public final int sampleRate;
    public final int sampleRateLookupKey;
    public final SeekTable seekTable;
    public final long totalSamples;

    private static int getBitsPerSampleLookupKey(int i) {
        if (i == 8) {
            return 1;
        }
        if (i == 12) {
            return 2;
        }
        if (i == 16) {
            return 4;
        }
        if (i != 20) {
            return i != 24 ? -1 : 6;
        }
        return 5;
    }

    private static int getSampleRateLookupKey(int i) {
        switch (i) {
            case 8000:
                return 4;
            case AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case OpusUtil.SAMPLE_RATE /*48000*/:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND:
                return 3;
            default:
                return -1;
        }
    }

    public static class SeekTable {
        public final long[] pointOffsets;
        public final long[] pointSampleNumbers;

        public SeekTable(long[] jArr, long[] jArr2) {
            this.pointSampleNumbers = jArr;
            this.pointOffsets = jArr2;
        }
    }

    public FlacStreamMetadata(byte[] bArr, int i) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i * 8);
        this.minBlockSizeSamples = parsableBitArray.readBits(16);
        this.maxBlockSizeSamples = parsableBitArray.readBits(16);
        this.minFrameSize = parsableBitArray.readBits(24);
        this.maxFrameSize = parsableBitArray.readBits(24);
        int readBits = parsableBitArray.readBits(20);
        this.sampleRate = readBits;
        this.sampleRateLookupKey = getSampleRateLookupKey(readBits);
        this.channels = parsableBitArray.readBits(3) + 1;
        int readBits2 = parsableBitArray.readBits(5) + 1;
        this.bitsPerSample = readBits2;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(readBits2);
        this.totalSamples = parsableBitArray.readBitsToLong(36);
        this.seekTable = null;
        this.metadata = null;
    }

    public FlacStreamMetadata(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, ArrayList<String> arrayList, ArrayList<PictureFrame> arrayList2) {
        this(i, i2, i3, i4, i5, i6, i7, j, (SeekTable) null, concatenateVorbisMetadata(arrayList, arrayList2));
    }

    private FlacStreamMetadata(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, SeekTable seekTable2, Metadata metadata2) {
        this.minBlockSizeSamples = i;
        this.maxBlockSizeSamples = i2;
        this.minFrameSize = i3;
        this.maxFrameSize = i4;
        this.sampleRate = i5;
        this.sampleRateLookupKey = getSampleRateLookupKey(i5);
        this.channels = i6;
        this.bitsPerSample = i7;
        this.bitsPerSampleLookupKey = getBitsPerSampleLookupKey(i7);
        this.totalSamples = j;
        this.seekTable = seekTable2;
        this.metadata = metadata2;
    }

    public int getMaxDecodedFrameSize() {
        return this.maxBlockSizeSamples * this.channels * (this.bitsPerSample / 8);
    }

    public int getDecodedBitrate() {
        return this.bitsPerSample * this.sampleRate * this.channels;
    }

    public long getDurationUs() {
        long j = this.totalSamples;
        return j == 0 ? C.TIME_UNSET : (j * 1000000) / ((long) this.sampleRate);
    }

    public long getSampleNumber(long j) {
        return Util.constrainValue((j * ((long) this.sampleRate)) / 1000000, 0, this.totalSamples - 1);
    }

    public long getApproxBytesPerFrame() {
        long j;
        long j2;
        int i = this.maxFrameSize;
        if (i > 0) {
            j = (((long) i) + ((long) this.minFrameSize)) / 2;
            j2 = 1;
        } else {
            int i2 = this.minBlockSizeSamples;
            j = ((((i2 != this.maxBlockSizeSamples || i2 <= 0) ? 4096 : (long) i2) * ((long) this.channels)) * ((long) this.bitsPerSample)) / 8;
            j2 = 64;
        }
        return j + j2;
    }

    public Format getFormat(byte[] bArr, Metadata metadata2) {
        bArr[4] = Byte.MIN_VALUE;
        int i = this.maxFrameSize;
        if (i <= 0) {
            i = -1;
        }
        return new Format.Builder().setSampleMimeType(MimeTypes.AUDIO_FLAC).setMaxInputSize(i).setChannelCount(this.channels).setSampleRate(this.sampleRate).setPcmEncoding(Util.getPcmEncoding(this.bitsPerSample)).setInitializationData(Collections.singletonList(bArr)).setMetadata(getMetadataCopyWithAppendedEntriesFrom(metadata2)).build();
    }

    public Metadata getMetadataCopyWithAppendedEntriesFrom(Metadata metadata2) {
        Metadata metadata3 = this.metadata;
        return metadata3 == null ? metadata2 : metadata3.copyWithAppendedEntriesFrom(metadata2);
    }

    public FlacStreamMetadata copyWithSeekTable(SeekTable seekTable2) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, seekTable2, this.metadata);
    }

    public FlacStreamMetadata copyWithVorbisComments(List<String> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(VorbisUtil.parseVorbisComments(list)));
    }

    public FlacStreamMetadata copyWithPictureFrames(List<PictureFrame> list) {
        return new FlacStreamMetadata(this.minBlockSizeSamples, this.maxBlockSizeSamples, this.minFrameSize, this.maxFrameSize, this.sampleRate, this.channels, this.bitsPerSample, this.totalSamples, this.seekTable, getMetadataCopyWithAppendedEntriesFrom(new Metadata((List<? extends Metadata.Entry>) list)));
    }

    private static Metadata concatenateVorbisMetadata(List<String> list, List<PictureFrame> list2) {
        Metadata parseVorbisComments = VorbisUtil.parseVorbisComments(list);
        if (parseVorbisComments != null || !list2.isEmpty()) {
            return new Metadata((List<? extends Metadata.Entry>) list2).copyWithAppendedEntriesFrom(parseVorbisComments);
        }
        return null;
    }
}
