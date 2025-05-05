package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzim implements Comparator<zzik> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzik zzik = (zzik) obj;
        zzik zzik2 = (zzik) obj2;
        zziq zziq = (zziq) zzik.iterator();
        zziq zziq2 = (zziq) zzik2.iterator();
        while (zziq.hasNext() && zziq2.hasNext()) {
            int compare = Integer.compare(zzik.zza(zziq.zza()), zzik.zza(zziq2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzik.zzb(), zzik2.zzb());
    }

    zzim() {
    }
}
