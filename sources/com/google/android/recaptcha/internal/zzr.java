package com.google.android.recaptcha.internal;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzr implements zzaa {
    private final zzem zza;
    private final Lazy zzb = LazyKt.lazy(zzq.zza);
    private boolean zzc = true;

    public zzr(zzem zzem) {
        this.zza = zzem;
        int i = zzax.zza;
    }

    private final zzbh zzg() {
        return (zzbh) this.zzb.getValue();
    }

    public final int zza() {
        return 25;
    }

    public final zzem zzb() {
        return this.zza;
    }

    public final Object zzc(String str, Continuation continuation) {
        zzaa zzaa = this;
        zzep zzb2 = zzab.zzb(zzaa, str);
        String zza2 = zzg().zza();
        zzb2.zza();
        zzth zzf = zztk.zzf();
        zzti zzf2 = zztj.zzf();
        zzf2.zzw(zza2);
        zzf.zze(CollectionsKt.listOf(zzf2.zzk()));
        return zzab.zza(zzaa, (zztk) zzf.zzk());
    }

    public final Object zzd(zzsg zzsg, Continuation continuation) {
        zzep zzc2 = zzab.zzc(this);
        if (zzsg.zzl().length() == 0) {
            this.zzc = false;
            zzc2.zzb(new zzbf(zzbd.zzb, zzbc.zzab, (String) null));
            return Unit.INSTANCE;
        }
        zzg().zzb(MapsKt.mapOf(TuplesKt.to("_GRECAPTCHA_KC", zzsg.zzl())));
        zzc2.zza();
        return Unit.INSTANCE;
    }

    public final void zze(zzst zzst) {
    }

    public final boolean zzf() {
        return this.zzc;
    }
}
