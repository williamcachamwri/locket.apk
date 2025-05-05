package androidx.media3.exoplayer.source.preload;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.FlagSet;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.TargetPreloadStatusControl;
import com.google.common.base.Supplier;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class BasePreloadManager<T> {
    private final Handler applicationHandler;
    private final ListenerSet<Listener> listeners;
    private final Object lock = new Object();
    private final Map<MediaItem, BasePreloadManager<T>.MediaSourceHolder> mediaItemMediaSourceHolderMap;
    private final MediaSource.Factory mediaSourceFactory;
    protected final Comparator<T> rankingDataComparator;
    private final PriorityQueue<BasePreloadManager<T>.MediaSourceHolder> sourceHolderPriorityQueue;
    private final TargetPreloadStatusControl<T> targetPreloadStatusControl;
    private TargetPreloadStatusControl.PreloadStatus targetPreloadStatusOfCurrentPreloadingSource;

    public interface Listener {
        void onCompleted(MediaItem mediaItem);

        void onError(PreloadException preloadException);
    }

    static /* synthetic */ void lambda$new$0(Listener listener, FlagSet flagSet) {
    }

    /* access modifiers changed from: protected */
    public abstract void clearSourceInternal(MediaSource mediaSource);

    /* access modifiers changed from: protected */
    public MediaSource createMediaSourceForPreloading(MediaSource mediaSource) {
        return mediaSource;
    }

    /* access modifiers changed from: protected */
    public abstract void preloadSourceInternal(MediaSource mediaSource, long j);

    /* access modifiers changed from: protected */
    public void releaseInternal() {
    }

    /* access modifiers changed from: protected */
    public abstract void releaseSourceInternal(MediaSource mediaSource);

    /* access modifiers changed from: protected */
    public boolean shouldStartPreloadingNextSource() {
        return true;
    }

    protected static abstract class BuilderBase<T> {
        protected Supplier<MediaSource.Factory> mediaSourceFactorySupplier;
        protected final Comparator<T> rankingDataComparator;
        protected final TargetPreloadStatusControl<T> targetPreloadStatusControl;

        public abstract BasePreloadManager<T> build();

        public BuilderBase(Comparator<T> comparator, TargetPreloadStatusControl<T> targetPreloadStatusControl2, Supplier<MediaSource.Factory> supplier) {
            this.rankingDataComparator = comparator;
            this.targetPreloadStatusControl = targetPreloadStatusControl2;
            this.mediaSourceFactorySupplier = supplier;
        }
    }

    protected BasePreloadManager(Comparator<T> comparator, TargetPreloadStatusControl<T> targetPreloadStatusControl2, MediaSource.Factory factory) {
        Handler createHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper();
        this.applicationHandler = createHandlerForCurrentOrMainLooper;
        this.rankingDataComparator = comparator;
        this.targetPreloadStatusControl = targetPreloadStatusControl2;
        this.mediaSourceFactory = factory;
        this.listeners = new ListenerSet<>(createHandlerForCurrentOrMainLooper.getLooper(), Clock.DEFAULT, new BasePreloadManager$$ExternalSyntheticLambda0());
        this.mediaItemMediaSourceHolderMap = new HashMap();
        this.sourceHolderPriorityQueue = new PriorityQueue<>();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        verifyApplicationThread();
        this.listeners.remove(listener);
    }

    public void clearListeners() {
        verifyApplicationThread();
        this.listeners.clear();
    }

    public final int getSourceCount() {
        return this.mediaItemMediaSourceHolderMap.size();
    }

    public final void add(MediaItem mediaItem, T t) {
        add(this.mediaSourceFactory.createMediaSource(mediaItem), t);
    }

    public final void add(MediaSource mediaSource, T t) {
        MediaSource createMediaSourceForPreloading = createMediaSourceForPreloading(mediaSource);
        this.mediaItemMediaSourceHolderMap.put(createMediaSourceForPreloading.getMediaItem(), new MediaSourceHolder(this, createMediaSourceForPreloading, t));
    }

    public final void invalidate() {
        synchronized (this.lock) {
            this.sourceHolderPriorityQueue.clear();
            this.sourceHolderPriorityQueue.addAll(this.mediaItemMediaSourceHolderMap.values());
            while (!this.sourceHolderPriorityQueue.isEmpty() && !maybeStartPreloadNextSource()) {
                this.sourceHolderPriorityQueue.poll();
            }
        }
    }

    public final MediaSource getMediaSource(MediaItem mediaItem) {
        if (!this.mediaItemMediaSourceHolderMap.containsKey(mediaItem)) {
            return null;
        }
        return this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource;
    }

    public final boolean remove(MediaItem mediaItem) {
        if (!this.mediaItemMediaSourceHolderMap.containsKey(mediaItem)) {
            return false;
        }
        MediaSource mediaSource = this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource;
        this.mediaItemMediaSourceHolderMap.remove(mediaItem);
        releaseSourceInternal(mediaSource);
        return true;
    }

    public final boolean remove(MediaSource mediaSource) {
        MediaItem mediaItem = mediaSource.getMediaItem();
        if (!this.mediaItemMediaSourceHolderMap.containsKey(mediaItem) || mediaSource != this.mediaItemMediaSourceHolderMap.get(mediaItem).mediaSource) {
            return false;
        }
        this.mediaItemMediaSourceHolderMap.remove(mediaItem);
        releaseSourceInternal(mediaSource);
        return true;
    }

    public final void reset() {
        for (BasePreloadManager<T>.MediaSourceHolder mediaSourceHolder : this.mediaItemMediaSourceHolderMap.values()) {
            releaseSourceInternal(mediaSourceHolder.mediaSource);
        }
        this.mediaItemMediaSourceHolderMap.clear();
        synchronized (this.lock) {
            this.sourceHolderPriorityQueue.clear();
            this.targetPreloadStatusOfCurrentPreloadingSource = null;
        }
    }

    public final void release() {
        reset();
        releaseInternal();
        clearListeners();
    }

    /* access modifiers changed from: protected */
    public final void onPreloadCompleted(MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                this.applicationHandler.post(new BasePreloadManager$$ExternalSyntheticLambda3(this, mediaSource));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPreloadCompleted$2$androidx-media3-exoplayer-source-preload-BasePreloadManager  reason: not valid java name */
    public /* synthetic */ void m872lambda$onPreloadCompleted$2$androidxmedia3exoplayersourcepreloadBasePreloadManager(MediaSource mediaSource) {
        this.listeners.sendEvent(-1, new BasePreloadManager$$ExternalSyntheticLambda4(mediaSource));
        m874lambda$onPreloadSkipped$5$androidxmedia3exoplayersourcepreloadBasePreloadManager(mediaSource);
    }

    /* access modifiers changed from: protected */
    public final void onPreloadError(PreloadException preloadException, MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                this.applicationHandler.post(new BasePreloadManager$$ExternalSyntheticLambda5(this, preloadException, mediaSource));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPreloadError$4$androidx-media3-exoplayer-source-preload-BasePreloadManager  reason: not valid java name */
    public /* synthetic */ void m873lambda$onPreloadError$4$androidxmedia3exoplayersourcepreloadBasePreloadManager(PreloadException preloadException, MediaSource mediaSource) {
        this.listeners.sendEvent(-1, new BasePreloadManager$$ExternalSyntheticLambda1(preloadException));
        m874lambda$onPreloadSkipped$5$androidxmedia3exoplayersourcepreloadBasePreloadManager(mediaSource);
    }

    /* access modifiers changed from: protected */
    public final void onPreloadSkipped(MediaSource mediaSource) {
        synchronized (this.lock) {
            if (isPreloading(mediaSource)) {
                this.applicationHandler.post(new BasePreloadManager$$ExternalSyntheticLambda2(this, mediaSource));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        return;
     */
    /* renamed from: maybeAdvanceToNextSource */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m874lambda$onPreloadSkipped$5$androidxmedia3exoplayersourcepreloadBasePreloadManager(androidx.media3.exoplayer.source.MediaSource r2) {
        /*
            r1 = this;
            java.lang.Object r0 = r1.lock
            monitor-enter(r0)
            boolean r2 = r1.isPreloading(r2)     // Catch:{ all -> 0x0020 }
            if (r2 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return
        L_0x000b:
            java.util.PriorityQueue<androidx.media3.exoplayer.source.preload.BasePreloadManager<T>$MediaSourceHolder> r2 = r1.sourceHolderPriorityQueue     // Catch:{ all -> 0x0020 }
            r2.poll()     // Catch:{ all -> 0x0020 }
            java.util.PriorityQueue<androidx.media3.exoplayer.source.preload.BasePreloadManager<T>$MediaSourceHolder> r2 = r1.sourceHolderPriorityQueue     // Catch:{ all -> 0x0020 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0020 }
            if (r2 != 0) goto L_0x001e
            boolean r2 = r1.maybeStartPreloadNextSource()     // Catch:{ all -> 0x0020 }
            if (r2 == 0) goto L_0x000b
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.preload.BasePreloadManager.m874lambda$onPreloadSkipped$5$androidxmedia3exoplayersourcepreloadBasePreloadManager(androidx.media3.exoplayer.source.MediaSource):void");
    }

    private boolean isPreloading(MediaSource mediaSource) {
        return !this.sourceHolderPriorityQueue.isEmpty() && ((MediaSourceHolder) Assertions.checkNotNull(this.sourceHolderPriorityQueue.peek())).mediaSource == mediaSource;
    }

    /* access modifiers changed from: protected */
    public final TargetPreloadStatusControl.PreloadStatus getTargetPreloadStatus(MediaSource mediaSource) {
        synchronized (this.lock) {
            if (!isPreloading(mediaSource)) {
                return null;
            }
            TargetPreloadStatusControl.PreloadStatus preloadStatus = this.targetPreloadStatusOfCurrentPreloadingSource;
            return preloadStatus;
        }
    }

    private boolean maybeStartPreloadNextSource() {
        if (!shouldStartPreloadingNextSource()) {
            return false;
        }
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.sourceHolderPriorityQueue.peek());
        TargetPreloadStatusControl.PreloadStatus targetPreloadStatus = this.targetPreloadStatusControl.getTargetPreloadStatus(mediaSourceHolder.rankingData);
        this.targetPreloadStatusOfCurrentPreloadingSource = targetPreloadStatus;
        if (targetPreloadStatus != null) {
            preloadSourceInternal(mediaSourceHolder.mediaSource, mediaSourceHolder.startPositionUs);
            return true;
        }
        clearSourceInternal(mediaSourceHolder.mediaSource);
        return false;
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != this.applicationHandler.getLooper()) {
            throw new IllegalStateException("Preload manager is accessed on the wrong thread.");
        }
    }

    private final class MediaSourceHolder implements Comparable<BasePreloadManager<T>.MediaSourceHolder> {
        public final MediaSource mediaSource;
        public final T rankingData;
        public final long startPositionUs;

        public MediaSourceHolder(BasePreloadManager basePreloadManager, MediaSource mediaSource2, T t) {
            this(mediaSource2, t, C.TIME_UNSET);
        }

        public MediaSourceHolder(MediaSource mediaSource2, T t, long j) {
            this.mediaSource = mediaSource2;
            this.rankingData = t;
            this.startPositionUs = j;
        }

        public int compareTo(BasePreloadManager<T>.MediaSourceHolder mediaSourceHolder) {
            return BasePreloadManager.this.rankingDataComparator.compare(this.rankingData, mediaSourceHolder.rankingData);
        }
    }
}
