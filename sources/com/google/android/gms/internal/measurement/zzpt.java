package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzpt implements Supplier<zzps> {
    private static zzpt zza = new zzpt();
    private final Supplier<zzps> zzb = Suppliers.ofInstance(new zzpv());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzps) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzps) zza.get()).zzb();
    }
}
