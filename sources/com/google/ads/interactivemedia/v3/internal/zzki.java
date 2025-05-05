package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzki extends zzkx {
    private final zzim zzh;
    private final long zzi;
    private final long zzj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzki(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, zzim zzim, long j, long j2) {
        super(zzjj, "K0xZIBPInE6j6xPLxhKGMY561g1nMY757L1d/vVVfLAbZ7cYe/kji+8cDrSya44i", "LymMUKNT3cAvWIhxX52CTQ3uE86eU+14G9dqvWvUzWk=", zzan, i, 11);
        this.zzh = zzim;
        this.zzi = j;
        this.zzj = j2;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        zzim zzim = this.zzh;
        if (zzim != null) {
            zzik zzik = new zzik((String) this.zze.invoke((Object) null, new Object[]{zzim.zzb(), Long.valueOf(this.zzi), Long.valueOf(this.zzj)}));
            synchronized (this.zzd) {
                this.zzd.zzz(zzik.zza.longValue());
                if (zzik.zzb.longValue() >= 0) {
                    this.zzd.zzQ(zzik.zzb.longValue());
                }
                if (zzik.zzc.longValue() >= 0) {
                    this.zzd.zzf(zzik.zzc.longValue());
                }
            }
        }
    }
}
