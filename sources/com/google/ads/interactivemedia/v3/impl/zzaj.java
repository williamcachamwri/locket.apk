package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.impl.data.zzbl;
import com.google.ads.interactivemedia.v3.impl.data.zzbm;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zze;
import java.util.ArrayList;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaj implements zzbh {
    final /* synthetic */ zzak zza;

    zzaj(zzak zzak) {
        this.zza = zzak;
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        ArrayList arrayList = new ArrayList();
        zzbm zzbm = zzbu.iconsView;
        if (!(zzbm == null || zzbm.icons() == null)) {
            for (zzbl add : zzbu.iconsView.icons()) {
                arrayList.add(add);
            }
        }
        zzak zzak = this.zza;
        zzak.zzo(new zze(zzak.zza, arrayList, zzak.zzb));
    }
}
