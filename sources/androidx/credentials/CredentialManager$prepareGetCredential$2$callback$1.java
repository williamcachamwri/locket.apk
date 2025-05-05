package androidx.credentials;

import androidx.credentials.exceptions.GetCredentialException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialManager$prepareGetCredential$2$callback$1", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/PrepareGetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "onError", "", "e", "onResult", "result", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialManager.kt */
public final class CredentialManager$prepareGetCredential$2$callback$1 implements CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> {
    final /* synthetic */ CancellableContinuation<PrepareGetCredentialResponse> $continuation;

    CredentialManager$prepareGetCredential$2$callback$1(CancellableContinuation<? super PrepareGetCredentialResponse> cancellableContinuation) {
        this.$continuation = cancellableContinuation;
    }

    public void onResult(PrepareGetCredentialResponse prepareGetCredentialResponse) {
        Intrinsics.checkNotNullParameter(prepareGetCredentialResponse, "result");
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m2444constructorimpl(prepareGetCredentialResponse));
    }

    public void onError(GetCredentialException getCredentialException) {
        Intrinsics.checkNotNullParameter(getCredentialException, "e");
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(getCredentialException)));
    }
}
