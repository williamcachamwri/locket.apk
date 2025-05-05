package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.facebook.hermes.intl.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.internal.measurement.zznm;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpu;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzje;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import expo.modules.contacts.Columns;
import io.sentry.protocol.App;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzjq extends zzh {
    final zzu zza;
    private zzkz zzb;
    private zzjm zzc;
    private final Set<zzjl> zzd = new CopyOnWriteArraySet();
    private boolean zze;
    private final AtomicReference<String> zzf = new AtomicReference<>();
    private final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh = false;
    /* access modifiers changed from: private */
    public int zzi = 1;
    private zzav zzj;
    private PriorityQueue<zzno> zzk;
    private boolean zzl;
    private zzje zzm = zzje.zza;
    private final AtomicLong zzn = new AtomicLong(0);
    private long zzo = -1;
    private boolean zzp = true;
    /* access modifiers changed from: private */
    public zzav zzq;
    private SharedPreferences.OnSharedPreferenceChangeListener zzr;
    private zzav zzs;
    private final zzor zzt = new zzkr(this);

    public static int zza(String str) {
        Preconditions.checkNotEmpty(str);
        return 25;
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    public final Application.ActivityLifecycleCallbacks zzaa() {
        return this.zzb;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    public final zzaj zzab() {
        zzt();
        return zzo().zzaa();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
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

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    static /* synthetic */ int zza(zzjq zzjq, Throwable th) {
        String message = th.getMessage();
        zzjq.zzl = false;
        if (message == null) {
            return 2;
        }
        if ((th instanceof IllegalStateException) || message.contains("garbage collected") || th.getClass().getSimpleName().equals("ServiceUnavailableException")) {
            if (message.contains("Background")) {
                zzjq.zzl = true;
            }
            return 1;
        } else if (!(th instanceof SecurityException) || message.endsWith("READ_DEVICE_CONFIG")) {
            return 2;
        } else {
            return 3;
        }
    }

    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    public final Boolean zzac() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "boolean test flag value", new zzka(this, atomicReference));
    }

    public final Double zzad() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "double test flag value", new zzkw(this, atomicReference));
    }

    public final Integer zzae() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "int test flag value", new zzkt(this, atomicReference));
    }

    public final Long zzaf() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "long test flag value", new zzku(this, atomicReference));
    }

    public final String zzag() {
        return this.zzf.get();
    }

    public final String zzah() {
        zzlk zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zzb;
        }
        return null;
    }

    public final String zzai() {
        zzlk zzaa = this.zzu.zzq().zzaa();
        if (zzaa != null) {
            return zzaa.zza;
        }
        return null;
    }

    public final String zzaj() {
        if (this.zzu.zzu() != null) {
            return this.zzu.zzu();
        }
        try {
            return new zzhs(zza(), this.zzu.zzx()).zza("google_app_id");
        } catch (IllegalStateException e) {
            this.zzu.zzj().zzg().zza("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    public final String zzak() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzl().zza(atomicReference, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS, "String test flag value", new zzkj(this, atomicReference));
    }

    public final ArrayList<Bundle> zza(String str, String str2) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get conditional user properties", new zzkq(this, atomicReference, (String) null, str, str2));
            List list = (List) atomicReference.get();
            if (list != null) {
                return zzos.zzb((List<zzae>) list);
            }
            zzj().zzg().zza("Timed out waiting for get conditional user properties", (Object) null);
            return new ArrayList<>();
        }
    }

    public final List<zzon> zza(boolean z) {
        zzu();
        zzj().zzp().zza("Getting user properties (FE)");
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get all user properties from main thread");
            return Collections.emptyList();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzkk(this, atomicReference, z));
            List<zzon> list = (List) atomicReference.get();
            if (list != null) {
                return list;
            }
            zzj().zzg().zza("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyList();
        }
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        if (zzl().zzg()) {
            zzj().zzg().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        } else if (zzab.zza()) {
            zzj().zzg().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        } else {
            AtomicReference atomicReference = new AtomicReference();
            this.zzu.zzl().zza(atomicReference, 5000, "get user properties", new zzkp(this, atomicReference, (String) null, str, str2, z));
            List<zzon> list = (List) atomicReference.get();
            if (list == null) {
                zzj().zzg().zza("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyMap();
            }
            ArrayMap arrayMap = new ArrayMap(list.size());
            for (zzon zzon : list) {
                Object zza2 = zzon.zza();
                if (zza2 != null) {
                    arrayMap.put(zzon.zza, zza2);
                }
            }
            return arrayMap;
        }
    }

    /* access modifiers changed from: package-private */
    public final PriorityQueue<zzno> zzal() {
        if (this.zzk == null) {
            this.zzk = new PriorityQueue<>(Comparator.comparing(new zzjp(), new zzjs()));
        }
        return this.zzk;
    }

    static /* synthetic */ void zza(zzjq zzjq, Bundle bundle) {
        Bundle bundle2 = bundle;
        zzjq.zzt();
        zzjq.zzu();
        Preconditions.checkNotNull(bundle);
        String checkNotEmpty = Preconditions.checkNotEmpty(bundle2.getString("name"));
        if (!zzjq.zzu.zzac()) {
            zzjq.zzj().zzp().zza("Conditional property not cleared since app measurement is disabled");
            return;
        }
        zzon zzon = new zzon(checkNotEmpty, 0, (Object) null, "");
        try {
            zzbf zza2 = zzjq.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), "", bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), true, true);
            String string = bundle2.getString("app_id");
            long j = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP);
            boolean z = bundle2.getBoolean("active");
            String string2 = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
            zzae zzae = r4;
            zzae zzae2 = new zzae(string, "", zzon, j, z, string2, (zzbf) null, j2, (zzbf) null, j3, zza2);
            zzjq.zzo().zza(zzae);
        } catch (IllegalArgumentException unused) {
        }
    }

    static /* synthetic */ void zza(zzjq zzjq, zzje zzje, zzje zzje2) {
        if (!zznm.zza() || !zzjq.zze().zza(zzbh.zzcx)) {
            boolean zza2 = zzje.zza(zzje2, zzje.zza.ANALYTICS_STORAGE, zzje.zza.AD_STORAGE);
            boolean zzb2 = zzje.zzb(zzje2, zzje.zza.ANALYTICS_STORAGE, zzje.zza.AD_STORAGE);
            if (zza2 || zzb2) {
                zzjq.zzg().zzag();
            }
        }
    }

    static /* synthetic */ void zzb(zzjq zzjq, Bundle bundle) {
        Bundle bundle2 = bundle;
        zzjq.zzt();
        zzjq.zzu();
        Preconditions.checkNotNull(bundle);
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("origin");
        Preconditions.checkNotEmpty(string);
        Preconditions.checkNotEmpty(string2);
        Preconditions.checkNotNull(bundle2.get("value"));
        if (!zzjq.zzu.zzac()) {
            zzjq.zzj().zzp().zza("Conditional property not set since app measurement is disabled");
            return;
        }
        zzon zzon = new zzon(string, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP), bundle2.get("value"), string2);
        try {
            zzbf zza2 = zzjq.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS), string2, 0, true, true);
            zzbf zza3 = zzjq.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS), string2, 0, true, true);
            zzbf zza4 = zzjq.zzq().zza(bundle2.getString("app_id"), bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME), bundle2.getBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS), string2, 0, true, true);
            zzjq.zzo().zza(new zzae(bundle2.getString("app_id"), string2, zzon, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP), false, bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME), zza3, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT), zza2, bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE), zza4));
        } catch (IllegalArgumentException unused) {
        }
    }

    static /* synthetic */ void zza(zzjq zzjq, zzje zzje, long j, boolean z, boolean z2) {
        zzjq.zzt();
        zzjq.zzu();
        zzje zzo2 = zzjq.zzk().zzo();
        if (j <= zzjq.zzo && zzje.zza(zzo2.zza(), zzje.zza())) {
            zzjq.zzj().zzo().zza("Dropped out-of-date consent setting, proposed settings", zzje);
        } else if (zzjq.zzk().zza(zzje)) {
            zzjq.zzj().zzp().zza("Setting storage consent(FE)", zzje);
            zzjq.zzo = j;
            if (zzjq.zzo().zzao()) {
                zzjq.zzo().zzb(z);
            } else {
                zzjq.zzo().zza(z);
            }
            if (z2) {
                zzjq.zzo().zza((AtomicReference<String>) new AtomicReference());
            }
        } else {
            zzjq.zzj().zzo().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzje.zza()));
        }
    }

    static /* synthetic */ void zzb(zzjq zzjq, int i) {
        if (zzjq.zzj == null) {
            zzjq.zzj = new zzkb(zzjq, zzjq.zzu);
        }
        zzjq.zzj.zza((long) (i * 1000));
    }

    protected zzjq(zzhy zzhy) {
        super(zzhy);
        this.zza = new zzu(zzhy);
    }

    public final void zzam() {
        zzt();
        zzu();
        if (zze().zza(zzbh.zzdd)) {
            zzls zzo2 = zzo();
            zzo2.zzt();
            zzo2.zzu();
            boolean z = true;
            if (zzo2.zzap() && zzo2.zzq().zzg() < 242600) {
                z = false;
            }
            if (z) {
                zzo().zzac();
            }
        }
    }

    public final void zzan() {
        zzt();
        zzu();
        if (this.zzu.zzaf()) {
            Boolean zze2 = zze().zze("google_analytics_deferred_deep_link_enabled");
            if (zze2 != null && zze2.booleanValue()) {
                zzj().zzc().zza("Deferred Deep Link feature enabled.");
                zzl().zzb((Runnable) new zzjv(this));
            }
            zzo().zzad();
            this.zzp = false;
            String zzw = zzk().zzw();
            if (!TextUtils.isEmpty(zzw)) {
                zzf().zzac();
                if (!zzw.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzw);
                    zzc("auto", "_ou", bundle);
                }
            }
        }
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

    public final void zza(String str, String str2, Bundle bundle) {
        long currentTimeMillis = zzb().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        zzl().zzb((Runnable) new zzkn(this, bundle2));
    }

    public final void zzao() {
        if ((zza().getApplicationContext() instanceof Application) && this.zzb != null) {
            ((Application) zza().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zzb);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzap() {
        if (zzpn.zza() && zze().zza(zzbh.zzci)) {
            if (zzl().zzg()) {
                zzj().zzg().zza("Cannot get trigger URIs from analytics worker thread");
            } else if (zzab.zza()) {
                zzj().zzg().zza("Cannot get trigger URIs from main thread");
            } else {
                zzu();
                zzj().zzp().zza("Getting trigger URIs (FE)");
                AtomicReference atomicReference = new AtomicReference();
                zzl().zza(atomicReference, 5000, "get trigger URIs", new zzjr(this, atomicReference));
                List list = (List) atomicReference.get();
                if (list == null) {
                    zzj().zzg().zza("Timed out waiting for get trigger URIs");
                } else {
                    zzl().zzb((Runnable) new zzju(this, list));
                }
            }
        }
    }

    public final void zzaq() {
        zzt();
        if (zzk().zzo.zza()) {
            zzj().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza2 = zzk().zzp.zza();
        zzk().zzp.zza(1 + zza2);
        if (zza2 >= 5) {
            zzj().zzu().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzk().zzo.zza(true);
            return;
        }
        if (this.zzq == null) {
            this.zzq = new zzkm(this, this.zzu);
        }
        this.zzq.zza(0);
    }

    public final void zza(zzdo zzdo) throws RemoteException {
        zzl().zzb((Runnable) new zzks(this, zzdo));
    }

    public final void zzar() {
        zzt();
        zzj().zzc().zza("Handle tcf update.");
        zznm zza2 = zznm.zza(zzk().zzc());
        zzj().zzp().zza("Tcf preferences read", zza2);
        if (zzk().zza(zza2)) {
            Bundle zza3 = zza2.zza();
            zzj().zzp().zza("Consent generated from Tcf", zza3);
            if (zza3 != Bundle.EMPTY) {
                zza(zza3, -30, zzb().currentTimeMillis());
            }
            Bundle bundle = new Bundle();
            bundle.putString("_tcfd", zza2.zzb());
            zzc("auto", "_tcf", bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(AtomicReference atomicReference) {
        Bundle zza2 = zzk().zzi.zza();
        zzls zzo2 = zzo();
        if (zza2 == null) {
            zza2 = new Bundle();
        }
        zzo2.zza((AtomicReference<List<zzno>>) atomicReference, zza2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(List list) {
        zzt();
        if (Build.VERSION.SDK_INT >= 30) {
            SparseArray<Long> zzm2 = zzk().zzm();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                zzno zzno = (zzno) it.next();
                if (!zzm2.contains(zzno.zzc) || zzm2.get(zzno.zzc).longValue() < zzno.zzb) {
                    zzal().add(zzno);
                }
            }
            zzas();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        if ("IABTCF_TCString".equals(str)) {
            zzj().zzp().zza("IABTCF_TCString change picked up in listener.");
            ((zzav) Preconditions.checkNotNull(this.zzs)).zza(500);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle, long j) {
        if (TextUtils.isEmpty(zzg().zzae())) {
            zza(bundle, 0, j);
        } else {
            zzj().zzv().zza("Using developer consent only; google app id found");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(Bundle bundle) {
        Bundle bundle2;
        if (bundle.isEmpty()) {
            bundle2 = bundle;
        } else {
            bundle2 = zzk().zzt.zza();
            if (zze().zza(zzbh.zzdh)) {
                bundle2 = new Bundle(bundle2);
            }
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                    zzq();
                    if (zzos.zza(obj)) {
                        zzq();
                        zzos.zza(this.zzt, 27, (String) null, (String) null, 0);
                    }
                    zzj().zzv().zza("Invalid default event parameter type. Name, value", str, obj);
                } else if (zzos.zzg(str)) {
                    zzj().zzv().zza("Invalid default event parameter name. Name", str);
                } else if (obj == null) {
                    bundle2.remove(str);
                } else if (zzq().zza("param", str, zze().zza((String) null, false), obj)) {
                    zzq().zza(bundle2, str, obj);
                }
            }
            zzq();
            if (zzos.zza(bundle2, zze().zzc())) {
                zzq();
                zzos.zza(this.zzt, 26, (String) null, (String) null, 0);
                zzj().zzv().zza("Too many default event parameters set. Discarding beyond event parameter limit");
            }
        }
        zzk().zzt.zza(bundle2);
        if (!bundle.isEmpty() || zze().zza(zzbh.zzdf)) {
            zzo().zza(bundle2);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str) {
        if (zzg().zzb(str)) {
            zzg().zzag();
        }
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, zzb().currentTimeMillis());
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3 = str == null ? App.TYPE : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        String str4 = str2;
        if (Objects.equals(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            zzn().zza(bundle2, j);
            return;
        }
        long j2 = j;
        zzb(str3, str2, j, bundle2, z2, !z2 || this.zzc == null || zzos.zzg(str2), z, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, String str3) {
        zzs();
        zzb(str, str2, zzb().currentTimeMillis(), bundle, false, true, true, str3);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, j);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str, String str2, Bundle bundle) {
        zzt();
        zza(str, str2, zzb().currentTimeMillis(), bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, long j, Bundle bundle) {
        zzt();
        zza(str, str2, j, bundle, true, this.zzc == null || zzos.zzg(str2), true, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        boolean z4;
        String str4;
        long j2;
        String str5;
        String str6;
        boolean z5;
        Class<?> cls;
        String str7 = str;
        String str8 = str2;
        long j3 = j;
        Bundle bundle2 = bundle;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzt();
        zzu();
        if (!this.zzu.zzac()) {
            zzj().zzc().zza("Event not sent since app measurement is disabled");
            return;
        }
        List<String> zzaf = zzg().zzaf();
        if (zzaf == null || zzaf.contains(str8)) {
            int i = 0;
            boolean z6 = true;
            if (!this.zze) {
                this.zze = true;
                try {
                    if (!this.zzu.zzag()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, zza().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke((Object) null, new Object[]{zza()});
                    } catch (Exception e) {
                        zzj().zzu().zza("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException unused) {
                    zzj().zzo().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if ("_cmp".equals(str8) && bundle2.containsKey("gclid")) {
                zza("auto", "_lgclid", (Object) bundle2.getString("gclid"), zzb().currentTimeMillis());
            }
            if (z && zzos.zzj(str2)) {
                zzq().zza(bundle2, zzk().zzt.zza());
            }
            if (!z3 && !"_iap".equals(str8)) {
                zzos zzt2 = this.zzu.zzt();
                int i2 = 2;
                if (zzt2.zzc(NotificationCompat.CATEGORY_EVENT, str8)) {
                    if (!zzt2.zza(NotificationCompat.CATEGORY_EVENT, zzji.zza, zzji.zzb, str8)) {
                        i2 = 13;
                    } else if (zzt2.zza(NotificationCompat.CATEGORY_EVENT, 40, str8)) {
                        i2 = 0;
                    }
                }
                if (i2 != 0) {
                    zzj().zzm().zza("Invalid public event name. Event will not be logged (FE)", zzi().zza(str8));
                    this.zzu.zzt();
                    String zza2 = zzos.zza(str8, 40, true);
                    if (str8 != null) {
                        i = str2.length();
                    }
                    this.zzu.zzt();
                    zzos.zza(this.zzt, i2, "_ev", zza2, i);
                    return;
                }
            }
            zzlk zza3 = zzn().zza(false);
            if (zza3 != null && !bundle2.containsKey("_sc")) {
                zza3.zzd = true;
            }
            zzos.zza(zza3, bundle2, z && !z3);
            boolean equals = "am".equals(str7);
            boolean zzg2 = zzos.zzg(str2);
            if (z && this.zzc != null && !zzg2 && !equals) {
                zzj().zzc().zza("Passing event to registered event handler (FE)", zzi().zza(str8), zzi().zza(bundle2));
                Preconditions.checkNotNull(this.zzc);
                this.zzc.interceptEvent(str, str2, bundle, j);
            } else if (this.zzu.zzaf()) {
                int zza4 = zzq().zza(str8);
                if (zza4 != 0) {
                    zzj().zzm().zza("Invalid event name. Event will not be logged (FE)", zzi().zza(str8));
                    zzq();
                    String zza5 = zzos.zza(str8, 40, true);
                    if (str8 != null) {
                        i = str2.length();
                    }
                    this.zzu.zzt();
                    zzos.zza(this.zzt, str3, zza4, "_ev", zza5, i);
                    return;
                }
                String str9 = "_o";
                Bundle zza6 = zzq().zza(str3, str2, bundle, (List<String>) CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
                Preconditions.checkNotNull(zza6);
                if (zzn().zza(false) != null && "_ae".equals(str8)) {
                    zznh zznh = zzp().zzb;
                    long elapsedRealtime = zznh.zzb.zzb().elapsedRealtime();
                    long j4 = elapsedRealtime - zznh.zza;
                    zznh.zza = elapsedRealtime;
                    if (j4 > 0) {
                        zzq().zza(zza6, j4);
                    }
                }
                if (!"auto".equals(str7) && "_ssr".equals(str8)) {
                    zzos zzq2 = zzq();
                    String string = zza6.getString("_ffr");
                    if (Strings.isEmptyOrWhitespace(string)) {
                        string = null;
                    } else if (string != null) {
                        string = string.trim();
                    }
                    if (Objects.equals(string, zzq2.zzk().zzq.zza())) {
                        zzq2.zzj().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        z5 = false;
                    } else {
                        zzq2.zzk().zzq.zza(string);
                        z5 = true;
                    }
                    if (!z5) {
                        return;
                    }
                } else if ("_ae".equals(str8)) {
                    String zza7 = zzq().zzk().zzq.zza();
                    if (!TextUtils.isEmpty(zza7)) {
                        zza6.putString("_ffr", zza7);
                    }
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(zza6);
                if (zze().zza(zzbh.zzco)) {
                    z4 = zzp().zzaa();
                } else {
                    z4 = zzk().zzn.zza();
                }
                if (zzk().zzk.zza() <= 0 || !zzk().zza(j3) || !z4) {
                    str4 = "_ae";
                    j2 = 0;
                } else {
                    zzj().zzp().zza("Current session is expired, remove the session number, ID, and engagement time");
                    j2 = 0;
                    str4 = "_ae";
                    zza("auto", "_sid", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_sno", (Object) null, zzb().currentTimeMillis());
                    zza("auto", "_se", (Object) null, zzb().currentTimeMillis());
                    zzk().zzl.zza(0);
                }
                if (zza6.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j2) == 1) {
                    zzj().zzp().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzu.zzs().zza.zza(j3, true);
                }
                ArrayList arrayList2 = new ArrayList(zza6.keySet());
                Collections.sort(arrayList2);
                ArrayList arrayList3 = arrayList2;
                int size = arrayList2.size();
                int i3 = 0;
                while (i3 < size) {
                    Object obj = arrayList2.get(i3);
                    i3++;
                    String str10 = (String) obj;
                    if (str10 != null) {
                        zzq();
                        Bundle[] zzb2 = zzos.zzb(zza6.get(str10));
                        if (zzb2 != null) {
                            zza6.putParcelableArray(str10, zzb2);
                        }
                    }
                }
                int i4 = 0;
                while (i4 < arrayList.size()) {
                    Bundle bundle3 = (Bundle) arrayList.get(i4);
                    if (i4 != 0 ? z6 : false) {
                        str6 = "_ep";
                        str5 = str;
                    } else {
                        str5 = str;
                        str6 = str2;
                    }
                    bundle3.putString(str9, str5);
                    if (z2) {
                        bundle3 = zzq().zza(bundle3, (String) null);
                    }
                    Bundle bundle4 = bundle3;
                    Bundle bundle5 = bundle4;
                    zzbf zzbf = r1;
                    zzbf zzbf2 = new zzbf(str6, new zzbe(bundle4), str, j);
                    zzo().zza(zzbf, str3);
                    if (!equals) {
                        for (zzjl onEvent : this.zzd) {
                            onEvent.onEvent(str, str2, new Bundle(bundle5), j);
                            String str11 = str3;
                        }
                    }
                    i4++;
                    z6 = true;
                }
                if (zzn().zza(false) != null && str4.equals(str2)) {
                    zzp().zza(true, true, zzb().elapsedRealtime());
                }
            }
        } else {
            zzj().zzc().zza("Dropping non-safelisted event. event name, origin", str8, str7);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzas() {
        zzno poll;
        MeasurementManagerFutures zzo2;
        zzt();
        this.zzl = false;
        if (!zzal().isEmpty() && !this.zzh && (poll = zzal().poll()) != null && (zzo2 = zzq().zzo()) != null) {
            this.zzh = true;
            zzj().zzp().zza("Registering trigger URI", poll.zza);
            ListenableFuture<Unit> registerTriggerAsync = zzo2.registerTriggerAsync(Uri.parse(poll.zza));
            if (registerTriggerAsync == null) {
                this.zzh = false;
                zzal().add(poll);
                return;
            }
            if (!zze().zza(zzbh.zzcn)) {
                SparseArray<Long> zzm2 = zzk().zzm();
                zzm2.put(poll.zzc, Long.valueOf(poll.zzb));
                zzk().zza(zzm2);
            }
            Futures.addCallback(registerTriggerAsync, new zzkc(this, poll), new zzjz(this));
        }
    }

    public final void zza(zzjl zzjl) {
        zzu();
        Preconditions.checkNotNull(zzjl);
        if (!this.zzd.add(zzjl)) {
            zzj().zzu().zza("OnEventListener already registered");
        }
    }

    public final void zzat() {
        zzt();
        zzj().zzc().zza("Register tcfPrefChangeListener.");
        if (this.zzr == null) {
            this.zzs = new zzkf(this, this.zzu);
            this.zzr = new zzjy(this);
        }
        zzk().zzc().registerOnSharedPreferenceChangeListener(this.zzr);
    }

    public final void zza(long j) {
        zzc((String) null);
        zzl().zzb((Runnable) new zzkl(this, j));
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j) {
        zza(j, true);
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j, boolean z) {
        zzt();
        zzu();
        zzj().zzc().zza("Resetting analytics data (FE)");
        zznb zzp2 = zzp();
        zzp2.zzt();
        zzp2.zzb.zza();
        zzg().zzag();
        boolean zzac = this.zzu.zzac();
        zzha zzk2 = zzk();
        zzk2.zzc.zza(j);
        if (!TextUtils.isEmpty(zzk2.zzk().zzq.zza())) {
            zzk2.zzq.zza((String) null);
        }
        zzk2.zzk.zza(0);
        zzk2.zzl.zza(0);
        if (!zzk2.zze().zzx()) {
            zzk2.zzb(!zzac);
        }
        zzk2.zzr.zza((String) null);
        zzk2.zzs.zza(0);
        zzk2.zzt.zza((Bundle) null);
        if (z) {
            zzo().zzai();
        }
        zzp().zza.zza();
        this.zzp = !zzac;
    }

    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzl().zzb((Runnable) new zzki(this, str, str2, j, zzos.zza(bundle), z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzl().zzb((Runnable) new zzkh(this, str, str2, obj, j));
    }

    public final void zzb(boolean z) {
        if (zza().getApplicationContext() instanceof Application) {
            Application application = (Application) zza().getApplicationContext();
            if (this.zzb == null) {
                this.zzb = new zzkz(this);
            }
            if (z) {
                application.unregisterActivityLifecycleCallbacks(this.zzb);
                application.registerActivityLifecycleCallbacks(this.zzb);
                zzj().zzp().zza("Registered activity lifecycle callback");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        this.zzf.set(str);
    }

    public final void zzb(Bundle bundle) {
        zzb(bundle, zzb().currentTimeMillis());
    }

    public final void zzb(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            zzj().zzu().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzjf.zza(bundle2, "app_id", String.class, null);
        zzjf.zza(bundle2, "origin", String.class, null);
        zzjf.zza(bundle2, "name", String.class, null);
        zzjf.zza(bundle2, "value", Object.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzjf.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString("name"));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString("name");
        Object obj = bundle2.get("value");
        if (zzq().zzb(string) != 0) {
            zzj().zzg().zza("Invalid conditional user property name", zzi().zzc(string));
        } else if (zzq().zza(string, obj) != 0) {
            zzj().zzg().zza("Invalid conditional user property value", zzi().zzc(string), obj);
        } else {
            Object zzc2 = zzq().zzc(string, obj);
            if (zzc2 == null) {
                zzj().zzg().zza("Unable to normalize conditional user property value", zzi().zzc(string), obj);
                return;
            }
            zzjf.zza(bundle2, zzc2);
            long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
            if (TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME)) || (j2 <= 15552000000L && j2 >= 1)) {
                long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                if (j3 > 15552000000L || j3 < 1) {
                    zzj().zzg().zza("Invalid conditional user property time to live", zzi().zzc(string), Long.valueOf(j3));
                } else {
                    zzl().zzb((Runnable) new zzko(this, bundle2));
                }
            } else {
                zzj().zzg().zza("Invalid conditional user property timeout", zzi().zzc(string), Long.valueOf(j2));
            }
        }
    }

    public final void zzc(Bundle bundle, long j) {
        zzl().zzc((Runnable) new zzjw(this, bundle, j));
    }

    private final void zza(Bundle bundle, int i, long j) {
        zzu();
        String zza2 = zzje.zza(bundle);
        if (zza2 != null) {
            zzj().zzv().zza("Ignoring invalid consent setting", zza2);
            zzj().zzv().zza("Valid consent values are 'granted', 'denied'");
        }
        boolean zzg2 = zzl().zzg();
        zzje zza3 = zzje.zza(bundle, i);
        if (zza3.zzi()) {
            zza(zza3, j, zzg2);
        }
        zzax zza4 = zzax.zza(bundle, i);
        if (zza4.zzg()) {
            zza(zza4, zzg2);
        }
        Boolean zza5 = zzax.zza(bundle);
        if (zza5 != null) {
            String str = i == -30 ? "tcf" : App.TYPE;
            if (!zze().zza(zzbh.zzcs) || !zzg2) {
                zza(str, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, (Object) zza5.toString(), false, j);
            } else {
                zza(str, FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS, (Object) zza5.toString(), j);
            }
        }
    }

    public final void zzd(Bundle bundle, long j) {
        zza(bundle, -20, j);
    }

    public final void zzc(boolean z) {
        zzu();
        zzl().zzb((Runnable) new zzke(this, z));
    }

    public final void zzc(Bundle bundle) {
        zzl().zzb((Runnable) new zzjt(this, bundle == null ? new Bundle() : new Bundle(bundle)));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzax zzax, boolean z) {
        zzky zzky = new zzky(this, zzax);
        if (z) {
            zzt();
            zzky.run();
            return;
        }
        zzl().zzb((Runnable) zzky);
    }

    public final void zza(zzjm zzjm) {
        zzjm zzjm2;
        zzt();
        zzu();
        if (!(zzjm == null || zzjm == (zzjm2 = this.zzc))) {
            Preconditions.checkState(zzjm2 == null, "EventInterceptor already set.");
        }
        this.zzc = zzjm;
    }

    public final void zza(Boolean bool) {
        zzu();
        zzl().zzb((Runnable) new zzkv(this, bool));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzje zzje) {
        zzt();
        boolean z = (zzje.zzh() && zzje.zzg()) || zzo().zzan();
        if (z != this.zzu.zzad()) {
            this.zzu.zzb(z);
            Boolean zzu = zzk().zzu();
            if (!z || zzu == null || zzu.booleanValue()) {
                zza(Boolean.valueOf(z), false);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Boolean bool, boolean z) {
        zzt();
        zzu();
        zzj().zzc().zza("Setting app measurement enabled (FE)", bool);
        zzk().zza(bool);
        if (z) {
            zzk().zzb(bool);
        }
        if (this.zzu.zzad() || (bool != null && !bool.booleanValue())) {
            zzav();
        }
    }

    public final void zzc(long j) {
        zzl().zzb((Runnable) new zzkg(this, j));
    }

    public final void zza(Intent intent) {
        if (zzpu.zza() && zze().zza(zzbh.zzby)) {
            Uri data = intent.getData();
            if (data == null) {
                zzj().zzo().zza("Activity intent has no data. Preview Mode was not enabled.");
                return;
            }
            String queryParameter = data.getQueryParameter("sgtm_debug_enable");
            if (queryParameter == null || !queryParameter.equals("1")) {
                zzj().zzo().zza("Preview Mode was not enabled.");
                zze().zzh((String) null);
                return;
            }
            String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
            if (!TextUtils.isEmpty(queryParameter2)) {
                zzj().zzo().zza("Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
                zze().zzh(queryParameter2);
            }
        }
    }

    public final void zza(zzje zzje, long j, boolean z) {
        zzje zzje2;
        boolean z2;
        boolean z3;
        boolean z4;
        zzje zzje3 = zzje;
        zzu();
        int zza2 = zzje.zza();
        if (zza2 != -10 && zzje.zzc() == zzjh.UNINITIALIZED && zzje.zzd() == zzjh.UNINITIALIZED) {
            zzj().zzv().zza("Ignoring empty consent settings");
            return;
        }
        synchronized (this.zzg) {
            zzje2 = this.zzm;
            z2 = false;
            if (zzje.zza(zza2, zzje2.zza())) {
                z4 = zzje.zzc(this.zzm);
                if (zzje.zzh() && !this.zzm.zzh()) {
                    z2 = true;
                }
                zzje3 = zzje.zzb(this.zzm);
                this.zzm = zzje3;
                z3 = z2;
                z2 = true;
            } else {
                z4 = false;
                z3 = false;
            }
        }
        if (!z2) {
            zzj().zzo().zza("Ignoring lower-priority consent settings, proposed settings", zzje3);
            return;
        }
        long andIncrement = this.zzn.getAndIncrement();
        if (z4) {
            zzc((String) null);
            zzkx zzkx = new zzkx(this, zzje3, j, andIncrement, z3, zzje2);
            if (z) {
                zzt();
                zzkx.run();
                return;
            }
            zzl().zzc((Runnable) zzkx);
            return;
        }
        zzla zzla = new zzla(this, zzje3, andIncrement, z3, zzje2);
        if (z) {
            zzt();
            zzla.run();
        } else if (zza2 == 30 || zza2 == -10) {
            zzl().zzc((Runnable) zzla);
        } else {
            zzl().zzb((Runnable) zzla);
        }
    }

    public final void zza(String str, long j) {
        if (str == null || !TextUtils.isEmpty(str)) {
            zzl().zzb((Runnable) new zzjx(this, str));
            zza((String) null, Columns.ID, (Object) str, true, j);
            return;
        }
        this.zzu.zzj().zzu().zza("User ID must be non-empty or null");
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza(str, str2, obj, z, zzb().currentTimeMillis());
    }

    public final void zza(String str, String str2, Object obj, boolean z, long j) {
        int i;
        if (str == null) {
            str = App.TYPE;
        }
        String str3 = str;
        int i2 = 0;
        if (z) {
            i = zzq().zzb(str2);
        } else {
            zzos zzq2 = zzq();
            if (zzq2.zzc("user property", str2)) {
                if (!zzq2.zza("user property", zzjj.zza, str2)) {
                    i = 15;
                } else if (zzq2.zza("user property", 24, str2)) {
                    i = 0;
                }
            }
            i = 6;
        }
        if (i != 0) {
            zzq();
            String zza2 = zzos.zza(str2, 24, true);
            if (str2 != null) {
                i2 = str2.length();
            }
            this.zzu.zzt();
            zzos.zza(this.zzt, i, "_ev", zza2, i2);
        } else if (obj != null) {
            int zza3 = zzq().zza(str2, obj);
            if (zza3 != 0) {
                zzq();
                String zza4 = zzos.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i2 = String.valueOf(obj).length();
                }
                this.zzu.zzt();
                zzos.zza(this.zzt, zza3, "_ev", zza4, i2);
                return;
            }
            Object zzc2 = zzq().zzc(str2, obj);
            if (zzc2 != null) {
                zza(str3, str2, j, zzc2);
            }
        } else {
            zza(str3, str2, j, (Object) null);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzu();
        if (FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS.equals(str2)) {
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!TextUtils.isEmpty(str3)) {
                    String lowerCase = str3.toLowerCase(Locale.ENGLISH);
                    String str4 = Constants.CASEFIRST_FALSE;
                    Long valueOf = Long.valueOf(str4.equals(lowerCase) ? 1 : 0);
                    zzhd zzhd = zzk().zzh;
                    Long l = valueOf;
                    if (valueOf.longValue() == 1) {
                        str4 = "true";
                    }
                    zzhd.zza(str4);
                    obj = valueOf;
                    str2 = "_npa";
                    zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
                }
            }
            if (obj == null) {
                zzk().zzh.zza("unset");
                str2 = "_npa";
            }
            zzj().zzp().zza("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
        }
        String str5 = str2;
        Object obj2 = obj;
        if (!this.zzu.zzac()) {
            zzj().zzp().zza("User property not set since app measurement is disabled");
        } else if (this.zzu.zzaf()) {
            zzo().zza(new zzon(str5, j, obj2, str));
        }
    }

    public final void zzb(zzjl zzjl) {
        zzu();
        Preconditions.checkNotNull(zzjl);
        if (!this.zzd.remove(zzjl)) {
            zzj().zzu().zza("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: private */
    public final void zzav() {
        zzt();
        String zza2 = zzk().zzh.zza();
        if (zza2 != null) {
            if ("unset".equals(zza2)) {
                zza(App.TYPE, "_npa", (Object) null, zzb().currentTimeMillis());
            } else {
                zza(App.TYPE, "_npa", (Object) Long.valueOf("true".equals(zza2) ? 1 : 0), zzb().currentTimeMillis());
            }
        }
        if (!this.zzu.zzac() || !this.zzp) {
            zzj().zzc().zza("Updating Scion state (FE)");
            zzo().zzak();
            return;
        }
        zzj().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
        zzan();
        zzp().zza.zza();
        zzl().zzb((Runnable) new zzkd(this));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzau() {
        return this.zzl;
    }
}
