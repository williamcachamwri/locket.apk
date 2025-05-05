package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzop implements zzoe {
    private final zzyv zza;
    private final zzyv zzb;

    private zzop(byte[] bArr, byte[] bArr2) {
        this.zza = zzyv.zzb(bArr);
        this.zzb = zzyv.zzb(bArr2);
    }

    static zzop zza(byte[] bArr) throws GeneralSecurityException {
        return new zzop(bArr, zzyt.zzc(bArr));
    }
}
