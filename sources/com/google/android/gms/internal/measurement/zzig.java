package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzig {
    private static volatile int zza = 100;

    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzij zzij) throws zzkb {
        int zzc = zzc(bArr, i, zzij);
        int i2 = zzij.zza;
        if (i2 < 0) {
            throw zzkb.zzf();
        } else if (i2 > bArr.length - zzc) {
            throw zzkb.zzi();
        } else if (i2 == 0) {
            zzij.zzc = zzik.zza;
            return zzc;
        } else {
            zzij.zzc = zzik.zza(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzlc zzlc, zzmk<zzmj, zzmj> zzmk, zzij zzij) throws IOException {
        if (zzij.zzd.zza(zzlc, i >>> 3) == null) {
            return zza(i, bArr, i2, i3, zzlg.zzc(obj), zzij);
        }
        zzjt.zzd zzd = (zzjt.zzd) obj;
        zzd.zza();
        zzjm<zzjt.zzc> zzjm = zzd.zzc;
        throw new NoSuchMethodError();
    }

    static int zzc(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private static int zza(zzlu zzlu, byte[] bArr, int i, int i2, int i3, zzij zzij) throws IOException {
        Object zza2 = zzlu.zza();
        int zza3 = zza(zza2, zzlu, bArr, i, i2, i3, zzij);
        zzlu.zzd(zza2);
        zzij.zzc = zza2;
        return zza3;
    }

    static int zza(zzlu zzlu, int i, byte[] bArr, int i2, int i3, zzkc<?> zzkc, zzij zzij) throws IOException {
        int i4 = (i & -8) | 4;
        int zza2 = zza(zzlu, bArr, i2, i3, i4, zzij);
        zzkc.add(zzij.zzc);
        while (zza2 < i3) {
            int zzc = zzc(bArr, zza2, zzij);
            if (i != zzij.zza) {
                break;
            }
            zza2 = zza(zzlu, bArr, zzc, i3, i4, zzij);
            zzkc.add(zzij.zzc);
        }
        return zza2;
    }

    static int zza(zzlu zzlu, byte[] bArr, int i, int i2, zzij zzij) throws IOException {
        Object zza2 = zzlu.zza();
        int zza3 = zza(zza2, zzlu, bArr, i, i2, zzij);
        zzlu.zzd(zza2);
        zzij.zzc = zza2;
        return zza3;
    }

    static int zzb(zzlu<?> zzlu, int i, byte[] bArr, int i2, int i3, zzkc<?> zzkc, zzij zzij) throws IOException {
        int zza2 = zza((zzlu) zzlu, bArr, i2, i3, zzij);
        zzkc.add(zzij.zzc);
        while (zza2 < i3) {
            int zzc = zzc(bArr, zza2, zzij);
            if (i != zzij.zza) {
                break;
            }
            zza2 = zza((zzlu) zzlu, bArr, zzc, i3, zzij);
            zzkc.add(zzij.zzc);
        }
        return zza2;
    }

    static int zza(byte[] bArr, int i, zzkc<?> zzkc, zzij zzij) throws IOException {
        zzjw zzjw = (zzjw) zzkc;
        int zzc = zzc(bArr, i, zzij);
        int i2 = zzij.zza + zzc;
        while (zzc < i2) {
            zzc = zzc(bArr, zzc, zzij);
            zzjw.zzd(zzij.zza);
        }
        if (zzc == i2) {
            return zzc;
        }
        throw zzkb.zzi();
    }

    static int zzb(byte[] bArr, int i, zzij zzij) throws zzkb {
        int zzc = zzc(bArr, i, zzij);
        int i2 = zzij.zza;
        if (i2 < 0) {
            throw zzkb.zzf();
        } else if (i2 == 0) {
            zzij.zzc = "";
            return zzc;
        } else {
            zzij.zzc = zzmp.zzb(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzmj zzmj, zzij zzij) throws zzkb {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzd = zzd(bArr, i2, zzij);
                zzmj.zza(i, (Object) Long.valueOf(zzij.zzb));
                return zzd;
            } else if (i4 == 1) {
                zzmj.zza(i, (Object) Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzc = zzc(bArr, i2, zzij);
                int i5 = zzij.zza;
                if (i5 < 0) {
                    throw zzkb.zzf();
                } else if (i5 <= bArr.length - zzc) {
                    if (i5 == 0) {
                        zzmj.zza(i, (Object) zzik.zza);
                    } else {
                        zzmj.zza(i, (Object) zzik.zza(bArr, zzc, i5));
                    }
                    return zzc + i5;
                } else {
                    throw zzkb.zzi();
                }
            } else if (i4 == 3) {
                zzmj zzd2 = zzmj.zzd();
                int i6 = (i & -8) | 4;
                zzij.zze++;
                zza(zzij.zze);
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzc2 = zzc(bArr, i2, zzij);
                    int i8 = zzij.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzc2;
                        break;
                    }
                    int zza2 = zza(i7, bArr, zzc2, i3, zzd2, zzij);
                    i7 = i8;
                    i2 = zza2;
                }
                zzij.zze--;
                if (i2 > i3 || i7 != i6) {
                    throw zzkb.zzg();
                }
                zzmj.zza(i, (Object) zzd2);
                return i2;
            } else if (i4 == 5) {
                zzmj.zza(i, (Object) Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzkb.zzc();
            }
        } else {
            throw zzkb.zzc();
        }
    }

    static int zzc(byte[] bArr, int i, zzij zzij) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zza((int) b, bArr, i2, zzij);
        }
        zzij.zza = b;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzij zzij) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzij.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzij.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzij.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzij.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzij.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzkc<?> zzkc, zzij zzij) {
        zzjw zzjw = (zzjw) zzkc;
        int zzc = zzc(bArr, i2, zzij);
        zzjw.zzd(zzij.zza);
        while (zzc < i3) {
            int zzc2 = zzc(bArr, zzc, zzij);
            if (i != zzij.zza) {
                break;
            }
            zzc = zzc(bArr, zzc2, zzij);
            zzjw.zzd(zzij.zza);
        }
        return zzc;
    }

    static int zzd(byte[] bArr, int i, zzij zzij) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzij.zzb = j;
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
        zzij.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzlu zzlu, byte[] bArr, int i, int i2, int i3, zzij zzij) throws IOException {
        zzij.zze++;
        zza(zzij.zze);
        int zza2 = ((zzlg) zzlu).zza(obj, bArr, i, i2, i3, zzij);
        zzij.zze--;
        zzij.zzc = obj;
        return zza2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(java.lang.Object r6, com.google.android.gms.internal.measurement.zzlu r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.measurement.zzij r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zza((int) r9, (byte[]) r8, (int) r0, (com.google.android.gms.internal.measurement.zzij) r11)
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
            com.google.android.gms.internal.measurement.zzkb r6 = com.google.android.gms.internal.measurement.zzkb.zzi()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzig.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzlu, byte[], int, int, com.google.android.gms.internal.measurement.zzij):int");
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzij zzij) throws zzkb {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzd(bArr, i2, zzij);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zzc(bArr, i2, zzij) + zzij.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zzc(bArr, i2, zzij);
                    i6 = zzij.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzij);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzkb.zzg();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzkb.zzc();
            }
        } else {
            throw zzkb.zzc();
        }
    }

    static long zzd(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private static void zza(int i) throws zzkb {
        if (i >= zza) {
            throw zzkb.zzh();
        }
    }
}
