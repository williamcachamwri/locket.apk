package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpz implements zzpd<zzqa, zzqa> {
    private static final zzpz zza = new zzpz();

    public final Class<zzqa> zza() {
        return zzqa.class;
    }

    public final Class<zzqa> zzb() {
        return zzqa.class;
    }

    public final /* synthetic */ Object zza(zzoz zzoz) throws GeneralSecurityException {
        if (zzoz == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        } else if (zzoz.zzb() != null) {
            for (List<zzpb> it : zzoz.zzd()) {
                for (zzpb zzd : it) {
                    zzqa zzqa = (zzqa) zzd.zzd();
                }
            }
            return new zzqc(zzoz);
        } else {
            throw new GeneralSecurityException("no primary in primitive set");
        }
    }

    private zzpz() {
    }

    static void zzc() throws GeneralSecurityException {
        zzoc.zza().zza(zza);
    }
}
