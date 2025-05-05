package androidx.camera.lifecycle;

import androidx.camera.core.UseCase;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.RestrictedCameraInfo;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class LifecycleCameraRepository {
    private static final Object INSTANCE_LOCK = new Object();
    private static LifecycleCameraRepository sInstance;
    private final ArrayDeque<LifecycleOwner> mActiveLifecycleOwners = new ArrayDeque<>();
    CameraCoordinator mCameraCoordinator;
    private final Map<Key, LifecycleCamera> mCameraMap = new HashMap();
    private final Map<LifecycleCameraRepositoryObserver, Set<Key>> mLifecycleObserverMap = new HashMap();
    private final Object mLock = new Object();

    LifecycleCameraRepository() {
    }

    static LifecycleCameraRepository getInstance() {
        LifecycleCameraRepository lifecycleCameraRepository;
        synchronized (INSTANCE_LOCK) {
            if (sInstance == null) {
                sInstance = new LifecycleCameraRepository();
            }
            lifecycleCameraRepository = sInstance;
        }
        return lifecycleCameraRepository;
    }

    /* access modifiers changed from: package-private */
    public LifecycleCamera createLifecycleCamera(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter cameraUseCaseAdapter) {
        synchronized (this.mLock) {
            Preconditions.checkArgument(this.mCameraMap.get(Key.create(lifecycleOwner, cameraUseCaseAdapter.getCameraId())) == null, "LifecycleCamera already exists for the given LifecycleOwner and set of cameras");
            LifecycleCamera lifecycleCamera = new LifecycleCamera(lifecycleOwner, cameraUseCaseAdapter);
            if (cameraUseCaseAdapter.getUseCases().isEmpty()) {
                lifecycleCamera.suspend();
            }
            if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                return lifecycleCamera;
            }
            registerCamera(lifecycleCamera);
            return lifecycleCamera;
        }
    }

    /* access modifiers changed from: package-private */
    public LifecycleCamera getLifecycleCamera(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter.CameraId cameraId) {
        LifecycleCamera lifecycleCamera;
        synchronized (this.mLock) {
            lifecycleCamera = this.mCameraMap.get(Key.create(lifecycleOwner, cameraId));
        }
        return lifecycleCamera;
    }

    /* access modifiers changed from: package-private */
    public Collection<LifecycleCamera> getLifecycleCameras() {
        Collection<LifecycleCamera> unmodifiableCollection;
        synchronized (this.mLock) {
            unmodifiableCollection = Collections.unmodifiableCollection(this.mCameraMap.values());
        }
        return unmodifiableCollection;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        synchronized (this.mLock) {
            for (LifecycleCameraRepositoryObserver lifecycleOwner : new HashSet(this.mLifecycleObserverMap.keySet())) {
                unregisterLifecycle(lifecycleOwner.getLifecycleOwner());
            }
        }
    }

    private void registerCamera(LifecycleCamera lifecycleCamera) {
        Set set;
        synchronized (this.mLock) {
            LifecycleOwner lifecycleOwner = lifecycleCamera.getLifecycleOwner();
            Key create = Key.create(lifecycleOwner, CameraUseCaseAdapter.generateCameraId((RestrictedCameraInfo) lifecycleCamera.getCameraInfo(), (RestrictedCameraInfo) lifecycleCamera.getSecondaryCameraInfo()));
            LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver = getLifecycleCameraRepositoryObserver(lifecycleOwner);
            if (lifecycleCameraRepositoryObserver != null) {
                set = this.mLifecycleObserverMap.get(lifecycleCameraRepositoryObserver);
            } else {
                set = new HashSet();
            }
            set.add(create);
            this.mCameraMap.put(create, lifecycleCamera);
            if (lifecycleCameraRepositoryObserver == null) {
                LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver2 = new LifecycleCameraRepositoryObserver(lifecycleOwner, this);
                this.mLifecycleObserverMap.put(lifecycleCameraRepositoryObserver2, set);
                lifecycleOwner.getLifecycle().addObserver(lifecycleCameraRepositoryObserver2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterLifecycle(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver = getLifecycleCameraRepositoryObserver(lifecycleOwner);
            if (lifecycleCameraRepositoryObserver != null) {
                setInactive(lifecycleOwner);
                for (Key remove : this.mLifecycleObserverMap.get(lifecycleCameraRepositoryObserver)) {
                    this.mCameraMap.remove(remove);
                }
                this.mLifecycleObserverMap.remove(lifecycleCameraRepositoryObserver);
                lifecycleCameraRepositoryObserver.getLifecycleOwner().getLifecycle().removeObserver(lifecycleCameraRepositoryObserver);
            }
        }
    }

    private LifecycleCameraRepositoryObserver getLifecycleCameraRepositoryObserver(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            for (LifecycleCameraRepositoryObserver next : this.mLifecycleObserverMap.keySet()) {
                if (lifecycleOwner.equals(next.getLifecycleOwner())) {
                    return next;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bindToLifecycleCamera(androidx.camera.lifecycle.LifecycleCamera r5, androidx.camera.core.ViewPort r6, java.util.List<androidx.camera.core.CameraEffect> r7, java.util.Collection<androidx.camera.core.UseCase> r8, androidx.camera.core.concurrent.CameraCoordinator r9) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            boolean r1 = r8.isEmpty()     // Catch:{ all -> 0x0094 }
            if (r1 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000c
        L_0x000b:
            r1 = 0
        L_0x000c:
            androidx.core.util.Preconditions.checkArgument(r1)     // Catch:{ all -> 0x0094 }
            r4.mCameraCoordinator = r9     // Catch:{ all -> 0x0094 }
            androidx.lifecycle.LifecycleOwner r9 = r5.getLifecycleOwner()     // Catch:{ all -> 0x0094 }
            androidx.camera.lifecycle.LifecycleCameraRepository$LifecycleCameraRepositoryObserver r1 = r4.getLifecycleCameraRepositoryObserver(r9)     // Catch:{ all -> 0x0094 }
            if (r1 != 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return
        L_0x001d:
            java.util.Map<androidx.camera.lifecycle.LifecycleCameraRepository$LifecycleCameraRepositoryObserver, java.util.Set<androidx.camera.lifecycle.LifecycleCameraRepository$Key>> r2 = r4.mLifecycleObserverMap     // Catch:{ all -> 0x0094 }
            java.lang.Object r1 = r2.get(r1)     // Catch:{ all -> 0x0094 }
            java.util.Set r1 = (java.util.Set) r1     // Catch:{ all -> 0x0094 }
            androidx.camera.core.concurrent.CameraCoordinator r2 = r4.mCameraCoordinator     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x0030
            int r2 = r2.getCameraOperatingMode()     // Catch:{ all -> 0x0094 }
            r3 = 2
            if (r2 == r3) goto L_0x0067
        L_0x0030:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0094 }
        L_0x0034:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x0067
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0094 }
            androidx.camera.lifecycle.LifecycleCameraRepository$Key r2 = (androidx.camera.lifecycle.LifecycleCameraRepository.Key) r2     // Catch:{ all -> 0x0094 }
            java.util.Map<androidx.camera.lifecycle.LifecycleCameraRepository$Key, androidx.camera.lifecycle.LifecycleCamera> r3 = r4.mCameraMap     // Catch:{ all -> 0x0094 }
            java.lang.Object r2 = r3.get(r2)     // Catch:{ all -> 0x0094 }
            androidx.camera.lifecycle.LifecycleCamera r2 = (androidx.camera.lifecycle.LifecycleCamera) r2     // Catch:{ all -> 0x0094 }
            java.lang.Object r2 = androidx.core.util.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0094 }
            androidx.camera.lifecycle.LifecycleCamera r2 = (androidx.camera.lifecycle.LifecycleCamera) r2     // Catch:{ all -> 0x0094 }
            boolean r3 = r2.equals(r5)     // Catch:{ all -> 0x0094 }
            if (r3 != 0) goto L_0x0034
            java.util.List r2 = r2.getUseCases()     // Catch:{ all -> 0x0094 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x005f
            goto L_0x0034
        L_0x005f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0094 }
            java.lang.String r6 = "Multiple LifecycleCameras with use cases are registered to the same LifecycleOwner."
            r5.<init>(r6)     // Catch:{ all -> 0x0094 }
            throw r5     // Catch:{ all -> 0x0094 }
        L_0x0067:
            androidx.camera.core.internal.CameraUseCaseAdapter r1 = r5.getCameraUseCaseAdapter()     // Catch:{ CameraException -> 0x008d }
            r1.setViewPort(r6)     // Catch:{ CameraException -> 0x008d }
            androidx.camera.core.internal.CameraUseCaseAdapter r6 = r5.getCameraUseCaseAdapter()     // Catch:{ CameraException -> 0x008d }
            r6.setEffects(r7)     // Catch:{ CameraException -> 0x008d }
            r5.bind(r8)     // Catch:{ CameraException -> 0x008d }
            androidx.lifecycle.Lifecycle r5 = r9.getLifecycle()     // Catch:{ all -> 0x0094 }
            androidx.lifecycle.Lifecycle$State r5 = r5.getCurrentState()     // Catch:{ all -> 0x0094 }
            androidx.lifecycle.Lifecycle$State r6 = androidx.lifecycle.Lifecycle.State.STARTED     // Catch:{ all -> 0x0094 }
            boolean r5 = r5.isAtLeast(r6)     // Catch:{ all -> 0x0094 }
            if (r5 == 0) goto L_0x008b
            r4.setActive(r9)     // Catch:{ all -> 0x0094 }
        L_0x008b:
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            return
        L_0x008d:
            r5 = move-exception
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0094 }
            r6.<init>(r5)     // Catch:{ all -> 0x0094 }
            throw r6     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0094 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.lifecycle.LifecycleCameraRepository.bindToLifecycleCamera(androidx.camera.lifecycle.LifecycleCamera, androidx.camera.core.ViewPort, java.util.List, java.util.Collection, androidx.camera.core.concurrent.CameraCoordinator):void");
    }

    /* access modifiers changed from: package-private */
    public void unbind(Collection<UseCase> collection) {
        synchronized (this.mLock) {
            for (Key key : this.mCameraMap.keySet()) {
                LifecycleCamera lifecycleCamera = this.mCameraMap.get(key);
                boolean z = !lifecycleCamera.getUseCases().isEmpty();
                lifecycleCamera.unbind(collection);
                if (z && lifecycleCamera.getUseCases().isEmpty()) {
                    setInactive(lifecycleCamera.getLifecycleOwner());
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unbindAll() {
        synchronized (this.mLock) {
            for (Key key : this.mCameraMap.keySet()) {
                LifecycleCamera lifecycleCamera = this.mCameraMap.get(key);
                lifecycleCamera.unbindAll();
                setInactive(lifecycleCamera.getLifecycleOwner());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setActive(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            if (hasUseCaseBound(lifecycleOwner)) {
                if (this.mActiveLifecycleOwners.isEmpty()) {
                    this.mActiveLifecycleOwners.push(lifecycleOwner);
                } else {
                    CameraCoordinator cameraCoordinator = this.mCameraCoordinator;
                    if (cameraCoordinator == null || cameraCoordinator.getCameraOperatingMode() != 2) {
                        LifecycleOwner peek = this.mActiveLifecycleOwners.peek();
                        if (!lifecycleOwner.equals(peek)) {
                            suspendUseCases(peek);
                            this.mActiveLifecycleOwners.remove(lifecycleOwner);
                            this.mActiveLifecycleOwners.push(lifecycleOwner);
                        }
                    }
                }
                unsuspendUseCases(lifecycleOwner);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setInactive(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            this.mActiveLifecycleOwners.remove(lifecycleOwner);
            suspendUseCases(lifecycleOwner);
            if (!this.mActiveLifecycleOwners.isEmpty()) {
                unsuspendUseCases(this.mActiveLifecycleOwners.peek());
            }
        }
    }

    private boolean hasUseCaseBound(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver = getLifecycleCameraRepositoryObserver(lifecycleOwner);
            if (lifecycleCameraRepositoryObserver == null) {
                return false;
            }
            for (Key key : this.mLifecycleObserverMap.get(lifecycleCameraRepositoryObserver)) {
                if (!((LifecycleCamera) Preconditions.checkNotNull(this.mCameraMap.get(key))).getUseCases().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }

    private void suspendUseCases(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver = getLifecycleCameraRepositoryObserver(lifecycleOwner);
            if (lifecycleCameraRepositoryObserver != null) {
                for (Key key : this.mLifecycleObserverMap.get(lifecycleCameraRepositoryObserver)) {
                    ((LifecycleCamera) Preconditions.checkNotNull(this.mCameraMap.get(key))).suspend();
                }
            }
        }
    }

    private void unsuspendUseCases(LifecycleOwner lifecycleOwner) {
        synchronized (this.mLock) {
            for (Key key : this.mLifecycleObserverMap.get(getLifecycleCameraRepositoryObserver(lifecycleOwner))) {
                LifecycleCamera lifecycleCamera = this.mCameraMap.get(key);
                if (!((LifecycleCamera) Preconditions.checkNotNull(lifecycleCamera)).getUseCases().isEmpty()) {
                    lifecycleCamera.unsuspend();
                }
            }
        }
    }

    static abstract class Key {
        public abstract CameraUseCaseAdapter.CameraId getCameraId();

        public abstract LifecycleOwner getLifecycleOwner();

        Key() {
        }

        static Key create(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter.CameraId cameraId) {
            return new AutoValue_LifecycleCameraRepository_Key(lifecycleOwner, cameraId);
        }
    }

    private static class LifecycleCameraRepositoryObserver implements LifecycleObserver {
        private final LifecycleCameraRepository mLifecycleCameraRepository;
        private final LifecycleOwner mLifecycleOwner;

        LifecycleCameraRepositoryObserver(LifecycleOwner lifecycleOwner, LifecycleCameraRepository lifecycleCameraRepository) {
            this.mLifecycleOwner = lifecycleOwner;
            this.mLifecycleCameraRepository = lifecycleCameraRepository;
        }

        /* access modifiers changed from: package-private */
        public LifecycleOwner getLifecycleOwner() {
            return this.mLifecycleOwner;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart(LifecycleOwner lifecycleOwner) {
            this.mLifecycleCameraRepository.setActive(lifecycleOwner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop(LifecycleOwner lifecycleOwner) {
            this.mLifecycleCameraRepository.setInactive(lifecycleOwner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy(LifecycleOwner lifecycleOwner) {
            this.mLifecycleCameraRepository.unregisterLifecycle(lifecycleOwner);
        }
    }
}
