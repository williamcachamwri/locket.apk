package com.adsbynimbus.request;

import android.content.Context;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Data;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.NimbusResponse;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000 \u00162\u00020\u0001:\u0003\u0015\u0016\u0017J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH@¢\u0006\u0002\u0010\u000eJ3\u0010\b\u001a\u00020\u000f\"\f\b\u0000\u0010\u0010*\u00020\u0011*\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u0002H\u0010H\u0016¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\u0018"}, d2 = {"Lcom/adsbynimbus/request/RequestManager;", "", "apiKey", "", "getApiKey", "()Ljava/lang/String;", "publisherKey", "getPublisherKey", "makeRequest", "Lcom/adsbynimbus/request/NimbusResponse;", "context", "Landroid/content/Context;", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "(Landroid/content/Context;Lcom/adsbynimbus/request/NimbusRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "T", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "listener", "(Landroid/content/Context;Lcom/adsbynimbus/request/NimbusRequest;Lcom/adsbynimbus/request/NimbusResponse$Listener;)V", "Client", "Companion", "Listener", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestManager.kt */
public interface RequestManager {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final CopyOnWriteArraySet<NimbusRequest.Interceptor> interceptors = new CopyOnWriteArraySet<>();

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/adsbynimbus/request/RequestManager$Listener;", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "onError", "", "error", "Lcom/adsbynimbus/NimbusError;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestManager.kt */
    public interface Listener extends NimbusResponse.Listener, NimbusError.Listener {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: RequestManager.kt */
        public static final class DefaultImpls {
            public static void onError(Listener listener, NimbusError nimbusError) {
                Intrinsics.checkNotNullParameter(nimbusError, "error");
            }
        }

        void onError(NimbusError nimbusError);
    }

    @JvmStatic
    static App getApp() {
        return Companion.getApp();
    }

    @JvmStatic
    static <T extends Client> T getClient() {
        return Companion.getClient();
    }

    @JvmStatic
    static String getRequestUrl() {
        return Companion.getRequestUrl();
    }

    @JvmStatic
    static String getSessionId() {
        return Companion.getSessionId();
    }

    @JvmStatic
    static User getUser() {
        return Companion.getUser();
    }

    @JvmStatic
    static void setApp(App app) {
        Companion.setApp(app);
    }

    @JvmStatic
    static void setBlockedAdvertiserDomains(String... strArr) {
        Companion.setBlockedAdvertiserDomains(strArr);
    }

    @JvmStatic
    static void setClient(Client client) {
        Companion.setClient(client);
    }

    @JvmStatic
    static void setGdprConsent(String str) {
        Companion.setGdprConsent(str);
    }

    @JvmStatic
    static void setRequestUrl(String str) {
        Companion.setRequestUrl(str);
    }

    @JvmStatic
    static void setSessionId(String str) {
        Companion.setSessionId(str);
    }

    @JvmStatic
    static void setUser(User user) {
        Companion.setUser(user);
    }

    String getApiKey();

    String getPublisherKey();

    Object makeRequest(Context context, NimbusRequest nimbusRequest, Continuation<? super NimbusResponse> continuation);

    <T extends NimbusResponse.Listener & NimbusError.Listener> void makeRequest(Context context, NimbusRequest nimbusRequest, T t);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestManager.kt */
    public static final class DefaultImpls {
        public static String getApiKey(RequestManager requestManager) {
            return Platform.apiKey;
        }

        public static String getPublisherKey(RequestManager requestManager) {
            return Platform.publisherKey;
        }

        public static <T extends NimbusResponse.Listener & NimbusError.Listener> void makeRequest(RequestManager requestManager, Context context, NimbusRequest nimbusRequest, T t) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
            Intrinsics.checkNotNullParameter(t, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new RequestManager$makeRequest$1(requestManager, context, nimbusRequest, t, (Continuation<? super RequestManager$makeRequest$1>) null), 2, (Object) null);
        }

        public static Object makeRequest(RequestManager requestManager, Context context, NimbusRequest nimbusRequest, Continuation<? super NimbusResponse> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new RequestManager$makeRequest$3(nimbusRequest, requestManager, context, (Continuation<? super RequestManager$makeRequest$3>) null), continuation);
        }
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0016J+\u0010\u000f\u001a\u00020\u0003\"\f\b\u0000\u0010\u0010*\u00020\u000e*\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u0002H\u0010H&¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u000f\u001a\u00020\u0011H\u0016¨\u0006\u0017"}, d2 = {"Lcom/adsbynimbus/request/RequestManager$Client;", "", "handleError", "", "statusCode", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "listener", "Lcom/adsbynimbus/NimbusError$Listener;", "handleResponse", "response", "Lcom/adsbynimbus/request/NimbusResponse;", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "request", "T", "Lcom/adsbynimbus/request/NimbusRequest;", "callback", "(Lcom/adsbynimbus/request/NimbusRequest;Lcom/adsbynimbus/request/NimbusResponse$Listener;)V", "requiredHeaders", "", "", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestManager.kt */
    public interface Client {
        void handleError(int i, Exception exc, NimbusError.Listener listener);

        void handleResponse(NimbusResponse nimbusResponse, NimbusResponse.Listener listener);

        <T extends NimbusResponse.Listener & NimbusError.Listener> void request(NimbusRequest nimbusRequest, T t);

        Map<String, String> requiredHeaders(NimbusRequest nimbusRequest);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: RequestManager.kt */
        public static final class DefaultImpls {
            public static void handleResponse(Client client, NimbusResponse nimbusResponse, NimbusResponse.Listener listener) {
                Intrinsics.checkNotNullParameter(nimbusResponse, Response.TYPE);
                Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                Logger.log(4, "Network: " + nimbusResponse.bid.network + " | ID: " + nimbusResponse.bid.auction_id + " | " + nimbusResponse.bid.type);
                listener.onAdResponse(nimbusResponse);
            }

            public static void handleError(Client client, int i, Exception exc, NimbusError.Listener listener) {
                NimbusError nimbusError;
                Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                if (i == -2) {
                    nimbusError = new NimbusError(NimbusError.ErrorType.NETWORK_ERROR, "Error parsing Nimbus response", exc);
                } else if (i == 404) {
                    nimbusError = new NimbusError(NimbusError.ErrorType.NO_BID, "No bid for request", exc);
                } else if (i != 429) {
                    nimbusError = new NimbusError(NimbusError.ErrorType.NETWORK_ERROR, "Unknown network error", exc);
                } else {
                    nimbusError = new NimbusError(NimbusError.ErrorType.NETWORK_ERROR, "Too many requests", exc);
                }
                listener.onError(nimbusError);
            }

            public static Map<String, String> requiredHeaders(Client client, NimbusRequest nimbusRequest) {
                Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
                return RequestExtensions.headers(nimbusRequest);
            }
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0017\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\nH\u0007¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\b\u0010\u000e\u001a\u00020\rH\u0007J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0007J!\u0010\u0014\u001a\u00020\u00122\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0016\"\u00020\rH\u0007¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\nH\u0007J\u0012\u0010\u001a\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0007J\u0012\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\rH\u0007J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\rH\u0007J\u0012\u0010 \u001a\u00020\u00122\b\u0010!\u001a\u0004\u0018\u00010\u0010H\u0007R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\""}, d2 = {"Lcom/adsbynimbus/request/RequestManager$Companion;", "", "()V", "interceptors", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Lcom/adsbynimbus/request/NimbusRequest$Interceptor;", "getApp", "Lcom/adsbynimbus/openrtb/request/App;", "getClient", "T", "Lcom/adsbynimbus/request/RequestManager$Client;", "()Lcom/adsbynimbus/request/RequestManager$Client;", "getRequestUrl", "", "getSessionId", "getUser", "Lcom/adsbynimbus/openrtb/request/User;", "setApp", "", "openRTBApp", "setBlockedAdvertiserDomains", "advertisers", "", "([Ljava/lang/String;)V", "setClient", "defaultClient", "setGdprConsent", "consentString", "setRequestUrl", "requestUrl", "setSessionId", "id", "setUser", "openRTBUser", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestManager.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final void setClient(Client client) {
            Intrinsics.checkNotNullParameter(client, "defaultClient");
            RequestExtensions.client = client;
        }

        @JvmStatic
        public final <T extends Client> T getClient() {
            T t = RequestExtensions.client;
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of com.adsbynimbus.request.RequestManager.Companion.getClient");
            return t;
        }

        @JvmStatic
        public final App getApp() {
            return RequestExtensions.app;
        }

        @JvmStatic
        public final void setApp(App app) {
            RequestExtensions.app = app;
        }

        @JvmStatic
        public final void setRequestUrl(String str) {
            RequestExtensions.defaultRequestUrl = str;
        }

        @JvmStatic
        public final String getRequestUrl() {
            return RequestExtensions.defaultRequestUrl;
        }

        @JvmStatic
        public final String getSessionId() {
            return Nimbus.sessionId;
        }

        @JvmStatic
        public final void setSessionId(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            Nimbus.sessionId = str;
        }

        @JvmStatic
        public final User getUser() {
            return RequestExtensions.user;
        }

        @JvmStatic
        public final void setUser(User user) {
            RequestExtensions.user = user;
        }

        @JvmStatic
        public final void setBlockedAdvertiserDomains(String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "advertisers");
            RequestExtensions.blockedAdvertisers = strArr;
        }

        @JvmStatic
        public final void setGdprConsent(String str) {
            if (str != null || RequestExtensions.user != null) {
                User user = RequestExtensions.user;
                if (user == null) {
                    user = new User(0, (String) null, 0, (String) null, (String) null, (String) null, (Data[]) null, (User.Extension) null, 255, (DefaultConstructorMarker) null);
                }
                PrivacyExtensions.setGdprConsent(user, str);
                RequestExtensions.user = user;
            }
        }
    }
}
