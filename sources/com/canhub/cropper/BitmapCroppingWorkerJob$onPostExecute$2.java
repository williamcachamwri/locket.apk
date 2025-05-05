package com.canhub.cropper;

import com.canhub.cropper.BitmapCroppingWorkerJob;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.canhub.cropper.BitmapCroppingWorkerJob$onPostExecute$2", f = "BitmapCroppingWorkerJob.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BitmapCroppingWorkerJob.kt */
final class BitmapCroppingWorkerJob$onPostExecute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BitmapCroppingWorkerJob.Result $result;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapCroppingWorkerJob this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BitmapCroppingWorkerJob$onPostExecute$2(BitmapCroppingWorkerJob bitmapCroppingWorkerJob, BitmapCroppingWorkerJob.Result result, Continuation<? super BitmapCroppingWorkerJob$onPostExecute$2> continuation) {
        super(2, continuation);
        this.this$0 = bitmapCroppingWorkerJob;
        this.$result = result;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapCroppingWorkerJob$onPostExecute$2 bitmapCroppingWorkerJob$onPostExecute$2 = new BitmapCroppingWorkerJob$onPostExecute$2(this.this$0, this.$result, continuation);
        bitmapCroppingWorkerJob$onPostExecute$2.L$0 = obj;
        return bitmapCroppingWorkerJob$onPostExecute$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapCroppingWorkerJob$onPostExecute$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CropImageView cropImageView;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            if (CoroutineScopeKt.isActive((CoroutineScope) this.L$0) && (cropImageView = (CropImageView) this.this$0.cropImageViewReference.get()) != null) {
                BitmapCroppingWorkerJob.Result result = this.$result;
                booleanRef.element = true;
                cropImageView.onImageCroppingAsyncComplete(result);
            }
            if (!booleanRef.element && this.$result.getBitmap() != null) {
                this.$result.getBitmap().recycle();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
