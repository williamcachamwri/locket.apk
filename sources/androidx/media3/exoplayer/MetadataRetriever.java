package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import androidx.media3.extractor.DefaultExtractorsFactory;
import androidx.media3.extractor.ExtractorsFactory;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

public final class MetadataRetriever {
    public static final int DEFAULT_MAXIMUM_PARALLEL_RETRIEVALS = 5;

    private MetadataRetriever() {
    }

    public static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem) {
        return retrieveMetadata(context, mediaItem, Clock.DEFAULT);
    }

    public static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem) {
        return retrieveMetadata(factory, mediaItem, Clock.DEFAULT);
    }

    static ListenableFuture<TrackGroupArray> retrieveMetadata(Context context, MediaItem mediaItem, Clock clock) {
        return retrieveMetadata((MediaSource.Factory) new DefaultMediaSourceFactory(context, (ExtractorsFactory) new DefaultExtractorsFactory().setMp4ExtractorFlags(6)), mediaItem, clock);
    }

    private static ListenableFuture<TrackGroupArray> retrieveMetadata(MediaSource.Factory factory, MediaItem mediaItem, Clock clock) {
        return new MetadataRetrieverInternal(factory, mediaItem, clock).retrieveMetadata();
    }

    public static void setMaximumParallelRetrievals(int i) {
        boolean z = true;
        if (i < 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        SharedWorkerThread.MAX_PARALLEL_RETRIEVALS.set(i);
    }

    private static final class MetadataRetrieverInternal {
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        /* access modifiers changed from: private */
        public static final SharedWorkerThread SHARED_WORKER_THREAD = new SharedWorkerThread();
        private final MediaItem mediaItem;
        /* access modifiers changed from: private */
        public final MediaSource.Factory mediaSourceFactory;
        /* access modifiers changed from: private */
        public final HandlerWrapper mediaSourceHandler;
        /* access modifiers changed from: private */
        public final SettableFuture<TrackGroupArray> trackGroupsFuture = SettableFuture.create();

        public MetadataRetrieverInternal(MediaSource.Factory factory, MediaItem mediaItem2, Clock clock) {
            this.mediaSourceFactory = factory;
            this.mediaItem = mediaItem2;
            this.mediaSourceHandler = clock.createHandler(SHARED_WORKER_THREAD.addWorker(), new MediaSourceHandlerCallback());
        }

        public ListenableFuture<TrackGroupArray> retrieveMetadata() {
            SHARED_WORKER_THREAD.startRetrieval(this);
            return this.trackGroupsFuture;
        }

        public void start() {
            this.mediaSourceHandler.obtainMessage(1, this.mediaItem).sendToTarget();
        }

        private final class MediaSourceHandlerCallback implements Handler.Callback {
            private static final int ERROR_POLL_INTERVAL_MS = 100;
            /* access modifiers changed from: private */
            public MediaPeriod mediaPeriod;
            private MediaSource mediaSource;
            private final MediaSourceCaller mediaSourceCaller = new MediaSourceCaller();

            public MediaSourceHandlerCallback() {
            }

            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    MediaSource createMediaSource = MetadataRetrieverInternal.this.mediaSourceFactory.createMediaSource((MediaItem) message.obj);
                    this.mediaSource = createMediaSource;
                    createMediaSource.prepareSource(this.mediaSourceCaller, (TransferListener) null, PlayerId.UNSET);
                    MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessage(2);
                    return true;
                } else if (i == 2) {
                    try {
                        MediaPeriod mediaPeriod2 = this.mediaPeriod;
                        if (mediaPeriod2 == null) {
                            ((MediaSource) Assertions.checkNotNull(this.mediaSource)).maybeThrowSourceInfoRefreshError();
                        } else {
                            mediaPeriod2.maybeThrowPrepareError();
                        }
                        MetadataRetrieverInternal.this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100);
                    } catch (Exception e) {
                        MetadataRetrieverInternal.this.trackGroupsFuture.setException(e);
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(4).sendToTarget();
                    }
                    return true;
                } else if (i == 3) {
                    ((MediaPeriod) Assertions.checkNotNull(this.mediaPeriod)).continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0).build());
                    return true;
                } else if (i != 4) {
                    return false;
                } else {
                    if (this.mediaPeriod != null) {
                        ((MediaSource) Assertions.checkNotNull(this.mediaSource)).releasePeriod(this.mediaPeriod);
                    }
                    ((MediaSource) Assertions.checkNotNull(this.mediaSource)).releaseSource(this.mediaSourceCaller);
                    MetadataRetrieverInternal.this.mediaSourceHandler.removeCallbacksAndMessages((Object) null);
                    MetadataRetrieverInternal.SHARED_WORKER_THREAD.removeWorker();
                    return true;
                }
            }

            private final class MediaSourceCaller implements MediaSource.MediaSourceCaller {
                private final Allocator allocator = new DefaultAllocator(true, 65536);
                private final MediaPeriodCallback mediaPeriodCallback = new MediaPeriodCallback();
                private boolean mediaPeriodCreated;

                public MediaSourceCaller() {
                }

                public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
                    if (!this.mediaPeriodCreated) {
                        this.mediaPeriodCreated = true;
                        MediaPeriod unused = MediaSourceHandlerCallback.this.mediaPeriod = mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0)), this.allocator, 0);
                        MediaSourceHandlerCallback.this.mediaPeriod.prepare(this.mediaPeriodCallback, 0);
                    }
                }

                private final class MediaPeriodCallback implements MediaPeriod.Callback {
                    private MediaPeriodCallback() {
                    }

                    public void onPrepared(MediaPeriod mediaPeriod) {
                        MetadataRetrieverInternal.this.trackGroupsFuture.set(mediaPeriod.getTrackGroups());
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(4).sendToTarget();
                    }

                    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
                        MetadataRetrieverInternal.this.mediaSourceHandler.obtainMessage(3).sendToTarget();
                    }
                }
            }
        }
    }

    private static final class SharedWorkerThread {
        public static final AtomicInteger MAX_PARALLEL_RETRIEVALS = new AtomicInteger(5);
        private HandlerThread mediaSourceThread;
        private final Deque<MetadataRetrieverInternal> pendingRetrievals = new ArrayDeque();
        private int referenceCount;

        public synchronized Looper addWorker() {
            if (this.mediaSourceThread == null) {
                Assertions.checkState(this.referenceCount == 0);
                HandlerThread handlerThread = new HandlerThread("ExoPlayer:MetadataRetriever");
                this.mediaSourceThread = handlerThread;
                handlerThread.start();
            }
            this.referenceCount++;
            return this.mediaSourceThread.getLooper();
        }

        public synchronized void startRetrieval(MetadataRetrieverInternal metadataRetrieverInternal) {
            this.pendingRetrievals.addLast(metadataRetrieverInternal);
            maybeStartNewRetrieval();
        }

        public synchronized void removeWorker() {
            int i = this.referenceCount - 1;
            this.referenceCount = i;
            if (i == 0) {
                ((HandlerThread) Assertions.checkNotNull(this.mediaSourceThread)).quit();
                this.mediaSourceThread = null;
            } else {
                maybeStartNewRetrieval();
            }
        }

        private void maybeStartNewRetrieval() {
            if (!this.pendingRetrievals.isEmpty() && this.referenceCount - this.pendingRetrievals.size() < MAX_PARALLEL_RETRIEVALS.get()) {
                this.pendingRetrievals.removeFirst().start();
            }
        }
    }
}
