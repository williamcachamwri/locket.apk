package androidx.camera.video;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.os.SystemClock;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview$$ExternalSyntheticBackport0;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.EncoderProfilesProxy;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.Timebase;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.ThreadConfig;
import androidx.camera.core.internal.compat.quirk.SurfaceProcessingQuirk;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.camera.core.processing.DefaultSurfaceProcessor;
import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.SurfaceProcessorInternal;
import androidx.camera.core.processing.SurfaceProcessorNode;
import androidx.camera.core.processing.util.OutConfig;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.video.StreamInfo;
import androidx.camera.video.VideoOutput;
import androidx.camera.video.impl.VideoCaptureConfig;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.camera.video.internal.compat.quirk.SizeCannotEncodeVideoQuirk;
import androidx.camera.video.internal.config.VideoConfigUtil;
import androidx.camera.video.internal.encoder.SwappedVideoEncoderInfo;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.camera.video.internal.encoder.VideoEncoderInfoImpl;
import androidx.camera.video.internal.utils.DynamicRangeUtil;
import androidx.camera.video.internal.workaround.VideoEncoderInfoWrapper;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class VideoCapture<T extends VideoOutput> extends UseCase {
    private static final Defaults DEFAULT_CONFIG = new Defaults();
    private static final String SURFACE_UPDATE_KEY = "androidx.camera.video.VideoCapture.streamUpdate";
    private static final String TAG = "VideoCapture";
    private SurfaceEdge mCameraEdge;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private Rect mCropRect;
    DeferrableSurface mDeferrableSurface;
    private boolean mHasCompensatingTransformation = false;
    private SurfaceProcessorNode mNode;
    private int mRotationDegrees;
    SessionConfig.Builder mSessionConfigBuilder = new SessionConfig.Builder();
    VideoOutput.SourceState mSourceState = VideoOutput.SourceState.INACTIVE;
    private SourceStreamRequirementObserver mSourceStreamRequirementObserver;
    StreamInfo mStreamInfo = StreamInfo.STREAM_INFO_ANY_INACTIVE;
    private final Observable.Observer<StreamInfo> mStreamInfoObserver = new Observable.Observer<StreamInfo>() {
        public void onNewData(StreamInfo streamInfo) {
            if (streamInfo == null) {
                throw new IllegalArgumentException("StreamInfo can't be null");
            } else if (VideoCapture.this.mSourceState != VideoOutput.SourceState.INACTIVE) {
                Logger.d(VideoCapture.TAG, "Stream info update: old: " + VideoCapture.this.mStreamInfo + " new: " + streamInfo);
                StreamInfo streamInfo2 = VideoCapture.this.mStreamInfo;
                VideoCapture.this.mStreamInfo = streamInfo;
                StreamSpec streamSpec = (StreamSpec) Preconditions.checkNotNull(VideoCapture.this.getAttachedStreamSpec());
                if (VideoCapture.this.isStreamIdChanged(streamInfo2.getId(), streamInfo.getId()) || VideoCapture.this.shouldResetCompensatingTransformation(streamInfo2, streamInfo)) {
                    VideoCapture.this.resetPipeline();
                } else if ((streamInfo2.getId() != -1 && streamInfo.getId() == -1) || (streamInfo2.getId() == -1 && streamInfo.getId() != -1)) {
                    VideoCapture videoCapture = VideoCapture.this;
                    videoCapture.applyStreamInfoAndStreamSpecToSessionConfigBuilder(videoCapture.mSessionConfigBuilder, streamInfo, streamSpec);
                    VideoCapture videoCapture2 = VideoCapture.this;
                    videoCapture2.updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{videoCapture2.mSessionConfigBuilder.build()}));
                    VideoCapture.this.notifyReset();
                } else if (streamInfo2.getStreamState() != streamInfo.getStreamState()) {
                    VideoCapture videoCapture3 = VideoCapture.this;
                    videoCapture3.applyStreamInfoAndStreamSpecToSessionConfigBuilder(videoCapture3.mSessionConfigBuilder, streamInfo, streamSpec);
                    VideoCapture videoCapture4 = VideoCapture.this;
                    videoCapture4.updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{videoCapture4.mSessionConfigBuilder.build()}));
                    VideoCapture.this.notifyUpdated();
                }
            }
        }

        public void onError(Throwable th) {
            Logger.w(VideoCapture.TAG, "Receive onError from StreamState observer", th);
        }
    };
    private SurfaceRequest mSurfaceRequest;
    ListenableFuture<Void> mSurfaceUpdateFuture = null;

    public static <T extends VideoOutput> VideoCapture<T> withOutput(T t) {
        return new Builder((VideoOutput) Preconditions.checkNotNull(t)).build();
    }

    VideoCapture(VideoCaptureConfig<T> videoCaptureConfig) {
        super(videoCaptureConfig);
    }

    public T getOutput() {
        return ((VideoCaptureConfig) getCurrentConfig()).getVideoOutput();
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    public Range<Integer> getTargetFrameRate() {
        return getTargetFrameRateInternal();
    }

    public boolean isVideoStabilizationEnabled() {
        return getCurrentConfig().getVideoStabilizationMode() == 2;
    }

    public void setTargetRotation(int i) {
        if (setTargetRotationInternal(i)) {
            sendTransformationInfoIfReady();
        }
    }

    public int getMirrorMode() {
        int mirrorModeInternal = getMirrorModeInternal();
        if (mirrorModeInternal == -1) {
            return 0;
        }
        return mirrorModeInternal;
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec streamSpec, StreamSpec streamSpec2) {
        Logger.d(TAG, "onSuggestedStreamSpecUpdated: " + streamSpec);
        List<Size> customOrderedResolutions = ((VideoCaptureConfig) getCurrentConfig()).getCustomOrderedResolutions((List<Size>) null);
        if (customOrderedResolutions != null && !customOrderedResolutions.contains(streamSpec.getResolution())) {
            Logger.w(TAG, "suggested resolution " + streamSpec.getResolution() + " is not in custom ordered resolutions " + customOrderedResolutions);
        }
        return streamSpec;
    }

    public DynamicRange getDynamicRange() {
        if (getCurrentConfig().hasDynamicRange()) {
            return getCurrentConfig().getDynamicRange();
        }
        return Defaults.DEFAULT_DYNAMIC_RANGE;
    }

    public void onStateAttached() {
        super.onStateAttached();
        Logger.d(TAG, "VideoCapture#onStateAttached: cameraID = " + getCameraId());
        if (getAttachedStreamSpec() != null && this.mSurfaceRequest == null) {
            StreamSpec streamSpec = (StreamSpec) Preconditions.checkNotNull(getAttachedStreamSpec());
            this.mStreamInfo = (StreamInfo) fetchObservableValue(getOutput().getStreamInfo(), StreamInfo.STREAM_INFO_ANY_INACTIVE);
            SessionConfig.Builder createPipeline = createPipeline((VideoCaptureConfig) getCurrentConfig(), streamSpec);
            this.mSessionConfigBuilder = createPipeline;
            applyStreamInfoAndStreamSpecToSessionConfigBuilder(createPipeline, this.mStreamInfo, streamSpec);
            updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
            notifyActive();
            getOutput().getStreamInfo().addObserver(CameraXExecutors.mainThreadExecutor(), this.mStreamInfoObserver);
            SourceStreamRequirementObserver sourceStreamRequirementObserver = this.mSourceStreamRequirementObserver;
            if (sourceStreamRequirementObserver != null) {
                sourceStreamRequirementObserver.close();
            }
            this.mSourceStreamRequirementObserver = new SourceStreamRequirementObserver(getCameraControl());
            getOutput().isSourceStreamRequired().addObserver(CameraXExecutors.mainThreadExecutor(), this.mSourceStreamRequirementObserver);
            setSourceState(VideoOutput.SourceState.ACTIVE_NON_STREAMING);
        }
    }

    public void setViewPortCropRect(Rect rect) {
        super.setViewPortCropRect(rect);
        sendTransformationInfoIfReady();
    }

    public void onStateDetached() {
        Logger.d(TAG, "VideoCapture#onStateDetached");
        Preconditions.checkState(Threads.isMainThread(), "VideoCapture can only be detached on the main thread.");
        if (this.mSourceStreamRequirementObserver != null) {
            getOutput().isSourceStreamRequired().removeObserver(this.mSourceStreamRequirementObserver);
            this.mSourceStreamRequirementObserver.close();
            this.mSourceStreamRequirementObserver = null;
        }
        setSourceState(VideoOutput.SourceState.INACTIVE);
        getOutput().getStreamInfo().removeObserver(this.mStreamInfoObserver);
        ListenableFuture<Void> listenableFuture = this.mSurfaceUpdateFuture;
        if (listenableFuture != null && listenableFuture.cancel(false)) {
            Logger.d(TAG, "VideoCapture is detached from the camera. Surface update cancelled.");
        }
        clearPipeline();
    }

    /* access modifiers changed from: protected */
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config config) {
        this.mSessionConfigBuilder.addImplementationOptions(config);
        updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
        return ((StreamSpec) Objects.requireNonNull(getAttachedStreamSpec())).toBuilder().setImplementationOptions(config).build();
    }

    public String toString() {
        return "VideoCapture:" + getName();
    }

    public UseCaseConfig<?> getDefaultConfig(boolean z, UseCaseConfigFactory useCaseConfigFactory) {
        Defaults defaults = DEFAULT_CONFIG;
        Config config = useCaseConfigFactory.getConfig(defaults.getConfig().getCaptureType(), 1);
        if (z) {
            config = Config.mergeConfigs(config, defaults.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [androidx.camera.core.impl.UseCaseConfig$Builder, androidx.camera.core.impl.UseCaseConfig$Builder<?, ?, ?>] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.impl.UseCaseConfig<?> onMergeConfig(androidx.camera.core.impl.CameraInfoInternal r1, androidx.camera.core.impl.UseCaseConfig.Builder<?, ?, ?> r2) {
        /*
            r0 = this;
            r0.updateCustomOrderedResolutionsByQuality(r1, r2)
            androidx.camera.core.impl.UseCaseConfig r1 = r2.getUseCaseConfig()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.VideoCapture.onMergeConfig(androidx.camera.core.impl.CameraInfoInternal, androidx.camera.core.impl.UseCaseConfig$Builder):androidx.camera.core.impl.UseCaseConfig");
    }

    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return Builder.fromConfig(config);
    }

    private void sendTransformationInfoIfReady() {
        CameraInternal camera = getCamera();
        SurfaceEdge surfaceEdge = this.mCameraEdge;
        if (camera != null && surfaceEdge != null) {
            int compensatedRotation = getCompensatedRotation(camera);
            this.mRotationDegrees = compensatedRotation;
            surfaceEdge.updateTransformation(compensatedRotation, getAppTargetRotation());
        }
    }

    private Rect adjustCropRectWithInProgressTransformation(Rect rect, int i) {
        return shouldCompensateTransformation() ? TransformUtils.sizeToRect(TransformUtils.getRotatedSize(((SurfaceRequest.TransformationInfo) Preconditions.checkNotNull(this.mStreamInfo.getInProgressTransformationInfo())).getCropRect(), i)) : rect;
    }

    private int getCompensatedRotation(CameraInternal cameraInternal) {
        boolean isMirroringRequired = isMirroringRequired(cameraInternal);
        int relativeRotation = getRelativeRotation(cameraInternal, isMirroringRequired);
        if (!shouldCompensateTransformation()) {
            return relativeRotation;
        }
        SurfaceRequest.TransformationInfo transformationInfo = (SurfaceRequest.TransformationInfo) Objects.requireNonNull(this.mStreamInfo.getInProgressTransformationInfo());
        int rotationDegrees = transformationInfo.getRotationDegrees();
        if (isMirroringRequired != transformationInfo.isMirroring()) {
            rotationDegrees = -rotationDegrees;
        }
        return TransformUtils.within360(relativeRotation - rotationDegrees);
    }

    private Size adjustResolutionWithInProgressTransformation(Size size, Rect rect, Rect rect2) {
        if (!shouldCompensateTransformation() || rect2.equals(rect)) {
            return size;
        }
        float height = ((float) rect2.height()) / ((float) rect.height());
        return new Size((int) Math.ceil((double) (((float) size.getWidth()) * height)), (int) Math.ceil((double) (((float) size.getHeight()) * height)));
    }

    /* access modifiers changed from: package-private */
    public Rect getCropRect() {
        return this.mCropRect;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    private Rect calculateCropRect(Size size, VideoEncoderInfo videoEncoderInfo) {
        Rect rect;
        if (getViewPortCropRect() != null) {
            rect = getViewPortCropRect();
        } else {
            rect = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
        return (videoEncoderInfo == null || videoEncoderInfo.isSizeSupportedAllowSwapping(rect.width(), rect.height())) ? rect : adjustCropRectToValidSize(rect, size, videoEncoderInfo);
    }

    private SessionConfig.Builder createPipeline(VideoCaptureConfig<T> videoCaptureConfig, StreamSpec streamSpec) {
        VideoCaptureConfig<T> videoCaptureConfig2 = videoCaptureConfig;
        Threads.checkMainThread();
        CameraInternal cameraInternal = (CameraInternal) Preconditions.checkNotNull(getCamera());
        Size resolution = streamSpec.getResolution();
        VideoCapture$$ExternalSyntheticLambda4 videoCapture$$ExternalSyntheticLambda4 = new VideoCapture$$ExternalSyntheticLambda4(this);
        Range<Integer> resolveFrameRate = resolveFrameRate(streamSpec);
        VideoCapabilities videoCapabilities = getVideoCapabilities(cameraInternal.getCameraInfo());
        DynamicRange dynamicRange = streamSpec.getDynamicRange();
        VideoValidatedEncoderProfilesProxy findNearestHigherSupportedEncoderProfilesFor = videoCapabilities.findNearestHigherSupportedEncoderProfilesFor(resolution, dynamicRange);
        VideoEncoderInfo resolveVideoEncoderInfo = resolveVideoEncoderInfo(videoCaptureConfig.getVideoEncoderInfoFinder(), findNearestHigherSupportedEncoderProfilesFor, (MediaSpec) Objects.requireNonNull(getMediaSpec()), resolution, dynamicRange, resolveFrameRate);
        this.mRotationDegrees = getCompensatedRotation(cameraInternal);
        Rect calculateCropRect = calculateCropRect(resolution, resolveVideoEncoderInfo);
        Rect adjustCropRectWithInProgressTransformation = adjustCropRectWithInProgressTransformation(calculateCropRect, this.mRotationDegrees);
        this.mCropRect = adjustCropRectWithInProgressTransformation;
        Size adjustResolutionWithInProgressTransformation = adjustResolutionWithInProgressTransformation(resolution, calculateCropRect, adjustCropRectWithInProgressTransformation);
        boolean z = true;
        if (shouldCompensateTransformation()) {
            this.mHasCompensatingTransformation = true;
        }
        Rect rect = this.mCropRect;
        Rect adjustCropRectByQuirk = adjustCropRectByQuirk(rect, this.mRotationDegrees, isCreateNodeNeeded(cameraInternal, videoCaptureConfig2, rect, resolution), resolveVideoEncoderInfo);
        this.mCropRect = adjustCropRectByQuirk;
        SurfaceProcessorNode createNodeIfNeeded = createNodeIfNeeded(cameraInternal, videoCaptureConfig, adjustCropRectByQuirk, resolution, dynamicRange);
        this.mNode = createNodeIfNeeded;
        Timebase resolveTimebase = resolveTimebase(cameraInternal, createNodeIfNeeded);
        Logger.d(TAG, "camera timebase = " + cameraInternal.getCameraInfoInternal().getTimebase() + ", processing timebase = " + resolveTimebase);
        StreamSpec build = streamSpec.toBuilder().setResolution(adjustResolutionWithInProgressTransformation).setExpectedFrameRateRange(resolveFrameRate).build();
        if (this.mCameraEdge != null) {
            z = false;
        }
        Preconditions.checkState(z);
        Matrix sensorToBufferTransformMatrix = getSensorToBufferTransformMatrix();
        boolean hasTransform = cameraInternal.getHasTransform();
        Rect rect2 = this.mCropRect;
        SurfaceEdge surfaceEdge = new SurfaceEdge(2, 34, build, sensorToBufferTransformMatrix, hasTransform, rect2, this.mRotationDegrees, getAppTargetRotation(), shouldMirror(cameraInternal));
        this.mCameraEdge = surfaceEdge;
        surfaceEdge.addOnInvalidatedListener(videoCapture$$ExternalSyntheticLambda4);
        if (this.mNode != null) {
            OutConfig of = OutConfig.of(this.mCameraEdge);
            SurfaceEdge surfaceEdge2 = (SurfaceEdge) Objects.requireNonNull((SurfaceEdge) this.mNode.transform(SurfaceProcessorNode.In.of(this.mCameraEdge, Collections.singletonList(of))).get(of));
            surfaceEdge2.addOnInvalidatedListener(new VideoCapture$$ExternalSyntheticLambda5(this, surfaceEdge2, cameraInternal, videoCaptureConfig, resolveTimebase));
            this.mSurfaceRequest = surfaceEdge2.createSurfaceRequest(cameraInternal);
            DeferrableSurface deferrableSurface = this.mCameraEdge.getDeferrableSurface();
            this.mDeferrableSurface = deferrableSurface;
            deferrableSurface.getTerminationFuture().addListener(new VideoCapture$$ExternalSyntheticLambda6(this, deferrableSurface), CameraXExecutors.mainThreadExecutor());
        } else {
            SurfaceRequest createSurfaceRequest = this.mCameraEdge.createSurfaceRequest(cameraInternal);
            this.mSurfaceRequest = createSurfaceRequest;
            this.mDeferrableSurface = createSurfaceRequest.getDeferrableSurface();
        }
        videoCaptureConfig.getVideoOutput().onSurfaceRequested(this.mSurfaceRequest, resolveTimebase);
        sendTransformationInfoIfReady();
        this.mDeferrableSurface.setContainerClass(MediaCodec.class);
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(videoCaptureConfig2, streamSpec.getResolution());
        applyExpectedFrameRateRange(createFrom, streamSpec);
        createFrom.setVideoStabilization(videoCaptureConfig.getVideoStabilizationMode());
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
        }
        SessionConfig.CloseableErrorListener closeableErrorListener2 = new SessionConfig.CloseableErrorListener(new VideoCapture$$ExternalSyntheticLambda7(this));
        this.mCloseableErrorListener = closeableErrorListener2;
        createFrom.setErrorListener(closeableErrorListener2);
        if (streamSpec.getImplementationOptions() != null) {
            createFrom.addImplementationOptions(streamSpec.getImplementationOptions());
        }
        return createFrom;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createPipeline$2$androidx-camera-video-VideoCapture  reason: not valid java name */
    public /* synthetic */ void m256lambda$createPipeline$2$androidxcameravideoVideoCapture(DeferrableSurface deferrableSurface) {
        if (deferrableSurface == this.mDeferrableSurface) {
            clearPipeline();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createPipeline$3$androidx-camera-video-VideoCapture  reason: not valid java name */
    public /* synthetic */ void m257lambda$createPipeline$3$androidxcameravideoVideoCapture(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        resetPipeline();
    }

    /* access modifiers changed from: private */
    /* renamed from: onAppEdgeInvalidated */
    public void m255lambda$createPipeline$1$androidxcameravideoVideoCapture(SurfaceEdge surfaceEdge, CameraInternal cameraInternal, VideoCaptureConfig<T> videoCaptureConfig, Timebase timebase) {
        if (cameraInternal == getCamera()) {
            this.mSurfaceRequest = surfaceEdge.createSurfaceRequest(cameraInternal);
            videoCaptureConfig.getVideoOutput().onSurfaceRequested(this.mSurfaceRequest, timebase);
            sendTransformationInfoIfReady();
        }
    }

    private void clearPipeline() {
        Threads.checkMainThread();
        SessionConfig.CloseableErrorListener closeableErrorListener = this.mCloseableErrorListener;
        if (closeableErrorListener != null) {
            closeableErrorListener.close();
            this.mCloseableErrorListener = null;
        }
        DeferrableSurface deferrableSurface = this.mDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
            this.mDeferrableSurface = null;
        }
        SurfaceProcessorNode surfaceProcessorNode = this.mNode;
        if (surfaceProcessorNode != null) {
            surfaceProcessorNode.release();
            this.mNode = null;
        }
        SurfaceEdge surfaceEdge = this.mCameraEdge;
        if (surfaceEdge != null) {
            surfaceEdge.close();
            this.mCameraEdge = null;
        }
        this.mCropRect = null;
        this.mSurfaceRequest = null;
        this.mStreamInfo = StreamInfo.STREAM_INFO_ANY_INACTIVE;
        this.mRotationDegrees = 0;
        this.mHasCompensatingTransformation = false;
    }

    /* access modifiers changed from: package-private */
    public void resetPipeline() {
        if (getCamera() != null) {
            clearPipeline();
            SessionConfig.Builder createPipeline = createPipeline((VideoCaptureConfig) getCurrentConfig(), (StreamSpec) Preconditions.checkNotNull(getAttachedStreamSpec()));
            this.mSessionConfigBuilder = createPipeline;
            applyStreamInfoAndStreamSpecToSessionConfigBuilder(createPipeline, this.mStreamInfo, getAttachedStreamSpec());
            updateSessionConfig(Preview$$ExternalSyntheticBackport0.m(new Object[]{this.mSessionConfigBuilder.build()}));
            notifyReset();
        }
    }

    /* access modifiers changed from: package-private */
    public SurfaceEdge getCameraEdge() {
        return this.mCameraEdge;
    }

    public static final class Defaults implements ConfigProvider<VideoCaptureConfig<?>> {
        private static final VideoCaptureConfig<?> DEFAULT_CONFIG;
        static final DynamicRange DEFAULT_DYNAMIC_RANGE;
        static final Range<Integer> DEFAULT_FPS_RANGE = new Range<>(30, 30);
        private static final int DEFAULT_SURFACE_OCCUPANCY_PRIORITY = 5;
        private static final Function<VideoEncoderConfig, VideoEncoderInfo> DEFAULT_VIDEO_ENCODER_INFO_FINDER;
        private static final VideoOutput DEFAULT_VIDEO_OUTPUT;

        static {
            VideoCapture$Defaults$$ExternalSyntheticLambda0 videoCapture$Defaults$$ExternalSyntheticLambda0 = new VideoCapture$Defaults$$ExternalSyntheticLambda0();
            DEFAULT_VIDEO_OUTPUT = videoCapture$Defaults$$ExternalSyntheticLambda0;
            Function<VideoEncoderConfig, VideoEncoderInfo> function = VideoEncoderInfoImpl.FINDER;
            DEFAULT_VIDEO_ENCODER_INFO_FINDER = function;
            DynamicRange dynamicRange = DynamicRange.SDR;
            DEFAULT_DYNAMIC_RANGE = dynamicRange;
            DEFAULT_CONFIG = new Builder(videoCapture$Defaults$$ExternalSyntheticLambda0).setSurfaceOccupancyPriority(5).setVideoEncoderInfoFinder(function).setDynamicRange(dynamicRange).getUseCaseConfig();
        }

        public VideoCaptureConfig<?> getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    private MediaSpec getMediaSpec() {
        return (MediaSpec) fetchObservableValue(getOutput().getMediaSpec(), (Object) null);
    }

    private VideoCapabilities getVideoCapabilities(CameraInfo cameraInfo) {
        return getOutput().getMediaCapabilities(cameraInfo);
    }

    static class SourceStreamRequirementObserver implements Observable.Observer<Boolean> {
        private CameraControlInternal mCameraControl;
        private boolean mIsSourceStreamRequired = false;

        SourceStreamRequirementObserver(CameraControlInternal cameraControlInternal) {
            this.mCameraControl = cameraControlInternal;
        }

        public void onNewData(Boolean bool) {
            Preconditions.checkState(Threads.isMainThread(), "SourceStreamRequirementObserver can be updated from main thread only");
            updateVideoUsageInCamera(Boolean.TRUE.equals(bool));
        }

        public void onError(Throwable th) {
            Logger.w(VideoCapture.TAG, "SourceStreamRequirementObserver#onError", th);
        }

        private void updateVideoUsageInCamera(boolean z) {
            if (this.mIsSourceStreamRequired != z) {
                this.mIsSourceStreamRequired = z;
                CameraControlInternal cameraControlInternal = this.mCameraControl;
                if (cameraControlInternal == null) {
                    Logger.d(VideoCapture.TAG, "SourceStreamRequirementObserver#isSourceStreamRequired: Received new data despite being closed already");
                } else if (z) {
                    cameraControlInternal.incrementVideoUsage();
                } else {
                    cameraControlInternal.decrementVideoUsage();
                }
            }
        }

        public void close() {
            Preconditions.checkState(Threads.isMainThread(), "SourceStreamRequirementObserver can be closed from main thread only");
            Logger.d(VideoCapture.TAG, "SourceStreamRequirementObserver#close: mIsSourceStreamRequired = " + this.mIsSourceStreamRequired);
            if (this.mCameraControl == null) {
                Logger.d(VideoCapture.TAG, "SourceStreamRequirementObserver#close: Already closed!");
                return;
            }
            updateVideoUsageInCamera(false);
            this.mCameraControl = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void applyStreamInfoAndStreamSpecToSessionConfigBuilder(SessionConfig.Builder builder, StreamInfo streamInfo, StreamSpec streamSpec) {
        DeferrableSurface deferrableSurface;
        boolean z = true;
        boolean z2 = streamInfo.getId() == -1;
        if (streamInfo.getStreamState() != StreamInfo.StreamState.ACTIVE) {
            z = false;
        }
        if (!z2 || !z) {
            builder.clearSurfaces();
            DynamicRange dynamicRange = streamSpec.getDynamicRange();
            if (!z2 && (deferrableSurface = this.mDeferrableSurface) != null) {
                if (z) {
                    builder.addSurface(deferrableSurface, dynamicRange, (String) null, -1);
                } else {
                    builder.addNonRepeatingSurface(deferrableSurface, dynamicRange);
                }
            }
            setupSurfaceUpdateNotifier(builder, z);
            return;
        }
        throw new IllegalStateException("Unexpected stream state, stream is error but active");
    }

    private boolean isCreateNodeNeeded(CameraInternal cameraInternal, VideoCaptureConfig<?> videoCaptureConfig, Rect rect, Size size) {
        return getEffect() != null || shouldEnableSurfaceProcessingByConfig(cameraInternal, videoCaptureConfig) || shouldEnableSurfaceProcessingByQuirk(cameraInternal) || shouldCrop(rect, size) || shouldMirror(cameraInternal) || shouldCompensateTransformation();
    }

    private SurfaceProcessorNode createNodeIfNeeded(CameraInternal cameraInternal, VideoCaptureConfig<T> videoCaptureConfig, Rect rect, Size size, DynamicRange dynamicRange) {
        SurfaceProcessorInternal surfaceProcessorInternal;
        if (!isCreateNodeNeeded(cameraInternal, videoCaptureConfig, rect, size)) {
            return null;
        }
        Logger.d(TAG, "Surface processing is enabled.");
        CameraInternal cameraInternal2 = (CameraInternal) Objects.requireNonNull(getCamera());
        if (getEffect() != null) {
            surfaceProcessorInternal = getEffect().createSurfaceProcessorInternal();
        } else {
            surfaceProcessorInternal = DefaultSurfaceProcessor.Factory.newInstance(dynamicRange);
        }
        return new SurfaceProcessorNode(cameraInternal2, surfaceProcessorInternal);
    }

    /* access modifiers changed from: package-private */
    public SurfaceProcessorNode getNode() {
        return this.mNode;
    }

    private static Rect adjustCropRectByQuirk(Rect rect, int i, boolean z, VideoEncoderInfo videoEncoderInfo) {
        SizeCannotEncodeVideoQuirk sizeCannotEncodeVideoQuirk = (SizeCannotEncodeVideoQuirk) DeviceQuirks.get(SizeCannotEncodeVideoQuirk.class);
        if (sizeCannotEncodeVideoQuirk == null) {
            return rect;
        }
        if (!z) {
            i = 0;
        }
        return sizeCannotEncodeVideoQuirk.adjustCropRectForProblematicEncodeSize(rect, i, videoEncoderInfo);
    }

    private static Rect adjustCropRectToValidSize(Rect rect, Size size, VideoEncoderInfo videoEncoderInfo) {
        Logger.d(TAG, String.format("Adjust cropRect %s by width/height alignment %d/%d and supported widths %s / supported heights %s", new Object[]{TransformUtils.rectToString(rect), Integer.valueOf(videoEncoderInfo.getWidthAlignment()), Integer.valueOf(videoEncoderInfo.getHeightAlignment()), videoEncoderInfo.getSupportedWidths(), videoEncoderInfo.getSupportedHeights()}));
        boolean z = true;
        if ((!videoEncoderInfo.getSupportedWidths().contains(Integer.valueOf(rect.width())) || !videoEncoderInfo.getSupportedHeights().contains(Integer.valueOf(rect.height()))) && videoEncoderInfo.canSwapWidthHeight() && videoEncoderInfo.getSupportedHeights().contains(Integer.valueOf(rect.width())) && videoEncoderInfo.getSupportedWidths().contains(Integer.valueOf(rect.height()))) {
            videoEncoderInfo = new SwappedVideoEncoderInfo(videoEncoderInfo);
        }
        int widthAlignment = videoEncoderInfo.getWidthAlignment();
        int heightAlignment = videoEncoderInfo.getHeightAlignment();
        Range<Integer> supportedWidths = videoEncoderInfo.getSupportedWidths();
        Range<Integer> supportedHeights = videoEncoderInfo.getSupportedHeights();
        int alignDown = alignDown(rect.width(), widthAlignment, supportedWidths);
        int alignUp = alignUp(rect.width(), widthAlignment, supportedWidths);
        int alignDown2 = alignDown(rect.height(), heightAlignment, supportedHeights);
        int alignUp2 = alignUp(rect.height(), heightAlignment, supportedHeights);
        HashSet hashSet = new HashSet();
        addBySupportedSize(hashSet, alignDown, alignDown2, size, videoEncoderInfo);
        addBySupportedSize(hashSet, alignDown, alignUp2, size, videoEncoderInfo);
        addBySupportedSize(hashSet, alignUp, alignDown2, size, videoEncoderInfo);
        addBySupportedSize(hashSet, alignUp, alignUp2, size, videoEncoderInfo);
        if (hashSet.isEmpty()) {
            Logger.w(TAG, "Can't find valid cropped size");
            return rect;
        }
        ArrayList arrayList = new ArrayList(hashSet);
        Logger.d(TAG, "candidatesList = " + arrayList);
        Collections.sort(arrayList, new VideoCapture$$ExternalSyntheticLambda2(rect));
        Logger.d(TAG, "sorted candidatesList = " + arrayList);
        Size size2 = (Size) arrayList.get(0);
        int width = size2.getWidth();
        int height = size2.getHeight();
        if (width == rect.width() && height == rect.height()) {
            Logger.d(TAG, "No need to adjust cropRect because crop size is valid.");
            return rect;
        }
        if (width % 2 != 0 || height % 2 != 0 || width > size.getWidth() || height > size.getHeight()) {
            z = false;
        }
        Preconditions.checkState(z);
        Rect rect2 = new Rect(rect);
        if (width != rect.width()) {
            rect2.left = Math.max(0, rect.centerX() - (width / 2));
            rect2.right = rect2.left + width;
            if (rect2.right > size.getWidth()) {
                rect2.right = size.getWidth();
                rect2.left = rect2.right - width;
            }
        }
        if (height != rect.height()) {
            rect2.top = Math.max(0, rect.centerY() - (height / 2));
            rect2.bottom = rect2.top + height;
            if (rect2.bottom > size.getHeight()) {
                rect2.bottom = size.getHeight();
                rect2.top = rect2.bottom - height;
            }
        }
        Logger.d(TAG, String.format("Adjust cropRect from %s to %s", new Object[]{TransformUtils.rectToString(rect), TransformUtils.rectToString(rect2)}));
        return rect2;
    }

    static /* synthetic */ int lambda$adjustCropRectToValidSize$4(Rect rect, Size size, Size size2) {
        return (Math.abs(size.getWidth() - rect.width()) + Math.abs(size.getHeight() - rect.height())) - (Math.abs(size2.getWidth() - rect.width()) + Math.abs(size2.getHeight() - rect.height()));
    }

    private static void addBySupportedSize(Set<Size> set, int i, int i2, Size size, VideoEncoderInfo videoEncoderInfo) {
        if (i <= size.getWidth() && i2 <= size.getHeight()) {
            try {
                set.add(new Size(i, videoEncoderInfo.getSupportedHeightsFor(i).clamp(Integer.valueOf(i2)).intValue()));
            } catch (IllegalArgumentException e) {
                Logger.w(TAG, "No supportedHeights for width: " + i, e);
            }
            try {
                set.add(new Size(videoEncoderInfo.getSupportedWidthsFor(i2).clamp(Integer.valueOf(i)).intValue(), i2));
            } catch (IllegalArgumentException e2) {
                Logger.w(TAG, "No supportedWidths for height: " + i2, e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isStreamIdChanged(int i, int i2) {
        return !StreamInfo.NON_SURFACE_STREAM_ID.contains(Integer.valueOf(i)) && !StreamInfo.NON_SURFACE_STREAM_ID.contains(Integer.valueOf(i2)) && i != i2;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldResetCompensatingTransformation(StreamInfo streamInfo, StreamInfo streamInfo2) {
        return this.mHasCompensatingTransformation && streamInfo.getInProgressTransformationInfo() != null && streamInfo2.getInProgressTransformationInfo() == null;
    }

    private boolean shouldMirror(CameraInternal cameraInternal) {
        return cameraInternal.getHasTransform() && isMirroringRequired(cameraInternal);
    }

    private boolean shouldCompensateTransformation() {
        return this.mStreamInfo.getInProgressTransformationInfo() != null;
    }

    private static boolean shouldCrop(Rect rect, Size size) {
        return (size.getWidth() == rect.width() && size.getHeight() == rect.height()) ? false : true;
    }

    private static <T extends VideoOutput> boolean shouldEnableSurfaceProcessingByConfig(CameraInternal cameraInternal, VideoCaptureConfig<T> videoCaptureConfig) {
        return cameraInternal.getHasTransform() && videoCaptureConfig.isSurfaceProcessingForceEnabled();
    }

    private static boolean shouldEnableSurfaceProcessingByQuirk(CameraInternal cameraInternal) {
        return cameraInternal.getHasTransform() && (SurfaceProcessingQuirk.workaroundBySurfaceProcessing(DeviceQuirks.getAll()) || SurfaceProcessingQuirk.workaroundBySurfaceProcessing(cameraInternal.getCameraInfoInternal().getCameraQuirks()));
    }

    private static int alignDown(int i, int i2, Range<Integer> range) {
        return align(true, i, i2, range);
    }

    private static int alignUp(int i, int i2, Range<Integer> range) {
        return align(false, i, i2, range);
    }

    private static int align(boolean z, int i, int i2, Range<Integer> range) {
        int i3 = i % i2;
        if (i3 != 0) {
            i = z ? i - i3 : i + (i2 - i3);
        }
        return range.clamp(Integer.valueOf(i)).intValue();
    }

    private static Timebase resolveTimebase(CameraInternal cameraInternal, SurfaceProcessorNode surfaceProcessorNode) {
        if (surfaceProcessorNode != null || !cameraInternal.getHasTransform()) {
            return cameraInternal.getCameraInfoInternal().getTimebase();
        }
        return Timebase.UPTIME;
    }

    private static Range<Integer> resolveFrameRate(StreamSpec streamSpec) {
        Range<Integer> expectedFrameRateRange = streamSpec.getExpectedFrameRateRange();
        return Objects.equals(expectedFrameRateRange, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) ? Defaults.DEFAULT_FPS_RANGE : expectedFrameRateRange;
    }

    private static VideoEncoderInfo resolveVideoEncoderInfo(Function<VideoEncoderConfig, VideoEncoderInfo> function, VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy, MediaSpec mediaSpec, Size size, DynamicRange dynamicRange, Range<Integer> range) {
        VideoEncoderInfo apply = function.apply(VideoConfigUtil.resolveVideoEncoderConfig(VideoConfigUtil.resolveVideoMimeInfo(mediaSpec, dynamicRange, videoValidatedEncoderProfilesProxy), Timebase.UPTIME, mediaSpec.getVideoSpec(), size, dynamicRange, range));
        Size size2 = null;
        if (apply == null) {
            Logger.w(TAG, "Can't find videoEncoderInfo");
            return null;
        }
        if (videoValidatedEncoderProfilesProxy != null) {
            size2 = new Size(videoValidatedEncoderProfilesProxy.getDefaultVideoProfile().getWidth(), videoValidatedEncoderProfilesProxy.getDefaultVideoProfile().getHeight());
        }
        return VideoEncoderInfoWrapper.from(apply, size2);
    }

    private void setupSurfaceUpdateNotifier(SessionConfig.Builder builder, final boolean z) {
        ListenableFuture<Void> listenableFuture = this.mSurfaceUpdateFuture;
        if (listenableFuture != null && listenableFuture.cancel(false)) {
            Logger.d(TAG, "A newer surface update is requested. Previous surface update cancelled.");
        }
        final ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new VideoCapture$$ExternalSyntheticLambda1(this, builder));
        this.mSurfaceUpdateFuture = future;
        Futures.addCallback(future, new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
                VideoOutput.SourceState sourceState;
                if (future == VideoCapture.this.mSurfaceUpdateFuture && VideoCapture.this.mSourceState != VideoOutput.SourceState.INACTIVE) {
                    VideoCapture videoCapture = VideoCapture.this;
                    if (z) {
                        sourceState = VideoOutput.SourceState.ACTIVE_STREAMING;
                    } else {
                        sourceState = VideoOutput.SourceState.ACTIVE_NON_STREAMING;
                    }
                    videoCapture.setSourceState(sourceState);
                }
            }

            public void onFailure(Throwable th) {
                if (!(th instanceof CancellationException)) {
                    Logger.e(VideoCapture.TAG, "Surface update completed with unexpected exception", th);
                }
            }
        }, CameraXExecutors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setupSurfaceUpdateNotifier$6$androidx-camera-video-VideoCapture  reason: not valid java name */
    public /* synthetic */ Object m258lambda$setupSurfaceUpdateNotifier$6$androidxcameravideoVideoCapture(final SessionConfig.Builder builder, final CallbackToFutureAdapter.Completer completer) throws Exception {
        builder.addTag(SURFACE_UPDATE_KEY, Integer.valueOf(completer.hashCode()));
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AnonymousClass2 r2 = new CameraCaptureCallback() {
            private boolean mIsFirstCaptureResult = true;

            public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                Object tag;
                super.onCaptureCompleted(i, cameraCaptureResult);
                if (this.mIsFirstCaptureResult) {
                    this.mIsFirstCaptureResult = false;
                    Logger.d(VideoCapture.TAG, "cameraCaptureResult timestampNs = " + cameraCaptureResult.getTimestamp() + ", current system uptimeMs = " + SystemClock.uptimeMillis() + ", current system realtimeMs = " + SystemClock.elapsedRealtime());
                }
                if (!atomicBoolean.get() && (tag = cameraCaptureResult.getTagBundle().getTag(VideoCapture.SURFACE_UPDATE_KEY)) != null && ((Integer) tag).intValue() == completer.hashCode() && completer.set(null) && !atomicBoolean.getAndSet(true)) {
                    CameraXExecutors.mainThreadExecutor().execute(new VideoCapture$2$$ExternalSyntheticLambda0(this, builder));
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onCaptureCompleted$0$androidx-camera-video-VideoCapture$2  reason: not valid java name */
            public /* synthetic */ void m259lambda$onCaptureCompleted$0$androidxcameravideoVideoCapture$2(SessionConfig.Builder builder) {
                builder.removeCameraCaptureCallback(this);
            }
        };
        completer.addCancellationListener(new VideoCapture$$ExternalSyntheticLambda3(atomicBoolean, builder, r2), CameraXExecutors.directExecutor());
        builder.addRepeatingCameraCaptureCallback(r2);
        return String.format("%s[0x%x]", new Object[]{SURFACE_UPDATE_KEY, Integer.valueOf(completer.hashCode())});
    }

    static /* synthetic */ void lambda$setupSurfaceUpdateNotifier$5(AtomicBoolean atomicBoolean, SessionConfig.Builder builder, CameraCaptureCallback cameraCaptureCallback) {
        Preconditions.checkState(Threads.isMainThread(), "Surface update cancellation should only occur on main thread.");
        atomicBoolean.set(true);
        builder.removeCameraCaptureCallback(cameraCaptureCallback);
    }

    private void updateCustomOrderedResolutionsByQuality(CameraInfoInternal cameraInfoInternal, UseCaseConfig.Builder<?, ?, ?> builder) throws IllegalArgumentException {
        MediaSpec mediaSpec = getMediaSpec();
        Preconditions.checkArgument(mediaSpec != null, "Unable to update target resolution by null MediaSpec.");
        DynamicRange dynamicRange = getDynamicRange();
        VideoCapabilities videoCapabilities = getVideoCapabilities(cameraInfoInternal);
        List<Quality> supportedQualities = videoCapabilities.getSupportedQualities(dynamicRange);
        if (supportedQualities.isEmpty()) {
            Logger.w(TAG, "Can't find any supported quality on the device.");
            return;
        }
        VideoSpec videoSpec = mediaSpec.getVideoSpec();
        QualitySelector qualitySelector = videoSpec.getQualitySelector();
        List<Quality> prioritizedQualities = qualitySelector.getPrioritizedQualities(supportedQualities);
        Logger.d(TAG, "Found selectedQualities " + prioritizedQualities + " by " + qualitySelector);
        if (!prioritizedQualities.isEmpty()) {
            int aspectRatio = videoSpec.getAspectRatio();
            Map<Quality, Size> qualityToResolutionMap = QualitySelector.getQualityToResolutionMap(videoCapabilities, dynamicRange);
            QualityRatioToResolutionsTable qualityRatioToResolutionsTable = new QualityRatioToResolutionsTable(cameraInfoInternal.getSupportedResolutions(getImageFormat()), qualityToResolutionMap);
            ArrayList arrayList = new ArrayList();
            for (Quality resolutions : prioritizedQualities) {
                arrayList.addAll(qualityRatioToResolutionsTable.getResolutions(resolutions, aspectRatio));
            }
            List<Size> filterOutEncoderUnsupportedResolutions = filterOutEncoderUnsupportedResolutions((VideoCaptureConfig) builder.getUseCaseConfig(), mediaSpec, dynamicRange, videoCapabilities, arrayList, qualityToResolutionMap);
            Logger.d(TAG, "Set custom ordered resolutions = " + filterOutEncoderUnsupportedResolutions);
            builder.getMutableConfig().insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, filterOutEncoderUnsupportedResolutions);
            return;
        }
        throw new IllegalArgumentException("Unable to find supported quality by QualitySelector");
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [androidx.camera.video.impl.VideoCaptureConfig<?>, androidx.camera.video.impl.VideoCaptureConfig] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<android.util.Size> filterOutEncoderUnsupportedResolutions(androidx.camera.video.impl.VideoCaptureConfig<?> r8, androidx.camera.video.MediaSpec r9, androidx.camera.core.DynamicRange r10, androidx.camera.video.VideoCapabilities r11, java.util.List<android.util.Size> r12, java.util.Map<androidx.camera.video.Quality, android.util.Size> r13) {
        /*
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L_0x0007
            return r12
        L_0x0007:
            java.util.Iterator r0 = r12.iterator()
        L_0x000b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0051
            java.lang.Object r1 = r0.next()
            android.util.Size r1 = (android.util.Size) r1
            boolean r2 = r13.containsValue(r1)
            if (r2 == 0) goto L_0x001e
            goto L_0x000b
        L_0x001e:
            androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy r3 = r11.findNearestHigherSupportedEncoderProfilesFor(r1, r10)
            if (r3 != 0) goto L_0x0025
            goto L_0x000b
        L_0x0025:
            androidx.arch.core.util.Function r2 = r8.getVideoEncoderInfoFinder()
            android.util.Range<java.lang.Integer> r4 = androidx.camera.video.VideoCapture.Defaults.DEFAULT_FPS_RANGE
            android.util.Range r4 = r8.getTargetFrameRate(r4)
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
            r7 = r4
            android.util.Range r7 = (android.util.Range) r7
            r4 = r10
            r5 = r9
            r6 = r1
            androidx.camera.video.internal.encoder.VideoEncoderInfo r2 = findLargestSupportedSizeVideoEncoderInfo(r2, r3, r4, r5, r6, r7)
            if (r2 == 0) goto L_0x000b
            int r3 = r1.getWidth()
            int r1 = r1.getHeight()
            boolean r1 = r2.isSizeSupportedAllowSwapping(r3, r1)
            if (r1 != 0) goto L_0x000b
            r0.remove()
            goto L_0x000b
        L_0x0051:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.VideoCapture.filterOutEncoderUnsupportedResolutions(androidx.camera.video.impl.VideoCaptureConfig, androidx.camera.video.MediaSpec, androidx.camera.core.DynamicRange, androidx.camera.video.VideoCapabilities, java.util.List, java.util.Map):java.util.List");
    }

    private static VideoEncoderInfo findLargestSupportedSizeVideoEncoderInfo(Function<VideoEncoderConfig, VideoEncoderInfo> function, VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy, DynamicRange dynamicRange, MediaSpec mediaSpec, Size size, Range<Integer> range) {
        VideoEncoderInfo resolveVideoEncoderInfo;
        int area;
        if (dynamicRange.isFullySpecified()) {
            return resolveVideoEncoderInfo(function, videoValidatedEncoderProfilesProxy, mediaSpec, size, dynamicRange, range);
        }
        VideoEncoderInfo videoEncoderInfo = null;
        int i = Integer.MIN_VALUE;
        for (EncoderProfilesProxy.VideoProfileProxy next : videoValidatedEncoderProfilesProxy.getVideoProfiles()) {
            DynamicRange dynamicRange2 = dynamicRange;
            if (DynamicRangeUtil.isHdrSettingsMatched(next, dynamicRange) && (resolveVideoEncoderInfo = resolveVideoEncoderInfo(function, videoValidatedEncoderProfilesProxy, mediaSpec, size, new DynamicRange(DynamicRangeUtil.videoProfileHdrFormatsToDynamicRangeEncoding(next.getHdrFormat()), DynamicRangeUtil.videoProfileBitDepthToDynamicRangeBitDepth(next.getBitDepth())), range)) != null && (area = SizeUtil.getArea(resolveVideoEncoderInfo.getSupportedWidths().getUpper().intValue(), resolveVideoEncoderInfo.getSupportedHeights().getUpper().intValue())) > i) {
                videoEncoderInfo = resolveVideoEncoderInfo;
                i = area;
            }
        }
        return videoEncoderInfo;
    }

    private static <T> T fetchObservableValue(Observable<T> observable, T t) {
        ListenableFuture<T> fetchData = observable.fetchData();
        if (!fetchData.isDone()) {
            return t;
        }
        try {
            return fetchData.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void setSourceState(VideoOutput.SourceState sourceState) {
        if (sourceState != this.mSourceState) {
            this.mSourceState = sourceState;
            getOutput().onSourceStateChanged(sourceState);
        }
    }

    /* access modifiers changed from: package-private */
    public SurfaceRequest getSurfaceRequest() {
        return (SurfaceRequest) Objects.requireNonNull(this.mSurfaceRequest);
    }

    public Set<Integer> getSupportedEffectTargets() {
        HashSet hashSet = new HashSet();
        hashSet.add(2);
        return hashSet;
    }

    public static final class Builder<T extends VideoOutput> implements UseCaseConfig.Builder<VideoCapture<T>, VideoCaptureConfig<T>, Builder<T>>, ImageOutputConfig.Builder<Builder<T>>, ImageInputConfig.Builder<Builder<T>>, ThreadConfig.Builder<Builder<T>> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder(T t) {
            this(createInitialBundle(t));
        }

        private Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.mMutableConfig = mutableOptionsBundle;
            if (mutableOptionsBundle.containsOption(VideoCaptureConfig.OPTION_VIDEO_OUTPUT)) {
                Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
                if (cls == null || cls.equals(VideoCapture.class)) {
                    setCaptureType(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE);
                    Class<VideoCapture> cls2 = VideoCapture.class;
                    Class cls3 = cls2;
                    setTargetClass(cls2);
                    return;
                }
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            throw new IllegalArgumentException("VideoOutput is required");
        }

        static Builder<? extends VideoOutput> fromConfig(Config config) {
            return new Builder<>(MutableOptionsBundle.from(config));
        }

        public static <T extends VideoOutput> Builder<T> fromConfig(VideoCaptureConfig<T> videoCaptureConfig) {
            return new Builder<>(MutableOptionsBundle.from(videoCaptureConfig));
        }

        private static <T extends VideoOutput> MutableOptionsBundle createInitialBundle(T t) {
            MutableOptionsBundle create = MutableOptionsBundle.create();
            create.insertOption(VideoCaptureConfig.OPTION_VIDEO_OUTPUT, t);
            return create;
        }

        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public VideoCaptureConfig<T> getUseCaseConfig() {
            return new VideoCaptureConfig<>(OptionsBundle.from(this.mMutableConfig));
        }

        public Builder<T> setVideoOutput(VideoOutput videoOutput) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_VIDEO_OUTPUT, videoOutput);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder<T> setVideoEncoderInfoFinder(Function<VideoEncoderConfig, VideoEncoderInfo> function) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_VIDEO_ENCODER_INFO_FINDER, function);
            return this;
        }

        public VideoCapture<T> build() {
            return new VideoCapture<>(getUseCaseConfig());
        }

        public Builder<T> setTargetClass(Class<VideoCapture<T>> cls) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public Builder<T> setTargetName(String str) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
            return this;
        }

        public Builder<T> setTargetAspectRatio(int i) {
            throw new UnsupportedOperationException("setTargetAspectRatio is not supported.");
        }

        public Builder<T> setTargetRotation(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(i));
            return this;
        }

        public Builder<T> setMirrorMode(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_MIRROR_MODE, Integer.valueOf(i));
            return this;
        }

        public Builder<T> setTargetResolution(Size size) {
            throw new UnsupportedOperationException("setTargetResolution is not supported.");
        }

        public Builder<T> setDefaultResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_DEFAULT_RESOLUTION, size);
            return this;
        }

        public Builder<T> setMaxResolution(Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_MAX_RESOLUTION, size);
            return this;
        }

        public Builder<T> setSupportedResolutions(List<Pair<Integer, Size[]>> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, list);
            return this;
        }

        public Builder<T> setCustomOrderedResolutions(List<Size> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_CUSTOM_ORDERED_RESOLUTIONS, list);
            return this;
        }

        public Builder<T> setResolutionSelector(ResolutionSelector resolutionSelector) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_RESOLUTION_SELECTOR, resolutionSelector);
            return this;
        }

        public Builder<T> setDynamicRange(DynamicRange dynamicRange) {
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, dynamicRange);
            return this;
        }

        public Builder<T> setBackgroundExecutor(Executor executor) {
            getMutableConfig().insertOption(ThreadConfig.OPTION_BACKGROUND_EXECUTOR, executor);
            return this;
        }

        public Builder<T> setDefaultSessionConfig(SessionConfig sessionConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        public Builder<T> setDefaultCaptureConfig(CaptureConfig captureConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        public Builder<T> setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder<T> setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        public Builder<T> setSurfaceOccupancyPriority(int i) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i));
            return this;
        }

        public Builder<T> setZslDisabled(boolean z) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_ZSL_DISABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder<T> setHighResolutionDisabled(boolean z) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_HIGH_RESOLUTION_DISABLED, Boolean.valueOf(z));
            return this;
        }

        public Builder<T> setTargetFrameRate(Range<Integer> range) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_TARGET_FRAME_RATE, range);
            return this;
        }

        public Builder<T> setVideoStabilizationEnabled(boolean z) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, Integer.valueOf(z ? 2 : 1));
            return this;
        }

        public Builder<T> setCaptureType(UseCaseConfigFactory.CaptureType captureType) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, captureType);
            return this;
        }

        public Builder<T> setSurfaceProcessingForceEnabled() {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_FORCE_ENABLE_SURFACE_PROCESSING, true);
            return this;
        }
    }
}
