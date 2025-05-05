package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzjb;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzax {
    private final zzs zza;
    private final String zzb;

    zzax(zzs zzs, String str) {
        this.zza = zzs;
        this.zzb = str;
    }

    public final void zza(int i, String str) {
        if (str == null) {
            str = "null";
        }
        zzjb zzjb = new zzjb();
        zzjb.zza(zzaw.DEVICE_TYPE.zza(), String.valueOf(4));
        zzjb.zza(zzaw.EVENT_TYPE.zza(), String.valueOf(i - 1));
        zzjb.zza(zzaw.SPAM_CORRELATOR.zza(), this.zzb);
        zzjb.zza(zzaw.SPAM_SIGNAL.zza(), str);
        this.zza.zza("asscs", "116", zzjb.zzc());
    }
}
