package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzcx implements zzbg {
    private final zzoz<zzbg> zza;
    private final zznk zzb;
    private final zznk zzc;

    private zzcx(zzoz<zzbg> zzoz) {
        this.zza = zzoz;
        if (zzoz.zzf()) {
            zznl zza2 = zzny.zzb().zza();
            zznn zza3 = zznr.zza(zzoz);
            this.zzb = zza2.zza(zza3, "aead", "encrypt");
            this.zzc = zza2.zza(zza3, "aead", "decrypt");
            return;
        }
        this.zzb = zznr.zza;
        this.zzc = zznr.zza;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzpb next : this.zza.zza(Arrays.copyOf(bArr, 5))) {
                try {
                    byte[] zza2 = ((zzbg) next.zzd()).zza(bArr, bArr2);
                    this.zzc.zza(next.zza(), (long) bArr.length);
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzpb next2 : this.zza.zza(zzbj.zza)) {
            try {
                byte[] zza3 = ((zzbg) next2.zzd()).zza(bArr, bArr2);
                this.zzc.zza(next2.zza(), (long) bArr.length);
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        this.zzc.zza();
        throw new GeneralSecurityException("decryption failed");
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            byte[] zzb2 = this.zza.zzb().zzd().zzb(bArr, bArr2);
            this.zzb.zza(this.zza.zzb().zza(), (long) bArr.length);
            return zzb2;
        } catch (GeneralSecurityException e) {
            this.zzb.zza();
            throw e;
        }
    }
}
