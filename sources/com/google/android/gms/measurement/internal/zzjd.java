package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
abstract class zzjd extends zzja {
    private boolean zza;

    zzjd(zzhy zzhy) {
        super(zzhy);
        this.zzu.zzaa();
    }

    /* access modifiers changed from: protected */
    public void zzaa() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzh();

    /* access modifiers changed from: protected */
    public final void zzac() {
        if (!zzaf()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzad() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzh()) {
            this.zzu.zzz();
            this.zza = true;
        }
    }

    public final void zzae() {
        if (!this.zza) {
            zzaa();
            this.zzu.zzz();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaf() {
        return this.zza;
    }
}
