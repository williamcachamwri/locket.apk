package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzadk {
    static final zzadk zza = new zzadk(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private static volatile zzadk zzd;
    private final Map zze;

    zzadk() {
        this.zze = new HashMap();
    }

    public static zzadk zza() {
        int i = zzafi.zza;
        return zza;
    }

    public static zzadk zzb() {
        zzadk zzadk = zzd;
        if (zzadk != null) {
            return zzadk;
        }
        synchronized (zzadk.class) {
            zzadk zzadk2 = zzd;
            if (zzadk2 != null) {
                return zzadk2;
            }
            int i = zzafi.zza;
            zzadk zzb2 = zzads.zzb(zzadk.class);
            zzd = zzb2;
            return zzb2;
        }
    }

    public final zzadx zzc(zzafb zzafb, int i) {
        return (zzadx) this.zze.get(new zzadj(zzafb, i));
    }

    zzadk(boolean z) {
        this.zze = Collections.emptyMap();
    }
}
