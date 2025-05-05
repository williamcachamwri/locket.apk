package com.adsbynimbus.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.openrtb.request.App;
import com.adsbynimbus.openrtb.request.Banner;
import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.openrtb.request.Device;
import com.adsbynimbus.openrtb.request.Format;
import com.adsbynimbus.openrtb.request.Geo;
import com.adsbynimbus.openrtb.request.Impression;
import com.adsbynimbus.openrtb.request.User;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.request.RequestManager;
import com.amplitude.api.DeviceInfo;
import com.google.common.net.HttpHeaders;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001al\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00042\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010#\u001a\u00020\f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004\u001a\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H@¢\u0006\u0002\u00100\u001a<\u00101\u001a\u00020/*\u00020/2\u0006\u00102\u001a\u00020\u00152\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\b\b\u0002\u00103\u001a\u000204H@¢\u0006\u0002\u00105\u001a\u001f\u00106\u001a\b\u0012\u0004\u0012\u0002070\u0003*\u00020/2\u0006\u00102\u001a\u00020\u0015H\u0000¢\u0006\u0002\u00108\u001a\u0014\u00109\u001a\u00020:*\u00020;2\u0006\u0010<\u001a\u00020%H\u0000\u001a\u0018\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040>*\u00020/H\u0000\u001a\f\u0010?\u001a\u00020@*\u00020AH\u0000\"\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u00018\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00038\u0000@\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u0005\"\u0012\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\u0004\u0018\u00010\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000\"\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000\"\u0018\u0010\u000b\u001a\u00020\f*\u00020\r8@X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u0019\u0010\u0010\u001a\u00020\r*\u00020\u00118À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012\"\u0016\u0010\u0013\u001a\u00020\r*\u00020\u00118Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012\"\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0004*\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0018\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8@X\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006B"}, d2 = {"app", "Lcom/adsbynimbus/openrtb/request/App;", "blockedAdvertisers", "", "", "[Ljava/lang/String;", "client", "Lcom/adsbynimbus/request/RequestManager$Client;", "defaultRequestUrl", "user", "Lcom/adsbynimbus/openrtb/request/User;", "byteValue", "", "", "getByteValue", "(Z)B", "isNotEligibleForOM", "Lcom/adsbynimbus/openrtb/request/BidRequest;", "(Lcom/adsbynimbus/openrtb/request/BidRequest;)Z", "isRewardedVideo", "languageCode", "Landroid/content/Context;", "getLanguageCode", "(Landroid/content/Context;)Ljava/lang/String;", "wrappedNetworkError", "Lcom/adsbynimbus/NimbusError;", "", "getWrappedNetworkError", "(Ljava/lang/Throwable;)Lcom/adsbynimbus/NimbusError;", "device", "Lcom/adsbynimbus/openrtb/request/Device;", "adId", "limitAdTracking", "userAgent", "language", "connectionType", "widthPixels", "", "heightPixels", "pixelRatio", "", "manufacturer", "model", "osVersion", "makeRequestInternal", "Lcom/adsbynimbus/request/NimbusResponse;", "request", "Lcom/adsbynimbus/request/NimbusRequest;", "(Lcom/adsbynimbus/request/NimbusRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "build", "context", "preferences", "Landroid/content/SharedPreferences;", "(Lcom/adsbynimbus/request/NimbusRequest;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/SharedPreferences;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endCard", "Lcom/adsbynimbus/openrtb/request/Banner;", "(Lcom/adsbynimbus/request/NimbusRequest;Landroid/content/Context;)[Lcom/adsbynimbus/openrtb/request/Banner;", "format", "Lcom/adsbynimbus/openrtb/request/Format;", "Landroid/content/res/Resources;", "orientation", "headers", "", "removeOMIDFlag", "", "Lcom/adsbynimbus/openrtb/request/Impression;", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestExtensions.kt */
public final class RequestExtensions {
    public static App app;
    public static String[] blockedAdvertisers;
    public static RequestManager.Client client = new OkHttpNimbusClient((OkHttpClient.Builder) null, 1, (DefaultConstructorMarker) null);
    public static String defaultRequestUrl;
    public static User user;

    public static final byte getByteValue(boolean z) {
        return z ? (byte) 1 : 0;
    }

    public static /* synthetic */ Object build$default(NimbusRequest nimbusRequest, Context context, String str, String str2, String str3, SharedPreferences sharedPreferences, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            sharedPreferences = Platform.INSTANCE.getSharedPreferences();
        }
        return build(nimbusRequest, context, str, str2, str3, sharedPreferences, continuation);
    }

    public static final Object build(NimbusRequest nimbusRequest, Context context, String str, String str2, String str3, SharedPreferences sharedPreferences, Continuation<? super NimbusRequest> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new RequestExtensions$build$2(context, nimbusRequest, str, str2, str3, sharedPreferences, (Continuation<? super RequestExtensions$build$2>) null), continuation);
    }

    public static final Object makeRequestInternal(NimbusRequest nimbusRequest, Continuation<? super NimbusResponse> continuation) {
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        client.request(nimbusRequest, new RequestExtensions$makeRequestInternal$2$1(safeContinuation));
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000e, code lost:
        r2 = r2.ext.get("is_rewarded");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isRewardedVideo(com.adsbynimbus.openrtb.request.BidRequest r2) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.adsbynimbus.openrtb.request.Impression[] r2 = r2.imp
            r0 = 0
            r2 = r2[r0]
            com.adsbynimbus.openrtb.request.Video r2 = r2.video
            if (r2 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.lang.Byte> r2 = r2.ext
            java.lang.String r1 = "is_rewarded"
            java.lang.Object r2 = r2.get(r1)
            java.lang.Byte r2 = (java.lang.Byte) r2
            if (r2 == 0) goto L_0x001f
            byte r2 = r2.byteValue()
            goto L_0x0020
        L_0x001f:
            r2 = r0
        L_0x0020:
            if (r2 <= 0) goto L_0x0023
            r0 = 1
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.RequestExtensions.isRewardedVideo(com.adsbynimbus.openrtb.request.BidRequest):boolean");
    }

    public static final Banner[] endCard(NimbusRequest nimbusRequest, Context context) {
        Banner banner;
        Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = true;
        Banner[] bannerArr = new Banner[1];
        if (!(nimbusRequest.getInterstitialOrientation() == 2)) {
            if (context.getResources().getConfiguration().orientation != 2) {
                z = false;
            }
            if (!z) {
                banner = new Banner(320, 480, (Format[]) null, 0.0f, (byte[]) null, (byte) 0, (byte[]) null, (Byte) (byte) 1, 124, (DefaultConstructorMarker) null);
                bannerArr[0] = banner;
                return bannerArr;
            }
        }
        banner = new Banner(480, 320, (Format[]) null, 0.0f, (byte[]) null, (byte) 0, (byte[]) null, (Byte) (byte) 1, 124, (DefaultConstructorMarker) null);
        bannerArr[0] = banner;
        return bannerArr;
    }

    public static /* synthetic */ Device device$default(String str, byte b, String str2, String str3, byte b2, int i, int i2, float f, String str4, String str5, String str6, int i3, Object obj) {
        String str7 = (i3 & 1) != 0 ? Nimbus.EMPTY_AD_ID : str;
        return device(str7, (byte) ((i3 & 2) != 0 ? Intrinsics.areEqual((Object) str7, (Object) Nimbus.EMPTY_AD_ID) : b), (i3 & 4) != 0 ? "" : str2, str3, (i3 & 16) != 0 ? 0 : b2, (i3 & 32) != 0 ? 0 : i, (i3 & 64) != 0 ? 0 : i2, f, str4, str5, str6);
    }

    public static final Device device(String str, byte b, String str2, String str3, byte b2, int i, int i2, float f, String str4, String str5, String str6) {
        String str7 = str;
        Intrinsics.checkNotNullParameter(str7, "adId");
        Intrinsics.checkNotNullParameter(str2, "userAgent");
        Intrinsics.checkNotNullParameter(str4, Device.JsonKeys.MANUFACTURER);
        Intrinsics.checkNotNullParameter(str5, Device.JsonKeys.MODEL);
        Intrinsics.checkNotNullParameter(str6, "osVersion");
        String str8 = str.length() == 36 ? str7 : null;
        if (str8 == null) {
            str8 = Nimbus.EMPTY_AD_ID;
        }
        return new com.adsbynimbus.openrtb.request.Device(str2, str8, str4, str5, (String) null, DeviceInfo.OS_NAME, str6, i2, i, Float.valueOf(f), str3, (byte) 1, b2, (byte) 0, b, (Geo) null, (String) null, (String) null, 237584, (DefaultConstructorMarker) null);
    }

    public static final Map<String, String> headers(NimbusRequest nimbusRequest) {
        String str;
        Intrinsics.checkNotNullParameter(nimbusRequest, "<this>");
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to(BidRequest.OPENRTB_HEADER, BidRequest.OPENRTB_VERSION);
        pairArr[1] = TuplesKt.to(Components.INSTANCE_ID, Platform.INSTANCE.getIid());
        pairArr[2] = TuplesKt.to("Nimbus-Api-Key", nimbusRequest.getApiKey$request_release());
        pairArr[3] = TuplesKt.to("Nimbus-Sdkv", "2.26.1");
        com.adsbynimbus.openrtb.request.Device device = nimbusRequest.request.device;
        if (device == null || (str = device.ua) == null) {
            str = Platform.INSTANCE.getUserAgent();
        }
        pairArr[4] = TuplesKt.to(HttpHeaders.USER_AGENT, str);
        return MapsKt.mapOf(pairArr);
    }

    public static final String getLanguageCode(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getConfiguration().getLocales().get(0).getLanguage();
    }

    public static final Format format(Resources resources, int i) {
        Intrinsics.checkNotNullParameter(resources, "<this>");
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        boolean z = true;
        if (!(i == 0 || i == resources.getConfiguration().orientation)) {
            z = false;
        }
        if (z) {
            return new Format(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        return new Format(displayMetrics.heightPixels, displayMetrics.widthPixels);
    }

    public static final boolean isNotEligibleForOM(BidRequest bidRequest) {
        Intrinsics.checkNotNullParameter(bidRequest, "<this>");
        return !Nimbus.getThirdPartyViewabilityEnabled() || bidRequest.source == null;
    }

    public static final void removeOMIDFlag(Impression impression) {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        Intrinsics.checkNotNullParameter(impression, "<this>");
        Banner banner = impression.banner;
        byte[] bArr4 = null;
        if (banner != null) {
            Banner banner2 = impression.banner;
            if (banner2 == null || (bArr3 = banner2.api) == null) {
                bArr2 = null;
            } else {
                Collection arrayList = new ArrayList();
                int length = bArr3.length;
                for (int i = 0; i < length; i++) {
                    byte b = bArr3[i];
                    if (!(b == 7)) {
                        arrayList.add(Byte.valueOf(b));
                    }
                }
                bArr2 = CollectionsKt.toByteArray((List) arrayList);
            }
            banner.api = bArr2;
        }
        Video video = impression.video;
        if (video != null) {
            Video video2 = impression.video;
            if (!(video2 == null || (bArr = video2.api) == null)) {
                Collection arrayList2 = new ArrayList();
                int length2 = bArr.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    byte b2 = bArr[i2];
                    if (!(b2 == 7)) {
                        arrayList2.add(Byte.valueOf(b2));
                    }
                }
                List list = (List) arrayList2;
                if (!(!list.isEmpty())) {
                    list = null;
                }
                if (list != null) {
                    bArr4 = CollectionsKt.toByteArray(list);
                }
            }
            video.api = bArr4;
        }
    }

    public static final NimbusError getWrappedNetworkError(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        NimbusError.ErrorType errorType = NimbusError.ErrorType.NETWORK_ERROR;
        String message = th.getMessage();
        if (message == null) {
            message = "Error sending request to Nimbus";
        }
        return new NimbusError(errorType, message, th);
    }
}
