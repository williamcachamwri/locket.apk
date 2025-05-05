package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.EOFException;
import java.io.IOException;

final class SubtitleTranscodingTrackOutput implements TrackOutput {
    private final CueEncoder cueEncoder = new CueEncoder();
    private Format currentFormat;
    private SubtitleParser currentSubtitleParser;
    private final TrackOutput delegate;
    private final ParsableByteArray parsableScratch = new ParsableByteArray();
    private byte[] sampleData = Util.EMPTY_BYTE_ARRAY;
    private int sampleDataEnd = 0;
    private int sampleDataStart = 0;
    private final SubtitleParser.Factory subtitleParserFactory;

    public SubtitleTranscodingTrackOutput(TrackOutput trackOutput, SubtitleParser.Factory factory) {
        this.delegate = trackOutput;
        this.subtitleParserFactory = factory;
    }

    public void resetSubtitleParser() {
        SubtitleParser subtitleParser = this.currentSubtitleParser;
        if (subtitleParser != null) {
            subtitleParser.reset();
        }
    }

    public void format(Format format) {
        Assertions.checkNotNull(format.sampleMimeType);
        Assertions.checkArgument(MimeTypes.getTrackType(format.sampleMimeType) == 3);
        if (!format.equals(this.currentFormat)) {
            this.currentFormat = format;
            this.currentSubtitleParser = this.subtitleParserFactory.supportsFormat(format) ? this.subtitleParserFactory.create(format) : null;
        }
        if (this.currentSubtitleParser == null) {
            this.delegate.format(format);
        } else {
            this.delegate.format(format.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCodecs(format.sampleMimeType).setSubsampleOffsetUs(Long.MAX_VALUE).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(format)).build());
        }
    }

    public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
        if (this.currentSubtitleParser == null) {
            return this.delegate.sampleData(dataReader, i, z, i2);
        }
        ensureSampleDataCapacity(i);
        int read = dataReader.read(this.sampleData, this.sampleDataEnd, i);
        if (read != -1) {
            this.sampleDataEnd += read;
            return read;
        } else if (z) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleData(parsableByteArray, i, i2);
            return;
        }
        ensureSampleDataCapacity(i);
        parsableByteArray.readBytes(this.sampleData, this.sampleDataEnd, i);
        this.sampleDataEnd += i;
    }

    public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
        if (this.currentSubtitleParser == null) {
            this.delegate.sampleMetadata(j, i, i2, i3, cryptoData);
            return;
        }
        Assertions.checkArgument(cryptoData == null, "DRM on subtitles is not supported");
        int i4 = (this.sampleDataEnd - i3) - i2;
        this.currentSubtitleParser.parse(this.sampleData, i4, i2, SubtitleParser.OutputOptions.allCues(), new SubtitleTranscodingTrackOutput$$ExternalSyntheticLambda0(this, j, i));
        int i5 = i4 + i2;
        this.sampleDataStart = i5;
        if (i5 == this.sampleDataEnd) {
            this.sampleDataStart = 0;
            this.sampleDataEnd = 0;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: outputSample */
    public void m909lambda$sampleMetadata$0$androidxmedia3extractortextSubtitleTranscodingTrackOutput(CuesWithTiming cuesWithTiming, long j, int i) {
        Assertions.checkStateNotNull(this.currentFormat);
        byte[] encode = this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs);
        this.parsableScratch.reset(encode);
        this.delegate.sampleData(this.parsableScratch, encode.length);
        if (cuesWithTiming.startTimeUs == C.TIME_UNSET) {
            Assertions.checkState(this.currentFormat.subsampleOffsetUs == Long.MAX_VALUE);
        } else if (this.currentFormat.subsampleOffsetUs == Long.MAX_VALUE) {
            j += cuesWithTiming.startTimeUs;
        } else {
            j = cuesWithTiming.startTimeUs + this.currentFormat.subsampleOffsetUs;
        }
        this.delegate.sampleMetadata(j, i, encode.length, 0, (TrackOutput.CryptoData) null);
    }

    private void ensureSampleDataCapacity(int i) {
        int length = this.sampleData.length;
        int i2 = this.sampleDataEnd;
        if (length - i2 < i) {
            int i3 = i2 - this.sampleDataStart;
            int max = Math.max(i3 * 2, i + i3);
            byte[] bArr = this.sampleData;
            byte[] bArr2 = max <= bArr.length ? bArr : new byte[max];
            System.arraycopy(bArr, this.sampleDataStart, bArr2, 0, i3);
            this.sampleDataStart = 0;
            this.sampleDataEnd = i3;
            this.sampleData = bArr2;
        }
    }
}
