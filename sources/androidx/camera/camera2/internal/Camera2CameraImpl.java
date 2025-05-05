package androidx.camera.camera2.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.CamcorderProfile;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.ApiCompat;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.camera2.internal.compat.params.DynamicRangesCompat;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.LegacyCameraSurfaceCleanupQuirk;
import androidx.camera.camera2.internal.compat.workaround.CloseCameraBeforeCreateNewSession;
import androidx.camera.core.CameraState;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseAttachState;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.tracing.Trace;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

final class Camera2CameraImpl implements CameraInternal {
    private static final int ERROR_NONE = 0;
    private static final String TAG = "Camera2CameraImpl";
    final CameraAvailability mCameraAvailability;
    private final CameraCharacteristicsCompat mCameraCharacteristicsCompat;
    private CameraConfig mCameraConfig;
    final CameraConfigureAvailable mCameraConfigureAvailable;
    private final Camera2CameraControlImpl mCameraControlInternal;
    final CameraCoordinator mCameraCoordinator;
    CameraDevice mCameraDevice;
    int mCameraDeviceError;
    final Camera2CameraInfoImpl mCameraInfoInternal;
    private final CameraManagerCompat mCameraManager;
    private final CameraStateMachine mCameraStateMachine;
    final CameraStateRegistry mCameraStateRegistry;
    CaptureSessionInterface mCaptureSession;
    private final SynchronizedCaptureSession.OpenerBuilder mCaptureSessionOpenerBuilder;
    private final CaptureSessionRepository mCaptureSessionRepository;
    private final boolean mConfigAndCloseQuirk;
    private final DisplayInfoManager mDisplayInfoManager;
    private final DynamicRangesCompat mDynamicRangesCompat;
    /* access modifiers changed from: private */
    public final ErrorTimeoutReopenScheduler mErrorTimeoutReopenScheduler;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    boolean mIsActiveResumingMode;
    private boolean mIsConfigAndCloseRequired;
    private boolean mIsConfiguringForClose;
    private boolean mIsPrimary;
    final Object mLock;
    private MeteringRepeatingSession mMeteringRepeatingSession;
    private final Set<String> mNotifyStateAttachedSet;
    private final LiveDataObservable<CameraInternal.State> mObservableState;
    final AtomicInteger mReleaseRequestCount;
    final Map<CaptureSessionInterface, ListenableFuture<Void>> mReleasedCaptureSessions;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService mScheduledExecutorService;
    private SessionProcessor mSessionProcessor;
    private final boolean mShouldCloseCameraBeforeCreateNewSession;
    volatile InternalState mState = InternalState.INITIALIZED;
    /* access modifiers changed from: private */
    public final StateCallback mStateCallback;
    private final SupportedSurfaceCombination mSupportedSurfaceCombination;
    private int mTraceStateErrorCount;
    private final UseCaseAttachState mUseCaseAttachState;
    ListenableFuture<Void> mUserReleaseFuture;
    CallbackToFutureAdapter.Completer<Void> mUserReleaseNotifier;

    enum InternalState {
        RELEASED,
        RELEASING,
        INITIALIZED,
        PENDING_OPEN,
        CLOSING,
        REOPENING_QUIRK,
        REOPENING,
        OPENING,
        OPENED,
        CONFIGURED
    }

    static String getErrorMessage(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    Camera2CameraImpl(Context context, CameraManagerCompat cameraManagerCompat, String str, Camera2CameraInfoImpl camera2CameraInfoImpl, CameraCoordinator cameraCoordinator, CameraStateRegistry cameraStateRegistry, Executor executor, Handler handler, DisplayInfoManager displayInfoManager, long j) throws CameraUnavailableException {
        CameraManagerCompat cameraManagerCompat2 = cameraManagerCompat;
        String str2 = str;
        Camera2CameraInfoImpl camera2CameraInfoImpl2 = camera2CameraInfoImpl;
        CameraStateRegistry cameraStateRegistry2 = cameraStateRegistry;
        LiveDataObservable<CameraInternal.State> liveDataObservable = new LiveDataObservable<>();
        this.mObservableState = liveDataObservable;
        this.mCameraDeviceError = 0;
        this.mReleaseRequestCount = new AtomicInteger(0);
        this.mReleasedCaptureSessions = new LinkedHashMap();
        this.mTraceStateErrorCount = 0;
        this.mIsConfigAndCloseRequired = false;
        this.mIsConfiguringForClose = false;
        this.mIsPrimary = true;
        this.mNotifyStateAttachedSet = new HashSet();
        this.mCameraConfig = CameraConfigs.defaultConfig();
        this.mLock = new Object();
        this.mIsActiveResumingMode = false;
        this.mErrorTimeoutReopenScheduler = new ErrorTimeoutReopenScheduler();
        this.mCameraManager = cameraManagerCompat2;
        this.mCameraCoordinator = cameraCoordinator;
        this.mCameraStateRegistry = cameraStateRegistry2;
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(handler);
        this.mScheduledExecutorService = newHandlerExecutor;
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mExecutor = newSequentialExecutor;
        this.mStateCallback = new StateCallback(newSequentialExecutor, newHandlerExecutor, j);
        this.mUseCaseAttachState = new UseCaseAttachState(str2);
        liveDataObservable.postValue(CameraInternal.State.CLOSED);
        CameraStateMachine cameraStateMachine = new CameraStateMachine(cameraStateRegistry2);
        this.mCameraStateMachine = cameraStateMachine;
        CaptureSessionRepository captureSessionRepository = new CaptureSessionRepository(newSequentialExecutor);
        this.mCaptureSessionRepository = captureSessionRepository;
        this.mDisplayInfoManager = displayInfoManager;
        try {
            CameraCharacteristicsCompat cameraCharacteristicsCompat = cameraManagerCompat.getCameraCharacteristicsCompat(str);
            this.mCameraCharacteristicsCompat = cameraCharacteristicsCompat;
            CaptureSessionRepository captureSessionRepository2 = captureSessionRepository;
            Camera2CameraControlImpl camera2CameraControlImpl = r1;
            Camera2CameraControlImpl camera2CameraControlImpl2 = new Camera2CameraControlImpl(cameraCharacteristicsCompat, newHandlerExecutor, newSequentialExecutor, new ControlUpdateListenerInternal(), camera2CameraInfoImpl.getCameraQuirks());
            this.mCameraControlInternal = camera2CameraControlImpl;
            this.mCameraInfoInternal = camera2CameraInfoImpl2;
            camera2CameraInfoImpl2.linkWithCameraControl(camera2CameraControlImpl);
            camera2CameraInfoImpl2.setCameraStateSource(cameraStateMachine.getStateLiveData());
            this.mDynamicRangesCompat = DynamicRangesCompat.fromCameraCharacteristics(cameraCharacteristicsCompat);
            this.mCaptureSession = newCaptureSession();
            Executor executor2 = newSequentialExecutor;
            this.mCaptureSessionOpenerBuilder = new SynchronizedCaptureSession.OpenerBuilder(newSequentialExecutor, newHandlerExecutor, handler, captureSessionRepository2, camera2CameraInfoImpl.getCameraQuirks(), DeviceQuirks.getAll());
            this.mShouldCloseCameraBeforeCreateNewSession = CloseCameraBeforeCreateNewSession.shouldCloseCamera(camera2CameraInfoImpl.getCameraQuirks());
            this.mConfigAndCloseQuirk = camera2CameraInfoImpl.getCameraQuirks().contains(LegacyCameraSurfaceCleanupQuirk.class);
            CameraAvailability cameraAvailability = new CameraAvailability(str2);
            this.mCameraAvailability = cameraAvailability;
            CameraConfigureAvailable cameraConfigureAvailable = new CameraConfigureAvailable();
            this.mCameraConfigureAvailable = cameraConfigureAvailable;
            cameraStateRegistry2.registerCamera(this, executor2, cameraConfigureAvailable, cameraAvailability);
            cameraManagerCompat2.registerAvailabilityCallback(executor2, cameraAvailability);
            this.mSupportedSurfaceCombination = new SupportedSurfaceCombination(context, str2, cameraManagerCompat2, new CamcorderProfileHelper() {
                public boolean hasProfile(int i, int i2) {
                    return CamcorderProfile.hasProfile(i, i2);
                }

                public CamcorderProfile get(int i, int i2) {
                    return CamcorderProfile.get(i, i2);
                }
            });
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    private CaptureSessionInterface newCaptureSession() {
        synchronized (this.mLock) {
            if (this.mSessionProcessor == null) {
                CaptureSession captureSession = new CaptureSession(this.mDynamicRangesCompat, this.mCameraInfoInternal.getCameraQuirks());
                return captureSession;
            }
            ProcessingCaptureSession processingCaptureSession = new ProcessingCaptureSession(this.mSessionProcessor, this.mCameraInfoInternal, this.mDynamicRangesCompat, this.mExecutor, this.mScheduledExecutorService);
            return processingCaptureSession;
        }
    }

    public void open() {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: private */
    public void openInternal() {
        int ordinal = this.mState.ordinal();
        boolean z = false;
        if (ordinal == 2 || ordinal == 3) {
            tryForceOpenCameraDevice(false);
        } else if (ordinal != 4) {
            debugLog("open() ignored due to being in state: " + this.mState);
        } else {
            setState(InternalState.REOPENING);
            if (!isSessionCloseComplete() && !this.mIsConfiguringForClose && this.mCameraDeviceError == 0) {
                if (this.mCameraDevice != null) {
                    z = true;
                }
                Preconditions.checkState(z, "Camera Device should be open if session close is not complete");
                setState(InternalState.OPENED);
                openCaptureSession();
            }
        }
    }

    public void close() {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda12(this));
    }

    /* access modifiers changed from: private */
    public void closeInternal() {
        debugLog("Closing camera.");
        boolean z = true;
        switch (this.mState.ordinal()) {
            case 3:
                if (this.mCameraDevice != null) {
                    z = false;
                }
                Preconditions.checkState(z);
                setState(InternalState.INITIALIZED);
                return;
            case 5:
            case 6:
            case 7:
                if (!this.mStateCallback.cancelScheduledReopen() && !this.mErrorTimeoutReopenScheduler.isErrorHandling()) {
                    z = false;
                }
                this.mErrorTimeoutReopenScheduler.cancel();
                setState(InternalState.CLOSING);
                if (z) {
                    Preconditions.checkState(isSessionCloseComplete());
                    configAndCloseIfNeeded();
                    return;
                }
                return;
            case 8:
            case 9:
                setState(InternalState.CLOSING);
                closeCamera(false);
                return;
            default:
                debugLog("close() ignored due to being in state: " + this.mState);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void configAndCloseIfNeeded() {
        Preconditions.checkState(this.mState == InternalState.RELEASING || this.mState == InternalState.CLOSING);
        Preconditions.checkState(this.mReleasedCaptureSessions.isEmpty());
        if (!this.mIsConfigAndCloseRequired) {
            finishClose();
        } else if (this.mIsConfiguringForClose) {
            debugLog("Ignored since configAndClose is processing");
        } else if (!this.mCameraAvailability.isCameraAvailable()) {
            this.mIsConfigAndCloseRequired = false;
            finishClose();
            debugLog("Ignore configAndClose and finish the close flow directly since camera is unavailable.");
        } else {
            debugLog("Open camera to configAndClose");
            ListenableFuture<Void> openCameraConfigAndClose = openCameraConfigAndClose();
            this.mIsConfiguringForClose = true;
            openCameraConfigAndClose.addListener(new Camera2CameraImpl$$ExternalSyntheticLambda18(this), this.mExecutor);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configAndCloseIfNeeded$0$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m22lambda$configAndCloseIfNeeded$0$androidxcameracamera2internalCamera2CameraImpl() {
        this.mIsConfiguringForClose = false;
        this.mIsConfigAndCloseRequired = false;
        debugLog("OpenCameraConfigAndClose is done, state: " + this.mState);
        int ordinal = this.mState.ordinal();
        if (ordinal == 1 || ordinal == 4) {
            Preconditions.checkState(isSessionCloseComplete());
            finishClose();
        } else if (ordinal != 6) {
            debugLog("OpenCameraConfigAndClose finished while in state: " + this.mState);
        } else if (this.mCameraDeviceError != 0) {
            debugLog("OpenCameraConfigAndClose in error: " + getErrorMessage(this.mCameraDeviceError));
            this.mStateCallback.scheduleCameraReopen();
        } else {
            tryOpenCameraDevice(false);
        }
    }

    private ListenableFuture<Void> openCameraConfigAndClose() {
        return CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda20(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$openCameraConfigAndClose$1$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m32lambda$openCameraConfigAndClose$1$androidxcameracamera2internalCamera2CameraImpl(final CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            ArrayList arrayList = new ArrayList(this.mUseCaseAttachState.getAttachedBuilder().build().getDeviceStateCallbacks());
            arrayList.add(this.mCaptureSessionRepository.getCameraStateCallback());
            arrayList.add(new CameraDevice.StateCallback() {
                public void onOpened(CameraDevice cameraDevice) {
                    Camera2CameraImpl.this.debugLog("openCameraConfigAndClose camera opened");
                    ListenableFuture access$600 = Camera2CameraImpl.this.configAndClose(cameraDevice);
                    Objects.requireNonNull(cameraDevice);
                    access$600.addListener(new Camera2CameraImpl$2$$ExternalSyntheticLambda0(cameraDevice), Camera2CameraImpl.this.mExecutor);
                }

                public void onDisconnected(CameraDevice cameraDevice) {
                    Camera2CameraImpl.this.debugLog("openCameraConfigAndClose camera disconnected");
                    completer.set(null);
                }

                public void onError(CameraDevice cameraDevice, int i) {
                    Camera2CameraImpl.this.debugLog("openCameraConfigAndClose camera error " + i);
                    completer.set(null);
                }

                public void onClosed(CameraDevice cameraDevice) {
                    Camera2CameraImpl.this.debugLog("openCameraConfigAndClose camera closed");
                    completer.set(null);
                }
            });
            this.mCameraManager.openCamera(this.mCameraInfoInternal.getCameraId(), this.mExecutor, CameraDeviceStateCallbacks.createComboCallback((List<CameraDevice.StateCallback>) arrayList));
            return "configAndCloseTask";
        } catch (CameraAccessExceptionCompat | SecurityException e) {
            debugLog("Unable to open camera for configAndClose: " + e.getMessage(), e);
            completer.setException(e);
            return "configAndCloseTask";
        }
    }

    /* access modifiers changed from: private */
    public ListenableFuture<Void> configAndClose(CameraDevice cameraDevice) {
        CaptureSession captureSession = new CaptureSession(this.mDynamicRangesCompat);
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(640, 480);
        Surface surface = new Surface(surfaceTexture);
        ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        immediateSurface.getTerminationFuture().addListener(new Camera2CameraImpl$$ExternalSyntheticLambda11(surface, surfaceTexture), CameraXExecutors.directExecutor());
        SessionConfig.Builder builder = new SessionConfig.Builder();
        builder.addNonRepeatingSurface(immediateSurface);
        builder.setTemplateType(1);
        debugLog("Start configAndClose.");
        return FutureChain.from(Futures.transformAsyncOnCompletion(captureSession.open(builder.build(), cameraDevice, this.mCaptureSessionOpenerBuilder.build()))).transformAsync(new Camera2CameraImpl$$ExternalSyntheticLambda14(captureSession, immediateSurface), this.mExecutor);
    }

    static /* synthetic */ void lambda$configAndClose$2(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    static /* synthetic */ ListenableFuture lambda$configAndClose$3(CaptureSession captureSession, DeferrableSurface deferrableSurface, Void voidR) throws Exception {
        captureSession.close();
        deferrableSurface.close();
        return captureSession.release(false);
    }

    /* access modifiers changed from: package-private */
    public boolean isSessionCloseComplete() {
        return this.mReleasedCaptureSessions.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void finishClose() {
        Preconditions.checkState(this.mState == InternalState.RELEASING || this.mState == InternalState.CLOSING);
        Preconditions.checkState(this.mReleasedCaptureSessions.isEmpty());
        this.mCameraDevice = null;
        if (this.mState == InternalState.CLOSING) {
            setState(InternalState.INITIALIZED);
            return;
        }
        this.mCameraManager.unregisterAvailabilityCallback(this.mCameraAvailability);
        setState(InternalState.RELEASED);
        CallbackToFutureAdapter.Completer<Void> completer = this.mUserReleaseNotifier;
        if (completer != null) {
            completer.set(null);
            this.mUserReleaseNotifier = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void closeCamera(boolean z) {
        Preconditions.checkState(this.mState == InternalState.CLOSING || this.mState == InternalState.RELEASING || (this.mState == InternalState.REOPENING && this.mCameraDeviceError != 0), "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.mState + " (error: " + getErrorMessage(this.mCameraDeviceError) + ")");
        resetCaptureSession(z);
        this.mCaptureSession.cancelIssuedCaptureRequests();
    }

    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda21(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$5$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m34lambda$release$5$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda3(this, completer));
        return "Release[request=" + this.mReleaseRequestCount.getAndIncrement() + "]";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$4$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m33lambda$release$4$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(releaseInternal(), completer);
    }

    private ListenableFuture<Void> releaseInternal() {
        ListenableFuture<Void> orCreateUserReleaseFuture = getOrCreateUserReleaseFuture();
        boolean z = true;
        switch (this.mState.ordinal()) {
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
                if (!this.mStateCallback.cancelScheduledReopen() && !this.mErrorTimeoutReopenScheduler.isErrorHandling()) {
                    z = false;
                }
                this.mErrorTimeoutReopenScheduler.cancel();
                setState(InternalState.RELEASING);
                if (z) {
                    Preconditions.checkState(isSessionCloseComplete());
                    configAndCloseIfNeeded();
                    break;
                }
                break;
            case 2:
            case 3:
                if (this.mCameraDevice != null) {
                    z = false;
                }
                Preconditions.checkState(z);
                setState(InternalState.RELEASING);
                Preconditions.checkState(isSessionCloseComplete());
                configAndCloseIfNeeded();
                break;
            case 8:
            case 9:
                setState(InternalState.RELEASING);
                closeCamera(false);
                break;
            default:
                debugLog("release() ignored due to being in state: " + this.mState);
                break;
        }
        return orCreateUserReleaseFuture;
    }

    private ListenableFuture<Void> getOrCreateUserReleaseFuture() {
        if (this.mUserReleaseFuture == null) {
            if (this.mState != InternalState.RELEASED) {
                this.mUserReleaseFuture = CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda16(this));
            } else {
                this.mUserReleaseFuture = Futures.immediateFuture(null);
            }
        }
        return this.mUserReleaseFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOrCreateUserReleaseFuture$6$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m24lambda$getOrCreateUserReleaseFuture$6$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        Preconditions.checkState(this.mUserReleaseNotifier == null, "Camera can only be released once, so release completer should be null on creation.");
        this.mUserReleaseNotifier = completer;
        return "Release[camera=" + this + "]";
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> releaseSession(final CaptureSessionInterface captureSessionInterface, boolean z) {
        captureSessionInterface.close();
        ListenableFuture<Void> release = captureSessionInterface.release(z);
        debugLog("Releasing session in state " + this.mState.name());
        this.mReleasedCaptureSessions.put(captureSessionInterface, release);
        Futures.addCallback(release, new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(Void voidR) {
                Camera2CameraImpl.this.mReleasedCaptureSessions.remove(captureSessionInterface);
                int ordinal = Camera2CameraImpl.this.mState.ordinal();
                if (!(ordinal == 1 || ordinal == 4)) {
                    if (ordinal == 5 || (ordinal == 6 && Camera2CameraImpl.this.mCameraDeviceError != 0)) {
                        Camera2CameraImpl.this.debugLog("Camera reopen required. Checking if the current camera can be closed safely.");
                    } else {
                        return;
                    }
                }
                if (Camera2CameraImpl.this.isSessionCloseComplete() && Camera2CameraImpl.this.mCameraDevice != null) {
                    Camera2CameraImpl.this.debugLog("closing camera");
                    ApiCompat.Api21Impl.close(Camera2CameraImpl.this.mCameraDevice);
                    Camera2CameraImpl.this.mCameraDevice = null;
                }
            }
        }, CameraXExecutors.directExecutor());
        return release;
    }

    public Observable<CameraInternal.State> getCameraState() {
        return this.mObservableState;
    }

    public void onUseCaseActive(UseCase useCase) {
        SessionConfig sessionConfig;
        Preconditions.checkNotNull(useCase);
        String useCaseId = getUseCaseId(useCase);
        if (this.mIsPrimary) {
            sessionConfig = useCase.getSessionConfig();
        } else {
            sessionConfig = useCase.getSecondarySessionConfig();
        }
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda1(this, useCaseId, sessionConfig, useCase.getCurrentConfig(), useCase.getAttachedStreamSpec(), getCaptureTypes(useCase)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseActive$7$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m29lambda$onUseCaseActive$7$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig, StreamSpec streamSpec, List list) {
        debugLog("Use case " + str + " ACTIVE");
        this.mUseCaseAttachState.setUseCaseActive(str, sessionConfig, useCaseConfig, streamSpec, list);
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig, streamSpec, list);
        updateCaptureSessionConfig();
    }

    public void onUseCaseInactive(UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda0(this, getUseCaseId(useCase)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseInactive$8$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m30lambda$onUseCaseInactive$8$androidxcameracamera2internalCamera2CameraImpl(String str) {
        debugLog("Use case " + str + " INACTIVE");
        this.mUseCaseAttachState.setUseCaseInactive(str);
        updateCaptureSessionConfig();
    }

    public void onUseCaseUpdated(UseCase useCase) {
        SessionConfig sessionConfig;
        Preconditions.checkNotNull(useCase);
        String useCaseId = getUseCaseId(useCase);
        if (this.mIsPrimary) {
            sessionConfig = useCase.getSessionConfig();
        } else {
            sessionConfig = useCase.getSecondarySessionConfig();
        }
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda4(this, useCaseId, sessionConfig, useCase.getCurrentConfig(), useCase.getAttachedStreamSpec(), getCaptureTypes(useCase)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUseCaseUpdated$9$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m31lambda$onUseCaseUpdated$9$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig, StreamSpec streamSpec, List list) {
        debugLog("Use case " + str + " UPDATED");
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig, streamSpec, list);
        updateCaptureSessionConfig();
    }

    public void onUseCaseReset(UseCase useCase) {
        SessionConfig sessionConfig;
        Preconditions.checkNotNull(useCase);
        if (this.mIsPrimary) {
            sessionConfig = useCase.getSessionConfig();
        } else {
            sessionConfig = useCase.getSecondarySessionConfig();
        }
        UseCaseConfig<?> currentConfig = useCase.getCurrentConfig();
        StreamSpec attachedStreamSpec = useCase.getAttachedStreamSpec();
        List<UseCaseConfigFactory.CaptureType> captureTypes = getCaptureTypes(useCase);
        resetUseCase(getUseCaseId(useCase), sessionConfig, currentConfig, attachedStreamSpec, captureTypes);
    }

    private void resetUseCase(String str, SessionConfig sessionConfig, UseCaseConfig<?> useCaseConfig, StreamSpec streamSpec, List<UseCaseConfigFactory.CaptureType> list) {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda6(this, str, sessionConfig, useCaseConfig, streamSpec, list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$resetUseCase$10$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m35lambda$resetUseCase$10$androidxcameracamera2internalCamera2CameraImpl(String str, SessionConfig sessionConfig, UseCaseConfig useCaseConfig, StreamSpec streamSpec, List list) {
        debugLog("Use case " + str + " RESET");
        this.mUseCaseAttachState.updateUseCase(str, sessionConfig, useCaseConfig, streamSpec, list);
        addOrRemoveMeteringRepeatingUseCase();
        resetCaptureSession(false);
        updateCaptureSessionConfig();
        if (this.mState == InternalState.OPENED) {
            openCaptureSession();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isUseCaseAttached(UseCase useCase) {
        try {
            return ((Boolean) CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda5(this, getUseCaseId(useCase))).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to check if use case is attached.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isUseCaseAttached$12$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m28lambda$isUseCaseAttached$12$androidxcameracamera2internalCamera2CameraImpl(String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda8(this, completer, str));
            return "isUseCaseAttached";
        } catch (RejectedExecutionException unused) {
            completer.setException(new RuntimeException("Unable to check if use case is attached. Camera executor shut down."));
            return "isUseCaseAttached";
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isUseCaseAttached$11$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m27lambda$isUseCaseAttached$11$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer, String str) {
        completer.set(Boolean.valueOf(this.mUseCaseAttachState.isUseCaseAttached(str)));
    }

    /* access modifiers changed from: package-private */
    public boolean isMeteringRepeatingAttached() {
        try {
            return ((Boolean) CallbackToFutureAdapter.getFuture(new Camera2CameraImpl$$ExternalSyntheticLambda17(this)).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to check if MeteringRepeating is attached.", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isMeteringRepeatingAttached$14$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ Object m26lambda$isMeteringRepeatingAttached$14$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda19(this, completer));
            return "isMeteringRepeatingAttached";
        } catch (RejectedExecutionException unused) {
            completer.setException(new RuntimeException("Unable to check if MeteringRepeating is attached. Camera executor shut down."));
            return "isMeteringRepeatingAttached";
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$isMeteringRepeatingAttached$13$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m25lambda$isMeteringRepeatingAttached$13$androidxcameracamera2internalCamera2CameraImpl(CallbackToFutureAdapter.Completer completer) {
        MeteringRepeatingSession meteringRepeatingSession = this.mMeteringRepeatingSession;
        if (meteringRepeatingSession == null) {
            completer.set(false);
            return;
        }
        completer.set(Boolean.valueOf(this.mUseCaseAttachState.isUseCaseAttached(getMeteringRepeatingId(meteringRepeatingSession))));
    }

    public void attachUseCases(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (!arrayList.isEmpty()) {
            this.mCameraControlInternal.incrementUseCount();
            notifyStateAttachedAndCameraControlReady(new ArrayList(arrayList));
            try {
                this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda13(this, new ArrayList(toUseCaseInfos(arrayList))));
            } catch (RejectedExecutionException e) {
                debugLog("Unable to attach use cases.", e);
                this.mCameraControlInternal.decrementUseCount();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$attachUseCases$15$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m21lambda$attachUseCases$15$androidxcameracamera2internalCamera2CameraImpl(List list) {
        try {
            tryAttachUseCases(list);
        } finally {
            this.mCameraControlInternal.decrementUseCount();
        }
    }

    private void tryAttachUseCases(Collection<UseCaseInfo> collection) {
        Size surfaceResolution;
        boolean isEmpty = this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty();
        ArrayList arrayList = new ArrayList();
        Rational rational = null;
        for (UseCaseInfo next : collection) {
            if (!this.mUseCaseAttachState.isUseCaseAttached(next.getUseCaseId())) {
                this.mUseCaseAttachState.setUseCaseAttached(next.getUseCaseId(), next.getSessionConfig(), next.getUseCaseConfig(), next.getStreamSpec(), next.getCaptureTypes());
                arrayList.add(next.getUseCaseId());
                if (next.getUseCaseType() == Preview.class && (surfaceResolution = next.getSurfaceResolution()) != null) {
                    rational = new Rational(surfaceResolution.getWidth(), surfaceResolution.getHeight());
                }
            }
        }
        if (!arrayList.isEmpty()) {
            debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
            if (isEmpty) {
                this.mCameraControlInternal.setActive(true);
                this.mCameraControlInternal.incrementUseCount();
            }
            addOrRemoveMeteringRepeatingUseCase();
            updateZslDisabledByUseCaseConfigStatus();
            updateCaptureSessionConfig();
            resetCaptureSession(false);
            if (this.mState == InternalState.OPENED) {
                openCaptureSession();
            } else {
                openInternal();
            }
            if (rational != null) {
                this.mCameraControlInternal.setPreviewAspectRatio(rational);
            }
        }
    }

    private Collection<UseCaseInfo> toUseCaseInfos(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList();
        for (UseCase from : collection) {
            arrayList.add(UseCaseInfo.from(from, this.mIsPrimary));
        }
        return arrayList;
    }

    public void setExtendedConfig(CameraConfig cameraConfig) {
        if (cameraConfig == null) {
            cameraConfig = CameraConfigs.defaultConfig();
        }
        SessionProcessor sessionProcessor = cameraConfig.getSessionProcessor((SessionProcessor) null);
        this.mCameraConfig = cameraConfig;
        synchronized (this.mLock) {
            this.mSessionProcessor = sessionProcessor;
        }
    }

    public CameraConfig getExtendedConfig() {
        return this.mCameraConfig;
    }

    private void notifyStateAttachedAndCameraControlReady(List<UseCase> list) {
        for (UseCase next : list) {
            String useCaseId = getUseCaseId(next);
            if (!this.mNotifyStateAttachedSet.contains(useCaseId)) {
                this.mNotifyStateAttachedSet.add(useCaseId);
                next.onStateAttached();
                next.onCameraControlReady();
            }
        }
    }

    private void notifyStateDetachedToUseCases(List<UseCase> list) {
        for (UseCase next : list) {
            String useCaseId = getUseCaseId(next);
            if (this.mNotifyStateAttachedSet.contains(useCaseId)) {
                next.onStateDetached();
                this.mNotifyStateAttachedSet.remove(useCaseId);
            }
        }
    }

    public void detachUseCases(Collection<UseCase> collection) {
        ArrayList arrayList = new ArrayList(collection);
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList(toUseCaseInfos(arrayList));
            notifyStateDetachedToUseCases(new ArrayList(arrayList));
            this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda10(this, arrayList2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: tryDetachUseCases */
    public void m23lambda$detachUseCases$16$androidxcameracamera2internalCamera2CameraImpl(Collection<UseCaseInfo> collection) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (UseCaseInfo next : collection) {
            if (this.mUseCaseAttachState.isUseCaseAttached(next.getUseCaseId())) {
                this.mUseCaseAttachState.removeUseCase(next.getUseCaseId());
                arrayList.add(next.getUseCaseId());
                if (next.getUseCaseType() == Preview.class) {
                    z = true;
                }
            }
        }
        if (!arrayList.isEmpty()) {
            debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
            if (z) {
                this.mCameraControlInternal.setPreviewAspectRatio((Rational) null);
            }
            addOrRemoveMeteringRepeatingUseCase();
            if (this.mUseCaseAttachState.getAttachedUseCaseConfigs().isEmpty()) {
                this.mCameraControlInternal.setZslDisabledByUserCaseConfig(false);
            } else {
                updateZslDisabledByUseCaseConfigStatus();
            }
            if (this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty()) {
                this.mCameraControlInternal.decrementUseCount();
                resetCaptureSession(false);
                this.mCameraControlInternal.setActive(false);
                this.mCaptureSession = newCaptureSession();
                closeInternal();
                return;
            }
            updateCaptureSessionConfig();
            resetCaptureSession(false);
            if (this.mState == InternalState.OPENED) {
                openCaptureSession();
            }
        }
    }

    private void updateZslDisabledByUseCaseConfigStatus() {
        boolean z = false;
        for (UseCaseConfig<?> isZslDisabled : this.mUseCaseAttachState.getAttachedUseCaseConfigs()) {
            z |= isZslDisabled.isZslDisabled(false);
        }
        this.mCameraControlInternal.setZslDisabledByUserCaseConfig(z);
    }

    private void addOrRemoveMeteringRepeatingUseCase() {
        SessionConfig build = this.mUseCaseAttachState.getAttachedBuilder().build();
        CaptureConfig repeatingCaptureConfig = build.getRepeatingCaptureConfig();
        int size = repeatingCaptureConfig.getSurfaces().size();
        int size2 = build.getSurfaces().size();
        if (build.getSurfaces().isEmpty()) {
            return;
        }
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            if (this.mMeteringRepeatingSession == null) {
                this.mMeteringRepeatingSession = new MeteringRepeatingSession(this.mCameraInfoInternal.getCameraCharacteristicsCompat(), this.mDisplayInfoManager, new Camera2CameraImpl$$ExternalSyntheticLambda9(this));
            }
            if (isSurfaceCombinationWithMeteringRepeatingSupported()) {
                addMeteringRepeating();
            } else {
                Logger.e(TAG, "Failed to add a repeating surface, CameraControl and ImageCapture may encounter issues due to the absence of repeating surface. Please add a UseCase (Preview or ImageAnalysis) that can provide a repeating surface for CameraControl and ImageCapture to function properly.");
            }
        } else if (size2 == 1 && size == 1) {
            removeMeteringRepeating();
        } else if (size >= 2) {
            removeMeteringRepeating();
        } else if (this.mMeteringRepeatingSession == null || isSurfaceCombinationWithMeteringRepeatingSupported()) {
            Logger.d(TAG, "No need to remove a previous mMeteringRepeating, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        } else {
            removeMeteringRepeating();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addOrRemoveMeteringRepeatingUseCase$17$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m20lambda$addOrRemoveMeteringRepeatingUseCase$17$androidxcameracamera2internalCamera2CameraImpl() {
        if (isMeteringRepeatingAttached()) {
            resetUseCase(getMeteringRepeatingId(this.mMeteringRepeatingSession), this.mMeteringRepeatingSession.getSessionConfig(), this.mMeteringRepeatingSession.getUseCaseConfig(), (StreamSpec) null, Collections.singletonList(UseCaseConfigFactory.CaptureType.METERING_REPEATING));
        }
    }

    private boolean isSurfaceCombinationWithMeteringRepeatingSupported() {
        ArrayList arrayList = new ArrayList();
        int cameraMode = getCameraMode();
        for (UseCaseAttachState.UseCaseAttachInfo next : this.mUseCaseAttachState.getAttachedUseCaseInfo()) {
            if (next.getCaptureTypes() == null || next.getCaptureTypes().get(0) != UseCaseConfigFactory.CaptureType.METERING_REPEATING) {
                if (next.getStreamSpec() == null || next.getCaptureTypes() == null) {
                    Logger.w(TAG, "Invalid stream spec or capture types in " + next);
                    return false;
                }
                SessionConfig sessionConfig = next.getSessionConfig();
                UseCaseConfig<?> useCaseConfig = next.getUseCaseConfig();
                for (DeferrableSurface next2 : sessionConfig.getSurfaces()) {
                    arrayList.add(AttachedSurfaceInfo.create(this.mSupportedSurfaceCombination.transformSurfaceConfig(cameraMode, useCaseConfig.getInputFormat(), next2.getPrescribedSize()), useCaseConfig.getInputFormat(), next2.getPrescribedSize(), next.getStreamSpec().getDynamicRange(), next.getCaptureTypes(), next.getStreamSpec().getImplementationOptions(), useCaseConfig.getTargetFrameRate((Range<Integer>) null)));
                }
            }
        }
        Preconditions.checkNotNull(this.mMeteringRepeatingSession);
        HashMap hashMap = new HashMap();
        hashMap.put(this.mMeteringRepeatingSession.getUseCaseConfig(), Collections.singletonList(this.mMeteringRepeatingSession.getMeteringRepeatingSize()));
        try {
            this.mSupportedSurfaceCombination.getSuggestedStreamSpecifications(cameraMode, arrayList, hashMap, false, false);
            debugLog("Surface combination with metering repeating supported!");
            return true;
        } catch (IllegalArgumentException e) {
            debugLog("Surface combination with metering repeating  not supported!", e);
            return false;
        }
    }

    private int getCameraMode() {
        synchronized (this.mLock) {
            if (this.mCameraCoordinator.getCameraOperatingMode() == 2) {
                return 1;
            }
            return 0;
        }
    }

    private void removeMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            this.mUseCaseAttachState.setUseCaseDetached(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mUseCaseAttachState.setUseCaseInactive(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mMeteringRepeatingSession.clear();
            this.mMeteringRepeatingSession = null;
        }
    }

    private void addMeteringRepeating() {
        MeteringRepeatingSession meteringRepeatingSession = this.mMeteringRepeatingSession;
        if (meteringRepeatingSession != null) {
            String meteringRepeatingId = getMeteringRepeatingId(meteringRepeatingSession);
            this.mUseCaseAttachState.setUseCaseAttached(meteringRepeatingId, this.mMeteringRepeatingSession.getSessionConfig(), this.mMeteringRepeatingSession.getUseCaseConfig(), (StreamSpec) null, Collections.singletonList(UseCaseConfigFactory.CaptureType.METERING_REPEATING));
            this.mUseCaseAttachState.setUseCaseActive(meteringRepeatingId, this.mMeteringRepeatingSession.getSessionConfig(), this.mMeteringRepeatingSession.getUseCaseConfig(), (StreamSpec) null, Collections.singletonList(UseCaseConfigFactory.CaptureType.METERING_REPEATING));
        }
    }

    public CameraInfoInternal getCameraInfoInternal() {
        return this.mCameraInfoInternal;
    }

    public CameraAvailability getCameraAvailability() {
        return this.mCameraAvailability;
    }

    /* access modifiers changed from: package-private */
    public void tryForceOpenCameraDevice(boolean z) {
        debugLog("Attempting to force open the camera.");
        if (!this.mCameraStateRegistry.tryOpenCamera(this)) {
            debugLog("No cameras available. Waiting for available camera before opening camera.");
            setState(InternalState.PENDING_OPEN);
            return;
        }
        openCameraDevice(z);
    }

    /* access modifiers changed from: package-private */
    public void tryOpenCameraDevice(boolean z) {
        debugLog("Attempting to open the camera.");
        if (!(this.mCameraAvailability.isCameraAvailable() && this.mCameraStateRegistry.tryOpenCamera(this))) {
            debugLog("No cameras available. Waiting for available camera before opening camera.");
            setState(InternalState.PENDING_OPEN);
            return;
        }
        openCameraDevice(z);
    }

    public void setPrimary(boolean z) {
        this.mIsPrimary = z;
    }

    public void setActiveResumingMode(boolean z) {
        this.mExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda15(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setActiveResumingMode$18$androidx-camera-camera2-internal-Camera2CameraImpl  reason: not valid java name */
    public /* synthetic */ void m36lambda$setActiveResumingMode$18$androidxcameracamera2internalCamera2CameraImpl(boolean z) {
        this.mIsActiveResumingMode = z;
        if (z && this.mState == InternalState.PENDING_OPEN) {
            tryForceOpenCameraDevice(false);
        }
    }

    private void openCameraDevice(boolean z) {
        if (!z) {
            this.mStateCallback.resetReopenMonitor();
        }
        this.mStateCallback.cancelScheduledReopen();
        this.mErrorTimeoutReopenScheduler.cancel();
        debugLog("Opening camera.");
        setState(InternalState.OPENING);
        try {
            this.mCameraManager.openCamera(this.mCameraInfoInternal.getCameraId(), this.mExecutor, createDeviceStateCallback());
        } catch (CameraAccessExceptionCompat e) {
            debugLog("Unable to open camera due to " + e.getMessage());
            if (e.getReason() != 10001) {
                this.mErrorTimeoutReopenScheduler.start();
            } else {
                setState(InternalState.INITIALIZED, CameraState.StateError.create(7, e));
            }
        } catch (SecurityException e2) {
            debugLog("Unable to open camera due to " + e2.getMessage());
            setState(InternalState.REOPENING);
            this.mStateCallback.scheduleCameraReopen();
        }
    }

    private class ErrorTimeoutReopenScheduler {
        private static final long ERROR_TIMEOUT_MILLIS = 2000;
        private ScheduleNode mScheduleNode;

        private ErrorTimeoutReopenScheduler() {
            this.mScheduleNode = null;
        }

        public void start() {
            if (Camera2CameraImpl.this.mState != InternalState.OPENING) {
                Camera2CameraImpl.this.debugLog("Don't need the onError timeout handler.");
                return;
            }
            Camera2CameraImpl.this.debugLog("Camera waiting for onError.");
            cancel();
            this.mScheduleNode = new ScheduleNode();
        }

        public boolean isErrorHandling() {
            ScheduleNode scheduleNode = this.mScheduleNode;
            return scheduleNode != null && !scheduleNode.isDone();
        }

        public void deviceOnError() {
            Camera2CameraImpl.this.debugLog("Camera receive onErrorCallback");
            cancel();
        }

        public void cancel() {
            ScheduleNode scheduleNode = this.mScheduleNode;
            if (scheduleNode != null) {
                scheduleNode.cancel();
            }
            this.mScheduleNode = null;
        }

        private class ScheduleNode {
            private final AtomicBoolean mIsDone = new AtomicBoolean(false);
            private final ScheduledFuture<?> mScheduledFuture;

            ScheduleNode() {
                this.mScheduledFuture = Camera2CameraImpl.this.mScheduledExecutorService.schedule(new Camera2CameraImpl$ErrorTimeoutReopenScheduler$ScheduleNode$$ExternalSyntheticLambda1(this), 2000, TimeUnit.MILLISECONDS);
            }

            /* access modifiers changed from: private */
            public void execute() {
                if (!this.mIsDone.getAndSet(true)) {
                    Camera2CameraImpl.this.mExecutor.execute(new Camera2CameraImpl$ErrorTimeoutReopenScheduler$ScheduleNode$$ExternalSyntheticLambda0(this));
                }
            }

            /* access modifiers changed from: private */
            public void executeInternal() {
                if (Camera2CameraImpl.this.mState != InternalState.OPENING) {
                    Camera2CameraImpl.this.debugLog("Camera skip reopen at state: " + Camera2CameraImpl.this.mState);
                    return;
                }
                Camera2CameraImpl.this.debugLog("Camera onError timeout, reopen it.");
                Camera2CameraImpl.this.setState(InternalState.REOPENING);
                Camera2CameraImpl.this.mStateCallback.scheduleCameraReopen();
            }

            public void cancel() {
                this.mIsDone.set(true);
                this.mScheduledFuture.cancel(true);
            }

            public boolean isDone() {
                return this.mIsDone.get();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateCaptureSessionConfig() {
        SessionConfig.ValidatingBuilder activeAndAttachedBuilder = this.mUseCaseAttachState.getActiveAndAttachedBuilder();
        if (activeAndAttachedBuilder.isValid()) {
            this.mCameraControlInternal.setTemplate(activeAndAttachedBuilder.build().getTemplateType());
            activeAndAttachedBuilder.add(this.mCameraControlInternal.getSessionConfig());
            this.mCaptureSession.setSessionConfig(activeAndAttachedBuilder.build());
            return;
        }
        this.mCameraControlInternal.resetTemplate();
        this.mCaptureSession.setSessionConfig(this.mCameraControlInternal.getSessionConfig());
    }

    /* access modifiers changed from: package-private */
    public void openCaptureSession() {
        Preconditions.checkState(this.mState == InternalState.OPENED);
        SessionConfig.ValidatingBuilder attachedBuilder = this.mUseCaseAttachState.getAttachedBuilder();
        if (!attachedBuilder.isValid()) {
            debugLog("Unable to create capture session due to conflicting configurations");
        } else if (!this.mCameraStateRegistry.tryOpenCaptureSession(this.mCameraDevice.getId(), this.mCameraCoordinator.getPairedConcurrentCameraId(this.mCameraDevice.getId()))) {
            debugLog("Unable to create capture session in camera operating mode = " + this.mCameraCoordinator.getCameraOperatingMode());
        } else {
            HashMap hashMap = new HashMap();
            StreamUseCaseUtil.populateSurfaceToStreamUseCaseMapping(this.mUseCaseAttachState.getAttachedSessionConfigs(), this.mUseCaseAttachState.getAttachedUseCaseConfigs(), hashMap);
            this.mCaptureSession.setStreamUseCaseMap(hashMap);
            final CaptureSessionInterface captureSessionInterface = this.mCaptureSession;
            Futures.addCallback(captureSessionInterface.open(attachedBuilder.build(), (CameraDevice) Preconditions.checkNotNull(this.mCameraDevice), this.mCaptureSessionOpenerBuilder.build()), new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                    if (Camera2CameraImpl.this.mCameraCoordinator.getCameraOperatingMode() == 2 && Camera2CameraImpl.this.mState == InternalState.OPENED) {
                        Camera2CameraImpl.this.setState(InternalState.CONFIGURED);
                    }
                }

                public void onFailure(Throwable th) {
                    if (th instanceof DeferrableSurface.SurfaceClosedException) {
                        SessionConfig findSessionConfigForSurface = Camera2CameraImpl.this.findSessionConfigForSurface(((DeferrableSurface.SurfaceClosedException) th).getDeferrableSurface());
                        if (findSessionConfigForSurface != null) {
                            Camera2CameraImpl.this.postSurfaceClosedError(findSessionConfigForSurface);
                        }
                    } else if (th instanceof CancellationException) {
                        Camera2CameraImpl.this.debugLog("Unable to configure camera cancelled");
                    } else {
                        if (Camera2CameraImpl.this.mState == InternalState.OPENED) {
                            Camera2CameraImpl.this.setState(InternalState.OPENED, CameraState.StateError.create(4, th));
                        }
                        Logger.e(Camera2CameraImpl.TAG, "Unable to configure camera " + Camera2CameraImpl.this, th);
                        if (Camera2CameraImpl.this.mCaptureSession == captureSessionInterface) {
                            Camera2CameraImpl.this.resetCaptureSession(false);
                        }
                    }
                }
            }, this.mExecutor);
        }
    }

    /* access modifiers changed from: package-private */
    public SessionConfig findSessionConfigForSurface(DeferrableSurface deferrableSurface) {
        for (SessionConfig next : this.mUseCaseAttachState.getAttachedSessionConfigs()) {
            if (next.getSurfaces().contains(deferrableSurface)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void postSurfaceClosedError(SessionConfig sessionConfig) {
        ScheduledExecutorService mainThreadExecutor = CameraXExecutors.mainThreadExecutor();
        SessionConfig.ErrorListener errorListener = sessionConfig.getErrorListener();
        if (errorListener != null) {
            debugLog("Posting surface closed", new Throwable());
            mainThreadExecutor.execute(new Camera2CameraImpl$$ExternalSyntheticLambda2(errorListener, sessionConfig));
        }
    }

    /* access modifiers changed from: package-private */
    public void resetCaptureSession(boolean z) {
        Preconditions.checkState(this.mCaptureSession != null);
        debugLog("Resetting Capture Session");
        CaptureSessionInterface captureSessionInterface = this.mCaptureSession;
        SessionConfig sessionConfig = captureSessionInterface.getSessionConfig();
        List<CaptureConfig> captureConfigs = captureSessionInterface.getCaptureConfigs();
        CaptureSessionInterface newCaptureSession = newCaptureSession();
        this.mCaptureSession = newCaptureSession;
        newCaptureSession.setSessionConfig(sessionConfig);
        this.mCaptureSession.issueCaptureRequests(captureConfigs);
        if (this.mState.ordinal() != 8) {
            debugLog("Skipping Capture Session state check due to current camera state: " + this.mState + " and previous session status: " + captureSessionInterface.isInOpenState());
        } else if (this.mShouldCloseCameraBeforeCreateNewSession && captureSessionInterface.isInOpenState()) {
            debugLog("Close camera before creating new session");
            setState(InternalState.REOPENING_QUIRK);
        }
        if (this.mConfigAndCloseQuirk && captureSessionInterface.isInOpenState()) {
            debugLog("ConfigAndClose is required when close the camera.");
            this.mIsConfigAndCloseRequired = true;
        }
        releaseSession(captureSessionInterface, z);
    }

    private CameraDevice.StateCallback createDeviceStateCallback() {
        ArrayList arrayList = new ArrayList(this.mUseCaseAttachState.getAttachedBuilder().build().getDeviceStateCallbacks());
        arrayList.add(this.mCaptureSessionRepository.getCameraStateCallback());
        arrayList.add(this.mStateCallback);
        return CameraDeviceStateCallbacks.createComboCallback((List<CameraDevice.StateCallback>) arrayList);
    }

    private boolean checkAndAttachRepeatingSurface(CaptureConfig.Builder builder) {
        if (!builder.getSurfaces().isEmpty()) {
            Logger.w(TAG, "The capture config builder already has surface inside.");
            return false;
        }
        for (SessionConfig repeatingCaptureConfig : this.mUseCaseAttachState.getActiveAndAttachedSessionConfigs()) {
            CaptureConfig repeatingCaptureConfig2 = repeatingCaptureConfig.getRepeatingCaptureConfig();
            List<DeferrableSurface> surfaces = repeatingCaptureConfig2.getSurfaces();
            if (!surfaces.isEmpty()) {
                if (repeatingCaptureConfig2.getPreviewStabilizationMode() != 0) {
                    builder.setPreviewStabilization(repeatingCaptureConfig2.getPreviewStabilizationMode());
                }
                if (repeatingCaptureConfig2.getVideoStabilizationMode() != 0) {
                    builder.setVideoStabilization(repeatingCaptureConfig2.getVideoStabilizationMode());
                }
                for (DeferrableSurface addSurface : surfaces) {
                    builder.addSurface(addSurface);
                }
            }
        }
        if (!builder.getSurfaces().isEmpty()) {
            return true;
        }
        Logger.w(TAG, "Unable to find a repeating surface to attach to CaptureConfig");
        return false;
    }

    public CameraControlInternal getCameraControlInternal() {
        return this.mCameraControlInternal;
    }

    /* access modifiers changed from: package-private */
    public void submitCaptureRequests(List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig next : list) {
            CaptureConfig.Builder from = CaptureConfig.Builder.from(next);
            if (next.getTemplateType() == 5 && next.getCameraCaptureResult() != null) {
                from.setCameraCaptureResult(next.getCameraCaptureResult());
            }
            if (!next.getSurfaces().isEmpty() || !next.isUseRepeatingSurface() || checkAndAttachRepeatingSurface(from)) {
                arrayList.add(from.build());
            }
        }
        debugLog("Issue capture request");
        this.mCaptureSession.issueCaptureRequests(arrayList);
    }

    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", new Object[]{Integer.valueOf(hashCode()), this.mCameraInfoInternal.getCameraId()});
    }

    static String getUseCaseId(UseCase useCase) {
        return useCase.getName() + useCase.hashCode();
    }

    static List<UseCaseConfigFactory.CaptureType> getCaptureTypes(UseCase useCase) {
        if (useCase.getCamera() == null) {
            return null;
        }
        return StreamSharing.getCaptureTypes(useCase);
    }

    static String getMeteringRepeatingId(MeteringRepeatingSession meteringRepeatingSession) {
        return meteringRepeatingSession.getName() + meteringRepeatingSession.hashCode();
    }

    /* access modifiers changed from: package-private */
    public void debugLog(String str) {
        debugLog(str, (Throwable) null);
    }

    private void debugLog(String str, Throwable th) {
        Logger.d(TAG, String.format("{%s} %s", new Object[]{toString(), str}), th);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState) {
        setState(internalState, (CameraState.StateError) null);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState, CameraState.StateError stateError) {
        setState(internalState, stateError, true);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState, CameraState.StateError stateError, boolean z) {
        CameraInternal.State state;
        debugLog("Transitioning camera internal state: " + this.mState + " --> " + internalState);
        traceInternalState(internalState, stateError);
        this.mState = internalState;
        switch (internalState.ordinal()) {
            case 0:
                state = CameraInternal.State.RELEASED;
                break;
            case 1:
                state = CameraInternal.State.RELEASING;
                break;
            case 2:
                state = CameraInternal.State.CLOSED;
                break;
            case 3:
                state = CameraInternal.State.PENDING_OPEN;
                break;
            case 4:
            case 5:
                state = CameraInternal.State.CLOSING;
                break;
            case 6:
            case 7:
                state = CameraInternal.State.OPENING;
                break;
            case 8:
                state = CameraInternal.State.OPEN;
                break;
            case 9:
                state = CameraInternal.State.CONFIGURED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + internalState);
        }
        this.mCameraStateRegistry.markCameraState(this, state, z);
        this.mObservableState.postValue(state);
        this.mCameraStateMachine.updateState(state, stateError);
    }

    /* access modifiers changed from: package-private */
    public void traceInternalState(InternalState internalState, CameraState.StateError stateError) {
        if (Trace.isEnabled()) {
            Trace.setCounter("CX:C2State[" + this + "]", internalState.ordinal());
            if (stateError != null) {
                this.mTraceStateErrorCount++;
            }
            if (this.mTraceStateErrorCount > 0) {
                Trace.setCounter("CX:C2StateErrorCode[" + this + "]", stateError != null ? stateError.getCode() : 0);
            }
        }
    }

    static abstract class UseCaseInfo {
        /* access modifiers changed from: package-private */
        public abstract List<UseCaseConfigFactory.CaptureType> getCaptureTypes();

        /* access modifiers changed from: package-private */
        public abstract SessionConfig getSessionConfig();

        /* access modifiers changed from: package-private */
        public abstract StreamSpec getStreamSpec();

        /* access modifiers changed from: package-private */
        public abstract Size getSurfaceResolution();

        /* access modifiers changed from: package-private */
        public abstract UseCaseConfig<?> getUseCaseConfig();

        /* access modifiers changed from: package-private */
        public abstract String getUseCaseId();

        /* access modifiers changed from: package-private */
        public abstract Class<?> getUseCaseType();

        UseCaseInfo() {
        }

        static UseCaseInfo create(String str, Class<?> cls, SessionConfig sessionConfig, UseCaseConfig<?> useCaseConfig, Size size, StreamSpec streamSpec, List<UseCaseConfigFactory.CaptureType> list) {
            return new AutoValue_Camera2CameraImpl_UseCaseInfo(str, cls, sessionConfig, useCaseConfig, size, streamSpec, list);
        }

        static UseCaseInfo from(UseCase useCase, boolean z) {
            SessionConfig sessionConfig;
            String useCaseId = Camera2CameraImpl.getUseCaseId(useCase);
            Class<?> cls = useCase.getClass();
            if (z) {
                sessionConfig = useCase.getSessionConfig();
            } else {
                sessionConfig = useCase.getSecondarySessionConfig();
            }
            return create(useCaseId, cls, sessionConfig, useCase.getCurrentConfig(), useCase.getAttachedSurfaceResolution(), useCase.getAttachedStreamSpec(), Camera2CameraImpl.getCaptureTypes(useCase));
        }
    }

    final class StateCallback extends CameraDevice.StateCallback {
        private final CameraReopenMonitor mCameraReopenMonitor;
        private final Executor mExecutor;
        ScheduledFuture<?> mScheduledReopenHandle;
        private ScheduledReopen mScheduledReopenRunnable;
        private final ScheduledExecutorService mScheduler;

        StateCallback(Executor executor, ScheduledExecutorService scheduledExecutorService, long j) {
            this.mExecutor = executor;
            this.mScheduler = scheduledExecutorService;
            this.mCameraReopenMonitor = new CameraReopenMonitor(j);
        }

        public void onOpened(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onOpened()");
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.mCameraDeviceError = 0;
            resetReopenMonitor();
            int ordinal = Camera2CameraImpl.this.mState.ordinal();
            if (ordinal == 1 || ordinal == 4) {
                Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
                Camera2CameraImpl.this.mCameraDevice.close();
                Camera2CameraImpl.this.mCameraDevice = null;
            } else if (ordinal == 5 || ordinal == 6 || ordinal == 7) {
                Camera2CameraImpl.this.setState(InternalState.OPENED);
                if (Camera2CameraImpl.this.mCameraStateRegistry.tryOpenCaptureSession(cameraDevice.getId(), Camera2CameraImpl.this.mCameraCoordinator.getPairedConcurrentCameraId(Camera2CameraImpl.this.mCameraDevice.getId()))) {
                    Camera2CameraImpl.this.openCaptureSession();
                }
            } else {
                throw new IllegalStateException("onOpened() should not be possible from state: " + Camera2CameraImpl.this.mState);
            }
        }

        public void onClosed(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onClosed()");
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDevice == null, "Unexpected onClose callback on camera device: " + cameraDevice);
            int ordinal = Camera2CameraImpl.this.mState.ordinal();
            if (ordinal == 1 || ordinal == 4) {
                Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
                Camera2CameraImpl.this.configAndCloseIfNeeded();
            } else if (ordinal != 5 && ordinal != 6) {
                throw new IllegalStateException("Camera closed while in state: " + Camera2CameraImpl.this.mState);
            } else if (Camera2CameraImpl.this.mCameraDeviceError != 0) {
                Camera2CameraImpl.this.debugLog("Camera closed due to error: " + Camera2CameraImpl.getErrorMessage(Camera2CameraImpl.this.mCameraDeviceError));
                scheduleCameraReopen();
            } else {
                Camera2CameraImpl.this.tryOpenCameraDevice(false);
            }
        }

        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        public void onError(CameraDevice cameraDevice, int i) {
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.mCameraDeviceError = i;
            Camera2CameraImpl.this.mErrorTimeoutReopenScheduler.deviceOnError();
            int ordinal = Camera2CameraImpl.this.mState.ordinal();
            if (ordinal != 1) {
                switch (ordinal) {
                    case 4:
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        Logger.d(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i), Camera2CameraImpl.this.mState.name()}));
                        handleErrorOnOpen(cameraDevice, i);
                        return;
                    default:
                        throw new IllegalStateException("onError() should not be possible from state: " + Camera2CameraImpl.this.mState);
                }
            }
            Logger.e(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i), Camera2CameraImpl.this.mState.name()}));
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void handleErrorOnOpen(CameraDevice cameraDevice, int i) {
            Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.OPENING || Camera2CameraImpl.this.mState == InternalState.OPENED || Camera2CameraImpl.this.mState == InternalState.CONFIGURED || Camera2CameraImpl.this.mState == InternalState.REOPENING || Camera2CameraImpl.this.mState == InternalState.REOPENING_QUIRK, "Attempt to handle open error from non open state: " + Camera2CameraImpl.this.mState);
            if (i == 1 || i == 2 || i == 4) {
                Logger.d(Camera2CameraImpl.TAG, String.format("Attempt to reopen camera[%s] after error[%s]", new Object[]{cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(i)}));
                reopenCameraAfterError(i);
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + Camera2CameraImpl.getErrorMessage(i) + " closing camera.");
            Camera2CameraImpl.this.setState(InternalState.CLOSING, CameraState.StateError.create(i == 3 ? 5 : 6));
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void reopenCameraAfterError(int i) {
            int i2 = 1;
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDeviceError != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            if (i == 1) {
                i2 = 2;
            } else if (i != 2) {
                i2 = 3;
            }
            Camera2CameraImpl.this.setState(InternalState.REOPENING, CameraState.StateError.create(i2));
            Camera2CameraImpl.this.closeCamera(false);
        }

        /* access modifiers changed from: package-private */
        public void scheduleCameraReopen() {
            boolean z = true;
            Preconditions.checkState(this.mScheduledReopenRunnable == null);
            if (this.mScheduledReopenHandle != null) {
                z = false;
            }
            Preconditions.checkState(z);
            if (this.mCameraReopenMonitor.canScheduleCameraReopen()) {
                this.mScheduledReopenRunnable = new ScheduledReopen(this.mExecutor);
                Camera2CameraImpl.this.debugLog("Attempting camera re-open in " + this.mCameraReopenMonitor.getReopenDelayMs() + "ms: " + this.mScheduledReopenRunnable + " activeResuming = " + Camera2CameraImpl.this.mIsActiveResumingMode);
                this.mScheduledReopenHandle = this.mScheduler.schedule(this.mScheduledReopenRunnable, (long) this.mCameraReopenMonitor.getReopenDelayMs(), TimeUnit.MILLISECONDS);
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Camera reopening attempted for " + this.mCameraReopenMonitor.getReopenLimitMs() + "ms without success.");
            Camera2CameraImpl.this.setState(InternalState.PENDING_OPEN, (CameraState.StateError) null, false);
        }

        /* access modifiers changed from: package-private */
        public boolean cancelScheduledReopen() {
            if (this.mScheduledReopenHandle == null) {
                return false;
            }
            Camera2CameraImpl.this.debugLog("Cancelling scheduled re-open: " + this.mScheduledReopenRunnable);
            this.mScheduledReopenRunnable.cancel();
            this.mScheduledReopenRunnable = null;
            this.mScheduledReopenHandle.cancel(false);
            this.mScheduledReopenHandle = null;
            return true;
        }

        /* access modifiers changed from: package-private */
        public void resetReopenMonitor() {
            this.mCameraReopenMonitor.reset();
        }

        class ScheduledReopen implements Runnable {
            private boolean mCancelled = false;
            private Executor mExecutor;

            ScheduledReopen(Executor executor) {
                this.mExecutor = executor;
            }

            /* access modifiers changed from: package-private */
            public void cancel() {
                this.mCancelled = true;
            }

            public void run() {
                this.mExecutor.execute(new Camera2CameraImpl$StateCallback$ScheduledReopen$$ExternalSyntheticLambda0(this));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$run$0$androidx-camera-camera2-internal-Camera2CameraImpl$StateCallback$ScheduledReopen  reason: not valid java name */
            public /* synthetic */ void m40lambda$run$0$androidxcameracamera2internalCamera2CameraImpl$StateCallback$ScheduledReopen() {
                if (!this.mCancelled) {
                    Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.REOPENING || Camera2CameraImpl.this.mState == InternalState.REOPENING_QUIRK);
                    if (StateCallback.this.shouldActiveResume()) {
                        Camera2CameraImpl.this.tryForceOpenCameraDevice(true);
                    } else {
                        Camera2CameraImpl.this.tryOpenCameraDevice(true);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean shouldActiveResume() {
            return Camera2CameraImpl.this.mIsActiveResumingMode && (Camera2CameraImpl.this.mCameraDeviceError == 1 || Camera2CameraImpl.this.mCameraDeviceError == 2);
        }

        class CameraReopenMonitor {
            static final int ACTIVE_REOPEN_DELAY_BASE_MS = 1000;
            static final int ACTIVE_REOPEN_LIMIT_MS = 1800000;
            static final int INVALID_TIME = -1;
            static final int REOPEN_DELAY_MS = 700;
            static final int REOPEN_LIMIT_MS = 10000;
            private final long mCameraOpenRetryMaxTimeoutInMs;
            private long mFirstReopenTime = -1;

            CameraReopenMonitor(long j) {
                this.mCameraOpenRetryMaxTimeoutInMs = j;
            }

            /* access modifiers changed from: package-private */
            public int getReopenDelayMs() {
                if (!StateCallback.this.shouldActiveResume()) {
                    return 700;
                }
                long elapsedTime = getElapsedTime();
                if (elapsedTime <= 120000) {
                    return 1000;
                }
                return elapsedTime <= 300000 ? 2000 : 4000;
            }

            /* access modifiers changed from: package-private */
            public int getReopenLimitMs() {
                if (!StateCallback.this.shouldActiveResume()) {
                    long j = this.mCameraOpenRetryMaxTimeoutInMs;
                    if (j > 0) {
                        return Math.min((int) j, 10000);
                    }
                    return 10000;
                }
                long j2 = this.mCameraOpenRetryMaxTimeoutInMs;
                if (j2 > 0) {
                    return Math.min((int) j2, ACTIVE_REOPEN_LIMIT_MS);
                }
                return ACTIVE_REOPEN_LIMIT_MS;
            }

            /* access modifiers changed from: package-private */
            public long getElapsedTime() {
                long uptimeMillis = SystemClock.uptimeMillis();
                if (this.mFirstReopenTime == -1) {
                    this.mFirstReopenTime = uptimeMillis;
                }
                return uptimeMillis - this.mFirstReopenTime;
            }

            /* access modifiers changed from: package-private */
            public boolean canScheduleCameraReopen() {
                if (!(getElapsedTime() >= ((long) getReopenLimitMs()))) {
                    return true;
                }
                reset();
                return false;
            }

            /* access modifiers changed from: package-private */
            public void reset() {
                this.mFirstReopenTime = -1;
            }
        }
    }

    final class CameraAvailability extends CameraManager.AvailabilityCallback implements CameraStateRegistry.OnOpenAvailableListener {
        private boolean mCameraAvailable = true;
        private final String mCameraId;

        CameraAvailability(String str) {
            this.mCameraId = str;
        }

        public void onCameraAvailable(String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = true;
                if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                    Camera2CameraImpl.this.tryOpenCameraDevice(false);
                }
            }
        }

        public void onCameraUnavailable(String str) {
            if (this.mCameraId.equals(str)) {
                this.mCameraAvailable = false;
            }
        }

        public void onOpenAvailable() {
            if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                Camera2CameraImpl.this.tryOpenCameraDevice(false);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isCameraAvailable() {
            return this.mCameraAvailable;
        }
    }

    final class CameraConfigureAvailable implements CameraStateRegistry.OnConfigureAvailableListener {
        CameraConfigureAvailable() {
        }

        public void onConfigureAvailable() {
            if (Camera2CameraImpl.this.mState == InternalState.OPENED) {
                Camera2CameraImpl.this.openCaptureSession();
            }
        }
    }

    final class ControlUpdateListenerInternal implements CameraControlInternal.ControlUpdateCallback {
        ControlUpdateListenerInternal() {
        }

        public void onCameraControlUpdateSessionConfig() {
            Camera2CameraImpl.this.updateCaptureSessionConfig();
        }

        public void onCameraControlCaptureRequests(List<CaptureConfig> list) {
            Camera2CameraImpl.this.submitCaptureRequests((List) Preconditions.checkNotNull(list));
        }
    }
}
