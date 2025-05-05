package androidx.credentials.provider.utils;

import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final boolean test(Object obj) {
        return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$7(this.f$0, obj);
    }
}
