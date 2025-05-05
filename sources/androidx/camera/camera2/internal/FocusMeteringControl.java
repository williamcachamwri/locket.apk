package androidx.camera.camera2.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.util.Log;
import android.util.Rational;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.workaround.MeteringRegionCorrection;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class FocusMeteringControl {
    static final long AUTO_FOCUS_TIMEOUT_DURATION = 5000;
    private static final MeteringRectangle[] EMPTY_RECTANGLES = new MeteringRectangle[0];
    private static final String TAG = "FocusMeteringControl";
    private MeteringRectangle[] mAeRects;
    private MeteringRectangle[] mAfRects;
    private ScheduledFuture<?> mAutoCancelHandle;
    private ScheduledFuture<?> mAutoFocusTimeoutHandle;
    private MeteringRectangle[] mAwbRects;
    private final Camera2CameraControlImpl mCameraControl;
    Integer mCurrentAfState = 0;
    final Executor mExecutor;
    long mFocusTimeoutCounter = 0;
    private volatile boolean mIsActive = false;
    boolean mIsAutoFocusCompleted = false;
    private boolean mIsExternalFlashAeModeEnabled;
    boolean mIsFocusSuccessful = false;
    private boolean mIsInAfAutoMode = false;
    private final MeteringRegionCorrection mMeteringRegionCorrection;
    private volatile Rational mPreviewAspectRatio = null;
    CallbackToFutureAdapter.Completer<FocusMeteringResult> mRunningActionCompleter;
    CallbackToFutureAdapter.Completer<Void> mRunningCancelCompleter;
    private final ScheduledExecutorService mScheduler;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForAeMode;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForCancel = null;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForFocus = null;
    private int mTemplate = 1;

    FocusMeteringControl(Camera2CameraControlImpl camera2CameraControlImpl, ScheduledExecutorService scheduledExecutorService, Executor executor, Quirks quirks) {
        MeteringRectangle[] meteringRectangleArr = EMPTY_RECTANGLES;
        this.mAfRects = meteringRectangleArr;
        this.mAeRects = meteringRectangleArr;
        this.mAwbRects = meteringRectangleArr;
        this.mRunningActionCompleter = null;
        this.mRunningCancelCompleter = null;
        this.mIsExternalFlashAeModeEnabled = false;
        this.mSessionListenerForAeMode = null;
        this.mCameraControl = camera2CameraControlImpl;
        this.mExecutor = executor;
        this.mScheduler = scheduledExecutorService;
        this.mMeteringRegionCorrection = new MeteringRegionCorrection(quirks);
    }

    /* access modifiers changed from: package-private */
    public void setActive(boolean z) {
        if (z != this.mIsActive) {
            this.mIsActive = z;
            if (!this.mIsActive) {
                cancelFocusAndMeteringWithoutAsyncResult();
            }
        }
    }

    public void setPreviewAspectRatio(Rational rational) {
        this.mPreviewAspectRatio = rational;
    }

    private Rational getDefaultAspectRatio() {
        if (this.mPreviewAspectRatio != null) {
            return this.mPreviewAspectRatio;
        }
        Rect cropSensorRegion = this.mCameraControl.getCropSensorRegion();
        return new Rational(cropSensorRegion.width(), cropSensorRegion.height());
    }

    /* access modifiers changed from: package-private */
    public void setTemplate(int i) {
        this.mTemplate = i;
    }

    /* access modifiers changed from: package-private */
    public void addFocusMeteringOptions(Camera2ImplConfig.Builder builder) {
        int i;
        if (this.mIsInAfAutoMode) {
            i = 1;
        } else {
            i = getDefaultAfMode();
        }
        builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(this.mCameraControl.getSupportedAfMode(i)), Config.OptionPriority.REQUIRED);
        if (this.mAfRects.length != 0) {
            builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AF_REGIONS, this.mAfRects, Config.OptionPriority.REQUIRED);
        }
        if (this.mAeRects.length != 0) {
            builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AE_REGIONS, this.mAeRects, Config.OptionPriority.REQUIRED);
        }
        if (this.mAwbRects.length != 0) {
            builder.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AWB_REGIONS, this.mAwbRects, Config.OptionPriority.REQUIRED);
        }
    }

    private static boolean isValid(MeteringPoint meteringPoint) {
        return meteringPoint.getX() >= 0.0f && meteringPoint.getX() <= 1.0f && meteringPoint.getY() >= 0.0f && meteringPoint.getY() <= 1.0f;
    }

    private static PointF getFovAdjustedPoint(MeteringPoint meteringPoint, Rational rational, Rational rational2, int i, MeteringRegionCorrection meteringRegionCorrection) {
        if (meteringPoint.getSurfaceAspectRatio() != null) {
            rational2 = meteringPoint.getSurfaceAspectRatio();
        }
        PointF correctedPoint = meteringRegionCorrection.getCorrectedPoint(meteringPoint, i);
        if (!rational2.equals(rational)) {
            if (rational2.compareTo(rational) > 0) {
                float doubleValue = (float) (rational2.doubleValue() / rational.doubleValue());
                correctedPoint.y = (((float) ((((double) doubleValue) - 1.0d) / 2.0d)) + correctedPoint.y) * (1.0f / doubleValue);
            } else {
                float doubleValue2 = (float) (rational.doubleValue() / rational2.doubleValue());
                correctedPoint.x = (((float) ((((double) doubleValue2) - 1.0d) / 2.0d)) + correctedPoint.x) * (1.0f / doubleValue2);
            }
        }
        return correctedPoint;
    }

    private static MeteringRectangle getMeteringRect(MeteringPoint meteringPoint, PointF pointF, Rect rect) {
        int width = (int) (((float) rect.left) + (pointF.x * ((float) rect.width())));
        int height = (int) (((float) rect.top) + (pointF.y * ((float) rect.height())));
        int size = ((int) (meteringPoint.getSize() * ((float) rect.width()))) / 2;
        int size2 = ((int) (meteringPoint.getSize() * ((float) rect.height()))) / 2;
        Rect rect2 = new Rect(width - size, height - size2, width + size, height + size2);
        rect2.left = rangeLimit(rect2.left, rect.right, rect.left);
        rect2.right = rangeLimit(rect2.right, rect.right, rect.left);
        rect2.top = rangeLimit(rect2.top, rect.bottom, rect.top);
        rect2.bottom = rangeLimit(rect2.bottom, rect.bottom, rect.top);
        return new MeteringRectangle(rect2, 1000);
    }

    private static int rangeLimit(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction) {
        return startFocusAndMetering(focusMeteringAction, 5000);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction, long j) {
        return CallbackToFutureAdapter.getFuture(new FocusMeteringControl$$ExternalSyntheticLambda8(this, focusMeteringAction, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startFocusAndMetering$1$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ Object m78lambda$startFocusAndMetering$1$androidxcameracamera2internalFocusMeteringControl(FocusMeteringAction focusMeteringAction, long j, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda9(this, completer, focusMeteringAction, j));
        return "startFocusAndMetering";
    }

    private List<MeteringRectangle> getMeteringRectangles(List<MeteringPoint> list, int i, Rational rational, Rect rect, int i2) {
        if (list.isEmpty() || i == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Rational rational2 = new Rational(rect.width(), rect.height());
        for (MeteringPoint next : list) {
            if (arrayList.size() == i) {
                break;
            } else if (isValid(next)) {
                MeteringRectangle meteringRect = getMeteringRect(next, getFovAdjustedPoint(next, rational2, rational, i2, this.mMeteringRegionCorrection), rect);
                if (!(meteringRect.getWidth() == 0 || meteringRect.getHeight() == 0)) {
                    arrayList.add(meteringRect);
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: startFocusAndMeteringInternal */
    public void m77lambda$startFocusAndMetering$0$androidxcameracamera2internalFocusMeteringControl(CallbackToFutureAdapter.Completer<FocusMeteringResult> completer, FocusMeteringAction focusMeteringAction, long j) {
        if (!this.mIsActive) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        Rect cropSensorRegion = this.mCameraControl.getCropSensorRegion();
        Rational defaultAspectRatio = getDefaultAspectRatio();
        Rational rational = defaultAspectRatio;
        Rect rect = cropSensorRegion;
        List<MeteringRectangle> meteringRectangles = getMeteringRectangles(focusMeteringAction.getMeteringPointsAf(), this.mCameraControl.getMaxAfRegionCount(), rational, rect, 1);
        List<MeteringRectangle> meteringRectangles2 = getMeteringRectangles(focusMeteringAction.getMeteringPointsAe(), this.mCameraControl.getMaxAeRegionCount(), rational, rect, 2);
        List<MeteringRectangle> meteringRectangles3 = getMeteringRectangles(focusMeteringAction.getMeteringPointsAwb(), this.mCameraControl.getMaxAwbRegionCount(), rational, rect, 4);
        if (!meteringRectangles.isEmpty() || !meteringRectangles2.isEmpty() || !meteringRectangles3.isEmpty()) {
            failActionFuture("Cancelled by another startFocusAndMetering()");
            failCancelFuture("Cancelled by another startFocusAndMetering()");
            disableAutoCancel();
            this.mRunningActionCompleter = completer;
            MeteringRectangle[] meteringRectangleArr = EMPTY_RECTANGLES;
            executeMeteringAction((MeteringRectangle[]) meteringRectangles.toArray(meteringRectangleArr), (MeteringRectangle[]) meteringRectangles2.toArray(meteringRectangleArr), (MeteringRectangle[]) meteringRectangles3.toArray(meteringRectangleArr), focusMeteringAction, j);
            return;
        }
        completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
    }

    /* access modifiers changed from: package-private */
    public void triggerAf(final CallbackToFutureAdapter.Completer<CameraCaptureResult> completer, boolean z) {
        if (this.mIsActive) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mTemplate);
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            if (z) {
                builder2.setCaptureRequestOptionWithPriority(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(this.mCameraControl.getSupportedAeMode(1)), Config.OptionPriority.HIGH_PRIORITY_REQUIRED);
            }
            builder.addImplementationOptions(builder2.build());
            builder.addCameraCaptureCallback(new CameraCaptureCallback() {
                public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                    CallbackToFutureAdapter.Completer completer = completer;
                    if (completer != null) {
                        completer.set(cameraCaptureResult);
                    }
                }

                public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
                    CallbackToFutureAdapter.Completer completer = completer;
                    if (completer != null) {
                        completer.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
                    }
                }

                public void onCaptureCancelled(int i) {
                    CallbackToFutureAdapter.Completer completer = completer;
                    if (completer != null) {
                        completer.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                    }
                }
            });
            this.mCameraControl.submitCaptureRequestsInternal(Collections.singletonList(builder.build()));
        } else if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> triggerAePrecapture() {
        return CallbackToFutureAdapter.getFuture(new FocusMeteringControl$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$triggerAePrecapture$3$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ Object m80lambda$triggerAePrecapture$3$androidxcameracamera2internalFocusMeteringControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda12(this, completer));
        return "triggerAePrecapture";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: triggerAePrecapture */
    public void m79lambda$triggerAePrecapture$2$androidxcameracamera2internalFocusMeteringControl(final CallbackToFutureAdapter.Completer<Void> completer) {
        Logger.d(TAG, "triggerAePrecapture");
        if (this.mIsActive) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mTemplate);
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
            builder.addImplementationOptions(builder2.build());
            builder.addCameraCaptureCallback(new CameraCaptureCallback() {
                public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                    if (completer != null) {
                        Logger.d(FocusMeteringControl.TAG, "triggerAePrecapture: triggering capture request completed");
                        completer.set(null);
                    }
                }

                public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
                    CallbackToFutureAdapter.Completer completer = completer;
                    if (completer != null) {
                        completer.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
                    }
                }

                public void onCaptureCancelled(int i) {
                    CallbackToFutureAdapter.Completer completer = completer;
                    if (completer != null) {
                        completer.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                    }
                }
            });
            this.mCameraControl.submitCaptureRequestsInternal(Collections.singletonList(builder.build()));
        } else if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
        }
    }

    /* access modifiers changed from: package-private */
    public void cancelAfAeTrigger(boolean z, boolean z2) {
        if (this.mIsActive) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setUseRepeatingSurface(true);
            builder.setTemplateType(this.mTemplate);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            if (z) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            }
            if (z2) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
            }
            builder.addImplementationOptions(builder2.build());
            this.mCameraControl.submitCaptureRequestsInternal(Collections.singletonList(builder.build()));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isExternalFlashAeModeEnabled() {
        return this.mIsExternalFlashAeModeEnabled;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> enableExternalFlashAeMode(boolean z) {
        if (Build.VERSION.SDK_INT < 28) {
            Log.d(TAG, "CONTROL_AE_MODE_ON_EXTERNAL_FLASH is not supported in API " + Build.VERSION.SDK_INT);
            return Futures.immediateFuture(null);
        } else if (this.mCameraControl.getSupportedAeMode(5) != 5) {
            Log.d(TAG, "CONTROL_AE_MODE_ON_EXTERNAL_FLASH is not supported in this device");
            return Futures.immediateFuture(null);
        } else {
            Log.d(TAG, "enableExternalFlashAeMode: CONTROL_AE_MODE_ON_EXTERNAL_FLASH supported");
            return CallbackToFutureAdapter.getFuture(new FocusMeteringControl$$ExternalSyntheticLambda10(this, z));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enableExternalFlashAeMode$5$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ Object m70lambda$enableExternalFlashAeMode$5$androidxcameracamera2internalFocusMeteringControl(boolean z, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda0(this, z, completer));
        return "enableExternalFlashAeMode";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enableExternalFlashAeMode$4$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ void m69lambda$enableExternalFlashAeMode$4$androidxcameracamera2internalFocusMeteringControl(boolean z, CallbackToFutureAdapter.Completer completer) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForAeMode);
        this.mIsExternalFlashAeModeEnabled = z;
        enableExternalFlashAeMode((CallbackToFutureAdapter.Completer<Void>) completer);
    }

    private void enableExternalFlashAeMode(CallbackToFutureAdapter.Completer<Void> completer) {
        if (this.mIsActive) {
            FocusMeteringControl$$ExternalSyntheticLambda14 focusMeteringControl$$ExternalSyntheticLambda14 = new FocusMeteringControl$$ExternalSyntheticLambda14(this, this.mCameraControl.updateSessionConfigSynchronous(), completer);
            this.mSessionListenerForAeMode = focusMeteringControl$$ExternalSyntheticLambda14;
            this.mCameraControl.addCaptureResultListener(focusMeteringControl$$ExternalSyntheticLambda14);
        } else if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enableExternalFlashAeMode$6$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ boolean m71lambda$enableExternalFlashAeMode$6$androidxcameracamera2internalFocusMeteringControl(long j, CallbackToFutureAdapter.Completer completer, TotalCaptureResult totalCaptureResult) {
        boolean z = ((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_MODE)).intValue() == 5;
        Logger.d(TAG, "enableExternalFlashAeMode: isAeModeExternalFlash = " + z);
        if (z != this.mIsExternalFlashAeModeEnabled || !Camera2CameraControlImpl.isSessionUpdated(totalCaptureResult, j)) {
            return false;
        }
        Logger.d(TAG, "enableExternalFlashAeMode: session updated with isAeModeExternalFlash = " + z);
        if (completer != null) {
            completer.set(null);
        }
        return true;
    }

    private void disableAutoCancel() {
        ScheduledFuture<?> scheduledFuture = this.mAutoCancelHandle;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mAutoCancelHandle = null;
        }
    }

    private void clearAutoFocusTimeoutHandle() {
        ScheduledFuture<?> scheduledFuture = this.mAutoFocusTimeoutHandle;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mAutoFocusTimeoutHandle = null;
        }
    }

    /* access modifiers changed from: package-private */
    public int getDefaultAfMode() {
        return this.mTemplate != 3 ? 4 : 3;
    }

    private boolean isAfModeSupported() {
        return this.mCameraControl.getSupportedAfMode(1) == 1;
    }

    /* access modifiers changed from: package-private */
    public void completeActionFuture(boolean z) {
        clearAutoFocusTimeoutHandle();
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.mRunningActionCompleter;
        if (completer != null) {
            completer.set(FocusMeteringResult.create(z));
            this.mRunningActionCompleter = null;
        }
    }

    private void failActionFuture(String str) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForFocus);
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.mRunningActionCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.mRunningActionCompleter = null;
        }
    }

    private void failCancelFuture(String str) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForCancel);
        CallbackToFutureAdapter.Completer<Void> completer = this.mRunningCancelCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.mRunningCancelCompleter = null;
        }
    }

    private void completeCancelFuture() {
        CallbackToFutureAdapter.Completer<Void> completer = this.mRunningCancelCompleter;
        if (completer != null) {
            completer.set(null);
            this.mRunningCancelCompleter = null;
        }
    }

    private void executeMeteringAction(MeteringRectangle[] meteringRectangleArr, MeteringRectangle[] meteringRectangleArr2, MeteringRectangle[] meteringRectangleArr3, FocusMeteringAction focusMeteringAction, long j) {
        long j2;
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForFocus);
        disableAutoCancel();
        clearAutoFocusTimeoutHandle();
        this.mAfRects = meteringRectangleArr;
        this.mAeRects = meteringRectangleArr2;
        this.mAwbRects = meteringRectangleArr3;
        if (shouldTriggerAF()) {
            this.mIsInAfAutoMode = true;
            this.mIsAutoFocusCompleted = false;
            this.mIsFocusSuccessful = false;
            j2 = this.mCameraControl.updateSessionConfigSynchronous();
            triggerAf((CallbackToFutureAdapter.Completer<CameraCaptureResult>) null, true);
        } else {
            this.mIsInAfAutoMode = false;
            this.mIsAutoFocusCompleted = true;
            this.mIsFocusSuccessful = false;
            j2 = this.mCameraControl.updateSessionConfigSynchronous();
        }
        this.mCurrentAfState = 0;
        FocusMeteringControl$$ExternalSyntheticLambda1 focusMeteringControl$$ExternalSyntheticLambda1 = new FocusMeteringControl$$ExternalSyntheticLambda1(this, isAfModeSupported(), j2);
        this.mSessionListenerForFocus = focusMeteringControl$$ExternalSyntheticLambda1;
        this.mCameraControl.addCaptureResultListener(focusMeteringControl$$ExternalSyntheticLambda1);
        long j3 = this.mFocusTimeoutCounter + 1;
        this.mFocusTimeoutCounter = j3;
        this.mAutoFocusTimeoutHandle = this.mScheduler.schedule(new FocusMeteringControl$$ExternalSyntheticLambda2(this, j3), j, TimeUnit.MILLISECONDS);
        if (focusMeteringAction.isAutoCancelEnabled()) {
            this.mAutoCancelHandle = this.mScheduler.schedule(new FocusMeteringControl$$ExternalSyntheticLambda3(this, j3), focusMeteringAction.getAutoCancelDurationInMillis(), TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeMeteringAction$7$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ boolean m74lambda$executeMeteringAction$7$androidxcameracamera2internalFocusMeteringControl(boolean z, long j, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        if (shouldTriggerAF()) {
            if (!z || num == null) {
                this.mIsFocusSuccessful = true;
                this.mIsAutoFocusCompleted = true;
            } else if (this.mCurrentAfState.intValue() == 3) {
                if (num.intValue() == 4) {
                    this.mIsFocusSuccessful = true;
                    this.mIsAutoFocusCompleted = true;
                } else if (num.intValue() == 5) {
                    this.mIsFocusSuccessful = false;
                    this.mIsAutoFocusCompleted = true;
                }
            }
        }
        if (!this.mIsAutoFocusCompleted || !Camera2CameraControlImpl.isSessionUpdated(totalCaptureResult, j)) {
            if (!this.mCurrentAfState.equals(num) && num != null) {
                this.mCurrentAfState = num;
            }
            return false;
        }
        completeActionFuture(this.mIsFocusSuccessful);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeMeteringAction$9$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ void m76lambda$executeMeteringAction$9$androidxcameracamera2internalFocusMeteringControl(long j) {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda5(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeMeteringAction$8$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ void m75lambda$executeMeteringAction$8$androidxcameracamera2internalFocusMeteringControl(long j) {
        if (j == this.mFocusTimeoutCounter) {
            this.mIsFocusSuccessful = false;
            completeActionFuture(false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeMeteringAction$11$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ void m73lambda$executeMeteringAction$11$androidxcameracamera2internalFocusMeteringControl(long j) {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda6(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$executeMeteringAction$10$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ void m72lambda$executeMeteringAction$10$androidxcameracamera2internalFocusMeteringControl(long j) {
        if (j == this.mFocusTimeoutCounter) {
            cancelFocusAndMeteringWithoutAsyncResult();
        }
    }

    private boolean shouldTriggerAF() {
        return this.mAfRects.length > 0;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> cancelFocusAndMetering() {
        return CallbackToFutureAdapter.getFuture(new FocusMeteringControl$$ExternalSyntheticLambda11(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$cancelFocusAndMetering$13$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ Object m67lambda$cancelFocusAndMetering$13$androidxcameracamera2internalFocusMeteringControl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new FocusMeteringControl$$ExternalSyntheticLambda13(this, completer));
        return "cancelFocusAndMetering";
    }

    /* access modifiers changed from: package-private */
    public void cancelFocusAndMeteringWithoutAsyncResult() {
        m66lambda$cancelFocusAndMetering$12$androidxcameracamera2internalFocusMeteringControl((CallbackToFutureAdapter.Completer<Void>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cancelFocusAndMeteringInternal */
    public void m66lambda$cancelFocusAndMetering$12$androidxcameracamera2internalFocusMeteringControl(CallbackToFutureAdapter.Completer<Void> completer) {
        failCancelFuture("Cancelled by another cancelFocusAndMetering()");
        failActionFuture("Cancelled by cancelFocusAndMetering()");
        this.mRunningCancelCompleter = completer;
        disableAutoCancel();
        clearAutoFocusTimeoutHandle();
        if (shouldTriggerAF()) {
            cancelAfAeTrigger(true, false);
        }
        MeteringRectangle[] meteringRectangleArr = EMPTY_RECTANGLES;
        this.mAfRects = meteringRectangleArr;
        this.mAeRects = meteringRectangleArr;
        this.mAwbRects = meteringRectangleArr;
        this.mIsInAfAutoMode = false;
        long updateSessionConfigSynchronous = this.mCameraControl.updateSessionConfigSynchronous();
        if (this.mRunningCancelCompleter != null) {
            FocusMeteringControl$$ExternalSyntheticLambda4 focusMeteringControl$$ExternalSyntheticLambda4 = new FocusMeteringControl$$ExternalSyntheticLambda4(this, this.mCameraControl.getSupportedAfMode(getDefaultAfMode()), updateSessionConfigSynchronous);
            this.mSessionListenerForCancel = focusMeteringControl$$ExternalSyntheticLambda4;
            this.mCameraControl.addCaptureResultListener(focusMeteringControl$$ExternalSyntheticLambda4);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$cancelFocusAndMeteringInternal$14$androidx-camera-camera2-internal-FocusMeteringControl  reason: not valid java name */
    public /* synthetic */ boolean m68lambda$cancelFocusAndMeteringInternal$14$androidxcameracamera2internalFocusMeteringControl(int i, long j, TotalCaptureResult totalCaptureResult) {
        if (((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)).intValue() != i || !Camera2CameraControlImpl.isSessionUpdated(totalCaptureResult, j)) {
            return false;
        }
        completeCancelFuture();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isFocusMeteringSupported(FocusMeteringAction focusMeteringAction) {
        Rect cropSensorRegion = this.mCameraControl.getCropSensorRegion();
        Rational defaultAspectRatio = getDefaultAspectRatio();
        Rect rect = cropSensorRegion;
        return !getMeteringRectangles(focusMeteringAction.getMeteringPointsAf(), this.mCameraControl.getMaxAfRegionCount(), defaultAspectRatio, rect, 1).isEmpty() || !getMeteringRectangles(focusMeteringAction.getMeteringPointsAe(), this.mCameraControl.getMaxAeRegionCount(), defaultAspectRatio, rect, 2).isEmpty() || !getMeteringRectangles(focusMeteringAction.getMeteringPointsAwb(), this.mCameraControl.getMaxAwbRegionCount(), defaultAspectRatio, rect, 4).isEmpty();
    }
}
