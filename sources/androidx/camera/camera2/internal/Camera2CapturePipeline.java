package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.FlashAvailabilityChecker;
import androidx.camera.camera2.internal.compat.workaround.OverrideAeModeForStillCapture;
import androidx.camera.camera2.internal.compat.workaround.UseFlashModeTorchFor3aUpdate;
import androidx.camera.camera2.internal.compat.workaround.UseTorchAsFlash;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.ConvergenceUtils;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class Camera2CapturePipeline {
    private static final String TAG = "Camera2CapturePipeline";
    private final Camera2CameraControlImpl mCameraControl;
    private final Quirks mCameraQuirk;
    private final Executor mExecutor;
    private final boolean mHasFlashUnit;
    private final boolean mIsLegacyDevice;
    private final ScheduledExecutorService mScheduler;
    private int mTemplate = 1;
    private final UseTorchAsFlash mUseTorchAsFlash;

    interface PipelineTask {
        boolean isCaptureResultNeeded();

        void postCapture();

        ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult);
    }

    Camera2CapturePipeline(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Quirks quirks, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        boolean z = true;
        this.mCameraControl = camera2CameraControlImpl;
        Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        this.mIsLegacyDevice = (num == null || num.intValue() != 2) ? false : z;
        this.mExecutor = executor;
        this.mScheduler = scheduledExecutorService;
        this.mCameraQuirk = quirks;
        this.mUseTorchAsFlash = new UseTorchAsFlash(quirks);
        Objects.requireNonNull(cameraCharacteristicsCompat);
        this.mHasFlashUnit = FlashAvailabilityChecker.isFlashAvailable(new Camera2CameraInfoImpl$$ExternalSyntheticLambda0(cameraCharacteristicsCompat));
    }

    public void setTemplate(int i) {
        this.mTemplate = i;
    }

    public ListenableFuture<List<Void>> submitStillCaptures(List<CaptureConfig> list, int i, int i2, int i3) {
        return Futures.nonCancellationPropagating(createPipeline(i, i2, i3).executeCapture(list, i2));
    }

    /* access modifiers changed from: package-private */
    public Pipeline createPipeline(int i, int i2, int i3) {
        OverrideAeModeForStillCapture overrideAeModeForStillCapture = new OverrideAeModeForStillCapture(this.mCameraQuirk);
        Pipeline pipeline = new Pipeline(this.mTemplate, this.mExecutor, this.mScheduler, this.mCameraControl, this.mIsLegacyDevice, overrideAeModeForStillCapture);
        if (i == 0) {
            pipeline.addTask(new AfTask(this.mCameraControl));
        }
        if (i2 == 3) {
            pipeline.addTask(new ScreenFlashTask(this.mCameraControl, this.mExecutor, this.mScheduler, new UseFlashModeTorchFor3aUpdate(this.mCameraQuirk)));
        } else if (this.mHasFlashUnit) {
            if (isTorchAsFlash(i3)) {
                pipeline.addTask(new TorchTask(this.mCameraControl, i2, this.mExecutor, this.mScheduler, !this.mUseTorchAsFlash.shouldUseTorchAsFlash() && !this.mCameraControl.isInVideoUsage()));
            } else {
                pipeline.addTask(new AePreCaptureTask(this.mCameraControl, i2, overrideAeModeForStillCapture));
            }
        }
        Logger.d(TAG, "createPipeline: captureMode = " + i + ", flashMode = " + i2 + ", flashType = " + i3 + ", pipeline tasks = " + pipeline.mTasks);
        return pipeline;
    }

    /* access modifiers changed from: package-private */
    public CameraCapturePipeline getCameraCapturePipeline(int i, int i2, int i3) {
        return new CameraCapturePipelineImpl(createPipeline(i, i2, i3), this.mExecutor, i2);
    }

    static class CameraCapturePipelineImpl implements CameraCapturePipeline {
        private final Executor mExecutor;
        private int mFlashMode;
        private final Pipeline mPipelineDelegate;

        static /* synthetic */ Void lambda$invokePreCapture$0(TotalCaptureResult totalCaptureResult) {
            return null;
        }

        CameraCapturePipelineImpl(Pipeline pipeline, Executor executor, int i) {
            this.mPipelineDelegate = pipeline;
            this.mExecutor = executor;
            this.mFlashMode = i;
        }

        public ListenableFuture<Void> invokePreCapture() {
            Logger.d(Camera2CapturePipeline.TAG, "invokePreCapture");
            return FutureChain.from(this.mPipelineDelegate.executePreCapture(this.mFlashMode)).transform(new Camera2CapturePipeline$CameraCapturePipelineImpl$$ExternalSyntheticLambda1(), this.mExecutor);
        }

        public ListenableFuture<Void> invokePostCapture() {
            return CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$CameraCapturePipelineImpl$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$invokePostCapture$1$androidx-camera-camera2-internal-Camera2CapturePipeline$CameraCapturePipelineImpl  reason: not valid java name */
        public /* synthetic */ Object m43lambda$invokePostCapture$1$androidxcameracamera2internalCamera2CapturePipeline$CameraCapturePipelineImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mPipelineDelegate.executePostCapture();
            completer.set(null);
            return "invokePostCaptureFuture";
        }
    }

    static class Pipeline {
        private static final long CHECK_3A_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(1);
        private static final long CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(5);
        private final Camera2CameraControlImpl mCameraControl;
        private final Executor mExecutor;
        private final boolean mIsLegacyDevice;
        private final OverrideAeModeForStillCapture mOverrideAeModeForStillCapture;
        private final PipelineTask mPipelineSubTask = new PipelineTask() {
            public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
                ArrayList arrayList = new ArrayList();
                for (PipelineTask preCapture : Pipeline.this.mTasks) {
                    arrayList.add(preCapture.preCapture(totalCaptureResult));
                }
                return Futures.transform(Futures.allAsList(arrayList), new Camera2CapturePipeline$Pipeline$1$$ExternalSyntheticLambda0(), CameraXExecutors.directExecutor());
            }

            public boolean isCaptureResultNeeded() {
                for (PipelineTask isCaptureResultNeeded : Pipeline.this.mTasks) {
                    if (isCaptureResultNeeded.isCaptureResultNeeded()) {
                        return true;
                    }
                }
                return false;
            }

            public void postCapture() {
                for (PipelineTask postCapture : Pipeline.this.mTasks) {
                    postCapture.postCapture();
                }
            }
        };
        private final ScheduledExecutorService mScheduler;
        final List<PipelineTask> mTasks = new ArrayList();
        private final int mTemplate;
        private long mTimeout3A = CHECK_3A_TIMEOUT_IN_NS;

        Pipeline(int i, Executor executor, ScheduledExecutorService scheduledExecutorService, Camera2CameraControlImpl camera2CameraControlImpl, boolean z, OverrideAeModeForStillCapture overrideAeModeForStillCapture) {
            this.mTemplate = i;
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
            this.mCameraControl = camera2CameraControlImpl;
            this.mIsLegacyDevice = z;
            this.mOverrideAeModeForStillCapture = overrideAeModeForStillCapture;
        }

        /* access modifiers changed from: package-private */
        public void addTask(PipelineTask pipelineTask) {
            this.mTasks.add(pipelineTask);
        }

        private void setTimeout3A(long j) {
            this.mTimeout3A = j;
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<List<Void>> executeCapture(List<CaptureConfig> list, int i) {
            FutureChain<T> transformAsync = FutureChain.from(executePreCapture(i)).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda3(this, list, i), this.mExecutor);
            transformAsync.addListener(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda4(this), this.mExecutor);
            return transformAsync;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executeCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m44lambda$executeCapture$0$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(List list, int i, TotalCaptureResult totalCaptureResult) throws Exception {
            return submitConfigsInternal(list, i);
        }

        public ListenableFuture<TotalCaptureResult> executePreCapture(int i) {
            ListenableFuture<TotalCaptureResult> listenableFuture;
            ListenableFuture<TotalCaptureResult> immediateFuture = Futures.immediateFuture(null);
            if (this.mTasks.isEmpty()) {
                return immediateFuture;
            }
            if (this.mPipelineSubTask.isCaptureResultNeeded()) {
                listenableFuture = Camera2CapturePipeline.waitForResult(this.mCameraControl, (ResultListener.Checker) null);
            } else {
                listenableFuture = Futures.immediateFuture(null);
            }
            return FutureChain.from(listenableFuture).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda1(this, i), this.mExecutor).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda2(this), this.mExecutor);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executePreCapture$1$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m45lambda$executePreCapture$1$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(int i, TotalCaptureResult totalCaptureResult) throws Exception {
            if (Camera2CapturePipeline.isFlashRequired(i, totalCaptureResult)) {
                setTimeout3A(CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS);
            }
            return this.mPipelineSubTask.preCapture(totalCaptureResult);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executePreCapture$3$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m46lambda$executePreCapture$3$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(Boolean bool) throws Exception {
            if (Boolean.TRUE.equals(bool)) {
                return Camera2CapturePipeline.waitForResult(this.mTimeout3A, this.mScheduler, this.mCameraControl, new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda5());
            }
            return Futures.immediateFuture(null);
        }

        public void executePostCapture() {
            this.mPipelineSubTask.postCapture();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0078 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.util.concurrent.ListenableFuture<java.util.List<java.lang.Void>> submitConfigsInternal(java.util.List<androidx.camera.core.impl.CaptureConfig> r7, int r8) {
            /*
                r6 = this;
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Iterator r7 = r7.iterator()
            L_0x000e:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L_0x008c
                java.lang.Object r2 = r7.next()
                androidx.camera.core.impl.CaptureConfig r2 = (androidx.camera.core.impl.CaptureConfig) r2
                androidx.camera.core.impl.CaptureConfig$Builder r3 = androidx.camera.core.impl.CaptureConfig.Builder.from(r2)
                int r4 = r2.getTemplateType()
                r5 = 5
                if (r4 != r5) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                boolean r4 = r4.isZslDisabledByFlashMode()
                if (r4 != 0) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                boolean r4 = r4.isZslDisabledByUserCaseConfig()
                if (r4 != 0) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                androidx.camera.core.ImageProxy r4 = r4.dequeueImageFromBuffer()
                if (r4 == 0) goto L_0x0057
                androidx.camera.camera2.internal.Camera2CameraControlImpl r5 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r5 = r5.getZslControl()
                boolean r5 = r5.enqueueImageToImageWriter(r4)
                if (r5 == 0) goto L_0x0057
                r5 = 1
                goto L_0x0058
            L_0x0057:
                r5 = 0
            L_0x0058:
                if (r5 == 0) goto L_0x0063
                androidx.camera.core.ImageInfo r4 = r4.getImageInfo()
                androidx.camera.core.impl.CameraCaptureResult r4 = androidx.camera.core.impl.CameraCaptureResults.retrieveCameraCaptureResult(r4)
                goto L_0x0064
            L_0x0063:
                r4 = 0
            L_0x0064:
                if (r4 == 0) goto L_0x006a
                r3.setCameraCaptureResult(r4)
                goto L_0x006d
            L_0x006a:
                r6.applyStillCaptureTemplate(r3, r2)
            L_0x006d:
                androidx.camera.camera2.internal.compat.workaround.OverrideAeModeForStillCapture r2 = r6.mOverrideAeModeForStillCapture
                boolean r2 = r2.shouldSetAeModeAlwaysFlash(r8)
                if (r2 == 0) goto L_0x0078
                r6.applyAeModeQuirk(r3)
            L_0x0078:
                androidx.camera.camera2.internal.Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda0 r2 = new androidx.camera.camera2.internal.Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda0
                r2.<init>(r6, r3)
                com.google.common.util.concurrent.ListenableFuture r2 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r2)
                r0.add(r2)
                androidx.camera.core.impl.CaptureConfig r2 = r3.build()
                r1.add(r2)
                goto L_0x000e
            L_0x008c:
                androidx.camera.camera2.internal.Camera2CameraControlImpl r7 = r6.mCameraControl
                r7.submitCaptureRequestsInternal(r1)
                com.google.common.util.concurrent.ListenableFuture r7 = androidx.camera.core.impl.utils.futures.Futures.allAsList(r0)
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CapturePipeline.Pipeline.submitConfigsInternal(java.util.List, int):com.google.common.util.concurrent.ListenableFuture");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$submitConfigsInternal$4$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ Object m47lambda$submitConfigsInternal$4$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(CaptureConfig.Builder builder, final CallbackToFutureAdapter.Completer completer) throws Exception {
            builder.addCameraCaptureCallback(new CameraCaptureCallback() {
                public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                    completer.set(null);
                }

                public void onCaptureFailed(int i, CameraCaptureFailure cameraCaptureFailure) {
                    completer.setException(new ImageCaptureException(2, "Capture request failed with reason " + cameraCaptureFailure.getReason(), (Throwable) null));
                }

                public void onCaptureCancelled(int i) {
                    completer.setException(new ImageCaptureException(3, "Capture request is cancelled because camera is closed", (Throwable) null));
                }
            });
            return "submitStillCapture";
        }

        private void applyStillCaptureTemplate(CaptureConfig.Builder builder, CaptureConfig captureConfig) {
            int i;
            if (this.mTemplate != 3 || this.mIsLegacyDevice) {
                i = (captureConfig.getTemplateType() == -1 || captureConfig.getTemplateType() == 5) ? 2 : -1;
            } else {
                i = 4;
            }
            if (i != -1) {
                builder.setTemplateType(i);
            }
        }

        private void applyAeModeQuirk(CaptureConfig.Builder builder) {
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, 3);
            builder.addImplementationOptions(builder2.build());
        }
    }

    static ListenableFuture<TotalCaptureResult> waitForResult(long j, ScheduledExecutorService scheduledExecutorService, Camera2CameraControlImpl camera2CameraControlImpl, ResultListener.Checker checker) {
        return Futures.makeTimeoutFuture(TimeUnit.NANOSECONDS.toMillis(j), scheduledExecutorService, null, true, waitForResult(camera2CameraControlImpl, checker));
    }

    static ListenableFuture<TotalCaptureResult> waitForResult(Camera2CameraControlImpl camera2CameraControlImpl, ResultListener.Checker checker) {
        ResultListener resultListener = new ResultListener(checker);
        camera2CameraControlImpl.addCaptureResultListener(resultListener);
        ListenableFuture<TotalCaptureResult> future = resultListener.getFuture();
        future.addListener(new Camera2CapturePipeline$$ExternalSyntheticLambda0(camera2CameraControlImpl, resultListener), camera2CameraControlImpl.mExecutor);
        return future;
    }

    /* access modifiers changed from: package-private */
    public static boolean is3AConverged(TotalCaptureResult totalCaptureResult, boolean z) {
        if (totalCaptureResult == null) {
            return false;
        }
        return ConvergenceUtils.is3AConverged(new Camera2CameraCaptureResult(totalCaptureResult), z);
    }

    static class AfTask implements PipelineTask {
        private final Camera2CameraControlImpl mCameraControl;
        private boolean mIsExecuted = false;

        public boolean isCaptureResultNeeded() {
            return true;
        }

        AfTask(Camera2CameraControlImpl camera2CameraControlImpl) {
            this.mCameraControl = camera2CameraControlImpl;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            Integer num;
            ListenableFuture<Boolean> immediateFuture = Futures.immediateFuture(true);
            if (totalCaptureResult == null || (num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)) == null) {
                return immediateFuture;
            }
            int intValue = num.intValue();
            if (intValue == 1 || intValue == 2) {
                Logger.d(Camera2CapturePipeline.TAG, "TriggerAf? AF mode auto");
                Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num2 != null && num2.intValue() == 0) {
                    Logger.d(Camera2CapturePipeline.TAG, "Trigger AF");
                    this.mIsExecuted = true;
                    this.mCameraControl.getFocusMeteringControl().triggerAf((CallbackToFutureAdapter.Completer<CameraCaptureResult>) null, false);
                }
            }
            return immediateFuture;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                Logger.d(Camera2CapturePipeline.TAG, "cancel TriggerAF");
                this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(true, false);
            }
        }
    }

    static class TorchTask implements PipelineTask {
        private static final long CHECK_3A_WITH_TORCH_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(2);
        private final Camera2CameraControlImpl mCameraControl;
        private final Executor mExecutor;
        private final int mFlashMode;
        private boolean mIsExecuted = false;
        private final ScheduledExecutorService mScheduler;
        private final boolean mTriggerAePrecapture;

        TorchTask(Camera2CameraControlImpl camera2CameraControlImpl, int i, Executor executor, ScheduledExecutorService scheduledExecutorService, boolean z) {
            this.mCameraControl = camera2CameraControlImpl;
            this.mFlashMode = i;
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
            this.mTriggerAePrecapture = z;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            Logger.d(Camera2CapturePipeline.TAG, "TorchTask#preCapture: isFlashRequired = " + Camera2CapturePipeline.isFlashRequired(this.mFlashMode, totalCaptureResult));
            if (Camera2CapturePipeline.isFlashRequired(this.mFlashMode, totalCaptureResult)) {
                if (this.mCameraControl.isTorchOn()) {
                    Logger.d(Camera2CapturePipeline.TAG, "Torch already on, not turn on");
                } else {
                    Logger.d(Camera2CapturePipeline.TAG, "Turn on torch");
                    this.mIsExecuted = true;
                    return FutureChain.from(CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda1(this))).transformAsync(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda2(this), this.mExecutor).transformAsync(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda3(this), this.mExecutor).transform(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda4(), CameraXExecutors.directExecutor());
                }
            }
            return Futures.immediateFuture(false);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$TorchTask  reason: not valid java name */
        public /* synthetic */ Object m57lambda$preCapture$0$androidxcameracamera2internalCamera2CapturePipeline$TorchTask(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCameraControl.getTorchControl().m94lambda$enableTorch$1$androidxcameracamera2internalTorchControl(completer, true);
            return "TorchOn";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$1$androidx-camera-camera2-internal-Camera2CapturePipeline$TorchTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m58lambda$preCapture$1$androidxcameracamera2internalCamera2CapturePipeline$TorchTask(Void voidR) throws Exception {
            if (this.mTriggerAePrecapture) {
                return this.mCameraControl.getFocusMeteringControl().triggerAePrecapture();
            }
            return Futures.immediateFuture(null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$3$androidx-camera-camera2-internal-Camera2CapturePipeline$TorchTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m59lambda$preCapture$3$androidxcameracamera2internalCamera2CapturePipeline$TorchTask(Void voidR) throws Exception {
            return Camera2CapturePipeline.waitForResult(CHECK_3A_WITH_TORCH_TIMEOUT_IN_NS, this.mScheduler, this.mCameraControl, new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda0());
        }

        static /* synthetic */ Boolean lambda$preCapture$4(TotalCaptureResult totalCaptureResult) {
            return false;
        }

        public boolean isCaptureResultNeeded() {
            return this.mFlashMode == 0;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                this.mCameraControl.getTorchControl().m94lambda$enableTorch$1$androidxcameracamera2internalTorchControl((CallbackToFutureAdapter.Completer<Void>) null, false);
                Logger.d(Camera2CapturePipeline.TAG, "Turning off torch");
                if (this.mTriggerAePrecapture) {
                    this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(false, true);
                }
            }
        }
    }

    static class AePreCaptureTask implements PipelineTask {
        private final Camera2CameraControlImpl mCameraControl;
        private final int mFlashMode;
        private boolean mIsExecuted = false;
        private final OverrideAeModeForStillCapture mOverrideAeModeForStillCapture;

        AePreCaptureTask(Camera2CameraControlImpl camera2CameraControlImpl, int i, OverrideAeModeForStillCapture overrideAeModeForStillCapture) {
            this.mCameraControl = camera2CameraControlImpl;
            this.mFlashMode = i;
            this.mOverrideAeModeForStillCapture = overrideAeModeForStillCapture;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            if (!Camera2CapturePipeline.isFlashRequired(this.mFlashMode, totalCaptureResult)) {
                return Futures.immediateFuture(false);
            }
            Logger.d(Camera2CapturePipeline.TAG, "Trigger AE");
            this.mIsExecuted = true;
            return FutureChain.from(CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$AePreCaptureTask$$ExternalSyntheticLambda0(this))).transform(new Camera2CapturePipeline$AePreCaptureTask$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$AePreCaptureTask  reason: not valid java name */
        public /* synthetic */ Object m42lambda$preCapture$0$androidxcameracamera2internalCamera2CapturePipeline$AePreCaptureTask(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCameraControl.getFocusMeteringControl().m79lambda$triggerAePrecapture$2$androidxcameracamera2internalFocusMeteringControl(completer);
            this.mOverrideAeModeForStillCapture.onAePrecaptureStarted();
            return "AePreCapture";
        }

        static /* synthetic */ Boolean lambda$preCapture$1(Void voidR) {
            return true;
        }

        public boolean isCaptureResultNeeded() {
            return this.mFlashMode == 0;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                Logger.d(Camera2CapturePipeline.TAG, "cancel TriggerAePreCapture");
                this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(false, true);
                this.mOverrideAeModeForStillCapture.onAePrecaptureFinished();
            }
        }
    }

    static class ScreenFlashTask implements PipelineTask {
        private static final long CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(2);
        private final Camera2CameraControlImpl mCameraControl;
        private final Executor mExecutor;
        private final ScheduledExecutorService mScheduler;
        private final ImageCapture.ScreenFlash mScreenFlash;
        private final UseFlashModeTorchFor3aUpdate mUseFlashModeTorchFor3aUpdate;

        public boolean isCaptureResultNeeded() {
            return false;
        }

        ScreenFlashTask(Camera2CameraControlImpl camera2CameraControlImpl, Executor executor, ScheduledExecutorService scheduledExecutorService, UseFlashModeTorchFor3aUpdate useFlashModeTorchFor3aUpdate) {
            this.mCameraControl = camera2CameraControlImpl;
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
            this.mUseFlashModeTorchFor3aUpdate = useFlashModeTorchFor3aUpdate;
            this.mScreenFlash = (ImageCapture.ScreenFlash) Objects.requireNonNull(camera2CameraControlImpl.getScreenFlash());
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            Logger.d(Camera2CapturePipeline.TAG, "ScreenFlashTask#preCapture");
            AtomicReference atomicReference = new AtomicReference();
            return FutureChain.from(CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda11(this, atomicReference))).transformAsync(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda12(this), this.mExecutor).transformAsync(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda13(this), this.mExecutor).transformAsync(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda1(this, CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda10(atomicReference))), this.mExecutor).transformAsync(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda2(this), this.mExecutor).transformAsync(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda3(this), this.mExecutor).transform(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda4(), CameraXExecutors.directExecutor());
        }

        static /* synthetic */ void lambda$preCapture$0(CallbackToFutureAdapter.Completer completer) {
            Logger.d(Camera2CapturePipeline.TAG, "ScreenFlashTask#preCapture: UI change applied");
            completer.set(null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$3$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ Object m51lambda$preCapture$3$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(AtomicReference atomicReference, CallbackToFutureAdapter.Completer completer) throws Exception {
            CameraXExecutors.mainThreadExecutor().execute(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda6(this, atomicReference, completer));
            return "OnScreenFlashStart";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$2$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ void m50lambda$preCapture$2$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(AtomicReference atomicReference, CallbackToFutureAdapter.Completer completer) {
            Logger.d(Camera2CapturePipeline.TAG, "ScreenFlashTask#preCapture: invoking applyScreenFlashUi");
            this.mScreenFlash.apply(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3), (ImageCapture.ScreenFlashListener) atomicReference.get());
            completer.set(null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$4$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m52lambda$preCapture$4$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(Void voidR) throws Exception {
            return this.mCameraControl.getFocusMeteringControl().enableExternalFlashAeMode(true);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$6$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m54lambda$preCapture$6$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(Void voidR) throws Exception {
            return CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda5(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$5$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ Object m53lambda$preCapture$5$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(CallbackToFutureAdapter.Completer completer) throws Exception {
            if (!this.mUseFlashModeTorchFor3aUpdate.shouldUseFlashModeTorch()) {
                completer.set(null);
                return "EnableTorchInternal";
            }
            Logger.d(Camera2CapturePipeline.TAG, "ScreenFlashTask#preCapture: enable torch");
            this.mCameraControl.enableTorchInternal(true);
            completer.set(null);
            return "EnableTorchInternal";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$7$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m55lambda$preCapture$7$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(ListenableFuture listenableFuture, Object obj) throws Exception {
            return Futures.makeTimeoutFuture(TimeUnit.SECONDS.toMillis(3), this.mScheduler, null, true, listenableFuture);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$8$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m56lambda$preCapture$8$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(Void voidR) throws Exception {
            return this.mCameraControl.getFocusMeteringControl().triggerAePrecapture();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$10$androidx-camera-camera2-internal-Camera2CapturePipeline$ScreenFlashTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m49lambda$preCapture$10$androidxcameracamera2internalCamera2CapturePipeline$ScreenFlashTask(Void voidR) throws Exception {
            return Camera2CapturePipeline.waitForResult(CHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS, this.mScheduler, this.mCameraControl, new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda9());
        }

        static /* synthetic */ Boolean lambda$preCapture$11(TotalCaptureResult totalCaptureResult) {
            return false;
        }

        public void postCapture() {
            Logger.d(Camera2CapturePipeline.TAG, "ScreenFlashTask#postCapture");
            if (this.mUseFlashModeTorchFor3aUpdate.shouldUseFlashModeTorch()) {
                this.mCameraControl.enableTorchInternal(false);
            }
            this.mCameraControl.getFocusMeteringControl().enableExternalFlashAeMode(false).addListener(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda7(), this.mExecutor);
            this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(false, true);
            ScheduledExecutorService mainThreadExecutor = CameraXExecutors.mainThreadExecutor();
            ImageCapture.ScreenFlash screenFlash = this.mScreenFlash;
            Objects.requireNonNull(screenFlash);
            mainThreadExecutor.execute(new Camera2CapturePipeline$ScreenFlashTask$$ExternalSyntheticLambda8(screenFlash));
        }
    }

    static boolean isFlashRequired(int i, TotalCaptureResult totalCaptureResult) {
        Logger.d(TAG, "isFlashRequired: flashMode = " + i);
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    return false;
                }
                if (i != 3) {
                    throw new AssertionError(i);
                }
            }
            return true;
        }
        Integer num = totalCaptureResult != null ? (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE) : null;
        Logger.d(TAG, "isFlashRequired: aeState = " + num);
        if (num == null || num.intValue() != 4) {
            return false;
        }
        return true;
    }

    static class ResultListener implements Camera2CameraControlImpl.CaptureResultListener {
        private final Checker mChecker;
        private CallbackToFutureAdapter.Completer<TotalCaptureResult> mCompleter;
        private final ListenableFuture<TotalCaptureResult> mFuture = CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$ResultListener$$ExternalSyntheticLambda0(this));

        interface Checker {
            boolean check(TotalCaptureResult totalCaptureResult);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-camera-camera2-internal-Camera2CapturePipeline$ResultListener  reason: not valid java name */
        public /* synthetic */ Object m48lambda$new$0$androidxcameracamera2internalCamera2CapturePipeline$ResultListener(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCompleter = completer;
            return "waitFor3AResult";
        }

        ResultListener(Checker checker) {
            this.mChecker = checker;
        }

        public ListenableFuture<TotalCaptureResult> getFuture() {
            return this.mFuture;
        }

        public boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
            Checker checker = this.mChecker;
            if (checker != null && !checker.check(totalCaptureResult)) {
                return false;
            }
            this.mCompleter.set(totalCaptureResult);
            return true;
        }
    }

    private boolean isTorchAsFlash(int i) {
        return this.mUseTorchAsFlash.shouldUseTorchAsFlash() || this.mTemplate == 3 || i == 1;
    }
}
