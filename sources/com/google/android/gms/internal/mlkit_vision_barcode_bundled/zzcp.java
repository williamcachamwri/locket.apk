package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzcp {
    static int zza(byte[] bArr, int i, zzco zzco) throws zzeo {
        int zzj = zzj(bArr, i, zzco);
        int i2 = zzco.zza;
        if (i2 < 0) {
            throw zzeo.zzd();
        } else if (i2 > bArr.length - zzj) {
            throw zzeo.zzg();
        } else if (i2 == 0) {
            zzco.zzc = zzdb.zzb;
            return zzj;
        } else {
            zzco.zzc = zzdb.zzr(bArr, zzj, i2);
            return zzj + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzgh zzgh, byte[] bArr, int i, int i2, int i3, zzco zzco) throws IOException {
        Object zze = zzgh.zze();
        int zzn = zzn(zze, zzgh, bArr, i, i2, i3, zzco);
        zzgh.zzf(zze);
        zzco.zzc = zze;
        return zzn;
    }

    static int zzd(zzgh zzgh, byte[] bArr, int i, int i2, zzco zzco) throws IOException {
        Object zze = zzgh.zze();
        int zzo = zzo(zze, zzgh, bArr, i, i2, zzco);
        zzgh.zzf(zze);
        zzco.zzc = zze;
        return zzo;
    }

    static int zze(zzgh zzgh, int i, byte[] bArr, int i2, int i3, zzel zzel, zzco zzco) throws IOException {
        int zzd = zzd(zzgh, bArr, i2, i3, zzco);
        zzel.add(zzco.zzc);
        while (zzd < i3) {
            int zzj = zzj(bArr, zzd, zzco);
            if (i != zzco.zza) {
                break;
            }
            zzd = zzd(zzgh, bArr, zzj, i3, zzco);
            zzel.add(zzco.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzel zzel, zzco zzco) throws IOException {
        zzee zzee = (zzee) zzel;
        int zzj = zzj(bArr, i, zzco);
        int i2 = zzco.zza + zzj;
        while (zzj < i2) {
            zzj = zzj(bArr, zzj, zzco);
            zzee.zzg(zzco.zza);
        }
        if (zzj == i2) {
            return zzj;
        }
        throw zzeo.zzg();
    }

    static int zzg(byte[] bArr, int i, zzco zzco) throws zzeo {
        int zzj = zzj(bArr, i, zzco);
        int i2 = zzco.zza;
        if (i2 < 0) {
            throw zzeo.zzd();
        } else if (i2 == 0) {
            zzco.zzc = "";
            return zzj;
        } else {
            zzco.zzc = new String(bArr, zzj, i2, zzem.zzb);
            return zzj + i2;
        }
    }

    static int zzh(byte[] bArr, int i, zzco zzco) throws zzeo {
        int i2;
        int zzj = zzj(bArr, i, zzco);
        int i3 = zzco.zza;
        if (i3 < 0) {
            throw zzeo.zzd();
        } else if (i3 == 0) {
            zzco.zzc = "";
            return zzj;
        } else {
            int length = bArr.length;
            int i4 = zzhn.zza;
            if ((zzj | i3 | ((length - zzj) - i3)) >= 0) {
                int i5 = zzj + i3;
                char[] cArr = new char[i3];
                int i6 = 0;
                while (i2 < i5) {
                    byte b = bArr[i2];
                    if (!zzhj.zzd(b)) {
                        break;
                    }
                    zzj = i2 + 1;
                    cArr[i6] = (char) b;
                    i6++;
                }
                int i7 = i6;
                while (i2 < i5) {
                    int i8 = i2 + 1;
                    byte b2 = bArr[i2];
                    if (zzhj.zzd(b2)) {
                        int i9 = i7 + 1;
                        cArr[i7] = (char) b2;
                        i2 = i8;
                        while (true) {
                            i7 = i9;
                            if (i2 >= i5) {
                                break;
                            }
                            byte b3 = bArr[i2];
                            if (!zzhj.zzd(b3)) {
                                break;
                            }
                            i2++;
                            i9 = i7 + 1;
                            cArr[i7] = (char) b3;
                        }
                    } else if (b2 < -32) {
                        if (i8 < i5) {
                            zzhj.zzc(b2, bArr[i8], cArr, i7);
                            i2 = i8 + 1;
                            i7++;
                        } else {
                            throw zzeo.zzc();
                        }
                    } else if (b2 < -16) {
                        if (i8 < i5 - 1) {
                            int i10 = i8 + 1;
                            zzhj.zzb(b2, bArr[i8], bArr[i10], cArr, i7);
                            i2 = i10 + 1;
                            i7++;
                        } else {
                            throw zzeo.zzc();
                        }
                    } else if (i8 < i5 - 2) {
                        int i11 = i8 + 1;
                        byte b4 = bArr[i8];
                        int i12 = i11 + 1;
                        zzhj.zza(b2, b4, bArr[i11], bArr[i12], cArr, i7);
                        i7 += 2;
                        i2 = i12 + 1;
                    } else {
                        throw zzeo.zzc();
                    }
                }
                zzco.zzc = new String(cArr, 0, i7);
                return i5;
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(zzj), Integer.valueOf(i3)}));
        }
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzgz zzgz, zzco zzco) throws zzeo {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzm = zzm(bArr, i2, zzco);
                zzgz.zzj(i, Long.valueOf(zzco.zzb));
                return zzm;
            } else if (i4 == 1) {
                zzgz.zzj(i, Long.valueOf(zzq(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzj = zzj(bArr, i2, zzco);
                int i5 = zzco.zza;
                if (i5 < 0) {
                    throw zzeo.zzd();
                } else if (i5 <= bArr.length - zzj) {
                    if (i5 == 0) {
                        zzgz.zzj(i, zzdb.zzb);
                    } else {
                        zzgz.zzj(i, zzdb.zzr(bArr, zzj, i5));
                    }
                    return zzj + i5;
                } else {
                    throw zzeo.zzg();
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzgz zzf = zzgz.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzj2 = zzj(bArr, i2, zzco);
                    int i8 = zzco.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzj2;
                        break;
                    }
                    int zzi = zzi(i7, bArr, zzj2, i3, zzf, zzco);
                    i7 = i8;
                    i2 = zzi;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzeo.zze();
                }
                zzgz.zzj(i, zzf);
                return i2;
            } else if (i4 == 5) {
                zzgz.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzeo.zzb();
            }
        } else {
            throw zzeo.zzb();
        }
    }

    static int zzj(byte[] bArr, int i, zzco zzco) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzco);
        }
        zzco.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzco zzco) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzco.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzco.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzco.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzco.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzco.zza = i11;
                return i12;
            }
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzel zzel, zzco zzco) {
        zzee zzee = (zzee) zzel;
        int zzj = zzj(bArr, i2, zzco);
        zzee.zzg(zzco.zza);
        while (zzj < i3) {
            int zzj2 = zzj(bArr, zzj, zzco);
            if (i != zzco.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzco);
            zzee.zzg(zzco.zza);
        }
        return zzj;
    }

    static int zzm(byte[] bArr, int i, zzco zzco) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzco.zzb = j;
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
        zzco.zzb = j2;
        return i3;
    }

    static int zzn(Object obj, zzgh zzgh, byte[] bArr, int i, int i2, int i3, zzco zzco) throws IOException {
        int zzc = ((zzfr) zzgh).zzc(obj, bArr, i, i2, i3, zzco);
        zzco.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzo(java.lang.Object r6, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzk(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zzh(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzo(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):int");
    }

    static long zzq(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static int zzp(int i, byte[] bArr, int i2, int i3, zzco zzco) throws zzeo {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzm(bArr, i2, zzco);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zzj(bArr, i2, zzco) + zzco.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zzj(bArr, i2, zzco);
                    i6 = zzco.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zzp(i6, bArr, i2, i3, zzco);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzeo.zze();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzeo.zzb();
            }
        } else {
            throw zzeo.zzb();
        }
    }
}
