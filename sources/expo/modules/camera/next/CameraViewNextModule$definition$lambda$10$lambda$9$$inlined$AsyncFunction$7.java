package expo.modules.camera.next;

import expo.modules.core.utilities.EmulatorUtilities;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\b\b\u0004\u0010\u0006*\u00020\u00072\u0010\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "T", "Landroid/view/View;", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/views/ViewDefinitionBuilder$AsyncFunction$21"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewDefinitionBuilder.kt */
public final class CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$7 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ CameraViewNextModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CameraViewNextModule$definition$lambda$10$lambda$9$$inlined$AsyncFunction$7(CameraViewNextModule cameraViewNextModule) {
        super(1);
        this.this$0 = cameraViewNextModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        ExpoCameraView expoCameraView = objArr[0];
        if (expoCameraView != null) {
            ExpoCameraView expoCameraView2 = expoCameraView;
            PictureOptions pictureOptions = objArr[1];
            if (pictureOptions != null) {
                PictureOptions pictureOptions2 = pictureOptions;
                Promise promise = objArr[2];
                if (promise != null) {
                    Promise promise2 = promise;
                    if (!EmulatorUtilities.INSTANCE.isRunningOnEmulator()) {
                        expoCameraView2.takePicture(pictureOptions2, promise2, this.this$0.getCacheDirectory());
                        return Unit.INSTANCE;
                    }
                    byte[] generateSimulatorPhoto = CameraViewHelper.INSTANCE.generateSimulatorPhoto(expoCameraView2.getWidth(), expoCameraView2.getHeight());
                    return BuildersKt__Builders_commonKt.launch$default(this.this$0.moduleScope, (CoroutineContext) null, (CoroutineStart) null, new CameraViewNextModule$definition$1$6$10$1(generateSimulatorPhoto, promise2, pictureOptions2, this.this$0, expoCameraView2, (Continuation<? super CameraViewNextModule$definition$1$6$10$1>) null), 3, (Object) null);
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.camera.next.PictureOptions");
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.camera.next.ExpoCameraView");
    }
}
