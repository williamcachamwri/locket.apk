package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzyl;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzyf<T_WRAPPER extends zzyl<JcePrimitiveT>, JcePrimitiveT> {
    public static final zzyf<zzyk, Cipher> zza = new zzyf<>(new zzyk());
    public static final zzyf<zzyo, Mac> zzb = new zzyf<>(new zzyo());
    public static final zzyf<zzyn, KeyAgreement> zzc = new zzyf<>(new zzyn());
    public static final zzyf<zzyp, KeyPairGenerator> zzd = new zzyf<>(new zzyp());
    public static final zzyf<zzym, KeyFactory> zze = new zzyf<>(new zzym());
    private final zzyj<JcePrimitiveT> zzf;

    public final JcePrimitiveT zza(String str) throws GeneralSecurityException {
        return this.zzf.zza(str);
    }

    public static List<Provider> zza(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String provider : strArr) {
            Provider provider2 = Security.getProvider(provider);
            if (provider2 != null) {
                arrayList.add(provider2);
            }
        }
        return arrayList;
    }

    static {
        new zzyf(new zzyq());
        new zzyf(new zzyr());
    }

    private zzyf(T_WRAPPER t_wrapper) {
        if (zzil.zzb()) {
            this.zzf = new zzyg(t_wrapper);
        } else if (zzyy.zza()) {
            this.zzf = new zzye(t_wrapper);
        } else {
            this.zzf = new zzyh(t_wrapper);
        }
    }
}
