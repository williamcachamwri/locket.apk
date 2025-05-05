package androidx.camera.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/camera/lifecycle/LifecycleCameraProvider;", "kotlin.jvm.PlatformType", "it", "Ljava/lang/Void;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: LifecycleCameraProvider.kt */
final class LifecycleCameraProvider$Companion$createInstanceAsync$1 extends Lambda implements Function1<Void, LifecycleCameraProvider> {
    final /* synthetic */ LifecycleCameraProviderImpl $lifecycleCameraProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LifecycleCameraProvider$Companion$createInstanceAsync$1(LifecycleCameraProviderImpl lifecycleCameraProviderImpl) {
        super(1);
        this.$lifecycleCameraProvider = lifecycleCameraProviderImpl;
    }

    public final LifecycleCameraProvider invoke(Void voidR) {
        return this.$lifecycleCameraProvider;
    }
}
