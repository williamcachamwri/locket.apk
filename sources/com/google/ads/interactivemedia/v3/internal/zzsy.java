package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
class zzsy extends zzsz {
    @CheckForNull
    private volatile zzsz zza;
    final zzsu zzb;
    @CheckForNull
    final Character zzc;

    zzsy(zzsu zzsu, @CheckForNull Character ch) {
        zzsu zzsu2 = zzsu;
        this.zzb = zzsu;
        boolean z = true;
        if (ch != null) {
            ch.charValue();
            if (zzsu.zze('=')) {
                z = false;
            }
        }
        zzqh.zzg(z, "Padding character %s was already in alphabet", ch);
        this.zzc = ch;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzsy) {
            zzsy zzsy = (zzsy) obj;
            if (!this.zzb.equals(zzsy.zzb) || !Objects.equals(this.zzc, zzsy.zzc)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Character ch = this.zzc;
        return Objects.hashCode(ch) ^ this.zzb.hashCode();
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
    public int zza(byte[] bArr, CharSequence charSequence) throws zzsx {
        zzsu zzsu;
        CharSequence zzg = zzg(charSequence);
        if (this.zzb.zzd(zzg.length())) {
            int i = 0;
            int i2 = 0;
            while (i < zzg.length()) {
                long j = 0;
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    zzsu = this.zzb;
                    if (i3 >= zzsu.zzc) {
                        break;
                    }
                    j <<= zzsu.zzb;
                    if (i + i3 < zzg.length()) {
                        j |= (long) this.zzb.zzb(zzg.charAt(i4 + i));
                        i4++;
                    }
                    i3++;
                }
                int i5 = zzsu.zzd;
                int i6 = i4 * zzsu.zzb;
                int i7 = (i5 - 1) * 8;
                while (i7 >= (i5 * 8) - i6) {
                    bArr[i2] = (byte) ((int) ((j >>> i7) & 255));
                    i7 -= 8;
                    i2++;
                }
                i += this.zzb.zzc;
            }
            return i2;
        }
        throw new zzsx("Invalid input length " + zzg.length());
    }

    /* access modifiers changed from: package-private */
    public zzsz zzb(zzsu zzsu, @CheckForNull Character ch) {
        return new zzsy(zzsu, ch);
    }

    /* access modifiers changed from: package-private */
    public void zzc(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        zzqh.zzh(0, i2, bArr.length);
        while (i3 < i2) {
            zzh(appendable, bArr, i3, Math.min(this.zzb.zzd, i2 - i3));
            i3 += this.zzb.zzd;
        }
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        return (int) (((((long) this.zzb.zzb) * ((long) i)) + 7) / 8);
    }

    /* access modifiers changed from: package-private */
    public final int zze(int i) {
        zzsu zzsu = this.zzb;
        return zzsu.zzc * zztd.zza(i, zzsu.zzd, RoundingMode.CEILING);
    }

    public final zzsz zzf() {
        zzsz zzsz = this.zza;
        if (zzsz == null) {
            zzsu zzsu = this.zzb;
            zzsu zzc2 = zzsu.zzc();
            if (zzc2 == zzsu) {
                zzsz = this;
            } else {
                zzsz = zzb(zzc2, this.zzc);
            }
            this.zza = zzsz;
        }
        return zzsz;
    }

    /* access modifiers changed from: package-private */
    public final void zzh(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
        zzqh.zzh(i, i + i2, bArr.length);
        int i3 = 0;
        zzqh.zzd(i2 <= this.zzb.zzd);
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = (i2 + 1) * 8;
        zzsu zzsu = this.zzb;
        while (i3 < i2 * 8) {
            long j2 = j >>> ((i5 - zzsu.zzb) - i3);
            zzsu zzsu2 = this.zzb;
            appendable.append(zzsu2.zza(((int) j2) & zzsu2.zza));
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

    zzsy(String str, String str2, @CheckForNull Character ch) {
        this(new zzsu(str, str2.toCharArray()), ch);
    }

    /* access modifiers changed from: package-private */
    public final CharSequence zzg(CharSequence charSequence) {
        charSequence.getClass();
        Character ch = this.zzc;
        if (ch == null) {
            return charSequence;
        }
        ch.charValue();
        int length = charSequence.length();
        do {
            length--;
            if (length < 0 || charSequence.charAt(length) != '=') {
            }
            length--;
            break;
        } while (charSequence.charAt(length) != '=');
        return charSequence.subSequence(0, length + 1);
    }
}
