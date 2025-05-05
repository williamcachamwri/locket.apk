package com.google.ads.interactivemedia.v3.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrk extends zzrm {
    private final transient zzrm zza;

    zzrk(zzrm zzrm) {
        this.zza = zzrm;
    }

    private final int zzo(int i) {
        return (this.zza.size() - 1) - i;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.contains(obj);
    }

    public final Object get(int i) {
        zzqh.zza(i, this.zza.size(), FirebaseAnalytics.Param.INDEX);
        return this.zza.get(zzo(i));
    }

    public final int indexOf(@CheckForNull Object obj) {
        int lastIndexOf = this.zza.lastIndexOf(obj);
        if (lastIndexOf >= 0) {
            return zzo(lastIndexOf);
        }
        return -1;
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        int indexOf = this.zza.indexOf(obj);
        if (indexOf >= 0) {
            return zzo(indexOf);
        }
        return -1;
    }

    public final int size() {
        return this.zza.size();
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return this.zza.zzf();
    }

    public final zzrm zzh() {
        return this.zza;
    }

    public final zzrm zzi(int i, int i2) {
        zzqh.zzh(i, i2, this.zza.size());
        zzrm zzrm = this.zza;
        return zzrm.subList(zzrm.size() - i2, this.zza.size() - i).zzh();
    }
}
