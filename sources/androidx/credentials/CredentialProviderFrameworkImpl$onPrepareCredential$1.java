package androidx.credentials;

import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnsupportedException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFrameworkImpl.kt */
final class CredentialProviderFrameworkImpl$onPrepareCredential$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> $callback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CredentialProviderFrameworkImpl$onPrepareCredential$1(CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        super(0);
        this.$callback = credentialManagerCallback;
    }

    public final void invoke() {
        this.$callback.onError(new GetCredentialUnsupportedException("Your device doesn't support credential manager"));
    }
}
