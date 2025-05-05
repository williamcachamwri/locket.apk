package androidx.credentials.webauthn;

import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0007R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/credentials/webauthn/FidoPublicKeyCredential;", "", "rawId", "", "response", "Landroidx/credentials/webauthn/AuthenticatorResponse;", "authenticatorAttachment", "", "([BLandroidx/credentials/webauthn/AuthenticatorResponse;Ljava/lang/String;)V", "getAuthenticatorAttachment", "()Ljava/lang/String;", "getRawId", "()[B", "getResponse", "()Landroidx/credentials/webauthn/AuthenticatorResponse;", "json", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FidoPublicKeyCredential.kt */
public final class FidoPublicKeyCredential {
    private final String authenticatorAttachment;
    private final byte[] rawId;
    private final AuthenticatorResponse response;

    public FidoPublicKeyCredential(byte[] bArr, AuthenticatorResponse authenticatorResponse, String str) {
        Intrinsics.checkNotNullParameter(bArr, "rawId");
        Intrinsics.checkNotNullParameter(authenticatorResponse, Response.TYPE);
        Intrinsics.checkNotNullParameter(str, "authenticatorAttachment");
        this.rawId = bArr;
        this.response = authenticatorResponse;
        this.authenticatorAttachment = str;
    }

    public final byte[] getRawId() {
        return this.rawId;
    }

    public final AuthenticatorResponse getResponse() {
        return this.response;
    }

    public final String getAuthenticatorAttachment() {
        return this.authenticatorAttachment;
    }

    public final String json() {
        String b64Encode = WebAuthnUtils.Companion.b64Encode(this.rawId);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", b64Encode);
        jSONObject.put("rawId", b64Encode);
        jSONObject.put("type", "public-key");
        jSONObject.put("authenticatorAttachment", this.authenticatorAttachment);
        jSONObject.put(Response.TYPE, this.response.json());
        jSONObject.put("clientExtensionResults", new JSONObject());
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "ret.toString()");
        return jSONObject2;
    }
}
