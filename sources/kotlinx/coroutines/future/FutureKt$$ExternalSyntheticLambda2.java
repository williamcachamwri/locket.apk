package kotlinx.coroutines.future;

import java.util.function.BiFunction;
import kotlin.jvm.functions.Function2;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FutureKt$$ExternalSyntheticLambda2 implements BiFunction {
    public final /* synthetic */ Function2 f$0;

    public /* synthetic */ FutureKt$$ExternalSyntheticLambda2(Function2 function2) {
        this.f$0 = function2;
    }

    public final Object apply(Object obj, Object obj2) {
        return FutureKt.asDeferred$lambda$6(this.f$0, obj, (Throwable) obj2);
    }
}
