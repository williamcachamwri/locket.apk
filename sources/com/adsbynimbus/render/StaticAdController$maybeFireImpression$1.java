package com.adsbynimbus.render;

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
@DebugMetadata(c = "com.adsbynimbus.render.StaticAdController$maybeFireImpression$1", f = "StaticAdController.kt", i = {}, l = {125}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StaticAdController.kt */
final class StaticAdController$maybeFireImpression$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ StaticAdController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticAdController$maybeFireImpression$1(StaticAdController staticAdController, Continuation<? super StaticAdController$maybeFireImpression$1> continuation) {
        super(2, continuation);
        this.this$0 = staticAdController;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StaticAdController$maybeFireImpression$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StaticAdController$maybeFireImpression$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay((long) this.this$0.getCompletionTimeout(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.this$0.dispatchAdEvent(AdEvent.COMPLETED);
        return Unit.INSTANCE;
    }
}
