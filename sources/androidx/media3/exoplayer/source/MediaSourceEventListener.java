package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface MediaSourceEventListener {
    void onDownstreamFormatChanged(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    void onLoadCanceled(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadCompleted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onLoadError(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    void onLoadStarted(int i, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, (MediaSource.MediaPeriodId) null);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i, MediaSource.MediaPeriodId mediaPeriodId2) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i;
            this.mediaPeriodId = mediaPeriodId2;
        }

        public EventDispatcher withParameters(int i, MediaSource.MediaPeriodId mediaPeriodId2) {
            return new EventDispatcher(this.listenerAndHandlers, i, mediaPeriodId2);
        }

        @Deprecated
        public EventDispatcher withParameters(int i, MediaSource.MediaPeriodId mediaPeriodId2, long j) {
            return new EventDispatcher(this.listenerAndHandlers, i, mediaPeriodId2);
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(mediaSourceEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                if (next.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i) {
            loadStarted(loadEventInfo, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            MediaLoadData mediaLoadData = new MediaLoadData(i, i2, format, i3, obj, Util.usToMs(j), Util.usToMs(j2));
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadStarted(loadEventInfo, mediaLoadData);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, loadEventInfo, mediaLoadData));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$loadStarted$0$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m861lambda$loadStarted$0$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i) {
            loadCompleted(loadEventInfo, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            MediaLoadData mediaLoadData = new MediaLoadData(i, i2, format, i3, obj, Util.usToMs(j), Util.usToMs(j2));
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadCompleted(loadEventInfo, mediaLoadData);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, loadEventInfo, mediaLoadData));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$loadCompleted$1$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m859lambda$loadCompleted$1$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i) {
            loadCanceled(loadEventInfo, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2) {
            MediaLoadData mediaLoadData = new MediaLoadData(i, i2, format, i3, obj, Util.usToMs(j), Util.usToMs(j2));
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadCanceled(loadEventInfo, mediaLoadData);
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, loadEventInfo, mediaLoadData));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$loadCanceled$2$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m858lambda$loadCanceled$2$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        public void loadError(LoadEventInfo loadEventInfo, int i, IOException iOException, boolean z) {
            loadError(loadEventInfo, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, iOException, z);
        }

        public void loadError(LoadEventInfo loadEventInfo, int i, int i2, Format format, int i3, Object obj, long j, long j2, IOException iOException, boolean z) {
            MediaLoadData mediaLoadData = new MediaLoadData(i, i2, format, i3, obj, Util.usToMs(j), Util.usToMs(j2));
            LoadEventInfo loadEventInfo2 = loadEventInfo;
            loadError(loadEventInfo, mediaLoadData, iOException, z);
        }

        public void loadError(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, loadEventInfo, mediaLoadData, iOException, z));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$loadError$3$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m860lambda$loadError$3$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z);
        }

        public void upstreamDiscarded(int i, long j, long j2) {
            upstreamDiscarded(new MediaLoadData(1, i, (Format) null, 3, (Object) null, Util.usToMs(j), Util.usToMs(j2)));
        }

        public void upstreamDiscarded(MediaLoadData mediaLoadData) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, (MediaSource.MediaPeriodId) Assertions.checkNotNull(this.mediaPeriodId), mediaLoadData));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$upstreamDiscarded$4$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m862lambda$upstreamDiscarded$4$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(MediaSource.MediaPeriodId mediaPeriodId2, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onUpstreamDiscarded(this.windowIndex, mediaPeriodId2, mediaLoadData);
        }

        public void downstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
            downstreamFormatChanged(new MediaLoadData(1, i, format, i2, obj, Util.usToMs(j), C.TIME_UNSET));
        }

        public void downstreamFormatChanged(MediaLoadData mediaLoadData) {
            dispatchEvent(new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, mediaLoadData));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$downstreamFormatChanged$5$androidx-media3-exoplayer-source-MediaSourceEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m857lambda$downstreamFormatChanged$5$androidxmedia3exoplayersourceMediaSourceEventListener$EventDispatcher(MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        public void dispatchEvent(Consumer<MediaSourceEventListener> consumer) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new MediaSourceEventListener$EventDispatcher$$ExternalSyntheticLambda0(consumer, next.listener));
            }
        }

        private static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler2;
                this.listener = mediaSourceEventListener;
            }
        }
    }
}
