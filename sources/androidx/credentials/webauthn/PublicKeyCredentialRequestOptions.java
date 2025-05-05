package androidx.credentials.webauthn;

import androidx.credentials.webauthn.WebAuthnUtils;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import io.sentry.ProfilingTraceData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000f¨\u0006\u0016"}, d2 = {"Landroidx/credentials/webauthn/PublicKeyCredentialRequestOptions;", "", "requestJson", "", "(Ljava/lang/String;)V", "challenge", "", "getChallenge", "()[B", "json", "Lorg/json/JSONObject;", "getJson", "()Lorg/json/JSONObject;", "rpId", "getRpId", "()Ljava/lang/String;", "timeout", "", "getTimeout", "()J", "userVerification", "getUserVerification", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PublicKeyCredentialRequestOptions.kt */
public final class PublicKeyCredentialRequestOptions {
    private final byte[] challenge;
    private final JSONObject json;
    private final String rpId;
    private final long timeout;
    private final String userVerification;

    public PublicKeyCredentialRequestOptions(String str) {
        Intrinsics.checkNotNullParameter(str, "requestJson");
        JSONObject jSONObject = new JSONObject(str);
        this.json = jSONObject;
        String string = jSONObject.getString(ClientData.KEY_CHALLENGE);
        WebAuthnUtils.Companion companion = WebAuthnUtils.Companion;
        Intrinsics.checkNotNullExpressionValue(string, "challengeString");
        this.challenge = companion.b64Decode(string);
        this.timeout = jSONObject.optLong(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, 0);
        String optString = jSONObject.optString("rpId", "");
        Intrinsics.checkNotNullExpressionValue(optString, "json.optString(\"rpId\", \"\")");
        this.rpId = optString;
        String optString2 = jSONObject.optString("userVerification", "preferred");
        Intrinsics.checkNotNullExpressionValue(optString2, "json.optString(\"userVerification\", \"preferred\")");
        this.userVerification = optString2;
    }

    public final JSONObject getJson() {
        return this.json;
    }

    public final byte[] getChallenge() {
        return this.challenge;
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final String getRpId() {
        return this.rpId;
    }

    public final String getUserVerification() {
        return this.userVerification;
    }
}
