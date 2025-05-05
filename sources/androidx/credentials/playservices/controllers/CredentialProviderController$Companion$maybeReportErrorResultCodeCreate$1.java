package androidx.credentials.playservices.controllers;

import androidx.credentials.exceptions.CreateCredentialException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003\"\b\b\u0002\u0010\u0005*\u00020\u0003\"\b\b\u0003\u0010\u0006*\u00020\u0003\"\b\b\u0004\u0010\u0007*\u00020\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "T1", "", "T2", "R2", "R1", "E1", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderController.kt */
final class CredentialProviderController$Companion$maybeReportErrorResultCodeCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Ref.ObjectRef<CreateCredentialException> $exception;
    final /* synthetic */ Function1<CreateCredentialException, Unit> $onError;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CredentialProviderController$Companion$maybeReportErrorResultCodeCreate$1(Function1<? super CreateCredentialException, Unit> function1, Ref.ObjectRef<CreateCredentialException> objectRef) {
        super(0);
        this.$onError = function1;
        this.$exception = objectRef;
    }

    public final void invoke() {
        this.$onError.invoke(this.$exception.element);
    }
}
