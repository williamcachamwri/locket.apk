package androidx.camera.lifecycle;

import androidx.camera.core.CameraX;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: LifecycleCameraProviderImpl.kt */
final class LifecycleCameraProviderImpl$initAsync$1$initFuture$1 extends Lambda implements Function1<Void, ListenableFuture<Void>> {
    final /* synthetic */ CameraX $cameraX;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LifecycleCameraProviderImpl$initAsync$1$initFuture$1(CameraX cameraX) {
        super(1);
        this.$cameraX = cameraX;
    }

    public final ListenableFuture<Void> invoke(Void voidR) {
        return this.$cameraX.getInitializeFuture();
    }
}
