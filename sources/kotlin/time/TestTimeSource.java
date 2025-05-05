package kotlin.time;

import kotlin.Metadata;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TimeSources.kt */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        markNow();
    }

    /* access modifiers changed from: protected */
    public long read() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m1734plusAssignLRDsOJo(long j) {
        long r0 = Duration.m1644toLongimpl(j, getUnit());
        boolean z = true;
        if (!(((r0 - 1) | 1) == Long.MAX_VALUE)) {
            long j2 = this.reading;
            long j3 = j2 + r0;
            if ((r0 ^ j2) >= 0 && (j2 ^ j3) < 0) {
                m1733overflowLRDsOJo(j);
            }
            this.reading = j3;
            return;
        }
        long r02 = Duration.m1601divUwyO8pc(j, 2);
        if ((1 | (Duration.m1644toLongimpl(r02, getUnit()) - 1)) != Long.MAX_VALUE) {
            z = false;
        }
        if (!z) {
            long j4 = this.reading;
            try {
                m1734plusAssignLRDsOJo(r02);
                m1734plusAssignLRDsOJo(Duration.m1633minusLRDsOJo(j, r02));
            } catch (IllegalStateException e) {
                this.reading = j4;
                throw e;
            }
        } else {
            m1733overflowLRDsOJo(j);
        }
    }

    /* renamed from: overflow-LRDsOJo  reason: not valid java name */
    private final void m1733overflowLRDsOJo(long j) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(getUnit()) + " is advanced by " + Duration.m1647toStringimpl(j) + FilenameUtils.EXTENSION_SEPARATOR);
    }
}
