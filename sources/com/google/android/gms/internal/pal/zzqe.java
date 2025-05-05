package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqe extends zzqt {
    private final zzqj zza;
    private final zzyw zzb;
    @Nullable
    private final Integer zzc;

    private zzqe(zzqj zzqj, zzyw zzyw, @Nullable Integer num) {
        this.zza = zzqj;
        this.zzb = zzyw;
        this.zzc = num;
    }

    public static zzqe zzb(zzqj zzqj, zzyw zzyw, @Nullable Integer num) throws GeneralSecurityException {
        if (zzyw.zza() != 32) {
            throw new GeneralSecurityException("Invalid key size");
        } else if (zzqj.zzc() && num == null) {
            throw new GeneralSecurityException("Cannot create key without ID requirement with format with ID requirement");
        } else if (zzqj.zzc() || num == null) {
            return new zzqe(zzqj, zzyw, num);
        } else {
            throw new GeneralSecurityException("Cannot create key with ID requirement with format without ID requirement");
        }
    }

    public final /* synthetic */ zzks zza() {
        return this.zza;
    }
}
