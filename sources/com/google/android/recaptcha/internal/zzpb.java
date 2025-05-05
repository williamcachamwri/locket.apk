package com.google.android.recaptcha.internal;

import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzpb extends zzpg {
    zzpb() {
        super((zzpf) null);
    }

    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzc(); i++) {
                ((zzmu) ((zzpc) zzg(i)).zza()).zzg();
            }
            for (Map.Entry key : zzd()) {
                ((zzmu) key.getKey()).zzg();
            }
        }
        super.zza();
    }
}
