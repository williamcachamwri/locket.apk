package expo.modules.imagepicker;

import android.net.Uri;
import expo.modules.imagepicker.contracts.CropImageContractOptions;
import expo.modules.imagepicker.contracts.ImagePickerContractResult;
import expo.modules.kotlin.activityresult.AppContextActivityResultLauncher;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.ImagePickerModule$launchContract$2", f = "ImagePickerModule.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImagePickerModule.kt */
final class ImagePickerModule$launchContract$2 extends SuspendLambda implements Function1<Continuation<? super ImagePickerContractResult>, Object> {
    final /* synthetic */ ImagePickerOptions $options;
    final /* synthetic */ Ref.ObjectRef<ImagePickerContractResult.Success> $result;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImagePickerModule$launchContract$2(ImagePickerModule imagePickerModule, Ref.ObjectRef<ImagePickerContractResult.Success> objectRef, ImagePickerOptions imagePickerOptions, Continuation<? super ImagePickerModule$launchContract$2> continuation) {
        super(1, continuation);
        this.this$0 = imagePickerModule;
        this.$result = objectRef;
        this.$options = imagePickerOptions;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new ImagePickerModule$launchContract$2(this.this$0, this.$result, this.$options, continuation);
    }

    public final Object invoke(Continuation<? super ImagePickerContractResult> continuation) {
        return ((ImagePickerModule$launchContract$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AppContextActivityResultLauncher access$getCropImageLauncher$p = this.this$0.cropImageLauncher;
            if (access$getCropImageLauncher$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageLauncher");
                access$getCropImageLauncher$p = null;
            }
            String uri = ((Uri) ((ImagePickerContractResult.Success) this.$result.element).getData().get(0).getSecond()).toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            this.label = 1;
            obj = access$getCropImageLauncher$p.launch(new CropImageContractOptions(uri, this.$options), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
