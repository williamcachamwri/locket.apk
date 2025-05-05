package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.CompositionSettings;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigProvider;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.RestrictedCameraInfo;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import androidx.tracing.Trace;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0005¢\u0006\u0002\u0010\u0002Ji\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\b\u00105\u001a\u0004\u0018\u0001062\u000e\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001080\u00042\u0016\u00109\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010;0:\"\u0004\u0018\u00010;H\u0002¢\u0006\u0002\u0010<J \u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020?H\u0017J5\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010=\u001a\u0002002\u0016\u00109\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010;0:\"\u0004\u0018\u00010;H\u0017¢\u0006\u0002\u0010@J\u0018\u0010+\u001a\u00020A2\u000e\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010C0\u0004H\u0017J\u0015\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GH\u0000¢\u0006\u0002\bHJ\u0018\u0010I\u001a\u00020J2\u0006\u0010=\u001a\u0002002\u0006\u0010K\u001a\u00020\u0005H\u0002J\u0010\u0010L\u001a\u00020\u00052\u0006\u0010=\u001a\u000200H\u0016J\u0010\u0010M\u001a\u00020%2\u0006\u0010=\u001a\u000200H\u0016J'\u0010N\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\"\u001a\u00020#2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010GH\u0000¢\u0006\u0002\bOJ\u0010\u0010P\u001a\u00020%2\u0006\u0010Q\u001a\u00020;H\u0016J\u0010\u0010R\u001a\u00020%2\u0006\u0010Q\u001a\u00020;H\u0002J\u0010\u0010S\u001a\u00020%2\u0006\u0010Q\u001a\u00020;H\u0002J\u0013\u0010T\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0000¢\u0006\u0002\bUJ%\u0010V\u001a\u00020E2\u0016\u00109\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010;0:\"\u0004\u0018\u00010;H\u0017¢\u0006\u0002\u0010WJ\b\u0010X\u001a\u00020EH\u0017R0\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048B@BX\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR \u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00108\u0002X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148B@BX\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R \u0010 \u001a\u0010\u0012\f\u0012\n !*\u0004\u0018\u00010\u001f0\u001f0\u001e8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020%8WX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010&R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", "Landroidx/camera/lifecycle/LifecycleCameraProvider;", "()V", "cameraInfos", "", "Landroidx/camera/core/CameraInfo;", "activeConcurrentCameraInfos", "getActiveConcurrentCameraInfos", "()Ljava/util/List;", "setActiveConcurrentCameraInfos", "(Ljava/util/List;)V", "availableCameraInfos", "getAvailableCameraInfos", "availableConcurrentCameraInfos", "getAvailableConcurrentCameraInfos", "cameraInfoMap", "", "Landroidx/camera/core/internal/CameraUseCaseAdapter$CameraId;", "Landroidx/camera/core/impl/RestrictedCameraInfo;", "cameraOperatingMode", "", "getCameraOperatingMode", "()I", "setCameraOperatingMode", "(I)V", "cameraX", "Landroidx/camera/core/CameraX;", "cameraXConfigProvider", "Landroidx/camera/core/CameraXConfig$Provider;", "cameraXInitializeFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "cameraXShutdownFuture", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "isConcurrentCameraModeOn", "", "()Z", "lifecycleCameraRepository", "Landroidx/camera/lifecycle/LifecycleCameraRepository;", "lock", "", "bindToLifecycle", "Landroidx/camera/core/Camera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "primaryCameraSelector", "Landroidx/camera/core/CameraSelector;", "secondaryCameraSelector", "primaryCompositionSettings", "Landroidx/camera/core/CompositionSettings;", "secondaryCompositionSettings", "viewPort", "Landroidx/camera/core/ViewPort;", "effects", "Landroidx/camera/core/CameraEffect;", "useCases", "", "Landroidx/camera/core/UseCase;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/CompositionSettings;Landroidx/camera/core/CompositionSettings;Landroidx/camera/core/ViewPort;Ljava/util/List;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "cameraSelector", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "Landroidx/camera/core/ConcurrentCamera;", "singleCameraConfigs", "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "configure", "", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "configure$camera_lifecycle_release", "getCameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "cameraInfo", "getCameraInfo", "hasCamera", "initAsync", "initAsync$camera_lifecycle_release", "isBound", "useCase", "isPreview", "isVideoCapture", "shutdownAsync", "shutdownAsync$camera_lifecycle_release", "unbind", "([Landroidx/camera/core/UseCase;)V", "unbindAll", "Companion", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LifecycleCameraProviderImpl.kt */
public final class LifecycleCameraProviderImpl implements LifecycleCameraProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "LifecycleCameraProvider";
    /* access modifiers changed from: private */
    public final Map<CameraUseCaseAdapter.CameraId, RestrictedCameraInfo> cameraInfoMap;
    /* access modifiers changed from: private */
    public CameraX cameraX;
    /* access modifiers changed from: private */
    public CameraXConfig.Provider cameraXConfigProvider;
    private ListenableFuture<Void> cameraXInitializeFuture;
    private ListenableFuture<Void> cameraXShutdownFuture;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public final LifecycleCameraRepository lifecycleCameraRepository;
    /* access modifiers changed from: private */
    public final Object lock = new Object();

    public LifecycleCameraProviderImpl() {
        ListenableFuture<Void> immediateFuture = Futures.immediateFuture(null);
        Intrinsics.checkNotNullExpressionValue(immediateFuture, "immediateFuture<Void>(null)");
        this.cameraXShutdownFuture = immediateFuture;
        LifecycleCameraRepository instance = LifecycleCameraRepository.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        this.lifecycleCameraRepository = instance;
        this.cameraInfoMap = new HashMap();
    }

    public static /* synthetic */ ListenableFuture initAsync$camera_lifecycle_release$default(LifecycleCameraProviderImpl lifecycleCameraProviderImpl, Context context2, CameraXConfig cameraXConfig, int i, Object obj) {
        if ((i & 2) != 0) {
            cameraXConfig = null;
        }
        return lifecycleCameraProviderImpl.initAsync$camera_lifecycle_release(context2, cameraXConfig);
    }

    public final ListenableFuture<Void> initAsync$camera_lifecycle_release(Context context2, CameraXConfig cameraXConfig) {
        Intrinsics.checkNotNullParameter(context2, "context");
        synchronized (this.lock) {
            ListenableFuture<Void> listenableFuture = this.cameraXInitializeFuture;
            if (listenableFuture != null) {
                Intrinsics.checkNotNull(listenableFuture, "null cannot be cast to non-null type com.google.common.util.concurrent.ListenableFuture<java.lang.Void>");
                return listenableFuture;
            }
            if (cameraXConfig != null) {
                configure$camera_lifecycle_release(cameraXConfig);
            }
            CameraX cameraX2 = new CameraX(context2, this.cameraXConfigProvider);
            FutureChain<T> transformAsync = FutureChain.from(this.cameraXShutdownFuture).transformAsync(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda1(new LifecycleCameraProviderImpl$initAsync$1$initFuture$1(cameraX2)), CameraXExecutors.directExecutor());
            Intrinsics.checkNotNullExpressionValue(transformAsync, "cameraX = CameraX(contex…ecutors.directExecutor())");
            this.cameraXInitializeFuture = transformAsync;
            Futures.addCallback(transformAsync, new LifecycleCameraProviderImpl$initAsync$1$2(this, cameraX2, context2), CameraXExecutors.directExecutor());
            ListenableFuture<Void> nonCancellationPropagating = Futures.nonCancellationPropagating(transformAsync);
            Intrinsics.checkNotNullExpressionValue(nonCancellationPropagating, "nonCancellationPropagating(initFuture)");
            return nonCancellationPropagating;
        }
    }

    /* access modifiers changed from: private */
    public static final ListenableFuture initAsync$lambda$2$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return (ListenableFuture) function1.invoke(obj);
    }

    public final ListenableFuture<Void> shutdownAsync$camera_lifecycle_release() {
        ListenableFuture<Void> listenableFuture;
        Threads.runOnMainSync(new LifecycleCameraProviderImpl$$ExternalSyntheticLambda0(this));
        CameraX cameraX2 = this.cameraX;
        if (cameraX2 != null) {
            Intrinsics.checkNotNull(cameraX2);
            cameraX2.getCameraFactory().getCameraCoordinator().shutdown();
        }
        CameraX cameraX3 = this.cameraX;
        if (cameraX3 != null) {
            Intrinsics.checkNotNull(cameraX3);
            listenableFuture = cameraX3.shutdown();
        } else {
            listenableFuture = Futures.immediateFuture(null);
        }
        Intrinsics.checkNotNullExpressionValue(listenableFuture, "if (cameraX != null) cam…mediateFuture<Void>(null)");
        synchronized (this.lock) {
            this.cameraXConfigProvider = null;
            this.cameraXInitializeFuture = null;
            this.cameraXShutdownFuture = listenableFuture;
            this.cameraInfoMap.clear();
            Unit unit = Unit.INSTANCE;
        }
        this.cameraX = null;
        this.context = null;
        return listenableFuture;
    }

    /* access modifiers changed from: private */
    public static final void shutdownAsync$lambda$5(LifecycleCameraProviderImpl lifecycleCameraProviderImpl) {
        Intrinsics.checkNotNullParameter(lifecycleCameraProviderImpl, "this$0");
        lifecycleCameraProviderImpl.unbindAll();
        lifecycleCameraProviderImpl.lifecycleCameraRepository.clear();
    }

    public boolean isBound(UseCase useCase) {
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        for (LifecycleCamera next : this.lifecycleCameraRepository.getLifecycleCameras()) {
            Intrinsics.checkNotNullExpressionValue(next, "lifecycleCameraRepository.lifecycleCameras");
            if (next.isBound(useCase)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConcurrentCameraModeOn() {
        return getCameraOperatingMode() == 2;
    }

    /* access modifiers changed from: private */
    public final boolean isVideoCapture(UseCase useCase) {
        return useCase.getCurrentConfig().containsOption(UseCaseConfig.OPTION_CAPTURE_TYPE) && useCase.getCurrentConfig().getCaptureType() == UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE;
    }

    /* access modifiers changed from: private */
    public final boolean isPreview(UseCase useCase) {
        return useCase instanceof Preview;
    }

    /* access modifiers changed from: private */
    public final CameraConfig getCameraConfig(CameraSelector cameraSelector, CameraInfo cameraInfo) {
        Iterator it = cameraSelector.getCameraFilterSet().iterator();
        CameraConfig cameraConfig = null;
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "cameraSelector.cameraFilterSet");
            CameraFilter cameraFilter = (CameraFilter) next;
            if (!Intrinsics.areEqual((Object) cameraFilter.getIdentifier(), (Object) CameraFilter.DEFAULT_ID)) {
                CameraConfigProvider configProvider = ExtendedCameraConfigProviderStore.getConfigProvider(cameraFilter.getIdentifier());
                Context context2 = this.context;
                Intrinsics.checkNotNull(context2);
                CameraConfig config = configProvider.getConfig(cameraInfo, context2);
                if (config == null) {
                    continue;
                } else if (cameraConfig == null) {
                    cameraConfig = config;
                } else {
                    throw new IllegalArgumentException("Cannot apply multiple extended camera configs at the same time.");
                }
            }
        }
        return cameraConfig == null ? CameraConfigs.defaultConfig() : cameraConfig;
    }

    /* access modifiers changed from: private */
    public final int getCameraOperatingMode() {
        CameraX cameraX2 = this.cameraX;
        if (cameraX2 == null) {
            return 0;
        }
        Intrinsics.checkNotNull(cameraX2);
        return cameraX2.getCameraFactory().getCameraCoordinator().getCameraOperatingMode();
    }

    /* access modifiers changed from: private */
    public final void setCameraOperatingMode(int i) {
        CameraX cameraX2 = this.cameraX;
        if (cameraX2 != null) {
            Intrinsics.checkNotNull(cameraX2);
            cameraX2.getCameraFactory().getCameraCoordinator().setCameraOperatingMode(i);
        }
    }

    /* access modifiers changed from: private */
    public final List<CameraInfo> getActiveConcurrentCameraInfos() {
        CameraX cameraX2 = this.cameraX;
        if (cameraX2 == null) {
            return new ArrayList<>();
        }
        Intrinsics.checkNotNull(cameraX2);
        List<CameraInfo> activeConcurrentCameraInfos = cameraX2.getCameraFactory().getCameraCoordinator().getActiveConcurrentCameraInfos();
        Intrinsics.checkNotNullExpressionValue(activeConcurrentCameraInfos, "cameraX!!.cameraFactory.…tiveConcurrentCameraInfos");
        return activeConcurrentCameraInfos;
    }

    /* access modifiers changed from: private */
    public final void setActiveConcurrentCameraInfos(List<? extends CameraInfo> list) {
        CameraX cameraX2 = this.cameraX;
        if (cameraX2 != null) {
            Intrinsics.checkNotNull(cameraX2);
            cameraX2.getCameraFactory().getCameraCoordinator().setActiveConcurrentCameraInfos(list);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProviderImpl$Companion;", "", "()V", "TAG", "", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LifecycleCameraProviderImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void configure$camera_lifecycle_release(CameraXConfig cameraXConfig) {
        Intrinsics.checkNotNullParameter(cameraXConfig, "cameraXConfig");
        Trace.beginSection("CX:configureInstanceInternal");
        try {
            synchronized (this.lock) {
                Preconditions.checkNotNull(cameraXConfig);
                Preconditions.checkState(this.cameraXConfigProvider == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
                this.cameraXConfigProvider = new LifecycleCameraProviderImpl$configure$1$1$1(cameraXConfig);
                Unit unit = Unit.INSTANCE;
            }
            Unit unit2 = Unit.INSTANCE;
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public void unbind(UseCase... useCaseArr) {
        Intrinsics.checkNotNullParameter(useCaseArr, "useCases");
        Trace.beginSection("CX:unbind");
        try {
            Threads.checkMainThread();
            if (getCameraOperatingMode() != 2) {
                this.lifecycleCameraRepository.unbind(CollectionsKt.listOf(Arrays.copyOf(useCaseArr, useCaseArr.length)));
                Unit unit = Unit.INSTANCE;
                return;
            }
            throw new UnsupportedOperationException("Unbind usecase is not supported in concurrent camera mode, call unbindAll() first.");
        } finally {
            Trace.endSection();
        }
    }

    public void unbindAll() {
        Trace.beginSection("CX:unbindAll");
        try {
            Threads.checkMainThread();
            setCameraOperatingMode(0);
            this.lifecycleCameraRepository.unbindAll();
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        boolean z;
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Trace.beginSection("CX:hasCamera");
        try {
            CameraX access$getCameraX$p = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p);
            cameraSelector.select(access$getCameraX$p.getCameraRepository().getCameras());
            z = true;
        } catch (IllegalArgumentException unused) {
            z = false;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
        Trace.endSection();
        return z;
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCaseArr) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCaseArr, "useCases");
        Trace.beginSection("CX:bindToLifecycle");
        try {
            if (getCameraOperatingMode() != 2) {
                setCameraOperatingMode(1);
                CompositionSettings compositionSettings = CompositionSettings.DEFAULT;
                Intrinsics.checkNotNullExpressionValue(compositionSettings, "DEFAULT");
                CompositionSettings compositionSettings2 = CompositionSettings.DEFAULT;
                Intrinsics.checkNotNullExpressionValue(compositionSettings2, "DEFAULT");
                return bindToLifecycle(lifecycleOwner, cameraSelector, (CameraSelector) null, compositionSettings, compositionSettings2, (ViewPort) null, CollectionsKt.emptyList(), (UseCase[]) Arrays.copyOf(useCaseArr, useCaseArr.length));
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first");
        } finally {
            Trace.endSection();
        }
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCaseGroup, "useCaseGroup");
        Trace.beginSection("CX:bindToLifecycle-UseCaseGroup");
        try {
            if (getCameraOperatingMode() != 2) {
                setCameraOperatingMode(1);
                CompositionSettings compositionSettings = CompositionSettings.DEFAULT;
                Intrinsics.checkNotNullExpressionValue(compositionSettings, "DEFAULT");
                CompositionSettings compositionSettings2 = CompositionSettings.DEFAULT;
                Intrinsics.checkNotNullExpressionValue(compositionSettings2, "DEFAULT");
                ViewPort viewPort = useCaseGroup.getViewPort();
                List<CameraEffect> effects = useCaseGroup.getEffects();
                Intrinsics.checkNotNullExpressionValue(effects, "useCaseGroup.effects");
                List<UseCase> useCases = useCaseGroup.getUseCases();
                Intrinsics.checkNotNullExpressionValue(useCases, "useCaseGroup.useCases");
                UseCase[] useCaseArr = (UseCase[]) useCases.toArray(new UseCase[0]);
                return bindToLifecycle(lifecycleOwner, cameraSelector, (CameraSelector) null, compositionSettings, compositionSettings2, viewPort, effects, (UseCase[]) Arrays.copyOf(useCaseArr, useCaseArr.length));
            }
            throw new UnsupportedOperationException("bindToLifecycle for single camera is not supported in concurrent camera mode, call unbindAll() first.");
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:74|75|76) */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0204, code lost:
        if (access$isPreview(r10, r5) == false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02ff, code lost:
        throw new java.lang.IllegalArgumentException("Invalid camera selectors in camera configs.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:74:0x02f8 */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0219 A[Catch:{ all -> 0x031e }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x027f A[Catch:{ all -> 0x031e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.ConcurrentCamera bindToLifecycle(java.util.List<androidx.camera.core.ConcurrentCamera.SingleCameraConfig> r17) {
        /*
            r16 = this;
            r10 = r16
            r0 = r17
            java.lang.String r1 = "singleCameraConfigs"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "CX:bindToLifecycle-Concurrent"
            androidx.tracing.Trace.beginSection(r1)
            int r1 = r17.size()     // Catch:{ all -> 0x031e }
            r2 = 2
            if (r1 < r2) goto L_0x0316
            int r1 = r17.size()     // Catch:{ all -> 0x031e }
            if (r1 > r2) goto L_0x030e
            r11 = 0
            java.lang.Object r1 = r0.get(r11)     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x031e }
            androidx.camera.core.ConcurrentCamera$SingleCameraConfig r1 = (androidx.camera.core.ConcurrentCamera.SingleCameraConfig) r1     // Catch:{ all -> 0x031e }
            r3 = 1
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x031e }
            androidx.camera.core.ConcurrentCamera$SingleCameraConfig r4 = (androidx.camera.core.ConcurrentCamera.SingleCameraConfig) r4     // Catch:{ all -> 0x031e }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x031e }
            r5.<init>()     // Catch:{ all -> 0x031e }
            r12 = r5
            java.util.List r12 = (java.util.List) r12     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r5 = r1.getCameraSelector()     // Catch:{ all -> 0x031e }
            java.lang.Integer r5 = r5.getLensFacing()     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r6 = r4.getCameraSelector()     // Catch:{ all -> 0x031e }
            java.lang.Integer r6 = r6.getLensFacing()     // Catch:{ all -> 0x031e }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x031e }
            java.lang.String r13 = "config.useCaseGroup.useCases"
            java.lang.String r6 = "firstCameraConfig.useCaseGroup.effects"
            java.lang.String r7 = "firstCameraConfig.lifecycleOwner"
            java.lang.String r8 = "Camera is already running, call unbindAll() before binding more cameras."
            java.lang.String r9 = "firstCameraConfig.cameraSelector"
            java.lang.String r14 = "DEFAULT"
            if (r5 == 0) goto L_0x014b
            int r5 = r16.getCameraOperatingMode()     // Catch:{ all -> 0x031e }
            if (r5 == r2) goto L_0x0145
            androidx.lifecycle.LifecycleOwner r2 = r1.getLifecycleOwner()     // Catch:{ all -> 0x031e }
            androidx.lifecycle.LifecycleOwner r5 = r4.getLifecycleOwner()     // Catch:{ all -> 0x031e }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)     // Catch:{ all -> 0x031e }
            if (r2 == 0) goto L_0x013d
            androidx.camera.core.UseCaseGroup r2 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ViewPort r2 = r2.getViewPort()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r5 = r4.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ViewPort r5 = r5.getViewPort()     // Catch:{ all -> 0x031e }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r5)     // Catch:{ all -> 0x031e }
            if (r2 == 0) goto L_0x013d
            androidx.camera.core.UseCaseGroup r2 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r2 = r2.getEffects()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r4 = r4.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r4 = r4.getEffects()     // Catch:{ all -> 0x031e }
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x031e }
            if (r2 == 0) goto L_0x013d
            androidx.lifecycle.LifecycleOwner r2 = r1.getLifecycleOwner()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r4 = r1.getCameraSelector()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r5 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ViewPort r7 = r5.getViewPort()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r1 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r8 = r1.getEffects()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)     // Catch:{ all -> 0x031e }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x031e }
            r1.<init>()     // Catch:{ all -> 0x031e }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x031e }
            java.util.Iterator r0 = r17.iterator()     // Catch:{ all -> 0x031e }
        L_0x00c5:
            boolean r5 = r0.hasNext()     // Catch:{ all -> 0x031e }
            if (r5 == 0) goto L_0x0110
            java.lang.Object r5 = r0.next()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ConcurrentCamera$SingleCameraConfig r5 = (androidx.camera.core.ConcurrentCamera.SingleCameraConfig) r5     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r6 = r5.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r6 = r6.getUseCases()     // Catch:{ all -> 0x031e }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x031e }
        L_0x00e0:
            boolean r9 = r6.hasNext()     // Catch:{ all -> 0x031e }
            if (r9 == 0) goto L_0x00ff
            java.lang.Object r9 = r6.next()     // Catch:{ all -> 0x031e }
            java.lang.String r15 = "config!!.useCaseGroup.useCases"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r15)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase r9 = (androidx.camera.core.UseCase) r9     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r15 = r5.getCameraSelector()     // Catch:{ all -> 0x031e }
            java.lang.String r15 = r15.getPhysicalCameraId()     // Catch:{ all -> 0x031e }
            if (r15 == 0) goto L_0x00e0
            r9.setPhysicalCameraId(r15)     // Catch:{ all -> 0x031e }
            goto L_0x00e0
        L_0x00ff:
            androidx.camera.core.UseCaseGroup r5 = r5.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r5 = r5.getUseCases()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r13)     // Catch:{ all -> 0x031e }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x031e }
            r1.addAll(r5)     // Catch:{ all -> 0x031e }
            goto L_0x00c5
        L_0x0110:
            r10.setCameraOperatingMode(r3)     // Catch:{ all -> 0x031e }
            r0 = 0
            androidx.camera.core.CompositionSettings r5 = androidx.camera.core.CompositionSettings.DEFAULT     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CompositionSettings r6 = androidx.camera.core.CompositionSettings.DEFAULT     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r14)     // Catch:{ all -> 0x031e }
            java.util.Collection r1 = (java.util.Collection) r1     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase[] r3 = new androidx.camera.core.UseCase[r11]     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = r1.toArray(r3)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase[] r1 = (androidx.camera.core.UseCase[]) r1     // Catch:{ all -> 0x031e }
            int r3 = r1.length     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)     // Catch:{ all -> 0x031e }
            r9 = r1
            androidx.camera.core.UseCase[] r9 = (androidx.camera.core.UseCase[]) r9     // Catch:{ all -> 0x031e }
            r1 = r16
            r3 = r4
            r4 = r0
            androidx.camera.core.Camera r0 = r1.bindToLifecycle(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x031e }
            r12.add(r0)     // Catch:{ all -> 0x031e }
            goto L_0x02ef
        L_0x013d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Two camera configs need to have the same lifecycle owner, view port and effects."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x0145:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x031e }
            r0.<init>(r8)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x014b:
            android.content.Context r5 = r16.context     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x031e }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x031e }
            java.lang.String r15 = "android.hardware.camera.concurrent"
            boolean r5 = r5.hasSystemFeature(r15)     // Catch:{ all -> 0x031e }
            if (r5 == 0) goto L_0x0306
            int r5 = r16.getCameraOperatingMode()     // Catch:{ all -> 0x031e }
            if (r5 == r3) goto L_0x0300
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x031e }
            r5.<init>()     // Catch:{ all -> 0x031e }
            r15 = r5
            java.util.List r15 = (java.util.List) r15     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r5 = r1.getCameraSelector()     // Catch:{ IllegalArgumentException -> 0x02f8 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r9)     // Catch:{ IllegalArgumentException -> 0x02f8 }
            androidx.camera.core.CameraInfo r5 = r10.getCameraInfo(r5)     // Catch:{ IllegalArgumentException -> 0x02f8 }
            androidx.camera.core.CameraSelector r8 = r4.getCameraSelector()     // Catch:{ IllegalArgumentException -> 0x02f8 }
            java.lang.String r11 = "secondCameraConfig.cameraSelector"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r11)     // Catch:{ IllegalArgumentException -> 0x02f8 }
            androidx.camera.core.CameraInfo r8 = r10.getCameraInfo(r8)     // Catch:{ IllegalArgumentException -> 0x02f8 }
            r15.add(r5)     // Catch:{ all -> 0x031e }
            r15.add(r8)     // Catch:{ all -> 0x031e }
            java.util.List r5 = r16.getActiveConcurrentCameraInfos()     // Catch:{ all -> 0x031e }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x031e }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x031e }
            r5 = r5 ^ r3
            if (r5 == 0) goto L_0x01aa
            java.util.List r5 = r16.getActiveConcurrentCameraInfos()     // Catch:{ all -> 0x031e }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r5)     // Catch:{ all -> 0x031e }
            if (r5 == 0) goto L_0x01a2
            goto L_0x01aa
        L_0x01a2:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Cameras are already running, call unbindAll() before binding more cameras."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x01aa:
            r10.setCameraOperatingMode(r2)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r5 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r5 = r5.getUseCases()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r8 = r4.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r8 = r8.getUseCases()     // Catch:{ all -> 0x031e }
            boolean r5 = java.util.Objects.equals(r5, r8)     // Catch:{ all -> 0x031e }
            if (r5 == 0) goto L_0x0216
            androidx.camera.core.UseCaseGroup r5 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r5 = r5.getUseCases()     // Catch:{ all -> 0x031e }
            int r5 = r5.size()     // Catch:{ all -> 0x031e }
            if (r5 != r2) goto L_0x0216
            androidx.camera.core.UseCaseGroup r2 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r2 = r2.getUseCases()     // Catch:{ all -> 0x031e }
            r5 = 0
            java.lang.Object r2 = r2.get(r5)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase r2 = (androidx.camera.core.UseCase) r2     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r5 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r5 = r5.getUseCases()     // Catch:{ all -> 0x031e }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase r5 = (androidx.camera.core.UseCase) r5     // Catch:{ all -> 0x031e }
            java.lang.String r8 = "useCase0"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)     // Catch:{ all -> 0x031e }
            boolean r8 = r10.isVideoCapture(r2)     // Catch:{ all -> 0x031e }
            java.lang.String r11 = "useCase1"
            if (r8 == 0) goto L_0x0206
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r11)     // Catch:{ all -> 0x031e }
            boolean r8 = r10.isPreview(r5)     // Catch:{ all -> 0x031e }
            if (r8 != 0) goto L_0x0217
        L_0x0206:
            boolean r2 = r10.isPreview(r2)     // Catch:{ all -> 0x031e }
            if (r2 == 0) goto L_0x0216
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r11)     // Catch:{ all -> 0x031e }
            boolean r2 = r10.isVideoCapture(r5)     // Catch:{ all -> 0x031e }
            if (r2 == 0) goto L_0x0216
            goto L_0x0217
        L_0x0216:
            r3 = 0
        L_0x0217:
            if (r3 == 0) goto L_0x027f
            androidx.lifecycle.LifecycleOwner r2 = r1.getLifecycleOwner()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r7)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r3 = r1.getCameraSelector()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r9)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r0 = r4.getCameraSelector()     // Catch:{ all -> 0x031e }
            androidx.camera.core.CompositionSettings r5 = r1.getCompositionSettings()     // Catch:{ all -> 0x031e }
            java.lang.String r7 = "firstCameraConfig.compositionSettings"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r7)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CompositionSettings r7 = r4.getCompositionSettings()     // Catch:{ all -> 0x031e }
            java.lang.String r4 = "secondCameraConfig.compositionSettings"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r4)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r4 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ViewPort r8 = r4.getViewPort()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r4 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r9 = r4.getEffects()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r1 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r1 = r1.getUseCases()     // Catch:{ all -> 0x031e }
            java.lang.String r4 = "firstCameraConfig.useCaseGroup.useCases"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)     // Catch:{ all -> 0x031e }
            java.util.Collection r1 = (java.util.Collection) r1     // Catch:{ all -> 0x031e }
            r4 = 0
            androidx.camera.core.UseCase[] r4 = new androidx.camera.core.UseCase[r4]     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = r1.toArray(r4)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase[] r1 = (androidx.camera.core.UseCase[]) r1     // Catch:{ all -> 0x031e }
            int r4 = r1.length     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r4)     // Catch:{ all -> 0x031e }
            r11 = r1
            androidx.camera.core.UseCase[] r11 = (androidx.camera.core.UseCase[]) r11     // Catch:{ all -> 0x031e }
            r1 = r16
            r4 = r0
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r11
            androidx.camera.core.Camera r0 = r1.bindToLifecycle(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x031e }
            r12.add(r0)     // Catch:{ all -> 0x031e }
            goto L_0x02ec
        L_0x027f:
            java.util.Iterator r0 = r17.iterator()     // Catch:{ all -> 0x031e }
        L_0x0283:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x031e }
            if (r1 == 0) goto L_0x02ec
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ConcurrentCamera$SingleCameraConfig r1 = (androidx.camera.core.ConcurrentCamera.SingleCameraConfig) r1     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x031e }
            androidx.lifecycle.LifecycleOwner r2 = r1.getLifecycleOwner()     // Catch:{ all -> 0x031e }
            java.lang.String r3 = "config!!.lifecycleOwner"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CameraSelector r3 = r1.getCameraSelector()     // Catch:{ all -> 0x031e }
            java.lang.String r4 = "config.cameraSelector"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ all -> 0x031e }
            r4 = 0
            androidx.camera.core.CompositionSettings r5 = androidx.camera.core.CompositionSettings.DEFAULT     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)     // Catch:{ all -> 0x031e }
            androidx.camera.core.CompositionSettings r6 = androidx.camera.core.CompositionSettings.DEFAULT     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r14)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r7 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            androidx.camera.core.ViewPort r7 = r7.getViewPort()     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r8 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r8 = r8.getEffects()     // Catch:{ all -> 0x031e }
            java.lang.String r9 = "config.useCaseGroup.effects"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCaseGroup r1 = r1.getUseCaseGroup()     // Catch:{ all -> 0x031e }
            java.util.List r1 = r1.getUseCases()     // Catch:{ all -> 0x031e }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)     // Catch:{ all -> 0x031e }
            java.util.Collection r1 = (java.util.Collection) r1     // Catch:{ all -> 0x031e }
            r11 = 0
            androidx.camera.core.UseCase[] r9 = new androidx.camera.core.UseCase[r11]     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = r1.toArray(r9)     // Catch:{ all -> 0x031e }
            androidx.camera.core.UseCase[] r1 = (androidx.camera.core.UseCase[]) r1     // Catch:{ all -> 0x031e }
            int r9 = r1.length     // Catch:{ all -> 0x031e }
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r9)     // Catch:{ all -> 0x031e }
            r9 = r1
            androidx.camera.core.UseCase[] r9 = (androidx.camera.core.UseCase[]) r9     // Catch:{ all -> 0x031e }
            r1 = r16
            androidx.camera.core.Camera r1 = r1.bindToLifecycle(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x031e }
            r12.add(r1)     // Catch:{ all -> 0x031e }
            goto L_0x0283
        L_0x02ec:
            r10.setActiveConcurrentCameraInfos(r15)     // Catch:{ all -> 0x031e }
        L_0x02ef:
            androidx.camera.core.ConcurrentCamera r0 = new androidx.camera.core.ConcurrentCamera     // Catch:{ all -> 0x031e }
            r0.<init>(r12)     // Catch:{ all -> 0x031e }
            androidx.tracing.Trace.endSection()
            return r0
        L_0x02f8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Invalid camera selectors in camera configs."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x0300:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x031e }
            r0.<init>(r8)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x0306:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Concurrent camera is not supported on the device."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x030e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Concurrent camera is only supporting two cameras at maximum."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x0316:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x031e }
            java.lang.String r1 = "Concurrent camera needs two camera configs."
            r0.<init>(r1)     // Catch:{ all -> 0x031e }
            throw r0     // Catch:{ all -> 0x031e }
        L_0x031e:
            r0 = move-exception
            androidx.tracing.Trace.endSection()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.lifecycle.LifecycleCameraProviderImpl.bindToLifecycle(java.util.List):androidx.camera.core.ConcurrentCamera");
    }

    public List<CameraInfo> getAvailableCameraInfos() {
        Trace.beginSection("CX:getAvailableCameraInfos");
        try {
            List<CameraInfo> arrayList = new ArrayList<>();
            CameraX access$getCameraX$p = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p);
            LinkedHashSet<CameraInternal> cameras = access$getCameraX$p.getCameraRepository().getCameras();
            Intrinsics.checkNotNullExpressionValue(cameras, "cameraX!!.cameraRepository.cameras");
            for (CameraInternal cameraInfo : cameras) {
                CameraInfo cameraInfo2 = cameraInfo.getCameraInfo();
                Intrinsics.checkNotNullExpressionValue(cameraInfo2, "camera.cameraInfo");
                arrayList.add(cameraInfo2);
            }
            return arrayList;
        } finally {
            Trace.endSection();
        }
    }

    public List<List<CameraInfo>> getAvailableConcurrentCameraInfos() {
        Trace.beginSection("CX:getAvailableConcurrentCameraInfos");
        try {
            Objects.requireNonNull(this.cameraX);
            CameraX access$getCameraX$p = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p);
            Objects.requireNonNull(access$getCameraX$p.getCameraFactory().getCameraCoordinator());
            CameraX access$getCameraX$p2 = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p2);
            List<List<CameraSelector>> concurrentCameraSelectors = access$getCameraX$p2.getCameraFactory().getCameraCoordinator().getConcurrentCameraSelectors();
            Intrinsics.checkNotNullExpressionValue(concurrentCameraSelectors, "cameraX!!.cameraFactory.…concurrentCameraSelectors");
            List<List<CameraInfo>> arrayList = new ArrayList<>();
            for (List<CameraSelector> it : concurrentCameraSelectors) {
                List arrayList2 = new ArrayList();
                for (CameraSelector cameraSelector : it) {
                    try {
                        Intrinsics.checkNotNullExpressionValue(cameraSelector, "cameraSelector");
                        arrayList2.add(getCameraInfo(cameraSelector));
                    } catch (IllegalArgumentException unused) {
                    }
                }
                arrayList.add(arrayList2);
            }
            return arrayList;
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public final Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, CameraSelector cameraSelector2, CompositionSettings compositionSettings, CompositionSettings compositionSettings2, ViewPort viewPort, List<? extends CameraEffect> list, UseCase... useCaseArr) {
        RestrictedCameraInfo restrictedCameraInfo;
        CameraInternal cameraInternal;
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        CameraSelector cameraSelector3 = cameraSelector;
        CameraSelector cameraSelector4 = cameraSelector2;
        UseCase[] useCaseArr2 = useCaseArr;
        Trace.beginSection("CX:bindToLifecycle-internal");
        try {
            Threads.checkMainThread();
            CameraX access$getCameraX$p = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p);
            CameraInternal select = cameraSelector3.select(access$getCameraX$p.getCameraRepository().getCameras());
            Intrinsics.checkNotNullExpressionValue(select, "primaryCameraSelector.se…cameraRepository.cameras)");
            boolean z = true;
            select.setPrimary(true);
            CameraInfo cameraInfo = getCameraInfo(cameraSelector3);
            Intrinsics.checkNotNull(cameraInfo, "null cannot be cast to non-null type androidx.camera.core.impl.RestrictedCameraInfo");
            RestrictedCameraInfo restrictedCameraInfo2 = (RestrictedCameraInfo) cameraInfo;
            if (cameraSelector4 != null) {
                CameraX access$getCameraX$p2 = this.cameraX;
                Intrinsics.checkNotNull(access$getCameraX$p2);
                CameraInternal select2 = cameraSelector4.select(access$getCameraX$p2.getCameraRepository().getCameras());
                select2.setPrimary(false);
                CameraInfo cameraInfo2 = getCameraInfo(cameraSelector4);
                Intrinsics.checkNotNull(cameraInfo2, "null cannot be cast to non-null type androidx.camera.core.impl.RestrictedCameraInfo");
                restrictedCameraInfo = (RestrictedCameraInfo) cameraInfo2;
                cameraInternal = select2;
            } else {
                cameraInternal = null;
                restrictedCameraInfo = null;
            }
            LifecycleCamera lifecycleCamera = this.lifecycleCameraRepository.getLifecycleCamera(lifecycleOwner2, CameraUseCaseAdapter.generateCameraId(restrictedCameraInfo2, restrictedCameraInfo));
            Collection<LifecycleCamera> lifecycleCameras = this.lifecycleCameraRepository.getLifecycleCameras();
            for (UseCase useCase : ArraysKt.filterNotNull(useCaseArr)) {
                Iterator<LifecycleCamera> it = lifecycleCameras.iterator();
                while (true) {
                    if (it.hasNext()) {
                        LifecycleCamera next = it.next();
                        Intrinsics.checkNotNullExpressionValue(next, "lifecycleCameras");
                        LifecycleCamera lifecycleCamera2 = next;
                        if (lifecycleCamera2.isBound(useCase)) {
                            if (!Intrinsics.areEqual((Object) lifecycleCamera2, (Object) lifecycleCamera)) {
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                String format = String.format("Use case %s already bound to a different lifecycle.", Arrays.copyOf(new Object[]{useCase}, 1));
                                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                                throw new IllegalStateException(format);
                            }
                        }
                    }
                }
            }
            if (lifecycleCamera == null) {
                LifecycleCameraRepository access$getLifecycleCameraRepository$p = this.lifecycleCameraRepository;
                CameraX access$getCameraX$p3 = this.cameraX;
                Intrinsics.checkNotNull(access$getCameraX$p3);
                CameraCoordinator cameraCoordinator = access$getCameraX$p3.getCameraFactory().getCameraCoordinator();
                CameraX access$getCameraX$p4 = this.cameraX;
                Intrinsics.checkNotNull(access$getCameraX$p4);
                CameraDeviceSurfaceManager cameraDeviceSurfaceManager = access$getCameraX$p4.getCameraDeviceSurfaceManager();
                CameraX access$getCameraX$p5 = this.cameraX;
                Intrinsics.checkNotNull(access$getCameraX$p5);
                lifecycleCamera = access$getLifecycleCameraRepository$p.createLifecycleCamera(lifecycleOwner2, new CameraUseCaseAdapter(select, cameraInternal, restrictedCameraInfo2, restrictedCameraInfo, compositionSettings, compositionSettings2, cameraCoordinator, cameraDeviceSurfaceManager, access$getCameraX$p5.getDefaultConfigFactory()));
            }
            if (useCaseArr2.length != 0) {
                z = false;
            }
            if (z) {
                Intrinsics.checkNotNull(lifecycleCamera);
            } else {
                LifecycleCameraRepository access$getLifecycleCameraRepository$p2 = this.lifecycleCameraRepository;
                Intrinsics.checkNotNull(lifecycleCamera);
                CameraX access$getCameraX$p6 = this.cameraX;
                Intrinsics.checkNotNull(access$getCameraX$p6);
                access$getLifecycleCameraRepository$p2.bindToLifecycleCamera(lifecycleCamera, viewPort, list, CollectionsKt.listOf(Arrays.copyOf(useCaseArr2, useCaseArr2.length)), access$getCameraX$p6.getCameraFactory().getCameraCoordinator());
            }
            Trace.endSection();
            return lifecycleCamera;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        Object obj;
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Trace.beginSection("CX:getCameraInfo");
        try {
            CameraX access$getCameraX$p = this.cameraX;
            Intrinsics.checkNotNull(access$getCameraX$p);
            CameraInfoInternal cameraInfoInternal = cameraSelector.select(access$getCameraX$p.getCameraRepository().getCameras()).getCameraInfoInternal();
            Intrinsics.checkNotNullExpressionValue(cameraInfoInternal, "cameraSelector.select(ca…meras).cameraInfoInternal");
            CameraConfig access$getCameraConfig = getCameraConfig(cameraSelector, cameraInfoInternal);
            CameraUseCaseAdapter.CameraId create = CameraUseCaseAdapter.CameraId.create(cameraInfoInternal.getCameraId(), access$getCameraConfig.getCompatibilityId());
            Intrinsics.checkNotNullExpressionValue(create, "create(\n                …ilityId\n                )");
            synchronized (this.lock) {
                obj = this.cameraInfoMap.get(create);
                if (obj == null) {
                    obj = new RestrictedCameraInfo(cameraInfoInternal, access$getCameraConfig);
                    this.cameraInfoMap.put(create, obj);
                }
                Unit unit = Unit.INSTANCE;
            }
            RestrictedCameraInfo restrictedCameraInfo = (RestrictedCameraInfo) obj;
            Trace.endSection();
            return restrictedCameraInfo;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }
}
