package com.google.ads.interactivemedia.v3.internal;

import com.google.common.base.Ascii;
import java.io.IOException;
import javax.annotation.CheckForNull;
import okio.Utf8;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsw extends zzsy {
    private zzsw(zzsu zzsu, @CheckForNull Character ch) {
        super(zzsu, ch);
        zzqh.zzd(zzsu.zzf.length == 64);
    }

    /* access modifiers changed from: package-private */
    public final int zza(byte[] bArr, CharSequence charSequence) throws zzsx {
        CharSequence zzg = zzg(charSequence);
        if (this.zzb.zzd(zzg.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zzg.length()) {
                int i3 = i + 1;
                int i4 = i2 + 1;
                int zzb = (this.zzb.zzb(zzg.charAt(i)) << 18) | (this.zzb.zzb(zzg.charAt(i3)) << 12);
                bArr[i2] = (byte) (zzb >>> 16);
                int i5 = i3 + 1;
                if (i5 < zzg.length()) {
                    int i6 = i5 + 1;
                    int zzb2 = zzb | (this.zzb.zzb(zzg.charAt(i5)) << 6);
                    i2 = i4 + 1;
                    bArr[i4] = (byte) ((zzb2 >>> 8) & 255);
                    if (i6 < zzg.length()) {
                        bArr[i2] = (byte) ((zzb2 | this.zzb.zzb(zzg.charAt(i6))) & 255);
                        i2++;
                        i = i6 + 1;
                    } else {
                        i = i6;
                    }
                } else {
                    i = i5;
                    i2 = i4;
                }
            }
            return i2;
        }
        throw new zzsx("Invalid input length " + zzg.length());
    }

    /* access modifiers changed from: package-private */
    public final zzsz zzb(zzsu zzsu, @CheckForNull Character ch) {
        return new zzsw(zzsu, ch);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzqh.zzh(0, i2, bArr.length);
        for (int i4 = i2; i4 >= 3; i4 -= 3) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            byte b = ((bArr[i3] & 255) << 16) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
            appendable.append(this.zzb.zza(b >>> Ascii.DC2));
            appendable.append(this.zzb.zza((b >>> 12) & 63));
            appendable.append(this.zzb.zza((b >>> 6) & 63));
            appendable.append(this.zzb.zza(b & Utf8.REPLACEMENT_BYTE));
            i3 = i6 + 1;
        }
        if (i3 < i2) {
            zzh(appendable, bArr, i3, i2 - i3);
        }
    }

    zzsw(String str, String str2, @CheckForNull Character ch) {
        this(new zzsu(str, str2.toCharArray()), ch);
    }
}
