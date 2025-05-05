package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzpv {
    /* access modifiers changed from: private */
    public final Map zza;
    /* access modifiers changed from: private */
    public final Map zzb;
    /* access modifiers changed from: private */
    public final Map zzc;
    /* access modifiers changed from: private */
    public final Map zzd;

    public zzpv() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public final zzpv zza(zzou zzou) throws GeneralSecurityException {
        zzpx zzpx = new zzpx(zzou.zzd(), zzou.zzc(), (zzpw) null);
        if (this.zzb.containsKey(zzpx)) {
            zzou zzou2 = (zzou) this.zzb.get(zzpx);
            if (!zzou2.equals(zzou) || !zzou.equals(zzou2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzpx.toString()));
            }
        } else {
            this.zzb.put(zzpx, zzou);
        }
        return this;
    }

    public final zzpv zzb(zzox zzox) throws GeneralSecurityException {
        zzpz zzpz = new zzpz(zzox.zza(), zzox.zzb(), (zzpy) null);
        if (this.zza.containsKey(zzpz)) {
            zzox zzox2 = (zzox) this.zza.get(zzpz);
            if (!zzox2.equals(zzox) || !zzox.equals(zzox2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzpz.toString()));
            }
        } else {
            this.zza.put(zzpz, zzox);
        }
        return this;
    }

    public final zzpv zzc(zzpm zzpm) throws GeneralSecurityException {
        zzpx zzpx = new zzpx(zzpm.zzb(), zzpm.zza(), (zzpw) null);
        if (this.zzd.containsKey(zzpx)) {
            zzpm zzpm2 = (zzpm) this.zzd.get(zzpx);
            if (!zzpm2.equals(zzpm) || !zzpm.equals(zzpm2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzpx.toString()));
            }
        } else {
            this.zzd.put(zzpx, zzpm);
        }
        return this;
    }

    public final zzpv zzd(zzpp zzpp) throws GeneralSecurityException {
        zzpz zzpz = new zzpz(zzpp.zza(), zzpp.zzb(), (zzpy) null);
        if (this.zzc.containsKey(zzpz)) {
            zzpp zzpp2 = (zzpp) this.zzc.get(zzpz);
            if (!zzpp2.equals(zzpp) || !zzpp.equals(zzpp2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzpz.toString()));
            }
        } else {
            this.zzc.put(zzpz, zzpp);
        }
        return this;
    }

    public zzpv(zzqb zzqb) {
        this.zza = new HashMap(zzqb.zza);
        this.zzb = new HashMap(zzqb.zzb);
        this.zzc = new HashMap(zzqb.zzc);
        this.zzd = new HashMap(zzqb.zzd);
    }
}
