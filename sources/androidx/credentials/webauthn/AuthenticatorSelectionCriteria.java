package androidx.credentials.webauthn;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Landroidx/credentials/webauthn/AuthenticatorSelectionCriteria;", "", "authenticatorAttachment", "", "residentKey", "requireResidentKey", "", "userVerification", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAuthenticatorAttachment", "()Ljava/lang/String;", "getRequireResidentKey", "()Z", "getResidentKey", "getUserVerification", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FidoDataTypes.kt */
public final class AuthenticatorSelectionCriteria {
    private final String authenticatorAttachment;
    private final boolean requireResidentKey;
    private final String residentKey;
    private final String userVerification;

    public static /* synthetic */ AuthenticatorSelectionCriteria copy$default(AuthenticatorSelectionCriteria authenticatorSelectionCriteria, String str, String str2, boolean z, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = authenticatorSelectionCriteria.authenticatorAttachment;
        }
        if ((i & 2) != 0) {
            str2 = authenticatorSelectionCriteria.residentKey;
        }
        if ((i & 4) != 0) {
            z = authenticatorSelectionCriteria.requireResidentKey;
        }
        if ((i & 8) != 0) {
            str3 = authenticatorSelectionCriteria.userVerification;
        }
        return authenticatorSelectionCriteria.copy(str, str2, z, str3);
    }

    public final String component1() {
        return this.authenticatorAttachment;
    }

    public final String component2() {
        return this.residentKey;
    }

    public final boolean component3() {
        return this.requireResidentKey;
    }

    public final String component4() {
        return this.userVerification;
    }

    public final AuthenticatorSelectionCriteria copy(String str, String str2, boolean z, String str3) {
        Intrinsics.checkNotNullParameter(str, "authenticatorAttachment");
        Intrinsics.checkNotNullParameter(str2, "residentKey");
        Intrinsics.checkNotNullParameter(str3, "userVerification");
        return new AuthenticatorSelectionCriteria(str, str2, z, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AuthenticatorSelectionCriteria)) {
            return false;
        }
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) obj;
        return Intrinsics.areEqual((Object) this.authenticatorAttachment, (Object) authenticatorSelectionCriteria.authenticatorAttachment) && Intrinsics.areEqual((Object) this.residentKey, (Object) authenticatorSelectionCriteria.residentKey) && this.requireResidentKey == authenticatorSelectionCriteria.requireResidentKey && Intrinsics.areEqual((Object) this.userVerification, (Object) authenticatorSelectionCriteria.userVerification);
    }

    public int hashCode() {
        int hashCode = ((this.authenticatorAttachment.hashCode() * 31) + this.residentKey.hashCode()) * 31;
        boolean z = this.requireResidentKey;
        if (z) {
            z = true;
        }
        return ((hashCode + (z ? 1 : 0)) * 31) + this.userVerification.hashCode();
    }

    public String toString() {
        return "AuthenticatorSelectionCriteria(authenticatorAttachment=" + this.authenticatorAttachment + ", residentKey=" + this.residentKey + ", requireResidentKey=" + this.requireResidentKey + ", userVerification=" + this.userVerification + ')';
    }

    public AuthenticatorSelectionCriteria(String str, String str2, boolean z, String str3) {
        Intrinsics.checkNotNullParameter(str, "authenticatorAttachment");
        Intrinsics.checkNotNullParameter(str2, "residentKey");
        Intrinsics.checkNotNullParameter(str3, "userVerification");
        this.authenticatorAttachment = str;
        this.residentKey = str2;
        this.requireResidentKey = z;
        this.userVerification = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AuthenticatorSelectionCriteria(String str, String str2, boolean z, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? "preferred" : str3);
    }

    public final String getAuthenticatorAttachment() {
        return this.authenticatorAttachment;
    }

    public final String getResidentKey() {
        return this.residentKey;
    }

    public final boolean getRequireResidentKey() {
        return this.requireResidentKey;
    }

    public final String getUserVerification() {
        return this.userVerification;
    }
}
