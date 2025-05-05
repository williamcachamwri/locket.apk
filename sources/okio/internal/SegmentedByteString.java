package okio.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.C0012SegmentedByteString;
import okio.Segment;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a-\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u0017\u0010\u000e\u001a\u00020\u000f*\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\bH\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\bH\b\u001a\u0015\u0010\u0014\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0001H\b\u001a-\u0010\u0017\u001a\u00020\u000f*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a-\u0010\u0017\u001a\u00020\u000f*\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u001d\u0010\u001a\u001a\u00020\u0019*\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u0001H\b\u001a\r\u0010\u001d\u001a\u00020\u000b*\u00020\bH\b\u001a%\u0010\u001e\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a]\u0010!\u001a\u00020\u0007*\u00020\b2K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\bø\u0001\u0000\u001aj\u0010!\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012K\u0010\"\u001aG\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070#H\b\u001a\u0014\u0010'\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0001H\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006("}, d2 = {"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonCopyInto", "", "Lokio/SegmentedByteString;", "offset", "target", "", "targetOffset", "byteCount", "commonEquals", "", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "otherOffset", "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-SegmentedByteString  reason: invalid class name */
/* compiled from: SegmentedByteString.kt */
public final class SegmentedByteString {
    public static final int binarySearch(int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else if (i6 <= i) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return (-i2) - 1;
    }

    public static final int segment(C0012SegmentedByteString segmentedByteString, int i) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        int binarySearch = binarySearch(segmentedByteString.getDirectory$okio(), i + 1, 0, ((Object[]) segmentedByteString.getSegments$okio()).length);
        return binarySearch >= 0 ? binarySearch : ~binarySearch;
    }

    public static final void forEachSegment(C0012SegmentedByteString segmentedByteString, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(function3, "action");
        int length = ((Object[]) segmentedByteString.getSegments$okio()).length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = segmentedByteString.getDirectory$okio()[length + i];
            int i4 = segmentedByteString.getDirectory$okio()[i];
            function3.invoke(segmentedByteString.getSegments$okio()[i], Integer.valueOf(i3), Integer.valueOf(i4 - i2));
            i++;
            i2 = i4;
        }
    }

    private static final void forEachSegment(C0012SegmentedByteString segmentedByteString, int i, int i2, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        int i3;
        int segment = segment(segmentedByteString, i);
        while (i < i2) {
            if (segment == 0) {
                i3 = 0;
            } else {
                i3 = segmentedByteString.getDirectory$okio()[segment - 1];
            }
            int i4 = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment];
            int min = Math.min(i2, (segmentedByteString.getDirectory$okio()[segment] - i3) + i3) - i;
            function3.invoke(segmentedByteString.getSegments$okio()[segment], Integer.valueOf(i4 + (i - i3)), Integer.valueOf(min));
            i += min;
            segment++;
        }
    }

    public static final ByteString commonSubstring(C0012SegmentedByteString segmentedByteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        ByteString byteString = segmentedByteString;
        int resolveDefaultParameter = okio.SegmentedByteString.resolveDefaultParameter(byteString, i2);
        int i3 = 0;
        if (i >= 0) {
            if (resolveDefaultParameter <= segmentedByteString.size()) {
                int i4 = resolveDefaultParameter - i;
                if (!(i4 >= 0)) {
                    throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " < beginIndex=" + i).toString());
                } else if (i == 0 && resolveDefaultParameter == segmentedByteString.size()) {
                    return byteString;
                } else {
                    if (i == resolveDefaultParameter) {
                        return ByteString.EMPTY;
                    }
                    int segment = segment(segmentedByteString, i);
                    int segment2 = segment(segmentedByteString, resolveDefaultParameter - 1);
                    byte[][] bArr = (byte[][]) ArraysKt.copyOfRange((T[]) (Object[]) segmentedByteString.getSegments$okio(), segment, segment2 + 1);
                    Object[] objArr = (Object[]) bArr;
                    int[] iArr = new int[(objArr.length * 2)];
                    if (segment <= segment2) {
                        int i5 = segment;
                        int i6 = 0;
                        while (true) {
                            iArr[i6] = Math.min(segmentedByteString.getDirectory$okio()[i5] - i, i4);
                            int i7 = i6 + 1;
                            iArr[i6 + objArr.length] = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + i5];
                            if (i5 == segment2) {
                                break;
                            }
                            i5++;
                            i6 = i7;
                        }
                    }
                    if (segment != 0) {
                        i3 = segmentedByteString.getDirectory$okio()[segment - 1];
                    }
                    int length = objArr.length;
                    iArr[length] = iArr[length] + (i - i3);
                    return new C0012SegmentedByteString(bArr, iArr);
                }
            } else {
                throw new IllegalArgumentException(("endIndex=" + resolveDefaultParameter + " > length(" + segmentedByteString.size() + ')').toString());
            }
        } else {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        }
    }

    public static final byte commonInternalGet(C0012SegmentedByteString segmentedByteString, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        okio.SegmentedByteString.checkOffsetAndCount((long) segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length - 1], (long) i, 1);
        int segment = segment(segmentedByteString, i);
        if (segment == 0) {
            i2 = 0;
        } else {
            i2 = segmentedByteString.getDirectory$okio()[segment - 1];
        }
        return segmentedByteString.getSegments$okio()[segment][(i - i2) + segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment]];
    }

    public static final int commonGetSize(C0012SegmentedByteString segmentedByteString) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        return segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length - 1];
    }

    public static final byte[] commonToByteArray(C0012SegmentedByteString segmentedByteString) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        byte[] bArr = new byte[segmentedByteString.size()];
        int length = ((Object[]) segmentedByteString.getSegments$okio()).length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = segmentedByteString.getDirectory$okio()[length + i];
            int i5 = segmentedByteString.getDirectory$okio()[i];
            int i6 = i5 - i2;
            ArraysKt.copyInto(segmentedByteString.getSegments$okio()[i], bArr, i3, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    public static final boolean commonRangeEquals(C0012SegmentedByteString segmentedByteString, int i, ByteString byteString, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(byteString, "other");
        if (i < 0 || i > segmentedByteString.size() - i3) {
            return false;
        }
        int i5 = i3 + i;
        int segment = segment(segmentedByteString, i);
        while (i < i5) {
            if (segment == 0) {
                i4 = 0;
            } else {
                i4 = segmentedByteString.getDirectory$okio()[segment - 1];
            }
            int i6 = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment];
            int min = Math.min(i5, (segmentedByteString.getDirectory$okio()[segment] - i4) + i4) - i;
            if (!byteString.rangeEquals(i2, segmentedByteString.getSegments$okio()[segment], i6 + (i - i4), min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public static final boolean commonRangeEquals(C0012SegmentedByteString segmentedByteString, int i, byte[] bArr, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "other");
        if (i < 0 || i > segmentedByteString.size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i5 = i3 + i;
        int segment = segment(segmentedByteString, i);
        while (i < i5) {
            if (segment == 0) {
                i4 = 0;
            } else {
                i4 = segmentedByteString.getDirectory$okio()[segment - 1];
            }
            int i6 = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment];
            int min = Math.min(i5, (segmentedByteString.getDirectory$okio()[segment] - i4) + i4) - i;
            if (!okio.SegmentedByteString.arrayRangeEquals(segmentedByteString.getSegments$okio()[segment], i6 + (i - i4), bArr, i2, min)) {
                return false;
            }
            i2 += min;
            i += min;
            segment++;
        }
        return true;
    }

    public static final void commonCopyInto(C0012SegmentedByteString segmentedByteString, int i, byte[] bArr, int i2, int i3) {
        int i4;
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "target");
        long j = (long) i3;
        okio.SegmentedByteString.checkOffsetAndCount((long) segmentedByteString.size(), (long) i, j);
        okio.SegmentedByteString.checkOffsetAndCount((long) bArr.length, (long) i2, j);
        int i5 = i3 + i;
        int segment = segment(segmentedByteString, i);
        while (i < i5) {
            if (segment == 0) {
                i4 = 0;
            } else {
                i4 = segmentedByteString.getDirectory$okio()[segment - 1];
            }
            int i6 = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment];
            int min = Math.min(i5, (segmentedByteString.getDirectory$okio()[segment] - i4) + i4) - i;
            int i7 = i6 + (i - i4);
            ArraysKt.copyInto(segmentedByteString.getSegments$okio()[segment], bArr, i2, i7, i7 + min);
            i2 += min;
            i += min;
            segment++;
        }
    }

    public static final boolean commonEquals(C0012SegmentedByteString segmentedByteString, Object obj) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        if (obj == segmentedByteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == segmentedByteString.size() && segmentedByteString.rangeEquals(0, byteString, 0, segmentedByteString.size())) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(C0012SegmentedByteString segmentedByteString) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        int hashCode$okio = segmentedByteString.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = ((Object[]) segmentedByteString.getSegments$okio()).length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = segmentedByteString.getDirectory$okio()[length + i];
            int i5 = segmentedByteString.getDirectory$okio()[i];
            byte[] bArr = segmentedByteString.getSegments$okio()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        segmentedByteString.setHashCode$okio(i2);
        return i2;
    }

    public static final void commonWrite(C0012SegmentedByteString segmentedByteString, Buffer buffer, int i, int i2) {
        int i3;
        Intrinsics.checkNotNullParameter(segmentedByteString, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int i4 = i + i2;
        int segment = segment(segmentedByteString, i);
        while (i < i4) {
            if (segment == 0) {
                i3 = 0;
            } else {
                i3 = segmentedByteString.getDirectory$okio()[segment - 1];
            }
            int i5 = segmentedByteString.getDirectory$okio()[((Object[]) segmentedByteString.getSegments$okio()).length + segment];
            int min = Math.min(i4, (segmentedByteString.getDirectory$okio()[segment] - i3) + i3) - i;
            int i6 = i5 + (i - i3);
            Segment segment2 = new Segment(segmentedByteString.getSegments$okio()[segment], i6, i6 + min, true, false);
            if (buffer.head == null) {
                segment2.prev = segment2;
                segment2.next = segment2.prev;
                buffer.head = segment2.next;
            } else {
                Segment segment3 = buffer.head;
                Intrinsics.checkNotNull(segment3);
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(segment2);
            }
            i += min;
            segment++;
        }
        buffer.setSize$okio(buffer.size() + ((long) i2));
    }
}
