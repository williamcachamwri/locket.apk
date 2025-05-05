package com.google.android.gms.internal.pal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzmh implements zzjt {
    private static final byte[] zza = new byte[0];
    private final zzvt zzb;
    private final zzjt zzc;

    public zzmh(zzvt zzvt, zzjt zzjt) {
        this.zzb = zzvt;
        this.zzc = zzjt;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zzas = zzlf.zzd(this.zzb).zzas();
        byte[] zza2 = this.zzc.zza(zzas, zza);
        byte[] zza3 = ((zzjt) zzlf.zzi(this.zzb.zzf(), zzas, zzjt.class)).zza(bArr, bArr2);
        int length = zza2.length;
        return ByteBuffer.allocate(length + 4 + zza3.length).putInt(length).put(zza2).put(zza3).array();
    }
}
