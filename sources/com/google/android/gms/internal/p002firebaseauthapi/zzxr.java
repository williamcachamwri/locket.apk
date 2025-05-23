package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzxr extends ThreadLocal<Cipher> {
    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        return zza();
    }

    private static Cipher zza() {
        try {
            return zzyf.zza.zza("AES/CTR/NOPADDING");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    zzxr() {
    }
}
