package expo.modules.camera.next;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\b\b\u0004\u0010\u0006*\u00020\u00072\u0010\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\n¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "T", "Landroid/view/View;", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/views/ViewDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewDefinitionBuilder.kt */
public final class CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$10 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ CameraViewNextModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$10(CameraViewNextModule cameraViewNextModule) {
        super(2);
        this.this$0 = cameraViewNextModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        ExpoCameraView expoCameraView = objArr[0];
        if (expoCameraView != null) {
            ExpoCameraView expoCameraView2 = expoCameraView;
            RecordingOptions recordingOptions = objArr[1];
            if (recordingOptions != null) {
                RecordingOptions recordingOptions2 = recordingOptions;
                if (expoCameraView2.getMute() || this.this$0.getPermissionsManager().hasGrantedPermissions("android.permission.RECORD_AUDIO")) {
                    expoCameraView2.record(recordingOptions2, promise, this.this$0.getCacheDirectory());
                    return;
                }
                throw new Exceptions.MissingPermissions("android.permission.RECORD_AUDIO");
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.camera.next.RecordingOptions");
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.camera.next.ExpoCameraView");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
