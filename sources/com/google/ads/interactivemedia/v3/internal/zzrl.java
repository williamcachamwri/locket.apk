package com.google.ads.interactivemedia.v3.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrl extends zzrm {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzrm zzc;

    zzrl(zzrm zzrm, int i, int i2) {
        this.zzc = zzrm;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzqh.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
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

    public final zzrm zzi(int i, int i2) {
        zzqh.zzh(i, i2, this.zzb);
        int i3 = this.zza;
        return this.zzc.subList(i + i3, i2 + i3);
    }
}
