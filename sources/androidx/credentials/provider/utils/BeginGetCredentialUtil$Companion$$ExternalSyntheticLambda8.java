package androidx.credentials.provider.utils;

import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import java.util.function.Function;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda8 implements Function {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ BeginGetCredentialUtil$Companion$$ExternalSyntheticLambda8(Function1 function1) {
        this.f$0 = function1;
    }

    public final Object apply(Object obj) {
        return BeginGetCredentialUtil.Companion.convertToJetpackResponse$lambda$14(this.f$0, obj);
    }
}
