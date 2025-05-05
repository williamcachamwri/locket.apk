package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.adsbynimbus.Nimbus;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzmw extends zznr {
    public final zzhb zza;
    public final zzhb zzb;
    public final zzhb zzc;
    public final zzhb zzd;
    public final zzhb zze;
    public final zzhb zzf;
    private final Map<String, zzmv> zzh = new HashMap();

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    @Deprecated
    private final Pair<String, Boolean> zza(String str) {
        zzmv zzmv;
        AdvertisingIdClient.Info info;
        zzt();
        long elapsedRealtime = zzb().elapsedRealtime();
        zzmv zzmv2 = this.zzh.get(str);
        if (zzmv2 != null && elapsedRealtime < zzmv2.zzc) {
            return new Pair<>(zzmv2.zza, Boolean.valueOf(zzmv2.zzb));
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        long zzd2 = zze().zzd(str) + elapsedRealtime;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(zza());
        } catch (PackageManager.NameNotFoundException unused) {
            if (zzmv2 != null) {
                try {
                    if (elapsedRealtime < zzmv2.zzc + zze().zzc(str, zzbh.zzb)) {
                        return new Pair<>(zzmv2.zza, Boolean.valueOf(zzmv2.zzb));
                    }
                } catch (Exception e) {
                    zzj().zzc().zza("Unable to get advertising id", e);
                    zzmv = new zzmv("", false, zzd2);
                }
            }
            info = null;
        }
        if (info == null) {
            return new Pair<>(Nimbus.EMPTY_AD_ID, false);
        }
        String id = info.getId();
        if (id != null) {
            zzmv = new zzmv(id, info.isLimitAdTrackingEnabled(), zzd2);
        } else {
            zzmv = new zzmv("", info.isLimitAdTrackingEnabled(), zzd2);
        }
        this.zzh.put(str, zzmv);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(zzmv.zza, Boolean.valueOf(zzmv.zzb));
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str, zzje zzje) {
        if (zzje.zzg()) {
            return zza(str);
        }
        return new Pair<>("", false);
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

    /* access modifiers changed from: package-private */
    @Deprecated
    public final String zza(String str, boolean z) {
        zzt();
        String str2 = z ? (String) zza(str).first : Nimbus.EMPTY_AD_ID;
        MessageDigest zzu = zzos.zzu();
        if (zzu == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzu.digest(str2.getBytes()))});
    }

    zzmw(zznv zznv) {
        super(zznv);
        zzha zzk = zzk();
        Objects.requireNonNull(zzk);
        this.zza = new zzhb(zzk, "last_delete_stale", 0);
        zzha zzk2 = zzk();
        Objects.requireNonNull(zzk2);
        this.zzb = new zzhb(zzk2, "last_delete_stale_batch", 0);
        zzha zzk3 = zzk();
        Objects.requireNonNull(zzk3);
        this.zzc = new zzhb(zzk3, "backoff", 0);
        zzha zzk4 = zzk();
        Objects.requireNonNull(zzk4);
        this.zzd = new zzhb(zzk4, "last_upload", 0);
        zzha zzk5 = zzk();
        Objects.requireNonNull(zzk5);
        this.zze = new zzhb(zzk5, "last_upload_attempt", 0);
        zzha zzk6 = zzk();
        Objects.requireNonNull(zzk6);
        this.zzf = new zzhb(zzk6, "midnight_offset", 0);
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
