package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u0001*\u00020\u00072\u0010\u0010\b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\tH@"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "P3", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "expo.modules.kotlin.functions.AsyncFunctionBuilder$SuspendBody$19", f = "AsyncFunctionBuilder.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AsyncFunctionBuilder.kt */
public final class AsyncFunctionBuilder$SuspendBody$19 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function5<P0, P1, P2, P3, Continuation<? super R>, Object> $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsyncFunctionBuilder$SuspendBody$19(Function5<? super P0, ? super P1, ? super P2, ? super P3, ? super Continuation<? super R>, ? extends Object> function5, Continuation<? super AsyncFunctionBuilder$SuspendBody$19> continuation) {
        super(3, continuation);
        this.$block = function5;
    }

    public final Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilder$SuspendBody$19 asyncFunctionBuilder$SuspendBody$19 = new AsyncFunctionBuilder$SuspendBody$19(this.$block, continuation);
        asyncFunctionBuilder$SuspendBody$19.L$0 = objArr;
        return asyncFunctionBuilder$SuspendBody$19.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Function5<P0, P1, P2, P3, Continuation<? super R>, Object> function5 = this.$block;
            Object obj2 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P0");
            Object obj3 = obj2;
            Object obj4 = objArr[1];
            Intrinsics.reifiedOperationMarker(1, "P1");
            Object obj5 = obj4;
            Object obj6 = objArr[2];
            Intrinsics.reifiedOperationMarker(1, "P2");
            Object obj7 = obj6;
            Object obj8 = objArr[3];
            Intrinsics.reifiedOperationMarker(1, "P3");
            Object obj9 = obj8;
            this.label = 1;
            obj = function5.invoke(obj2, obj4, obj6, obj8, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Object[] objArr = (Object[]) this.L$0;
        Function5<P0, P1, P2, P3, Continuation<? super R>, Object> function5 = this.$block;
        Object obj2 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj3 = obj2;
        Object obj4 = objArr[1];
        Intrinsics.reifiedOperationMarker(1, "P1");
        Object obj5 = obj4;
        Object obj6 = objArr[2];
        Intrinsics.reifiedOperationMarker(1, "P2");
        Object obj7 = obj6;
        Object obj8 = objArr[3];
        Intrinsics.reifiedOperationMarker(1, "P3");
        Object obj9 = obj8;
        return function5.invoke(obj2, obj4, obj6, obj8, this);
    }
}
