package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
class zzaiw extends zzaix {
    protected final byte[] zzb;

    public byte zza(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public int zze() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public byte zzb(int i) {
        return this.zzb[i];
    }

    /* access modifiers changed from: protected */
    public final int zzb(int i, int i2, int i3) {
        return zzakb.zza(i, this.zzb, zze(), i3);
    }

    public int zzb() {
        return this.zzb.length;
    }

    public final zzaip zza(int i, int i2) {
        int zza = zza(0, i2, zzb());
        if (zza == 0) {
            return zzaip.zza;
        }
        return new zzait(this.zzb, zze(), zza);
    }

    public final zzajb zzc() {
        return zzajb.zza(this.zzb, zze(), zzb(), true);
    }

    zzaiw(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    /* access modifiers changed from: protected */
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzaim zzaim) throws IOException {
        zzaim.zza(this.zzb, zze(), zzb());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaip) || zzb() != ((zzaip) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (!(obj instanceof zzaiw)) {
            return obj.equals(this);
        }
        zzaiw zzaiw = (zzaiw) obj;
        int zza = zza();
        int zza2 = zzaiw.zza();
        if (zza == 0 || zza2 == 0 || zza == zza2) {
            return zza(zzaiw, 0, zzb());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzaip zzaip, int i, int i2) {
        if (i2 > zzaip.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zzb());
        } else if (i2 > zzaip.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zzaip.zzb());
        } else if (!(zzaip instanceof zzaiw)) {
            return zzaip.zza(0, i2).equals(zza(0, i2));
        } else {
            zzaiw zzaiw = (zzaiw) zzaip;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzaiw.zzb;
            int zze = zze() + i2;
            int zze2 = zze();
            int zze3 = zzaiw.zze();
            while (zze2 < zze) {
                if (bArr[zze2] != bArr2[zze3]) {
                    return false;
                }
                zze2++;
                zze3++;
            }
            return true;
        }
    }
}
