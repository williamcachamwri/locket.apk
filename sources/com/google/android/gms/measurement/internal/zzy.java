package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzy {
    private zzfy.zzf zza;
    private Long zzb;
    private long zzc;
    private final /* synthetic */ zzt zzd;

    /* access modifiers changed from: package-private */
    public final zzfy.zzf zza(String str, zzfy.zzf zzf) {
        String zzg = zzf.zzg();
        List<zzfy.zzh> zzh = zzf.zzh();
        this.zzd.g_();
        Long l = (Long) zzoo.zzb(zzf, "_eid");
        boolean z = true;
        boolean z2 = l != null;
        if (!z2 || !zzg.equals("_ep")) {
            z = false;
        }
        if (z) {
            Preconditions.checkNotNull(l);
            this.zzd.g_();
            zzg = (String) zzoo.zzb(zzf, "_en");
            if (TextUtils.isEmpty(zzg)) {
                this.zzd.zzj().zzn().zza("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.zza == null || this.zzb == null || l.longValue() != this.zzb.longValue()) {
                Pair<zzfy.zzf, Long> zza2 = this.zzd.zzh().zza(str, l);
                if (zza2 == null || zza2.first == null) {
                    this.zzd.zzj().zzn().zza("Extra parameter without existing main event. eventName, eventId", zzg, l);
                    return null;
                }
                this.zza = (zzfy.zzf) zza2.first;
                this.zzc = ((Long) zza2.second).longValue();
                this.zzd.g_();
                this.zzb = (Long) zzoo.zzb(this.zza, "_eid");
            }
            long j = this.zzc - 1;
            this.zzc = j;
            if (j <= 0) {
                zzal zzh2 = this.zzd.zzh();
                zzh2.zzt();
                zzh2.zzj().zzp().zza("Clearing complex main event info. appId", str);
                try {
                    zzh2.e_().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    zzh2.zzj().zzg().zza("Error clearing complex main event", e);
                }
            } else {
                this.zzd.zzh().zza(str, l, this.zzc, this.zza);
            }
            ArrayList arrayList = new ArrayList();
            for (zzfy.zzh next : this.zza.zzh()) {
                this.zzd.g_();
                if (zzoo.zza(zzf, next.zzg()) == null) {
                    arrayList.add(next);
                }
            }
            if (!arrayList.isEmpty()) {
                arrayList.addAll(zzh);
                zzh = arrayList;
            } else {
                this.zzd.zzj().zzn().zza("No unique parameters in main event. eventName", zzg);
            }
        } else if (z2) {
            this.zzb = l;
            this.zza = zzf;
            this.zzd.g_();
            long longValue = ((Long) zzoo.zza(zzf, "_epc", (Object) 0L)).longValue();
            this.zzc = longValue;
            if (longValue <= 0) {
                this.zzd.zzj().zzn().zza("Complex event with zero extra param count. eventName", zzg);
            } else {
                this.zzd.zzh().zza(str, (Long) Preconditions.checkNotNull(l), this.zzc, zzf);
            }
        }
        zzjt.zzb zzcd = zzf.zzcd();
        zzjt.zzb zzb2 = zzcd;
        return (zzfy.zzf) ((zzjt) ((zzfy.zzf.zza) zzcd).zza(zzg).zzd().zza((Iterable<? extends zzfy.zzh>) zzh).zzai());
    }

    private zzy(zzt zzt) {
        this.zzd = zzt;
    }
}
