package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.amplitude.api.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzkb;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.measurement.internal.zzje;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzhl extends zznr implements zzai {
    final LruCache<String, zzb> zza = new zzho(this, 20);
    final zzv zzb = new zzhr(this);
    /* access modifiers changed from: private */
    public final Map<String, Map<String, String>> zzc = new ArrayMap();
    private final Map<String, Set<String>> zzd = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzf = new ArrayMap();
    private final Map<String, zzfr.zzd> zzh = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzi = new ArrayMap();
    private final Map<String, String> zzj = new ArrayMap();
    private final Map<String, String> zzk = new ArrayMap();
    private final Map<String, String> zzl = new ArrayMap();

    /* access modifiers changed from: package-private */
    public final int zzb(String str, String str2) {
        Integer num;
        zzt();
        zzu(str);
        Map map = this.zzi.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final long zza(String str) {
        String zza2 = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zza2)) {
            return 0;
        }
        try {
            return Long.parseLong(zza2);
        } catch (NumberFormatException e) {
            zzj().zzu().zza("Unable to parse timezone offset. appId", zzgo.zza(str), e);
            return 0;
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    static /* synthetic */ zzb zza(zzhl zzhl, String str) {
        zzhl.zzal();
        Preconditions.checkNotEmpty(str);
        if (!zzhl.zzk(str)) {
            return null;
        }
        if (!zzhl.zzh.containsKey(str) || zzhl.zzh.get(str) == null) {
            zzhl.zzu(str);
        } else {
            zzhl.zza(str, zzhl.zzh.get(str));
        }
        return zzhl.zza.snapshot().get(str);
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

    /* access modifiers changed from: package-private */
    public final zzjh zza(String str, zzje.zza zza2) {
        zzt();
        zzu(str);
        zzfr.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return zzjh.UNINITIALIZED;
        }
        for (zzfr.zza.C0002zza next : zzb2.zzf()) {
            if (zza(next.zzc()) == zza2) {
                int i = zzht.zzc[next.zzb().ordinal()];
                if (i == 1) {
                    return zzjh.DENIED;
                }
                if (i != 2) {
                    return zzjh.UNINITIALIZED;
                }
                return zzjh.GRANTED;
            }
        }
        return zzjh.UNINITIALIZED;
    }

    /* access modifiers changed from: package-private */
    public final zzje.zza zzb(String str, zzje.zza zza2) {
        zzt();
        zzu(str);
        zzfr.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return null;
        }
        for (zzfr.zza.zzc next : zzb2.zze()) {
            if (zza2 == zza(next.zzc())) {
                return zza(next.zzb());
            }
        }
        return null;
    }

    private static zzje.zza zza(zzfr.zza.zze zze2) {
        int i = zzht.zzb[zze2.ordinal()];
        if (i == 1) {
            return zzje.zza.AD_STORAGE;
        }
        if (i == 2) {
            return zzje.zza.ANALYTICS_STORAGE;
        }
        if (i == 3) {
            return zzje.zza.AD_USER_DATA;
        }
        if (i != 4) {
            return null;
        }
        return zzje.zza.AD_PERSONALIZATION;
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
    public final zzfr.zza zzb(String str) {
        zzt();
        zzu(str);
        zzfr.zzd zzc2 = zzc(str);
        if (zzc2 == null || !zzc2.zzo()) {
            return null;
        }
        return zzc2.zzd();
    }

    /* access modifiers changed from: protected */
    public final zzfr.zzd zzc(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzu(str);
        return this.zzh.get(str);
    }

    private final zzfr.zzd zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzfr.zzd.zzg();
        }
        try {
            zzfr.zzd zzd2 = (zzfr.zzd) ((zzjt) ((zzfr.zzd.zza) zzoo.zza(zzfr.zzd.zze(), bArr)).zzai());
            zzgq zzp = zzj().zzp();
            String str2 = null;
            Long valueOf = zzd2.zzr() ? Long.valueOf(zzd2.zzc()) : null;
            if (zzd2.zzp()) {
                str2 = zzd2.zzi();
            }
            zzp.zza("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzd2;
        } catch (zzkb e) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzgo.zza(str), e);
            return zzfr.zzd.zzg();
        } catch (RuntimeException e2) {
            zzj().zzu().zza("Unable to merge remote config. appId", zzgo.zza(str), e2);
            return zzfr.zzd.zzg();
        }
    }

    public final String zza(String str, String str2) {
        zzt();
        zzu(str);
        Map map = this.zzc.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String zzd(String str) {
        zzt();
        return this.zzl.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zze(String str) {
        zzt();
        return this.zzk.get(str);
    }

    /* access modifiers changed from: package-private */
    public final String zzf(String str) {
        zzt();
        zzu(str);
        return this.zzj.get(str);
    }

    private static Map<String, String> zza(zzfr.zzd zzd2) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzd2 != null) {
            for (zzfr.zzh next : zzd2.zzn()) {
                arrayMap.put(next.zzb(), next.zzc());
            }
        }
        return arrayMap;
    }

    /* access modifiers changed from: package-private */
    public final Set<String> zzg(String str) {
        zzt();
        zzu(str);
        return this.zzd.get(str);
    }

    /* access modifiers changed from: package-private */
    public final SortedSet<String> zzh(String str) {
        zzt();
        zzu(str);
        TreeSet treeSet = new TreeSet();
        zzfr.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return treeSet;
        }
        for (zzfr.zza.zzf zzb3 : zzb2.zzc()) {
            treeSet.add(zzb3.zzb());
        }
        return treeSet;
    }

    zzhl(zznv zznv) {
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

    /* access modifiers changed from: protected */
    public final void zzi(String str) {
        zzt();
        this.zzk.put(str, (Object) null);
    }

    private final void zza(String str, zzfr.zzd.zza zza2) {
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zza2 != null) {
            for (zzfr.zzb zzb2 : zza2.zze()) {
                hashSet.add(zzb2.zzb());
            }
            for (int i = 0; i < zza2.zza(); i++) {
                zzjt.zzb zzcd = zza2.zza(i).zzcd();
                zzjt.zzb zzb3 = zzcd;
                zzfr.zzc.zza zza3 = (zzfr.zzc.zza) zzcd;
                if (zza3.zzb().isEmpty()) {
                    zzj().zzu().zza("EventConfig contained null event name");
                } else {
                    String zzb4 = zza3.zzb();
                    String zzb5 = zzji.zzb(zza3.zzb());
                    if (!TextUtils.isEmpty(zzb5)) {
                        zza3 = zza3.zza(zzb5);
                        zza2.zza(i, zza3);
                    }
                    if (zza3.zze() && zza3.zzc()) {
                        arrayMap.put(zzb4, true);
                    }
                    if (zza3.zzf() && zza3.zzd()) {
                        arrayMap2.put(zza3.zzb(), true);
                    }
                    if (zza3.zzg()) {
                        if (zza3.zza() < 2 || zza3.zza() > 65535) {
                            zzj().zzu().zza("Invalid sampling rate. Event name, sample rate", zza3.zzb(), Integer.valueOf(zza3.zza()));
                        } else {
                            arrayMap3.put(zza3.zzb(), Integer.valueOf(zza3.zza()));
                        }
                    }
                }
            }
        }
        this.zzd.put(str, hashSet);
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzi.put(str, arrayMap3);
    }

    private final void zzu(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        if (this.zzh.get(str) == null) {
            zzan zzf2 = zzh().zzf(str);
            if (zzf2 == null) {
                this.zzc.put(str, (Object) null);
                this.zze.put(str, (Object) null);
                this.zzd.put(str, (Object) null);
                this.zzf.put(str, (Object) null);
                this.zzh.put(str, (Object) null);
                this.zzj.put(str, (Object) null);
                this.zzk.put(str, (Object) null);
                this.zzl.put(str, (Object) null);
                this.zzi.put(str, (Object) null);
                return;
            }
            zzjt.zzb zzcd = zza(str, zzf2.zza).zzcd();
            zzjt.zzb zzb2 = zzcd;
            zzfr.zzd.zza zza2 = (zzfr.zzd.zza) zzcd;
            zza(str, zza2);
            this.zzc.put(str, zza((zzfr.zzd) ((zzjt) zza2.zzai())));
            this.zzh.put(str, (zzfr.zzd) ((zzjt) zza2.zzai()));
            zza(str, (zzfr.zzd) ((zzjt) zza2.zzai()));
            this.zzj.put(str, zza2.zzc());
            this.zzk.put(str, zzf2.zzb);
            this.zzl.put(str, zzf2.zzc);
        }
    }

    private final void zza(String str, zzfr.zzd zzd2) {
        if (zzd2.zza() == 0) {
            this.zza.remove(str);
            return;
        }
        zzj().zzp().zza("EES programs found", Integer.valueOf(zzd2.zza()));
        zzgd.zzc zzc2 = zzd2.zzm().get(0);
        try {
            zzb zzb2 = new zzb();
            zzb2.zza("internal.remoteConfig", new zzhn(this, str));
            zzb2.zza("internal.appMetadata", new zzhm(this, str));
            zzb2.zza("internal.logger", new zzhp(this));
            zzb2.zza(zzc2);
            this.zza.put(str, zzb2);
            zzj().zzp().zza("EES program loaded for appId, activities", str, Integer.valueOf(zzc2.zza().zza()));
            for (zzgd.zzb zzb3 : zzc2.zza().zzd()) {
                zzj().zzp().zza("EES program activity", zzb3.zzb());
            }
        } catch (zzc unused) {
            zzj().zzg().zza("Failed to load EES program. appId", str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(String str) {
        zzt();
        this.zzh.remove(str);
    }

    public final boolean zzk(String str) {
        zzfr.zzd zzd2;
        if (TextUtils.isEmpty(str) || (zzd2 = this.zzh.get(str)) == null || zzd2.zza() == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(String str, zzje.zza zza2) {
        zzt();
        zzu(str);
        zzfr.zza zzb2 = zzb(str);
        if (zzb2 == null) {
            return false;
        }
        Iterator<zzfr.zza.C0002zza> it = zzb2.zzd().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zzfr.zza.C0002zza next = it.next();
            if (zza2 == zza(next.zzc())) {
                if (next.zzb() == zzfr.zza.zzd.GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzm(String str) {
        zzt();
        zzu(str);
        zzfr.zza zzb2 = zzb(str);
        if (zzb2 != null && zzb2.zzh() && !zzb2.zzg()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(String str, String str2) {
        Boolean bool;
        zzt();
        zzu(str);
        if ("ecommerce_purchase".equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = this.zzf.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(String str, String str2) {
        Boolean bool;
        zzt();
        zzu(str);
        if (zzl(str) && zzos.zzg(str2)) {
            return true;
        }
        if (zzn(str) && zzos.zzh(str2)) {
            return true;
        }
        Map map = this.zze.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzn(String str) {
        return "1".equals(zza(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2, String str3) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        zzjt.zzb zzcd = zza(str, bArr).zzcd();
        zzjt.zzb zzb2 = zzcd;
        zzfr.zzd.zza zza2 = (zzfr.zzd.zza) zzcd;
        if (zza2 == null) {
            return false;
        }
        zza(str, zza2);
        zza(str, (zzfr.zzd) ((zzjt) zza2.zzai()));
        this.zzh.put(str, (zzfr.zzd) ((zzjt) zza2.zzai()));
        this.zzj.put(str, zza2.zzc());
        this.zzk.put(str, str2);
        this.zzl.put(str, str3);
        this.zzc.put(str, zza((zzfr.zzd) ((zzjt) zza2.zzai())));
        zzh().zza(str, (List<zzfo.zza>) new ArrayList(zza2.zzd()));
        try {
            zza2.zzb();
            bArr = ((zzfr.zzd) ((zzjt) zza2.zzai())).zzca();
        } catch (RuntimeException e) {
            zzj().zzu().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzgo.zza(str), e);
        }
        zzal zzh2 = zzh();
        Preconditions.checkNotEmpty(str);
        zzh2.zzt();
        zzh2.zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        contentValues.put("e_tag", str3);
        try {
            if (((long) zzh2.e_().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzh2.zzj().zzg().zza("Failed to update remote config (got 0). appId", zzgo.zza(str));
            }
        } catch (SQLiteException e2) {
            zzh2.zzj().zzg().zza("Error storing remote config. appId", zzgo.zza(str), e2);
        }
        this.zzh.put(str, (zzfr.zzd) ((zzjt) zza2.zzai()));
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo(String str) {
        zzt();
        zzu(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("app_instance_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp(String str) {
        zzt();
        zzu(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains("device_model") || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzq(String str) {
        zzt();
        zzu(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("enhanced_user_id");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzr(String str) {
        zzt();
        zzu(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("google_signals");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzs(String str) {
        zzt();
        zzu(str);
        if (this.zzd.get(str) != null) {
            return this.zzd.get(str).contains(Constants.AMP_TRACKING_OPTION_OS_VERSION) || this.zzd.get(str).contains("device_info");
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzt(String str) {
        zzt();
        zzu(str);
        return this.zzd.get(str) != null && this.zzd.get(str).contains("user_id");
    }
}
