package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public class zzkk {
    private volatile zzlc zza;
    private volatile zzik zzb;

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return this.zzb.zzb();
        }
        if (this.zza != null) {
            return this.zza.zzcb();
        }
        return 0;
    }

    public final zzik zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                zzik zzik = this.zzb;
                return zzik;
            }
            if (this.zza == null) {
                this.zzb = zzik.zza;
            } else {
                this.zzb = this.zza.zzbz();
            }
            zzik zzik2 = this.zzb;
            return zzik2;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzlc zzb(com.google.android.gms.internal.measurement.zzlc r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzlc r0 = r1.zza
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzlc r0 = r1.zza     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zza = r2     // Catch:{ zzkb -> 0x0012 }
            com.google.android.gms.internal.measurement.zzik r0 = com.google.android.gms.internal.measurement.zzik.zza     // Catch:{ zzkb -> 0x0012 }
            r1.zzb = r0     // Catch:{ zzkb -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zza = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzik r2 = com.google.android.gms.internal.measurement.zzik.zza     // Catch:{ all -> 0x001a }
            r1.zzb = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.measurement.zzlc r2 = r1.zza
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkk.zzb(com.google.android.gms.internal.measurement.zzlc):com.google.android.gms.internal.measurement.zzlc");
    }

    public final zzlc zza(zzlc zzlc) {
        zzlc zzlc2 = this.zza;
        this.zzb = null;
        this.zza = zzlc;
        return zzlc2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkk)) {
            return false;
        }
        zzkk zzkk = (zzkk) obj;
        zzlc zzlc = this.zza;
        zzlc zzlc2 = zzkk.zza;
        if (zzlc == null && zzlc2 == null) {
            return zzb().equals(zzkk.zzb());
        }
        if (zzlc != null && zzlc2 != null) {
            return zzlc.equals(zzlc2);
        }
        if (zzlc != null) {
            return zzlc.equals(zzkk.zzb(zzlc.zzck()));
        }
        return zzb(zzlc2.zzck()).equals(zzlc2);
    }
}
