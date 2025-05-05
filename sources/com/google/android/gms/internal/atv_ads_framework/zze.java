package com.google.android.gms.internal.atv_ads_framework;

import com.google.android.datatransport.Transformer;
import java.io.IOException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final /* synthetic */ class zze implements Transformer {
    public static final /* synthetic */ zze zza = new zze();

    private /* synthetic */ zze() {
    }

    public final Object apply(Object obj) {
        zzac zzac = (zzac) obj;
        try {
            int zzn = zzac.zzn();
            byte[] bArr = new byte[zzn];
            zzcv zzz = zzcv.zzz(bArr, 0, zzn);
            zzac.zzz(zzz);
            zzz.zzA();
            return bArr;
        } catch (IOException e) {
            String name = zzac.getClass().getName();
            throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
