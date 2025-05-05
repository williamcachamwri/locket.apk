package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzft {
    private final zzrb zza;
    private final Boolean zzb = null;
    private final Boolean zzc;
    private final zzqk zzd;
    private final zzvz zze;
    private final zzcs zzf;
    private final zzcs zzg;

    /* synthetic */ zzft(zzfr zzfr, zzfs zzfs) {
        this.zza = zzfr.zza;
        this.zzc = zzfr.zzb;
        this.zzd = null;
        this.zze = zzfr.zzc;
        this.zzf = zzfr.zzd;
        this.zzg = zzfr.zze;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzft)) {
            return false;
        }
        zzft zzft = (zzft) obj;
        if (Objects.equal(this.zza, zzft.zza)) {
            Boolean bool = zzft.zzb;
            if (Objects.equal((Object) null, (Object) null) && Objects.equal(this.zzc, zzft.zzc)) {
                zzqk zzqk = zzft.zzd;
                return Objects.equal((Object) null, (Object) null) && Objects.equal(this.zze, zzft.zze) && Objects.equal(this.zzf, zzft.zzf) && Objects.equal(this.zzg, zzft.zzg);
            }
        }
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, this.zzg);
    }

    public final zzcs zza() {
        return this.zzf;
    }

    public final zzcs zzb() {
        return this.zzg;
    }

    public final zzrb zzc() {
        return this.zza;
    }

    public final zzvz zzd() {
        return this.zze;
    }

    public final Boolean zze() {
        return this.zzc;
    }
}
