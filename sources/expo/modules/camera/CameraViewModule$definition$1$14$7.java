package expo.modules.camera;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/camera/ExpoCameraView;", "zoom", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewModule.kt */
final class CameraViewModule$definition$1$14$7 extends Lambda implements Function2<ExpoCameraView, Float, Unit> {
    public static final CameraViewModule$definition$1$14$7 INSTANCE = new CameraViewModule$definition$1$14$7();

    CameraViewModule$definition$1$14$7() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((ExpoCameraView) obj, ((Number) obj2).floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(ExpoCameraView expoCameraView, float f) {
        Intrinsics.checkNotNullParameter(expoCameraView, "view");
        expoCameraView.getCameraView$expo_camera_release().setZoom(f);
    }
}
