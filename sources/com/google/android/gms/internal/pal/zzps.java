package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzps implements zzpu {
    private final String zza;
    private final zzyv zzb;
    private final zzaby zzc;
    private final zzvn zzd;
    @Nullable
    private final Integer zze;
    private final int zzf;

    private zzps(String str, zzaby zzaby, zzvn zzvn, int i, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzqd.zza(str);
        this.zzc = zzaby;
        this.zzd = zzvn;
        this.zzf = i;
        this.zze = num;
    }

    public static zzps zzf(String str, zzaby zzaby, zzvn zzvn, int i, @Nullable Integer num) throws GeneralSecurityException {
        if (i == 5) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzps(str, zzaby, zzvn, i, num);
    }

    public final zzvn zza() {
        return this.zzd;
    }

    public final zzyv zzb() {
        return this.zzb;
    }

    public final zzaby zzc() {
        return this.zzc;
    }

    @Nullable
    public final Integer zzd() {
        return this.zze;
    }

    public final String zze() {
        return this.zza;
    }

    public final int zzg() {
        return this.zzf;
    }
}
