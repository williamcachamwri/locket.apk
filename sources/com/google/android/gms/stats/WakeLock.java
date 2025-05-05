package com.google.android.gms.stats;

import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.stats.zzb;
import com.google.android.gms.internal.stats.zzc;
import com.google.android.gms.internal.stats.zzh;
import com.google.android.gms.internal.stats.zzi;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-stats@@17.0.1 */
public class WakeLock {
    private static final long zzb = TimeUnit.DAYS.toMillis(366);
    private static volatile ScheduledExecutorService zzc = null;
    private static final Object zzd = new Object();
    private static volatile zzd zze = new zzb();
    zzb zza;
    private final Object zzf = new Object();
    private final PowerManager.WakeLock zzg;
    private int zzh = 0;
    private Future<?> zzi;
    private long zzj;
    private final Set<zze> zzk = new HashSet();
    private boolean zzl = true;
    private int zzm;
    private Clock zzn = DefaultClock.getInstance();
    private WorkSource zzo;
    private final String zzp;
    private final String zzq;
    private final Context zzr;
    private final Map<String, zzc> zzs = new HashMap();
    private AtomicInteger zzt = new AtomicInteger(0);
    private final ScheduledExecutorService zzu;

    public WakeLock(Context context, int i, String str) {
        String str2;
        String packageName = context.getPackageName();
        Preconditions.checkNotNull(context, "WakeLock: context must not be null");
        Preconditions.checkNotEmpty(str, "WakeLock: wakeLockName must not be empty");
        this.zzr = context.getApplicationContext();
        this.zzq = str;
        this.zza = null;
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(str);
            if (valueOf.length() != 0) {
                str2 = "*gcore*:".concat(valueOf);
            } else {
                str2 = new String("*gcore*:");
            }
            this.zzp = str2;
        } else {
            this.zzp = str;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        Object[] objArr = new Object[0];
        if (powerManager != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(i, str);
            this.zzg = newWakeLock;
            if (WorkSourceUtil.hasWorkSourcePermission(context)) {
                WorkSource fromPackage = WorkSourceUtil.fromPackage(context, Strings.isEmptyOrWhitespace(packageName) ? context.getPackageName() : packageName);
                this.zzo = fromPackage;
                if (fromPackage != null) {
                    zze(newWakeLock, fromPackage);
                }
            }
            ScheduledExecutorService scheduledExecutorService = zzc;
            if (scheduledExecutorService == null) {
                synchronized (zzd) {
                    scheduledExecutorService = zzc;
                    if (scheduledExecutorService == null) {
                        zzh.zza();
                        scheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1));
                        zzc = scheduledExecutorService;
                    }
                }
            }
            this.zzu = scheduledExecutorService;
            return;
        }
        StringBuilder sb = new StringBuilder(29);
        sb.append("expected a non-null reference", 0, 29);
        throw new zzi(sb.toString());
    }

    public static /* synthetic */ void zza(WakeLock wakeLock) {
        synchronized (wakeLock.zzf) {
            if (wakeLock.isHeld()) {
                SentryLogcatAdapter.e("WakeLock", String.valueOf(wakeLock.zzp).concat(" ** IS FORCE-RELEASED ON TIMEOUT **"));
                wakeLock.zzc();
                if (wakeLock.isHeld()) {
                    wakeLock.zzh = 1;
                    wakeLock.zzd(0);
                }
            }
        }
    }

    private final String zzb(String str) {
        if (this.zzl) {
            TextUtils.isEmpty((CharSequence) null);
        }
        return null;
    }

    private final void zzc() {
        if (!this.zzk.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.zzk);
            this.zzk.clear();
            if (arrayList.size() > 0) {
                zze zze2 = (zze) arrayList.get(0);
                throw null;
            }
        }
    }

    private final void zzd(int i) {
        synchronized (this.zzf) {
            if (isHeld()) {
                if (this.zzl) {
                    int i2 = this.zzh - 1;
                    this.zzh = i2;
                    if (i2 > 0) {
                        return;
                    }
                } else {
                    this.zzh = 0;
                }
                zzc();
                for (zzc zzc2 : this.zzs.values()) {
                    zzc2.zza = 0;
                }
                this.zzs.clear();
                Future<?> future = this.zzi;
                if (future != null) {
                    future.cancel(false);
                    this.zzi = null;
                    this.zzj = 0;
                }
                this.zzm = 0;
                if (this.zzg.isHeld()) {
                    try {
                        this.zzg.release();
                        if (this.zza != null) {
                            this.zza = null;
                        }
                    } catch (RuntimeException e) {
                        if (e.getClass().equals(RuntimeException.class)) {
                            SentryLogcatAdapter.e("WakeLock", String.valueOf(this.zzp).concat(" failed to release!"), e);
                            if (this.zza != null) {
                                this.zza = null;
                            }
                            return;
                        }
                        throw e;
                    } catch (Throwable th) {
                        if (this.zza != null) {
                            this.zza = null;
                        }
                        throw th;
                    }
                } else {
                    SentryLogcatAdapter.e("WakeLock", String.valueOf(this.zzp).concat(" should be held!"));
                }
            }
        }
    }

    private static void zze(PowerManager.WakeLock wakeLock, WorkSource workSource) {
        try {
            wakeLock.setWorkSource(workSource);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            SentryLogcatAdapter.wtf("WakeLock", e.toString());
        }
    }

    public void acquire(long j) {
        this.zzt.incrementAndGet();
        long j2 = Long.MAX_VALUE;
        long max = Math.max(Math.min(Long.MAX_VALUE, zzb), 1);
        if (j > 0) {
            max = Math.min(j, max);
        }
        synchronized (this.zzf) {
            if (!isHeld()) {
                this.zza = zzb.zza(false, (zzc) null);
                this.zzg.acquire();
                this.zzn.elapsedRealtime();
            }
            this.zzh++;
            this.zzm++;
            zzb((String) null);
            zzc zzc2 = this.zzs.get((Object) null);
            if (zzc2 == null) {
                zzc2 = new zzc((zzb) null);
                this.zzs.put((Object) null, zzc2);
            }
            zzc2.zza++;
            long elapsedRealtime = this.zzn.elapsedRealtime();
            if (Long.MAX_VALUE - elapsedRealtime > max) {
                j2 = elapsedRealtime + max;
            }
            if (j2 > this.zzj) {
                this.zzj = j2;
                Future<?> future = this.zzi;
                if (future != null) {
                    future.cancel(false);
                }
                this.zzi = this.zzu.schedule(new zza(this), max, TimeUnit.MILLISECONDS);
            }
        }
    }

    public boolean isHeld() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzh > 0;
        }
        return z;
    }

    public void release() {
        if (this.zzt.decrementAndGet() < 0) {
            SentryLogcatAdapter.e("WakeLock", String.valueOf(this.zzp).concat(" release without a matched acquire!"));
        }
        synchronized (this.zzf) {
            zzb((String) null);
            if (this.zzs.containsKey((Object) null)) {
                zzc zzc2 = this.zzs.get((Object) null);
                if (zzc2 != null) {
                    int i = zzc2.zza - 1;
                    zzc2.zza = i;
                    if (i == 0) {
                        this.zzs.remove((Object) null);
                    }
                }
            } else {
                SentryLogcatAdapter.w("WakeLock", String.valueOf(this.zzp).concat(" counter does not exist"));
            }
            zzd(0);
        }
    }

    public void setReferenceCounted(boolean z) {
        synchronized (this.zzf) {
            this.zzl = z;
        }
    }
}
