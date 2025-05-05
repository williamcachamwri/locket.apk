package com.google.android.gms.internal.pal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxy implements zzjt {
    private final zzyk zza;
    private final zzkq zzb;

    public zzxy(zzyk zzyk, zzkq zzkq, int i) {
        this.zza = zzyk;
        this.zzb = zzkq;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zza2 = this.zza.zza(bArr);
        byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(0).array(), 8);
        return zzxo.zzc(zza2, this.zzb.zza(zzxo.zzc(bArr2, zza2, copyOf)));
    }
}
