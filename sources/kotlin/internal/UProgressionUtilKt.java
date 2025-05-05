package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001¢\u0006\u0004\b\u000f\u0010\u0006\u001a'\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001¢\u0006\u0004\b\u0011\u0010\t¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: UProgressionUtil.kt */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m3647differenceModuloWZ9TVnA(int i, int i2, int i3) {
        int remainderUnsigned = Integer.remainderUnsigned(i, i3);
        int remainderUnsigned2 = Integer.remainderUnsigned(i2, i3);
        int compareUnsigned = Integer.compareUnsigned(remainderUnsigned, remainderUnsigned2);
        int r1 = UInt.m2539constructorimpl(remainderUnsigned - remainderUnsigned2);
        return compareUnsigned >= 0 ? r1 : UInt.m2539constructorimpl(r1 + i3);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m3648differenceModulosambcqE(long j, long j2, long j3) {
        long remainderUnsigned = Long.remainderUnsigned(j, j3);
        long remainderUnsigned2 = Long.remainderUnsigned(j2, j3);
        int compareUnsigned = Long.compareUnsigned(remainderUnsigned, remainderUnsigned2);
        long r1 = ULong.m2618constructorimpl(remainderUnsigned - remainderUnsigned2);
        return compareUnsigned >= 0 ? r1 : ULong.m2618constructorimpl(r1 + j3);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m3650getProgressionLastElementNkh28Cs(int i, int i2, int i3) {
        if (i3 > 0) {
            if (Integer.compareUnsigned(i, i2) >= 0) {
                return i2;
            }
            return UInt.m2539constructorimpl(i2 - m3647differenceModuloWZ9TVnA(i2, i, UInt.m2539constructorimpl(i3)));
        } else if (i3 < 0) {
            return Integer.compareUnsigned(i, i2) <= 0 ? i2 : UInt.m2539constructorimpl(i2 + m3647differenceModuloWZ9TVnA(i, i2, UInt.m2539constructorimpl(-i3)));
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m3649getProgressionLastElement7ftBX0g(long j, long j2, long j3) {
        int i = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
        if (i > 0) {
            if (Long.compareUnsigned(j, j2) >= 0) {
                return j2;
            }
            return ULong.m2618constructorimpl(j2 - m3648differenceModulosambcqE(j2, j, ULong.m2618constructorimpl(j3)));
        } else if (i >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else if (Long.compareUnsigned(j, j2) <= 0) {
            return j2;
        } else {
            return ULong.m2618constructorimpl(j2 + m3648differenceModulosambcqE(j, j2, ULong.m2618constructorimpl(-j3)));
        }
    }
}
