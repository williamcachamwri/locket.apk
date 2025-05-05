package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznv {
    private static final zznx<zzne> zza = new zznu();
    private static final zznv zzb = zzb();
    private final Map<Class<? extends zzch>, zznx<? extends zzch>> zzc = new HashMap();

    public final zzbp zza(zzch zzch, @Nullable Integer num) throws GeneralSecurityException {
        return zzb(zzch, num);
    }

    private final synchronized <ParametersT extends zzch> zzbp zzb(ParametersT parameterst, @Nullable Integer num) throws GeneralSecurityException {
        zznx zznx;
        zznx = this.zzc.get(parameterst.getClass());
        if (zznx != null) {
        } else {
            throw new GeneralSecurityException("Cannot create a new key for parameters " + String.valueOf(parameterst) + ": no key creator for this class was registered.");
        }
        return zznx.zza(parameterst, num);
    }

    public static /* synthetic */ zznc zza(zzne zzne, Integer num) {
        zzvu zza2 = zzne.zzb().zza();
        zzbs<?> zza3 = zzmt.zza().zza(zza2.zzf());
        if (zzmt.zza().zzb(zza2.zzf())) {
            zzvq zza4 = zza3.zza(zza2.zze());
            return new zznc(zzpc.zza(zza4.zzf(), zza4.zze(), zza4.zzb(), zza2.zzd(), num), zzbq.zza());
        }
        throw new GeneralSecurityException("Creating new keys is not allowed.");
    }

    public static zznv zza() {
        return zzb;
    }

    private static zznv zzb() {
        zznv zznv = new zznv();
        try {
            zznv.zza(zza, zzne.class);
            return zznv;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("unexpected error.", e);
        }
    }

    public final synchronized <ParametersT extends zzch> void zza(zznx<ParametersT> zznx, Class<ParametersT> cls) throws GeneralSecurityException {
        zznx zznx2 = this.zzc.get(cls);
        if (zznx2 != null) {
            if (!zznx2.equals(zznx)) {
                throw new GeneralSecurityException("Different key creator for parameters class " + String.valueOf(cls) + " already inserted");
            }
        }
        this.zzc.put(cls, zznx);
    }
}
