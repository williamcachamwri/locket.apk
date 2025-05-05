package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
class zzcg extends zzch {
    @CheckForNull
    private volatile zzch zza;
    final zzcd zzb;
    @CheckForNull
    final Character zzc;

    zzcg(zzcd zzcd, @CheckForNull Character ch) {
        this.zzb = zzcd;
        boolean z = true;
        if (ch != null) {
            ch.charValue();
            if (zzcd.zzc('=')) {
                z = false;
            }
        }
        if (z) {
            this.zzc = ch;
            return;
        }
        throw new IllegalArgumentException(zzaq.zza("Padding character %s was already in alphabet", ch));
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzcg) {
            zzcg zzcg = (zzcg) obj;
            if (this.zzb.equals(zzcg.zzb)) {
                Character ch = this.zzc;
                Character ch2 = zzcg.zzc;
                if (ch == ch2) {
                    return true;
                }
                if (ch == null || !ch.equals(ch2)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int hashCode = this.zzb.hashCode();
        Character ch = this.zzc;
        if (ch == null) {
            i = 0;
        } else {
            i = ch.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public zzch zza(zzcd zzcd, @CheckForNull Character ch) {
        return new zzcg(zzcd, ch);
    }

    /* access modifiers changed from: package-private */
    public void zzb(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzap.zze(0, i2, bArr.length);
        while (i3 < i2) {
            zze(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        zzcd zzcd = this.zzb;
        return zzcd.zzc * zzcj.zza(i, zzcd.zzd, RoundingMode.CEILING);
    }

    public final zzch zzd() {
        zzch zzch = this.zza;
        if (zzch == null) {
            zzcd zzb2 = this.zzb.zzb();
            if (zzb2 == this.zzb) {
                zzch = this;
            } else {
                zzch = zza(zzb2, this.zzc);
            }
            this.zza = zzch;
        }
        return zzch;
    }

    /* access modifiers changed from: package-private */
    public final void zze(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzap.zze(i, i + i2, bArr.length);
        int i3 = 0;
        zzap.zzc(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.zzb.zzb;
        while (i3 < i2 * 8) {
            zzcd zzcd = this.zzb;
            appendable.append(zzcd.zza(zzcd.zza & ((int) (j >>> (i5 - i3)))));
            i3 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i3 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable.append('=');
                i3 += this.zzb.zzb;
            }
        }
    }

    zzcg(String str, String str2, @CheckForNull Character ch) {
        this(new zzcd(str, str2.toCharArray()), ch);
    }
}
