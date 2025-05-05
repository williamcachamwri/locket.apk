package com.google.android.gms.internal.fido;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzcr implements Comparator {
    zzcr() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzcz zzcz = (zzcz) obj;
        zzcz zzcz2 = (zzcz) obj2;
        zzcq zzcq = new zzcq(zzcz);
        zzcq zzcq2 = new zzcq(zzcz2);
        while (zzcq.hasNext() && zzcq2.hasNext()) {
            int compareTo = Integer.valueOf(zzcq.zza() & 255).compareTo(Integer.valueOf(zzcq2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzcz.zzd()).compareTo(Integer.valueOf(zzcz2.zzd()));
    }
}
