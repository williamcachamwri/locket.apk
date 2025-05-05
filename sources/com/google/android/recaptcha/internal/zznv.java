package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public class zznv {
    protected volatile zzok zza;
    private volatile zzlg zzb;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zznv)) {
            return false;
        }
        zznv zznv = (zznv) obj;
        zzok zzok = this.zza;
        zzok zzok2 = zznv.zza;
        if (zzok == null && zzok2 == null) {
            return zzb().equals(zznv.zzb());
        }
        if (zzok != null && zzok2 != null) {
            return zzok.equals(zzok2);
        }
        if (zzok != null) {
            zznv.zzd(zzok.zzm());
            return zzok.equals(zznv.zza);
        }
        zzd(zzok2.zzm());
        return this.zza.equals(zzok2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zzle) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzo();
        }
        return 0;
    }

    public final zzlg zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                zzlg zzlg = this.zzb;
                return zzlg;
            }
            if (this.zza == null) {
                this.zzb = zzlg.zzb;
            } else {
                this.zzb = this.zza.zzb();
            }
            zzlg zzlg2 = this.zzb;
            return zzlg2;
        }
    }

    public final zzok zzc(zzok zzok) {
        zzok zzok2 = this.zza;
        this.zzb = null;
        this.zza = zzok;
        return zzok2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.recaptcha.internal.zzok r2) {
        /*
            r1 = this;
            com.google.android.recaptcha.internal.zzok r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.recaptcha.internal.zzok r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x000c:
            r1.zza = r2     // Catch:{ zznp -> 0x0013 }
            com.google.android.recaptcha.internal.zzlg r0 = com.google.android.recaptcha.internal.zzlg.zzb     // Catch:{ zznp -> 0x0013 }
            r1.zzb = r0     // Catch:{ zznp -> 0x0013 }
            goto L_0x0019
        L_0x0013:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.recaptcha.internal.zzlg r2 = com.google.android.recaptcha.internal.zzlg.zzb     // Catch:{ all -> 0x001b }
            r1.zzb = r2     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zznv.zzd(com.google.android.recaptcha.internal.zzok):void");
    }
}
