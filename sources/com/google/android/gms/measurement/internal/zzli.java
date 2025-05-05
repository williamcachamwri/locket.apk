package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzov;
import com.google.android.gms.internal.measurement.zzpo;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzli extends zznr {
    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    public zzli(zznv zznv) {
        super(zznv);
    }

    public final byte[] zza(zzbf zzbf, String str) {
        zzop zzop;
        zzfy.zzj.zza zza;
        Bundle bundle;
        zzfy.zzk.zza zza2;
        zzg zzg;
        byte[] bArr;
        zzbb zzbb;
        long j;
        zzbf zzbf2 = zzbf;
        String str2 = str;
        zzt();
        this.zzu.zzy();
        Preconditions.checkNotNull(zzbf);
        Preconditions.checkNotEmpty(str);
        if (!zze().zze(str2, zzbh.zzbl)) {
            zzj().zzc().zza("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzbf2.zza) || "_iapx".equals(zzbf2.zza)) {
            zzfy.zzj.zza zzb = zzfy.zzj.zzb();
            zzh().zzp();
            zzg zze = zzh().zze(str2);
            if (zze == null) {
                zzj().zzc().zza("Log and bundle not available. package_name", str2);
                byte[] bArr2 = new byte[0];
                zzh().zzu();
                return bArr2;
            } else if (!zze.zzar()) {
                zzj().zzc().zza("Log and bundle disabled. package_name", str2);
                byte[] bArr3 = new byte[0];
                zzh().zzu();
                return bArr3;
            } else {
                zzfy.zzk.zza zzp = zzfy.zzk.zzw().zzh(1).zzp(DeviceInfo.OS_NAME);
                if (!TextUtils.isEmpty(zze.zzac())) {
                    zzp.zzb(zze.zzac());
                }
                if (!TextUtils.isEmpty(zze.zzae())) {
                    zzp.zzd((String) Preconditions.checkNotNull(zze.zzae()));
                }
                if (!TextUtils.isEmpty(zze.zzaf())) {
                    zzp.zze((String) Preconditions.checkNotNull(zze.zzaf()));
                }
                if (zze.zze() != -2147483648L) {
                    zzp.zze((int) zze.zze());
                }
                zzp.zzf(zze.zzq()).zzd(zze.zzo());
                String zzah = zze.zzah();
                String zzaa = zze.zzaa();
                if (!TextUtils.isEmpty(zzah)) {
                    zzp.zzm(zzah);
                } else if (!TextUtils.isEmpty(zzaa)) {
                    zzp.zza(zzaa);
                }
                zzp.zzj(zze.zzw());
                zzje zzb2 = this.zzg.zzb(str2);
                zzp.zzc(zze.zzn());
                if (this.zzu.zzac() && zze().zzj(zzp.zzt()) && zzb2.zzg() && !TextUtils.isEmpty((CharSequence) null)) {
                    zzp.zzj((String) null);
                }
                zzp.zzg(zzb2.zze());
                if (zzb2.zzg() && zze.zzaq()) {
                    Pair<String, Boolean> zza3 = zzn().zza(zze.zzac(), zzb2);
                    if (zze.zzaq() && zza3 != null && !TextUtils.isEmpty((CharSequence) zza3.first)) {
                        try {
                            zzp.zzq(zza((String) zza3.first, Long.toString(zzbf2.zzd)));
                            if (zza3.second != null) {
                                zzp.zzc(((Boolean) zza3.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzj().zzc().zza("Resettable device id encryption failed", e.getMessage());
                            return new byte[0];
                        } finally {
                            zzh().zzu();
                        }
                    }
                }
                zzf().zzac();
                zzfy.zzk.zza zzi = zzp.zzi(Build.MODEL);
                zzf().zzac();
                zzi.zzo(Build.VERSION.RELEASE).zzj((int) zzf().zzc()).zzs(zzf().zzg());
                try {
                    if (zzb2.zzh() && zze.zzad() != null) {
                        zzp.zzc(zza((String) Preconditions.checkNotNull(zze.zzad()), Long.toString(zzbf2.zzd)));
                    }
                    if (!TextUtils.isEmpty(zze.zzag())) {
                        zzp.zzl((String) Preconditions.checkNotNull(zze.zzag()));
                    }
                    String zzac = zze.zzac();
                    List<zzop> zzl = zzh().zzl(zzac);
                    Iterator<zzop> it = zzl.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzop = null;
                            break;
                        }
                        zzop = it.next();
                        if ("_lte".equals(zzop.zzc)) {
                            break;
                        }
                    }
                    if (zzop == null || zzop.zze == null) {
                        zzop zzop2 = new zzop(zzac, "auto", "_lte", zzb().currentTimeMillis(), 0L);
                        zzl.add(zzop2);
                        zzh().zza(zzop2);
                    }
                    zzfy.zzo[] zzoArr = new zzfy.zzo[zzl.size()];
                    for (int i = 0; i < zzl.size(); i++) {
                        zzfy.zzo.zza zzb3 = zzfy.zzo.zze().zza(zzl.get(i).zzc).zzb(zzl.get(i).zzd);
                        g_().zza(zzb3, zzl.get(i).zze);
                        zzoArr[i] = (zzfy.zzo) ((zzjt) zzb3.zzai());
                    }
                    zzp.zze((Iterable<? extends zzfy.zzo>) Arrays.asList(zzoArr));
                    this.zzg.zza(zze, zzp);
                    if (zzov.zza() && zze().zza(zzbh.zzcu)) {
                        this.zzg.zzb(zze, zzp);
                    }
                    zzgs zza4 = zzgs.zza(zzbf);
                    zzq().zza(zza4.zzc, zzh().zzd(str2));
                    zzq().zza(zza4, zze().zzb(str2));
                    Bundle bundle2 = zza4.zzc;
                    bundle2.putLong("_c", 1);
                    zzj().zzc().zza("Marking in-app purchase as real-time");
                    bundle2.putLong("_r", 1);
                    bundle2.putString("_o", zzbf2.zzc);
                    if (zzq().zzd(zzp.zzt(), zze.zzam())) {
                        zzq().zza(bundle2, "_dbg", (Object) 1L);
                        zzq().zza(bundle2, "_r", (Object) 1L);
                    }
                    zzbb zzd = zzh().zzd(str2, zzbf2.zza);
                    if (zzd == null) {
                        zza2 = zzp;
                        bundle = bundle2;
                        zza = zzb;
                        zzg = zze;
                        bArr = null;
                        zzbb = new zzbb(str, zzbf2.zza, 0, 0, zzbf2.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j = 0;
                    } else {
                        zza2 = zzp;
                        bundle = bundle2;
                        zza = zzb;
                        zzg = zze;
                        bArr = null;
                        j = zzd.zzf;
                        zzbb = zzd.zza(zzbf2.zzd);
                    }
                    zzh().zza(zzbb);
                    zzbc zzbc = new zzbc(this.zzu, zzbf2.zzc, str, zzbf2.zza, zzbf2.zzd, j, bundle);
                    zzfy.zzf.zza zza5 = zzfy.zzf.zze().zzb(zzbc.zzd).zza(zzbc.zzb).zza(zzbc.zze);
                    Iterator<String> it2 = zzbc.zzf.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        zzfy.zzh.zza zza6 = zzfy.zzh.zze().zza(next);
                        Object zzc = zzbc.zzf.zzc(next);
                        if (zzc != null) {
                            g_().zza(zza6, zzc);
                            zza5.zza(zza6);
                        }
                    }
                    zzfy.zzk.zza zza7 = zza2;
                    zza7.zza(zza5).zza(zzfy.zzl.zza().zza(zzfy.zzg.zza().zza(zzbb.zzc).zza(zzbf2.zza)));
                    zza7.zza((Iterable<? extends zzfy.zzd>) zzg().zza(zzg.zzac(), Collections.emptyList(), zza7.zzab(), Long.valueOf(zza5.zzc()), Long.valueOf(zza5.zzc())));
                    if (zza5.zzg()) {
                        zza7.zzi(zza5.zzc()).zze(zza5.zzc());
                    }
                    long zzs = zzg.zzs();
                    int i2 = (zzs > 0 ? 1 : (zzs == 0 ? 0 : -1));
                    if (i2 != 0) {
                        zza7.zzg(zzs);
                    }
                    long zzu = zzg.zzu();
                    if (zzu != 0) {
                        zza7.zzh(zzu);
                    } else if (i2 != 0) {
                        zza7.zzh(zzs);
                    }
                    String zzal = zzg.zzal();
                    if (zzpo.zza()) {
                        if (zze().zze(str, zzbh.zzbw) && zzal != null) {
                            zza7.zzr(zzal);
                        }
                    } else {
                        String str3 = str;
                    }
                    zzg.zzap();
                    zza7.zzf((int) zzg.zzt()).zzl(106000).zzk(zzb().currentTimeMillis()).zzd(Boolean.TRUE.booleanValue());
                    this.zzg.zza(zza7.zzt(), zza7);
                    zzfy.zzj.zza zza8 = zza;
                    zza8.zza(zza7);
                    zzg zzg2 = zzg;
                    zzg2.zzr(zza7.zzf());
                    zzg2.zzp(zza7.zze());
                    zzh().zza(zzg2, false, false);
                    zzh().zzw();
                    try {
                        return g_().zzb(((zzfy.zzj) ((zzjt) zza8.zzai())).zzca());
                    } catch (IOException e2) {
                        zzj().zzg().zza("Data loss. Failed to bundle and serialize. appId", zzgo.zza(str), e2);
                        return bArr;
                    }
                } catch (SecurityException e3) {
                    zzj().zzc().zza("app instance id encryption failed", e3.getMessage());
                    byte[] bArr4 = new byte[0];
                    zzh().zzu();
                    return bArr4;
                }
            }
        } else {
            zzj().zzc().zza("Generating a payload for this event is not available. package_name, event_name", str2, zzbf2.zza);
            return null;
        }
    }
}
