package com.google.ads.interactivemedia.v3.internal;

import android.os.Bundle;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import com.google.ads.interactivemedia.v3.impl.zzau;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzej {
    private final zznf zza;
    private final zzfd zzb;
    private final int zzc;

    public zzej(zzei zzei) {
        this.zza = new zznm(zzei.zza);
        this.zzb = zzei.zzc;
        this.zzc = zzei.zzb;
    }

    public final String zza(zzau zzau, String str) {
        if (this.zzc <= 0) {
            zzfk.zzc("AdsIdentityTokenLoader: invalid parameter for gksTimeoutMs");
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Bundle bundle = new Bundle();
            if (zzau != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("x-afma-token-requester-type", zzau.toString());
                bundle.putBundle("extra_headers", bundle2);
            }
            zznf zznf = this.zza;
            return (String) Tasks.await(((zznm) zznf).doRead(TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures(zzpo.zza).run(new zzni((zznm) zznf, bundle)).build()), (long) this.zzc, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzb.zzg(zzbp.ADS_IDENTITY_TOKEN_LOADER, zzbq.GET_ADSIDENTITY_TOKEN, e);
            this.zzb.zzc(str).zzk(zzfd.zza(currentTimeMillis, System.currentTimeMillis()));
            return "";
        }
    }
}
