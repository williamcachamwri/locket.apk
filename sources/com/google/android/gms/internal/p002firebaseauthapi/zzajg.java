package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzajg extends zzaim {
    private static final Logger zza = Logger.getLogger(zzajg.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzb = zzamp.zzc();
    zzajj zze;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajg$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    private static abstract class zza extends zzajg {
        final byte[] zza;
        final int zzb;
        int zzc;
        int zzd;

        public final int zza() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        zza(int i) {
            super();
            if (i >= 0) {
                byte[] bArr = new byte[Math.max(i, 20)];
                this.zza = bArr;
                this.zzb = bArr.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        /* access modifiers changed from: package-private */
        public final void zza(byte b) {
            byte[] bArr = this.zza;
            int i = this.zzc;
            this.zzc = i + 1;
            bArr[i] = b;
            this.zzd++;
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i) {
            byte[] bArr = this.zza;
            int i2 = this.zzc;
            int i3 = i2 + 1;
            bArr[i2] = (byte) i;
            int i4 = i3 + 1;
            bArr[i3] = (byte) (i >> 8);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (i >> 16);
            this.zzc = i5 + 1;
            bArr[i5] = (byte) (i >>> 24);
            this.zzd += 4;
        }

        /* access modifiers changed from: package-private */
        public final void zza(long j) {
            byte[] bArr = this.zza;
            int i = this.zzc;
            int i2 = i + 1;
            bArr[i] = (byte) ((int) (j & 255));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((int) ((j >> 8) & 255));
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((int) ((j >> 16) & 255));
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((int) (255 & (j >> 24)));
            int i6 = i5 + 1;
            bArr[i5] = (byte) ((int) (j >> 32));
            int i7 = i6 + 1;
            bArr[i6] = (byte) ((int) (j >> 40));
            int i8 = i7 + 1;
            bArr[i7] = (byte) ((int) (j >> 48));
            this.zzc = i8 + 1;
            bArr[i8] = (byte) ((int) (j >> 56));
            this.zzd += 8;
        }

        /* access modifiers changed from: package-private */
        public final void zza(int i, int i2) {
            zzb((i << 3) | i2);
        }

        /* access modifiers changed from: package-private */
        public final void zzb(int i) {
            if (zzajg.zzb) {
                long j = (long) this.zzc;
                while ((i & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i2 = this.zzc;
                    this.zzc = i2 + 1;
                    zzamp.zza(bArr, (long) i2, (byte) (i | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                zzamp.zza(bArr2, (long) i3, (byte) i);
                this.zzd += (int) (((long) this.zzc) - j);
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i4 = this.zzc;
                this.zzc = i4 + 1;
                bArr3[i4] = (byte) (i | 128);
                this.zzd++;
                i >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i5 = this.zzc;
            this.zzc = i5 + 1;
            bArr4[i5] = (byte) i;
            this.zzd++;
        }

        /* access modifiers changed from: package-private */
        public final void zzb(long j) {
            if (zzajg.zzb) {
                long j2 = (long) this.zzc;
                while ((j & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i = this.zzc;
                    this.zzc = i + 1;
                    zzamp.zza(bArr, (long) i, (byte) (((int) j) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zza;
                int i2 = this.zzc;
                this.zzc = i2 + 1;
                zzamp.zza(bArr2, (long) i2, (byte) ((int) j));
                this.zzd += (int) (((long) this.zzc) - j2);
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                bArr3[i3] = (byte) (((int) j) | 128);
                this.zzd++;
                j >>>= 7;
            }
            byte[] bArr4 = this.zza;
            int i4 = this.zzc;
            this.zzc = i4 + 1;
            bArr4[i4] = (byte) ((int) j);
            this.zzd++;
        }
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajg$zzc */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    private static class zzc extends zzajg {
        private final byte[] zza;
        private final int zzb;
        private int zzc;

        public final int zza() {
            return this.zzb - this.zzc;
        }

        public final void zzc() {
        }

        zzc(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if ((i2 | 0 | (bArr.length - i2)) >= 0) {
                this.zza = bArr;
                this.zzc = 0;
                this.zzb = i2;
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
        }

        public final void zzb(byte b) throws IOException {
            int i = this.zzc;
            try {
                int i2 = i + 1;
                try {
                    this.zza[i] = b;
                    this.zzc = i2;
                } catch (IndexOutOfBoundsException e) {
                    e = e;
                    i = i2;
                    throw new zzb(i, this.zzb, 1, (Throwable) e);
                }
            } catch (IndexOutOfBoundsException e2) {
                e = e2;
                throw new zzb(i, this.zzb, 1, (Throwable) e);
            }
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zza, this.zzc, i2);
                this.zzc += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(this.zzc, this.zzb, i2, (Throwable) e);
            }
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzk(i, 0);
            zzb(z ? (byte) 1 : 0);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzn(i2);
            zzc(bArr, 0, i2);
        }

        public final void zzc(int i, zzaip zzaip) throws IOException {
            zzk(i, 2);
            zzb(zzaip);
        }

        public final void zzb(zzaip zzaip) throws IOException {
            zzn(zzaip.zzb());
            zzaip.zza((zzaim) this);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzk(i, 5);
            zzk(i2);
        }

        public final void zzk(int i) throws IOException {
            int i2 = this.zzc;
            try {
                byte[] bArr = this.zza;
                bArr[i2] = (byte) i;
                bArr[i2 + 1] = (byte) (i >> 8);
                bArr[i2 + 2] = (byte) (i >> 16);
                bArr[i2 + 3] = (byte) (i >>> 24);
                this.zzc = i2 + 4;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(i2, this.zzb, 4, (Throwable) e);
            }
        }

        public final void zzf(int i, long j) throws IOException {
            zzk(i, 1);
            zzh(j);
        }

        public final void zzh(long j) throws IOException {
            int i = this.zzc;
            try {
                byte[] bArr = this.zza;
                bArr[i] = (byte) ((int) j);
                bArr[i + 1] = (byte) ((int) (j >> 8));
                bArr[i + 2] = (byte) ((int) (j >> 16));
                bArr[i + 3] = (byte) ((int) (j >> 24));
                bArr[i + 4] = (byte) ((int) (j >> 32));
                bArr[i + 5] = (byte) ((int) (j >> 40));
                bArr[i + 6] = (byte) ((int) (j >> 48));
                bArr[i + 7] = (byte) ((int) (j >> 56));
                this.zzc = i + 8;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(i, this.zzb, 8, (Throwable) e);
            }
        }

        public final void zzi(int i, int i2) throws IOException {
            zzk(i, 0);
            zzl(i2);
        }

        public final void zzl(int i) throws IOException {
            if (i >= 0) {
                zzn(i);
            } else {
                zzj((long) i);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zzc(int i, zzalc zzalc, zzalv zzalv) throws IOException {
            zzk(i, 2);
            zzn(((zzaif) zzalc).zza(zzalv));
            zzalv.zza(zzalc, (zzanf) this.zze);
        }

        public final void zzc(zzalc zzalc) throws IOException {
            zzn(zzalc.zzl());
            zzalc.zza(this);
        }

        /* access modifiers changed from: package-private */
        public final void zzb(zzalc zzalc, zzalv zzalv) throws IOException {
            zzn(((zzaif) zzalc).zza(zzalv));
            zzalv.zza(zzalc, (zzanf) this.zze);
        }

        public final void zzb(int i, zzalc zzalc) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzk(3, 2);
            zzc(zzalc);
            zzk(1, 4);
        }

        public final void zzd(int i, zzaip zzaip) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzc(3, zzaip);
            zzk(1, 4);
        }

        public final void zzb(int i, String str) throws IOException {
            zzk(i, 2);
            zzb(str);
        }

        public final void zzb(String str) throws IOException {
            int i = this.zzc;
            try {
                int zzj = zzj(str.length() * 3);
                int zzj2 = zzj(str.length());
                if (zzj2 == zzj) {
                    int i2 = i + zzj2;
                    this.zzc = i2;
                    int zza2 = zzamt.zza(str, this.zza, i2, zza());
                    this.zzc = i;
                    zzn((zza2 - i) - zzj2);
                    this.zzc = zza2;
                    return;
                }
                zzn(zzamt.zza(str));
                this.zzc = zzamt.zza(str, this.zza, this.zzc, zza());
            } catch (zzamx e) {
                this.zzc = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final void zzk(int i, int i2) throws IOException {
            zzn((i << 3) | i2);
        }

        public final void zzl(int i, int i2) throws IOException {
            zzk(i, 0);
            zzn(i2);
        }

        public final void zzn(int i) throws IOException {
            while ((i & -128) != 0) {
                byte[] bArr = this.zza;
                int i2 = this.zzc;
                this.zzc = i2 + 1;
                bArr[i2] = (byte) (i | 128);
                i >>>= 7;
            }
            try {
                byte[] bArr2 = this.zza;
                int i3 = this.zzc;
                this.zzc = i3 + 1;
                bArr2[i3] = (byte) i;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(this.zzc, this.zzb, 1, (Throwable) e);
            }
        }

        public final void zzh(int i, long j) throws IOException {
            zzk(i, 0);
            zzj(j);
        }

        public final void zzj(long j) throws IOException {
            if (!zzajg.zzb || zza() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.zza;
                    int i = this.zzc;
                    this.zzc = i + 1;
                    bArr[i] = (byte) (((int) j) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.zza;
                    int i2 = this.zzc;
                    this.zzc = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(this.zzc, this.zzb, 1, (Throwable) e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.zza;
                    int i3 = this.zzc;
                    this.zzc = i3 + 1;
                    zzamp.zza(bArr3, (long) i3, (byte) (((int) j) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.zza;
                int i4 = this.zzc;
                this.zzc = i4 + 1;
                zzamp.zza(bArr4, (long) i4, (byte) ((int) j));
            }
        }
    }

    public static int zza(double d) {
        return 8;
    }

    public static int zza(float f) {
        return 4;
    }

    private static int zza(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static int zza(boolean z) {
        return 1;
    }

    private static long zza(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzc(long j) {
        return 8;
    }

    public static int zzd(int i) {
        return 4;
    }

    public static int zze(long j) {
        return 8;
    }

    static int zzf(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int zzg(int i) {
        return 4;
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzb(int i, zzalc zzalc) throws IOException;

    public abstract void zzb(int i, String str) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(zzaip zzaip) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(zzalc zzalc, zzalv zzalv) throws IOException;

    public abstract void zzb(String str) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzb(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzc() throws IOException;

    public abstract void zzc(int i, zzaip zzaip) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zzc(int i, zzalc zzalc, zzalv zzalv) throws IOException;

    public abstract void zzc(zzalc zzalc) throws IOException;

    public abstract void zzd(int i, zzaip zzaip) throws IOException;

    public abstract void zzf(int i, long j) throws IOException;

    public abstract void zzh(int i, int i2) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzh(long j) throws IOException;

    public abstract void zzi(int i, int i2) throws IOException;

    public abstract void zzj(long j) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzk(int i, int i2) throws IOException;

    public abstract void zzl(int i) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzn(int i) throws IOException;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajg$zzb */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        private zzb(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: " + str, th);
        }

        zzb(int i, int i2, int i3, Throwable th) {
            this((long) i, (long) i2, i3, th);
        }

        private zzb(long j, long j2, int i, Throwable th) {
            this(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)}), th);
        }
    }

    public static int zza(int i, boolean z) {
        return zzj(i << 3) + 1;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajg$zzd */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    private static final class zzd extends zza {
        private final OutputStream zzf;

        zzd(OutputStream outputStream, int i) {
            super(i);
            if (outputStream != null) {
                this.zzf = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }

        private final void zze() throws IOException {
            this.zzf.write(this.zza, 0, this.zzc);
            this.zzc = 0;
        }

        public final void zzc() throws IOException {
            if (this.zzc > 0) {
                zze();
            }
        }

        private final void zzo(int i) throws IOException {
            if (this.zzb - this.zzc < i) {
                zze();
            }
        }

        public final void zzb(byte b) throws IOException {
            if (this.zzc == this.zzb) {
                zze();
            }
            zza(b);
        }

        private final void zzc(byte[] bArr, int i, int i2) throws IOException {
            if (this.zzb - this.zzc >= i2) {
                System.arraycopy(bArr, i, this.zza, this.zzc, i2);
                this.zzc += i2;
            } else {
                int i3 = this.zzb - this.zzc;
                System.arraycopy(bArr, i, this.zza, this.zzc, i3);
                int i4 = i + i3;
                i2 -= i3;
                this.zzc = this.zzb;
                this.zzd += i3;
                zze();
                if (i2 <= this.zzb) {
                    System.arraycopy(bArr, i4, this.zza, 0, i2);
                    this.zzc = i2;
                } else {
                    this.zzf.write(bArr, i4, i2);
                }
            }
            this.zzd += i2;
        }

        public final void zzb(int i, boolean z) throws IOException {
            zzo(11);
            zza(i, 0);
            zza(z ? (byte) 1 : 0);
        }

        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            zzn(i2);
            zzc(bArr, 0, i2);
        }

        public final void zzc(int i, zzaip zzaip) throws IOException {
            zzk(i, 2);
            zzb(zzaip);
        }

        public final void zzb(zzaip zzaip) throws IOException {
            zzn(zzaip.zzb());
            zzaip.zza((zzaim) this);
        }

        public final void zzh(int i, int i2) throws IOException {
            zzo(14);
            zza(i, 5);
            zza(i2);
        }

        public final void zzk(int i) throws IOException {
            zzo(4);
            zza(i);
        }

        public final void zzf(int i, long j) throws IOException {
            zzo(18);
            zza(i, 1);
            zza(j);
        }

        public final void zzh(long j) throws IOException {
            zzo(8);
            zza(j);
        }

        public final void zzi(int i, int i2) throws IOException {
            zzo(20);
            zza(i, 0);
            if (i2 >= 0) {
                zzb(i2);
            } else {
                zzb((long) i2);
            }
        }

        public final void zzl(int i) throws IOException {
            if (i >= 0) {
                zzn(i);
            } else {
                zzj((long) i);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        /* access modifiers changed from: package-private */
        public final void zzc(int i, zzalc zzalc, zzalv zzalv) throws IOException {
            zzk(i, 2);
            zzb(zzalc, zzalv);
        }

        public final void zzc(zzalc zzalc) throws IOException {
            zzn(zzalc.zzl());
            zzalc.zza(this);
        }

        /* access modifiers changed from: package-private */
        public final void zzb(zzalc zzalc, zzalv zzalv) throws IOException {
            zzn(((zzaif) zzalc).zza(zzalv));
            zzalv.zza(zzalc, (zzanf) this.zze);
        }

        public final void zzb(int i, zzalc zzalc) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzk(3, 2);
            zzc(zzalc);
            zzk(1, 4);
        }

        public final void zzd(int i, zzaip zzaip) throws IOException {
            zzk(1, 3);
            zzl(2, i);
            zzc(3, zzaip);
            zzk(1, 4);
        }

        public final void zzb(int i, String str) throws IOException {
            zzk(i, 2);
            zzb(str);
        }

        public final void zzb(String str) throws IOException {
            int i;
            int i2;
            try {
                int length = str.length() * 3;
                int zzj = zzj(length);
                int i3 = zzj + length;
                if (i3 > this.zzb) {
                    byte[] bArr = new byte[length];
                    int zza = zzamt.zza(str, bArr, 0, length);
                    zzn(zza);
                    zza(bArr, 0, zza);
                    return;
                }
                if (i3 > this.zzb - this.zzc) {
                    zze();
                }
                int zzj2 = zzj(str.length());
                i = this.zzc;
                if (zzj2 == zzj) {
                    this.zzc = i + zzj2;
                    int zza2 = zzamt.zza(str, this.zza, this.zzc, this.zzb - this.zzc);
                    this.zzc = i;
                    i2 = (zza2 - i) - zzj2;
                    zzb(i2);
                    this.zzc = zza2;
                } else {
                    i2 = zzamt.zza(str);
                    zzb(i2);
                    this.zzc = zzamt.zza(str, this.zza, this.zzc, i2);
                }
                this.zzd += i2;
            } catch (zzamx e) {
                this.zzd -= this.zzc - i;
                this.zzc = i;
                throw e;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new zzb(e2);
            } catch (zzamx e3) {
                zza(str, e3);
            }
        }

        public final void zzk(int i, int i2) throws IOException {
            zzn((i << 3) | i2);
        }

        public final void zzl(int i, int i2) throws IOException {
            zzo(20);
            zza(i, 0);
            zzb(i2);
        }

        public final void zzn(int i) throws IOException {
            zzo(5);
            zzb(i);
        }

        public final void zzh(int i, long j) throws IOException {
            zzo(20);
            zza(i, 0);
            zzb(j);
        }

        public final void zzj(long j) throws IOException {
            zzo(10);
            zzb(j);
        }
    }

    public static int zza(byte[] bArr) {
        int length = bArr.length;
        return zzj(length) + length;
    }

    public static int zza(int i, zzaip zzaip) {
        int zzj = zzj(i << 3);
        int zzb2 = zzaip.zzb();
        return zzj + zzj(zzb2) + zzb2;
    }

    public static int zza(zzaip zzaip) {
        int zzb2 = zzaip.zzb();
        return zzj(zzb2) + zzb2;
    }

    public static int zza(int i, double d) {
        return zzj(i << 3) + 8;
    }

    public static int zzb(int i, int i2) {
        return zzj(i << 3) + zzg((long) i2);
    }

    public static int zzc(int i) {
        return zzg((long) i);
    }

    public static int zzc(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zza(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zza(int i, float f) {
        return zzj(i << 3) + 4;
    }

    @Deprecated
    static int zza(int i, zzalc zzalc, zzalv zzalv) {
        return (zzj(i << 3) << 1) + ((zzaif) zzalc).zza(zzalv);
    }

    @Deprecated
    public static int zza(zzalc zzalc) {
        return zzalc.zzl();
    }

    public static int zzd(int i, int i2) {
        return zzj(i << 3) + zzg((long) i2);
    }

    public static int zze(int i) {
        return zzg((long) i);
    }

    public static int zzb(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzd(long j) {
        return zzg(j);
    }

    public static int zza(int i, zzakk zzakk) {
        return (zzj(8) << 1) + zzg(2, i) + zzb(3, zzakk);
    }

    public static int zzb(int i, zzakk zzakk) {
        int zzj = zzj(i << 3);
        int zza2 = zzakk.zza();
        return zzj + zzj(zza2) + zza2;
    }

    public static int zza(zzakk zzakk) {
        int zza2 = zzakk.zza();
        return zzj(zza2) + zza2;
    }

    public static int zza(int i, zzalc zzalc) {
        return (zzj(8) << 1) + zzg(2, i) + zzj(24) + zzb(zzalc);
    }

    static int zzb(int i, zzalc zzalc, zzalv zzalv) {
        return zzj(i << 3) + zza(zzalc, zzalv);
    }

    public static int zzb(zzalc zzalc) {
        int zzl = zzalc.zzl();
        return zzj(zzl) + zzl;
    }

    static int zza(zzalc zzalc, zzalv zzalv) {
        int zza2 = ((zzaif) zzalc).zza(zzalv);
        return zzj(zza2) + zza2;
    }

    public static int zzb(int i, zzaip zzaip) {
        return (zzj(8) << 1) + zzg(2, i) + zza(3, zzaip);
    }

    public static int zze(int i, int i2) {
        return zzj(i << 3) + 4;
    }

    public static int zzc(int i, long j) {
        return zzj(i << 3) + 8;
    }

    public static int zzf(int i, int i2) {
        return zzj(i << 3) + zzj(zza(i2));
    }

    public static int zzh(int i) {
        return zzj(zza(i));
    }

    public static int zzd(int i, long j) {
        return zzj(i << 3) + zzg(zza(j));
    }

    public static int zzf(long j) {
        return zzg(zza(j));
    }

    public static int zza(int i, String str) {
        return zzj(i << 3) + zza(str);
    }

    public static int zza(String str) {
        int i;
        try {
            i = zzamt.zza(str);
        } catch (zzamx unused) {
            i = str.getBytes(zzakb.zza).length;
        }
        return zzj(i) + i;
    }

    public static int zzi(int i) {
        return zzj(i << 3);
    }

    public static int zzg(int i, int i2) {
        return zzj(i << 3) + zzj(i2);
    }

    public static int zzj(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int zze(int i, long j) {
        return zzj(i << 3) + zzg(j);
    }

    public static int zzg(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static zzajg zzb(byte[] bArr) {
        return new zzc(bArr, 0, bArr.length);
    }

    public static zzajg zza(OutputStream outputStream, int i) {
        return new zzd(outputStream, i);
    }

    private zzajg() {
    }

    public final void zzb() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzamx zzamx) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzamx);
        byte[] bytes = str.getBytes(zzakb.zza);
        try {
            zzn(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        }
    }

    public final void zzb(boolean z) throws IOException {
        zzb(z ? (byte) 1 : 0);
    }

    public final void zzb(int i, double d) throws IOException {
        zzf(i, Double.doubleToRawLongBits(d));
    }

    public final void zzb(double d) throws IOException {
        zzh(Double.doubleToRawLongBits(d));
    }

    public final void zzb(int i, float f) throws IOException {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(float f) throws IOException {
        zzk(Float.floatToRawIntBits(f));
    }

    public final void zzj(int i, int i2) throws IOException {
        zzl(i, zza(i2));
    }

    public final void zzm(int i) throws IOException {
        zzn(zza(i));
    }

    public final void zzg(int i, long j) throws IOException {
        zzh(i, zza(j));
    }

    public final void zzi(long j) throws IOException {
        zzj(zza(j));
    }
}
