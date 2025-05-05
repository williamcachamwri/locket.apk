package expo.modules.camera.next;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/camera/next/ExpoCameraView;", "enabled", "", "invoke", "(Lexpo/modules/camera/next/ExpoCameraView;Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewNextModule.kt */
final class CameraViewNextModule$definition$1$6$9 extends Lambda implements Function2<ExpoCameraView, Boolean, Unit> {
    public static final CameraViewNextModule$definition$1$6$9 INSTANCE = new CameraViewNextModule$definition$1$6$9();

    CameraViewNextModule$definition$1$6$9() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((ExpoCameraView) obj, (Boolean) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(ExpoCameraView expoCameraView, Boolean bool) {
        Intrinsics.checkNotNullParameter(expoCameraView, "view");
        expoCameraView.setShouldScanBarcodes(bool != null ? bool.booleanValue() : false);
    }
}
