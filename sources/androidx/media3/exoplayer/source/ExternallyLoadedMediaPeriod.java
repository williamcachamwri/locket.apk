package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.source.ExternalLoader;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

final class ExternallyLoadedMediaPeriod implements MediaPeriod {
    private final ExternalLoader externalLoader;
    /* access modifiers changed from: private */
    public final AtomicBoolean loadingFinished = new AtomicBoolean();
    private ListenableFuture<?> loadingFuture;
    /* access modifiers changed from: private */
    public final AtomicReference<Throwable> loadingThrowable = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final byte[] sampleData;
    /* access modifiers changed from: private */
    public final TrackGroupArray tracks;
    private final Uri uri;

    public void discardBuffer(long j, boolean z) {
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }

    public void maybeThrowPrepareError() {
    }

    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public void reevaluateBuffer(long j) {
    }

    public long seekToUs(long j) {
        return j;
    }

    public ExternallyLoadedMediaPeriod(Uri uri2, String str, ExternalLoader externalLoader2) {
        this.uri = uri2;
        Format build = new Format.Builder().setSampleMimeType(str).build();
        this.externalLoader = externalLoader2;
        this.tracks = new TrackGroupArray(new TrackGroup(build));
        this.sampleData = uri2.toString().getBytes(StandardCharsets.UTF_8);
    }

    public void prepare(MediaPeriod.Callback callback, long j) {
        callback.onPrepared(this);
        ListenableFuture<?> load = this.externalLoader.load(new ExternalLoader.LoadRequest(this.uri));
        this.loadingFuture = load;
        Futures.addCallback(load, new FutureCallback<Object>() {
            public void onSuccess(Object obj) {
                ExternallyLoadedMediaPeriod.this.loadingFinished.set(true);
            }

            public void onFailure(Throwable th) {
                ExternallyLoadedMediaPeriod.this.loadingThrowable.set(th);
            }
        }, MoreExecutors.directExecutor());
    }

    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    public long selectTracks(ExoTrackSelection[] exoTrackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        for (int i = 0; i < exoTrackSelectionArr.length; i++) {
            if (sampleStreamArr[i] != null && (exoTrackSelectionArr[i] == null || !zArr[i])) {
                sampleStreamArr[i] = null;
            }
            if (sampleStreamArr[i] == null && exoTrackSelectionArr[i] != null) {
                sampleStreamArr[i] = new SampleStreamImpl();
                zArr2[i] = true;
            }
        }
        return j;
    }

    public long getBufferedPositionUs() {
        return this.loadingFinished.get() ? Long.MIN_VALUE : 0;
    }

    public long getNextLoadPositionUs() {
        return this.loadingFinished.get() ? Long.MIN_VALUE : 0;
    }

    public boolean continueLoading(LoadingInfo loadingInfo) {
        return !this.loadingFinished.get();
    }

    public boolean isLoading() {
        return !this.loadingFinished.get();
    }

    public void releasePeriod() {
        ListenableFuture<?> listenableFuture = this.loadingFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
        }
    }

    private final class SampleStreamImpl implements SampleStream {
        private static final int STREAM_STATE_END_OF_STREAM = 2;
        private static final int STREAM_STATE_SEND_FORMAT = 0;
        private static final int STREAM_STATE_SEND_SAMPLE = 1;
        private int streamState = 0;

        public int skipData(long j) {
            return 0;
        }

        public SampleStreamImpl() {
        }

        public boolean isReady() {
            return ExternallyLoadedMediaPeriod.this.loadingFinished.get();
        }

        public void maybeThrowError() throws IOException {
            Throwable th = (Throwable) ExternallyLoadedMediaPeriod.this.loadingThrowable.get();
            if (th != null) {
                throw new IOException(th);
            }
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            int i2 = this.streamState;
            if (i2 == 2) {
                decoderInputBuffer.addFlag(4);
                return -4;
            } else if ((i & 2) != 0 || i2 == 0) {
                formatHolder.format = ExternallyLoadedMediaPeriod.this.tracks.get(0).getFormat(0);
                this.streamState = 1;
                return -5;
            } else if (!ExternallyLoadedMediaPeriod.this.loadingFinished.get()) {
                return -3;
            } else {
                int length = ExternallyLoadedMediaPeriod.this.sampleData.length;
                decoderInputBuffer.addFlag(1);
                decoderInputBuffer.timeUs = 0;
                if ((i & 4) == 0) {
                    decoderInputBuffer.ensureSpaceForWrite(length);
                    decoderInputBuffer.data.put(ExternallyLoadedMediaPeriod.this.sampleData, 0, length);
                }
                if ((i & 1) == 0) {
                    this.streamState = 2;
                }
                return -4;
            }
        }
    }
}
