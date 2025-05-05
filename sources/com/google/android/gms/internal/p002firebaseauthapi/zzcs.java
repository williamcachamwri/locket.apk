package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import com.google.android.gms.internal.p002firebaseauthapi.zzwd;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzcs {
    public static zzwd zza(zzwa zzwa) {
        zzwd.zza zza = zzwd.zza().zza(zzwa.zzb());
        for (zzwa.zzb next : zzwa.zze()) {
            zza.zza((zzwd.zzb) ((zzajy) zzwd.zzb.zza().zza(next.zzb().zzf()).zza(next.zzc()).zza(next.zzf()).zza(next.zza()).zze()));
        }
        return (zzwd) ((zzajy) zza.zze());
    }

    static {
        Charset.forName("UTF-8");
    }

    public static void zzb(zzwa zzwa) throws GeneralSecurityException {
        int zzb = zzwa.zzb();
        int i = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzwa.zzb next : zzwa.zze()) {
            if (next.zzc() == zzvv.ENABLED) {
                if (!next.e_()) {
                    throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzf() == zzws.UNKNOWN_PREFIX) {
                    throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(next.zza())}));
                } else if (next.zzc() != zzvv.UNKNOWN_STATUS) {
                    if (next.zza() == zzb) {
                        if (!z) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("keyset contains multiple primary keys");
                        }
                    }
                    if (next.zzb().zzb() != zzvq.zzb.ASYMMETRIC_PUBLIC) {
                        z2 = false;
                    }
                    i++;
                } else {
                    throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(next.zza())}));
                }
            }
        }
        if (i == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        } else if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
