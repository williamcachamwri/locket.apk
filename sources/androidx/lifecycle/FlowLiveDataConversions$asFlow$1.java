package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", f = "FlowLiveData.kt", i = {0, 1, 2}, l = {107, 112, 113, 115}, m = "invokeSuspend", n = {"observer", "observer", "observer"}, s = {"L$0", "L$0", "L$0"})
/* compiled from: FlowLiveData.kt */
final class FlowLiveDataConversions$asFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ LiveData<T> $this_asFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowLiveDataConversions$asFlow$1(LiveData<T> liveData, Continuation<? super FlowLiveDataConversions$asFlow$1> continuation) {
        super(2, continuation);
        this.$this_asFlow = liveData;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowLiveDataConversions$asFlow$1 flowLiveDataConversions$asFlow$1 = new FlowLiveDataConversions$asFlow$1(this.$this_asFlow, continuation);
        flowLiveDataConversions$asFlow$1.L$0 = obj;
        return flowLiveDataConversions$asFlow$1;
    }

    public final Object invoke(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
        return ((FlowLiveDataConversions$asFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009b A[Catch:{ all -> 0x0036 }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r1 == 0) goto L_0x0040
            if (r1 == r5) goto L_0x0038
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x0026
            if (r1 == r2) goto L_0x001d
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x001d:
            java.lang.Object r0 = r9.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ca
        L_0x0026:
            java.lang.Object r1 = r9.L$0
            androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0036 }
            goto L_0x009c
        L_0x002e:
            java.lang.Object r1 = r9.L$0
            androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
            kotlin.ResultKt.throwOnFailure(r10)     // Catch:{ all -> 0x0036 }
            goto L_0x008e
        L_0x0036:
            r10 = move-exception
            goto L_0x00a2
        L_0x0038:
            java.lang.Object r1 = r9.L$0
            androidx.lifecycle.Observer r1 = (androidx.lifecycle.Observer) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x006d
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.channels.ProducerScope r10 = (kotlinx.coroutines.channels.ProducerScope) r10
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0 r1 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$$ExternalSyntheticLambda0
            r1.<init>(r10)
            kotlinx.coroutines.MainCoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r10 = r10.getImmediate()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1 r7 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1
            androidx.lifecycle.LiveData<T> r8 = r9.$this_asFlow
            r7.<init>(r8, r1, r6)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r8 = r9
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r9.L$0 = r1
            r9.label = r5
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r7, r8)
            if (r10 != r0) goto L_0x006d
            return r0
        L_0x006d:
            kotlinx.coroutines.MainCoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getMain()     // Catch:{ all -> 0x0036 }
            kotlinx.coroutines.MainCoroutineDispatcher r10 = r10.getImmediate()     // Catch:{ all -> 0x0036 }
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10     // Catch:{ all -> 0x0036 }
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2 r5 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2     // Catch:{ all -> 0x0036 }
            androidx.lifecycle.LiveData<T> r7 = r9.$this_asFlow     // Catch:{ all -> 0x0036 }
            r5.<init>(r7, r1, r6)     // Catch:{ all -> 0x0036 }
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5     // Catch:{ all -> 0x0036 }
            r7 = r9
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch:{ all -> 0x0036 }
            r9.L$0 = r1     // Catch:{ all -> 0x0036 }
            r9.label = r4     // Catch:{ all -> 0x0036 }
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r5, r7)     // Catch:{ all -> 0x0036 }
            if (r10 != r0) goto L_0x008e
            return r0
        L_0x008e:
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch:{ all -> 0x0036 }
            r9.L$0 = r1     // Catch:{ all -> 0x0036 }
            r9.label = r3     // Catch:{ all -> 0x0036 }
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.awaitCancellation(r10)     // Catch:{ all -> 0x0036 }
            if (r10 != r0) goto L_0x009c
            return r0
        L_0x009c:
            kotlin.KotlinNothingValueException r10 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0036 }
            r10.<init>()     // Catch:{ all -> 0x0036 }
            throw r10     // Catch:{ all -> 0x0036 }
        L_0x00a2:
            kotlinx.coroutines.MainCoroutineDispatcher r3 = kotlinx.coroutines.Dispatchers.getMain()
            kotlinx.coroutines.MainCoroutineDispatcher r3 = r3.getImmediate()
            kotlinx.coroutines.NonCancellable r4 = kotlinx.coroutines.NonCancellable.INSTANCE
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            kotlin.coroutines.CoroutineContext r3 = r3.plus(r4)
            androidx.lifecycle.FlowLiveDataConversions$asFlow$1$3 r4 = new androidx.lifecycle.FlowLiveDataConversions$asFlow$1$3
            androidx.lifecycle.LiveData<T> r5 = r9.$this_asFlow
            r4.<init>(r5, r1, r6)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r9.L$0 = r10
            r9.label = r2
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r3, r4, r1)
            if (r1 != r0) goto L_0x00c9
            return r0
        L_0x00c9:
            r0 = r10
        L_0x00ca:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.FlowLiveDataConversions$asFlow$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(ProducerScope producerScope, Object obj) {
        producerScope.m1826trySendJP2dKIU(obj);
    }
}
