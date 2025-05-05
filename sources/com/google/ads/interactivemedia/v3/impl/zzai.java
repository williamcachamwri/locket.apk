package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzai implements zzbh {
    final /* synthetic */ zzak zza;

    zzai(zzak zzak) {
        this.zza = zzak;
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        JavaScriptMessage.MsgType zzb = javaScriptMessage.zzb();
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        AdEvent.AdEventType adEventType = AdEvent.AdEventType.ALL_ADS_COMPLETED;
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = zzb.ordinal();
        if (ordinal == 28) {
            this.zza.zzi.zza(zzbu);
        } else if (ordinal == 62) {
            this.zza.zzh.zzf(zzbu.resizeAndPositionVideo);
        } else if (ordinal == 63) {
            this.zza.zzh.zzg();
        }
    }
}
