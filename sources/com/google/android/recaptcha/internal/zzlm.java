package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Locale;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzlm extends zzlp {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    zzlm(byte[] bArr, int i, int i2) {
        super((zzlo) null);
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.zzb = bArr;
            this.zzd = 0;
            this.zzc = i2;
            return;
        }
        throw new IllegalArgumentException(String.format(Locale.US, "Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i2)}));
    }

    public final int zza() {
        return this.zzc - this.zzd;
    }

    public final void zzb(byte b) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i = this.zzd;
        try {
            int i2 = i + 1;
            try {
                this.zzb[i] = b;
                this.zzd = i2;
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBoundsException = e;
                i = i2;
                throw new zzln((long) i, (long) this.zzc, 1, indexOutOfBoundsException);
            }
        } catch (IndexOutOfBoundsException e2) {
            indexOutOfBoundsException = e2;
            throw new zzln((long) i, (long) this.zzc, 1, indexOutOfBoundsException);
        }
    }

    public final void zzc(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzb, this.zzd, i2);
            this.zzd += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzln((long) this.zzd, (long) this.zzc, i2, e);
        }
    }

    public final void zzd(int i, boolean z) throws IOException {
        zzt(i << 3);
        zzb(z ? (byte) 1 : 0);
    }

    public final void zze(int i, zzlg zzlg) throws IOException {
        zzt((i << 3) | 2);
        zzt(zzlg.zzd());
        zzlg.zzi(this);
    }

    public final void zzf(int i, int i2) throws IOException {
        zzt((i << 3) | 5);
        zzg(i2);
    }

    public final void zzg(int i) throws IOException {
        int i2 = this.zzd;
        try {
            byte[] bArr = this.zzb;
            bArr[i2] = (byte) (i & 255);
            bArr[i2 + 1] = (byte) ((i >> 8) & 255);
            bArr[i2 + 2] = (byte) ((i >> 16) & 255);
            bArr[i2 + 3] = (byte) ((i >> 24) & 255);
            this.zzd = i2 + 4;
        } catch (IndexOutOfBoundsException e) {
            throw new zzln((long) i2, (long) this.zzc, 4, e);
        }
    }

    public final void zzh(int i, long j) throws IOException {
        zzt((i << 3) | 1);
        zzi(j);
    }

    public final void zzi(long j) throws IOException {
        int i = this.zzd;
        try {
            byte[] bArr = this.zzb;
            bArr[i] = (byte) (((int) j) & 255);
            bArr[i + 1] = (byte) (((int) (j >> 8)) & 255);
            bArr[i + 2] = (byte) (((int) (j >> 16)) & 255);
            bArr[i + 3] = (byte) (((int) (j >> 24)) & 255);
            bArr[i + 4] = (byte) (((int) (j >> 32)) & 255);
            bArr[i + 5] = (byte) (((int) (j >> 40)) & 255);
            bArr[i + 6] = (byte) (((int) (j >> 48)) & 255);
            bArr[i + 7] = (byte) (((int) (j >> 56)) & 255);
            this.zzd = i + 8;
        } catch (IndexOutOfBoundsException e) {
            throw new zzln((long) i, (long) this.zzc, 8, e);
        }
    }

    public final void zzj(int i, int i2) throws IOException {
        zzt(i << 3);
        zzk(i2);
    }

    public final void zzk(int i) throws IOException {
        if (i >= 0) {
            zzt(i);
        } else {
            zzv((long) i);
        }
    }

    public final void zzl(byte[] bArr, int i, int i2) throws IOException {
        zzc(bArr, 0, i2);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(int i, zzok zzok, zzoy zzoy) throws IOException {
        zzt((i << 3) | 2);
        zzt(((zzkq) zzok).zza(zzoy));
        zzoy.zzj(zzok, this.zza);
    }

    public final void zzn(int i, zzok zzok) throws IOException {
        zzt(11);
        zzs(2, i);
        zzt(26);
        zzt(zzok.zzo());
        zzok.zze(this);
        zzt(12);
    }

    public final void zzo(int i, zzlg zzlg) throws IOException {
        zzt(11);
        zzs(2, i);
        zze(3, zzlg);
        zzt(12);
    }

    public final void zzp(int i, String str) throws IOException {
        zzt((i << 3) | 2);
        zzq(str);
    }

    public final void zzq(String str) throws IOException {
        int i = this.zzd;
        try {
            int zzA = zzA(str.length() * 3);
            int zzA2 = zzA(str.length());
            if (zzA2 == zzA) {
                int i2 = i + zzA2;
                this.zzd = i2;
                int zzb2 = zzpx.zzb(str, this.zzb, i2, this.zzc - i2);
                this.zzd = i;
                zzt((zzb2 - i) - zzA2);
                this.zzd = zzb2;
                return;
            }
            zzt(zzpx.zzc(str));
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            this.zzd = zzpx.zzb(str, bArr, i3, this.zzc - i3);
        } catch (zzpw e) {
            this.zzd = i;
            zzD(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzln(e2);
        }
    }

    public final void zzr(int i, int i2) throws IOException {
        zzt((i << 3) | i2);
    }

    public final void zzs(int i, int i2) throws IOException {
        zzt(i << 3);
        zzt(i2);
    }

    public final void zzu(int i, long j) throws IOException {
        zzt(i << 3);
        zzv(j);
    }

    public final void zzt(int i) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i2;
        int i3 = this.zzd;
        while ((i & -128) != 0) {
            i2 = i3 + 1;
            this.zzb[i3] = (byte) ((i | 128) & 255);
            i >>>= 7;
            i3 = i2;
        }
        try {
            i2 = i3 + 1;
            try {
                this.zzb[i3] = (byte) i;
                this.zzd = i2;
            } catch (IndexOutOfBoundsException e) {
                indexOutOfBoundsException = e;
                i3 = i2;
                throw new zzln((long) i3, (long) this.zzc, 1, indexOutOfBoundsException);
            }
        } catch (IndexOutOfBoundsException e2) {
            indexOutOfBoundsException = e2;
            throw new zzln((long) i3, (long) this.zzc, 1, indexOutOfBoundsException);
        }
    }

    public final void zzv(long j) throws IOException {
        IndexOutOfBoundsException indexOutOfBoundsException;
        int i;
        int i2 = this.zzd;
        if (!zzlp.zzc || this.zzc - i2 < 10) {
            while ((j & -128) != 0) {
                int i3 = i2 + 1;
                try {
                    this.zzb[i2] = (byte) ((((int) j) | 128) & 255);
                    j >>>= 7;
                    i2 = i3;
                } catch (IndexOutOfBoundsException e) {
                    e = e;
                    i2 = i3;
                    indexOutOfBoundsException = e;
                    throw new zzln((long) i2, (long) this.zzc, 1, indexOutOfBoundsException);
                }
            }
            try {
                i = i2 + 1;
                try {
                    this.zzb[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e2) {
                    indexOutOfBoundsException = e2;
                    i2 = i;
                    throw new zzln((long) i2, (long) this.zzc, 1, indexOutOfBoundsException);
                }
            } catch (IndexOutOfBoundsException e3) {
                e = e3;
                indexOutOfBoundsException = e;
                throw new zzln((long) i2, (long) this.zzc, 1, indexOutOfBoundsException);
            }
        } else {
            while ((j & -128) != 0) {
                zzpu.zzn(this.zzb, (long) i2, (byte) ((((int) j) | 128) & 255));
                j >>>= 7;
                i2++;
            }
            i = i2 + 1;
            zzpu.zzn(this.zzb, (long) i2, (byte) ((int) j));
        }
        this.zzd = i;
    }
}
