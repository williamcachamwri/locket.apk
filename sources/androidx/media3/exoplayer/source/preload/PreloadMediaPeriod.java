package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.Objects;

final class PreloadMediaPeriod implements MediaPeriod {
    /* access modifiers changed from: private */
    public MediaPeriod.Callback callback;
    public final MediaPeriod mediaPeriod;
    private PreloadTrackSelectionHolder preloadTrackSelectionHolder;
    private boolean prepareInternalCalled;
    public boolean prepared;

    public PreloadMediaPeriod(MediaPeriod mediaPeriod2) {
        this.mediaPeriod = mediaPeriod2;
    }

    public void preload(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        if (this.prepared) {
            callback2.onPrepared(this);
        }
        if (!this.prepareInternalCalled) {
            prepareInternal(j);
        }
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        if (this.prepared) {
            callback2.onPrepared(this);
        } else if (!this.prepareInternalCalled) {
            prepareInternal(j);
        }
    }

    private void prepareInternal(long j) {
        this.prepareInternalCalled = true;
        this.mediaPeriod.prepare(new MediaPeriod.Callback() {
            public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
                ((MediaPeriod.Callback) Assertions.checkNotNull(PreloadMediaPeriod.this.callback)).onContinueLoadingRequested(PreloadMediaPeriod.this);
            }

            public void onPrepared(MediaPeriod mediaPeriod) {
                PreloadMediaPeriod.this.prepared = true;
                ((MediaPeriod.Callback) Assertions.checkNotNull(PreloadMediaPeriod.this.callback)).onPrepared(PreloadMediaPeriod.this);
            }
        }, j);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        return selectTracksInternal(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j);
    }

    private long selectTracksInternal(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        PreloadTrackSelectionHolder preloadTrackSelectionHolder2 = this.preloadTrackSelectionHolder;
        if (preloadTrackSelectionHolder2 == null) {
            return this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j);
        }
        Assertions.checkState(sampleStreamArr2.length == preloadTrackSelectionHolder2.streams.length);
        if (j != this.preloadTrackSelectionHolder.trackSelectionPositionUs) {
            for (int i = 0; i < this.preloadTrackSelectionHolder.streams.length; i++) {
                if (this.preloadTrackSelectionHolder.streams[i] != null) {
                    sampleStreamArr2[i] = this.preloadTrackSelectionHolder.streams[i];
                    zArr[i] = false;
                }
            }
            this.preloadTrackSelectionHolder = null;
            return this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr, zArr2, j);
        }
        PreloadTrackSelectionHolder preloadTrackSelectionHolder3 = (PreloadTrackSelectionHolder) Assertions.checkNotNull(this.preloadTrackSelectionHolder);
        long j2 = preloadTrackSelectionHolder3.trackSelectionPositionUs;
        boolean[] zArr3 = preloadTrackSelectionHolder3.streamResetFlags;
        if (maybeUpdatePreloadTrackSelectionHolderForReselection(exoTrackSelectionArr, preloadTrackSelectionHolder3)) {
            boolean[] zArr4 = new boolean[zArr3.length];
            long selectTracks = this.mediaPeriod.selectTracks(preloadTrackSelectionHolder3.selections, preloadTrackSelectionHolder3.mayRetainStreamFlags, preloadTrackSelectionHolder3.streams, zArr4, preloadTrackSelectionHolder3.trackSelectionPositionUs);
            for (int i2 = 0; i2 < preloadTrackSelectionHolder3.mayRetainStreamFlags.length; i2++) {
                if (preloadTrackSelectionHolder3.mayRetainStreamFlags[i2]) {
                    zArr4[i2] = true;
                }
            }
            zArr3 = zArr4;
            j2 = selectTracks;
        }
        System.arraycopy(preloadTrackSelectionHolder3.streams, 0, sampleStreamArr2, 0, preloadTrackSelectionHolder3.streams.length);
        System.arraycopy(zArr3, 0, zArr2, 0, zArr3.length);
        this.preloadTrackSelectionHolder = null;
        return j2;
    }

    private static boolean maybeUpdatePreloadTrackSelectionHolderForReselection(ExoTrackSelection[] exoTrackSelectionArr, PreloadTrackSelectionHolder preloadTrackSelectionHolder2) {
        ExoTrackSelection[] exoTrackSelectionArr2 = ((PreloadTrackSelectionHolder) Assertions.checkNotNull(preloadTrackSelectionHolder2)).selections;
        boolean z = false;
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            ExoTrackSelection exoTrackSelection = exoTrackSelectionArr[i];
            ExoTrackSelection exoTrackSelection2 = exoTrackSelectionArr2[i];
            if (exoTrackSelection != null || exoTrackSelection2 != null) {
                preloadTrackSelectionHolder2.mayRetainStreamFlags[i] = false;
                if (exoTrackSelection == null) {
                    preloadTrackSelectionHolder2.selections[i] = null;
                } else if (exoTrackSelection2 == null) {
                    preloadTrackSelectionHolder2.selections[i] = exoTrackSelection;
                } else if (!isSameAdaptionSet(exoTrackSelection, exoTrackSelection2)) {
                    preloadTrackSelectionHolder2.selections[i] = exoTrackSelection;
                } else if (exoTrackSelection.getTrackGroup().type == 2 || exoTrackSelection.getTrackGroup().type == 1 || exoTrackSelection.getSelectedIndexInTrackGroup() == exoTrackSelection2.getSelectedIndexInTrackGroup()) {
                    preloadTrackSelectionHolder2.mayRetainStreamFlags[i] = true;
                } else {
                    preloadTrackSelectionHolder2.selections[i] = exoTrackSelection;
                }
                z = true;
            }
        }
        return z;
    }

    private static boolean isSameAdaptionSet(ExoTrackSelection exoTrackSelection, ExoTrackSelection exoTrackSelection2) {
        if (!Objects.equals(exoTrackSelection.getTrackGroup(), exoTrackSelection2.getTrackGroup()) || exoTrackSelection.length() != exoTrackSelection2.length()) {
            return false;
        }
        for (int i = 0; i < exoTrackSelection.length(); i++) {
            if (exoTrackSelection.getIndexInTrackGroup(i) != exoTrackSelection2.getIndexInTrackGroup(i)) {
                return false;
            }
        }
        return true;
    }

    public long selectTracksForPreloading(ExoTrackSelection[] exoTrackSelectionArr, long j) {
        boolean[] zArr = new boolean[exoTrackSelectionArr.length];
        ExoTrackSelection[] exoTrackSelectionArr2 = exoTrackSelectionArr;
        boolean[] zArr2 = new boolean[exoTrackSelectionArr.length];
        SampleStream[] sampleStreamArr = new SampleStream[exoTrackSelectionArr.length];
        boolean[] zArr3 = zArr;
        long selectTracksInternal = selectTracksInternal(exoTrackSelectionArr2, zArr2, sampleStreamArr, zArr3, j);
        this.preloadTrackSelectionHolder = new PreloadTrackSelectionHolder(exoTrackSelectionArr2, zArr2, sampleStreamArr, zArr3, selectTracksInternal);
        return selectTracksInternal;
    }

    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j, z);
    }

    public long readDiscontinuity() {
        return this.mediaPeriod.readDiscontinuity();
    }

    public long seekToUs(long j) {
        return this.mediaPeriod.seekToUs(j);
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j, seekParameters);
    }

    public long getBufferedPositionUs() {
        return this.mediaPeriod.getBufferedPositionUs();
    }

    public long getNextLoadPositionUs() {
        return this.mediaPeriod.getNextLoadPositionUs();
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo);
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j);
    }

    public void maybeThrowStreamError() throws IOException {
        Assertions.checkState(this.prepared);
        PreloadTrackSelectionHolder preloadTrackSelectionHolder2 = this.preloadTrackSelectionHolder;
        if (preloadTrackSelectionHolder2 != null) {
            for (SampleStream sampleStream : preloadTrackSelectionHolder2.streams) {
                if (sampleStream != null) {
                    sampleStream.maybeThrowError();
                }
            }
        }
    }

    private static class PreloadTrackSelectionHolder {
        public final boolean[] mayRetainStreamFlags;
        public final ExoTrackSelection[] selections;
        public final boolean[] streamResetFlags;
        public final SampleStream[] streams;
        public final long trackSelectionPositionUs;

        public PreloadTrackSelectionHolder(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
            this.selections = exoTrackSelectionArr;
            this.mayRetainStreamFlags = zArr;
            this.streams = sampleStreamArr;
            this.streamResetFlags = zArr2;
            this.trackSelectionPositionUs = j;
        }
    }
}
