package com.adsbynimbus.render.internal;

import com.adsbynimbus.Nimbus;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.AdEvent;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.internal.AdTrackersKt$trackEvent$2$1$1", f = "AdTrackers.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AdTrackers.kt */
final class AdTrackersKt$trackEvent$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AdEvent $adEvent;
    final /* synthetic */ Function1<String, HttpURLConnection> $connectionProvider;
    final /* synthetic */ String $it;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdTrackersKt$trackEvent$2$1$1(AdEvent adEvent, String str, Function1<? super String, ? extends HttpURLConnection> function1, Continuation<? super AdTrackersKt$trackEvent$2$1$1> continuation) {
        super(2, continuation);
        this.$adEvent = adEvent;
        this.$it = str;
        this.$connectionProvider = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AdTrackersKt$trackEvent$2$1$1 adTrackersKt$trackEvent$2$1$1 = new AdTrackersKt$trackEvent$2$1$1(this.$adEvent, this.$it, this.$connectionProvider, continuation);
        adTrackersKt$trackEvent$2$1$1.L$0 = obj;
        return adTrackersKt$trackEvent$2$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdTrackersKt$trackEvent$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Integer num;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Function1<String, HttpURLConnection> function1 = this.$connectionProvider;
            String str = this.$it;
            AdEvent adEvent = this.$adEvent;
            try {
                Result.Companion companion = Result.Companion;
                HttpURLConnection invoke = function1.invoke(str);
                HttpURLConnection httpURLConnection = invoke;
                httpURLConnection.setConnectTimeout(5000);
                if (adEvent == AdEvent.CLICKED) {
                    httpURLConnection.setRequestProperty("Nimbus-Session-Id", Nimbus.sessionId);
                }
                num = Result.m2444constructorimpl(Boxing.boxInt(invoke.getResponseCode()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                num = Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
            boolean z = false;
            Integer boxInt = Boxing.boxInt(0);
            if (Result.m2450isFailureimpl(num)) {
                num = boxInt;
            }
            int intValue = ((Number) num).intValue();
            if (200 <= intValue && intValue < 400) {
                z = true;
            }
            if (z) {
                Logger.log(2, "Successfully fired " + this.$adEvent.name() + " event tracker [" + this.$it + AbstractJsonLexerKt.END_LIST);
            } else {
                Logger.log(5, "Error firing " + this.$adEvent.name() + " event tracker [" + this.$it + AbstractJsonLexerKt.END_LIST);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
