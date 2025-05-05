package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.zzacg;
import com.google.ads.interactivemedia.v3.internal.zzach;
import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzach<MessageType extends zzach<MessageType, BuilderType>, BuilderType extends zzacg<MessageType, BuilderType>> implements zzafb {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zzat(zzaft zzaft) {
        throw null;
    }

    public final zzacw zzau() {
        try {
            int zzax = zzax();
            zzacw zzacw = zzacw.zzb;
            byte[] bArr = new byte[zzax];
            zzadc zzadc = new zzadc(bArr, 0, zzax);
            zzaR(zzadc);
            zzadc.zzB();
            return new zzacv(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final byte[] zzav() {
        try {
            int zzax = zzax();
            byte[] bArr = new byte[zzax];
            zzadc zzadc = new zzadc(bArr, 0, zzax);
            zzaR(zzadc);
            zzadc.zzB();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
