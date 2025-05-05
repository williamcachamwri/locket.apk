package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzer extends zzfg {
    private final zzcz zzi;
    private final long zzj;
    private final long zzk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzer(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2, zzcz zzcz, long j, long j2) {
        super(zzdu, "X9PgbTHLX0FFxbl3gdPDuVwcglfXy5CDrzo8siaVNaH+OIJ6JI34Wu3QK5rLega4", "JLulXGPEHVwHK+0FG96HP9my+NvwpTQbwIaIZrjn9OU=", zzr, i, 11);
        this.zzi = zzcz;
        this.zzj = j;
        this.zzk = j2;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        zzcz zzcz = this.zzi;
        if (zzcz != null) {
            zzcx zzcx = new zzcx((String) this.zzf.invoke((Object) null, new Object[]{zzcz.zza(), Long.valueOf(this.zzj), Long.valueOf(this.zzk)}));
            synchronized (this.zze) {
                this.zze.zzz(zzcx.zza.longValue());
                if (zzcx.zzb.longValue() >= 0) {
                    this.zze.zzR(zzcx.zzb.longValue());
                }
                if (zzcx.zzc.longValue() >= 0) {
                    this.zze.zzf(zzcx.zzc.longValue());
                }
            }
        }
    }
}
