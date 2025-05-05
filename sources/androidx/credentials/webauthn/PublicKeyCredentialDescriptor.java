package androidx.credentials.webauthn;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Landroidx/credentials/webauthn/PublicKeyCredentialDescriptor;", "", "type", "", "id", "", "transports", "", "(Ljava/lang/String;[BLjava/util/List;)V", "getId", "()[B", "getTransports", "()Ljava/util/List;", "getType", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FidoDataTypes.kt */
public final class PublicKeyCredentialDescriptor {
    private final byte[] id;
    private final List<String> transports;
    private final String type;

    public static /* synthetic */ PublicKeyCredentialDescriptor copy$default(PublicKeyCredentialDescriptor publicKeyCredentialDescriptor, String str, byte[] bArr, List<String> list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = publicKeyCredentialDescriptor.type;
        }
        if ((i & 2) != 0) {
            bArr = publicKeyCredentialDescriptor.id;
        }
        if ((i & 4) != 0) {
            list = publicKeyCredentialDescriptor.transports;
        }
        return publicKeyCredentialDescriptor.copy(str, bArr, list);
    }

    public final String component1() {
        return this.type;
    }

    public final byte[] component2() {
        return this.id;
    }

    public final List<String> component3() {
        return this.transports;
    }

    public final PublicKeyCredentialDescriptor copy(String str, byte[] bArr, List<String> list) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bArr, "id");
        Intrinsics.checkNotNullParameter(list, "transports");
        return new PublicKeyCredentialDescriptor(str, bArr, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PublicKeyCredentialDescriptor)) {
            return false;
        }
        PublicKeyCredentialDescriptor publicKeyCredentialDescriptor = (PublicKeyCredentialDescriptor) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) publicKeyCredentialDescriptor.type) && Intrinsics.areEqual((Object) this.id, (Object) publicKeyCredentialDescriptor.id) && Intrinsics.areEqual((Object) this.transports, (Object) publicKeyCredentialDescriptor.transports);
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + Arrays.hashCode(this.id)) * 31) + this.transports.hashCode();
    }

    public String toString() {
        return "PublicKeyCredentialDescriptor(type=" + this.type + ", id=" + Arrays.toString(this.id) + ", transports=" + this.transports + ')';
    }

    public PublicKeyCredentialDescriptor(String str, byte[] bArr, List<String> list) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bArr, "id");
        Intrinsics.checkNotNullParameter(list, "transports");
        this.type = str;
        this.id = bArr;
        this.transports = list;
    }

    public final String getType() {
        return this.type;
    }

    public final byte[] getId() {
        return this.id;
    }

    public final List<String> getTransports() {
        return this.transports;
    }
}
