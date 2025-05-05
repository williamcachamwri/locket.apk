package com.adsbynimbus.render;

import com.adsbynimbus.internal.Logger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.BlockingAdController$start$2", f = "BlockingAdController.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BlockingAdController.kt */
final class BlockingAdController$start$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ BlockingAdController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BlockingAdController$start$2(BlockingAdController blockingAdController, Continuation<? super BlockingAdController$start$2> continuation) {
        super(2, continuation);
        this.this$0 = blockingAdController;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BlockingAdController$start$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BlockingAdController$start$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.retries--;
            this.label = 1;
            if (DelayKt.delay(64, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.start();
        Logger.log(3, "Retrying start() for Nimbus Ad: " + this.this$0.ad.position());
        return Unit.INSTANCE;
    }
}
