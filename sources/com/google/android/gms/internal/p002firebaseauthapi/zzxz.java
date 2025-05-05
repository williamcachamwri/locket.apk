package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxz {
    private ECPrivateKey zza;

    public zzxz(ECPrivateKey eCPrivateKey) {
        this.zza = eCPrivateKey;
    }

    public final byte[] zza(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i, zzyd zzyd) throws GeneralSecurityException {
        int i2 = 1;
        byte[] zza2 = zzxv.zza(bArr, zzyb.zza(this.zza, zzyb.zza(this.zza.getParams(), zzyd, bArr)));
        Mac zza3 = zzyf.zzb.zza(str);
        if (i <= zza3.getMacLength() * 255) {
            if (bArr2 == null || bArr2.length == 0) {
                zza3.init(new SecretKeySpec(new byte[zza3.getMacLength()], str));
            } else {
                zza3.init(new SecretKeySpec(bArr2, str));
            }
            byte[] doFinal = zza3.doFinal(zza2);
            byte[] bArr4 = new byte[i];
            zza3.init(new SecretKeySpec(doFinal, str));
            byte[] bArr5 = new byte[0];
            int i3 = 0;
            while (true) {
                zza3.update(bArr5);
                zza3.update(bArr3);
                zza3.update((byte) i2);
                bArr5 = zza3.doFinal();
                if (bArr5.length + i3 < i) {
                    System.arraycopy(bArr5, 0, bArr4, i3, bArr5.length);
                    i3 += bArr5.length;
                    i2++;
                } else {
                    System.arraycopy(bArr5, 0, bArr4, i3, i - i3);
                    return bArr4;
                }
            }
        } else {
            throw new GeneralSecurityException("size too large");
        }
    }
}
