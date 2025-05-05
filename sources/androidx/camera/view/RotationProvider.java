package androidx.camera.view;

import android.content.Context;
import android.view.OrientationEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class RotationProvider {
    boolean mIgnoreCanDetectForTest = false;
    final Map<Listener, ListenerWrapper> mListeners = new HashMap();
    final Object mLock = new Object();
    final OrientationEventListener mOrientationListener;

    public interface Listener {
        void onRotationChanged(int i);
    }

    static int orientationToSurfaceRotation(int i) {
        if (i >= 315 || i < 45) {
            return 0;
        }
        if (i >= 225) {
            return 1;
        }
        return i >= 135 ? 2 : 3;
    }

    public RotationProvider(Context context) {
        this.mOrientationListener = new OrientationEventListener(context) {
            private static final int INVALID_SURFACE_ROTATION = -1;
            private int mRotation = -1;

            public void onOrientationChanged(int i) {
                int orientationToSurfaceRotation;
                ArrayList<ListenerWrapper> arrayList;
                if (i != -1 && this.mRotation != (orientationToSurfaceRotation = RotationProvider.orientationToSurfaceRotation(i))) {
                    this.mRotation = orientationToSurfaceRotation;
                    synchronized (RotationProvider.this.mLock) {
                        arrayList = new ArrayList<>(RotationProvider.this.mListeners.values());
                    }
                    if (!arrayList.isEmpty()) {
                        for (ListenerWrapper onRotationChanged : arrayList) {
                            onRotationChanged.onRotationChanged(orientationToSurfaceRotation);
                        }
                    }
                }
            }
        };
    }

    public boolean addListener(Executor executor, Listener listener) {
        synchronized (this.mLock) {
            if (!this.mOrientationListener.canDetectOrientation() && !this.mIgnoreCanDetectForTest) {
                return false;
            }
            this.mListeners.put(listener, new ListenerWrapper(listener, executor));
            this.mOrientationListener.enable();
            return true;
        }
    }

    public void removeListener(Listener listener) {
        synchronized (this.mLock) {
            ListenerWrapper listenerWrapper = this.mListeners.get(listener);
            if (listenerWrapper != null) {
                listenerWrapper.disable();
                this.mListeners.remove(listener);
            }
            if (this.mListeners.isEmpty()) {
                this.mOrientationListener.disable();
            }
        }
    }

    private static class ListenerWrapper {
        private final AtomicBoolean mEnabled = new AtomicBoolean(true);
        private final Executor mExecutor;
        private final Listener mListener;

        ListenerWrapper(Listener listener, Executor executor) {
            this.mListener = listener;
            this.mExecutor = executor;
        }

        /* access modifiers changed from: package-private */
        public void onRotationChanged(int i) {
            this.mExecutor.execute(new RotationProvider$ListenerWrapper$$ExternalSyntheticLambda0(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onRotationChanged$0$androidx-camera-view-RotationProvider$ListenerWrapper  reason: not valid java name */
        public /* synthetic */ void m320lambda$onRotationChanged$0$androidxcameraviewRotationProvider$ListenerWrapper(int i) {
            if (this.mEnabled.get()) {
                this.mListener.onRotationChanged(i);
            }
        }

        /* access modifiers changed from: package-private */
        public void disable() {
            this.mEnabled.set(false);
        }
    }
}
