package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzoe implements Supplier<zzoh> {
    private static zzoe zza = new zzoe();
    private final Supplier<zzoh> zzb = Suppliers.ofInstance(new zzog());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoh) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzoh) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zzoh) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zzoh) zza.get()).zzd();
    }
}
