package com.google.android.gms.internal.atv_ads_framework;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
class zzck extends zzcj {
    protected final byte[] zza;

    zzck(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcn) || zzd() != ((zzcn) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzck)) {
            return obj.equals(this);
        }
        zzck zzck = (zzck) obj;
        int zzk = zzk();
        int zzk2 = zzck.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzck.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd > zzck.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzck.zzd());
        } else if (!(zzck instanceof zzck)) {
            return zzck.zzf(0, zzd).equals(zzf(0, zzd));
        } else {
            byte[] bArr = this.zza;
            byte[] bArr2 = zzck.zza;
            zzck.zzc();
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
    public final int zze(int i, int i2, int i3) {
        return zzdp.zzb(i, this.zza, 0, i3);
    }

    public final zzcn zzf(int i, int i2) {
        zzj(0, i2, zzd());
        if (i2 == 0) {
            return zzcn.zzb;
        }
        return new zzch(this.zza, 0, i2);
    }

    /* access modifiers changed from: protected */
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzh(zzcd zzcd) throws IOException {
        ((zzcs) zzcd).zzc(this.zza, 0, zzd());
    }

    public final boolean zzi() {
        return zzgd.zze(this.zza, 0, zzd());
    }
}
