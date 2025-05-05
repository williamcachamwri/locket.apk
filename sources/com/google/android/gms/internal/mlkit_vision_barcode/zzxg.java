package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzxg extends zzxn {
    private final float zza;
    private final float zzb;
    private final float zzc;
    private final float zzd;

    zzxg(float f, float f2, float f3, float f4, float f5) {
        this.zza = f;
        this.zzb = f2;
        this.zzc = f3;
        this.zzd = f4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzxn) {
            zzxn zzxn = (zzxn) obj;
            if (Float.floatToIntBits(this.zza) == Float.floatToIntBits(zzxn.zzc()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(zzxn.zze()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(zzxn.zzb()) && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzxn.zzd())) {
                int floatToIntBits = Float.floatToIntBits(0.0f);
                zzxn.zza();
                if (floatToIntBits == Float.floatToIntBits(0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003) ^ Float.floatToIntBits(this.zzc)) * 1000003) ^ Float.floatToIntBits(this.zzd)) * 1000003) ^ Float.floatToIntBits(0.0f);
    }

    public final String toString() {
        return "PredictedArea{xMin=" + this.zza + ", yMin=" + this.zzb + ", xMax=" + this.zzc + ", yMax=" + this.zzd + ", confidenceScore=0.0}";
    }

    /* access modifiers changed from: package-private */
    public final float zza() {
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public final float zzb() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final float zzc() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final float zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final float zze() {
        return this.zzb;
    }
}
