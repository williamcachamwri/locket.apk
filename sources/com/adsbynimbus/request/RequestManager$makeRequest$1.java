package com.adsbynimbus.request;

import android.content.Context;
import com.adsbynimbus.NimbusError;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\f\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.request.RequestManager$makeRequest$1", f = "RequestManager.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RequestManager.kt */
final class RequestManager$makeRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ T $listener;
    final /* synthetic */ NimbusRequest $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RequestManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestManager$makeRequest$1(RequestManager requestManager, Context context, NimbusRequest nimbusRequest, T t, Continuation<? super RequestManager$makeRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = requestManager;
        this.$context = context;
        this.$request = nimbusRequest;
        this.$listener = t;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RequestManager$makeRequest$1 requestManager$makeRequest$1 = new RequestManager$makeRequest$1(this.this$0, this.$context, this.$request, this.$listener, continuation);
        requestManager$makeRequest$1.L$0 = obj;
        return requestManager$makeRequest$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RequestManager$makeRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            RequestManager requestManager = this.this$0;
            Context context = this.$context;
            NimbusRequest nimbusRequest = this.$request;
            Result.Companion companion = Result.Companion;
            this.label = 1;
            obj = requestManager.makeRequest(context, nimbusRequest, (Continuation<? super NimbusResponse>) this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        obj2 = Result.m2444constructorimpl((NimbusResponse) obj);
        T t = this.$listener;
        if (Result.m2451isSuccessimpl(obj2)) {
            t.onAdResponse((NimbusResponse) obj2);
        }
        T t2 = this.$listener;
        Throwable r6 = Result.m2447exceptionOrNullimpl(obj2);
        if (r6 != null) {
            NimbusError.Listener listener = (NimbusError.Listener) t2;
            NimbusError nimbusError = r6 instanceof NimbusError ? (NimbusError) r6 : null;
            if (nimbusError == null) {
                nimbusError = RequestExtensions.getWrappedNetworkError(r6);
            }
            listener.onError(nimbusError);
        }
        return Unit.INSTANCE;
    }
}
