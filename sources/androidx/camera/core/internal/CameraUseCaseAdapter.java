package androidx.camera.core.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Identifier;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.RestrictedCameraControl;
import androidx.camera.core.impl.RestrictedCameraInfo;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.compat.workaround.StreamSharingForceEnabler;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CameraUseCaseAdapter implements Camera {
    private static final String TAG = "CameraUseCaseAdapter";
    private final RestrictedCameraControl mAdapterCameraControl;
    private final RestrictedCameraInfo mAdapterCameraInfo;
    private final RestrictedCameraInfo mAdapterSecondaryCameraInfo;
    private final List<UseCase> mAppUseCases;
    private boolean mAttached;
    private final CameraConfig mCameraConfig;
    private final CameraCoordinator mCameraCoordinator;
    private final CameraDeviceSurfaceManager mCameraDeviceSurfaceManager;
    private final CameraInternal mCameraInternal;
    private final List<UseCase> mCameraUseCases;
    private final CompositionSettings mCompositionSettings;
    private List<CameraEffect> mEffects;
    private final CameraId mId;
    private Config mInteropConfig;
    private final Object mLock;
    private UseCase mPlaceholderForExtensions;
    private final CameraInternal mSecondaryCameraInternal;
    private final CompositionSettings mSecondaryCompositionSettings;
    private StreamSharing mStreamSharing;
    private final StreamSharingForceEnabler mStreamSharingForceEnabler;
    private final UseCaseConfigFactory mUseCaseConfigFactory;
    private ViewPort mViewPort;

    public CameraUseCaseAdapter(CameraInternal cameraInternal, CameraCoordinator cameraCoordinator, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this(cameraInternal, (CameraInternal) null, new RestrictedCameraInfo(cameraInternal.getCameraInfoInternal(), CameraConfigs.defaultConfig()), (RestrictedCameraInfo) null, CompositionSettings.DEFAULT, CompositionSettings.DEFAULT, cameraCoordinator, cameraDeviceSurfaceManager, useCaseConfigFactory);
    }

    public CameraUseCaseAdapter(CameraInternal cameraInternal, CameraInternal cameraInternal2, RestrictedCameraInfo restrictedCameraInfo, RestrictedCameraInfo restrictedCameraInfo2, CompositionSettings compositionSettings, CompositionSettings compositionSettings2, CameraCoordinator cameraCoordinator, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this.mAppUseCases = new ArrayList();
        this.mCameraUseCases = new ArrayList();
        this.mEffects = Collections.emptyList();
        this.mLock = new Object();
        this.mAttached = true;
        this.mInteropConfig = null;
        this.mStreamSharingForceEnabler = new StreamSharingForceEnabler();
        this.mCameraInternal = cameraInternal;
        this.mSecondaryCameraInternal = cameraInternal2;
        this.mCompositionSettings = compositionSettings;
        this.mSecondaryCompositionSettings = compositionSettings2;
        this.mCameraCoordinator = cameraCoordinator;
        this.mCameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
        this.mUseCaseConfigFactory = useCaseConfigFactory;
        CameraConfig cameraConfig = restrictedCameraInfo.getCameraConfig();
        this.mCameraConfig = cameraConfig;
        this.mAdapterCameraControl = new RestrictedCameraControl(cameraInternal.getCameraControlInternal(), cameraConfig.getSessionProcessor((SessionProcessor) null));
        this.mAdapterCameraInfo = restrictedCameraInfo;
        this.mAdapterSecondaryCameraInfo = restrictedCameraInfo2;
        this.mId = generateCameraId(restrictedCameraInfo, restrictedCameraInfo2);
    }

    public static CameraId generateCameraId(RestrictedCameraInfo restrictedCameraInfo, RestrictedCameraInfo restrictedCameraInfo2) {
        String str;
        StringBuilder append = new StringBuilder().append(restrictedCameraInfo.getCameraId());
        if (restrictedCameraInfo2 == null) {
            str = "";
        } else {
            str = restrictedCameraInfo2.getCameraId();
        }
        return CameraId.create(append.append(str).toString(), restrictedCameraInfo.getCameraConfig().getCompatibilityId());
    }

    public CameraId getCameraId() {
        return this.mId;
    }

    public boolean isEquivalent(CameraUseCaseAdapter cameraUseCaseAdapter) {
        return getCameraId().equals(cameraUseCaseAdapter.getCameraId());
    }

    public void setViewPort(ViewPort viewPort) {
        synchronized (this.mLock) {
            this.mViewPort = viewPort;
        }
    }

    public void setEffects(List<CameraEffect> list) {
        synchronized (this.mLock) {
            this.mEffects = list;
        }
    }

    public void addUseCases(Collection<UseCase> collection) throws CameraException {
        synchronized (this.mLock) {
            this.mCameraInternal.setExtendedConfig(this.mCameraConfig);
            CameraInternal cameraInternal = this.mSecondaryCameraInternal;
            if (cameraInternal != null) {
                cameraInternal.setExtendedConfig(this.mCameraConfig);
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.mAppUseCases);
            linkedHashSet.addAll(collection);
            try {
                CameraInternal cameraInternal2 = this.mSecondaryCameraInternal;
                boolean z = true;
                boolean z2 = cameraInternal2 != null;
                if (cameraInternal2 == null) {
                    z = false;
                }
                updateUseCases(linkedHashSet, z2, z);
            } catch (IllegalArgumentException e) {
                throw new CameraException((Throwable) e);
            }
        }
    }

    public void removeUseCases(Collection<UseCase> collection) {
        synchronized (this.mLock) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(this.mAppUseCases);
            linkedHashSet.removeAll(collection);
            CameraInternal cameraInternal = this.mSecondaryCameraInternal;
            boolean z = true;
            boolean z2 = cameraInternal != null;
            if (cameraInternal == null) {
                z = false;
            }
            updateUseCases(linkedHashSet, z2, z);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateUseCases(Collection<UseCase> collection) {
        updateUseCases(collection, false, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fb, code lost:
        r4 = r11.get(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateUseCases(java.util.Collection<androidx.camera.core.UseCase> r20, boolean r21, boolean r22) {
        /*
            r19 = this;
            r7 = r19
            r8 = r20
            r9 = r22
            java.lang.Object r10 = r7.mLock
            monitor-enter(r10)
            r19.checkUnsupportedFeatureCombinationAndThrow(r20)     // Catch:{ all -> 0x01eb }
            r11 = 1
            if (r21 != 0) goto L_0x001a
            boolean r0 = r19.shouldForceEnableStreamSharing(r20)     // Catch:{ all -> 0x01eb }
            if (r0 == 0) goto L_0x001a
            r7.updateUseCases(r8, r11, r9)     // Catch:{ all -> 0x01eb }
            monitor-exit(r10)     // Catch:{ all -> 0x01eb }
            return
        L_0x001a:
            androidx.camera.core.streamsharing.StreamSharing r0 = r19.createOrReuseStreamSharing(r20, r21)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r12 = r7.calculatePlaceholderForExtensions(r8, r0)     // Catch:{ all -> 0x01eb }
            java.util.Collection r13 = calculateCameraUseCases(r8, r12, r0)     // Catch:{ all -> 0x01eb }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x01eb }
            r14.<init>(r13)     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mCameraUseCases     // Catch:{ all -> 0x01eb }
            r14.removeAll(r1)     // Catch:{ all -> 0x01eb }
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x01eb }
            r15.<init>(r13)     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mCameraUseCases     // Catch:{ all -> 0x01eb }
            r15.retainAll(r1)     // Catch:{ all -> 0x01eb }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mCameraUseCases     // Catch:{ all -> 0x01eb }
            r6.<init>(r1)     // Catch:{ all -> 0x01eb }
            r6.removeAll(r13)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraConfig r1 = r7.mCameraConfig     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfigFactory r1 = r1.getUseCaseConfigFactory()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfigFactory r2 = r7.mUseCaseConfigFactory     // Catch:{ all -> 0x01eb }
            java.util.Map r5 = getConfigs(r14, r1, r2)     // Catch:{ all -> 0x01eb }
            java.util.Map r16 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x01eb }
            int r2 = r19.getCameraMode()     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInternal r1 = r7.mCameraInternal     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInfoInternal r3 = r1.getCameraInfoInternal()     // Catch:{ IllegalArgumentException -> 0x01d2 }
            r1 = r19
            r4 = r14
            r17 = r5
            r5 = r15
            r18 = r6
            r6 = r17
            java.util.Map r6 = r1.calculateSuggestedStreamSpecs(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInternal r1 = r7.mSecondaryCameraInternal     // Catch:{ IllegalArgumentException -> 0x01d2 }
            if (r1 == 0) goto L_0x008c
            int r2 = r19.getCameraMode()     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInternal r1 = r7.mSecondaryCameraInternal     // Catch:{ IllegalArgumentException -> 0x01d2 }
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInternal r1 = (androidx.camera.core.impl.CameraInternal) r1     // Catch:{ IllegalArgumentException -> 0x01d2 }
            androidx.camera.core.impl.CameraInfoInternal r3 = r1.getCameraInfoInternal()     // Catch:{ IllegalArgumentException -> 0x01d2 }
            r1 = r19
            r4 = r14
            r5 = r15
            r11 = r6
            r6 = r17
            java.util.Map r16 = r1.calculateSuggestedStreamSpecs(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x01d2 }
            goto L_0x008d
        L_0x008c:
            r11 = r6
        L_0x008d:
            r1 = r16
            r7.updateViewPortAndSensorToBufferMatrix(r11, r13)     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.CameraEffect> r2 = r7.mEffects     // Catch:{ all -> 0x01eb }
            updateEffects(r2, r13, r8)     // Catch:{ all -> 0x01eb }
            java.util.Iterator r2 = r18.iterator()     // Catch:{ all -> 0x01eb }
        L_0x009b:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x01eb }
            if (r3 == 0) goto L_0x00ad
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r3 = (androidx.camera.core.UseCase) r3     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r4 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            r3.unbindFromCamera(r4)     // Catch:{ all -> 0x01eb }
            goto L_0x009b
        L_0x00ad:
            androidx.camera.core.impl.CameraInternal r2 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            r3 = r18
            r2.detachUseCases(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r2 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            if (r2 == 0) goto L_0x00df
            java.util.Iterator r2 = r3.iterator()     // Catch:{ all -> 0x01eb }
        L_0x00bc:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x00d4
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r4 = (androidx.camera.core.UseCase) r4     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r5 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = java.util.Objects.requireNonNull(r5)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r5 = (androidx.camera.core.impl.CameraInternal) r5     // Catch:{ all -> 0x01eb }
            r4.unbindFromCamera(r5)     // Catch:{ all -> 0x01eb }
            goto L_0x00bc
        L_0x00d4:
            androidx.camera.core.impl.CameraInternal r2 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            java.lang.Object r2 = java.util.Objects.requireNonNull(r2)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r2 = (androidx.camera.core.impl.CameraInternal) r2     // Catch:{ all -> 0x01eb }
            r2.detachUseCases(r3)     // Catch:{ all -> 0x01eb }
        L_0x00df:
            boolean r2 = r3.isEmpty()     // Catch:{ all -> 0x01eb }
            if (r2 == 0) goto L_0x012b
            java.util.Iterator r2 = r15.iterator()     // Catch:{ all -> 0x01eb }
        L_0x00e9:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x01eb }
            if (r3 == 0) goto L_0x012b
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r3 = (androidx.camera.core.UseCase) r3     // Catch:{ all -> 0x01eb }
            boolean r4 = r11.containsKey(r3)     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x00e9
            java.lang.Object r4 = r11.get(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r4 = (androidx.camera.core.impl.StreamSpec) r4     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.Config r5 = r4.getImplementationOptions()     // Catch:{ all -> 0x01eb }
            if (r5 == 0) goto L_0x00e9
            androidx.camera.core.impl.SessionConfig r6 = r3.getSessionConfig()     // Catch:{ all -> 0x01eb }
            boolean r4 = hasImplementationOptionChanged(r4, r6)     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x00e9
            r3.updateSuggestedStreamSpecImplementationOptions(r5)     // Catch:{ all -> 0x01eb }
            boolean r4 = r7.mAttached     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x00e9
            androidx.camera.core.impl.CameraInternal r4 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            r4.onUseCaseUpdated(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r4 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            if (r4 == 0) goto L_0x00e9
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r4 = (androidx.camera.core.impl.CameraInternal) r4     // Catch:{ all -> 0x01eb }
            r4.onUseCaseUpdated(r3)     // Catch:{ all -> 0x01eb }
            goto L_0x00e9
        L_0x012b:
            java.util.Iterator r2 = r14.iterator()     // Catch:{ all -> 0x01eb }
        L_0x012f:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x01eb }
            if (r3 == 0) goto L_0x018e
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r3 = (androidx.camera.core.UseCase) r3     // Catch:{ all -> 0x01eb }
            r4 = r17
            java.lang.Object r5 = r4.get(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.internal.CameraUseCaseAdapter$ConfigPair r5 = (androidx.camera.core.internal.CameraUseCaseAdapter.ConfigPair) r5     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = java.util.Objects.requireNonNull(r5)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.internal.CameraUseCaseAdapter$ConfigPair r5 = (androidx.camera.core.internal.CameraUseCaseAdapter.ConfigPair) r5     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r6 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            if (r6 == 0) goto L_0x0172
            androidx.camera.core.impl.CameraInternal r9 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            java.lang.Object r6 = java.util.Objects.requireNonNull(r6)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r6 = (androidx.camera.core.impl.CameraInternal) r6     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfig<?> r15 = r5.mExtendedConfig     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfig<?> r5 = r5.mCameraConfig     // Catch:{ all -> 0x01eb }
            r3.bindToCamera(r9, r6, r15, r5)     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = r11.get(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r5 = (androidx.camera.core.impl.StreamSpec) r5     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = androidx.core.util.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r5 = (androidx.camera.core.impl.StreamSpec) r5     // Catch:{ all -> 0x01eb }
            java.lang.Object r6 = r1.get(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r6 = (androidx.camera.core.impl.StreamSpec) r6     // Catch:{ all -> 0x01eb }
            r3.updateSuggestedStreamSpec(r5, r6)     // Catch:{ all -> 0x01eb }
            goto L_0x018b
        L_0x0172:
            androidx.camera.core.impl.CameraInternal r6 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfig<?> r9 = r5.mExtendedConfig     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.UseCaseConfig<?> r5 = r5.mCameraConfig     // Catch:{ all -> 0x01eb }
            r15 = 0
            r3.bindToCamera(r6, r15, r9, r5)     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = r11.get(r3)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r5 = (androidx.camera.core.impl.StreamSpec) r5     // Catch:{ all -> 0x01eb }
            java.lang.Object r5 = androidx.core.util.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.StreamSpec r5 = (androidx.camera.core.impl.StreamSpec) r5     // Catch:{ all -> 0x01eb }
            r3.updateSuggestedStreamSpec(r5, r15)     // Catch:{ all -> 0x01eb }
        L_0x018b:
            r17 = r4
            goto L_0x012f
        L_0x018e:
            boolean r1 = r7.mAttached     // Catch:{ all -> 0x01eb }
            if (r1 == 0) goto L_0x01a4
            androidx.camera.core.impl.CameraInternal r1 = r7.mCameraInternal     // Catch:{ all -> 0x01eb }
            r1.attachUseCases(r14)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r1 = r7.mSecondaryCameraInternal     // Catch:{ all -> 0x01eb }
            if (r1 == 0) goto L_0x01a4
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)     // Catch:{ all -> 0x01eb }
            androidx.camera.core.impl.CameraInternal r1 = (androidx.camera.core.impl.CameraInternal) r1     // Catch:{ all -> 0x01eb }
            r1.attachUseCases(r14)     // Catch:{ all -> 0x01eb }
        L_0x01a4:
            java.util.Iterator r1 = r14.iterator()     // Catch:{ all -> 0x01eb }
        L_0x01a8:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x01eb }
            if (r2 == 0) goto L_0x01b8
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x01eb }
            androidx.camera.core.UseCase r2 = (androidx.camera.core.UseCase) r2     // Catch:{ all -> 0x01eb }
            r2.notifyState()     // Catch:{ all -> 0x01eb }
            goto L_0x01a8
        L_0x01b8:
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mAppUseCases     // Catch:{ all -> 0x01eb }
            r1.clear()     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mAppUseCases     // Catch:{ all -> 0x01eb }
            r1.addAll(r8)     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mCameraUseCases     // Catch:{ all -> 0x01eb }
            r1.clear()     // Catch:{ all -> 0x01eb }
            java.util.List<androidx.camera.core.UseCase> r1 = r7.mCameraUseCases     // Catch:{ all -> 0x01eb }
            r1.addAll(r13)     // Catch:{ all -> 0x01eb }
            r7.mPlaceholderForExtensions = r12     // Catch:{ all -> 0x01eb }
            r7.mStreamSharing = r0     // Catch:{ all -> 0x01eb }
            monitor-exit(r10)     // Catch:{ all -> 0x01eb }
            return
        L_0x01d2:
            r0 = move-exception
            if (r21 != 0) goto L_0x01ea
            boolean r1 = r19.hasExtension()     // Catch:{ all -> 0x01eb }
            if (r1 != 0) goto L_0x01ea
            androidx.camera.core.concurrent.CameraCoordinator r1 = r7.mCameraCoordinator     // Catch:{ all -> 0x01eb }
            int r1 = r1.getCameraOperatingMode()     // Catch:{ all -> 0x01eb }
            r2 = 2
            if (r1 == r2) goto L_0x01ea
            r1 = 1
            r7.updateUseCases(r8, r1, r9)     // Catch:{ all -> 0x01eb }
            monitor-exit(r10)     // Catch:{ all -> 0x01eb }
            return
        L_0x01ea:
            throw r0     // Catch:{ all -> 0x01eb }
        L_0x01eb:
            r0 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x01eb }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.CameraUseCaseAdapter.updateUseCases(java.util.Collection, boolean, boolean):void");
    }

    private boolean shouldForceEnableStreamSharing(Collection<UseCase> collection) {
        if (!hasExtension() || !hasVideoCapture(collection)) {
            return this.mStreamSharingForceEnabler.shouldForceEnableStreamSharing(this.mCameraInternal.getCameraInfoInternal().getCameraId(), collection);
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean hasImplementationOptionChanged(androidx.camera.core.impl.StreamSpec r4, androidx.camera.core.impl.SessionConfig r5) {
        /*
            androidx.camera.core.impl.Config r4 = r4.getImplementationOptions()
            androidx.camera.core.impl.Config r0 = r5.getImplementationOptions()
            java.util.Set r1 = r4.listOptions()
            int r1 = r1.size()
            androidx.camera.core.impl.Config r5 = r5.getImplementationOptions()
            java.util.Set r5 = r5.listOptions()
            int r5 = r5.size()
            r2 = 1
            if (r1 == r5) goto L_0x0020
            return r2
        L_0x0020:
            java.util.Set r5 = r4.listOptions()
            java.util.Iterator r5 = r5.iterator()
        L_0x0028:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0049
            java.lang.Object r1 = r5.next()
            androidx.camera.core.impl.Config$Option r1 = (androidx.camera.core.impl.Config.Option) r1
            boolean r3 = r0.containsOption(r1)
            if (r3 == 0) goto L_0x0048
            java.lang.Object r3 = r0.retrieveOption(r1)
            java.lang.Object r1 = r4.retrieveOption(r1)
            boolean r1 = java.util.Objects.equals(r3, r1)
            if (r1 != 0) goto L_0x0028
        L_0x0048:
            return r2
        L_0x0049:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.CameraUseCaseAdapter.hasImplementationOptionChanged(androidx.camera.core.impl.StreamSpec, androidx.camera.core.impl.SessionConfig):boolean");
    }

    private int getCameraMode() {
        synchronized (this.mLock) {
            if (this.mCameraCoordinator.getCameraOperatingMode() == 2) {
                return 1;
            }
            return 0;
        }
    }

    private boolean hasExtension() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCameraConfig.getSessionProcessor((SessionProcessor) null) != null;
        }
        return z;
    }

    private Set<UseCase> getStreamSharingChildren(Collection<UseCase> collection, boolean z) {
        HashSet hashSet = new HashSet();
        int sharingTargets = getSharingTargets(z);
        for (UseCase next : collection) {
            Preconditions.checkArgument(!StreamSharing.isStreamSharing(next), "Only support one level of sharing for now.");
            if (next.isEffectTargetsSupported(sharingTargets)) {
                hashSet.add(next);
            }
        }
        return hashSet;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: boolean} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getSharingTargets(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.util.List<androidx.camera.core.CameraEffect> r1 = r7.mEffects     // Catch:{ all -> 0x0039 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0039 }
            r2 = 0
        L_0x000a:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0039 }
            r4 = 0
            if (r3 == 0) goto L_0x002c
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0039 }
            androidx.camera.core.CameraEffect r3 = (androidx.camera.core.CameraEffect) r3     // Catch:{ all -> 0x0039 }
            int r5 = r3.getTargets()     // Catch:{ all -> 0x0039 }
            int r5 = androidx.camera.core.processing.TargetUtils.getNumberOfTargets(r5)     // Catch:{ all -> 0x0039 }
            r6 = 1
            if (r5 <= r6) goto L_0x000a
            if (r2 != 0) goto L_0x0025
            r4 = r6
        L_0x0025:
            java.lang.String r2 = "Can only have one sharing effect."
            androidx.core.util.Preconditions.checkState(r4, r2)     // Catch:{ all -> 0x0039 }
            r2 = r3
            goto L_0x000a
        L_0x002c:
            if (r2 != 0) goto L_0x002f
            goto L_0x0033
        L_0x002f:
            int r4 = r2.getTargets()     // Catch:{ all -> 0x0039 }
        L_0x0033:
            if (r8 == 0) goto L_0x0037
            r4 = r4 | 3
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            return r4
        L_0x0039:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.CameraUseCaseAdapter.getSharingTargets(boolean):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.camera.core.streamsharing.StreamSharing createOrReuseStreamSharing(java.util.Collection<androidx.camera.core.UseCase> r9, boolean r10) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            java.util.Set r6 = r8.getStreamSharingChildren(r9, r10)     // Catch:{ all -> 0x004f }
            int r9 = r6.size()     // Catch:{ all -> 0x004f }
            r10 = 2
            r1 = 0
            if (r9 >= r10) goto L_0x001d
            boolean r9 = r8.hasExtension()     // Catch:{ all -> 0x004f }
            if (r9 == 0) goto L_0x001b
            boolean r9 = hasVideoCapture(r6)     // Catch:{ all -> 0x004f }
            if (r9 != 0) goto L_0x001d
        L_0x001b:
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return r1
        L_0x001d:
            androidx.camera.core.streamsharing.StreamSharing r9 = r8.mStreamSharing     // Catch:{ all -> 0x004f }
            if (r9 == 0) goto L_0x0035
            java.util.Set r9 = r9.getChildren()     // Catch:{ all -> 0x004f }
            boolean r9 = r9.equals(r6)     // Catch:{ all -> 0x004f }
            if (r9 == 0) goto L_0x0035
            androidx.camera.core.streamsharing.StreamSharing r9 = r8.mStreamSharing     // Catch:{ all -> 0x004f }
            java.lang.Object r9 = java.util.Objects.requireNonNull(r9)     // Catch:{ all -> 0x004f }
            androidx.camera.core.streamsharing.StreamSharing r9 = (androidx.camera.core.streamsharing.StreamSharing) r9     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return r9
        L_0x0035:
            boolean r9 = isStreamSharingChildrenCombinationValid(r6)     // Catch:{ all -> 0x004f }
            if (r9 != 0) goto L_0x003d
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return r1
        L_0x003d:
            androidx.camera.core.streamsharing.StreamSharing r9 = new androidx.camera.core.streamsharing.StreamSharing     // Catch:{ all -> 0x004f }
            androidx.camera.core.impl.CameraInternal r2 = r8.mCameraInternal     // Catch:{ all -> 0x004f }
            androidx.camera.core.impl.CameraInternal r3 = r8.mSecondaryCameraInternal     // Catch:{ all -> 0x004f }
            androidx.camera.core.CompositionSettings r4 = r8.mCompositionSettings     // Catch:{ all -> 0x004f }
            androidx.camera.core.CompositionSettings r5 = r8.mSecondaryCompositionSettings     // Catch:{ all -> 0x004f }
            androidx.camera.core.impl.UseCaseConfigFactory r7 = r8.mUseCaseConfigFactory     // Catch:{ all -> 0x004f }
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            return r9
        L_0x004f:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.CameraUseCaseAdapter.createOrReuseStreamSharing(java.util.Collection, boolean):androidx.camera.core.streamsharing.StreamSharing");
    }

    static boolean isStreamSharingChildrenCombinationValid(Collection<UseCase> collection) {
        int[] iArr = {1, 2, 4};
        HashSet hashSet = new HashSet();
        for (UseCase next : collection) {
            int i = 0;
            while (true) {
                if (i < 3) {
                    int i2 = iArr[i];
                    if (next.isEffectTargetsSupported(i2)) {
                        if (hashSet.contains(Integer.valueOf(i2))) {
                            return false;
                        }
                        hashSet.add(Integer.valueOf(i2));
                    }
                    i++;
                }
            }
        }
        return true;
    }

    static Collection<UseCase> calculateCameraUseCases(Collection<UseCase> collection, UseCase useCase, StreamSharing streamSharing) {
        ArrayList arrayList = new ArrayList(collection);
        if (useCase != null) {
            arrayList.add(useCase);
        }
        if (streamSharing != null) {
            arrayList.add(streamSharing);
            arrayList.removeAll(streamSharing.getChildren());
        }
        return arrayList;
    }

    public List<UseCase> getUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mAppUseCases);
        }
        return arrayList;
    }

    public Collection<UseCase> getCameraUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mCameraUseCases);
        }
        return arrayList;
    }

    public void attachUseCases() {
        synchronized (this.mLock) {
            if (!this.mAttached) {
                if (!this.mCameraUseCases.isEmpty()) {
                    this.mCameraInternal.setExtendedConfig(this.mCameraConfig);
                    CameraInternal cameraInternal = this.mSecondaryCameraInternal;
                    if (cameraInternal != null) {
                        cameraInternal.setExtendedConfig(this.mCameraConfig);
                    }
                }
                this.mCameraInternal.attachUseCases(this.mCameraUseCases);
                CameraInternal cameraInternal2 = this.mSecondaryCameraInternal;
                if (cameraInternal2 != null) {
                    cameraInternal2.attachUseCases(this.mCameraUseCases);
                }
                restoreInteropConfig();
                for (UseCase notifyState : this.mCameraUseCases) {
                    notifyState.notifyState();
                }
                this.mAttached = true;
            }
        }
    }

    public void setActiveResumingMode(boolean z) {
        this.mCameraInternal.setActiveResumingMode(z);
    }

    public void detachUseCases() {
        synchronized (this.mLock) {
            if (this.mAttached) {
                this.mCameraInternal.detachUseCases(new ArrayList(this.mCameraUseCases));
                CameraInternal cameraInternal = this.mSecondaryCameraInternal;
                if (cameraInternal != null) {
                    cameraInternal.detachUseCases(new ArrayList(this.mCameraUseCases));
                }
                cacheInteropConfig();
                this.mAttached = false;
            }
        }
    }

    private void restoreInteropConfig() {
        synchronized (this.mLock) {
            if (this.mInteropConfig != null) {
                this.mCameraInternal.getCameraControlInternal().addInteropConfig(this.mInteropConfig);
            }
        }
    }

    private void cacheInteropConfig() {
        synchronized (this.mLock) {
            CameraControlInternal cameraControlInternal = this.mCameraInternal.getCameraControlInternal();
            this.mInteropConfig = cameraControlInternal.getInteropConfig();
            cameraControlInternal.clearInteropConfig();
        }
    }

    private Map<UseCase, StreamSpec> calculateSuggestedStreamSpecs(int i, CameraInfoInternal cameraInfoInternal, Collection<UseCase> collection, Collection<UseCase> collection2, Map<UseCase, ConfigPair> map) {
        Size size;
        Rect rect;
        boolean z;
        CameraInfoInternal cameraInfoInternal2 = cameraInfoInternal;
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Iterator<UseCase> it = collection2.iterator();
        while (true) {
            size = null;
            if (!it.hasNext()) {
                break;
            }
            UseCase next = it.next();
            AttachedSurfaceInfo create = AttachedSurfaceInfo.create(this.mCameraDeviceSurfaceManager.transformSurfaceConfig(i, cameraId, next.getImageFormat(), next.getAttachedSurfaceResolution()), next.getImageFormat(), next.getAttachedSurfaceResolution(), ((StreamSpec) Preconditions.checkNotNull(next.getAttachedStreamSpec())).getDynamicRange(), StreamSharing.getCaptureTypes(next), next.getAttachedStreamSpec().getImplementationOptions(), next.getCurrentConfig().getTargetFrameRate((Range<Integer>) null));
            arrayList.add(create);
            hashMap2.put(create, next);
            hashMap.put(next, next.getAttachedStreamSpec());
        }
        int i2 = i;
        if (!collection.isEmpty()) {
            HashMap hashMap3 = new HashMap();
            HashMap hashMap4 = new HashMap();
            try {
                rect = this.mCameraInternal.getCameraControlInternal().getSensorRect();
            } catch (NullPointerException unused) {
                rect = null;
            }
            if (rect != null) {
                size = TransformUtils.rectToSize(rect);
            }
            SupportedOutputSizesSorter supportedOutputSizesSorter = new SupportedOutputSizesSorter(cameraInfoInternal2, size);
            Iterator<UseCase> it2 = collection.iterator();
            loop1:
            while (true) {
                z = false;
                while (true) {
                    if (!it2.hasNext()) {
                        break loop1;
                    }
                    UseCase next2 = it2.next();
                    ConfigPair configPair = map.get(next2);
                    UseCaseConfig<?> mergeConfigs = next2.mergeConfigs(cameraInfoInternal2, configPair.mExtendedConfig, configPair.mCameraConfig);
                    hashMap3.put(mergeConfigs, next2);
                    hashMap4.put(mergeConfigs, supportedOutputSizesSorter.getSortedSupportedOutputSizes(mergeConfigs));
                    if (next2.getCurrentConfig() instanceof PreviewConfig) {
                        if (((PreviewConfig) next2.getCurrentConfig()).getPreviewStabilizationMode() == 2) {
                            z = true;
                        }
                    }
                }
            }
            Pair<Map<UseCaseConfig<?>, StreamSpec>, Map<AttachedSurfaceInfo, StreamSpec>> suggestedStreamSpecs = this.mCameraDeviceSurfaceManager.getSuggestedStreamSpecs(i, cameraId, arrayList, hashMap4, z, hasVideoCapture(collection));
            for (Map.Entry entry : hashMap3.entrySet()) {
                hashMap.put((UseCase) entry.getValue(), (StreamSpec) ((Map) suggestedStreamSpecs.first).get(entry.getKey()));
            }
            for (Map.Entry entry2 : ((Map) suggestedStreamSpecs.second).entrySet()) {
                if (hashMap2.containsKey(entry2.getKey())) {
                    hashMap.put((UseCase) hashMap2.get(entry2.getKey()), (StreamSpec) entry2.getValue());
                }
            }
        }
        return hashMap;
    }

    static void updateEffects(List<CameraEffect> list, Collection<UseCase> collection, Collection<UseCase> collection2) {
        List<CameraEffect> effectsOnUseCases = setEffectsOnUseCases(list, collection);
        ArrayList arrayList = new ArrayList(collection2);
        arrayList.removeAll(collection);
        List<CameraEffect> effectsOnUseCases2 = setEffectsOnUseCases(effectsOnUseCases, arrayList);
        if (effectsOnUseCases2.size() > 0) {
            Logger.w(TAG, "Unused effects: " + effectsOnUseCases2);
        }
    }

    private static List<CameraEffect> setEffectsOnUseCases(List<CameraEffect> list, Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(list);
        for (UseCase next : collection) {
            next.setEffect((CameraEffect) null);
            for (CameraEffect next2 : list) {
                if (next.isEffectTargetsSupported(next2.getTargets())) {
                    Preconditions.checkState(next.getEffect() == null, next + " already has effect" + next.getEffect());
                    next.setEffect(next2);
                    arrayList.remove(next2);
                }
            }
        }
        return arrayList;
    }

    private void updateViewPortAndSensorToBufferMatrix(Map<UseCase, StreamSpec> map, Collection<UseCase> collection) {
        synchronized (this.mLock) {
            if (this.mViewPort != null && !collection.isEmpty()) {
                Integer valueOf = Integer.valueOf(this.mCameraInternal.getCameraInfoInternal().getLensFacing());
                boolean z = true;
                if (valueOf == null) {
                    Logger.w(TAG, "The lens facing is null, probably an external.");
                } else if (valueOf.intValue() != 0) {
                    z = false;
                }
                Map<UseCase, Rect> calculateViewPortRects = ViewPorts.calculateViewPortRects(this.mCameraInternal.getCameraControlInternal().getSensorRect(), z, this.mViewPort.getAspectRatio(), this.mCameraInternal.getCameraInfoInternal().getSensorRotationDegrees(this.mViewPort.getRotation()), this.mViewPort.getScaleType(), this.mViewPort.getLayoutDirection(), map);
                for (UseCase next : collection) {
                    next.setViewPortCropRect((Rect) Preconditions.checkNotNull(calculateViewPortRects.get(next)));
                }
            }
            for (UseCase next2 : collection) {
                next2.setSensorToBufferTransformMatrix(calculateSensorToBufferTransformMatrix(this.mCameraInternal.getCameraControlInternal().getSensorRect(), ((StreamSpec) Preconditions.checkNotNull(map.get(next2))).getResolution()));
            }
        }
    }

    private static Matrix calculateSensorToBufferTransformMatrix(Rect rect, Size size) {
        Preconditions.checkArgument(rect.width() > 0 && rect.height() > 0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF rectF = new RectF(rect);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) size.getWidth(), (float) size.getHeight()), rectF, Matrix.ScaleToFit.CENTER);
        matrix.invert(matrix);
        return matrix;
    }

    private static class ConfigPair {
        UseCaseConfig<?> mCameraConfig;
        UseCaseConfig<?> mExtendedConfig;

        ConfigPair(UseCaseConfig<?> useCaseConfig, UseCaseConfig<?> useCaseConfig2) {
            this.mExtendedConfig = useCaseConfig;
            this.mCameraConfig = useCaseConfig2;
        }
    }

    private static Map<UseCase, ConfigPair> getConfigs(Collection<UseCase> collection, UseCaseConfigFactory useCaseConfigFactory, UseCaseConfigFactory useCaseConfigFactory2) {
        UseCaseConfig<?> useCaseConfig;
        HashMap hashMap = new HashMap();
        for (UseCase next : collection) {
            if (StreamSharing.isStreamSharing(next)) {
                useCaseConfig = generateExtendedStreamSharingConfigFromPreview(useCaseConfigFactory, (StreamSharing) next);
            } else {
                useCaseConfig = next.getDefaultConfig(false, useCaseConfigFactory);
            }
            hashMap.put(next, new ConfigPair(useCaseConfig, next.getDefaultConfig(true, useCaseConfigFactory2)));
        }
        return hashMap;
    }

    private static UseCaseConfig<?> generateExtendedStreamSharingConfigFromPreview(UseCaseConfigFactory useCaseConfigFactory, StreamSharing streamSharing) {
        UseCaseConfig<?> defaultConfig = new Preview.Builder().build().getDefaultConfig(false, useCaseConfigFactory);
        if (defaultConfig == null) {
            return null;
        }
        MutableOptionsBundle from = MutableOptionsBundle.from(defaultConfig);
        from.removeOption(TargetConfig.OPTION_TARGET_CLASS);
        return streamSharing.getUseCaseConfigBuilder(from).getUseCaseConfig();
    }

    private void checkUnsupportedFeatureCombinationAndThrow(Collection<UseCase> collection) throws IllegalArgumentException {
        if (hasExtension()) {
            if (hasNonSdrConfig(collection)) {
                throw new IllegalArgumentException("Extensions are only supported for use with standard dynamic range.");
            } else if (hasUltraHdrImageCapture(collection)) {
                throw new IllegalArgumentException("Extensions are not supported for use with Ultra HDR image capture.");
            } else if (hasRawImageCapture(collection)) {
                throw new IllegalArgumentException("Extensions are not supported for use with Raw image capture.");
            }
        }
        synchronized (this.mLock) {
            if (!this.mEffects.isEmpty()) {
                if (hasUltraHdrImageCapture(collection) || hasRawImageCapture(collection)) {
                    throw new IllegalArgumentException("Ultra HDR image and Raw capture does not support for use with CameraEffect.");
                }
            }
        }
    }

    private static boolean hasNonSdrConfig(Collection<UseCase> collection) {
        for (UseCase currentConfig : collection) {
            if (isNotSdr(currentConfig.getCurrentConfig().getDynamicRange())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNotSdr(DynamicRange dynamicRange) {
        boolean z = dynamicRange.getBitDepth() == 10;
        boolean z2 = (dynamicRange.getEncoding() == 1 || dynamicRange.getEncoding() == 0) ? false : true;
        if (z || z2) {
            return true;
        }
        return false;
    }

    private static boolean hasUltraHdrImageCapture(Collection<UseCase> collection) {
        for (UseCase next : collection) {
            if (isImageCapture(next)) {
                UseCaseConfig<?> currentConfig = next.getCurrentConfig();
                if (currentConfig.containsOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT) && ((Integer) Preconditions.checkNotNull((Integer) currentConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT))).intValue() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasRawImageCapture(Collection<UseCase> collection) {
        for (UseCase next : collection) {
            if (isImageCapture(next)) {
                UseCaseConfig<?> currentConfig = next.getCurrentConfig();
                if (currentConfig.containsOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT) && ((Integer) Preconditions.checkNotNull((Integer) currentConfig.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT))).intValue() == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static abstract class CameraId {
        public abstract Identifier getCameraConfigId();

        public abstract String getCameraIdString();

        public static CameraId create(String str, Identifier identifier) {
            return new AutoValue_CameraUseCaseAdapter_CameraId(str, identifier);
        }
    }

    public static final class CameraException extends Exception {
        public CameraException() {
        }

        public CameraException(String str) {
            super(str);
        }

        public CameraException(Throwable th) {
            super(th);
        }
    }

    public CameraControl getCameraControl() {
        return this.mAdapterCameraControl;
    }

    public CameraInfo getCameraInfo() {
        return this.mAdapterCameraInfo;
    }

    public CameraInfo getSecondaryCameraInfo() {
        return this.mAdapterSecondaryCameraInfo;
    }

    public CameraConfig getExtendedConfig() {
        CameraConfig cameraConfig;
        synchronized (this.mLock) {
            cameraConfig = this.mCameraConfig;
        }
        return cameraConfig;
    }

    public boolean isUseCasesCombinationSupported(boolean z, UseCase... useCaseArr) {
        Collection asList = Arrays.asList(useCaseArr);
        if (z) {
            asList = calculateCameraUseCases(asList, (UseCase) null, createOrReuseStreamSharing(asList, true));
        }
        Collection collection = asList;
        synchronized (this.mLock) {
            try {
                calculateSuggestedStreamSpecs(getCameraMode(), this.mCameraInternal.getCameraInfoInternal(), collection, Collections.emptyList(), getConfigs(collection, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory));
            } catch (IllegalArgumentException unused) {
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    private UseCase calculatePlaceholderForExtensions(Collection<UseCase> collection, StreamSharing streamSharing) {
        UseCase useCase;
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(collection);
            if (streamSharing != null) {
                arrayList.add(streamSharing);
                arrayList.removeAll(streamSharing.getChildren());
            }
            if (isCoexistingPreviewImageCaptureRequired()) {
                if (isExtraPreviewRequired(arrayList)) {
                    useCase = isPreview(this.mPlaceholderForExtensions) ? this.mPlaceholderForExtensions : createExtraPreview();
                } else if (isExtraImageCaptureRequired(arrayList)) {
                    useCase = isImageCapture(this.mPlaceholderForExtensions) ? this.mPlaceholderForExtensions : createExtraImageCapture();
                }
            }
            useCase = null;
        }
        return useCase;
    }

    private boolean isCoexistingPreviewImageCaptureRequired() {
        boolean z;
        synchronized (this.mLock) {
            z = true;
            if (this.mCameraConfig.getUseCaseCombinationRequiredRule() != 1) {
                z = false;
            }
        }
        return z;
    }

    private static boolean isExtraPreviewRequired(Collection<UseCase> collection) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase next : collection) {
            if (isPreview(next) || StreamSharing.isStreamSharing(next)) {
                z2 = true;
            } else if (isImageCapture(next)) {
                z = true;
            }
        }
        if (!z || z2) {
            return false;
        }
        return true;
    }

    private static boolean isExtraImageCaptureRequired(Collection<UseCase> collection) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase next : collection) {
            if (isPreview(next) || StreamSharing.isStreamSharing(next)) {
                z = true;
            } else if (isImageCapture(next)) {
                z2 = true;
            }
        }
        if (!z || z2) {
            return false;
        }
        return true;
    }

    private static boolean hasVideoCapture(Collection<UseCase> collection) {
        for (UseCase isVideoCapture : collection) {
            if (isVideoCapture(isVideoCapture)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isVideoCapture(UseCase useCase) {
        if (useCase != null) {
            if (!useCase.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE)) {
                SentryLogcatAdapter.e(TAG, useCase + " UseCase does not have capture type.");
            } else if (useCase.getCurrentConfig().getCaptureType() == UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean isPreview(UseCase useCase) {
        return useCase instanceof Preview;
    }

    private static boolean isImageCapture(UseCase useCase) {
        return useCase instanceof ImageCapture;
    }

    private Preview createExtraPreview() {
        Preview build = new Preview.Builder().setTargetName("Preview-Extra").build();
        build.setSurfaceProvider(new CameraUseCaseAdapter$$ExternalSyntheticLambda1());
        return build;
    }

    static /* synthetic */ void lambda$createExtraPreview$1(SurfaceRequest surfaceRequest) {
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(surfaceRequest.getResolution().getWidth(), surfaceRequest.getResolution().getHeight());
        surfaceTexture.detachFromGLContext();
        Surface surface = new Surface(surfaceTexture);
        surfaceRequest.provideSurface(surface, CameraXExecutors.directExecutor(), new CameraUseCaseAdapter$$ExternalSyntheticLambda0(surface, surfaceTexture));
    }

    static /* synthetic */ void lambda$createExtraPreview$0(Surface surface, SurfaceTexture surfaceTexture, SurfaceRequest.Result result) {
        surface.release();
        surfaceTexture.release();
    }

    private ImageCapture createExtraImageCapture() {
        return new ImageCapture.Builder().setTargetName("ImageCapture-Extra").build();
    }
}
