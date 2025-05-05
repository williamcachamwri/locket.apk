package androidx.credentials.provider;

import android.os.OutcomeReceiver;
import android.service.credentials.BeginGetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/provider/CredentialProviderService$onBeginGetCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderService.kt */
public final class CredentialProviderService$onBeginGetCredential$outcome$1 implements OutcomeReceiver<BeginGetCredentialResponse, GetCredentialException> {
    final /* synthetic */ OutcomeReceiver<BeginGetCredentialResponse, android.credentials.GetCredentialException> $callback;

    CredentialProviderService$onBeginGetCredential$outcome$1(OutcomeReceiver<BeginGetCredentialResponse, android.credentials.GetCredentialException> outcomeReceiver) {
        this.$callback = outcomeReceiver;
    }

    public void onResult(BeginGetCredentialResponse beginGetCredentialResponse) {
        Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
        this.$callback.onResult(BeginGetCredentialUtil.Companion.convertToFrameworkResponse(beginGetCredentialResponse));
    }

    public void onError(GetCredentialException getCredentialException) {
        Intrinsics.checkNotNullParameter(getCredentialException, "error");
        this.$callback.onError(new android.credentials.GetCredentialException(getCredentialException.getType(), getCredentialException.getMessage()));
    }
}
