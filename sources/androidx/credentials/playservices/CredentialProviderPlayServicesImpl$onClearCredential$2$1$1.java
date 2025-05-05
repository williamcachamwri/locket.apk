package androidx.credentials.playservices;

import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.ClearCredentialUnknownException;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderPlayServicesImpl.kt */
final class CredentialProviderPlayServicesImpl$onClearCredential$2$1$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CredentialManagerCallback<Void, ClearCredentialException> $callback;
    final /* synthetic */ Exception $e;
    final /* synthetic */ Executor $executor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CredentialProviderPlayServicesImpl$onClearCredential$2$1$1(Exception exc, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
        super(0);
        this.$e = exc;
        this.$executor = executor;
        this.$callback = credentialManagerCallback;
    }

    public final void invoke() {
        SentryLogcatAdapter.w("PlayServicesImpl", "During clear credential sign out failed with " + this.$e);
        this.$executor.execute(new CredentialProviderPlayServicesImpl$onClearCredential$2$1$1$$ExternalSyntheticLambda0(this.$callback, this.$e));
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(CredentialManagerCallback credentialManagerCallback, Exception exc) {
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "$callback");
        Intrinsics.checkNotNullParameter(exc, "$e");
        credentialManagerCallback.onError(new ClearCredentialUnknownException(exc.getMessage()));
    }
}
