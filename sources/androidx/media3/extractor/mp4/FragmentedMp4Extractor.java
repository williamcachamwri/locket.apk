package androidx.media3.extractor.mp4;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.Util;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.container.ReorderingSeiMessageQueue;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SniffFailure;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessageEncoder;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class FragmentedMp4Extractor implements Extractor {
    private static final Format EMSG_FORMAT = new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_EMSG).build();
    private static final int EXTRA_TRACKS_BASE_ID = 100;
    @Deprecated
    public static final ExtractorsFactory FACTORY = new FragmentedMp4Extractor$$ExternalSyntheticLambda0();
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 32;
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    public static final int FLAG_READ_WITHIN_GOP_SAMPLE_DEPENDENCIES = 64;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final int SAMPLE_GROUP_TYPE_seig = 1936025959;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] ceaTrackOutputs;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<Mp4Box.ContainerBox> containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final EventMessageEncoder eventMessageEncoder;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private boolean isSampleDependedOn;
    private ImmutableList<SniffFailure> lastSniffFailures;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private final ReorderingSeiMessageQueue reorderingSeiMessageQueue;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private final ParsableByteArray scratch;
    private final byte[] scratchBytes;
    private long segmentIndexEarliestPresentationTimeUs;
    private final Track sideloadedTrack;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == 1836019574 || i == 1953653099 || i == 1835297121 || i == 1835626086 || i == 1937007212 || i == 1836019558 || i == 1953653094 || i == 1836475768 || i == 1701082227;
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == 1751411826 || i == 1835296868 || i == 1836476516 || i == 1936286840 || i == 1937011556 || i == 1937011827 || i == 1668576371 || i == 1937011555 || i == 1937011578 || i == 1937013298 || i == 1937007471 || i == 1668232756 || i == 1937011571 || i == 1952867444 || i == 1952868452 || i == 1953196132 || i == 1953654136 || i == 1953658222 || i == 1886614376 || i == 1935763834 || i == 1935763823 || i == 1936027235 || i == 1970628964 || i == 1935828848 || i == 1936158820 || i == 1701606260 || i == 1835362404 || i == 1701671783;
    }

    /* access modifiers changed from: protected */
    public Track modifyTrack(Track track) {
        return track;
    }

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new FragmentedMp4Extractor(factory)};
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new FragmentedMp4Extractor$$ExternalSyntheticLambda1(factory);
    }

    static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new FragmentedMp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 32)};
    }

    @Deprecated
    public FragmentedMp4Extractor() {
        this(SubtitleParser.Factory.UNSUPPORTED, 32, (TimestampAdjuster) null, (Track) null, ImmutableList.of(), (TrackOutput) null);
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory factory) {
        this(factory, 0, (TimestampAdjuster) null, (Track) null, ImmutableList.of(), (TrackOutput) null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i) {
        this(SubtitleParser.Factory.UNSUPPORTED, i | 32, (TimestampAdjuster) null, (Track) null, ImmutableList.of(), (TrackOutput) null);
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i) {
        this(factory, i, (TimestampAdjuster) null, (Track) null, ImmutableList.of(), (TrackOutput) null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster2) {
        this(SubtitleParser.Factory.UNSUPPORTED, i | 32, timestampAdjuster2, (Track) null, ImmutableList.of(), (TrackOutput) null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster2, Track track) {
        this(SubtitleParser.Factory.UNSUPPORTED, i | 32, timestampAdjuster2, track, ImmutableList.of(), (TrackOutput) null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster2, Track track, List<Format> list) {
        this(SubtitleParser.Factory.UNSUPPORTED, i | 32, timestampAdjuster2, track, list, (TrackOutput) null);
    }

    @Deprecated
    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster2, Track track, List<Format> list, TrackOutput trackOutput) {
        this(SubtitleParser.Factory.UNSUPPORTED, i | 32, timestampAdjuster2, track, list, trackOutput);
    }

    public FragmentedMp4Extractor(SubtitleParser.Factory factory, int i, TimestampAdjuster timestampAdjuster2, Track track, List<Format> list, TrackOutput trackOutput) {
        this.subtitleParserFactory = factory;
        this.flags = i;
        this.timestampAdjuster = timestampAdjuster2;
        this.sideloadedTrack = track;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        byte[] bArr = new byte[16];
        this.scratchBytes = bArr;
        this.scratch = new ParsableByteArray(bArr);
        this.containerAtoms = new ArrayDeque<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        this.lastSniffFailures = ImmutableList.of();
        this.durationUs = C.TIME_UNSET;
        this.pendingSeekTimeUs = C.TIME_UNSET;
        this.segmentIndexEarliestPresentationTimeUs = C.TIME_UNSET;
        this.extractorOutput = ExtractorOutput.PLACEHOLDER;
        this.emsgTrackOutputs = new TrackOutput[0];
        this.ceaTrackOutputs = new TrackOutput[0];
        this.reorderingSeiMessageQueue = new ReorderingSeiMessageQueue(new FragmentedMp4Extractor$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$2$androidx-media3-extractor-mp4-FragmentedMp4Extractor  reason: not valid java name */
    public /* synthetic */ void m906lambda$new$2$androidxmedia3extractormp4FragmentedMp4Extractor(long j, ParsableByteArray parsableByteArray) {
        CeaUtil.consume(j, parsableByteArray, this.ceaTrackOutputs);
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException {
        SniffFailure sniffFragmented = Sniffer.sniffFragmented(extractorInput);
        this.lastSniffFailures = sniffFragmented != null ? ImmutableList.of(sniffFragmented) : ImmutableList.of();
        return sniffFragmented == null;
    }

    public ImmutableList<SniffFailure> getSniffFailureDetails() {
        return this.lastSniffFailures;
    }

    public void init(ExtractorOutput extractorOutput2) {
        if ((this.flags & 32) == 0) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
        enterReadingAtomHeaderState();
        initExtraTracks();
        if (this.sideloadedTrack != null) {
            this.trackBundles.put(0, new TrackBundle(this.extractorOutput.track(0, this.sideloadedTrack.type), new TrackSampleTable(this.sideloadedTrack, new long[0], new int[0], 0, new long[0], new int[0], 0), new DefaultSampleValues(0, 0, 0, 0)));
            this.extractorOutput.endTracks();
        }
    }

    public void seek(long j, long j2) {
        int size = this.trackBundles.size();
        for (int i = 0; i < size; i++) {
            this.trackBundles.valueAt(i).resetFragmentInfo();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.reorderingSeiMessageQueue.flush();
        this.pendingSeekTimeUs = j2;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        while (true) {
            int i = this.parserState;
            if (i != 0) {
                if (i == 1) {
                    readAtomPayload(extractorInput);
                } else if (i == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                this.reorderingSeiMessageQueue.flush();
                return -1;
            }
        }
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.getData(), 0, 8, true)) {
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
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
            int i = this.atomType;
            if ((i == 1836019558 || i == 1835295092) && !this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs, position));
                this.haveOutputSeekMap = true;
            }
            if (this.atomType == 1836019558) {
                int size = this.trackBundles.size();
                for (int i2 = 0; i2 < size; i2++) {
                    TrackFragment trackFragment = this.trackBundles.valueAt(i2).fragment;
                    trackFragment.atomPosition = position;
                    trackFragment.auxiliaryDataPosition = position;
                    trackFragment.dataPosition = position;
                }
            }
            int i3 = this.atomType;
            if (i3 == 1835295092) {
                this.currentTrackBundle = null;
                this.endOfMdatPosition = position + this.atomSize;
                this.parserState = 2;
                return true;
            }
            if (shouldParseContainerAtom(i3)) {
                long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
                this.containerAtoms.push(new Mp4Box.ContainerBox(this.atomType, position2));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(position2);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                if (this.atomHeaderBytesRead != 8) {
                    throw ParserException.createForUnsupportedContainerFeature("Leaf atom defines extended atom size (unsupported).");
                } else if (this.atomSize <= 2147483647L) {
                    ParsableByteArray parsableByteArray = new ParsableByteArray((int) this.atomSize);
                    System.arraycopy(this.atomHeader.getData(), 0, parsableByteArray.getData(), 0, 8);
                    this.atomData = parsableByteArray;
                    this.parserState = 1;
                } else {
                    throw ParserException.createForUnsupportedContainerFeature("Leaf atom with length > 2147483647 (unsupported).");
                }
            } else if (this.atomSize <= 2147483647L) {
                this.atomData = null;
                this.parserState = 1;
            } else {
                throw ParserException.createForUnsupportedContainerFeature("Skipping atom with length > 2147483647 (unsupported).");
            }
            return true;
        }
        throw ParserException.createForUnsupportedContainerFeature("Atom size less than header length (unsupported).");
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws IOException {
        int i = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.getData(), 8, i);
            onLeafAtomRead(new Mp4Box.LeafBox(this.atomType, parsableByteArray), extractorInput.getPosition());
        } else {
            extractorInput.skipFully(i);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private void onLeafAtomRead(Mp4Box.LeafBox leafBox, long j) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafBox);
        } else if (leafBox.type == 1936286840) {
            Pair<Long, ChunkIndex> parseSidx = parseSidx(leafBox.data, j);
            this.segmentIndexEarliestPresentationTimeUs = ((Long) parseSidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) parseSidx.second);
            this.haveOutputSeekMap = true;
        } else if (leafBox.type == 1701671783) {
            onEmsgLeafAtomRead(leafBox.data);
        }
    }

    private void onContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        if (containerBox.type == 1836019574) {
            onMoovContainerAtomRead(containerBox);
        } else if (containerBox.type == 1836019558) {
            onMoofContainerAtomRead(containerBox);
        } else if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(containerBox);
        }
    }

    private void onMoovContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        boolean z = true;
        int i = 0;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox.leafChildren);
        Mp4Box.ContainerBox containerBox2 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox.getContainerBoxOfType(Mp4Box.TYPE_mvex));
        SparseArray sparseArray = new SparseArray();
        int size = containerBox2.leafChildren.size();
        long j = -9223372036854775807L;
        for (int i2 = 0; i2 < size; i2++) {
            Mp4Box.LeafBox leafBox = containerBox2.leafChildren.get(i2);
            if (leafBox.type == 1953654136) {
                Pair<Integer, DefaultSampleValues> parseTrex = parseTrex(leafBox.data);
                sparseArray.put(((Integer) parseTrex.first).intValue(), (DefaultSampleValues) parseTrex.second);
            } else if (leafBox.type == 1835362404) {
                j = parseMehd(leafBox.data);
            }
        }
        List<TrackSampleTable> parseTraks = BoxParser.parseTraks(containerBox, new GaplessInfoHolder(), j, drmInitDataFromAtoms, (this.flags & 16) != 0, false, new FragmentedMp4Extractor$$ExternalSyntheticLambda3(this));
        int size2 = parseTraks.size();
        if (this.trackBundles.size() == 0) {
            while (i < size2) {
                TrackSampleTable trackSampleTable = parseTraks.get(i);
                Track track = trackSampleTable.track;
                this.trackBundles.put(track.id, new TrackBundle(this.extractorOutput.track(i, track.type), trackSampleTable, getDefaultSampleValues(sparseArray, track.id)));
                this.durationUs = Math.max(this.durationUs, track.durationUs);
                i++;
            }
            this.extractorOutput.endTracks();
            return;
        }
        if (this.trackBundles.size() != size2) {
            z = false;
        }
        Assertions.checkState(z);
        while (i < size2) {
            TrackSampleTable trackSampleTable2 = parseTraks.get(i);
            Track track2 = trackSampleTable2.track;
            this.trackBundles.get(track2.id).reset(trackSampleTable2, getDefaultSampleValues(sparseArray, track2.id));
            i++;
        }
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> sparseArray, int i) {
        if (sparseArray.size() == 1) {
            return sparseArray.valueAt(0);
        }
        return (DefaultSampleValues) Assertions.checkNotNull(sparseArray.get(i));
    }

    private void onMoofContainerAtomRead(Mp4Box.ContainerBox containerBox) throws ParserException {
        parseMoof(containerBox, this.trackBundles, this.sideloadedTrack != null, this.flags, this.scratchBytes);
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerBox.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i = 0; i < size; i++) {
                this.trackBundles.valueAt(i).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
        if (this.pendingSeekTimeUs != C.TIME_UNSET) {
            int size2 = this.trackBundles.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.trackBundles.valueAt(i2).seek(this.pendingSeekTimeUs);
            }
            this.pendingSeekTimeUs = C.TIME_UNSET;
        }
    }

    private void initExtraTracks() {
        int i;
        TrackOutput[] trackOutputArr = new TrackOutput[2];
        this.emsgTrackOutputs = trackOutputArr;
        TrackOutput trackOutput = this.additionalEmsgTrackOutput;
        int i2 = 0;
        if (trackOutput != null) {
            trackOutputArr[0] = trackOutput;
            i = 1;
        } else {
            i = 0;
        }
        int i3 = 100;
        if ((this.flags & 4) != 0) {
            trackOutputArr[i] = this.extractorOutput.track(100, 5);
            i3 = 101;
            i++;
        }
        TrackOutput[] trackOutputArr2 = (TrackOutput[]) Util.nullSafeArrayCopy(this.emsgTrackOutputs, i);
        this.emsgTrackOutputs = trackOutputArr2;
        for (TrackOutput format : trackOutputArr2) {
            format.format(EMSG_FORMAT);
        }
        this.ceaTrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
        while (i2 < this.ceaTrackOutputs.length) {
            TrackOutput track = this.extractorOutput.track(i3, 3);
            track.format(this.closedCaptionFormats.get(i2));
            this.ceaTrackOutputs[i2] = track;
            i2++;
            i3++;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onEmsgLeafAtomRead(androidx.media3.common.util.ParsableByteArray r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            androidx.media3.extractor.TrackOutput[] r2 = r0.emsgTrackOutputs
            int r2 = r2.length
            if (r2 != 0) goto L_0x000a
            return
        L_0x000a:
            r2 = 8
            r1.setPosition(r2)
            int r2 = r28.readInt()
            int r2 = androidx.media3.extractor.mp4.BoxParser.parseFullBoxVersion(r2)
            r3 = 1
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 == 0) goto L_0x0072
            if (r2 == r3) goto L_0x0036
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Skipping unsupported emsg version: "
            r1.<init>(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "FragmentedMp4Extractor"
            androidx.media3.common.util.Log.w(r2, r1)
            return
        L_0x0036:
            long r12 = r28.readUnsignedInt()
            long r6 = r28.readUnsignedLongToLong()
            r8 = 1000000(0xf4240, double:4.940656E-318)
            r10 = r12
            long r14 = androidx.media3.common.util.Util.scaleLargeTimestamp(r6, r8, r10)
            long r6 = r28.readUnsignedInt()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = androidx.media3.common.util.Util.scaleLargeTimestamp(r6, r8, r10)
            long r8 = r28.readUnsignedInt()
            java.lang.String r2 = r28.readNullTerminatedString()
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r10 = r28.readNullTerminatedString()
            java.lang.Object r10 = androidx.media3.common.util.Assertions.checkNotNull(r10)
            java.lang.String r10 = (java.lang.String) r10
            r20 = r2
            r22 = r6
            r24 = r8
            r21 = r10
            r8 = r4
            goto L_0x00bc
        L_0x0072:
            java.lang.String r2 = r28.readNullTerminatedString()
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkNotNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r6 = r28.readNullTerminatedString()
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)
            r10 = r6
            java.lang.String r10 = (java.lang.String) r10
            long r6 = r28.readUnsignedInt()
            long r11 = r28.readUnsignedInt()
            r13 = 1000000(0xf4240, double:4.940656E-318)
            r15 = r6
            long r8 = androidx.media3.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            long r11 = r0.segmentIndexEarliestPresentationTimeUs
            int r13 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00a1
            long r11 = r11 + r8
            r17 = r11
            goto L_0x00a3
        L_0x00a1:
            r17 = r4
        L_0x00a3:
            long r11 = r28.readUnsignedInt()
            r13 = 1000(0x3e8, double:4.94E-321)
            r15 = r6
            long r6 = androidx.media3.common.util.Util.scaleLargeTimestamp(r11, r13, r15)
            long r11 = r28.readUnsignedInt()
            r20 = r2
            r22 = r6
            r21 = r10
            r24 = r11
            r14 = r17
        L_0x00bc:
            int r2 = r28.bytesLeft()
            byte[] r2 = new byte[r2]
            int r6 = r28.bytesLeft()
            r7 = 0
            r1.readBytes(r2, r7, r6)
            androidx.media3.extractor.metadata.emsg.EventMessage r1 = new androidx.media3.extractor.metadata.emsg.EventMessage
            r19 = r1
            r26 = r2
            r19.<init>(r20, r21, r22, r24, r26)
            androidx.media3.common.util.ParsableByteArray r2 = new androidx.media3.common.util.ParsableByteArray
            androidx.media3.extractor.metadata.emsg.EventMessageEncoder r6 = r0.eventMessageEncoder
            byte[] r1 = r6.encode(r1)
            r2.<init>((byte[]) r1)
            int r1 = r2.bytesLeft()
            androidx.media3.extractor.TrackOutput[] r6 = r0.emsgTrackOutputs
            int r10 = r6.length
            r11 = r7
        L_0x00e6:
            if (r11 >= r10) goto L_0x00f3
            r12 = r6[r11]
            r2.setPosition(r7)
            r12.sampleData(r2, r1)
            int r11 = r11 + 1
            goto L_0x00e6
        L_0x00f3:
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0107
            java.util.ArrayDeque<androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r4 = new androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r4.<init>(r8, r3, r1)
            r2.addLast(r4)
            int r2 = r0.pendingMetadataSampleBytes
            int r2 = r2 + r1
            r0.pendingMetadataSampleBytes = r2
            goto L_0x0158
        L_0x0107:
            java.util.ArrayDeque<androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x011f
            java.util.ArrayDeque<androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r3 = new androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r3.<init>(r14, r7, r1)
            r2.addLast(r3)
            int r2 = r0.pendingMetadataSampleBytes
            int r2 = r2 + r1
            r0.pendingMetadataSampleBytes = r2
            goto L_0x0158
        L_0x011f:
            androidx.media3.common.util.TimestampAdjuster r2 = r0.timestampAdjuster
            if (r2 == 0) goto L_0x0139
            boolean r2 = r2.isInitialized()
            if (r2 != 0) goto L_0x0139
            java.util.ArrayDeque<androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo> r2 = r0.pendingMetadataSampleInfos
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo r3 = new androidx.media3.extractor.mp4.FragmentedMp4Extractor$MetadataSampleInfo
            r3.<init>(r14, r7, r1)
            r2.addLast(r3)
            int r2 = r0.pendingMetadataSampleBytes
            int r2 = r2 + r1
            r0.pendingMetadataSampleBytes = r2
            goto L_0x0158
        L_0x0139:
            androidx.media3.common.util.TimestampAdjuster r2 = r0.timestampAdjuster
            if (r2 == 0) goto L_0x0141
            long r14 = r2.adjustSampleTimestamp(r14)
        L_0x0141:
            androidx.media3.extractor.TrackOutput[] r2 = r0.emsgTrackOutputs
            int r3 = r2.length
        L_0x0144:
            if (r7 >= r3) goto L_0x0158
            r16 = r2[r7]
            r19 = 1
            r21 = 0
            r22 = 0
            r17 = r14
            r20 = r1
            r16.sampleMetadata(r17, r19, r20, r21, r22)
            int r7 = r7 + 1
            goto L_0x0144
        L_0x0158:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.FragmentedMp4Extractor.onEmsgLeafAtomRead(androidx.media3.common.util.ParsableByteArray):void");
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readInt() - 1, parsableByteArray.readInt(), parsableByteArray.readInt(), parsableByteArray.readInt()));
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i, byte[] bArr) throws ParserException {
        int size = containerBox.containerChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i2);
            if (containerBox2.type == 1953653094) {
                parseTraf(containerBox2, sparseArray, z, i, bArr);
            }
        }
    }

    private static void parseTraf(Mp4Box.ContainerBox containerBox, SparseArray<TrackBundle> sparseArray, boolean z, int i, byte[] bArr) throws ParserException {
        TrackBundle parseTfhd = parseTfhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_tfhd))).data, sparseArray, z);
        if (parseTfhd != null) {
            TrackFragment trackFragment = parseTfhd.fragment;
            long j = trackFragment.nextFragmentDecodeTime;
            boolean z2 = trackFragment.nextFragmentDecodeTimeIncludesMoov;
            parseTfhd.resetFragmentInfo();
            boolean unused = parseTfhd.currentlyInFragment = true;
            Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_tfdt);
            if (leafBoxOfType == null || (i & 2) != 0) {
                trackFragment.nextFragmentDecodeTime = j;
                trackFragment.nextFragmentDecodeTimeIncludesMoov = z2;
            } else {
                trackFragment.nextFragmentDecodeTime = parseTfdt(leafBoxOfType.data);
                trackFragment.nextFragmentDecodeTimeIncludesMoov = true;
            }
            parseTruns(containerBox, parseTfhd, i);
            TrackEncryptionBox sampleDescriptionEncryptionBox = parseTfhd.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Assertions.checkNotNull(trackFragment.header)).sampleDescriptionIndex);
            Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_saiz);
            if (leafBoxOfType2 != null) {
                parseSaiz((TrackEncryptionBox) Assertions.checkNotNull(sampleDescriptionEncryptionBox), leafBoxOfType2.data, trackFragment);
            }
            Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_saio);
            if (leafBoxOfType3 != null) {
                parseSaio(leafBoxOfType3.data, trackFragment);
            }
            Mp4Box.LeafBox leafBoxOfType4 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_senc);
            if (leafBoxOfType4 != null) {
                parseSenc(leafBoxOfType4.data, trackFragment);
            }
            parseSampleGroups(containerBox, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
            int size = containerBox.leafChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                Mp4Box.LeafBox leafBox = containerBox.leafChildren.get(i2);
                if (leafBox.type == 1970628964) {
                    parseUuid(leafBox.data, trackFragment, bArr);
                }
            }
        }
    }

    private static void parseTruns(Mp4Box.ContainerBox containerBox, TrackBundle trackBundle, int i) throws ParserException {
        List<Mp4Box.LeafBox> list = containerBox.leafChildren;
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            Mp4Box.LeafBox leafBox = list.get(i4);
            if (leafBox.type == 1953658222) {
                ParsableByteArray parsableByteArray = leafBox.data;
                parsableByteArray.setPosition(12);
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (readUnsignedIntToInt > 0) {
                    i3 += readUnsignedIntToInt;
                    i2++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i2, i3);
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            Mp4Box.LeafBox leafBox2 = list.get(i7);
            if (leafBox2.type == 1953658222) {
                i6 = parseTrun(trackBundle, i5, i, leafBox2.data, i6);
                i5++;
            }
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i;
        int i2 = trackEncryptionBox.perSampleIvSize;
        parsableByteArray.setPosition(8);
        boolean z = true;
        if ((BoxParser.parseFullBoxFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt <= trackFragment.sampleCount) {
            if (readUnsignedByte == 0) {
                boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
                i = 0;
                for (int i3 = 0; i3 < readUnsignedIntToInt; i3++) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    i += readUnsignedByte2;
                    zArr[i3] = readUnsignedByte2 > i2;
                }
            } else {
                if (readUnsignedByte <= i2) {
                    z = false;
                }
                i = (readUnsignedByte * readUnsignedIntToInt) + 0;
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z);
            }
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, readUnsignedIntToInt, trackFragment.sampleCount, false);
            if (i > 0) {
                trackFragment.initEncryptionData(i);
                return;
            }
            return;
        }
        throw ParserException.createForMalformedContainer("Saiz sample count " + readUnsignedIntToInt + " is greater than fragment sample count" + trackFragment.sampleCount, (Throwable) null);
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        long j;
        parsableByteArray.setPosition(8);
        int readInt = parsableByteArray.readInt();
        if ((BoxParser.parseFullBoxFlags(readInt) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt == 1) {
            int parseFullBoxVersion = BoxParser.parseFullBoxVersion(readInt);
            long j2 = trackFragment.auxiliaryDataPosition;
            if (parseFullBoxVersion == 0) {
                j = parsableByteArray.readUnsignedInt();
            } else {
                j = parsableByteArray.readUnsignedLongToLong();
            }
            trackFragment.auxiliaryDataPosition = j2 + j;
            return;
        }
        throw ParserException.createForMalformedContainer("Unexpected saio entry count: " + readUnsignedIntToInt, (Throwable) null);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.util.SparseArray, android.util.SparseArray<androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle parseTfhd(androidx.media3.common.util.ParsableByteArray r4, android.util.SparseArray<androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle> r5, boolean r6) {
        /*
            r0 = 8
            r4.setPosition(r0)
            int r0 = r4.readInt()
            int r0 = androidx.media3.extractor.mp4.BoxParser.parseFullBoxFlags(r0)
            int r1 = r4.readInt()
            if (r6 == 0) goto L_0x0019
            r6 = 0
            java.lang.Object r5 = r5.valueAt(r6)
            goto L_0x001d
        L_0x0019:
            java.lang.Object r5 = r5.get(r1)
        L_0x001d:
            androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle r5 = (androidx.media3.extractor.mp4.FragmentedMp4Extractor.TrackBundle) r5
            if (r5 != 0) goto L_0x0023
            r4 = 0
            return r4
        L_0x0023:
            r6 = r0 & 1
            if (r6 == 0) goto L_0x0033
            long r1 = r4.readUnsignedLongToLong()
            androidx.media3.extractor.mp4.TrackFragment r6 = r5.fragment
            r6.dataPosition = r1
            androidx.media3.extractor.mp4.TrackFragment r6 = r5.fragment
            r6.auxiliaryDataPosition = r1
        L_0x0033:
            androidx.media3.extractor.mp4.DefaultSampleValues r6 = r5.defaultSampleValues
            r1 = r0 & 2
            if (r1 == 0) goto L_0x0040
            int r1 = r4.readInt()
            int r1 = r1 + -1
            goto L_0x0042
        L_0x0040:
            int r1 = r6.sampleDescriptionIndex
        L_0x0042:
            r2 = r0 & 8
            if (r2 == 0) goto L_0x004b
            int r2 = r4.readInt()
            goto L_0x004d
        L_0x004b:
            int r2 = r6.duration
        L_0x004d:
            r3 = r0 & 16
            if (r3 == 0) goto L_0x0056
            int r3 = r4.readInt()
            goto L_0x0058
        L_0x0056:
            int r3 = r6.size
        L_0x0058:
            r0 = r0 & 32
            if (r0 == 0) goto L_0x0061
            int r4 = r4.readInt()
            goto L_0x0063
        L_0x0061:
            int r4 = r6.flags
        L_0x0063:
            androidx.media3.extractor.mp4.TrackFragment r6 = r5.fragment
            androidx.media3.extractor.mp4.DefaultSampleValues r0 = new androidx.media3.extractor.mp4.DefaultSampleValues
            r0.<init>(r1, r2, r3, r4)
            r6.header = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mp4.FragmentedMp4Extractor.parseTfhd(androidx.media3.common.util.ParsableByteArray, android.util.SparseArray, boolean):androidx.media3.extractor.mp4.FragmentedMp4Extractor$TrackBundle");
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return BoxParser.parseFullBoxVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static boolean isEdtsListDurationForEntireMediaTimeline(Track track) {
        if (track.editListDurations == null || track.editListDurations.length != 1 || track.editListMediaTimes == null) {
            return false;
        }
        if (track.editListDurations[0] == 0) {
            return true;
        }
        if (Util.scaleLargeTimestamp(track.editListDurations[0], 1000000, track.movieTimescale) + Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000000, track.timescale) >= track.durationUs) {
            return true;
        }
        return false;
    }

    private static int parseTrun(TrackBundle trackBundle, int i, int i2, ParsableByteArray parsableByteArray, int i3) throws ParserException {
        boolean z;
        int i4;
        boolean z2;
        int i5;
        boolean z3;
        boolean z4;
        boolean z5;
        int i6;
        TrackBundle trackBundle2 = trackBundle;
        parsableByteArray.setPosition(8);
        int parseFullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        Track track = trackBundle2.moovSampleTable.track;
        TrackFragment trackFragment = trackBundle2.fragment;
        DefaultSampleValues defaultSampleValues = (DefaultSampleValues) Util.castNonNull(trackFragment.header);
        trackFragment.trunLength[i] = parsableByteArray.readUnsignedIntToInt();
        trackFragment.trunDataPosition[i] = trackFragment.dataPosition;
        if ((parseFullBoxFlags & 1) != 0) {
            long[] jArr = trackFragment.trunDataPosition;
            jArr[i] = jArr[i] + ((long) parsableByteArray.readInt());
        }
        boolean z6 = (parseFullBoxFlags & 4) != 0;
        int i7 = defaultSampleValues.flags;
        if (z6) {
            i7 = parsableByteArray.readInt();
        }
        boolean z7 = (parseFullBoxFlags & 256) != 0;
        boolean z8 = (parseFullBoxFlags & 512) != 0;
        boolean z9 = (parseFullBoxFlags & 1024) != 0;
        boolean z10 = (parseFullBoxFlags & 2048) != 0;
        long j = isEdtsListDurationForEntireMediaTimeline(track) ? ((long[]) Util.castNonNull(track.editListMediaTimes))[0] : 0;
        int[] iArr = trackFragment.sampleSizeTable;
        long[] jArr2 = trackFragment.samplePresentationTimesUs;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        int i8 = i7;
        boolean z11 = track.type == 2 && (i2 & 1) != 0;
        int i9 = i3 + trackFragment.trunLength[i];
        boolean z12 = z11;
        long[] jArr3 = jArr2;
        boolean[] zArr2 = zArr;
        long j2 = track.timescale;
        long j3 = trackFragment.nextFragmentDecodeTime;
        int i10 = i3;
        while (i10 < i9) {
            int checkNonNegative = checkNonNegative(z7 ? parsableByteArray.readInt() : defaultSampleValues.duration);
            if (z8) {
                i4 = parsableByteArray.readInt();
                z = z7;
            } else {
                z = z7;
                i4 = defaultSampleValues.size;
            }
            int checkNonNegative2 = checkNonNegative(i4);
            if (z9) {
                z2 = z6;
                i5 = parsableByteArray.readInt();
            } else if (i10 != 0 || !z6) {
                z2 = z6;
                i5 = defaultSampleValues.flags;
            } else {
                z2 = z6;
                i5 = i8;
            }
            if (z10) {
                z5 = z10;
                z4 = z8;
                z3 = z9;
                i6 = parsableByteArray.readInt();
            } else {
                z5 = z10;
                z4 = z8;
                z3 = z9;
                i6 = 0;
            }
            jArr3[i10] = Util.scaleLargeTimestamp((((long) i6) + j3) - j, 1000000, j2);
            if (!trackFragment.nextFragmentDecodeTimeIncludesMoov) {
                jArr3[i10] = jArr3[i10] + trackBundle2.moovSampleTable.durationUs;
            }
            iArr[i10] = checkNonNegative2;
            zArr2[i10] = ((i5 >> 16) & 1) == 0 && (!z12 || i10 == 0);
            j3 += (long) checkNonNegative;
            i10++;
            trackBundle2 = trackBundle;
            z7 = z;
            z6 = z2;
            z10 = z5;
            z8 = z4;
            z9 = z3;
        }
        trackFragment.nextFragmentDecodeTime = j3;
        return i9;
    }

    private static int checkNonNegative(int i) throws ParserException {
        if (i >= 0) {
            return i;
        }
        throw ParserException.createForMalformedContainer("Unexpected negative value: " + i, (Throwable) null);
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int parseFullBoxFlags = BoxParser.parseFullBoxFlags(parsableByteArray.readInt());
        if ((parseFullBoxFlags & 1) == 0) {
            boolean z = (parseFullBoxFlags & 2) != 0;
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt == 0) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, trackFragment.sampleCount, false);
            } else if (readUnsignedIntToInt == trackFragment.sampleCount) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z);
                trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
                trackFragment.fillEncryptionData(parsableByteArray);
            } else {
                throw ParserException.createForMalformedContainer("Senc sample count " + readUnsignedIntToInt + " is different from fragment sample count" + trackFragment.sampleCount, (Throwable) null);
            }
        } else {
            throw ParserException.createForUnsupportedContainerFeature("Overriding TrackEncryptionBox parameters is unsupported.");
        }
    }

    private static void parseSampleGroups(Mp4Box.ContainerBox containerBox, String str, TrackFragment trackFragment) throws ParserException {
        Mp4Box.ContainerBox containerBox2 = containerBox;
        TrackFragment trackFragment2 = trackFragment;
        byte[] bArr = null;
        ParsableByteArray parsableByteArray = null;
        ParsableByteArray parsableByteArray2 = null;
        for (int i = 0; i < containerBox2.leafChildren.size(); i++) {
            Mp4Box.LeafBox leafBox = containerBox2.leafChildren.get(i);
            ParsableByteArray parsableByteArray3 = leafBox.data;
            if (leafBox.type == 1935828848) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray = parsableByteArray3;
                }
            } else if (leafBox.type == 1936158820) {
                parsableByteArray3.setPosition(12);
                if (parsableByteArray3.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    parsableByteArray2 = parsableByteArray3;
                }
            }
        }
        if (parsableByteArray != null && parsableByteArray2 != null) {
            parsableByteArray.setPosition(8);
            int parseFullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
            parsableByteArray.skipBytes(4);
            if (parseFullBoxVersion == 1) {
                parsableByteArray.skipBytes(4);
            }
            if (parsableByteArray.readInt() == 1) {
                parsableByteArray2.setPosition(8);
                int parseFullBoxVersion2 = BoxParser.parseFullBoxVersion(parsableByteArray2.readInt());
                parsableByteArray2.skipBytes(4);
                if (parseFullBoxVersion2 == 1) {
                    if (parsableByteArray2.readUnsignedInt() == 0) {
                        throw ParserException.createForUnsupportedContainerFeature("Variable length description in sgpd found (unsupported)");
                    }
                } else if (parseFullBoxVersion2 >= 2) {
                    parsableByteArray2.skipBytes(4);
                }
                if (parsableByteArray2.readUnsignedInt() == 1) {
                    parsableByteArray2.skipBytes(1);
                    int readUnsignedByte = parsableByteArray2.readUnsignedByte();
                    int i2 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                    int i3 = readUnsignedByte & 15;
                    boolean z = parsableByteArray2.readUnsignedByte() == 1;
                    if (z) {
                        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
                        byte[] bArr2 = new byte[16];
                        parsableByteArray2.readBytes(bArr2, 0, 16);
                        if (readUnsignedByte2 == 0) {
                            int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                            bArr = new byte[readUnsignedByte3];
                            parsableByteArray2.readBytes(bArr, 0, readUnsignedByte3);
                        }
                        trackFragment2.definesEncryptionData = true;
                        trackFragment2.trackEncryptionBox = new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i2, i3, bArr);
                        return;
                    }
                    return;
                }
                throw ParserException.createForUnsupportedContainerFeature("Entry count in sgpd != 1 (unsupported).");
            }
            throw ParserException.createForUnsupportedContainerFeature("Entry count in sbgp != 1 (unsupported).");
        }
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j) throws ParserException {
        long j2;
        long j3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(8);
        int parseFullBoxVersion = BoxParser.parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray2.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullBoxVersion == 0) {
            j3 = parsableByteArray.readUnsignedInt();
            j2 = parsableByteArray.readUnsignedInt();
        } else {
            j3 = parsableByteArray.readUnsignedLongToLong();
            j2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j4 = j3;
        long j5 = j + j2;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j4, 1000000, readUnsignedInt);
        parsableByteArray2.skipBytes(2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        long[] jArr3 = new long[readUnsignedShort];
        long j6 = scaleLargeTimestamp;
        long j7 = j4;
        int i = 0;
        long j8 = j7;
        while (i < readUnsignedShort) {
            int readInt = parsableByteArray.readInt();
            if ((readInt & Integer.MIN_VALUE) == 0) {
                long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                iArr[i] = readInt & Integer.MAX_VALUE;
                jArr[i] = j5;
                jArr3[i] = j6;
                long j9 = j8 + readUnsignedInt2;
                long[] jArr4 = jArr2;
                long[] jArr5 = jArr3;
                int i2 = readUnsignedShort;
                int[] iArr2 = iArr;
                long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(j9, 1000000, readUnsignedInt);
                jArr4[i] = scaleLargeTimestamp2 - jArr5[i];
                parsableByteArray2.skipBytes(4);
                j5 += (long) iArr2[i];
                i++;
                iArr = iArr2;
                jArr3 = jArr5;
                jArr2 = jArr4;
                jArr = jArr;
                readUnsignedShort = i2;
                long j10 = scaleLargeTimestamp2;
                j8 = j9;
                j6 = j10;
            } else {
                throw ParserException.createForMalformedContainer("Unhandled indirect reference", (Throwable) null);
            }
        }
        return Pair.create(Long.valueOf(scaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws IOException {
        int size = this.trackBundles.size();
        long j = Long.MAX_VALUE;
        TrackBundle trackBundle = null;
        for (int i = 0; i < size; i++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill && trackFragment.auxiliaryDataPosition < j) {
                j = trackFragment.auxiliaryDataPosition;
                trackBundle = this.trackBundles.valueAt(i);
            }
        }
        if (trackBundle == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j - extractorInput.getPosition());
        if (position >= 0) {
            extractorInput.skipFully(position);
            trackBundle.fragment.fillEncryptionData(extractorInput);
            return;
        }
        throw ParserException.createForMalformedContainer("Offset to encryption data was negative.", (Throwable) null);
    }

    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r9v8 */
    private boolean readSample(ExtractorInput extractorInput) throws IOException {
        boolean z;
        int i;
        int i2;
        ExtractorInput extractorInput2 = extractorInput;
        TrackBundle trackBundle = this.currentTrackBundle;
        boolean z2 = false;
        if (trackBundle == null) {
            trackBundle = getNextTrackBundle(this.trackBundles);
            if (trackBundle == null) {
                int position = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                if (position >= 0) {
                    extractorInput2.skipFully(position);
                    enterReadingAtomHeaderState();
                    return false;
                }
                throw ParserException.createForMalformedContainer("Offset to end of mdat was negative.", (Throwable) null);
            }
            int currentSampleOffset = (int) (trackBundle.getCurrentSampleOffset() - extractorInput.getPosition());
            if (currentSampleOffset < 0) {
                Log.w(TAG, "Ignoring negative offset to sample data.");
                currentSampleOffset = 0;
            }
            extractorInput2.skipFully(currentSampleOffset);
            this.currentTrackBundle = trackBundle;
        }
        int i3 = 4;
        ? r9 = 1;
        if (this.parserState == 3) {
            this.sampleSize = trackBundle.getCurrentSampleSize();
            this.isSampleDependedOn = (this.flags & 64) == 0 || !Objects.equals(trackBundle.moovSampleTable.track.format.sampleMimeType, MimeTypes.VIDEO_H264);
            if (trackBundle.currentSampleIndex < trackBundle.firstSampleToOutputIndex) {
                extractorInput2.skipFully(this.sampleSize);
                trackBundle.skipSampleEncryptionData();
                if (!trackBundle.next()) {
                    this.currentTrackBundle = null;
                }
                this.parserState = 3;
                return true;
            }
            if (trackBundle.moovSampleTable.track.sampleTransformation == 1) {
                this.sampleSize -= 8;
                extractorInput2.skipFully(8);
            }
            if (MimeTypes.AUDIO_AC4.equals(trackBundle.moovSampleTable.track.format.sampleMimeType)) {
                this.sampleBytesWritten = trackBundle.outputSampleEncryptionData(this.sampleSize, 7);
                Ac4Util.getAc4SampleHeader(this.sampleSize, this.scratch);
                trackBundle.output.sampleData(this.scratch, 7);
                this.sampleBytesWritten += 7;
            } else {
                this.sampleBytesWritten = trackBundle.outputSampleEncryptionData(this.sampleSize, 0);
            }
            this.sampleSize += this.sampleBytesWritten;
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        Track track = trackBundle.moovSampleTable.track;
        TrackOutput trackOutput = trackBundle.output;
        long currentSamplePresentationTimeUs = trackBundle.getCurrentSamplePresentationTimeUs();
        TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
        if (timestampAdjuster2 != null) {
            currentSamplePresentationTimeUs = timestampAdjuster2.adjustSampleTimestamp(currentSamplePresentationTimeUs);
        }
        long j = currentSamplePresentationTimeUs;
        if (track.nalUnitLengthFieldLength == 0) {
            while (true) {
                int i4 = this.sampleBytesWritten;
                int i5 = this.sampleSize;
                if (i4 >= i5) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData((DataReader) extractorInput2, i5 - i4, false);
            }
        } else {
            byte[] data = this.nalPrefix.getData();
            data[0] = 0;
            data[1] = 0;
            data[2] = 0;
            int i6 = track.nalUnitLengthFieldLength + 1;
            int i7 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i8 = this.sampleCurrentNalBytesRemaining;
                if (i8 == 0) {
                    extractorInput2.readFully(data, i7, i6);
                    this.nalPrefix.setPosition(z2);
                    int readInt = this.nalPrefix.readInt();
                    if (readInt >= r9) {
                        this.sampleCurrentNalBytesRemaining = readInt - 1;
                        this.nalStartCode.setPosition(z2);
                        trackOutput.sampleData(this.nalStartCode, i3);
                        trackOutput.sampleData(this.nalPrefix, r9);
                        this.processSeiNalUnitPayload = (this.ceaTrackOutputs.length <= 0 || !NalUnitUtil.isNalUnitSei(track.format, data[i3])) ? z2 : r9;
                        this.sampleBytesWritten += 5;
                        this.sampleSize += i7;
                        if (!this.isSampleDependedOn && Objects.equals(trackBundle.moovSampleTable.track.format.sampleMimeType, MimeTypes.VIDEO_H264) && NalUnitUtil.isH264NalUnitDependedOn(data[i3])) {
                            this.isSampleDependedOn = r9;
                        }
                        z = r9;
                    } else {
                        throw ParserException.createForMalformedContainer("Invalid NAL length", (Throwable) null);
                    }
                } else {
                    if (this.processSeiNalUnitPayload) {
                        this.nalBuffer.reset(i8);
                        extractorInput2.readFully(this.nalBuffer.getData(), z2 ? 1 : 0, this.sampleCurrentNalBytesRemaining);
                        trackOutput.sampleData(this.nalBuffer, this.sampleCurrentNalBytesRemaining);
                        i2 = this.sampleCurrentNalBytesRemaining;
                        int unescapeStream = NalUnitUtil.unescapeStream(this.nalBuffer.getData(), this.nalBuffer.limit());
                        this.nalBuffer.setPosition((Objects.equals(track.format.sampleMimeType, MimeTypes.VIDEO_H265) || MimeTypes.containsCodecsCorrespondingToMimeType(track.format.codecs, MimeTypes.VIDEO_H265)) ? 1 : 0);
                        this.nalBuffer.setLimit(unescapeStream);
                        if (track.format.maxNumReorderSamples == -1) {
                            if (this.reorderingSeiMessageQueue.getMaxSize() != 0) {
                                this.reorderingSeiMessageQueue.setMaxSize(0);
                            }
                        } else if (this.reorderingSeiMessageQueue.getMaxSize() != track.format.maxNumReorderSamples) {
                            this.reorderingSeiMessageQueue.setMaxSize(track.format.maxNumReorderSamples);
                        }
                        this.reorderingSeiMessageQueue.add(j, this.nalBuffer);
                        i = 4;
                        if ((trackBundle.getCurrentSampleFlags() & 4) != 0) {
                            this.reorderingSeiMessageQueue.flush();
                        }
                    } else {
                        i = i3;
                        i2 = trackOutput.sampleData((DataReader) extractorInput2, i8, z2);
                    }
                    this.sampleBytesWritten += i2;
                    this.sampleCurrentNalBytesRemaining -= i2;
                    i3 = i;
                    z2 = false;
                    z = true;
                }
                r9 = z;
            }
        }
        int currentSampleFlags = trackBundle.getCurrentSampleFlags();
        if ((this.flags & 64) != 0 && !this.isSampleDependedOn) {
            currentSampleFlags |= 67108864;
        }
        int i9 = currentSampleFlags;
        TrackEncryptionBox encryptionBoxIfEncrypted = trackBundle.getEncryptionBoxIfEncrypted();
        trackOutput.sampleMetadata(j, i9, this.sampleSize, 0, encryptionBoxIfEncrypted != null ? encryptionBoxIfEncrypted.cryptoData : null);
        outputPendingMetadataSamples(j);
        if (!trackBundle.next()) {
            this.currentTrackBundle = null;
        }
        this.parserState = 3;
        return true;
    }

    private void outputPendingMetadataSamples(long j) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo removeFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= removeFirst.size;
            long j2 = removeFirst.sampleTimeUs;
            if (removeFirst.sampleTimeIsRelative) {
                j2 += j;
            }
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                j2 = timestampAdjuster2.adjustSampleTimestamp(j2);
            }
            for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                sampleMetadata.sampleMetadata(j2, 1, removeFirst.size, this.pendingMetadataSampleBytes, (TrackOutput.CryptoData) null);
            }
        }
    }

    private static TrackBundle getNextTrackBundle(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackBundle valueAt = sparseArray.valueAt(i);
            if ((valueAt.currentlyInFragment || valueAt.currentSampleIndex != valueAt.moovSampleTable.sampleCount) && (!valueAt.currentlyInFragment || valueAt.currentTrackRunIndex != valueAt.fragment.trunCount)) {
                long currentSampleOffset = valueAt.getCurrentSampleOffset();
                if (currentSampleOffset < j) {
                    trackBundle = valueAt;
                    j = currentSampleOffset;
                }
            }
        }
        return trackBundle;
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Mp4Box.LeafBox> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            Mp4Box.LeafBox leafBox = list.get(i);
            if (leafBox.type == 1886614376) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] data = leafBox.data.getData();
                UUID parseUuid = PsshAtomUtil.parseUuid(data);
                if (parseUuid == null) {
                    Log.w(TAG, "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(parseUuid, MimeTypes.VIDEO_MP4, data));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData((List<DrmInitData.SchemeData>) arrayList);
    }

    private static final class MetadataSampleInfo {
        public final boolean sampleTimeIsRelative;
        public final long sampleTimeUs;
        public final int size;

        public MetadataSampleInfo(long j, boolean z, int i) {
            this.sampleTimeUs = j;
            this.sampleTimeIsRelative = z;
            this.size = i;
        }
    }

    private static final class TrackBundle {
        private static final int SINGLE_SUBSAMPLE_ENCRYPTION_DATA_LENGTH = 8;
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        /* access modifiers changed from: private */
        public boolean currentlyInFragment;
        private final ParsableByteArray defaultInitializationVector = new ParsableByteArray();
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte = new ParsableByteArray(1);
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment = new TrackFragment();
        public TrackSampleTable moovSampleTable;
        public final TrackOutput output;
        public final ParsableByteArray scratch = new ParsableByteArray();

        public TrackBundle(TrackOutput trackOutput, TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2) {
            this.output = trackOutput;
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            reset(trackSampleTable, defaultSampleValues2);
        }

        public void reset(TrackSampleTable trackSampleTable, DefaultSampleValues defaultSampleValues2) {
            this.moovSampleTable = trackSampleTable;
            this.defaultSampleValues = defaultSampleValues2;
            this.output.format(trackSampleTable.track.format);
            resetFragmentInfo();
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex);
            this.output.format(this.moovSampleTable.track.format.buildUpon().setDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)).build());
        }

        public void resetFragmentInfo() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
            this.currentlyInFragment = false;
        }

        public void seek(long j) {
            int i = this.currentSampleIndex;
            while (i < this.fragment.sampleCount && this.fragment.getSamplePresentationTimeUs(i) <= j) {
                if (this.fragment.sampleIsSyncFrameTable[i]) {
                    this.firstSampleToOutputIndex = i;
                }
                i++;
            }
        }

        public long getCurrentSamplePresentationTimeUs() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.timestampsUs[this.currentSampleIndex];
            }
            return this.fragment.getSamplePresentationTimeUs(this.currentSampleIndex);
        }

        public long getCurrentSampleOffset() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.offsets[this.currentSampleIndex];
            }
            return this.fragment.trunDataPosition[this.currentTrackRunIndex];
        }

        public int getCurrentSampleSize() {
            if (!this.currentlyInFragment) {
                return this.moovSampleTable.sizes[this.currentSampleIndex];
            }
            return this.fragment.sampleSizeTable[this.currentSampleIndex];
        }

        public int getCurrentSampleFlags() {
            int i;
            if (!this.currentlyInFragment) {
                i = this.moovSampleTable.flags[this.currentSampleIndex];
            } else {
                i = this.fragment.sampleIsSyncFrameTable[this.currentSampleIndex] ? 1 : 0;
            }
            return getEncryptionBoxIfEncrypted() != null ? i | 1073741824 : i;
        }

        public boolean next() {
            this.currentSampleIndex++;
            if (!this.currentlyInFragment) {
                return false;
            }
            int i = this.currentSampleInTrackRun + 1;
            this.currentSampleInTrackRun = i;
            int[] iArr = this.fragment.trunLength;
            int i2 = this.currentTrackRunIndex;
            if (i != iArr[i2]) {
                return true;
            }
            this.currentTrackRunIndex = i2 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        }

        public int outputSampleEncryptionData(int i, int i2) {
            ParsableByteArray parsableByteArray;
            int i3;
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return 0;
            }
            if (encryptionBoxIfEncrypted.perSampleIvSize != 0) {
                parsableByteArray = this.fragment.sampleEncryptionData;
                i3 = encryptionBoxIfEncrypted.perSampleIvSize;
            } else {
                byte[] bArr = (byte[]) Util.castNonNull(encryptionBoxIfEncrypted.defaultInitializationVector);
                this.defaultInitializationVector.reset(bArr, bArr.length);
                parsableByteArray = this.defaultInitializationVector;
                i3 = bArr.length;
            }
            boolean sampleHasSubsampleEncryptionTable = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            boolean z = sampleHasSubsampleEncryptionTable || i2 != 0;
            this.encryptionSignalByte.getData()[0] = (byte) ((z ? 128 : 0) | i3);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1, 1);
            this.output.sampleData(parsableByteArray, i3, 1);
            if (!z) {
                return i3 + 1;
            }
            if (!sampleHasSubsampleEncryptionTable) {
                this.scratch.reset(8);
                byte[] data = this.scratch.getData();
                data[0] = 0;
                data[1] = 1;
                data[2] = (byte) ((i2 >> 8) & 255);
                data[3] = (byte) (i2 & 255);
                data[4] = (byte) ((i >> 24) & 255);
                data[5] = (byte) ((i >> 16) & 255);
                data[6] = (byte) ((i >> 8) & 255);
                data[7] = (byte) (i & 255);
                this.output.sampleData(this.scratch, 8, 1);
                return i3 + 1 + 8;
            }
            ParsableByteArray parsableByteArray2 = this.fragment.sampleEncryptionData;
            int readUnsignedShort = parsableByteArray2.readUnsignedShort();
            parsableByteArray2.skipBytes(-2);
            int i4 = (readUnsignedShort * 6) + 2;
            if (i2 != 0) {
                this.scratch.reset(i4);
                byte[] data2 = this.scratch.getData();
                parsableByteArray2.readBytes(data2, 0, i4);
                int i5 = (((data2[2] & 255) << 8) | (data2[3] & 255)) + i2;
                data2[2] = (byte) ((i5 >> 8) & 255);
                data2[3] = (byte) (i5 & 255);
                parsableByteArray2 = this.scratch;
            }
            this.output.sampleData(parsableByteArray2, i4, 1);
            return i3 + 1 + i4;
        }

        public void skipSampleEncryptionData() {
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted != null) {
                ParsableByteArray parsableByteArray = this.fragment.sampleEncryptionData;
                if (encryptionBoxIfEncrypted.perSampleIvSize != 0) {
                    parsableByteArray.skipBytes(encryptionBoxIfEncrypted.perSampleIvSize);
                }
                if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex)) {
                    parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() * 6);
                }
            }
        }

        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            TrackEncryptionBox trackEncryptionBox;
            if (!this.currentlyInFragment) {
                return null;
            }
            int i = ((DefaultSampleValues) Util.castNonNull(this.fragment.header)).sampleDescriptionIndex;
            if (this.fragment.trackEncryptionBox != null) {
                trackEncryptionBox = this.fragment.trackEncryptionBox;
            } else {
                trackEncryptionBox = this.moovSampleTable.track.getSampleDescriptionEncryptionBox(i);
            }
            if (trackEncryptionBox == null || !trackEncryptionBox.isEncrypted) {
                return null;
            }
            return trackEncryptionBox;
        }
    }
}
