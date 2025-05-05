package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class zzeu {
    private static final zzdo zzb = zzdo.zza;
    protected volatile zzfo zza;
    private volatile zzdb zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeu)) {
            return false;
        }
        zzeu zzeu = (zzeu) obj;
        zzfo zzfo = this.zza;
        zzfo zzfo2 = zzeu.zza;
        if (zzfo == null && zzfo2 == null) {
            return zzb().equals(zzeu.zzb());
        }
        if (zzfo != null && zzfo2 != null) {
            return zzfo.equals(zzfo2);
        }
        if (zzfo != null) {
            zzeu.zzd(zzfo.zzab());
            return zzfo.equals(zzeu.zza);
        }
        zzd(zzfo2.zzab());
        return this.zza.equals(zzfo2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzcy) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzE();
        }
        return 0;
    }

    public final zzdb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzdb zzdb = this.zzc;
                return zzdb;
            }
            if (this.zza == null) {
                this.zzc = zzdb.zzb;
            } else {
                this.zzc = this.zza.zzC();
            }
            zzdb zzdb2 = this.zzc;
            return zzdb2;
        }
    }

    public final zzfo zzc(zzfo zzfo) {
        zzfo zzfo2 = this.zza;
        this.zzc = null;
        this.zza = zzfo;
        return zzfo2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 != 0) goto L_0x0019
            r1.zza = r2     // Catch:{ zzeo -> 0x0011 }
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzb     // Catch:{ zzeo -> 0x0011 }
            r1.zzc = r0     // Catch:{ zzeo -> 0x0011 }
            goto L_0x0017
        L_0x0011:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzb     // Catch:{ all -> 0x001b }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeu.zzd(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo):void");
    }
}
