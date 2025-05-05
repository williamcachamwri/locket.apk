package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B+\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tB7\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/credentials/GetPublicKeyCredentialOption;", "Landroidx/credentials/CredentialOption;", "requestJson", "", "clientDataHash", "", "allowedProviders", "", "Landroid/content/ComponentName;", "(Ljava/lang/String;[BLjava/util/Set;)V", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "(Ljava/lang/String;[BLjava/util/Set;Landroid/os/Bundle;Landroid/os/Bundle;)V", "getClientDataHash", "()[B", "getRequestJson", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GetPublicKeyCredentialOption.kt */
public final class GetPublicKeyCredentialOption extends CredentialOption {
    public static final String BUNDLE_KEY_CLIENT_DATA_HASH = "androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH";
    public static final String BUNDLE_KEY_REQUEST_JSON = "androidx.credentials.BUNDLE_KEY_REQUEST_JSON";
    public static final String BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION = "androidx.credentials.BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final byte[] clientDataHash;
    private final String requestJson;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetPublicKeyCredentialOption(String str) {
        this(str, (byte[]) null, (Set) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetPublicKeyCredentialOption(String str, byte[] bArr) {
        this(str, bArr, (Set) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    public /* synthetic */ GetPublicKeyCredentialOption(String str, byte[] bArr, Set set, Bundle bundle, Bundle bundle2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bArr, (Set<ComponentName>) set, bundle, bundle2);
    }

    @JvmStatic
    public static final GetPublicKeyCredentialOption createFrom$credentials_release(Bundle bundle, Set<ComponentName> set, Bundle bundle2) {
        return Companion.createFrom$credentials_release(bundle, set, bundle2);
    }

    @JvmStatic
    public static final Bundle toRequestDataBundle$credentials_release(String str, byte[] bArr) {
        return Companion.toRequestDataBundle$credentials_release(str, bArr);
    }

    public final String getRequestJson() {
        return this.requestJson;
    }

    public final byte[] getClientDataHash() {
        return this.clientDataHash;
    }

    private GetPublicKeyCredentialOption(String str, byte[] bArr, Set<ComponentName> set, Bundle bundle, Bundle bundle2) {
        super(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, bundle, bundle2, false, true, set);
        this.requestJson = str;
        this.clientDataHash = bArr;
        if (!RequestValidationHelper.Companion.isValidJSON(str)) {
            throw new IllegalArgumentException("requestJson must not be empty, and must be a valid JSON".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GetPublicKeyCredentialOption(String str, byte[] bArr, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? SetsKt.emptySet() : set);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GetPublicKeyCredentialOption(java.lang.String r8, byte[] r9, java.util.Set<android.content.ComponentName> r10) {
        /*
            r7 = this;
            java.lang.String r0 = "requestJson"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "allowedProviders"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            androidx.credentials.GetPublicKeyCredentialOption$Companion r0 = Companion
            android.os.Bundle r5 = r0.toRequestDataBundle$credentials_release(r8, r9)
            android.os.Bundle r6 = r0.toRequestDataBundle$credentials_release(r8, r9)
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>((java.lang.String) r2, (byte[]) r3, (java.util.Set<android.content.ComponentName>) r4, (android.os.Bundle) r5, (android.os.Bundle) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.GetPublicKeyCredentialOption.<init>(java.lang.String, byte[], java.util.Set):void");
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000fJ\u001f\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0001¢\u0006\u0002\b\u0014R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/credentials/GetPublicKeyCredentialOption$Companion;", "", "()V", "BUNDLE_KEY_CLIENT_DATA_HASH", "", "BUNDLE_KEY_REQUEST_JSON", "BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION", "createFrom", "Landroidx/credentials/GetPublicKeyCredentialOption;", "data", "Landroid/os/Bundle;", "allowedProviders", "", "Landroid/content/ComponentName;", "candidateQueryData", "createFrom$credentials_release", "toRequestDataBundle", "requestJson", "clientDataHash", "", "toRequestDataBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: GetPublicKeyCredentialOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle toRequestDataBundle$credentials_release(String str, byte[] bArr) {
            Intrinsics.checkNotNullParameter(str, "requestJson");
            Bundle bundle = new Bundle();
            bundle.putString(PublicKeyCredential.BUNDLE_KEY_SUBTYPE, GetPublicKeyCredentialOption.BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION);
            bundle.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", str);
            bundle.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", bArr);
            return bundle;
        }

        @JvmStatic
        public final GetPublicKeyCredentialOption createFrom$credentials_release(Bundle bundle, Set<ComponentName> set, Bundle bundle2) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(set, "allowedProviders");
            Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON");
                byte[] byteArray = bundle.getByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH");
                Intrinsics.checkNotNull(string);
                return new GetPublicKeyCredentialOption(string, byteArray, set, bundle, bundle2, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
