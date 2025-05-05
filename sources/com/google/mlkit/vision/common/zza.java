package com.google.mlkit.vision.common;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zza extends PointF3D {
    private final float zza;
    private final float zzb;
    private final float zzc;

    zza(float f, float f2, float f3) {
        this.zza = f;
        this.zzb = f2;
        this.zzc = f3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PointF3D) {
            PointF3D pointF3D = (PointF3D) obj;
            return Float.floatToIntBits(this.zza) == Float.floatToIntBits(pointF3D.getX()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(pointF3D.getY()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(pointF3D.getZ());
        }
    }

    public final float getX() {
        return this.zza;
    }

    public final float getY() {
        return this.zzb;
    }

    public final float getZ() {
        return this.zzc;
    }

    public final int hashCode() {
        return ((((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003) ^ Float.floatToIntBits(this.zzc);
    }

    public final String toString() {
        float f = this.zza;
        float f2 = this.zzb;
        float f3 = this.zzc;
        return "PointF3D{x=" + f + ", y=" + f2 + ", z=" + f3 + "}";
    }
}
