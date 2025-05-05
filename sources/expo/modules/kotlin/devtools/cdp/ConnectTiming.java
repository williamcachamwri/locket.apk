package expo.modules.kotlin.devtools.cdp;

import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ConnectTiming;", "Lexpo/modules/kotlin/devtools/cdp/JsonSerializable;", "requestTime", "Ljava/math/BigDecimal;", "Lexpo/modules/kotlin/devtools/cdp/MonotonicTime;", "(Ljava/math/BigDecimal;)V", "getRequestTime", "()Ljava/math/BigDecimal;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public final class ConnectTiming implements JsonSerializable {
    private final BigDecimal requestTime;

    public static /* synthetic */ ConnectTiming copy$default(ConnectTiming connectTiming, BigDecimal bigDecimal, int i, Object obj) {
        if ((i & 1) != 0) {
            bigDecimal = connectTiming.requestTime;
        }
        return connectTiming.copy(bigDecimal);
    }

    public final BigDecimal component1() {
        return this.requestTime;
    }

    public final ConnectTiming copy(BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "requestTime");
        return new ConnectTiming(bigDecimal);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ConnectTiming) && Intrinsics.areEqual((Object) this.requestTime, (Object) ((ConnectTiming) obj).requestTime);
    }

    public int hashCode() {
        return this.requestTime.hashCode();
    }

    public String toString() {
        return "ConnectTiming(requestTime=" + this.requestTime + ")";
    }

    public ConnectTiming(BigDecimal bigDecimal) {
        Intrinsics.checkNotNullParameter(bigDecimal, "requestTime");
        this.requestTime = bigDecimal;
    }

    public final BigDecimal getRequestTime() {
        return this.requestTime;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("requestTime", this.requestTime);
        return jSONObject;
    }
}
