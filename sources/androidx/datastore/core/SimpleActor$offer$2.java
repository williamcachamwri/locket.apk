package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.SimpleActor$offer$2", f = "SimpleActor.kt", i = {}, l = {122, 122}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SimpleActor.kt */
final class SimpleActor$offer$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ SimpleActor<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleActor$offer$2(SimpleActor<T> simpleActor, Continuation<? super SimpleActor$offer$2> continuation) {
        super(2, continuation);
        this.this$0 = simpleActor;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SimpleActor$offer$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SimpleActor$offer$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            goto L_0x0072
        L_0x0014:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001c:
            java.lang.Object r1 = r7.L$0
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r1
            r1 = r0
            r0 = r7
            goto L_0x0064
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.datastore.core.SimpleActor<T> r8 = r7.this$0
            java.util.concurrent.atomic.AtomicInteger r8 = r8.remainingMessages
            int r8 = r8.get()
            if (r8 <= 0) goto L_0x0038
            r8 = r3
            goto L_0x0039
        L_0x0038:
            r8 = 0
        L_0x0039:
            if (r8 == 0) goto L_0x0081
            r8 = r7
        L_0x003c:
            androidx.datastore.core.SimpleActor<T> r1 = r8.this$0
            kotlinx.coroutines.CoroutineScope r1 = r1.scope
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r1)
            androidx.datastore.core.SimpleActor<T> r1 = r8.this$0
            kotlin.jvm.functions.Function2 r1 = r1.consumeMessage
            androidx.datastore.core.SimpleActor<T> r4 = r8.this$0
            kotlinx.coroutines.channels.Channel r4 = r4.messageQueue
            r5 = r8
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r4 = r4.receive(r5)
            if (r4 != r0) goto L_0x005f
            return r0
        L_0x005f:
            r6 = r0
            r0 = r8
            r8 = r4
            r4 = r1
            r1 = r6
        L_0x0064:
            r5 = 0
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r8 = r4.invoke(r8, r0)
            if (r8 != r1) goto L_0x0070
            return r1
        L_0x0070:
            r8 = r0
            r0 = r1
        L_0x0072:
            androidx.datastore.core.SimpleActor<T> r1 = r8.this$0
            java.util.concurrent.atomic.AtomicInteger r1 = r1.remainingMessages
            int r1 = r1.decrementAndGet()
            if (r1 != 0) goto L_0x003c
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0081:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.SimpleActor$offer$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
