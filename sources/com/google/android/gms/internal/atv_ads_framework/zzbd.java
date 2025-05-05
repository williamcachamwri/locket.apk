package com.google.android.gms.internal.atv_ads_framework;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbd extends zzbe {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzbe zzc;

    zzbd(zzbe zzbe, int i, int i2) {
        this.zzc = zzbe;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzaq.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    public final zzbe zzh(int i, int i2) {
        zzaq.zzc(i, i2, this.zzb);
        zzbe zzbe = this.zzc;
        int i3 = this.zza;
        return zzbe.subList(i + i3, i2 + i3);
    }
}
