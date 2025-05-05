package com.google.android.recaptcha.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzjx {
    private static final char[] zza = "0123456789abcdef".toCharArray();
    public static final /* synthetic */ int zzb = 0;

    zzjx() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzjx) {
            zzjx zzjx = (zzjx) obj;
            if (zzb() != zzjx.zzb() || !zzc(zzjx)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (zzb() >= 32) {
            return zza();
        }
        byte[] zze = zze();
        byte b = zze[0] & 255;
        for (int i = 1; i < zze.length; i++) {
            b |= (zze[i] & 255) << (i * 8);
        }
        return b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(r1 + r1);
        for (byte b : zze()) {
            char[] cArr = zza;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public abstract int zza();

    public abstract int zzb();

    /* access modifiers changed from: package-private */
    public abstract boolean zzc(zzjx zzjx);

    public abstract byte[] zzd();

    /* access modifiers changed from: package-private */
    public byte[] zze() {
        throw null;
    }
}
