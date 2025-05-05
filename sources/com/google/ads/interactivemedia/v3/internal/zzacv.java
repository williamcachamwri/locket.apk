package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
class zzacv extends zzacu {
    protected final byte[] zza;

    zzacv(byte[] bArr) {
        super((zzact) null);
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacw) || zzd() != ((zzacw) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzacv)) {
            return obj.equals(this);
        }
        zzacv zzacv = (zzacv) obj;
        int zzn = zzn();
        int zzn2 = zzacv.zzn();
        if (zzn == 0 || zzn2 == 0 || zzn == zzn2) {
            return zzg(zzacv, 0, zzd());
        }
        return false;
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
        System.arraycopy(this.zza, i, bArr, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg(zzacw zzacw, int i, int i2) {
        if (i2 <= zzacw.zzd()) {
            int i3 = i + i2;
            if (i3 > zzacw.zzd()) {
                int zzd = zzacw.zzd();
                throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + zzd);
            } else if (!(zzacw instanceof zzacv)) {
                return zzacw.zzj(i, i3).equals(zzj(0, i2));
            } else {
                zzacv zzacv = (zzacv) zzacw;
                byte[] bArr = this.zza;
                byte[] bArr2 = zzacv.zza;
                int zzc = zzc() + i2;
                int zzc2 = zzc();
                int zzc3 = zzacv.zzc() + i;
                while (zzc2 < zzc) {
                    if (bArr[zzc2] != bArr2[zzc3]) {
                        return false;
                    }
                    zzc2++;
                    zzc3++;
                }
                return true;
            }
        } else {
            int zzd2 = zzd();
            throw new IllegalArgumentException("Length too large: " + i2 + zzd2);
        }
    }

    /* access modifiers changed from: protected */
    public final int zzi(int i, int i2, int i3) {
        return zzaee.zzb(i, this.zza, zzc() + i2, i3);
    }

    public final zzacw zzj(int i, int i2) {
        int zzm = zzm(i, i2, zzd());
        if (zzm == 0) {
            return zzacw.zzb;
        }
        return new zzacr(this.zza, zzc() + i, zzm);
    }

    public final zzada zzk() {
        return zzada.zzE(this.zza, zzc(), zzd(), true);
    }

    /* access modifiers changed from: package-private */
    public final void zzl(zzaco zzaco) throws IOException {
        ((zzadc) zzaco).zzc(this.zza, zzc(), zzd());
    }
}
