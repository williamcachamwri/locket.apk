package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
class zzabv extends zzabu {
    protected final byte[] zza;

    zzabv(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaby) || zzd() != ((zzaby) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzabv)) {
            return obj.equals(this);
        }
        zzabv zzabv = (zzabv) obj;
        int zzm = zzm();
        int zzm2 = zzabv.zzm();
        if (zzm != 0 && zzm2 != 0 && zzm != zzm2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzabv.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd > zzabv.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzabv.zzd());
        } else if (!(zzabv instanceof zzabv)) {
            return zzabv.zzg(0, zzd).equals(zzg(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzabv.zza;
            zzabv.zzc();
            int i = 0;
            int i2 = 0;
            while (i < zzd) {
                if (bArr[i] != bArr2[i2]) {
                    return false;
                }
                i++;
                i2++;
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
        return zzadg.zzd(i, this.zza, 0, i3);
    }

    public final zzaby zzg(int i, int i2) {
        int zzl = zzl(0, i2, zzd());
        if (zzl == 0) {
            return zzaby.zzb;
        }
        return new zzabs(this.zza, 0, zzl);
    }

    public final zzacc zzh() {
        return zzacc.zzu(this.zza, 0, zzd(), true);
    }

    /* access modifiers changed from: protected */
    public final String zzi(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzj(zzabo zzabo) throws IOException {
        ((zzace) zzabo).zzc(this.zza, 0, zzd());
    }

    public final boolean zzk() {
        return zzafx.zzf(this.zza, 0, zzd());
    }
}
