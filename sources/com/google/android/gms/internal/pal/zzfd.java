package com.google.android.gms.internal.pal;

import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfd extends zzfg {
    private final View zzi;

    public zzfd(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, View view) {
        super(zzdu, "xcWDoPM3ZfO4P10VSUmZKRTMvsXPXnglJL31bwAJBgJGdSUy2IQG17s4MILOncV2", "9rXsTdb/WXYONX554dN5CJ2eqpcy9gFPMPi8uAjaHTA=", zzr, i, 57);
        this.zzi = view;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzi != null) {
            Boolean bool = (Boolean) zzfv.zzc().zzb(zzgk.zzcD);
            Boolean bool2 = (Boolean) zzfv.zzc().zzb(zzgk.zziC);
            zzdy zzdy = new zzdy((String) this.zzf.invoke((Object) null, new Object[]{this.zzi, this.zzb.zzb().getResources().getDisplayMetrics(), bool, bool2}));
            zzad zza = zzae.zza();
            zza.zzb(zzdy.zza.longValue());
            zza.zzd(zzdy.zzb.longValue());
            zza.zze(zzdy.zzc.longValue());
            if (bool2.booleanValue()) {
                zza.zzc(zzdy.zze.longValue());
            }
            if (bool.booleanValue()) {
                zza.zza(zzdy.zzd.longValue());
            }
            this.zze.zzZ((zzae) zza.zzan());
        }
    }
}
