package androidx.media3.exoplayer.drm;

import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public interface DrmSessionEventListener {
    void onDrmKeysLoaded(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    void onDrmKeysRemoved(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    void onDrmKeysRestored(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    void onDrmSessionAcquired(int i, MediaSource.MediaPeriodId mediaPeriodId, int i2) {
    }

    void onDrmSessionManagerError(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
    }

    void onDrmSessionReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
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

        public void addEventListener(Handler handler, DrmSessionEventListener drmSessionEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(drmSessionEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, drmSessionEventListener));
        }

        public void removeEventListener(DrmSessionEventListener drmSessionEventListener) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                if (next.listener == drmSessionEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        public void drmSessionAcquired(int i) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, next.listener, i));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmSessionAcquired$0$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m509lambda$drmSessionAcquired$0$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener, int i) {
            drmSessionEventListener.onDrmSessionAcquired(this.windowIndex, this.mediaPeriodId, i);
        }

        public void drmKeysLoaded() {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, next.listener));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmKeysLoaded$1$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m506lambda$drmKeysLoaded$1$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysLoaded(this.windowIndex, this.mediaPeriodId);
        }

        public void drmSessionManagerError(Exception exc) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, next.listener, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmSessionManagerError$2$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m510lambda$drmSessionManagerError$2$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener, Exception exc) {
            drmSessionEventListener.onDrmSessionManagerError(this.windowIndex, this.mediaPeriodId, exc);
        }

        public void drmKeysRestored() {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, next.listener));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmKeysRestored$3$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m508lambda$drmKeysRestored$3$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysRestored(this.windowIndex, this.mediaPeriodId);
        }

        public void drmKeysRemoved() {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, next.listener));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmKeysRemoved$4$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m507lambda$drmKeysRemoved$4$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmKeysRemoved(this.windowIndex, this.mediaPeriodId);
        }

        public void drmSessionReleased() {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new DrmSessionEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, next.listener));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$drmSessionReleased$5$androidx-media3-exoplayer-drm-DrmSessionEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m511lambda$drmSessionReleased$5$androidxmedia3exoplayerdrmDrmSessionEventListener$EventDispatcher(DrmSessionEventListener drmSessionEventListener) {
            drmSessionEventListener.onDrmSessionReleased(this.windowIndex, this.mediaPeriodId);
        }

        private static final class ListenerAndHandler {
            public Handler handler;
            public DrmSessionEventListener listener;

            public ListenerAndHandler(Handler handler2, DrmSessionEventListener drmSessionEventListener) {
                this.handler = handler2;
                this.listener = drmSessionEventListener;
            }
        }
    }
}
