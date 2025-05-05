package expo.modules.kotlin.devtools;

import expo.modules.kotlin.devtools.cdp.Event;
import expo.modules.kotlin.devtools.cdp.ExpoReceivedResponseBodyParams;
import expo.modules.kotlin.devtools.cdp.LoadingFinishedParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentExtraInfoParams;
import expo.modules.kotlin.devtools.cdp.RequestWillBeSentParams;
import expo.modules.kotlin.devtools.cdp.ResponseReceivedParams;
import io.sentry.SentryBaseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\"\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor;", "Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpInterceptorsDelegate;", "()V", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope$expo_modules_core_release", "()Lkotlinx/coroutines/CoroutineScope;", "setCoroutineScope$expo_modules_core_release", "(Lkotlinx/coroutines/CoroutineScope;)V", "delegate", "Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "didReceiveResponse", "", "requestId", "", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "dispatchEvent", "event", "Lexpo/modules/kotlin/devtools/cdp/Event;", "setDelegate", "willSendRequest", "redirectResponse", "Delegate", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoRequestCdpInterceptor.kt */
public final class ExpoRequestCdpInterceptor implements ExpoNetworkInspectOkHttpInterceptorsDelegate {
    public static final ExpoRequestCdpInterceptor INSTANCE = new ExpoRequestCdpInterceptor();
    private static CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    /* access modifiers changed from: private */
    public static Delegate delegate;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoRequestCdpInterceptor$Delegate;", "", "dispatch", "", "event", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExpoRequestCdpInterceptor.kt */
    public interface Delegate {
        void dispatch(String str);
    }

    private ExpoRequestCdpInterceptor() {
    }

    public final CoroutineScope getCoroutineScope$expo_modules_core_release() {
        return coroutineScope;
    }

    public final void setCoroutineScope$expo_modules_core_release(CoroutineScope coroutineScope2) {
        Intrinsics.checkNotNullParameter(coroutineScope2, "<set-?>");
        coroutineScope = coroutineScope2;
    }

    public final void setDelegate(Delegate delegate2) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ExpoRequestCdpInterceptor$setDelegate$1(this, delegate2, (Continuation<? super ExpoRequestCdpInterceptor$setDelegate$1>) null), 3, (Object) null);
    }

    private final void dispatchEvent(Event event) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ExpoRequestCdpInterceptor$dispatchEvent$1(event, (Continuation<? super ExpoRequestCdpInterceptor$dispatchEvent$1>) null), 3, (Object) null);
    }

    public void willSendRequest(String str, Request request, Response response) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(request, SentryBaseEvent.JsonKeys.REQUEST);
        BigDecimal scale = new BigDecimal(((double) System.currentTimeMillis()) / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.requestWillBeSent", new RequestWillBeSentParams(scale, str, request, response)));
        dispatchEvent(new Event("Network.requestWillBeSentExtraInfo", new RequestWillBeSentExtraInfoParams(scale, str, request)));
    }

    public void didReceiveResponse(String str, Request request, Response response) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(request, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(response, io.sentry.protocol.Response.TYPE);
        BigDecimal scale = new BigDecimal(((double) System.currentTimeMillis()) / 1000.0d).setScale(3, RoundingMode.CEILING);
        Intrinsics.checkNotNull(scale);
        dispatchEvent(new Event("Network.responseReceived", new ResponseReceivedParams(scale, str, request, response)));
        if (response.peekBody(1048577).contentLength() <= 1048576) {
            dispatchEvent(new Event("Expo(Network.receivedResponseBody)", new ExpoReceivedResponseBodyParams(scale, str, request, response)));
        }
        dispatchEvent(new Event("Network.loadingFinished", new LoadingFinishedParams(scale, str, request, response)));
    }
}
