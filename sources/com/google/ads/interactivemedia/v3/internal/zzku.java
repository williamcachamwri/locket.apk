package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzku extends zzkx {
    private final View zzh;

    public zzku(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, View view) {
        super(zzjj, "eWuCTuBs0C/3RzXp2Vb1vvOoZ3gI6cRGRcjUOPnlCHO99O+zvrqChDuDIos51zgD", "J2273uJe3SOyR84V1pdek1TQgOTMXJxG9MDUVU7F0ew=", zzan, i, 57);
        this.zzh = view;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzh != null) {
            Boolean bool = (Boolean) zzls.zzc().zza(zzmj.zzz);
            Boolean bool2 = (Boolean) zzls.zzc().zza(zzmj.zzB);
            zzjn zzjn = new zzjn((String) this.zze.invoke((Object) null, new Object[]{this.zzh, this.zza.zzb().getResources().getDisplayMetrics(), bool, bool2}));
            zzbn zza = zzbo.zza();
            zza.zzb(zzjn.zza.longValue());
            zza.zzd(zzjn.zzb.longValue());
            zza.zze(zzjn.zzc.longValue());
            if (bool2.booleanValue()) {
                zza.zzc(zzjn.zze.longValue());
            }
            if (bool.booleanValue()) {
                zza.zza(zzjn.zzd.longValue());
            }
            this.zzd.zzY((zzbo) zza.zzal());
        }
    }
}
