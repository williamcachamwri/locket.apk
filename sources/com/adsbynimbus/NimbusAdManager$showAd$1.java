package com.adsbynimbus;

import android.content.Context;
import android.view.ViewGroup;
import com.adsbynimbus.NimbusAdManager;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.Renderer;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.NimbusResponse;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.NimbusAdManager$showAd$1", f = "NimbusAdManager.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NimbusAdManager.kt */
final class NimbusAdManager$showAd$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NimbusAdManager.Listener $listener;
    final /* synthetic */ NimbusRequest $request;
    final /* synthetic */ ViewGroup $viewGroup;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NimbusAdManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NimbusAdManager$showAd$1(NimbusRequest nimbusRequest, NimbusAdManager nimbusAdManager, ViewGroup viewGroup, NimbusAdManager.Listener listener, Continuation<? super NimbusAdManager$showAd$1> continuation) {
        super(2, continuation);
        this.$request = nimbusRequest;
        this.this$0 = nimbusAdManager;
        this.$viewGroup = viewGroup;
        this.$listener = listener;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NimbusAdManager$showAd$1 nimbusAdManager$showAd$1 = new NimbusAdManager$showAd$1(this.$request, this.this$0, this.$viewGroup, this.$listener, continuation);
        nimbusAdManager$showAd$1.L$0 = obj;
        return nimbusAdManager$showAd$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NimbusAdManager$showAd$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (Nimbus.getThirdPartyViewabilityEnabled()) {
                this.$request.configureViewability(Nimbus.sdkName, "2.26.1");
            }
            NimbusAdManager nimbusAdManager = this.this$0;
            ViewGroup viewGroup = this.$viewGroup;
            NimbusRequest nimbusRequest = this.$request;
            Result.Companion companion = Result.Companion;
            Context context = viewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "viewGroup.context");
            this.label = 1;
            obj = nimbusAdManager.makeRequest(context, nimbusRequest, (Continuation<? super NimbusResponse>) this);
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
        NimbusAdManager.Listener listener = this.$listener;
        Throwable r1 = Result.m2447exceptionOrNullimpl(obj2);
        if (r1 != null) {
            NimbusError nimbusError = r1 instanceof NimbusError ? (NimbusError) r1 : null;
            if (nimbusError == null) {
                NimbusError.ErrorType errorType = NimbusError.ErrorType.NETWORK_ERROR;
                String message = r1.getMessage();
                if (message == null) {
                    message = "";
                }
                nimbusError = new NimbusError(errorType, message, r1);
            }
            listener.onError(nimbusError);
        }
        NimbusAdManager.Listener listener2 = this.$listener;
        ViewGroup viewGroup2 = this.$viewGroup;
        NimbusRequest nimbusRequest2 = this.$request;
        if (Result.m2451isSuccessimpl(obj2)) {
            NimbusResponse nimbusResponse = (NimbusResponse) obj2;
            listener2.onAdResponse(nimbusResponse);
            Renderer.Companion companion3 = Renderer.Companion;
            nimbusResponse.companionAds = nimbusRequest2.getCompanionAds();
            companion3.loadAd(nimbusResponse, viewGroup2, listener2);
        }
        return Unit.INSTANCE;
    }
}
