package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.StreamKey;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import io.sentry.protocol.SentryThread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class HlsMediaPeriod implements MediaPeriod, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private int audioVideoSampleStreamWrapperCount;
    private final CmcdConfiguration cmcdConfiguration;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private int[][] manifestUrlIndicesPerWrapper;
    /* access modifiers changed from: private */
    public MediaPeriod.Callback mediaPeriodCallback;
    private final TransferListener mediaTransferListener;
    private final int metadataType;
    private int pendingPrepareCount;
    private final PlayerId playerId;
    /* access modifiers changed from: private */
    public final HlsPlaylistTracker playlistTracker;
    private final HlsSampleStreamWrapper.Callback sampleStreamWrapperCallback = new SampleStreamWrapperCallback();
    /* access modifiers changed from: private */
    public HlsSampleStreamWrapper[] sampleStreamWrappers;
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    /* access modifiers changed from: private */
    public TrackGroupArray trackGroups;
    private final boolean useSessionKeys;

    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    static /* synthetic */ int access$106(HlsMediaPeriod hlsMediaPeriod) {
        int i = hlsMediaPeriod.pendingPrepareCount - 1;
        hlsMediaPeriod.pendingPrepareCount = i;
        return i;
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, TransferListener transferListener, CmcdConfiguration cmcdConfiguration2, DrmSessionManager drmSessionManager2, DrmSessionEventListener.EventDispatcher eventDispatcher2, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, MediaSourceEventListener.EventDispatcher eventDispatcher3, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, boolean z, int i, boolean z2, PlayerId playerId2, long j) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.mediaTransferListener = transferListener;
        this.cmcdConfiguration = cmcdConfiguration2;
        this.drmSessionManager = drmSessionManager2;
        this.drmEventDispatcher = eventDispatcher2;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.eventDispatcher = eventDispatcher3;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.allowChunklessPreparation = z;
        this.metadataType = i;
        this.useSessionKeys = z2;
        this.playerId = playerId2;
        this.timestampAdjusterInitializationTimeoutMs = j;
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.empty();
        this.streamWrapperIndices = new IdentityHashMap<>();
        this.timestampAdjusterProvider = new TimestampAdjusterProvider();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[0];
        this.enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
        this.manifestUrlIndicesPerWrapper = new int[0][];
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        for (HlsSampleStreamWrapper release : this.sampleStreamWrappers) {
            release.release();
        }
        this.mediaPeriodCallback = null;
    }

    public void prepare(MediaPeriod.Callback callback, long j) {
        this.mediaPeriodCallback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper maybeThrowPrepareError : this.sampleStreamWrappers) {
            maybeThrowPrepareError.maybeThrowPrepareError();
        }
    }

    public TrackGroupArray getTrackGroups() {
        return (TrackGroupArray) Assertions.checkNotNull(this.trackGroups);
    }

    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        TrackGroupArray trackGroupArray;
        int[] iArr;
        int i;
        HlsMediaPeriod hlsMediaPeriod = this;
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(hlsMediaPeriod.playlistTracker.getMultivariantPlaylist());
        boolean z = !hlsMultivariantPlaylist.variants.isEmpty();
        int length = hlsMediaPeriod.sampleStreamWrappers.length - hlsMultivariantPlaylist.subtitles.size();
        int i2 = 0;
        if (z) {
            HlsSampleStreamWrapper hlsSampleStreamWrapper = hlsMediaPeriod.sampleStreamWrappers[0];
            iArr = hlsMediaPeriod.manifestUrlIndicesPerWrapper[0];
            trackGroupArray = hlsSampleStreamWrapper.getTrackGroups();
            i = hlsSampleStreamWrapper.getPrimaryTrackGroupIndex();
        } else {
            iArr = new int[0];
            trackGroupArray = TrackGroupArray.EMPTY;
            i = 0;
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        for (ExoTrackSelection next : list) {
            TrackGroup trackGroup = next.getTrackGroup();
            int indexOf = trackGroupArray.indexOf(trackGroup);
            if (indexOf == -1) {
                int i3 = z;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = hlsMediaPeriod.sampleStreamWrappers;
                    if (i3 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    } else if (hlsSampleStreamWrapperArr[i3].getTrackGroups().indexOf(trackGroup) != -1) {
                        int i4 = i3 < length ? 1 : 2;
                        int[] iArr2 = hlsMediaPeriod.manifestUrlIndicesPerWrapper[i3];
                        int i5 = 0;
                        while (i5 < next.length()) {
                            arrayList.add(new StreamKey(i4, iArr2[next.getIndexInTrackGroup(i5)]));
                            i5++;
                        }
                    } else {
                        i3++;
                        hlsMediaPeriod = this;
                    }
                }
            } else if (indexOf == i) {
                for (int i6 = i2; i6 < next.length(); i6++) {
                    arrayList.add(new StreamKey(i2, iArr[next.getIndexInTrackGroup(i6)]));
                }
                z3 = true;
            } else {
                z2 = true;
            }
            hlsMediaPeriod = this;
            i2 = 0;
        }
        if (z2 && !z3) {
            int i7 = iArr[0];
            int i8 = hlsMultivariantPlaylist.variants.get(iArr[0]).format.bitrate;
            for (int i9 = 1; i9 < iArr.length; i9++) {
                int i10 = hlsMultivariantPlaylist.variants.get(iArr[i9]).format.bitrate;
                if (i10 < i8) {
                    i7 = iArr[i9];
                    i8 = i10;
                }
            }
            arrayList.add(new StreamKey(0, i7));
        }
        return arrayList;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        boolean z;
        ExoTrackSelection[] exoTrackSelectionArr2 = exoTrackSelectionArr;
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        int[] iArr = new int[exoTrackSelectionArr2.length];
        int[] iArr2 = new int[exoTrackSelectionArr2.length];
        for (int i = 0; i < exoTrackSelectionArr2.length; i++) {
            SampleStream sampleStream = sampleStreamArr2[i];
            iArr[i] = sampleStream == null ? -1 : this.streamWrapperIndices.get(sampleStream).intValue();
            iArr2[i] = -1;
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr2[i];
            if (exoTrackSelection != null) {
                TrackGroup trackGroup = exoTrackSelection.getTrackGroup();
                int i2 = 0;
                while (true) {
                    HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
                    if (i2 >= hlsSampleStreamWrapperArr.length) {
                        break;
                    } else if (hlsSampleStreamWrapperArr[i2].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i] = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        this.streamWrapperIndices.clear();
        int length = exoTrackSelectionArr2.length;
        SampleStream[] sampleStreamArr3 = new SampleStream[length];
        SampleStream[] sampleStreamArr4 = new SampleStream[exoTrackSelectionArr2.length];
        ExoTrackSelection[] exoTrackSelectionArr3 = new ExoTrackSelection[exoTrackSelectionArr2.length];
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = new HlsSampleStreamWrapper[this.sampleStreamWrappers.length];
        int i3 = 0;
        int i4 = 0;
        boolean z2 = false;
        while (i4 < this.sampleStreamWrappers.length) {
            for (int i5 = 0; i5 < exoTrackSelectionArr2.length; i5++) {
                ExoTrackSelection exoTrackSelection2 = null;
                sampleStreamArr4[i5] = iArr[i5] == i4 ? sampleStreamArr2[i5] : null;
                if (iArr2[i5] == i4) {
                    exoTrackSelection2 = exoTrackSelectionArr2[i5];
                }
                exoTrackSelectionArr3[i5] = exoTrackSelection2;
            }
            HlsSampleStreamWrapper hlsSampleStreamWrapper = this.sampleStreamWrappers[i4];
            HlsSampleStreamWrapper hlsSampleStreamWrapper2 = hlsSampleStreamWrapper;
            int i6 = i3;
            int i7 = length;
            int i8 = i4;
            ExoTrackSelection[] exoTrackSelectionArr4 = exoTrackSelectionArr3;
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr3 = hlsSampleStreamWrapperArr2;
            boolean selectTracks = hlsSampleStreamWrapper.selectTracks(exoTrackSelectionArr3, zArr, sampleStreamArr4, zArr2, j, z2);
            int i9 = 0;
            boolean z3 = false;
            while (true) {
                z = true;
                if (i9 >= exoTrackSelectionArr2.length) {
                    break;
                }
                SampleStream sampleStream2 = sampleStreamArr4[i9];
                if (iArr2[i9] == i8) {
                    Assertions.checkNotNull(sampleStream2);
                    sampleStreamArr3[i9] = sampleStream2;
                    this.streamWrapperIndices.put(sampleStream2, Integer.valueOf(i8));
                    z3 = true;
                } else if (iArr[i9] == i8) {
                    if (sampleStream2 != null) {
                        z = false;
                    }
                    Assertions.checkState(z);
                }
                i9++;
            }
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr4 = hlsSampleStreamWrapperArr3;
            if (z3) {
                hlsSampleStreamWrapperArr4[i6] = hlsSampleStreamWrapper2;
                i3 = i6 + 1;
                if (i6 == 0) {
                    hlsSampleStreamWrapper2.setIsPrimaryTimestampSource(true);
                    if (!selectTracks) {
                        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr5 = this.enabledSampleStreamWrappers;
                        if (hlsSampleStreamWrapperArr5.length != 0 && hlsSampleStreamWrapper2 == hlsSampleStreamWrapperArr5[0]) {
                        }
                    }
                    this.timestampAdjusterProvider.reset();
                    z2 = true;
                } else {
                    if (i8 >= this.audioVideoSampleStreamWrapperCount) {
                        z = false;
                    }
                    hlsSampleStreamWrapper2.setIsPrimaryTimestampSource(z);
                }
            } else {
                i3 = i6;
            }
            i4 = i8 + 1;
            sampleStreamArr2 = sampleStreamArr;
            hlsSampleStreamWrapperArr2 = hlsSampleStreamWrapperArr4;
            length = i7;
            exoTrackSelectionArr3 = exoTrackSelectionArr4;
        }
        System.arraycopy(sampleStreamArr3, 0, sampleStreamArr2, 0, length);
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr6 = (HlsSampleStreamWrapper[]) Util.nullSafeArrayCopy(hlsSampleStreamWrapperArr2, i3);
        this.enabledSampleStreamWrappers = hlsSampleStreamWrapperArr6;
        ImmutableList copyOf = ImmutableList.copyOf((E[]) hlsSampleStreamWrapperArr6);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.create(copyOf, Lists.transform(copyOf, new HlsMediaPeriod$$ExternalSyntheticLambda0()));
        return j;
    }

    public void discardBuffer(long j, boolean z) {
        for (HlsSampleStreamWrapper discardBuffer : this.enabledSampleStreamWrappers) {
            discardBuffer.discardBuffer(j, z);
        }
    }

    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        if (this.trackGroups != null) {
            return this.compositeSequenceableLoader.continueLoading(loadingInfo);
        }
        for (HlsSampleStreamWrapper continuePreparing : this.sampleStreamWrappers) {
            continuePreparing.continuePreparing();
        }
        return false;
    }

    public boolean isLoading() {
        return this.compositeSequenceableLoader.isLoading();
    }

    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    public long seekToUs(long j) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean seekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j, false);
            int i = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i].seekToUs(j, seekToUs);
                i++;
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            if (hlsSampleStreamWrapper.isVideoSampleStream()) {
                return hlsSampleStreamWrapper.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    public void onPlaylistChanged() {
        for (HlsSampleStreamWrapper onPlaylistUpdated : this.sampleStreamWrappers) {
            onPlaylistUpdated.onPlaylistUpdated();
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
    }

    public boolean onPlaylistError(Uri uri, LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo, boolean z) {
        boolean z2 = true;
        for (HlsSampleStreamWrapper onPlaylistError : this.sampleStreamWrappers) {
            z2 &= onPlaylistError.onPlaylistError(uri, loadErrorInfo, z);
        }
        this.mediaPeriodCallback.onContinueLoadingRequested(this);
        return z2;
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        Map<String, DrmInitData> map;
        HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist());
        if (this.useSessionKeys) {
            map = deriveOverridingDrmInitData(hlsMultivariantPlaylist.sessionKeyDrmInitData);
        } else {
            map = Collections.emptyMap();
        }
        Map<String, DrmInitData> map2 = map;
        int i = 1;
        boolean z = !hlsMultivariantPlaylist.variants.isEmpty();
        List<HlsMultivariantPlaylist.Rendition> list = hlsMultivariantPlaylist.audios;
        List<HlsMultivariantPlaylist.Rendition> list2 = hlsMultivariantPlaylist.subtitles;
        int i2 = 0;
        this.pendingPrepareCount = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            buildAndPrepareMainSampleStreamWrapper(hlsMultivariantPlaylist, j, arrayList, arrayList2, map2);
        }
        buildAndPrepareAudioSampleStreamWrappers(j, list, arrayList, arrayList2, map2);
        this.audioVideoSampleStreamWrapperCount = arrayList.size();
        int i3 = 0;
        while (i3 < list2.size()) {
            HlsMultivariantPlaylist.Rendition rendition = list2.get(i3);
            String str = "subtitle:" + i3 + ":" + rendition.name;
            Format format = rendition.format;
            Uri[] uriArr = new Uri[i];
            uriArr[i2] = rendition.url;
            Format[] formatArr = new Format[i];
            formatArr[i2] = format;
            ArrayList arrayList3 = arrayList2;
            int i4 = i3;
            HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(str, 3, uriArr, formatArr, (Format) null, Collections.emptyList(), map2, j);
            arrayList3.add(new int[]{i4});
            arrayList.add(buildSampleStreamWrapper);
            buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(str, this.extractorFactory.getOutputTextFormat(format))}, 0, new int[0]);
            i3 = i4 + 1;
            i2 = 0;
            arrayList2 = arrayList3;
            i = 1;
        }
        int i5 = i2;
        this.sampleStreamWrappers = (HlsSampleStreamWrapper[]) arrayList.toArray(new HlsSampleStreamWrapper[i5]);
        this.manifestUrlIndicesPerWrapper = (int[][]) arrayList2.toArray(new int[i5][]);
        this.pendingPrepareCount = this.sampleStreamWrappers.length;
        for (int i6 = i5; i6 < this.audioVideoSampleStreamWrapperCount; i6++) {
            this.sampleStreamWrappers[i6].setIsPrimaryTimestampSource(true);
        }
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
        int length = hlsSampleStreamWrapperArr.length;
        for (int i7 = i5; i7 < length; i7++) {
            hlsSampleStreamWrapperArr[i7].continuePreparing();
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMultivariantPlaylist hlsMultivariantPlaylist, long j, List<HlsSampleStreamWrapper> list, List<int[]> list2, Map<String, DrmInitData> map) {
        int i;
        boolean z;
        boolean z2;
        int i2;
        HlsMultivariantPlaylist hlsMultivariantPlaylist2 = hlsMultivariantPlaylist;
        int size = hlsMultivariantPlaylist2.variants.size();
        int[] iArr = new int[size];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < hlsMultivariantPlaylist2.variants.size(); i5++) {
            Format format = hlsMultivariantPlaylist2.variants.get(i5).format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                iArr[i5] = 2;
                i3++;
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                iArr[i5] = 1;
                i4++;
            } else {
                iArr[i5] = -1;
            }
        }
        if (i3 > 0) {
            i = i3;
            z = false;
            z2 = true;
        } else if (i4 < size) {
            i = size - i4;
            z2 = false;
            z = true;
        } else {
            i = size;
            z2 = false;
            z = false;
        }
        Uri[] uriArr = new Uri[i];
        Format[] formatArr = new Format[i];
        int[] iArr2 = new int[i];
        int i6 = 0;
        for (int i7 = 0; i7 < hlsMultivariantPlaylist2.variants.size(); i7++) {
            if ((!z2 || iArr[i7] == 2) && (!z || iArr[i7] != 1)) {
                HlsMultivariantPlaylist.Variant variant = hlsMultivariantPlaylist2.variants.get(i7);
                uriArr[i6] = variant.url;
                formatArr[i6] = variant.format;
                iArr2[i6] = i7;
                i6++;
            }
        }
        String str = formatArr[0].codecs;
        int codecCountOfType = Util.getCodecCountOfType(str, 2);
        int codecCountOfType2 = Util.getCodecCountOfType(str, 1);
        boolean z3 = (codecCountOfType2 == 1 || (codecCountOfType2 == 0 && hlsMultivariantPlaylist2.audios.isEmpty())) && codecCountOfType <= 1 && codecCountOfType2 + codecCountOfType > 0;
        int i8 = (z2 || codecCountOfType2 <= 0) ? 0 : 1;
        Format format2 = hlsMultivariantPlaylist2.muxedAudioFormat;
        String str2 = SentryThread.JsonKeys.MAIN;
        int i9 = codecCountOfType2;
        int i10 = codecCountOfType;
        HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(SentryThread.JsonKeys.MAIN, i8, uriArr, formatArr, format2, hlsMultivariantPlaylist2.muxedCaptionFormats, map, j);
        list.add(buildSampleStreamWrapper);
        list2.add(iArr2);
        if (this.allowChunklessPreparation && z3) {
            ArrayList arrayList = new ArrayList();
            if (i10 > 0) {
                Format[] formatArr2 = new Format[i];
                for (int i11 = 0; i11 < i; i11++) {
                    formatArr2[i11] = deriveVideoFormat(formatArr[i11]);
                }
                arrayList.add(new TrackGroup(str2, formatArr2));
                if (i9 > 0 && (hlsMultivariantPlaylist2.muxedAudioFormat != null || hlsMultivariantPlaylist2.audios.isEmpty())) {
                    arrayList.add(new TrackGroup("main:audio", deriveAudioFormat(formatArr[0], hlsMultivariantPlaylist2.muxedAudioFormat, false)));
                }
                List<Format> list3 = hlsMultivariantPlaylist2.muxedCaptionFormats;
                if (list3 != null) {
                    for (int i12 = 0; i12 < list3.size(); i12++) {
                        arrayList.add(new TrackGroup("main:cc:" + i12, this.extractorFactory.getOutputTextFormat(list3.get(i12))));
                    }
                }
                i2 = 1;
            } else {
                Format[] formatArr3 = new Format[i];
                for (int i13 = 0; i13 < i; i13++) {
                    formatArr3[i13] = deriveAudioFormat(formatArr[i13], hlsMultivariantPlaylist2.muxedAudioFormat, true);
                }
                i2 = 1;
                arrayList.add(new TrackGroup(str2, formatArr3));
            }
            Format[] formatArr4 = new Format[i2];
            formatArr4[0] = new Format.Builder().setId("ID3").setSampleMimeType(MimeTypes.APPLICATION_ID3).build();
            TrackGroup trackGroup = new TrackGroup("main:id3", formatArr4);
            arrayList.add(trackGroup);
            buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo((TrackGroup[]) arrayList.toArray(new TrackGroup[0]), 0, arrayList.indexOf(trackGroup));
        }
    }

    private void buildAndPrepareAudioSampleStreamWrappers(long j, List<HlsMultivariantPlaylist.Rendition> list, List<HlsSampleStreamWrapper> list2, List<int[]> list3, Map<String, DrmInitData> map) {
        List<HlsMultivariantPlaylist.Rendition> list4 = list;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        ArrayList arrayList3 = new ArrayList(list.size());
        HashSet hashSet = new HashSet();
        for (int i = 0; i < list.size(); i++) {
            String str = list4.get(i).name;
            if (!hashSet.add(str)) {
                List<HlsSampleStreamWrapper> list5 = list2;
                List<int[]> list6 = list3;
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList3.clear();
                boolean z = true;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (Util.areEqual(str, list4.get(i2).name)) {
                        HlsMultivariantPlaylist.Rendition rendition = list4.get(i2);
                        arrayList3.add(Integer.valueOf(i2));
                        arrayList.add(rendition.url);
                        arrayList2.add(rendition.format);
                        z &= Util.getCodecCountOfType(rendition.format.codecs, 1) == 1;
                    }
                }
                String str2 = "audio:" + str;
                HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(str2, 1, (Uri[]) arrayList.toArray((Uri[]) Util.castNonNullTypeArray(new Uri[0])), (Format[]) arrayList2.toArray(new Format[0]), (Format) null, Collections.emptyList(), map, j);
                list3.add(Ints.toArray(arrayList3));
                list2.add(buildSampleStreamWrapper);
                if (this.allowChunklessPreparation && z) {
                    buildSampleStreamWrapper.prepareWithMultivariantPlaylistInfo(new TrackGroup[]{new TrackGroup(str2, (Format[]) arrayList2.toArray(new Format[0]))}, 0, new int[0]);
                }
            }
        }
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(String str, int i, Uri[] uriArr, Format[] formatArr, Format format, List<Format> list, Map<String, DrmInitData> map, long j) {
        HlsChunkSource hlsChunkSource = new HlsChunkSource(this.extractorFactory, this.playlistTracker, uriArr, formatArr, this.dataSourceFactory, this.mediaTransferListener, this.timestampAdjusterProvider, this.timestampAdjusterInitializationTimeoutMs, list, this.playerId, this.cmcdConfiguration);
        return new HlsSampleStreamWrapper(str, i, this.sampleStreamWrapperCallback, hlsChunkSource, map, this.allocator, j, format, this.drmSessionManager, this.drmEventDispatcher, this.loadErrorHandlingPolicy, this.eventDispatcher, this.metadataType);
    }

    private static Map<String, DrmInitData> deriveOverridingDrmInitData(List<DrmInitData> list) {
        ArrayList arrayList = new ArrayList(list);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < arrayList.size()) {
            DrmInitData drmInitData = list.get(i);
            String str = drmInitData.schemeType;
            i++;
            int i2 = i;
            while (i2 < arrayList.size()) {
                DrmInitData drmInitData2 = (DrmInitData) arrayList.get(i2);
                if (TextUtils.equals(drmInitData2.schemeType, str)) {
                    drmInitData = drmInitData.merge(drmInitData2);
                    arrayList.remove(i2);
                } else {
                    i2++;
                }
            }
            hashMap.put(str, drmInitData);
        }
        return hashMap;
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return new Format.Builder().setId(format.id).setLabel(format.label).setLabels(format.labels).setContainerMimeType(format.containerMimeType).setSampleMimeType(MimeTypes.getMediaMimeType(codecsOfType)).setCodecs(codecsOfType).setMetadata(format.metadata).setAverageBitrate(format.averageBitrate).setPeakBitrate(format.peakBitrate).setWidth(format.width).setHeight(format.height).setFrameRate(format.frameRate).setSelectionFlags(format.selectionFlags).setRoleFlags(format.roleFlags).build();
    }

    private static Format deriveAudioFormat(Format format, Format format2, boolean z) {
        List<Label> list;
        String str;
        String str2;
        int i;
        int i2;
        int i3;
        Metadata metadata;
        String str3;
        List<Label> of = ImmutableList.of();
        int i4 = -1;
        if (format2 != null) {
            str3 = format2.codecs;
            metadata = format2.metadata;
            i3 = format2.channelCount;
            i2 = format2.selectionFlags;
            i = format2.roleFlags;
            str2 = format2.language;
            str = format2.label;
            list = format2.labels;
        } else {
            String codecsOfType = Util.getCodecsOfType(format.codecs, 1);
            metadata = format.metadata;
            if (z) {
                i3 = format.channelCount;
                i2 = format.selectionFlags;
                i = format.roleFlags;
                str2 = format.language;
                str = format.label;
                of = format.labels;
            } else {
                i2 = 0;
                str2 = null;
                i3 = -1;
                i = 0;
                str = null;
            }
            List<Label> list2 = of;
            str3 = codecsOfType;
            list = list2;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str3);
        int i5 = z ? format.averageBitrate : -1;
        if (z) {
            i4 = format.peakBitrate;
        }
        return new Format.Builder().setId(format.id).setLabel(str).setLabels(list).setContainerMimeType(format.containerMimeType).setSampleMimeType(mediaMimeType).setCodecs(str3).setMetadata(metadata).setAverageBitrate(i5).setPeakBitrate(i4).setChannelCount(i3).setSelectionFlags(i2).setRoleFlags(i).setLanguage(str2).build();
    }

    private class SampleStreamWrapperCallback implements HlsSampleStreamWrapper.Callback {
        private SampleStreamWrapperCallback() {
        }

        public void onPrepared() {
            if (HlsMediaPeriod.access$106(HlsMediaPeriod.this) <= 0) {
                int i = 0;
                for (HlsSampleStreamWrapper trackGroups : HlsMediaPeriod.this.sampleStreamWrappers) {
                    i += trackGroups.getTrackGroups().length;
                }
                TrackGroup[] trackGroupArr = new TrackGroup[i];
                int i2 = 0;
                for (HlsSampleStreamWrapper hlsSampleStreamWrapper : HlsMediaPeriod.this.sampleStreamWrappers) {
                    int i3 = hlsSampleStreamWrapper.getTrackGroups().length;
                    int i4 = 0;
                    while (i4 < i3) {
                        trackGroupArr[i2] = hlsSampleStreamWrapper.getTrackGroups().get(i4);
                        i4++;
                        i2++;
                    }
                }
                TrackGroupArray unused = HlsMediaPeriod.this.trackGroups = new TrackGroupArray(trackGroupArr);
                HlsMediaPeriod.this.mediaPeriodCallback.onPrepared(HlsMediaPeriod.this);
            }
        }

        public void onPlaylistRefreshRequired(Uri uri) {
            HlsMediaPeriod.this.playlistTracker.refreshPlaylist(uri);
        }

        public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
            HlsMediaPeriod.this.mediaPeriodCallback.onContinueLoadingRequested(HlsMediaPeriod.this);
        }
    }
}
