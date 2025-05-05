package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzer;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzes  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzes extends zzcu {
    private final zzer zza;
    private final zzzc zzb;
    @Nullable
    private final Integer zzc;

    public static zzes zza(zzer zzer, @Nullable Integer num) throws GeneralSecurityException {
        zzzc zzzc;
        if (zzer.zzb() == zzer.zza.zza) {
            if (num != null) {
                zzzc = zzzc.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
            } else {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
        } else if (zzer.zzb() != zzer.zza.zzb) {
            throw new GeneralSecurityException("Unknown Variant: " + String.valueOf(zzer.zzb()));
        } else if (num == null) {
            zzzc = zzzc.zza(new byte[0]);
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        return new zzes(zzer, zzzc, num);
    }

    public final zzer zzb() {
        return this.zza;
    }

    public final zzzc zzc() {
        return this.zzb;
    }

    public final Integer zza() {
        return this.zzc;
    }

    private zzes(zzer zzer, zzzc zzzc, @Nullable Integer num) {
        this.zza = zzer;
        this.zzb = zzzc;
        this.zzc = num;
    }
}
