package com.google.android.play.core.appupdate;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public final class zzab {
    private zzi zza;

    private zzab() {
    }

    /* synthetic */ zzab(zzaa zzaa) {
    }

    public final zzab zzb(zzi zzi) {
        this.zza = zzi;
        return this;
    }

    public final zza zza() {
        zzi zzi = this.zza;
        if (zzi != null) {
            return new zzz(zzi, (zzy) null);
        }
        throw new IllegalStateException(String.valueOf(zzi.class.getCanonicalName()).concat(" must be set"));
    }
}
