package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbt extends zzbi {
    final transient Object zza;

    zzbt(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.equals(obj);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final /* synthetic */ Iterator iterator() {
        return new zzbj(this.zza);
    }

    public final int size() {
        return 1;
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "[" + obj + "]";
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        objArr[0] = this.zza;
        return 1;
    }

    public final zzbe zzd() {
        Object obj = this.zza;
        int i = zzbe.zzd;
        Object[] objArr = {obj};
        zzbk.zzb(objArr, 1);
        return zzbe.zzi(objArr, 1);
    }

    public final zzbu zze() {
        return new zzbj(this.zza);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        throw null;
    }
}
