package com.google.android.gms.internal.fido;

import java.io.IOException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzce extends zzcg {
    final char[] zza;

    private zzce(zzcd zzcd) {
        super(zzcd, (Character) null);
        this.zza = new char[512];
        zzap.zzc(zzcd.zzf.length == 16);
        for (int i = 0; i < 256; i++) {
            this.zza[i] = zzcd.zza(i >>> 4);
            this.zza[i | 256] = zzcd.zza(i & 15);
        }
    }

    /* access modifiers changed from: package-private */
    public final zzch zza(zzcd zzcd, @CheckForNull Character ch) {
        return new zzce(zzcd);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzap.zze(0, i2, bArr.length);
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i3] & 255;
            appendable.append(this.zza[b]);
            appendable.append(this.zza[b | 256]);
        }
    }

    zzce(String str, String str2) {
        this(new zzcd("base16()", "0123456789ABCDEF".toCharArray()));
    }
}
