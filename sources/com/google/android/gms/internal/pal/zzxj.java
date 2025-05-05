package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxj implements zzjt {
    private final zzmq zza;

    public zzxj(byte[] bArr) throws GeneralSecurityException {
        if (zzna.zza(2)) {
            this.zza = new zzmq(bArr, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zza.zza(zzyq.zza(12), bArr, bArr2);
    }
}
