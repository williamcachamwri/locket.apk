package expo.modules.kotlin.devtools.cdp;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B+\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB%\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\n\u0010\f\u001a\u00060\u0003j\u0002`\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\r\u0010\u0017\u001a\u00060\u0005j\u0002`\u0006HÆ\u0003J\r\u0010\u0018\u001a\u00060\u0003j\u0002`\rHÆ\u0003J\t\u0010\u0019\u001a\u00020\u000fHÆ\u0003J/\u0010\u001a\u001a\u00020\u00002\f\b\u0002\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\f\b\u0002\u0010\f\u001a\u00060\u0003j\u0002`\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\b\u0010!\u001a\u00020\"H\u0016J\t\u0010#\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\f\u001a\u00060\u0003j\u0002`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/LoadingFinishedParams;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "now", "Ljava/math/BigDecimal;", "requestId", "", "Lexpo/modules/kotlin/devtools/cdp/RequestId;", "request", "Lokhttp3/Request;", "response", "Lokhttp3/Response;", "(Ljava/math/BigDecimal;Ljava/lang/String;Lokhttp3/Request;Lokhttp3/Response;)V", "timestamp", "Lexpo/modules/kotlin/devtools/cdp/MonotonicTime;", "encodedDataLength", "", "(Ljava/lang/String;Ljava/math/BigDecimal;J)V", "getEncodedDataLength", "()J", "getRequestId", "()Ljava/lang/String;", "getTimestamp", "()Ljava/math/BigDecimal;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public final class LoadingFinishedParams implements JsonSerializable {
    private final long encodedDataLength;
    private final String requestId;
    private final BigDecimal timestamp;

    public static /* synthetic */ LoadingFinishedParams copy$default(LoadingFinishedParams loadingFinishedParams, String str, BigDecimal bigDecimal, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loadingFinishedParams.requestId;
        }
        if ((i & 2) != 0) {
            bigDecimal = loadingFinishedParams.timestamp;
        }
        if ((i & 4) != 0) {
            j = loadingFinishedParams.encodedDataLength;
        }
        return loadingFinishedParams.copy(str, bigDecimal, j);
    }

    public final String component1() {
        return this.requestId;
    }

    public final BigDecimal component2() {
        return this.timestamp;
    }

    public final long component3() {
        return this.encodedDataLength;
    }

    public final LoadingFinishedParams copy(String str, BigDecimal bigDecimal, long j) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(bigDecimal, "timestamp");
        return new LoadingFinishedParams(str, bigDecimal, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadingFinishedParams)) {
            return false;
        }
        LoadingFinishedParams loadingFinishedParams = (LoadingFinishedParams) obj;
        return Intrinsics.areEqual((Object) this.requestId, (Object) loadingFinishedParams.requestId) && Intrinsics.areEqual((Object) this.timestamp, (Object) loadingFinishedParams.timestamp) && this.encodedDataLength == loadingFinishedParams.encodedDataLength;
    }

    public int hashCode() {
        return (((this.requestId.hashCode() * 31) + this.timestamp.hashCode()) * 31) + Long.hashCode(this.encodedDataLength);
    }

    public String toString() {
        String str = this.requestId;
        BigDecimal bigDecimal = this.timestamp;
        return "LoadingFinishedParams(requestId=" + str + ", timestamp=" + bigDecimal + ", encodedDataLength=" + this.encodedDataLength + ")";
    }

    public LoadingFinishedParams(String str, BigDecimal bigDecimal, long j) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(bigDecimal, "timestamp");
        this.requestId = str;
        this.timestamp = bigDecimal;
        this.encodedDataLength = j;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final BigDecimal getTimestamp() {
        return this.timestamp;
    }

    public final long getEncodedDataLength() {
        return this.encodedDataLength;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LoadingFinishedParams(java.math.BigDecimal r2, java.lang.String r3, okhttp3.Request r4, okhttp3.Response r5) {
        /*
            r1 = this;
            java.lang.String r0 = "now"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "requestId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "request"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r4 = "response"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
            okhttp3.ResponseBody r4 = r5.body()
            if (r4 == 0) goto L_0x001f
            long r4 = r4.contentLength()
            goto L_0x0021
        L_0x001f:
            r4 = 0
        L_0x0021:
            r1.<init>(r3, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.devtools.cdp.LoadingFinishedParams.<init>(java.math.BigDecimal, java.lang.String, okhttp3.Request, okhttp3.Response):void");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestId", this.requestId);
        jSONObject.put("timestamp", this.timestamp);
        jSONObject.put("encodedDataLength", this.encodedDataLength);
        return jSONObject;
    }
}
