package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzoj implements Supplier<zzoi> {
    private static zzoj zza = new zzoj();
    private final Supplier<zzoi> zzb = Suppliers.ofInstance(new zzol());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoi) zza.get()).zza();
    }
}
