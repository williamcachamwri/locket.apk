package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsv extends zzsy {
    final char[] zza;

    private zzsv(zzsu zzsu) {
        super(zzsu, (Character) null);
        this.zza = new char[512];
        zzqh.zzd(zzsu.zzf.length == 16);
        for (int i = 0; i < 256; i++) {
            this.zza[i] = zzsu.zza(i >>> 4);
            this.zza[i | 256] = zzsu.zza(i & 15);
        }
    }

    /* access modifiers changed from: package-private */
    public final int zza(byte[] bArr, CharSequence charSequence) throws zzsx {
        if (charSequence.length() % 2 != 1) {
            int i = 0;
            int i2 = 0;
            while (i < charSequence.length()) {
                bArr[i2] = (byte) ((this.zzb.zzb(charSequence.charAt(i)) << 4) | this.zzb.zzb(charSequence.charAt(i + 1)));
                i += 2;
                i2++;
            }
            return i2;
        }
        throw new zzsx("Invalid input length " + charSequence.length());
    }

    /* access modifiers changed from: package-private */
    public final zzsz zzb(zzsu zzsu, @CheckForNull Character ch) {
        return new zzsv(zzsu);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzqh.zzh(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i3] & 255;
            appendable.append(this.zza[b]);
            appendable.append(this.zza[b | 256]);
        }
    }

    zzsv(String str, String str2) {
        this(new zzsu("base16()", "0123456789ABCDEF".toCharArray()));
    }
}
