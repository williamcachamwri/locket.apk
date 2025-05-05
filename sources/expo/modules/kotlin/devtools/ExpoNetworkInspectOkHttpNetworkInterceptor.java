package expo.modules.kotlin.devtools;

import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpNetworkInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
public final class ExpoNetworkInspectOkHttpNetworkInterceptor implements Interceptor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long MAX_BODY_SIZE = 1048576;

    public Response intercept(Interceptor.Chain chain) {
        String str;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        try {
            RedirectResponse redirectResponse = (RedirectResponse) request.tag(RedirectResponse.class);
            if (redirectResponse == null || (str = redirectResponse.getRequestId()) == null) {
                str = String.valueOf(request.hashCode());
            }
            ExpoNetworkInspectOkHttpInterceptorsKt.getDelegate().willSendRequest(str, request, redirectResponse != null ? redirectResponse.getPriorResponse() : null);
            if (proceed.isRedirect()) {
                RedirectResponse redirectResponse2 = (RedirectResponse) proceed.request().tag(RedirectResponse.class);
                if (redirectResponse2 != null) {
                    redirectResponse2.setRequestId(str);
                    redirectResponse2.setPriorResponse(proceed);
                }
            } else {
                ExpoNetworkInspectOkHttpInterceptorsKt.getDelegate().didReceiveResponse(str, request, proceed);
            }
        } catch (Exception e) {
            SentryLogcatAdapter.e("ExpoNetworkInspector", "Failed to send network request CDP event", e);
        }
        return proceed;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lexpo/modules/kotlin/devtools/ExpoNetworkInspectOkHttpNetworkInterceptor$Companion;", "", "()V", "MAX_BODY_SIZE", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExpoNetworkInspectOkHttpInterceptors.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
