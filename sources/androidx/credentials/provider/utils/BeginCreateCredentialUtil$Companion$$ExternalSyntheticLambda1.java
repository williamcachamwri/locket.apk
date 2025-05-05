package androidx.credentials.provider.utils;

import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import java.util.function.Predicate;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda1(Function1 function1) {
        this.f$0 = function1;
    }

    public final boolean test(Object obj) {
        return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$6(this.f$0, obj);
    }
}
