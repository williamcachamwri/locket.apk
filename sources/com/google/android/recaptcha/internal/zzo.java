package com.google.android.recaptcha.internal;

import android.content.ContentResolver;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzo implements zzaa {
    private final zzem zza;
    private final ContentResolver zzb;

    public zzo(zzem zzem, ContentResolver contentResolver) {
        this.zza = zzem;
        this.zzb = contentResolver;
    }

    public final int zza() {
        return 17;
    }

    public final zzem zzb() {
        return this.zza;
    }

    public final Object zzc(String str, Continuation continuation) {
        zzaa zzaa = this;
        zzep zzb2 = zzab.zzb(zzaa, str);
        String zza2 = zzar.zza(this.zzb);
        zzb2.zza();
        zzth zzf = zztk.zzf();
        zzti zzf2 = zztj.zzf();
        zzf2.zzw(zza2);
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
