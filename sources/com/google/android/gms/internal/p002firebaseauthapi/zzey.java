package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzfa;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzey  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzey extends zzcu {
    private final zzfa zza;
    private final zzzc zzb;
    @Nullable
    private final Integer zzc;

    public static zzey zza(zzfa zzfa, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc;
        if (zzfa.zzc() == zzfa.zzb.zzb) {
            if (num == null) {
                zzzc = zzog.zza;
            } else {
                throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
            }
        } else if (zzfa.zzc() != zzfa.zzb.zza) {
            throw new GeneralSecurityException("Unknown Variant: " + String.valueOf(zzfa.zzc()));
        } else if (num != null) {
            zzzc = zzog.zzb(num.intValue());
        } else {
            throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
        }
        return new zzey(zzfa, zzzc, num);
    }

    public final zzfa zzb() {
        return this.zza;
    }

    public final zzzc zzc() {
        return this.zzb;
    }

    public final Integer zza() {
        return this.zzc;
    }

    private zzey(zzfa zzfa, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzfa;
        this.zzb = zzzc;
        this.zzc = num;
    }
}
