package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzxf extends zzxm {
    private final int zzb;
    private final int zzc;
    private final float zzd;
    private final float zze;
    private final boolean zzf;
    private final float zzg;
    private final float zzh;
    private final long zzi;
    private final long zzj;
    private final boolean zzk;
    private final float zzl;
    private final float zzm;

    /* synthetic */ zzxf(int i, int i2, float f, float f2, boolean z, float f3, float f4, long j, long j2, boolean z2, float f5, float f6, zzxe zzxe) {
        this.zzb = i;
        this.zzc = i2;
        this.zzd = f;
        this.zze = f2;
        this.zzf = z;
        this.zzg = f3;
        this.zzh = f4;
        this.zzi = j;
        this.zzj = j2;
        this.zzk = z2;
        this.zzl = f5;
        this.zzm = f6;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzxm) {
            zzxm zzxm = (zzxm) obj;
            return this.zzb == zzxm.zzh() && this.zzc == zzxm.zzg() && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzxm.zzd()) && Float.floatToIntBits(this.zze) == Float.floatToIntBits(zzxm.zzc()) && this.zzf == zzxm.zzl() && Float.floatToIntBits(this.zzg) == Float.floatToIntBits(zzxm.zzb()) && Float.floatToIntBits(this.zzh) == Float.floatToIntBits(zzxm.zza()) && this.zzi == zzxm.zzj() && this.zzj == zzxm.zzi() && this.zzk == zzxm.zzk() && Float.floatToIntBits(this.zzl) == Float.floatToIntBits(zzxm.zze()) && Float.floatToIntBits(this.zzm) == Float.floatToIntBits(zzxm.zzf());
        }
    }

    public final int hashCode() {
        int i = 1237;
        int floatToIntBits = (((((((((((((((((this.zzb ^ 1000003) * 1000003) ^ this.zzc) * 1000003) ^ Float.floatToIntBits(this.zzd)) * 1000003) ^ Float.floatToIntBits(this.zze)) * 1000003) ^ (true != this.zzf ? 1237 : 1231)) * 1000003) ^ Float.floatToIntBits(this.zzg)) * 1000003) ^ Float.floatToIntBits(this.zzh)) * 1000003) ^ ((int) this.zzi)) * 1000003) ^ ((int) this.zzj)) * 1000003;
        if (true == this.zzk) {
            i = 1231;
        }
        return ((((floatToIntBits ^ i) * 1000003) ^ Float.floatToIntBits(this.zzl)) * 1000003) ^ Float.floatToIntBits(this.zzm);
    }

    public final String toString() {
        return "AutoZoomOptions{recentFramesToCheck=" + this.zzb + ", recentFramesContainingPredictedArea=" + this.zzc + ", recentFramesIou=" + this.zzd + ", maxCoverage=" + this.zze + ", useConfidenceScore=" + this.zzf + ", lowerConfidenceScore=" + this.zzg + ", higherConfidenceScore=" + this.zzh + ", zoomIntervalInMillis=" + this.zzi + ", resetIntervalInMillis=" + this.zzj + ", enableZoomThreshold=" + this.zzk + ", zoomInThreshold=" + this.zzl + ", zoomOutThreshold=" + this.zzm + "}";
    }

    /* access modifiers changed from: package-private */
    public final float zza() {
        return this.zzh;
    }

    /* access modifiers changed from: package-private */
    public final float zzb() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final float zzc() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final float zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final float zze() {
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    public final float zzf() {
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final int zzg() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final int zzh() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final long zzi() {
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    public final long zzj() {
        return this.zzi;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk() {
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        return this.zzf;
    }
}
