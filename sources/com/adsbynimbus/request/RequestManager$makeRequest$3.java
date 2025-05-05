package com.adsbynimbus.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lcom/adsbynimbus/request/NimbusResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.request.RequestManager$makeRequest$3", f = "RequestManager.kt", i = {}, l = {53, 52}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RequestManager.kt */
final class RequestManager$makeRequest$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NimbusResponse>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ NimbusRequest $request;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RequestManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestManager$makeRequest$3(NimbusRequest nimbusRequest, RequestManager requestManager, Context context, Continuation<? super RequestManager$makeRequest$3> continuation) {
        super(2, continuation);
        this.$request = nimbusRequest;
        this.this$0 = requestManager;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RequestManager$makeRequest$3 requestManager$makeRequest$3 = new RequestManager$makeRequest$3(this.$request, this.this$0, this.$context, continuation);
        requestManager$makeRequest$3.L$0 = obj;
        return requestManager$makeRequest$3;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NimbusResponse> continuation) {
        return ((RequestManager$makeRequest$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x001f }
            goto L_0x009b
        L_0x0013:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x001f }
            goto L_0x0090
        L_0x001f:
            r14 = move-exception
            goto L_0x00a2
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            kotlinx.coroutines.CoroutineScope r14 = (kotlinx.coroutines.CoroutineScope) r14
            com.adsbynimbus.request.NimbusRequest r14 = r13.$request
            com.adsbynimbus.request.RequestManager r1 = r13.this$0
            java.lang.String r1 = r1.getApiKey()
            r14.setApiKey$request_release(r1)
            com.adsbynimbus.request.NimbusRequest r14 = r13.$request
            java.lang.String r14 = r14.getRequestUrl()
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 != 0) goto L_0x0044
            r14 = r3
            goto L_0x0045
        L_0x0044:
            r14 = 0
        L_0x0045:
            if (r14 == 0) goto L_0x0067
            com.adsbynimbus.request.NimbusRequest r14 = r13.$request
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "https://"
            r1.<init>(r4)
            com.adsbynimbus.request.RequestManager r4 = r13.this$0
            java.lang.String r4 = r4.getPublisherKey()
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r4 = ".adsbynimbus.com/rta/v1"
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r14.setRequestUrl(r1)
        L_0x0067:
            com.adsbynimbus.request.NimbusRequest r4 = r13.$request
            android.content.Context r5 = r13.$context
            kotlin.Result$Companion r14 = kotlin.Result.Companion     // Catch:{ all -> 0x001f }
            java.lang.String r6 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x001f }
            java.lang.String r14 = "MANUFACTURER"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r14)     // Catch:{ all -> 0x001f }
            java.lang.String r7 = android.os.Build.MODEL     // Catch:{ all -> 0x001f }
            java.lang.String r14 = "MODEL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r14)     // Catch:{ all -> 0x001f }
            java.lang.String r8 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x001f }
            java.lang.String r14 = "RELEASE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r14)     // Catch:{ all -> 0x001f }
            r9 = 0
            r11 = 16
            r12 = 0
            r13.label = r3     // Catch:{ all -> 0x001f }
            r10 = r13
            java.lang.Object r14 = com.adsbynimbus.request.RequestExtensions.build$default(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x001f }
            if (r14 != r0) goto L_0x0090
            return r0
        L_0x0090:
            com.adsbynimbus.request.NimbusRequest r14 = (com.adsbynimbus.request.NimbusRequest) r14     // Catch:{ all -> 0x001f }
            r13.label = r2     // Catch:{ all -> 0x001f }
            java.lang.Object r14 = com.adsbynimbus.request.RequestExtensions.makeRequestInternal(r14, r13)     // Catch:{ all -> 0x001f }
            if (r14 != r0) goto L_0x009b
            return r0
        L_0x009b:
            com.adsbynimbus.request.NimbusResponse r14 = (com.adsbynimbus.request.NimbusResponse) r14     // Catch:{ all -> 0x001f }
            java.lang.Object r14 = kotlin.Result.m2444constructorimpl(r14)     // Catch:{ all -> 0x001f }
            goto L_0x00ac
        L_0x00a2:
            kotlin.Result$Companion r0 = kotlin.Result.Companion
            java.lang.Object r14 = kotlin.ResultKt.createFailure(r14)
            java.lang.Object r14 = kotlin.Result.m2444constructorimpl(r14)
        L_0x00ac:
            com.adsbynimbus.request.NimbusRequest r0 = r13.$request
            boolean r1 = kotlin.Result.m2451isSuccessimpl(r14)
            if (r1 == 0) goto L_0x00d1
            r1 = r14
            com.adsbynimbus.request.NimbusResponse r1 = (com.adsbynimbus.request.NimbusResponse) r1
            java.util.Set r0 = r0.getInterceptors()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x00c1:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00d1
            java.lang.Object r2 = r0.next()
            com.adsbynimbus.request.NimbusRequest$Interceptor r2 = (com.adsbynimbus.request.NimbusRequest.Interceptor) r2
            r2.onAdResponse(r1)
            goto L_0x00c1
        L_0x00d1:
            com.adsbynimbus.request.NimbusRequest r0 = r13.$request
            java.lang.Throwable r1 = kotlin.Result.m2447exceptionOrNullimpl(r14)
            if (r1 == 0) goto L_0x0102
            java.util.Set r0 = r0.getInterceptors()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x00e3:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0102
            java.lang.Object r2 = r0.next()
            com.adsbynimbus.request.NimbusRequest$Interceptor r2 = (com.adsbynimbus.request.NimbusRequest.Interceptor) r2
            boolean r3 = r1 instanceof com.adsbynimbus.NimbusError
            if (r3 == 0) goto L_0x00f7
            r3 = r1
            com.adsbynimbus.NimbusError r3 = (com.adsbynimbus.NimbusError) r3
            goto L_0x00f8
        L_0x00f7:
            r3 = 0
        L_0x00f8:
            if (r3 != 0) goto L_0x00fe
            com.adsbynimbus.NimbusError r3 = com.adsbynimbus.request.RequestExtensions.getWrappedNetworkError(r1)
        L_0x00fe:
            r2.onError(r3)
            goto L_0x00e3
        L_0x0102:
            kotlin.ResultKt.throwOnFailure(r14)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.RequestManager$makeRequest$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
