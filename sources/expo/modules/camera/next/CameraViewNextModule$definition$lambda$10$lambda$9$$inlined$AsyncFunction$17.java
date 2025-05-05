package expo.modules.camera.next;

import androidx.camera.video.Recording;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\b\b\u0002\u0010\u0004*\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "", "R", "P0", "T", "Landroid/view/View;", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/views/ViewDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewDefinitionBuilder.kt */
public final class CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$17 extends Lambda implements Function1<Object[], Object> {
    public CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$17() {
        super(1);
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        ExpoCameraView expoCameraView = objArr[0];
        if (expoCameraView != null) {
            Recording activeRecording = expoCameraView.getActiveRecording();
            if (activeRecording == null) {
                return null;
            }
            activeRecording.close();
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.camera.next.ExpoCameraView");
    }
}
