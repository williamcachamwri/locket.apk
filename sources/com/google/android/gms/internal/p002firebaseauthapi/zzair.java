package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Comparator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzair  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzair implements Comparator<zzaip> {
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzaip zzaip = (zzaip) obj;
        zzaip zzaip2 = (zzaip) obj2;
        zzaiv zzaiv = (zzaiv) zzaip.iterator();
        zzaiv zzaiv2 = (zzaiv) zzaip2.iterator();
        while (zzaiv.hasNext() && zzaiv2.hasNext()) {
            int compare = Integer.compare(zzaip.zza(zzaiv.zza()), zzaip.zza(zzaiv2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzaip.zzb(), zzaip2.zzb());
    }

    zzair() {
    }
}
