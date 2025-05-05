package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzpi implements Supplier<zzpl> {
    private static zzpi zza = new zzpi();
    private final Supplier<zzpl> zzb = Suppliers.ofInstance(new zzpk());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzpl) zza.get()).zza();
    }
}
