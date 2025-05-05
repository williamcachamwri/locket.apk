package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzhw implements zzhb {
    private static final Map<String, zzhw> zza = new ArrayMap();
    private final SharedPreferences zzb;
    private final Runnable zzc;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzd;
    private final Object zze = new Object();
    private volatile Map<String, ?> zzf;
    private final List<zzgz> zzg = new ArrayList();

    private static SharedPreferences zza(Context context, String str) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            if (str.startsWith("direct_boot:")) {
                if (zzgs.zza()) {
                    context = context.createDeviceProtectedStorageContext();
                }
                return zzcu.zza(context, str.substring(12), 0, zzcq.zza);
            }
            SharedPreferences zza2 = zzcu.zza(context, str, 0, zzcq.zza);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return zza2;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    static zzhw zza(Context context, String str, Runnable runnable) {
        zzhw zzhw;
        if (!((!zzgs.zza() || str.startsWith("direct_boot:")) ? true : zzgs.zzb(context))) {
            return null;
        }
        synchronized (zzhw.class) {
            Map<String, zzhw> map = zza;
            zzhw = map.get(str);
            if (zzhw == null) {
                zzhw = new zzhw(zza(context, str), runnable);
                map.put(str, zzhw);
            }
        }
        return zzhw;
    }

    /* JADX INFO: finally extract failed */
    public final Object zza(String str) {
        Map<String, ?> map = this.zzf;
        if (map == null) {
            synchronized (this.zze) {
                map = this.zzf;
                if (map == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        Map<String, ?> all = this.zzb.getAll();
                        this.zzf = all;
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        map = all;
                    } catch (Throwable th) {
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                        throw th;
                    }
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private zzhw(SharedPreferences sharedPreferences, Runnable runnable) {
        zzhv zzhv = new zzhv(this);
        this.zzd = zzhv;
        this.zzb = sharedPreferences;
        this.zzc = runnable;
        sharedPreferences.registerOnSharedPreferenceChangeListener(zzhv);
    }

    static synchronized void zza() {
        synchronized (zzhw.class) {
            for (zzhw next : zza.values()) {
                next.zzb.unregisterOnSharedPreferenceChangeListener(next.zzd);
            }
            zza.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zze) {
            this.zzf = null;
            this.zzc.run();
        }
        synchronized (this) {
            for (zzgz zza2 : this.zzg) {
                zza2.zza();
            }
        }
    }
}
