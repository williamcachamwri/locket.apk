package com.google.android.gms.internal.pal;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzri {
    private final zzrb zza;
    private final List zzb;
    @Nullable
    private final Integer zzc;

    /* synthetic */ zzri(zzrb zzrb, List list, Integer num, zzrh zzrh) {
        this.zza = zzrb;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzri)) {
            return false;
        }
        zzri zzri = (zzri) obj;
        if (this.zza.equals(zzri.zza) && this.zzb.equals(zzri.zzb)) {
            Integer num = this.zzc;
            Integer num2 = zzri.zzc;
            if (num == num2) {
                return true;
            }
            if (num == null || !num.equals(num2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", new Object[]{this.zza, this.zzb, this.zzc});
    }
}
