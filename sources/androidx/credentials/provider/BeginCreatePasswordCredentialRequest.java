package androidx.credentials.provider;

import android.os.Bundle;
import androidx.credentials.PasswordCredential;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest;", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "candidateQueryData", "Landroid/os/Bundle;", "(Landroidx/credentials/provider/CallingAppInfo;Landroid/os/Bundle;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginCreatePasswordCredentialRequest.kt */
public final class BeginCreatePasswordCredentialRequest extends BeginCreateCredentialRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final BeginCreatePasswordCredentialRequest createFrom$credentials_release(Bundle bundle, CallingAppInfo callingAppInfo) {
        return Companion.createFrom$credentials_release(bundle, callingAppInfo);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeginCreatePasswordCredentialRequest(CallingAppInfo callingAppInfo, Bundle bundle) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, callingAppInfo);
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/BeginCreatePasswordCredentialRequest;", "data", "Landroid/os/Bundle;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginCreatePasswordCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginCreatePasswordCredentialRequest createFrom$credentials_release(Bundle bundle, CallingAppInfo callingAppInfo) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                return new BeginCreatePasswordCredentialRequest(callingAppInfo, bundle);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
