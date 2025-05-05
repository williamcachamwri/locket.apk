package androidx.camera.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageCaptureExt.kt */
final class ImageCaptureExtKt$takePicture$4$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ Ref.ObjectRef<DelegatingImageSavedCallback> $delegatingCallback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageCaptureExtKt$takePicture$4$2(Ref.ObjectRef<DelegatingImageSavedCallback> objectRef) {
        super(1);
        this.$delegatingCallback = objectRef;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        DelegatingImageSavedCallback delegatingImageSavedCallback;
        if (this.$delegatingCallback.element == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delegatingCallback");
            delegatingImageSavedCallback = null;
        } else {
            delegatingImageSavedCallback = (DelegatingImageSavedCallback) this.$delegatingCallback.element;
        }
        delegatingImageSavedCallback.dispose();
    }
}
