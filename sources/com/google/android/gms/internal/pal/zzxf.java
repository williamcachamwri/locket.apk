package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxf implements zzyk {
    private static final ThreadLocal zza = new zzxe();
    private final SecretKeySpec zzb;
    private final int zzc;
    private final int zzd;

    public zzxf(byte[] bArr, int i) throws GeneralSecurityException {
        if (zzna.zza(2)) {
            zzys.zza(bArr.length);
            this.zzb = new SecretKeySpec(bArr, "AES");
            int blockSize = ((Cipher) zza.get()).getBlockSize();
            this.zzd = blockSize;
            if (i < 12 || i > blockSize) {
                throw new GeneralSecurityException("invalid IV size");
            }
            this.zzc = i;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzc;
        if (length <= Integer.MAX_VALUE - i) {
            byte[] bArr2 = new byte[(i + length)];
            byte[] zza2 = zzyq.zza(i);
            System.arraycopy(zza2, 0, bArr2, 0, this.zzc);
            int i2 = this.zzc;
            Cipher cipher = (Cipher) zza.get();
            byte[] bArr3 = new byte[this.zzd];
            System.arraycopy(zza2, 0, bArr3, 0, this.zzc);
            cipher.init(1, this.zzb, new IvParameterSpec(bArr3));
            if (cipher.doFinal(bArr, 0, length, bArr2, i2) == length) {
                return bArr2;
            }
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
        int i3 = this.zzc;
        throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - i3));
    }
}
