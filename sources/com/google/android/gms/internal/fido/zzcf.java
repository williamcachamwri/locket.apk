package com.google.android.gms.internal.fido;

import com.google.common.base.Ascii;
import java.io.IOException;
import javax.annotation.CheckForNull;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzcf extends zzcg {
    private zzcf(zzcd zzcd, @CheckForNull Character ch) {
        super(zzcd, ch);
        zzap.zzc(zzcd.zzf.length == 64);
    }

    /* access modifiers changed from: package-private */
    public final zzch zza(zzcd zzcd, @CheckForNull Character ch) {
        return new zzcf(zzcd, ch);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzap.zze(0, i2, bArr.length);
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
            zze(appendable, bArr, i3, i2 - i3);
        }
    }

    zzcf(String str, String str2, @CheckForNull Character ch) {
        this(new zzcd(str, str2.toCharArray()), ch);
    }
}
