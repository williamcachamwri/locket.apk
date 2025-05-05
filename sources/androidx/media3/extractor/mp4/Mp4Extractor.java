package androidx.media3.extractor.mp4;

import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class Mp4Extractor implements Extractor, SeekMap {
    @Deprecated
    public static final ExtractorsFactory FACTORY = new Mp4Extractor$$ExternalSyntheticLambda1();
    private static final int FILE_TYPE_HEIC = 2;
    private static final int FILE_TYPE_MP4 = 0;
    private static final int FILE_TYPE_QUICKTIME = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 16;
    public static final int FLAG_MARK_FIRST_VIDEO_TRACK_WITH_MAIN_ROLE = 8;
    public static final int FLAG_READ_EDITABLE_VIDEO_TRACKS = 64;
    public static final int FLAG_READ_MOTION_PHOTO_METADATA = 2;
    public static final int FLAG_READ_SEF_DATA = 4;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES = 32;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private static final int STATE_READING_SEF = 3;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private long durationUs;
    private long edvdAtomOffset;
    private ExtractorOutput extractorOutput;
    private int fileType;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isSampleDependedOn;
    private ImmutableList<SniffFailure> lastSniffFailures;
    private MotionPhotoMetadata motionPhotoMetadata;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private boolean readingEditableVideoTracks;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private long sampleOffsetForEditableVideoTracks;
    private int sampleTrackIndex;
    private final ParsableByteArray scratch;
    private boolean seekToEdvdAtom;
    private boolean seenFtypAtom;
    private final SefReader sefReader;
    private final List<Metadata.Entry> slowMotionMetadataEntries;
    private final SubtitleParser.Factory subtitleParserFactory;
    private Mp4Track[] tracks;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static int brandToFileType(int i) {
        if (i != 1751476579) {
            return i != 1903435808 ? 0 : 1;
        }
        return 2;
    }

    static /* synthetic */ Track lambda$processMoovAtom$2(Track track) {
        return track;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == 1836019574 || i == 1953653099 || i == 1835297121 || i == 1835626086 || i == 1937007212 || i == 1701082227 || i == 1835365473 || i == 1701082724;
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == 1835296868 || i == 1836476516 || i == 1751411826 || i == 1937011556 || i == 1937011827 || i == 1937011571 || i == 1668576371 || i == 1701606260 || i == 1937011555 || i == 1937011578 || i == 1937013298 || i == 1937007471 || i == 1668232756 || i == 1953196132 || i == 1718909296 || i == 1969517665 || i == 1801812339 || i == 1768715124;
    }

    public boolean isSeekable() {
        return true;
    }

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new Mp4Extractor(factory)};
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new Mp4Extractor$$ExternalSyntheticLambda0(factory);
    }

    static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 16)};
    }

    @Deprecated
    public Mp4Extractor() {
        this(SubtitleParser.Factory.UNSUPPORTED, 16);
    }

    public Mp4Extractor(SubtitleParser.Factory factory) {
        this(factory, 0);
    }

    @Deprecated
    public Mp4Extractor(int i) {
        this(SubtitleParser.Factory.UNSUPPORTED, i);
    }

    public Mp4Extractor(SubtitleParser.Factory factory, int i) {
        this.subtitleParserFactory = factory;
        this.flags = i;
        this.lastSniffFailures = ImmutableList.of();
        boolean z = false;
        this.parserState = (i & 4) != 0 ? 3 : 0;
        this.sefReader = new SefReader();
        this.slowMotionMetadataEntries = new ArrayList();
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.scratch = new ParsableByteArray();
        this.sampleTrackIndex = -1;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.tracks = new Mp4Track[0];
        this.isSampleDependedOn = (i & 32) == 0 ? true : z;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        SniffFailure sniffUnfragmented = Sniffer.sniffUnfragmented(extractorInput, (this.flags & 2) != 0);
        this.lastSniffFailures = sniffUnfragmented != null ? ImmutableList.of(sniffUnfragmented) : ImmutableList.of();
        if (sniffUnfragmented == null) {
            return true;
        }
        return false;
    }

    public ImmutableList<SniffFailure> getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    public void init(ExtractorOutput extractorOutput2) {
        if ((this.flags & 16) == 0) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.isSampleDependedOn = (this.flags & 32) == 0;
        if (j != 0) {
            for (Mp4Track mp4Track : this.tracks) {
                updateSampleIndex(mp4Track, j2);
                if (mp4Track.trueHdSampleRechunker != null) {
                    mp4Track.trueHdSampleRechunker.reset();
                }
            }
        } else if (this.parserState != 3) {
            enterReadingAtomHeaderState();
        } else {
            this.sefReader.reset();
            this.slowMotionMetadataEntries.clear();
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i = this.parserState;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        return readSample(extractorInput, positionHolder);
                    }
                    if (i == 3) {
                        return readSefData(extractorInput, positionHolder);
                    }
                    throw new IllegalStateException();
                } else if (readAtomPayload(extractorInput, positionHolder)) {
                    return 1;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekMap.SeekPoints getSeekPoints(long j) {
        return getSeekPoints(j, -1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.extractor.SeekMap.SeekPoints getSeekPoints(long r17, int r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r19
            androidx.media3.extractor.mp4.Mp4Extractor$Mp4Track[] r4 = r0.tracks
            int r5 = r4.length
            if (r5 != 0) goto L_0x0013
            androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
            androidx.media3.extractor.SeekPoint r2 = androidx.media3.extractor.SeekPoint.START
            r1.<init>(r2)
            return r1
        L_0x0013:
            r5 = -1
            if (r3 == r5) goto L_0x0018
            r6 = r3
            goto L_0x001a
        L_0x0018:
            int r6 = r0.firstVideoTrackIndex
        L_0x001a:
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r9 = -1
            if (r6 == r5) goto L_0x0058
            r4 = r4[r6]
            androidx.media3.extractor.mp4.TrackSampleTable r4 = r4.sampleTable
            int r6 = getSynchronizationSampleIndex(r4, r1)
            if (r6 != r5) goto L_0x0035
            androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
            androidx.media3.extractor.SeekPoint r2 = androidx.media3.extractor.SeekPoint.START
            r1.<init>(r2)
            return r1
        L_0x0035:
            long[] r11 = r4.timestampsUs
            r12 = r11[r6]
            long[] r11 = r4.offsets
            r14 = r11[r6]
            int r11 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r11 >= 0) goto L_0x005e
            int r11 = r4.sampleCount
            int r11 = r11 + -1
            if (r6 >= r11) goto L_0x005e
            int r1 = r4.getIndexOfLaterOrEqualSynchronizationSample(r1)
            if (r1 == r5) goto L_0x005e
            if (r1 == r6) goto L_0x005e
            long[] r2 = r4.timestampsUs
            r9 = r2[r1]
            long[] r2 = r4.offsets
            r1 = r2[r1]
            goto L_0x0060
        L_0x0058:
            r14 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r12 = r1
        L_0x005e:
            r1 = r9
            r9 = r7
        L_0x0060:
            if (r3 != r5) goto L_0x007f
            r3 = 0
        L_0x0063:
            androidx.media3.extractor.mp4.Mp4Extractor$Mp4Track[] r4 = r0.tracks
            int r5 = r4.length
            if (r3 >= r5) goto L_0x007f
            int r5 = r0.firstVideoTrackIndex
            if (r3 == r5) goto L_0x007c
            r4 = r4[r3]
            androidx.media3.extractor.mp4.TrackSampleTable r4 = r4.sampleTable
            long r14 = maybeAdjustSeekOffset(r4, r12, r14)
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x007c
            long r1 = maybeAdjustSeekOffset(r4, r9, r1)
        L_0x007c:
            int r3 = r3 + 1
            goto L_0x0063
        L_0x007f:
            androidx.media3.extractor.SeekPoint r3 = new androidx.media3.extractor.SeekPoint
            r3.<init>(r12, r14)
            int r4 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r4 != 0) goto L_0x008e
            androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
            r1.<init>(r3)
            return r1
        L_0x008e:
            androidx.media3.extractor.SeekPoint r4 = new androidx.media3.extractor.SeekPoint
            r4.<init>(r9, r1)
            androidx.media3.extractor.SeekMap$SeekPoints r1 = new androidx.media3.extractor.SeekMap$SeekPoints
            r1.<init>(r3, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Mp4Extractor.getSeekPoints(long, int):androidx.media3.extractor.SeekMap$SeekPoints");
    }

    public long[] getSampleTimestampsUs(int i) {
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length <= i) {
            return new long[0];
        }
        return mp4TrackArr[i].sampleTable.timestampsUs;
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        Mp4Box.ContainerBox peek;
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
                processEndOfStreamReadingAtomHeader();
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j = this.atomSize;
        if (j == 1) {
            extractorInput.readFully(this.atomHeader.getData(), 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && (peek = this.containerAtoms.peek()) != null) {
                length = peek.endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            if (shouldParseContainerAtom(this.atomType)) {
                long position = extractorInput.getPosition();
                long j2 = this.atomSize;
                int i = this.atomHeaderBytesRead;
                long j3 = (position + j2) - ((long) i);
                if (j2 != ((long) i) && this.atomType == 1835365473) {
                    maybeSkipRemainingMetaAtomHeaderBytes(extractorInput);
                }
                this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, j3));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(j3);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                Assertions.checkState(this.atomHeaderBytesRead == 8);
                Assertions.checkState(this.atomSize <= 2147483647L);
                ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
                System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                this.atomData = parsableByteArray;
                this.parserState = 1;
            } else {
                processUnparsedAtom(extractorInput.getPosition() - ((long) this.atomHeaderBytesRead));
                this.atomData = null;
                this.parserState = 1;
            }
            return true;
        }
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readAtomPayload(androidx.media3.extractor.ExtractorInput r10, androidx.media3.extractor.PositionHolder r11) throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.atomSize
            int r2 = r9.atomHeaderBytesRead
            long r2 = (long) r2
            long r0 = r0 - r2
            long r2 = r10.getPosition()
            long r2 = r2 + r0
            androidx.media3.common.util.ParsableByteArray r4 = r9.atomData
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x0046
            byte[] r7 = r4.getData()
            int r8 = r9.atomHeaderBytesRead
            int r0 = (int) r0
            r10.readFully(r7, r8, r0)
            int r10 = r9.atomType
            r0 = 1718909296(0x66747970, float:2.8862439E23)
            if (r10 != r0) goto L_0x002b
            r9.seenFtypAtom = r5
            int r10 = processFtypAtom(r4)
            r9.fileType = r10
            goto L_0x005e
        L_0x002b:
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            boolean r10 = r10.isEmpty()
            if (r10 != 0) goto L_0x005e
            java.util.ArrayDeque<androidx.media3.container.Mp4Box$ContainerBox> r10 = r9.containerAtoms
            java.lang.Object r10 = r10.peek()
            androidx.media3.container.Mp4Box$ContainerBox r10 = (androidx.media3.container.Mp4Box.ContainerBox) r10
            androidx.media3.container.Mp4Box$LeafBox r0 = new androidx.media3.container.Mp4Box$LeafBox
            int r1 = r9.atomType
            r0.<init>(r1, r4)
            r10.add((androidx.media3.container.Mp4Box.LeafBox) r0)
            goto L_0x005e
        L_0x0046:
            boolean r4 = r9.seenFtypAtom
            if (r4 != 0) goto L_0x0053
            int r4 = r9.atomType
            r7 = 1835295092(0x6d646174, float:4.4175247E27)
            if (r4 != r7) goto L_0x0053
            r9.fileType = r5
        L_0x0053:
            r7 = 262144(0x40000, double:1.295163E-318)
            int r4 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r4 >= 0) goto L_0x0060
            int r0 = (int) r0
            r10.skipFully(r0)
        L_0x005e:
            r10 = r6
            goto L_0x0068
        L_0x0060:
            long r7 = r10.getPosition()
            long r7 = r7 + r0
            r11.position = r7
            r10 = r5
        L_0x0068:
            r9.processAtomEnded(r2)
            boolean r0 = r9.seekToEdvdAtom
            if (r0 == 0) goto L_0x0078
            r9.readingEditableVideoTracks = r5
            long r0 = r9.edvdAtomOffset
            r11.position = r0
            r9.seekToEdvdAtom = r6
            r10 = r5
        L_0x0078:
            if (r10 == 0) goto L_0x0080
            int r10 = r9.parserState
            r11 = 2
            if (r10 == r11) goto L_0x0080
            goto L_0x0081
        L_0x0080:
            r5 = r6
        L_0x0081:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.Mp4Extractor.readAtomPayload(androidx.media3.extractor.ExtractorInput, androidx.media3.extractor.PositionHolder):boolean");
    }

    private int readSefData(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int read = this.sefReader.read(extractorInput, positionHolder, this.slowMotionMetadataEntries);
        if (read == 1 && positionHolder.position == 0) {
            enterReadingAtomHeaderState();
        }
        return read;
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            Mp4Box.ContainerBox pop = this.containerAtoms.pop();
            if (pop.type == 1836019574) {
                processMoovAtom(pop);
                this.containerAtoms.clear();
                if (!this.seekToEdvdAtom) {
                    this.parserState = 2;
                }
            } else if (!this.containerAtoms.isEmpty()) {
                this.containerAtoms.peek().add(pop);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processMoovAtom(Mp4Box.ContainerBox containerBox) throws ParserException {
        List list;
        Metadata metadata;
        Metadata metadata2;
        List<TrackSampleTable> list2;
        int i;
        int i2;
        Mp4Box.ContainerBox containerBox2 = containerBox;
        Mp4Box.ContainerBox containerBoxOfType = containerBox2.getContainerBoxOfType(Mp4Box.TYPE_meta);
        List arrayList = new ArrayList();
        if (containerBoxOfType != null) {
            Metadata parseMdtaFromMeta = BoxParser.parseMdtaFromMeta(containerBoxOfType);
            if (this.readingEditableVideoTracks) {
                Assertions.checkStateNotNull(parseMdtaFromMeta);
                maybeSetDefaultSampleOffsetForEditableVideoTracks(parseMdtaFromMeta);
                arrayList = getAuxiliaryTrackTypesForEditableVideoTracks(parseMdtaFromMeta);
            } else if (shouldSeekToEdvdAtom(parseMdtaFromMeta)) {
                this.seekToEdvdAtom = true;
                return;
            }
            metadata = parseMdtaFromMeta;
            list = arrayList;
        } else {
            list = arrayList;
            metadata = null;
        }
        ArrayList arrayList2 = new ArrayList();
        boolean z = this.fileType == 1;
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        Mp4Box.LeafBox leafBoxOfType = containerBox2.getLeafBoxOfType(Mp4Box.TYPE_udta);
        if (leafBoxOfType != null) {
            Metadata parseUdta = BoxParser.parseUdta(leafBoxOfType);
            gaplessInfoHolder.setFromMetadata(parseUdta);
            metadata2 = parseUdta;
        } else {
            metadata2 = null;
        }
        Metadata metadata3 = new Metadata(BoxParser.parseMvhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_mvhd))).data));
        List<TrackSampleTable> parseTraks = BoxParser.parseTraks(containerBox, gaplessInfoHolder, C.TIME_UNSET, (DrmInitData) null, (this.flags & 1) != 0, z, new Mp4Extractor$$ExternalSyntheticLambda2());
        if (this.readingEditableVideoTracks) {
            Assertions.checkState(list.size() == parseTraks.size(), String.format(Locale.US, "The number of auxiliary track types from metadata (%d) is not same as the number of editable video tracks (%d)", new Object[]{Integer.valueOf(list.size()), Integer.valueOf(parseTraks.size())}));
        }
        long j = C.TIME_UNSET;
        long j2 = -9223372036854775807L;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        while (i3 < parseTraks.size()) {
            TrackSampleTable trackSampleTable = parseTraks.get(i3);
            if (trackSampleTable.sampleCount == 0) {
                list2 = parseTraks;
                i = i3;
            } else {
                Track track = trackSampleTable.track;
                int i6 = i3;
                long j3 = track.durationUs != j ? track.durationUs : trackSampleTable.durationUs;
                j2 = Math.max(j2, j3);
                int i7 = i4 + 1;
                list2 = parseTraks;
                Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i4, track.type));
                if (MimeTypes.AUDIO_TRUEHD.equals(track.format.sampleMimeType)) {
                    i2 = trackSampleTable.maximumSize * 16;
                } else {
                    i2 = trackSampleTable.maximumSize + 30;
                }
                Format.Builder buildUpon = track.format.buildUpon();
                buildUpon.setMaxInputSize(i2);
                if (track.type == 2) {
                    int i8 = track.format.roleFlags;
                    if ((this.flags & 8) != 0) {
                        i8 |= i5 == -1 ? 1 : 2;
                    }
                    if (track.format.frameRate == -1.0f && j3 > 0 && trackSampleTable.sampleCount > 0) {
                        buildUpon.setFrameRate(((float) trackSampleTable.sampleCount) / (((float) j3) / 1000000.0f));
                    }
                    if (this.readingEditableVideoTracks) {
                        i8 |= 32768;
                        i = i6;
                        buildUpon.setAuxiliaryTrackType(((Integer) list.get(i)).intValue());
                    } else {
                        i = i6;
                    }
                    buildUpon.setRoleFlags(i8);
                } else {
                    i = i6;
                }
                MetadataUtil.setFormatGaplessInfo(track.type, gaplessInfoHolder, buildUpon);
                int i9 = track.type;
                Metadata[] metadataArr = new Metadata[3];
                metadataArr[0] = this.slowMotionMetadataEntries.isEmpty() ? null : new Metadata((List<? extends Metadata.Entry>) this.slowMotionMetadataEntries);
                metadataArr[1] = metadata2;
                metadataArr[2] = metadata3;
                MetadataUtil.setFormatMetadata(i9, metadata, buildUpon, metadataArr);
                mp4Track.trackOutput.format(buildUpon.build());
                if (track.type == 2) {
                    if (i5 == -1) {
                        i5 = arrayList2.size();
                    }
                }
                arrayList2.add(mp4Track);
                i4 = i7;
            }
            i3 = i + 1;
            parseTraks = list2;
            j = C.TIME_UNSET;
        }
        this.firstVideoTrackIndex = i5;
        this.durationUs = j2;
        Mp4Track[] mp4TrackArr = (Mp4Track[]) arrayList2.toArray(new Mp4Track[0]);
        this.tracks = mp4TrackArr;
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(mp4TrackArr);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private boolean shouldSeekToEdvdAtom(Metadata metadata) {
        MdtaMetadataEntry findMdtaMetadataEntryWithKey;
        if (!(metadata == null || (this.flags & 64) == 0 || (findMdtaMetadataEntryWithKey = MetadataUtil.findMdtaMetadataEntryWithKey(metadata, MdtaMetadataEntry.KEY_EDITABLE_TRACKS_OFFSET)) == null)) {
            long readUnsignedLongToLong = new ParsableByteArray(findMdtaMetadataEntryWithKey.value).readUnsignedLongToLong();
            if (readUnsignedLongToLong > 0) {
                this.edvdAtomOffset = readUnsignedLongToLong;
                return true;
            }
        }
        return false;
    }

    private void maybeSetDefaultSampleOffsetForEditableVideoTracks(Metadata metadata) {
        MdtaMetadataEntry findMdtaMetadataEntryWithKey = MetadataUtil.findMdtaMetadataEntryWithKey(metadata, MdtaMetadataEntry.KEY_EDITABLE_TRACKS_SAMPLES_LOCATION);
        if (findMdtaMetadataEntryWithKey != null && findMdtaMetadataEntryWithKey.value[0] == 0) {
            this.sampleOffsetForEditableVideoTracks = this.edvdAtomOffset + 16;
        }
    }

    private List<Integer> getAuxiliaryTrackTypesForEditableVideoTracks(Metadata metadata) {
        List<Integer> editableTrackTypesFromMap = ((MdtaMetadataEntry) Assertions.checkStateNotNull(MetadataUtil.findMdtaMetadataEntryWithKey(metadata, MdtaMetadataEntry.KEY_EDITABLE_TRACKS_MAP))).getEditableTrackTypesFromMap();
        ArrayList arrayList = new ArrayList(editableTrackTypesFromMap.size());
        for (int i = 0; i < editableTrackTypesFromMap.size(); i++) {
            int intValue = editableTrackTypesFromMap.get(i).intValue();
            int i2 = 1;
            if (intValue != 0) {
                if (intValue != 1) {
                    i2 = 3;
                    if (intValue != 2) {
                        i2 = intValue != 3 ? 0 : 4;
                    }
                } else {
                    i2 = 2;
                }
            }
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        int i;
        PositionHolder positionHolder2;
        boolean z;
        boolean z2;
        ExtractorInput extractorInput2 = extractorInput;
        long position = extractorInput.getPosition();
        if (this.sampleTrackIndex == -1) {
            int trackIndexOfNextReadSample = getTrackIndexOfNextReadSample(position);
            this.sampleTrackIndex = trackIndexOfNextReadSample;
            if (trackIndexOfNextReadSample == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i2 = mp4Track.sampleIndex;
        long j = mp4Track.sampleTable.offsets[i2] + this.sampleOffsetForEditableVideoTracks;
        int i3 = mp4Track.sampleTable.sizes[i2];
        TrueHdSampleRechunker trueHdSampleRechunker = mp4Track.trueHdSampleRechunker;
        long j2 = (j - position) + ((long) this.sampleBytesRead);
        if (j2 < 0) {
            i = 1;
            positionHolder2 = positionHolder;
        } else if (j2 >= 262144) {
            positionHolder2 = positionHolder;
            i = 1;
        } else {
            if (mp4Track.track.sampleTransformation == 1) {
                j2 += 8;
                i3 -= 8;
            }
            extractorInput2.skipFully((int) j2);
            if (!Objects.equals(mp4Track.track.format.sampleMimeType, MimeTypes.VIDEO_H264)) {
                this.isSampleDependedOn = true;
            }
            if (mp4Track.track.nalUnitLengthFieldLength == 0) {
                if (MimeTypes.AUDIO_AC4.equals(mp4Track.track.format.sampleMimeType)) {
                    if (this.sampleBytesWritten == 0) {
                        Ac4Util.getAc4SampleHeader(i3, this.scratch);
                        trackOutput.sampleData(this.scratch, 7);
                        this.sampleBytesWritten += 7;
                    }
                    i3 += 7;
                } else if (trueHdSampleRechunker != null) {
                    trueHdSampleRechunker.startSample(extractorInput2);
                }
                while (true) {
                    int i4 = this.sampleBytesWritten;
                    if (i4 >= i3) {
                        break;
                    }
                    int sampleData = trackOutput.sampleData((DataReader) extractorInput2, i3 - i4, false);
                    this.sampleBytesRead += sampleData;
                    this.sampleBytesWritten += sampleData;
                    this.sampleCurrentNalBytesRemaining -= sampleData;
                }
            } else {
                byte[] data = this.nalPrefix.getData();
                data[0] = 0;
                data[1] = 0;
                data[2] = 0;
                int i5 = mp4Track.track.nalUnitLengthFieldLength + 1;
                int i6 = 4;
                int i7 = 4 - mp4Track.track.nalUnitLengthFieldLength;
                while (this.sampleBytesWritten < i3) {
                    int i8 = this.sampleCurrentNalBytesRemaining;
                    if (i8 == 0) {
                        extractorInput2.readFully(data, i7, i5);
                        this.sampleBytesRead += i5;
                        this.nalPrefix.setPosition(0);
                        int readInt = this.nalPrefix.readInt();
                        if (readInt >= 1) {
                            this.sampleCurrentNalBytesRemaining = readInt - 1;
                            this.nalStartCode.setPosition(0);
                            trackOutput.sampleData(this.nalStartCode, i6);
                            trackOutput.sampleData(this.nalPrefix, 1);
                            this.sampleBytesWritten += 5;
                            i3 += i7;
                            if (!this.isSampleDependedOn && NalUnitUtil.isH264NalUnitDependedOn(data[i6])) {
                                this.isSampleDependedOn = true;
                            }
                        } else {
                            throw ParserException.createForMalformedContainer("Invalid NAL length", (Throwable) null);
                        }
                    } else {
                        int sampleData2 = trackOutput.sampleData((DataReader) extractorInput2, i8, false);
                        this.sampleBytesRead += sampleData2;
                        this.sampleBytesWritten += sampleData2;
                        this.sampleCurrentNalBytesRemaining -= sampleData2;
                        i6 = 4;
                    }
                }
            }
            int i9 = i3;
            long j3 = mp4Track.sampleTable.timestampsUs[i2];
            int i10 = mp4Track.sampleTable.flags[i2];
            if (!this.isSampleDependedOn) {
                i10 |= 67108864;
            }
            if (trueHdSampleRechunker != null) {
                int i11 = i9;
                z = true;
                TrueHdSampleRechunker trueHdSampleRechunker2 = trueHdSampleRechunker;
                trueHdSampleRechunker.sampleMetadata(trackOutput, j3, i10, i11, 0, (TrackOutput.CryptoData) null);
                if (i2 + 1 == mp4Track.sampleTable.sampleCount) {
                    trueHdSampleRechunker2.outputPendingSampleMetadata(trackOutput, (TrackOutput.CryptoData) null);
                }
                z2 = false;
            } else {
                z2 = false;
                z = true;
                trackOutput.sampleMetadata(j3, i10, i9, 0, (TrackOutput.CryptoData) null);
            }
            mp4Track.sampleIndex++;
            this.sampleTrackIndex = -1;
            this.sampleBytesRead = z2 ? 1 : 0;
            this.sampleBytesWritten = z2;
            this.sampleCurrentNalBytesRemaining = z2;
            this.isSampleDependedOn = (this.flags & 32) == 0 ? z : z2;
            return z2;
        }
        positionHolder2.position = j;
        return i;
    }

    private int getTrackIndexOfNextReadSample(long j) {
        int i = -1;
        int i2 = -1;
        int i3 = 0;
        long j2 = Long.MAX_VALUE;
        boolean z = true;
        long j3 = Long.MAX_VALUE;
        boolean z2 = true;
        long j4 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i3 >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i3];
            int i4 = mp4Track.sampleIndex;
            if (i4 != mp4Track.sampleTable.sampleCount) {
                long j5 = mp4Track.sampleTable.offsets[i4];
                long j6 = ((long[][]) Util.castNonNull(this.accumulatedSampleSizes))[i3][i4];
                long j7 = j5 - j;
                boolean z3 = j7 < 0 || j7 >= 262144;
                if ((!z3 && z2) || (z3 == z2 && j7 < j4)) {
                    z2 = z3;
                    j4 = j7;
                    i2 = i3;
                    j3 = j6;
                }
                if (j6 < j2) {
                    z = z3;
                    i = i3;
                    j2 = j6;
                }
            }
            i3++;
        }
        return (j2 == Long.MAX_VALUE || !z || j3 < j2 + MAXIMUM_READ_AHEAD_BYTES_STREAM) ? i2 : i;
    }

    private void updateSampleIndex(Mp4Track mp4Track, long j) {
        TrackSampleTable trackSampleTable = mp4Track.sampleTable;
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
        if (indexOfEarlierOrEqualSynchronizationSample == -1) {
            indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
        }
        mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
    }

    private void processEndOfStreamReadingAtomHeader() {
        Metadata metadata;
        if (this.fileType == 2 && (this.flags & 2) != 0) {
            TrackOutput track = this.extractorOutput.track(0, 4);
            if (this.motionPhotoMetadata == null) {
                metadata = null;
            } else {
                metadata = new Metadata(this.motionPhotoMetadata);
            }
            track.format(new Format.Builder().setMetadata(metadata).build());
            this.extractorOutput.endTracks();
            this.extractorOutput.seekMap(new SeekMap.Unseekable(C.TIME_UNSET));
        }
    }

    private void maybeSkipRemainingMetaAtomHeaderBytes(ExtractorInput extractorInput) throws IOException {
        this.scratch.reset(8);
        extractorInput.peekFully(this.scratch.getData(), 0, 8);
        BoxParser.maybeSkipRemainingMetaBoxHeaderBytes(this.scratch);
        extractorInput.skipFully(this.scratch.getPosition());
        extractorInput.resetPeekPosition();
    }

    private void processUnparsedAtom(long j) {
        if (this.atomType == 1836086884) {
            int i = this.atomHeaderBytesRead;
            this.motionPhotoMetadata = new MotionPhotoMetadata(0, j, C.TIME_UNSET, j + ((long) i), this.atomSize - ((long) i));
        }
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i = 0; i < mp4TrackArr.length; i++) {
            jArr[i] = new long[mp4TrackArr[i].sampleTable.sampleCount];
            jArr2[i] = mp4TrackArr[i].sampleTable.timestampsUs[0];
        }
        long j = 0;
        int i2 = 0;
        while (i2 < mp4TrackArr.length) {
            long j2 = Long.MAX_VALUE;
            int i3 = -1;
            for (int i4 = 0; i4 < mp4TrackArr.length; i4++) {
                if (!zArr[i4]) {
                    long j3 = jArr2[i4];
                    if (j3 <= j2) {
                        i3 = i4;
                        j2 = j3;
                    }
                }
            }
            int i5 = iArr[i3];
            jArr[i3][i5] = j;
            j += (long) mp4TrackArr[i3].sampleTable.sizes[i5];
            int i6 = i5 + 1;
            iArr[i3] = i6;
            if (i6 < jArr[i3].length) {
                jArr2[i3] = mp4TrackArr[i3].sampleTable.timestampsUs[i6];
            } else {
                zArr[i3] = true;
                i2++;
            }
        }
        return jArr;
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j, long j2) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j);
        if (synchronizationSampleIndex == -1) {
            return j2;
        }
        return Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j2);
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
        return indexOfEarlierOrEqualSynchronizationSample == -1 ? trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j) : indexOfEarlierOrEqualSynchronizationSample;
    }

    private static int processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int brandToFileType = brandToFileType(parsableByteArray.readInt());
        if (brandToFileType != 0) {
            return brandToFileType;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            int brandToFileType2 = brandToFileType(parsableByteArray.readInt());
            if (brandToFileType2 != 0) {
                return brandToFileType2;
            }
        }
        return 0;
    }

    private static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;
        public final TrueHdSampleRechunker trueHdSampleRechunker;

        public Mp4Track(Track track2, TrackSampleTable trackSampleTable, TrackOutput trackOutput2) {
            this.track = track2;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput2;
            this.trueHdSampleRechunker = MimeTypes.AUDIO_TRUEHD.equals(track2.format.sampleMimeType) ? new TrueHdSampleRechunker() : null;
        }
    }
}
