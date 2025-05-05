package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzqk {
    private final zzqi zza;
    private final Integer zzb;
    private final Integer zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzqk(zzqh zzqh, zzqj zzqj) {
        this.zza = zzqh.zza;
        this.zzb = zzqh.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzqk)) {
            return false;
        }
        zzqk zzqk = (zzqk) obj;
        if (Objects.equal(this.zza, zzqk.zza) && Objects.equal(this.zzb, zzqk.zzb)) {
            Integer num = zzqk.zzc;
            if (Objects.equal((Object) null, (Object) null)) {
                Boolean bool = zzqk.zzd;
                if (Objects.equal((Object) null, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, null);
    }

    public final zzqi zza() {
        return this.zza;
    }

    public final Integer zzb() {
        return this.zzb;
    }
}
