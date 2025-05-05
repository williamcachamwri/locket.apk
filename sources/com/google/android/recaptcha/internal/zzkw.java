package com.google.android.recaptcha.internal;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzkw {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzb = 100;

    static int zza(byte[] bArr, int i, zzkv zzkv) throws zznp {
        int zzi = zzi(bArr, i, zzkv);
        int i2 = zzkv.zza;
        if (i2 < 0) {
            throw new zznp("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i2 > bArr.length - zzi) {
            throw new zznp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } else if (i2 == 0) {
            zzkv.zzc = zzlg.zzb;
            return zzi;
        } else {
            zzkv.zzc = zzlg.zzl(bArr, zzi, i2);
            return zzi + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzoy zzoy, byte[] bArr, int i, int i2, int i3, zzkv zzkv) throws IOException {
        Object zze = zzoy.zze();
        int zzm = zzm(zze, zzoy, bArr, i, i2, i3, zzkv);
        zzoy.zzf(zze);
        zzkv.zzc = zze;
        return zzm;
    }

    static int zzd(zzoy zzoy, byte[] bArr, int i, int i2, zzkv zzkv) throws IOException {
        Object zze = zzoy.zze();
        int zzn = zzn(zze, zzoy, bArr, i, i2, zzkv);
        zzoy.zzf(zze);
        zzkv.zzc = zze;
        return zzn;
    }

    static int zze(zzoy zzoy, int i, byte[] bArr, int i2, int i3, zznm zznm, zzkv zzkv) throws IOException {
        int zzd = zzd(zzoy, bArr, i2, i3, zzkv);
        zznm.add(zzkv.zzc);
        while (zzd < i3) {
            int zzi = zzi(bArr, zzd, zzkv);
            if (i != zzkv.zza) {
                break;
            }
            zzd = zzd(zzoy, bArr, zzi, i3, zzkv);
            zznm.add(zzkv.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zznm zznm, zzkv zzkv) throws IOException {
        zzng zzng = (zzng) zznm;
        int zzi = zzi(bArr, i, zzkv);
        int i2 = zzkv.zza + zzi;
        while (zzi < i2) {
            zzi = zzi(bArr, zzi, zzkv);
            zzng.zzh(zzkv.zza);
        }
        if (zzi == i2) {
            return zzi;
        }
        throw new zznp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static int zzg(byte[] bArr, int i, zzkv zzkv) throws zznp {
        int zzi = zzi(bArr, i, zzkv);
        int i2 = zzkv.zza;
        if (i2 < 0) {
            throw new zznp("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        } else if (i2 == 0) {
            zzkv.zzc = "";
            return zzi;
        } else {
            zzkv.zzc = new String(bArr, zzi, i2, zznn.zza);
            return zzi + i2;
        }
    }

    static int zzi(byte[] bArr, int i, zzkv zzkv) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzj(b, bArr, i2, zzkv);
        }
        zzkv.zza = b;
        return i2;
    }

    static int zzj(int i, byte[] bArr, int i2, zzkv zzkv) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzkv.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzkv.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzkv.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzkv.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzkv.zza = i11;
                return i12;
            }
        }
    }

    static int zzk(int i, byte[] bArr, int i2, int i3, zznm zznm, zzkv zzkv) {
        zzng zzng = (zzng) zznm;
        int zzi = zzi(bArr, i2, zzkv);
        zzng.zzh(zzkv.zza);
        while (zzi < i3) {
            int zzi2 = zzi(bArr, zzi, zzkv);
            if (i != zzkv.zza) {
                break;
            }
            zzi = zzi(bArr, zzi2, zzkv);
            zzng.zzh(zzkv.zza);
        }
        return zzi;
    }

    static int zzl(byte[] bArr, int i, zzkv zzkv) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzkv.zzb = j;
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
        zzkv.zzb = j2;
        return i3;
    }

    static int zzm(Object obj, zzoy zzoy, byte[] bArr, int i, int i2, int i3, zzkv zzkv) throws IOException {
        int i4 = zzkv.zze + 1;
        zzkv.zze = i4;
        zzq(i4);
        int zzc = ((zzon) zzoy).zzc(obj, bArr, i, i2, i3, zzkv);
        zzkv.zze--;
        zzkv.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzn(java.lang.Object r6, com.google.android.recaptcha.internal.zzoy r7, byte[] r8, int r9, int r10, com.google.android.recaptcha.internal.zzkv r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzj(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x002d
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x002d
            int r10 = r11.zze
            int r10 = r10 + 1
            r11.zze = r10
            zzq(r10)
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
            com.google.android.recaptcha.internal.zznp r6 = new com.google.android.recaptcha.internal.zznp
            java.lang.String r7 = "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."
            r6.<init>((java.lang.String) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzkw.zzn(java.lang.Object, com.google.android.recaptcha.internal.zzoy, byte[], int, int, com.google.android.recaptcha.internal.zzkv):int");
    }

    static int zzo(int i, byte[] bArr, int i2, int i3, zzkv zzkv) throws zznp {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzl(bArr, i2, zzkv);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zzi(bArr, i2, zzkv) + zzkv.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zzi(bArr, i2, zzkv);
                    i6 = zzkv.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zzo(i6, bArr, i2, i3, zzkv);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw new zznp("Failed to parse the message.");
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw new zznp("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zznp("Protocol message contained an invalid tag (zero).");
        }
    }

    static long zzp(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    private static void zzq(int i) throws zznp {
        if (i >= zzb) {
            throw new zznp("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
        }
    }

    static int zzh(int i, byte[] bArr, int i2, int i3, zzpo zzpo, zzkv zzkv) throws zznp {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzl = zzl(bArr, i2, zzkv);
                zzpo.zzj(i, Long.valueOf(zzkv.zzb));
                return zzl;
            } else if (i4 == 1) {
                zzpo.zzj(i, Long.valueOf(zzp(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzi = zzi(bArr, i2, zzkv);
                int i5 = zzkv.zza;
                if (i5 < 0) {
                    throw new zznp("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                } else if (i5 <= bArr.length - zzi) {
                    if (i5 == 0) {
                        zzpo.zzj(i, zzlg.zzb);
                    } else {
                        zzpo.zzj(i, zzlg.zzl(bArr, zzi, i5));
                    }
                    return zzi + i5;
                } else {
                    throw new zznp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzpo zzf = zzpo.zzf();
                int i7 = zzkv.zze + 1;
                zzkv.zze = i7;
                zzq(i7);
                int i8 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzi2 = zzi(bArr, i2, zzkv);
                    i8 = zzkv.zza;
                    if (i8 == i6) {
                        i2 = zzi2;
                        break;
                    }
                    i2 = zzh(i8, bArr, zzi2, i3, zzf, zzkv);
                }
                zzkv.zze--;
                if (i2 > i3 || i8 != i6) {
                    throw new zznp("Failed to parse the message.");
                }
                zzpo.zzj(i, zzf);
                return i2;
            } else if (i4 == 5) {
                zzpo.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw new zznp("Protocol message contained an invalid tag (zero).");
            }
        } else {
            throw new zznp("Protocol message contained an invalid tag (zero).");
        }
    }
}
