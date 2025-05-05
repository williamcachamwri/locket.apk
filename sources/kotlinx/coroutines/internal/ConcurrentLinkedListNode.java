package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\f\u001a\u0004\u0018\u00018\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\b¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u0000¢\u0006\u0002\u0010\u0017J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0015J\u0006\u0010\u001f\u001a\u00020\u001cR\u0011\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007X\u0004R\u0011\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0004R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00028BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0011\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0003\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0013R\u0012\u0010\u001e\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0019R\u0016\u0010 \u001a\u0004\u0018\u00018\u00008BX\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0014\u0010\"\u001a\u00028\u00008BX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013¨\u0006$"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "", "prev", "<init>", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "nextOrClosed", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrIfClosed", "onClosedAction", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "next", "getNext", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "trySetNext", "", "value", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "isTail", "()Z", "getPrev", "cleanPrev", "", "markAsClosed", "isRemoved", "remove", "aliveSegmentLeft", "getAliveSegmentLeft", "aliveSegmentRight", "getAliveSegmentRight", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: ConcurrentLinkedList.kt */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater _prev$volatile$FU;
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;

    static {
        Class<ConcurrentLinkedListNode> cls = ConcurrentLinkedListNode.class;
        _next$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_next$volatile");
        _prev$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_prev$volatile");
    }

    private final /* synthetic */ Object get_next$volatile() {
        return this._next$volatile;
    }

    private final /* synthetic */ Object get_prev$volatile() {
        return this._prev$volatile;
    }

    private final /* synthetic */ void set_next$volatile(Object obj) {
        this._next$volatile = obj;
    }

    private final /* synthetic */ void set_prev$volatile(Object obj) {
        this._prev$volatile = obj;
    }

    private final /* synthetic */ void update$atomicfu(Object obj, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Function1<Object, ? extends Object> function1) {
        Object obj2;
        do {
            obj2 = atomicReferenceFieldUpdater.get(obj);
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, obj, obj2, function1.invoke(obj2)));
    }

    public abstract boolean isRemoved();

    public ConcurrentLinkedListNode(N n) {
        this._prev$volatile = n;
    }

    /* access modifiers changed from: private */
    public final Object getNextOrClosed() {
        return _next$volatile$FU.get(this);
    }

    public final N nextOrIfClosed(Function0 function0) {
        N access$getNextOrClosed = getNextOrClosed();
        if (access$getNextOrClosed != ConcurrentLinkedListKt.CLOSED) {
            return (ConcurrentLinkedListNode) access$getNextOrClosed;
        }
        function0.invoke();
        throw new KotlinNothingValueException();
    }

    public final boolean trySetNext(N n) {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$volatile$FU, this, (Object) null, n);
    }

    public final boolean isTail() {
        return getNext() == null;
    }

    public final N getPrev() {
        return (ConcurrentLinkedListNode) _prev$volatile$FU.get(this);
    }

    public final void cleanPrev() {
        _prev$volatile$FU.set(this, (Object) null);
    }

    public final boolean markAsClosed() {
        return AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(_next$volatile$FU, this, (Object) null, ConcurrentLinkedListKt.CLOSED);
    }

    public final void remove() {
        Object obj;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(isRemoved() || isTail())) {
                throw new AssertionError();
            }
        }
        if (!isTail()) {
            while (true) {
                ConcurrentLinkedListNode aliveSegmentLeft = getAliveSegmentLeft();
                ConcurrentLinkedListNode aliveSegmentRight = getAliveSegmentRight();
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$volatile$FU;
                do {
                    obj = atomicReferenceFieldUpdater.get(aliveSegmentRight);
                } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, aliveSegmentRight, obj, ((ConcurrentLinkedListNode) obj) == null ? null : aliveSegmentLeft));
                if (aliveSegmentLeft != null) {
                    _next$volatile$FU.set(aliveSegmentLeft, aliveSegmentRight);
                }
                if ((!aliveSegmentRight.isRemoved() || aliveSegmentRight.isTail()) && (aliveSegmentLeft == null || !aliveSegmentLeft.isRemoved())) {
                    return;
                }
            }
        }
    }

    private final N getAliveSegmentLeft() {
        N prev = getPrev();
        while (prev != null && prev.isRemoved()) {
            prev = (ConcurrentLinkedListNode) _prev$volatile$FU.get(prev);
        }
        return prev;
    }

    private final N getAliveSegmentRight() {
        N next;
        if (!DebugKt.getASSERTIONS_ENABLED() || (!isTail())) {
            N next2 = getNext();
            Intrinsics.checkNotNull(next2);
            while (next2.isRemoved() && (next = next2.getNext()) != null) {
                next2 = next;
            }
            return next2;
        }
        throw new AssertionError();
    }

    public final N getNext() {
        N access$getNextOrClosed = getNextOrClosed();
        if (access$getNextOrClosed == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (ConcurrentLinkedListNode) access$getNextOrClosed;
    }
}
