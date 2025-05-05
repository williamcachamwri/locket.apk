package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
class zziv extends zzis {
    protected final byte[] zzb;

    public byte zza(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public int zzc() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public final int zzb(int i, int i2, int i3) {
        return zzjv.zza(i, this.zzb, zzc(), i3);
    }

    public int zzb() {
        return this.zzb.length;
    }

    public final zzik zza(int i, int i2) {
        int zza = zza(0, i2, zzb());
        if (zza == 0) {
            return zzik.zza;
        }
        return new zzio(this.zzb, zzc(), zza);
    }

    zziv(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzil zzil) throws IOException {
        zzil.zza(this.zzb, zzc(), zzb());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzik) || zzb() != ((zzik) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (!(obj instanceof zziv)) {
            return obj.equals(this);
        }
        zziv zziv = (zziv) obj;
        int zza = zza();
        int zza2 = zziv.zza();
        if (zza == 0 || zza2 == 0 || zza == zza2) {
            return zza(zziv, 0, zzb());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzik zzik, int i, int i2) {
        if (i2 > zzik.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zzb());
        } else if (i2 > zzik.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzik.zzb());
        } else if (!(zzik instanceof zziv)) {
            return zzik.zza(0, i2).equals(zza(0, i2));
        } else {
            zziv zziv = (zziv) zzik;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zziv.zzb;
            int zzc = zzc() + i2;
            int zzc2 = zzc();
            int zzc3 = zziv.zzc();
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
}
