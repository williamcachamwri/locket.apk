package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzaem {
    protected volatile zzafb zza;
    private volatile zzacw zzb;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaem)) {
            return false;
        }
        zzaem zzaem = (zzaem) obj;
        zzafb zzafb = this.zza;
        zzafb zzafb2 = zzaem.zza;
        if (zzafb == null && zzafb2 == null) {
            return zzb().equals(zzaem.zzb());
        }
        if (zzafb != null && zzafb2 != null) {
            return zzafb.equals(zzafb2);
        }
        if (zzafb != null) {
            zzaem.zzd(zzafb.zzaS());
            return zzafb.equals(zzaem.zza);
        }
        zzd(zzafb2.zzaS());
        return this.zza.equals(zzafb2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzacv) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzax();
        }
        return 0;
    }

    public final zzacw zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                zzacw zzacw = this.zzb;
                return zzacw;
            }
            if (this.zza == null) {
                this.zzb = zzacw.zzb;
            } else {
                this.zzb = this.zza.zzau();
            }
            zzacw zzacw2 = this.zzb;
            return zzacw2;
        }
    }

    public final zzafb zzc(zzafb zzafb) {
        zzafb zzafb2 = this.zza;
        this.zzb = null;
        this.zza = zzafb;
        return zzafb2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.ads.interactivemedia.v3.internal.zzafb r2) {
        /*
            r1 = this;
            com.google.ads.interactivemedia.v3.internal.zzafb r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.ads.interactivemedia.v3.internal.zzafb r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x000c:
            r1.zza = r2     // Catch:{ zzaeg -> 0x0013 }
            com.google.ads.interactivemedia.v3.internal.zzacw r0 = com.google.ads.interactivemedia.v3.internal.zzacw.zzb     // Catch:{ zzaeg -> 0x0013 }
            r1.zzb = r0     // Catch:{ zzaeg -> 0x0013 }
            goto L_0x0019
        L_0x0013:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.ads.interactivemedia.v3.internal.zzacw r2 = com.google.ads.interactivemedia.v3.internal.zzacw.zzb     // Catch:{ all -> 0x001b }
            r1.zzb = r2     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzaem.zzd(com.google.ads.interactivemedia.v3.internal.zzafb):void");
    }
}
