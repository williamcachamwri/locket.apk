package androidx.camera.core.streamsharing;

import android.graphics.Rect;
import android.util.Size;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.Preview$$ExternalSyntheticBackport0;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.processing.DefaultSurfaceProcessor;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.camera.core.processing.concurrent.DualOutConfig;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessor;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode;
import androidx.camera.core.processing.util.OutConfig;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StreamSharing extends UseCase {
    private static final String TAG = "StreamSharing";
    private SurfaceEdge mCameraEdge;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private final CompositionSettings mCompositionSettings;
    private final StreamSharingConfig mDefaultConfig;
    private DualSurfaceProcessorNode mDualSharingNode;
    private SurfaceProcessorNode mEffectNode;
    private SurfaceEdge mSecondaryCameraEdge;
    private final CompositionSettings mSecondaryCompositionSettings;
    SessionConfig.Builder mSecondarySessionConfigBuilder;
    private SurfaceEdge mSecondarySharingInputEdge;
    SessionConfig.Builder mSessionConfigBuilder;
    private SurfaceEdge mSharingInputEdge;
    private SurfaceProcessorNode mSharingNode;
    private final VirtualCameraAdapter mVirtualCameraAdapter;

    interface Control {
        ListenableFuture<Void> jpegSnapshot(int i, int i2);
    }

    private static StreamSharingConfig getDefaultConfig(Set<UseCase> set) {
        MutableConfig mutableConfig = new StreamSharingBuilder().getMutableConfig();
        mutableConfig.insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 34);
        ArrayList arrayList = new ArrayList();
        for (UseCase next : set) {
            if (next.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE)) {
                arrayList.add(next.getCurrentConfig().getCaptureType());
            } else {
                SentryLogcatAdapter.e(TAG, "A child does not have capture type.");
            }
        }
        mutableConfig.insertOption(StreamSharingConfig.OPTION_CAPTURE_TYPES, arrayList);
        mutableConfig.insertOption(ImageOutputConfig.OPTION_MIRROR_MODE, 2);
        return new StreamSharingConfig(OptionsBundle.from(mutableConfig));
    }

    public StreamSharing(CameraInternal cameraInternal, CameraInternal cameraInternal2, CompositionSettings compositionSettings, CompositionSettings compositionSettings2, Set<UseCase> set, UseCaseConfigFactory useCaseConfigFactory) {
        super(getDefaultConfig(set));
        this.mDefaultConfig = getDefaultConfig(set);
        this.mCompositionSettings = compositionSettings;
        this.mSecondaryCompositionSettings = compositionSettings2;
        this.mVirtualCameraAdapter = new VirtualCameraAdapter(cameraInternal, cameraInternal2, set, useCaseConfigFactory, new StreamSharing$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-streamsharing-StreamSharing  reason: not valid java name */
    public /* synthetic */ ListenableFuture m225lambda$new$0$androidxcameracorestreamsharingStreamSharing(int i, int i2) {
        SurfaceProcessorNode surfaceProcessorNode = this.mSharingNode;
        if (surfaceProcessorNode != null) {
            return surfaceProcessorNode.getSurfaceProcessor().snapshot(i, i2);
        }
        return Futures.immediateFailedFuture(new Exception("Failed to take picture: pipeline is not ready."));
    }

    public UseCaseConfig<?> getDefaultConfig(boolean z, UseCaseConfigFactory useCaseConfigFactory) {
        Config config = useCaseConfigFactory.getConfig(this.mDefaultConfig.getCaptureType(), 1);
        if (z) {
            config = Config.mergeConfigs(config, this.mDefaultConfig.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return new StreamSharingBuilder(MutableOptionsBundle.from(config));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [androidx.camera.core.impl.UseCaseConfig$Builder, androidx.camera.core.impl.UseCaseConfig$Builder<?, ?, ?>] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.impl.UseCaseConfig<?> onMergeConfig(androidx.camera.core.impl.CameraInfoInternal r2, androidx.camera.core.impl.UseCaseConfig.Builder<?, ?, ?> r3) {
        /*
            r1 = this;
            androidx.camera.core.streamsharing.VirtualCameraAdapter r2 = r1.mVirtualCameraAdapter
            androidx.camera.core.impl.MutableConfig r0 = r3.getMutableConfig()
            r2.mergeChildrenConfigs(r0)
            androidx.camera.core.impl.UseCaseConfig r2 = r3.getUseCaseConfig()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.streamsharing.StreamSharing.onMergeConfig(androidx.camera.core.impl.CameraInfoInternal, androidx.camera.core.impl.UseCaseConfig$Builder):androidx.camera.core.impl.UseCaseConfig");
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec streamSpec, StreamSpec streamSpec2) {
        updateSessionConfig(createPipelineAndUpdateChildrenSpecs(getCameraId(), getSecondaryCameraId(), getCurrentConfig(), streamSpec, streamSpec2));
        notifyActive();
        return streamSpec;
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config config) {
        this.mSessionConfigBuilder.addImplementationOptions(config);
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(config).build();
    }

    public void onBind() {
        super.onBind();
        this.mVirtualCameraAdapter.bindChildren();
    }

    public void onUnbind() {
        super.onUnbind();
        clearPipeline();
        this.mVirtualCameraAdapter.unbindChildren();
    }

    public void onStateAttached() {
        super.onStateAttached();
        this.mVirtualCameraAdapter.notifyStateAttached();
    }

    public void onStateDetached() {
        super.onStateDetached();
        this.mVirtualCameraAdapter.notifyStateDetached();
    }

    public Set<UseCase> getChildren() {
        return this.mVirtualCameraAdapter.getChildren();
    }

    public Set<Integer> getSupportedEffectTargets() {
        HashSet hashSet = new HashSet();
        hashSet.add(3);
        return hashSet;
    }

    private List<SessionConfig> createPipelineAndUpdateChildrenSpecs(String str, String str2, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2) {
        Threads.checkMainThread();
        boolean z = true;
        if (streamSpec2 == null) {
            createPrimaryCamera(str, str2, useCaseConfig, streamSpec, (StreamSpec) null);
            this.mSharingNode = getSharingNode((CameraInternal) Objects.requireNonNull(getCamera()), streamSpec);
            if (getViewPortCropRect() == null) {
                z = false;
            }
            Map<UseCase, OutConfig> childrenOutConfigs = this.mVirtualCameraAdapter.getChildrenOutConfigs(this.mSharingInputEdge, getTargetRotationInternal(), z);
            SurfaceProcessorNode.Out transform = this.mSharingNode.transform(SurfaceProcessorNode.In.of(this.mSharingInputEdge, new ArrayList(childrenOutConfigs.values())));
            HashMap hashMap = new HashMap();
            for (Map.Entry next : childrenOutConfigs.entrySet()) {
                hashMap.put((UseCase) next.getKey(), (SurfaceEdge) transform.get(next.getValue()));
            }
            this.mVirtualCameraAdapter.setChildrenEdges(hashMap);
            return Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()});
        }
        createPrimaryCamera(str, str2, useCaseConfig, streamSpec, streamSpec2);
        createSecondaryCamera(str, str2, useCaseConfig, streamSpec, streamSpec2);
        this.mDualSharingNode = getDualSharingNode(getCamera(), getSecondaryCamera(), streamSpec, this.mCompositionSettings, this.mSecondaryCompositionSettings);
        if (getViewPortCropRect() == null) {
            z = false;
        }
        Map<UseCase, DualOutConfig> childrenOutConfigs2 = this.mVirtualCameraAdapter.getChildrenOutConfigs(this.mSharingInputEdge, this.mSecondarySharingInputEdge, getTargetRotationInternal(), z);
        DualSurfaceProcessorNode.Out transform2 = this.mDualSharingNode.transform(DualSurfaceProcessorNode.In.of(this.mSharingInputEdge, this.mSecondarySharingInputEdge, new ArrayList(childrenOutConfigs2.values())));
        HashMap hashMap2 = new HashMap();
        for (Map.Entry next2 : childrenOutConfigs2.entrySet()) {
            hashMap2.put((UseCase) next2.getKey(), (SurfaceEdge) transform2.get(next2.getValue()));
        }
        this.mVirtualCameraAdapter.setChildrenEdges(hashMap2);
        return Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build(), this.mSecondarySessionConfigBuilder.build()});
    }

    private void createPrimaryCamera(String str, String str2, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2) {
        SurfaceEdge surfaceEdge = new SurfaceEdge(3, 34, streamSpec, getSensorToBufferTransformMatrix(), ((CameraInternal) Objects.requireNonNull(getCamera())).getHasTransform(), (Rect) Objects.requireNonNull(getCropRect(streamSpec.getResolution())), getRelativeRotation((CameraInternal) Objects.requireNonNull(getCamera())), -1, isMirroringRequired((CameraInternal) Objects.requireNonNull(getCamera())));
        this.mCameraEdge = surfaceEdge;
        this.mSharingInputEdge = getSharingInputEdge(surfaceEdge, (CameraInternal) Objects.requireNonNull(getCamera()));
        UseCaseConfig<?> useCaseConfig2 = useCaseConfig;
        StreamSpec streamSpec3 = streamSpec;
        SessionConfig.Builder createSessionConfigBuilder = createSessionConfigBuilder(this.mCameraEdge, useCaseConfig2, streamSpec3);
        this.mSessionConfigBuilder = createSessionConfigBuilder;
        addCameraErrorListener(createSessionConfigBuilder, str, str2, useCaseConfig2, streamSpec3, streamSpec2);
    }

    private void createSecondaryCamera(String str, String str2, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2) {
        SurfaceEdge surfaceEdge = new SurfaceEdge(3, 34, streamSpec2, getSensorToBufferTransformMatrix(), ((CameraInternal) Objects.requireNonNull(getSecondaryCamera())).getHasTransform(), (Rect) Objects.requireNonNull(getCropRect(streamSpec2.getResolution())), getRelativeRotation((CameraInternal) Objects.requireNonNull(getSecondaryCamera())), -1, isMirroringRequired((CameraInternal) Objects.requireNonNull(getSecondaryCamera())));
        this.mSecondaryCameraEdge = surfaceEdge;
        this.mSecondarySharingInputEdge = getSharingInputEdge(surfaceEdge, (CameraInternal) Objects.requireNonNull(getSecondaryCamera()));
        UseCaseConfig<?> useCaseConfig2 = useCaseConfig;
        StreamSpec streamSpec3 = streamSpec2;
        SessionConfig.Builder createSessionConfigBuilder = createSessionConfigBuilder(this.mSecondaryCameraEdge, useCaseConfig2, streamSpec3);
        this.mSecondarySessionConfigBuilder = createSessionConfigBuilder;
        addCameraErrorListener(createSessionConfigBuilder, str, str2, useCaseConfig2, streamSpec, streamSpec3);
    }

    private SessionConfig.Builder createSessionConfigBuilder(SurfaceEdge surfaceEdge, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec) {
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(useCaseConfig, streamSpec.getResolution());
        propagateChildrenTemplate(createFrom);
        propagateChildrenCamera2Interop(streamSpec.getResolution(), createFrom);
        createFrom.addSurface(surfaceEdge.getDeferrableSurface(), streamSpec.getDynamicRange(), (String) null, -1);
        createFrom.addRepeatingCameraCaptureCallback(this.mVirtualCameraAdapter.getParentMetadataCallback());
        if (streamSpec.getImplementationOptions() != null) {
            createFrom.addImplementationOptions(streamSpec.getImplementationOptions());
        }
        applyExpectedFrameRateRange(createFrom, streamSpec);
        return createFrom;
    }

    private void propagateChildrenTemplate(SessionConfig.Builder builder) {
        int i = -1;
        for (UseCase childTemplate : getChildren()) {
            i = SessionConfig.getHigherPriorityTemplateType(i, getChildTemplate(childTemplate));
        }
        if (i != -1) {
            builder.setTemplateType(i);
        }
    }

    private static int getChildTemplate(UseCase useCase) {
        return useCase.getCurrentConfig().getDefaultSessionConfig().getTemplateType();
    }

    private void propagateChildrenCamera2Interop(Size size, SessionConfig.Builder builder) {
        for (UseCase currentConfig : getChildren()) {
            SessionConfig build = SessionConfig.Builder.createFrom(currentConfig.getCurrentConfig(), size).build();
            builder.addAllRepeatingCameraCaptureCallbacks(build.getRepeatingCameraCaptureCallbacks());
            builder.addAllCameraCaptureCallbacks(build.getSingleCameraCaptureCallbacks());
            builder.addAllSessionStateCallbacks(build.getSessionStateCallbacks());
            builder.addAllDeviceStateCallbacks(build.getDeviceStateCallbacks());
            builder.addImplementationOptions(build.getImplementationOptions());
        }
    }

    private SurfaceEdge getSharingInputEdge(SurfaceEdge surfaceEdge, CameraInternal cameraInternal) {
        if (getEffect() == null || getEffect().getTransformation() == 2 || getEffect().getOutputOption() == 1) {
            return surfaceEdge;
        }
        this.mEffectNode = new SurfaceProcessorNode(cameraInternal, getEffect().createSurfaceProcessorInternal());
        int rotationAppliedByEffect = getRotationAppliedByEffect();
        Rect cropRectAppliedByEffect = getCropRectAppliedByEffect(surfaceEdge);
        OutConfig of = OutConfig.of(surfaceEdge.getTargets(), surfaceEdge.getFormat(), cropRectAppliedByEffect, TransformUtils.getRotatedSize(cropRectAppliedByEffect, rotationAppliedByEffect), rotationAppliedByEffect, getMirroringAppliedByEffect(), true);
        return (SurfaceEdge) Objects.requireNonNull((SurfaceEdge) this.mEffectNode.transform(SurfaceProcessorNode.In.of(surfaceEdge, Collections.singletonList(of))).get(of));
    }

    private SurfaceProcessorNode getSharingNode(CameraInternal cameraInternal, StreamSpec streamSpec) {
        if (getEffect() == null || getEffect().getOutputOption() != 1) {
            return new SurfaceProcessorNode(cameraInternal, DefaultSurfaceProcessor.Factory.newInstance(streamSpec.getDynamicRange()));
        }
        SurfaceProcessorNode surfaceProcessorNode = new SurfaceProcessorNode(cameraInternal, getEffect().createSurfaceProcessorInternal());
        this.mEffectNode = surfaceProcessorNode;
        return surfaceProcessorNode;
    }

    private DualSurfaceProcessorNode getDualSharingNode(CameraInternal cameraInternal, CameraInternal cameraInternal2, StreamSpec streamSpec, CompositionSettings compositionSettings, CompositionSettings compositionSettings2) {
        return new DualSurfaceProcessorNode(cameraInternal, cameraInternal2, DualSurfaceProcessor.Factory.newInstance(streamSpec.getDynamicRange(), compositionSettings, compositionSettings2));
    }

    private int getRotationAppliedByEffect() {
        if (((CameraEffect) Preconditions.checkNotNull(getEffect())).getTransformation() == 1) {
            return getRelativeRotation((CameraInternal) Preconditions.checkNotNull(getCamera()));
        }
        return 0;
    }

    private boolean getMirroringAppliedByEffect() {
        if (((CameraEffect) Preconditions.checkNotNull(getEffect())).getTransformation() != 1) {
            return false;
        }
        CameraInternal cameraInternal = (CameraInternal) Preconditions.checkNotNull(getCamera());
        if (!cameraInternal.isFrontFacing() || !cameraInternal.getHasTransform()) {
            return false;
        }
        return true;
    }

    private Rect getCropRectAppliedByEffect(SurfaceEdge surfaceEdge) {
        if (((CameraEffect) Preconditions.checkNotNull(getEffect())).getTransformation() == 1) {
            return TransformUtils.sizeToRect(surfaceEdge.getStreamSpec().getResolution());
        }
        return surfaceEdge.getCropRect();
    }

    private void addCameraErrorListener(SessionConfig.Builder builder, String str, String str2, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2) {
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
        }
        SessionConfig.CloseableErrorListener closeableErrorListener2 = new SessionConfig.CloseableErrorListener(new StreamSharing$$ExternalSyntheticLambda3(this, str, str2, useCaseConfig, streamSpec, streamSpec2));
        this.mCloseableErrorListener = closeableErrorListener2;
        builder.setErrorListener(closeableErrorListener2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addCameraErrorListener$1$androidx-camera-core-streamsharing-StreamSharing  reason: not valid java name */
    public /* synthetic */ void m224lambda$addCameraErrorListener$1$androidxcameracorestreamsharingStreamSharing(String str, String str2, UseCaseConfig useCaseConfig, StreamSpec streamSpec, StreamSpec streamSpec2, SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        if (getCamera() != null) {
            clearPipeline();
            updateSessionConfig(createPipelineAndUpdateChildrenSpecs(str, str2, useCaseConfig, streamSpec, streamSpec2));
            notifyReset();
            this.mVirtualCameraAdapter.resetChildren();
        }
    }

    private void clearPipeline() {
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
            this.mCloseableErrorListener = null;
        }
        SurfaceEdge surfaceEdge = this.mCameraEdge;
        if (surfaceEdge != null) {
            surfaceEdge.close();
            this.mCameraEdge = null;
        }
        SurfaceEdge surfaceEdge2 = this.mSecondaryCameraEdge;
        if (surfaceEdge2 != null) {
            surfaceEdge2.close();
            this.mSecondaryCameraEdge = null;
        }
        SurfaceEdge surfaceEdge3 = this.mSharingInputEdge;
        if (surfaceEdge3 != null) {
            surfaceEdge3.close();
            this.mSharingInputEdge = null;
        }
        SurfaceEdge surfaceEdge4 = this.mSecondarySharingInputEdge;
        if (surfaceEdge4 != null) {
            surfaceEdge4.close();
            this.mSecondarySharingInputEdge = null;
        }
        SurfaceProcessorNode surfaceProcessorNode = this.mSharingNode;
        if (surfaceProcessorNode != null) {
            surfaceProcessorNode.release();
            this.mSharingNode = null;
        }
        DualSurfaceProcessorNode dualSurfaceProcessorNode = this.mDualSharingNode;
        if (dualSurfaceProcessorNode != null) {
            dualSurfaceProcessorNode.release();
            this.mDualSharingNode = null;
        }
        SurfaceProcessorNode surfaceProcessorNode2 = this.mEffectNode;
        if (surfaceProcessorNode2 != null) {
            surfaceProcessorNode2.release();
            this.mEffectNode = null;
        }
    }

    private Rect getCropRect(Size size) {
        if (getViewPortCropRect() != null) {
            return getViewPortCropRect();
        }
        return new Rect(0, 0, size.getWidth(), size.getHeight());
    }

    /* access modifiers changed from: package-private */
    public SurfaceEdge getCameraEdge() {
        return this.mCameraEdge;
    }

    /* access modifiers changed from: package-private */
    public SurfaceProcessorNode getSharingNode() {
        return this.mSharingNode;
    }

    /* access modifiers changed from: package-private */
    public VirtualCameraAdapter getVirtualCameraAdapter() {
        return this.mVirtualCameraAdapter;
    }

    public static List<UseCaseConfigFactory.CaptureType> getCaptureTypes(UseCase useCase) {
        ArrayList arrayList = new ArrayList();
        if (isStreamSharing(useCase)) {
            for (UseCase currentConfig : ((StreamSharing) useCase).getChildren()) {
                arrayList.add(currentConfig.getCurrentConfig().getCaptureType());
            }
        } else {
            arrayList.add(useCase.getCurrentConfig().getCaptureType());
        }
        return arrayList;
    }

    public static boolean isStreamSharing(UseCase useCase) {
        return useCase instanceof StreamSharing;
    }

    public SurfaceEdge getSharingInputEdge() {
        return this.mSharingInputEdge;
    }
}
