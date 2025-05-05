package com.google.android.gms.internal.pal;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaca extends zzacc {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj = Integer.MAX_VALUE;

    /* synthetic */ zzaca(byte[] bArr, int i, int i2, boolean z, zzabz zzabz) {
        super((zzacb) null);
        this.zze = bArr;
        this.zzf = i2;
        this.zzh = 0;
    }

    private final void zzv() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = this.zzj;
        if (i > i2) {
            int i3 = i - i2;
            this.zzg = i3;
            this.zzf = i - i3;
            return;
        }
        this.zzg = 0;
    }

    public final byte zza() throws IOException {
        int i = this.zzh;
        if (i != this.zzf) {
            byte[] bArr = this.zze;
            this.zzh = i + 1;
            return bArr[i];
        }
        throw zzadi.zzi();
    }

    public final int zzb() {
        return this.zzh;
    }

    public final int zzc(int i) throws zzadi {
        if (i >= 0) {
            int i2 = i + this.zzh;
            if (i2 >= 0) {
                int i3 = this.zzj;
                if (i2 <= i3) {
                    this.zzj = i2;
                    zzv();
                    return i3;
                }
                throw zzadi.zzi();
            }
            throw zzadi.zzg();
        }
        throw zzadi.zzf();
    }

    public final int zzd() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 4) {
            byte[] bArr = this.zze;
            this.zzh = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzadi.zzi();
    }

    public final int zzf() throws IOException {
        if (zzp()) {
            this.zzi = 0;
            return 0;
        }
        int zze2 = zze();
        this.zzi = zze2;
        if ((zze2 >>> 3) != 0) {
            return zze2;
        }
        throw zzadi.zzc();
    }

    public final long zzg() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 8) {
            byte[] bArr = this.zze;
            this.zzh = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzadi.zzi();
    }

    /* access modifiers changed from: package-private */
    public final long zzi() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zza = zza();
            j |= ((long) (zza & Byte.MAX_VALUE)) << i;
            if ((zza & 128) == 0) {
                return j;
            }
        }
        throw zzadi.zze();
    }

    public final zzaby zzj() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                zzaby zzo = zzaby.zzo(this.zze, i2, zze2);
                this.zzh += zze2;
                return zzo;
            }
        }
        if (zze2 == 0) {
            return zzaby.zzb;
        }
        if (zze2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zze2 <= i3 - i4) {
                int i5 = zze2 + i4;
                this.zzh = i5;
                return zzaby.zzq(Arrays.copyOfRange(this.zze, i4, i5));
            }
        }
        if (zze2 <= 0) {
            throw zzadi.zzf();
        }
        throw zzadi.zzi();
    }

    public final String zzk() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                String str = new String(this.zze, i2, zze2, zzadg.zzb);
                this.zzh += zze2;
                return str;
            }
        }
        if (zze2 == 0) {
            return "";
        }
        if (zze2 < 0) {
            throw zzadi.zzf();
        }
        throw zzadi.zzi();
    }

    public final String zzl() throws IOException {
        int zze2 = zze();
        if (zze2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zze2 <= i - i2) {
                String zzd = zzafx.zzd(this.zze, i2, zze2);
                this.zzh += zze2;
                return zzd;
            }
        }
        if (zze2 == 0) {
            return "";
        }
        if (zze2 <= 0) {
            throw zzadi.zzf();
        }
        throw zzadi.zzi();
    }

    public final void zzm(int i) throws zzadi {
        if (this.zzi != i) {
            throw zzadi.zzb();
        }
    }

    public final void zzn(int i) {
        this.zzj = i;
        zzv();
    }

    public final void zzo(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzadi.zzf();
        }
        throw zzadi.zzi();
    }

    public final boolean zzp() throws IOException {
        return this.zzh == this.zzf;
    }

    public final boolean zzq() throws IOException {
        return zzh() != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0067, code lost:
        if (r2[r3] >= 0) goto L_0x0069;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zze() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 != r0) goto L_0x0007
            goto L_0x006c
        L_0x0007:
            byte[] r2 = r5.zze
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0012
            r5.zzh = r3
            return r0
        L_0x0012:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006c
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0023
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0069
        L_0x0023:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0030
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002e:
            r1 = r3
            goto L_0x0069
        L_0x0030:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003e
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0069
        L_0x003e:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x002e
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0069
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002e
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0069
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002e
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006c
        L_0x0069:
            r5.zzh = r1
            return r0
        L_0x006c:
            long r0 = r5.zzi()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaca.zze():int");
    }

    public final boolean zzr(int i) throws IOException {
        int zzf2;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzf - this.zzh >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zze;
                    int i4 = this.zzh;
                    this.zzh = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzadi.zze();
            }
            while (i3 < 10) {
                if (zza() < 0) {
                    i3++;
                }
            }
            throw zzadi.zze();
            return true;
        } else if (i2 == 1) {
            zzo(8);
            return true;
        } else if (i2 == 2) {
            zzo(zze());
            return true;
        } else if (i2 == 3) {
            do {
                zzf2 = zzf();
                if (zzf2 == 0 || !zzr(zzf2)) {
                    zzm(((i >>> 3) << 3) | 4);
                }
                zzf2 = zzf();
                break;
            } while (!zzr(zzf2));
            zzm(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzo(4);
                return true;
            }
            throw zzadi.zza();
        }
    }

    public final long zzh() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        byte b;
        int i = this.zzh;
        int i2 = this.zzf;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.zzh = i3;
                return (long) b2;
            } else if (i2 - i3 >= 9) {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << 14);
                    if (b4 >= 0) {
                        j2 = (long) (b4 ^ 16256);
                    } else {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            i5 = i4 + 1;
                            long j5 = (((long) bArr[i4]) << 28) ^ ((long) b5);
                            if (j5 >= 0) {
                                j4 = 266354560;
                            } else {
                                int i6 = i5 + 1;
                                long j6 = j5 ^ (((long) bArr[i5]) << 35);
                                if (j6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i5 = i6 + 1;
                                    j5 = j6 ^ (((long) bArr[i6]) << 42);
                                    if (j5 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i6 = i5 + 1;
                                        j6 = j5 ^ (((long) bArr[i5]) << 49);
                                        if (j6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i5 = i6 + 1;
                                            j2 = (j6 ^ (((long) bArr[i6]) << 56)) ^ 71499008037633920L;
                                            if (j2 < 0) {
                                                i6 = i5 + 1;
                                                if (((long) bArr[i5]) >= 0) {
                                                    j = j2;
                                                    i4 = i6;
                                                    this.zzh = i4;
                                                    return j;
                                                }
                                            }
                                        }
                                    }
                                }
                                j = j3 ^ j6;
                                i4 = i6;
                                this.zzh = i4;
                                return j;
                            }
                            j2 = j5 ^ j4;
                        }
                    }
                    i4 = i5;
                    j = j2;
                    this.zzh = i4;
                    return j;
                }
                j = (long) b;
                this.zzh = i4;
                return j;
            }
        }
        return zzi();
    }
}
