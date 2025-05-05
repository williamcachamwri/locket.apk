package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzdq extends zzdp {
    zzdq() {
    }

    /* access modifiers changed from: package-private */
    public final int zza(Map.Entry entry) {
        return ((zzea) entry.getKey()).zza;
    }

    /* access modifiers changed from: package-private */
    public final zzdt zzb(Object obj) {
        return ((zzdz) obj).zza;
    }

    /* access modifiers changed from: package-private */
    public final zzdt zzc(Object obj) {
        return ((zzdz) obj).zzc();
    }

    /* access modifiers changed from: package-private */
    public final Object zzd(zzdo zzdo, zzfo zzfo, int i) {
        return zzdo.zzb(zzfo, i);
    }

    /* access modifiers changed from: package-private */
    public final void zze(Object obj) {
        ((zzdz) obj).zza.zzg();
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzhq zzhq, Map.Entry entry) throws IOException {
        zzea zzea = (zzea) entry.getKey();
        zzho zzho = zzho.DOUBLE;
        switch (zzea.zzb.ordinal()) {
            case 0:
                zzhq.zzf(zzea.zza, ((Double) entry.getValue()).doubleValue());
                return;
            case 1:
                zzhq.zzo(zzea.zza, ((Float) entry.getValue()).floatValue());
                return;
            case 2:
                zzhq.zzt(zzea.zza, ((Long) entry.getValue()).longValue());
                return;
            case 3:
                zzhq.zzK(zzea.zza, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzhq.zzr(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 5:
                zzhq.zzm(zzea.zza, ((Long) entry.getValue()).longValue());
                return;
            case 6:
                zzhq.zzk(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 7:
                zzhq.zzb(zzea.zza, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 8:
                zzhq.zzG(zzea.zza, (String) entry.getValue());
                return;
            case 9:
                zzhq.zzq(zzea.zza, entry.getValue(), zzfx.zza().zzb(entry.getValue().getClass()));
                return;
            case 10:
                zzhq.zzv(zzea.zza, entry.getValue(), zzfx.zza().zzb(entry.getValue().getClass()));
                return;
            case 11:
                zzhq.zzd(zzea.zza, (zzdb) entry.getValue());
                return;
            case 12:
                zzhq.zzI(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzhq.zzr(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 14:
                zzhq.zzx(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzhq.zzz(zzea.zza, ((Long) entry.getValue()).longValue());
                return;
            case 16:
                zzhq.zzB(zzea.zza, ((Integer) entry.getValue()).intValue());
                return;
            case 17:
                zzhq.zzD(zzea.zza, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg(zzfo zzfo) {
        return zzfo instanceof zzdz;
    }
}
