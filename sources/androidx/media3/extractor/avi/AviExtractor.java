package androidx.media3.extractor.avi;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.NoOpExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

public final class AviExtractor implements Extractor {
    private static final int AVIIF_KEYFRAME = 16;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 1;
    public static final int FOURCC_AVI_ = 541677121;
    public static final int FOURCC_JUNK = 1263424842;
    public static final int FOURCC_LIST = 1414744396;
    public static final int FOURCC_RIFF = 1179011410;
    public static final int FOURCC_auds = 1935963489;
    public static final int FOURCC_avih = 1751742049;
    public static final int FOURCC_hdrl = 1819436136;
    public static final int FOURCC_idx1 = 829973609;
    public static final int FOURCC_movi = 1769369453;
    public static final int FOURCC_strf = 1718776947;
    public static final int FOURCC_strh = 1752331379;
    public static final int FOURCC_strl = 1819440243;
    public static final int FOURCC_strn = 1852994675;
    public static final int FOURCC_txts = 1937012852;
    public static final int FOURCC_vids = 1935960438;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_FINDING_IDX1_HEADER = 4;
    private static final int STATE_FINDING_MOVI_HEADER = 3;
    private static final int STATE_READING_HDRL_BODY = 2;
    private static final int STATE_READING_HDRL_HEADER = 1;
    private static final int STATE_READING_IDX1_BODY = 5;
    private static final int STATE_READING_SAMPLES = 6;
    private static final int STATE_SKIPPING_TO_HDRL = 0;
    private static final String TAG = "AviExtractor";
    private AviMainHeaderChunk aviHeader;
    private final ChunkHeaderHolder chunkHeaderHolder;
    /* access modifiers changed from: private */
    public ChunkReader[] chunkReaders;
    private ChunkReader currentChunkReader;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int hdrlSize;
    private int idx1BodySize;
    private long moviEnd;
    private long moviStart;
    private final boolean parseSubtitlesDuringExtraction;
    private long pendingReposition;
    private final ParsableByteArray scratch;
    private boolean seekMapHasBeenOutput;
    private int state;
    private final SubtitleParser.Factory subtitleParserFactory;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public void release() {
    }

    @Deprecated
    public AviExtractor() {
        this(1, SubtitleParser.Factory.UNSUPPORTED);
    }

    public AviExtractor(int i, SubtitleParser.Factory factory) {
        this.subtitleParserFactory = factory;
        this.parseSubtitlesDuringExtraction = (i & 1) != 0 ? false : true;
        this.scratch = new ParsableByteArray(12);
        this.chunkHeaderHolder = new ChunkHeaderHolder();
        this.extractorOutput = new NoOpExtractorOutput();
        this.chunkReaders = new ChunkReader[0];
        this.moviStart = -1;
        this.moviEnd = -1;
        this.hdrlSize = -1;
        this.durationUs = C.TIME_UNSET;
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.state = 0;
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
        this.pendingReposition = -1;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        extractorInput.peekFully(this.scratch.getData(), 0, 12);
        this.scratch.setPosition(0);
        if (this.scratch.readLittleEndianInt() != 1179011410) {
            return false;
        }
        this.scratch.skipBytes(4);
        if (this.scratch.readLittleEndianInt() == 541677121) {
            return true;
        }
        return false;
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        if (resolvePendingReposition(extractorInput, positionHolder)) {
            return 1;
        }
        switch (this.state) {
            case 0:
                if (sniff(extractorInput)) {
                    extractorInput.skipFully(12);
                    this.state = 1;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("AVI Header List not found", (Throwable) null);
            case 1:
                extractorInput.readFully(this.scratch.getData(), 0, 12);
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateWithListHeaderFrom(this.scratch);
                if (this.chunkHeaderHolder.listType == 1819436136) {
                    this.hdrlSize = this.chunkHeaderHolder.size;
                    this.state = 2;
                    return 0;
                }
                throw ParserException.createForMalformedContainer("hdrl expected, found: " + this.chunkHeaderHolder.listType, (Throwable) null);
            case 2:
                int i = this.hdrlSize - 4;
                ParsableByteArray parsableByteArray = new ParsableByteArray(i);
                extractorInput.readFully(parsableByteArray.getData(), 0, i);
                parseHdrlBody(parsableByteArray);
                this.state = 3;
                return 0;
            case 3:
                if (this.moviStart != -1) {
                    long position = extractorInput.getPosition();
                    long j = this.moviStart;
                    if (position != j) {
                        this.pendingReposition = j;
                        return 0;
                    }
                }
                extractorInput.peekFully(this.scratch.getData(), 0, 12);
                extractorInput.resetPeekPosition();
                this.scratch.setPosition(0);
                this.chunkHeaderHolder.populateFrom(this.scratch);
                int readLittleEndianInt = this.scratch.readLittleEndianInt();
                if (this.chunkHeaderHolder.chunkType == 1179011410) {
                    extractorInput.skipFully(12);
                    return 0;
                } else if (this.chunkHeaderHolder.chunkType == 1414744396 && readLittleEndianInt == 1769369453) {
                    long position2 = extractorInput.getPosition();
                    this.moviStart = position2;
                    this.moviEnd = position2 + ((long) this.chunkHeaderHolder.size) + 8;
                    if (!this.seekMapHasBeenOutput) {
                        if (((AviMainHeaderChunk) Assertions.checkNotNull(this.aviHeader)).hasIndex()) {
                            this.state = 4;
                            this.pendingReposition = this.moviEnd;
                            return 0;
                        }
                        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                        this.seekMapHasBeenOutput = true;
                    }
                    this.pendingReposition = extractorInput.getPosition() + 12;
                    this.state = 6;
                    return 0;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) this.chunkHeaderHolder.size) + 8;
                    return 0;
                }
            case 4:
                extractorInput.readFully(this.scratch.getData(), 0, 8);
                this.scratch.setPosition(0);
                int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
                int readLittleEndianInt3 = this.scratch.readLittleEndianInt();
                if (readLittleEndianInt2 == 829973609) {
                    this.state = 5;
                    this.idx1BodySize = readLittleEndianInt3;
                } else {
                    this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt3);
                }
                return 0;
            case 5:
                ParsableByteArray parsableByteArray2 = new ParsableByteArray(this.idx1BodySize);
                extractorInput.readFully(parsableByteArray2.getData(), 0, this.idx1BodySize);
                parseIdx1Body(parsableByteArray2);
                this.state = 6;
                this.pendingReposition = this.moviStart;
                return 0;
            case 6:
                return readMoviChunks(extractorInput);
            default:
                throw new AssertionError();
        }
    }

    public void seek(long j, long j2) {
        this.pendingReposition = -1;
        this.currentChunkReader = null;
        for (ChunkReader seekToPosition : this.chunkReaders) {
            seekToPosition.seekToPosition(j);
        }
        if (j != 0) {
            this.state = 6;
        } else if (this.chunkReaders.length == 0) {
            this.state = 0;
        } else {
            this.state = 3;
        }
    }

    private boolean resolvePendingReposition(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        boolean z;
        if (this.pendingReposition != -1) {
            long position = extractorInput.getPosition();
            long j = this.pendingReposition;
            if (j < position || j > 262144 + position) {
                positionHolder.position = j;
                z = true;
                this.pendingReposition = -1;
                return z;
            }
            extractorInput.skipFully((int) (j - position));
        }
        z = false;
        this.pendingReposition = -1;
        return z;
    }

    private void parseHdrlBody(ParsableByteArray parsableByteArray) throws IOException {
        ListChunk parseFrom = ListChunk.parseFrom(FOURCC_hdrl, parsableByteArray);
        if (parseFrom.getType() == 1819436136) {
            AviMainHeaderChunk aviMainHeaderChunk = (AviMainHeaderChunk) parseFrom.getChild(AviMainHeaderChunk.class);
            if (aviMainHeaderChunk != null) {
                this.aviHeader = aviMainHeaderChunk;
                this.durationUs = ((long) aviMainHeaderChunk.totalFrames) * ((long) aviMainHeaderChunk.frameDurationUs);
                ArrayList arrayList = new ArrayList();
                UnmodifiableIterator<AviChunk> it = parseFrom.children.iterator();
                int i = 0;
                while (it.hasNext()) {
                    AviChunk next = it.next();
                    if (next.getType() == 1819440243) {
                        int i2 = i + 1;
                        ChunkReader processStreamList = processStreamList((ListChunk) next, i);
                        if (processStreamList != null) {
                            arrayList.add(processStreamList);
                        }
                        i = i2;
                    }
                }
                this.chunkReaders = (ChunkReader[]) arrayList.toArray(new ChunkReader[0]);
                this.extractorOutput.endTracks();
                return;
            }
            throw ParserException.createForMalformedContainer("AviHeader not found", (Throwable) null);
        }
        throw ParserException.createForMalformedContainer("Unexpected header list type " + parseFrom.getType(), (Throwable) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [int] */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseIdx1Body(androidx.media3.common.util.ParsableByteArray r10) {
        /*
            r9 = this;
            long r0 = r9.peekSeekOffset(r10)
        L_0x0004:
            int r2 = r10.bytesLeft()
            r3 = 0
            r4 = 1
            r5 = 16
            if (r2 < r5) goto L_0x002e
            int r2 = r10.readLittleEndianInt()
            int r6 = r10.readLittleEndianInt()
            int r7 = r10.readLittleEndianInt()
            long r7 = (long) r7
            long r7 = r7 + r0
            r10.readLittleEndianInt()
            androidx.media3.extractor.avi.ChunkReader r2 = r9.getChunkReader(r2)
            if (r2 != 0) goto L_0x0026
            goto L_0x0004
        L_0x0026:
            r6 = r6 & r5
            if (r6 != r5) goto L_0x002a
            r3 = r4
        L_0x002a:
            r2.appendIndexChunk(r7, r3)
            goto L_0x0004
        L_0x002e:
            androidx.media3.extractor.avi.ChunkReader[] r10 = r9.chunkReaders
            int r0 = r10.length
        L_0x0031:
            if (r3 >= r0) goto L_0x003b
            r1 = r10[r3]
            r1.compactIndex()
            int r3 = r3 + 1
            goto L_0x0031
        L_0x003b:
            r9.seekMapHasBeenOutput = r4
            androidx.media3.extractor.ExtractorOutput r10 = r9.extractorOutput
            androidx.media3.extractor.avi.AviExtractor$AviSeekMap r0 = new androidx.media3.extractor.avi.AviExtractor$AviSeekMap
            long r1 = r9.durationUs
            r0.<init>(r1)
            r10.seekMap(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.avi.AviExtractor.parseIdx1Body(androidx.media3.common.util.ParsableByteArray):void");
    }

    private long peekSeekOffset(ParsableByteArray parsableByteArray) {
        long j = 0;
        if (parsableByteArray.bytesLeft() < 16) {
            return 0;
        }
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(8);
        long j2 = this.moviStart;
        if (((long) parsableByteArray.readLittleEndianInt()) <= j2) {
            j = j2 + 8;
        }
        parsableByteArray.setPosition(position);
        return j;
    }

    private ChunkReader getChunkReader(int i) {
        for (ChunkReader chunkReader : this.chunkReaders) {
            if (chunkReader.handlesChunkId(i)) {
                return chunkReader;
            }
        }
        return null;
    }

    private int readMoviChunks(ExtractorInput extractorInput) throws IOException {
        if (extractorInput.getPosition() >= this.moviEnd) {
            return -1;
        }
        ChunkReader chunkReader = this.currentChunkReader;
        if (chunkReader == null) {
            alignInputToEvenPosition(extractorInput);
            int i = 12;
            extractorInput.peekFully(this.scratch.getData(), 0, 12);
            this.scratch.setPosition(0);
            int readLittleEndianInt = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1414744396) {
                this.scratch.setPosition(8);
                if (this.scratch.readLittleEndianInt() != 1769369453) {
                    i = 8;
                }
                extractorInput.skipFully(i);
                extractorInput.resetPeekPosition();
                return 0;
            }
            int readLittleEndianInt2 = this.scratch.readLittleEndianInt();
            if (readLittleEndianInt == 1263424842) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2) + 8;
                return 0;
            }
            extractorInput.skipFully(8);
            extractorInput.resetPeekPosition();
            ChunkReader chunkReader2 = getChunkReader(readLittleEndianInt);
            if (chunkReader2 == null) {
                this.pendingReposition = extractorInput.getPosition() + ((long) readLittleEndianInt2);
                return 0;
            }
            chunkReader2.onChunkStart(readLittleEndianInt2);
            this.currentChunkReader = chunkReader2;
        } else if (chunkReader.onChunkData(extractorInput)) {
            this.currentChunkReader = null;
        }
        return 0;
    }

    private ChunkReader processStreamList(ListChunk listChunk, int i) {
        AviStreamHeaderChunk aviStreamHeaderChunk = (AviStreamHeaderChunk) listChunk.getChild(AviStreamHeaderChunk.class);
        StreamFormatChunk streamFormatChunk = (StreamFormatChunk) listChunk.getChild(StreamFormatChunk.class);
        if (aviStreamHeaderChunk == null) {
            Log.w(TAG, "Missing Stream Header");
            return null;
        } else if (streamFormatChunk == null) {
            Log.w(TAG, "Missing Stream Format");
            return null;
        } else {
            long durationUs2 = aviStreamHeaderChunk.getDurationUs();
            Format format = streamFormatChunk.format;
            Format.Builder buildUpon = format.buildUpon();
            buildUpon.setId(i);
            int i2 = aviStreamHeaderChunk.suggestedBufferSize;
            if (i2 != 0) {
                buildUpon.setMaxInputSize(i2);
            }
            StreamNameChunk streamNameChunk = (StreamNameChunk) listChunk.getChild(StreamNameChunk.class);
            if (streamNameChunk != null) {
                buildUpon.setLabel(streamNameChunk.name);
            }
            int trackType = MimeTypes.getTrackType(format.sampleMimeType);
            if (trackType != 1 && trackType != 2) {
                return null;
            }
            TrackOutput track = this.extractorOutput.track(i, trackType);
            track.format(buildUpon.build());
            ChunkReader chunkReader = new ChunkReader(i, trackType, durationUs2, aviStreamHeaderChunk.length, track);
            this.durationUs = Math.max(this.durationUs, durationUs2);
            return chunkReader;
        }
    }

    private static void alignInputToEvenPosition(ExtractorInput extractorInput) throws IOException {
        if ((extractorInput.getPosition() & 1) == 1) {
            extractorInput.skipFully(1);
        }
    }

    private class AviSeekMap implements SeekMap {
        private final long durationUs;

        public boolean isSeekable() {
            return true;
        }

        public AviSeekMap(long j) {
            this.durationUs = j;
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekMap.SeekPoints getSeekPoints(long j) {
            SeekMap.SeekPoints seekPoints = AviExtractor.this.chunkReaders[0].getSeekPoints(j);
            for (int i = 1; i < AviExtractor.this.chunkReaders.length; i++) {
                SeekMap.SeekPoints seekPoints2 = AviExtractor.this.chunkReaders[i].getSeekPoints(j);
                if (seekPoints2.first.position < seekPoints.first.position) {
                    seekPoints = seekPoints2;
                }
            }
            return seekPoints;
        }
    }

    private static class ChunkHeaderHolder {
        public int chunkType;
        public int listType;
        public int size;

        private ChunkHeaderHolder() {
        }

        public void populateWithListHeaderFrom(ParsableByteArray parsableByteArray) throws ParserException {
            populateFrom(parsableByteArray);
            if (this.chunkType == 1414744396) {
                this.listType = parsableByteArray.readLittleEndianInt();
                return;
            }
            throw ParserException.createForMalformedContainer("LIST expected, found: " + this.chunkType, (Throwable) null);
        }

        public void populateFrom(ParsableByteArray parsableByteArray) {
            this.chunkType = parsableByteArray.readLittleEndianInt();
            this.size = parsableByteArray.readLittleEndianInt();
            this.listType = 0;
        }
    }
}
