package androidx.credentials.provider.utils;

import androidx.credentials.provider.utils.BeginCreateCredentialUtil;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ BeginCreateCredentialUtil$Companion$$ExternalSyntheticLambda2(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return BeginCreateCredentialUtil.Companion.convertToJetpackResponse$lambda$7(this.f$0, obj);
    }
}
