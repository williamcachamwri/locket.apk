package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzajd extends zzajb {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    private final byte zzw() throws IOException {
        if (this.zzi == this.zzg) {
            zzg(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzz());
    }

    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzx());
    }

    private static int zza(InputStream inputStream) throws IOException {
        try {
            return inputStream.available();
        } catch (zzakf e) {
            e.zzk();
            throw e;
        }
    }

    public final int zzc() {
        return this.zzk + this.zzi;
    }

    public final int zza(int i) throws zzakf {
        if (i >= 0) {
            int i2 = i + this.zzk + this.zzi;
            if (i2 >= 0) {
                int i3 = this.zzl;
                if (i2 <= i3) {
                    this.zzl = i2;
                    zzab();
                    return i3;
                }
                throw zzakf.zzj();
            }
            throw zzakf.zzg();
        }
        throw zzakf.zzf();
    }

    private static int zza(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        try {
            return inputStream.read(bArr, i, i2);
        } catch (zzakf e) {
            e.zzk();
            throw e;
        }
    }

    public final int zzd() throws IOException {
        return zzy();
    }

    public final int zze() throws IOException {
        return zzx();
    }

    public final int zzf() throws IOException {
        return zzy();
    }

    private final int zzx() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzg(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzy() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 == r0) goto L_0x006b
            byte[] r2 = r5.zzf
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.zzi = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0068
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002d:
            r1 = r3
            goto L_0x0068
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0068
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006b
        L_0x0068:
            r5.zzi = r1
            return r0
        L_0x006b:
            long r0 = r5.zzm()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzajd.zzy():int");
    }

    public final int zzg() throws IOException {
        return zzx();
    }

    public final int zzh() throws IOException {
        return zze(zzy());
    }

    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzj = 0;
            return 0;
        }
        int zzy = zzy();
        this.zzj = zzy;
        if ((zzy >>> 3) != 0) {
            return zzy;
        }
        throw zzakf.zzc();
    }

    public final int zzj() throws IOException {
        return zzy();
    }

    public final long zzk() throws IOException {
        return zzz();
    }

    public final long zzl() throws IOException {
        return zzaa();
    }

    private final long zzz() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzg(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzaa() throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r11.zzi
            int r1 = r11.zzg
            if (r1 == r0) goto L_0x00b8
            byte[] r2 = r11.zzf
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0012
            r11.zzi = r3
            long r0 = (long) r0
            return r0
        L_0x0012:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x00b8
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0025
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0022:
            long r2 = (long) r0
            goto L_0x00b5
        L_0x0025:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0036
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto L_0x00b5
        L_0x0036:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0044
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0022
        L_0x0044:
            long r3 = (long) r0
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x005b
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L_0x0057:
            long r2 = r3 ^ r1
            r1 = r0
            goto L_0x00b5
        L_0x005b:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0070
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L_0x006d:
            long r2 = r3 ^ r5
            goto L_0x00b5
        L_0x0070:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0083
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L_0x0057
        L_0x0083:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0096
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L_0x006d
        L_0x0096:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b3
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x00b8
            goto L_0x00b4
        L_0x00b3:
            r1 = r0
        L_0x00b4:
            r2 = r3
        L_0x00b5:
            r11.zzi = r1
            return r2
        L_0x00b8:
            long r0 = r11.zzm()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzajd.zzaa():long");
    }

    /* access modifiers changed from: package-private */
    public final long zzm() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzw = zzw();
            j |= ((long) (zzw & Byte.MAX_VALUE)) << i;
            if ((zzw & 128) == 0) {
                return j;
            }
        }
        throw zzakf.zze();
    }

    public final long zzn() throws IOException {
        return zzz();
    }

    public final long zzo() throws IOException {
        return zza(zzaa());
    }

    public final long zzp() throws IOException {
        return zzaa();
    }

    private static long zza(InputStream inputStream, long j) throws IOException {
        try {
            return inputStream.skip(j);
        } catch (zzakf e) {
            e.zzk();
            throw e;
        }
    }

    public final zzaip zzq() throws IOException {
        int zzy = zzy();
        int i = this.zzg;
        int i2 = this.zzi;
        if (zzy <= i - i2 && zzy > 0) {
            zzaip zza = zzaip.zza(this.zzf, i2, zzy);
            this.zzi += zzy;
            return zza;
        } else if (zzy == 0) {
            return zzaip.zza;
        } else {
            if (zzy >= 0) {
                byte[] zzj2 = zzj(zzy);
                if (zzj2 != null) {
                    return zzaip.zza(zzj2);
                }
                int i3 = this.zzi;
                int i4 = this.zzg;
                int i5 = i4 - i3;
                this.zzk += i4;
                this.zzi = 0;
                this.zzg = 0;
                List<byte[]> zzf2 = zzf(zzy - i5);
                byte[] bArr = new byte[zzy];
                System.arraycopy(this.zzf, i3, bArr, 0, i5);
                for (byte[] next : zzf2) {
                    System.arraycopy(next, 0, bArr, i5, next.length);
                    i5 += next.length;
                }
                return zzaip.zzb(bArr);
            }
            throw zzakf.zzf();
        }
    }

    public final String zzr() throws IOException {
        int zzy = zzy();
        if (zzy > 0 && zzy <= this.zzg - this.zzi) {
            String str = new String(this.zzf, this.zzi, zzy, zzakb.zza);
            this.zzi += zzy;
            return str;
        } else if (zzy == 0) {
            return "";
        } else {
            if (zzy < 0) {
                throw zzakf.zzf();
            } else if (zzy > this.zzg) {
                return new String(zza(zzy, false), zzakb.zza);
            } else {
                zzg(zzy);
                String str2 = new String(this.zzf, this.zzi, zzy, zzakb.zza);
                this.zzi += zzy;
                return str2;
            }
        }
    }

    public final String zzs() throws IOException {
        byte[] bArr;
        int zzy = zzy();
        int i = this.zzi;
        int i2 = this.zzg;
        if (zzy <= i2 - i && zzy > 0) {
            bArr = this.zzf;
            this.zzi = i + zzy;
        } else if (zzy == 0) {
            return "";
        } else {
            if (zzy >= 0) {
                i = 0;
                if (zzy <= i2) {
                    zzg(zzy);
                    bArr = this.zzf;
                    this.zzi = zzy;
                } else {
                    bArr = zza(zzy, false);
                }
            } else {
                throw zzakf.zzf();
            }
        }
        return zzamt.zzb(bArr, i, zzy);
    }

    private final List<byte[]> zzf(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int min = Math.min(i, 4096);
            byte[] bArr = new byte[min];
            int i2 = 0;
            while (i2 < min) {
                int read = this.zze.read(bArr, i2, min - i2);
                if (read != -1) {
                    this.zzk += read;
                    i2 += read;
                } else {
                    throw zzakf.zzj();
                }
            }
            i -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private zzajd(InputStream inputStream, int i) {
        super();
        this.zzl = Integer.MAX_VALUE;
        zzakb.zza(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    public final void zzb(int i) throws zzakf {
        if (this.zzj != i) {
            throw zzakf.zzb();
        }
    }

    public final void zzc(int i) {
        this.zzl = i;
        zzab();
    }

    private final void zzab() {
        int i = this.zzg + this.zzh;
        this.zzg = i;
        int i2 = this.zzk + i;
        int i3 = this.zzl;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzh = i4;
            this.zzg = i - i4;
            return;
        }
        this.zzh = 0;
    }

    private final void zzg(int i) throws IOException {
        if (zzi(i)) {
            return;
        }
        if (i > (this.zzc - this.zzk) - this.zzi) {
            throw zzakf.zzi();
        }
        throw zzakf.zzj();
    }

    private final void zzh(int i) throws IOException {
        int i2 = this.zzg;
        int i3 = this.zzi;
        if (i <= i2 - i3 && i >= 0) {
            this.zzi = i3 + i;
        } else if (i >= 0) {
            int i4 = this.zzk;
            int i5 = i4 + i3 + i;
            int i6 = this.zzl;
            if (i5 <= i6) {
                this.zzk = i4 + i3;
                int i7 = i2 - i3;
                this.zzg = 0;
                this.zzi = 0;
                while (i7 < i) {
                    try {
                        long j = (long) (i - i7);
                        long zza = zza(this.zze, j);
                        int i8 = (zza > 0 ? 1 : (zza == 0 ? 0 : -1));
                        if (i8 >= 0 && zza <= j) {
                            if (i8 == 0) {
                                break;
                            }
                            i7 += (int) zza;
                        } else {
                            throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + zza + "\nThe InputStream implementation is buggy.");
                        }
                    } catch (Throwable th) {
                        this.zzk += i7;
                        zzab();
                        throw th;
                    }
                }
                this.zzk += i7;
                zzab();
                if (i7 < i) {
                    int i9 = this.zzg;
                    int i10 = i9 - this.zzi;
                    this.zzi = i9;
                    zzg(1);
                    while (true) {
                        int i11 = i - i10;
                        int i12 = this.zzg;
                        if (i11 > i12) {
                            i10 += i12;
                            this.zzi = i12;
                            zzg(1);
                        } else {
                            this.zzi = i11;
                            return;
                        }
                    }
                }
            } else {
                zzh((i6 - i4) - i3);
                throw zzakf.zzj();
            }
        } else {
            throw zzakf.zzf();
        }
    }

    public final boolean zzt() throws IOException {
        return this.zzi == this.zzg && !zzi(1);
    }

    public final boolean zzu() throws IOException {
        return zzaa() != 0;
    }

    public final boolean zzd(int i) throws IOException {
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzg - this.zzi >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzf;
                    int i4 = this.zzi;
                    this.zzi = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzakf.zze();
            }
            while (i3 < 10) {
                if (zzw() < 0) {
                    i3++;
                }
            }
            throw zzakf.zze();
            return true;
        } else if (i2 == 1) {
            zzh(8);
            return true;
        } else if (i2 == 2) {
            zzh(zzy());
            return true;
        } else if (i2 == 3) {
            zzv();
            zzb(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzh(4);
                return true;
            }
            throw zzakf.zza();
        }
    }

    private final boolean zzi(int i) throws IOException {
        while (this.zzi + i > this.zzg) {
            int i2 = this.zzc;
            int i3 = this.zzk;
            int i4 = this.zzi;
            if (i > (i2 - i3) - i4 || i3 + i4 + i > this.zzl) {
                return false;
            }
            if (i4 > 0) {
                int i5 = this.zzg;
                if (i5 > i4) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, i4, bArr, 0, i5 - i4);
                }
                this.zzk += i4;
                this.zzg -= i4;
                this.zzi = 0;
            }
            InputStream inputStream = this.zze;
            byte[] bArr2 = this.zzf;
            int i6 = this.zzg;
            int zza = zza(inputStream, bArr2, i6, Math.min(bArr2.length - i6, (this.zzc - this.zzk) - this.zzg));
            if (zza == 0 || zza < -1 || zza > this.zzf.length) {
                throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + zza + "\nThe InputStream implementation is buggy.");
            } else if (zza <= 0) {
                return false;
            } else {
                this.zzg += zza;
                zzab();
                if (this.zzg >= i) {
                    return true;
                }
            }
        }
        throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
    }

    private final byte[] zza(int i, boolean z) throws IOException {
        byte[] zzj2 = zzj(i);
        if (zzj2 != null) {
            return zzj2;
        }
        int i2 = this.zzi;
        int i3 = this.zzg;
        int i4 = i3 - i2;
        this.zzk += i3;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzf2 = zzf(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, i4);
        for (byte[] next : zzf2) {
            System.arraycopy(next, 0, bArr, i4, next.length);
            i4 += next.length;
        }
        return bArr;
    }

    private final byte[] zzj(int i) throws IOException {
        if (i == 0) {
            return zzakb.zzb;
        }
        if (i >= 0) {
            int i2 = this.zzk + this.zzi + i;
            if (i2 - this.zzc <= 0) {
                int i3 = this.zzl;
                if (i2 <= i3) {
                    int i4 = this.zzg - this.zzi;
                    int i5 = i - i4;
                    if (i5 >= 4096 && i5 > zza(this.zze)) {
                        return null;
                    }
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.zzf, this.zzi, bArr, 0, i4);
                    this.zzk += this.zzg;
                    this.zzi = 0;
                    this.zzg = 0;
                    while (i4 < i) {
                        int zza = zza(this.zze, bArr, i4, i - i4);
                        if (zza != -1) {
                            this.zzk += zza;
                            i4 += zza;
                        } else {
                            throw zzakf.zzj();
                        }
                    }
                    return bArr;
                }
                zzh((i3 - this.zzk) - this.zzi);
                throw zzakf.zzj();
            }
            throw zzakf.zzi();
        }
        throw zzakf.zzf();
    }
}
