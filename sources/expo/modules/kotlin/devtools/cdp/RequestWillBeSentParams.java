package expo.modules.kotlin.devtools.cdp;

import io.sentry.SentryBaseEvent;
import java.math.BigDecimal;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bB\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u000e\u0012\n\u0010\u000f\u001a\u00060\u0003j\u0002`\u0010\u0012\n\u0010\u0011\u001a\u00060\u0003j\u0002`\u0012\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\r\u0010.\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\u001aHÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\u000eHÆ\u0003J\r\u00104\u001a\u00060\u0003j\u0002`\u0010HÆ\u0003J\r\u00105\u001a\u00060\u0003j\u0002`\u0012HÆ\u0003J\u0015\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014HÆ\u0003J\t\u00107\u001a\u00020\u0016HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u0001\u00109\u001a\u00020\u00002\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u000e2\f\b\u0002\u0010\u000f\u001a\u00060\u0003j\u0002`\u00102\f\b\u0002\u0010\u0011\u001a\u00060\u0003j\u0002`\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u001aHÆ\u0001J\u0013\u0010:\u001a\u00020\u00162\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0003J\t\u0010=\u001a\u00020>HÖ\u0001J\b\u0010?\u001a\u00020@H\u0016J\t\u0010A\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0011\u0010\u0007\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0015\u0010\u000f\u001a\u00060\u0003j\u0002`\u0010¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0015\u0010\u0011\u001a\u00060\u0003j\u0002`\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010*¨\u0006B"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/RequestWillBeSentParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "now", "Ljava/math/BigDecimal;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", "request", "Lokhttp3/Request;", "redirectResponse", "Lokhttp3/Response;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Request;Lokhttp3/Response;)V", "loaderId", "documentURL", "Lexpo/modules/kotlin/devtools/cdp/Request;", "timestamp", "Lexpo/modules/kotlin/devtools/cdp/MonotonicTime;", "wallTime", "Lexpo/modules/kotlin/devtools/cdp/TimeSinceEpoch;", "initiator", "", "redirectHasExtraInfo", "", "Lexpo/modules/kotlin/devtools/cdp/Response;", "referrerPolicy", "type", "Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lexpo/modules/kotlin/devtools/cdp/Request;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/util/Map;ZLexpo/modules/kotlin/devtools/cdp/Response;Ljava/lang/String;Lexpo/modules/kotlin/devtools/cdp/ResourceType;)V", "getDocumentURL", "()Ljava/lang/String;", "getInitiator", "()Ljava/util/Map;", "getLoaderId", "getRedirectHasExtraInfo", "()Z", "getRedirectResponse", "()Lexpo/modules/kotlin/devtools/cdp/Response;", "getReferrerPolicy", "getRequest", "()Lexpo/modules/kotlin/devtools/cdp/Request;", "getRequestId", "getTimestamp", "()Ljava/math/BigDecimal;", "getType", "()Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "getWallTime", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public final class RequestWillBeSentParams implements JsonSerializable {
    private final String documentURL;
    private final Map<String, String> initiator;
    private final String loaderId;
    private final boolean redirectHasExtraInfo;
    private final Response redirectResponse;
    private final String referrerPolicy;
    private final Request request;
    private final String requestId;
    private final BigDecimal timestamp;
    private final ResourceType type;
    private final BigDecimal wallTime;

    public static /* synthetic */ RequestWillBeSentParams copy$default(RequestWillBeSentParams requestWillBeSentParams, String str, String str2, String str3, Request request2, BigDecimal bigDecimal, BigDecimal bigDecimal2, Map map, boolean z, Response response, String str4, ResourceType resourceType, int i, Object obj) {
        RequestWillBeSentParams requestWillBeSentParams2 = requestWillBeSentParams;
        int i2 = i;
        return requestWillBeSentParams.copy((i2 & 1) != 0 ? requestWillBeSentParams2.requestId : str, (i2 & 2) != 0 ? requestWillBeSentParams2.loaderId : str2, (i2 & 4) != 0 ? requestWillBeSentParams2.documentURL : str3, (i2 & 8) != 0 ? requestWillBeSentParams2.request : request2, (i2 & 16) != 0 ? requestWillBeSentParams2.timestamp : bigDecimal, (i2 & 32) != 0 ? requestWillBeSentParams2.wallTime : bigDecimal2, (i2 & 64) != 0 ? requestWillBeSentParams2.initiator : map, (i2 & 128) != 0 ? requestWillBeSentParams2.redirectHasExtraInfo : z, (i2 & 256) != 0 ? requestWillBeSentParams2.redirectResponse : response, (i2 & 512) != 0 ? requestWillBeSentParams2.referrerPolicy : str4, (i2 & 1024) != 0 ? requestWillBeSentParams2.type : resourceType);
    }

    public final String component1() {
        return this.requestId;
    }

    public final String component10() {
        return this.referrerPolicy;
    }

    public final ResourceType component11() {
        return this.type;
    }

    public final String component2() {
        return this.loaderId;
    }

    public final String component3() {
        return this.documentURL;
    }

    public final Request component4() {
        return this.request;
    }

    public final BigDecimal component5() {
        return this.timestamp;
    }

    public final BigDecimal component6() {
        return this.wallTime;
    }

    public final Map<String, String> component7() {
        return this.initiator;
    }

    public final boolean component8() {
        return this.redirectHasExtraInfo;
    }

    public final Response component9() {
        return this.redirectResponse;
    }

    public final RequestWillBeSentParams copy(String str, String str2, String str3, Request request2, BigDecimal bigDecimal, BigDecimal bigDecimal2, Map<String, String> map, boolean z, Response response, String str4, ResourceType resourceType) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "loaderId");
        String str5 = str3;
        Intrinsics.checkNotNullParameter(str5, "documentURL");
        Request request3 = request2;
        Intrinsics.checkNotNullParameter(request3, SentryBaseEvent.JsonKeys.REQUEST);
        BigDecimal bigDecimal3 = bigDecimal;
        Intrinsics.checkNotNullParameter(bigDecimal3, "timestamp");
        BigDecimal bigDecimal4 = bigDecimal2;
        Intrinsics.checkNotNullParameter(bigDecimal4, "wallTime");
        Map<String, String> map2 = map;
        Intrinsics.checkNotNullParameter(map2, "initiator");
        String str6 = str4;
        Intrinsics.checkNotNullParameter(str6, "referrerPolicy");
        ResourceType resourceType2 = resourceType;
        Intrinsics.checkNotNullParameter(resourceType2, "type");
        return new RequestWillBeSentParams(str, str2, str5, request3, bigDecimal3, bigDecimal4, map2, z, response, str6, resourceType2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RequestWillBeSentParams)) {
            return false;
        }
        RequestWillBeSentParams requestWillBeSentParams = (RequestWillBeSentParams) obj;
        return Intrinsics.areEqual((Object) this.requestId, (Object) requestWillBeSentParams.requestId) && Intrinsics.areEqual((Object) this.loaderId, (Object) requestWillBeSentParams.loaderId) && Intrinsics.areEqual((Object) this.documentURL, (Object) requestWillBeSentParams.documentURL) && Intrinsics.areEqual((Object) this.request, (Object) requestWillBeSentParams.request) && Intrinsics.areEqual((Object) this.timestamp, (Object) requestWillBeSentParams.timestamp) && Intrinsics.areEqual((Object) this.wallTime, (Object) requestWillBeSentParams.wallTime) && Intrinsics.areEqual((Object) this.initiator, (Object) requestWillBeSentParams.initiator) && this.redirectHasExtraInfo == requestWillBeSentParams.redirectHasExtraInfo && Intrinsics.areEqual((Object) this.redirectResponse, (Object) requestWillBeSentParams.redirectResponse) && Intrinsics.areEqual((Object) this.referrerPolicy, (Object) requestWillBeSentParams.referrerPolicy) && this.type == requestWillBeSentParams.type;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((this.requestId.hashCode() * 31) + this.loaderId.hashCode()) * 31) + this.documentURL.hashCode()) * 31) + this.request.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + this.wallTime.hashCode()) * 31) + this.initiator.hashCode()) * 31) + Boolean.hashCode(this.redirectHasExtraInfo)) * 31;
        Response response = this.redirectResponse;
        return ((((hashCode + (response == null ? 0 : response.hashCode())) * 31) + this.referrerPolicy.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        String str = this.requestId;
        String str2 = this.loaderId;
        String str3 = this.documentURL;
        Request request2 = this.request;
        BigDecimal bigDecimal = this.timestamp;
        BigDecimal bigDecimal2 = this.wallTime;
        Map<String, String> map = this.initiator;
        boolean z = this.redirectHasExtraInfo;
        Response response = this.redirectResponse;
        String str4 = this.referrerPolicy;
        return "RequestWillBeSentParams(requestId=" + str + ", loaderId=" + str2 + ", documentURL=" + str3 + ", request=" + request2 + ", timestamp=" + bigDecimal + ", wallTime=" + bigDecimal2 + ", initiator=" + map + ", redirectHasExtraInfo=" + z + ", redirectResponse=" + response + ", referrerPolicy=" + str4 + ", type=" + this.type + ")";
    }

    public RequestWillBeSentParams(String str, String str2, String str3, Request request2, BigDecimal bigDecimal, BigDecimal bigDecimal2, Map<String, String> map, boolean z, Response response, String str4, ResourceType resourceType) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "loaderId");
        Intrinsics.checkNotNullParameter(str3, "documentURL");
        Intrinsics.checkNotNullParameter(request2, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(bigDecimal, "timestamp");
        Intrinsics.checkNotNullParameter(bigDecimal2, "wallTime");
        Intrinsics.checkNotNullParameter(map, "initiator");
        Intrinsics.checkNotNullParameter(str4, "referrerPolicy");
        Intrinsics.checkNotNullParameter(resourceType, "type");
        this.requestId = str;
        this.loaderId = str2;
        this.documentURL = str3;
        this.request = request2;
        this.timestamp = bigDecimal;
        this.wallTime = bigDecimal2;
        this.initiator = map;
        this.redirectHasExtraInfo = z;
        this.redirectResponse = response;
        this.referrerPolicy = str4;
        this.type = resourceType;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getLoaderId() {
        return this.loaderId;
    }

    public final String getDocumentURL() {
        return this.documentURL;
    }

    public final Request getRequest() {
        return this.request;
    }

    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    public final BigDecimal getWallTime() {
        return this.wallTime;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RequestWillBeSentParams(java.lang.String r15, java.lang.String r16, java.lang.String r17, expo.modules.kotlin.devtools.cdp.Request r18, java.math.BigDecimal r19, java.math.BigDecimal r20, java.util.Map r21, boolean r22, expo.modules.kotlin.devtools.cdp.Response r23, java.lang.String r24, expo.modules.kotlin.devtools.cdp.ResourceType r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 2
            if (r1 == 0) goto L_0x000a
            java.lang.String r1 = ""
            r4 = r1
            goto L_0x000c
        L_0x000a:
            r4 = r16
        L_0x000c:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x0014
            java.lang.String r1 = "mobile"
            r5 = r1
            goto L_0x0016
        L_0x0014:
            r5 = r17
        L_0x0016:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0028
            java.lang.String r1 = "type"
            java.lang.String r2 = "script"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r1)
            r9 = r1
            goto L_0x002a
        L_0x0028:
            r9 = r21
        L_0x002a:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = "no-referrer"
            r12 = r0
            goto L_0x0034
        L_0x0032:
            r12 = r24
        L_0x0034:
            r2 = r14
            r3 = r15
            r6 = r18
            r7 = r19
            r8 = r20
            r10 = r22
            r11 = r23
            r13 = r25
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.RequestWillBeSentParams.<init>(java.lang.String, java.lang.String, java.lang.String, expo.modules.kotlin.devtools.cdp.Request, java.math.BigDecimal, java.math.BigDecimal, java.util.Map, boolean, expo.modules.kotlin.devtools.cdp.Response, java.lang.String, expo.modules.kotlin.devtools.cdp.ResourceType, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Map<String, String> getInitiator() {
        return this.initiator;
    }

    public final boolean getRedirectHasExtraInfo() {
        return this.redirectHasExtraInfo;
    }

    public final Response getRedirectResponse() {
        return this.redirectResponse;
    }

    public final String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    public final ResourceType getType() {
        return this.type;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RequestWillBeSentParams(java.math.BigDecimal r18, java.lang.String r19, okhttp3.Request r20, okhttp3.Response r21) {
        /*
            r17 = this;
            r0 = r20
            r1 = r21
            java.lang.String r2 = "now"
            r9 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            java.lang.String r2 = "requestId"
            r4 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
            java.lang.String r2 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r5 = 0
            r6 = 0
            expo.modules.kotlin.devtools.cdp.Request r7 = new expo.modules.kotlin.devtools.cdp.Request
            r7.<init>(r0)
            r10 = 0
            if (r1 == 0) goto L_0x0023
            r0 = 1
            goto L_0x0024
        L_0x0023:
            r0 = 0
        L_0x0024:
            r11 = r0
            if (r1 == 0) goto L_0x002d
            expo.modules.kotlin.devtools.cdp.Response r0 = new expo.modules.kotlin.devtools.cdp.Response
            r0.<init>(r1)
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            r12 = r0
            r13 = 0
            expo.modules.kotlin.devtools.cdp.ResourceType r14 = expo.modules.kotlin.devtools.cdp.ResourceType.OTHER
            r15 = 582(0x246, float:8.16E-43)
            r16 = 0
            r3 = r17
            r4 = r19
            r8 = r18
            r9 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.RequestWillBeSentParams.<init>(java.math.BigDecimal, java.lang.String, okhttp3.Request, okhttp3.Response):void");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("loaderId", this.loaderId);
        jSONObject.put("documentURL", this.documentURL);
        jSONObject.put(SentryBaseEvent.JsonKeys.REQUEST, this.request.toJSONObject());
        jSONObject.put("timestamp", this.timestamp);
        jSONObject.put("wallTime", this.wallTime);
        jSONObject.put("initiator", new JSONObject(this.initiator));
        jSONObject.put("redirectHasExtraInfo", this.redirectHasExtraInfo);
        Response response = this.redirectResponse;
        if (response != null) {
            jSONObject.put("redirectResponse", response.toJSONObject());
        }
        jSONObject.put("referrerPolicy", this.referrerPolicy);
        jSONObject.put("type", this.type.getValue());
        return jSONObject;
    }
}
