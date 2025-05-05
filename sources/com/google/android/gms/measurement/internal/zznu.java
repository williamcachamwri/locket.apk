package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzpu;
import java.util.HashMap;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zznu extends zzns {
    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzt zzg() {
        return super.zzg();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzhl zzm() {
        return super.zzm();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzmw zzn() {
        return super.zzn();
    }

    public final zznw zza(String str) {
        zzg zze;
        if (zzpu.zza() && zze().zza(zzbh.zzbx)) {
            zzq();
            if (zzos.zzf(str)) {
                zzj().zzp().zza("sgtm feature flag enabled.");
                zzg zze2 = zzh().zze(str);
                if (zze2 == null) {
                    return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                String zzad = zze2.zzad();
                zzfr.zzd zzc = zzm().zzc(str);
                boolean z = false;
                if (!(zzc == null || (zze = zzh().zze(str)) == null || ((!zzc.zzq() || zzc.zzh().zza() != 100) && !zzq().zzd(str, zze.zzam()) && (!zze().zza(zzbh.zzbz) ? TextUtils.isEmpty(zzad) || zzad.hashCode() % 100 >= zzc.zzh().zza() : TextUtils.isEmpty(zzad) || Math.abs(zzad.hashCode() % 100) >= zzc.zzh().zza())))) {
                    z = true;
                }
                if (!z) {
                    return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
                }
                zznw zznw = null;
                if (zze2.zzat()) {
                    zzj().zzp().zza("sgtm upload enabled in manifest.");
                    zzfr.zzd zzc2 = zzm().zzc(zze2.zzac());
                    if (zzc2 != null && zzc2.zzq()) {
                        String zze3 = zzc2.zzh().zze();
                        if (!TextUtils.isEmpty(zze3)) {
                            String zzd = zzc2.zzh().zzd();
                            zzj().zzp().zza("sgtm configured with upload_url, server_info", zze3, TextUtils.isEmpty(zzd) ? "Y" : "N");
                            if (TextUtils.isEmpty(zzd)) {
                                zznw = new zznw(zze3, zznt.SGTM);
                            } else {
                                HashMap hashMap = new HashMap();
                                hashMap.put("x-sgtm-server-info", zzd);
                                if (!TextUtils.isEmpty(zze2.zzam())) {
                                    hashMap.put("x-gtm-server-preview", zze2.zzam());
                                }
                                zznw = new zznw(zze3, hashMap, zznt.SGTM);
                            }
                        }
                    }
                }
                if (zznw != null) {
                    return zznw;
                }
            }
        }
        return new zznw(zzb(str), zznt.GOOGLE_ANALYTICS);
    }

    public final /* bridge */ /* synthetic */ zznu zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzoo g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    public final String zza(zzg zzg) {
        Uri.Builder builder = new Uri.Builder();
        String zzah = zzg.zzah();
        if (TextUtils.isEmpty(zzah)) {
            zzah = zzg.zzaa();
        }
        builder.scheme(zzbh.zze.zza(null)).encodedAuthority(zzbh.zzf.zza(null)).path("config/app/" + zzah).appendQueryParameter("platform", DeviceInfo.OS_NAME).appendQueryParameter("gmp_version", "106000").appendQueryParameter("runtime_version", "0");
        return builder.build().toString();
    }

    private final String zzb(String str) {
        String zzf = zzm().zzf(str);
        if (TextUtils.isEmpty(zzf)) {
            return zzbh.zzq.zza(null);
        }
        Uri parse = Uri.parse(zzbh.zzq.zza(null));
        Uri.Builder buildUpon = parse.buildUpon();
        buildUpon.authority(zzf + "." + parse.getAuthority());
        return buildUpon.build().toString();
    }

    zznu(zznv zznv) {
        super(zznv);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }
}
