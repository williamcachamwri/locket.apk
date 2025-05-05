package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import java.io.IOException;
import java.util.List;

final class TimeOffsetMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private final MediaPeriod mediaPeriod;
    private final long timeOffsetUs;

    public TimeOffsetMediaPeriod(MediaPeriod mediaPeriod2, long j) {
        this.mediaPeriod = mediaPeriod2;
        this.timeOffsetUs = j;
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, j - this.timeOffsetUs);
    }

    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    public List<StreamKey> getStreamKeys(List<ExoTrackSelection> list) {
        return this.mediaPeriod.getStreamKeys(list);
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SampleStream[] sampleStreamArr2 = sampleStreamArr;
        SampleStream[] sampleStreamArr3 = new SampleStream[sampleStreamArr2.length];
        int i = 0;
        while (true) {
            SampleStream sampleStream = null;
            if (i >= sampleStreamArr2.length) {
                break;
            }
            TimeOffsetSampleStream timeOffsetSampleStream = (TimeOffsetSampleStream) sampleStreamArr2[i];
            if (timeOffsetSampleStream != null) {
                sampleStream = timeOffsetSampleStream.getChildStream();
            }
            sampleStreamArr3[i] = sampleStream;
            i++;
        }
        long selectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, j - this.timeOffsetUs);
        for (int i2 = 0; i2 < sampleStreamArr2.length; i2++) {
            SampleStream sampleStream2 = sampleStreamArr3[i2];
            if (sampleStream2 == null) {
                sampleStreamArr2[i2] = null;
            } else {
                SampleStream sampleStream3 = sampleStreamArr2[i2];
                if (sampleStream3 == null || ((TimeOffsetSampleStream) sampleStream3).getChildStream() != sampleStream2) {
                    sampleStreamArr2[i2] = new TimeOffsetSampleStream(sampleStream2, this.timeOffsetUs);
                }
            }
        }
        return selectTracks + this.timeOffsetUs;
    }

    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(j - this.timeOffsetUs, z);
    }

    public long readDiscontinuity() {
        long readDiscontinuity = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        return this.timeOffsetUs + readDiscontinuity;
    }

    public long seekToUs(long j) {
        return this.mediaPeriod.seekToUs(j - this.timeOffsetUs) + this.timeOffsetUs;
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.mediaPeriod.getAdjustedSeekPositionUs(j - this.timeOffsetUs, seekParameters) + this.timeOffsetUs;
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.timeOffsetUs + bufferedPositionUs;
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.timeOffsetUs + nextLoadPositionUs;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo.buildUpon().setPlaybackPositionUs(loadingInfo.playbackPositionUs - this.timeOffsetUs).build());
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(j - this.timeOffsetUs);
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    private static final class TimeOffsetSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final long timeOffsetUs;

        public TimeOffsetSampleStream(SampleStream sampleStream2, long j) {
            this.sampleStream = sampleStream2;
            this.timeOffsetUs = j;
        }

        public SampleStream getChildStream() {
            return this.sampleStream;
        }

        public boolean isReady() {
            return this.sampleStream.isReady();
        }

        public void maybeThrowError() throws IOException {
            this.sampleStream.maybeThrowError();
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            int readData = this.sampleStream.readData(formatHolder, decoderInputBuffer, i);
            if (readData == -4) {
                decoderInputBuffer.timeUs += this.timeOffsetUs;
            }
            return readData;
        }

        public int skipData(long j) {
            return this.sampleStream.skipData(j - this.timeOffsetUs);
        }
    }
}
