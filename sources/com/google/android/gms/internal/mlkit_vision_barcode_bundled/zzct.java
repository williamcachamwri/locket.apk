package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Comparator;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzct implements Comparator {
    zzct() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzdb zzdb = (zzdb) obj;
        zzdb zzdb2 = (zzdb) obj2;
        zzcw zzq = zzdb.iterator();
        zzcw zzq2 = zzdb2.iterator();
        while (zzq.hasNext() && zzq2.hasNext()) {
            int compareTo = Integer.valueOf(zzq.zza() & 255).compareTo(Integer.valueOf(zzq2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzdb.zzd()).compareTo(Integer.valueOf(zzdb2.zzd()));
    }
}
