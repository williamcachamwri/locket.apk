package com.canhub.cropper;

import android.graphics.Bitmap;
import android.net.Uri;
import com.canhub.cropper.BitmapCroppingWorkerJob;
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
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.canhub.cropper.BitmapCroppingWorkerJob$start$1", f = "BitmapCroppingWorkerJob.kt", i = {}, l = {77, 102}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BitmapCroppingWorkerJob.kt */
final class BitmapCroppingWorkerJob$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BitmapCroppingWorkerJob this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BitmapCroppingWorkerJob$start$1(BitmapCroppingWorkerJob bitmapCroppingWorkerJob, Continuation<? super BitmapCroppingWorkerJob$start$1> continuation) {
        super(2, continuation);
        this.this$0 = bitmapCroppingWorkerJob;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BitmapCroppingWorkerJob$start$1 bitmapCroppingWorkerJob$start$1 = new BitmapCroppingWorkerJob$start$1(this.this$0, continuation);
        bitmapCroppingWorkerJob$start$1.L$0 = obj;
        return bitmapCroppingWorkerJob$start$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BitmapCroppingWorkerJob$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final BitmapUtils.BitmapSampled bitmapSampled;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                if (this.this$0.getUri() != null) {
                    bitmapSampled = BitmapUtils.INSTANCE.cropBitmap(this.this$0.context, this.this$0.getUri(), this.this$0.cropPoints, this.this$0.degreesRotated, this.this$0.orgWidth, this.this$0.orgHeight, this.this$0.fixAspectRatio, this.this$0.aspectRatioX, this.this$0.aspectRatioY, this.this$0.reqWidth, this.this$0.reqHeight, this.this$0.flipHorizontally, this.this$0.flipVertically);
                } else if (this.this$0.bitmap != null) {
                    bitmapSampled = BitmapUtils.INSTANCE.cropBitmapObjectHandleOOM(this.this$0.bitmap, this.this$0.cropPoints, this.this$0.degreesRotated, this.this$0.fixAspectRatio, this.this$0.aspectRatioX, this.this$0.aspectRatioY, this.this$0.flipHorizontally, this.this$0.flipVertically);
                } else {
                    this.label = 1;
                    if (this.this$0.onPostExecute(new BitmapCroppingWorkerJob.Result((Bitmap) null, 1), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                final Bitmap resizeBitmap = BitmapUtils.INSTANCE.resizeBitmap(bitmapSampled.getBitmap(), this.this$0.reqWidth, this.this$0.reqHeight, this.this$0.options);
                final BitmapCroppingWorkerJob bitmapCroppingWorkerJob = this.this$0;
                Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                this.label = 2;
                if (this.this$0.onPostExecute(new BitmapCroppingWorkerJob.Result(e, false), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.canhub.cropper.BitmapCroppingWorkerJob$start$1$1", f = "BitmapCroppingWorkerJob.kt", i = {}, l = {93}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.canhub.cropper.BitmapCroppingWorkerJob$start$1$1  reason: invalid class name */
    /* compiled from: BitmapCroppingWorkerJob.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(bitmapCroppingWorkerJob, resizeBitmap, bitmapSampled, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Uri writeBitmapToUri = BitmapUtils.INSTANCE.writeBitmapToUri(bitmapCroppingWorkerJob.context, resizeBitmap, bitmapCroppingWorkerJob.saveCompressFormat, bitmapCroppingWorkerJob.saveCompressQuality, bitmapCroppingWorkerJob.customOutputUri);
                resizeBitmap.recycle();
                this.label = 1;
                if (bitmapCroppingWorkerJob.onPostExecute(new BitmapCroppingWorkerJob.Result(writeBitmapToUri, bitmapSampled.getSampleSize()), this) == coroutine_suspended) {
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
}
