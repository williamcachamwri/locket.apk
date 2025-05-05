package com.google.android.gms.internal.fido;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzax extends zzaz {
    private final transient zzaz zza;

    zzax(zzaz zzaz) {
        this.zza = zzaz;
    }

    private final int zzl(int i) {
        return (this.zza.size() - 1) - i;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.contains(obj);
    }

    public final Object get(int i) {
        zzap.zza(i, this.zza.size(), FirebaseAnalytics.Param.INDEX);
        return this.zza.get(zzl(i));
    }

    public final int indexOf(@CheckForNull Object obj) {
        int lastIndexOf = this.zza.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return zzl(lastIndexOf);
        }
        return -1;
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        int indexOf = this.zza.indexOf(obj);
        if (indexOf >= 0) {
            return zzl(indexOf);
        }
        return -1;
    }

    public final int size() {
        return this.zza.size();
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    public final zzaz zzf() {
        return this.zza;
    }

    public final zzaz zzg(int i, int i2) {
        zzap.zze(i, i2, this.zza.size());
        zzaz zzaz = this.zza;
        return zzaz.subList(zzaz.size() - i2, this.zza.size() - i).zzf();
    }
}
