package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzov  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzov {
    /* access modifiers changed from: private */
    public final Map<zzox, zzor<?, ?>> zza;
    /* access modifiers changed from: private */
    public final Map<Class<?>, zzpd<?, ?>> zzb;

    public static zzou zza(zzov zzov) {
        return new zzou(zzov);
    }

    public final Class<?> zza(Class<?> cls) throws GeneralSecurityException {
        if (this.zzb.containsKey(cls)) {
            return this.zzb.get(cls).zza();
        }
        throw new GeneralSecurityException("No input primitive class for " + String.valueOf(cls) + " available");
    }

    public final <KeyT extends zzbp, PrimitiveT> PrimitiveT zza(KeyT keyt, Class<PrimitiveT> cls) throws GeneralSecurityException {
        zzox zzox = new zzox(keyt.getClass(), cls);
        if (this.zza.containsKey(zzox)) {
            return this.zza.get(zzox).zza(keyt);
        }
        throw new GeneralSecurityException("No PrimitiveConstructor for " + String.valueOf(zzox) + " available");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.firebase-auth-api.zzoz, com.google.android.gms.internal.firebase-auth-api.zzoz<InputPrimitiveT>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <InputPrimitiveT, WrapperPrimitiveT> WrapperPrimitiveT zza(com.google.android.gms.internal.p002firebaseauthapi.zzoz<InputPrimitiveT> r3, java.lang.Class<WrapperPrimitiveT> r4) throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            java.util.Map<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzpd<?, ?>> r0 = r2.zzb
            boolean r0 = r0.containsKey(r4)
            if (r0 == 0) goto L_0x0039
            java.util.Map<java.lang.Class<?>, com.google.android.gms.internal.firebase-auth-api.zzpd<?, ?>> r0 = r2.zzb
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.firebase-auth-api.zzpd r4 = (com.google.android.gms.internal.p002firebaseauthapi.zzpd) r4
            java.lang.Class r0 = r3.zzc()
            java.lang.Class r1 = r4.zza()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0031
            java.lang.Class r0 = r4.zza()
            java.lang.Class r1 = r3.zzc()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0031
            java.lang.Object r3 = r4.zza(r3)
            return r3
        L_0x0031:
            java.security.GeneralSecurityException r3 = new java.security.GeneralSecurityException
            java.lang.String r4 = "Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet"
            r3.<init>(r4)
            throw r3
        L_0x0039:
            java.security.GeneralSecurityException r3 = new java.security.GeneralSecurityException
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No wrapper found for "
            r0.<init>(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzov.zza(com.google.android.gms.internal.firebase-auth-api.zzoz, java.lang.Class):java.lang.Object");
    }

    private zzov(zzou zzou) {
        this.zza = new HashMap(zzou.zza);
        this.zzb = new HashMap(zzou.zzb);
    }
}
