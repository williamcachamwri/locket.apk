package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class Camera2CameraControlImpl implements CameraControlInternal {
    private static final int DEFAULT_TEMPLATE = 1;
    private static final String TAG = "Camera2CameraControlImp";
    static final String TAG_SESSION_UPDATE_ID = "CameraControlSessionUpdateId";
    private final AutoFlashAEModeDisabler mAutoFlashAEModeDisabler;
    private final Camera2CameraControl mCamera2CameraControl;
    private final Camera2CapturePipeline mCamera2CapturePipeline;
    private final CameraCaptureCallbackSet mCameraCaptureCallbackSet;
    private final CameraCharacteristicsCompat mCameraCharacteristics;
    private final CameraControlInternal.ControlUpdateCallback mControlUpdateCallback;
    private long mCurrentSessionUpdateId;
    final Executor mExecutor;
    private final ExposureControl mExposureControl;
    private volatile int mFlashMode;
    private volatile ListenableFuture<Void> mFlashModeChangeSessionUpdateFuture;
    private final FocusMeteringControl mFocusMeteringControl;
    private volatile boolean mIsTorchOn;
    private final Object mLock;
    private final AtomicLong mNextSessionUpdateId;
    private ImageCapture.ScreenFlash mScreenFlash;
    final CameraControlSessionCallback mSessionCallback;
    private final SessionConfig.Builder mSessionConfigBuilder;
    private int mTemplate;
    private final TorchControl mTorchControl;
    private int mUseCount;
    private final VideoUsageControl mVideoUsageControl;
    private final ZoomControl mZoomControl;
    ZslControl mZslControl;

    public interface CaptureResultListener {
        boolean onCaptureResult(TotalCaptureResult totalCaptureResult);
    }

    static /* synthetic */ void lambda$addInteropConfig$0() {
    }

    static /* synthetic */ void lambda$clearInteropConfig$1() {
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat, ScheduledExecutorService scheduledExecutorService, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback) {
        this(cameraCharacteristicsCompat, scheduledExecutorService, executor, controlUpdateCallback, new Quirks(new ArrayList()));
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat, ScheduledExecutorService scheduledExecutorService, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback, Quirks quirks) {
        this.mLock = new Object();
        SessionConfig.Builder builder = new SessionConfig.Builder();
        this.mSessionConfigBuilder = builder;
        this.mUseCount = 0;
        this.mIsTorchOn = false;
        this.mFlashMode = 2;
        this.mNextSessionUpdateId = new AtomicLong(0);
        this.mFlashModeChangeSessionUpdateFuture = Futures.immediateFuture(null);
        this.mTemplate = 1;
        this.mCurrentSessionUpdateId = 0;
        CameraCaptureCallbackSet cameraCaptureCallbackSet = new CameraCaptureCallbackSet();
        this.mCameraCaptureCallbackSet = cameraCaptureCallbackSet;
        this.mCameraCharacteristics = cameraCharacteristicsCompat;
        this.mControlUpdateCallback = controlUpdateCallback;
        this.mExecutor = executor;
        this.mVideoUsageControl = new VideoUsageControl(executor);
        CameraControlSessionCallback cameraControlSessionCallback = new CameraControlSessionCallback(executor);
        this.mSessionCallback = cameraControlSessionCallback;
        builder.setTemplateType(this.mTemplate);
        builder.addRepeatingCameraCaptureCallback(CaptureCallbackContainer.create(cameraControlSessionCallback));
        builder.addRepeatingCameraCaptureCallback(cameraCaptureCallbackSet);
        this.mExposureControl = new ExposureControl(this, cameraCharacteristicsCompat, executor);
        this.mFocusMeteringControl = new FocusMeteringControl(this, scheduledExecutorService, executor, quirks);
        this.mZoomControl = new ZoomControl(this, cameraCharacteristicsCompat, executor);
        this.mTorchControl = new TorchControl(this, cameraCharacteristicsCompat, executor);
        this.mZslControl = new ZslControlImpl(cameraCharacteristicsCompat);
        this.mAutoFlashAEModeDisabler = new AutoFlashAEModeDisabler(quirks);
        this.mCamera2CameraControl = new Camera2CameraControl(this, executor);
        this.mCamera2CapturePipeline = new Camera2CapturePipeline(this, cameraCharacteristicsCompat, quirks, executor, scheduledExecutorService);
    }

    /* access modifiers changed from: package-private */
    public void incrementUseCount() {
        synchronized (this.mLock) {
            this.mUseCount++;
        }
    }

    /* access modifiers changed from: package-private */
    public void decrementUseCount() {
        synchronized (this.mLock) {
            int i = this.mUseCount;
            if (i != 0) {
                this.mUseCount = i - 1;
            } else {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getUseCount() {
        int i;
        synchronized (this.mLock) {
            i = this.mUseCount;
        }
        return i;
    }

    public ZoomControl getZoomControl() {
        return this.mZoomControl;
    }

    public FocusMeteringControl getFocusMeteringControl() {
        return this.mFocusMeteringControl;
    }

    public TorchControl getTorchControl() {
        return this.mTorchControl;
    }

    public ExposureControl getExposureControl() {
        return this.mExposureControl;
    }

    public ZslControl getZslControl() {
        return this.mZslControl;
    }

    public Camera2CameraControl getCamera2CameraControl() {
        return this.mCamera2CameraControl;
    }

    public void addInteropConfig(Config config) {
        this.mCamera2CameraControl.addCaptureRequestOptions(CaptureRequestOptions.Builder.from(config).build()).addListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
    }

    public void clearInteropConfig() {
        this.mCamera2CameraControl.clearCaptureRequestOptions().addListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda6(), CameraXExecutors.directExecutor());
    }

    public Config getInteropConfig() {
        return this.mCamera2CameraControl.getCamera2ImplConfig();
    }

    /* access modifiers changed from: package-private */
    public void setActive(boolean z) {
        Logger.d(TAG, "setActive: isActive = " + z);
        this.mFocusMeteringControl.setActive(z);
        this.mZoomControl.setActive(z);
        this.mTorchControl.setActive(z);
        this.mExposureControl.setActive(z);
        this.mCamera2CameraControl.setActive(z);
        if (!z) {
            this.mScreenFlash = null;
            this.mVideoUsageControl.resetDirectly();
        }
    }

    public void setPreviewAspectRatio(Rational rational) {
        this.mFocusMeteringControl.setPreviewAspectRatio(rational);
    }

    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.startFocusAndMetering(focusMeteringAction));
    }

    public ListenableFuture<Void> cancelFocusAndMetering() {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.cancelFocusAndMetering());
    }

    public ListenableFuture<Void> setZoomRatio(float f) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setZoomRatio(f));
    }

    public ListenableFuture<Void> setLinearZoom(float f) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setLinearZoom(f));
    }

    public int getFlashMode() {
        return this.mFlashMode;
    }

    public void setFlashMode(int i) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return;
        }
        this.mFlashMode = i;
        Logger.d(TAG, "setFlashMode: mFlashMode = " + this.mFlashMode);
        ZslControl zslControl = this.mZslControl;
        boolean z = true;
        if (!(this.mFlashMode == 1 || this.mFlashMode == 0)) {
            z = false;
        }
        zslControl.setZslDisabledByFlashMode(z);
        this.mFlashModeChangeSessionUpdateFuture = updateSessionConfigAsync();
    }

    public void setScreenFlash(ImageCapture.ScreenFlash screenFlash) {
        this.mScreenFlash = screenFlash;
    }

    public ImageCapture.ScreenFlash getScreenFlash() {
        return this.mScreenFlash;
    }

    public void addZslConfig(SessionConfig.Builder builder) {
        this.mZslControl.addZslConfig(builder);
    }

    public void setZslDisabledByUserCaseConfig(boolean z) {
        this.mZslControl.setZslDisabledByUserCaseConfig(z);
    }

    public boolean isZslDisabledByByUserCaseConfig() {
        return this.mZslControl.isZslDisabledByUserCaseConfig();
    }

    public ListenableFuture<Void> enableTorch(boolean z) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mTorchControl.enableTorch(z));
    }

    private ListenableFuture<Void> waitForSessionUpdateId(long j) {
        return CallbackToFutureAdapter.getFuture(new Camera2CameraControlImpl$$ExternalSyntheticLambda8(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$waitForSessionUpdateId$3$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ Object m17lambda$waitForSessionUpdateId$3$androidxcameracamera2internalCamera2CameraControlImpl(long j, CallbackToFutureAdapter.Completer completer) throws Exception {
        addCaptureResultListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda4(j, completer));
        return "waitForSessionUpdateId:" + j;
    }

    static /* synthetic */ boolean lambda$waitForSessionUpdateId$2(long j, CallbackToFutureAdapter.Completer completer, TotalCaptureResult totalCaptureResult) {
        if (!isSessionUpdated(totalCaptureResult, j)) {
            return false;
        }
        completer.set(null);
        return true;
    }

    static boolean isSessionUpdated(TotalCaptureResult totalCaptureResult, long j) {
        Long l;
        if (totalCaptureResult.getRequest() == null) {
            return false;
        }
        Object tag = totalCaptureResult.getRequest().getTag();
        if (!(tag instanceof TagBundle) || (l = (Long) ((TagBundle) tag).getTag(TAG_SESSION_UPDATE_ID)) == null || l.longValue() < j) {
            return false;
        }
        return true;
    }

    public ListenableFuture<Integer> setExposureCompensationIndex(int i) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return this.mExposureControl.setExposureCompensationIndex(i);
    }

    public ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return FutureChain.from(Futures.nonCancellationPropagating(this.mFlashModeChangeSessionUpdateFuture)).transformAsync(new Camera2CameraControlImpl$$ExternalSyntheticLambda9(this, list, i, getFlashMode(), i2), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$submitStillCaptureRequests$4$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m14lambda$submitStillCaptureRequests$4$androidxcameracamera2internalCamera2CameraControlImpl(List list, int i, int i2, int i3, Void voidR) throws Exception {
        return this.mCamera2CapturePipeline.submitStillCaptures(list, i, i2, i3);
    }

    public ListenableFuture<CameraCapturePipeline> getCameraCapturePipelineAsync(int i, int i2) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return FutureChain.from(Futures.nonCancellationPropagating(this.mFlashModeChangeSessionUpdateFuture)).transformAsync(new Camera2CameraControlImpl$$ExternalSyntheticLambda5(this, i, getFlashMode(), i2), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCameraCapturePipelineAsync$5$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m12lambda$getCameraCapturePipelineAsync$5$androidxcameracamera2internalCamera2CameraControlImpl(int i, int i2, int i3, Void voidR) throws Exception {
        return Futures.immediateFuture(this.mCamera2CapturePipeline.getCameraCapturePipeline(i, i2, i3));
    }

    public SessionConfig getSessionConfig() {
        this.mSessionConfigBuilder.setTemplateType(this.mTemplate);
        this.mSessionConfigBuilder.setImplementationOptions(getSessionOptions());
        this.mSessionConfigBuilder.addTag(TAG_SESSION_UPDATE_ID, Long.valueOf(this.mCurrentSessionUpdateId));
        return this.mSessionConfigBuilder.build();
    }

    /* access modifiers changed from: package-private */
    public void setTemplate(int i) {
        this.mTemplate = i;
        this.mFocusMeteringControl.setTemplate(i);
        this.mCamera2CapturePipeline.setTemplate(this.mTemplate);
    }

    /* access modifiers changed from: package-private */
    public void resetTemplate() {
        setTemplate(1);
    }

    private boolean isControlInUse() {
        return getUseCount() > 0;
    }

    public void updateSessionConfig() {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda2(this));
    }

    public ListenableFuture<Void> updateSessionConfigAsync() {
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControlImpl$$ExternalSyntheticLambda3(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSessionConfigAsync$7$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ Object m16lambda$updateSessionConfigAsync$7$androidxcameracamera2internalCamera2CameraControlImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda10(this, completer));
        return "updateSessionConfigAsync";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSessionConfigAsync$6$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m15lambda$updateSessionConfigAsync$6$androidxcameracamera2internalCamera2CameraControlImpl(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(waitForSessionUpdateId(updateSessionConfigSynchronous()), completer);
    }

    /* access modifiers changed from: package-private */
    public long updateSessionConfigSynchronous() {
        this.mCurrentSessionUpdateId = this.mNextSessionUpdateId.getAndIncrement();
        this.mControlUpdateCallback.onCameraControlUpdateSessionConfig();
        return this.mCurrentSessionUpdateId;
    }

    /* access modifiers changed from: package-private */
    public Rect getCropSensorRegion() {
        return this.mZoomControl.getCropSensorRegion();
    }

    public Rect getSensorRect() {
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (!"robolectric".equals(Build.FINGERPRINT) || rect != null) {
            return (Rect) Preconditions.checkNotNull(rect);
        }
        return new Rect(0, 0, 4000, 3000);
    }

    /* access modifiers changed from: package-private */
    public void removeCaptureResultListener(CaptureResultListener captureResultListener) {
        this.mSessionCallback.removeListener(captureResultListener);
    }

    /* access modifiers changed from: package-private */
    public void addCaptureResultListener(CaptureResultListener captureResultListener) {
        this.mSessionCallback.addListener(captureResultListener);
    }

    /* access modifiers changed from: package-private */
    public void addSessionCameraCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda7(this, executor, cameraCaptureCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addSessionCameraCaptureCallback$8$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m11lambda$addSessionCameraCaptureCallback$8$androidxcameracamera2internalCamera2CameraControlImpl(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.addCaptureCallback(executor, cameraCaptureCallback);
    }

    /* access modifiers changed from: package-private */
    public void removeSessionCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda0(this, cameraCaptureCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeSessionCameraCaptureCallback$9$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m13lambda$removeSessionCameraCaptureCallback$9$androidxcameracamera2internalCamera2CameraControlImpl(CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.removeCaptureCallback(cameraCaptureCallback);
    }

    /* access modifiers changed from: package-private */
    public void enableTorchInternal(boolean z) {
        this.mIsTorchOn = z;
        if (!z) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mTemplate);
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(1)));
            builder2.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 0);
            builder.addImplementationOptions(builder2.build());
            submitCaptureRequestsInternal(Collections.singletonList(builder.build()));
        }
        updateSessionConfigSynchronous();
    }

    /* access modifiers changed from: package-private */
    public boolean isTorchOn() {
        return this.mIsTorchOn;
    }

    /* access modifiers changed from: package-private */
    public void submitCaptureRequestsInternal(List<CaptureConfig> list) {
        this.mControlUpdateCallback.onCameraControlCaptureRequests(list);
    }

    /* access modifiers changed from: package-private */
    public Config getSessionOptions() {
        Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
        builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_MODE, 1, Config.OptionPriority.REQUIRED);
        this.mFocusMeteringControl.addFocusMeteringOptions(builder);
        this.mZoomControl.addZoomOption(builder);
        int i = this.mFocusMeteringControl.isExternalFlashAeModeEnabled() ? 5 : 1;
        if (this.mIsTorchOn) {
            builder.setCaptureRequestOptionWithPriority(CaptureRequest.FLASH_MODE, 2, Config.OptionPriority.REQUIRED);
        } else {
            int i2 = this.mFlashMode;
            if (i2 == 0) {
                i = this.mAutoFlashAEModeDisabler.getCorrectedAeMode(2);
            } else if (i2 == 1) {
                i = 3;
            } else if (i2 == 2) {
                i = 1;
            }
        }
        builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(i)), Config.OptionPriority.REQUIRED);
        builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(getSupportedAwbMode(1)), Config.OptionPriority.REQUIRED);
        this.mExposureControl.setCaptureRequestOption(builder);
        this.mCamera2CameraControl.applyOptionsToBuilder(builder);
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    public int getSupportedAfMode(int i) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(4, iArr)) {
            return 4;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getSupportedAeMode(int i) {
        return getSupportedAeMode(this.mCameraCharacteristics, i);
    }

    public static int getSupportedAeMode(CameraCharacteristicsCompat cameraCharacteristicsCompat, int i) {
        int[] iArr = (int[]) cameraCharacteristicsCompat.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    private int getSupportedAwbMode(int i) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    private static boolean isModeInList(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getMaxAfRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getMaxAeRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getMaxAwbRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public long getCurrentSessionUpdateId() {
        return this.mCurrentSessionUpdateId;
    }

    public void incrementVideoUsage() {
        this.mVideoUsageControl.incrementUsage();
    }

    public void decrementVideoUsage() {
        this.mVideoUsageControl.decrementUsage();
    }

    public boolean isInVideoUsage() {
        int usage = this.mVideoUsageControl.getUsage();
        Logger.d(TAG, "isInVideoUsage: mVideoUsageControl value = " + usage);
        return usage > 0;
    }

    static final class CameraControlSessionCallback extends CameraCaptureSession.CaptureCallback {
        private final Executor mExecutor;
        final Set<CaptureResultListener> mResultListeners = new HashSet();

        CameraControlSessionCallback(Executor executor) {
            this.mExecutor = executor;
        }

        /* access modifiers changed from: package-private */
        public void addListener(CaptureResultListener captureResultListener) {
            this.mResultListeners.add(captureResultListener);
        }

        /* access modifiers changed from: package-private */
        public void removeListener(CaptureResultListener captureResultListener) {
            this.mResultListeners.remove(captureResultListener);
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mExecutor.execute(new Camera2CameraControlImpl$CameraControlSessionCallback$$ExternalSyntheticLambda0(this, totalCaptureResult));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureCompleted$0$androidx-camera-camera2-internal-Camera2CameraControlImpl$CameraControlSessionCallback  reason: not valid java name */
        public /* synthetic */ void m18lambda$onCaptureCompleted$0$androidxcameracamera2internalCamera2CameraControlImpl$CameraControlSessionCallback(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (CaptureResultListener next : this.mResultListeners) {
                if (next.onCaptureResult(totalCaptureResult)) {
                    hashSet.add(next);
                }
            }
            if (!hashSet.isEmpty()) {
                this.mResultListeners.removeAll(hashSet);
            }
        }
    }

    static final class CameraCaptureCallbackSet extends CameraCaptureCallback {
        Map<CameraCaptureCallback, Executor> mCallbackExecutors = new ArrayMap();
        Set<CameraCaptureCallback> mCallbacks = new HashSet();

        CameraCaptureCallbackSet() {
        }

        /* access modifiers changed from: package-private */
        public void addCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
            this.mCallbacks.add(cameraCaptureCallback);
            this.mCallbackExecutors.put(cameraCaptureCallback, executor);
        }

        /* access modifiers changed from: package-private */
        public void removeCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCallbacks.remove(cameraCaptureCallback);
            this.mCallbackExecutors.remove(cameraCaptureCallback);
        }

        public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda2(next, i, cameraCaptureResult));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCompleted.", e);
                }
            }
        }

        public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda0(next, i, cameraCaptureFailure));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureFailed.", e);
                }
            }
        }

        public void onCaptureCancelled(int i) {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda1(next, i));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCancelled.", e);
                }
            }
        }
    }
}
