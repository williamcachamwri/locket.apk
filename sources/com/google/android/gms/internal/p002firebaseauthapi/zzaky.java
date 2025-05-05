package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaky  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaky implements zzakz {
    public final int zza(int i, Object obj, Object obj2) {
        zzakw zzakw = (zzakw) obj;
        zzaku zzaku = (zzaku) obj2;
        if (zzakw.isEmpty()) {
            return 0;
        }
        Iterator it = zzakw.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    public final zzakx<?, ?> zza(Object obj) {
        zzaku zzaku = (zzaku) obj;
        throw new NoSuchMethodError();
    }

    public final Object zza(Object obj, Object obj2) {
        zzakw zzakw = (zzakw) obj;
        zzakw zzakw2 = (zzakw) obj2;
        if (!zzakw2.isEmpty()) {
            if (!zzakw.zzd()) {
                zzakw = zzakw.zzb();
            }
            zzakw.zza(zzakw2);
        }
        return zzakw;
    }

    public final Object zzb(Object obj) {
        return zzakw.zza().zzb();
    }

    public final Object zzc(Object obj) {
        ((zzakw) obj).zzc();
        return obj;
    }

    public final Map<?, ?> zzd(Object obj) {
        return (zzakw) obj;
    }

    public final Map<?, ?> zze(Object obj) {
        return (zzakw) obj;
    }

    zzaky() {
    }

    public final boolean zzf(Object obj) {
        return !((zzakw) obj).zzd();
    }
}
