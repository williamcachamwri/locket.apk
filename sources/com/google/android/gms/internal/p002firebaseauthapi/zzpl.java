package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpl {
    /* access modifiers changed from: private */
    public final Map<zzpn, zzmw<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<zzpk, zzms<?>> zzb;
    /* access modifiers changed from: private */
    public final Map<zzpn, zzon<?, ?>> zzc;
    /* access modifiers changed from: private */
    public final Map<zzpk, zzoj<?>> zzd;

    public final <SerializationT extends zzpj> zzpl zza(zzms<SerializationT> zzms) throws GeneralSecurityException {
        zzpk zzpk = new zzpk(zzms.zzb(), zzms.zza());
        if (this.zzb.containsKey(zzpk)) {
            zzms zzms2 = this.zzb.get(zzpk);
            if (!zzms2.equals(zzms) || !zzms.equals(zzms2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzpk));
            }
        } else {
            this.zzb.put(zzpk, zzms);
        }
        return this;
    }

    public final <KeyT extends zzbp, SerializationT extends zzpj> zzpl zza(zzmw<KeyT, SerializationT> zzmw) throws GeneralSecurityException {
        zzpn zzpn = new zzpn(zzmw.zza(), zzmw.zzb());
        if (this.zza.containsKey(zzpn)) {
            zzmw zzmw2 = this.zza.get(zzpn);
            if (!zzmw2.equals(zzmw) || !zzmw.equals(zzmw2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzpn));
            }
        } else {
            this.zza.put(zzpn, zzmw);
        }
        return this;
    }

    public final <SerializationT extends zzpj> zzpl zza(zzoj<SerializationT> zzoj) throws GeneralSecurityException {
        zzpk zzpk = new zzpk(zzoj.zzb(), zzoj.zza());
        if (this.zzd.containsKey(zzpk)) {
            zzoj zzoj2 = this.zzd.get(zzpk);
            if (!zzoj2.equals(zzoj) || !zzoj.equals(zzoj2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + String.valueOf(zzpk));
            }
        } else {
            this.zzd.put(zzpk, zzoj);
        }
        return this;
    }

    public final <ParametersT extends zzch, SerializationT extends zzpj> zzpl zza(zzon<ParametersT, SerializationT> zzon) throws GeneralSecurityException {
        zzpn zzpn = new zzpn(zzon.zza(), zzon.zzb());
        if (this.zzc.containsKey(zzpn)) {
            zzon zzon2 = this.zzc.get(zzpn);
            if (!zzon2.equals(zzon) || !zzon.equals(zzon2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + String.valueOf(zzpn));
            }
        } else {
            this.zzc.put(zzpn, zzon);
        }
        return this;
    }

    public final zzpi zza() {
        return new zzpi(this);
    }

    public zzpl() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public zzpl(zzpi zzpi) {
        this.zza = new HashMap(zzpi.zza);
        this.zzb = new HashMap(zzpi.zzb);
        this.zzc = new HashMap(zzpi.zzc);
        this.zzd = new HashMap(zzpi.zzd);
    }
}
