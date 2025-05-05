package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzbz;
import com.google.android.gms.internal.measurement.zzov;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzhh implements Runnable {
    private final /* synthetic */ zzbz zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzhi zzc;

    zzhh(zzhi zzhi, zzbz zzbz, ServiceConnection serviceConnection) {
        this.zza = zzbz;
        this.zzb = serviceConnection;
        this.zzc = zzhi;
    }

    public final void run() {
        zzhf zzhf = this.zzc.zza;
        String zza2 = this.zzc.zzb;
        zzbz zzbz = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza3 = zzhf.zza(zza2, zzbz);
        zzhf.zza.zzl().zzt();
        zzhf.zza.zzy();
        if (zza3 != null) {
            long j = zza3.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzhf.zza.zzj().zzu().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza3.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzhf.zza.zzj().zzg().zza("No referrer defined in Install Referrer response");
                } else {
                    zzhf.zza.zzj().zzp().zza("InstallReferrer API result", string);
                    boolean z = zzov.zza() && zzhf.zza.zzf().zza(zzbh.zzcu);
                    Bundle zza4 = zzhf.zza.zzt().zza(Uri.parse("?" + string), z);
                    if (zza4 == null) {
                        zzhf.zza.zzj().zzg().zza("No campaign params defined in Install Referrer result");
                    } else {
                        if (!z) {
                            String string2 = zza4.getString("medium");
                            if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                                long j2 = zza3.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                                if (j2 == 0) {
                                    zzhf.zza.zzj().zzg().zza("Install Referrer is missing click timestamp for ad campaign");
                                } else {
                                    zza4.putLong("click_timestamp", j2);
                                }
                            }
                        } else if (zza4.containsKey("gclid") || zza4.containsKey("gbraid")) {
                            long j3 = zza3.getLong("referrer_click_timestamp_server_seconds", 0) * 1000;
                            if (j3 > 0) {
                                zza4.putLong("click_timestamp", j3);
                            }
                        }
                        if (j == zzhf.zza.zzn().zzd.zza()) {
                            zzhf.zza.zzj().zzp().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzhf.zza.zzac()) {
                            zzhf.zza.zzn().zzd.zza(j);
                            zzhf.zza.zzj().zzp().zza("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            zza4.putString("_cis", "referrer API v2");
                            zzhf.zza.zzp().zza("auto", "_cmp", zza4, zza2);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzhf.zza.zza(), serviceConnection);
        }
    }
}
