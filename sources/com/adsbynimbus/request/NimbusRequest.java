package com.adsbynimbus.request;

import android.content.Context;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.openrtb.enumerations.TitleLength;
import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Asset;
import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.openrtb.request.Device;
import com.adsbynimbus.openrtb.request.EID;
import com.adsbynimbus.openrtb.request.Extension;
import com.adsbynimbus.openrtb.request.Format;
import com.adsbynimbus.openrtb.request.Impression;
import com.adsbynimbus.openrtb.request.Native;
import com.adsbynimbus.openrtb.request.NimbusNative;
import com.adsbynimbus.openrtb.request.Regs;
import com.adsbynimbus.openrtb.request.Source;
import com.adsbynimbus.openrtb.request.UID;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.openrtb.request.builders.AndroidBidRequestBuilder;
import com.adsbynimbus.render.CompanionAd;
import com.adsbynimbus.request.NimbusResponse;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.dynamiclinks.DynamicLink;
import io.sentry.SentryBaseEvent;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u0001:\u000212B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J.\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0014\b\u0002\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030*H\u0007J\u0006\u0010+\u001a\u00020,J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u0003R\u001a\u0010\u0007\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\t\"\u0004\b$\u0010\u000b¨\u00063"}, d2 = {"Lcom/adsbynimbus/request/NimbusRequest;", "", "position", "", "request", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "(Ljava/lang/String;Lcom/adsbynimbus/openrtb/request/BidRequest;)V", "apiKey", "getApiKey$request_release", "()Ljava/lang/String;", "setApiKey$request_release", "(Ljava/lang/String;)V", "companionAds", "", "Lcom/adsbynimbus/render/CompanionAd;", "getCompanionAds", "()[Lcom/adsbynimbus/render/CompanionAd;", "setCompanionAds", "([Lcom/adsbynimbus/render/CompanionAd;)V", "[Lcom/adsbynimbus/render/CompanionAd;", "extendedIds", "", "Lcom/adsbynimbus/openrtb/request/EID;", "getExtendedIds", "()Ljava/util/Set;", "interceptors", "Lcom/adsbynimbus/request/NimbusRequest$Interceptor;", "getInterceptors", "interstitialOrientation", "", "getInterstitialOrientation", "()I", "setInterstitialOrientation", "(I)V", "requestUrl", "getRequestUrl", "setRequestUrl", "addExtendedId", "", "source", "id", "extensions", "", "builder", "Lcom/adsbynimbus/openrtb/request/builders/AndroidBidRequestBuilder;", "configureViewability", "", "partnerName", "partnerVersion", "Companion", "Interceptor", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusRequest.kt */
public final class NimbusRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static byte[] defaultApis = {3, 5, 6, 7};
    public static byte[] defaultProtocols = {2, 5, 3, 6, 7, 8};
    public String apiKey;
    private CompanionAd[] companionAds;
    private final Set<EID> extendedIds;
    private final Set<Interceptor> interceptors;
    private int interstitialOrientation;
    public final String position;
    public final BidRequest request;
    private String requestUrl;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/adsbynimbus/request/NimbusRequest$Interceptor;", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "modifyRequest", "", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "onAdResponse", "nimbusResponse", "Lcom/adsbynimbus/request/NimbusResponse;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusRequest.kt */
    public interface Interceptor extends NimbusResponse.Listener, NimbusError.Listener {

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: NimbusRequest.kt */
        public static final class DefaultImpls {
            public static void onAdResponse(Interceptor interceptor, NimbusResponse nimbusResponse) {
                Intrinsics.checkNotNullParameter(nimbusResponse, "nimbusResponse");
            }

            public static void onError(Interceptor interceptor, NimbusError nimbusError) {
                Intrinsics.checkNotNullParameter(nimbusError, "error");
            }
        }

        void modifyRequest(NimbusRequest nimbusRequest);

        void onAdResponse(NimbusResponse nimbusResponse);

        void onError(NimbusError nimbusError);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest) {
        return Companion.addNativeAd(nimbusRequest);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize, z);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize, z, b);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize, z, b, b2);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize, z, b, b2, b3);
    }

    @JvmStatic
    public static final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr) {
        return Companion.addNativeAd(nimbusRequest, nativeAdSize, z, b, b2, b3, bArr);
    }

    @JvmStatic
    public static final NimbusRequest asRewardedAd(NimbusRequest nimbusRequest, Context context) {
        return Companion.asRewardedAd(nimbusRequest, context);
    }

    @JvmStatic
    public static final NimbusRequest forBannerAd(String str, Format format) {
        return Companion.forBannerAd(str, format);
    }

    @JvmStatic
    public static final NimbusRequest forBannerAd(String str, Format format, byte b) {
        return Companion.forBannerAd(str, format, b);
    }

    @JvmStatic
    public static final NimbusRequest forInterstitialAd(String str) {
        return Companion.forInterstitialAd(str);
    }

    @JvmStatic
    public static final NimbusRequest forInterstitialAd(String str, int i) {
        return Companion.forInterstitialAd(str, i);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str) {
        return Companion.forNativeAd(str);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize) {
        return Companion.forNativeAd(str, nativeAdSize);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z) {
        return Companion.forNativeAd(str, nativeAdSize, z);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b) {
        return Companion.forNativeAd(str, nativeAdSize, z, b);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2) {
        return Companion.forNativeAd(str, nativeAdSize, z, b, b2);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3) {
        return Companion.forNativeAd(str, nativeAdSize, z, b, b2, b3);
    }

    @JvmStatic
    public static final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr) {
        return Companion.forNativeAd(str, nativeAdSize, z, b, b2, b3, bArr);
    }

    @JvmStatic
    public static final NimbusRequest forRewardedVideo(String str) {
        return Companion.forRewardedVideo(str);
    }

    @JvmStatic
    public static final NimbusRequest forRewardedVideo(String str, int i) {
        return Companion.forRewardedVideo(str, i);
    }

    @JvmStatic
    public static final NimbusRequest forVideoAd(String str) {
        return Companion.forVideoAd(str);
    }

    @JvmStatic
    public static final NimbusRequest forVideoAd(String str, byte b) {
        return Companion.forVideoAd(str, b);
    }

    @JvmStatic
    public static final NimbusRequest wrap(BidRequest bidRequest) {
        return Companion.wrap(bidRequest);
    }

    public final boolean addExtendedId(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "source");
        Intrinsics.checkNotNullParameter(str2, "id");
        return addExtendedId$default(this, str, str2, (Map) null, 4, (Object) null);
    }

    public NimbusRequest(String str, BidRequest bidRequest) {
        Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
        Intrinsics.checkNotNullParameter(bidRequest, SentryBaseEvent.JsonKeys.REQUEST);
        this.position = str;
        this.request = bidRequest;
        this.companionAds = new CompanionAd[0];
        this.extendedIds = new LinkedHashSet();
        String str2 = RequestExtensions.defaultRequestUrl;
        this.requestUrl = str2 == null ? "" : str2;
        this.interceptors = new LinkedHashSet();
    }

    public /* synthetic */ NimbusRequest(String str, BidRequest bidRequest, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str2;
        NimbusRequest nimbusRequest;
        BidRequest bidRequest2;
        if ((i & 2) != 0) {
            Impression[] impressionArr = {new Impression((Banner) null, (Video) null, (Native) null, (byte) 0, (byte) 0, new Impression.Extension(str, (Set) null, (String) null, (String) null, 14, (DefaultConstructorMarker) null), 31, (DefaultConstructorMarker) null)};
            App app = RequestExtensions.app;
            User user = RequestExtensions.user;
            Source source = null;
            Source source2 = true ^ Nimbus.getThirdPartyViewabilityEnabled() ? null : new Source((Map) null, 1, (DefaultConstructorMarker) null);
            if (source2 != null) {
                source2.getExt().put("omidpn", Nimbus.sdkName);
                source2.getExt().put("omidpv", "2.26.1");
                Unit unit = Unit.INSTANCE;
                source = source2;
            }
            bidRequest2 = new BidRequest(impressionArr, app, (Device) null, (Format) null, user, (byte) 0, 0, (String[]) null, source, (Regs) null, (Map) null, 1772, (DefaultConstructorMarker) null);
            bidRequest2.ext.put("session_id", Nimbus.sessionId);
            nimbusRequest = this;
            str2 = str;
        } else {
            nimbusRequest = this;
            str2 = str;
            bidRequest2 = bidRequest;
        }
        new NimbusRequest(str2, bidRequest2);
    }

    public static /* synthetic */ boolean addExtendedId$default(NimbusRequest nimbusRequest, String str, String str2, Map map, int i, Object obj) {
        if ((i & 4) != 0) {
            map = MapsKt.emptyMap();
        }
        return nimbusRequest.addExtendedId(str, str2, map);
    }

    public final boolean addExtendedId(String str, String str2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "source");
        Intrinsics.checkNotNullParameter(str2, "id");
        Intrinsics.checkNotNullParameter(map, "extensions");
        return this.extendedIds.add(new EID(str, SetsKt.setOf(new UID(str2, 0, (Map) MapsKt.toMutableMap(map), 2, (DefaultConstructorMarker) null))));
    }

    public final AndroidBidRequestBuilder builder() {
        return new AndroidBidRequestBuilder(this.request);
    }

    public final void configureViewability(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "partnerName");
        Intrinsics.checkNotNullParameter(str2, "partnerVersion");
        BidRequest bidRequest = this.request;
        Source source = new Source((Map) null, 1, (DefaultConstructorMarker) null);
        source.getExt().put("omidpn", str);
        source.getExt().put("omidpv", str2);
        bidRequest.source = source;
    }

    public final CompanionAd[] getCompanionAds() {
        return this.companionAds;
    }

    public final void setCompanionAds(CompanionAd[] companionAdArr) {
        Intrinsics.checkNotNullParameter(companionAdArr, "<set-?>");
        this.companionAds = companionAdArr;
    }

    public final Set<EID> getExtendedIds() {
        return this.extendedIds;
    }

    public final String getRequestUrl() {
        return this.requestUrl;
    }

    public final void setRequestUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.requestUrl = str;
    }

    public final int getInterstitialOrientation() {
        return this.interstitialOrientation;
    }

    public final void setInterstitialOrientation(int i) {
        this.interstitialOrientation = i;
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007JY\u0010\u0011\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007J\u001a\u0010\u001c\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0007JU\u0010 \u001a\u00020\u0007*\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010!JI\u0010\"\u001a\u00020#*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\r2\b\u0010$\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020\u0007*\u00020\u00072\u0006\u0010'\u001a\u00020(H\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/adsbynimbus/request/NimbusRequest$Companion;", "", "()V", "defaultApis", "", "defaultProtocols", "forBannerAd", "Lcom/adsbynimbus/request/NimbusRequest;", "position", "", "format", "Lcom/adsbynimbus/openrtb/request/Format;", "screenPosition", "", "forInterstitialAd", "orientation", "", "forNativeAd", "size", "Lcom/adsbynimbus/request/NativeAdSize;", "includeVideo", "", "adContext", "adContextSubType", "placementType", "battr", "(Ljava/lang/String;Lcom/adsbynimbus/request/NativeAdSize;ZLjava/lang/Byte;Ljava/lang/Byte;Ljava/lang/Byte;[B)Lcom/adsbynimbus/request/NimbusRequest;", "forRewardedVideo", "forVideoAd", "wrap", "bid", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "addNativeAd", "(Lcom/adsbynimbus/request/NimbusRequest;Lcom/adsbynimbus/request/NativeAdSize;ZLjava/lang/Byte;Ljava/lang/Byte;Ljava/lang/Byte;[B)Lcom/adsbynimbus/request/NimbusRequest;", "addNativeComponent", "", "blockedAttr", "(Lcom/adsbynimbus/request/NimbusRequest;Lcom/adsbynimbus/request/NativeAdSize;ZLjava/lang/Byte;Ljava/lang/Byte;Ljava/lang/Byte;[B)V", "asRewardedAd", "context", "Landroid/content/Context;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            return addNativeAd$default(this, nimbusRequest, (NativeAdSize) null, false, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, 63, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return addNativeAd$default(this, nimbusRequest, nativeAdSize, false, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, 62, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return addNativeAd$default(this, nimbusRequest, nativeAdSize, z, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, 60, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return addNativeAd$default(this, nimbusRequest, nativeAdSize, z, b, (Byte) null, (Byte) null, (byte[]) null, 56, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return addNativeAd$default(this, nimbusRequest, nativeAdSize, z, b, b2, (Byte) null, (byte[]) null, 48, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return addNativeAd$default(this, nimbusRequest, nativeAdSize, z, b, b2, b3, (byte[]) null, 32, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forBannerAd(String str, Format format) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(format, "format");
            return forBannerAd$default(this, str, format, (byte) 0, 4, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forInterstitialAd(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            return forInterstitialAd$default(this, str, 0, 2, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            return forNativeAd$default(this, str, (NativeAdSize) null, false, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, WebSocketProtocol.PAYLOAD_SHORT, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return forNativeAd$default(this, str, nativeAdSize, false, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, 124, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return forNativeAd$default(this, str, nativeAdSize, z, (Byte) null, (Byte) null, (Byte) null, (byte[]) null, 120, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return forNativeAd$default(this, str, nativeAdSize, z, b, (Byte) null, (Byte) null, (byte[]) null, 112, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return forNativeAd$default(this, str, nativeAdSize, z, b, b2, (Byte) null, (byte[]) null, 96, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            return forNativeAd$default(this, str, nativeAdSize, z, b, b2, b3, (byte[]) null, 64, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forRewardedVideo(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            return forRewardedVideo$default(this, str, 0, 2, (Object) null);
        }

        @JvmStatic
        public final NimbusRequest forVideoAd(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            return forVideoAd$default(this, str, (byte) 0, 2, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ NimbusRequest forInterstitialAd$default(Companion companion, String str, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.forInterstitialAd(str, i);
        }

        @JvmStatic
        public final NimbusRequest forInterstitialAd(String str, int i) {
            String str2 = str;
            int i2 = i;
            Intrinsics.checkNotNullParameter(str2, ViewProps.POSITION);
            NimbusRequest nimbusRequest = new NimbusRequest(str2, (BidRequest) null, 2, (DefaultConstructorMarker) null);
            nimbusRequest.setInterstitialOrientation(i2);
            Format format = i2 == 2 ? Format.INTERSTITIAL_LAND : Format.INTERSTITIAL_PORT;
            Impression impression = nimbusRequest.request.imp[0];
            impression.instl = 1;
            Banner banner = r5;
            Banner banner2 = new Banner(format.w, format.h, (Format[]) null, 0.0f, (byte[]) null, (byte) 7, NimbusRequest.defaultApis, (Byte) null, 156, (DefaultConstructorMarker) null);
            impression.banner = banner;
            impression.video = new Video(0.0f, (String[]) null, 0, 0, NimbusRequest.defaultProtocols, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte[]) null, 0, 0, 0, 0, (byte) 7, (byte[]) null, new byte[]{7}, (Banner[]) null, (byte[]) null, (Map) null, 3866607, (DefaultConstructorMarker) null);
            nimbusRequest.setCompanionAds(new CompanionAd[]{CompanionAd.Companion.end(format.w, format.h)});
            return nimbusRequest;
        }

        public static /* synthetic */ NimbusRequest forBannerAd$default(Companion companion, String str, Format format, byte b, int i, Object obj) {
            if ((i & 4) != 0) {
                b = 0;
            }
            return companion.forBannerAd(str, format, b);
        }

        @JvmStatic
        public final NimbusRequest forBannerAd(String str, Format format, byte b) {
            String str2 = str;
            Format format2 = format;
            Intrinsics.checkNotNullParameter(str2, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(format2, "format");
            NimbusRequest nimbusRequest = new NimbusRequest(str2, (BidRequest) null, 2, (DefaultConstructorMarker) null);
            nimbusRequest.request.imp[0].banner = new Banner(format2.w, format2.h, (Format[]) null, 0.0f, (byte[]) null, b, NimbusRequest.defaultApis, (Byte) null, 156, (DefaultConstructorMarker) null);
            return nimbusRequest;
        }

        public static /* synthetic */ NimbusRequest forVideoAd$default(Companion companion, String str, byte b, int i, Object obj) {
            if ((i & 2) != 0) {
                b = 0;
            }
            return companion.forVideoAd(str, b);
        }

        @JvmStatic
        public final NimbusRequest forVideoAd(String str, byte b) {
            String str2 = str;
            Intrinsics.checkNotNullParameter(str2, ViewProps.POSITION);
            NimbusRequest nimbusRequest = new NimbusRequest(str2, (BidRequest) null, 2, (DefaultConstructorMarker) null);
            Impression impression = nimbusRequest.request.imp[0];
            Video video = r1;
            NimbusRequest nimbusRequest2 = nimbusRequest;
            Video video2 = new Video(0.0f, (String[]) null, 0, 0, NimbusRequest.defaultProtocols, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte[]) null, 0, 0, 0, 0, b, (byte[]) null, new byte[]{7}, (Banner[]) null, (byte[]) null, (Map) null, 3866607, (DefaultConstructorMarker) null);
            impression.video = video;
            return nimbusRequest2;
        }

        public static /* synthetic */ NimbusRequest forRewardedVideo$default(Companion companion, String str, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.forRewardedVideo(str, i);
        }

        @JvmStatic
        public final NimbusRequest forRewardedVideo(String str, int i) {
            String str2 = str;
            int i2 = i;
            Intrinsics.checkNotNullParameter(str2, ViewProps.POSITION);
            NimbusRequest nimbusRequest = new NimbusRequest(str2, (BidRequest) null, 2, (DefaultConstructorMarker) null);
            nimbusRequest.setInterstitialOrientation(i2);
            Impression impression = nimbusRequest.request.imp[0];
            boolean z = true;
            impression.instl = 1;
            Video video = r6;
            Video video2 = new Video(0.0f, (String[]) null, 0, 0, NimbusRequest.defaultProtocols, 0, 0, 0, (byte) 0, (byte) 0, (byte) 0, (byte[]) null, 0, 0, 0, 0, (byte) 7, (byte[]) null, new byte[]{7}, (Banner[]) null, (byte[]) null, (Map) null, 3866607, (DefaultConstructorMarker) null);
            video.ext.put("is_rewarded", (byte) 1);
            impression.video = video;
            CompanionAd[] companionAdArr = new CompanionAd[1];
            if (i2 != 2) {
                z = false;
            }
            CompanionAd.Companion companion = CompanionAd.Companion;
            companionAdArr[0] = z ? companion.end(480, 320) : companion.end(320, 480);
            nimbusRequest.setCompanionAds(companionAdArr);
            return nimbusRequest;
        }

        @JvmStatic
        public final NimbusRequest asRewardedAd(NimbusRequest nimbusRequest, Context context) {
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(context, "context");
            Video video = nimbusRequest.request.imp[0].video;
            boolean z = true;
            if (video != null) {
                video.ext.put("is_rewarded", (byte) 1);
            }
            CompanionAd[] companionAdArr = new CompanionAd[1];
            if (context.getResources().getConfiguration().orientation != 2) {
                z = false;
            }
            companionAdArr[0] = z ? CompanionAd.Companion.end(480, 320) : CompanionAd.Companion.end(320, 480);
            nimbusRequest.setCompanionAds(companionAdArr);
            return nimbusRequest;
        }

        public static /* synthetic */ NimbusRequest addNativeAd$default(Companion companion, NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr, int i, Object obj) {
            NativeAdSize nativeAdSize2 = (i & 1) != 0 ? NativeAdSize.Medium : nativeAdSize;
            boolean z2 = (i & 2) != 0 ? false : z;
            byte[] bArr2 = null;
            Byte b4 = (i & 4) != 0 ? null : b;
            Byte b5 = (i & 8) != 0 ? null : b2;
            Byte b6 = (i & 16) != 0 ? null : b3;
            if ((i & 32) == 0) {
                bArr2 = bArr;
            }
            return companion.addNativeAd(nimbusRequest, nativeAdSize2, z2, b4, b5, b6, bArr2);
        }

        @JvmStatic
        public final NimbusRequest addNativeAd(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr) {
            NimbusRequest nimbusRequest2 = nimbusRequest;
            Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            NimbusRequest.Companion.addNativeComponent(nimbusRequest, nativeAdSize, z, b, b2, b3, bArr);
            return nimbusRequest2;
        }

        public static /* synthetic */ NimbusRequest forNativeAd$default(Companion companion, String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr, int i, Object obj) {
            NativeAdSize nativeAdSize2 = (i & 2) != 0 ? NativeAdSize.Medium : nativeAdSize;
            boolean z2 = (i & 4) != 0 ? false : z;
            byte[] bArr2 = null;
            Byte b4 = (i & 8) != 0 ? null : b;
            Byte b5 = (i & 16) != 0 ? null : b2;
            Byte b6 = (i & 32) != 0 ? null : b3;
            if ((i & 64) == 0) {
                bArr2 = bArr;
            }
            return companion.forNativeAd(str, nativeAdSize2, z2, b4, b5, b6, bArr2);
        }

        @JvmStatic
        public final NimbusRequest forNativeAd(String str, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr) {
            String str2 = str;
            Intrinsics.checkNotNullParameter(str, ViewProps.POSITION);
            Intrinsics.checkNotNullParameter(nativeAdSize, "size");
            NimbusRequest nimbusRequest = new NimbusRequest(str, (BidRequest) null, 2, (DefaultConstructorMarker) null);
            NimbusRequest.Companion.addNativeComponent(nimbusRequest, nativeAdSize, z, b, b2, b3, bArr);
            return nimbusRequest;
        }

        private final void addNativeComponent(NimbusRequest nimbusRequest, NativeAdSize nativeAdSize, boolean z, Byte b, Byte b2, Byte b3, byte[] bArr) {
            NimbusRequest nimbusRequest2 = nimbusRequest;
            Impression impression = nimbusRequest2.request.imp[0];
            Native nativeR = nimbusRequest2.request.imp[0].f0native;
            if (nativeR == null) {
                nativeR = new Native(0.0f, (String) null, (String) null, (byte[]) null, (byte[]) null, (Extension) null, 63, (DefaultConstructorMarker) null);
            }
            nativeR.battr = bArr;
            Extension extension = nativeR.ext;
            if (extension == null) {
                extension = new Extension((NimbusNative) null, 1, (DefaultConstructorMarker) null);
            }
            List createListBuilder = CollectionsKt.createListBuilder();
            createListBuilder.add(AssetExtensionKt.title(Asset.Companion, 1, true, TitleLength.LONG));
            createListBuilder.add(AssetExtensionKt.image(Asset.Companion, 2, true, (byte) 3, nativeAdSize.getAdFormat()));
            createListBuilder.add(AssetExtensionKt.data(Asset.Companion, 3, true, (byte) 2, 25));
            if (z) {
                createListBuilder.add(AssetExtensionKt.video$default(Asset.Companion, 4, true, (String[]) null, 0, 0, (byte[]) null, 60, (Object) null));
            }
            extension.nimbusNative = new NimbusNative((String) null, b3, b, b2, CollectionsKt.build(createListBuilder), 1, (DefaultConstructorMarker) null);
            nativeR.ext = extension;
            impression.f0native = nativeR;
        }

        @JvmStatic
        public final NimbusRequest wrap(BidRequest bidRequest) {
            byte[] bArr;
            byte[] bArr2;
            Intrinsics.checkNotNullParameter(bidRequest, "bid");
            NimbusRequest nimbusRequest = new NimbusRequest(bidRequest.imp[0].ext.position, bidRequest);
            if (nimbusRequest.request.source == null) {
                nimbusRequest.configureViewability(Nimbus.sdkName, "2.26.1");
            }
            Banner banner = nimbusRequest.request.imp[0].banner;
            if (banner != null) {
                byte[] bArr3 = banner.api;
                if (bArr3 == null || (bArr2 = ArraysKt.plus(bArr3, (byte) 7)) == null) {
                    bArr2 = new byte[]{7};
                }
                banner.api = bArr2;
            }
            Video video = nimbusRequest.request.imp[0].video;
            if (video != null) {
                byte[] bArr4 = video.api;
                if (bArr4 == null || (bArr = ArraysKt.plus(bArr4, (byte) 7)) == null) {
                    bArr = new byte[]{7};
                }
                video.api = bArr;
            }
            return nimbusRequest;
        }
    }

    public final Set<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public final String getApiKey$request_release() {
        String str = this.apiKey;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DynamicLink.Builder.KEY_API_KEY);
        return null;
    }

    public final void setApiKey$request_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiKey = str;
    }
}
