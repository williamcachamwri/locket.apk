package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public class zzdu {
    private static final zzcx zzb = zzcx.zza;
    protected volatile zzeo zza;
    private volatile zzcn zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdu)) {
            return false;
        }
        zzdu zzdu = (zzdu) obj;
        zzeo zzeo = this.zza;
        zzeo zzeo2 = zzdu.zza;
        if (zzeo == null && zzeo2 == null) {
            return zzb().equals(zzdu.zzb());
        }
        if (zzeo != null && zzeo2 != null) {
            return zzeo.equals(zzeo2);
        }
        if (zzeo != null) {
            zzdu.zzc(zzeo.zzl());
            return zzeo.equals(zzdu.zza);
        }
        zzc(zzeo2.zzl());
        return this.zza.equals(zzeo2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzck) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzn();
        }
        return 0;
    }

    public final zzcn zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzcn zzcn = this.zzc;
                return zzcn;
            }
            if (this.zza == null) {
                this.zzc = zzcn.zzb;
            } else {
                this.zzc = this.zza.zzk();
            }
            zzcn zzcn2 = this.zzc;
            return zzcn2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.internal.atv_ads_framework.zzeo r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.atv_ads_framework.zzeo r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.atv_ads_framework.zzeo r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0019
            r1.zza = r2     // Catch:{ zzdr -> 0x0011 }
            com.google.android.gms.internal.atv_ads_framework.zzcn r0 = com.google.android.gms.internal.atv_ads_framework.zzcn.zzb     // Catch:{ zzdr -> 0x0011 }
            r1.zzc = r0     // Catch:{ zzdr -> 0x0011 }
            goto L_0x0017
        L_0x0011:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.atv_ads_framework.zzcn r2 = com.google.android.gms.internal.atv_ads_framework.zzcn.zzb     // Catch:{ all -> 0x001b }
            r1.zzc = r2     // Catch:{ all -> 0x001b }
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzdu.zzc(com.google.android.gms.internal.atv_ads_framework.zzeo):void");
    }
}
