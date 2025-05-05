package com.google.ads.interactivemedia.v3.internal;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzacm {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzb = 100;

    static int zza(byte[] bArr, int i, zzacl zzacl) throws zzaeg {
        int zzh = zzh(bArr, i, zzacl);
        int i2 = zzacl.zza;
        if (i2 < 0) {
            throw new zzaeg("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i2 > bArr.length - zzh) {
            throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else if (i2 == 0) {
            zzacl.zzc = zzacw.zzb;
            return zzh;
        } else {
            zzacl.zzc = zzacw.zzp(bArr, zzh, i2);
            return zzh + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzaft zzaft, byte[] bArr, int i, int i2, int i3, zzacl zzacl) throws IOException {
        Object zze = zzaft.zze();
        int zzl = zzl(zze, zzaft, bArr, i, i2, i3, zzacl);
        zzaft.zzf(zze);
        zzacl.zzc = zze;
        return zzl;
    }

    static int zzd(zzaft zzaft, byte[] bArr, int i, int i2, zzacl zzacl) throws IOException {
        Object zze = zzaft.zze();
        int zzm = zzm(zze, zzaft, bArr, i, i2, zzacl);
        zzaft.zzf(zze);
        zzacl.zzc = zze;
        return zzm;
    }

    static int zze(zzaft zzaft, int i, byte[] bArr, int i2, int i3, zzaed zzaed, zzacl zzacl) throws IOException {
        int zzd = zzd(zzaft, bArr, i2, i3, zzacl);
        zzaed.add(zzacl.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzacl);
            if (i != zzacl.zza) {
                break;
            }
            zzd = zzd(zzaft, bArr, zzh, i3, zzacl);
            zzaed.add(zzacl.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzaed zzaed, zzacl zzacl) throws IOException {
        zzadz zzadz = (zzadz) zzaed;
        int zzh = zzh(bArr, i, zzacl);
        int i2 = zzacl.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzacl);
            zzadz.zzg(zzacl.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static int zzh(byte[] bArr, int i, zzacl zzacl) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzi(b, bArr, i2, zzacl);
        }
        zzacl.zza = b;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzacl zzacl) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzacl.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzacl.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzacl.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzacl.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzacl.zza = i11;
                return i12;
            }
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzaed zzaed, zzacl zzacl) {
        zzadz zzadz = (zzadz) zzaed;
        int zzh = zzh(bArr, i2, zzacl);
        zzadz.zzg(zzacl.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzacl);
            if (i != zzacl.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzacl);
            zzadz.zzg(zzacl.zza);
        }
        return zzh;
    }

    static int zzk(byte[] bArr, int i, zzacl zzacl) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzacl.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            byte b3 = b2;
            i3 = i5;
            b = b3;
        }
        zzacl.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzaft zzaft, byte[] bArr, int i, int i2, int i3, zzacl zzacl) throws IOException {
        int i4 = zzacl.zze + 1;
        zzacl.zze = i4;
        zzo(i4);
        int zzc = ((zzafe) zzaft).zzc(obj, bArr, i, i2, i3, zzacl);
        zzacl.zze--;
        zzacl.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzm(java.lang.Object r6, com.google.ads.interactivemedia.v3.internal.zzaft r7, byte[] r8, int r9, int r10, com.google.ads.interactivemedia.v3.internal.zzacl r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzi(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x002d
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x002d
            int r10 = r11.zze
            int r10 = r10 + 1
            r11.zze = r10
            zzo(r10)
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zzi(r1, r2, r3, r4, r5)
            int r7 = r11.zze
            int r7 = r7 + -1
            r11.zze = r7
            r11.zzc = r6
            return r9
        L_0x002d:
            com.google.ads.interactivemedia.v3.internal.zzaeg r6 = new com.google.ads.interactivemedia.v3.internal.zzaeg
            java.lang.String r7 = "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."
            r6.<init>((java.lang.String) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzacm.zzm(java.lang.Object, com.google.ads.interactivemedia.v3.internal.zzaft, byte[], int, int, com.google.ads.interactivemedia.v3.internal.zzacl):int");
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    private static void zzo(int i) throws zzaeg {
        if (i >= zzb) {
            throw new zzaeg("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzagi zzagi, zzacl zzacl) throws zzaeg {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzk = zzk(bArr, i2, zzacl);
                zzagi.zzj(i, Long.valueOf(zzacl.zzb));
                return zzk;
            } else if (i4 == 1) {
                zzagi.zzj(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzh = zzh(bArr, i2, zzacl);
                int i5 = zzacl.zza;
                if (i5 < 0) {
                    throw new zzaeg("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                } else if (i5 <= bArr.length - zzh) {
                    if (i5 == 0) {
                        zzagi.zzj(i, zzacw.zzb);
                    } else {
                        zzagi.zzj(i, zzacw.zzp(bArr, zzh, i5));
                    }
                    return zzh + i5;
                } else {
                    throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzagi zzf = zzagi.zzf();
                int i7 = zzacl.zze + 1;
                zzacl.zze = i7;
                zzo(i7);
                int i8 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i2, zzacl);
                    i8 = zzacl.zza;
                    if (i8 == i6) {
                        i2 = zzh2;
                        break;
                    }
                    i2 = zzg(i8, bArr, zzh2, i3, zzf, zzacl);
                }
                zzacl.zze--;
                if (i2 > i3 || i8 != i6) {
                    throw new zzaeg("Failed to parse the message.");
                }
                zzagi.zzj(i, zzf);
                return i2;
            } else if (i4 == 5) {
                zzagi.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw new zzaeg("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zzaeg("Protocol message contained an invalid tag (zero).");
        }
    }
}
