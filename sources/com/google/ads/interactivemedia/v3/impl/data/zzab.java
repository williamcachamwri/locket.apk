package com.google.ads.interactivemedia.v3.impl.data;

import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzab extends zzbm {
    private final List<zzbl> icons;

    zzab(List<zzbl> list) {
        if (list != null) {
            this.icons = list;
            return;
        }
        throw new NullPointerException("Null icons");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbm) {
            return this.icons.equals(((zzbm) obj).icons());
        }
        return false;
    }

    public int hashCode() {
        return this.icons.hashCode() ^ 1000003;
    }

    public List<zzbl> icons() {
        return this.icons;
    }

    public String toString() {
        String valueOf = String.valueOf(this.icons);
        return "IconsViewData{icons=" + valueOf + "}";
    }
}
