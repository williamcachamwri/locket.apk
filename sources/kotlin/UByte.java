package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

@JvmInline
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\f¢\u0006\u0004\b\n\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\b\u001c\u0010\u000fJ\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\b\u001d\u0010\u0012J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\b\u001e\u0010\u001fJ\u0018\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\b¢\u0006\u0004\b'\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\b¢\u0006\u0004\b(\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\b¢\u0006\u0004\b)\u0010\u001fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\b¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0013\u0010.\u001a\u00020\u0000H\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0013\u00100\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u0018\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\b3\u0010\u000fJ\u0018\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\b4\u0010\u0012J\u0018\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\b5\u0010\u001fJ\u0018\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\b6\u0010\u0018J\u0018\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\b¢\u0006\u0004\b8\u0010\u000bJ\u0018\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\b¢\u0006\u0004\b9\u0010\u0012J\u0018\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\b¢\u0006\u0004\b:\u0010\u001fJ\u0018\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\b¢\u0006\u0004\b;\u0010<J\u0018\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\f¢\u0006\u0004\b>\u0010\u000bJ\u0018\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\b@\u0010\u000fJ\u0018\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\bA\u0010\u0012J\u0018\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\bB\u0010\u001fJ\u0018\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\bC\u0010\u0018J\u0018\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\bF\u0010GJ\u0018\u0010H\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\bI\u0010GJ\u0018\u0010J\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\bK\u0010\u000fJ\u0018\u0010J\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\bL\u0010\u0012J\u0018\u0010J\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\bM\u0010\u001fJ\u0018\u0010J\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\bN\u0010\u0018J\u0018\u0010O\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\n¢\u0006\u0004\bP\u0010\u000fJ\u0018\u0010O\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\n¢\u0006\u0004\bQ\u0010\u0012J\u0018\u0010O\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\n¢\u0006\u0004\bR\u0010\u001fJ\u0018\u0010O\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\n¢\u0006\u0004\bS\u0010\u0018J\u0010\u0010T\u001a\u00020\u0003H\b¢\u0006\u0004\bU\u0010\u0005J\u0010\u0010V\u001a\u00020WH\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\rH\b¢\u0006\u0004\b_\u0010-J\u0010\u0010`\u001a\u00020aH\b¢\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001a\u00020eH\b¢\u0006\u0004\bf\u0010gJ\u000f\u0010h\u001a\u00020iH\u0016¢\u0006\u0004\bj\u0010kJ\u0013\u0010l\u001a\u00020\u0000H\bø\u0001\u0000¢\u0006\u0004\bm\u0010\u0005J\u0013\u0010n\u001a\u00020\u0010H\bø\u0001\u0000¢\u0006\u0004\bo\u0010-J\u0013\u0010p\u001a\u00020\u0013H\bø\u0001\u0000¢\u0006\u0004\bq\u0010cJ\u0013\u0010r\u001a\u00020\u0016H\bø\u0001\u0000¢\u0006\u0004\bs\u0010gJ\u0018\u0010t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\f¢\u0006\u0004\bu\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0001\u0002\u0001\u00020\u0003\u0002\u0004\n\u0002\b!¨\u0006w"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UByte.kt */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m2456boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m2462constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m2468equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).m2512unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m2469equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m2474hashCodeimpl(byte b) {
        return Byte.hashCode(b);
    }

    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m2500toByteimpl(byte b) {
        return b;
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m2503toIntimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m2504toLongimpl(byte b) {
        return ((long) b) & 255;
    }

    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m2505toShortimpl(byte b) {
        return (short) (((short) b) & 255);
    }

    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m2507toUBytew2LRezQ(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m2468equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m2474hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m2512unboximpl() {
        return this.data;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return Intrinsics.compare((int) m2512unboximpl() & 255, (int) ((UByte) obj).m2512unboximpl() & 255);
    }

    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0004XTø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bXT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: UByte.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private int m2457compareTo7apg3OU(byte b) {
        return Intrinsics.compare((int) m2512unboximpl() & 255, (int) b & 255);
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static int m2458compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare((int) b & 255, (int) b2 & 255);
    }

    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m2461compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare((int) b & 255, (int) s & UShort.MAX_VALUE);
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m2460compareToWZ4Q5Ns(byte b, int i) {
        return Integer.compareUnsigned(UInt.m2539constructorimpl(b & 255), i);
    }

    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m2459compareToVKZWuLQ(byte b, long j) {
        return Long.compareUnsigned(ULong.m2618constructorimpl(((long) b) & 255), j);
    }

    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m2486plus7apg3OU(byte b, byte b2) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) + UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m2489plusxj2QHRw(byte b, short s) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) + UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m2488plusWZ4Q5Ns(byte b, int i) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) + i);
    }

    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m2487plusVKZWuLQ(byte b, long j) {
        return ULong.m2618constructorimpl(ULong.m2618constructorimpl(((long) b) & 255) + j);
    }

    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m2477minus7apg3OU(byte b, byte b2) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) - UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m2480minusxj2QHRw(byte b, short s) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) - UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m2479minusWZ4Q5Ns(byte b, int i) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) - i);
    }

    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m2478minusVKZWuLQ(byte b, long j) {
        return ULong.m2618constructorimpl(ULong.m2618constructorimpl(((long) b) & 255) - j);
    }

    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m2496times7apg3OU(byte b, byte b2) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) * UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m2499timesxj2QHRw(byte b, short s) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) * UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m2498timesWZ4Q5Ns(byte b, int i) {
        return UInt.m2539constructorimpl(UInt.m2539constructorimpl(b & 255) * i);
    }

    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m2497timesVKZWuLQ(byte b, long j) {
        return ULong.m2618constructorimpl(ULong.m2618constructorimpl(((long) b) & 255) * j);
    }

    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m2464div7apg3OU(byte b, byte b2) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m2467divxj2QHRw(byte b, short s) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m2466divWZ4Q5Ns(byte b, int i) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), i);
    }

    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m2465divVKZWuLQ(byte b, long j) {
        return Long.divideUnsigned(ULong.m2618constructorimpl(((long) b) & 255), j);
    }

    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m2492rem7apg3OU(byte b, byte b2) {
        return Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m2495remxj2QHRw(byte b, short s) {
        return Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m2494remWZ4Q5Ns(byte b, int i) {
        return Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), i);
    }

    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m2493remVKZWuLQ(byte b, long j) {
        return Long.remainderUnsigned(ULong.m2618constructorimpl(((long) b) & 255), j);
    }

    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m2470floorDiv7apg3OU(byte b, byte b2) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m2473floorDivxj2QHRw(byte b, short s) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m2472floorDivWZ4Q5Ns(byte b, int i) {
        return Integer.divideUnsigned(UInt.m2539constructorimpl(b & 255), i);
    }

    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m2471floorDivVKZWuLQ(byte b, long j) {
        return Long.divideUnsigned(ULong.m2618constructorimpl(((long) b) & 255), j);
    }

    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m2481mod7apg3OU(byte b, byte b2) {
        return m2462constructorimpl((byte) Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255)));
    }

    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m2484modxj2QHRw(byte b, short s) {
        return UShort.m2725constructorimpl((short) Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(s & UShort.MAX_VALUE)));
    }

    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m2483modWZ4Q5Ns(byte b, int i) {
        return Integer.remainderUnsigned(UInt.m2539constructorimpl(b & 255), i);
    }

    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m2482modVKZWuLQ(byte b, long j) {
        return Long.remainderUnsigned(ULong.m2618constructorimpl(((long) b) & 255), j);
    }

    /* renamed from: inc-w2LRezQ  reason: not valid java name */
    private static final byte m2475incw2LRezQ(byte b) {
        return m2462constructorimpl((byte) (b + 1));
    }

    /* renamed from: dec-w2LRezQ  reason: not valid java name */
    private static final byte m2463decw2LRezQ(byte b) {
        return m2462constructorimpl((byte) (b - 1));
    }

    /* renamed from: rangeTo-7apg3OU  reason: not valid java name */
    private static final UIntRange m2490rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255), (DefaultConstructorMarker) null);
    }

    /* renamed from: rangeUntil-7apg3OU  reason: not valid java name */
    private static final UIntRange m2491rangeUntil7apg3OU(byte b, byte b2) {
        return URangesKt.m3709untilJ1ME1BU(UInt.m2539constructorimpl(b & 255), UInt.m2539constructorimpl(b2 & 255));
    }

    /* renamed from: and-7apg3OU  reason: not valid java name */
    private static final byte m2455and7apg3OU(byte b, byte b2) {
        return m2462constructorimpl((byte) (b & b2));
    }

    /* renamed from: or-7apg3OU  reason: not valid java name */
    private static final byte m2485or7apg3OU(byte b, byte b2) {
        return m2462constructorimpl((byte) (b | b2));
    }

    /* renamed from: xor-7apg3OU  reason: not valid java name */
    private static final byte m2511xor7apg3OU(byte b, byte b2) {
        return m2462constructorimpl((byte) (b ^ b2));
    }

    /* renamed from: inv-w2LRezQ  reason: not valid java name */
    private static final byte m2476invw2LRezQ(byte b) {
        return m2462constructorimpl((byte) (~b));
    }

    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m2510toUShortMh2AYeg(byte b) {
        return UShort.m2725constructorimpl((short) (((short) b) & 255));
    }

    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m2508toUIntpVg5ArA(byte b) {
        return UInt.m2539constructorimpl(b & 255);
    }

    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m2509toULongsVKNKU(byte b) {
        return ULong.m2618constructorimpl(((long) b) & 255);
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m2502toFloatimpl(byte b) {
        return (float) UnsignedKt.uintToDouble(b & 255);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m2501toDoubleimpl(byte b) {
        return UnsignedKt.uintToDouble(b & 255);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m2506toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }

    public String toString() {
        return m2506toStringimpl(this.data);
    }
}
