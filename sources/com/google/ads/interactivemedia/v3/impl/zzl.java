package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.BaseRequest;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignals;
import com.google.ads.interactivemedia.v3.impl.data.zzbn;
import com.google.ads.interactivemedia.v3.impl.data.zzcf;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfd;
import com.google.ads.interactivemedia.v3.internal.zzqf;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import com.google.ads.interactivemedia.v3.internal.zzuk;
import com.google.ads.interactivemedia.v3.internal.zzuu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzl implements Callable {
    public final /* synthetic */ BaseRequest zza;
    public final /* synthetic */ zzuu zzb;
    public final /* synthetic */ zzahj zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ zzuu zze;
    public final /* synthetic */ zzuu zzf;
    public final /* synthetic */ zzuu zzg;

    public /* synthetic */ zzl(zzy zzy, BaseRequest baseRequest, zzuu zzuu, zzahj zzahj, long j, zzuu zzuu2, zzuu zzuu3, zzuu zzuu4) {
        this.zza = baseRequest;
        this.zzb = zzuu;
        this.zzc = zzahj;
        this.zzd = j;
        this.zze = zzuu2;
        this.zzf = zzuu3;
        this.zzg = zzuu4;
    }

    public final Object call() {
        SecureSignals secureSignals = this.zza.getSecureSignals();
        List list = (List) zzy.zzt(this.zzb, new ArrayList());
        if (secureSignals != null) {
            list.add(zzcf.createBy1stPartyData(secureSignals));
        }
        zzuu zzuu = this.zzg;
        zzuu zzuu2 = this.zzf;
        zzuu zzuu3 = this.zze;
        this.zzc.zzh(zzfd.zza(this.zzd, System.currentTimeMillis()));
        zzrm zzk = zzrm.zzk(list);
        zzrp zzc2 = zzrp.zzc((Map) zzy.zzt(zzuu, new HashMap()));
        return new zzad(zzqf.zzh((zzbn) zzuk.zzd(zzuu3)), (String) zzuk.zzd(zzuu2), zzk, zzc2);
    }
}
