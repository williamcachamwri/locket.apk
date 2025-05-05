package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.IntMath;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class CartesianList<E> extends AbstractList<List<E>> implements RandomAccess {
    /* access modifiers changed from: private */
    public final transient ImmutableList<List<E>> axes;
    private final transient int[] axesSizeProduct;

    static <E> List<List<E>> create(List<? extends List<? extends E>> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
        for (List copyOf : list) {
            ImmutableList copyOf2 = ImmutableList.copyOf(copyOf);
            if (copyOf2.isEmpty()) {
                return ImmutableList.of();
            }
            builder.add((Object) copyOf2);
        }
        return new CartesianList(builder.build());
    }

    CartesianList(ImmutableList<List<E>> immutableList) {
        this.axes = immutableList;
        int[] iArr = new int[(immutableList.size() + 1)];
        iArr[immutableList.size()] = 1;
        try {
            for (int size = immutableList.size() - 1; size >= 0; size--) {
                iArr[size] = IntMath.checkedMultiply(iArr[size + 1], ((List) immutableList.get(size)).size());
            }
            this.axesSizeProduct = iArr;
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Cartesian product too large; must have size at most Integer.MAX_VALUE");
        }
    }

    /* access modifiers changed from: private */
    public int getAxisIndexForProductIndex(int i, int i2) {
        return (i / this.axesSizeProduct[i2 + 1]) % ((List) this.axes.get(i2)).size();
    }

    public int indexOf(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            int indexOf = ((List) this.axes.get(nextIndex)).indexOf(listIterator.next());
            if (indexOf == -1) {
                return -1;
            }
            i += indexOf * this.axesSizeProduct[nextIndex + 1];
        }
        return i;
    }

    public int lastIndexOf(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return -1;
        }
        List list = (List) obj;
        if (list.size() != this.axes.size()) {
            return -1;
        }
        ListIterator listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            int lastIndexOf = ((List) this.axes.get(nextIndex)).lastIndexOf(listIterator.next());
            if (lastIndexOf == -1) {
                return -1;
            }
            i += lastIndexOf * this.axesSizeProduct[nextIndex + 1];
        }
        return i;
    }

    public ImmutableList<E> get(final int i) {
        Preconditions.checkElementIndex(i, size());
        return new ImmutableList<E>(this) {
            final /* synthetic */ CartesianList this$0;

            /* access modifiers changed from: package-private */
            public boolean isPartialView() {
                return true;
            }

            {
                this.this$0 = r1;
            }

            public int size() {
                return this.this$0.axes.size();
            }

            public E get(int i) {
                Preconditions.checkElementIndex(i, size());
                return ((List) this.this$0.axes.get(i)).get(this.this$0.getAxisIndexForProductIndex(i, i));
            }

            /* access modifiers changed from: package-private */
            public Object writeReplace() {
                return super.writeReplace();
            }
        };
    }

    public int size() {
        return this.axesSizeProduct[0];
    }

    public boolean contains(@CheckForNull Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        List<Object> list = (List) obj;
        if (list.size() != this.axes.size()) {
            return false;
        }
        int i = 0;
        for (Object contains : list) {
            if (!((List) this.axes.get(i)).contains(contains)) {
                return false;
            }
            i++;
        }
        return true;
    }
}
