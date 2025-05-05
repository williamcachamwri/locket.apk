package com.google.android.gms.internal.pal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zziy extends zziz {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zziz zzc;

    zziy(zziz zziz, int i, int i2) {
        this.zzc = zziz;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzip.zza(i, this.zzb, FirebaseAnalytics.Param.INDEX);
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
    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    public final zziz zzf(int i, int i2) {
        zzip.zzc(i, i2, this.zzb);
        zziz zziz = this.zzc;
        int i3 = this.zza;
        return zziz.subList(i + i3, i2 + i3);
    }
}
