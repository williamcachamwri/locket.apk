package androidx.media3.exoplayer.dash.offline;

import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.dash.BaseUrlExclusionList;
import androidx.media3.exoplayer.dash.DashSegmentIndex;
import androidx.media3.exoplayer.dash.DashUtil;
import androidx.media3.exoplayer.dash.DashWrappingSegmentIndex;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.DashManifestParser;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.offline.SegmentDownloader;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.ChunkIndex;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class DashDownloader extends SegmentDownloader<DashManifest> {
    private final BaseUrlExclusionList baseUrlExclusionList;

    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new CredentialManager$$ExternalSyntheticLambda0());
    }

    public DashDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, new DashManifestParser(), factory, executor, 20000);
    }

    @Deprecated
    public DashDownloader(MediaItem mediaItem, ParsingLoadable.Parser<DashManifest> parser, CacheDataSource.Factory factory, Executor executor) {
        this(mediaItem, parser, factory, executor, 20000);
    }

    public DashDownloader(MediaItem mediaItem, ParsingLoadable.Parser<DashManifest> parser, CacheDataSource.Factory factory, Executor executor, long j) {
        super(mediaItem, parser, factory, executor, j);
        this.baseUrlExclusionList = new BaseUrlExclusionList();
    }

    /* access modifiers changed from: protected */
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, DashManifest dashManifest, boolean z) throws IOException, InterruptedException {
        DashManifest dashManifest2 = dashManifest;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < dashManifest.getPeriodCount(); i++) {
            Period period = dashManifest2.getPeriod(i);
            long msToUs = Util.msToUs(period.startMs);
            long periodDurationUs = dashManifest2.getPeriodDurationUs(i);
            int i2 = 0;
            for (List<AdaptationSet> list = period.adaptationSets; i2 < list.size(); list = list) {
                addSegmentsForAdaptationSet(dataSource, list.get(i2), msToUs, periodDurationUs, z, arrayList);
                i2++;
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b7 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addSegmentsForAdaptationSet(androidx.media3.datasource.DataSource r27, androidx.media3.exoplayer.dash.manifest.AdaptationSet r28, long r29, long r31, boolean r33, java.util.ArrayList<androidx.media3.exoplayer.offline.SegmentDownloader.Segment> r34) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r26 = this;
            r7 = r26
            r8 = r28
            r9 = r33
            r10 = r34
            r0 = 0
            r11 = r0
        L_0x000a:
            java.util.List<androidx.media3.exoplayer.dash.manifest.Representation> r0 = r8.representations
            int r0 = r0.size()
            if (r11 >= r0) goto L_0x00bc
            java.util.List<androidx.media3.exoplayer.dash.manifest.Representation> r0 = r8.representations
            java.lang.Object r0 = r0.get(r11)
            androidx.media3.exoplayer.dash.manifest.Representation r0 = (androidx.media3.exoplayer.dash.manifest.Representation) r0
            int r1 = r8.type     // Catch:{ IOException -> 0x00b0 }
            r12 = r27
            androidx.media3.exoplayer.dash.DashSegmentIndex r13 = r7.getSegmentIndex(r12, r1, r0, r9)     // Catch:{ IOException -> 0x00ae }
            if (r13 == 0) goto L_0x00a2
            r14 = r31
            long r16 = r13.getSegmentCount(r14)
            r1 = -1
            int r1 = (r16 > r1 ? 1 : (r16 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x009a
            androidx.media3.exoplayer.dash.BaseUrlExclusionList r1 = r7.baseUrlExclusionList
            com.google.common.collect.ImmutableList<androidx.media3.exoplayer.dash.manifest.BaseUrl> r2 = r0.baseUrls
            androidx.media3.exoplayer.dash.manifest.BaseUrl r1 = r1.selectBaseUrl(r2)
            java.lang.Object r1 = androidx.media3.common.util.Util.castNonNull(r1)
            androidx.media3.exoplayer.dash.manifest.BaseUrl r1 = (androidx.media3.exoplayer.dash.manifest.BaseUrl) r1
            java.lang.String r6 = r1.url
            androidx.media3.exoplayer.dash.manifest.RangedUri r18 = r0.getInitializationUri()
            if (r18 == 0) goto L_0x0058
            r1 = r26
            r2 = r0
            r3 = r6
            r4 = r29
            r19 = r6
            r6 = r18
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
            goto L_0x005a
        L_0x0058:
            r19 = r6
        L_0x005a:
            androidx.media3.exoplayer.dash.manifest.RangedUri r6 = r0.getIndexUri()
            if (r6 == 0) goto L_0x006e
            r1 = r26
            r2 = r0
            r3 = r19
            r4 = r29
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
        L_0x006e:
            long r1 = r13.getFirstSegmentNum()
            long r16 = r1 + r16
            r20 = 1
            long r16 = r16 - r20
            r4 = r1
        L_0x0079:
            int r1 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r1 > 0) goto L_0x00b7
            long r1 = r13.getTimeUs(r4)
            long r22 = r29 + r1
            androidx.media3.exoplayer.dash.manifest.RangedUri r6 = r13.getSegmentUrl(r4)
            r1 = r26
            r2 = r0
            r3 = r19
            r24 = r4
            r4 = r22
            androidx.media3.exoplayer.offline.SegmentDownloader$Segment r1 = r1.createSegment(r2, r3, r4, r6)
            r10.add(r1)
            long r4 = r24 + r20
            goto L_0x0079
        L_0x009a:
            androidx.media3.exoplayer.offline.DownloadException r0 = new androidx.media3.exoplayer.offline.DownloadException
            java.lang.String r1 = "Unbounded segment index"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x00a2:
            r14 = r31
            androidx.media3.exoplayer.offline.DownloadException r0 = new androidx.media3.exoplayer.offline.DownloadException     // Catch:{ IOException -> 0x00ac }
            java.lang.String r1 = "Missing segment index"
            r0.<init>((java.lang.String) r1)     // Catch:{ IOException -> 0x00ac }
            throw r0     // Catch:{ IOException -> 0x00ac }
        L_0x00ac:
            r0 = move-exception
            goto L_0x00b5
        L_0x00ae:
            r0 = move-exception
            goto L_0x00b3
        L_0x00b0:
            r0 = move-exception
            r12 = r27
        L_0x00b3:
            r14 = r31
        L_0x00b5:
            if (r9 == 0) goto L_0x00bb
        L_0x00b7:
            int r11 = r11 + 1
            goto L_0x000a
        L_0x00bb:
            throw r0
        L_0x00bc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.offline.DashDownloader.addSegmentsForAdaptationSet(androidx.media3.datasource.DataSource, androidx.media3.exoplayer.dash.manifest.AdaptationSet, long, long, boolean, java.util.ArrayList):void");
    }

    private SegmentDownloader.Segment createSegment(Representation representation, String str, long j, RangedUri rangedUri) {
        return new SegmentDownloader.Segment(j, DashUtil.buildDataSpec(representation, str, rangedUri, 0, ImmutableMap.of()));
    }

    private DashSegmentIndex getSegmentIndex(final DataSource dataSource, final int i, final Representation representation, boolean z) throws IOException, InterruptedException {
        DashSegmentIndex index = representation.getIndex();
        if (index != null) {
            return index;
        }
        ChunkIndex chunkIndex = (ChunkIndex) execute(new RunnableFutureTask<ChunkIndex, IOException>() {
            /* access modifiers changed from: protected */
            public ChunkIndex doWork() throws IOException {
                return DashUtil.loadChunkIndex(dataSource, i, representation);
            }
        }, z);
        if (chunkIndex == null) {
            return null;
        }
        return new DashWrappingSegmentIndex(chunkIndex, representation.presentationTimeOffsetUs);
    }
}
