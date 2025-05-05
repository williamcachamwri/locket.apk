package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzyc implements zzbg {
    private final zzys zza;
    private final zzci zzb;
    private final int zzc;
    private final byte[] zzd;

    public static zzbg zza(zzcz zzcz) throws GeneralSecurityException {
        return new zzyc(new zzxn(zzcz.zze().zza(zzbq.zza()), zzcz.zzc().zzd()), new zzyz(new zzyx("HMAC" + String.valueOf(zzcz.zzc().zzg()), new SecretKeySpec(zzcz.zzf().zza(zzbq.zza()), "HMAC")), zzcz.zzc().zze()), zzcz.zzc().zze(), zzcz.zzd().zzb());
    }

    private zzyc(zzys zzys, zzci zzci, int i, byte[] bArr) {
        this.zza = zzys;
        this.zzb = zzci;
        this.zzc = i;
        this.zzd = bArr;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzc;
        byte[] bArr3 = this.zzd;
        if (length < i + bArr3.length) {
            throw new GeneralSecurityException("Decryption failed (ciphertext too short).");
        } else if (zzpr.zza(bArr3, bArr)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, this.zzd.length, bArr.length - this.zzc);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, bArr.length - this.zzc, bArr.length);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
            this.zzb.zza(copyOfRange2, zzxv.zza(bArr2, copyOfRange, copyOf));
            return this.zza.zza(copyOfRange);
        } else {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzb2 = this.zza.zzb(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
        return zzxv.zza(this.zzd, zzb2, this.zzb.zza(zzxv.zza(bArr2, zzb2, copyOf)));
    }
}
