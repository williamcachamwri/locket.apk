package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakk  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzakk {
    private volatile zzalc zza;
    private volatile zzaip zzb;

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return this.zzb.zzb();
        }
        if (this.zza != null) {
            return this.zza.zzl();
        }
        return 0;
    }

    public final zzaip zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                zzaip zzaip = this.zzb;
                return zzaip;
            }
            if (this.zza == null) {
                this.zzb = zzaip.zza;
            } else {
                this.zzb = this.zza.a_();
            }
            zzaip zzaip2 = this.zzb;
            return zzaip2;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.p002firebaseauthapi.zzalc zzb(com.google.android.gms.internal.p002firebaseauthapi.zzalc r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.firebase-auth-api.zzalc r0 = r1.zza
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.firebase-auth-api.zzalc r0 = r1.zza     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zza = r2     // Catch:{ zzakf -> 0x0012 }
            com.google.android.gms.internal.firebase-auth-api.zzaip r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaip.zza     // Catch:{ zzakf -> 0x0012 }
            r1.zzb = r0     // Catch:{ zzakf -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zza = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.firebase-auth-api.zzaip r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaip.zza     // Catch:{ all -> 0x001a }
            r1.zzb = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.firebase-auth-api.zzalc r2 = r1.zza
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakk.zzb(com.google.android.gms.internal.firebase-auth-api.zzalc):com.google.android.gms.internal.firebase-auth-api.zzalc");
    }

    public final zzalc zza(zzalc zzalc) {
        zzalc zzalc2 = this.zza;
        this.zzb = null;
        this.zza = zzalc;
        return zzalc2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzakk)) {
            return false;
        }
        zzakk zzakk = (zzakk) obj;
        zzalc zzalc = this.zza;
        zzalc zzalc2 = zzakk.zza;
        if (zzalc == null && zzalc2 == null) {
            return zzb().equals(zzakk.zzb());
        }
        if (zzalc != null && zzalc2 != null) {
            return zzalc.equals(zzalc2);
        }
        if (zzalc != null) {
            return zzalc.equals(zzakk.zzb(zzalc.zzg()));
        }
        return zzb(zzalc2.zzg()).equals(zzalc2);
    }
}
