package com.google.ads.interactivemedia.pal;

import com.adsbynimbus.Nimbus;
import com.google.android.gms.internal.pal.zzaw;
import com.google.android.gms.internal.pal.zzig;
import com.google.android.gms.internal.pal.zzii;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzab implements zzii {
    public static final /* synthetic */ zzab zza = new zzab();

    private /* synthetic */ zzab() {
    }

    public final Object zza(Object obj) {
        zzaw zzaw = (zzaw) obj;
        int i = NonceLoader.zza;
        boolean z = false;
        if (!zzaw.zzc() && !zzig.zza(zzaw.zza(), Nimbus.EMPTY_AD_ID)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
