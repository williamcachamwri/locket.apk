package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.StreamKey;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.primitives.Floats;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

final class SpeedProviderMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    public final MediaPeriod mediaPeriod;
    private final SpeedProviderMapper speedProviderMapper;

    public SpeedProviderMediaPeriod(MediaPeriod mediaPeriod2, SpeedProvider speedProvider) {
        this.mediaPeriod = mediaPeriod2;
        this.speedProviderMapper = new SpeedProviderMapper(speedProvider);
    }

    public MediaPeriod getWrappedMediaPeriod() {
        return this.mediaPeriod;
    }

    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.mediaPeriod.prepare(this, this.speedProviderMapper.getOriginalTimeUs(j));
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
            SpeedProviderMapperSampleStream speedProviderMapperSampleStream = (SpeedProviderMapperSampleStream) sampleStreamArr2[i];
            if (speedProviderMapperSampleStream != null) {
                sampleStream = speedProviderMapperSampleStream.getChildStream();
            }
            sampleStreamArr3[i] = sampleStream;
            i++;
        }
        long selectTracks = this.mediaPeriod.selectTracks(exoTrackSelectionArr, zArr, sampleStreamArr3, zArr2, this.speedProviderMapper.getOriginalTimeUs(j));
        for (int i2 = 0; i2 < sampleStreamArr2.length; i2++) {
            SampleStream sampleStream2 = sampleStreamArr3[i2];
            if (sampleStream2 == null) {
                sampleStreamArr2[i2] = null;
            } else {
                SampleStream sampleStream3 = sampleStreamArr2[i2];
                if (sampleStream3 == null || ((SpeedProviderMapperSampleStream) sampleStream3).getChildStream() != sampleStream2) {
                    sampleStreamArr2[i2] = new SpeedProviderMapperSampleStream(sampleStream2, this.speedProviderMapper);
                }
            }
        }
        return this.speedProviderMapper.getAdjustedTimeUs(selectTracks);
    }

    public void discardBuffer(long j, boolean z) {
        this.mediaPeriod.discardBuffer(this.speedProviderMapper.getOriginalTimeUs(j), z);
    }

    public long readDiscontinuity() {
        long readDiscontinuity = this.mediaPeriod.readDiscontinuity();
        if (readDiscontinuity == C.TIME_UNSET) {
            return C.TIME_UNSET;
        }
        return this.speedProviderMapper.getAdjustedTimeUs(readDiscontinuity);
    }

    public long seekToUs(long j) {
        SpeedProviderMapper speedProviderMapper2 = this.speedProviderMapper;
        return speedProviderMapper2.getAdjustedTimeUs(this.mediaPeriod.seekToUs(speedProviderMapper2.getOriginalTimeUs(j)));
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        SpeedProviderMapper speedProviderMapper2 = this.speedProviderMapper;
        return speedProviderMapper2.getAdjustedTimeUs(this.mediaPeriod.getAdjustedSeekPositionUs(speedProviderMapper2.getOriginalTimeUs(j), seekParameters));
    }

    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.speedProviderMapper.getAdjustedTimeUs(bufferedPositionUs);
    }

    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.speedProviderMapper.getAdjustedTimeUs(nextLoadPositionUs);
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return this.mediaPeriod.continueLoading(loadingInfo.buildUpon().setPlaybackPositionUs(this.speedProviderMapper.getOriginalTimeUs(loadingInfo.playbackPositionUs)).build());
    }

    public boolean isLoading() {
        return this.mediaPeriod.isLoading();
    }

    public void reevaluateBuffer(long j) {
        this.mediaPeriod.reevaluateBuffer(this.speedProviderMapper.getOriginalTimeUs(j));
    }

    public void onPrepared(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod2) {
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
    }

    private static final class SpeedProviderMapper {
        private final long[] inputSegmentStartTimesUs;
        private final long[] outputSegmentStartTimesUs;
        private final float[] speeds;

        public SpeedProviderMapper(SpeedProvider speedProvider) {
            SpeedProvider speedProvider2 = speedProvider;
            LongArray longArray = new LongArray();
            LongArray longArray2 = new LongArray();
            ArrayList arrayList = new ArrayList();
            float speed = speedProvider2.getSpeed(0);
            longArray.add(0);
            longArray2.add(0);
            arrayList.add(Float.valueOf(speed));
            float f = speed;
            long nextSpeedChangeTimeUs = speedProvider2.getNextSpeedChangeTimeUs(0);
            long j = 0;
            long j2 = 0;
            while (nextSpeedChangeTimeUs != C.TIME_UNSET) {
                j += (long) (((float) (nextSpeedChangeTimeUs - j2)) / f);
                f = speedProvider2.getSpeed(nextSpeedChangeTimeUs);
                longArray.add(j);
                longArray2.add(nextSpeedChangeTimeUs);
                arrayList.add(Float.valueOf(f));
                long j3 = nextSpeedChangeTimeUs;
                nextSpeedChangeTimeUs = speedProvider2.getNextSpeedChangeTimeUs(nextSpeedChangeTimeUs);
                j2 = j3;
            }
            this.outputSegmentStartTimesUs = longArray.toArray();
            this.inputSegmentStartTimesUs = longArray2.toArray();
            this.speeds = Floats.toArray(arrayList);
        }

        public long getAdjustedTimeUs(long j) {
            int binarySearchFloor = Util.binarySearchFloor(this.inputSegmentStartTimesUs, j, true, true);
            return (long) (((float) this.outputSegmentStartTimesUs[binarySearchFloor]) + (((float) (j - this.inputSegmentStartTimesUs[binarySearchFloor])) / this.speeds[binarySearchFloor]));
        }

        public long getOriginalTimeUs(long j) {
            int binarySearchFloor = Util.binarySearchFloor(this.outputSegmentStartTimesUs, j, true, true);
            return (long) (((float) this.inputSegmentStartTimesUs[binarySearchFloor]) + (((float) (j - this.outputSegmentStartTimesUs[binarySearchFloor])) * this.speeds[binarySearchFloor]));
        }
    }

    private static final class SpeedProviderMapperSampleStream implements SampleStream {
        private final SampleStream sampleStream;
        private final SpeedProviderMapper speedProviderMapper;

        public SpeedProviderMapperSampleStream(SampleStream sampleStream2, SpeedProviderMapper speedProviderMapper2) {
            this.sampleStream = sampleStream2;
            this.speedProviderMapper = speedProviderMapper2;
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
                decoderInputBuffer.timeUs = this.speedProviderMapper.getAdjustedTimeUs(decoderInputBuffer.timeUs);
            }
            return readData;
        }

        public int skipData(long j) {
            return this.sampleStream.skipData(this.speedProviderMapper.getOriginalTimeUs(j));
        }
    }
}
