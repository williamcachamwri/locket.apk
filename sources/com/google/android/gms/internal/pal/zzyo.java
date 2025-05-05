package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzyo implements zzkq {
    private final zzrj zza;
    private final int zzb;

    public zzyo(zzrj zzrj, int i) throws GeneralSecurityException {
        this.zza = zzrj;
        this.zzb = i;
        if (i >= 10) {
            zzrj.zza(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final byte[] zza(byte[] bArr) throws GeneralSecurityException {
        return this.zza.zza(bArr, this.zzb);
    }
}
