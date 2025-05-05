package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznr {
    public static final zznk zza = new zznt();

    public static <P> zznn zza(zzoz<P> zzoz) {
        zzbr zzbr;
        zznm zznm = new zznm();
        zznm.zza(zzoz.zza());
        for (List<zzpb<P>> it : zzoz.zzd()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    zzpb zzpb = (zzpb) it2.next();
                    int i = zznq.zza[zzpb.zzb().ordinal()];
                    if (i == 1) {
                        zzbr = zzbr.zza;
                    } else if (i == 2) {
                        zzbr = zzbr.zzb;
                    } else if (i == 3) {
                        zzbr = zzbr.zzc;
                    } else {
                        throw new IllegalStateException("Unknown key status");
                    }
                    int zza2 = zzpb.zza();
                    String zze = zzpb.zze();
                    if (zze.startsWith("type.googleapis.com/google.crypto.")) {
                        zze = zze.substring(34);
                    }
                    zznm.zza(zzbr, zza2, zze, zzpb.zzc().name());
                }
            }
        }
        if (zzoz.zzb() != null) {
            zznm.zza(zzoz.zzb().zza());
        }
        try {
            return zznm.zza();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
