package com.adsbynimbus.render.internal;

import android.graphics.Rect;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Comparisons.kt */
public final class ExposureTrackerKt$exposedPercent$$inlined$sortedBy$1<T> implements Comparator {
    public final int compare(T t, T t2) {
        Rect rect = (Rect) t;
        Rect rect2 = (Rect) t2;
        return ComparisonsKt.compareValues(Integer.valueOf(rect.width() + rect.height() + rect.left + rect.top), Integer.valueOf(rect2.width() + rect2.height() + rect2.left + rect2.top));
    }
}
