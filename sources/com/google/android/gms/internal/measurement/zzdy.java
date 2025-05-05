package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.media3.common.C;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhs;
import com.google.android.gms.measurement.internal.zzjl;
import com.google.android.gms.measurement.internal.zzjm;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
public class zzdy {
    private static volatile zzdy zzb;
    protected final Clock zza;
    /* access modifiers changed from: private */
    public final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private final List<Pair<zzjl, zzb>> zzf;
    private int zzg;
    /* access modifiers changed from: private */
    public boolean zzh;
    private String zzi;
    /* access modifiers changed from: private */
    public volatile zzdj zzj;

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
    static class zzb extends zzds {
        private final zzjl zza;

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzb(zzjl zzjl) {
            this.zza = zzjl;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.onEvent(str, str2, bundle, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
    static class zzc extends zzds {
        private final zzjm zza;

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        zzc(zzjm zzjm) {
            this.zza = zzjm;
        }

        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.interceptEvent(str, str2, bundle, j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
    class zzd implements Application.ActivityLifecycleCallbacks {
        zzd() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzdy.this.zza((zza) new zzfg(this, bundle, activity));
        }

        public final void onActivityDestroyed(Activity activity) {
            zzdy.this.zza((zza) new zzfl(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzdy.this.zza((zza) new zzfk(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzdy.this.zza((zza) new zzfh(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzdk zzdk = new zzdk();
            zzdy.this.zza((zza) new zzfm(this, activity, zzdk));
            Bundle zza2 = zzdk.zza(50);
            if (zza2 != null) {
                bundle.putAll(zza2);
            }
        }

        public final void onActivityStarted(Activity activity) {
            zzdy.this.zza((zza) new zzfi(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzdy.this.zza((zza) new zzfj(this, activity));
        }
    }

    public final int zza(String str) {
        zzdk zzdk = new zzdk();
        zza((zza) new zzev(this, str, zzdk));
        Integer num = (Integer) zzdk.zza(zzdk.zza(10000), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
    abstract class zza implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        zza(zzdy zzdy) {
            this(true);
        }

        /* access modifiers changed from: package-private */
        public abstract void zza() throws RemoteException;

        /* access modifiers changed from: protected */
        public void zzb() {
        }

        zza(boolean z) {
            this.zza = zzdy.this.zza.currentTimeMillis();
            this.zzb = zzdy.this.zza.elapsedRealtime();
            this.zzc = z;
        }

        public void run() {
            if (zzdy.this.zzh) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e) {
                zzdy.this.zza(e, false, this.zzc);
                zzb();
            }
        }
    }

    public final long zza() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzeq(this, zzdk));
        Long zzb2 = zzdk.zzb(500);
        if (zzb2 != null) {
            return zzb2.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i = this.zzg + 1;
        this.zzg = i;
        return nextLong + ((long) i);
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzdk zzdk = new zzdk();
        zza((zza) new zzew(this, bundle, zzdk));
        if (z) {
            return zzdk.zza(5000);
        }
        return null;
    }

    public final AppMeasurementSdk zzb() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final zzdj zza(Context context, boolean z) {
        try {
            return zzdm.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e) {
            zza((Exception) e, true, false);
            return null;
        }
    }

    public static zzdy zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    public static zzdy zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzdy.class) {
                if (zzb == null) {
                    zzb = new zzdy(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    public final Long zzc() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzex(this, zzdk));
        return zzdk.zzb(120000);
    }

    public final Object zza(int i) {
        zzdk zzdk = new zzdk();
        zza((zza) new zzfa(this, zzdk, i));
        return zzdk.zza(zzdk.zza((long) C.DEFAULT_SEEK_FORWARD_INCREMENT_MS), Object.class);
    }

    public final String zzd() {
        return this.zzi;
    }

    public final String zze() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzey(this, zzdk));
        return zzdk.zzc(120000);
    }

    public final String zzf() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzen(this, zzdk));
        return zzdk.zzc(50);
    }

    public final String zzg() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzes(this, zzdk));
        return zzdk.zzc(500);
    }

    public final String zzh() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzep(this, zzdk));
        return zzdk.zzc(500);
    }

    public final String zzi() {
        zzdk zzdk = new zzdk();
        zza((zza) new zzeo(this, zzdk));
        return zzdk.zzc(500);
    }

    public final List<Bundle> zza(String str, String str2) {
        zzdk zzdk = new zzdk();
        zza((zza) new zzeb(this, str, str2, zzdk));
        List<Bundle> list = (List) zzdk.zza(zzdk.zza(5000), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzdk zzdk = new zzdk();
        zza((zza) new zzer(this, str, str2, z, zzdk));
        Bundle zza2 = zzdk.zza(5000);
        if (zza2 == null || zza2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zza2.size());
        for (String str3 : zza2.keySet()) {
            Object obj = zza2.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(str3, obj);
            }
        }
        return hashMap;
    }

    private zzdy(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        boolean z = true;
        this.zzd = zzdc.zza().zza(new zzek(this), 1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        if (!(!zzb(context) || zzk())) {
            this.zzi = null;
            this.zzh = true;
            SentryLogcatAdapter.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (!zzc(str2, str3)) {
            this.zzi = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 != null ? false : z)) {
                    SentryLogcatAdapter.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
            }
        } else {
            this.zzi = str2;
        }
        zza((zza) new zzdx(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            SentryLogcatAdapter.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzd());
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Exception exc, boolean z, boolean z2) {
        this.zzh |= z;
        if (z) {
            SentryLogcatAdapter.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zza(5, "Error with data collection. Data lost.", (Object) exc, (Object) null, (Object) null);
        }
        SentryLogcatAdapter.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    public final void zzb(String str) {
        zza((zza) new zzem(this, str));
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza((zza) new zzec(this, str, str2, bundle));
    }

    public final void zzc(String str) {
        zza((zza) new zzel(this, str));
    }

    public final void zza(String str, Bundle bundle) {
        zza((String) null, str, bundle, false, true, (Long) null);
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, (Long) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza((zza) new zzff(this, l, str, str2, bundle, z, z2));
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza((zza) new zzeu(this, false, 5, str, obj, (Object) null, (Object) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r4.zzj == null) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.zzj.registerOnMeasurementEventListener(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0045, code lost:
        io.sentry.android.core.SentryLogcatAdapter.w(r4.zzc, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        zza((com.google.android.gms.internal.measurement.zzdy.zza) new com.google.android.gms.internal.measurement.zzfe(r4, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzjl r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r0 = r4.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            int r2 = r2.size()     // Catch:{ all -> 0x0055 }
            if (r1 >= r2) goto L_0x002b
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0055 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0055 }
            boolean r2 = r5.equals(r2)     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x0028
            java.lang.String r5 = r4.zzc     // Catch:{ all -> 0x0055 }
            java.lang.String r1 = "OnEventListener already registered."
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r5, (java.lang.String) r1)     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x0028:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002b:
            com.google.android.gms.internal.measurement.zzdy$zzb r1 = new com.google.android.gms.internal.measurement.zzdy$zzb     // Catch:{ all -> 0x0055 }
            r1.<init>(r5)     // Catch:{ all -> 0x0055 }
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r2 = r4.zzf     // Catch:{ all -> 0x0055 }
            android.util.Pair r3 = new android.util.Pair     // Catch:{ all -> 0x0055 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0055 }
            r2.add(r3)     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.measurement.zzdj r5 = r4.zzj
            if (r5 == 0) goto L_0x004c
            com.google.android.gms.internal.measurement.zzdj r5 = r4.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            r5.registerOnMeasurementEventListener(r1)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0045 }
            return
        L_0x0045:
            java.lang.String r5 = r4.zzc
            java.lang.String r0 = "Failed to register event listener on calling thread. Trying again on the dynamite thread."
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r5, (java.lang.String) r0)
        L_0x004c:
            com.google.android.gms.internal.measurement.zzfe r5 = new com.google.android.gms.internal.measurement.zzfe
            r5.<init>(r4, r1)
            r4.zza((com.google.android.gms.internal.measurement.zzdy.zza) r5)
            return
        L_0x0055:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdy.zza(com.google.android.gms.measurement.internal.zzjl):void");
    }

    public final void zzj() {
        zza((zza) new zzeh(this));
    }

    /* access modifiers changed from: private */
    public final void zza(zza zza2) {
        this.zzd.execute(zza2);
    }

    public final void zza(Bundle bundle) {
        zza((zza) new zzdz(this, bundle));
    }

    public final void zzb(Bundle bundle) {
        zza((zza) new zzef(this, bundle));
    }

    public final void zzc(Bundle bundle) {
        zza((zza) new zzei(this, bundle));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza((zza) new zzed(this, activity, str, str2));
    }

    public final void zza(boolean z) {
        zza((zza) new zzez(this, z));
    }

    public final void zzd(Bundle bundle) {
        zza((zza) new zzfc(this, bundle));
    }

    public final void zza(zzjm zzjm) {
        zzc zzc2 = new zzc(zzjm);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzc2);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                SentryLogcatAdapter.w(this.zzc, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zza((zza) new zzet(this, zzc2));
    }

    public final void zza(Boolean bool) {
        zza((zza) new zzeg(this, bool));
    }

    public final void zza(long j) {
        zza((zza) new zzej(this, j));
    }

    public final void zza(Intent intent) {
        zza((zza) new zzfb(this, intent));
    }

    public final void zzd(String str) {
        zza((zza) new zzee(this, str));
    }

    public final void zzb(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj, boolean z) {
        zza((zza) new zzea(this, str, str2, obj, z));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0043, code lost:
        if (r3.zzj == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3.zzj.unregisterOnMeasurementEventListener(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        io.sentry.android.core.SentryLogcatAdapter.w(r3.zzc, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
        zza((com.google.android.gms.internal.measurement.zzdy.zza) new com.google.android.gms.internal.measurement.zzfd(r3, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zzjl r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r0 = r3.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r2 = r3.zzf     // Catch:{ all -> 0x005b }
            int r2 = r2.size()     // Catch:{ all -> 0x005b }
            if (r1 >= r2) goto L_0x002b
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r2 = r3.zzf     // Catch:{ all -> 0x005b }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x005b }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x005b }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x005b }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x0028
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r4 = r3.zzf     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x005b }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ all -> 0x005b }
            goto L_0x002c
        L_0x0028:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002b:
            r4 = 0
        L_0x002c:
            if (r4 != 0) goto L_0x0037
            java.lang.String r4 = r3.zzc     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "OnEventListener had not been registered."
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r4, (java.lang.String) r1)     // Catch:{ all -> 0x005b }
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            return
        L_0x0037:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjl, com.google.android.gms.internal.measurement.zzdy$zzb>> r1 = r3.zzf     // Catch:{ all -> 0x005b }
            r1.remove(r4)     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x005b }
            com.google.android.gms.internal.measurement.zzdy$zzb r4 = (com.google.android.gms.internal.measurement.zzdy.zzb) r4     // Catch:{ all -> 0x005b }
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            com.google.android.gms.internal.measurement.zzdj r0 = r3.zzj
            if (r0 == 0) goto L_0x0052
            com.google.android.gms.internal.measurement.zzdj r0 = r3.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004b }
            r0.unregisterOnMeasurementEventListener(r4)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004b }
            return
        L_0x004b:
            java.lang.String r0 = r3.zzc
            java.lang.String r1 = "Failed to unregister event listener on calling thread. Trying again on the dynamite thread."
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r0, (java.lang.String) r1)
        L_0x0052:
            com.google.android.gms.internal.measurement.zzfd r0 = new com.google.android.gms.internal.measurement.zzfd
            r0.<init>(r3, r4)
            r3.zza((com.google.android.gms.internal.measurement.zzdy.zza) r0)
            return
        L_0x005b:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdy.zzb(com.google.android.gms.measurement.internal.zzjl):void");
    }

    /* access modifiers changed from: private */
    public final boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    private final boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private static boolean zzb(Context context) {
        try {
            if (new zzhs(context, zzhs.zza(context)).zza("google_app_id") != null) {
                return true;
            }
            return false;
        } catch (IllegalStateException unused) {
        }
    }
}
