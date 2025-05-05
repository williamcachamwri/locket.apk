package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzc extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzc zza;
    private int zzd;
    private int zze;
    private zzdb zzf = zzdb.zzb;
    private String zzg = "";
    private int zzh;
    private zzr zzi;
    private zzy zzj;
    private zzci zzk;
    private zzag zzl;
    private zzao zzm;
    private zzaj zzn;
    private zzac zzo;
    private zzp zzp;
    private zzt zzq;
    private zzl zzr;
    private zzel zzs = zzO();
    private zzej zzt = zzN();
    private String zzu = "";
    private zzel zzv = zzO();
    private boolean zzw = true;
    private double zzx;
    private zzdb zzy = zzdb.zzb;
    private byte zzz = 2;

    static {
        zzc zzc = new zzc();
        zza = zzc;
        zzed.zzU(zzc.class, zzc);
    }

    private zzc() {
    }

    static /* synthetic */ void zzp(zzc zzc, int i, zzae zzae) {
        zzae.getClass();
        zzel zzel = zzc.zzs;
        if (!zzel.zzc()) {
            zzc.zzs = zzed.zzP(zzel);
        }
        zzc.zzs.set(i, zzae);
    }

    public final int zzA() {
        int zza2 = zzi.zza(this.zzh);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    public final int zza() {
        return this.zzs.size();
    }

    public final zzci zzb() {
        zzci zzci = this.zzk;
        return zzci == null ? zzci.zzb() : zzci;
    }

    public final zzp zzd() {
        zzp zzp2 = this.zzp;
        return zzp2 == null ? zzp.zzd() : zzp2;
    }

    public final zzr zze() {
        zzr zzr2 = this.zzi;
        return zzr2 == null ? zzr.zzc() : zzr2;
    }

    public final zzt zzf() {
        zzt zzt2 = this.zzq;
        return zzt2 == null ? zzt.zzb() : zzt2;
    }

    public final zzy zzh() {
        zzy zzy2 = this.zzj;
        return zzy2 == null ? zzy.zzb() : zzy2;
    }

    public final zzac zzi() {
        zzac zzac = this.zzo;
        return zzac == null ? zzac.zzd() : zzac;
    }

    public final zzag zzj() {
        zzag zzag = this.zzl;
        return zzag == null ? zzag.zzb() : zzag;
    }

    public final zzaj zzk() {
        zzaj zzaj = this.zzn;
        return zzaj == null ? zzaj.zzb() : zzaj;
    }

    public final zzao zzl() {
        zzao zzao = this.zzm;
        return zzao == null ? zzao.zzb() : zzao;
    }

    public final zzdb zzm() {
        return this.zzf;
    }

    public final String zzn() {
        return this.zzg;
    }

    public final List zzo() {
        return this.zzs;
    }

    public final boolean zzq() {
        return (this.zzd & 2048) != 0;
    }

    public final boolean zzr() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzs() {
        return (this.zzd & 4096) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 1024) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 64) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 128) != 0;
    }

    public final boolean zzx() {
        return (this.zzd & 512) != 0;
    }

    public final boolean zzy() {
        return (this.zzd & 256) != 0;
    }

    public final int zzz() {
        int zza2 = zzf.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzz);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0003\u000b\u0001ᴌ\u0000\u0002ᔊ\u0001\u0003ᔈ\u0002\u0004ᴌ\u0003\u0005ᐉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bᐉ\u0007\tᐉ\b\nᐉ\t\u000bЛ\fဈ\u000e\rЛ\u000eည\u0011\u000fᐉ\n\u0010ဉ\u000b\u0011ဉ\f\u0012\u0016\u0013ဉ\r\u0014ဇ\u000f\u0015က\u0010", new Object[]{"zzd", "zze", zze.zza, "zzf", "zzg", "zzh", zzh.zza, "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzs", zzae.class, "zzu", "zzv", zzae.class, "zzy", "zzo", "zzp", "zzq", "zzt", "zzr", "zzw", "zzx"});
        } else if (i2 == 3) {
            return new zzc();
        } else {
            if (i2 == 4) {
                return new zzb((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzz = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
