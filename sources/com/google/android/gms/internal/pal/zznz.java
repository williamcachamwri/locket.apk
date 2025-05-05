package com.google.android.gms.internal.pal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznz {
    private static final byte[] zza = new byte[0];
    private final zzny zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private BigInteger zzg = BigInteger.ZERO;

    private zznz(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzny zzny) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzny;
    }

    static zznz zzc(byte[] bArr, byte[] bArr2, zzoc zzoc, zznx zznx, zzny zzny, byte[] bArr3) throws GeneralSecurityException {
        zznx zznx2 = zznx;
        byte[] zzb2 = zzol.zzb(zzoc.zzb(), zznx.zzc(), zzny.zzb());
        byte[] bArr4 = zzol.zzl;
        byte[] bArr5 = zza;
        byte[] zzc2 = zzxo.zzc(zzol.zza, zznx2.zze(bArr4, bArr5, "psk_id_hash", zzb2), zznx2.zze(zzol.zzl, bArr3, "info_hash", zzb2));
        zznx zznx3 = zznx;
        byte[] zze2 = zznx2.zze(bArr2, bArr5, "secret", zzb2);
        byte[] bArr6 = zzc2;
        byte[] bArr7 = zzb2;
        return new zznz(bArr, zznx3.zzd(zze2, bArr6, "key", bArr7, zzny.zza()), zznx3.zzd(zze2, bArr6, "base_nonce", bArr7, 12), BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzny);
    }

    private final synchronized byte[] zzd() throws GeneralSecurityException {
        byte[] zzd2;
        byte[] bArr = this.zze;
        byte[] byteArray = this.zzg.toByteArray();
        int length = byteArray.length;
        if (length != 12) {
            if (length > 13) {
                throw new GeneralSecurityException("integer too large");
            } else if (length != 13) {
                byte[] bArr2 = new byte[12];
                System.arraycopy(byteArray, 0, bArr2, 12 - length, length);
                byteArray = bArr2;
            } else if (byteArray[0] == 0) {
                byteArray = Arrays.copyOfRange(byteArray, 1, 13);
            } else {
                throw new GeneralSecurityException("integer too large");
            }
        }
        zzd2 = zzxo.zzd(bArr, byteArray);
        if (this.zzg.compareTo(this.zzc) < 0) {
            this.zzg = this.zzg.add(BigInteger.ONE);
        } else {
            throw new GeneralSecurityException("message limit reached");
        }
        return zzd2;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zzc(this.zzd, zzd(), bArr, bArr2);
    }
}
