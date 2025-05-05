package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzon implements zzoe {
    private final zzyv zza;
    private final zzyv zzb;

    private zzon(byte[] bArr, byte[] bArr2) {
        this.zza = zzyv.zzb(bArr);
        this.zzb = zzyv.zzb(bArr2);
    }

    static zzon zza(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        zzxx.zze(zzxx.zzj(zzxx.zzk(i), 1, bArr2), zzxx.zzh(i, bArr));
        return new zzon(bArr, bArr2);
    }
}
