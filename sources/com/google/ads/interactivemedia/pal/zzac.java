package com.google.ads.interactivemedia.pal;

import com.google.android.gms.appset.AppSetIdInfo;
import com.google.android.gms.internal.pal.zzii;
import com.google.android.gms.internal.pal.zzjc;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzac implements zzii {
    public static final /* synthetic */ zzac zza = new zzac();

    private /* synthetic */ zzac() {
    }

    public final Object zza(Object obj) {
        AppSetIdInfo appSetIdInfo = (AppSetIdInfo) obj;
        int i = NonceLoader.zza;
        return zzjc.zze(zzak.PER_VENDOR_ID.zza(), appSetIdInfo.getId(), zzak.PER_VENDOR_ID_SCOPE.zza(), String.valueOf(appSetIdInfo.getScope()));
    }
}
