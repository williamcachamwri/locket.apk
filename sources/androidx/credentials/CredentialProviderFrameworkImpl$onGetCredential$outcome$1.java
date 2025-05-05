package androidx.credentials;

import android.credentials.GetCredentialException;
import android.credentials.GetCredentialResponse;
import android.os.OutcomeReceiver;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialProviderFrameworkImpl$onGetCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Landroid/credentials/GetCredentialResponse;", "Landroid/credentials/GetCredentialException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFrameworkImpl.kt */
public final class CredentialProviderFrameworkImpl$onGetCredential$outcome$1 implements OutcomeReceiver<GetCredentialResponse, GetCredentialException> {
    final /* synthetic */ CredentialManagerCallback<GetCredentialResponse, androidx.credentials.exceptions.GetCredentialException> $callback;
    final /* synthetic */ CredentialProviderFrameworkImpl this$0;

    CredentialProviderFrameworkImpl$onGetCredential$outcome$1(CredentialManagerCallback<GetCredentialResponse, androidx.credentials.exceptions.GetCredentialException> credentialManagerCallback, CredentialProviderFrameworkImpl credentialProviderFrameworkImpl) {
        this.$callback = credentialManagerCallback;
        this.this$0 = credentialProviderFrameworkImpl;
    }

    public void onResult(GetCredentialResponse getCredentialResponse) {
        Intrinsics.checkNotNullParameter(getCredentialResponse, Response.TYPE);
        this.$callback.onResult(this.this$0.convertGetResponseToJetpackClass$credentials_release(getCredentialResponse));
    }

    public void onError(GetCredentialException getCredentialException) {
        Intrinsics.checkNotNullParameter(getCredentialException, "error");
        this.$callback.onError(this.this$0.convertToJetpackGetException$credentials_release(getCredentialException));
    }
}
