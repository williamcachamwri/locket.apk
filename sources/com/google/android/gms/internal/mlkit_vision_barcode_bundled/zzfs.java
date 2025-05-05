package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfs implements zzgh {
    private final zzfo zza;
    private final zzgy zzb;
    private final boolean zzc;
    private final zzdp zzd;

    private zzfs(zzgy zzgy, zzdp zzdp, zzfo zzfo) {
        this.zzb = zzgy;
        this.zzc = zzdp.zzg(zzfo);
        this.zzd = zzdp;
        this.zza = zzfo;
    }

    static zzfs zzc(zzgy zzgy, zzdp zzdp, zzfo zzfo) {
        return new zzfs(zzgy, zzdp, zzfo);
    }

    public final int zza(Object obj) {
        zzgy zzgy = this.zzb;
        int zzb2 = zzgy.zzb(zzgy.zzd(obj));
        return this.zzc ? zzb2 + this.zzd.zzb(obj).zzb() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zzb(obj).zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zzfo zzfo = this.zza;
        if (zzfo instanceof zzed) {
            return ((zzed) zzfo).zzJ();
        }
        return zzfo.zzY().zzk();
    }

    public final void zzf(Object obj) {
        this.zzb.zzg(obj);
        this.zzd.zze(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzgj.zzC(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgj.zzB(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, byte[] bArr, int i, int i2, zzco zzco) throws IOException {
        zzed zzed = (zzed) obj;
        zzgz zzgz = zzed.zzc;
        if (zzgz == zzgz.zzc()) {
            zzgz = zzgz.zzf();
            zzed.zzc = zzgz;
        }
        zzdt zzc2 = ((zzdz) obj).zzc();
        Object obj2 = null;
        while (i < i2) {
            int zzj = zzcp.zzj(bArr, i, zzco);
            int i3 = zzco.zza;
            if (i3 == 11) {
                int i4 = 0;
                zzdb zzdb = null;
                while (zzj < i2) {
                    zzj = zzcp.zzj(bArr, zzj, zzco);
                    int i5 = zzco.zza;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3) {
                            if (obj2 != null) {
                                zzeb zzeb = (zzeb) obj2;
                                zzj = zzcp.zzd(zzfx.zza().zzb(zzeb.zzc.getClass()), bArr, zzj, i2, zzco);
                                zzc2.zzi(zzeb.zzd, zzco.zzc);
                            } else if (i7 == 2) {
                                zzj = zzcp.zza(bArr, zzj, zzco);
                                zzdb = (zzdb) zzco.zzc;
                            }
                        }
                    } else if (i7 == 0) {
                        zzj = zzcp.zzj(bArr, zzj, zzco);
                        i4 = zzco.zza;
                        obj2 = this.zzd.zzd(zzco.zzd, this.zza, i4);
                    }
                    if (i5 == 12) {
                        break;
                    }
                    zzj = zzcp.zzp(i5, bArr, zzj, i2, zzco);
                }
                if (zzdb != null) {
                    zzgz.zzj((i4 << 3) | 2, zzdb);
                }
                i = zzj;
            } else if ((i3 & 7) == 2) {
                Object zzd2 = this.zzd.zzd(zzco.zzd, this.zza, i3 >>> 3);
                if (zzd2 != null) {
                    zzeb zzeb2 = (zzeb) zzd2;
                    i = zzcp.zzd(zzfx.zza().zzb(zzeb2.zzc.getClass()), bArr, zzj, i2, zzco);
                    zzc2.zzi(zzeb2.zzd, zzco.zzc);
                } else {
                    i = zzcp.zzi(i3, bArr, zzj, i2, zzgz, zzco);
                }
                obj2 = zzd2;
            } else {
                i = zzcp.zzp(i3, bArr, zzj, i2, zzco);
            }
        }
        if (i != i2) {
            throw zzeo.zze();
        }
    }

    public final void zzi(Object obj, zzhq zzhq) throws IOException {
        Iterator zzf = this.zzd.zzb(obj).zzf();
        while (zzf.hasNext()) {
            Map.Entry entry = (Map.Entry) zzf.next();
            zzds zzds = (zzds) entry.getKey();
            if (zzds.zze() == zzhp.MESSAGE) {
                zzds.zzg();
                zzds.zzf();
                if (entry instanceof zzer) {
                    zzhq.zzw(zzds.zza(), ((zzer) entry).zza().zzb());
                } else {
                    zzhq.zzw(zzds.zza(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        zzgy zzgy = this.zzb;
        zzgy.zzi(zzgy.zzd(obj), zzhq);
    }

    public final boolean zzj(Object obj, Object obj2) {
        if (!this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zzb(obj).equals(this.zzd.zzb(obj2));
        }
        return true;
    }

    public final boolean zzk(Object obj) {
        return this.zzd.zzb(obj).zzk();
    }
}
