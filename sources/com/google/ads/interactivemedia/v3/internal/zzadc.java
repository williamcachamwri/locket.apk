package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadc extends zzadf {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    zzadc(byte[] bArr, int i, int i2) {
        super((zzade) null);
        int length = bArr.length;
        if (((length - i2) | i2) >= 0) {
            this.zzb = bArr;
            this.zzd = 0;
            this.zzc = i2;
            return;
        }
        throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(length), 0, Integer.valueOf(i2)}));
    }

    public final int zza() {
        return this.zzc - this.zzd;
    }

    public final void zzd(int i, boolean z) throws IOException {
        zzt(i << 3);
        zzb(z ? (byte) 1 : 0);
    }

    public final void zze(int i, zzacw zzacw) throws IOException {
        zzt((i << 3) | 2);
        zzt(zzacw.zzd());
        zzacw.zzl(this);
    }

    public final void zzf(int i, int i2) throws IOException {
        zzt((i << 3) | 5);
        zzg(i2);
    }

    public final void zzg(int i) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            int i3 = i2 + 1;
            bArr[i2] = (byte) (i & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((i >> 8) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((i >> 16) & 255);
            this.zzd = i5 + 1;
            bArr[i5] = (byte) ((i >> 24) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
        }
    }

    public final void zzh(int i, long j) throws IOException {
        zzt((i << 3) | 1);
        zzi(j);
    }

    public final void zzi(long j) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            int i2 = i + 1;
            bArr[i] = (byte) (((int) j) & 255);
            int i3 = i2 + 1;
            bArr[i2] = (byte) (((int) (j >> 8)) & 255);
            int i4 = i3 + 1;
            bArr[i3] = (byte) (((int) (j >> 16)) & 255);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (((int) (j >> 24)) & 255);
            int i6 = i5 + 1;
            bArr[i5] = (byte) (((int) (j >> 32)) & 255);
            int i7 = i6 + 1;
            bArr[i6] = (byte) (((int) (j >> 40)) & 255);
            int i8 = i7 + 1;
            bArr[i7] = (byte) (((int) (j >> 48)) & 255);
            this.zzd = i8 + 1;
            bArr[i8] = (byte) (((int) (j >> 56)) & 255);
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
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
    public final void zzm(int i, zzafb zzafb, zzaft zzaft) throws IOException {
        zzt((i << 3) | 2);
        zzt(((zzach) zzafb).zzat(zzaft));
        zzaft.zzj(zzafb, this.zza);
    }

    public final void zzn(int i, zzafb zzafb) throws IOException {
        zzt(11);
        zzs(2, i);
        zzt(26);
        zzt(zzafb.zzax());
        zzafb.zzaR(this);
        zzt(12);
    }

    public final void zzo(int i, zzacw zzacw) throws IOException {
        zzt(11);
        zzs(2, i);
        zze(3, zzacw);
        zzt(12);
    }

    public final void zzp(int i, String str) throws IOException {
        zzt((i << 3) | 2);
        zzq(str);
    }

    public final void zzq(String str) throws IOException {
        int i = this.zzd;
        try {
            int zzz = zzz(str.length() * 3);
            int zzz2 = zzz(str.length());
            if (zzz2 == zzz) {
                int i2 = i + zzz2;
                this.zzd = i2;
                int zzb2 = zzagr.zzb(str, this.zzb, i2, this.zzc - i2);
                this.zzd = i;
                zzt((zzb2 - i) - zzz2);
                this.zzd = zzb2;
                return;
            }
            zzt(zzagr.zzc(str));
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            this.zzd = zzagr.zzb(str, bArr, i3, this.zzc - i3);
        } catch (zzagq e) {
            this.zzd = i;
            zzC(str, e);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzadd(e2);
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

    public final void zzb(byte b) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            this.zzd = i + 1;
            bArr[i] = b;
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
        }
    }

    public final void zzc(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, i, this.zzb, this.zzd, i2);
            this.zzd += i2;
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i2)}), e);
        }
    }

    public final void zzt(int i) throws IOException {
        while ((i & -128) != 0) {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            this.zzd = i2 + 1;
            bArr[i2] = (byte) ((i | 128) & 255);
            i >>>= 7;
        }
        try {
            byte[] bArr2 = this.zzb;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            bArr2[i3] = (byte) i;
        } catch (IndexOutOfBoundsException e) {
            throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
        }
    }

    public final void zzv(long j) throws IOException {
        if (!zzadf.zzc || this.zzc - this.zzd < 10) {
            while ((j & -128) != 0) {
                byte[] bArr = this.zzb;
                int i = this.zzd;
                this.zzd = i + 1;
                bArr[i] = (byte) ((((int) j) | 128) & 255);
                j >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
            } catch (IndexOutOfBoundsException e) {
                throw new zzadd(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1}), e);
            }
        } else {
            while (true) {
                int i3 = (int) j;
                if ((j & -128) == 0) {
                    byte[] bArr3 = this.zzb;
                    int i4 = this.zzd;
                    this.zzd = i4 + 1;
                    zzago.zzn(bArr3, (long) i4, (byte) i3);
                    return;
                }
                byte[] bArr4 = this.zzb;
                int i5 = this.zzd;
                this.zzd = i5 + 1;
                zzago.zzn(bArr4, (long) i5, (byte) ((i3 | 128) & 255));
                j >>>= 7;
            }
        }
    }
}
