package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.DynamicRangeProfiles;
import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.DynamicRangeConversions;
import androidx.camera.camera2.internal.compat.params.DynamicRangesCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.quirk.CaptureNoResponseQuirk;
import androidx.camera.camera2.internal.compat.workaround.RequestMonitor;
import androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow;
import androidx.camera.camera2.internal.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.internal.compat.workaround.TorchStateReset;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;

final class CaptureSession implements CaptureSessionInterface {
    private static final String TAG = "CaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    private final List<CaptureConfig> mCaptureConfigs;
    private final StateCallback mCaptureSessionStateCallback;
    List<DeferrableSurface> mConfiguredDeferrableSurfaces;
    private final Map<DeferrableSurface, Surface> mConfiguredSurfaceMap;
    private final DynamicRangesCompat mDynamicRangesCompat;
    CallbackToFutureAdapter.Completer<Void> mReleaseCompleter;
    ListenableFuture<Void> mReleaseFuture;
    private final RequestMonitor mRequestMonitor;
    SessionConfig mSessionConfig;
    final Object mSessionLock;
    SynchronizedCaptureSession.Opener mSessionOpener;
    State mState;
    private final StillCaptureFlow mStillCaptureFlow;
    private Map<DeferrableSurface, Long> mStreamUseCaseMap;
    SynchronizedCaptureSession mSynchronizedCaptureSession;
    private final TemplateParamsOverride mTemplateParamsOverride;
    /* access modifiers changed from: private */
    public final TorchStateReset mTorchStateReset;

    enum State {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    CaptureSession(DynamicRangesCompat dynamicRangesCompat) {
        this(dynamicRangesCompat, new Quirks(Collections.emptyList()));
    }

    CaptureSession(DynamicRangesCompat dynamicRangesCompat, Quirks quirks) {
        this.mSessionLock = new Object();
        this.mCaptureConfigs = new ArrayList();
        this.mConfiguredSurfaceMap = new HashMap();
        this.mConfiguredDeferrableSurfaces = Collections.emptyList();
        this.mState = State.UNINITIALIZED;
        this.mStreamUseCaseMap = new HashMap();
        this.mStillCaptureFlow = new StillCaptureFlow();
        this.mTorchStateReset = new TorchStateReset();
        this.mState = State.INITIALIZED;
        this.mDynamicRangesCompat = dynamicRangesCompat;
        this.mCaptureSessionStateCallback = new StateCallback();
        this.mRequestMonitor = new RequestMonitor(quirks.contains(CaptureNoResponseQuirk.class));
        this.mTemplateParamsOverride = new TemplateParamsOverride(quirks);
    }

    public void setStreamUseCaseMap(Map<DeferrableSurface, Long> map) {
        synchronized (this.mSessionLock) {
            this.mStreamUseCaseMap = map;
        }
    }

    public SessionConfig getSessionConfig() {
        SessionConfig sessionConfig;
        synchronized (this.mSessionLock) {
            sessionConfig = this.mSessionConfig;
        }
        return sessionConfig;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSessionConfig(androidx.camera.core.impl.SessionConfig r4) {
        /*
            r3 = this;
            java.lang.String r0 = "setSessionConfig() should not be possible in state: "
            java.lang.Object r1 = r3.mSessionLock
            monitor-enter(r1)
            androidx.camera.camera2.internal.CaptureSession$State r2 = r3.mState     // Catch:{ all -> 0x005d }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x005d }
            switch(r2) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0043;
                case 2: goto L_0x0043;
                case 3: goto L_0x0043;
                case 4: goto L_0x0017;
                case 5: goto L_0x000f;
                case 6: goto L_0x000f;
                case 7: goto L_0x000f;
                default: goto L_0x000e;
            }     // Catch:{ all -> 0x005d }
        L_0x000e:
            goto L_0x005b
        L_0x000f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x005d }
            java.lang.String r0 = "Session configuration cannot be set on a closed/released session."
            r4.<init>(r0)     // Catch:{ all -> 0x005d }
            throw r4     // Catch:{ all -> 0x005d }
        L_0x0017:
            r3.mSessionConfig = r4     // Catch:{ all -> 0x005d }
            if (r4 != 0) goto L_0x001d
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            return
        L_0x001d:
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r0 = r3.mConfiguredSurfaceMap     // Catch:{ all -> 0x005d }
            java.util.Set r0 = r0.keySet()     // Catch:{ all -> 0x005d }
            java.util.List r4 = r4.getSurfaces()     // Catch:{ all -> 0x005d }
            boolean r4 = r0.containsAll(r4)     // Catch:{ all -> 0x005d }
            if (r4 != 0) goto L_0x0036
            java.lang.String r4 = "CaptureSession"
            java.lang.String r0 = "Does not have the proper configured lists"
            androidx.camera.core.Logger.e(r4, r0)     // Catch:{ all -> 0x005d }
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            return
        L_0x0036:
            java.lang.String r4 = "CaptureSession"
            java.lang.String r0 = "Attempting to submit CaptureRequest after setting"
            androidx.camera.core.Logger.d(r4, r0)     // Catch:{ all -> 0x005d }
            androidx.camera.core.impl.SessionConfig r4 = r3.mSessionConfig     // Catch:{ all -> 0x005d }
            r3.issueRepeatingCaptureRequests(r4)     // Catch:{ all -> 0x005d }
            goto L_0x005b
        L_0x0043:
            r3.mSessionConfig = r4     // Catch:{ all -> 0x005d }
            goto L_0x005b
        L_0x0046:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r2.<init>(r0)     // Catch:{ all -> 0x005d }
            androidx.camera.camera2.internal.CaptureSession$State r0 = r3.mState     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x005d }
            r4.<init>(r0)     // Catch:{ all -> 0x005d }
            throw r4     // Catch:{ all -> 0x005d }
        L_0x005b:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            return
        L_0x005d:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.setSessionConfig(androidx.camera.core.impl.SessionConfig):void");
    }

    public ListenableFuture<Void> open(SessionConfig sessionConfig, CameraDevice cameraDevice, SynchronizedCaptureSession.Opener opener) {
        synchronized (this.mSessionLock) {
            if (this.mState.ordinal() != 1) {
                Logger.e(TAG, "Open not allowed in state: " + this.mState);
                ListenableFuture<Void> immediateFailedFuture = Futures.immediateFailedFuture(new IllegalStateException("open() should not allow the state: " + this.mState));
                return immediateFailedFuture;
            }
            this.mState = State.GET_SURFACE;
            ArrayList arrayList = new ArrayList(sessionConfig.getSurfaces());
            this.mConfiguredDeferrableSurfaces = arrayList;
            this.mSessionOpener = opener;
            FutureChain<T> transformAsync = FutureChain.from(opener.startWithDeferrableSurface(arrayList, 5000)).transformAsync(new CaptureSession$$ExternalSyntheticLambda3(this, sessionConfig, cameraDevice), this.mSessionOpener.getExecutor());
            Futures.addCallback(transformAsync, new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                }

                public void onFailure(Throwable th) {
                    synchronized (CaptureSession.this.mSessionLock) {
                        CaptureSession.this.mSessionOpener.stop();
                        int ordinal = CaptureSession.this.mState.ordinal();
                        if (ordinal == 3 || ordinal == 5 || ordinal == 6) {
                            if (!(th instanceof CancellationException)) {
                                Logger.w(CaptureSession.TAG, "Opening session with fail " + CaptureSession.this.mState, th);
                                CaptureSession.this.finishClose();
                            }
                        }
                    }
                }
            }, this.mSessionOpener.getExecutor());
            ListenableFuture<Void> nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
            return nonCancellationPropagating;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0112, code lost:
        return androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: openCaptureSession */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<java.lang.Void> m62lambda$open$0$androidxcameracamera2internalCaptureSession(java.util.List<android.view.Surface> r10, androidx.camera.core.impl.SessionConfig r11, android.hardware.camera2.CameraDevice r12) {
        /*
            r9 = this;
            java.lang.String r0 = "openCaptureSession() not execute in state: "
            java.lang.String r1 = "openCaptureSession() should not be possible in state: "
            java.lang.Object r2 = r9.mSessionLock
            monitor-enter(r2)
            androidx.camera.camera2.internal.CaptureSession$State r3 = r9.mState     // Catch:{ all -> 0x012d }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x012d }
            if (r3 == 0) goto L_0x0113
            r4 = 1
            if (r3 == r4) goto L_0x0113
            r5 = 2
            if (r3 == r5) goto L_0x0032
            r10 = 4
            if (r3 == r10) goto L_0x0113
            java.util.concurrent.CancellationException r10 = new java.util.concurrent.CancellationException     // Catch:{ all -> 0x012d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x012d }
            r11.<init>(r0)     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.CaptureSession$State r12 = r9.mState     // Catch:{ all -> 0x012d }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x012d }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x012d }
            r10.<init>(r11)     // Catch:{ all -> 0x012d }
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10)     // Catch:{ all -> 0x012d }
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            return r10
        L_0x0032:
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r0 = r9.mConfiguredSurfaceMap     // Catch:{ all -> 0x012d }
            r0.clear()     // Catch:{ all -> 0x012d }
            r0 = 0
            r1 = r0
        L_0x0039:
            int r3 = r10.size()     // Catch:{ all -> 0x012d }
            if (r1 >= r3) goto L_0x0055
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r3 = r9.mConfiguredSurfaceMap     // Catch:{ all -> 0x012d }
            java.util.List<androidx.camera.core.impl.DeferrableSurface> r6 = r9.mConfiguredDeferrableSurfaces     // Catch:{ all -> 0x012d }
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.DeferrableSurface r6 = (androidx.camera.core.impl.DeferrableSurface) r6     // Catch:{ all -> 0x012d }
            java.lang.Object r7 = r10.get(r1)     // Catch:{ all -> 0x012d }
            android.view.Surface r7 = (android.view.Surface) r7     // Catch:{ all -> 0x012d }
            r3.put(r6, r7)     // Catch:{ all -> 0x012d }
            int r1 = r1 + 1
            goto L_0x0039
        L_0x0055:
            androidx.camera.camera2.internal.CaptureSession$State r10 = androidx.camera.camera2.internal.CaptureSession.State.OPENING     // Catch:{ all -> 0x012d }
            r9.mState = r10     // Catch:{ all -> 0x012d }
            java.lang.String r10 = "CaptureSession"
            java.lang.String r1 = "Opening capture session."
            androidx.camera.core.Logger.d(r10, r1)     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$StateCallback[] r10 = new androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback[r5]     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.CaptureSession$StateCallback r1 = r9.mCaptureSessionStateCallback     // Catch:{ all -> 0x012d }
            r10[r0] = r1     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks$Adapter r0 = new androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks$Adapter     // Catch:{ all -> 0x012d }
            java.util.List r1 = r11.getSessionStateCallbacks()     // Catch:{ all -> 0x012d }
            r0.<init>((java.util.List<android.hardware.camera2.CameraCaptureSession.StateCallback>) r1)     // Catch:{ all -> 0x012d }
            r10[r4] = r0     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$StateCallback r10 = androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks.createComboCallback(r10)     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.impl.Camera2ImplConfig r0 = new androidx.camera.camera2.impl.Camera2ImplConfig     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.Config r1 = r11.getImplementationOptions()     // Catch:{ all -> 0x012d }
            r0.<init>(r1)     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.CaptureConfig r1 = r11.getRepeatingCaptureConfig()     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.CaptureConfig$Builder r1 = androidx.camera.core.impl.CaptureConfig.Builder.from(r1)     // Catch:{ all -> 0x012d }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x012d }
            r3.<init>()     // Catch:{ all -> 0x012d }
            r4 = 0
            java.lang.String r0 = r0.getPhysicalCameraId(r4)     // Catch:{ all -> 0x012d }
            java.util.List r4 = r11.getOutputConfigs()     // Catch:{ all -> 0x012d }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x012d }
        L_0x0098:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x012d }
            if (r5 == 0) goto L_0x00cd
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.SessionConfig$OutputConfig r5 = (androidx.camera.core.impl.SessionConfig.OutputConfig) r5     // Catch:{ all -> 0x012d }
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, android.view.Surface> r6 = r9.mConfiguredSurfaceMap     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat r6 = r9.getOutputConfigurationCompat(r5, r6, r0)     // Catch:{ all -> 0x012d }
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, java.lang.Long> r7 = r9.mStreamUseCaseMap     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.DeferrableSurface r8 = r5.getSurface()     // Catch:{ all -> 0x012d }
            boolean r7 = r7.containsKey(r8)     // Catch:{ all -> 0x012d }
            if (r7 == 0) goto L_0x00c9
            java.util.Map<androidx.camera.core.impl.DeferrableSurface, java.lang.Long> r7 = r9.mStreamUseCaseMap     // Catch:{ all -> 0x012d }
            androidx.camera.core.impl.DeferrableSurface r5 = r5.getSurface()     // Catch:{ all -> 0x012d }
            java.lang.Object r5 = r7.get(r5)     // Catch:{ all -> 0x012d }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x012d }
            long r7 = r5.longValue()     // Catch:{ all -> 0x012d }
            r6.setStreamUseCase(r7)     // Catch:{ all -> 0x012d }
        L_0x00c9:
            r3.add(r6)     // Catch:{ all -> 0x012d }
            goto L_0x0098
        L_0x00cd:
            java.util.List r0 = r9.getUniqueOutputConfigurations(r3)     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r3 = r9.mSessionOpener     // Catch:{ all -> 0x012d }
            int r4 = r11.getSessionType()     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat r10 = r3.createSessionConfigurationCompat(r4, r0, r10)     // Catch:{ all -> 0x012d }
            int r0 = r11.getTemplateType()     // Catch:{ all -> 0x012d }
            r3 = 5
            if (r0 != r3) goto L_0x00f3
            android.hardware.camera2.params.InputConfiguration r0 = r11.getInputConfiguration()     // Catch:{ all -> 0x012d }
            if (r0 == 0) goto L_0x00f3
            android.hardware.camera2.params.InputConfiguration r11 = r11.getInputConfiguration()     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.compat.params.InputConfigurationCompat r11 = androidx.camera.camera2.internal.compat.params.InputConfigurationCompat.wrap(r11)     // Catch:{ all -> 0x012d }
            r10.setInputConfiguration(r11)     // Catch:{ all -> 0x012d }
        L_0x00f3:
            androidx.camera.core.impl.CaptureConfig r11 = r1.build()     // Catch:{ CameraAccessException -> 0x010c }
            androidx.camera.camera2.internal.compat.workaround.TemplateParamsOverride r0 = r9.mTemplateParamsOverride     // Catch:{ CameraAccessException -> 0x010c }
            android.hardware.camera2.CaptureRequest r11 = androidx.camera.camera2.internal.Camera2CaptureRequestBuilder.buildWithoutTarget(r11, r12, r0)     // Catch:{ CameraAccessException -> 0x010c }
            if (r11 == 0) goto L_0x0102
            r10.setSessionParameters(r11)     // Catch:{ CameraAccessException -> 0x010c }
        L_0x0102:
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r11 = r9.mSessionOpener     // Catch:{ all -> 0x012d }
            java.util.List<androidx.camera.core.impl.DeferrableSurface> r0 = r9.mConfiguredDeferrableSurfaces     // Catch:{ all -> 0x012d }
            com.google.common.util.concurrent.ListenableFuture r10 = r11.openCaptureSession(r12, r10, r0)     // Catch:{ all -> 0x012d }
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            return r10
        L_0x010c:
            r10 = move-exception
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10)     // Catch:{ all -> 0x012d }
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            return r10
        L_0x0113:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x012d }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x012d }
            r11.<init>(r1)     // Catch:{ all -> 0x012d }
            androidx.camera.camera2.internal.CaptureSession$State r12 = r9.mState     // Catch:{ all -> 0x012d }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x012d }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x012d }
            r10.<init>(r11)     // Catch:{ all -> 0x012d }
            com.google.common.util.concurrent.ListenableFuture r10 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r10)     // Catch:{ all -> 0x012d }
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            return r10
        L_0x012d:
            r10 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x012d }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.m62lambda$open$0$androidxcameracamera2internalCaptureSession(java.util.List, androidx.camera.core.impl.SessionConfig, android.hardware.camera2.CameraDevice):com.google.common.util.concurrent.ListenableFuture");
    }

    private List<OutputConfigurationCompat> getUniqueOutputConfigurations(List<OutputConfigurationCompat> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (OutputConfigurationCompat next : list) {
            if (!arrayList.contains(next.getSurface())) {
                arrayList.add(next.getSurface());
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    private OutputConfigurationCompat getOutputConfigurationCompat(SessionConfig.OutputConfig outputConfig, Map<DeferrableSurface, Surface> map, String str) {
        long j;
        DynamicRangeProfiles dynamicRangeProfiles;
        Surface surface = map.get(outputConfig.getSurface());
        Preconditions.checkNotNull(surface, "Surface in OutputConfig not found in configuredSurfaceMap.");
        OutputConfigurationCompat outputConfigurationCompat = new OutputConfigurationCompat(outputConfig.getSurfaceGroupId(), surface);
        if (str != null) {
            outputConfigurationCompat.setPhysicalCameraId(str);
        } else {
            outputConfigurationCompat.setPhysicalCameraId(outputConfig.getPhysicalCameraId());
        }
        if (outputConfig.getMirrorMode() == 0) {
            outputConfigurationCompat.setMirrorMode(1);
        } else if (outputConfig.getMirrorMode() == 1) {
            outputConfigurationCompat.setMirrorMode(2);
        }
        if (!outputConfig.getSharedSurfaces().isEmpty()) {
            outputConfigurationCompat.enableSurfaceSharing();
            for (DeferrableSurface deferrableSurface : outputConfig.getSharedSurfaces()) {
                Surface surface2 = map.get(deferrableSurface);
                Preconditions.checkNotNull(surface2, "Surface in OutputConfig not found in configuredSurfaceMap.");
                outputConfigurationCompat.addSurface(surface2);
            }
        }
        if (Build.VERSION.SDK_INT >= 33 && (dynamicRangeProfiles = this.mDynamicRangesCompat.toDynamicRangeProfiles()) != null) {
            DynamicRange dynamicRange = outputConfig.getDynamicRange();
            Long dynamicRangeToFirstSupportedProfile = DynamicRangeConversions.dynamicRangeToFirstSupportedProfile(dynamicRange, dynamicRangeProfiles);
            if (dynamicRangeToFirstSupportedProfile == null) {
                Logger.e(TAG, "Requested dynamic range is not supported. Defaulting to STANDARD dynamic range profile.\nRequested dynamic range:\n  " + dynamicRange);
            } else {
                j = dynamicRangeToFirstSupportedProfile.longValue();
                outputConfigurationCompat.setDynamicRangeProfile(j);
                return outputConfigurationCompat;
            }
        }
        j = 1;
        outputConfigurationCompat.setDynamicRangeProfile(j);
        return outputConfigurationCompat;
    }

    public void close() {
        synchronized (this.mSessionLock) {
            int ordinal = this.mState.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        Preconditions.checkNotNull(this.mSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                        this.mSessionOpener.stop();
                    } else if (ordinal == 3 || ordinal == 4) {
                        Preconditions.checkNotNull(this.mSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                        this.mSessionOpener.stop();
                        this.mState = State.CLOSED;
                        this.mRequestMonitor.stop();
                        this.mSessionConfig = null;
                    }
                }
                this.mState = State.RELEASED;
            } else {
                throw new IllegalStateException("close() should not be possible in state: " + this.mState);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        r5.mState = androidx.camera.camera2.internal.CaptureSession.State.RELEASING;
        r5.mRequestMonitor.stop();
        androidx.core.util.Preconditions.checkNotNull(r5.mSessionOpener, "The Opener shouldn't null in state:" + r5.mState);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        if (r5.mSessionOpener.stop() == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        finishClose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        if (r5.mReleaseFuture != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        r5.mReleaseFuture = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(new androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0063, code lost:
        r6 = r5.mReleaseFuture;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r5.mState = androidx.camera.camera2.internal.CaptureSession.State.RELEASED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a0, code lost:
        return androidx.camera.core.impl.utils.futures.Futures.immediateFuture(null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<java.lang.Void> release(boolean r6) {
        /*
            r5 = this;
            java.lang.String r0 = "The Opener shouldn't null in state:"
            java.lang.String r1 = "The Opener shouldn't null in state:"
            java.lang.String r2 = "release() should not be possible in state: "
            java.lang.Object r3 = r5.mSessionLock
            monitor-enter(r3)
            androidx.camera.camera2.internal.CaptureSession$State r4 = r5.mState     // Catch:{ all -> 0x00a1 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x00a1 }
            switch(r4) {
                case 0: goto L_0x0085;
                case 1: goto L_0x0080;
                case 2: goto L_0x0067;
                case 3: goto L_0x002b;
                case 4: goto L_0x0014;
                case 5: goto L_0x0014;
                case 6: goto L_0x0054;
                default: goto L_0x0012;
            }     // Catch:{ all -> 0x00a1 }
        L_0x0012:
            goto L_0x009a
        L_0x0014:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r1 = r5.mSynchronizedCaptureSession     // Catch:{ all -> 0x00a1 }
            if (r1 == 0) goto L_0x002b
            if (r6 == 0) goto L_0x0026
            r1.abortCaptures()     // Catch:{ CameraAccessException -> 0x001e }
            goto L_0x0026
        L_0x001e:
            r6 = move-exception
            java.lang.String r1 = "CaptureSession"
            java.lang.String r2 = "Unable to abort captures."
            androidx.camera.core.Logger.e(r1, r2, r6)     // Catch:{ all -> 0x00a1 }
        L_0x0026:
            androidx.camera.camera2.internal.SynchronizedCaptureSession r6 = r5.mSynchronizedCaptureSession     // Catch:{ all -> 0x00a1 }
            r6.close()     // Catch:{ all -> 0x00a1 }
        L_0x002b:
            androidx.camera.camera2.internal.CaptureSession$State r6 = androidx.camera.camera2.internal.CaptureSession.State.RELEASING     // Catch:{ all -> 0x00a1 }
            r5.mState = r6     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.compat.workaround.RequestMonitor r6 = r5.mRequestMonitor     // Catch:{ all -> 0x00a1 }
            r6.stop()     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r6 = r5.mSessionOpener     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r1.<init>(r0)     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.CaptureSession$State r0 = r5.mState     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x00a1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a1 }
            androidx.core.util.Preconditions.checkNotNull(r6, r0)     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r6 = r5.mSessionOpener     // Catch:{ all -> 0x00a1 }
            boolean r6 = r6.stop()     // Catch:{ all -> 0x00a1 }
            if (r6 == 0) goto L_0x0054
            r5.finishClose()     // Catch:{ all -> 0x00a1 }
            goto L_0x009a
        L_0x0054:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r6 = r5.mReleaseFuture     // Catch:{ all -> 0x00a1 }
            if (r6 != 0) goto L_0x0063
            androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2 r6 = new androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2     // Catch:{ all -> 0x00a1 }
            r6.<init>(r5)     // Catch:{ all -> 0x00a1 }
            com.google.common.util.concurrent.ListenableFuture r6 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r6)     // Catch:{ all -> 0x00a1 }
            r5.mReleaseFuture = r6     // Catch:{ all -> 0x00a1 }
        L_0x0063:
            com.google.common.util.concurrent.ListenableFuture<java.lang.Void> r6 = r5.mReleaseFuture     // Catch:{ all -> 0x00a1 }
            monitor-exit(r3)     // Catch:{ all -> 0x00a1 }
            return r6
        L_0x0067:
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r6 = r5.mSessionOpener     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r0.<init>(r1)     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.CaptureSession$State r1 = r5.mState     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a1 }
            androidx.core.util.Preconditions.checkNotNull(r6, r0)     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.SynchronizedCaptureSession$Opener r6 = r5.mSessionOpener     // Catch:{ all -> 0x00a1 }
            r6.stop()     // Catch:{ all -> 0x00a1 }
        L_0x0080:
            androidx.camera.camera2.internal.CaptureSession$State r6 = androidx.camera.camera2.internal.CaptureSession.State.RELEASED     // Catch:{ all -> 0x00a1 }
            r5.mState = r6     // Catch:{ all -> 0x00a1 }
            goto L_0x009a
        L_0x0085:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r0.<init>(r2)     // Catch:{ all -> 0x00a1 }
            androidx.camera.camera2.internal.CaptureSession$State r1 = r5.mState     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00a1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a1 }
            r6.<init>(r0)     // Catch:{ all -> 0x00a1 }
            throw r6     // Catch:{ all -> 0x00a1 }
        L_0x009a:
            monitor-exit(r3)     // Catch:{ all -> 0x00a1 }
            r6 = 0
            com.google.common.util.concurrent.ListenableFuture r6 = androidx.camera.core.impl.utils.futures.Futures.immediateFuture(r6)
            return r6
        L_0x00a1:
            r6 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00a1 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.CaptureSession.release(boolean):com.google.common.util.concurrent.ListenableFuture");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$1$androidx-camera-camera2-internal-CaptureSession  reason: not valid java name */
    public /* synthetic */ Object m63lambda$release$1$androidxcameracamera2internalCaptureSession(CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.mSessionLock) {
            Preconditions.checkState(this.mReleaseCompleter == null, "Release completer expected to be null");
            this.mReleaseCompleter = completer;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    public void issueCaptureRequests(List<CaptureConfig> list) {
        synchronized (this.mSessionLock) {
            switch (this.mState.ordinal()) {
                case 0:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.mState);
                case 1:
                case 2:
                case 3:
                    this.mCaptureConfigs.addAll(list);
                    break;
                case 4:
                    this.mCaptureConfigs.addAll(list);
                    issuePendingCaptureRequest();
                    break;
                case 5:
                case 6:
                case 7:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    public List<CaptureConfig> getCaptureConfigs() {
        List<CaptureConfig> unmodifiableList;
        synchronized (this.mSessionLock) {
            unmodifiableList = Collections.unmodifiableList(this.mCaptureConfigs);
        }
        return unmodifiableList;
    }

    /* access modifiers changed from: package-private */
    public State getState() {
        State state;
        synchronized (this.mSessionLock) {
            state = this.mState;
        }
        return state;
    }

    public boolean isInOpenState() {
        boolean z;
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                if (this.mState != State.OPENING) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void finishClose() {
        if (this.mState == State.RELEASED) {
            Logger.d(TAG, "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.mState = State.RELEASED;
        this.mSynchronizedCaptureSession = null;
        CallbackToFutureAdapter.Completer<Void> completer = this.mReleaseCompleter;
        if (completer != null) {
            completer.set(null);
            this.mReleaseCompleter = null;
        }
    }

    /* access modifiers changed from: package-private */
    public int issueRepeatingCaptureRequests(SessionConfig sessionConfig) {
        synchronized (this.mSessionLock) {
            if (sessionConfig == null) {
                Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no configuration case.");
                return -1;
            } else if (this.mState != State.OPENED) {
                Logger.d(TAG, "Skipping issueRepeatingCaptureRequests due to session closed");
                return -1;
            } else {
                CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
                if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
                    Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no surface.");
                    try {
                        this.mSynchronizedCaptureSession.stopRepeating();
                    } catch (CameraAccessException e) {
                        Logger.e(TAG, "Unable to access camera: " + e.getMessage());
                        Thread.dumpStack();
                    }
                } else {
                    try {
                        Logger.d(TAG, "Issuing request for session.");
                        CaptureRequest build = Camera2CaptureRequestBuilder.build(repeatingCaptureConfig, this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap, true, this.mTemplateParamsOverride);
                        if (build == null) {
                            Logger.d(TAG, "Skipping issuing empty request for session.");
                            return -1;
                        }
                        int singleRepeatingRequest = this.mSynchronizedCaptureSession.setSingleRepeatingRequest(build, this.mRequestMonitor.createMonitorListener(createCamera2CaptureCallback(repeatingCaptureConfig.getCameraCaptureCallbacks(), new CameraCaptureSession.CaptureCallback[0])));
                        return singleRepeatingRequest;
                    } catch (CameraAccessException e2) {
                        Logger.e(TAG, "Unable to access camera: " + e2.getMessage());
                        Thread.dumpStack();
                        return -1;
                    }
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void issuePendingCaptureRequest() {
        this.mRequestMonitor.getRequestsProcessedFuture().addListener(new CaptureSession$$ExternalSyntheticLambda1(this), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$issuePendingCaptureRequest$2$androidx-camera-camera2-internal-CaptureSession  reason: not valid java name */
    public /* synthetic */ void m61lambda$issuePendingCaptureRequest$2$androidxcameracamera2internalCaptureSession() {
        synchronized (this.mSessionLock) {
            if (!this.mCaptureConfigs.isEmpty()) {
                try {
                    issueBurstCaptureRequest(this.mCaptureConfigs);
                } finally {
                    this.mCaptureConfigs.clear();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int issueBurstCaptureRequest(List<CaptureConfig> list) {
        boolean z;
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                Logger.d(TAG, "Skipping issueBurstCaptureRequest due to session closed");
                return -1;
            } else if (list.isEmpty()) {
                return -1;
            } else {
                try {
                    CameraBurstCaptureCallback cameraBurstCaptureCallback = new CameraBurstCaptureCallback();
                    ArrayList arrayList = new ArrayList();
                    Logger.d(TAG, "Issuing capture request.");
                    boolean z2 = false;
                    for (CaptureConfig next : list) {
                        if (next.getSurfaces().isEmpty()) {
                            Logger.d(TAG, "Skipping issuing empty capture request.");
                        } else {
                            Iterator<DeferrableSurface> it = next.getSurfaces().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = true;
                                    break;
                                }
                                DeferrableSurface next2 = it.next();
                                if (!this.mConfiguredSurfaceMap.containsKey(next2)) {
                                    Logger.d(TAG, "Skipping capture request with invalid surface: " + next2);
                                    z = false;
                                    break;
                                }
                            }
                            if (z) {
                                if (next.getTemplateType() == 2) {
                                    z2 = true;
                                }
                                CaptureConfig.Builder from = CaptureConfig.Builder.from(next);
                                if (next.getTemplateType() == 5 && next.getCameraCaptureResult() != null) {
                                    from.setCameraCaptureResult(next.getCameraCaptureResult());
                                }
                                SessionConfig sessionConfig = this.mSessionConfig;
                                if (sessionConfig != null) {
                                    from.addImplementationOptions(sessionConfig.getRepeatingCaptureConfig().getImplementationOptions());
                                }
                                from.addImplementationOptions(next.getImplementationOptions());
                                CaptureRequest build = Camera2CaptureRequestBuilder.build(from.build(), this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap, false, this.mTemplateParamsOverride);
                                if (build == null) {
                                    Logger.d(TAG, "Skipping issuing request without surface.");
                                    return -1;
                                }
                                ArrayList arrayList2 = new ArrayList();
                                for (CameraCaptureCallback captureCallback : next.getCameraCaptureCallbacks()) {
                                    CaptureCallbackConverter.toCaptureCallback(captureCallback, arrayList2);
                                }
                                cameraBurstCaptureCallback.addCamera2Callbacks(build, arrayList2);
                                arrayList.add(build);
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        if (this.mStillCaptureFlow.shouldStopRepeatingBeforeCapture(arrayList, z2)) {
                            this.mSynchronizedCaptureSession.stopRepeating();
                            cameraBurstCaptureCallback.setCaptureSequenceCallback(new CaptureSession$$ExternalSyntheticLambda0(this));
                        }
                        if (this.mTorchStateReset.isTorchResetRequired(arrayList, z2)) {
                            cameraBurstCaptureCallback.addCamera2Callbacks((CaptureRequest) arrayList.get(arrayList.size() - 1), Collections.singletonList(new CameraCaptureSession.CaptureCallback() {
                                public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                                    synchronized (CaptureSession.this.mSessionLock) {
                                        if (CaptureSession.this.mSessionConfig != null) {
                                            CaptureConfig repeatingCaptureConfig = CaptureSession.this.mSessionConfig.getRepeatingCaptureConfig();
                                            Logger.d(CaptureSession.TAG, "Submit FLASH_MODE_OFF request");
                                            CaptureSession captureSession = CaptureSession.this;
                                            captureSession.issueCaptureRequests(Collections.singletonList(captureSession.mTorchStateReset.createTorchResetRequest(repeatingCaptureConfig)));
                                        }
                                    }
                                }
                            }));
                        }
                        int captureBurstRequests = this.mSynchronizedCaptureSession.captureBurstRequests(arrayList, cameraBurstCaptureCallback);
                        return captureBurstRequests;
                    }
                    Logger.d(TAG, "Skipping issuing burst request due to no valid request elements");
                } catch (CameraAccessException e) {
                    Logger.e(TAG, "Unable to access camera: " + e.getMessage());
                    Thread.dumpStack();
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$issueBurstCaptureRequest$3$androidx-camera-camera2-internal-CaptureSession  reason: not valid java name */
    public /* synthetic */ void m60lambda$issueBurstCaptureRequest$3$androidxcameracamera2internalCaptureSession(CameraCaptureSession cameraCaptureSession, int i, boolean z) {
        synchronized (this.mSessionLock) {
            if (this.mState == State.OPENED) {
                issueRepeatingCaptureRequests(this.mSessionConfig);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void abortCaptures() {
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                Logger.e(TAG, "Unable to abort captures. Incorrect state:" + this.mState);
                return;
            }
            try {
                this.mSynchronizedCaptureSession.abortCaptures();
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to abort captures.", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void stopRepeating() {
        synchronized (this.mSessionLock) {
            if (this.mState != State.OPENED) {
                Logger.e(TAG, "Unable to stop repeating. Incorrect state:" + this.mState);
                return;
            }
            try {
                this.mSynchronizedCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to stop repeating.", e);
            }
        }
    }

    public void cancelIssuedCaptureRequests() {
        ArrayList<CaptureConfig> arrayList;
        synchronized (this.mSessionLock) {
            if (!this.mCaptureConfigs.isEmpty()) {
                arrayList = new ArrayList<>(this.mCaptureConfigs);
                this.mCaptureConfigs.clear();
            } else {
                arrayList = null;
            }
        }
        if (arrayList != null) {
            for (CaptureConfig captureConfig : arrayList) {
                for (CameraCaptureCallback onCaptureCancelled : captureConfig.getCameraCaptureCallbacks()) {
                    onCaptureCancelled.onCaptureCancelled(captureConfig.getId());
                }
            }
        }
    }

    private CameraCaptureSession.CaptureCallback createCamera2CaptureCallback(List<CameraCaptureCallback> list, CameraCaptureSession.CaptureCallback... captureCallbackArr) {
        ArrayList arrayList = new ArrayList(list.size() + captureCallbackArr.length);
        for (CameraCaptureCallback captureCallback : list) {
            arrayList.add(CaptureCallbackConverter.toCaptureCallback(captureCallback));
        }
        Collections.addAll(arrayList, captureCallbackArr);
        return Camera2CaptureCallbacks.createComboCallback((List<CameraCaptureSession.CaptureCallback>) arrayList);
    }

    final class StateCallback extends SynchronizedCaptureSession.StateCallback {
        StateCallback() {
        }

        public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                switch (CaptureSession.this.mState.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + CaptureSession.this.mState);
                    case 3:
                        CaptureSession.this.mState = State.OPENED;
                        CaptureSession.this.mSynchronizedCaptureSession = synchronizedCaptureSession;
                        Logger.d(CaptureSession.TAG, "Attempting to send capture request onConfigured");
                        CaptureSession captureSession = CaptureSession.this;
                        captureSession.issueRepeatingCaptureRequests(captureSession.mSessionConfig);
                        CaptureSession.this.issuePendingCaptureRequest();
                        break;
                    case 5:
                        CaptureSession.this.mSynchronizedCaptureSession = synchronizedCaptureSession;
                        break;
                    case 6:
                        synchronizedCaptureSession.close();
                        break;
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onConfigured() mState=" + CaptureSession.this.mState);
            }
        }

        public void onReady(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                if (CaptureSession.this.mState.ordinal() != 0) {
                    Logger.d(CaptureSession.TAG, "CameraCaptureSession.onReady() " + CaptureSession.this.mState);
                } else {
                    throw new IllegalStateException("onReady() should not be possible in state: " + CaptureSession.this.mState);
                }
            }
        }

        public void onSessionFinished(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                if (CaptureSession.this.mState != State.UNINITIALIZED) {
                    Logger.d(CaptureSession.TAG, "onSessionFinished()");
                    CaptureSession.this.finishClose();
                } else {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + CaptureSession.this.mState);
                }
            }
        }

        public void onConfigureFailed(SynchronizedCaptureSession synchronizedCaptureSession) {
            synchronized (CaptureSession.this.mSessionLock) {
                switch (CaptureSession.this.mState.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 4:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + CaptureSession.this.mState);
                    case 3:
                    case 5:
                    case 6:
                        CaptureSession.this.finishClose();
                        break;
                    case 7:
                        Logger.d(CaptureSession.TAG, "ConfigureFailed callback after change to RELEASED state");
                        break;
                }
                Logger.e(CaptureSession.TAG, "CameraCaptureSession.onConfigureFailed() " + CaptureSession.this.mState);
            }
        }
    }
}
