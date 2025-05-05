package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaff implements zzaft {
    private final zzafb zza;
    private final zzagh zzb;
    private final boolean zzc;
    private final zzadl zzd;

    private zzaff(zzagh zzagh, zzadl zzadl, zzafb zzafb) {
        this.zzb = zzagh;
        this.zzc = zzafb instanceof zzadv;
        this.zzd = zzadl;
        this.zza = zzafb;
    }

    static zzaff zzc(zzagh zzagh, zzadl zzadl, zzafb zzafb) {
        return new zzaff(zzagh, zzadl, zzafb);
    }

    public final int zza(Object obj) {
        zzagi zzagi = ((zzady) obj).zzc;
        zzagi zzagi2 = zzagi;
        int zzb2 = zzagi.zzb();
        return this.zzc ? zzb2 + ((zzadv) obj).zzb.zzc() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = ((zzady) obj).zzc.hashCode();
        return this.zzc ? (hashCode * 53) + ((zzadv) obj).zzb.zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zzafb zzafb = this.zza;
        if (zzafb instanceof zzady) {
            return ((zzady) zzafb).zzaB();
        }
        return zzafb.zzaQ().zzan();
    }

    public final void zzf(Object obj) {
        this.zzb.zzi(obj);
        this.zzd.zza(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzafv.zzq(this.zzb, obj, obj2);
        if (this.zzc) {
            zzafv.zzp(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, zzafl zzafl, zzadk zzadk) throws IOException {
        this.zzb.zza(obj);
        zzadv zzadv = (zzadv) obj;
        throw null;
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzacl zzacl) throws IOException {
        zzady zzady = (zzady) obj;
        if (zzady.zzc == zzagi.zzc()) {
            zzady.zzc = zzagi.zzf();
        }
        zzadv zzadv = (zzadv) obj;
        throw null;
    }

    public final void zzj(Object obj, zzagu zzagu) throws IOException {
        Iterator zze = ((zzadv) obj).zzb.zze();
        while (zze.hasNext()) {
            Map.Entry entry = (Map.Entry) zze.next();
            zzado zzado = (zzado) entry.getKey();
            if (zzado.zzc() != zzagt.MESSAGE || zzado.zze() || zzado.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzaej) {
                zzagu.zzw(zzado.zza(), ((zzaej) entry).zza().zzb());
            } else {
                zzagu.zzw(zzado.zza(), entry.getValue());
            }
        }
        zzagi zzagi = ((zzady) obj).zzc;
        zzagi zzagi2 = zzagi;
        zzagi.zzk(zzagu);
    }

    public final boolean zzk(Object obj, Object obj2) {
        if (!((zzady) obj).zzc.equals(((zzady) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzadv) obj).zzb.equals(((zzadv) obj2).zzb);
        }
        return true;
    }

    public final boolean zzl(Object obj) {
        return ((zzadv) obj).zzb.zzh();
    }
}
