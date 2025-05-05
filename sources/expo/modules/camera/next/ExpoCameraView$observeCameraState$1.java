package expo.modules.camera.next;

import androidx.camera.core.CameraState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/camera/core/CameraState;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCameraView.kt */
final class ExpoCameraView$observeCameraState$1 extends Lambda implements Function1<CameraState, Unit> {
    final /* synthetic */ ExpoCameraView this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExpoCameraView.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CameraState.Type.values().length];
            try {
                iArr[CameraState.Type.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoCameraView$observeCameraState$1(ExpoCameraView expoCameraView) {
        super(1);
        this.this$0 = expoCameraView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CameraState) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CameraState cameraState) {
        if (WhenMappings.$EnumSwitchMapping$0[cameraState.getType().ordinal()] == 1) {
            this.this$0.getOnCameraReady().invoke(Unit.INSTANCE);
        }
    }
}
