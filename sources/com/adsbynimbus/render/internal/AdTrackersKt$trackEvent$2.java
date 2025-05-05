package com.adsbynimbus.render.internal;

import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.render.AdEvent;
import java.net.HttpURLConnection;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.internal.AdTrackersKt$trackEvent$2", f = "AdTrackers.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AdTrackers.kt */
final class AdTrackersKt$trackEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AdEvent $adEvent;
    final /* synthetic */ Function1<String, HttpURLConnection> $connectionProvider;
    final /* synthetic */ NimbusAd $this_trackEvent;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AdTrackersKt$trackEvent$2(NimbusAd nimbusAd, AdEvent adEvent, Function1<? super String, ? extends HttpURLConnection> function1, Continuation<? super AdTrackersKt$trackEvent$2> continuation) {
        super(2, continuation);
        this.$this_trackEvent = nimbusAd;
        this.$adEvent = adEvent;
        this.$connectionProvider = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AdTrackersKt$trackEvent$2 adTrackersKt$trackEvent$2 = new AdTrackersKt$trackEvent$2(this.$this_trackEvent, this.$adEvent, this.$connectionProvider, continuation);
        adTrackersKt$trackEvent$2.L$0 = obj;
        return adTrackersKt$trackEvent$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdTrackersKt$trackEvent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Collection<String> trackersForEvent = this.$this_trackEvent.trackersForEvent(this.$adEvent);
            if (trackersForEvent != null) {
                AdEvent adEvent = this.$adEvent;
                Function1<String, HttpURLConnection> function1 = this.$connectionProvider;
                for (String adTrackersKt$trackEvent$2$1$1 : trackersForEvent) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new AdTrackersKt$trackEvent$2$1$1(adEvent, adTrackersKt$trackEvent$2$1$1, function1, (Continuation<? super AdTrackersKt$trackEvent$2$1$1>) null), 2, (Object) null);
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
