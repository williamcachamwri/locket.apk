package com.google.ads.interactivemedia.pal;

import android.net.Uri;
import com.google.android.gms.internal.pal.zzjb;
import com.google.android.gms.internal.pal.zzjc;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzs {
    private final String zza;
    private final String zzb;
    private final String zzc;

    zzs(zzq zzq) {
        this.zza = zzq.zzb();
        this.zzb = zzq.zzc();
        this.zzc = zzq.zza();
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, Map map) {
        zzjb zzjb = new zzjb();
        zzjb.zzb(map);
        zzjb.zza(zzr.SDKV.zza(), this.zzb);
        zzjb.zza(zzr.PALV.zza(), this.zza);
        zzjb.zza(zzr.CORRELATOR.zza(), this.zzc);
        zzjb.zza(zzr.EVENT_ID.zza(), str2);
        zzjb.zza(zzr.LOGGER_ID.zza(), str);
        zzjc zzc2 = zzjb.zzc();
        Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
        for (String str3 : zzc2.keySet()) {
            buildUpon.appendQueryParameter(str3, (String) zzc2.get(str3));
        }
        new zzo(this, buildUpon.build().toString()).start();
    }
}
