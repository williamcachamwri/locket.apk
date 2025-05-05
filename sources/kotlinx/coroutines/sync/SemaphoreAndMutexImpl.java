package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.selects.SelectInstance;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001a\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\u001eH\u0005Jb\u0010\u001a\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u001f2\u0006\u0010\u001d\u001a\u0002H\u001f2!\u0010 \u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00190!2!\u0010$\u001a\u001d\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00160!H\b¢\u0006\u0002\u0010%J\u001e\u0010&\u001a\u00020\u00162\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0004J\b\u0010*\u001a\u00020\u0003H\u0002J\u0006\u0010+\u001a\u00020\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020\u0019H\u0002J\f\u00100\u001a\u00020\u0019*\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004R\t\u0010\n\u001a\u00020\u000bX\u0004R\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004R\t\u0010\r\u001a\u00020\u000bX\u0004R\t\u0010\u000e\u001a\u00020\u000fX\u0004R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00160\u0014X\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreAndMutexImpl;", "", "permits", "", "acquiredPermits", "<init>", "(II)V", "head", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/sync/SemaphoreSegment;", "deqIdx", "Lkotlinx/atomicfu/AtomicLong;", "tail", "enqIdx", "_availablePermits", "Lkotlinx/atomicfu/AtomicInt;", "availablePermits", "getAvailablePermits", "()I", "onCancellationRelease", "Lkotlin/Function3;", "", "", "Lkotlin/coroutines/CoroutineContext;", "tryAcquire", "", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "waiter", "Lkotlinx/coroutines/CancellableContinuation;", "W", "suspend", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onAcquired", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onAcquireRegFunction", "select", "Lkotlinx/coroutines/selects/SelectInstance;", "ignoredParam", "decPermits", "release", "coerceAvailablePermitsAtMaximum", "addAcquireToQueue", "Lkotlinx/coroutines/Waiter;", "tryResumeNextFromQueue", "tryResumeAcquire", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Semaphore.kt */
public class SemaphoreAndMutexImpl {
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater deqIdx$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicLongFieldUpdater enqIdx$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater head$volatile$FU;
    /* access modifiers changed from: private */
    public static final /* synthetic */ AtomicReferenceFieldUpdater tail$volatile$FU;
    private volatile /* synthetic */ int _availablePermits$volatile;
    private volatile /* synthetic */ long deqIdx$volatile;
    private volatile /* synthetic */ long enqIdx$volatile;
    private volatile /* synthetic */ Object head$volatile;
    private final Function3<Throwable, Unit, CoroutineContext, Unit> onCancellationRelease;
    private final int permits;
    private volatile /* synthetic */ Object tail$volatile;

    static {
        Class<SemaphoreAndMutexImpl> cls = SemaphoreAndMutexImpl.class;
        head$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "head$volatile");
        deqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(cls, "deqIdx$volatile");
        tail$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "tail$volatile");
        enqIdx$volatile$FU = AtomicLongFieldUpdater.newUpdater(cls, "enqIdx$volatile");
        _availablePermits$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "_availablePermits$volatile");
    }

    private final /* synthetic */ long getDeqIdx$volatile() {
        return this.deqIdx$volatile;
    }

    private final /* synthetic */ long getEnqIdx$volatile() {
        return this.enqIdx$volatile;
    }

    private final /* synthetic */ Object getHead$volatile() {
        return this.head$volatile;
    }

    private final /* synthetic */ Object getTail$volatile() {
        return this.tail$volatile;
    }

    private final /* synthetic */ int get_availablePermits$volatile() {
        return this._availablePermits$volatile;
    }

    private final /* synthetic */ void setDeqIdx$volatile(long j) {
        this.deqIdx$volatile = j;
    }

    private final /* synthetic */ void setEnqIdx$volatile(long j) {
        this.enqIdx$volatile = j;
    }

    private final /* synthetic */ void setHead$volatile(Object obj) {
        this.head$volatile = obj;
    }

    private final /* synthetic */ void setTail$volatile(Object obj) {
        this.tail$volatile = obj;
    }

    private final /* synthetic */ void set_availablePermits$volatile(int i) {
        this._availablePermits$volatile = i;
    }

    public SemaphoreAndMutexImpl(int i, int i2) {
        this.permits = i;
        boolean z = true;
        if (i > 0) {
            if ((i2 < 0 || i2 > i) ? false : z) {
                SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0, (SemaphoreSegment) null, 2);
                this.head$volatile = semaphoreSegment;
                this.tail$volatile = semaphoreSegment;
                this._availablePermits$volatile = i - i2;
                this.onCancellationRelease = new SemaphoreAndMutexImpl$$ExternalSyntheticLambda0(this);
                return;
            }
            throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i).toString());
        }
        throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i).toString());
    }

    public final int getAvailablePermits() {
        return Math.max(_availablePermits$volatile$FU.get(this), 0);
    }

    /* access modifiers changed from: private */
    public static final Unit onCancellationRelease$lambda$2(SemaphoreAndMutexImpl semaphoreAndMutexImpl, Throwable th, Unit unit, CoroutineContext coroutineContext) {
        semaphoreAndMutexImpl.release();
        return Unit.INSTANCE;
    }

    public final boolean tryAcquire() {
        while (true) {
            int i = _availablePermits$volatile$FU.get(this);
            if (i > this.permits) {
                coerceAvailablePermitsAtMaximum();
            } else if (i <= 0) {
                return false;
            } else {
                if (_availablePermits$volatile$FU.compareAndSet(this, i, i - 1)) {
                    return true;
                }
            }
        }
    }

    public final Object acquire(Continuation<? super Unit> continuation) {
        if (decPermits() > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        return acquireSlowPath == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? acquireSlowPath : Unit.INSTANCE;
    }

    private final <W> void acquire(W w, Function1<? super W, Boolean> function1, Function1<? super W, Unit> function12) {
        while (decPermits() <= 0) {
            if (function1.invoke(w).booleanValue()) {
                return;
            }
        }
        function12.invoke(w);
    }

    private final int decPermits() {
        int andDecrement;
        do {
            andDecrement = _availablePermits$volatile$FU.getAndDecrement(this);
        } while (andDecrement > this.permits);
        return andDecrement;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void release() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$volatile$FU
            int r0 = r0.getAndIncrement(r3)
            int r1 = r3.permits
            if (r0 >= r1) goto L_0x0016
            if (r0 < 0) goto L_0x000f
            return
        L_0x000f:
            boolean r0 = r3.tryResumeNextFromQueue()
            if (r0 == 0) goto L_0x0000
            return
        L_0x0016:
            r3.coerceAvailablePermitsAtMaximum()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "The number of released permits cannot be greater than "
            r1.<init>(r2)
            int r2 = r3.permits
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.release():void");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private final void coerceAvailablePermitsAtMaximum() {
        /*
            r3 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = _availablePermits$volatile$FU
            int r0 = r0.get(r3)
            int r1 = r3.permits
            if (r0 <= r1) goto L_0x0018
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = _availablePermits$volatile$FU
            int r2 = r3.permits
            boolean r0 = r1.compareAndSet(r3, r0, r2)
            if (r0 == 0) goto L_0x0000
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.coerceAvailablePermitsAtMaximum():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0061, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0047, code lost:
        r10 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean addAcquireToQueue(kotlinx.coroutines.Waiter r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = tail$volatile$FU
            java.lang.Object r2 = r2.get(r0)
            kotlinx.coroutines.sync.SemaphoreSegment r2 = (kotlinx.coroutines.sync.SemaphoreSegment) r2
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = enqIdx$volatile$FU
            long r3 = r3.getAndIncrement(r0)
            kotlinx.coroutines.sync.SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1 r5 = kotlinx.coroutines.sync.SemaphoreAndMutexImpl$addAcquireToQueue$createNewSegment$1.INSTANCE
            kotlin.reflect.KFunction r5 = (kotlin.reflect.KFunction) r5
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = tail$volatile$FU
            int r7 = kotlinx.coroutines.sync.SemaphoreKt.SEGMENT_SIZE
            long r7 = (long) r7
            long r7 = r3 / r7
        L_0x0025:
            r9 = r2
            kotlinx.coroutines.internal.Segment r9 = (kotlinx.coroutines.internal.Segment) r9
            r10 = r5
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            java.lang.Object r9 = kotlinx.coroutines.internal.ConcurrentLinkedListKt.findSegmentInternal(r9, r7, r10)
            boolean r10 = kotlinx.coroutines.internal.SegmentOrClosed.m1863isClosedimpl(r9)
            if (r10 != 0) goto L_0x006e
            kotlinx.coroutines.internal.Segment r10 = kotlinx.coroutines.internal.SegmentOrClosed.m1861getSegmentimpl(r9)
        L_0x0039:
            java.lang.Object r13 = r6.get(r0)
            kotlinx.coroutines.internal.Segment r13 = (kotlinx.coroutines.internal.Segment) r13
            long r14 = r13.id
            long r11 = r10.id
            int r11 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r11 < 0) goto L_0x0049
        L_0x0047:
            r10 = 1
            goto L_0x0061
        L_0x0049:
            boolean r11 = r10.tryIncPointers$kotlinx_coroutines_core()
            if (r11 != 0) goto L_0x0051
            r10 = 0
            goto L_0x0061
        L_0x0051:
            boolean r11 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r6, r0, r13, r10)
            if (r11 == 0) goto L_0x0064
            boolean r10 = r13.decPointers$kotlinx_coroutines_core()
            if (r10 == 0) goto L_0x0047
            r13.remove()
            goto L_0x0047
        L_0x0061:
            if (r10 == 0) goto L_0x0025
            goto L_0x006e
        L_0x0064:
            boolean r11 = r10.decPointers$kotlinx_coroutines_core()
            if (r11 == 0) goto L_0x0039
            r10.remove()
            goto L_0x0039
        L_0x006e:
            kotlinx.coroutines.internal.Segment r2 = kotlinx.coroutines.internal.SegmentOrClosed.m1861getSegmentimpl(r9)
            kotlinx.coroutines.sync.SemaphoreSegment r2 = (kotlinx.coroutines.sync.SemaphoreSegment) r2
            int r5 = kotlinx.coroutines.sync.SemaphoreKt.SEGMENT_SIZE
            long r5 = (long) r5
            long r3 = r3 % r5
            int r3 = (int) r3
            r4 = 0
            java.util.concurrent.atomic.AtomicReferenceArray r5 = r2.getAcquirers()
            boolean r4 = com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(r5, r3, r4, r1)
            if (r4 == 0) goto L_0x008d
            kotlinx.coroutines.internal.Segment r2 = (kotlinx.coroutines.internal.Segment) r2
            r1.invokeOnCancellation(r2, r3)
            r1 = 1
            return r1
        L_0x008d:
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.sync.SemaphoreKt.PERMIT
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.sync.SemaphoreKt.TAKEN
            java.util.concurrent.atomic.AtomicReferenceArray r6 = r2.getAcquirers()
            boolean r4 = com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(r6, r3, r4, r5)
            if (r4 == 0) goto L_0x00d9
            boolean r2 = r1 instanceof kotlinx.coroutines.CancellableContinuation
            if (r2 == 0) goto L_0x00b3
            java.lang.String r2 = "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r2)
            kotlinx.coroutines.CancellableContinuation r1 = (kotlinx.coroutines.CancellableContinuation) r1
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            kotlin.jvm.functions.Function3<java.lang.Throwable, kotlin.Unit, kotlin.coroutines.CoroutineContext, kotlin.Unit> r3 = r0.onCancellationRelease
            r1.resume(r2, r3)
        L_0x00b1:
            r1 = 1
            goto L_0x00bf
        L_0x00b3:
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.SelectInstance
            if (r2 == 0) goto L_0x00c0
            kotlinx.coroutines.selects.SelectInstance r1 = (kotlinx.coroutines.selects.SelectInstance) r1
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r1.selectInRegistrationPhase(r2)
            goto L_0x00b1
        L_0x00bf:
            return r1
        L_0x00c0:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "unexpected: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x00d9:
            r1 = 1
            boolean r4 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            if (r4 == 0) goto L_0x00fa
            java.util.concurrent.atomic.AtomicReferenceArray r2 = r2.getAcquirers()
            java.lang.Object r2 = r2.get(r3)
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.sync.SemaphoreKt.BROKEN
            if (r2 != r3) goto L_0x00f0
            r12 = r1
            goto L_0x00f1
        L_0x00f0:
            r12 = 0
        L_0x00f1:
            if (r12 == 0) goto L_0x00f4
            goto L_0x00fa
        L_0x00f4:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            throw r1
        L_0x00fa:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.addAcquireToQueue(kotlinx.coroutines.Waiter):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005e, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0044, code lost:
        r9 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean tryResumeNextFromQueue() {
        /*
            r15 = this;
            r0 = r15
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = head$volatile$FU
            java.lang.Object r1 = r1.get(r15)
            kotlinx.coroutines.sync.SemaphoreSegment r1 = (kotlinx.coroutines.sync.SemaphoreSegment) r1
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = deqIdx$volatile$FU
            long r2 = r2.getAndIncrement(r15)
            int r4 = kotlinx.coroutines.sync.SemaphoreKt.SEGMENT_SIZE
            long r4 = (long) r4
            long r4 = r2 / r4
            kotlinx.coroutines.sync.SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1 r6 = kotlinx.coroutines.sync.SemaphoreAndMutexImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE
            kotlin.reflect.KFunction r6 = (kotlin.reflect.KFunction) r6
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = head$volatile$FU
        L_0x0022:
            r8 = r1
            kotlinx.coroutines.internal.Segment r8 = (kotlinx.coroutines.internal.Segment) r8
            r9 = r6
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            java.lang.Object r8 = kotlinx.coroutines.internal.ConcurrentLinkedListKt.findSegmentInternal(r8, r4, r9)
            boolean r9 = kotlinx.coroutines.internal.SegmentOrClosed.m1863isClosedimpl(r8)
            if (r9 != 0) goto L_0x006b
            kotlinx.coroutines.internal.Segment r9 = kotlinx.coroutines.internal.SegmentOrClosed.m1861getSegmentimpl(r8)
        L_0x0036:
            java.lang.Object r12 = r7.get(r15)
            kotlinx.coroutines.internal.Segment r12 = (kotlinx.coroutines.internal.Segment) r12
            long r13 = r12.id
            long r10 = r9.id
            int r10 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r10 < 0) goto L_0x0046
        L_0x0044:
            r9 = 1
            goto L_0x005e
        L_0x0046:
            boolean r10 = r9.tryIncPointers$kotlinx_coroutines_core()
            if (r10 != 0) goto L_0x004e
            r9 = 0
            goto L_0x005e
        L_0x004e:
            boolean r10 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r7, r15, r12, r9)
            if (r10 == 0) goto L_0x0061
            boolean r9 = r12.decPointers$kotlinx_coroutines_core()
            if (r9 == 0) goto L_0x0044
            r12.remove()
            goto L_0x0044
        L_0x005e:
            if (r9 == 0) goto L_0x0022
            goto L_0x006b
        L_0x0061:
            boolean r10 = r9.decPointers$kotlinx_coroutines_core()
            if (r10 == 0) goto L_0x0036
            r9.remove()
            goto L_0x0036
        L_0x006b:
            kotlinx.coroutines.internal.Segment r1 = kotlinx.coroutines.internal.SegmentOrClosed.m1861getSegmentimpl(r8)
            kotlinx.coroutines.sync.SemaphoreSegment r1 = (kotlinx.coroutines.sync.SemaphoreSegment) r1
            r1.cleanPrev()
            long r6 = r1.id
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x007c
            r4 = 0
            return r4
        L_0x007c:
            int r4 = kotlinx.coroutines.sync.SemaphoreKt.SEGMENT_SIZE
            long r4 = (long) r4
            long r2 = r2 % r4
            int r2 = (int) r2
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.sync.SemaphoreKt.PERMIT
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r1.getAcquirers()
            java.lang.Object r3 = r4.getAndSet(r2, r3)
            if (r3 != 0) goto L_0x00bf
            int r3 = kotlinx.coroutines.sync.SemaphoreKt.MAX_SPIN_CYCLES
            r10 = 0
        L_0x0096:
            if (r10 >= r3) goto L_0x00ac
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r1.getAcquirers()
            java.lang.Object r4 = r4.get(r2)
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.sync.SemaphoreKt.TAKEN
            if (r4 != r5) goto L_0x00a8
            r4 = 1
            return r4
        L_0x00a8:
            r4 = 1
            int r10 = r10 + 1
            goto L_0x0096
        L_0x00ac:
            r4 = 1
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.sync.SemaphoreKt.PERMIT
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.sync.SemaphoreKt.BROKEN
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r1.getAcquirers()
            boolean r1 = com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(r1, r2, r3, r5)
            r1 = r1 ^ r4
            return r1
        L_0x00bf:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.sync.SemaphoreKt.CANCELLED
            if (r3 != r1) goto L_0x00c7
            r1 = 0
            return r1
        L_0x00c7:
            boolean r1 = r15.tryResumeAcquire(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreAndMutexImpl.tryResumeNextFromQueue():boolean");
    }

    private final boolean tryResumeAcquire(Object obj) {
        if (obj instanceof CancellableContinuation) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancellableContinuation<kotlin.Unit>");
            CancellableContinuation cancellableContinuation = (CancellableContinuation) obj;
            Object tryResume = cancellableContinuation.tryResume(Unit.INSTANCE, (Object) null, this.onCancellationRelease);
            if (tryResume == null) {
                return false;
            }
            cancellableContinuation.completeResume(tryResume);
            return true;
        } else if (obj instanceof SelectInstance) {
            return ((SelectInstance) obj).trySelect(this, Unit.INSTANCE);
        } else {
            throw new IllegalStateException(("unexpected: " + obj).toString());
        }
    }

    /* access modifiers changed from: private */
    public final Object acquireSlowPath(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl<? super Unit> orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation));
        try {
            if (!addAcquireToQueue(orCreateCancellableContinuation)) {
                acquire((CancellableContinuation<? super Unit>) orCreateCancellableContinuation);
            }
            Object result = orCreateCancellableContinuation.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
        } catch (Throwable th) {
            orCreateCancellableContinuation.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final void acquire(CancellableContinuation<? super Unit> cancellableContinuation) {
        while (decPermits() <= 0) {
            Intrinsics.checkNotNull(cancellableContinuation, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) cancellableContinuation)) {
                return;
            }
        }
        cancellableContinuation.resume(Unit.INSTANCE, (Function3<? super Throwable, ? super R, ? super CoroutineContext, Unit>) this.onCancellationRelease);
    }

    /* access modifiers changed from: protected */
    public final void onAcquireRegFunction(SelectInstance<?> selectInstance, Object obj) {
        while (decPermits() <= 0) {
            Intrinsics.checkNotNull(selectInstance, "null cannot be cast to non-null type kotlinx.coroutines.Waiter");
            if (addAcquireToQueue((Waiter) selectInstance)) {
                return;
            }
        }
        selectInstance.selectInRegistrationPhase(Unit.INSTANCE);
    }
}
