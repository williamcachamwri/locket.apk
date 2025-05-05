package com.google.android.gms.internal.p002firebaseauthapi;

import android.security.keystore.KeyGenParameterSpec;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzmf implements zzcd {
    private static final Object zza = new Object();

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmf$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        public zza() {
            zzmf.zza();
        }
    }

    static /* synthetic */ boolean zza() {
        return true;
    }

    public final zzbg zza(String str) throws GeneralSecurityException {
        zzmg zzmg;
        try {
            synchronized (zza) {
                zzmg = new zzmg(zzzb.zza("android-keystore://", str));
                byte[] zza2 = zzpe.zza(10);
                byte[] bArr = new byte[0];
                if (!Arrays.equals(zza2, zzmg.zza(zzmg.zzb(zza2, bArr), bArr))) {
                    throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
                }
            }
            return zzmg;
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public zzmf() throws GeneralSecurityException {
        this(new zza());
    }

    private zzmf(zza zza2) {
    }

    public final boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    static boolean zzc(String str) throws GeneralSecurityException {
        synchronized (zza) {
            String zza2 = zzzb.zza("android-keystore://", str);
            if (zzme.zzb(zza2)) {
                return false;
            }
            KeyGenParameterSpec build = new KeyGenParameterSpec.Builder(zza2, 3).setKeySize(256).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).build();
            KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            instance.init(build);
            instance.generateKey();
            return true;
        }
    }
}
