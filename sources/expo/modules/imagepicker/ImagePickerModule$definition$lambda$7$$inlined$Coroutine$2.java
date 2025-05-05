package expo.modules.imagepicker;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001*\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H@¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$4", "expo/modules/kotlin/functions/AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2", f = "ImagePickerModule.kt", i = {0}, l = {174, 180}, m = "invokeSuspend", n = {"options"}, s = {"L$0"})
/* compiled from: AsyncFunctionBuilder.kt */
public final class ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2(Continuation continuation, ImagePickerModule imagePickerModule) {
        super(3, continuation);
        this.this$0 = imagePickerModule;
    }

    public final Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2 imagePickerModule$definition$lambda$7$$inlined$Coroutine$2 = new ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2(continuation, this.this$0);
        imagePickerModule$definition$lambda$7$$inlined$Coroutine$2.L$0 = objArr;
        return imagePickerModule$definition$lambda$7$$inlined$Coroutine$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: expo.modules.imagepicker.ImagePickerOptions} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0086
        L_0x0012:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001a:
            java.lang.Object r1 = r7.L$0
            expo.modules.imagepicker.ImagePickerOptions r1 = (expo.modules.imagepicker.ImagePickerOptions) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0046
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            r1 = 0
            r8 = r8[r1]
            if (r8 == 0) goto L_0x0087
            r1 = r8
            expo.modules.imagepicker.ImagePickerOptions r1 = (expo.modules.imagepicker.ImagePickerOptions) r1
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            expo.modules.imagepicker.ImagePickerModule r8 = r7.this$0
            r8.ensureTargetActivityIsAvailable(r1)
            expo.modules.imagepicker.ImagePickerModule r8 = r7.this$0
            r7.L$0 = r1
            r7.label = r3
            java.lang.Object r8 = r8.ensureCameraPermissionsAreGranted(r7)
            if (r8 != r0) goto L_0x0046
            return r0
        L_0x0046:
            expo.modules.imagepicker.ImagePickerModule r8 = r7.this$0
            java.io.File r8 = r8.getCacheDirectory()
            expo.modules.imagepicker.MediaTypes r3 = r1.getMediaTypes()
            java.lang.String r3 = r3.toFileExtension()
            java.io.File r8 = expo.modules.imagepicker.ImagePickerUtilsKt.createOutputFile(r8, r3)
            expo.modules.imagepicker.ImagePickerModule r3 = r7.this$0
            android.content.Context r3 = r3.getContext()
            android.net.Uri r8 = expo.modules.imagepicker.ImagePickerUtilsKt.toContentUri(r8, r3)
            java.lang.String r8 = r8.toString()
            java.lang.String r3 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            expo.modules.imagepicker.contracts.CameraContractOptions r8 = r1.toCameraContractOptions(r8)
            expo.modules.imagepicker.ImagePickerModule r3 = r7.this$0
            expo.modules.imagepicker.ImagePickerModule$definition$1$5$1 r4 = new expo.modules.imagepicker.ImagePickerModule$definition$1$5$1
            expo.modules.imagepicker.ImagePickerModule r5 = r7.this$0
            r6 = 0
            r4.<init>(r5, r8, r6)
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r7.L$0 = r6
            r7.label = r2
            java.lang.Object r8 = r3.launchContract(r4, r1, r7)
            if (r8 != r0) goto L_0x0086
            return r0
        L_0x0086:
            return r8
        L_0x0087:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type expo.modules.imagepicker.ImagePickerOptions"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerModule$definition$lambda$7$$inlined$Coroutine$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
