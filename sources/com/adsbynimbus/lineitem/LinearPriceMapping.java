package com.adsbynimbus.lineitem;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001b\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/adsbynimbus/lineitem/LinearPriceMapping;", "Lcom/adsbynimbus/lineitem/Mapping;", "Ljava/util/Comparator;", "Lcom/adsbynimbus/lineitem/LinearPriceGranularity;", "Lkotlin/Comparator;", "granularities", "", "([Lcom/adsbynimbus/lineitem/LinearPriceGranularity;)V", "getGranularities", "()[Lcom/adsbynimbus/lineitem/LinearPriceGranularity;", "[Lcom/adsbynimbus/lineitem/LinearPriceGranularity;", "compare", "", "o1", "o2", "getTarget", "", "ad", "Lcom/adsbynimbus/NimbusAd;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicPrice.kt */
public final class LinearPriceMapping implements Mapping, Comparator<LinearPriceGranularity> {
    private final LinearPriceGranularity[] granularities;

    public LinearPriceMapping(LinearPriceGranularity... linearPriceGranularityArr) {
        Intrinsics.checkNotNullParameter(linearPriceGranularityArr, "granularities");
        this.granularities = linearPriceGranularityArr;
        ArraysKt.sortWith(linearPriceGranularityArr, this);
    }

    public final LinearPriceGranularity[] getGranularities() {
        return this.granularities;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.adsbynimbus.lineitem.LinearPriceGranularity} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getTarget(com.adsbynimbus.NimbusAd r8) {
        /*
            r7 = this;
            java.lang.String r0 = "ad"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            com.adsbynimbus.lineitem.LinearPriceGranularity[] r0 = r7.granularities
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x000a:
            if (r3 >= r1) goto L_0x0021
            r4 = r0[r3]
            int r5 = r8.bidInCents()
            int r6 = r4.getMax()
            if (r5 >= r6) goto L_0x001a
            r5 = 1
            goto L_0x001b
        L_0x001a:
            r5 = r2
        L_0x001b:
            if (r5 == 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            int r3 = r3 + 1
            goto L_0x000a
        L_0x0021:
            r4 = 0
        L_0x0022:
            if (r4 != 0) goto L_0x002b
            java.lang.Object r0 = kotlin.collections.ArraysKt.last((T[]) r0)
            r4 = r0
            com.adsbynimbus.lineitem.LinearPriceGranularity r4 = (com.adsbynimbus.lineitem.LinearPriceGranularity) r4
        L_0x002b:
            java.lang.String r8 = r4.getTarget(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.lineitem.LinearPriceMapping.getTarget(com.adsbynimbus.NimbusAd):java.lang.String");
    }

    public int compare(LinearPriceGranularity linearPriceGranularity, LinearPriceGranularity linearPriceGranularity2) {
        Intrinsics.checkNotNullParameter(linearPriceGranularity, "o1");
        Intrinsics.checkNotNullParameter(linearPriceGranularity2, "o2");
        return linearPriceGranularity.getMin() - linearPriceGranularity2.getMin();
    }
}
