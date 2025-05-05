package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.StrictMode;
import androidx.collection.ArrayMap;
import com.google.common.base.Preconditions;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgu implements zzhb {
    private static final Map<Uri, zzgu> zza = new ArrayMap();
    private static final String[] zzb = {"key", "value"};
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg = new Object();
    private volatile Map<String, String> zzh;
    private final List<zzgz> zzi = new ArrayList();

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzgu zza(android.content.ContentResolver r4, android.net.Uri r5, java.lang.Runnable r6) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzgu> r0 = com.google.android.gms.internal.measurement.zzgu.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.measurement.zzgu> r1 = zza     // Catch:{ all -> 0x0018 }
            java.lang.Object r2 = r1.get(r5)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.internal.measurement.zzgu r2 = (com.google.android.gms.internal.measurement.zzgu) r2     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x0016
            com.google.android.gms.internal.measurement.zzgu r3 = new com.google.android.gms.internal.measurement.zzgu     // Catch:{ SecurityException -> 0x0016 }
            r3.<init>(r4, r5, r6)     // Catch:{ SecurityException -> 0x0016 }
            r1.put(r5, r3)     // Catch:{ SecurityException -> 0x0015 }
        L_0x0015:
            r2 = r3
        L_0x0016:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            return r2
        L_0x0018:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgu.zza(android.content.ContentResolver, android.net.Uri, java.lang.Runnable):com.google.android.gms.internal.measurement.zzgu");
    }

    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    public final Map<String, String> zza() {
        Map<String, String> map = this.zzh;
        if (map == null) {
            synchronized (this.zzg) {
                map = this.zzh;
                if (map == null) {
                    map = zze();
                    this.zzh = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Map zzb() {
        Cursor query;
        Map map;
        ContentProviderClient acquireUnstableContentProviderClient = this.zzc.acquireUnstableContentProviderClient(this.zzd);
        if (acquireUnstableContentProviderClient == null) {
            SentryLogcatAdapter.w("ConfigurationContentLdr", "Unable to acquire ContentProviderClient, using default values");
            return Collections.emptyMap();
        }
        try {
            query = acquireUnstableContentProviderClient.query(this.zzd, zzb, (String) null, (String[]) null, (String) null);
            if (query == null) {
                SentryLogcatAdapter.w("ConfigurationContentLdr", "ContentProvider query returned null cursor, using default values");
                Map emptyMap = Collections.emptyMap();
                if (query != null) {
                    query.close();
                }
                acquireUnstableContentProviderClient.release();
                return emptyMap;
            }
            int count = query.getCount();
            if (count == 0) {
                Map emptyMap2 = Collections.emptyMap();
                if (query != null) {
                    query.close();
                }
                acquireUnstableContentProviderClient.release();
                return emptyMap2;
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            if (!query.isAfterLast()) {
                SentryLogcatAdapter.w("ConfigurationContentLdr", "Cursor read incomplete (ContentProvider dead?), using default values");
                Map emptyMap3 = Collections.emptyMap();
                if (query != null) {
                    query.close();
                }
                acquireUnstableContentProviderClient.release();
                return emptyMap3;
            }
            if (query != null) {
                query.close();
            }
            acquireUnstableContentProviderClient.release();
            return map;
        } catch (RemoteException e) {
            try {
                SentryLogcatAdapter.w("ConfigurationContentLdr", "ContentProvider query failed, using default values", e);
                return Collections.emptyMap();
            } finally {
                acquireUnstableContentProviderClient.release();
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return (Map) zzha.zza(new zzgx(this));
        } catch (SQLiteException | IllegalStateException | SecurityException e) {
            SentryLogcatAdapter.w("ConfigurationContentLdr", "Unable to query ContentProvider, using default values", e);
            return Collections.emptyMap();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private zzgu(ContentResolver contentResolver, Uri uri, Runnable runnable) {
        zzgw zzgw = new zzgw(this, (Handler) null);
        this.zzf = zzgw;
        Preconditions.checkNotNull(contentResolver);
        Preconditions.checkNotNull(uri);
        this.zzc = contentResolver;
        this.zzd = uri;
        this.zze = runnable;
        contentResolver.registerContentObserver(uri, false, zzgw);
    }

    static synchronized void zzc() {
        synchronized (zzgu.class) {
            for (zzgu next : zza.values()) {
                next.zzc.unregisterContentObserver(next.zzf);
            }
            zza.clear();
        }
    }

    public final void zzd() {
        synchronized (this.zzg) {
            this.zzh = null;
            this.zze.run();
        }
        synchronized (this) {
            for (zzgz zza2 : this.zzi) {
                zza2.zza();
            }
        }
    }
}
