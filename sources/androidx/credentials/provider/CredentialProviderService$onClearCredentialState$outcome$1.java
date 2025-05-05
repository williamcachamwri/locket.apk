package androidx.credentials.provider;

import android.credentials.ClearCredentialStateException;
import android.os.OutcomeReceiver;
import androidx.credentials.exceptions.ClearCredentialException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/provider/CredentialProviderService$onClearCredentialState$outcome$1", "Landroid/os/OutcomeReceiver;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onError", "", "error", "onResult", "response", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderService.kt */
public final class CredentialProviderService$onClearCredentialState$outcome$1 implements OutcomeReceiver<Void, ClearCredentialException> {
    final /* synthetic */ OutcomeReceiver<Void, ClearCredentialStateException> $callback;

    CredentialProviderService$onClearCredentialState$outcome$1(OutcomeReceiver<Void, ClearCredentialStateException> outcomeReceiver) {
        this.$callback = outcomeReceiver;
    }

    public void onResult(Void voidR) {
        this.$callback.onResult(voidR);
    }

    public void onError(ClearCredentialException clearCredentialException) {
        Intrinsics.checkNotNullParameter(clearCredentialException, "error");
        this.$callback.onError(new ClearCredentialStateException(clearCredentialException.getType(), clearCredentialException.getMessage()));
    }
}
