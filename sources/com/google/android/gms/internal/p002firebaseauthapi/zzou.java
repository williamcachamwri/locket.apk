package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzou  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzou {
    /* access modifiers changed from: private */
    public final Map<zzox, zzor<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<Class<?>, zzpd<?, ?>> zzb;

    public final <KeyT extends zzbp, PrimitiveT> zzou zza(zzor<KeyT, PrimitiveT> zzor) throws GeneralSecurityException {
        if (zzor != null) {
            zzox zzox = new zzox(zzor.zza(), zzor.zzb());
            if (this.zza.containsKey(zzox)) {
                zzor zzor2 = this.zza.get(zzox);
                if (!zzor2.equals(zzor) || !zzor.equals(zzor2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + String.valueOf(zzox));
                }
            } else {
                this.zza.put(zzox, zzor);
            }
            return this;
        }
        throw new NullPointerException("primitive constructor must be non-null");
    }

    public final <InputPrimitiveT, WrapperPrimitiveT> zzou zza(zzpd<InputPrimitiveT, WrapperPrimitiveT> zzpd) throws GeneralSecurityException {
        if (zzpd != null) {
            Class<WrapperPrimitiveT> zzb2 = zzpd.zzb();
            if (this.zzb.containsKey(zzb2)) {
                zzpd zzpd2 = this.zzb.get(zzb2);
                if (!zzpd2.equals(zzpd) || !zzpd.equals(zzpd2)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + String.valueOf(zzb2));
                }
            } else {
                this.zzb.put(zzb2, zzpd);
            }
            return this;
        }
        throw new NullPointerException("wrapper must be non-null");
    }

    public final zzov zza() {
        return new zzov(this);
    }

    private zzou() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    private zzou(zzov zzov) {
        this.zza = new HashMap(zzov.zza);
        this.zzb = new HashMap(zzov.zzb);
    }
}
