package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/Active;", "Lkotlinx/coroutines/NotCompleted;", "<init>", "()V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: CancellableContinuationImpl.kt */
final class Active implements NotCompleted {
    public static final Active INSTANCE = new Active();

    public String toString() {
        return "Active";
    }

    private Active() {
    }
}
