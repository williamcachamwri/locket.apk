package expo.modules.kotlin.functions;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001*\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\bH@¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "Lkotlinx/coroutines/CoroutineScope;", "it", "", "expo/modules/kotlin/functions/AsyncFunctionBuilder$SuspendBody$13"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "expo.modules.kotlin.functions.AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9", f = "AsyncFunctionBuilder.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AsyncFunctionBuilder.kt */
public final class AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9 extends SuspendLambda implements Function3<CoroutineScope, Object[], Continuation<? super Object>, Object> {
    final /* synthetic */ Function4 $block;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9(Function4 function4, Continuation continuation) {
        super(3, continuation);
        this.$block = function4;
    }

    public final Object invoke(CoroutineScope coroutineScope, Object[] objArr, Continuation<Object> continuation) {
        AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9 asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9 = new AsyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9(this.$block, continuation);
        asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9.L$0 = objArr;
        return asyncFunctionBuilderKt$Coroutine$$inlined$SuspendBody$9.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object[] objArr = (Object[]) this.L$0;
            Function4 function4 = this.$block;
            Object obj2 = objArr[0];
            Intrinsics.reifiedOperationMarker(1, "P0");
            Object obj3 = obj2;
            Object obj4 = objArr[1];
            Intrinsics.reifiedOperationMarker(1, "P1");
            Object obj5 = obj4;
            Object obj6 = objArr[2];
            Intrinsics.reifiedOperationMarker(1, "P2");
            Object obj7 = obj6;
            this.label = 1;
            obj = function4.invoke(obj2, obj4, obj6, this);
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
        Function4 function4 = this.$block;
        Object obj2 = objArr[0];
        Intrinsics.reifiedOperationMarker(1, "P0");
        Object obj3 = obj2;
        Object obj4 = objArr[1];
        Intrinsics.reifiedOperationMarker(1, "P1");
        Object obj5 = obj4;
        Object obj6 = objArr[2];
        Intrinsics.reifiedOperationMarker(1, "P2");
        Object obj7 = obj6;
        return function4.invoke(obj2, obj4, obj6, this);
    }
}
