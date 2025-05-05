package expo.modules.imagepicker;

import android.os.OperationCanceledException;
import expo.modules.imagepicker.contracts.ImagePickerContractResult;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.ImagePickerModule$launchPicker$2", f = "ImagePickerModule.kt", i = {}, l = {163}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImagePickerModule.kt */
final class ImagePickerModule$launchPicker$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ImagePickerContractResult.Success>, Object> {
    final /* synthetic */ Function1<Continuation<? super ImagePickerContractResult>, Object> $pickerLauncher;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImagePickerModule$launchPicker$2(Function1<? super Continuation<? super ImagePickerContractResult>, ? extends Object> function1, Continuation<? super ImagePickerModule$launchPicker$2> continuation) {
        super(2, continuation);
        this.$pickerLauncher = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImagePickerModule$launchPicker$2(this.$pickerLauncher, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ImagePickerContractResult.Success> continuation) {
        return ((ImagePickerModule$launchPicker$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1<Continuation<? super ImagePickerContractResult>, Object> function1 = this.$pickerLauncher;
            this.label = 1;
            obj = function1.invoke(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ImagePickerContractResult imagePickerContractResult = (ImagePickerContractResult) obj;
        if (imagePickerContractResult instanceof ImagePickerContractResult.Success) {
            return (ImagePickerContractResult.Success) imagePickerContractResult;
        }
        if (imagePickerContractResult instanceof ImagePickerContractResult.Cancelled) {
            throw new OperationCanceledException();
        } else if (imagePickerContractResult instanceof ImagePickerContractResult.Error) {
            throw new FailedToPickMediaException();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
