package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzns implements Supplier<zznv> {
    private static zzns zza = new zzns();
    private final Supplier<zznv> zzb = Suppliers.ofInstance(new zznu());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznv) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznv) zza.get()).zzb();
    }
}
