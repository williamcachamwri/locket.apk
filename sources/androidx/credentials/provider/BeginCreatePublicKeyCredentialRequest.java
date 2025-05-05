package androidx.credentials.provider;

import android.os.Bundle;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.provider.utils.RequestValidationUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Landroidx/credentials/provider/BeginCreatePublicKeyCredentialRequest;", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "requestJson", "", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "candidateQueryData", "Landroid/os/Bundle;", "clientDataHash", "", "(Ljava/lang/String;Landroidx/credentials/provider/CallingAppInfo;Landroid/os/Bundle;[B)V", "getClientDataHash", "()[B", "getRequestJson", "()Ljava/lang/String;", "initiateBundle", "", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginCreatePublicKeyCredentialRequest.kt */
public final class BeginCreatePublicKeyCredentialRequest extends BeginCreateCredentialRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final byte[] clientDataHash;
    private final String requestJson;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BeginCreatePublicKeyCredentialRequest(String str, CallingAppInfo callingAppInfo, Bundle bundle) {
        this(str, callingAppInfo, bundle, (byte[]) null, 8, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
    }

    @JvmStatic
    public static final BeginCreatePublicKeyCredentialRequest createForTest(Bundle bundle, CallingAppInfo callingAppInfo) {
        return Companion.createForTest(bundle, callingAppInfo);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BeginCreatePublicKeyCredentialRequest(String str, CallingAppInfo callingAppInfo, Bundle bundle, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, callingAppInfo, bundle, (i & 8) != 0 ? null : bArr);
    }

    public final String getRequestJson() {
        return this.requestJson;
    }

    public final byte[] getClientDataHash() {
        return this.clientDataHash;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeginCreatePublicKeyCredentialRequest(String str, CallingAppInfo callingAppInfo, Bundle bundle, byte[] bArr) {
        super(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, bundle, callingAppInfo);
        Intrinsics.checkNotNullParameter(str, "requestJson");
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
        this.requestJson = str;
        this.clientDataHash = bArr;
        if (RequestValidationUtil.Companion.isValidJSON(str)) {
            initiateBundle(bundle, str);
            return;
        }
        throw new IllegalArgumentException("requestJson must not be empty, and must be a valid JSON".toString());
    }

    private final void initiateBundle(Bundle bundle, String str) {
        bundle.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", str);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001f\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/BeginCreatePublicKeyCredentialRequest$Companion;", "", "()V", "createForTest", "Landroidx/credentials/provider/BeginCreatePublicKeyCredentialRequest;", "data", "Landroid/os/Bundle;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "createFrom", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginCreatePublicKeyCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginCreatePublicKeyCredentialRequest createForTest(Bundle bundle, CallingAppInfo callingAppInfo) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            return createFrom$credentials_release(bundle, callingAppInfo);
        }

        public final BeginCreatePublicKeyCredentialRequest createFrom$credentials_release(Bundle bundle, CallingAppInfo callingAppInfo) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON");
                byte[] byteArray = bundle.getByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH");
                Intrinsics.checkNotNull(string);
                return new BeginCreatePublicKeyCredentialRequest(string, callingAppInfo, bundle, byteArray);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
