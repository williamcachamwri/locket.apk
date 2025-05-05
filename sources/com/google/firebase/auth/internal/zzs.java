package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import com.google.android.gms.internal.p002firebaseauthapi.zzbi;
import com.google.android.gms.internal.p002firebaseauthapi.zzbo;
import com.google.android.gms.internal.p002firebaseauthapi.zzce;
import com.google.android.gms.internal.p002firebaseauthapi.zzkh;
import com.google.android.gms.internal.p002firebaseauthapi.zzkm;
import com.google.android.gms.internal.p002firebaseauthapi.zzma;
import com.google.android.gms.internal.p002firebaseauthapi.zzpg;
import com.google.android.gms.internal.p002firebaseauthapi.zzr;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzs {
    private static zzs zza;
    private final String zzb;
    private final zzma zzc;

    public static zzs zza(Context context, String str) {
        zzs zzs = zza;
        if (zzs == null || !zzr.zza(zzs.zzb, str)) {
            zza = new zzs(context, str, true);
        }
        return zza;
    }

    public final String zza(String str) {
        String str2;
        zzma zzma = this.zzc;
        if (zzma == null) {
            SentryLogcatAdapter.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
        try {
            synchronized (zzma) {
                str2 = new String(((zzbo) this.zzc.zza().zza(zzpg.zza(), zzbo.class)).zza(Base64.decode(str, 8), (byte[]) null), "UTF-8");
            }
            return str2;
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            SentryLogcatAdapter.e("FirebearCryptoHelper", "Exception encountered while decrypting bytes:\n" + e.getMessage());
            return null;
        }
    }

    public final String zza() {
        if (this.zzc == null) {
            SentryLogcatAdapter.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzce zza2 = zzbi.zza((OutputStream) byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zza().zza(zza2);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            SentryLogcatAdapter.e("FirebearCryptoHelper", "Exception encountered when attempting to get Public Key:\n" + e.getMessage());
            return null;
        }
    }

    private zzs(Context context, String str, boolean z) {
        zzma zzma;
        this.zzb = str;
        try {
            zzkh.zza();
            zzma.zza zza2 = new zzma.zza().zza(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", new Object[]{str})).zza(zzkm.zza);
            zza2.zza(String.format("android-keystore://firebear_master_key_id.%s", new Object[]{str}));
            zzma = zza2.zza();
        } catch (IOException | GeneralSecurityException e) {
            SentryLogcatAdapter.e("FirebearCryptoHelper", "Exception encountered during crypto setup:\n" + e.getMessage());
            zzma = null;
        }
        this.zzc = zzma;
    }
}
