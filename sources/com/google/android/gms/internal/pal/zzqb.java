package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqb {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    /* synthetic */ zzqb(zzpv zzpv, zzqa zzqa) {
        this.zza = new HashMap(zzpv.zza);
        this.zzb = new HashMap(zzpv.zzb);
        this.zzc = new HashMap(zzpv.zzc);
        this.zzd = new HashMap(zzpv.zzd);
    }

    public final zzka zza(zzpu zzpu, @Nullable zzlg zzlg) throws GeneralSecurityException {
        zzpx zzpx = new zzpx(zzpu.getClass(), zzpu.zzb(), (zzpw) null);
        if (this.zzb.containsKey(zzpx)) {
            return ((zzou) this.zzb.get(zzpx)).zza(zzpu, zzlg);
        }
        String obj = zzpx.toString();
        throw new GeneralSecurityException("No Key Parser for requested key type " + obj + " available");
    }
}
