package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.RemoteException;
import androidx.exifinterface.media.ExifInterface;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzgg {
    private final Context zza;
    private final zzuv zzb;
    private final zzfd zzc;
    private final zzuu zzd;

    public zzgg(Context context, zzuv zzuv, TestingConfiguration testingConfiguration, zzfd zzfd) {
        this.zza = context;
        this.zzb = zzuv;
        this.zzc = zzfd;
        this.zzd = zzuv.zza(new zzge(context, zzuv, testingConfiguration, zzfd));
    }

    public final String zza(Integer num) {
        if (num == null || num.intValue() <= 0) {
            return zzb();
        }
        zzuu zza2 = this.zzb.zza(new zzgf(this));
        try {
            return (String) zza2.get((long) num.intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.zzc.zzg(zzbp.SPAM_MS_PARAMETER_LOADER, zzbq.GET_SPAM_MS_PARAMETER, e);
            String str = true != (e instanceof TimeoutException) ? ExifInterface.GPS_MEASUREMENT_3D : "17";
            zza2.cancel(false);
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        zzqf zzf = zzqf.zzf();
        try {
            zzf = (zzqf) this.zzd.get();
        } catch (InterruptedException | ExecutionException unused) {
        }
        if (!zzf.zze()) {
            return ExifInterface.GPS_MEASUREMENT_3D;
        }
        try {
            return ((zzlj) zzf.zzb()).zza(this.zza);
        } catch (RemoteException e) {
            this.zzc.zzg(zzbp.SPAM_MS_PARAMETER_LOADER, zzbq.GET_SPAM_MS_PARAMETER_FROM_ADSHIELD, e);
            return ExifInterface.GPS_MEASUREMENT_3D;
        }
    }
}
