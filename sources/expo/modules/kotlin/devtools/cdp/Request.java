package expo.modules.kotlin.devtools.cdp;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B7\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tj\u0002`\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\fJ\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tj\u0002`\nHÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003JC\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tj\u0002`\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\t\u0010 \u001a\u00020\u0006HÖ\u0001R!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tj\u0002`\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010¨\u0006!"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/Request;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "url", "", "method", "headers", "", "Lexpo/modules/kotlin/devtools/cdp/Headers;", "postData", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "getHeaders", "()Ljava/util/Map;", "getMethod", "()Ljava/lang/String;", "getPostData", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public final class Request implements JsonSerializable {
    private final Map<String, String> headers;
    private final String method;
    private final String postData;
    private final String url;

    public static /* synthetic */ Request copy$default(Request request, String str, String str2, Map<String, String> map, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = request.url;
        }
        if ((i & 2) != 0) {
            str2 = request.method;
        }
        if ((i & 4) != 0) {
            map = request.headers;
        }
        if ((i & 8) != 0) {
            str3 = request.postData;
        }
        return request.copy(str, str2, map, str3);
    }

    public final String component1() {
        return this.url;
    }

    public final String component2() {
        return this.method;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    public final String component4() {
        return this.postData;
    }

    public final Request copy(String str, String str2, Map<String, String> map, String str3) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(map, "headers");
        return new Request(str, str2, map, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Request)) {
            return false;
        }
        Request request = (Request) obj;
        return Intrinsics.areEqual((Object) this.url, (Object) request.url) && Intrinsics.areEqual((Object) this.method, (Object) request.method) && Intrinsics.areEqual((Object) this.headers, (Object) request.headers) && Intrinsics.areEqual((Object) this.postData, (Object) request.postData);
    }

    public int hashCode() {
        int hashCode = ((((this.url.hashCode() * 31) + this.method.hashCode()) * 31) + this.headers.hashCode()) * 31;
        String str = this.postData;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.url;
        String str2 = this.method;
        Map<String, String> map = this.headers;
        return "Request(url=" + str + ", method=" + str2 + ", headers=" + map + ", postData=" + this.postData + ")";
    }

    public Request(String str, String str2, Map<String, String> map, String str3) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(map, "headers");
        this.url = str;
        this.method = str2;
        this.headers = map;
        this.postData = str3;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getMethod() {
        return this.method;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getPostData() {
        return this.postData;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Request(okhttp3.Request r9) {
        /*
            r8 = this;
            java.lang.String r0 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            okhttp3.HttpUrl r0 = r9.url()
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r9.method()
            okhttp3.Headers r2 = r9.headers()
            java.util.Map r2 = expo.modules.kotlin.devtools.OkHttpHeadersExtensionKt.toSingleMap(r2)
            okhttp3.RequestBody r9 = r9.body()
            r3 = 0
            if (r9 == 0) goto L_0x0043
            long r4 = r9.contentLength()
            r6 = 1048576(0x100000, double:5.180654E-318)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x0043
            okio.Buffer r3 = new okio.Buffer
            r3.<init>()
            r4 = r3
            okio.BufferedSink r4 = (okio.BufferedSink) r4
            r9.writeTo(r4)
            long r4 = r3.size()
            long r4 = kotlin.ranges.RangesKt.coerceAtMost((long) r4, (long) r6)
            java.lang.String r9 = r3.readUtf8(r4)
            r3 = r9
        L_0x0043:
            r8.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.Request.<init>(okhttp3.Request):void");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("method", this.method);
        jSONObject.put("headers", new JSONObject(this.headers));
        String str = this.postData;
        if (str != null) {
            jSONObject.put("postData", str);
        }
        return jSONObject;
    }
}
