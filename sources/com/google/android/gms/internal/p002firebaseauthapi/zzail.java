package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.common.base.Ascii;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzail  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzail {
    private static volatile int zza = 100;

    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzaik zzaik) throws zzakf {
        int zzc = zzc(bArr, i, zzaik);
        int i2 = zzaik.zza;
        if (i2 < 0) {
            throw zzakf.zzf();
        } else if (i2 > bArr.length - zzc) {
            throw zzakf.zzj();
        } else if (i2 == 0) {
            zzaik.zzc = zzaip.zza;
            return zzc;
        } else {
            zzaik.zzc = zzaip.zza(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzalc zzalc, zzamo<zzamn, zzamn> zzamo, zzaik zzaik) throws IOException {
        if (zzaik.zzd.zza(zzalc, i >>> 3) == null) {
            return zza(i, bArr, i2, i3, zzalg.zzc(obj), zzaik);
        }
        zzajy.zzb zzb = (zzajy.zzb) obj;
        zzb.zza();
        zzajr<zzajy.zze> zzajr = zzb.zzc;
        throw new NoSuchMethodError();
    }

    static int zzc(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private static int zza(zzalv zzalv, byte[] bArr, int i, int i2, int i3, zzaik zzaik) throws IOException {
        Object zza2 = zzalv.zza();
        int zza3 = zza(zza2, zzalv, bArr, i, i2, i3, zzaik);
        zzalv.zzd(zza2);
        zzaik.zzc = zza2;
        return zza3;
    }

    static int zza(zzalv zzalv, int i, byte[] bArr, int i2, int i3, zzakc<?> zzakc, zzaik zzaik) throws IOException {
        int i4 = (i & -8) | 4;
        int zza2 = zza(zzalv, bArr, i2, i3, i4, zzaik);
        zzakc.add(zzaik.zzc);
        while (zza2 < i3) {
            int zzc = zzc(bArr, zza2, zzaik);
            if (i != zzaik.zza) {
                break;
            }
            zza2 = zza(zzalv, bArr, zzc, i3, i4, zzaik);
            zzakc.add(zzaik.zzc);
        }
        return zza2;
    }

    static int zza(zzalv zzalv, byte[] bArr, int i, int i2, zzaik zzaik) throws IOException {
        Object zza2 = zzalv.zza();
        int zza3 = zza(zza2, zzalv, bArr, i, i2, zzaik);
        zzalv.zzd(zza2);
        zzaik.zzc = zza2;
        return zza3;
    }

    static int zzb(zzalv<?> zzalv, int i, byte[] bArr, int i2, int i3, zzakc<?> zzakc, zzaik zzaik) throws IOException {
        int zza2 = zza((zzalv) zzalv, bArr, i2, i3, zzaik);
        zzakc.add(zzaik.zzc);
        while (zza2 < i3) {
            int zzc = zzc(bArr, zza2, zzaik);
            if (i != zzaik.zza) {
                break;
            }
            zza2 = zza((zzalv) zzalv, bArr, zzc, i3, zzaik);
            zzakc.add(zzaik.zzc);
        }
        return zza2;
    }

    static int zza(byte[] bArr, int i, zzakc<?> zzakc, zzaik zzaik) throws IOException {
        zzajz zzajz = (zzajz) zzakc;
        int zzc = zzc(bArr, i, zzaik);
        int i2 = zzaik.zza + zzc;
        while (zzc < i2) {
            zzc = zzc(bArr, zzc, zzaik);
            zzajz.zzc(zzaik.zza);
        }
        if (zzc == i2) {
            return zzc;
        }
        throw zzakf.zzj();
    }

    static int zzb(byte[] bArr, int i, zzaik zzaik) throws zzakf {
        int zzc = zzc(bArr, i, zzaik);
        int i2 = zzaik.zza;
        if (i2 < 0) {
            throw zzakf.zzf();
        } else if (i2 == 0) {
            zzaik.zzc = "";
            return zzc;
        } else {
            zzaik.zzc = zzamt.zzb(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzamn zzamn, zzaik zzaik) throws zzakf {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzd = zzd(bArr, i2, zzaik);
                zzamn.zza(i, (Object) Long.valueOf(zzaik.zzb));
                return zzd;
            } else if (i4 == 1) {
                zzamn.zza(i, (Object) Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzc = zzc(bArr, i2, zzaik);
                int i5 = zzaik.zza;
                if (i5 < 0) {
                    throw zzakf.zzf();
                } else if (i5 <= bArr.length - zzc) {
                    if (i5 == 0) {
                        zzamn.zza(i, (Object) zzaip.zza);
                    } else {
                        zzamn.zza(i, (Object) zzaip.zza(bArr, zzc, i5));
                    }
                    return zzc + i5;
                } else {
                    throw zzakf.zzj();
                }
            } else if (i4 == 3) {
                zzamn zzd2 = zzamn.zzd();
                int i6 = (i & -8) | 4;
                zzaik.zze++;
                zza(zzaik.zze);
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzc2 = zzc(bArr, i2, zzaik);
                    int i8 = zzaik.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzc2;
                        break;
                    }
                    int zza2 = zza(i7, bArr, zzc2, i3, zzd2, zzaik);
                    i7 = i8;
                    i2 = zza2;
                }
                zzaik.zze--;
                if (i2 > i3 || i7 != i6) {
                    throw zzakf.zzg();
                }
                zzamn.zza(i, (Object) zzd2);
                return i2;
            } else if (i4 == 5) {
                zzamn.zza(i, (Object) Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzakf.zzc();
            }
        } else {
            throw zzakf.zzc();
        }
    }

    static int zzc(byte[] bArr, int i, zzaik zzaik) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzaik);
        }
        zzaik.zza = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzaik zzaik) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzaik.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzaik.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzaik.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzaik.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzaik.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzakc<?> zzakc, zzaik zzaik) {
        zzajz zzajz = (zzajz) zzakc;
        int zzc = zzc(bArr, i2, zzaik);
        zzajz.zzc(zzaik.zza);
        while (zzc < i3) {
            int zzc2 = zzc(bArr, zzc, zzaik);
            if (i != zzaik.zza) {
                break;
            }
            zzc = zzc(bArr, zzc2, zzaik);
            zzajz.zzc(zzaik.zza);
        }
        return zzc;
    }

    static int zzd(byte[] bArr, int i, zzaik zzaik) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzaik.zzb = j;
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
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzaik.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzalv zzalv, byte[] bArr, int i, int i2, int i3, zzaik zzaik) throws IOException {
        zzaik.zze++;
        zza(zzaik.zze);
        int zza2 = ((zzalg) zzalv).zza(obj, bArr, i, i2, i3, zzaik);
        zzaik.zze--;
        zzaik.zzc = obj;
        return zza2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(java.lang.Object r6, com.google.android.gms.internal.p002firebaseauthapi.zzalv r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.p002firebaseauthapi.zzaik r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zza((int) r9, (byte[]) r8, (int) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzaik) r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x002f
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x002f
            int r10 = r11.zze
            int r10 = r10 + 1
            r11.zze = r10
            int r10 = r11.zze
            zza(r10)
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            int r7 = r11.zze
            int r7 = r7 + -1
            r11.zze = r7
            r11.zzc = r6
            return r9
        L_0x002f:
            com.google.android.gms.internal.firebase-auth-api.zzakf r6 = com.google.android.gms.internal.p002firebaseauthapi.zzakf.zzj()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzail.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzalv, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzaik):int");
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzaik zzaik) throws zzakf {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzd(bArr, i2, zzaik);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zzc(bArr, i2, zzaik) + zzaik.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zzc(bArr, i2, zzaik);
                    i6 = zzaik.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzaik);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzakf.zzg();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzakf.zzc();
            }
        } else {
            throw zzakf.zzc();
        }
    }

    static long zzd(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private static void zza(int i) throws zzakf {
        if (i >= zza) {
            throw zzakf.zzh();
        }
    }
}
