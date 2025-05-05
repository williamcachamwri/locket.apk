package androidx.media3.extractor.ts;

import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import com.google.common.primitives.Ints;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DtsReader implements ElementaryStreamReader {
    private static final int CORE_HEADER_SIZE = 18;
    static final int EXTSS_HEADER_SIZE_MAX = 4096;
    static final int FTOC_MAX_HEADER_SIZE = 5408;
    private static final int STATE_FINDING_EXTSS_HEADER_SIZE = 2;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_FINDING_UHD_HEADER_SIZE = 4;
    private static final int STATE_READING_CORE_HEADER = 1;
    private static final int STATE_READING_EXTSS_HEADER = 3;
    private static final int STATE_READING_SAMPLE = 6;
    private static final int STATE_READING_UHD_HEADER = 5;
    private int bytesRead;
    private int extensionSubstreamHeaderSize = -1;
    private Format format;
    private String formatId;
    private int frameType;
    private final ParsableByteArray headerScratchBytes;
    private final String language;
    private TrackOutput output;
    private final int roleFlags;
    private long sampleDurationUs;
    private int sampleSize;
    private int state = 0;
    private int syncBytes;
    private long timeUs = C.TIME_UNSET;
    private final AtomicInteger uhdAudioChunkId = new AtomicInteger();
    private int uhdHeaderSize = -1;

    public void packetFinished(boolean z) {
    }

    public DtsReader(String str, int i, int i2) {
        this.headerScratchBytes = new ParsableByteArray(new byte[i2]);
        this.language = str;
        this.roleFlags = i;
    }

    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
        this.timeUs = C.TIME_UNSET;
        this.uhdAudioChunkId.set(0);
    }

    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        Assertions.checkStateNotNull(this.output);
        while (parsableByteArray.bytesLeft() > 0) {
            switch (this.state) {
                case 0:
                    if (skipToNextSyncWord(parsableByteArray)) {
                        int i = this.frameType;
                        if (i != 3 && i != 4) {
                            if (i != 1) {
                                this.state = 2;
                                break;
                            } else {
                                this.state = 1;
                                break;
                            }
                        } else {
                            this.state = 4;
                            break;
                        }
                    } else {
                        break;
                    }
                case 1:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 18)) {
                        break;
                    } else {
                        parseCoreHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, 18);
                        this.state = 6;
                        break;
                    }
                case 2:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 7)) {
                        break;
                    } else {
                        this.extensionSubstreamHeaderSize = DtsUtil.parseDtsHdHeaderSize(this.headerScratchBytes.getData());
                        this.state = 3;
                        break;
                    }
                case 3:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.extensionSubstreamHeaderSize)) {
                        break;
                    } else {
                        parseExtensionSubstreamHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.extensionSubstreamHeaderSize);
                        this.state = 6;
                        break;
                    }
                case 4:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), 6)) {
                        break;
                    } else {
                        int parseDtsUhdHeaderSize = DtsUtil.parseDtsUhdHeaderSize(this.headerScratchBytes.getData());
                        this.uhdHeaderSize = parseDtsUhdHeaderSize;
                        int i2 = this.bytesRead;
                        if (i2 > parseDtsUhdHeaderSize) {
                            int i3 = i2 - parseDtsUhdHeaderSize;
                            this.bytesRead = i2 - i3;
                            parsableByteArray.setPosition(parsableByteArray.getPosition() - i3);
                        }
                        this.state = 5;
                        break;
                    }
                case 5:
                    if (!continueRead(parsableByteArray, this.headerScratchBytes.getData(), this.uhdHeaderSize)) {
                        break;
                    } else {
                        parseUhdHeader();
                        this.headerScratchBytes.setPosition(0);
                        this.output.sampleData(this.headerScratchBytes, this.uhdHeaderSize);
                        this.state = 6;
                        break;
                    }
                case 6:
                    int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    this.output.sampleData(parsableByteArray, min);
                    int i4 = this.bytesRead + min;
                    this.bytesRead = i4;
                    if (i4 == this.sampleSize) {
                        Assertions.checkState(this.timeUs != C.TIME_UNSET);
                        this.output.sampleMetadata(this.timeUs, this.frameType == 4 ? 0 : 1, this.sampleSize, 0, (TrackOutput.CryptoData) null);
                        this.timeUs += this.sampleDurationUs;
                        this.state = 0;
                        break;
                    } else {
                        break;
                    }
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int min = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        int i2 = this.bytesRead + min;
        this.bytesRead = i2;
        return i2 == i;
    }

    private boolean skipToNextSyncWord(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.syncBytes << 8;
            this.syncBytes = i;
            int readUnsignedByte = i | parsableByteArray.readUnsignedByte();
            this.syncBytes = readUnsignedByte;
            int frameType2 = DtsUtil.getFrameType(readUnsignedByte);
            this.frameType = frameType2;
            if (frameType2 != 0) {
                byte[] data = this.headerScratchBytes.getData();
                int i2 = this.syncBytes;
                data[0] = (byte) ((i2 >> 24) & 255);
                data[1] = (byte) ((i2 >> 16) & 255);
                data[2] = (byte) ((i2 >> 8) & 255);
                data[3] = (byte) (i2 & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    @RequiresNonNull({"output"})
    private void parseCoreHeader() {
        byte[] data = this.headerScratchBytes.getData();
        if (this.format == null) {
            Format parseDtsFormat = DtsUtil.parseDtsFormat(data, this.formatId, this.language, this.roleFlags, (DrmInitData) null);
            this.format = parseDtsFormat;
            this.output.format(parseDtsFormat);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(data);
        this.sampleDurationUs = (long) Ints.checkedCast(Util.sampleCountToDurationUs((long) DtsUtil.parseDtsAudioSampleCount(data), this.format.sampleRate));
    }

    @RequiresNonNull({"output"})
    private void parseExtensionSubstreamHeader() throws ParserException {
        DtsUtil.DtsHeader parseDtsHdHeader = DtsUtil.parseDtsHdHeader(this.headerScratchBytes.getData());
        updateFormatWithDtsHeaderInfo(parseDtsHdHeader);
        this.sampleSize = parseDtsHdHeader.frameSize;
        this.sampleDurationUs = parseDtsHdHeader.frameDurationUs == C.TIME_UNSET ? 0 : parseDtsHdHeader.frameDurationUs;
    }

    @RequiresNonNull({"output"})
    private void parseUhdHeader() throws ParserException {
        DtsUtil.DtsHeader parseDtsUhdHeader = DtsUtil.parseDtsUhdHeader(this.headerScratchBytes.getData(), this.uhdAudioChunkId);
        if (this.frameType == 3) {
            updateFormatWithDtsHeaderInfo(parseDtsUhdHeader);
        }
        this.sampleSize = parseDtsUhdHeader.frameSize;
        this.sampleDurationUs = parseDtsUhdHeader.frameDurationUs == C.TIME_UNSET ? 0 : parseDtsUhdHeader.frameDurationUs;
    }

    @RequiresNonNull({"output"})
    private void updateFormatWithDtsHeaderInfo(DtsUtil.DtsHeader dtsHeader) {
        if (dtsHeader.sampleRate != -2147483647 && dtsHeader.channelCount != -1) {
            if (this.format == null || dtsHeader.channelCount != this.format.channelCount || dtsHeader.sampleRate != this.format.sampleRate || !Util.areEqual(dtsHeader.mimeType, this.format.sampleMimeType)) {
                Format format2 = this.format;
                Format build = (format2 == null ? new Format.Builder() : format2.buildUpon()).setId(this.formatId).setSampleMimeType(dtsHeader.mimeType).setChannelCount(dtsHeader.channelCount).setSampleRate(dtsHeader.sampleRate).setLanguage(this.language).setRoleFlags(this.roleFlags).build();
                this.format = build;
                this.output.format(build);
            }
        }
    }
}
