package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zznl implements Supplier<zznk> {
    private static zznl zza = new zznl();
    private final Supplier<zznk> zzb = Suppliers.ofInstance(new zznn());

    @SideEffectFree
    public static long zza() {
        return ((zznk) zza.get()).zza();
    }

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }
}
