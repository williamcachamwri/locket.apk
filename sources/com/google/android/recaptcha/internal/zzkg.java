package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;
import okio.Utf8;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzkg extends zzki {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzkg(java.lang.String r2, java.lang.String r3, @javax.annotation.CheckForNull java.lang.Character r4) {
        /*
            r1 = this;
            com.google.android.recaptcha.internal.zzkf r0 = new com.google.android.recaptcha.internal.zzkf
            char[] r3 = r3.toCharArray()
            r0.<init>(r2, r3)
            r1.<init>(r0, r4)
            char[] r2 = r0.zzf
            int r2 = r2.length
            r3 = 64
            if (r2 != r3) goto L_0x0017
            r2 = 1
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            com.google.android.recaptcha.internal.zzjh.zza(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzkg.<init>(java.lang.String, java.lang.String, java.lang.Character):void");
    }

    /* access modifiers changed from: package-private */
    public final int zza(byte[] bArr, CharSequence charSequence) throws zzkh {
        CharSequence zze = zze(charSequence);
        if (this.zza.zzc(zze.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zze.length()) {
                int i3 = i + 1;
                int i4 = i2 + 1;
                int zzb = (this.zza.zzb(zze.charAt(i)) << 18) | (this.zza.zzb(zze.charAt(i3)) << 12);
                bArr[i2] = (byte) (zzb >>> 16);
                int i5 = i3 + 1;
                if (i5 < zze.length()) {
                    int i6 = i5 + 1;
                    int zzb2 = zzb | (this.zza.zzb(zze.charAt(i5)) << 6);
                    i2 = i4 + 1;
                    bArr[i4] = (byte) ((zzb2 >>> 8) & 255);
                    if (i6 < zze.length()) {
                        bArr[i2] = (byte) ((zzb2 | this.zza.zzb(zze.charAt(i6))) & 255);
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
        throw new zzkh("Invalid input length " + zze.length());
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzjh.zzd(0, i2, bArr.length);
        for (int i4 = i2; i4 >= 3; i4 -= 3) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            byte b = ((bArr[i3] & 255) << 16) | ((bArr[i5] & 255) << 8) | (bArr[i6] & 255);
            appendable.append(this.zza.zza(b >>> Ascii.DC2));
            appendable.append(this.zza.zza((b >>> 12) & 63));
            appendable.append(this.zza.zza((b >>> 6) & 63));
            appendable.append(this.zza.zza(b & Utf8.REPLACEMENT_BYTE));
            i3 = i6 + 1;
        }
        if (i3 < i2) {
            zzf(appendable, bArr, i3, i2 - i3);
        }
    }
}
