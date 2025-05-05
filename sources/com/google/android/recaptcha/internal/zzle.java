package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
class zzle extends zzld {
    protected final byte[] zza;

    zzle(byte[] bArr) {
        super((zzlf) null);
        bArr.getClass();
        this.zza = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzlg) || zzd() != ((zzlg) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzle)) {
            return obj.equals(this);
        }
        zzle zzle = (zzle) obj;
        int zzk = zzk();
        int zzk2 = zzle.zzk();
        if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
            return false;
        }
        int zzd = zzd();
        if (zzd > zzle.zzd()) {
            throw new IllegalArgumentException("Length too large: " + zzd + zzd());
        } else if (zzd > zzle.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzle.zzd());
        } else if (!(zzle instanceof zzle)) {
            return zzle.zzg(0, zzd).equals(zzg(0, zzd));
        } else {
            zzle zzle2 = zzle;
            byte[] bArr = this.zza;
            byte[] bArr2 = zzle.zza;
            zzle.zzc();
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
        return zznn.zzb(i, this.zza, 0, i3);
    }

    public final zzlg zzg(int i, int i2) {
        int zzj = zzj(0, i2, zzd());
        if (zzj == 0) {
            return zzlg.zzb;
        }
        return new zzlb(this.zza, 0, zzj);
    }

    /* access modifiers changed from: protected */
    public final String zzh(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void zzi(zzky zzky) throws IOException {
        ((zzlm) zzky).zzc(this.zza, 0, zzd());
    }
}
