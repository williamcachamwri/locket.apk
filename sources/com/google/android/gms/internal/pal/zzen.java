package com.google.android.gms.internal.pal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzen implements Callable {
    private final zzdu zza;
    private final zzr zzb;

    public zzen(zzdu zzdu, zzr zzr) {
        this.zza = zzdu;
        this.zzb = zzr;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        if (this.zza.zzl() != null) {
            this.zza.zzl().get();
        }
        zzaf zzc = this.zza.zzc();
        if (zzc == null) {
            return null;
        }
        try {
            synchronized (this.zzb) {
                zzr zzr = this.zzb;
                byte[] zzas = zzc.zzas();
                zzr.zzam(zzas, 0, zzas.length, zzacm.zza());
            }
            return null;
        } catch (zzadi | NullPointerException unused) {
            return null;
        }
    }
}
