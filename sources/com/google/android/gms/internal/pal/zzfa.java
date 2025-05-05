package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfa extends zzfg {
    private final zzeb zzi;
    private long zzj;

    public zzfa(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, zzeb zzeb) {
        super(zzdu, "mkunJHFc5vhTAVOcsaNSYx7OvFB6slgbORGrA/joIDO0IYq5rQvDcAbp2AI6CPUh", "k8GEQUoJxJPI/0jAlfeUix8QD7WaaXAfMcSQAzrpgrU=", zzr, i, 53);
        this.zzi = zzeb;
        if (zzeb != null) {
            this.zzj = zzeb.zza();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzi != null) {
            this.zze.zzQ(((Long) this.zzf.invoke((Object) null, new Object[]{Long.valueOf(this.zzj)})).longValue());
        }
    }
}
