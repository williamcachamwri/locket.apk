package com.google.android.recaptcha.internal;

import java.io.IOException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzkj {
    private static final zzkj zza = new zzkg("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzkj zzb = new zzkg("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    static {
        new zzki("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new zzki("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        zzkf zzkf = new zzkf("base16()", "0123456789ABCDEF".toCharArray());
        new zzki(zzkf, (Character) null);
        char[] cArr = new char[512];
        zzjh.zza(zzkf.zzf.length == 16);
        for (int i = 0; i < 256; i++) {
            cArr[i] = zzkf.zza(i >>> 4);
            cArr[i | 256] = zzkf.zza(i & 15);
        }
    }

    zzkj() {
    }

    public static zzkj zzg() {
        return zza;
    }

    public static zzkj zzh() {
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public abstract int zza(byte[] bArr, CharSequence charSequence) throws zzkh;

    /* access modifiers changed from: package-private */
    public abstract void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract int zzc(int i);

    /* access modifiers changed from: package-private */
    public abstract int zzd(int i);

    /* access modifiers changed from: package-private */
    public CharSequence zze(CharSequence charSequence) {
        throw null;
    }

    public final String zzi(byte[] bArr, int i, int i2) {
        zzjh.zzd(0, i2, bArr.length);
        StringBuilder sb = new StringBuilder(zzd(i2));
        try {
            zzb(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final byte[] zzj(CharSequence charSequence) {
        try {
            CharSequence zze = zze(charSequence);
            int zzc = zzc(zze.length());
            byte[] bArr = new byte[zzc];
            int zza2 = zza(bArr, zze);
            if (zza2 == zzc) {
                return bArr;
            }
            byte[] bArr2 = new byte[zza2];
            System.arraycopy(bArr, 0, bArr2, 0, zza2);
            return bArr2;
        } catch (zzkh e) {
            throw new IllegalArgumentException(e);
        }
    }
}
