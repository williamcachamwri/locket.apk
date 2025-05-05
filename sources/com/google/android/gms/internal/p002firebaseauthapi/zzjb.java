package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzjb implements zzbl {
    private final zzoz<zzbl> zza;
    private final zznk zzb;

    public zzjb(zzoz<zzbl> zzoz) {
        this.zza = zzoz;
        if (zzoz.zzf()) {
            zznl zza2 = zzny.zzb().zza();
            zznn zza3 = zznr.zza(zzoz);
            zza2.zza(zza3, "daead", "encrypt");
            this.zzb = zza2.zza(zza3, "daead", "decrypt");
            return;
        }
        this.zzb = zznr.zza;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzpb next : this.zza.zza(Arrays.copyOf(bArr, 5))) {
                try {
                    byte[] zza2 = ((zzbl) next.zzd()).zza(bArr, bArr2);
                    this.zzb.zza(next.zza(), (long) bArr.length);
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzpb next2 : this.zza.zza(zzbj.zza)) {
            try {
                byte[] zza3 = ((zzbl) next2.zzd()).zza(bArr, bArr2);
                this.zzb.zza(next2.zza(), (long) bArr.length);
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        this.zzb.zza();
        throw new GeneralSecurityException("decryption failed");
    }
}
