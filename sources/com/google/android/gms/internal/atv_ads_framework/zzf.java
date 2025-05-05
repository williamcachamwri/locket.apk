package com.google.android.gms.internal.atv_ads_framework;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.Destination;
import com.google.android.datatransport.runtime.TransportRuntime;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzf {
    private static zzf zza;
    private final TransportFactory zzb;
    private final Context zzc;

    private zzf(Context context, TransportFactory transportFactory) {
        context.getClass();
        this.zzc = context;
        transportFactory.getClass();
        this.zzb = transportFactory;
    }

    public static synchronized zzf zza(Context context) {
        zzf zzf;
        synchronized (zzf.class) {
            if (zza == null) {
                TransportRuntime.initialize(context.getApplicationContext());
                zza = new zzf(context.getApplicationContext(), TransportRuntime.getInstance().newFactory((Destination) CCTDestination.INSTANCE));
            }
            zzf = zza;
        }
        return zzf;
    }

    private final void zzd(zzac zzac) {
        this.zzb.getTransport("TV_ADS_LIB", zzac.class, Encoding.of("proto"), zze.zza).send(Event.ofData(zzac));
    }

    public final void zzb(zzn zzn) {
        zzab zzb2 = zzad.zzb(this.zzc);
        zzb2.zzb(zzn);
        zzd((zzac) zzb2.zzi());
    }

    public final void zzc(zzaa zzaa) {
        zzab zzb2 = zzad.zzb(this.zzc);
        zzb2.zzf(zzaa);
        zzd((zzac) zzb2.zzi());
    }
}
