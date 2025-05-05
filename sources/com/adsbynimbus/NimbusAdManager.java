package com.adsbynimbus;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.EID;
import com.adsbynimbus.openrtb.request.UID;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.render.ManagedAd;
import com.adsbynimbus.render.Renderer;
import com.adsbynimbus.request.NimbusRequest;
import com.adsbynimbus.request.NimbusResponse;
import com.adsbynimbus.request.RequestManager;
import com.adsbynimbus.request.internal.RequestExtensionsKt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.SentryBaseEvent;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\u00020\u0001:\u0002%&B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bJ(\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0003\u0010 \u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bJ4\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0003\u0010 \u001a\u00020\u00112\b\b\u0001\u0010!\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J(\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010 \u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bJ(\u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007R\u0014\u0010\u0004\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\u00020\f*\u00020\r8Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\u00020\u0011*\u00020\u00118Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/adsbynimbus/NimbusAdManager;", "Lcom/adsbynimbus/request/RequestManager;", "publisherKey", "", "apiKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getApiKey", "()Ljava/lang/String;", "apiKeyOverride", "getPublisherKey", "publisherKeyOverride", "blockingRendererError", "Lcom/adsbynimbus/NimbusError;", "Lcom/adsbynimbus/request/NimbusResponse;", "getBlockingRendererError", "(Lcom/adsbynimbus/request/NimbusResponse;)Lcom/adsbynimbus/NimbusError;", "toClampedMillis", "", "getToClampedMillis", "(I)I", "showAd", "", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "viewGroup", "Landroid/view/ViewGroup;", "listener", "Lcom/adsbynimbus/NimbusAdManager$Listener;", "refreshInterval", "showBlockingAd", "activity", "Landroid/app/Activity;", "closeButtonDelaySeconds", "closeDelayAfterCompleteSeconds", "showRewardedAd", "showRewardedVideoAd", "closeButtonDelayMillis", "Companion", "Listener", "all_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusAdManager.kt */
public final class NimbusAdManager implements RequestManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String apiKeyOverride;
    private final String publisherKeyOverride;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0017¨\u0006\u000b"}, d2 = {"Lcom/adsbynimbus/NimbusAdManager$Listener;", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "onAdResponse", "", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "all_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusAdManager.kt */
    public interface Listener extends NimbusResponse.Listener, Renderer.Listener, NimbusError.Listener {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: NimbusAdManager.kt */
        public static final class DefaultImpls {
            public static void onAdResponse(Listener listener, NimbusResponse nimbusResponse) {
                Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
            }

            public static void onError(Listener listener, NimbusError nimbusError) {
                Intrinsics.checkNotNullParameter(nimbusError, "error");
            }
        }

        void onAdResponse(NimbusResponse nimbusResponse);

        void onError(NimbusError nimbusError);
    }

    public NimbusAdManager() {
        this((String) null, (String) null, 3, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final boolean addExtendedId(String str, String str2) {
        return Companion.addExtendedId(str, str2);
    }

    @JvmStatic
    public static final boolean addExtendedId(String str, String str2, Map<String, String> map) {
        return Companion.addExtendedId(str, str2, map);
    }

    public static final Set<EID> getExtendedIds() {
        return Companion.getExtendedIds();
    }

    @JvmStatic
    public static final void setApp(App app) {
        Companion.setApp(app);
    }

    @JvmStatic
    public static final void setBlockedAdvertiserDomains(String... strArr) {
        Companion.setBlockedAdvertiserDomains(strArr);
    }

    @JvmStatic
    public static final void setGdprConsent(String str) {
        Companion.setGdprConsent(str);
    }

    @JvmStatic
    public static final void setRequestUrl(String str) {
        Companion.setRequestUrl(str);
    }

    @JvmStatic
    public static final void setSessionId(String str) {
        Companion.setSessionId(str);
    }

    @JvmStatic
    public static final void setUser(User user) {
        Companion.setUser(user);
    }

    public NimbusAdManager(String str, String str2) {
        this.publisherKeyOverride = str;
        this.apiKeyOverride = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NimbusAdManager(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public Object makeRequest(Context context, NimbusRequest nimbusRequest, Continuation<? super NimbusResponse> continuation) {
        return RequestManager.DefaultImpls.makeRequest((RequestManager) this, context, nimbusRequest, continuation);
    }

    public <T extends NimbusResponse.Listener & NimbusError.Listener> void makeRequest(Context context, NimbusRequest nimbusRequest, T t) {
        RequestManager.DefaultImpls.makeRequest((RequestManager) this, context, nimbusRequest, t);
    }

    public String getPublisherKey() {
        String str = this.publisherKeyOverride;
        return str == null ? Platform.publisherKey : str;
    }

    public String getApiKey() {
        String str = this.apiKeyOverride;
        return str == null ? Platform.apiKey : str;
    }

    public final void showAd(NimbusRequest nimbusRequest, ViewGroup viewGroup, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new NimbusAdManager$showAd$1(nimbusRequest, this, viewGroup, listener, (Continuation<? super NimbusAdManager$showAd$1>) null), 2, (Object) null);
    }

    public final void showAd(NimbusRequest nimbusRequest, int i, ViewGroup viewGroup, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        if (Nimbus.getThirdPartyViewabilityEnabled()) {
            nimbusRequest.configureViewability(Nimbus.sdkName, "2.26.1");
        }
        listener.onAdRendered(ManagedAd.refreshingController(viewGroup, this, nimbusRequest, i, listener));
    }

    public final void showBlockingAd(NimbusRequest nimbusRequest, Activity activity, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        showBlockingAd(nimbusRequest, 5, activity, listener);
    }

    public static /* synthetic */ void showBlockingAd$default(NimbusAdManager nimbusAdManager, NimbusRequest nimbusRequest, int i, Activity activity, Listener listener, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 5;
        }
        nimbusAdManager.showBlockingAd(nimbusRequest, i, activity, listener);
    }

    public final void showBlockingAd(NimbusRequest nimbusRequest, int i, Activity activity, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        showBlockingAd(nimbusRequest, i, 0, activity, listener);
    }

    static /* synthetic */ void showBlockingAd$default(NimbusAdManager nimbusAdManager, NimbusRequest nimbusRequest, int i, int i2, Activity activity, Listener listener, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 5;
        }
        nimbusAdManager.showBlockingAd(nimbusRequest, i, i2, activity, listener);
    }

    private final void showBlockingAd(NimbusRequest nimbusRequest, int i, int i2, Activity activity, Listener listener) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getNimbusScope(), Dispatchers.getMain(), (CoroutineStart) null, new NimbusAdManager$showBlockingAd$1(nimbusRequest, this, activity, listener, i, i2, (Continuation<? super NimbusAdManager$showBlockingAd$1>) null), 2, (Object) null);
    }

    public final void showRewardedAd(NimbusRequest nimbusRequest, int i, Activity activity, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        showBlockingAd(NimbusRequest.Companion.asRewardedAd(nimbusRequest, activity), i, 5, activity, listener);
    }

    @Deprecated(message = "Use showRewardedAd instead", replaceWith = @ReplaceWith(expression = "showRewardedAd", imports = {}))
    public final void showRewardedVideoAd(NimbusRequest nimbusRequest, int i, Activity activity, Listener listener) {
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        showBlockingAd(NimbusRequest.Companion.asRewardedAd(nimbusRequest, activity), i / 1000, activity, listener);
    }

    private final int getToClampedMillis(int i) {
        return RangesKt.coerceIn(i * 1000, 0, 3600000);
    }

    private final NimbusError getBlockingRendererError(NimbusResponse nimbusResponse) {
        return new NimbusError(NimbusError.ErrorType.RENDERER_ERROR, "No renderer installed for blocking " + nimbusResponse.network() + ' ' + nimbusResponse.type(), (Throwable) null);
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000fH\bJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\bJ\"\u0010\u0014\u001a\u00020\u00112\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0016\"\u00020\fH\b¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\bJ\u0011\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\fH\bJ\u0011\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\fH\bJ\u0013\u0010\u001e\u001a\u00020\u00112\b\u0010\u001f\u001a\u0004\u0018\u00010 H\bR!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048Æ\u0002X\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006!"}, d2 = {"Lcom/adsbynimbus/NimbusAdManager$Companion;", "", "()V", "extendedIds", "", "Lcom/adsbynimbus/openrtb/request/EID;", "getExtendedIds$annotations", "getExtendedIds", "()Ljava/util/Set;", "addExtendedId", "", "source", "", "id", "extensions", "", "setApp", "", "app", "Lcom/adsbynimbus/openrtb/request/App;", "setBlockedAdvertiserDomains", "blockedAdvertisers", "", "([Ljava/lang/String;)V", "setGdprConsent", "consentString", "setRequestUrl", "requestUrl", "setSessionId", "sessionId", "setUser", "user", "Lcom/adsbynimbus/openrtb/request/User;", "all_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusAdManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getExtendedIds$annotations() {
        }

        private Companion() {
        }

        public final Set<EID> getExtendedIds() {
            return RequestExtensionsKt.getGlobalExtendedIds();
        }

        public static /* synthetic */ boolean addExtendedId$default(Companion companion, String str, String str2, Map map, int i, Object obj) {
            if ((i & 4) != 0) {
                map = MapsKt.emptyMap();
            }
            Intrinsics.checkNotNullParameter(str, "source");
            Intrinsics.checkNotNullParameter(str2, "id");
            Intrinsics.checkNotNullParameter(map, "extensions");
            return RequestExtensionsKt.getGlobalExtendedIds().add(new EID(str, SetsKt.setOf(new UID(str2, 0, MapsKt.toMutableMap(map), 2, (DefaultConstructorMarker) null))));
        }

        @JvmStatic
        public final boolean addExtendedId(String str, String str2, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "source");
            Intrinsics.checkNotNullParameter(str2, "id");
            Intrinsics.checkNotNullParameter(map, "extensions");
            return RequestExtensionsKt.getGlobalExtendedIds().add(new EID(str, SetsKt.setOf(new UID(str2, 0, (Map) MapsKt.toMutableMap(map), 2, (DefaultConstructorMarker) null))));
        }

        @JvmStatic
        public final void setApp(App app) {
            RequestManager.Companion.setApp(app);
        }

        @JvmStatic
        public final void setUser(User user) {
            RequestManager.Companion.setUser(user);
        }

        @JvmStatic
        public final void setBlockedAdvertiserDomains(String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "blockedAdvertisers");
            RequestManager.Companion.setBlockedAdvertiserDomains((String[]) Arrays.copyOf(strArr, strArr.length));
        }

        @JvmStatic
        public final void setRequestUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "requestUrl");
            RequestManager.Companion.setRequestUrl(str);
        }

        @JvmStatic
        public final void setSessionId(String str) {
            Intrinsics.checkNotNullParameter(str, "sessionId");
            Nimbus.sessionId = str;
        }

        @JvmStatic
        public final void setGdprConsent(String str) {
            RequestManager.Companion.setGdprConsent(str);
        }

        @JvmStatic
        public final boolean addExtendedId(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "source");
            Intrinsics.checkNotNullParameter(str2, "id");
            return RequestExtensionsKt.getGlobalExtendedIds().add(new EID(str, SetsKt.setOf(new UID(str2, 0, MapsKt.toMutableMap(MapsKt.emptyMap()), 2, (DefaultConstructorMarker) null))));
        }
    }
}
