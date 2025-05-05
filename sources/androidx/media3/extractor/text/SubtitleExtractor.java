package androidx.media3.extractor.text;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubtitleExtractor implements Extractor {
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int STATE_CREATED = 0;
    private static final int STATE_EXTRACTING = 2;
    private static final int STATE_FINISHED = 4;
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_RELEASED = 5;
    private static final int STATE_SEEKING = 3;
    private int bytesRead;
    private final CueEncoder cueEncoder = new CueEncoder();
    private final Format format;
    private final List<Sample> samples;
    private final ParsableByteArray scratchSampleArray = new ParsableByteArray();
    private long seekTimeUs;
    private int state;
    private byte[] subtitleData = Util.EMPTY_BYTE_ARRAY;
    private final SubtitleParser subtitleParser;
    private long[] timestamps;
    private TrackOutput trackOutput;

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        return true;
    }

    public SubtitleExtractor(SubtitleParser subtitleParser2, Format format2) {
        this.subtitleParser = subtitleParser2;
        this.format = format2.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCodecs(format2.sampleMimeType).setCueReplacementBehavior(subtitleParser2.getCueReplacementBehavior()).build();
        this.samples = new ArrayList();
        this.state = 0;
        this.timestamps = Util.EMPTY_LONG_ARRAY;
        this.seekTimeUs = C.TIME_UNSET;
    }

    public void init(ExtractorOutput extractorOutput) {
        Assertions.checkState(this.state == 0);
        TrackOutput track = extractorOutput.track(0, 3);
        this.trackOutput = track;
        track.format(this.format);
        extractorOutput.endTracks();
        extractorOutput.seekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, C.TIME_UNSET));
        this.state = 1;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i = this.state;
        Assertions.checkState((i == 0 || i == 5) ? false : true);
        if (this.state == 1) {
            int checkedCast = extractorInput.getLength() != -1 ? Ints.checkedCast(extractorInput.getLength()) : 1024;
            if (checkedCast > this.subtitleData.length) {
                this.subtitleData = new byte[checkedCast];
            }
            this.bytesRead = 0;
            this.state = 2;
        }
        if (this.state == 2 && readFromInput(extractorInput)) {
            parseAndWriteToOutput();
            this.state = 4;
        }
        if (this.state == 3 && skipInput(extractorInput)) {
            writeToOutput();
            this.state = 4;
        }
        if (this.state == 4) {
            return -1;
        }
        return 0;
    }

    public void seek(long j, long j2) {
        int i = this.state;
        Assertions.checkState((i == 0 || i == 5) ? false : true);
        this.seekTimeUs = j2;
        if (this.state == 2) {
            this.state = 1;
        }
        if (this.state == 4) {
            this.state = 3;
        }
    }

    public void release() {
        if (this.state != 5) {
            this.subtitleParser.reset();
            this.state = 5;
        }
    }

    private boolean skipInput(ExtractorInput extractorInput) throws IOException {
        return extractorInput.skip((extractorInput.getLength() > -1 ? 1 : (extractorInput.getLength() == -1 ? 0 : -1)) != 0 ? Ints.checkedCast(extractorInput.getLength()) : 1024) == -1;
    }

    private boolean readFromInput(ExtractorInput extractorInput) throws IOException {
        byte[] bArr = this.subtitleData;
        if (bArr.length == this.bytesRead) {
            this.subtitleData = Arrays.copyOf(bArr, bArr.length + 1024);
        }
        byte[] bArr2 = this.subtitleData;
        int i = this.bytesRead;
        int read = extractorInput.read(bArr2, i, bArr2.length - i);
        if (read != -1) {
            this.bytesRead += read;
        }
        long length = extractorInput.getLength();
        return (length != -1 && ((long) this.bytesRead) == length) || read == -1;
    }

    private void parseAndWriteToOutput() throws IOException {
        SubtitleParser.OutputOptions outputOptions;
        try {
            long j = this.seekTimeUs;
            if (j != C.TIME_UNSET) {
                outputOptions = SubtitleParser.OutputOptions.cuesAfterThenRemainingCuesBefore(j);
            } else {
                outputOptions = SubtitleParser.OutputOptions.allCues();
            }
            this.subtitleParser.parse(this.subtitleData, 0, this.bytesRead, outputOptions, new SubtitleExtractor$$ExternalSyntheticLambda0(this));
            Collections.sort(this.samples);
            this.timestamps = new long[this.samples.size()];
            for (int i = 0; i < this.samples.size(); i++) {
                this.timestamps[i] = this.samples.get(i).timeUs;
            }
            this.subtitleData = Util.EMPTY_BYTE_ARRAY;
        } catch (RuntimeException e) {
            throw ParserException.createForMalformedContainer("SubtitleParser failed.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$parseAndWriteToOutput$0$androidx-media3-extractor-text-SubtitleExtractor  reason: not valid java name */
    public /* synthetic */ void m908lambda$parseAndWriteToOutput$0$androidxmedia3extractortextSubtitleExtractor(CuesWithTiming cuesWithTiming) {
        Sample sample = new Sample(cuesWithTiming.startTimeUs, this.cueEncoder.encode(cuesWithTiming.cues, cuesWithTiming.durationUs));
        this.samples.add(sample);
        if (this.seekTimeUs == C.TIME_UNSET || cuesWithTiming.startTimeUs >= this.seekTimeUs) {
            writeToOutput(sample);
        }
    }

    private void writeToOutput() {
        int i;
        long j = this.seekTimeUs;
        if (j == C.TIME_UNSET) {
            i = 0;
        } else {
            i = Util.binarySearchFloor(this.timestamps, j, true, true);
        }
        while (i < this.samples.size()) {
            writeToOutput(this.samples.get(i));
            i++;
        }
    }

    private void writeToOutput(Sample sample) {
        Assertions.checkStateNotNull(this.trackOutput);
        int length = sample.data.length;
        this.scratchSampleArray.reset(sample.data);
        this.trackOutput.sampleData(this.scratchSampleArray, length);
        this.trackOutput.sampleMetadata(sample.timeUs, 1, length, 0, (TrackOutput.CryptoData) null);
    }

    private static class Sample implements Comparable<Sample> {
        /* access modifiers changed from: private */
        public final byte[] data;
        /* access modifiers changed from: private */
        public final long timeUs;

        private Sample(long j, byte[] bArr) {
            this.timeUs = j;
            this.data = bArr;
        }

        public int compareTo(Sample sample) {
            return Long.compare(this.timeUs, sample.timeUs);
        }
    }
}
