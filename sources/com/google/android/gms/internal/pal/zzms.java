package com.google.android.gms.internal.pal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
abstract class zzms {
    int[] zza;
    private final int zzb;

    public zzms(byte[] bArr, int i) throws InvalidKeyException {
        if (bArr.length == 32) {
            this.zza = zzmo.zzd(bArr);
            this.zzb = i;
            return;
        }
        throw new InvalidKeyException("The key length in bytes must be 32.");
    }

    /* access modifiers changed from: package-private */
    public abstract int zza();

    /* access modifiers changed from: package-private */
    public abstract int[] zzb(int[] iArr, int i);

    /* access modifiers changed from: package-private */
    public final ByteBuffer zzc(byte[] bArr, int i) {
        int[] zzb2 = zzb(zzmo.zzd(bArr), i);
        int[] iArr = (int[]) zzb2.clone();
        zzmo.zzc(iArr);
        for (int i2 = 0; i2 < 16; i2++) {
            zzb2[i2] = zzb2[i2] + iArr[i2];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(zzb2, 0, 16);
        return order;
    }

    public final void zzd(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= bArr2.length) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr2);
            if (bArr.length == zza()) {
                int remaining = wrap.remaining();
                int i = (remaining / 64) + 1;
                for (int i2 = 0; i2 < i; i2++) {
                    ByteBuffer zzc = zzc(bArr, this.zzb + i2);
                    if (i2 == i - 1) {
                        zzxo.zza(byteBuffer, wrap, zzc, remaining % 64);
                    } else {
                        zzxo.zza(byteBuffer, wrap, zzc, 64);
                    }
                }
                return;
            }
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + zza());
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }
}
