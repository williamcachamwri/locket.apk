package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.CameraX;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/camera/lifecycle/LifecycleCameraProviderImpl$initAsync$1$2", "Landroidx/camera/core/impl/utils/futures/FutureCallback;", "Ljava/lang/Void;", "onFailure", "", "t", "", "onSuccess", "void", "camera-lifecycle_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LifecycleCameraProviderImpl.kt */
public final class LifecycleCameraProviderImpl$initAsync$1$2 implements FutureCallback<Void> {
    final /* synthetic */ CameraX $cameraX;
    final /* synthetic */ Context $context;
    final /* synthetic */ LifecycleCameraProviderImpl this$0;

    LifecycleCameraProviderImpl$initAsync$1$2(LifecycleCameraProviderImpl lifecycleCameraProviderImpl, CameraX cameraX, Context context) {
        this.this$0 = lifecycleCameraProviderImpl;
        this.$cameraX = cameraX;
        this.$context = context;
    }

    public void onSuccess(Void voidR) {
        this.this$0.cameraX = this.$cameraX;
        this.this$0.context = ContextUtil.getApplicationContext(this.$context);
    }

    public void onFailure(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "t");
        this.this$0.shutdownAsync$camera_lifecycle_release();
    }
}
