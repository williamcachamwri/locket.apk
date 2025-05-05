package com.google.android.gms.internal.atv_ads_framework;

import com.google.android.gms.internal.atv_ads_framework.zzbz;
import com.google.android.gms.internal.atv_ads_framework.zzca;
import java.io.IOException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class zzca<MessageType extends zzca<MessageType, BuilderType>, BuilderType extends zzbz<MessageType, BuilderType>> implements zzeo {
    protected int zza = 0;

    /* access modifiers changed from: package-private */
    public int zzj(zzey zzey) {
        throw null;
    }

    public final zzcn zzk() {
        try {
            int zzn = zzn();
            zzcn zzcn = zzcn.zzb;
            byte[] bArr = new byte[zzn];
            zzcv zzz = zzcv.zzz(bArr, 0, zzn);
            zzz(zzz);
            zzz.zzA();
            return new zzck(bArr);
        } catch (IOException e) {
            String name = getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
