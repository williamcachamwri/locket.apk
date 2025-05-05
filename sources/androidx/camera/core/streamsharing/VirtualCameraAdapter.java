package androidx.camera.core.streamsharing;

import android.graphics.Rect;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.concurrent.DualOutConfig;
import androidx.camera.core.processing.util.OutConfig;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class VirtualCameraAdapter implements UseCase.StateChangeCallback {
    private static final String TAG = "VirtualCameraAdapter";
    final Set<UseCase> mChildren;
    final Map<UseCase, Boolean> mChildrenActiveState = new HashMap();
    private final Set<UseCaseConfig<?>> mChildrenConfigs;
    private final Map<UseCase, UseCaseConfig<?>> mChildrenConfigsMap;
    final Map<UseCase, SurfaceEdge> mChildrenEdges = new HashMap();
    private final Map<UseCase, VirtualCamera> mChildrenVirtualCameras = new HashMap();
    private final CameraInternal mParentCamera;
    private final CameraCaptureCallback mParentMetadataCallback = createCameraCaptureCallback();
    private final ResolutionsMerger mResolutionsMerger;
    private final CameraInternal mSecondaryParentCamera;
    private ResolutionsMerger mSecondaryResolutionsMerger;
    private final UseCaseConfigFactory mUseCaseConfigFactory;

    VirtualCameraAdapter(CameraInternal cameraInternal, CameraInternal cameraInternal2, Set<UseCase> set, UseCaseConfigFactory useCaseConfigFactory, StreamSharing.Control control) {
        this.mParentCamera = cameraInternal;
        this.mSecondaryParentCamera = cameraInternal2;
        this.mUseCaseConfigFactory = useCaseConfigFactory;
        this.mChildren = set;
        Map<UseCase, UseCaseConfig<?>> childrenConfigsMap = toChildrenConfigsMap(cameraInternal, set, useCaseConfigFactory);
        this.mChildrenConfigsMap = childrenConfigsMap;
        HashSet hashSet = new HashSet(childrenConfigsMap.values());
        this.mChildrenConfigs = hashSet;
        this.mResolutionsMerger = new ResolutionsMerger(cameraInternal, hashSet);
        if (cameraInternal2 != null) {
            this.mSecondaryResolutionsMerger = new ResolutionsMerger(cameraInternal2, hashSet);
        }
        for (UseCase next : set) {
            this.mChildrenActiveState.put(next, false);
            this.mChildrenVirtualCameras.put(next, new VirtualCamera(cameraInternal, this, control));
        }
    }

    /* access modifiers changed from: package-private */
    public void mergeChildrenConfigs(MutableConfig mutableConfig) {
        mutableConfig.insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, this.mResolutionsMerger.getMergedResolutions(mutableConfig));
        mutableConfig.insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(getHighestSurfacePriority(this.mChildrenConfigs)));
        DynamicRange resolveDynamicRange = DynamicRangeUtils.resolveDynamicRange(this.mChildrenConfigs);
        if (resolveDynamicRange != null) {
            mutableConfig.insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, resolveDynamicRange);
            mutableConfig.insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, resolveTargetFrameRate(this.mChildrenConfigs));
            for (UseCase next : this.mChildren) {
                if (next.getCurrentConfig().getVideoStabilizationMode() != 0) {
                    mutableConfig.insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, Integer.valueOf(next.getCurrentConfig().getVideoStabilizationMode()));
                }
                if (next.getCurrentConfig().getPreviewStabilizationMode() != 0) {
                    mutableConfig.insertOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, Integer.valueOf(next.getCurrentConfig().getPreviewStabilizationMode()));
                }
            }
            return;
        }
        throw new IllegalArgumentException("Failed to merge child dynamic ranges, can not find a dynamic range that satisfies all children.");
    }

    /* access modifiers changed from: package-private */
    public void bindChildren() {
        for (UseCase next : this.mChildren) {
            next.bindToCamera((CameraInternal) Objects.requireNonNull(this.mChildrenVirtualCameras.get(next)), (CameraInternal) null, (UseCaseConfig<?>) null, next.getDefaultConfig(true, this.mUseCaseConfigFactory));
        }
    }

    /* access modifiers changed from: package-private */
    public void unbindChildren() {
        for (UseCase next : this.mChildren) {
            next.unbindFromCamera((CameraInternal) Objects.requireNonNull(this.mChildrenVirtualCameras.get(next)));
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyStateAttached() {
        for (UseCase next : this.mChildren) {
            next.onStateAttached();
            next.onCameraControlReady();
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyStateDetached() {
        for (UseCase onStateDetached : this.mChildren) {
            onStateDetached.onStateDetached();
        }
    }

    /* access modifiers changed from: package-private */
    public Set<UseCase> getChildren() {
        return this.mChildren;
    }

    /* access modifiers changed from: package-private */
    public Map<UseCase, OutConfig> getChildrenOutConfigs(SurfaceEdge surfaceEdge, int i, boolean z) {
        HashMap hashMap = new HashMap();
        for (UseCase next : this.mChildren) {
            hashMap.put(next, calculateOutConfig(next, this.mResolutionsMerger, this.mParentCamera, surfaceEdge, i, z));
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public Map<UseCase, DualOutConfig> getChildrenOutConfigs(SurfaceEdge surfaceEdge, SurfaceEdge surfaceEdge2, int i, boolean z) {
        HashMap hashMap = new HashMap();
        for (UseCase next : this.mChildren) {
            UseCase useCase = next;
            int i2 = i;
            boolean z2 = z;
            hashMap.put(next, DualOutConfig.of(calculateOutConfig(useCase, this.mResolutionsMerger, this.mParentCamera, surfaceEdge, i2, z2), calculateOutConfig(useCase, this.mSecondaryResolutionsMerger, (CameraInternal) Objects.requireNonNull(this.mSecondaryParentCamera), surfaceEdge2, i2, z2)));
        }
        return hashMap;
    }

    private OutConfig calculateOutConfig(UseCase useCase, ResolutionsMerger resolutionsMerger, CameraInternal cameraInternal, SurfaceEdge surfaceEdge, int i, boolean z) {
        int sensorRotationDegrees = cameraInternal.getCameraInfo().getSensorRotationDegrees(i);
        boolean isMirrored = TransformUtils.isMirrored(surfaceEdge.getSensorToBufferTransform());
        Pair<Rect, Size> preferredChildSizePair = resolutionsMerger.getPreferredChildSizePair((UseCaseConfig) Objects.requireNonNull(this.mChildrenConfigsMap.get(useCase)), surfaceEdge.getCropRect(), TransformUtils.getRotationDegrees(surfaceEdge.getSensorToBufferTransform()), z);
        int childRotationDegrees = getChildRotationDegrees(useCase, this.mParentCamera);
        ((VirtualCamera) Objects.requireNonNull(this.mChildrenVirtualCameras.get(useCase))).setRotationDegrees(childRotationDegrees);
        int within360 = TransformUtils.within360((surfaceEdge.getRotationDegrees() + childRotationDegrees) - sensorRotationDegrees);
        return OutConfig.of(getChildTargetType(useCase), getChildFormat(useCase), (Rect) preferredChildSizePair.first, TransformUtils.rotateSize((Size) preferredChildSizePair.second, within360), within360, useCase.isMirroringRequired(cameraInternal) ^ isMirrored);
    }

    /* access modifiers changed from: package-private */
    public void setChildrenEdges(Map<UseCase, SurfaceEdge> map) {
        this.mChildrenEdges.clear();
        this.mChildrenEdges.putAll(map);
        for (Map.Entry next : this.mChildrenEdges.entrySet()) {
            UseCase useCase = (UseCase) next.getKey();
            SurfaceEdge surfaceEdge = (SurfaceEdge) next.getValue();
            useCase.setViewPortCropRect(surfaceEdge.getCropRect());
            useCase.setSensorToBufferTransformMatrix(surfaceEdge.getSensorToBufferTransform());
            useCase.updateSuggestedStreamSpec(surfaceEdge.getStreamSpec(), (StreamSpec) null);
            useCase.notifyState();
        }
    }

    /* access modifiers changed from: package-private */
    public void resetChildren() {
        Threads.checkMainThread();
        for (UseCase onUseCaseReset : this.mChildren) {
            onUseCaseReset(onUseCaseReset);
        }
    }

    /* access modifiers changed from: package-private */
    public CameraCaptureCallback getParentMetadataCallback() {
        return this.mParentMetadataCallback;
    }

    public void onUseCaseActive(UseCase useCase) {
        Threads.checkMainThread();
        if (!isUseCaseActive(useCase)) {
            this.mChildrenActiveState.put(useCase, true);
            DeferrableSurface childSurface = getChildSurface(useCase);
            if (childSurface != null) {
                forceSetProvider(getUseCaseEdge(useCase), childSurface, useCase.getSessionConfig());
            }
        }
    }

    public void onUseCaseInactive(UseCase useCase) {
        Threads.checkMainThread();
        if (isUseCaseActive(useCase)) {
            this.mChildrenActiveState.put(useCase, false);
            getUseCaseEdge(useCase).disconnect();
        }
    }

    public void onUseCaseUpdated(UseCase useCase) {
        Threads.checkMainThread();
        if (isUseCaseActive(useCase)) {
            SurfaceEdge useCaseEdge = getUseCaseEdge(useCase);
            DeferrableSurface childSurface = getChildSurface(useCase);
            if (childSurface != null) {
                forceSetProvider(useCaseEdge, childSurface, useCase.getSessionConfig());
            } else {
                useCaseEdge.disconnect();
            }
        }
    }

    public void onUseCaseReset(UseCase useCase) {
        DeferrableSurface childSurface;
        Threads.checkMainThread();
        SurfaceEdge useCaseEdge = getUseCaseEdge(useCase);
        if (isUseCaseActive(useCase) && (childSurface = getChildSurface(useCase)) != null) {
            forceSetProvider(useCaseEdge, childSurface, useCase.getSessionConfig());
        }
    }

    private int getChildRotationDegrees(UseCase useCase, CameraInternal cameraInternal) {
        return cameraInternal.getCameraInfo().getSensorRotationDegrees(((ImageOutputConfig) useCase.getCurrentConfig()).getTargetRotation(0));
    }

    private static int getChildFormat(UseCase useCase) {
        return useCase instanceof ImageCapture ? 256 : 34;
    }

    private static int getChildTargetType(UseCase useCase) {
        if (useCase instanceof Preview) {
            return 1;
        }
        return useCase instanceof ImageCapture ? 4 : 2;
    }

    private static Map<UseCase, UseCaseConfig<?>> toChildrenConfigsMap(CameraInternal cameraInternal, Set<UseCase> set, UseCaseConfigFactory useCaseConfigFactory) {
        HashMap hashMap = new HashMap();
        for (UseCase next : set) {
            hashMap.put(next, next.mergeConfigs(cameraInternal.getCameraInfoInternal(), (UseCaseConfig<?>) null, next.getDefaultConfig(true, useCaseConfigFactory)));
        }
        return hashMap;
    }

    private static int getHighestSurfacePriority(Set<UseCaseConfig<?>> set) {
        int i = 0;
        for (UseCaseConfig<?> surfaceOccupancyPriority : set) {
            i = Math.max(i, surfaceOccupancyPriority.getSurfaceOccupancyPriority(0));
        }
        return i;
    }

    private SurfaceEdge getUseCaseEdge(UseCase useCase) {
        return (SurfaceEdge) Objects.requireNonNull(this.mChildrenEdges.get(useCase));
    }

    private boolean isUseCaseActive(UseCase useCase) {
        return ((Boolean) Objects.requireNonNull(this.mChildrenActiveState.get(useCase))).booleanValue();
    }

    private static void forceSetProvider(SurfaceEdge surfaceEdge, DeferrableSurface deferrableSurface, SessionConfig sessionConfig) {
        surfaceEdge.invalidate();
        try {
            surfaceEdge.setProvider(deferrableSurface);
        } catch (DeferrableSurface.SurfaceClosedException unused) {
            if (sessionConfig.getErrorListener() != null) {
                sessionConfig.getErrorListener().onError(sessionConfig, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
            }
        }
    }

    static DeferrableSurface getChildSurface(UseCase useCase) {
        List<DeferrableSurface> list;
        if (useCase instanceof ImageCapture) {
            list = useCase.getSessionConfig().getSurfaces();
        } else {
            list = useCase.getSessionConfig().getRepeatingCaptureConfig().getSurfaces();
        }
        Preconditions.checkState(list.size() <= 1);
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public CameraCaptureCallback createCameraCaptureCallback() {
        return new CameraCaptureCallback() {
            public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                super.onCaptureCompleted(i, cameraCaptureResult);
                for (UseCase sessionConfig : VirtualCameraAdapter.this.mChildren) {
                    VirtualCameraAdapter.sendCameraCaptureResultToChild(cameraCaptureResult, sessionConfig.getSessionConfig(), i);
                }
            }
        };
    }

    static void sendCameraCaptureResultToChild(CameraCaptureResult cameraCaptureResult, SessionConfig sessionConfig, int i) {
        for (CameraCaptureCallback onCaptureCompleted : sessionConfig.getRepeatingCameraCaptureCallbacks()) {
            onCaptureCompleted.onCaptureCompleted(i, new VirtualCameraCaptureResult(sessionConfig.getRepeatingCaptureConfig().getTagBundle(), cameraCaptureResult));
        }
    }

    private static Range<Integer> resolveTargetFrameRate(Set<UseCaseConfig<?>> set) {
        Range<Integer> range = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        for (UseCaseConfig<?> targetFrameRate : set) {
            Range<Integer> targetFrameRate2 = targetFrameRate.getTargetFrameRate(range);
            if (StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED.equals(range)) {
                range = targetFrameRate2;
            } else {
                try {
                    range = range.intersect(targetFrameRate2);
                } catch (IllegalArgumentException unused) {
                    Logger.d(TAG, "No intersected frame rate can be found from the target frame rate settings of the UseCases! Resolved: " + range + " <<>> " + targetFrameRate2);
                    return range.extend(targetFrameRate2);
                }
            }
        }
        return range;
    }
}
