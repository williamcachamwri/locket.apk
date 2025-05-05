package com.google.android.gms.internal.fido;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
class zzcw extends zzcv {
    protected final byte[] zza;

    zzcw(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcz) || zzd() != ((zzcz) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzcw)) {
            return obj.equals(this);
        }
        zzcw zzcw = (zzcw) obj;
        int zzk = zzk();
        int zzk2 = zzcw.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzcw.zzd()) {
            int zzd2 = zzd();
            throw new IllegalArgumentException("Length too large: " + zzd + zzd2);
        } else if (zzd > zzcw.zzd()) {
            int zzd3 = zzcw.zzd();
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzd3);
        } else if (!(zzcw instanceof zzcw)) {
            return zzcw.zzg(0, zzd).equals(zzg(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzcw.zza;
            int zzc = zzc() + zzd;
            int zzc2 = zzc();
            int zzc3 = zzcw.zzc();
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        }
    }

    public byte zza(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zza[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zza.length;
    }

    /* access modifiers changed from: protected */
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzf(int i, int i2, int i3) {
        byte[] bArr = this.zza;
        int zzc = zzc();
        byte[] bArr2 = zzde.zzd;
        for (int i4 = zzc; i4 < zzc + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public final zzcz zzg(int i, int i2) {
        int zzj = zzj(i, i2, zzd());
        if (zzj == 0) {
            return zzcz.zzb;
        }
        return new zzct(this.zza, zzc() + i, zzj);
    }

    public final InputStream zzh() {
        return new ByteArrayInputStream(this.zza, zzc(), zzd());
    }

    public final ByteBuffer zzi() {
        return ByteBuffer.wrap(this.zza, zzc(), zzd()).asReadOnlyBuffer();
    }
}
