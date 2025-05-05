package androidx.media3.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.TrueHdSampleRechunker;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.SubtitleTranscodingExtractorOutput;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    @Deprecated
    public static final ExtractorsFactory FACTORY = new MatroskaExtractor$$ExternalSyntheticLambda0();
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    public static final int FLAG_EMIT_RAW_SUBTITLE_DATA = 2;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_BITS_PER_CHANNEL = 21938;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    /* access modifiers changed from: private */
    public static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final boolean parseSubtitlesDuringExtraction;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final SubtitleParser.Factory subtitleParserFactory;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* access modifiers changed from: protected */
    public int getElementType(int i) {
        switch (i) {
            case ID_TRACK_TYPE /*131*/:
            case 136:
            case ID_BLOCK_DURATION /*155*/:
            case ID_CHANNELS /*159*/:
            case ID_PIXEL_WIDTH /*176*/:
            case ID_CUE_TIME /*179*/:
            case ID_PIXEL_HEIGHT /*186*/:
            case 215:
            case ID_TIME_CODE /*231*/:
            case ID_BLOCK_ADD_ID /*238*/:
            case ID_CUE_CLUSTER_POSITION /*241*/:
            case ID_REFERENCE_BLOCK /*251*/:
            case ID_BLOCK_ADD_ID_TYPE /*16871*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
            case ID_EBML_READ_VERSION /*17143*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
            case ID_SEEK_POSITION /*21420*/:
            case ID_STEREO_MODE /*21432*/:
            case ID_DISPLAY_WIDTH /*21680*/:
            case ID_DISPLAY_UNIT /*21682*/:
            case ID_DISPLAY_HEIGHT /*21690*/:
            case ID_FLAG_FORCED /*21930*/:
            case ID_COLOUR_BITS_PER_CHANNEL /*21938*/:
            case ID_COLOUR_RANGE /*21945*/:
            case ID_COLOUR_TRANSFER /*21946*/:
            case ID_COLOUR_PRIMARIES /*21947*/:
            case ID_MAX_CLL /*21948*/:
            case ID_MAX_FALL /*21949*/:
            case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
            case ID_CODEC_DELAY /*22186*/:
            case ID_SEEK_PRE_ROLL /*22203*/:
            case ID_AUDIO_BIT_DEPTH /*25188*/:
            case ID_DISCARD_PADDING /*30114*/:
            case ID_PROJECTION_TYPE /*30321*/:
            case ID_DEFAULT_DURATION /*2352003*/:
            case ID_TIMECODE_SCALE /*2807729*/:
                return 2;
            case 134:
            case 17026:
            case ID_NAME /*21358*/:
            case ID_LANGUAGE /*2274716*/:
                return 3;
            case ID_BLOCK_GROUP /*160*/:
            case ID_BLOCK_MORE /*166*/:
            case ID_TRACK_ENTRY /*174*/:
            case ID_CUE_TRACK_POSITIONS /*183*/:
            case ID_CUE_POINT /*187*/:
            case 224:
            case 225:
            case ID_BLOCK_ADDITION_MAPPING /*16868*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
            case ID_SEEK /*19899*/:
            case ID_CONTENT_COMPRESSION /*20532*/:
            case ID_CONTENT_ENCRYPTION /*20533*/:
            case ID_COLOUR /*21936*/:
            case ID_MASTERING_METADATA /*21968*/:
            case ID_CONTENT_ENCODING /*25152*/:
            case ID_CONTENT_ENCODINGS /*28032*/:
            case ID_BLOCK_ADDITIONS /*30113*/:
            case ID_PROJECTION /*30320*/:
            case ID_SEEK_HEAD /*290298740*/:
            case 357149030:
            case ID_TRACKS /*374648427*/:
            case ID_SEGMENT /*408125543*/:
            case ID_EBML /*440786851*/:
            case ID_CUES /*475249515*/:
            case ID_CLUSTER /*524531317*/:
                return 1;
            case ID_BLOCK /*161*/:
            case ID_SIMPLE_BLOCK /*163*/:
            case ID_BLOCK_ADDITIONAL /*165*/:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /*16877*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
            case ID_SEEK_ID /*21419*/:
            case ID_CODEC_PRIVATE /*25506*/:
            case ID_PROJECTION_PRIVATE /*30322*/:
                return 4;
            case ID_SAMPLING_FREQUENCY /*181*/:
            case ID_DURATION /*17545*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
            case ID_LUMNINANCE_MAX /*21977*/:
            case ID_LUMNINANCE_MIN /*21978*/:
            case ID_PROJECTION_POSE_YAW /*30323*/:
            case ID_PROJECTION_POSE_PITCH /*30324*/:
            case ID_PROJECTION_POSE_ROLL /*30325*/:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    public final void release() {
    }

    static /* synthetic */ Extractor[] lambda$newFactory$0(SubtitleParser.Factory factory) {
        return new Extractor[]{new MatroskaExtractor(factory)};
    }

    public static ExtractorsFactory newFactory(SubtitleParser.Factory factory) {
        return new MatroskaExtractor$$ExternalSyntheticLambda1(factory);
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", Integer.valueOf(RotationOptions.ROTATE_180));
        hashMap.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(hashMap);
    }

    static /* synthetic */ Extractor[] lambda$static$1() {
        return new Extractor[]{new MatroskaExtractor(SubtitleParser.Factory.UNSUPPORTED, 2)};
    }

    @Deprecated
    public MatroskaExtractor() {
        this(new DefaultEbmlReader(), 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    @Deprecated
    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i | 2, SubtitleParser.Factory.UNSUPPORTED);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory) {
        this(new DefaultEbmlReader(), 0, factory);
    }

    public MatroskaExtractor(SubtitleParser.Factory factory, int i) {
        this(new DefaultEbmlReader(), i, factory);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i, SubtitleParser.Factory factory) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.subtitleParserFactory = factory;
        boolean z = false;
        this.seekForCuesEnabled = (i & 1) == 0;
        this.parseSubtitlesDuringExtraction = (i & 2) == 0 ? true : z;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }

    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        if (this.parseSubtitlesDuringExtraction) {
            extractorOutput2 = new SubtitleTranscodingExtractorOutput(extractorOutput2, this.subtitleParserFactory);
        }
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            Track valueAt = this.tracks.valueAt(i);
            valueAt.assertOutputInitialized();
            valueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        assertInitialized();
        if (i == ID_BLOCK_GROUP) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(i).hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            getCurrentTrack(i).hasColorInfo = true;
        } else if (i == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 == -1 || j3 == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
                return;
            }
            throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", (Throwable) null);
        } else if (i == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i != ID_CLUSTER || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    /* access modifiers changed from: protected */
    public void endMasterElement(int i) throws ParserException {
        assertInitialized();
        if (i != ID_BLOCK_GROUP) {
            if (i == ID_TRACK_ENTRY) {
                Track track = (Track) Assertions.checkStateNotNull(this.currentTrack);
                if (track.codecId != null) {
                    if (isCodecSupported(track.codecId)) {
                        track.initializeOutput(this.extractorOutput, track.number);
                        this.tracks.put(track.number, track);
                    }
                    this.currentTrack = null;
                    return;
                }
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", (Throwable) null);
            } else if (i == ID_SEEK) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == ID_CUES) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", (Throwable) null);
            } else if (i == ID_CONTENT_ENCODING) {
                assertInTrackEntry(i);
                if (!this.currentTrack.hasContentEncryption) {
                    return;
                }
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", (Throwable) null);
            } else if (i == ID_CONTENT_ENCODINGS) {
                assertInTrackEntry(i);
                if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", (Throwable) null);
                }
            } else if (i == 357149030) {
                if (this.timecodeScale == C.TIME_UNSET) {
                    this.timecodeScale = 1000000;
                }
                long j2 = this.durationTimecode;
                if (j2 != C.TIME_UNSET) {
                    this.durationUs = scaleTimecodeToUs(j2);
                }
            } else if (i != ID_TRACKS) {
                if (i == ID_CUES) {
                    if (!this.sentSeekMap) {
                        this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                        this.sentSeekMap = true;
                    }
                    this.cueTimesUs = null;
                    this.cueClusterPositions = null;
                }
            } else if (this.tracks.size() != 0) {
                this.extractorOutput.endTracks();
            } else {
                throw ParserException.createForMalformedContainer("No valid tracks were found", (Throwable) null);
            }
        } else if (this.blockState == 2) {
            Track track2 = this.tracks.get(this.blockTrackNumber);
            track2.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track2.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.blockSampleCount; i4++) {
                i3 += this.blockSampleSizes[i4];
            }
            int i5 = 0;
            while (i5 < this.blockSampleCount) {
                long j3 = this.blockTimeUs + ((long) ((track2.defaultSampleDurationNs * i5) / 1000));
                int i6 = this.blockFlags;
                if (i5 == 0 && !this.blockHasReferenceBlock) {
                    i6 |= 1;
                }
                int i7 = this.blockSampleSizes[i5];
                int i8 = i3 - i7;
                commitSampleToOutput(track2, j3, i6, i7, i8);
                i5++;
                i3 = i8;
            }
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void integerElement(int i, long j) throws ParserException {
        if (i != ID_CONTENT_ENCODING_ORDER) {
            if (i != ID_CONTENT_ENCODING_SCOPE) {
                boolean z = false;
                switch (i) {
                    case ID_TRACK_TYPE /*131*/:
                        getCurrentTrack(i).type = (int) j;
                        return;
                    case 136:
                        Track currentTrack2 = getCurrentTrack(i);
                        if (j == 1) {
                            z = true;
                        }
                        currentTrack2.flagDefault = z;
                        return;
                    case ID_BLOCK_DURATION /*155*/:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case ID_CHANNELS /*159*/:
                        getCurrentTrack(i).channelCount = (int) j;
                        return;
                    case ID_PIXEL_WIDTH /*176*/:
                        getCurrentTrack(i).width = (int) j;
                        return;
                    case ID_CUE_TIME /*179*/:
                        assertInCues(i);
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case ID_PIXEL_HEIGHT /*186*/:
                        getCurrentTrack(i).height = (int) j;
                        return;
                    case 215:
                        getCurrentTrack(i).number = (int) j;
                        return;
                    case ID_TIME_CODE /*231*/:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case ID_BLOCK_ADD_ID /*238*/:
                        this.blockAdditionalId = (int) j;
                        return;
                    case ID_CUE_CLUSTER_POSITION /*241*/:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            assertInCues(i);
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case ID_REFERENCE_BLOCK /*251*/:
                        this.blockHasReferenceBlock = true;
                        return;
                    case ID_BLOCK_ADD_ID_TYPE /*16871*/:
                        int unused = getCurrentTrack(i).blockAddIdType = (int) j;
                        return;
                    case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                        if (j != 3) {
                            throw ParserException.createForMalformedContainer("ContentCompAlgo " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_DOC_TYPE_READ_VERSION /*17029*/:
                        if (j < 1 || j > 2) {
                            throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_EBML_READ_VERSION /*17143*/:
                        if (j != 1) {
                            throw ParserException.createForMalformedContainer("EBMLReadVersion " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                        if (j != 5) {
                            throw ParserException.createForMalformedContainer("ContentEncAlgo " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                        if (j != 1) {
                            throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_SEEK_POSITION /*21420*/:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case ID_STEREO_MODE /*21432*/:
                        int i2 = (int) j;
                        assertInTrackEntry(i);
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DISPLAY_WIDTH /*21680*/:
                        getCurrentTrack(i).displayWidth = (int) j;
                        return;
                    case ID_DISPLAY_UNIT /*21682*/:
                        getCurrentTrack(i).displayUnit = (int) j;
                        return;
                    case ID_DISPLAY_HEIGHT /*21690*/:
                        getCurrentTrack(i).displayHeight = (int) j;
                        return;
                    case ID_FLAG_FORCED /*21930*/:
                        Track currentTrack3 = getCurrentTrack(i);
                        if (j == 1) {
                            z = true;
                        }
                        currentTrack3.flagForced = z;
                        return;
                    case ID_COLOUR_BITS_PER_CHANNEL /*21938*/:
                        assertInTrackEntry(i);
                        this.currentTrack.hasColorInfo = true;
                        this.currentTrack.bitsPerChannel = (int) j;
                        return;
                    case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
                        getCurrentTrack(i).maxBlockAdditionId = (int) j;
                        return;
                    case ID_CODEC_DELAY /*22186*/:
                        getCurrentTrack(i).codecDelayNs = j;
                        return;
                    case ID_SEEK_PRE_ROLL /*22203*/:
                        getCurrentTrack(i).seekPreRollNs = j;
                        return;
                    case ID_AUDIO_BIT_DEPTH /*25188*/:
                        getCurrentTrack(i).audioBitDepth = (int) j;
                        return;
                    case ID_DISCARD_PADDING /*30114*/:
                        this.blockGroupDiscardPaddingNs = j;
                        return;
                    case ID_PROJECTION_TYPE /*30321*/:
                        assertInTrackEntry(i);
                        int i3 = (int) j;
                        if (i3 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i3 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i3 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i3 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DEFAULT_DURATION /*2352003*/:
                        getCurrentTrack(i).defaultSampleDurationNs = (int) j;
                        return;
                    case ID_TIMECODE_SCALE /*2807729*/:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case ID_COLOUR_RANGE /*21945*/:
                                assertInTrackEntry(i);
                                int i4 = (int) j;
                                if (i4 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i4 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_COLOUR_TRANSFER /*21946*/:
                                assertInTrackEntry(i);
                                int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j);
                                if (isoTransferCharacteristicsToColorTransfer != -1) {
                                    this.currentTrack.colorTransfer = isoTransferCharacteristicsToColorTransfer;
                                    return;
                                }
                                return;
                            case ID_COLOUR_PRIMARIES /*21947*/:
                                assertInTrackEntry(i);
                                this.currentTrack.hasColorInfo = true;
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j);
                                if (isoColorPrimariesToColorSpace != -1) {
                                    this.currentTrack.colorSpace = isoColorPrimariesToColorSpace;
                                    return;
                                }
                                return;
                            case ID_MAX_CLL /*21948*/:
                                getCurrentTrack(i).maxContentLuminance = (int) j;
                                return;
                            case ID_MAX_FALL /*21949*/:
                                getCurrentTrack(i).maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                throw ParserException.createForMalformedContainer("ContentEncodingScope " + j + " not supported", (Throwable) null);
            }
        } else if (j != 0) {
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j + " not supported", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void floatElement(int i, double d) throws ParserException {
        if (i == ID_SAMPLING_FREQUENCY) {
            getCurrentTrack(i).sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    getCurrentTrack(i).primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    getCurrentTrack(i).primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    getCurrentTrack(i).primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    getCurrentTrack(i).primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    getCurrentTrack(i).primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    getCurrentTrack(i).primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    getCurrentTrack(i).whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    getCurrentTrack(i).whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    getCurrentTrack(i).maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    getCurrentTrack(i).minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case ID_PROJECTION_POSE_YAW /*30323*/:
                            getCurrentTrack(i).projectionPoseYaw = (float) d;
                            return;
                        case ID_PROJECTION_POSE_PITCH /*30324*/:
                            getCurrentTrack(i).projectionPosePitch = (float) d;
                            return;
                        case ID_PROJECTION_POSE_ROLL /*30325*/:
                            getCurrentTrack(i).projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: protected */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            getCurrentTrack(i).codecId = str;
        } else if (i != 17026) {
            if (i == ID_NAME) {
                getCurrentTrack(i).name = str;
            } else if (i == ID_LANGUAGE) {
                String unused = getCurrentTrack(i).language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
        Track track;
        int i3;
        Track track2;
        Track track3;
        long j;
        int i4;
        byte b;
        int i5;
        int i6 = i;
        int i7 = i2;
        ExtractorInput extractorInput2 = extractorInput;
        int i8 = 0;
        int i9 = 1;
        if (i6 == ID_BLOCK || i6 == ID_SIMPLE_BLOCK) {
            if (this.blockState == 0) {
                this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput2, false, true, 8);
                this.blockTrackNumberLength = this.varintReader.getLastLength();
                this.blockDurationUs = C.TIME_UNSET;
                this.blockState = 1;
                this.scratch.reset(0);
            }
            Track track4 = this.tracks.get(this.blockTrackNumber);
            if (track4 == null) {
                extractorInput2.skipFully(i7 - this.blockTrackNumberLength);
                this.blockState = 0;
                return;
            }
            track4.assertOutputInitialized();
            if (this.blockState == 1) {
                readScratch(extractorInput2, 3);
                int i10 = (this.scratch.getData()[2] & 6) >> 1;
                byte b2 = 255;
                if (i10 == 0) {
                    this.blockSampleCount = 1;
                    int[] ensureArrayCapacity = ensureArrayCapacity(this.blockSampleSizes, 1);
                    this.blockSampleSizes = ensureArrayCapacity;
                    ensureArrayCapacity[0] = (i7 - this.blockTrackNumberLength) - 3;
                } else {
                    int i11 = 4;
                    readScratch(extractorInput2, 4);
                    int i12 = (this.scratch.getData()[3] & 255) + 1;
                    this.blockSampleCount = i12;
                    int[] ensureArrayCapacity2 = ensureArrayCapacity(this.blockSampleSizes, i12);
                    this.blockSampleSizes = ensureArrayCapacity2;
                    if (i10 == 2) {
                        int i13 = this.blockSampleCount;
                        Arrays.fill(ensureArrayCapacity2, 0, i13, ((i7 - this.blockTrackNumberLength) - 4) / i13);
                    } else if (i10 == 1) {
                        int i14 = 0;
                        int i15 = 0;
                        while (true) {
                            i4 = this.blockSampleCount;
                            if (i14 >= i4 - 1) {
                                break;
                            }
                            this.blockSampleSizes[i14] = 0;
                            do {
                                i11++;
                                readScratch(extractorInput2, i11);
                                b = this.scratch.getData()[i11 - 1] & 255;
                                int[] iArr = this.blockSampleSizes;
                                i5 = iArr[i14] + b;
                                iArr[i14] = i5;
                            } while (b == 255);
                            i15 += i5;
                            i14++;
                        }
                        this.blockSampleSizes[i4 - 1] = ((i7 - this.blockTrackNumberLength) - i11) - i15;
                    } else if (i10 == 3) {
                        int i16 = 0;
                        int i17 = 0;
                        while (true) {
                            int i18 = this.blockSampleCount;
                            if (i16 >= i18 - 1) {
                                track2 = track4;
                                this.blockSampleSizes[i18 - 1] = ((i7 - this.blockTrackNumberLength) - i11) - i17;
                                break;
                            }
                            this.blockSampleSizes[i16] = i8;
                            i11++;
                            readScratch(extractorInput2, i11);
                            int i19 = i11 - 1;
                            if (this.scratch.getData()[i19] != 0) {
                                int i20 = i8;
                                while (true) {
                                    if (i20 >= 8) {
                                        track3 = track4;
                                        j = 0;
                                        break;
                                    }
                                    int i21 = i9 << (7 - i20);
                                    if ((this.scratch.getData()[i19] & i21) != 0) {
                                        int i22 = i11 + i20;
                                        readScratch(extractorInput2, i22);
                                        track3 = track4;
                                        j = (long) ((~i21) & this.scratch.getData()[i19] & b2);
                                        int i23 = i19 + 1;
                                        while (i23 < i22) {
                                            j = (j << 8) | ((long) (this.scratch.getData()[i23] & b2));
                                            i23++;
                                            i22 = i22;
                                            b2 = 255;
                                        }
                                        int i24 = i22;
                                        if (i16 > 0) {
                                            j -= (1 << ((i20 * 7) + 6)) - 1;
                                        }
                                        i11 = i24;
                                    } else {
                                        Track track5 = track4;
                                        i20++;
                                        i9 = 1;
                                        b2 = 255;
                                    }
                                }
                                if (j >= -2147483648L && j <= 2147483647L) {
                                    int i25 = (int) j;
                                    int[] iArr2 = this.blockSampleSizes;
                                    if (i16 != 0) {
                                        i25 += iArr2[i16 - 1];
                                    }
                                    iArr2[i16] = i25;
                                    i17 += i25;
                                    i16++;
                                    track4 = track3;
                                    i8 = 0;
                                    i9 = 1;
                                    b2 = 255;
                                }
                            } else {
                                throw ParserException.createForMalformedContainer("No valid varint length mask found", (Throwable) null);
                            }
                        }
                        throw ParserException.createForMalformedContainer("EBML lacing sample size out of range.", (Throwable) null);
                    } else {
                        throw ParserException.createForMalformedContainer("Unexpected lacing value: " + i10, (Throwable) null);
                    }
                }
                track2 = track4;
                this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((long) ((this.scratch.getData()[0] << 8) | (this.scratch.getData()[1] & 255)));
                track = track2;
                this.blockFlags = (track.type == 2 || (i6 == ID_SIMPLE_BLOCK && (this.scratch.getData()[2] & 128) == 128)) ? 1 : 0;
                this.blockState = 2;
                this.blockSampleIndex = 0;
                i3 = ID_SIMPLE_BLOCK;
            } else {
                track = track4;
                i3 = ID_SIMPLE_BLOCK;
            }
            if (i6 == i3) {
                while (true) {
                    int i26 = this.blockSampleIndex;
                    if (i26 < this.blockSampleCount) {
                        commitSampleToOutput(track, ((long) ((this.blockSampleIndex * track.defaultSampleDurationNs) / 1000)) + this.blockTimeUs, this.blockFlags, writeSampleData(extractorInput2, track, this.blockSampleSizes[i26], false), 0);
                        this.blockSampleIndex++;
                    } else {
                        this.blockState = 0;
                        return;
                    }
                }
            } else {
                while (true) {
                    int i27 = this.blockSampleIndex;
                    if (i27 < this.blockSampleCount) {
                        int[] iArr3 = this.blockSampleSizes;
                        iArr3[i27] = writeSampleData(extractorInput2, track, iArr3[i27], true);
                        this.blockSampleIndex++;
                    } else {
                        return;
                    }
                }
            }
        } else if (i6 != ID_BLOCK_ADDITIONAL) {
            if (i6 == ID_BLOCK_ADD_ID_EXTRA_DATA) {
                handleBlockAddIDExtraData(getCurrentTrack(i), extractorInput2, i7);
            } else if (i6 == ID_CONTENT_COMPRESSION_SETTINGS) {
                assertInTrackEntry(i);
                this.currentTrack.sampleStrippedBytes = new byte[i7];
                extractorInput2.readFully(this.currentTrack.sampleStrippedBytes, 0, i7);
            } else if (i6 == ID_CONTENT_ENCRYPTION_KEY_ID) {
                byte[] bArr = new byte[i7];
                extractorInput2.readFully(bArr, 0, i7);
                getCurrentTrack(i).cryptoData = new TrackOutput.CryptoData(1, bArr, 0, 0);
            } else if (i6 == ID_SEEK_ID) {
                Arrays.fill(this.seekEntryIdBytes.getData(), (byte) 0);
                extractorInput2.readFully(this.seekEntryIdBytes.getData(), 4 - i7, i7);
                this.seekEntryIdBytes.setPosition(0);
                this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
            } else if (i6 == ID_CODEC_PRIVATE) {
                assertInTrackEntry(i);
                this.currentTrack.codecPrivate = new byte[i7];
                extractorInput2.readFully(this.currentTrack.codecPrivate, 0, i7);
            } else if (i6 == ID_PROJECTION_PRIVATE) {
                assertInTrackEntry(i);
                this.currentTrack.projectionData = new byte[i7];
                extractorInput2.readFully(this.currentTrack.projectionData, 0, i7);
            } else {
                throw ParserException.createForMalformedContainer("Unexpected id: " + i6, (Throwable) null);
            }
        } else if (this.blockState == 2) {
            handleBlockAdditionalData(this.tracks.get(this.blockTrackNumber), this.blockAdditionalId, extractorInput2, i7);
        }
    }

    /* access modifiers changed from: protected */
    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i) throws IOException {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            track.dolbyVisionConfigBytes = new byte[i];
            extractorInput.readFully(track.dolbyVisionConfigBytes, 0, i);
            return;
        }
        extractorInput.skipFully(i);
    }

    /* access modifiers changed from: protected */
    public void handleBlockAdditionalData(Track track, int i, ExtractorInput extractorInput, int i2) throws IOException {
        if (i != 4 || !CODEC_ID_VP9.equals(track.codecId)) {
            extractorInput.skipFully(i2);
            return;
        }
        this.supplementalData.reset(i2);
        extractorInput.readFully(this.supplementalData.getData(), 0, i2);
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int i) throws ParserException {
        if (this.currentTrack == null) {
            throw ParserException.createForMalformedContainer("Element " + i + " must be in a TrackEntry", (Throwable) null);
        }
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private void assertInCues(int i) throws ParserException {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + i + " must be in a Cues", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public Track getCurrentTrack(int i) throws ParserException {
        assertInTrackEntry(i);
        return this.currentTrack;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009d  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor.Track r13, long r14, int r16, int r17, int r18) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            androidx.media3.extractor.TrueHdSampleRechunker r2 = r1.trueHdSampleRechunker
            r3 = 1
            if (r2 == 0) goto L_0x0019
            androidx.media3.extractor.TrueHdSampleRechunker r4 = r1.trueHdSampleRechunker
            androidx.media3.extractor.TrackOutput r5 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r9 = r17
            r10 = r18
            r4.sampleMetadata(r5, r6, r8, r9, r10, r11)
            goto L_0x00c4
        L_0x0019:
            java.lang.String r2 = "S_TEXT/UTF8"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0037
            java.lang.String r2 = "S_TEXT/ASS"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0037
            java.lang.String r2 = "S_TEXT/WEBVTT"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0053
        L_0x0037:
            int r2 = r0.blockSampleCount
            java.lang.String r4 = "MatroskaExtractor"
            if (r2 <= r3) goto L_0x0043
            java.lang.String r2 = "Skipping subtitle sample in laced block."
            androidx.media3.common.util.Log.w(r4, r2)
            goto L_0x0053
        L_0x0043:
            long r5 = r0.blockDurationUs
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0056
            java.lang.String r2 = "Skipping subtitle sample with no duration."
            androidx.media3.common.util.Log.w(r4, r2)
        L_0x0053:
            r2 = r17
            goto L_0x0097
        L_0x0056:
            java.lang.String r2 = r1.codecId
            long r4 = r0.blockDurationUs
            androidx.media3.common.util.ParsableByteArray r6 = r0.subtitleSample
            byte[] r6 = r6.getData()
            setSubtitleEndTime(r2, r4, r6)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.getPosition()
        L_0x0069:
            androidx.media3.common.util.ParsableByteArray r4 = r0.subtitleSample
            int r4 = r4.limit()
            if (r2 >= r4) goto L_0x0084
            androidx.media3.common.util.ParsableByteArray r4 = r0.subtitleSample
            byte[] r4 = r4.getData()
            byte r4 = r4[r2]
            if (r4 != 0) goto L_0x0081
            androidx.media3.common.util.ParsableByteArray r4 = r0.subtitleSample
            r4.setLimit(r2)
            goto L_0x0084
        L_0x0081:
            int r2 = r2 + 1
            goto L_0x0069
        L_0x0084:
            androidx.media3.extractor.TrackOutput r2 = r1.output
            androidx.media3.common.util.ParsableByteArray r4 = r0.subtitleSample
            int r5 = r4.limit()
            r2.sampleData(r4, r5)
            androidx.media3.common.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.limit()
            int r2 = r17 + r2
        L_0x0097:
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            r4 = r16 & r4
            if (r4 == 0) goto L_0x00b7
            int r4 = r0.blockSampleCount
            if (r4 <= r3) goto L_0x00a8
            androidx.media3.common.util.ParsableByteArray r4 = r0.supplementalData
            r5 = 0
            r4.reset((int) r5)
            goto L_0x00b7
        L_0x00a8:
            androidx.media3.common.util.ParsableByteArray r4 = r0.supplementalData
            int r4 = r4.limit()
            androidx.media3.extractor.TrackOutput r5 = r1.output
            androidx.media3.common.util.ParsableByteArray r6 = r0.supplementalData
            r7 = 2
            r5.sampleData((androidx.media3.common.util.ParsableByteArray) r6, (int) r4, (int) r7)
            int r2 = r2 + r4
        L_0x00b7:
            r9 = r2
            androidx.media3.extractor.TrackOutput r5 = r1.output
            androidx.media3.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r10 = r18
            r5.sampleMetadata(r6, r8, r9, r10, r11)
        L_0x00c4:
            r0.haveOutputSample = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.commitSampleToOutput(androidx.media3.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i));
            }
            extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int i, boolean z) throws IOException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
            return finishWriteSampleData();
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
            return finishWriteSampleData();
        } else if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            boolean z2 = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.getData(), 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.getData()[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.getData()[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", (Throwable) null);
                        }
                    }
                    byte b = this.sampleSignalByte;
                    if ((b & 1) == 1) {
                        boolean z3 = (b & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] data = this.scratch.getData();
                            if (!z3) {
                                i3 = 0;
                            }
                            data[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                            this.sampleBytesWritten += 8;
                        }
                        if (z3) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.getData(), 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.getData(), 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5, 1);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                if (track.samplesHaveSupplementalData(z)) {
                    this.blockFlags |= 268435456;
                    this.supplementalData.reset(0);
                    int limit = (this.sampleStrippedBytes.limit() + i) - this.sampleBytesRead;
                    this.scratch.reset(4);
                    this.scratch.getData()[0] = (byte) ((limit >> 24) & 255);
                    this.scratch.getData()[1] = (byte) ((limit >> 16) & 255);
                    this.scratch.getData()[2] = (byte) ((limit >> 8) & 255);
                    this.scratch.getData()[3] = (byte) (limit & 255);
                    trackOutput.sampleData(this.scratch, 4, 2);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit2 = i + this.sampleStrippedBytes.limit();
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit2) {
                        break;
                    }
                    int writeToOutput = writeToOutput(extractorInput, trackOutput, limit2 - i9);
                    this.sampleBytesRead += writeToOutput;
                    this.sampleBytesWritten += writeToOutput;
                }
            } else {
                byte[] data2 = this.nalLength.getData();
                data2[0] = 0;
                data2[1] = 0;
                data2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit2) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        writeToTarget(extractorInput, data2, i11, i10);
                        this.sampleBytesRead += i10;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput2 = writeToOutput(extractorInput, trackOutput, i12);
                        this.sampleBytesRead += writeToOutput2;
                        this.sampleBytesWritten += writeToOutput2;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput2;
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + i));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private static void setSubtitleEndTime(String str, long j, byte[] bArr) {
        int i;
        byte[] bArr2;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c = 0;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c = 1;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                bArr2 = formatSubtitleTimecode(j, SSA_TIMECODE_FORMAT, 10000);
                i = 21;
                break;
            case 1:
                bArr2 = formatSubtitleTimecode(j, VTT_TIMECODE_FORMAT, 1000);
                i = 25;
                break;
            case 2:
                bArr2 = formatSubtitleTimecode(j, SUBRIP_TIMECODE_FORMAT, 1000);
                i = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
    }

    private static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Assertions.checkArgument(j != C.TIME_UNSET);
        int i = (int) (j / 3600000000L);
        long j3 = j - ((((long) i) * 3600) * 1000000);
        int i2 = (int) (j3 / 60000000);
        long j4 = j3 - ((((long) i2) * 60) * 1000000);
        int i3 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - (((long) i3) * 1000000)) / j2))}));
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i, false);
        }
        int min = Math.min(i, bytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, min);
        return min;
    }

    private SeekMap buildSeekMap(LongArray longArray, LongArray longArray2) {
        int i;
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || longArray == null || longArray.size() == 0 || longArray2 == null || longArray2.size() != longArray.size()) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = longArray.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jArr3[i3] = longArray.get(i3);
            jArr[i3] = this.segmentContentPosition + longArray2.get(i3);
        }
        while (true) {
            i = size - 1;
            if (i2 >= i) {
                break;
            }
            int i4 = i2 + 1;
            iArr[i2] = (int) (jArr[i4] - jArr[i2]);
            jArr2[i2] = jArr3[i4] - jArr3[i2];
            i2 = i4;
        }
        int i5 = i;
        while (i5 > 0 && jArr3[i5] > this.durationUs) {
            i5--;
        }
        iArr[i5] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i5]);
        jArr2[i5] = this.durationUs - jArr3[i5];
        if (i5 < i) {
            Log.w(TAG, "Discarding trailing cue points with timestamps greater than total duration");
            int i6 = i5 + 1;
            iArr = Arrays.copyOf(iArr, i6);
            jArr = Arrays.copyOf(jArr, i6);
            jArr2 = Arrays.copyOf(jArr2, i6);
            jArr3 = Arrays.copyOf(jArr3, i6);
        }
        return new ChunkIndex(iArr, jArr, jArr2, jArr3);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", (Throwable) null);
    }

    private static boolean isCodecSupported(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals(CODEC_ID_MPEG4_AP)) {
                    c = 0;
                    break;
                }
                break;
            case -2095575984:
                if (str.equals(CODEC_ID_MPEG4_SP)) {
                    c = 1;
                    break;
                }
                break;
            case -1985379776:
                if (str.equals(CODEC_ID_ACM)) {
                    c = 2;
                    break;
                }
                break;
            case -1784763192:
                if (str.equals(CODEC_ID_TRUEHD)) {
                    c = 3;
                    break;
                }
                break;
            case -1730367663:
                if (str.equals(CODEC_ID_VORBIS)) {
                    c = 4;
                    break;
                }
                break;
            case -1482641358:
                if (str.equals(CODEC_ID_MP2)) {
                    c = 5;
                    break;
                }
                break;
            case -1482641357:
                if (str.equals(CODEC_ID_MP3)) {
                    c = 6;
                    break;
                }
                break;
            case -1373388978:
                if (str.equals(CODEC_ID_FOURCC)) {
                    c = 7;
                    break;
                }
                break;
            case -933872740:
                if (str.equals(CODEC_ID_DVBSUB)) {
                    c = 8;
                    break;
                }
                break;
            case -538363189:
                if (str.equals(CODEC_ID_MPEG4_ASP)) {
                    c = 9;
                    break;
                }
                break;
            case -538363109:
                if (str.equals(CODEC_ID_H264)) {
                    c = 10;
                    break;
                }
                break;
            case -425012669:
                if (str.equals(CODEC_ID_VOBSUB)) {
                    c = 11;
                    break;
                }
                break;
            case -356037306:
                if (str.equals(CODEC_ID_DTS_LOSSLESS)) {
                    c = 12;
                    break;
                }
                break;
            case 62923557:
                if (str.equals(CODEC_ID_AAC)) {
                    c = 13;
                    break;
                }
                break;
            case 62923603:
                if (str.equals(CODEC_ID_AC3)) {
                    c = 14;
                    break;
                }
                break;
            case 62927045:
                if (str.equals(CODEC_ID_DTS)) {
                    c = 15;
                    break;
                }
                break;
            case 82318131:
                if (str.equals(CODEC_ID_AV1)) {
                    c = 16;
                    break;
                }
                break;
            case 82338133:
                if (str.equals(CODEC_ID_VP8)) {
                    c = 17;
                    break;
                }
                break;
            case 82338134:
                if (str.equals(CODEC_ID_VP9)) {
                    c = 18;
                    break;
                }
                break;
            case 99146302:
                if (str.equals(CODEC_ID_PGS)) {
                    c = 19;
                    break;
                }
                break;
            case 444813526:
                if (str.equals(CODEC_ID_THEORA)) {
                    c = 20;
                    break;
                }
                break;
            case 542569478:
                if (str.equals(CODEC_ID_DTS_EXPRESS)) {
                    c = 21;
                    break;
                }
                break;
            case 635596514:
                if (str.equals(CODEC_ID_PCM_FLOAT)) {
                    c = 22;
                    break;
                }
                break;
            case 725948237:
                if (str.equals(CODEC_ID_PCM_INT_BIG)) {
                    c = 23;
                    break;
                }
                break;
            case 725957860:
                if (str.equals(CODEC_ID_PCM_INT_LIT)) {
                    c = 24;
                    break;
                }
                break;
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c = 25;
                    break;
                }
                break;
            case 855502857:
                if (str.equals(CODEC_ID_H265)) {
                    c = 26;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c = 27;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c = 28;
                    break;
                }
                break;
            case 1809237540:
                if (str.equals(CODEC_ID_MPEG2)) {
                    c = 29;
                    break;
                }
                break;
            case 1950749482:
                if (str.equals(CODEC_ID_E_AC3)) {
                    c = 30;
                    break;
                }
                break;
            case 1950789798:
                if (str.equals(CODEC_ID_FLAC)) {
                    c = 31;
                    break;
                }
                break;
            case 1951062397:
                if (str.equals(CODEC_ID_OPUS)) {
                    c = ' ';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return true;
            default:
                return false;
        }
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    protected static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth = -1;
        public int bitsPerChannel = -1;
        /* access modifiers changed from: private */
        public int blockAddIdType;
        public int channelCount = 1;
        public long codecDelayNs = 0;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange = -1;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int displayWidth = -1;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagDefault = true;
        public boolean flagForced;
        public boolean hasColorInfo = false;
        public boolean hasContentEncryption;
        public int height = -1;
        /* access modifiers changed from: private */
        public String language = "eng";
        public int maxBlockAdditionId;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public byte[] projectionData = null;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public float projectionPoseYaw = 0.0f;
        public int projectionType = -1;
        public int sampleRate = 8000;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs = 0;
        public int stereoMode = -1;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public int width = -1;

        protected Track() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v14, resolved type: java.lang.String} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x029f, code lost:
            r1 = null;
            r3 = null;
            r4 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x0300, code lost:
            r1 = null;
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x0302, code lost:
            r4 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x0303, code lost:
            r9 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x0358, code lost:
            r4 = -1;
            r9 = -1;
            r19 = r3;
            r3 = r1;
            r1 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x038d, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:151:0x0395, code lost:
            r1 = null;
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x03a6, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:154:0x03a7, code lost:
            r4 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x03ed, code lost:
            r1 = null;
            r3 = null;
            r17 = androidx.media3.common.MimeTypes.AUDIO_UNKNOWN;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x0403, code lost:
            if (r0.dolbyVisionConfigBytes == null) goto L_0x0417;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x0405, code lost:
            r6 = androidx.media3.extractor.DolbyVisionConfig.parse(new androidx.media3.common.util.ParsableByteArray(r0.dolbyVisionConfigBytes));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x0410, code lost:
            if (r6 == null) goto L_0x0417;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x0412, code lost:
            r3 = r6.codecs;
            r17 = androidx.media3.common.MimeTypes.VIDEO_DOLBY_VISION;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x0417, code lost:
            r6 = r17;
            r7 = r0.flagDefault | 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x041f, code lost:
            if (r0.flagForced == false) goto L_0x0423;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x0421, code lost:
            r12 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x0423, code lost:
            r12 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x0424, code lost:
            r7 = r7 | r12;
            r12 = new androidx.media3.common.Format.Builder();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x042e, code lost:
            if (androidx.media3.common.MimeTypes.isAudio(r6) == false) goto L_0x0442;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x0430, code lost:
            r12.setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setPcmEncoding(r9);
            r5 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:181:0x0446, code lost:
            if (androidx.media3.common.MimeTypes.isVideo(r6) == false) goto L_0x053b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x044a, code lost:
            if (r0.displayUnit != 0) goto L_0x045e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x044c, code lost:
            r2 = r0.displayWidth;
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x044f, code lost:
            if (r2 != -1) goto L_0x0453;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0451, code lost:
            r2 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0453, code lost:
            r0.displayWidth = r2;
            r2 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x0457, code lost:
            if (r2 != -1) goto L_0x045b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x0459, code lost:
            r2 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x045b, code lost:
            r0.displayHeight = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x045e, code lost:
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x045f, code lost:
            r2 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x0461, code lost:
            if (r2 == r5) goto L_0x0471;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x0463, code lost:
            r9 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x0465, code lost:
            if (r9 == r5) goto L_0x0471;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x0467, code lost:
            r2 = ((float) (r0.height * r2)) / ((float) (r0.width * r9));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:197:0x0471, code lost:
            r2 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:199:0x0475, code lost:
            if (r0.hasColorInfo == false) goto L_0x04a6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x0477, code lost:
            r11 = new androidx.media3.common.ColorInfo.Builder().setColorSpace(r0.colorSpace).setColorRange(r0.colorRange).setColorTransfer(r0.colorTransfer).setHdrStaticInfo(getHdrStaticInfo()).setLumaBitdepth(r0.bitsPerChannel).setChromaBitdepth(r0.bitsPerChannel).build();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x04a8, code lost:
            if (r0.name == null) goto L_0x04c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x04b4, code lost:
            if (androidx.media3.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) == false) goto L_0x04c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:205:0x04b6, code lost:
            r5 = ((java.lang.Integer) androidx.media3.extractor.mkv.MatroskaExtractor.access$600().get(r0.name)).intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:207:0x04c8, code lost:
            if (r0.projectionType != 0) goto L_0x0516;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x04d1, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x0516;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:211:0x04d9, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x0516;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x04e1, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x04e5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:214:0x04e3, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:216:0x04ed, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 90.0f) != 0) goto L_0x04f2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:217:0x04ef, code lost:
            r5 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:219:0x04fa, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, -180.0f) == 0) goto L_0x0514;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x0504, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 180.0f) != 0) goto L_0x0507;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x050f, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, -90.0f) != 0) goto L_0x0516;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:224:0x0511, code lost:
            r5 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:225:0x0514, code lost:
            r5 = com.facebook.imagepipeline.common.RotationOptions.ROTATE_180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:226:0x0516, code lost:
            r12.setWidth(r0.width).setHeight(r0.height).setPixelWidthHeightRatio(r2).setRotationDegrees(r5).setProjectionData(r0.projectionData).setStereoMode(r0.stereoMode).setColorInfo(r11);
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:228:0x053f, code lost:
            if (androidx.media3.common.MimeTypes.APPLICATION_SUBRIP.equals(r6) != false) goto L_0x0569;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:230:0x0545, code lost:
            if (androidx.media3.common.MimeTypes.TEXT_SSA.equals(r6) != false) goto L_0x0569;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:232:0x054b, code lost:
            if (androidx.media3.common.MimeTypes.TEXT_VTT.equals(r6) != false) goto L_0x0569;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:234:0x0551, code lost:
            if (androidx.media3.common.MimeTypes.APPLICATION_VOBSUB.equals(r6) != false) goto L_0x0569;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:236:0x0557, code lost:
            if (androidx.media3.common.MimeTypes.APPLICATION_PGS.equals(r6) != false) goto L_0x0569;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:238:0x055f, code lost:
            if (androidx.media3.common.MimeTypes.APPLICATION_DVBSUBS.equals(r6) == false) goto L_0x0562;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:240:0x0568, code lost:
            throw androidx.media3.common.ParserException.createForMalformedContainer("Unexpected MIME type.", (java.lang.Throwable) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:241:0x0569, code lost:
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:243:0x056c, code lost:
            if (r0.name == null) goto L_0x057f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:245:0x0578, code lost:
            if (androidx.media3.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) != false) goto L_0x057f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:246:0x057a, code lost:
            r12.setLabel(r0.name);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:247:0x057f, code lost:
            r1 = r12.setId(r22).setSampleMimeType(r6).setMaxInputSize(r4).setLanguage(r0.language).setSelectionFlags(r7).setInitializationData(r1).setCodecs(r3).setDrmInitData(r0.drmInitData).build();
            r2 = r21.track(r0.number, r5);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:248:0x05b6, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
        @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"this.output"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(androidx.media3.extractor.ExtractorOutput r21, int r22) throws androidx.media3.common.ParserException {
            /*
                r20 = this;
                r0 = r20
                java.lang.String r1 = r0.codecId
                r1.hashCode()
                int r2 = r1.hashCode()
                r3 = 24
                r4 = 16
                r6 = 32
                r8 = 8
                r9 = 4
                r10 = 3
                switch(r2) {
                    case -2095576542: goto L_0x01bf;
                    case -2095575984: goto L_0x01b3;
                    case -1985379776: goto L_0x01a7;
                    case -1784763192: goto L_0x019b;
                    case -1730367663: goto L_0x018f;
                    case -1482641358: goto L_0x0183;
                    case -1482641357: goto L_0x0177;
                    case -1373388978: goto L_0x016b;
                    case -933872740: goto L_0x015e;
                    case -538363189: goto L_0x0150;
                    case -538363109: goto L_0x0142;
                    case -425012669: goto L_0x0134;
                    case -356037306: goto L_0x0126;
                    case 62923557: goto L_0x0118;
                    case 62923603: goto L_0x010a;
                    case 62927045: goto L_0x00fc;
                    case 82318131: goto L_0x00ef;
                    case 82338133: goto L_0x00e1;
                    case 82338134: goto L_0x00d3;
                    case 99146302: goto L_0x00c5;
                    case 444813526: goto L_0x00b7;
                    case 542569478: goto L_0x00a9;
                    case 635596514: goto L_0x009b;
                    case 725948237: goto L_0x008e;
                    case 725957860: goto L_0x0082;
                    case 738597099: goto L_0x0075;
                    case 855502857: goto L_0x0068;
                    case 1045209816: goto L_0x005b;
                    case 1422270023: goto L_0x004e;
                    case 1809237540: goto L_0x0041;
                    case 1950749482: goto L_0x0034;
                    case 1950789798: goto L_0x0027;
                    case 1951062397: goto L_0x001b;
                    default: goto L_0x0018;
                }
            L_0x0018:
                r1 = -1
                goto L_0x01ca
            L_0x001b:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0024
                goto L_0x0018
            L_0x0024:
                r1 = r6
                goto L_0x01ca
            L_0x0027:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0030
                goto L_0x0018
            L_0x0030:
                r1 = 31
                goto L_0x01ca
            L_0x0034:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x003d
                goto L_0x0018
            L_0x003d:
                r1 = 30
                goto L_0x01ca
            L_0x0041:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x004a
                goto L_0x0018
            L_0x004a:
                r1 = 29
                goto L_0x01ca
            L_0x004e:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0057
                goto L_0x0018
            L_0x0057:
                r1 = 28
                goto L_0x01ca
            L_0x005b:
                java.lang.String r2 = "S_TEXT/WEBVTT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0064
                goto L_0x0018
            L_0x0064:
                r1 = 27
                goto L_0x01ca
            L_0x0068:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0071
                goto L_0x0018
            L_0x0071:
                r1 = 26
                goto L_0x01ca
            L_0x0075:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x007e
                goto L_0x0018
            L_0x007e:
                r1 = 25
                goto L_0x01ca
            L_0x0082:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x008b
                goto L_0x0018
            L_0x008b:
                r1 = r3
                goto L_0x01ca
            L_0x008e:
                java.lang.String r2 = "A_PCM/INT/BIG"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0097
                goto L_0x0018
            L_0x0097:
                r1 = 23
                goto L_0x01ca
            L_0x009b:
                java.lang.String r2 = "A_PCM/FLOAT/IEEE"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00a5
                goto L_0x0018
            L_0x00a5:
                r1 = 22
                goto L_0x01ca
            L_0x00a9:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00b3
                goto L_0x0018
            L_0x00b3:
                r1 = 21
                goto L_0x01ca
            L_0x00b7:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00c1
                goto L_0x0018
            L_0x00c1:
                r1 = 20
                goto L_0x01ca
            L_0x00c5:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00cf
                goto L_0x0018
            L_0x00cf:
                r1 = 19
                goto L_0x01ca
            L_0x00d3:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00dd
                goto L_0x0018
            L_0x00dd:
                r1 = 18
                goto L_0x01ca
            L_0x00e1:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00eb
                goto L_0x0018
            L_0x00eb:
                r1 = 17
                goto L_0x01ca
            L_0x00ef:
                java.lang.String r2 = "V_AV1"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00f9
                goto L_0x0018
            L_0x00f9:
                r1 = r4
                goto L_0x01ca
            L_0x00fc:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0106
                goto L_0x0018
            L_0x0106:
                r1 = 15
                goto L_0x01ca
            L_0x010a:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0114
                goto L_0x0018
            L_0x0114:
                r1 = 14
                goto L_0x01ca
            L_0x0118:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0122
                goto L_0x0018
            L_0x0122:
                r1 = 13
                goto L_0x01ca
            L_0x0126:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0130
                goto L_0x0018
            L_0x0130:
                r1 = 12
                goto L_0x01ca
            L_0x0134:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x013e
                goto L_0x0018
            L_0x013e:
                r1 = 11
                goto L_0x01ca
            L_0x0142:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x014c
                goto L_0x0018
            L_0x014c:
                r1 = 10
                goto L_0x01ca
            L_0x0150:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x015a
                goto L_0x0018
            L_0x015a:
                r1 = 9
                goto L_0x01ca
            L_0x015e:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0168
                goto L_0x0018
            L_0x0168:
                r1 = r8
                goto L_0x01ca
            L_0x016b:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0175
                goto L_0x0018
            L_0x0175:
                r1 = 7
                goto L_0x01ca
            L_0x0177:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0181
                goto L_0x0018
            L_0x0181:
                r1 = 6
                goto L_0x01ca
            L_0x0183:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x018d
                goto L_0x0018
            L_0x018d:
                r1 = 5
                goto L_0x01ca
            L_0x018f:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0199
                goto L_0x0018
            L_0x0199:
                r1 = r9
                goto L_0x01ca
            L_0x019b:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01a5
                goto L_0x0018
            L_0x01a5:
                r1 = r10
                goto L_0x01ca
            L_0x01a7:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01b1
                goto L_0x0018
            L_0x01b1:
                r1 = 2
                goto L_0x01ca
            L_0x01b3:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01bd
                goto L_0x0018
            L_0x01bd:
                r1 = 1
                goto L_0x01ca
            L_0x01bf:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01c9
                goto L_0x0018
            L_0x01c9:
                r1 = 0
            L_0x01ca:
                java.lang.String r2 = "application/pgs"
                java.lang.String r13 = "application/vobsub"
                java.lang.String r14 = "text/vtt"
                java.lang.String r15 = "text/x-ssa"
                java.lang.String r5 = "application/x-subrip"
                r16 = 4096(0x1000, float:5.74E-42)
                java.lang.String r7 = ". Setting mimeType to audio/x-unknown"
                java.lang.String r17 = "audio/raw"
                java.lang.String r12 = "MatroskaExtractor"
                java.lang.String r18 = "audio/x-unknown"
                r11 = 0
                switch(r1) {
                    case 0: goto L_0x03f3;
                    case 1: goto L_0x03f3;
                    case 2: goto L_0x03b6;
                    case 3: goto L_0x03ab;
                    case 4: goto L_0x0398;
                    case 5: goto L_0x0393;
                    case 6: goto L_0x0390;
                    case 7: goto L_0x0374;
                    case 8: goto L_0x0361;
                    case 9: goto L_0x03f3;
                    case 10: goto L_0x033e;
                    case 11: goto L_0x0330;
                    case 12: goto L_0x032d;
                    case 13: goto L_0x0310;
                    case 14: goto L_0x030d;
                    case 15: goto L_0x030a;
                    case 16: goto L_0x0306;
                    case 17: goto L_0x02fd;
                    case 18: goto L_0x02f9;
                    case 19: goto L_0x02f6;
                    case 20: goto L_0x02f2;
                    case 21: goto L_0x030a;
                    case 22: goto L_0x02d3;
                    case 23: goto L_0x02a4;
                    case 24: goto L_0x027d;
                    case 25: goto L_0x026a;
                    case 26: goto L_0x024e;
                    case 27: goto L_0x0248;
                    case 28: goto L_0x0244;
                    case 29: goto L_0x023f;
                    case 30: goto L_0x023b;
                    case 31: goto L_0x022d;
                    case 32: goto L_0x01eb;
                    default: goto L_0x01e4;
                }
            L_0x01e4:
                java.lang.String r1 = "Unrecognized codec identifier."
                androidx.media3.common.ParserException r1 = androidx.media3.common.ParserException.createForMalformedContainer(r1, r11)
                throw r1
            L_0x01eb:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r10)
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r8)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r6 = r0.codecDelayNs
                java.nio.ByteBuffer r3 = r3.putLong(r6)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r8)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r6 = r0.seekPreRollNs
                java.nio.ByteBuffer r3 = r3.putLong(r6)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.lang.String r17 = "audio/opus"
                r16 = 5760(0x1680, float:8.071E-42)
                goto L_0x03a6
            L_0x022d:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r17 = "audio/flac"
                goto L_0x038d
            L_0x023b:
                java.lang.String r17 = "audio/eac3"
                goto L_0x0300
            L_0x023f:
                java.lang.String r17 = "video/mpeg2"
                goto L_0x0300
            L_0x0244:
                r17 = r5
                goto L_0x0300
            L_0x0248:
                r1 = r11
                r3 = r1
                r17 = r14
                goto L_0x0302
            L_0x024e:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                androidx.media3.extractor.HevcConfig r1 = androidx.media3.extractor.HevcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/hevc"
                goto L_0x0358
            L_0x026a:
                byte[] r1 = androidx.media3.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1, r3)
                r3 = r11
                r17 = r15
                goto L_0x0302
            L_0x027d:
                int r1 = r0.audioBitDepth
                int r9 = androidx.media3.common.util.Util.getPcmEncoding(r1)
                if (r9 != 0) goto L_0x029f
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported little endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.StringBuilder r1 = r1.append(r7)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r12, r1)
                goto L_0x03ed
            L_0x029f:
                r1 = r11
                r3 = r1
                r4 = -1
                goto L_0x0401
            L_0x02a4:
                int r1 = r0.audioBitDepth
                if (r1 != r8) goto L_0x02aa
                r9 = r10
                goto L_0x029f
            L_0x02aa:
                if (r1 != r4) goto L_0x02af
                r9 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x029f
            L_0x02af:
                if (r1 != r3) goto L_0x02b4
                r9 = 1342177280(0x50000000, float:8.5899346E9)
                goto L_0x029f
            L_0x02b4:
                if (r1 != r6) goto L_0x02b9
                r9 = 1610612736(0x60000000, float:3.6893488E19)
                goto L_0x029f
            L_0x02b9:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported big endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.StringBuilder r1 = r1.append(r7)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r12, r1)
                goto L_0x03ed
            L_0x02d3:
                int r1 = r0.audioBitDepth
                if (r1 != r6) goto L_0x02d8
                goto L_0x029f
            L_0x02d8:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported floating point PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.StringBuilder r1 = r1.append(r7)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r12, r1)
                goto L_0x03ed
            L_0x02f2:
                java.lang.String r17 = "video/x-unknown"
                goto L_0x0300
            L_0x02f6:
                r17 = r2
                goto L_0x0300
            L_0x02f9:
                java.lang.String r17 = "video/x-vnd.on2.vp9"
                goto L_0x0300
            L_0x02fd:
                java.lang.String r17 = "video/x-vnd.on2.vp8"
            L_0x0300:
                r1 = r11
                r3 = r1
            L_0x0302:
                r4 = -1
            L_0x0303:
                r9 = -1
                goto L_0x0401
            L_0x0306:
                java.lang.String r17 = "video/av01"
                goto L_0x0300
            L_0x030a:
                java.lang.String r17 = "audio/vnd.dts"
                goto L_0x0300
            L_0x030d:
                java.lang.String r17 = "audio/ac3"
                goto L_0x0300
            L_0x0310:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                byte[] r3 = r0.codecPrivate
                androidx.media3.extractor.AacUtil$Config r3 = androidx.media3.extractor.AacUtil.parseAudioSpecificConfig(r3)
                int r4 = r3.sampleRateHz
                r0.sampleRate = r4
                int r4 = r3.channelCount
                r0.channelCount = r4
                java.lang.String r3 = r3.codecs
                java.lang.String r17 = "audio/mp4a-latm"
                goto L_0x0302
            L_0x032d:
                java.lang.String r17 = "audio/vnd.dts.hd"
                goto L_0x0300
            L_0x0330:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1)
                r3 = r11
                r17 = r13
                goto L_0x0302
            L_0x033e:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                androidx.media3.extractor.AvcConfig r1 = androidx.media3.extractor.AvcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/avc"
            L_0x0358:
                r4 = -1
                r9 = -1
                r19 = r3
                r3 = r1
                r1 = r19
                goto L_0x0401
            L_0x0361:
                byte[] r1 = new byte[r9]
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r4 = 0
                java.lang.System.arraycopy(r3, r4, r1, r4, r9)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1)
                java.lang.String r17 = "application/dvbsubs"
                goto L_0x038d
            L_0x0374:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r3 = r1.first
                r17 = r3
                java.lang.String r17 = (java.lang.String) r17
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
            L_0x038d:
                r3 = r11
                goto L_0x0302
            L_0x0390:
                java.lang.String r17 = "audio/mpeg"
                goto L_0x0395
            L_0x0393:
                java.lang.String r17 = "audio/mpeg-L2"
            L_0x0395:
                r1 = r11
                r3 = r1
                goto L_0x03a7
            L_0x0398:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r17 = "audio/vorbis"
                r16 = 8192(0x2000, float:1.14794E-41)
            L_0x03a6:
                r3 = r11
            L_0x03a7:
                r4 = r16
                goto L_0x0303
            L_0x03ab:
                androidx.media3.extractor.TrueHdSampleRechunker r1 = new androidx.media3.extractor.TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r17 = "audio/true-hd"
                goto L_0x0300
            L_0x03b6:
                androidx.media3.common.util.ParsableByteArray r1 = new androidx.media3.common.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x03e8
                int r1 = r0.audioBitDepth
                int r9 = androidx.media3.common.util.Util.getPcmEncoding(r1)
                if (r9 != 0) goto L_0x029f
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.StringBuilder r1 = r1.append(r7)
                java.lang.String r1 = r1.toString()
                androidx.media3.common.util.Log.w(r12, r1)
                goto L_0x03ed
            L_0x03e8:
                java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown"
                androidx.media3.common.util.Log.w(r12, r1)
            L_0x03ed:
                r1 = r11
                r3 = r1
                r17 = r18
                goto L_0x0302
            L_0x03f3:
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x03f9
                r1 = r11
                goto L_0x03fd
            L_0x03f9:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x03fd:
                java.lang.String r17 = "video/mp4v-es"
                goto L_0x038d
            L_0x0401:
                byte[] r6 = r0.dolbyVisionConfigBytes
                if (r6 == 0) goto L_0x0417
                androidx.media3.common.util.ParsableByteArray r6 = new androidx.media3.common.util.ParsableByteArray
                byte[] r7 = r0.dolbyVisionConfigBytes
                r6.<init>((byte[]) r7)
                androidx.media3.extractor.DolbyVisionConfig r6 = androidx.media3.extractor.DolbyVisionConfig.parse(r6)
                if (r6 == 0) goto L_0x0417
                java.lang.String r3 = r6.codecs
                java.lang.String r17 = "video/dolby-vision"
            L_0x0417:
                r6 = r17
                boolean r7 = r0.flagDefault
                r8 = 0
                r7 = r7 | r8
                boolean r12 = r0.flagForced
                if (r12 == 0) goto L_0x0423
                r12 = 2
                goto L_0x0424
            L_0x0423:
                r12 = r8
            L_0x0424:
                r7 = r7 | r12
                androidx.media3.common.Format$Builder r12 = new androidx.media3.common.Format$Builder
                r12.<init>()
                boolean r16 = androidx.media3.common.MimeTypes.isAudio(r6)
                if (r16 == 0) goto L_0x0442
                int r2 = r0.channelCount
                androidx.media3.common.Format$Builder r2 = r12.setChannelCount(r2)
                int r5 = r0.sampleRate
                androidx.media3.common.Format$Builder r2 = r2.setSampleRate(r5)
                r2.setPcmEncoding(r9)
                r5 = 1
                goto L_0x056a
            L_0x0442:
                boolean r9 = androidx.media3.common.MimeTypes.isVideo(r6)
                if (r9 == 0) goto L_0x053b
                int r2 = r0.displayUnit
                if (r2 != 0) goto L_0x045e
                int r2 = r0.displayWidth
                r5 = -1
                if (r2 != r5) goto L_0x0453
                int r2 = r0.width
            L_0x0453:
                r0.displayWidth = r2
                int r2 = r0.displayHeight
                if (r2 != r5) goto L_0x045b
                int r2 = r0.height
            L_0x045b:
                r0.displayHeight = r2
                goto L_0x045f
            L_0x045e:
                r5 = -1
            L_0x045f:
                int r2 = r0.displayWidth
                if (r2 == r5) goto L_0x0471
                int r9 = r0.displayHeight
                if (r9 == r5) goto L_0x0471
                int r10 = r0.height
                int r10 = r10 * r2
                float r2 = (float) r10
                int r10 = r0.width
                int r10 = r10 * r9
                float r9 = (float) r10
                float r2 = r2 / r9
                goto L_0x0473
            L_0x0471:
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x0473:
                boolean r9 = r0.hasColorInfo
                if (r9 == 0) goto L_0x04a6
                byte[] r9 = r20.getHdrStaticInfo()
                androidx.media3.common.ColorInfo$Builder r10 = new androidx.media3.common.ColorInfo$Builder
                r10.<init>()
                int r11 = r0.colorSpace
                androidx.media3.common.ColorInfo$Builder r10 = r10.setColorSpace(r11)
                int r11 = r0.colorRange
                androidx.media3.common.ColorInfo$Builder r10 = r10.setColorRange(r11)
                int r11 = r0.colorTransfer
                androidx.media3.common.ColorInfo$Builder r10 = r10.setColorTransfer(r11)
                androidx.media3.common.ColorInfo$Builder r9 = r10.setHdrStaticInfo(r9)
                int r10 = r0.bitsPerChannel
                androidx.media3.common.ColorInfo$Builder r9 = r9.setLumaBitdepth(r10)
                int r10 = r0.bitsPerChannel
                androidx.media3.common.ColorInfo$Builder r9 = r9.setChromaBitdepth(r10)
                androidx.media3.common.ColorInfo r11 = r9.build()
            L_0x04a6:
                java.lang.String r9 = r0.name
                if (r9 == 0) goto L_0x04c6
                java.util.Map r9 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r10 = r0.name
                boolean r9 = r9.containsKey(r10)
                if (r9 == 0) goto L_0x04c6
                java.util.Map r5 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r9 = r0.name
                java.lang.Object r5 = r5.get(r9)
                java.lang.Integer r5 = (java.lang.Integer) r5
                int r5 = r5.intValue()
            L_0x04c6:
                int r9 = r0.projectionType
                if (r9 != 0) goto L_0x0516
                float r9 = r0.projectionPoseYaw
                r10 = 0
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x0516
                float r9 = r0.projectionPosePitch
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x0516
                float r9 = r0.projectionPoseRoll
                int r9 = java.lang.Float.compare(r9, r10)
                if (r9 != 0) goto L_0x04e5
                r5 = r8
                goto L_0x0516
            L_0x04e5:
                float r8 = r0.projectionPoseRoll
                r9 = 1119092736(0x42b40000, float:90.0)
                int r8 = java.lang.Float.compare(r8, r9)
                if (r8 != 0) goto L_0x04f2
                r5 = 90
                goto L_0x0516
            L_0x04f2:
                float r8 = r0.projectionPoseRoll
                r9 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r8 = java.lang.Float.compare(r8, r9)
                if (r8 == 0) goto L_0x0514
                float r8 = r0.projectionPoseRoll
                r9 = 1127481344(0x43340000, float:180.0)
                int r8 = java.lang.Float.compare(r8, r9)
                if (r8 != 0) goto L_0x0507
                goto L_0x0514
            L_0x0507:
                float r8 = r0.projectionPoseRoll
                r9 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r8 = java.lang.Float.compare(r8, r9)
                if (r8 != 0) goto L_0x0516
                r5 = 270(0x10e, float:3.78E-43)
                goto L_0x0516
            L_0x0514:
                r5 = 180(0xb4, float:2.52E-43)
            L_0x0516:
                int r8 = r0.width
                androidx.media3.common.Format$Builder r8 = r12.setWidth(r8)
                int r9 = r0.height
                androidx.media3.common.Format$Builder r8 = r8.setHeight(r9)
                androidx.media3.common.Format$Builder r2 = r8.setPixelWidthHeightRatio(r2)
                androidx.media3.common.Format$Builder r2 = r2.setRotationDegrees(r5)
                byte[] r5 = r0.projectionData
                androidx.media3.common.Format$Builder r2 = r2.setProjectionData(r5)
                int r5 = r0.stereoMode
                androidx.media3.common.Format$Builder r2 = r2.setStereoMode(r5)
                r2.setColorInfo(r11)
                r5 = 2
                goto L_0x056a
            L_0x053b:
                boolean r5 = r5.equals(r6)
                if (r5 != 0) goto L_0x0569
                boolean r5 = r15.equals(r6)
                if (r5 != 0) goto L_0x0569
                boolean r5 = r14.equals(r6)
                if (r5 != 0) goto L_0x0569
                boolean r5 = r13.equals(r6)
                if (r5 != 0) goto L_0x0569
                boolean r2 = r2.equals(r6)
                if (r2 != 0) goto L_0x0569
                java.lang.String r2 = "application/dvbsubs"
                boolean r2 = r2.equals(r6)
                if (r2 == 0) goto L_0x0562
                goto L_0x0569
            L_0x0562:
                java.lang.String r1 = "Unexpected MIME type."
                androidx.media3.common.ParserException r1 = androidx.media3.common.ParserException.createForMalformedContainer(r1, r11)
                throw r1
            L_0x0569:
                r5 = r10
            L_0x056a:
                java.lang.String r2 = r0.name
                if (r2 == 0) goto L_0x057f
                java.util.Map r2 = androidx.media3.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r8 = r0.name
                boolean r2 = r2.containsKey(r8)
                if (r2 != 0) goto L_0x057f
                java.lang.String r2 = r0.name
                r12.setLabel(r2)
            L_0x057f:
                r2 = r22
                androidx.media3.common.Format$Builder r2 = r12.setId((int) r2)
                androidx.media3.common.Format$Builder r2 = r2.setSampleMimeType(r6)
                androidx.media3.common.Format$Builder r2 = r2.setMaxInputSize(r4)
                java.lang.String r4 = r0.language
                androidx.media3.common.Format$Builder r2 = r2.setLanguage(r4)
                androidx.media3.common.Format$Builder r2 = r2.setSelectionFlags(r7)
                androidx.media3.common.Format$Builder r1 = r2.setInitializationData(r1)
                androidx.media3.common.Format$Builder r1 = r1.setCodecs(r3)
                androidx.media3.common.DrmInitData r2 = r0.drmInitData
                androidx.media3.common.Format$Builder r1 = r1.setDrmInitData(r2)
                androidx.media3.common.Format r1 = r1.build()
                int r2 = r0.number
                r3 = r21
                androidx.media3.extractor.TrackOutput r2 = r3.track(r2, r5)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.mkv.MatroskaExtractor.Track.initializeOutput(androidx.media3.extractor.ExtractorOutput, int):void");
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        /* access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            if (MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId)) {
                return z;
            }
            return this.maxBlockAdditionId > 0;
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            order.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_DIVX, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>(MimeTypes.VIDEO_H263, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", (Throwable) null);
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>(MimeTypes.VIDEO_UNKNOWN, (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", (Throwable) null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            byte b;
            byte b2;
            try {
                if (bArr[0] == 2) {
                    int i = 0;
                    int i2 = 1;
                    while (true) {
                        b = bArr[i2];
                        if ((b & 255) != 255) {
                            break;
                        }
                        i += 255;
                        i2++;
                    }
                    int i3 = i2 + 1;
                    int i4 = i + (b & 255);
                    int i5 = 0;
                    while (true) {
                        b2 = bArr[i3];
                        if ((b2 & 255) != 255) {
                            break;
                        }
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + (b2 & 255);
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                        }
                        throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                    }
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                }
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", (Throwable) null);
            }
        }

        /* access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, (Throwable) null);
        }
    }
}
