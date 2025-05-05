package androidx.credentials.provider;

import androidx.credentials.provider.PendingIntentHandler;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PendingIntentHandler$Companion$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ PendingIntentHandler$Companion$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return PendingIntentHandler.Companion.retrieveProviderGetCredentialRequest$lambda$1(this.f$0, obj);
    }
}
