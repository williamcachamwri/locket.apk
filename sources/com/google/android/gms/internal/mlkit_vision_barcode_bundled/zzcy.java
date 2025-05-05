package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
class zzcy extends zzcx {
    protected final byte[] zza;

    zzcy(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdb) || zzd() != ((zzdb) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzcy)) {
            return obj.equals(this);
        }
        zzcy zzcy = (zzcy) obj;
        int zzp = zzp();
        int zzp2 = zzcy.zzp();
        if (zzp == 0 || zzp2 == 0 || zzp == zzp2) {
            return zzg(zzcy, 0, zzd());
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
    public final boolean zzg(zzdb zzdb, int i, int i2) {
        if (i2 <= zzdb.zzd()) {
            int i3 = i + i2;
            if (i3 > zzdb.zzd()) {
                int zzd = zzdb.zzd();
                throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + zzd);
            } else if (!(zzdb instanceof zzcy)) {
                return zzdb.zzk(i, i3).equals(zzk(0, i2));
            } else {
                zzcy zzcy = (zzcy) zzdb;
                byte[] bArr = this.zza;
                byte[] bArr2 = zzcy.zza;
                int zzc = zzc() + i2;
                int zzc2 = zzc();
                int zzc3 = zzcy.zzc() + i;
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
        return zzem.zzb(i, this.zza, zzc() + i2, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzj(int i, int i2, int i3) {
        int zzc = zzc() + i2;
        return zzhn.zzf(i, this.zza, zzc, i3 + zzc);
    }

    public final zzdb zzk(int i, int i2) {
        int zzo = zzo(i, i2, zzd());
        if (zzo == 0) {
            return zzdb.zzb;
        }
        return new zzcv(this.zza, zzc() + i, zzo);
    }

    /* access modifiers changed from: protected */
    public final String zzl(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(zzcr zzcr) throws IOException {
        ((zzdg) zzcr).zzc(this.zza, zzc(), zzd());
    }

    public final boolean zzn() {
        int zzc = zzc();
        return zzhn.zzh(this.zza, zzc, zzd() + zzc);
    }
}
