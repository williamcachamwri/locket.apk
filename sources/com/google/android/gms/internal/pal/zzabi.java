package com.google.android.gms.internal.pal;

import com.google.android.gms.internal.pal.zzabh;
import com.google.android.gms.internal.pal.zzabi;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzabi<MessageType extends zzabi<MessageType, BuilderType>, BuilderType extends zzabh<MessageType, BuilderType>> implements zzaef {
    protected int zza = 0;

    public final zzaby zzaI() {
        try {
            int zzat = zzat();
            zzaby zzaby = zzaby.zzb;
            byte[] bArr = new byte[zzat];
            zzach zzC = zzach.zzC(bArr);
            zzaG(zzC);
            zzC.zzD();
            return new zzabv(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    /* access modifiers changed from: package-private */
    public int zzap() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public void zzar(int i) {
        throw null;
    }

    public final byte[] zzas() {
        try {
            byte[] bArr = new byte[zzat()];
            zzach zzC = zzach.zzC(bArr);
            zzaG(zzC);
            zzC.zzD();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
