package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzaw;
import com.google.android.gms.internal.pal.zzii;
import com.google.android.gms.internal.pal.zzjc;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzaf implements zzii {
    public static final /* synthetic */ zzaf zza = new zzaf();

    private /* synthetic */ zzaf() {
    }

    public final Object zza(Object obj) {
        zzaw zzaw = (zzaw) obj;
        int i = NonceLoader.zza;
        return zzjc.zzf(zzak.ADVERTISING_ID.zza(), zzaw.zza(), zzak.ID_TYPE.zza(), zzaw.zzb(), zzak.LIMIT_AD_TRACKING.zza(), true != zzaw.zzc() ? "0" : "1");
    }
}
