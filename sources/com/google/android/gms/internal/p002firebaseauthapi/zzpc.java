package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpc implements zzpj {
    private final String zza;
    private final zzzc zzb;
    private final zzaip zzc;
    private final zzvq.zzb zzd;
    private final zzws zze;
    @Nullable
    private final Integer zzf;

    public static zzpc zza(String str, zzaip zzaip, zzvq.zzb zzb2, zzws zzws, @Nullable Integer num) throws GeneralSecurityException {
        if (zzws == zzws.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzpc(str, zzpr.zza(str), zzaip, zzb2, zzws, num);
    }

    public final zzvq.zzb zza() {
        return this.zzd;
    }

    public final zzws zzb() {
        return this.zze;
    }

    public final zzzc zzc() {
        return this.zzb;
    }

    public final zzaip zzd() {
        return this.zzc;
    }

    @Nullable
    public final Integer zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zza;
    }

    private zzpc(String str, zzzc zzzc, zzaip zzaip, zzvq.zzb zzb2, zzws zzws, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzzc;
        this.zzc = zzaip;
        this.zzd = zzb2;
        this.zze = zzws;
        this.zzf = num;
    }
}
