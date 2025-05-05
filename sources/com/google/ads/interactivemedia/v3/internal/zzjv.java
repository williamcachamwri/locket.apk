package com.google.ads.interactivemedia.v3.internal;

import android.provider.Settings;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjv extends zzkx {
    public zzjv(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2) {
        super(zzjj, "IxJDzw7riPGIi+6mP3gv4cSOSWfR5YtNTbyqZn2Ht5HKdNQC0tKhOeKDSDHSp4KX", "z9spx3v9+kPNu6he2ixuUPrYedAM+Y/M/eZi1fM7bqI=", zzan, i, 49);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzd.zzaa(3);
        try {
            int i = 1;
            boolean booleanValue = ((Boolean) this.zze.invoke((Object) null, new Object[]{this.zza.zzb()})).booleanValue();
            zzan zzan = this.zzd;
            if (true == booleanValue) {
                i = 2;
            }
            zzan.zzaa(i);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
