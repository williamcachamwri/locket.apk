package com.adsbynimbus.lineitem;

import com.adsbynimbus.NimbusAd;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/adsbynimbus/lineitem/LinearPriceGranularity;", "Lcom/adsbynimbus/lineitem/Mapping;", "min", "", "max", "step", "(III)V", "getMax", "()I", "getMin", "getStep", "getTarget", "", "ad", "Lcom/adsbynimbus/NimbusAd;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicPrice.kt */
public final class LinearPriceGranularity implements Mapping {
    private final int max;
    private final int min;
    private final int step;

    public LinearPriceGranularity(int i, int i2) {
        this(i, i2, 0, 4, (DefaultConstructorMarker) null);
    }

    public LinearPriceGranularity(int i, int i2, int i3) {
        this.min = i;
        this.max = i2;
        this.step = i3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LinearPriceGranularity(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 20 : i3);
    }

    public final int getMin() {
        return this.min;
    }

    public final int getMax() {
        return this.max;
    }

    public final int getStep() {
        return this.step;
    }

    public String getTarget(NimbusAd nimbusAd) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        return String.valueOf(RangesKt.coerceIn(nimbusAd.bidInCents() - (nimbusAd.bidInCents() % this.step), this.min, this.max));
    }
}
