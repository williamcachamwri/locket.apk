package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzoq implements Supplier<zzot> {
    private static zzoq zza = new zzoq();
    private final Supplier<zzot> zzb = Suppliers.ofInstance(new zzos());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzot) zza.get()).zza();
    }
}
