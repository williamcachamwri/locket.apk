package androidx.credentials.playservices.controllers;

import androidx.credentials.CredentialManagerCallback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0005*\u00020\u0003\"\b\b\u0003\u0010\u0006*\u00020\u0003\"\b\b\u0004\u0010\u0007*\u00020\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "T1", "", "T2", "R2", "R1", "E1", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderController.kt */
final class CredentialProviderController$maybeReportErrorFromResultReceiver$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ CredentialManagerCallback<R1, E1> $callback;
    final /* synthetic */ E1 $exception;
    final /* synthetic */ Executor $executor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CredentialProviderController$maybeReportErrorFromResultReceiver$1(Executor executor, CredentialManagerCallback<R1, E1> credentialManagerCallback, E1 e1) {
        super(0);
        this.$executor = executor;
        this.$callback = credentialManagerCallback;
        this.$exception = e1;
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(CredentialManagerCallback credentialManagerCallback, Object obj) {
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "$callback");
        Intrinsics.checkNotNullParameter(obj, "$exception");
        credentialManagerCallback.onError(obj);
    }

    public final void invoke() {
        this.$executor.execute(new CredentialProviderController$maybeReportErrorFromResultReceiver$1$$ExternalSyntheticLambda0(this.$callback, this.$exception));
    }
}
