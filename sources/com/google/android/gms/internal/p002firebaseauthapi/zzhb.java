package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzhb extends ThreadLocal<Cipher> {
    /* access modifiers changed from: protected */
    @Nullable
    public final /* synthetic */ Object initialValue() {
        return zza();
    }

    @Nullable
    private static Cipher zza() {
        try {
            Cipher zza = zzyf.zza.zza("ChaCha20-Poly1305");
            if (!zzhc.zzb(zza)) {
                return null;
            }
            return zza;
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    zzhb() {
    }
}
