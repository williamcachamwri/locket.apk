package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxs implements zzjx {
    private final ECPrivateKey zza;
    private final zzxu zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzxr zze;

    public zzxs(ECPrivateKey eCPrivateKey, byte[] bArr, String str, int i, zzxr zzxr) throws GeneralSecurityException {
        this.zza = eCPrivateKey;
        this.zzb = new zzxu(eCPrivateKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzxr;
    }
}
