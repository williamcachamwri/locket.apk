package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zznl {
    private final zznh zza;
    private final zznj zzb = null;
    private final zznj zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zznl(zzni zzni, zznk zznk) {
        this.zza = zzni.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zznl)) {
            return false;
        }
        zznl zznl = (zznl) obj;
        if (Objects.equal(this.zza, zznl.zza)) {
            zznj zznj = zznl.zzb;
            if (Objects.equal((Object) null, (Object) null)) {
                zznj zznj2 = zznl.zzc;
                if (Objects.equal((Object) null, (Object) null)) {
                    Boolean bool = zznl.zzd;
                    if (Objects.equal((Object) null, (Object) null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zznh zza() {
        return this.zza;
    }
}
