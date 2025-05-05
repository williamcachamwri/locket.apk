package com.google.android.gms.internal.atv_ads_framework;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzfb extends zzfl {
    zzfb(int i) {
        super(i, (zzfk) null);
    }

    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                Map.Entry zzg = zzg(i);
                if (((zzdb) zzg.getKey()).zzc()) {
                    zzg.setValue(Collections.unmodifiableList((List) zzg.getValue()));
                }
            }
            for (Map.Entry entry : zzc()) {
                if (((zzdb) entry.getKey()).zzc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
