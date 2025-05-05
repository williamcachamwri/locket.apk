package com.google.android.gms.measurement;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.measurement.internal.zzhy;
import com.google.android.gms.measurement.internal.zzjl;
import com.google.android.gms.measurement.internal.zzjm;
import com.google.android.gms.measurement.internal.zzjq;
import com.google.android.gms.measurement.internal.zzon;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzb extends AppMeasurement.zza {
    private final zzhy zza;
    private final zzjq zzb;

    public final int zza(String str) {
        return zzjq.zza(str);
    }

    public final long zzf() {
        return this.zza.zzt().zzn();
    }

    public final Boolean zza() {
        return this.zzb.zzac();
    }

    public final Double zzb() {
        return this.zzb.zzad();
    }

    public final Integer zzc() {
        return this.zzb.zzae();
    }

    public final Long zzd() {
        return this.zzb.zzaf();
    }

    public final Object zza(int i) {
        if (i == 0) {
            return zze();
        }
        if (i == 1) {
            return zzd();
        }
        if (i == 2) {
            return zzb();
        }
        if (i == 3) {
            return zzc();
        }
        if (i != 4) {
            return null;
        }
        return zza();
    }

    public final String zzg() {
        return this.zzb.zzag();
    }

    public final String zzh() {
        return this.zzb.zzah();
    }

    public final String zzi() {
        return this.zzb.zzai();
    }

    public final String zzj() {
        return this.zzb.zzag();
    }

    public final String zze() {
        return this.zzb.zzak();
    }

    public final List<Bundle> zza(String str, String str2) {
        return this.zzb.zza(str, str2);
    }

    public final Map<String, Object> zza(boolean z) {
        List<zzon> zza2 = this.zzb.zza(z);
        ArrayMap arrayMap = new ArrayMap(zza2.size());
        for (zzon next : zza2) {
            Object zza3 = next.zza();
            if (zza3 != null) {
                arrayMap.put(next.zza, zza3);
            }
        }
        return arrayMap;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        return this.zzb.zza(str, str2, z);
    }

    public zzb(zzhy zzhy) {
        super();
        Preconditions.checkNotNull(zzhy);
        this.zza = zzhy;
        this.zzb = zzhy.zzp();
    }

    public final void zzb(String str) {
        this.zza.zze().zza(str, this.zza.zzb().elapsedRealtime());
    }

    public final void zza(String str, String str2, Bundle bundle) {
        this.zza.zzp().zza(str, str2, bundle);
    }

    public final void zzc(String str) {
        this.zza.zze().zzb(str, this.zza.zzb().elapsedRealtime());
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        this.zzb.zzb(str, str2, bundle);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        this.zzb.zza(str, str2, bundle, j);
    }

    public final void zza(zzjl zzjl) {
        this.zzb.zza(zzjl);
    }

    public final void zza(Bundle bundle) {
        this.zzb.zzb(bundle);
    }

    public final void zza(zzjm zzjm) {
        this.zzb.zza(zzjm);
    }

    public final void zzb(zzjl zzjl) {
        this.zzb.zzb(zzjl);
    }
}
