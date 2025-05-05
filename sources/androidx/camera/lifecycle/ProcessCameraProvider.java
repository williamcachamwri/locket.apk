package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import androidx.tracing.Trace;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0017J5\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00190\u0018\"\u0004\u0018\u00010\u0019H\u0017¢\u0006\u0002\u0010\u001aJ\u0018\u0010\u000f\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u0006H\u0017J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010#\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u0019H\u0016J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020&0%H\u0007J%\u0010,\u001a\u00020\u001f2\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00190\u0018\"\u0004\u0018\u00010\u0019H\u0017¢\u0006\u0002\u0010-J\b\u0010.\u001a\u00020\u001fH\u0017R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00068F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Landroidx/camera/lifecycle/ProcessCameraProvider;", "Landroidx/camera/lifecycle/LifecycleCameraProvider;", "lifecycleCameraProvider", "Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", "(Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;)V", "availableCameraInfos", "", "Landroidx/camera/core/CameraInfo;", "getAvailableCameraInfos", "()Ljava/util/List;", "availableConcurrentCameraInfos", "getAvailableConcurrentCameraInfos", "isConcurrentCameraModeOn", "", "()Z", "bindToLifecycle", "Landroidx/camera/core/Camera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "useCases", "", "Landroidx/camera/core/UseCase;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "Landroidx/camera/core/ConcurrentCamera;", "singleCameraConfigs", "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "configure", "", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "getCameraInfo", "hasCamera", "initAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "context", "Landroid/content/Context;", "isBound", "useCase", "shutdownAsync", "unbind", "([Landroidx/camera/core/UseCase;)V", "unbindAll", "Companion", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessCameraProvider.kt */
public final class ProcessCameraProvider implements LifecycleCameraProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ProcessCameraProvider sAppInstance = new ProcessCameraProvider(new LifecycleCameraProviderImpl());
    private final LifecycleCameraProviderImpl lifecycleCameraProvider;

    @JvmStatic
    /* renamed from: clearConfiguration-LRDsOJo  reason: not valid java name */
    public static final void m235clearConfigurationLRDsOJo(long j) {
        Companion.m237clearConfigurationLRDsOJo(j);
    }

    @JvmStatic
    public static final void configureInstance(CameraXConfig cameraXConfig) {
        Companion.configureInstance(cameraXConfig);
    }

    @JvmStatic
    public static final ListenableFuture<ProcessCameraProvider> getInstance(Context context) {
        return Companion.getInstance(context);
    }

    private ProcessCameraProvider(LifecycleCameraProviderImpl lifecycleCameraProviderImpl) {
        this.lifecycleCameraProvider = lifecycleCameraProviderImpl;
    }

    public boolean isBound(UseCase useCase) {
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        return this.lifecycleCameraProvider.isBound(useCase);
    }

    public void unbind(UseCase... useCaseArr) {
        Intrinsics.checkNotNullParameter(useCaseArr, "useCases");
        this.lifecycleCameraProvider.unbind((UseCase[]) Arrays.copyOf(useCaseArr, useCaseArr.length));
    }

    public void unbindAll() {
        this.lifecycleCameraProvider.unbindAll();
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCaseArr) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCaseArr, "useCases");
        return this.lifecycleCameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, (UseCase[]) Arrays.copyOf(useCaseArr, useCaseArr.length));
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        Intrinsics.checkNotNullParameter(useCaseGroup, "useCaseGroup");
        return this.lifecycleCameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, useCaseGroup);
    }

    public ConcurrentCamera bindToLifecycle(List<ConcurrentCamera.SingleCameraConfig> list) {
        Intrinsics.checkNotNullParameter(list, "singleCameraConfigs");
        return this.lifecycleCameraProvider.bindToLifecycle(list);
    }

    public List<CameraInfo> getAvailableCameraInfos() {
        return this.lifecycleCameraProvider.getAvailableCameraInfos();
    }

    public final List<List<CameraInfo>> getAvailableConcurrentCameraInfos() {
        return this.lifecycleCameraProvider.getAvailableConcurrentCameraInfos();
    }

    public final boolean isConcurrentCameraModeOn() {
        return this.lifecycleCameraProvider.isConcurrentCameraModeOn();
    }

    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        return this.lifecycleCameraProvider.hasCamera(cameraSelector);
    }

    public CameraInfo getCameraInfo(CameraSelector cameraSelector) {
        Intrinsics.checkNotNullParameter(cameraSelector, "cameraSelector");
        return this.lifecycleCameraProvider.getCameraInfo(cameraSelector);
    }

    public final ListenableFuture<Void> shutdownAsync() {
        return this.lifecycleCameraProvider.shutdownAsync$camera_lifecycle_release();
    }

    /* access modifiers changed from: private */
    public final ListenableFuture<Void> initAsync(Context context) {
        return this.lifecycleCameraProvider.initAsync$camera_lifecycle_release(context, (CameraXConfig) null);
    }

    /* access modifiers changed from: private */
    public final void configure(CameraXConfig cameraXConfig) {
        this.lifecycleCameraProvider.configure$camera_lifecycle_release(cameraXConfig);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0007J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/camera/lifecycle/ProcessCameraProvider$Companion;", "", "()V", "sAppInstance", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "clearConfiguration", "", "timeout", "Lkotlin/time/Duration;", "clearConfiguration-LRDsOJo", "(J)V", "configureInstance", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "getInstance", "Lcom/google/common/util/concurrent/ListenableFuture;", "context", "Landroid/content/Context;", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ProcessCameraProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ListenableFuture<ProcessCameraProvider> getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Preconditions.checkNotNull(context);
            ListenableFuture<ProcessCameraProvider> transform = Futures.transform(ProcessCameraProvider.sAppInstance.initAsync(context), new ProcessCameraProvider$Companion$$ExternalSyntheticLambda0(ProcessCameraProvider$Companion$getInstance$1.INSTANCE), CameraXExecutors.directExecutor());
            Intrinsics.checkNotNullExpressionValue(transform, "transform(\n             …tExecutor()\n            )");
            return transform;
        }

        /* access modifiers changed from: private */
        public static final ProcessCameraProvider getInstance$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (ProcessCameraProvider) function1.invoke(obj);
        }

        /* renamed from: clearConfiguration-LRDsOJo$default  reason: not valid java name */
        public static /* synthetic */ void m236clearConfigurationLRDsOJo$default(Companion companion, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                Duration.Companion companion2 = Duration.Companion;
                j = DurationKt.toDuration(10, DurationUnit.SECONDS);
            }
            companion.m237clearConfigurationLRDsOJo(j);
        }

        @JvmStatic
        /* renamed from: clearConfiguration-LRDsOJo  reason: not valid java name */
        public final void m237clearConfigurationLRDsOJo(long j) {
            ProcessCameraProvider.sAppInstance.shutdownAsync().get(Duration.m1618getInWholeNanosecondsimpl(j), TimeUnit.NANOSECONDS);
        }

        @JvmStatic
        public final void configureInstance(CameraXConfig cameraXConfig) {
            Intrinsics.checkNotNullParameter(cameraXConfig, "cameraXConfig");
            Trace.beginSection("CX:configureInstance");
            try {
                ProcessCameraProvider.sAppInstance.configure(cameraXConfig);
                Unit unit = Unit.INSTANCE;
            } finally {
                Trace.endSection();
            }
        }
    }
}
