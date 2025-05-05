package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@JvmInline
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b@\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0007\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\b\u0010\tJ$\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\rH\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0004¢\u0006\u0002\n\u0000\u0001\u0003\u0001\u0004\u0018\u00010\u0002¨\u0006\u0017"}, d2 = {"Lkotlinx/coroutines/internal/InlineList;", "E", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "plus", "element", "plus-FjFbRPM", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEachReversed", "", "action", "Lkotlin/Function1;", "forEachReversed-impl", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "equals", "", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: InlineList.kt */
public final class InlineList<E> {
    private final Object holder;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ InlineList m1847boximpl(Object obj) {
        return new InlineList(obj);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static <E> Object m1848constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1850equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.areEqual(obj, ((InlineList) obj2).m1856unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1851equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1853hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1855toStringimpl(Object obj) {
        return "InlineList(holder=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m1850equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m1853hashCodeimpl(this.holder);
    }

    public String toString() {
        return m1855toStringimpl(this.holder);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m1856unboximpl() {
        return this.holder;
    }

    private /* synthetic */ InlineList(Object obj) {
        this.holder = obj;
    }

    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static /* synthetic */ Object m1849constructorimpl$default(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            obj = null;
        }
        return m1848constructorimpl(obj);
    }

    /* renamed from: plus-FjFbRPM  reason: not valid java name */
    public static final Object m1854plusFjFbRPM(Object obj, E e) {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(e instanceof List))) {
            throw new AssertionError();
        } else if (obj == null) {
            return m1848constructorimpl(e);
        } else {
            if (obj instanceof ArrayList) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
                ((ArrayList) obj).add(e);
                return m1848constructorimpl(obj);
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(e);
            return m1848constructorimpl(arrayList);
        }
    }

    /* renamed from: forEachReversed-impl  reason: not valid java name */
    public static final void m1852forEachReversedimpl(Object obj, Function1<? super E, Unit> function1) {
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                function1.invoke(obj);
                return;
            }
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<E of kotlinx.coroutines.internal.InlineList>");
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size();
            while (true) {
                size--;
                if (-1 < size) {
                    function1.invoke(arrayList.get(size));
                } else {
                    return;
                }
            }
        }
    }
}
