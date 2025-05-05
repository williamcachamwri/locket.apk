package androidx.media3.exoplayer.dash;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.chunk.ContainerMediaChunk;
import androidx.media3.exoplayer.source.chunk.InitializationChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.SingleSampleMediaChunk;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private final CmcdConfiguration cmcdConfiguration;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long lastChunkRequestRealtimeMs = C.TIME_UNSET;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private ExoTrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final ChunkExtractor.Factory chunkExtractorFactory;
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        public Factory(DataSource.Factory factory, int i) {
            this(BundledChunkExtractor.FACTORY, factory, i);
        }

        public Factory(ChunkExtractor.Factory factory, DataSource.Factory factory2, int i) {
            this.chunkExtractorFactory = factory;
            this.dataSourceFactory = factory2;
            this.maxSegmentsPerLoad = i;
        }

        public Factory setSubtitleParserFactory(SubtitleParser.Factory factory) {
            this.chunkExtractorFactory.setSubtitleParserFactory(factory);
            return this;
        }

        public Factory experimentalParseSubtitlesDuringExtraction(boolean z) {
            this.chunkExtractorFactory.experimentalParseSubtitlesDuringExtraction(z);
            return this;
        }

        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, long j, boolean z, List<Format> list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler, TransferListener transferListener, PlayerId playerId, CmcdConfiguration cmcdConfiguration) {
            TransferListener transferListener2 = transferListener;
            DataSource createDataSource = this.dataSourceFactory.createDataSource();
            if (transferListener2 != null) {
                createDataSource.addTransferListener(transferListener2);
            }
            return new DefaultDashChunkSource(this.chunkExtractorFactory, loaderErrorThrower, dashManifest, baseUrlExclusionList, i, iArr, exoTrackSelection, i2, createDataSource, j, this.maxSegmentsPerLoad, z, list, playerTrackEmsgHandler, playerId, cmcdConfiguration);
        }

        public Format getOutputTextFormat(Format format) {
            return this.chunkExtractorFactory.getOutputTextFormat(format);
        }
    }

    public DefaultDashChunkSource(ChunkExtractor.Factory factory, LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList2, int i, int[] iArr, ExoTrackSelection exoTrackSelection, int i2, DataSource dataSource2, long j, int i3, boolean z, List<Format> list, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2, PlayerId playerId, CmcdConfiguration cmcdConfiguration2) {
        DashManifest dashManifest2 = dashManifest;
        BaseUrlExclusionList baseUrlExclusionList3 = baseUrlExclusionList2;
        int i4 = i;
        ExoTrackSelection exoTrackSelection2 = exoTrackSelection;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest2;
        this.baseUrlExclusionList = baseUrlExclusionList3;
        this.adaptationSetIndices = iArr;
        this.trackSelection = exoTrackSelection2;
        this.trackType = i2;
        this.dataSource = dataSource2;
        this.periodIndex = i4;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler2;
        this.cmcdConfiguration = cmcdConfiguration2;
        long periodDurationUs = dashManifest2.getPeriodDurationUs(i4);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[exoTrackSelection.length()];
        int i5 = 0;
        while (i5 < this.representationHolders.length) {
            Representation representation = representations.get(exoTrackSelection2.getIndexInTrackGroup(i5));
            BaseUrl selectBaseUrl = baseUrlExclusionList3.selectBaseUrl(representation.baseUrls);
            RepresentationHolder[] representationHolderArr = this.representationHolders;
            if (selectBaseUrl == null) {
                selectBaseUrl = (BaseUrl) representation.baseUrls.get(0);
            }
            int i6 = i5;
            representationHolderArr[i6] = new RepresentationHolder(periodDurationUs, representation, selectBaseUrl, factory.createProgressiveMediaExtractor(i2, representation.format, z, list, playerTrackEmsgHandler2, playerId), 0, representation.getIndex());
            i5 = i6 + 1;
        }
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        long j2 = j;
        for (RepresentationHolder representationHolder : this.representationHolders) {
            if (representationHolder.segmentIndex != null) {
                long segmentCount = representationHolder.getSegmentCount();
                if (segmentCount != 0) {
                    long segmentNum = representationHolder.getSegmentNum(j2);
                    long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                    return seekParameters.resolveSeekPositionUs(j, segmentStartTimeUs, (segmentStartTimeUs >= j2 || (segmentCount != -1 && segmentNum >= (representationHolder.getFirstSegmentNum() + segmentCount) - 1)) ? segmentStartTimeUs : representationHolder.getSegmentStartTimeUs(segmentNum + 1));
                }
            }
        }
        return j2;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = dashManifest.getPeriodDurationUs(i);
            ArrayList<Representation> representations = getRepresentations();
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                RepresentationHolder[] representationHolderArr = this.representationHolders;
                representationHolderArr[i2] = representationHolderArr[i2].copyWithNewRepresentation(periodDurationUs, representations.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    public void updateTrackSelection(ExoTrackSelection exoTrackSelection) {
        this.trackSelection = exoTrackSelection;
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }
        throw iOException;
    }

    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    public boolean shouldCancelLoad(long j, Chunk chunk, List<? extends MediaChunk> list) {
        if (this.fatalError != null) {
            return false;
        }
        return this.trackSelection.shouldCancelChunkLoad(j, chunk, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getNextChunk(androidx.media3.exoplayer.LoadingInfo r44, long r45, java.util.List<? extends androidx.media3.exoplayer.source.chunk.MediaChunk> r47, androidx.media3.exoplayer.source.chunk.ChunkHolder r48) {
        /*
            r43 = this;
            r15 = r43
            r9 = r44
            r14 = r48
            java.io.IOException r0 = r15.fatalError
            if (r0 == 0) goto L_0x000b
            return
        L_0x000b:
            long r10 = r9.playbackPositionUs
            long r12 = r45 - r10
            androidx.media3.exoplayer.dash.manifest.DashManifest r0 = r15.manifest
            long r0 = r0.availabilityStartTimeMs
            long r0 = androidx.media3.common.util.Util.msToUs(r0)
            androidx.media3.exoplayer.dash.manifest.DashManifest r2 = r15.manifest
            int r3 = r15.periodIndex
            androidx.media3.exoplayer.dash.manifest.Period r2 = r2.getPeriod(r3)
            long r2 = r2.startMs
            long r2 = androidx.media3.common.util.Util.msToUs(r2)
            long r0 = r0 + r2
            long r0 = r0 + r45
            androidx.media3.exoplayer.dash.PlayerEmsgHandler$PlayerTrackEmsgHandler r2 = r15.playerTrackEmsgHandler
            if (r2 == 0) goto L_0x0033
            boolean r0 = r2.maybeRefreshManifestBeforeLoadingNextChunk(r0)
            if (r0 == 0) goto L_0x0033
            return
        L_0x0033:
            long r0 = r15.elapsedRealtimeOffsetMs
            long r0 = androidx.media3.common.util.Util.getNowUnixTimeMs(r0)
            long r7 = androidx.media3.common.util.Util.msToUs(r0)
            long r25 = r15.getNowPeriodTimeUs(r7)
            boolean r0 = r47.isEmpty()
            r27 = 0
            r5 = 1
            if (r0 == 0) goto L_0x004f
            r6 = r47
            r28 = r27
            goto L_0x005e
        L_0x004f:
            int r0 = r47.size()
            int r0 = r0 - r5
            r6 = r47
            java.lang.Object r0 = r6.get(r0)
            androidx.media3.exoplayer.source.chunk.MediaChunk r0 = (androidx.media3.exoplayer.source.chunk.MediaChunk) r0
            r28 = r0
        L_0x005e:
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            int r3 = r0.length()
            androidx.media3.exoplayer.source.chunk.MediaChunkIterator[] r4 = new androidx.media3.exoplayer.source.chunk.MediaChunkIterator[r3]
            r29 = 0
            r2 = r29
        L_0x006a:
            if (r2 >= r3) goto L_0x00c6
            androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationHolder[] r0 = r15.representationHolders
            r1 = r0[r2]
            androidx.media3.exoplayer.dash.DashSegmentIndex r0 = r1.segmentIndex
            if (r0 != 0) goto L_0x0081
            androidx.media3.exoplayer.source.chunk.MediaChunkIterator r0 = androidx.media3.exoplayer.source.chunk.MediaChunkIterator.EMPTY
            r4[r2] = r0
            r14 = r2
            r24 = r3
            r30 = r4
            r31 = r12
            r12 = r7
            goto L_0x00b7
        L_0x0081:
            long r16 = r1.getFirstAvailableSegmentNum(r7)
            long r20 = r1.getLastAvailableSegmentNum(r7)
            r0 = r43
            r14 = r2
            r2 = r28
            r24 = r3
            r30 = r4
            r3 = r45
            r5 = r16
            r31 = r12
            r12 = r7
            r7 = r20
            long r18 = r0.getSegmentNum(r1, r2, r3, r5, r7)
            int r0 = (r18 > r16 ? 1 : (r18 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a8
            androidx.media3.exoplayer.source.chunk.MediaChunkIterator r0 = androidx.media3.exoplayer.source.chunk.MediaChunkIterator.EMPTY
            r30[r14] = r0
            goto L_0x00b7
        L_0x00a8:
            androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationHolder r17 = r15.updateSelectedBaseUrl(r14)
            androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationSegmentIterator r0 = new androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationSegmentIterator
            r16 = r0
            r22 = r25
            r16.<init>(r17, r18, r20, r22)
            r30[r14] = r0
        L_0x00b7:
            int r2 = r14 + 1
            r6 = r47
            r14 = r48
            r7 = r12
            r3 = r24
            r4 = r30
            r12 = r31
            r5 = 1
            goto L_0x006a
        L_0x00c6:
            r30 = r4
            r31 = r12
            r12 = r7
            long r21 = r15.getAvailableLiveDurationUs(r12, r10)
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            r16 = r0
            r17 = r10
            r19 = r31
            r23 = r47
            r24 = r30
            r16.updateSelectedTrack(r17, r19, r21, r23, r24)
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            int r0 = r0.getSelectedIndex()
            androidx.media3.exoplayer.upstream.CmcdConfiguration r1 = r15.cmcdConfiguration
            r2 = 0
            if (r1 != 0) goto L_0x00ed
            r14 = r27
            goto L_0x0119
        L_0x00ed:
            androidx.media3.exoplayer.upstream.CmcdData$Factory r1 = new androidx.media3.exoplayer.upstream.CmcdData$Factory
            androidx.media3.exoplayer.upstream.CmcdConfiguration r4 = r15.cmcdConfiguration
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r5 = r15.trackSelection
            r6 = r31
            long r36 = java.lang.Math.max(r2, r6)
            float r6 = r9.playbackSpeed
            java.lang.String r39 = "d"
            androidx.media3.exoplayer.dash.manifest.DashManifest r7 = r15.manifest
            boolean r7 = r7.dynamic
            long r10 = r15.lastChunkRequestRealtimeMs
            boolean r41 = r9.rebufferedSince(r10)
            boolean r42 = r47.isEmpty()
            r33 = r1
            r34 = r4
            r35 = r5
            r38 = r6
            r40 = r7
            r33.<init>(r34, r35, r36, r38, r39, r40, r41, r42)
            r14 = r1
        L_0x0119:
            long r4 = android.os.SystemClock.elapsedRealtime()
            r15.lastChunkRequestRealtimeMs = r4
            androidx.media3.exoplayer.dash.DefaultDashChunkSource$RepresentationHolder r9 = r15.updateSelectedBaseUrl(r0)
            androidx.media3.exoplayer.source.chunk.ChunkExtractor r0 = r9.chunkExtractor
            if (r0 == 0) goto L_0x016a
            androidx.media3.exoplayer.dash.manifest.Representation r0 = r9.representation
            androidx.media3.exoplayer.source.chunk.ChunkExtractor r1 = r9.chunkExtractor
            androidx.media3.common.Format[] r1 = r1.getSampleFormats()
            if (r1 != 0) goto L_0x0137
            androidx.media3.exoplayer.dash.manifest.RangedUri r1 = r0.getInitializationUri()
            r6 = r1
            goto L_0x0139
        L_0x0137:
            r6 = r27
        L_0x0139:
            androidx.media3.exoplayer.dash.DashSegmentIndex r1 = r9.segmentIndex
            if (r1 != 0) goto L_0x0143
            androidx.media3.exoplayer.dash.manifest.RangedUri r0 = r0.getIndexUri()
            r7 = r0
            goto L_0x0145
        L_0x0143:
            r7 = r27
        L_0x0145:
            if (r6 != 0) goto L_0x0149
            if (r7 == 0) goto L_0x016a
        L_0x0149:
            androidx.media3.datasource.DataSource r2 = r15.dataSource
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            androidx.media3.common.Format r3 = r0.getSelectedFormat()
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            int r4 = r0.getSelectionReason()
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            java.lang.Object r5 = r0.getSelectionData()
            r0 = r43
            r1 = r9
            r8 = r14
            androidx.media3.exoplayer.source.chunk.Chunk r0 = r0.newInitializationChunk(r1, r2, r3, r4, r5, r6, r7, r8)
            r10 = r48
            r10.chunk = r0
            return
        L_0x016a:
            r10 = r48
            long r16 = r9.periodDurationUs
            androidx.media3.exoplayer.dash.manifest.DashManifest r0 = r15.manifest
            boolean r0 = r0.dynamic
            if (r0 == 0) goto L_0x0184
            int r0 = r15.periodIndex
            androidx.media3.exoplayer.dash.manifest.DashManifest r1 = r15.manifest
            int r1 = r1.getPeriodCount()
            r11 = 1
            int r1 = r1 - r11
            if (r0 != r1) goto L_0x0185
            r5 = r11
            goto L_0x0187
        L_0x0184:
            r11 = 1
        L_0x0185:
            r5 = r29
        L_0x0187:
            r18 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r5 == 0) goto L_0x0196
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0193
            goto L_0x0196
        L_0x0193:
            r0 = r29
            goto L_0x0197
        L_0x0196:
            r0 = r11
        L_0x0197:
            long r6 = r9.getSegmentCount()
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 != 0) goto L_0x01a2
            r10.endOfStream = r0
            return
        L_0x01a2:
            long r20 = r9.getFirstAvailableSegmentNum(r12)
            long r12 = r9.getLastAvailableSegmentNum(r12)
            if (r5 == 0) goto L_0x01c0
            long r1 = r9.getSegmentEndTimeUs(r12)
            long r3 = r9.getSegmentStartTimeUs(r12)
            long r3 = r1 - r3
            long r1 = r1 + r3
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 < 0) goto L_0x01bd
            r5 = r11
            goto L_0x01bf
        L_0x01bd:
            r5 = r29
        L_0x01bf:
            r0 = r0 & r5
        L_0x01c0:
            r7 = r0
            r0 = r43
            r1 = r9
            r2 = r28
            r3 = r45
            r5 = r20
            r11 = r7
            r7 = r12
            long r7 = r0.getSegmentNum(r1, r2, r3, r5, r7)
            int r0 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r0 >= 0) goto L_0x01dc
            androidx.media3.exoplayer.source.BehindLiveWindowException r0 = new androidx.media3.exoplayer.source.BehindLiveWindowException
            r0.<init>()
            r15.fatalError = r0
            return
        L_0x01dc:
            int r0 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r0 > 0) goto L_0x0246
            boolean r1 = r15.missingLastSegment
            if (r1 == 0) goto L_0x01e7
            if (r0 < 0) goto L_0x01e7
            goto L_0x0246
        L_0x01e7:
            if (r11 == 0) goto L_0x01f5
            long r0 = r9.getSegmentStartTimeUs(r7)
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 < 0) goto L_0x01f5
            r0 = 1
            r10.endOfStream = r0
            return
        L_0x01f5:
            int r0 = r15.maxSegmentsPerLoad
            long r0 = (long) r0
            long r12 = r12 - r7
            r2 = 1
            long r12 = r12 + r2
            long r0 = java.lang.Math.min(r0, r12)
            int r0 = (int) r0
            int r1 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r1 == 0) goto L_0x0216
            r1 = 1
        L_0x0206:
            if (r0 <= r1) goto L_0x0216
            long r4 = (long) r0
            long r4 = r4 + r7
            long r4 = r4 - r2
            long r4 = r9.getSegmentStartTimeUs(r4)
            int r4 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r4 < 0) goto L_0x0216
            int r0 = r0 + -1
            goto L_0x0206
        L_0x0216:
            r11 = r0
            boolean r0 = r47.isEmpty()
            if (r0 == 0) goto L_0x021f
            r18 = r45
        L_0x021f:
            androidx.media3.datasource.DataSource r2 = r15.dataSource
            int r3 = r15.trackType
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            androidx.media3.common.Format r4 = r0.getSelectedFormat()
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            int r5 = r0.getSelectionReason()
            androidx.media3.exoplayer.trackselection.ExoTrackSelection r0 = r15.trackSelection
            java.lang.Object r6 = r0.getSelectionData()
            r0 = r43
            r1 = r9
            r9 = r11
            r10 = r18
            r12 = r25
            r15 = r48
            androidx.media3.exoplayer.source.chunk.Chunk r0 = r0.newMediaChunk(r1, r2, r3, r4, r5, r6, r7, r9, r10, r12, r14)
            r15.chunk = r0
            return
        L_0x0246:
            r15 = r10
            r15.endOfStream = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.DefaultDashChunkSource.getNextChunk(androidx.media3.exoplayer.LoadingInfo, long, java.util.List, androidx.media3.exoplayer.source.chunk.ChunkHolder):void");
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        ChunkIndex chunkIndex;
        if (chunk instanceof InitializationChunk) {
            int indexOf = this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat);
            RepresentationHolder representationHolder = this.representationHolders[indexOf];
            if (representationHolder.segmentIndex == null && (chunkIndex = ((ChunkExtractor) Assertions.checkStateNotNull(representationHolder.chunkExtractor)).getChunkIndex()) != null) {
                this.representationHolders[indexOf] = representationHolder.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(chunkIndex, representationHolder.representation.presentationTimeOffsetUs));
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler2 != null) {
            playerTrackEmsgHandler2.onChunkLoadCompleted(chunk);
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean z, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        LoadErrorHandlingPolicy.FallbackSelection fallbackSelectionFor;
        if (!z) {
            return false;
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler2 != null && playerTrackEmsgHandler2.onChunkLoadError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (loadErrorInfo.exception instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) loadErrorInfo.exception).responseCode == 404) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
            long segmentCount = representationHolder.getSegmentCount();
            if (!(segmentCount == -1 || segmentCount == 0)) {
                if (((MediaChunk) chunk).getNextChunkIndex() > (representationHolder.getFirstSegmentNum() + segmentCount) - 1) {
                    this.missingLastSegment = true;
                    return true;
                }
            }
        }
        RepresentationHolder representationHolder2 = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)];
        BaseUrl selectBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder2.representation.baseUrls);
        if (selectBaseUrl != null && !representationHolder2.selectedBaseUrl.equals(selectBaseUrl)) {
            return true;
        }
        LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions = createFallbackOptions(this.trackSelection, representationHolder2.representation.baseUrls);
        if ((!createFallbackOptions.isFallbackAvailable(2) && !createFallbackOptions.isFallbackAvailable(1)) || (fallbackSelectionFor = loadErrorHandlingPolicy.getFallbackSelectionFor(createFallbackOptions, loadErrorInfo)) == null || !createFallbackOptions.isFallbackAvailable(fallbackSelectionFor.type)) {
            return false;
        }
        if (fallbackSelectionFor.type == 2) {
            ExoTrackSelection exoTrackSelection = this.trackSelection;
            return exoTrackSelection.excludeTrack(exoTrackSelection.indexOf(chunk.trackFormat), fallbackSelectionFor.exclusionDurationMs);
        } else if (fallbackSelectionFor.type != 1) {
            return false;
        } else {
            this.baseUrlExclusionList.exclude(representationHolder2.selectedBaseUrl, fallbackSelectionFor.exclusionDurationMs);
            return true;
        }
    }

    public void release() {
        for (RepresentationHolder representationHolder : this.representationHolders) {
            ChunkExtractor chunkExtractor = representationHolder.chunkExtractor;
            if (chunkExtractor != null) {
                chunkExtractor.release();
            }
        }
    }

    private LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection exoTrackSelection, List<BaseUrl> list) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int length = exoTrackSelection.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (exoTrackSelection.isTrackExcluded(i2, elapsedRealtime)) {
                i++;
            }
        }
        int priorityCount = BaseUrlExclusionList.getPriorityCount(list);
        return new LoadErrorHandlingPolicy.FallbackOptions(priorityCount, priorityCount - this.baseUrlExclusionList.getPriorityCountAfterExclusion(list), length, i);
    }

    private long getSegmentNum(RepresentationHolder representationHolder, MediaChunk mediaChunk, long j, long j2, long j3) {
        if (mediaChunk != null) {
            return mediaChunk.getNextChunkIndex();
        }
        return Util.constrainValue(representationHolder.getSegmentNum(j), j2, j3);
    }

    @RequiresNonNull({"manifest", "adaptationSetIndices"})
    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i).representations);
        }
        return arrayList;
    }

    private long getAvailableLiveDurationUs(long j, long j2) {
        if (!this.manifest.dynamic || this.representationHolders[0].getSegmentCount() == 0) {
            return C.TIME_UNSET;
        }
        return Math.max(0, Math.min(getNowPeriodTimeUs(j), this.representationHolders[0].getSegmentEndTimeUs(this.representationHolders[0].getLastAvailableSegmentNum(j))) - j2);
    }

    private long getNowPeriodTimeUs(long j) {
        if (this.manifest.availabilityStartTimeMs == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        return j - Util.msToUs(this.manifest.availabilityStartTimeMs + this.manifest.getPeriod(this.periodIndex).startMs);
    }

    /* access modifiers changed from: protected */
    @RequiresNonNull({"#1.chunkExtractor"})
    public Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource2, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2, CmcdData.Factory factory) {
        Representation representation = representationHolder.representation;
        if (rangedUri != null) {
            RangedUri attemptMerge = rangedUri.attemptMerge(rangedUri2, representationHolder.selectedBaseUrl.url);
            if (attemptMerge != null) {
                rangedUri = attemptMerge;
            }
        } else {
            rangedUri = (RangedUri) Assertions.checkNotNull(rangedUri2);
        }
        DataSpec buildDataSpec = DashUtil.buildDataSpec(representation, representationHolder.selectedBaseUrl.url, rangedUri, 0, ImmutableMap.of());
        if (factory != null) {
            buildDataSpec = factory.setObjectType("i").createCmcdData().addToDataSpec(buildDataSpec);
        }
        return new InitializationChunk(dataSource2, buildDataSpec, format, i, obj, representationHolder.chunkExtractor);
    }

    /* access modifiers changed from: protected */
    public Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource2, int i, Format format, int i2, Object obj, long j, int i3, long j2, long j3, CmcdData.Factory factory) {
        DataSpec dataSpec;
        RepresentationHolder representationHolder2 = representationHolder;
        long j4 = j;
        long j5 = j3;
        CmcdData.Factory factory2 = factory;
        Representation representation = representationHolder2.representation;
        long segmentStartTimeUs = representationHolder2.getSegmentStartTimeUs(j4);
        RangedUri segmentUrl = representationHolder2.getSegmentUrl(j4);
        if (representationHolder2.chunkExtractor == null) {
            long segmentEndTimeUs = representationHolder2.getSegmentEndTimeUs(j4);
            DataSpec buildDataSpec = DashUtil.buildDataSpec(representation, representationHolder2.selectedBaseUrl.url, segmentUrl, representationHolder2.isSegmentAvailableAtFullNetworkSpeed(j4, j5) ? 0 : 8, ImmutableMap.of());
            if (factory2 != null) {
                factory2.setChunkDurationUs(segmentEndTimeUs - segmentStartTimeUs).setObjectType(CmcdData.Factory.getObjectType(this.trackSelection));
                Pair<String, String> nextObjectAndRangeRequest = getNextObjectAndRangeRequest(j4, segmentUrl, representationHolder2);
                if (nextObjectAndRangeRequest != null) {
                    factory2.setNextObjectRequest((String) nextObjectAndRangeRequest.first).setNextRangeRequest((String) nextObjectAndRangeRequest.second);
                }
                dataSpec = factory.createCmcdData().addToDataSpec(buildDataSpec);
            } else {
                dataSpec = buildDataSpec;
            }
            return new SingleSampleMediaChunk(dataSource2, dataSpec, format, i2, obj, segmentStartTimeUs, segmentEndTimeUs, j, i, format);
        }
        int i4 = 1;
        int i5 = i3;
        int i6 = 1;
        while (i4 < i5) {
            RangedUri attemptMerge = segmentUrl.attemptMerge(representationHolder2.getSegmentUrl(((long) i4) + j4), representationHolder2.selectedBaseUrl.url);
            if (attemptMerge == null) {
                break;
            }
            i6++;
            i4++;
            segmentUrl = attemptMerge;
        }
        long j6 = (((long) i6) + j4) - 1;
        long segmentEndTimeUs2 = representationHolder2.getSegmentEndTimeUs(j6);
        long access$000 = representationHolder.periodDurationUs;
        long j7 = C.TIME_UNSET;
        if (access$000 != C.TIME_UNSET && access$000 <= segmentEndTimeUs2) {
            j7 = access$000;
        }
        DataSpec buildDataSpec2 = DashUtil.buildDataSpec(representation, representationHolder2.selectedBaseUrl.url, segmentUrl, representationHolder2.isSegmentAvailableAtFullNetworkSpeed(j6, j5) ? 0 : 8, ImmutableMap.of());
        if (factory2 != null) {
            factory2.setChunkDurationUs(segmentEndTimeUs2 - segmentStartTimeUs).setObjectType(CmcdData.Factory.getObjectType(this.trackSelection));
            Pair<String, String> nextObjectAndRangeRequest2 = getNextObjectAndRangeRequest(j4, segmentUrl, representationHolder2);
            if (nextObjectAndRangeRequest2 != null) {
                factory2.setNextObjectRequest((String) nextObjectAndRangeRequest2.first).setNextRangeRequest((String) nextObjectAndRangeRequest2.second);
            }
            buildDataSpec2 = factory.createCmcdData().addToDataSpec(buildDataSpec2);
        }
        DataSpec dataSpec2 = buildDataSpec2;
        long j8 = -representation.presentationTimeOffsetUs;
        if (MimeTypes.isImage(format.sampleMimeType)) {
            j8 += segmentStartTimeUs;
        }
        return new ContainerMediaChunk(dataSource2, dataSpec2, format, i2, obj, segmentStartTimeUs, segmentEndTimeUs2, j2, j7, j, i6, j8, representationHolder2.chunkExtractor);
    }

    private Pair<String, String> getNextObjectAndRangeRequest(long j, RangedUri rangedUri, RepresentationHolder representationHolder) {
        long j2 = j + 1;
        if (j2 >= representationHolder.getSegmentCount()) {
            return null;
        }
        RangedUri segmentUrl = representationHolder.getSegmentUrl(j2);
        String relativePath = UriUtil.getRelativePath(rangedUri.resolveUri(representationHolder.selectedBaseUrl.url), segmentUrl.resolveUri(representationHolder.selectedBaseUrl.url));
        String str = segmentUrl.start + "-";
        if (segmentUrl.length != -1) {
            str = str + (segmentUrl.start + segmentUrl.length);
        }
        return new Pair<>(relativePath, str);
    }

    private RepresentationHolder updateSelectedBaseUrl(int i) {
        RepresentationHolder representationHolder = this.representationHolders[i];
        BaseUrl selectBaseUrl = this.baseUrlExclusionList.selectBaseUrl(representationHolder.representation.baseUrls);
        if (selectBaseUrl == null || selectBaseUrl.equals(representationHolder.selectedBaseUrl)) {
            return representationHolder;
        }
        RepresentationHolder copyWithNewSelectedBaseUrl = representationHolder.copyWithNewSelectedBaseUrl(selectBaseUrl);
        this.representationHolders[i] = copyWithNewSelectedBaseUrl;
        return copyWithNewSelectedBaseUrl;
    }

    protected static final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final long nowPeriodTimeUs;
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder representationHolder2, long j, long j2, long j3) {
            super(j, j2);
            this.representationHolder = representationHolder2;
            this.nowPeriodTimeUs = j3;
        }

        public DataSpec getDataSpec() {
            checkInBounds();
            long currentIndex = getCurrentIndex();
            return DashUtil.buildDataSpec(this.representationHolder.representation, this.representationHolder.selectedBaseUrl.url, this.representationHolder.getSegmentUrl(currentIndex), this.representationHolder.isSegmentAvailableAtFullNetworkSpeed(currentIndex, this.nowPeriodTimeUs) ? 0 : 8, ImmutableMap.of());
        }

        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(getCurrentIndex());
        }

        public long getChunkEndTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(getCurrentIndex());
        }
    }

    protected static final class RepresentationHolder {
        final ChunkExtractor chunkExtractor;
        /* access modifiers changed from: private */
        public final long periodDurationUs;
        public final Representation representation;
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;
        public final BaseUrl selectedBaseUrl;

        RepresentationHolder(long j, Representation representation2, BaseUrl baseUrl, ChunkExtractor chunkExtractor2, long j2, DashSegmentIndex dashSegmentIndex) {
            this.periodDurationUs = j;
            this.representation = representation2;
            this.selectedBaseUrl = baseUrl;
            this.segmentNumShift = j2;
            this.chunkExtractor = chunkExtractor2;
            this.segmentIndex = dashSegmentIndex;
        }

        /* access modifiers changed from: package-private */
        public RepresentationHolder copyWithNewRepresentation(long j, Representation representation2) throws BehindLiveWindowException {
            long segmentNum;
            long segmentNum2;
            long j2 = j;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation2.getIndex();
            if (index == null) {
                return new RepresentationHolder(j, representation2, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index);
            } else if (!index.isExplicit()) {
                return new RepresentationHolder(j, representation2, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index2);
            } else {
                long segmentCount = index.getSegmentCount(j2);
                if (segmentCount == 0) {
                    return new RepresentationHolder(j, representation2, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, index2);
                }
                Assertions.checkStateNotNull(index2);
                long firstSegmentNum = index.getFirstSegmentNum();
                long timeUs = index.getTimeUs(firstSegmentNum);
                long j3 = (segmentCount + firstSegmentNum) - 1;
                long firstSegmentNum2 = index2.getFirstSegmentNum();
                DashSegmentIndex dashSegmentIndex = index;
                long timeUs2 = index2.getTimeUs(firstSegmentNum2);
                long j4 = firstSegmentNum;
                long j5 = this.segmentNumShift;
                int i = ((index.getTimeUs(j3) + index.getDurationUs(j3, j2)) > timeUs2 ? 1 : ((index.getTimeUs(j3) + index.getDurationUs(j3, j2)) == timeUs2 ? 0 : -1));
                if (i == 0) {
                    segmentNum = j3 + 1;
                } else if (i < 0) {
                    throw new BehindLiveWindowException();
                } else if (timeUs2 < timeUs) {
                    segmentNum2 = j5 - (index2.getSegmentNum(timeUs, j2) - j4);
                    return new RepresentationHolder(j, representation2, this.selectedBaseUrl, this.chunkExtractor, segmentNum2, index2);
                } else {
                    segmentNum = dashSegmentIndex.getSegmentNum(timeUs2, j2);
                }
                segmentNum2 = j5 + (segmentNum - firstSegmentNum2);
                return new RepresentationHolder(j, representation2, this.selectedBaseUrl, this.chunkExtractor, segmentNum2, index2);
            }
        }

        /* access modifiers changed from: package-private */
        public RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex dashSegmentIndex) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, dashSegmentIndex);
        }

        /* access modifiers changed from: package-private */
        public RepresentationHolder copyWithNewSelectedBaseUrl(BaseUrl baseUrl) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, baseUrl, this.chunkExtractor, this.segmentNumShift, this.segmentIndex);
        }

        public long getFirstSegmentNum() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstSegmentNum() + this.segmentNumShift;
        }

        public long getFirstAvailableSegmentNum(long j) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstAvailableSegmentNum(this.periodDurationUs, j) + this.segmentNumShift;
        }

        public long getSegmentCount() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(long j) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getTimeUs(j - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(long j) {
            return getSegmentStartTimeUs(j) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getDurationUs(j - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long j) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(long j) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentUrl(j - this.segmentNumShift);
        }

        public long getLastAvailableSegmentNum(long j) {
            return (getFirstAvailableSegmentNum(j) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getAvailableSegmentCount(this.periodDurationUs, j)) - 1;
        }

        public boolean isSegmentAvailableAtFullNetworkSpeed(long j, long j2) {
            if (!((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).isExplicit() && j2 != C.TIME_UNSET && getSegmentEndTimeUs(j) > j2) {
                return false;
            }
            return true;
        }
    }
}
