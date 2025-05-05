package expo.modules.imagepicker;

import expo.modules.imagepicker.contracts.ImagePickerContractResult;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.ImagePickerModule", f = "ImagePickerModule.kt", i = {0, 0, 0, 1, 1, 1}, l = {130, 137, 141}, m = "launchContract", n = {"this", "options", "result", "this", "options", "result"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: ImagePickerModule.kt */
final class ImagePickerModule$launchContract$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImagePickerModule$launchContract$1(ImagePickerModule imagePickerModule, Continuation<? super ImagePickerModule$launchContract$1> continuation) {
        super(continuation);
        this.this$0 = imagePickerModule;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.launchContract((Function1<? super Continuation<? super ImagePickerContractResult>, ? extends Object>) null, (ImagePickerOptions) null, this);
    }
}
