package com.adsbynimbus.request;

import com.adsbynimbus.NimbusError;
import com.adsbynimbus.request.RequestManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/adsbynimbus/request/RequestExtensions$makeRequestInternal$2$1", "Lcom/adsbynimbus/request/RequestManager$Listener;", "onAdResponse", "", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestExtensions.kt */
public final class RequestExtensions$makeRequestInternal$2$1 implements RequestManager.Listener {
    final /* synthetic */ Continuation<NimbusResponse> $it;

    RequestExtensions$makeRequestInternal$2$1(Continuation<? super NimbusResponse> continuation) {
        this.$it = continuation;
    }

    public void onAdResponse(NimbusResponse nimbusResponse) {
        Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
        Continuation<NimbusResponse> continuation = this.$it;
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2444constructorimpl(nimbusResponse));
    }

    public void onError(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        Continuation<NimbusResponse> continuation = this.$it;
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(nimbusError)));
    }
}
