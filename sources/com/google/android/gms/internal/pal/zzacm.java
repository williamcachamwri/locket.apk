package com.google.android.gms.internal.pal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzacm {
    static final zzacm zza = new zzacm(true);
    private static volatile boolean zzb = false;
    private static volatile zzacm zzc;
    private final Map zzd;

    zzacm() {
        this.zzd = new HashMap();
    }

    public static zzacm zza() {
        zzacm zzacm = zzc;
        if (zzacm == null) {
            synchronized (zzacm.class) {
                zzacm = zzc;
                if (zzacm == null) {
                    zzacm = zza;
                    zzc = zzacm;
                }
            }
        }
        return zzacm;
    }

    public final zzacx zzb(zzaef zzaef, int i) {
        return (zzacx) this.zzd.get(new zzacl(zzaef, i));
    }

    zzacm(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
