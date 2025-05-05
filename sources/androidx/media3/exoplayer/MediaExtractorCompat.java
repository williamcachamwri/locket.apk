package androidx.media3.exoplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaDataSource;
import android.media.MediaFormat;
import android.net.Uri;
import android.util.SparseArray;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.FileDescriptorDataSource;
import androidx.media3.datasource.MediaDataSourceAdapter;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.UnrecognizedInputFormatException;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.TrackOutput;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class MediaExtractorCompat {
    public static final int SEEK_TO_CLOSEST_SYNC = 2;
    public static final int SEEK_TO_NEXT_SYNC = 1;
    public static final int SEEK_TO_PREVIOUS_SYNC = 0;
    private static final String TAG = "MediaExtractorCompat";
    /* access modifiers changed from: private */
    public final Allocator allocator;
    private DataSource currentDataSource;
    private Extractor currentExtractor;
    private ExtractorInput currentExtractorInput;
    private final DataSource.Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;
    private final FormatHolder formatHolder;
    private boolean hasBeenPrepared;
    private Map<String, String> httpRequestHeaders;
    private final DecoderInputBuffer noDataBuffer;
    private long offsetInCurrentFile;
    private SeekPoint pendingSeek;
    private final PositionHolder positionHolder;
    private final DecoderInputBuffer sampleHolderWithBufferReplacementDirect;
    private final DecoderInputBuffer sampleHolderWithBufferReplacementDisabled;
    /* access modifiers changed from: private */
    public final SparseArray<MediaExtractorSampleQueue> sampleQueues;
    /* access modifiers changed from: private */
    public SeekMap seekMap;
    private final Set<Integer> selectedTrackIndices;
    /* access modifiers changed from: private */
    public final ArrayDeque<Integer> trackIndicesPerSampleInQueuedOrder;
    private final ArrayList<MediaExtractorTrack> tracks;
    /* access modifiers changed from: private */
    public boolean tracksEnded;
    private int upstreamFormatsCount;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeekMode {
    }

    public MediaExtractorCompat(Context context) {
        this(new DefaultExtractorsFactory(), new DefaultDataSource.Factory(context));
    }

    public MediaExtractorCompat(ExtractorsFactory extractorsFactory2, DataSource.Factory factory) {
        this.extractorsFactory = extractorsFactory2;
        this.dataSourceFactory = factory;
        this.positionHolder = new PositionHolder();
        this.allocator = new DefaultAllocator(true, 65536);
        this.tracks = new ArrayList<>();
        this.sampleQueues = new SparseArray<>();
        this.trackIndicesPerSampleInQueuedOrder = new ArrayDeque<>();
        this.formatHolder = new FormatHolder();
        this.sampleHolderWithBufferReplacementDisabled = new DecoderInputBuffer(0);
        this.sampleHolderWithBufferReplacementDirect = new DecoderInputBuffer(2);
        this.noDataBuffer = DecoderInputBuffer.newNoDataInstance();
        this.selectedTrackIndices = new HashSet();
    }

    public void setDataSource(Uri uri, long j) throws IOException {
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(uri, j));
    }

    public void setDataSource(AssetFileDescriptor assetFileDescriptor) throws IOException {
        if (assetFileDescriptor.getDeclaredLength() == -1) {
            setDataSource(assetFileDescriptor.getFileDescriptor());
            return;
        }
        setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getDeclaredLength());
    }

    public void setDataSource(FileDescriptor fileDescriptor) throws IOException {
        setDataSource(fileDescriptor, 0, -1);
    }

    public void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IOException {
        prepareDataSource(new FileDescriptorDataSource(fileDescriptor, j, j2), buildDataSpec(Uri.EMPTY, 0));
    }

    public void setDataSource(Context context, Uri uri, Map<String, String> map) throws IOException {
        AssetFileDescriptor openAssetFileDescriptor;
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if ((scheme == null || scheme.equals("file")) && path != null) {
            setDataSource(path);
            return;
        }
        try {
            openAssetFileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
            if (openAssetFileDescriptor != null) {
                setDataSource(openAssetFileDescriptor);
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                    return;
                }
                return;
            }
            if (openAssetFileDescriptor != null) {
                openAssetFileDescriptor.close();
            }
            setDataSource(uri.toString(), map);
            return;
        } catch (FileNotFoundException | SecurityException unused) {
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setDataSource(String str) throws IOException {
        setDataSource(str, (Map<String, String>) null);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException {
        this.httpRequestHeaders = map;
        prepareDataSource(this.dataSourceFactory.createDataSource(), buildDataSpec(Uri.parse(str), 0));
    }

    public void setDataSource(MediaDataSource mediaDataSource) throws IOException {
        prepareDataSource(new MediaDataSourceAdapter(mediaDataSource, false), buildDataSpec(Uri.EMPTY, 0));
    }

    /* JADX WARNING: type inference failed for: r11v9, types: [androidx.media3.extractor.ExtractorInput] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void prepareDataSource(androidx.media3.datasource.DataSource r11, androidx.media3.datasource.DataSpec r12) throws java.io.IOException {
        /*
            r10 = this;
            boolean r0 = r10.hasBeenPrepared
            r1 = 1
            r0 = r0 ^ r1
            androidx.media3.common.util.Assertions.checkState(r0)
            r10.hasBeenPrepared = r1
            long r2 = r12.position
            r10.offsetInCurrentFile = r2
            r10.currentDataSource = r11
            long r8 = r11.open(r12)
            androidx.media3.extractor.DefaultExtractorInput r11 = new androidx.media3.extractor.DefaultExtractorInput
            androidx.media3.datasource.DataSource r5 = r10.currentDataSource
            r6 = 0
            r4 = r11
            r4.<init>(r5, r6, r8)
            androidx.media3.extractor.Extractor r12 = r10.selectExtractor(r11)
            androidx.media3.exoplayer.MediaExtractorCompat$ExtractorOutputImpl r0 = new androidx.media3.exoplayer.MediaExtractorCompat$ExtractorOutputImpl
            r2 = 0
            r0.<init>()
            r12.init(r0)
            r0 = r1
        L_0x002b:
            if (r0 == 0) goto L_0x0071
            r0 = -1
            androidx.media3.extractor.PositionHolder r3 = r10.positionHolder     // Catch:{ Exception -> 0x0037, OutOfMemoryError -> 0x0035 }
            int r3 = r12.read(r11, r3)     // Catch:{ Exception -> 0x0037, OutOfMemoryError -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r2 = move-exception
            goto L_0x0038
        L_0x0037:
            r2 = move-exception
        L_0x0038:
            r3 = r0
        L_0x0039:
            boolean r4 = r10.tracksEnded
            if (r4 == 0) goto L_0x004e
            int r4 = r10.upstreamFormatsCount
            android.util.SparseArray<androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorSampleQueue> r5 = r10.sampleQueues
            int r5 = r5.size()
            if (r4 < r5) goto L_0x004e
            androidx.media3.extractor.SeekMap r4 = r10.seekMap
            if (r4 != 0) goto L_0x004c
            goto L_0x004e
        L_0x004c:
            r4 = 0
            goto L_0x004f
        L_0x004e:
            r4 = r1
        L_0x004f:
            if (r2 != 0) goto L_0x0062
            if (r4 == 0) goto L_0x0056
            if (r3 != r0) goto L_0x0056
            goto L_0x0062
        L_0x0056:
            if (r3 != r1) goto L_0x0060
            androidx.media3.extractor.PositionHolder r11 = r10.positionHolder
            long r5 = r11.position
            androidx.media3.extractor.ExtractorInput r11 = r10.reopenCurrentDataSource(r5)
        L_0x0060:
            r0 = r4
            goto L_0x002b
        L_0x0062:
            r10.release()
            if (r2 == 0) goto L_0x006a
            java.lang.String r11 = "Exception encountered while parsing input media."
            goto L_0x006c
        L_0x006a:
            java.lang.String r11 = "Reached end of input before preparation completed."
        L_0x006c:
            androidx.media3.common.ParserException r11 = androidx.media3.common.ParserException.createForMalformedContainer(r11, r2)
            throw r11
        L_0x0071:
            r10.currentExtractorInput = r11
            r10.currentExtractor = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaExtractorCompat.prepareDataSource(androidx.media3.datasource.DataSource, androidx.media3.datasource.DataSpec):void");
    }

    public void release() {
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            this.sampleQueues.valueAt(i).release();
        }
        this.sampleQueues.clear();
        Extractor extractor = this.currentExtractor;
        if (extractor != null) {
            extractor.release();
            this.currentExtractor = null;
        }
        this.currentExtractorInput = null;
        this.pendingSeek = null;
        DataSourceUtil.closeQuietly(this.currentDataSource);
        this.currentDataSource = null;
    }

    public int getTrackCount() {
        return this.tracks.size();
    }

    public MediaFormat getTrackFormat(int i) {
        return this.tracks.get(i).createDownstreamMediaFormat(this.formatHolder, this.noDataBuffer);
    }

    public void selectTrack(int i) {
        this.selectedTrackIndices.add(Integer.valueOf(i));
    }

    public void unselectTrack(int i) {
        this.selectedTrackIndices.remove(Integer.valueOf(i));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007a A[LOOP:0: B:22:0x0072->B:24:0x007a, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void seekTo(long r6, int r8) {
        /*
            r5 = this;
            androidx.media3.extractor.SeekMap r0 = r5.seekMap
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            java.util.Set<java.lang.Integer> r0 = r5.selectedTrackIndices
            int r0 = r0.size()
            r1 = 1
            if (r0 != r1) goto L_0x0037
            androidx.media3.extractor.Extractor r0 = r5.currentExtractor
            boolean r2 = r0 instanceof androidx.media3.extractor.mp4.Mp4Extractor
            if (r2 == 0) goto L_0x0037
            androidx.media3.extractor.mp4.Mp4Extractor r0 = (androidx.media3.extractor.mp4.Mp4Extractor) r0
            java.util.ArrayList<androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorTrack> r2 = r5.tracks
            java.util.Set<java.lang.Integer> r3 = r5.selectedTrackIndices
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r3 = r3.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.lang.Object r2 = r2.get(r3)
            androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorTrack r2 = (androidx.media3.exoplayer.MediaExtractorCompat.MediaExtractorTrack) r2
            int r2 = r2.getIdOfBackingTrack()
            androidx.media3.extractor.SeekMap$SeekPoints r0 = r0.getSeekPoints(r6, r2)
            goto L_0x003d
        L_0x0037:
            androidx.media3.extractor.SeekMap r0 = r5.seekMap
            androidx.media3.extractor.SeekMap$SeekPoints r0 = r0.getSeekPoints(r6)
        L_0x003d:
            if (r8 == 0) goto L_0x006a
            if (r8 == r1) goto L_0x0067
            r1 = 2
            if (r8 != r1) goto L_0x0061
            androidx.media3.extractor.SeekPoint r8 = r0.second
            long r1 = r8.timeUs
            long r1 = r6 - r1
            long r1 = java.lang.Math.abs(r1)
            androidx.media3.extractor.SeekPoint r8 = r0.first
            long r3 = r8.timeUs
            long r6 = r6 - r3
            long r6 = java.lang.Math.abs(r6)
            int r6 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r6 >= 0) goto L_0x005e
            androidx.media3.extractor.SeekPoint r6 = r0.second
            goto L_0x006c
        L_0x005e:
            androidx.media3.extractor.SeekPoint r6 = r0.first
            goto L_0x006c
        L_0x0061:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>()
            throw r6
        L_0x0067:
            androidx.media3.extractor.SeekPoint r6 = r0.second
            goto L_0x006c
        L_0x006a:
            androidx.media3.extractor.SeekPoint r6 = r0.first
        L_0x006c:
            java.util.ArrayDeque<java.lang.Integer> r7 = r5.trackIndicesPerSampleInQueuedOrder
            r7.clear()
            r7 = 0
        L_0x0072:
            android.util.SparseArray<androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorSampleQueue> r8 = r5.sampleQueues
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x0088
            android.util.SparseArray<androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorSampleQueue> r8 = r5.sampleQueues
            java.lang.Object r8 = r8.valueAt(r7)
            androidx.media3.exoplayer.MediaExtractorCompat$MediaExtractorSampleQueue r8 = (androidx.media3.exoplayer.MediaExtractorCompat.MediaExtractorSampleQueue) r8
            r8.reset()
            int r7 = r7 + 1
            goto L_0x0072
        L_0x0088:
            r5.pendingSeek = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.MediaExtractorCompat.seekTo(long, int):void");
    }

    public boolean advance() {
        if (!advanceToSampleOrEndOfInput()) {
            return false;
        }
        skipOneSample();
        return advanceToSampleOrEndOfInput();
    }

    public int readSampleData(ByteBuffer byteBuffer, int i) {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        byteBuffer.position(i);
        byteBuffer.limit(byteBuffer.capacity());
        this.sampleHolderWithBufferReplacementDisabled.data = byteBuffer;
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementDisabled, false);
        byteBuffer.flip();
        byteBuffer.position(i);
        this.sampleHolderWithBufferReplacementDisabled.data = null;
        return byteBuffer.remaining();
    }

    public int getSampleTrackIndex() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        return this.trackIndicesPerSampleInQueuedOrder.peekFirst().intValue();
    }

    public long getSampleSize() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        peekNextSelectedTrackSample(this.sampleHolderWithBufferReplacementDirect, false);
        ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(this.sampleHolderWithBufferReplacementDirect.data);
        int position = byteBuffer.position();
        byteBuffer.position(0);
        return (long) position;
    }

    public long getSampleTime() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        peekNextSelectedTrackSample(this.noDataBuffer, true);
        return this.noDataBuffer.timeUs;
    }

    public int getSampleFlags() {
        if (!advanceToSampleOrEndOfInput()) {
            return -1;
        }
        peekNextSelectedTrackSample(this.noDataBuffer, true);
        return ((this.noDataBuffer.isEncrypted() ? (char) 2 : 0) | false) | this.noDataBuffer.isKeyFrame() ? 1 : 0;
    }

    public Allocator getAllocator() {
        return this.allocator;
    }

    private void peekNextSelectedTrackSample(DecoderInputBuffer decoderInputBuffer, boolean z) {
        MediaExtractorSampleQueue mediaExtractorSampleQueue = this.tracks.get(((Integer) Assertions.checkNotNull(this.trackIndicesPerSampleInQueuedOrder.peekFirst())).intValue()).sampleQueue;
        boolean z2 = false;
        int i = (z ? 4 : 0) | 1;
        int read = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, i, false);
        if (read == -5) {
            read = mediaExtractorSampleQueue.read(this.formatHolder, decoderInputBuffer, i, false);
        }
        this.formatHolder.clear();
        if (read == -4) {
            z2 = true;
        }
        Assertions.checkState(z2);
    }

    private Extractor selectExtractor(ExtractorInput extractorInput) throws IOException {
        Extractor extractor;
        Extractor[] createExtractors = this.extractorsFactory.createExtractors();
        int length = createExtractors.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                extractor = null;
                break;
            }
            extractor = createExtractors[i];
            try {
                if (extractor.sniff(extractorInput)) {
                    extractorInput.resetPeekPosition();
                    break;
                }
                extractorInput.resetPeekPosition();
                i++;
            } catch (EOFException unused) {
            } catch (Throwable th) {
                extractorInput.resetPeekPosition();
                throw th;
            }
        }
        if (extractor != null) {
            return extractor;
        }
        throw new UnrecognizedInputFormatException("None of the available extractors (" + Joiner.on(", ").join((Iterable<? extends Object>) Lists.transform(ImmutableList.copyOf((E[]) createExtractors), new MediaExtractorCompat$$ExternalSyntheticLambda0())) + ") could read the stream.", (Uri) Assertions.checkNotNull(((DataSource) Assertions.checkNotNull(this.currentDataSource)).getUri()), ImmutableList.of());
    }

    @EnsuresNonNullIf(expression = {"trackIndicesPerSampleInQueuedOrder.peekFirst()"}, result = true)
    private boolean advanceToSampleOrEndOfInput() {
        try {
            maybeResolvePendingSeek();
            boolean z = false;
            while (true) {
                if (!this.trackIndicesPerSampleInQueuedOrder.isEmpty()) {
                    if (this.selectedTrackIndices.contains(this.trackIndicesPerSampleInQueuedOrder.peekFirst())) {
                        return true;
                    }
                    skipOneSample();
                } else if (z) {
                    return false;
                } else {
                    try {
                        int read = ((Extractor) Assertions.checkNotNull(this.currentExtractor)).read((ExtractorInput) Assertions.checkNotNull(this.currentExtractorInput), this.positionHolder);
                        if (read == -1) {
                            z = true;
                        } else if (read == 1) {
                            this.currentExtractorInput = reopenCurrentDataSource(this.positionHolder.position);
                        }
                    } catch (Exception | OutOfMemoryError e) {
                        Log.w(TAG, "Treating exception as the end of input.", e);
                    }
                }
            }
        } catch (IOException e2) {
            Log.w(TAG, "Treating exception as the end of input.", e2);
            return false;
        }
    }

    private void skipOneSample() {
        MediaExtractorTrack mediaExtractorTrack = this.tracks.get(this.trackIndicesPerSampleInQueuedOrder.removeFirst().intValue());
        if (!mediaExtractorTrack.isCompatibilityTrack) {
            mediaExtractorTrack.discardFrontSample();
        }
    }

    private ExtractorInput reopenCurrentDataSource(long j) throws IOException {
        DataSource dataSource = (DataSource) Assertions.checkNotNull(this.currentDataSource);
        DataSourceUtil.closeQuietly(dataSource);
        long open = dataSource.open(buildDataSpec((Uri) Assertions.checkNotNull(dataSource.getUri()), this.offsetInCurrentFile + j));
        if (open != -1) {
            open += j;
        }
        return new DefaultExtractorInput(dataSource, j, open);
    }

    /* access modifiers changed from: private */
    public void onSampleQueueFormatInitialized(MediaExtractorSampleQueue mediaExtractorSampleQueue, Format format) {
        this.upstreamFormatsCount++;
        mediaExtractorSampleQueue.setMainTrackIndex(this.tracks.size());
        this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, false, (String) null));
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType != null) {
            mediaExtractorSampleQueue.setCompatibilityTrackIndex(this.tracks.size());
            this.tracks.add(new MediaExtractorTrack(mediaExtractorSampleQueue, true, alternativeCodecMimeType));
        }
    }

    private void maybeResolvePendingSeek() throws IOException {
        SeekPoint seekPoint = this.pendingSeek;
        if (seekPoint != null) {
            SeekPoint seekPoint2 = (SeekPoint) Assertions.checkNotNull(seekPoint);
            ((Extractor) Assertions.checkNotNull(this.currentExtractor)).seek(seekPoint2.position, seekPoint2.timeUs);
            this.currentExtractorInput = reopenCurrentDataSource(seekPoint2.position);
            this.pendingSeek = null;
        }
    }

    private DataSpec buildDataSpec(Uri uri, long j) {
        DataSpec.Builder flags = new DataSpec.Builder().setUri(uri).setPosition(j).setFlags(6);
        Map<String, String> map = this.httpRequestHeaders;
        if (map != null) {
            flags.setHttpRequestHeaders(map);
        }
        return flags.build();
    }

    private final class ExtractorOutputImpl implements ExtractorOutput {
        private ExtractorOutputImpl() {
        }

        public TrackOutput track(int i, int i2) {
            MediaExtractorSampleQueue mediaExtractorSampleQueue = (MediaExtractorSampleQueue) MediaExtractorCompat.this.sampleQueues.get(i);
            if (mediaExtractorSampleQueue != null) {
                return mediaExtractorSampleQueue;
            }
            if (MediaExtractorCompat.this.tracksEnded) {
                return new DiscardingTrackOutput();
            }
            MediaExtractorCompat mediaExtractorCompat = MediaExtractorCompat.this;
            MediaExtractorSampleQueue mediaExtractorSampleQueue2 = new MediaExtractorSampleQueue(mediaExtractorCompat.allocator, i);
            MediaExtractorCompat.this.sampleQueues.put(i, mediaExtractorSampleQueue2);
            return mediaExtractorSampleQueue2;
        }

        public void endTracks() {
            boolean unused = MediaExtractorCompat.this.tracksEnded = true;
        }

        public void seekMap(SeekMap seekMap) {
            SeekMap unused = MediaExtractorCompat.this.seekMap = seekMap;
        }
    }

    private static final class MediaExtractorTrack {
        public final String compatibilityTrackMimeType;
        public final boolean isCompatibilityTrack;
        public final MediaExtractorSampleQueue sampleQueue;

        private MediaExtractorTrack(MediaExtractorSampleQueue mediaExtractorSampleQueue, boolean z, String str) {
            this.sampleQueue = mediaExtractorSampleQueue;
            this.isCompatibilityTrack = z;
            this.compatibilityTrackMimeType = str;
        }

        public MediaFormat createDownstreamMediaFormat(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer) {
            formatHolder.clear();
            this.sampleQueue.read(formatHolder, decoderInputBuffer, 2, false);
            MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat((Format) Assertions.checkNotNull(formatHolder.format));
            formatHolder.clear();
            if (this.compatibilityTrackMimeType != null) {
                if (Util.SDK_INT >= 29) {
                    createMediaFormatFromFormat.removeKey("codecs-string");
                }
                createMediaFormatFromFormat.setString("mime", this.compatibilityTrackMimeType);
            }
            return createMediaFormatFromFormat;
        }

        public void discardFrontSample() {
            this.sampleQueue.skip(1);
            this.sampleQueue.discardToRead();
        }

        public int getIdOfBackingTrack() {
            return this.sampleQueue.trackId;
        }

        public String toString() {
            return String.format("MediaExtractorSampleQueue: %s, isCompatibilityTrack: %s, compatibilityTrackMimeType: %s", new Object[]{this.sampleQueue, Boolean.valueOf(this.isCompatibilityTrack), this.compatibilityTrackMimeType});
        }
    }

    private final class MediaExtractorSampleQueue extends SampleQueue {
        private int compatibilityTrackIndex = -1;
        private int mainTrackIndex = -1;
        public final int trackId;

        public MediaExtractorSampleQueue(Allocator allocator, int i) {
            super(allocator, (DrmSessionManager) null, (DrmSessionEventListener.EventDispatcher) null);
            this.trackId = i;
        }

        public void setMainTrackIndex(int i) {
            this.mainTrackIndex = i;
        }

        public void setCompatibilityTrackIndex(int i) {
            this.compatibilityTrackIndex = i;
        }

        public Format getAdjustedUpstreamFormat(Format format) {
            if (getUpstreamFormat() == null) {
                MediaExtractorCompat.this.onSampleQueueFormatInitialized(this, format);
            }
            return super.getAdjustedUpstreamFormat(format);
        }

        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            int i4 = i & -536870913;
            Assertions.checkState(this.mainTrackIndex != -1);
            int writeIndex = getWriteIndex();
            super.sampleMetadata(j, i4, i2, i3, cryptoData);
            if (getWriteIndex() == writeIndex + 1) {
                if (this.compatibilityTrackIndex != -1) {
                    MediaExtractorCompat.this.trackIndicesPerSampleInQueuedOrder.addLast(Integer.valueOf(this.compatibilityTrackIndex));
                }
                MediaExtractorCompat.this.trackIndicesPerSampleInQueuedOrder.addLast(Integer.valueOf(this.mainTrackIndex));
            }
        }

        public String toString() {
            return String.format("trackId: %s, mainTrackIndex: %s, compatibilityTrackIndex: %s", new Object[]{Integer.valueOf(this.trackId), Integer.valueOf(this.mainTrackIndex), Integer.valueOf(this.compatibilityTrackIndex)});
        }
    }
}
