package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J5\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u000b\"\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\u0018\u0010\u0002\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH&J%\u0010\u0015\u001a\u00020\u00162\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\f0\u000b\"\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0016H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProvider;", "Landroidx/camera/core/CameraProvider;", "bindToLifecycle", "Landroidx/camera/core/Camera;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "useCaseGroup", "Landroidx/camera/core/UseCaseGroup;", "useCases", "", "Landroidx/camera/core/UseCase;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "Landroidx/camera/core/ConcurrentCamera;", "singleCameraConfigs", "", "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "isBound", "", "useCase", "unbind", "", "([Landroidx/camera/core/UseCase;)V", "unbindAll", "Companion", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LifecycleCameraProvider.kt */
public interface LifecycleCameraProvider extends CameraProvider {
    public static final Companion Companion = Companion.$$INSTANCE;

    @JvmStatic
    static Object createInstance(Context context, CameraXConfig cameraXConfig, Continuation<? super LifecycleCameraProvider> continuation) {
        return Companion.createInstance(context, cameraXConfig, continuation);
    }

    @JvmStatic
    static Object createInstance(Context context, Continuation<? super LifecycleCameraProvider> continuation) {
        return Companion.createInstance(context, continuation);
    }

    @JvmStatic
    static ListenableFuture<LifecycleCameraProvider> createInstanceAsync(Context context) {
        return Companion.createInstanceAsync(context);
    }

    @JvmStatic
    static ListenableFuture<LifecycleCameraProvider> createInstanceAsync(Context context, CameraXConfig cameraXConfig) {
        return Companion.createInstanceAsync(context, cameraXConfig);
    }

    Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup);

    Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCaseArr);

    ConcurrentCamera bindToLifecycle(List<ConcurrentCamera.SingleCameraConfig> list);

    boolean isBound(UseCase useCase);

    void unbind(UseCase... useCaseArr);

    void unbindAll();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH@¢\u0006\u0002\u0010\tJ\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\f"}, d2 = {"Landroidx/camera/lifecycle/LifecycleCameraProvider$Companion;", "", "()V", "createInstance", "Landroidx/camera/lifecycle/LifecycleCameraProvider;", "context", "Landroid/content/Context;", "cameraXConfig", "Landroidx/camera/core/CameraXConfig;", "(Landroid/content/Context;Landroidx/camera/core/CameraXConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createInstanceAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LifecycleCameraProvider.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @JvmStatic
        public final Object createInstance(Context context, Continuation<? super LifecycleCameraProvider> continuation) {
            return createInstance$default(this, context, (CameraXConfig) null, continuation, 2, (Object) null);
        }

        @JvmStatic
        public final ListenableFuture<LifecycleCameraProvider> createInstanceAsync(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return createInstanceAsync$default(this, context, (CameraXConfig) null, 2, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ Object createInstance$default(Companion companion, Context context, CameraXConfig cameraXConfig, Continuation continuation, int i, Object obj) {
            if ((i & 2) != 0) {
                cameraXConfig = null;
            }
            return companion.createInstance(context, cameraXConfig, continuation);
        }

        @JvmStatic
        public final Object createInstance(Context context, CameraXConfig cameraXConfig, Continuation<? super LifecycleCameraProvider> continuation) {
            return ListenableFutureKt.await(createInstanceAsync(context, cameraXConfig), continuation);
        }

        public static /* synthetic */ ListenableFuture createInstanceAsync$default(Companion companion, Context context, CameraXConfig cameraXConfig, int i, Object obj) {
            if ((i & 2) != 0) {
                cameraXConfig = null;
            }
            return companion.createInstanceAsync(context, cameraXConfig);
        }

        @JvmStatic
        public final ListenableFuture<LifecycleCameraProvider> createInstanceAsync(Context context, CameraXConfig cameraXConfig) {
            Intrinsics.checkNotNullParameter(context, "context");
            Preconditions.checkNotNull(context);
            LifecycleCameraProviderImpl lifecycleCameraProviderImpl = new LifecycleCameraProviderImpl();
            ListenableFuture<LifecycleCameraProvider> transform = Futures.transform(lifecycleCameraProviderImpl.initAsync$camera_lifecycle_release(context, cameraXConfig), new LifecycleCameraProvider$Companion$$ExternalSyntheticLambda0(new LifecycleCameraProvider$Companion$createInstanceAsync$1(lifecycleCameraProviderImpl)), CameraXExecutors.directExecutor());
            Intrinsics.checkNotNullExpressionValue(transform, "lifecycleCameraProvider …tExecutor()\n            )");
            return transform;
        }

        /* access modifiers changed from: private */
        public static final LifecycleCameraProvider createInstanceAsync$lambda$0(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (LifecycleCameraProvider) function1.invoke(obj);
        }
    }
}
