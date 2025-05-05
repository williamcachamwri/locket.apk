package kotlinx.coroutines.internal;

import androidx.core.internal.view.SupportMenu;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.NotCompleted;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u0013\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0016J\"\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0006\u0010\u001e\u001a\u00020\u0018R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\t\u0010\u000e\u001a\u00020\u000fX\u0004R\u0014\u0010\u0010\u001a\u00020\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/internal/Segment;", "S", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/NotCompleted;", "id", "", "prev", "pointers", "", "<init>", "(JLkotlinx/coroutines/internal/Segment;I)V", "numberOfSlots", "getNumberOfSlots", "()I", "cleanedAndPointers", "Lkotlinx/atomicfu/AtomicInt;", "isRemoved", "", "()Z", "tryIncPointers", "tryIncPointers$kotlinx_coroutines_core", "decPointers", "decPointers$kotlinx_coroutines_core", "onCancellation", "", "index", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "onSlotCleaned", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: ConcurrentLinkedList.kt */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> implements NotCompleted {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater cleanedAndPointers$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers$volatile");
    private volatile /* synthetic */ int cleanedAndPointers$volatile;
    public final long id;

    private final /* synthetic */ int getCleanedAndPointers$volatile() {
        return this.cleanedAndPointers$volatile;
    }

    private final /* synthetic */ void setCleanedAndPointers$volatile(int i) {
        this.cleanedAndPointers$volatile = i;
    }

    public abstract int getNumberOfSlots();

    public abstract void onCancellation(int i, Throwable th, CoroutineContext coroutineContext);

    public Segment(long j, S s, int i) {
        super((ConcurrentLinkedListNode) s);
        this.id = j;
        this.cleanedAndPointers$volatile = i << 16;
    }

    public boolean isRemoved() {
        return cleanedAndPointers$volatile$FU.get(this) == getNumberOfSlots() && !isTail();
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return cleanedAndPointers$volatile$FU.addAndGet(this, SupportMenu.CATEGORY_MASK) == getNumberOfSlots() && !isTail();
    }

    public final void onSlotCleaned() {
        if (cleanedAndPointers$volatile$FU.incrementAndGet(this) == getNumberOfSlots()) {
            remove();
        }
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int i;
        AtomicIntegerFieldUpdater cleanedAndPointers$volatile$FU2 = cleanedAndPointers$volatile$FU;
        do {
            i = cleanedAndPointers$volatile$FU2.get(this);
            if (!(i != getNumberOfSlots() || isTail())) {
                return false;
            }
        } while (!cleanedAndPointers$volatile$FU2.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
