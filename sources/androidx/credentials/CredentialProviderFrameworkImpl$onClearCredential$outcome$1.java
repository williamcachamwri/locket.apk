package androidx.credentials;

import android.credentials.ClearCredentialStateException;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.ClearCredentialUnknownException;
import io.sentry.protocol.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialProviderFrameworkImpl$onClearCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Ljava/lang/Void;", "Landroid/credentials/ClearCredentialStateException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFrameworkImpl.kt */
public final class CredentialProviderFrameworkImpl$onClearCredential$outcome$1 implements OutcomeReceiver<Void, ClearCredentialStateException> {
    final /* synthetic */ CredentialManagerCallback<Void, ClearCredentialException> $callback;

    CredentialProviderFrameworkImpl$onClearCredential$outcome$1(CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
        this.$callback = credentialManagerCallback;
    }

    public void onResult(Void voidR) {
        Intrinsics.checkNotNullParameter(voidR, Response.TYPE);
        Log.i("CredManProvService", "Clear result returned from framework: ");
        this.$callback.onResult(voidR);
    }

    public void onError(ClearCredentialStateException clearCredentialStateException) {
        Intrinsics.checkNotNullParameter(clearCredentialStateException, "error");
        Log.i("CredManProvService", "ClearCredentialStateException error returned from framework");
        this.$callback.onError(new ClearCredentialUnknownException((CharSequence) null, 1, (DefaultConstructorMarker) null));
    }
}
