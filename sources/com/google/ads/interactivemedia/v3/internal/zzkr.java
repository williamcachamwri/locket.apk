package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkr extends zzkx {
    private final zzjq zzh;
    private long zzi;

    public zzkr(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, zzjq zzjq) {
        super(zzjj, "S2bj7XqeiGNcYHcKeeGhBD7AjwenAND57ZasB9YyvkNKuXmMxi2URXZo9xEY1HWC", "FYnfwG63I09Vg7QzBJMFCV+7n/vqGsbswosvmgiipjk=", zzan, i, 53);
        this.zzh = zzjq;
        if (zzjq != null) {
            this.zzi = zzjq.zza();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzh != null) {
            this.zzd.zzP(((Long) this.zze.invoke((Object) null, new Object[]{Long.valueOf(this.zzi)})).longValue());
        }
    }
}
