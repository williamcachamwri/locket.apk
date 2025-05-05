package com.google.android.tv.ads;

import java.util.List;

/* renamed from: com.google.android.tv.ads.$AutoValue_IconClickFallbackImages  reason: invalid class name */
/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
abstract class C$AutoValue_IconClickFallbackImages extends IconClickFallbackImages {
    private final List zza;

    C$AutoValue_IconClickFallbackImages(List list) {
        if (list != null) {
            this.zza = list;
            return;
        }
        throw new NullPointerException("Null iconClickFallbackImageList");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof IconClickFallbackImages) {
            return this.zza.equals(((IconClickFallbackImages) obj).getIconClickFallbackImageList());
        }
        return false;
    }

    public List<IconClickFallbackImage> getIconClickFallbackImageList() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "IconClickFallbackImages{iconClickFallbackImageList=" + obj + "}";
    }
}
