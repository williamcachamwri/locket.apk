package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzoq {
    private final zzjt zza;
    private final zzjw zzb;

    public zzoq(zzjt zzjt) {
        this.zza = zzjt;
        this.zzb = null;
    }

    public zzoq(zzjw zzjw) {
        this.zza = null;
        this.zzb = zzjw;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzjt zzjt = this.zza;
        if (zzjt != null) {
            return zzjt.zza(bArr, bArr2);
        }
        return this.zzb.zza(bArr, bArr2);
    }
}
