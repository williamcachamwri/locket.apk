package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.amplitude.api.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzje;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzha extends zzjd {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private long zzaa;
    public zzhe zzb;
    public final zzhb zzc = new zzhb(this, "first_open_time", 0);
    public final zzhb zzd = new zzhb(this, "app_install_time", 0);
    public final zzhd zze = new zzhd(this, "app_instance_id", (String) null);
    public final zzhb zzf = new zzhb(this, "session_timeout", Constants.SESSION_TIMEOUT_MILLIS);
    public final zzgz zzg = new zzgz(this, "start_new_session", true);
    public final zzhd zzh = new zzhd(this, "non_personalized_ads", (String) null);
    public final zzhc zzi = new zzhc(this, "last_received_uri_timestamps_by_source", (Bundle) null);
    public final zzgz zzj = new zzgz(this, "allow_remote_dynamite", false);
    public final zzhb zzk = new zzhb(this, "last_pause_time", 0);
    public final zzhb zzl = new zzhb(this, "session_id", 0);
    public boolean zzm;
    public zzgz zzn = new zzgz(this, "app_backgrounded", false);
    public zzgz zzo = new zzgz(this, "deep_link_retrieval_complete", false);
    public zzhb zzp = new zzhb(this, "deep_link_retrieval_attempts", 0);
    public final zzhd zzq = new zzhd(this, "firebase_feature_rollouts", (String) null);
    public final zzhd zzr = new zzhd(this, "deferred_attribution_cache", (String) null);
    public final zzhb zzs = new zzhb(this, "deferred_attribution_cache_timestamp", 0);
    public final zzhc zzt = new zzhc(this, "default_event_parameters", (Bundle) null);
    private SharedPreferences zzv;
    private Object zzw = new Object();
    private SharedPreferences zzx;
    private String zzy;
    private boolean zzz;

    /* access modifiers changed from: protected */
    public final SharedPreferences zzc() {
        zzt();
        zzac();
        if (this.zzx == null) {
            synchronized (this.zzw) {
                if (this.zzx == null) {
                    String str = zza().getPackageName() + "_preferences";
                    zzj().zzp().zza("Default prefs file", str);
                    this.zzx = zza().getSharedPreferences(str, 0);
                }
            }
        }
        return this.zzx;
    }

    /* access modifiers changed from: protected */
    public final boolean zzh() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final SharedPreferences zzg() {
        zzt();
        zzac();
        Preconditions.checkNotNull(this.zzv);
        return this.zzv;
    }

    /* access modifiers changed from: package-private */
    public final Pair<String, Boolean> zza(String str) {
        zzt();
        if (!zzo().zza(zzje.zza.AD_STORAGE)) {
            return new Pair<>("", false);
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (this.zzy != null && elapsedRealtime < this.zzaa) {
            return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
        }
        this.zzaa = elapsedRealtime + zze().zzd(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zza());
            this.zzy = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.zzy = id;
            }
            this.zzz = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e) {
            zzj().zzc().zza("Unable to get advertising id", e);
            this.zzy = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzy, Boolean.valueOf(this.zzz));
    }

    /* access modifiers changed from: package-private */
    public final SparseArray<Long> zzm() {
        Bundle zza2 = this.zzi.zza();
        if (zza2 == null) {
            return new SparseArray<>();
        }
        int[] intArray = zza2.getIntArray("uriSources");
        long[] longArray = zza2.getLongArray("uriTimestamps");
        if (intArray == null || longArray == null) {
            return new SparseArray<>();
        }
        if (intArray.length != longArray.length) {
            zzj().zzg().zza("Trigger URI source and timestamp array lengths do not match");
            return new SparseArray<>();
        }
        SparseArray<Long> sparseArray = new SparseArray<>();
        for (int i = 0; i < intArray.length; i++) {
            sparseArray.put(intArray[i], Long.valueOf(longArray[i]));
        }
        return sparseArray;
    }

    /* access modifiers changed from: package-private */
    public final zzax zzn() {
        zzt();
        return zzax.zza(zzg().getString("dma_consent_settings", (String) null));
    }

    /* access modifiers changed from: package-private */
    public final zzje zzo() {
        zzt();
        return zzje.zza(zzg().getString("consent_settings", "G1"), zzg().getInt("consent_source", 100));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzp() {
        zzt();
        if (!zzg().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzg().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzu() {
        zzt();
        if (zzg().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzv() {
        zzt();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final String zzw() {
        zzt();
        String string = zzg().getString("previous_os_version", (String) null);
        zzf().zzac();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor edit = zzg().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    public final String zzx() {
        zzt();
        return zzg().getString("admob_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public final String zzy() {
        zzt();
        return zzg().getString("gmp_app_id", (String) null);
    }

    zzha(zzhy zzhy) {
        super(zzhy);
    }

    /* access modifiers changed from: package-private */
    public final void zzz() {
        zzt();
        Boolean zzv2 = zzv();
        SharedPreferences.Editor edit = zzg().edit();
        edit.clear();
        edit.apply();
        if (zzv2 != null) {
            zza(zzv2);
        }
    }

    /* access modifiers changed from: protected */
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    public final void zzaa() {
        SharedPreferences sharedPreferences = zza().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzv = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.zzm = z;
        if (!z) {
            SharedPreferences.Editor edit = this.zzv.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzb = new zzhe(this, "health_monitor", Math.max(0, zzbh.zzc.zza(null).longValue()));
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("admob_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zza(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Boolean bool) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled_from_api");
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzt();
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        zzt();
        zzj().zzp().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzg().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public final void zza(SparseArray<Long> sparseArray) {
        if (sparseArray == null) {
            this.zzi.zza((Bundle) null);
            return;
        }
        int[] iArr = new int[sparseArray.size()];
        long[] jArr = new long[sparseArray.size()];
        for (int i = 0; i < sparseArray.size(); i++) {
            iArr[i] = sparseArray.keyAt(i);
            jArr[i] = sparseArray.valueAt(i).longValue();
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("uriSources", iArr);
        bundle.putLongArray("uriTimestamps", jArr);
        this.zzi.zza(bundle);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzab() {
        SharedPreferences sharedPreferences = this.zzv;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(long j) {
        return j - this.zzf.zza() > this.zzk.zza();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzax zzax) {
        zzt();
        if (!zzje.zza(zzax.zza(), zzn().zza())) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("dma_consent_settings", zzax.zzf());
        edit.apply();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(zzje zzje) {
        zzt();
        int zza2 = zzje.zza();
        if (!zza(zza2)) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("consent_settings", zzje.zzf());
        edit.putInt("consent_source", zza2);
        edit.apply();
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(int i) {
        return zzje.zza(i, zzg().getInt("consent_source", 100));
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zznm zznm) {
        zzt();
        String string = zzg().getString("stored_tcf_param", "");
        String zzc2 = zznm.zzc();
        if (zzc2.equals(string)) {
            return false;
        }
        SharedPreferences.Editor edit = zzg().edit();
        edit.putString("stored_tcf_param", zzc2);
        edit.apply();
        return true;
    }
}
