package androidx.credentials.webauthn;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Landroidx/credentials/webauthn/PublicKeyCredentialParameters;", "", "type", "", "alg", "", "(Ljava/lang/String;J)V", "getAlg", "()J", "getType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FidoDataTypes.kt */
public final class PublicKeyCredentialParameters {
    private final long alg;
    private final String type;

    public static /* synthetic */ PublicKeyCredentialParameters copy$default(PublicKeyCredentialParameters publicKeyCredentialParameters, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = publicKeyCredentialParameters.type;
        }
        if ((i & 2) != 0) {
            j = publicKeyCredentialParameters.alg;
        }
        return publicKeyCredentialParameters.copy(str, j);
    }

    public final String component1() {
        return this.type;
    }

    public final long component2() {
        return this.alg;
    }

    public final PublicKeyCredentialParameters copy(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "type");
        return new PublicKeyCredentialParameters(str, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PublicKeyCredentialParameters)) {
            return false;
        }
        PublicKeyCredentialParameters publicKeyCredentialParameters = (PublicKeyCredentialParameters) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) publicKeyCredentialParameters.type) && this.alg == publicKeyCredentialParameters.alg;
    }

    public int hashCode() {
        return (this.type.hashCode() * 31) + Long.hashCode(this.alg);
    }

    public String toString() {
        return "PublicKeyCredentialParameters(type=" + this.type + ", alg=" + this.alg + ')';
    }

    public PublicKeyCredentialParameters(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "type");
        this.type = str;
        this.alg = j;
    }

    public final long getAlg() {
        return this.alg;
    }

    public final String getType() {
        return this.type;
    }
}
