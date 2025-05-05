package com.google.android.gms.internal.p002firebaseauthapi;

import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.ProviderException;
import javax.crypto.BadPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzmg implements zzbg {
    private static final String zza = "zzmg";
    private final zzbg zzb;

    public zzmg(String str) throws GeneralSecurityException, IOException {
        this.zzb = zzme.zza(str);
    }

    private static void zza() {
        try {
            Thread.sleep((long) ((int) (Math.random() * 100.0d)));
        } catch (InterruptedException unused) {
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            return this.zzb.zza(bArr, bArr2);
        } catch (BadPaddingException e) {
            throw e;
        } catch (GeneralSecurityException | ProviderException e2) {
            SentryLogcatAdapter.w(zza, "encountered a potentially transient KeyStore error, will wait and retry", e2);
            zza();
            return this.zzb.zza(bArr, bArr2);
        }
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            return this.zzb.zzb(bArr, bArr2);
        } catch (GeneralSecurityException | ProviderException e) {
            SentryLogcatAdapter.w(zza, "encountered a potentially transient KeyStore error, will wait and retry", e);
            zza();
            return this.zzb.zzb(bArr, bArr2);
        }
    }
}
