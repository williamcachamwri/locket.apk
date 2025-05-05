package androidx.camera.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.FileDescriptorOutputOptions;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.MediaStoreOutputOptions;
import androidx.camera.video.OutputOptions;
import androidx.camera.video.PendingRecording;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.RotationProvider;
import androidx.camera.view.internal.ScreenFlashUiInfo;
import androidx.camera.view.video.AudioConfig;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class CameraController {
    private static final float AE_SIZE = 0.25f;
    private static final float AF_SIZE = 0.16666667f;
    private static final String CAMERA_NOT_ATTACHED = "Use cases not attached to camera.";
    private static final String CAMERA_NOT_INITIALIZED = "Camera not initialized.";
    @Deprecated
    public static final int COORDINATE_SYSTEM_VIEW_REFERENCED = 1;
    public static final int IMAGE_ANALYSIS = 2;
    public static final int IMAGE_CAPTURE = 1;
    private static final String IMAGE_CAPTURE_DISABLED = "ImageCapture disabled.";
    private static final ImageCapture.ScreenFlash NO_OP_SCREEN_FLASH = new ImageCapture.ScreenFlash() {
        public void clear() {
        }

        public void apply(long j, ImageCapture.ScreenFlashListener screenFlashListener) {
            screenFlashListener.onCompleted();
        }
    };
    private static final String PREVIEW_VIEW_NOT_ATTACHED = "PreviewView not attached to CameraController.";
    private static final String TAG = "CameraController";
    public static final int TAP_TO_FOCUS_FAILED = 4;
    public static final int TAP_TO_FOCUS_FOCUSED = 2;
    public static final int TAP_TO_FOCUS_NOT_FOCUSED = 3;
    public static final int TAP_TO_FOCUS_NOT_STARTED = 0;
    public static final int TAP_TO_FOCUS_STARTED = 1;
    public static final int VIDEO_CAPTURE = 4;
    private static final String VIDEO_CAPTURE_DISABLED = "VideoCapture disabled.";
    private static final String VIDEO_RECORDING_UNFINISHED = "Recording video. Only one recording can be active at a time.";
    Recording mActiveRecording;
    private ImageAnalysis.Analyzer mAnalysisAnalyzer;
    private Executor mAnalysisBackgroundExecutor;
    private Executor mAnalysisExecutor;
    private final Context mAppContext;
    Camera mCamera;
    ProcessCameraProviderWrapper mCameraProvider;
    CameraSelector mCameraSelector;
    final RotationProvider.Listener mDeviceRotationListener;
    private final Set<CameraEffect> mEffects;
    private int mEnabledUseCases;
    ImageAnalysis mImageAnalysis;
    ResolutionSelector mImageAnalysisResolutionSelector;
    OutputSize mImageAnalysisTargetSize;
    ImageCapture mImageCapture;
    Executor mImageCaptureIoExecutor;
    ResolutionSelector mImageCaptureResolutionSelector;
    OutputSize mImageCaptureTargetSize;
    private final ListenableFuture<Void> mInitializationFuture;
    private final PendingValue<Boolean> mPendingEnableTorch;
    private final PendingValue<Float> mPendingLinearZoom;
    private final PendingValue<Float> mPendingZoomRatio;
    private boolean mPinchToZoomEnabled;
    Preview mPreview;
    private DynamicRange mPreviewDynamicRange;
    ResolutionSelector mPreviewResolutionSelector;
    OutputSize mPreviewTargetSize;
    Map<Consumer<VideoRecordEvent>, Recording> mRecordingMap;
    private final RotationProvider mRotationProvider;
    private final Map<ScreenFlashUiInfo.ProviderType, ScreenFlashUiInfo> mScreenFlashUiInfoMap;
    Preview.SurfaceProvider mSurfaceProvider;
    private boolean mTapToFocusEnabled;
    final MutableLiveData<Integer> mTapToFocusState;
    private final ForwardingLiveData<Integer> mTorchState;
    VideoCapture<Recorder> mVideoCapture;
    private DynamicRange mVideoCaptureDynamicRange;
    private int mVideoCaptureMirrorMode;
    QualitySelector mVideoCaptureQualitySelector;
    private Range<Integer> mVideoCaptureTargetFrameRate;
    ViewPort mViewPort;
    private final ForwardingLiveData<ZoomState> mZoomState;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UseCases {
    }

    private float speedUpZoomBy2X(float f) {
        return f > 1.0f ? ((f - 1.0f) * 2.0f) + 1.0f : 1.0f - ((1.0f - f) * 2.0f);
    }

    /* access modifiers changed from: package-private */
    public abstract Camera startCamera();

    CameraController(Context context) {
        this(context, Futures.transform(ProcessCameraProvider.getInstance(context), new CameraController$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor()));
    }

    CameraController(Context context, ListenableFuture<ProcessCameraProviderWrapper> listenableFuture) {
        this.mCameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        this.mEnabledUseCases = 3;
        this.mActiveRecording = null;
        this.mRecordingMap = new HashMap();
        this.mVideoCaptureQualitySelector = Recorder.DEFAULT_QUALITY_SELECTOR;
        this.mVideoCaptureMirrorMode = 0;
        this.mVideoCaptureDynamicRange = DynamicRange.UNSPECIFIED;
        this.mPreviewDynamicRange = DynamicRange.UNSPECIFIED;
        this.mVideoCaptureTargetFrameRate = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
        this.mPinchToZoomEnabled = true;
        this.mTapToFocusEnabled = true;
        this.mZoomState = new ForwardingLiveData<>();
        this.mTorchState = new ForwardingLiveData<>();
        this.mTapToFocusState = new MutableLiveData<>(0);
        this.mPendingEnableTorch = new PendingValue<>();
        this.mPendingLinearZoom = new PendingValue<>();
        this.mPendingZoomRatio = new PendingValue<>();
        this.mEffects = new HashSet();
        this.mScreenFlashUiInfoMap = new HashMap();
        Context applicationContext = ContextUtil.getApplicationContext(context);
        this.mAppContext = applicationContext;
        this.mPreview = createPreview();
        this.mImageCapture = createImageCapture((Integer) null);
        this.mImageAnalysis = createImageAnalysis((Integer) null, (Integer) null, (Integer) null);
        this.mVideoCapture = createVideoCapture();
        this.mInitializationFuture = Futures.transform(listenableFuture, new CameraController$$ExternalSyntheticLambda2(this), CameraXExecutors.mainThreadExecutor());
        this.mRotationProvider = new RotationProvider(applicationContext);
        this.mDeviceRotationListener = new CameraController$$ExternalSyntheticLambda3(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-view-CameraController  reason: not valid java name */
    public /* synthetic */ Void m306lambda$new$0$androidxcameraviewCameraController(ProcessCameraProviderWrapper processCameraProviderWrapper) {
        this.mCameraProvider = processCameraProviderWrapper;
        unbindAllAndRecreate();
        startCameraAndTrackStates();
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$androidx-camera-view-CameraController  reason: not valid java name */
    public /* synthetic */ void m307lambda$new$1$androidxcameraviewCameraController(int i) {
        this.mImageAnalysis.setTargetRotation(i);
        this.mImageCapture.setTargetRotation(i);
        this.mVideoCapture.setTargetRotation(i);
    }

    public ListenableFuture<Void> getInitializationFuture() {
        return this.mInitializationFuture;
    }

    private boolean isCameraInitialized() {
        return this.mCameraProvider != null;
    }

    private boolean isPreviewViewAttached() {
        return (this.mSurfaceProvider == null || this.mViewPort == null) ? false : true;
    }

    private boolean isCameraAttached() {
        return this.mCamera != null;
    }

    public void setEnabledUseCases(int i) {
        Threads.checkMainThread();
        int i2 = this.mEnabledUseCases;
        if (i != i2) {
            this.mEnabledUseCases = i;
            if (!isVideoCaptureEnabled() && isRecording()) {
                stopRecording();
            }
            startCameraAndTrackStates(new CameraController$$ExternalSyntheticLambda7(this, i2));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setEnabledUseCases$2$androidx-camera-view-CameraController  reason: not valid java name */
    public /* synthetic */ void m309lambda$setEnabledUseCases$2$androidxcameraviewCameraController(int i) {
        this.mEnabledUseCases = i;
    }

    private boolean isUseCaseEnabled(int i) {
        return (i & this.mEnabledUseCases) != 0;
    }

    private void configureResolution(ImageOutputConfig.Builder<?> builder, ResolutionSelector resolutionSelector, OutputSize outputSize) {
        AspectRatioStrategy viewportAspectRatioStrategy;
        if (resolutionSelector != null) {
            builder.setResolutionSelector(resolutionSelector);
        } else if (outputSize != null) {
            setTargetOutputSize(builder, outputSize);
        } else {
            ViewPort viewPort = this.mViewPort;
            if (viewPort != null && (viewportAspectRatioStrategy = getViewportAspectRatioStrategy(viewPort)) != null) {
                builder.setResolutionSelector(new ResolutionSelector.Builder().setAspectRatioStrategy(viewportAspectRatioStrategy).build());
            }
        }
    }

    private void setTargetOutputSize(ImageOutputConfig.Builder<?> builder, OutputSize outputSize) {
        if (outputSize != null) {
            if (outputSize.getResolution() != null) {
                builder.setTargetResolution(outputSize.getResolution());
            } else if (outputSize.getAspectRatio() != -1) {
                builder.setTargetAspectRatio(outputSize.getAspectRatio());
            } else {
                Logger.e(TAG, "Invalid target surface size. " + outputSize);
            }
        }
    }

    private boolean isOutputSizeEqual(OutputSize outputSize, OutputSize outputSize2) {
        if (outputSize == outputSize2) {
            return true;
        }
        return outputSize != null && outputSize.equals(outputSize2);
    }

    /* access modifiers changed from: package-private */
    public void attachPreviewSurface(Preview.SurfaceProvider surfaceProvider, ViewPort viewPort) {
        Threads.checkMainThread();
        if (this.mSurfaceProvider != surfaceProvider) {
            this.mSurfaceProvider = surfaceProvider;
            this.mPreview.setSurfaceProvider(surfaceProvider);
        }
        boolean z = this.mViewPort == null || getViewportAspectRatioStrategy(viewPort) != getViewportAspectRatioStrategy(this.mViewPort);
        this.mViewPort = viewPort;
        startListeningToRotationEvents();
        if (z) {
            unbindAllAndRecreate();
        }
        startCameraAndTrackStates();
    }

    /* access modifiers changed from: package-private */
    public void clearPreviewSurface() {
        Threads.checkMainThread();
        ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
        if (processCameraProviderWrapper != null) {
            processCameraProviderWrapper.unbind(this.mPreview, this.mImageCapture, this.mImageAnalysis, this.mVideoCapture);
        }
        this.mPreview.setSurfaceProvider((Preview.SurfaceProvider) null);
        this.mCamera = null;
        this.mSurfaceProvider = null;
        this.mViewPort = null;
        stopListeningToRotationEvents();
    }

    private void startListeningToRotationEvents() {
        this.mRotationProvider.addListener(CameraXExecutors.mainThreadExecutor(), this.mDeviceRotationListener);
    }

    private void stopListeningToRotationEvents() {
        this.mRotationProvider.removeListener(this.mDeviceRotationListener);
    }

    @Deprecated
    public void setPreviewTargetSize(OutputSize outputSize) {
        Threads.checkMainThread();
        if (!isOutputSizeEqual(this.mPreviewTargetSize, outputSize)) {
            this.mPreviewTargetSize = outputSize;
            unbindPreviewAndRecreate();
            startCameraAndTrackStates();
        }
    }

    @Deprecated
    public OutputSize getPreviewTargetSize() {
        Threads.checkMainThread();
        return this.mPreviewTargetSize;
    }

    public void setPreviewResolutionSelector(ResolutionSelector resolutionSelector) {
        Threads.checkMainThread();
        if (this.mPreviewResolutionSelector != resolutionSelector) {
            this.mPreviewResolutionSelector = resolutionSelector;
            unbindPreviewAndRecreate();
            startCameraAndTrackStates();
        }
    }

    public ResolutionSelector getPreviewResolutionSelector() {
        Threads.checkMainThread();
        return this.mPreviewResolutionSelector;
    }

    public void setPreviewDynamicRange(DynamicRange dynamicRange) {
        Threads.checkMainThread();
        this.mPreviewDynamicRange = dynamicRange;
        unbindPreviewAndRecreate();
        startCameraAndTrackStates();
    }

    public DynamicRange getPreviewDynamicRange() {
        Threads.checkMainThread();
        return this.mPreviewDynamicRange;
    }

    private void unbindPreviewAndRecreate() {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mPreview);
        }
        Preview createPreview = createPreview();
        this.mPreview = createPreview;
        Preview.SurfaceProvider surfaceProvider = this.mSurfaceProvider;
        if (surfaceProvider != null) {
            createPreview.setSurfaceProvider(surfaceProvider);
        }
    }

    private Preview createPreview() {
        Preview.Builder builder = new Preview.Builder();
        configureResolution(builder, this.mPreviewResolutionSelector, this.mPreviewTargetSize);
        builder.setDynamicRange(this.mPreviewDynamicRange);
        return builder.build();
    }

    public boolean isImageCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(1);
    }

    public int getImageCaptureFlashMode() {
        Threads.checkMainThread();
        return this.mImageCapture.getFlashMode();
    }

    public void setImageCaptureFlashMode(int i) {
        Threads.checkMainThread();
        if (i == 3) {
            Integer lensFacing = this.mCameraSelector.getLensFacing();
            if (lensFacing == null || lensFacing.intValue() == 0) {
                updateScreenFlashToImageCapture();
            } else {
                throw new IllegalArgumentException("Not a front camera despite setting FLASH_MODE_SCREEN");
            }
        }
        this.mImageCapture.setFlashMode(i);
    }

    public void setScreenFlashUiInfo(ScreenFlashUiInfo screenFlashUiInfo) {
        ScreenFlashUiInfo screenFlashUiInfoByPriority = getScreenFlashUiInfoByPriority();
        this.mScreenFlashUiInfoMap.put(screenFlashUiInfo.getProviderType(), screenFlashUiInfo);
        ScreenFlashUiInfo screenFlashUiInfoByPriority2 = getScreenFlashUiInfoByPriority();
        if (screenFlashUiInfoByPriority2 != null && !screenFlashUiInfoByPriority2.equals(screenFlashUiInfoByPriority)) {
            updateScreenFlashToImageCapture();
        }
    }

    public void updateScreenFlashToImageCapture() {
        ScreenFlashUiInfo screenFlashUiInfoByPriority = getScreenFlashUiInfoByPriority();
        if (screenFlashUiInfoByPriority == null) {
            Logger.d(TAG, "No ScreenFlash instance set yet, need to wait for controller to be set to either ScreenFlashView or PreviewView");
            this.mImageCapture.setScreenFlash(NO_OP_SCREEN_FLASH);
            return;
        }
        this.mImageCapture.setScreenFlash(screenFlashUiInfoByPriority.getScreenFlash());
        Logger.d(TAG, "Set ScreenFlash instance to ImageCapture, provided by " + screenFlashUiInfoByPriority.getProviderType().name());
    }

    public ScreenFlashUiInfo getScreenFlashUiInfoByPriority() {
        if (this.mScreenFlashUiInfoMap.get(ScreenFlashUiInfo.ProviderType.SCREEN_FLASH_VIEW) != null) {
            return this.mScreenFlashUiInfoMap.get(ScreenFlashUiInfo.ProviderType.SCREEN_FLASH_VIEW);
        }
        if (this.mScreenFlashUiInfoMap.get(ScreenFlashUiInfo.ProviderType.PREVIEW_VIEW) != null) {
            return this.mScreenFlashUiInfoMap.get(ScreenFlashUiInfo.ProviderType.PREVIEW_VIEW);
        }
        return null;
    }

    public void takePicture(ImageCapture.OutputFileOptions outputFileOptions, Executor executor, ImageCapture.OnImageSavedCallback onImageSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        throwExceptionForInvalidScreenFlashCapture();
        updateMirroringFlagInOutputFileOptions(outputFileOptions);
        this.mImageCapture.m146lambda$takePicture$2$androidxcameracoreImageCapture(outputFileOptions, executor, onImageSavedCallback);
    }

    /* access modifiers changed from: package-private */
    public void updateMirroringFlagInOutputFileOptions(ImageCapture.OutputFileOptions outputFileOptions) {
        if (this.mCameraSelector.getLensFacing() != null && !outputFileOptions.getMetadata().isReversedHorizontalSet()) {
            outputFileOptions.getMetadata().setReversedHorizontal(this.mCameraSelector.getLensFacing().intValue() == 0);
        }
    }

    public void takePicture(Executor executor, ImageCapture.OnImageCapturedCallback onImageCapturedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        throwExceptionForInvalidScreenFlashCapture();
        this.mImageCapture.m145lambda$takePicture$1$androidxcameracoreImageCapture(executor, onImageCapturedCallback);
    }

    private void throwExceptionForInvalidScreenFlashCapture() {
        if (getImageCaptureFlashMode() != 3) {
            return;
        }
        if (getScreenFlashUiInfoByPriority() == null || getScreenFlashUiInfoByPriority().getScreenFlash() == null) {
            throw new IllegalStateException("No window set in PreviewView despite setting FLASH_MODE_SCREEN");
        }
    }

    public void setImageCaptureMode(int i) {
        Threads.checkMainThread();
        if (this.mImageCapture.getCaptureMode() != i) {
            unbindImageCaptureAndRecreate(Integer.valueOf(i));
            startCameraAndTrackStates();
        }
    }

    public int getImageCaptureMode() {
        Threads.checkMainThread();
        return this.mImageCapture.getCaptureMode();
    }

    @Deprecated
    public void setImageCaptureTargetSize(OutputSize outputSize) {
        Threads.checkMainThread();
        if (!isOutputSizeEqual(this.mImageCaptureTargetSize, outputSize)) {
            this.mImageCaptureTargetSize = outputSize;
            unbindImageCaptureAndRecreate(Integer.valueOf(getImageCaptureMode()));
            startCameraAndTrackStates();
        }
    }

    @Deprecated
    public OutputSize getImageCaptureTargetSize() {
        Threads.checkMainThread();
        return this.mImageCaptureTargetSize;
    }

    public void setImageCaptureResolutionSelector(ResolutionSelector resolutionSelector) {
        Threads.checkMainThread();
        if (this.mImageCaptureResolutionSelector != resolutionSelector) {
            this.mImageCaptureResolutionSelector = resolutionSelector;
            unbindImageCaptureAndRecreate(Integer.valueOf(getImageCaptureMode()));
            startCameraAndTrackStates();
        }
    }

    public ResolutionSelector getImageCaptureResolutionSelector() {
        Threads.checkMainThread();
        return this.mImageCaptureResolutionSelector;
    }

    public void setImageCaptureIoExecutor(Executor executor) {
        Threads.checkMainThread();
        if (this.mImageCaptureIoExecutor != executor) {
            this.mImageCaptureIoExecutor = executor;
            unbindImageCaptureAndRecreate(Integer.valueOf(getImageCaptureMode()));
            startCameraAndTrackStates();
        }
    }

    public Executor getImageCaptureIoExecutor() {
        Threads.checkMainThread();
        return this.mImageCaptureIoExecutor;
    }

    private void unbindImageCaptureAndRecreate(Integer num) {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mImageCapture);
        }
        int flashMode = this.mImageCapture.getFlashMode();
        this.mImageCapture = createImageCapture(num);
        setImageCaptureFlashMode(flashMode);
    }

    private ImageCapture createImageCapture(Integer num) {
        ImageCapture.Builder builder = new ImageCapture.Builder();
        if (num != null) {
            builder.setCaptureMode(num.intValue());
        }
        configureResolution(builder, this.mImageCaptureResolutionSelector, this.mImageCaptureTargetSize);
        Executor executor = this.mImageCaptureIoExecutor;
        if (executor != null) {
            builder.setIoExecutor(executor);
        }
        return builder.build();
    }

    public boolean isImageAnalysisEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(2);
    }

    public void setImageAnalysisAnalyzer(Executor executor, ImageAnalysis.Analyzer analyzer) {
        Threads.checkMainThread();
        ImageAnalysis.Analyzer analyzer2 = this.mAnalysisAnalyzer;
        if (analyzer2 != analyzer || this.mAnalysisExecutor != executor) {
            this.mAnalysisExecutor = executor;
            this.mAnalysisAnalyzer = analyzer;
            this.mImageAnalysis.setAnalyzer(executor, analyzer);
            restartCameraIfAnalyzerResolutionChanged(analyzer2, analyzer);
        }
    }

    public void clearImageAnalysisAnalyzer() {
        Threads.checkMainThread();
        ImageAnalysis.Analyzer analyzer = this.mAnalysisAnalyzer;
        this.mAnalysisExecutor = null;
        this.mAnalysisAnalyzer = null;
        this.mImageAnalysis.clearAnalyzer();
        restartCameraIfAnalyzerResolutionChanged(analyzer, (ImageAnalysis.Analyzer) null);
    }

    private void restartCameraIfAnalyzerResolutionChanged(ImageAnalysis.Analyzer analyzer, ImageAnalysis.Analyzer analyzer2) {
        Size size = null;
        Size defaultTargetResolution = analyzer == null ? null : analyzer.getDefaultTargetResolution();
        if (analyzer2 != null) {
            size = analyzer2.getDefaultTargetResolution();
        }
        if (!Objects.equals(defaultTargetResolution, size)) {
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    public int getImageAnalysisBackpressureStrategy() {
        Threads.checkMainThread();
        return this.mImageAnalysis.getBackpressureStrategy();
    }

    public void setImageAnalysisBackpressureStrategy(int i) {
        Threads.checkMainThread();
        if (this.mImageAnalysis.getBackpressureStrategy() != i) {
            unbindImageAnalysisAndRecreate(Integer.valueOf(i), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    public void setImageAnalysisImageQueueDepth(int i) {
        Threads.checkMainThread();
        if (this.mImageAnalysis.getImageQueueDepth() != i) {
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(i), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    public int getImageAnalysisImageQueueDepth() {
        Threads.checkMainThread();
        return this.mImageAnalysis.getImageQueueDepth();
    }

    @Deprecated
    public void setImageAnalysisTargetSize(OutputSize outputSize) {
        Threads.checkMainThread();
        if (!isOutputSizeEqual(this.mImageAnalysisTargetSize, outputSize)) {
            this.mImageAnalysisTargetSize = outputSize;
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    @Deprecated
    public OutputSize getImageAnalysisTargetSize() {
        Threads.checkMainThread();
        return this.mImageAnalysisTargetSize;
    }

    public void setImageAnalysisResolutionSelector(ResolutionSelector resolutionSelector) {
        Threads.checkMainThread();
        if (this.mImageAnalysisResolutionSelector != resolutionSelector) {
            this.mImageAnalysisResolutionSelector = resolutionSelector;
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    public ResolutionSelector getImageAnalysisResolutionSelector() {
        Threads.checkMainThread();
        return this.mImageAnalysisResolutionSelector;
    }

    public void setImageAnalysisBackgroundExecutor(Executor executor) {
        Threads.checkMainThread();
        if (this.mAnalysisBackgroundExecutor != executor) {
            this.mAnalysisBackgroundExecutor = executor;
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
            startCameraAndTrackStates();
        }
    }

    public Executor getImageAnalysisBackgroundExecutor() {
        Threads.checkMainThread();
        return this.mAnalysisBackgroundExecutor;
    }

    public void setImageAnalysisOutputImageFormat(int i) {
        Threads.checkMainThread();
        if (i != this.mImageAnalysis.getOutputImageFormat()) {
            unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(i));
        }
    }

    public int getImageAnalysisOutputImageFormat() {
        Threads.checkMainThread();
        return this.mImageAnalysis.getOutputImageFormat();
    }

    private void unbindImageAnalysisAndRecreate(Integer num, Integer num2, Integer num3) {
        ImageAnalysis.Analyzer analyzer;
        Threads.checkMainThread();
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mImageAnalysis);
        }
        ImageAnalysis createImageAnalysis = createImageAnalysis(num, num2, num3);
        this.mImageAnalysis = createImageAnalysis;
        Executor executor = this.mAnalysisExecutor;
        if (executor != null && (analyzer = this.mAnalysisAnalyzer) != null) {
            createImageAnalysis.setAnalyzer(executor, analyzer);
        }
    }

    private ImageAnalysis createImageAnalysis(Integer num, Integer num2, Integer num3) {
        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        if (num != null) {
            builder.setBackpressureStrategy(num.intValue());
        }
        if (num2 != null) {
            builder.setImageQueueDepth(num2.intValue());
        }
        if (num3 != null) {
            builder.setOutputImageFormat(num3.intValue());
        }
        configureResolution(builder, this.mImageAnalysisResolutionSelector, this.mImageAnalysisTargetSize);
        Executor executor = this.mAnalysisBackgroundExecutor;
        if (executor != null) {
            builder.setBackgroundExecutor(executor);
        }
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    public void updatePreviewViewTransform(Matrix matrix) {
        Threads.checkMainThread();
        ImageAnalysis.Analyzer analyzer = this.mAnalysisAnalyzer;
        if (analyzer != null && analyzer.getTargetCoordinateSystem() == 1) {
            this.mAnalysisAnalyzer.updateTransform(matrix);
        }
    }

    public boolean isVideoCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(4);
    }

    public Recording startRecording(FileOutputOptions fileOutputOptions, AudioConfig audioConfig, Executor executor, Consumer<VideoRecordEvent> consumer) {
        return startRecordingInternal(fileOutputOptions, audioConfig, executor, consumer);
    }

    public Recording startRecording(FileDescriptorOutputOptions fileDescriptorOutputOptions, AudioConfig audioConfig, Executor executor, Consumer<VideoRecordEvent> consumer) {
        return startRecordingInternal(fileDescriptorOutputOptions, audioConfig, executor, consumer);
    }

    public Recording startRecording(MediaStoreOutputOptions mediaStoreOutputOptions, AudioConfig audioConfig, Executor executor, Consumer<VideoRecordEvent> consumer) {
        return startRecordingInternal(mediaStoreOutputOptions, audioConfig, executor, consumer);
    }

    private Recording startRecordingInternal(OutputOptions outputOptions, AudioConfig audioConfig, Executor executor, Consumer<VideoRecordEvent> consumer) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isVideoCaptureEnabled(), VIDEO_CAPTURE_DISABLED);
        Preconditions.checkState(!isRecording(), VIDEO_RECORDING_UNFINISHED);
        Consumer<VideoRecordEvent> wrapListenerToDeactivateRecordingOnFinalized = wrapListenerToDeactivateRecordingOnFinalized(consumer);
        PendingRecording prepareRecording = prepareRecording(outputOptions);
        if (audioConfig.getAudioEnabled()) {
            checkAudioPermissionGranted();
            prepareRecording.withAudioEnabled();
        }
        Recording start = prepareRecording.start(executor, wrapListenerToDeactivateRecordingOnFinalized);
        setActiveRecording(start, wrapListenerToDeactivateRecordingOnFinalized);
        return start;
    }

    private void checkAudioPermissionGranted() {
        if (PermissionChecker.checkSelfPermission(this.mAppContext, "android.permission.RECORD_AUDIO") == -1) {
            throw new SecurityException("Attempted to start recording with audio, but application does not have RECORD_AUDIO permission granted.");
        }
    }

    private PendingRecording prepareRecording(OutputOptions outputOptions) {
        Recorder output = this.mVideoCapture.getOutput();
        if (outputOptions instanceof FileOutputOptions) {
            return output.prepareRecording(this.mAppContext, (FileOutputOptions) outputOptions);
        }
        if (outputOptions instanceof FileDescriptorOutputOptions) {
            return output.prepareRecording(this.mAppContext, (FileDescriptorOutputOptions) outputOptions);
        }
        if (outputOptions instanceof MediaStoreOutputOptions) {
            return output.prepareRecording(this.mAppContext, (MediaStoreOutputOptions) outputOptions);
        }
        throw new IllegalArgumentException("Unsupported OutputOptions type.");
    }

    private Consumer<VideoRecordEvent> wrapListenerToDeactivateRecordingOnFinalized(final Consumer<VideoRecordEvent> consumer) {
        final Executor mainExecutor = ContextCompat.getMainExecutor(this.mAppContext);
        return new Consumer<VideoRecordEvent>() {
            public void accept(VideoRecordEvent videoRecordEvent) {
                if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
                    if (!Threads.isMainThread()) {
                        mainExecutor.execute(new CameraController$2$$ExternalSyntheticLambda0(this));
                    } else {
                        CameraController.this.deactivateRecordingByListener(this);
                    }
                }
                consumer.accept(videoRecordEvent);
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$accept$0$androidx-camera-view-CameraController$2  reason: not valid java name */
            public /* synthetic */ void m310lambda$accept$0$androidxcameraviewCameraController$2() {
                CameraController.this.deactivateRecordingByListener(this);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void deactivateRecordingByListener(Consumer<VideoRecordEvent> consumer) {
        Recording remove = this.mRecordingMap.remove(consumer);
        if (remove != null) {
            deactivateRecording(remove);
        }
    }

    private void deactivateRecording(Recording recording) {
        if (this.mActiveRecording == recording) {
            this.mActiveRecording = null;
        }
    }

    private void setActiveRecording(Recording recording, Consumer<VideoRecordEvent> consumer) {
        this.mRecordingMap.put(consumer, recording);
        this.mActiveRecording = recording;
    }

    private void stopRecording() {
        Threads.checkMainThread();
        Recording recording = this.mActiveRecording;
        if (recording != null) {
            recording.stop();
            deactivateRecording(this.mActiveRecording);
        }
    }

    public boolean isRecording() {
        Threads.checkMainThread();
        Recording recording = this.mActiveRecording;
        return recording != null && !recording.isClosed();
    }

    public void setVideoCaptureQualitySelector(QualitySelector qualitySelector) {
        Threads.checkMainThread();
        this.mVideoCaptureQualitySelector = qualitySelector;
        unbindVideoAndRecreate();
        startCameraAndTrackStates();
    }

    public QualitySelector getVideoCaptureQualitySelector() {
        Threads.checkMainThread();
        return this.mVideoCaptureQualitySelector;
    }

    public void setVideoCaptureMirrorMode(int i) {
        Threads.checkMainThread();
        this.mVideoCaptureMirrorMode = i;
        unbindVideoAndRecreate();
        startCameraAndTrackStates();
    }

    public int getVideoCaptureMirrorMode() {
        Threads.checkMainThread();
        return this.mVideoCaptureMirrorMode;
    }

    public void setVideoCaptureDynamicRange(DynamicRange dynamicRange) {
        Threads.checkMainThread();
        this.mVideoCaptureDynamicRange = dynamicRange;
        unbindVideoAndRecreate();
        startCameraAndTrackStates();
    }

    public DynamicRange getVideoCaptureDynamicRange() {
        Threads.checkMainThread();
        return this.mVideoCaptureDynamicRange;
    }

    public void setVideoCaptureTargetFrameRate(Range<Integer> range) {
        Threads.checkMainThread();
        this.mVideoCaptureTargetFrameRate = range;
        unbindVideoAndRecreate();
        startCameraAndTrackStates();
    }

    public Range<Integer> getVideoCaptureTargetFrameRate() {
        Threads.checkMainThread();
        return this.mVideoCaptureTargetFrameRate;
    }

    private void unbindVideoAndRecreate() {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mVideoCapture);
        }
        this.mVideoCapture = createVideoCapture();
    }

    private VideoCapture<Recorder> createVideoCapture() {
        int viewportAspectRatioInt;
        Recorder.Builder qualitySelector = new Recorder.Builder().setQualitySelector(this.mVideoCaptureQualitySelector);
        if (!(this.mViewPort == null || this.mVideoCaptureQualitySelector != Recorder.DEFAULT_QUALITY_SELECTOR || (viewportAspectRatioInt = getViewportAspectRatioInt(this.mViewPort)) == -1)) {
            qualitySelector.setAspectRatio(viewportAspectRatioInt);
        }
        return new VideoCapture.Builder(qualitySelector.build()).setTargetFrameRate(this.mVideoCaptureTargetFrameRate).setMirrorMode(this.mVideoCaptureMirrorMode).setDynamicRange(this.mVideoCaptureDynamicRange).build();
    }

    private AspectRatioStrategy getViewportAspectRatioStrategy(ViewPort viewPort) {
        int viewportAspectRatioInt = getViewportAspectRatioInt(viewPort);
        if (viewportAspectRatioInt != -1) {
            return new AspectRatioStrategy(viewportAspectRatioInt, 1);
        }
        return null;
    }

    private int getViewportAspectRatioInt(ViewPort viewPort) {
        int i;
        int i2;
        boolean z;
        if (viewPort == null) {
            i = 0;
        } else {
            i = CameraOrientationUtil.surfaceRotationToDegrees(viewPort.getRotation());
        }
        ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
        if (processCameraProviderWrapper == null) {
            i2 = 0;
        } else {
            i2 = processCameraProviderWrapper.getCameraInfo(this.mCameraSelector).getSensorRotationDegrees();
        }
        ProcessCameraProviderWrapper processCameraProviderWrapper2 = this.mCameraProvider;
        if (processCameraProviderWrapper2 == null || processCameraProviderWrapper2.getCameraInfo(this.mCameraSelector).getLensFacing() == 1) {
            z = true;
        } else {
            z = false;
        }
        int relativeImageRotation = CameraOrientationUtil.getRelativeImageRotation(i, i2, z);
        Rational aspectRatio = viewPort.getAspectRatio();
        if (relativeImageRotation == 90 || relativeImageRotation == 270) {
            aspectRatio = new Rational(aspectRatio.getDenominator(), aspectRatio.getNumerator());
        }
        if (aspectRatio.equals(new Rational(4, 3))) {
            return 0;
        }
        if (aspectRatio.equals(new Rational(16, 9))) {
            return 1;
        }
        return -1;
    }

    private void unbindAllAndRecreate() {
        unbindPreviewAndRecreate();
        unbindImageCaptureAndRecreate(Integer.valueOf(getImageCaptureMode()));
        unbindImageAnalysisAndRecreate(Integer.valueOf(this.mImageAnalysis.getBackpressureStrategy()), Integer.valueOf(this.mImageAnalysis.getImageQueueDepth()), Integer.valueOf(this.mImageAnalysis.getOutputImageFormat()));
        unbindVideoAndRecreate();
    }

    public void setCameraSelector(CameraSelector cameraSelector) {
        Threads.checkMainThread();
        if (this.mCameraSelector != cameraSelector) {
            Integer lensFacing = cameraSelector.getLensFacing();
            if (this.mImageCapture.getFlashMode() != 3 || lensFacing == null || lensFacing.intValue() == 0) {
                CameraSelector cameraSelector2 = this.mCameraSelector;
                this.mCameraSelector = cameraSelector;
                ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
                if (processCameraProviderWrapper != null) {
                    processCameraProviderWrapper.unbind(this.mPreview, this.mImageCapture, this.mImageAnalysis, this.mVideoCapture);
                    startCameraAndTrackStates(new CameraController$$ExternalSyntheticLambda0(this, cameraSelector2));
                    return;
                }
                return;
            }
            throw new IllegalStateException("Not a front camera despite setting FLASH_MODE_SCREEN");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setCameraSelector$3$androidx-camera-view-CameraController  reason: not valid java name */
    public /* synthetic */ void m308lambda$setCameraSelector$3$androidxcameraviewCameraController(CameraSelector cameraSelector) {
        this.mCameraSelector = cameraSelector;
    }

    public boolean hasCamera(CameraSelector cameraSelector) {
        Threads.checkMainThread();
        Preconditions.checkNotNull(cameraSelector);
        ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
        if (processCameraProviderWrapper != null) {
            try {
                return processCameraProviderWrapper.hasCamera(cameraSelector);
            } catch (CameraInfoUnavailableException e) {
                Logger.w(TAG, "Failed to check camera availability", e);
                return false;
            }
        } else {
            throw new IllegalStateException("Camera not initialized. Please wait for the initialization future to finish. See #getInitializationFuture().");
        }
    }

    public CameraSelector getCameraSelector() {
        Threads.checkMainThread();
        return this.mCameraSelector;
    }

    public boolean isPinchToZoomEnabled() {
        Threads.checkMainThread();
        return this.mPinchToZoomEnabled;
    }

    public void setPinchToZoomEnabled(boolean z) {
        Threads.checkMainThread();
        this.mPinchToZoomEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void onPinchToZoom(float f) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
        } else if (!this.mPinchToZoomEnabled) {
            Logger.d(TAG, "Pinch to zoom disabled.");
        } else {
            Logger.d(TAG, "Pinch to zoom with scale: " + f);
            ZoomState value = getZoomState().getValue();
            if (value != null) {
                setZoomRatio(Math.min(Math.max(value.getZoomRatio() * speedUpZoomBy2X(f), value.getMinZoomRatio()), value.getMaxZoomRatio()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onTapToFocus(MeteringPointFactory meteringPointFactory, float f, float f2) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
        } else if (!this.mTapToFocusEnabled) {
            Logger.d(TAG, "Tap to focus disabled. ");
        } else {
            Logger.d(TAG, "Tap to focus started: " + f + ", " + f2);
            this.mTapToFocusState.postValue(1);
            MeteringPoint createPoint = meteringPointFactory.createPoint(f, f2, AF_SIZE);
            Futures.addCallback(this.mCamera.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(createPoint, 1).addPoint(meteringPointFactory.createPoint(f, f2, AE_SIZE), 2).build()), new FutureCallback<FocusMeteringResult>() {
                public void onSuccess(FocusMeteringResult focusMeteringResult) {
                    if (focusMeteringResult != null) {
                        Logger.d(CameraController.TAG, "Tap to focus onSuccess: " + focusMeteringResult.isFocusSuccessful());
                        CameraController.this.mTapToFocusState.postValue(Integer.valueOf(focusMeteringResult.isFocusSuccessful() ? 2 : 3));
                    }
                }

                public void onFailure(Throwable th) {
                    if (th instanceof CameraControl.OperationCanceledException) {
                        Logger.d(CameraController.TAG, "Tap-to-focus is canceled by new action.");
                        return;
                    }
                    Logger.d(CameraController.TAG, "Tap to focus failed.", th);
                    CameraController.this.mTapToFocusState.postValue(4);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    public boolean isTapToFocusEnabled() {
        Threads.checkMainThread();
        return this.mTapToFocusEnabled;
    }

    public void setTapToFocusEnabled(boolean z) {
        Threads.checkMainThread();
        this.mTapToFocusEnabled = z;
    }

    public LiveData<Integer> getTapToFocusState() {
        Threads.checkMainThread();
        return this.mTapToFocusState;
    }

    public LiveData<ZoomState> getZoomState() {
        Threads.checkMainThread();
        return this.mZoomState;
    }

    public CameraInfo getCameraInfo() {
        Threads.checkMainThread();
        Camera camera = this.mCamera;
        if (camera == null) {
            return null;
        }
        return camera.getCameraInfo();
    }

    public CameraControl getCameraControl() {
        Threads.checkMainThread();
        Camera camera = this.mCamera;
        if (camera == null) {
            return null;
        }
        return camera.getCameraControl();
    }

    public ListenableFuture<Void> setZoomRatio(float f) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            return this.mPendingZoomRatio.setValue(Float.valueOf(f));
        }
        return this.mCamera.getCameraControl().setZoomRatio(f);
    }

    public ListenableFuture<Void> setLinearZoom(float f) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            return this.mPendingLinearZoom.setValue(Float.valueOf(f));
        }
        return this.mCamera.getCameraControl().setLinearZoom(f);
    }

    public LiveData<Integer> getTorchState() {
        Threads.checkMainThread();
        return this.mTorchState;
    }

    public ListenableFuture<Void> enableTorch(boolean z) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            return this.mPendingEnableTorch.setValue(Boolean.valueOf(z));
        }
        return this.mCamera.getCameraControl().enableTorch(z);
    }

    public void setEffects(Set<CameraEffect> set) {
        Threads.checkMainThread();
        if (!Objects.equals(this.mEffects, set)) {
            ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
            if (processCameraProviderWrapper != null) {
                processCameraProviderWrapper.unbindAll();
            }
            this.mEffects.clear();
            this.mEffects.addAll(set);
            startCameraAndTrackStates();
        }
    }

    public void clearEffects() {
        Threads.checkMainThread();
        ProcessCameraProviderWrapper processCameraProviderWrapper = this.mCameraProvider;
        if (processCameraProviderWrapper != null) {
            processCameraProviderWrapper.unbindAll();
        }
        this.mEffects.clear();
        startCameraAndTrackStates();
    }

    /* access modifiers changed from: package-private */
    public void startCameraAndTrackStates() {
        startCameraAndTrackStates((Runnable) null);
    }

    /* access modifiers changed from: package-private */
    public void startCameraAndTrackStates(Runnable runnable) {
        try {
            this.mCamera = startCamera();
            if (!isCameraAttached()) {
                Logger.d(TAG, CAMERA_NOT_ATTACHED);
                return;
            }
            this.mZoomState.setSource(this.mCamera.getCameraInfo().getZoomState());
            this.mTorchState.setSource(this.mCamera.getCameraInfo().getTorchState());
            this.mPendingEnableTorch.propagateIfHasValue(new CameraController$$ExternalSyntheticLambda4(this));
            this.mPendingLinearZoom.propagateIfHasValue(new CameraController$$ExternalSyntheticLambda5(this));
            this.mPendingZoomRatio.propagateIfHasValue(new CameraController$$ExternalSyntheticLambda6(this));
        } catch (RuntimeException e) {
            if (runnable != null) {
                runnable.run();
            }
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    public UseCaseGroup createUseCaseGroup() {
        if (!isCameraInitialized()) {
            Logger.d(TAG, CAMERA_NOT_INITIALIZED);
            return null;
        } else if (!isPreviewViewAttached()) {
            Logger.d(TAG, PREVIEW_VIEW_NOT_ATTACHED);
            return null;
        } else {
            UseCaseGroup.Builder addUseCase = new UseCaseGroup.Builder().addUseCase(this.mPreview);
            if (isImageCaptureEnabled()) {
                addUseCase.addUseCase(this.mImageCapture);
            } else {
                this.mCameraProvider.unbind(this.mImageCapture);
            }
            if (isImageAnalysisEnabled()) {
                addUseCase.addUseCase(this.mImageAnalysis);
            } else {
                this.mCameraProvider.unbind(this.mImageAnalysis);
            }
            if (isVideoCaptureEnabled()) {
                addUseCase.addUseCase(this.mVideoCapture);
            } else {
                this.mCameraProvider.unbind(this.mVideoCapture);
            }
            addUseCase.setViewPort(this.mViewPort);
            for (CameraEffect addEffect : this.mEffects) {
                addUseCase.addEffect(addEffect);
            }
            return addUseCase.build();
        }
    }

    @Deprecated
    public static final class OutputSize {
        public static final int UNASSIGNED_ASPECT_RATIO = -1;
        private final int mAspectRatio;
        private final Size mResolution;

        @Retention(RetentionPolicy.SOURCE)
        public @interface OutputAspectRatio {
        }

        public OutputSize(int i) {
            Preconditions.checkArgument(i != -1);
            this.mAspectRatio = i;
            this.mResolution = null;
        }

        public OutputSize(Size size) {
            Preconditions.checkNotNull(size);
            this.mAspectRatio = -1;
            this.mResolution = size;
        }

        public int getAspectRatio() {
            return this.mAspectRatio;
        }

        public Size getResolution() {
            return this.mResolution;
        }

        public String toString() {
            return "aspect ratio: " + this.mAspectRatio + " resolution: " + this.mResolution;
        }
    }
}
