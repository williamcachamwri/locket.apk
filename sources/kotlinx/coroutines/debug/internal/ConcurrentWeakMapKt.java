package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0001\n\u0000\u001a\u000e\u0010\b\u001a\u00020\u0006*\u0004\u0018\u00010\tH\u0002\u001a\b\u0010\n\u001a\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"MAGIC", "", "MIN_CAPACITY", "REHASH", "Lkotlinx/coroutines/internal/Symbol;", "MARKED_NULL", "Lkotlinx/coroutines/debug/internal/Marked;", "MARKED_TRUE", "mark", "", "noImpl", "", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: ConcurrentWeakMap.kt */
public final class ConcurrentWeakMapKt {
    private static final int MAGIC = -1640531527;
    private static final Marked MARKED_NULL = new Marked((Object) null);
    private static final Marked MARKED_TRUE = new Marked(true);
    private static final int MIN_CAPACITY = 16;
    /* access modifiers changed from: private */
    public static final Symbol REHASH = new Symbol("REHASH");

    /* access modifiers changed from: private */
    public static final Marked mark(Object obj) {
        if (obj == null) {
            return MARKED_NULL;
        }
        if (Intrinsics.areEqual(obj, (Object) true)) {
            return MARKED_TRUE;
        }
        return new Marked(obj);
    }

    /* access modifiers changed from: private */
    public static final Void noImpl() {
        throw new UnsupportedOperationException("not implemented");
    }
}
