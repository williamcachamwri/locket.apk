package com.facebook.fresco.animation.bitmap.preparation.ondemandanimation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/facebook/fresco/animation/bitmap/preparation/ondemandanimation/CircularList;", "", "size", "", "(I)V", "getSize", "()I", "getPosition", "target", "isTargetAhead", "", "from", "length", "sublist", "", "animated-drawable_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CircularList.kt */
public final class CircularList {
    private final int size;

    public CircularList(int i) {
        this.size = i;
    }

    public final int getSize() {
        return this.size;
    }

    public final boolean isTargetAhead(int i, int i2, int i3) {
        int position = getPosition(i3 + i);
        if (i >= position) {
            if (i <= i2 && i2 <= this.size) {
                return true;
            }
            if (i2 >= 0 && i2 <= position) {
                return true;
            }
        } else if (i <= i2 && i2 <= position) {
            return true;
        }
        return false;
    }

    public final int getPosition(int i) {
        int i2 = i % this.size;
        Integer valueOf = Integer.valueOf(i2);
        if (!(valueOf.intValue() >= 0)) {
            valueOf = null;
        }
        return valueOf != null ? valueOf.intValue() : i2 + this.size;
    }

    public final List<Integer> sublist(int i, int i2) {
        Iterable until = RangesKt.until(0, i2);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(getPosition(((IntIterator) it).nextInt() + i)));
        }
        return (List) arrayList;
    }
}
