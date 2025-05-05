package androidx.credentials.provider;

import android.os.Bundle;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.provider.utils.RequestValidationUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;", "Landroidx/credentials/provider/BeginGetCredentialOption;", "candidateQueryData", "Landroid/os/Bundle;", "id", "", "requestJson", "clientDataHash", "", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;[B)V", "getClientDataHash", "()[B", "getRequestJson", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetPublicKeyCredentialOption.kt */
public final class BeginGetPublicKeyCredentialOption extends BeginGetCredentialOption {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final byte[] clientDataHash;
    private final String requestJson;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BeginGetPublicKeyCredentialOption(Bundle bundle, String str, String str2) {
        this(bundle, str, str2, (byte[]) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "requestJson");
    }

    @JvmStatic
    public static final BeginGetPublicKeyCredentialOption createFrom$credentials_release(Bundle bundle, String str) {
        return Companion.createFrom$credentials_release(bundle, str);
    }

    @JvmStatic
    public static final BeginGetPublicKeyCredentialOption createFromEntrySlice$credentials_release(Bundle bundle, String str) {
        return Companion.createFromEntrySlice$credentials_release(bundle, str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BeginGetPublicKeyCredentialOption(Bundle bundle, String str, String str2, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bundle, str, str2, (i & 8) != 0 ? null : bArr);
    }

    public final String getRequestJson() {
        return this.requestJson;
    }

    public final byte[] getClientDataHash() {
        return this.clientDataHash;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeginGetPublicKeyCredentialOption(Bundle bundle, String str, String str2, byte[] bArr) {
        super(str, PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, bundle);
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "requestJson");
        this.requestJson = str2;
        this.clientDataHash = bArr;
        if (!RequestValidationUtil.Companion.isValidJSON(str2)) {
            throw new IllegalArgumentException("requestJson must not be empty, and must be a valid JSON".toString());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\tJ\u001d\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/BeginGetPublicKeyCredentialOption;", "data", "Landroid/os/Bundle;", "id", "", "createFrom$credentials_release", "createFromEntrySlice", "createFromEntrySlice$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetPublicKeyCredentialOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginGetPublicKeyCredentialOption createFrom$credentials_release(Bundle bundle, String str) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(str, "id");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON");
                byte[] byteArray = bundle.getByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH");
                Intrinsics.checkNotNull(string);
                return new BeginGetPublicKeyCredentialOption(bundle, str, string, byteArray);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }

        @JvmStatic
        public final BeginGetPublicKeyCredentialOption createFromEntrySlice$credentials_release(Bundle bundle, String str) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(str, "id");
            return new BeginGetPublicKeyCredentialOption(bundle, str, "{\"dummy_key\":\"dummy_value\"}", (byte[]) null, 8, (DefaultConstructorMarker) null);
        }
    }
}
