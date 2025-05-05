package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
abstract class zznr extends zzns {
    private boolean zza;

    zznr(zznv zznv) {
        super(zznv);
        this.zzg.zzu();
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzc();

    /* access modifiers changed from: protected */
    public final void zzal() {
        if (!zzan()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzam() {
        if (!this.zza) {
            zzc();
            this.zzg.zzt();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzan() {
        return this.zza;
    }
}
