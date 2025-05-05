package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.core.net.UriKt;
import expo.modules.imagepicker.ImagePickerUtilsKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.contracts.CropImageContract$parseResult$1", f = "CropImageContract.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CropImageContract.kt */
final class CropImageContract$parseResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentResolver $contentResolver;
    final /* synthetic */ CropImageContractOptions $input;
    final /* synthetic */ Uri $targetUri;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CropImageContract$parseResult$1(CropImageContractOptions cropImageContractOptions, Uri uri, ContentResolver contentResolver, Continuation<? super CropImageContract$parseResult$1> continuation) {
        super(2, continuation);
        this.$input = cropImageContractOptions;
        this.$targetUri = uri;
        this.$contentResolver = contentResolver;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CropImageContract$parseResult$1(this.$input, this.$targetUri, this.$contentResolver, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CropImageContract$parseResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Uri parse = Uri.parse(this.$input.getSourceUri());
            File file = UriKt.toFile(this.$targetUri);
            ContentResolver contentResolver = this.$contentResolver;
            Intrinsics.checkNotNullExpressionValue(contentResolver, "$contentResolver");
            this.label = 1;
            if (ImagePickerUtilsKt.copyExifData(parse, file, contentResolver, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
