package com.google.android.gms.internal.fido;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzcd {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    /* access modifiers changed from: private */
    public final char[] zzf;
    private final byte[] zzg;
    private final boolean zzh;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzcd(java.lang.String r10, char[] r11) {
        /*
            r9 = this;
            r0 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r0]
            r2 = -1
            java.util.Arrays.fill(r1, r2)
            r3 = 0
            r4 = r3
        L_0x000a:
            int r5 = r11.length
            if (r4 >= r5) goto L_0x002b
            char r5 = r11[r4]
            r6 = 1
            if (r5 >= r0) goto L_0x0014
            r7 = r6
            goto L_0x0015
        L_0x0014:
            r7 = r3
        L_0x0015:
            java.lang.String r8 = "Non-ASCII character: %s"
            com.google.android.gms.internal.fido.zzap.zzd(r7, r8, r5)
            byte r7 = r1[r5]
            if (r7 != r2) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r6 = r3
        L_0x0020:
            java.lang.String r7 = "Duplicate character: %s"
            com.google.android.gms.internal.fido.zzap.zzd(r6, r7, r5)
            byte r6 = (byte) r4
            r1[r5] = r6
            int r4 = r4 + 1
            goto L_0x000a
        L_0x002b:
            r9.<init>(r10, r11, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzcd.<init>(java.lang.String, char[]):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzcd) {
            zzcd zzcd = (zzcd) obj;
            if (this.zzh != zzcd.zzh || !Arrays.equals(this.zzf, zzcd.zzf)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf) + (true != this.zzh ? 1237 : 1231);
    }

    public final String toString() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final char zza(int i) {
        return this.zzf[i];
    }

    /* access modifiers changed from: package-private */
    public final zzcd zzb() {
        int i;
        boolean z;
        int i2 = 0;
        for (char zza2 : this.zzf) {
            if (zzad.zza(zza2)) {
                char[] cArr = this.zzf;
                int length = cArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        z = false;
                        break;
                    }
                    char c = cArr[i3];
                    if (c >= 'A' && c <= 'Z') {
                        z = true;
                        break;
                    }
                    i3++;
                }
                if (!z) {
                    char[] cArr2 = new char[this.zzf.length];
                    while (true) {
                        char[] cArr3 = this.zzf;
                        if (i2 >= cArr3.length) {
                            break;
                        }
                        char c2 = cArr3[i2];
                        if (zzad.zza(c2)) {
                            c2 ^= ' ';
                        }
                        cArr2[i2] = (char) c2;
                        i2++;
                    }
                    zzcd zzcd = new zzcd(this.zze.concat(".upperCase()"), cArr2);
                    if (!this.zzh || zzcd.zzh) {
                        return zzcd;
                    }
                    byte[] bArr = zzcd.zzg;
                    byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
                    for (i = 65; i <= 90; i++) {
                        int i4 = i | 32;
                        byte[] bArr2 = zzcd.zzg;
                        byte b = bArr2[i];
                        byte b2 = bArr2[i4];
                        if (b == -1) {
                            copyOf[i] = b2;
                        } else {
                            char c3 = (char) i;
                            char c4 = (char) i4;
                            if (b2 == -1) {
                                copyOf[i4] = b;
                            } else {
                                throw new IllegalStateException(zzaq.zza("Can't ignoreCase() since '%s' and '%s' encode different values", Character.valueOf(c3), Character.valueOf(c4)));
                            }
                        }
                    }
                    return new zzcd(zzcd.zze.concat(".ignoreCase()"), zzcd.zzf, copyOf, true);
                }
                throw new IllegalStateException("Cannot call upperCase() on a mixed-case alphabet");
            }
        }
        return this;
    }

    public final boolean zzc(char c) {
        byte[] bArr = this.zzg;
        return bArr.length > 61 && bArr[61] != -1;
    }

    private zzcd(String str, char[] cArr, byte[] bArr, boolean z) {
        this.zze = str;
        cArr.getClass();
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb2 = zzcj.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb2;
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(zzb2);
            int i = 1 << (3 - numberOfTrailingZeros);
            this.zzc = i;
            this.zzd = zzb2 >> numberOfTrailingZeros;
            this.zza = length - 1;
            this.zzg = bArr;
            boolean[] zArr = new boolean[i];
            for (int i2 = 0; i2 < this.zzd; i2++) {
                zArr[zzcj.zza(i2 * 8, this.zzb, RoundingMode.CEILING)] = true;
            }
            this.zzh = z;
        } catch (ArithmeticException e) {
            int length2 = cArr.length;
            throw new IllegalArgumentException("Illegal alphabet length " + length2, e);
        }
    }
}
