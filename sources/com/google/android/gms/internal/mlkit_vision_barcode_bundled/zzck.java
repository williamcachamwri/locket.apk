package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
import java.io.IOException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public abstract class zzck<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zzcj<MessageType, BuilderType>> implements zzfo {
    protected int zzb = 0;

    /* access modifiers changed from: package-private */
    public int zzB(zzgh zzgh) {
        throw null;
    }

    public final zzdb zzC() {
        try {
            int zzE = zzE();
            zzdb zzdb = zzdb.zzb;
            byte[] bArr = new byte[zzE];
            zzdj zzA = zzdj.zzA(bArr, 0, zzE);
            zzaa(zzA);
            zzA.zzB();
            return new zzcy(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
