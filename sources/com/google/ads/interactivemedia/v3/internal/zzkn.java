package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkn extends zzkx {
    private List zzh = null;
    private final Context zzi;

    public zzkn(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, Context context) {
        super(zzjj, "gANfG8QAlaK6xQCfJ/5aephG6QXU3ANaJQYu4UcXCXizoZBn4LR1yFNp7MuwRzwn", "Nr8jAt12veLGV/WZ2ZuqlAKaqFe0ZsEk8BW6f32S8cI=", zzan, i, 31);
        this.zzi = context;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zzd.zzW(-1);
        this.zzd.zzS(-1);
        Context context = this.zzi;
        if (context == null) {
            context = this.zza.zzb();
        }
        if (this.zzh == null) {
            this.zzh = (List) this.zze.invoke((Object) null, new Object[]{context});
        }
        List list = this.zzh;
        if (list != null && list.size() == 2) {
            synchronized (this.zzd) {
                this.zzd.zzW(((Long) this.zzh.get(0)).longValue());
                this.zzd.zzS(((Long) this.zzh.get(1)).longValue());
            }
        }
    }
}
