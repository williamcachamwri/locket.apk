package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzs;
import com.google.android.gms.internal.measurement.zzv;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzhr implements zzv {
    private final /* synthetic */ zzhl zza;

    zzhr(zzhl zzhl) {
        this.zza = zzhl;
    }

    public final void zza(zzs zzs, String str, List<String> list, boolean z, boolean z2) {
        zzgq zzgq;
        int i = zzht.zza[zzs.ordinal()];
        if (i == 1) {
            zzgq = this.zza.zzj().zzc();
        } else if (i != 2) {
            if (i != 3) {
                zzgq = i != 4 ? this.zza.zzj().zzo() : this.zza.zzj().zzp();
            } else if (z) {
                zzgq = this.zza.zzj().zzw();
            } else {
                zzgq = !z2 ? this.zza.zzj().zzv() : this.zza.zzj().zzu();
            }
        } else if (z) {
            zzgq = this.zza.zzj().zzn();
        } else if (!z2) {
            zzgq = this.zza.zzj().zzm();
        } else {
            zzgq = this.zza.zzj().zzg();
        }
        int size = list.size();
        if (size == 1) {
            zzgq.zza(str, list.get(0));
        } else if (size == 2) {
            zzgq.zza(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzgq.zza(str);
        } else {
            zzgq.zza(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
