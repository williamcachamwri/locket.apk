package expo.modules.kotlin.devtools.cdp;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB!\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\r\u0010\u0019\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u000eHÆ\u0003J+\u0010\u001c\u001a\u00020\u00002\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u000e2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\b\u0010\"\u001a\u00020#H\u0016J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\f\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015¨\u0006%"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ExpoReceivedResponseBodyParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "now", "Ljava/math/BigDecimal;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Request;Lokhttp3/Response;)V", "body", "base64Encoded", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getBase64Encoded", "()Z", "setBase64Encoded", "(Z)V", "getBody", "()Ljava/lang/String;", "setBody", "(Ljava/lang/String;)V", "getRequestId", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public final class ExpoReceivedResponseBodyParams implements JsonSerializable {
    private boolean base64Encoded;
    private String body;
    private final String requestId;

    public static /* synthetic */ ExpoReceivedResponseBodyParams copy$default(ExpoReceivedResponseBodyParams expoReceivedResponseBodyParams, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = expoReceivedResponseBodyParams.requestId;
        }
        if ((i & 2) != 0) {
            str2 = expoReceivedResponseBodyParams.body;
        }
        if ((i & 4) != 0) {
            z = expoReceivedResponseBodyParams.base64Encoded;
        }
        return expoReceivedResponseBodyParams.copy(str, str2, z);
    }

    public final String component1() {
        return this.requestId;
    }

    public final String component2() {
        return this.body;
    }

    public final boolean component3() {
        return this.base64Encoded;
    }

    public final ExpoReceivedResponseBodyParams copy(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.TAG_BODY);
        return new ExpoReceivedResponseBodyParams(str, str2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExpoReceivedResponseBodyParams)) {
            return false;
        }
        ExpoReceivedResponseBodyParams expoReceivedResponseBodyParams = (ExpoReceivedResponseBodyParams) obj;
        return Intrinsics.areEqual((Object) this.requestId, (Object) expoReceivedResponseBodyParams.requestId) && Intrinsics.areEqual((Object) this.body, (Object) expoReceivedResponseBodyParams.body) && this.base64Encoded == expoReceivedResponseBodyParams.base64Encoded;
    }

    public int hashCode() {
        return (((this.requestId.hashCode() * 31) + this.body.hashCode()) * 31) + Boolean.hashCode(this.base64Encoded);
    }

    public String toString() {
        String str = this.requestId;
        String str2 = this.body;
        return "ExpoReceivedResponseBodyParams(requestId=" + str + ", body=" + str2 + ", base64Encoded=" + this.base64Encoded + ")";
    }

    public ExpoReceivedResponseBodyParams(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, TtmlNode.TAG_BODY);
        this.requestId = str;
        this.body = str2;
        this.base64Encoded = z;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getBody() {
        return this.body;
    }

    public final void setBody(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.body = str;
    }

    public final boolean getBase64Encoded() {
        return this.base64Encoded;
    }

    public final void setBase64Encoded(boolean z) {
        this.base64Encoded = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004f, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4.subtype(), (java.lang.Object) "json") != false) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0059  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExpoReceivedResponseBodyParams(java.math.BigDecimal r3, java.lang.String r4, okhttp3.Request r5, okhttp3.Response r6) {
        /*
            r2 = this;
            java.lang.String r0 = "now"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r3 = "requestId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
            java.lang.String r3 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
            java.lang.String r3 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r3)
            java.lang.String r3 = ""
            r5 = 0
            r2.<init>(r4, r3, r5)
            r3 = 1048576(0x100000, double:5.180654E-318)
            okhttp3.ResponseBody r3 = r6.peekBody(r3)
            okhttp3.MediaType r4 = r3.contentType()
            r6 = 0
            if (r4 == 0) goto L_0x002d
            java.lang.String r0 = r4.type()
            goto L_0x002e
        L_0x002d:
            r0 = r6
        L_0x002e:
            java.lang.String r1 = "text"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            r1 = 1
            if (r0 != 0) goto L_0x0051
            if (r4 == 0) goto L_0x003d
            java.lang.String r6 = r4.type()
        L_0x003d:
            java.lang.String r0 = "application"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r0)
            if (r6 == 0) goto L_0x0052
            java.lang.String r4 = r4.subtype()
            java.lang.String r6 = "json"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)
            if (r4 == 0) goto L_0x0052
        L_0x0051:
            r5 = r1
        L_0x0052:
            if (r5 == 0) goto L_0x0059
            java.lang.String r3 = r3.string()
            goto L_0x0065
        L_0x0059:
            okio.BufferedSource r3 = r3.source()
            okio.ByteString r3 = r3.readByteString()
            java.lang.String r3 = r3.base64()
        L_0x0065:
            r2.body = r3
            r3 = r5 ^ 1
            r2.base64Encoded = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.ExpoReceivedResponseBodyParams.<init>(java.math.BigDecimal, java.lang.String, okhttp3.Request, okhttp3.Response):void");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put(TtmlNode.TAG_BODY, this.body);
        jSONObject.put("base64Encoded", this.base64Encoded);
        return jSONObject;
    }
}
