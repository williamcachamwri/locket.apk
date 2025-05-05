package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.DynamicRangesCompat;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.OutputSurfaceConfiguration;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.SessionProcessorSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

final class ProcessingCaptureSession implements CaptureSessionInterface {
    private static final String TAG = "ProcessingCaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    private static List<DeferrableSurface> sHeldProcessorSurfaces = new ArrayList();
    private static int sNextInstanceId = 0;
    private final Camera2CameraInfoImpl mCamera2CameraInfoImpl;
    private final CaptureSession mCaptureSession;
    final Executor mExecutor;
    private int mInstanceId = 0;
    private List<DeferrableSurface> mOutputSurfaces = new ArrayList();
    private volatile List<CaptureConfig> mPendingCaptureConfigs = null;
    private SessionConfig mProcessorSessionConfig;
    private ProcessorState mProcessorState;
    private Camera2RequestProcessor mRequestProcessor;
    private final ScheduledExecutorService mScheduledExecutorService;
    private SessionConfig mSessionConfig;
    private CaptureRequestOptions mSessionOptions = new CaptureRequestOptions.Builder().build();
    private final SessionProcessor mSessionProcessor;
    private final SessionProcessorCaptureCallback mSessionProcessorCaptureCallback;
    private CaptureRequestOptions mStillCaptureOptions = new CaptureRequestOptions.Builder().build();

    private enum ProcessorState {
        UNINITIALIZED,
        SESSION_INITIALIZED,
        ON_CAPTURE_SESSION_STARTED,
        ON_CAPTURE_SESSION_ENDED,
        DE_INITIALIZED
    }

    public void setStreamUseCaseMap(Map<DeferrableSurface, Long> map) {
    }

    ProcessingCaptureSession(SessionProcessor sessionProcessor, Camera2CameraInfoImpl camera2CameraInfoImpl, DynamicRangesCompat dynamicRangesCompat, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        this.mCaptureSession = new CaptureSession(dynamicRangesCompat);
        this.mSessionProcessor = sessionProcessor;
        this.mCamera2CameraInfoImpl = camera2CameraInfoImpl;
        this.mExecutor = executor;
        this.mScheduledExecutorService = scheduledExecutorService;
        this.mProcessorState = ProcessorState.UNINITIALIZED;
        this.mSessionProcessorCaptureCallback = new SessionProcessorCaptureCallback();
        int i = sNextInstanceId;
        sNextInstanceId = i + 1;
        this.mInstanceId = i;
        Logger.d(TAG, "New ProcessingCaptureSession (id=" + this.mInstanceId + ")");
    }

    public ListenableFuture<Void> open(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSession.Opener opener) {
        Preconditions.checkArgument(this.mProcessorState == ProcessorState.UNINITIALIZED, "Invalid state state:" + this.mProcessorState);
        Preconditions.checkArgument(!sessionConfig.getSurfaces().isEmpty(), "SessionConfig contains no surfaces");
        Logger.d(TAG, "open (id=" + this.mInstanceId + ")");
        List<DeferrableSurface> surfaces = sessionConfig.getSurfaces();
        this.mOutputSurfaces = surfaces;
        return FutureChain.from(DeferrableSurfaces.surfaceListWithTimeout(surfaces, false, 5000, this.mExecutor, this.mScheduledExecutorService)).transformAsync(new ProcessingCaptureSession$$ExternalSyntheticLambda3(this, sessionConfig, cameraDevice, opener), this.mExecutor).transform(new ProcessingCaptureSession$$ExternalSyntheticLambda4(this), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$2$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ ListenableFuture m83lambda$open$2$androidxcameracamera2internalProcessingCaptureSession(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSession.Opener opener, List list) throws Exception {
        OutputSurface outputSurface;
        Logger.d(TAG, "-- getSurfaces done, start init (id=" + this.mInstanceId + ")");
        if (this.mProcessorState == ProcessorState.DE_INITIALIZED) {
            return Futures.immediateFailedFuture(new IllegalStateException("SessionProcessorCaptureSession is closed."));
        }
        DeferrableSurface deferrableSurface = null;
        if (list.contains((Object) null)) {
            return Futures.immediateFailedFuture(new DeferrableSurface.SurfaceClosedException("Surface closed", sessionConfig.getSurfaces().get(list.indexOf((Object) null))));
        }
        OutputSurface outputSurface2 = null;
        OutputSurface outputSurface3 = null;
        OutputSurface outputSurface4 = null;
        for (int i = 0; i < sessionConfig.getSurfaces().size(); i++) {
            DeferrableSurface deferrableSurface2 = sessionConfig.getSurfaces().get(i);
            if (isPreview(deferrableSurface2) || isStreamSharing(deferrableSurface2)) {
                outputSurface2 = OutputSurface.create((Surface) deferrableSurface2.getSurface().get(), deferrableSurface2.getPrescribedSize(), deferrableSurface2.getPrescribedStreamFormat());
            } else if (isImageCapture(deferrableSurface2)) {
                outputSurface3 = OutputSurface.create((Surface) deferrableSurface2.getSurface().get(), deferrableSurface2.getPrescribedSize(), deferrableSurface2.getPrescribedStreamFormat());
            } else if (isImageAnalysis(deferrableSurface2)) {
                outputSurface4 = OutputSurface.create((Surface) deferrableSurface2.getSurface().get(), deferrableSurface2.getPrescribedSize(), deferrableSurface2.getPrescribedStreamFormat());
            }
        }
        if (sessionConfig.getPostviewOutputConfig() != null) {
            deferrableSurface = sessionConfig.getPostviewOutputConfig().getSurface();
            outputSurface = OutputSurface.create((Surface) deferrableSurface.getSurface().get(), deferrableSurface.getPrescribedSize(), deferrableSurface.getPrescribedStreamFormat());
        } else {
            outputSurface = null;
        }
        this.mProcessorState = ProcessorState.SESSION_INITIALIZED;
        try {
            ArrayList arrayList = new ArrayList(this.mOutputSurfaces);
            if (deferrableSurface != null) {
                arrayList.add(deferrableSurface);
            }
            DeferrableSurfaces.incrementAll(arrayList);
            Logger.w(TAG, "== initSession (id=" + this.mInstanceId + ")");
            try {
                SessionConfig initSession = this.mSessionProcessor.initSession(this.mCamera2CameraInfoImpl, OutputSurfaceConfiguration.create(outputSurface2, outputSurface3, outputSurface4, outputSurface));
                this.mProcessorSessionConfig = initSession;
                initSession.getSurfaces().get(0).getTerminationFuture().addListener(new ProcessingCaptureSession$$ExternalSyntheticLambda0(this, deferrableSurface), CameraXExecutors.directExecutor());
                for (DeferrableSurface next : this.mProcessorSessionConfig.getSurfaces()) {
                    sHeldProcessorSurfaces.add(next);
                    next.getTerminationFuture().addListener(new ProcessingCaptureSession$$ExternalSyntheticLambda1(next), this.mExecutor);
                }
                SessionConfig.ValidatingBuilder validatingBuilder = new SessionConfig.ValidatingBuilder();
                validatingBuilder.add(sessionConfig);
                validatingBuilder.clearSurfaces();
                validatingBuilder.add(this.mProcessorSessionConfig);
                Preconditions.checkArgument(validatingBuilder.isValid(), "Cannot transform the SessionConfig");
                ListenableFuture<Void> open = this.mCaptureSession.open(validatingBuilder.build(), (CameraDevice) Preconditions.checkNotNull(cameraDevice), opener);
                Futures.addCallback(open, new FutureCallback<Void>() {
                    public void onSuccess(Void voidR) {
                    }

                    public void onFailure(Throwable th) {
                        Logger.e(ProcessingCaptureSession.TAG, "open session failed ", th);
                        ProcessingCaptureSession.this.close();
                        ProcessingCaptureSession.this.release(false);
                    }
                }, this.mExecutor);
                return open;
            } catch (Throwable th) {
                Logger.e(TAG, "initSession failed", th);
                DeferrableSurfaces.decrementAll(this.mOutputSurfaces);
                if (deferrableSurface != null) {
                    deferrableSurface.decrementUseCount();
                }
                throw th;
            }
        } catch (DeferrableSurface.SurfaceClosedException e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$0$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ void m82lambda$open$0$androidxcameracamera2internalProcessingCaptureSession(DeferrableSurface deferrableSurface) {
        DeferrableSurfaces.decrementAll(this.mOutputSurfaces);
        if (deferrableSurface != null) {
            deferrableSurface.decrementUseCount();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$open$3$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ Void m84lambda$open$3$androidxcameracamera2internalProcessingCaptureSession(Void voidR) {
        onConfigured(this.mCaptureSession);
        return null;
    }

    private static void cancelRequests(List<CaptureConfig> list) {
        for (CaptureConfig next : list) {
            for (CameraCaptureCallback onCaptureCancelled : next.getCameraCaptureCallbacks()) {
                onCaptureCancelled.onCaptureCancelled(next.getId());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0041 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void issueTriggerRequest(androidx.camera.core.impl.CaptureConfig r7) {
        /*
            r6 = this;
            java.lang.String r0 = "ProcessingCaptureSession"
            java.lang.String r1 = "issueTriggerRequest"
            androidx.camera.core.Logger.d(r0, r1)
            androidx.camera.core.impl.Config r0 = r7.getImplementationOptions()
            androidx.camera.camera2.interop.CaptureRequestOptions$Builder r0 = androidx.camera.camera2.interop.CaptureRequestOptions.Builder.from(r0)
            androidx.camera.camera2.interop.CaptureRequestOptions r0 = r0.build()
            java.util.Set r1 = r0.listOptions()
            java.util.Iterator r1 = r1.iterator()
        L_0x001b:
            boolean r2 = r1.hasNext()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r1.next()
            androidx.camera.core.impl.Config$Option r2 = (androidx.camera.core.impl.Config.Option) r2
            java.lang.Object r2 = r2.getToken()
            android.hardware.camera2.CaptureRequest$Key r2 = (android.hardware.camera2.CaptureRequest.Key) r2
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AF_TRIGGER
            boolean r5 = r2.equals(r5)
            if (r5 != 0) goto L_0x003f
            android.hardware.camera2.CaptureRequest$Key r5 = android.hardware.camera2.CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x001b
        L_0x003f:
            r1 = r3
            goto L_0x0042
        L_0x0041:
            r1 = r4
        L_0x0042:
            if (r1 != 0) goto L_0x0050
            androidx.camera.core.impl.CaptureConfig[] r0 = new androidx.camera.core.impl.CaptureConfig[r3]
            r0[r4] = r7
            java.util.List r7 = java.util.Arrays.asList(r0)
            cancelRequests(r7)
            return
        L_0x0050:
            androidx.camera.core.impl.SessionProcessor r1 = r6.mSessionProcessor
            androidx.camera.core.impl.TagBundle r2 = r7.getTagBundle()
            androidx.camera.camera2.internal.ProcessingCaptureSession$CaptureCallbackAdapter r3 = new androidx.camera.camera2.internal.ProcessingCaptureSession$CaptureCallbackAdapter
            int r4 = r7.getId()
            java.util.List r7 = r7.getCameraCaptureCallbacks()
            r5 = 0
            r3.<init>(r4, r7)
            r1.startTrigger(r0, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.ProcessingCaptureSession.issueTriggerRequest(androidx.camera.core.impl.CaptureConfig):void");
    }

    public void issueCaptureRequests(List<CaptureConfig> list) {
        if (!list.isEmpty()) {
            Logger.d(TAG, "issueCaptureRequests (id=" + this.mInstanceId + ") + state =" + this.mProcessorState);
            int ordinal = this.mProcessorState.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                if (this.mPendingCaptureConfigs != null) {
                    cancelRequests(list);
                    Logger.d(TAG, "cancel the request because are pending un-submitted request");
                    return;
                }
                this.mPendingCaptureConfigs = list;
            } else if (ordinal == 2) {
                for (CaptureConfig next : list) {
                    if (next.getTemplateType() == 2) {
                        issueStillCaptureRequest(next);
                    } else {
                        issueTriggerRequest(next);
                    }
                }
            } else if (ordinal == 3 || ordinal == 4) {
                Logger.d(TAG, "Run issueCaptureRequests in wrong state, state = " + this.mProcessorState);
                cancelRequests(list);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void issueStillCaptureRequest(CaptureConfig captureConfig) {
        CaptureRequestOptions.Builder from = CaptureRequestOptions.Builder.from(captureConfig.getImplementationOptions());
        if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_ROTATION)) {
            from.setCaptureRequestOption(CaptureRequest.JPEG_ORIENTATION, (Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_ROTATION));
        }
        if (captureConfig.getImplementationOptions().containsOption(CaptureConfig.OPTION_JPEG_QUALITY)) {
            from.setCaptureRequestOption(CaptureRequest.JPEG_QUALITY, Byte.valueOf(((Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_JPEG_QUALITY)).byteValue()));
        }
        CaptureRequestOptions build = from.build();
        this.mStillCaptureOptions = build;
        updateParameters(this.mSessionOptions, build);
        this.mSessionProcessor.startCapture(captureConfig.isPostviewEnabled(), captureConfig.getTagBundle(), new CaptureCallbackAdapter(captureConfig.getId(), captureConfig.getCameraCaptureCallbacks()));
    }

    private static class CaptureCallbackAdapter implements SessionProcessor.CaptureCallback {
        private List<CameraCaptureCallback> mCameraCaptureCallbacks;
        private final int mCaptureConfigId;
        private CameraCaptureResult mCaptureResult;

        private CaptureCallbackAdapter(int i, List<CameraCaptureCallback> list) {
            this.mCaptureResult = null;
            this.mCaptureConfigId = i;
            this.mCameraCaptureCallbacks = list;
        }

        public void onCaptureStarted(int i, long j) {
            for (CameraCaptureCallback onCaptureStarted : this.mCameraCaptureCallbacks) {
                onCaptureStarted.onCaptureStarted(this.mCaptureConfigId);
            }
        }

        public void onCaptureFailed(int i) {
            for (CameraCaptureCallback onCaptureFailed : this.mCameraCaptureCallbacks) {
                onCaptureFailed.onCaptureFailed(this.mCaptureConfigId, new CameraCaptureFailure(CameraCaptureFailure.Reason.ERROR));
            }
        }

        public void onCaptureCompleted(long j, int i, CameraCaptureResult cameraCaptureResult) {
            this.mCaptureResult = cameraCaptureResult;
        }

        public void onCaptureSequenceCompleted(int i) {
            CameraCaptureResult cameraCaptureResult = this.mCaptureResult;
            if (cameraCaptureResult == null) {
                cameraCaptureResult = new CameraCaptureResult.EmptyCameraCaptureResult();
            }
            for (CameraCaptureCallback onCaptureCompleted : this.mCameraCaptureCallbacks) {
                onCaptureCompleted.onCaptureCompleted(this.mCaptureConfigId, cameraCaptureResult);
            }
        }

        public void onCaptureProcessProgressed(int i) {
            for (CameraCaptureCallback onCaptureProcessProgressed : this.mCameraCaptureCallbacks) {
                onCaptureProcessProgressed.onCaptureProcessProgressed(this.mCaptureConfigId, i);
            }
        }
    }

    public ListenableFuture<Void> release(boolean z) {
        Logger.d(TAG, "release (id=" + this.mInstanceId + ") mProcessorState=" + this.mProcessorState);
        ListenableFuture<Void> release = this.mCaptureSession.release(z);
        int ordinal = this.mProcessorState.ordinal();
        if (ordinal == 1 || ordinal == 3) {
            release.addListener(new ProcessingCaptureSession$$ExternalSyntheticLambda2(this), CameraXExecutors.directExecutor());
        }
        this.mProcessorState = ProcessorState.DE_INITIALIZED;
        return release;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$4$androidx-camera-camera2-internal-ProcessingCaptureSession  reason: not valid java name */
    public /* synthetic */ void m85lambda$release$4$androidxcameracamera2internalProcessingCaptureSession() {
        Logger.d(TAG, "== deInitSession (id=" + this.mInstanceId + ")");
        this.mSessionProcessor.deInitSession();
    }

    private static List<SessionProcessorSurface> getSessionProcessorSurfaceList(List<DeferrableSurface> list) {
        ArrayList arrayList = new ArrayList();
        for (DeferrableSurface next : list) {
            Preconditions.checkArgument(next instanceof SessionProcessorSurface, "Surface must be SessionProcessorSurface");
            arrayList.add((SessionProcessorSurface) next);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void onConfigured(CaptureSession captureSession) {
        if (this.mProcessorState == ProcessorState.SESSION_INITIALIZED) {
            this.mRequestProcessor = new Camera2RequestProcessor(captureSession, getSessionProcessorSurfaceList(this.mProcessorSessionConfig.getSurfaces()));
            Logger.d(TAG, "== onCaptureSessinStarted (id = " + this.mInstanceId + ")");
            this.mSessionProcessor.onCaptureSessionStart(this.mRequestProcessor);
            this.mProcessorState = ProcessorState.ON_CAPTURE_SESSION_STARTED;
            SessionConfig sessionConfig = this.mSessionConfig;
            if (sessionConfig != null) {
                setSessionConfig(sessionConfig);
            }
            if (this.mPendingCaptureConfigs != null) {
                issueCaptureRequests(this.mPendingCaptureConfigs);
                this.mPendingCaptureConfigs = null;
            }
        }
    }

    public SessionConfig getSessionConfig() {
        return this.mSessionConfig;
    }

    public List<CaptureConfig> getCaptureConfigs() {
        return this.mPendingCaptureConfigs != null ? this.mPendingCaptureConfigs : Collections.emptyList();
    }

    public void cancelIssuedCaptureRequests() {
        Logger.d(TAG, "cancelIssuedCaptureRequests (id=" + this.mInstanceId + ")");
        if (this.mPendingCaptureConfigs != null) {
            for (CaptureConfig next : this.mPendingCaptureConfigs) {
                for (CameraCaptureCallback onCaptureCancelled : next.getCameraCaptureCallbacks()) {
                    onCaptureCancelled.onCaptureCancelled(next.getId());
                }
            }
            this.mPendingCaptureConfigs = null;
        }
    }

    public void close() {
        Logger.d(TAG, "close (id=" + this.mInstanceId + ") state=" + this.mProcessorState);
        if (this.mProcessorState == ProcessorState.ON_CAPTURE_SESSION_STARTED) {
            Logger.d(TAG, "== onCaptureSessionEnd (id = " + this.mInstanceId + ")");
            this.mSessionProcessor.onCaptureSessionEnd();
            Camera2RequestProcessor camera2RequestProcessor = this.mRequestProcessor;
            if (camera2RequestProcessor != null) {
                camera2RequestProcessor.close();
            }
            this.mProcessorState = ProcessorState.ON_CAPTURE_SESSION_ENDED;
        }
        this.mCaptureSession.close();
    }

    public void setSessionConfig(SessionConfig sessionConfig) {
        Logger.d(TAG, "setSessionConfig (id=" + this.mInstanceId + ")");
        this.mSessionConfig = sessionConfig;
        if (sessionConfig != null) {
            Camera2RequestProcessor camera2RequestProcessor = this.mRequestProcessor;
            if (camera2RequestProcessor != null) {
                camera2RequestProcessor.updateSessionConfig(sessionConfig);
            }
            if (this.mProcessorState == ProcessorState.ON_CAPTURE_SESSION_STARTED) {
                CaptureRequestOptions build = CaptureRequestOptions.Builder.from(sessionConfig.getImplementationOptions()).build();
                this.mSessionOptions = build;
                updateParameters(build, this.mStillCaptureOptions);
                if (!hasPreviewSurface(sessionConfig.getRepeatingCaptureConfig())) {
                    this.mSessionProcessor.stopRepeating();
                } else {
                    this.mSessionProcessor.startRepeating(sessionConfig.getRepeatingCaptureConfig().getTagBundle(), this.mSessionProcessorCaptureCallback);
                }
            }
        }
    }

    public boolean isInOpenState() {
        return this.mCaptureSession.isInOpenState();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean hasPreviewSurface(androidx.camera.core.impl.CaptureConfig r2) {
        /*
            java.util.List r2 = r2.getSurfaces()
            java.util.Iterator r2 = r2.iterator()
        L_0x0008:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0022
            java.lang.Object r0 = r2.next()
            androidx.camera.core.impl.DeferrableSurface r0 = (androidx.camera.core.impl.DeferrableSurface) r0
            boolean r1 = isPreview(r0)
            if (r1 != 0) goto L_0x0020
            boolean r0 = isStreamSharing(r0)
            if (r0 == 0) goto L_0x0008
        L_0x0020:
            r2 = 1
            return r2
        L_0x0022:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.ProcessingCaptureSession.hasPreviewSurface(androidx.camera.core.impl.CaptureConfig):boolean");
    }

    private void updateParameters(CaptureRequestOptions captureRequestOptions, CaptureRequestOptions captureRequestOptions2) {
        Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
        builder.insertAllOptions(captureRequestOptions);
        builder.insertAllOptions(captureRequestOptions2);
        this.mSessionProcessor.setParameters(builder.build());
    }

    private static boolean isPreview(DeferrableSurface deferrableSurface) {
        return Objects.equals(deferrableSurface.getContainerClass(), Preview.class);
    }

    private static boolean isImageCapture(DeferrableSurface deferrableSurface) {
        return Objects.equals(deferrableSurface.getContainerClass(), ImageCapture.class);
    }

    private static boolean isImageAnalysis(DeferrableSurface deferrableSurface) {
        return Objects.equals(deferrableSurface.getContainerClass(), ImageAnalysis.class);
    }

    private static boolean isStreamSharing(DeferrableSurface deferrableSurface) {
        return Objects.equals(deferrableSurface.getContainerClass(), StreamSharing.class);
    }

    private static class SessionProcessorCaptureCallback implements SessionProcessor.CaptureCallback {
        public void onCaptureCompleted(long j, int i, CameraCaptureResult cameraCaptureResult) {
        }

        public void onCaptureFailed(int i) {
        }

        public void onCaptureProcessStarted(int i) {
        }

        public void onCaptureSequenceAborted(int i) {
        }

        public void onCaptureSequenceCompleted(int i) {
        }

        public void onCaptureStarted(int i, long j) {
        }

        SessionProcessorCaptureCallback() {
        }
    }
}
