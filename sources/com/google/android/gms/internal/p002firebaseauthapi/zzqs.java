package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzqs implements zzci {
    private final zzoz<zzci> zza;
    private final zznk zzb;
    private final zznk zzc;

    private zzqs(zzoz<zzci> zzoz) {
        this.zza = zzoz;
        if (zzoz.zzf()) {
            zznl zza2 = zzny.zzb().zza();
            zznn zza3 = zznr.zza(zzoz);
            this.zzb = zza2.zza(zza3, "mac", "compute");
            this.zzc = zza2.zza(zza3, "mac", "verify");
            return;
        }
        this.zzb = zznr.zza;
        this.zzc = zznr.zza;
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzpb next : this.zza.zza(Arrays.copyOf(bArr, 5))) {
                try {
                    ((zzci) next.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next.zza(), (long) bArr2.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            for (zzpb next2 : this.zza.zze()) {
                try {
                    ((zzci) next2.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next2.zza(), (long) bArr2.length);
                    return;
                } catch (GeneralSecurityException unused2) {
                }
            }
            this.zzc.zza();
            throw new GeneralSecurityException("invalid MAC");
        }
        this.zzc.zza();
        throw new GeneralSecurityException("tag too short");
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        try {
            byte[] zza2 = this.zza.zzb().zzd().zza(bArr);
            this.zzb.zza(this.zza.zzb().zza(), (long) bArr.length);
            return zza2;
        } catch (GeneralSecurityException e) {
            this.zzb.zza();
            throw e;
        }
    }
}
