package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzki  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzki implements zzbo {
    private final zzoz<zzbo> zza;
    private final zznk zzb;

    public zzki(zzoz<zzbo> zzoz) {
        this.zza = zzoz;
        if (zzoz.zzf()) {
            this.zzb = zzny.zzb().zza().zza(zznr.zza(zzoz), "hybrid_decrypt", "decrypt");
        } else {
            this.zzb = zznr.zza;
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzpb next : this.zza.zza(Arrays.copyOfRange(bArr, 0, 5))) {
                try {
                    byte[] zza2 = ((zzbo) next.zzd()).zza(bArr, bArr2);
                    this.zzb.zza(next.zza(), (long) bArr.length);
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzpb next2 : this.zza.zza(zzbj.zza)) {
            try {
                byte[] zza3 = ((zzbo) next2.zzd()).zza(bArr, bArr2);
                this.zzb.zza(next2.zza(), (long) bArr.length);
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        this.zzb.zza();
        throw new GeneralSecurityException("decryption failed");
    }
}
