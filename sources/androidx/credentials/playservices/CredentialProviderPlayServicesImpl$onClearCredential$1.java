package androidx.credentials.playservices;

import android.os.CancellationSignal;
import android.util.Log;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderPlayServicesImpl.kt */
final class CredentialProviderPlayServicesImpl$onClearCredential$1 extends Lambda implements Function1<Void, Unit> {
    final /* synthetic */ CredentialManagerCallback<Void, ClearCredentialException> $callback;
    final /* synthetic */ CancellationSignal $cancellationSignal;
    final /* synthetic */ Executor $executor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CredentialProviderPlayServicesImpl$onClearCredential$1(CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
        super(1);
        this.$cancellationSignal = cancellationSignal;
        this.$executor = executor;
        this.$callback = credentialManagerCallback;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Void) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Void voidR) {
        CredentialProviderPlayServicesImpl.Companion companion = CredentialProviderPlayServicesImpl.Companion;
        CancellationSignal cancellationSignal = this.$cancellationSignal;
        final Executor executor = this.$executor;
        final CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback = this.$callback;
        companion.cancellationReviewerWithCallback$credentials_play_services_auth_release(cancellationSignal, new Function0<Unit>() {
            public final void invoke() {
                Log.i("PlayServicesImpl", "During clear credential, signed out successfully!");
                executor.execute(new CredentialProviderPlayServicesImpl$onClearCredential$1$1$$ExternalSyntheticLambda0(credentialManagerCallback));
            }

            /* access modifiers changed from: private */
            public static final void invoke$lambda$0(CredentialManagerCallback credentialManagerCallback) {
                Intrinsics.checkNotNullParameter(credentialManagerCallback, "$callback");
                credentialManagerCallback.onResult(null);
            }
        });
    }
}
