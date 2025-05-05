package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbu {
    @Nullable
    private final zzch zza;

    public static zzbu zza(zzch zzch) throws GeneralSecurityException {
        return new zzbu(zzch);
    }

    public final zzch zza() throws GeneralSecurityException {
        zzch zzch = this.zza;
        if (zzch != null) {
            return zzch;
        }
        return zzcp.zza(zzb().zzk());
    }

    private final zzvu zzb() {
        try {
            zzch zzch = this.zza;
            if (zzch instanceof zzne) {
                return ((zzne) zzch).zzb().zza();
            }
            return ((zzpf) zzof.zza().zza(this.zza, zzpf.class)).zza();
        } catch (GeneralSecurityException e) {
            throw new zzpp("Parsing parameters failed in getProto(). You probably want to call some Tink register function for " + String.valueOf(this.zza), e);
        }
    }

    private zzbu(zzch zzch) {
        this.zza = zzch;
    }
}
