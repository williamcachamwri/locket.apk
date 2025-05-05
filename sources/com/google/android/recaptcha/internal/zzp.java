package com.google.android.recaptcha.internal;

import android.os.Build;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzp implements zzaa {
    private final zzem zza;

    public zzp(zzem zzem) {
        this.zza = zzem;
    }

    private static final zztj zzg(String str) {
        zzti zzf = zztj.zzf();
        zzf.zzw(str);
        return (zztj) zzf.zzk();
    }

    public final int zza() {
        return 11;
    }

    public final zzem zzb() {
        return this.zza;
    }

    public final Object zzc(String str, Continuation continuation) {
        zzaa zzaa = this;
        zzep zzb = zzab.zzb(zzaa, str);
        zzth zzf = zztk.zzf();
        long j = Build.TIME;
        zzti zzf2 = zztj.zzf();
        zzf2.zzv(j);
        zzf.zze(CollectionsKt.listOf(zzg(Build.MANUFACTURER), zzg(Build.MODEL), zzg(Build.DEVICE), zzg(Build.HARDWARE), zzg(Build.FINGERPRINT), zzg(Build.PRODUCT), zzg(Build.BOARD), zzg(Build.BRAND), zzg(ArraysKt.joinToString$default((Object[]) Build.SUPPORTED_ABIS, (CharSequence) ",", (CharSequence) "[", (CharSequence) "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null)), (zztj) zzf2.zzk(), zzg(Build.ID), zzg(Build.BOOTLOADER), zzg(Build.DISPLAY), zzg(Build.TYPE), zzg(Build.TAGS)));
        zzb.zza();
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
