package com.adsbynimbus;

import android.app.Activity;
import android.content.Context;
import com.adsbynimbus.NimbusAdManager;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.BlockingAdRenderer;
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
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.NimbusAdManager$showBlockingAd$1", f = "NimbusAdManager.kt", i = {}, l = {135}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NimbusAdManager.kt */
final class NimbusAdManager$showBlockingAd$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ int $closeButtonDelaySeconds;
    final /* synthetic */ int $closeDelayAfterCompleteSeconds;
    final /* synthetic */ NimbusAdManager.Listener $listener;
    final /* synthetic */ NimbusRequest $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NimbusAdManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NimbusAdManager$showBlockingAd$1(NimbusRequest nimbusRequest, NimbusAdManager nimbusAdManager, Activity activity, NimbusAdManager.Listener listener, int i, int i2, Continuation<? super NimbusAdManager$showBlockingAd$1> continuation) {
        super(2, continuation);
        this.$request = nimbusRequest;
        this.this$0 = nimbusAdManager;
        this.$activity = activity;
        this.$listener = listener;
        this.$closeButtonDelaySeconds = i;
        this.$closeDelayAfterCompleteSeconds = i2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NimbusAdManager$showBlockingAd$1 nimbusAdManager$showBlockingAd$1 = new NimbusAdManager$showBlockingAd$1(this.$request, this.this$0, this.$activity, this.$listener, this.$closeButtonDelaySeconds, this.$closeDelayAfterCompleteSeconds, continuation);
        nimbusAdManager$showBlockingAd$1.L$0 = obj;
        return nimbusAdManager$showBlockingAd$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NimbusAdManager$showBlockingAd$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            Activity activity = this.$activity;
            NimbusRequest nimbusRequest = this.$request;
            Result.Companion companion = Result.Companion;
            this.label = 1;
            obj = nimbusAdManager.makeRequest((Context) activity, nimbusRequest, (Continuation<? super NimbusResponse>) this);
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
        NimbusRequest nimbusRequest2 = this.$request;
        int i2 = this.$closeButtonDelaySeconds;
        int i3 = this.$closeDelayAfterCompleteSeconds;
        Activity activity2 = this.$activity;
        if (Result.m2451isSuccessimpl(obj2)) {
            NimbusResponse nimbusResponse = (NimbusResponse) obj2;
            listener2.onAdResponse(nimbusResponse);
            nimbusResponse.companionAds = nimbusRequest2.getCompanionAds();
            BlockingAdRenderer.setsCloseButtonDelayRender(RangesKt.coerceIn(i2 * 1000, 0, 3600000));
            BlockingAdRenderer.setDelayAfterComplete(i3);
            AdController loadBlockingAd = Renderer.Companion.loadBlockingAd((Context) activity2, (NimbusAd) nimbusResponse);
            if (loadBlockingAd == null) {
                listener2.onError(new NimbusError(NimbusError.ErrorType.RENDERER_ERROR, "No renderer installed for blocking " + nimbusResponse.network() + ' ' + nimbusResponse.type(), (Throwable) null));
            } else {
                listener2.onAdRendered(loadBlockingAd);
                loadBlockingAd.start();
            }
        }
        return Unit.INSTANCE;
    }
}
