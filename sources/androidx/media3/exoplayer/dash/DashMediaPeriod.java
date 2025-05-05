package androidx.media3.exoplayer.dash;

import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.Descriptor;
import androidx.media3.exoplayer.dash.manifest.EventStream;
import androidx.media3.exoplayer.dash.manifest.Period;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.EmptySampleStream;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.ChunkSampleStream;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>>, ChunkSampleStream.ReleaseCallback<DashChunkSource> {
    private static final Pattern CEA608_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("CC([1-4])=(.+)");
    private static final Pattern CEA708_SERVICE_DESCRIPTOR_REGEX = Pattern.compile("([1-4])=lang:(\\w+)(,.+)?");
    private final Allocator allocator;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private MediaPeriod.Callback callback;
    private boolean canReportInitialDiscontinuity = true;
    private final DashChunkSource.Factory chunkSourceFactory;
    private final CmcdConfiguration cmcdConfiguration;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private final long elapsedRealtimeOffsetMs;
    private EventSampleStream[] eventSampleStreams;
    private List<EventStream> eventStreams;
    final int id;
    private long initialStartTimeUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private final PlayerId playerId;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams;
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerEmsgHandler.PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream;
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    private final TransferListener transferListener;

    public DashMediaPeriod(int i, DashManifest dashManifest, BaseUrlExclusionList baseUrlExclusionList2, int i2, DashChunkSource.Factory factory, TransferListener transferListener2, CmcdConfiguration cmcdConfiguration2, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher2, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback, PlayerId playerId2) {
        DrmSessionManager drmSessionManager3 = drmSessionManager2;
        Allocator allocator3 = allocator2;
        this.id = i;
        this.manifest = dashManifest;
        this.baseUrlExclusionList = baseUrlExclusionList2;
        this.periodIndex = i2;
        this.chunkSourceFactory = factory;
        this.transferListener = transferListener2;
        this.cmcdConfiguration = cmcdConfiguration2;
        this.drmSessionManager = drmSessionManager3;
        this.drmEventDispatcher = eventDispatcher;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.mediaSourceEventDispatcher = eventDispatcher2;
        this.elapsedRealtimeOffsetMs = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator3;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.playerId = playerId2;
        this.playerEmsgHandler = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator3);
        this.sampleStreams = newSampleStreamArray(0);
        this.eventSampleStreams = new EventSampleStream[0];
        this.trackEmsgHandlerBySampleStream = new IdentityHashMap<>();
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.empty();
        Period period = dashManifest.getPeriod(i2);
        this.eventStreams = period.eventStreams;
        Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups = buildTrackGroups(drmSessionManager3, factory, period.adaptationSets, this.eventStreams);
        this.trackGroups = (TrackGroupArray) buildTrackGroups.first;
        this.trackGroupInfos = (TrackGroupInfo[]) buildTrackGroups.second;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        this.manifest = dashManifest;
        this.periodIndex = i;
        this.playerEmsgHandler.updateManifest(dashManifest);
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSource : chunkSampleStreamArr) {
                chunkSource.getChunkSource().updateManifest(dashManifest, i);
            }
            this.callback.onContinueLoadingRequested(this);
        }
        this.eventStreams = dashManifest.getPeriod(i).eventStreams;
        for (EventSampleStream eventSampleStream : this.eventSampleStreams) {
            Iterator<EventStream> it = this.eventStreams.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventStream next = it.next();
                if (next.id().equals(eventSampleStream.eventStreamId())) {
                    boolean z = true;
                    int periodCount = dashManifest.getPeriodCount() - 1;
                    if (!dashManifest.dynamic || i != periodCount) {
                        z = false;
                    }
                    eventSampleStream.updateEventStream(next, z);
                }
            }
        }
    }

    public void release() {
        this.playerEmsgHandler.release();
        for (ChunkSampleStream<DashChunkSource> release : this.sampleStreams) {
            release.release(this);
        }
        this.callback = null;
    }

    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        PlayerEmsgHandler.PlayerTrackEmsgHandler remove = this.trackEmsgHandlerBySampleStream.remove(chunkSampleStream);
        if (remove != null) {
            remove.release();
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        callback2.onPrepared(this);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        List<AdaptationSet> list2 = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList arrayList = new ArrayList();
        for (ExoTrackSelection next : list) {
            TrackGroupInfo trackGroupInfo = this.trackGroupInfos[this.trackGroups.indexOf(next.getTrackGroup())];
            if (trackGroupInfo.trackGroupCategory == 0) {
                int[] iArr = trackGroupInfo.adaptationSetIndices;
                int length = next.length();
                int[] iArr2 = new int[length];
                for (int i = 0; i < next.length(); i++) {
                    iArr2[i] = next.getIndexInTrackGroup(i);
                }
                Arrays.sort(iArr2);
                int size = list2.get(iArr[0]).representations.size();
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    int i5 = iArr2[i4];
                    while (true) {
                        int i6 = i3 + size;
                        if (i5 < i6) {
                            break;
                        }
                        i2++;
                        size = list2.get(iArr[i2]).representations.size();
                        i3 = i6;
                    }
                    arrayList.add(new StreamKey(this.periodIndex, iArr[i2], i5 - i3));
                }
            }
        }
        return arrayList;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] streamIndexToTrackGroupIndex = getStreamIndexToTrackGroupIndex(exoTrackSelectionArr);
        releaseDisabledStreams(exoTrackSelectionArr, zArr, sampleStreamArr);
        releaseOrphanEmbeddedStreams(exoTrackSelectionArr, sampleStreamArr, streamIndexToTrackGroupIndex);
        selectNewStreams(exoTrackSelectionArr, sampleStreamArr, zArr2, j, streamIndexToTrackGroupIndex);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ChunkSampleStream chunkSampleStream : sampleStreamArr) {
            if (chunkSampleStream instanceof ChunkSampleStream) {
                arrayList.add(chunkSampleStream);
            } else if (chunkSampleStream instanceof EventSampleStream) {
                arrayList2.add((EventSampleStream) chunkSampleStream);
            }
        }
        ChunkSampleStream<DashChunkSource>[] newSampleStreamArray = newSampleStreamArray(arrayList.size());
        this.sampleStreams = newSampleStreamArray;
        arrayList.toArray(newSampleStreamArray);
        EventSampleStream[] eventSampleStreamArr = new EventSampleStream[arrayList2.size()];
        this.eventSampleStreams = eventSampleStreamArr;
        arrayList2.toArray(eventSampleStreamArr);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(arrayList, Lists.transform(arrayList, new DashMediaPeriod$$ExternalSyntheticLambda0()));
        if (this.canReportInitialDiscontinuity) {
            this.canReportInitialDiscontinuity = false;
            this.initialStartTimeUs = j;
        }
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        for (ChunkSampleStream<DashChunkSource> discardBuffer : this.sampleStreams) {
            discardBuffer.discardBuffer(j, z);
        }
    }

    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.compositeSequenceableLoader.continueLoading(loadingInfo);
    }

    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public long readDiscontinuity() {
        for (ChunkSampleStream<DashChunkSource> consumeInitialDiscontinuity : this.sampleStreams) {
            if (consumeInitialDiscontinuity.consumeInitialDiscontinuity()) {
                return this.initialStartTimeUs;
            }
        }
        return C.TIME_UNSET;
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long seekToUs(long j) {
        for (ChunkSampleStream<DashChunkSource> seekToUs : this.sampleStreams) {
            seekToUs.seekToUs(j);
        }
        for (EventSampleStream seekToUs2 : this.eventSampleStreams) {
            seekToUs2.seekToUs(j);
        }
        return j;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            if (chunkSampleStream.primaryTrackType == 2) {
                return chunkSampleStream.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private int[] getStreamIndexToTrackGroupIndex(ExoTrackSelection[] exoTrackSelectionArr) {
        int[] iArr = new int[exoTrackSelectionArr.length];
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            if (exoTrackSelection != null) {
                iArr[i] = this.trackGroups.indexOf(exoTrackSelection.getTrackGroup());
            } else {
                iArr[i] = -1;
            }
        }
        return iArr;
    }

    private void releaseDisabledStreams(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr) {
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            if (exoTrackSelectionArr[i] == null || !zArr[i]) {
                ChunkSampleStream chunkSampleStream = sampleStreamArr[i];
                if (chunkSampleStream instanceof ChunkSampleStream) {
                    chunkSampleStream.release(this);
                } else if (chunkSampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) {
                    ((ChunkSampleStream.EmbeddedSampleStream) chunkSampleStream).release();
                }
                sampleStreamArr[i] = null;
            }
        }
    }

    private void releaseOrphanEmbeddedStreams(ExoTrackSelection[] exoTrackSelectionArr, SampleStream[] sampleStreamArr, int[] iArr) {
        boolean z;
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            SampleStream sampleStream = sampleStreamArr[i];
            if ((sampleStream instanceof EmptySampleStream) || (sampleStream instanceof ChunkSampleStream.EmbeddedSampleStream)) {
                int primaryStreamIndex = getPrimaryStreamIndex(i, iArr);
                if (primaryStreamIndex == -1) {
                    z = sampleStreamArr[i] instanceof EmptySampleStream;
                } else {
                    ChunkSampleStream.EmbeddedSampleStream embeddedSampleStream = sampleStreamArr[i];
                    z = (embeddedSampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) && embeddedSampleStream.parent == sampleStreamArr[primaryStreamIndex];
                }
                if (!z) {
                    ChunkSampleStream.EmbeddedSampleStream embeddedSampleStream2 = sampleStreamArr[i];
                    if (embeddedSampleStream2 instanceof ChunkSampleStream.EmbeddedSampleStream) {
                        embeddedSampleStream2.release();
                    }
                    sampleStreamArr[i] = null;
                }
            }
        }
    }

    private void selectNewStreams(ExoTrackSelection[] exoTrackSelectionArr, SampleStream[] sampleStreamArr, boolean[] zArr, long j, int[] iArr) {
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            if (exoTrackSelection != null) {
                ChunkSampleStream chunkSampleStream = sampleStreamArr[i];
                if (chunkSampleStream == null) {
                    zArr[i] = true;
                    TrackGroupInfo trackGroupInfo = this.trackGroupInfos[iArr[i]];
                    if (trackGroupInfo.trackGroupCategory == 0) {
                        sampleStreamArr[i] = buildSampleStream(trackGroupInfo, exoTrackSelection, j);
                    } else if (trackGroupInfo.trackGroupCategory == 2) {
                        sampleStreamArr[i] = new EventSampleStream(this.eventStreams.get(trackGroupInfo.eventStreamGroupIndex), exoTrackSelection.getTrackGroup().getFormat(0), this.manifest.dynamic);
                    }
                } else if (chunkSampleStream instanceof ChunkSampleStream) {
                    ((DashChunkSource) chunkSampleStream.getChunkSource()).updateTrackSelection(exoTrackSelection);
                }
            }
        }
        for (int i2 = 0; i2 < exoTrackSelectionArr.length; i2++) {
            if (sampleStreamArr[i2] == null && exoTrackSelectionArr[i2] != null) {
                TrackGroupInfo trackGroupInfo2 = this.trackGroupInfos[iArr[i2]];
                if (trackGroupInfo2.trackGroupCategory == 1) {
                    int primaryStreamIndex = getPrimaryStreamIndex(i2, iArr);
                    if (primaryStreamIndex == -1) {
                        sampleStreamArr[i2] = new EmptySampleStream();
                    } else {
                        sampleStreamArr[i2] = sampleStreamArr[primaryStreamIndex].selectEmbeddedTrack(j, trackGroupInfo2.trackType);
                    }
                }
            }
        }
    }

    private int getPrimaryStreamIndex(int i, int[] iArr) {
        int i2 = iArr[i];
        if (i2 == -1) {
            return -1;
        }
        int i3 = this.trackGroupInfos[i2].primaryTrackGroupIndex;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 == i3 && this.trackGroupInfos[i5].trackGroupCategory == 0) {
                return i4;
            }
        }
        return -1;
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(DrmSessionManager drmSessionManager2, DashChunkSource.Factory factory, List<AdaptationSet> list, List<EventStream> list2) {
        int[][] groupedAdaptationSetIndices = getGroupedAdaptationSetIndices(list);
        int length = groupedAdaptationSetIndices.length;
        boolean[] zArr = new boolean[length];
        Format[][] formatArr = new Format[length][];
        int identifyEmbeddedTracks = identifyEmbeddedTracks(length, list, groupedAdaptationSetIndices, zArr, formatArr) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[identifyEmbeddedTracks];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[identifyEmbeddedTracks];
        buildManifestEventTrackGroupInfos(list2, trackGroupArr, trackGroupInfoArr, buildPrimaryAndEmbeddedTrackGroupInfos(drmSessionManager2, factory, list, groupedAdaptationSetIndices, length, zArr, formatArr, trackGroupArr, trackGroupInfoArr));
        return Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0057, code lost:
        r7 = (java.lang.Integer) r1.get(java.lang.Long.valueOf(java.lang.Long.parseLong(r7.value)));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int[][] getGroupedAdaptationSetIndices(java.util.List<androidx.media3.exoplayer.dash.manifest.AdaptationSet> r12) {
        /*
            int r0 = r12.size()
            java.util.HashMap r1 = com.google.common.collect.Maps.newHashMapWithExpectedSize(r0)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r0)
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>(r0)
            r4 = 0
            r5 = r4
        L_0x0014:
            if (r5 >= r0) goto L_0x003e
            java.lang.Object r6 = r12.get(r5)
            androidx.media3.exoplayer.dash.manifest.AdaptationSet r6 = (androidx.media3.exoplayer.dash.manifest.AdaptationSet) r6
            long r6 = r6.id
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            r1.put(r6, r7)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            r6.add(r7)
            r2.add(r6)
            r3.put(r5, r6)
            int r5 = r5 + 1
            goto L_0x0014
        L_0x003e:
            r5 = r4
        L_0x003f:
            if (r5 >= r0) goto L_0x00bc
            java.lang.Object r6 = r12.get(r5)
            androidx.media3.exoplayer.dash.manifest.AdaptationSet r6 = (androidx.media3.exoplayer.dash.manifest.AdaptationSet) r6
            java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r7 = r6.essentialProperties
            androidx.media3.exoplayer.dash.manifest.Descriptor r7 = findTrickPlayProperty(r7)
            if (r7 != 0) goto L_0x0055
            java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r7 = r6.supplementalProperties
            androidx.media3.exoplayer.dash.manifest.Descriptor r7 = findTrickPlayProperty(r7)
        L_0x0055:
            if (r7 == 0) goto L_0x006e
            java.lang.String r7 = r7.value
            long r7 = java.lang.Long.parseLong(r7)
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            java.lang.Object r7 = r1.get(r7)
            java.lang.Integer r7 = (java.lang.Integer) r7
            if (r7 == 0) goto L_0x006e
            int r7 = r7.intValue()
            goto L_0x006f
        L_0x006e:
            r7 = r5
        L_0x006f:
            if (r7 != r5) goto L_0x00a2
            java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r6 = r6.supplementalProperties
            androidx.media3.exoplayer.dash.manifest.Descriptor r6 = findAdaptationSetSwitchingProperty(r6)
            if (r6 == 0) goto L_0x00a2
            java.lang.String r6 = r6.value
            java.lang.String r8 = ","
            java.lang.String[] r6 = androidx.media3.common.util.Util.split(r6, r8)
            int r8 = r6.length
            r9 = r4
        L_0x0083:
            if (r9 >= r8) goto L_0x00a2
            r10 = r6[r9]
            long r10 = java.lang.Long.parseLong(r10)
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.Object r10 = r1.get(r10)
            java.lang.Integer r10 = (java.lang.Integer) r10
            if (r10 == 0) goto L_0x009f
            int r10 = r10.intValue()
            int r7 = java.lang.Math.min(r7, r10)
        L_0x009f:
            int r9 = r9 + 1
            goto L_0x0083
        L_0x00a2:
            if (r7 == r5) goto L_0x00b9
            java.lang.Object r6 = r3.get(r5)
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r3.get(r7)
            java.util.List r7 = (java.util.List) r7
            r7.addAll(r6)
            r3.put(r5, r7)
            r2.remove(r6)
        L_0x00b9:
            int r5 = r5 + 1
            goto L_0x003f
        L_0x00bc:
            int r12 = r2.size()
            int[][] r0 = new int[r12][]
        L_0x00c2:
            if (r4 >= r12) goto L_0x00d6
            java.lang.Object r1 = r2.get(r4)
            java.util.Collection r1 = (java.util.Collection) r1
            int[] r1 = com.google.common.primitives.Ints.toArray(r1)
            r0[r4] = r1
            java.util.Arrays.sort(r1)
            int r4 = r4 + 1
            goto L_0x00c2
        L_0x00d6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.DashMediaPeriod.getGroupedAdaptationSetIndices(java.util.List):int[][]");
    }

    private static int identifyEmbeddedTracks(int i, List<AdaptationSet> list, int[][] iArr, boolean[] zArr, Format[][] formatArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (hasEventMessageTrack(list, iArr[i3])) {
                zArr[i3] = true;
                i2++;
            }
            Format[] closedCaptionTrackFormats = getClosedCaptionTrackFormats(list, iArr[i3]);
            formatArr[i3] = closedCaptionTrackFormats;
            if (closedCaptionTrackFormats.length != 0) {
                i2++;
            }
        }
        return i2;
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(DrmSessionManager drmSessionManager2, DashChunkSource.Factory factory, List<AdaptationSet> list, int[][] iArr, int i, boolean[] zArr, Format[][] formatArr, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr) {
        String str;
        int i2;
        int i3;
        int i4;
        DashChunkSource.Factory factory2 = factory;
        List<AdaptationSet> list2 = list;
        int i5 = i;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i5) {
            int[] iArr2 = iArr[i6];
            ArrayList arrayList = new ArrayList();
            for (int i8 : iArr2) {
                arrayList.addAll(list2.get(i8).representations);
            }
            int size = arrayList.size();
            Format[] formatArr2 = new Format[size];
            for (int i9 = 0; i9 < size; i9++) {
                Format format = ((Representation) arrayList.get(i9)).format;
                formatArr2[i9] = format.buildUpon().setCryptoType(drmSessionManager2.getCryptoType(format)).build();
            }
            DrmSessionManager drmSessionManager3 = drmSessionManager2;
            AdaptationSet adaptationSet = list2.get(iArr2[0]);
            if (adaptationSet.id != -1) {
                str = Long.toString(adaptationSet.id);
            } else {
                str = "unset:" + i6;
            }
            int i10 = i7 + 1;
            if (zArr[i6]) {
                i2 = i10 + 1;
            } else {
                i2 = i10;
                i10 = -1;
            }
            if (formatArr[i6].length != 0) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
                i2 = -1;
            }
            maybeUpdateFormatsForParsedText(factory2, formatArr2);
            trackGroupArr[i7] = new TrackGroup(str, formatArr2);
            trackGroupInfoArr[i7] = TrackGroupInfo.primaryTrack(adaptationSet.type, iArr2, i7, i10, i2);
            if (i10 != -1) {
                String str2 = str + ":emsg";
                trackGroupArr[i10] = new TrackGroup(str2, new Format.Builder().setId(str2).setSampleMimeType(MimeTypes.APPLICATION_EMSG).build());
                trackGroupInfoArr[i10] = TrackGroupInfo.embeddedEmsgTrack(iArr2, i7);
                i4 = -1;
            } else {
                i4 = -1;
            }
            if (i2 != i4) {
                trackGroupInfoArr[i2] = TrackGroupInfo.embeddedClosedCaptionTrack(iArr2, i7, ImmutableList.copyOf((E[]) formatArr[i6]));
                maybeUpdateFormatsForParsedText(factory2, formatArr[i6]);
                trackGroupArr[i2] = new TrackGroup(str + ":cc", formatArr[i6]);
            }
            i6++;
            i7 = i3;
        }
        return i7;
    }

    private static void buildManifestEventTrackGroupInfos(List<EventStream> list, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr, int i) {
        int i2 = 0;
        while (i2 < list.size()) {
            EventStream eventStream = list.get(i2);
            trackGroupArr[i] = new TrackGroup(eventStream.id() + ":" + i2, new Format.Builder().setId(eventStream.id()).setSampleMimeType(MimeTypes.APPLICATION_EMSG).build());
            trackGroupInfoArr[i] = TrackGroupInfo.mpdEventTrack(i2);
            i2++;
            i++;
        }
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo, ExoTrackSelection exoTrackSelection, long j) {
        int i;
        TrackGroup trackGroup;
        ImmutableList<Format> immutableList;
        int i2;
        ChunkSampleStream<DashChunkSource> chunkSampleStream;
        TrackGroupInfo trackGroupInfo2 = trackGroupInfo;
        boolean z = trackGroupInfo2.embeddedEventMessageTrackGroupIndex != -1;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = null;
        if (z) {
            trackGroup = this.trackGroups.get(trackGroupInfo2.embeddedEventMessageTrackGroupIndex);
            i = 1;
        } else {
            trackGroup = null;
            i = 0;
        }
        if (trackGroupInfo2.embeddedClosedCaptionTrackGroupIndex != -1) {
            immutableList = this.trackGroupInfos[trackGroupInfo2.embeddedClosedCaptionTrackGroupIndex].embeddedClosedCaptionTrackOriginalFormats;
        } else {
            immutableList = ImmutableList.of();
        }
        int size = i + immutableList.size();
        Format[] formatArr = new Format[size];
        int[] iArr = new int[size];
        if (z) {
            formatArr[0] = trackGroup.getFormat(0);
            iArr[0] = 5;
            i2 = 1;
        } else {
            i2 = 0;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < immutableList.size(); i3++) {
            Format format = (Format) immutableList.get(i3);
            formatArr[i2] = format;
            iArr[i2] = 3;
            arrayList.add(format);
            i2++;
        }
        if (this.manifest.dynamic && z) {
            playerTrackEmsgHandler = this.playerEmsgHandler.newPlayerTrackEmsgHandler();
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = playerTrackEmsgHandler;
        DashChunkSource createDashChunkSource = this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.baseUrlExclusionList, this.periodIndex, trackGroupInfo2.adaptationSetIndices, exoTrackSelection, trackGroupInfo2.trackType, this.elapsedRealtimeOffsetMs, z, arrayList, playerTrackEmsgHandler2, this.transferListener, this.playerId, this.cmcdConfiguration);
        int i4 = trackGroupInfo2.trackType;
        ChunkSampleStream<DashChunkSource> chunkSampleStream2 = r1;
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler3 = playerTrackEmsgHandler2;
        ChunkSampleStream<DashChunkSource> chunkSampleStream3 = new ChunkSampleStream<>(i4, iArr, formatArr, createDashChunkSource, this, this.allocator, j, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.mediaSourceEventDispatcher, this.canReportInitialDiscontinuity, (ReleasableExecutor) null);
        synchronized (this) {
            chunkSampleStream = chunkSampleStream2;
            this.trackEmsgHandlerBySampleStream.put(chunkSampleStream, playerTrackEmsgHandler3);
        }
        return chunkSampleStream;
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> list) {
        return findDescriptor(list, "urn:mpeg:dash:adaptation-set-switching:2016");
    }

    private static Descriptor findTrickPlayProperty(List<Descriptor> list) {
        return findDescriptor(list, "http://dashif.org/guidelines/trickmode");
    }

    private static Descriptor findDescriptor(List<Descriptor> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if (str.equals(descriptor.schemeIdUri)) {
                return descriptor;
            }
        }
        return null;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Representation> list2 = list.get(i).representations;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!list2.get(i2).inbandEventStreams.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Format[] getClosedCaptionTrackFormats(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            AdaptationSet adaptationSet = list.get(i);
            List<Descriptor> list2 = list.get(i).accessibilityDescriptors;
            int i2 = 0;
            while (i2 < list2.size()) {
                Descriptor descriptor = list2.get(i2);
                if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri)) {
                    return parseClosedCaptionDescriptor(descriptor, CEA608_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA608).setId(adaptationSet.id + ":cea608").build());
                } else if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri)) {
                    return parseClosedCaptionDescriptor(descriptor, CEA708_SERVICE_DESCRIPTOR_REGEX, new Format.Builder().setSampleMimeType(MimeTypes.APPLICATION_CEA708).setId(adaptationSet.id + ":cea708").build());
                } else {
                    i2++;
                }
            }
        }
        return new Format[0];
    }

    private static Format[] parseClosedCaptionDescriptor(Descriptor descriptor, Pattern pattern, Format format) {
        String str = descriptor.value;
        if (str == null) {
            return new Format[]{format};
        }
        String[] split = Util.split(str, ";");
        Format[] formatArr = new Format[split.length];
        for (int i = 0; i < split.length; i++) {
            Matcher matcher = pattern.matcher(split[i]);
            if (!matcher.matches()) {
                return new Format[]{format};
            }
            int parseInt = Integer.parseInt(matcher.group(1));
            formatArr[i] = format.buildUpon().setId(format.id + ":" + parseInt).setAccessibilityChannel(parseInt).setLanguage(matcher.group(2)).build();
        }
        return formatArr;
    }

    private static void maybeUpdateFormatsForParsedText(DashChunkSource.Factory factory, Format[] formatArr) {
        for (int i = 0; i < formatArr.length; i++) {
            formatArr[i] = factory.getOutputTextFormat(formatArr[i]);
        }
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int i) {
        return new ChunkSampleStream[i];
    }

    private static final class TrackGroupInfo {
        private static final int CATEGORY_EMBEDDED = 1;
        private static final int CATEGORY_MANIFEST_EVENTS = 2;
        private static final int CATEGORY_PRIMARY = 0;
        public final int[] adaptationSetIndices;
        public final int embeddedClosedCaptionTrackGroupIndex;
        public final ImmutableList<Format> embeddedClosedCaptionTrackOriginalFormats;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface TrackGroupCategory {
        }

        public static TrackGroupInfo primaryTrack(int i, int[] iArr, int i2, int i3, int i4) {
            return new TrackGroupInfo(i, 0, iArr, i2, i3, i4, -1, ImmutableList.of());
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] iArr, int i) {
            return new TrackGroupInfo(5, 1, iArr, i, -1, -1, -1, ImmutableList.of());
        }

        public static TrackGroupInfo embeddedClosedCaptionTrack(int[] iArr, int i, ImmutableList<Format> immutableList) {
            return new TrackGroupInfo(3, 1, iArr, i, -1, -1, -1, immutableList);
        }

        public static TrackGroupInfo mpdEventTrack(int i) {
            return new TrackGroupInfo(5, 2, new int[0], -1, -1, -1, i, ImmutableList.of());
        }

        private TrackGroupInfo(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6, ImmutableList<Format> immutableList) {
            this.trackType = i;
            this.adaptationSetIndices = iArr;
            this.trackGroupCategory = i2;
            this.primaryTrackGroupIndex = i3;
            this.embeddedEventMessageTrackGroupIndex = i4;
            this.embeddedClosedCaptionTrackGroupIndex = i5;
            this.eventStreamGroupIndex = i6;
            this.embeddedClosedCaptionTrackOriginalFormats = immutableList;
        }
    }
}
