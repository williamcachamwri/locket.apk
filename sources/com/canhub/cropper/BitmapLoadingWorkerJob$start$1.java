package com.canhub.cropper;

import com.canhub.cropper.BitmapLoadingWorkerJob;
import com.canhub.cropper.BitmapUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.canhub.cropper.BitmapLoadingWorkerJob$start$1", f = "BitmapLoadingWorkerJob.kt", i = {}, l = {45, 58}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BitmapLoadingWorkerJob.kt */
final class BitmapLoadingWorkerJob$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapLoadingWorkerJob this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BitmapLoadingWorkerJob$start$1(BitmapLoadingWorkerJob bitmapLoadingWorkerJob, Continuation<? super BitmapLoadingWorkerJob$start$1> continuation) {
        super(2, continuation);
        this.this$0 = bitmapLoadingWorkerJob;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapLoadingWorkerJob$start$1 bitmapLoadingWorkerJob$start$1 = new BitmapLoadingWorkerJob$start$1(this.this$0, continuation);
        bitmapLoadingWorkerJob$start$1.L$0 = obj;
        return bitmapLoadingWorkerJob$start$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapLoadingWorkerJob$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                BitmapUtils.BitmapSampled decodeSampledBitmap = BitmapUtils.INSTANCE.decodeSampledBitmap(this.this$0.context, this.this$0.getUri(), this.this$0.width, this.this$0.height);
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    BitmapUtils.RotateBitmapResult orientateBitmapByExif = BitmapUtils.INSTANCE.orientateBitmapByExif(decodeSampledBitmap.getBitmap(), this.this$0.context, this.this$0.getUri());
                    this.label = 1;
                    if (this.this$0.onPostExecute(new BitmapLoadingWorkerJob.Result(this.this$0.getUri(), orientateBitmapByExif.getBitmap(), decodeSampledBitmap.getSampleSize(), orientateBitmapByExif.getDegrees(), orientateBitmapByExif.getFlipHorizontally(), orientateBitmapByExif.getFlipVertically()), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                this.label = 2;
                if (this.this$0.onPostExecute(new BitmapLoadingWorkerJob.Result(this.this$0.getUri(), e), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
