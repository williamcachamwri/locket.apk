package androidx.credentials;

import android.credentials.CreateCredentialException;
import android.credentials.CreateCredentialResponse;
import android.os.Bundle;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.credentials.CreateCredentialResponse;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialProviderFrameworkImpl$onCreateCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Landroid/credentials/CreateCredentialResponse;", "Landroid/credentials/CreateCredentialException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFrameworkImpl.kt */
public final class CredentialProviderFrameworkImpl$onCreateCredential$outcome$1 implements OutcomeReceiver<CreateCredentialResponse, CreateCredentialException> {
    final /* synthetic */ CredentialManagerCallback<CreateCredentialResponse, androidx.credentials.exceptions.CreateCredentialException> $callback;
    final /* synthetic */ CreateCredentialRequest $request;
    final /* synthetic */ CredentialProviderFrameworkImpl this$0;

    CredentialProviderFrameworkImpl$onCreateCredential$outcome$1(CredentialManagerCallback<CreateCredentialResponse, androidx.credentials.exceptions.CreateCredentialException> credentialManagerCallback, CreateCredentialRequest createCredentialRequest, CredentialProviderFrameworkImpl credentialProviderFrameworkImpl) {
        this.$callback = credentialManagerCallback;
        this.$request = createCredentialRequest;
        this.this$0 = credentialProviderFrameworkImpl;
    }

    public void onResult(CreateCredentialResponse createCredentialResponse) {
        Intrinsics.checkNotNullParameter(createCredentialResponse, Response.TYPE);
        Log.i("CredManProvService", "Create Result returned from framework: ");
        CredentialManagerCallback<CreateCredentialResponse, androidx.credentials.exceptions.CreateCredentialException> credentialManagerCallback = this.$callback;
        CreateCredentialResponse.Companion companion = CreateCredentialResponse.Companion;
        String type = this.$request.getType();
        Bundle data = createCredentialResponse.getData();
        Intrinsics.checkNotNullExpressionValue(data, "response.data");
        credentialManagerCallback.onResult(companion.createFrom(type, data));
    }

    public void onError(CreateCredentialException createCredentialException) {
        Intrinsics.checkNotNullParameter(createCredentialException, "error");
        Log.i("CredManProvService", "CreateCredentialResponse error returned from framework");
        this.$callback.onError(this.this$0.convertToJetpackCreateException$credentials_release(createCredentialException));
    }
}
