package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzke extends zzju implements Serializable {
    private final MessageDigest zza;
    private final int zzb;
    private final boolean zzc;
    private final String zzd = "Hashing.sha256()";

    zzke(String str, String str2) {
        MessageDigest zzc2 = zzc(MessageDigestAlgorithms.SHA_256);
        this.zza = zzc2;
        this.zzb = zzc2.getDigestLength();
        String str3 = "Hashing.sha256()";
        this.zzc = zzd(zzc2);
    }

    private static MessageDigest zzc(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static boolean zzd(MessageDigest messageDigest) {
        try {
            messageDigest.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    public final String toString() {
        return this.zzd;
    }

    public final zzjz zzb() {
        if (this.zzc) {
            try {
                return new zzkc((MessageDigest) this.zza.clone(), this.zzb, (zzkd) null);
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new zzkc(zzc(this.zza.getAlgorithm()), this.zzb, (zzkd) null);
    }
}
