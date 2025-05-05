package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public class zzadl {
    private static final zzacm zzb = zzacm.zza();
    protected volatile zzaef zza;
    private volatile zzaby zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzadl)) {
            return false;
        }
        zzadl zzadl = (zzadl) obj;
        zzaef zzaef = this.zza;
        zzaef zzaef2 = zzadl.zza;
        if (zzaef == null && zzaef2 == null) {
            return zzb().equals(zzadl.zzb());
        }
        if (zzaef != null && zzaef2 != null) {
            return zzaef.equals(zzaef2);
        }
        if (zzaef != null) {
            zzadl.zzc(zzaef.zzaq());
            return zzaef.equals(zzadl.zza);
        }
        zzc(zzaef2.zzaq());
        return this.zza.equals(zzaef2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzabv) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzat();
        }
        return 0;
    }

    public final zzaby zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzaby zzaby = this.zzc;
                return zzaby;
            }
            if (this.zza == null) {
                this.zzc = zzaby.zzb;
            } else {
                this.zzc = this.zza.zzaI();
            }
            zzaby zzaby2 = this.zzc;
            return zzaby2;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.internal.pal.zzaef r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.pal.zzaef r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.pal.zzaef r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0019
            r1.zza = r2     // Catch:{ zzadi -> 0x0011 }
            com.google.android.gms.internal.pal.zzaby r0 = com.google.android.gms.internal.pal.zzaby.zzb     // Catch:{ zzadi -> 0x0011 }
            r1.zzc = r0     // Catch:{ zzadi -> 0x0011 }
            goto L_0x0017
        L_0x0011:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.pal.zzaby r2 = com.google.android.gms.internal.pal.zzaby.zzb     // Catch:{ all -> 0x001b }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzadl.zzc(com.google.android.gms.internal.pal.zzaef):void");
    }
}
