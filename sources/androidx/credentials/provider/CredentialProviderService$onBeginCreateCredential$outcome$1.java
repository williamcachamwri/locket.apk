package androidx.credentials.provider;

import android.os.OutcomeReceiver;
import android.service.credentials.BeginCreateCredentialResponse;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/provider/CredentialProviderService$onBeginCreateCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Landroidx/credentials/provider/BeginCreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderService.kt */
public final class CredentialProviderService$onBeginCreateCredential$outcome$1 implements OutcomeReceiver<BeginCreateCredentialResponse, CreateCredentialException> {
    final /* synthetic */ OutcomeReceiver<BeginCreateCredentialResponse, android.credentials.CreateCredentialException> $callback;

    CredentialProviderService$onBeginCreateCredential$outcome$1(OutcomeReceiver<BeginCreateCredentialResponse, android.credentials.CreateCredentialException> outcomeReceiver) {
        this.$callback = outcomeReceiver;
    }

    public void onResult(BeginCreateCredentialResponse beginCreateCredentialResponse) {
        Intrinsics.checkNotNullParameter(beginCreateCredentialResponse, Response.TYPE);
        this.$callback.onResult(BeginCreateCredentialUtil.Companion.convertToFrameworkResponse(beginCreateCredentialResponse));
    }

    public void onError(CreateCredentialException createCredentialException) {
        Intrinsics.checkNotNullParameter(createCredentialException, "error");
        this.$callback.onError(new android.credentials.CreateCredentialException(createCredentialException.getType(), createCredentialException.getMessage()));
    }
}
