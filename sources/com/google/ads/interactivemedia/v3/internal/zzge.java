package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Build;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzge implements Callable {
    public final /* synthetic */ Context zza;
    public final /* synthetic */ zzuv zzb;
    public final /* synthetic */ TestingConfiguration zzc;
    public final /* synthetic */ zzfd zzd;

    public /* synthetic */ zzge(Context context, zzuv zzuv, TestingConfiguration testingConfiguration, zzfd zzfd) {
        this.zza = context;
        this.zzb = zzuv;
        this.zzc = testingConfiguration;
        this.zzd = zzfd;
    }

    public final Object call() {
        zzl zza2 = zzm.zza();
        zza2.zze(3);
        zza2.zzd("a.3.35.1");
        zza2.zzb(false);
        zza2.zzc(false);
        Context context = this.zza;
        int i = Build.VERSION.SDK_INT;
        zzuv zzuv = this.zzb;
        if (i < 30 && zzel.zzd(context, this.zzc)) {
            zzah zzb2 = zzai.zzb();
            zzb2.zza(true);
            zza2.zza((zzai) zzb2.zzal());
        }
        try {
            return zzqf.zzh(new zzlj(context, zzuv, (zzm) zza2.zzal()));
        } catch (RuntimeException e) {
            this.zzd.zzg(zzbp.SPAM_MS_PARAMETER_LOADER, zzbq.SETUP_AD_SHIELD, e);
            return zzqf.zzf();
        }
    }
}
