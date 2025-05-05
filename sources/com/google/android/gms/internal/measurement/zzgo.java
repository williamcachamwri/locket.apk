package com.google.android.gms.internal.measurement;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.RemoteException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgo implements zzgq {
    public final String zza(ContentResolver contentResolver, String str) throws zzgt {
        Cursor query;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(zzgi.zza);
        if (acquireUnstableContentProviderClient != null) {
            try {
                query = acquireUnstableContentProviderClient.query(zzgi.zza, (String[]) null, (String) null, new String[]{str}, (String) null);
                if (query == null) {
                    throw new zzgt("ContentProvider query returned null cursor");
                } else if (query.moveToFirst()) {
                    String string = query.getString(1);
                    if (query != null) {
                        query.close();
                    }
                    acquireUnstableContentProviderClient.release();
                    return string;
                } else {
                    if (query != null) {
                        query.close();
                    }
                    acquireUnstableContentProviderClient.release();
                    return null;
                }
            } catch (RemoteException e) {
                try {
                    throw new zzgt("ContentProvider query failed", e);
                } catch (Throwable th) {
                    acquireUnstableContentProviderClient.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            throw new zzgt("Unable to acquire ContentProviderClient");
        }
        throw th;
    }

    public final <T extends Map<String, String>> T zza(ContentResolver contentResolver, String[] strArr, zzgr<T> zzgr) throws zzgt {
        Cursor query;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(zzgi.zzb);
        if (acquireUnstableContentProviderClient != null) {
            try {
                query = acquireUnstableContentProviderClient.query(zzgi.zzb, (String[]) null, (String) null, strArr, (String) null);
                if (query != null) {
                    T zza = zzgr.zza(query.getCount());
                    while (query.moveToNext()) {
                        zza.put(query.getString(0), query.getString(1));
                    }
                    if (query.isAfterLast()) {
                        if (query != null) {
                            query.close();
                        }
                        acquireUnstableContentProviderClient.release();
                        return zza;
                    }
                    throw new zzgt("Cursor read incomplete (ContentProvider dead?)");
                }
                throw new zzgt("ContentProvider query returned null cursor");
            } catch (RemoteException e) {
                try {
                    throw new zzgt("ContentProvider query failed", e);
                } catch (Throwable th) {
                    acquireUnstableContentProviderClient.release();
                    throw th;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            throw new zzgt("Unable to acquire ContentProviderClient");
        }
        throw th;
    }
}
