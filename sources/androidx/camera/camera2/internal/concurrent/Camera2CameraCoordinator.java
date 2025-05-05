package androidx.camera.camera2.internal.concurrent;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.concurrent.CameraCoordinator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Camera2CameraCoordinator implements CameraCoordinator {
    private static final String TAG = "Camera2CameraCoordinator";
    private List<CameraInfo> mActiveConcurrentCameraInfos;
    private final CameraManagerCompat mCameraManager;
    private int mCameraOperatingMode = 0;
    private final Map<String, List<String>> mConcurrentCameraIdMap;
    private Set<Set<String>> mConcurrentCameraIds;
    private final List<CameraCoordinator.ConcurrentCameraModeListener> mConcurrentCameraModeListeners;

    public Camera2CameraCoordinator(CameraManagerCompat cameraManagerCompat) {
        this.mCameraManager = cameraManagerCompat;
        this.mConcurrentCameraIdMap = new HashMap();
        this.mConcurrentCameraIds = new HashSet();
        this.mConcurrentCameraModeListeners = new ArrayList();
        this.mActiveConcurrentCameraInfos = new ArrayList();
        retrieveConcurrentCameraIds();
    }

    public List<List<CameraSelector>> getConcurrentCameraSelectors() {
        ArrayList arrayList = new ArrayList();
        for (Set<String> it : this.mConcurrentCameraIds) {
            ArrayList arrayList2 = new ArrayList();
            for (String createCameraSelectorById : it) {
                arrayList2.add(createCameraSelectorById(this.mCameraManager, createCameraSelectorById));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<CameraInfo> getActiveConcurrentCameraInfos() {
        return this.mActiveConcurrentCameraInfos;
    }

    public void setActiveConcurrentCameraInfos(List<CameraInfo> list) {
        this.mActiveConcurrentCameraInfos = new ArrayList(list);
    }

    public String getPairedConcurrentCameraId(String str) {
        if (!this.mConcurrentCameraIdMap.containsKey(str)) {
            return null;
        }
        for (String str2 : this.mConcurrentCameraIdMap.get(str)) {
            Iterator<CameraInfo> it = this.mActiveConcurrentCameraInfos.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (str2.equals(Camera2CameraInfo.from(it.next()).getCameraId())) {
                        return str2;
                    }
                }
            }
        }
        return null;
    }

    public int getCameraOperatingMode() {
        return this.mCameraOperatingMode;
    }

    public void setCameraOperatingMode(int i) {
        if (i != this.mCameraOperatingMode) {
            for (CameraCoordinator.ConcurrentCameraModeListener onCameraOperatingModeUpdated : this.mConcurrentCameraModeListeners) {
                onCameraOperatingModeUpdated.onCameraOperatingModeUpdated(this.mCameraOperatingMode, i);
            }
        }
        if (this.mCameraOperatingMode == 2 && i != 2) {
            this.mActiveConcurrentCameraInfos.clear();
        }
        this.mCameraOperatingMode = i;
    }

    public void addListener(CameraCoordinator.ConcurrentCameraModeListener concurrentCameraModeListener) {
        this.mConcurrentCameraModeListeners.add(concurrentCameraModeListener);
    }

    public void removeListener(CameraCoordinator.ConcurrentCameraModeListener concurrentCameraModeListener) {
        this.mConcurrentCameraModeListeners.remove(concurrentCameraModeListener);
    }

    public void shutdown() {
        this.mConcurrentCameraModeListeners.clear();
        this.mConcurrentCameraIdMap.clear();
        this.mActiveConcurrentCameraInfos.clear();
        this.mConcurrentCameraIds.clear();
        this.mCameraOperatingMode = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0017 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void retrieveConcurrentCameraIds() {
        /*
            r10 = this;
            java.lang.String r0 = "Camera2CameraCoordinator"
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            androidx.camera.camera2.internal.compat.CameraManagerCompat r2 = r10.mCameraManager     // Catch:{ CameraAccessExceptionCompat -> 0x000e }
            java.util.Set r1 = r2.getConcurrentCameraIds()     // Catch:{ CameraAccessExceptionCompat -> 0x000e }
            goto L_0x0013
        L_0x000e:
            java.lang.String r2 = "Failed to get concurrent camera ids"
            androidx.camera.core.Logger.e(r0, r2)
        L_0x0013:
            java.util.Iterator r1 = r1.iterator()
        L_0x0017:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00cf
            java.lang.Object r2 = r1.next()
            java.util.Set r2 = (java.util.Set) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            int r2 = r3.size()
            r4 = 2
            if (r2 < r4) goto L_0x0017
            r2 = 0
            java.lang.Object r4 = r3.get(r2)
            java.lang.String r4 = (java.lang.String) r4
            r5 = 1
            java.lang.Object r6 = r3.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            androidx.camera.camera2.internal.compat.CameraManagerCompat r7 = r10.mCameraManager     // Catch:{ InitializationException -> 0x004f }
            boolean r7 = androidx.camera.camera2.internal.CameraIdUtil.isBackwardCompatible(r7, r4)     // Catch:{ InitializationException -> 0x004f }
            if (r7 == 0) goto L_0x0071
            androidx.camera.camera2.internal.compat.CameraManagerCompat r7 = r10.mCameraManager     // Catch:{ InitializationException -> 0x004f }
            boolean r7 = androidx.camera.camera2.internal.CameraIdUtil.isBackwardCompatible(r7, r6)     // Catch:{ InitializationException -> 0x004f }
            if (r7 == 0) goto L_0x0071
            r7 = r5
            goto L_0x0072
        L_0x004f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Concurrent camera id pair: ("
            r7.<init>(r8)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r8 = ", "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r6)
            java.lang.String r8 = ") is not backward compatible"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            androidx.camera.core.Logger.d(r0, r7)
        L_0x0071:
            r7 = r2
        L_0x0072:
            if (r7 != 0) goto L_0x0075
            goto L_0x0017
        L_0x0075:
            java.util.Set<java.util.Set<java.lang.String>> r7 = r10.mConcurrentCameraIds
            java.util.HashSet r8 = new java.util.HashSet
            java.lang.String[] r9 = new java.lang.String[]{r4, r6}
            java.util.List r9 = java.util.Arrays.asList(r9)
            r8.<init>(r9)
            r7.add(r8)
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7 = r10.mConcurrentCameraIdMap
            boolean r7 = r7.containsKey(r4)
            if (r7 != 0) goto L_0x0099
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7 = r10.mConcurrentCameraIdMap
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7.put(r4, r8)
        L_0x0099:
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7 = r10.mConcurrentCameraIdMap
            boolean r7 = r7.containsKey(r6)
            if (r7 != 0) goto L_0x00ab
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7 = r10.mConcurrentCameraIdMap
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r7.put(r6, r8)
        L_0x00ab:
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r7 = r10.mConcurrentCameraIdMap
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r3.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            r4.add(r5)
            java.util.Map<java.lang.String, java.util.List<java.lang.String>> r4 = r10.mConcurrentCameraIdMap
            java.lang.Object r4 = r4.get(r6)
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r2 = r3.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r4.add(r2)
            goto L_0x0017
        L_0x00cf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.concurrent.Camera2CameraCoordinator.retrieveConcurrentCameraIds():void");
    }

    private static CameraSelector createCameraSelectorById(CameraManagerCompat cameraManagerCompat, String str) {
        CameraSelector.Builder addCameraFilter = new CameraSelector.Builder().addCameraFilter(new Camera2CameraCoordinator$$ExternalSyntheticLambda0(str));
        try {
            addCameraFilter.requireLensFacing(((Integer) cameraManagerCompat.getCameraCharacteristicsCompat(str).get(CameraCharacteristics.LENS_FACING)).intValue());
            return addCameraFilter.build();
        } catch (CameraAccessExceptionCompat e) {
            throw new RuntimeException(e);
        }
    }

    static /* synthetic */ List lambda$createCameraSelectorById$0(String str, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CameraInfo cameraInfo = (CameraInfo) it.next();
            if (str.equals(Camera2CameraInfo.from(cameraInfo).getCameraId())) {
                return Collections.singletonList(cameraInfo);
            }
        }
        throw new IllegalArgumentException("No camera can be find for id: " + str);
    }
}
