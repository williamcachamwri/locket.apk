package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.selects.SelectImplementation", f = "Select.kt", i = {0}, l = {453, 456}, m = "doSelectSuspend", n = {"this"}, s = {"L$0"})
/* compiled from: Select.kt */
final class SelectImplementation$doSelectSuspend$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SelectImplementation<R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SelectImplementation$doSelectSuspend$1(SelectImplementation<R> selectImplementation, Continuation<? super SelectImplementation$doSelectSuspend$1> continuation) {
        super(continuation);
        this.this$0 = selectImplementation;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doSelectSuspend(this);
    }
}
