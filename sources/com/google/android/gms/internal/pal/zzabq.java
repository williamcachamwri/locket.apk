package com.google.android.gms.internal.pal;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzabq implements Comparator {
    zzabq() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzaby zzaby = (zzaby) obj;
        zzaby zzaby2 = (zzaby) obj2;
        zzabp zzabp = new zzabp(zzaby);
        zzabp zzabp2 = new zzabp(zzaby2);
        while (zzabp.hasNext() && zzabp2.hasNext()) {
            int compareTo = Integer.valueOf(zzabp.zza() & 255).compareTo(Integer.valueOf(zzabp2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzaby.zzd()).compareTo(Integer.valueOf(zzaby2.zzd()));
    }
}
