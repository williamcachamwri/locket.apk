package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzad implements zzaa {
    private final zzem zza;

    public zzad(zzem zzem) {
        this.zza = zzem;
    }

    public final int zza() {
        return 10;
    }

    public final zzem zzb() {
        return this.zza;
    }

    public final Object zzc(String str, Continuation continuation) {
        zzaa zzaa = this;
        zzep zzb = zzab.zzb(zzaa, str);
        int i = Build.VERSION.SDK_INT;
        zzb.zza();
        zzth zzf = zztk.zzf();
        zzti zzf2 = zztj.zzf();
        zzf2.zzw(String.valueOf(i));
        zzf.zze(CollectionsKt.listOf(zzf2.zzk()));
        return zzab.zza(zzaa, (zztk) zzf.zzk());
    }

    public final Object zzd(zzsg zzsg, Continuation continuation) {
        zzab.zzc(this).zza();
        return Unit.INSTANCE;
    }

    public final void zze(zzst zzst) {
    }

    public final boolean zzf() {
        return true;
    }
}
