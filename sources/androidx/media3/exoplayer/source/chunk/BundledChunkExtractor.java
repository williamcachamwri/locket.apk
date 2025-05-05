package androidx.media3.exoplayer.source.chunk;

import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.text.DefaultSubtitleParserFactory;
import androidx.media3.extractor.text.SubtitleParser;
import java.io.IOException;

public final class BundledChunkExtractor implements ExtractorOutput, ChunkExtractor {
    public static final Factory FACTORY = new Factory();
    private static final PositionHolder POSITION_HOLDER = new PositionHolder();
    private final SparseArray<BindingTrackOutput> bindingTrackOutputs = new SparseArray<>();
    private long endTimeUs;
    private final Extractor extractor;
    private boolean extractorInitialized;
    private final Format primaryTrackManifestFormat;
    private final int primaryTrackType;
    private Format[] sampleFormats;
    private SeekMap seekMap;
    private ChunkExtractor.TrackOutputProvider trackOutputProvider;

    public static final class Factory implements ChunkExtractor.Factory {
        private boolean parseSubtitlesDuringExtraction;
        private boolean parseWithinGopSampleDependencies;
        private SubtitleParser.Factory subtitleParserFactory = new DefaultSubtitleParserFactory();

        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.subtitleParserFactory = (SubtitleParser.Factory) Assertions.checkNotNull(factory);
            return this;
        }

        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.parseSubtitlesDuringExtraction = z;
            return this;
        }

        public Format getOutputTextFormat(Format format) {
            if (!this.parseSubtitlesDuringExtraction || !this.subtitleParserFactory.supportsFormat(format)) {
                return format;
            }
            return format.buildUpon().setSampleMimeType(MimeTypes.APPLICATION_MEDIA3_CUES).setCueReplacementBehavior(this.subtitleParserFactory.getCueReplacementBehavior(format)).setCodecs(format.sampleMimeType + (format.codecs != null ? " " + format.codecs : "")).setSubsampleOffsetUs(Long.MAX_VALUE).build();
        }

        /* JADX WARNING: type inference failed for: r10v10, types: [androidx.media3.extractor.png.PngExtractor] */
        /* JADX WARNING: type inference failed for: r10v11, types: [androidx.media3.extractor.jpeg.JpegExtractor] */
        /* JADX WARNING: type inference failed for: r10v13, types: [androidx.media3.extractor.mkv.MatroskaExtractor] */
        /* JADX WARNING: type inference failed for: r10v15, types: [androidx.media3.extractor.text.SubtitleExtractor] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media3.exoplayer.source.chunk.ChunkExtractor createProgressiveMediaExtractor(int r8, androidx.media3.common.Format r9, boolean r10, java.util.List<androidx.media3.common.Format> r11, androidx.media3.extractor.TrackOutput r12, androidx.media3.exoplayer.analytics.PlayerId r13) {
            /*
                r7 = this;
                java.lang.String r13 = r9.containerMimeType
                boolean r0 = androidx.media3.common.MimeTypes.isText(r13)
                if (r0 == 0) goto L_0x001a
                boolean r10 = r7.parseSubtitlesDuringExtraction
                if (r10 != 0) goto L_0x000e
                r8 = 0
                return r8
            L_0x000e:
                androidx.media3.extractor.text.SubtitleExtractor r10 = new androidx.media3.extractor.text.SubtitleExtractor
                androidx.media3.extractor.text.SubtitleParser$Factory r11 = r7.subtitleParserFactory
                androidx.media3.extractor.text.SubtitleParser r11 = r11.create(r9)
                r10.<init>(r11, r9)
                goto L_0x0068
            L_0x001a:
                boolean r0 = androidx.media3.common.MimeTypes.isMatroska(r13)
                r1 = 1
                if (r0 == 0) goto L_0x002e
                boolean r10 = r7.parseSubtitlesDuringExtraction
                if (r10 != 0) goto L_0x0026
                r1 = 3
            L_0x0026:
                androidx.media3.extractor.mkv.MatroskaExtractor r10 = new androidx.media3.extractor.mkv.MatroskaExtractor
                androidx.media3.extractor.text.SubtitleParser$Factory r11 = r7.subtitleParserFactory
                r10.<init>(r11, r1)
                goto L_0x0068
            L_0x002e:
                java.lang.String r0 = "image/jpeg"
                boolean r0 = java.util.Objects.equals(r13, r0)
                if (r0 == 0) goto L_0x003c
                androidx.media3.extractor.jpeg.JpegExtractor r10 = new androidx.media3.extractor.jpeg.JpegExtractor
                r10.<init>(r1)
                goto L_0x0068
            L_0x003c:
                java.lang.String r0 = "image/png"
                boolean r13 = java.util.Objects.equals(r13, r0)
                if (r13 == 0) goto L_0x004a
                androidx.media3.extractor.png.PngExtractor r10 = new androidx.media3.extractor.png.PngExtractor
                r10.<init>()
                goto L_0x0068
            L_0x004a:
                if (r10 == 0) goto L_0x004e
                r10 = 4
                goto L_0x004f
            L_0x004e:
                r10 = 0
            L_0x004f:
                boolean r13 = r7.parseSubtitlesDuringExtraction
                if (r13 != 0) goto L_0x0055
                r10 = r10 | 32
            L_0x0055:
                boolean r13 = r7.parseWithinGopSampleDependencies
                if (r13 == 0) goto L_0x005b
                r10 = r10 | 64
            L_0x005b:
                r2 = r10
                androidx.media3.extractor.mp4.FragmentedMp4Extractor r10 = new androidx.media3.extractor.mp4.FragmentedMp4Extractor
                androidx.media3.extractor.text.SubtitleParser$Factory r1 = r7.subtitleParserFactory
                r3 = 0
                r4 = 0
                r0 = r10
                r5 = r11
                r6 = r12
                r0.<init>(r1, r2, r3, r4, r5, r6)
            L_0x0068:
                androidx.media3.exoplayer.source.chunk.BundledChunkExtractor r11 = new androidx.media3.exoplayer.source.chunk.BundledChunkExtractor
                r11.<init>(r10, r8, r9)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.chunk.BundledChunkExtractor.Factory.createProgressiveMediaExtractor(int, androidx.media3.common.Format, boolean, java.util.List, androidx.media3.extractor.TrackOutput, androidx.media3.exoplayer.analytics.PlayerId):androidx.media3.exoplayer.source.chunk.ChunkExtractor");
        }

        public Factory experimentalParseWithinGopSampleDependencies(boolean z) {
            this.parseWithinGopSampleDependencies = z;
            return this;
        }
    }

    public BundledChunkExtractor(Extractor extractor2, int i, Format format) {
        this.extractor = extractor2;
        this.primaryTrackType = i;
        this.primaryTrackManifestFormat = format;
    }

    public ChunkIndex getChunkIndex() {
        SeekMap seekMap2 = this.seekMap;
        if (seekMap2 instanceof ChunkIndex) {
            return (ChunkIndex) seekMap2;
        }
        return null;
    }

    public Format[] getSampleFormats() {
        return this.sampleFormats;
    }

    public void init(ChunkExtractor.TrackOutputProvider trackOutputProvider2, long j, long j2) {
        this.trackOutputProvider = trackOutputProvider2;
        this.endTimeUs = j2;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            if (j != C.TIME_UNSET) {
                this.extractor.seek(0, j);
            }
            this.extractorInitialized = true;
            return;
        }
        Extractor extractor2 = this.extractor;
        if (j == C.TIME_UNSET) {
            j = 0;
        }
        extractor2.seek(0, j);
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            this.bindingTrackOutputs.valueAt(i).bind(trackOutputProvider2, j2);
        }
    }

    public void release() {
        this.extractor.release();
    }

    public boolean read(ExtractorInput extractorInput) throws IOException {
        int read = this.extractor.read(extractorInput, POSITION_HOLDER);
        Assertions.checkState(read != 1);
        if (read == 0) {
            return true;
        }
        return false;
    }

    public TrackOutput track(int i, int i2) {
        BindingTrackOutput bindingTrackOutput = this.bindingTrackOutputs.get(i);
        if (bindingTrackOutput == null) {
            Assertions.checkState(this.sampleFormats == null);
            bindingTrackOutput = new BindingTrackOutput(i, i2, i2 == this.primaryTrackType ? this.primaryTrackManifestFormat : null);
            bindingTrackOutput.bind(this.trackOutputProvider, this.endTimeUs);
            this.bindingTrackOutputs.put(i, bindingTrackOutput);
        }
        return bindingTrackOutput;
    }

    public void endTracks() {
        Format[] formatArr = new Format[this.bindingTrackOutputs.size()];
        for (int i = 0; i < this.bindingTrackOutputs.size(); i++) {
            formatArr[i] = (Format) Assertions.checkStateNotNull(this.bindingTrackOutputs.valueAt(i).sampleFormat);
        }
        this.sampleFormats = formatArr;
    }

    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
    }

    private static final class BindingTrackOutput implements TrackOutput {
        private long endTimeUs;
        private final DiscardingTrackOutput fakeTrackOutput = new DiscardingTrackOutput();
        private final int id;
        private final Format manifestFormat;
        public Format sampleFormat;
        private TrackOutput trackOutput;
        private final int type;

        public BindingTrackOutput(int i, int i2, Format format) {
            this.id = i;
            this.type = i2;
            this.manifestFormat = format;
        }

        public void bind(ChunkExtractor.TrackOutputProvider trackOutputProvider, long j) {
            if (trackOutputProvider == null) {
                this.trackOutput = this.fakeTrackOutput;
                return;
            }
            this.endTimeUs = j;
            TrackOutput track = trackOutputProvider.track(this.id, this.type);
            this.trackOutput = track;
            Format format = this.sampleFormat;
            if (format != null) {
                track.format(format);
            }
        }

        public void format(Format format) {
            Format format2 = this.manifestFormat;
            if (format2 != null) {
                format = format.withManifestFormatInfo(format2);
            }
            this.sampleFormat = format;
            ((TrackOutput) Util.castNonNull(this.trackOutput)).format(this.sampleFormat);
        }

        public int sampleData(DataReader dataReader, int i, boolean z, int i2) throws IOException {
            return ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(dataReader, i, z);
        }

        public void sampleData(ParsableByteArray parsableByteArray, int i, int i2) {
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleData(parsableByteArray, i);
        }

        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            long j2 = this.endTimeUs;
            if (j2 != C.TIME_UNSET && j >= j2) {
                this.trackOutput = this.fakeTrackOutput;
            }
            ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata(j, i, i2, i3, cryptoData);
        }
    }
}
