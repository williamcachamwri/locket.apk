package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zznr implements Supplier<zznq> {
    private static zznr zza = new zznr();
    private final Supplier<zznq> zzb = Suppliers.ofInstance(new zznt());

    public final /* synthetic */ Object get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zznq) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zznq) zza.get()).zzb();
    }

    @SideEffectFree
    public static boolean zzc() {
        return ((zznq) zza.get()).zzc();
    }

    @SideEffectFree
    public static boolean zzd() {
        return ((zznq) zza.get()).zzd();
    }
}
