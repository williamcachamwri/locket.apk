package expo.modules.imagepicker;

import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.imagepicker.ImagePickerModule$definition$1$8", f = "ImagePickerModule.kt", i = {0, 1}, l = {84, 88, 92}, m = "invokeSuspend", n = {"$this$RegisterActivityContracts", "$this$RegisterActivityContracts"}, s = {"L$0", "L$0"})
/* compiled from: ImagePickerModule.kt */
final class ImagePickerModule$definition$1$8 extends SuspendLambda implements Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImagePickerModule$definition$1$8(ImagePickerModule imagePickerModule, Continuation<? super ImagePickerModule$definition$1$8> continuation) {
        super(2, continuation);
        this.this$0 = imagePickerModule;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ImagePickerModule$definition$1$8 imagePickerModule$definition$1$8 = new ImagePickerModule$definition$1$8(this.this$0, continuation);
        imagePickerModule$definition$1$8.L$0 = obj;
        return imagePickerModule$definition$1$8;
    }

    public final Object invoke(AppContextActivityResultCaller appContextActivityResultCaller, Continuation<? super Unit> continuation) {
        return ((ImagePickerModule$definition$1$8) create(appContextActivityResultCaller, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x003a
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            java.lang.Object r0 = r9.L$0
            expo.modules.imagepicker.ImagePickerModule r0 = (expo.modules.imagepicker.ImagePickerModule) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00c4
        L_0x001a:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0022:
            java.lang.Object r1 = r9.L$1
            expo.modules.imagepicker.ImagePickerModule r1 = (expo.modules.imagepicker.ImagePickerModule) r1
            java.lang.Object r3 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r3 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r3
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0096
        L_0x002e:
            java.lang.Object r1 = r9.L$1
            expo.modules.imagepicker.ImagePickerModule r1 = (expo.modules.imagepicker.ImagePickerModule) r1
            java.lang.Object r4 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r4 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r4
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x006a
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            expo.modules.kotlin.activityresult.AppContextActivityResultCaller r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultCaller) r10
            expo.modules.imagepicker.ImagePickerModule r1 = r9.this$0
            expo.modules.imagepicker.contracts.CameraContract r5 = new expo.modules.imagepicker.contracts.CameraContract
            expo.modules.imagepicker.ImagePickerModule r6 = r9.this$0
            expo.modules.kotlin.providers.AppContextProvider r6 = (expo.modules.kotlin.providers.AppContextProvider) r6
            r5.<init>(r6)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r5 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r5
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$1 r6 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$1
            expo.modules.imagepicker.ImagePickerModule r7 = r9.this$0
            r6.<init>(r7)
            expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback r6 = (expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback) r6
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r9.L$0 = r10
            r9.L$1 = r1
            r9.label = r4
            java.lang.Object r4 = r10.registerForActivityResult(r5, r6, r7)
            if (r4 != r0) goto L_0x0067
            return r0
        L_0x0067:
            r8 = r4
            r4 = r10
            r10 = r8
        L_0x006a:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            r1.cameraLauncher = r10
            expo.modules.imagepicker.ImagePickerModule r1 = r9.this$0
            expo.modules.imagepicker.contracts.ImageLibraryContract r10 = new expo.modules.imagepicker.contracts.ImageLibraryContract
            expo.modules.imagepicker.ImagePickerModule r5 = r9.this$0
            expo.modules.kotlin.providers.AppContextProvider r5 = (expo.modules.kotlin.providers.AppContextProvider) r5
            r10.<init>(r5)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r10
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$2 r5 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$2
            expo.modules.imagepicker.ImagePickerModule r6 = r9.this$0
            r5.<init>(r6)
            expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback r5 = (expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback) r5
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r4
            r9.L$1 = r1
            r9.label = r3
            java.lang.Object r10 = r4.registerForActivityResult(r10, r5, r6)
            if (r10 != r0) goto L_0x0095
            return r0
        L_0x0095:
            r3 = r4
        L_0x0096:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            r1.imageLibraryLauncher = r10
            expo.modules.imagepicker.ImagePickerModule r10 = r9.this$0
            expo.modules.imagepicker.contracts.CropImageContract r1 = new expo.modules.imagepicker.contracts.CropImageContract
            expo.modules.imagepicker.ImagePickerModule r4 = r9.this$0
            expo.modules.kotlin.providers.AppContextProvider r4 = (expo.modules.kotlin.providers.AppContextProvider) r4
            r1.<init>(r4)
            expo.modules.kotlin.activityresult.AppContextActivityResultContract r1 = (expo.modules.kotlin.activityresult.AppContextActivityResultContract) r1
            expo.modules.imagepicker.ImagePickerModule$definition$1$8$3 r4 = new expo.modules.imagepicker.ImagePickerModule$definition$1$8$3
            expo.modules.imagepicker.ImagePickerModule r5 = r9.this$0
            r4.<init>(r5)
            expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback r4 = (expo.modules.kotlin.activityresult.AppContextActivityResultFallbackCallback) r4
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r10
            r6 = 0
            r9.L$1 = r6
            r9.label = r2
            java.lang.Object r1 = r3.registerForActivityResult(r1, r4, r5)
            if (r1 != r0) goto L_0x00c2
            return r0
        L_0x00c2:
            r0 = r10
            r10 = r1
        L_0x00c4:
            expo.modules.kotlin.activityresult.AppContextActivityResultLauncher r10 = (expo.modules.kotlin.activityresult.AppContextActivityResultLauncher) r10
            r0.cropImageLauncher = r10
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerModule$definition$1$8.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
