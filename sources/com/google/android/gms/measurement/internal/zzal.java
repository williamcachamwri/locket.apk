package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzpu;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.dynamiclinks.DynamicLink;
import io.sentry.protocol.App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzal extends zznr {
    /* access modifiers changed from: private */
    public static final String[] zza = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzb = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {App.JsonKeys.APP_VERSION, "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;", "daily_registered_triggers_count", "ALTER TABLE apps ADD COLUMN daily_registered_triggers_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzf = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzj = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzk = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final zzat zzl = new zzat(this, zza(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zznl zzm = new zznl(zzb());

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            return e_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting conditional property", zzgo.zza(str), zzi().zzc(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    public final long zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        try {
            return (long) e_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zze().zzb(str, zzbh.zzp))))});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting over the limit events. appId", zzgo.zza(str), e);
            return 0;
        }
    }

    public final long b_() {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", (String[]) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return -1;
            }
            long j = cursor.getLong(0);
            if (cursor != null) {
                cursor.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying raw events", e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final long zza(zzfy.zzk zzk2) throws IOException {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        byte[] zzca = zzk2.zzca();
        long zza2 = g_().zza(zzca);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzk2.zzz());
        contentValues.put("metadata_fingerprint", Long.valueOf(zza2));
        contentValues.put(TtmlNode.TAG_METADATA, zzca);
        try {
            e_().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
            return zza2;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event metadata. appId", zzgo.zza(zzk2.zzz()), e);
            throw e;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final long zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        long j = 0;
        try {
            long zza2 = zza("select " + str2 + " from app2 where app_id=?", new String[]{str}, -1);
            if (zza2 == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (e_.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzj().zzg().zza("Failed to insert column (got -1). appId", zzgo.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                zza2 = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + zza2));
                if (((long) e_.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzj().zzg().zza("Failed to update column (got 0). appId", zzgo.zza(str), str2);
                    e_.endTransaction();
                    return -1;
                }
                e_.setTransactionSuccessful();
                e_.endTransaction();
                return zza2;
            } catch (SQLiteException e) {
                long j2 = zza2;
                e = e;
                j = j2;
                try {
                    zzj().zzg().zza("Error inserting column. appId", zzgo.zza(str), str2, e);
                    e_.endTransaction();
                    return j;
                } catch (Throwable th) {
                    e_.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e2) {
            e = e2;
            zzj().zzg().zza("Error inserting column. appId", zzgo.zza(str), str2, e);
            e_.endTransaction();
            return j;
        }
    }

    public final long zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        return zza("select first_open_count from app2 where app_id=?", new String[]{str}, -1);
    }

    public final long c_() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long d_() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                long j2 = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return j2;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase e_() {
        zzt();
        try {
            return this.zzl.getWritableDatabase();
        } catch (SQLiteException e) {
            zzj().zzu().zza("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzd(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzt()
            r5.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.e_()     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            java.lang.String[] r3 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0072, all -> 0x0070 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x006e }
            if (r2 != 0) goto L_0x002e
            com.google.android.gms.measurement.internal.zzgo r6 = r5.zzj()     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x006e }
            java.lang.String r2 = "Default event parameters not found"
            r6.zza(r2)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            return r0
        L_0x002e:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r3 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzlb r2 = com.google.android.gms.measurement.internal.zzoo.zza(r3, (byte[]) r2)     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzlc r2 = r2.zzai()     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch:{ IOException -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch:{ IOException -> 0x0056 }
            r5.g_()     // Catch:{ SQLiteException -> 0x006e }
            java.util.List r6 = r2.zzh()     // Catch:{ SQLiteException -> 0x006e }
            android.os.Bundle r6 = com.google.android.gms.measurement.internal.zzoo.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzh>) r6)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x0055
            r1.close()
        L_0x0055:
            return r6
        L_0x0056:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgo r3 = r5.zzj()     // Catch:{ SQLiteException -> 0x006e }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x006e }
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x006e }
            r3.zza(r4, r6, r2)     // Catch:{ SQLiteException -> 0x006e }
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            return r0
        L_0x006e:
            r6 = move-exception
            goto L_0x0074
        L_0x0070:
            r6 = move-exception
            goto L_0x0089
        L_0x0072:
            r6 = move-exception
            r1 = r0
        L_0x0074:
            com.google.android.gms.measurement.internal.zzgo r2 = r5.zzj()     // Catch:{ all -> 0x0087 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zza(r3, r6)     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            return r0
        L_0x0087:
            r6 = move-exception
            r0 = r1
        L_0x0089:
            if (r0 == 0) goto L_0x008e
            r0.close()
        L_0x008e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzd(java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzfy.zzf, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzt()
            r7.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.e_()     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0077 }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.measurement.internal.zzgo r8 = r7.zzj()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzp()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r9 = "Main event not found"
            r8.zza(r9)     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0077 }
            long r3 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r4 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch:{ IOException -> 0x005f }
            com.google.android.gms.internal.measurement.zzlb r2 = com.google.android.gms.measurement.internal.zzoo.zza(r4, (byte[]) r2)     // Catch:{ IOException -> 0x005f }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r2     // Catch:{ IOException -> 0x005f }
            com.google.android.gms.internal.measurement.zzlc r2 = r2.zzai()     // Catch:{ IOException -> 0x005f }
            com.google.android.gms.internal.measurement.zzjt r2 = (com.google.android.gms.internal.measurement.zzjt) r2     // Catch:{ IOException -> 0x005f }
            com.google.android.gms.internal.measurement.zzfy$zzf r2 = (com.google.android.gms.internal.measurement.zzfy.zzf) r2     // Catch:{ IOException -> 0x005f }
            android.util.Pair r8 = android.util.Pair.create(r2, r3)     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 == 0) goto L_0x005e
            r1.close()
        L_0x005e:
            return r8
        L_0x005f:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgo r3 = r7.zzj()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x0077 }
            r3.zza(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0077 }
            if (r1 == 0) goto L_0x0076
            r1.close()
        L_0x0076:
            return r0
        L_0x0077:
            r8 = move-exception
            goto L_0x007d
        L_0x0079:
            r8 = move-exception
            goto L_0x0092
        L_0x007b:
            r8 = move-exception
            r1 = r0
        L_0x007d:
            com.google.android.gms.measurement.internal.zzgo r9 = r7.zzj()     // Catch:{ all -> 0x0090 }
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzg()     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x008f
            r1.close()
        L_0x008f:
            return r0
        L_0x0090:
            r8 = move-exception
            r0 = r1
        L_0x0092:
            if (r0 == 0) goto L_0x0097
            r0.close()
        L_0x0097:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x03fe A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0431  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x043a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x022c A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0230 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0264 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0282 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0285 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0294 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x02ea A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0360 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0362 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x036e A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0370 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0398 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x039b A[Catch:{ SQLiteException -> 0x0415 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x03b4 A[Catch:{ SQLiteException -> 0x0415 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzg zze(java.lang.String r27) {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            r26.zzt()
            r26.zzal()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r26.e_()     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r5 = "apps"
            r6 = 44
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "app_instance_id"
            r12 = 0
            r6[r12] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "gmp_app_id"
            r13 = 1
            r6[r13] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "resettable_device_id_hash"
            r14 = 2
            r6[r14] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "last_bundle_index"
            r15 = 3
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "last_bundle_start_timestamp"
            r11 = 4
            r6[r11] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "last_bundle_end_timestamp"
            r10 = 5
            r6[r10] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "app_version"
            r9 = 6
            r6[r9] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "app_store"
            r8 = 7
            r6[r8] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "gmp_version"
            r15 = 8
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "dev_cert_hash"
            r15 = 9
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "measurement_enabled"
            r15 = 10
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "day"
            r16 = 11
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_public_events_count"
            r16 = 12
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_events_count"
            r16 = 13
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_conversions_count"
            r16 = 14
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "config_fetched_time"
            r16 = 15
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "failed_config_fetch_time"
            r16 = 16
            r6[r16] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "app_version_int"
            r15 = 17
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "firebase_instance_id"
            r17 = 18
            r6[r17] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_error_events_count"
            r17 = 19
            r6[r17] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_realtime_events_count"
            r17 = 20
            r6[r17] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "health_monitor_sample"
            r17 = 21
            r6[r17] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "android_id"
            r17 = 22
            r6[r17] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "adid_reporting_enabled"
            r15 = 23
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "admob_app_id"
            r18 = 24
            r6[r18] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "dynamite_version"
            r15 = 25
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "safelisted_events"
            r15 = 26
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "ga_app_id"
            r19 = 27
            r6[r19] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "session_stitching_token"
            r19 = 28
            r6[r19] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "sgtm_upload_enabled"
            r15 = 29
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "target_os_version"
            r20 = 30
            r6[r20] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "session_stitching_token_hash"
            r20 = 31
            r6[r20] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "ad_services_version"
            r20 = 32
            r6[r20] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "unmatched_first_open_without_ad_id"
            r15 = 33
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "npa_metadata_value"
            r15 = 34
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "attribution_eligibility_status"
            r21 = 35
            r6[r21] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "sgtm_preview_key"
            r21 = 36
            r6[r21] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "dma_consent_state"
            r21 = 37
            r6[r21] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "daily_realtime_dcu_count"
            r21 = 38
            r6[r21] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "bundle_delivery_index"
            r21 = 39
            r6[r21] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "serialized_npa_metadata"
            r15 = 40
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "unmatched_pfo"
            r15 = 41
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "unmatched_uwa"
            r15 = 42
            r6[r15] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "ad_campaign_info"
            r22 = 43
            r6[r22] = r7     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            java.lang.String r7 = "app_id=?"
            java.lang.String[] r22 = new java.lang.String[]{r27}     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            r23 = 0
            r24 = 0
            r25 = 0
            r15 = r8
            r8 = r22
            r15 = r9
            r9 = r23
            r15 = r10
            r10 = r24
            r15 = r11
            r11 = r25
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x041b, all -> 0x0418 }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0415 }
            if (r5 != 0) goto L_0x0140
            if (r4 == 0) goto L_0x013f
            r4.close()
        L_0x013f:
            return r3
        L_0x0140:
            com.google.android.gms.measurement.internal.zzg r5 = new com.google.android.gms.measurement.internal.zzg     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zznv r6 = r1.zzg     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzhy r6 = r6.zzk()     // Catch:{ SQLiteException -> 0x0415 }
            r5.<init>(r6, r2)     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x016b
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcy     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x016b
            com.google.android.gms.measurement.internal.zznv r6 = r1.zzg     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje r6 = r6.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje$zza r7 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzje.zza) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0172
        L_0x016b:
            java.lang.String r6 = r4.getString(r12)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzb((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x0172:
            java.lang.String r6 = r4.getString(r13)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzf((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0199
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcy     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0199
            com.google.android.gms.measurement.internal.zznv r6 = r1.zzg     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje r6 = r6.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje$zza r7 = com.google.android.gms.measurement.internal.zzje.zza.AD_STORAGE     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzje.zza) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x01a0
        L_0x0199:
            java.lang.String r6 = r4.getString(r14)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzh((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x01a0:
            r6 = 3
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzq(r6)     // Catch:{ SQLiteException -> 0x0415 }
            long r6 = r4.getLong(r15)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzr(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 5
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzp(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 6
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzd((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 7
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzc((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 8
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzn(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 9
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzk((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 10
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x01ea
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x01e8
            goto L_0x01ea
        L_0x01e8:
            r6 = r12
            goto L_0x01eb
        L_0x01ea:
            r6 = r13
        L_0x01eb:
            r5.zzb((boolean) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 11
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzj((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 12
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzh((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 13
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzg((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 14
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zze((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 15
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzd((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 16
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzm(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 17
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 == 0) goto L_0x0230
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0235
        L_0x0230:
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            long r6 = (long) r6     // Catch:{ SQLiteException -> 0x0415 }
        L_0x0235:
            r5.zzb((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 18
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zze((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 19
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzf((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 20
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzi((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 21
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzg((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 23
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x026d
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x026b
            goto L_0x026d
        L_0x026b:
            r6 = r12
            goto L_0x026e
        L_0x026d:
            r6 = r13
        L_0x026e:
            r5.zza((boolean) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 24
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 25
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 == 0) goto L_0x0285
            r6 = 0
            goto L_0x0289
        L_0x0285:
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x0289:
            r5.zzl(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 26
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x02a6
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.String r7 = ","
            r8 = -1
            java.lang.String[] r6 = r6.split(r7, r8)     // Catch:{ SQLiteException -> 0x0415 }
            java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zza((java.util.List<java.lang.String>) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x02a6:
            boolean r6 = com.google.android.gms.internal.measurement.zznm.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x02c6
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcy     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x02c6
            com.google.android.gms.measurement.internal.zznv r6 = r1.zzg     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje r6 = r6.zzb((java.lang.String) r2)     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzje$zza r7 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzje.zza) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x02cf
        L_0x02c6:
            r6 = 28
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzj((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x02cf:
            boolean r6 = com.google.android.gms.internal.measurement.zzpu.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x031c
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzbx     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x031c
            r26.zzq()     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = com.google.android.gms.measurement.internal.zzos.zzf(r27)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x031c
            r6 = 29
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x02fa
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x02fa
            r6 = r13
            goto L_0x02fb
        L_0x02fa:
            r6 = r12
        L_0x02fb:
            r5.zzc((boolean) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 39
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzo(r6)     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzby     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x031c
            r6 = 36
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzk((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x031c:
            r6 = 30
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 31
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzs(r6)     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = com.google.android.gms.internal.measurement.zzpn.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0352
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzch     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zze(r2, r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0352
            r6 = 32
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zza((int) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 35
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzc((long) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x0352:
            r6 = 33
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x0362
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0362
            r6 = r13
            goto L_0x0363
        L_0x0362:
            r6 = r12
        L_0x0363:
            r5.zzd((boolean) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 34
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 == 0) goto L_0x0370
            r6 = r3
            goto L_0x037b
        L_0x0370:
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x0377
            r12 = r13
        L_0x0377:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r12)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x037b:
            r5.zza((java.lang.Boolean) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 37
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzc((int) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 38
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzb((int) r6)     // Catch:{ SQLiteException -> 0x0415 }
            r6 = 40
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 == 0) goto L_0x039b
            java.lang.String r6 = ""
            goto L_0x03a5
        L_0x039b:
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SQLiteException -> 0x0415 }
        L_0x03a5:
            r5.zzi((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcw     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x03da
            r6 = 41
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x03c7
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zza((java.lang.Long) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x03c7:
            r6 = 42
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x0415 }
            if (r7 != 0) goto L_0x03da
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zzb((java.lang.Long) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x03da:
            boolean r6 = com.google.android.gms.internal.measurement.zzov.zza()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x03f5
            com.google.android.gms.measurement.internal.zzag r6 = r26.zze()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbh.zzcu     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r6.zze(r2, r7)     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x03f5
            r6 = 43
            byte[] r6 = r4.getBlob(r6)     // Catch:{ SQLiteException -> 0x0415 }
            r5.zza((byte[]) r6)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x03f5:
            r5.zzao()     // Catch:{ SQLiteException -> 0x0415 }
            boolean r6 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0415 }
            if (r6 == 0) goto L_0x040f
            com.google.android.gms.measurement.internal.zzgo r6 = r26.zzj()     // Catch:{ SQLiteException -> 0x0415 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ SQLiteException -> 0x0415 }
            java.lang.String r7 = "Got multiple records for app, expected one. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r27)     // Catch:{ SQLiteException -> 0x0415 }
            r6.zza(r7, r8)     // Catch:{ SQLiteException -> 0x0415 }
        L_0x040f:
            if (r4 == 0) goto L_0x0414
            r4.close()
        L_0x0414:
            return r5
        L_0x0415:
            r0 = move-exception
            r5 = r0
            goto L_0x041e
        L_0x0418:
            r0 = move-exception
            r2 = r0
            goto L_0x0438
        L_0x041b:
            r0 = move-exception
            r5 = r0
            r4 = r3
        L_0x041e:
            com.google.android.gms.measurement.internal.zzgo r6 = r26.zzj()     // Catch:{ all -> 0x0435 }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzg()     // Catch:{ all -> 0x0435 }
            java.lang.String r7 = "Error querying app. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r27)     // Catch:{ all -> 0x0435 }
            r6.zza(r7, r2, r5)     // Catch:{ all -> 0x0435 }
            if (r4 == 0) goto L_0x0434
            r4.close()
        L_0x0434:
            return r3
        L_0x0435:
            r0 = move-exception
            r2 = r0
            r3 = r4
        L_0x0438:
            if (r3 == 0) goto L_0x043d
            r3.close()
        L_0x043d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0157  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzae zzc(java.lang.String r36, java.lang.String r37) {
        /*
            r35 = this;
            r7 = r37
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r36)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r37)
            r35.zzt()
            r35.zzal()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r35.e_()     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r10 = "conditional_properties"
            r0 = 11
            java.lang.String[] r11 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "origin"
            r1 = 0
            r11[r1] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "value"
            r2 = 1
            r11[r2] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "active"
            r3 = 2
            r11[r3] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "trigger_event_name"
            r4 = 3
            r11[r4] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "trigger_timeout"
            r5 = 4
            r11[r5] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "timed_out_event"
            r6 = 5
            r11[r6] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "creation_timestamp"
            r15 = 6
            r11[r15] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "triggered_event"
            r14 = 7
            r11[r14] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "triggered_timestamp"
            r13 = 8
            r11[r13] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "time_to_live"
            r12 = 9
            r11[r12] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "expired_event"
            r6 = 10
            r11[r6] = r0     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            java.lang.String r0 = "app_id=? and name=?"
            java.lang.String[] r16 = new java.lang.String[]{r36, r37}     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            r17 = 0
            r18 = 0
            r19 = 0
            r6 = r12
            r12 = r0
            r0 = r13
            r13 = r16
            r6 = r14
            r14 = r17
            r0 = r15
            r15 = r18
            r16 = r19
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x0130, all -> 0x012c }
            boolean r10 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x0128, all -> 0x0124 }
            if (r10 != 0) goto L_0x007c
            if (r9 == 0) goto L_0x007b
            r9.close()
        L_0x007b:
            return r8
        L_0x007c:
            java.lang.String r10 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x0128, all -> 0x0124 }
            if (r10 != 0) goto L_0x0084
            java.lang.String r10 = ""
        L_0x0084:
            r22 = r10
            r10 = r35
            java.lang.Object r11 = r10.zza((android.database.Cursor) r9, (int) r2)     // Catch:{ SQLiteException -> 0x0122 }
            int r3 = r9.getInt(r3)     // Catch:{ SQLiteException -> 0x0122 }
            if (r3 == 0) goto L_0x0095
            r26 = r2
            goto L_0x0097
        L_0x0095:
            r26 = r1
        L_0x0097:
            java.lang.String r27 = r9.getString(r4)     // Catch:{ SQLiteException -> 0x0122 }
            long r29 = r9.getLong(r5)     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzoo r1 = r35.g_()     // Catch:{ SQLiteException -> 0x0122 }
            r2 = 5
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r3 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable r1 = r1.zza((byte[]) r2, r3)     // Catch:{ SQLiteException -> 0x0122 }
            r28 = r1
            com.google.android.gms.measurement.internal.zzbf r28 = (com.google.android.gms.measurement.internal.zzbf) r28     // Catch:{ SQLiteException -> 0x0122 }
            long r24 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzoo r0 = r35.g_()     // Catch:{ SQLiteException -> 0x0122 }
            byte[] r1 = r9.getBlob(r6)     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r2 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0122 }
            r31 = r0
            com.google.android.gms.measurement.internal.zzbf r31 = (com.google.android.gms.measurement.internal.zzbf) r31     // Catch:{ SQLiteException -> 0x0122 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0122 }
            r0 = 9
            long r32 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzoo r0 = r35.g_()     // Catch:{ SQLiteException -> 0x0122 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r2 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0122 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0122 }
            r34 = r0
            com.google.android.gms.measurement.internal.zzbf r34 = (com.google.android.gms.measurement.internal.zzbf) r34     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzon r23 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ SQLiteException -> 0x0122 }
            r1 = r23
            r2 = r37
            r5 = r11
            r6 = r22
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzae r0 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ SQLiteException -> 0x0122 }
            r20 = r0
            r21 = r36
            r20.<init>(r21, r22, r23, r24, r26, r27, r28, r29, r31, r32, r34)     // Catch:{ SQLiteException -> 0x0122 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x0122 }
            if (r1 == 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzgo r1 = r35.zzj()     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ SQLiteException -> 0x0122 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r36)     // Catch:{ SQLiteException -> 0x0122 }
            com.google.android.gms.measurement.internal.zzgh r4 = r35.zzi()     // Catch:{ SQLiteException -> 0x0122 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ SQLiteException -> 0x0122 }
            r1.zza(r2, r3, r4)     // Catch:{ SQLiteException -> 0x0122 }
        L_0x011c:
            if (r9 == 0) goto L_0x0121
            r9.close()
        L_0x0121:
            return r0
        L_0x0122:
            r0 = move-exception
            goto L_0x0134
        L_0x0124:
            r0 = move-exception
            r10 = r35
            goto L_0x0154
        L_0x0128:
            r0 = move-exception
            r10 = r35
            goto L_0x0134
        L_0x012c:
            r0 = move-exception
            r10 = r35
            goto L_0x0155
        L_0x0130:
            r0 = move-exception
            r10 = r35
            r9 = r8
        L_0x0134:
            com.google.android.gms.measurement.internal.zzgo r1 = r35.zzj()     // Catch:{ all -> 0x0153 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x0153 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r36)     // Catch:{ all -> 0x0153 }
            com.google.android.gms.measurement.internal.zzgh r4 = r35.zzi()     // Catch:{ all -> 0x0153 }
            java.lang.String r4 = r4.zzc(r7)     // Catch:{ all -> 0x0153 }
            r1.zza(r2, r3, r4, r0)     // Catch:{ all -> 0x0153 }
            if (r9 == 0) goto L_0x0152
            r9.close()
        L_0x0152:
            return r8
        L_0x0153:
            r0 = move-exception
        L_0x0154:
            r8 = r9
        L_0x0155:
            if (r8 == 0) goto L_0x015a
            r8.close()
        L_0x015a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzae");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzan zzf(java.lang.String r13) {
        /*
            r12 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            r12.zzt()
            r12.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r12.e_()     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r2 = "apps"
            r3 = 3
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r4 = "remote_config"
            r9 = 0
            r3[r9] = r4     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r4 = "config_last_modified_time"
            r10 = 1
            r3[r10] = r4     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r4 = "e_tag"
            r11 = 2
            r3[r11] = r4     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            java.lang.String r4 = "app_id=?"
            java.lang.String[] r5 = new java.lang.String[]{r13}     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0075, all -> 0x0073 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0071 }
            if (r2 != 0) goto L_0x003b
            if (r1 == 0) goto L_0x003a
            r1.close()
        L_0x003a:
            return r0
        L_0x003b:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0071 }
            java.lang.String r3 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0071 }
            java.lang.String r4 = r1.getString(r11)     // Catch:{ SQLiteException -> 0x0071 }
            boolean r5 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0071 }
            if (r5 == 0) goto L_0x005e
            com.google.android.gms.measurement.internal.zzgo r5 = r12.zzj()     // Catch:{ SQLiteException -> 0x0071 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x0071 }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r13)     // Catch:{ SQLiteException -> 0x0071 }
            r5.zza(r6, r7)     // Catch:{ SQLiteException -> 0x0071 }
        L_0x005e:
            if (r2 != 0) goto L_0x0066
            if (r1 == 0) goto L_0x0065
            r1.close()
        L_0x0065:
            return r0
        L_0x0066:
            com.google.android.gms.measurement.internal.zzan r5 = new com.google.android.gms.measurement.internal.zzan     // Catch:{ SQLiteException -> 0x0071 }
            r5.<init>(r2, r3, r4)     // Catch:{ SQLiteException -> 0x0071 }
            if (r1 == 0) goto L_0x0070
            r1.close()
        L_0x0070:
            return r5
        L_0x0071:
            r2 = move-exception
            goto L_0x0077
        L_0x0073:
            r13 = move-exception
            goto L_0x0090
        L_0x0075:
            r2 = move-exception
            r1 = r0
        L_0x0077:
            com.google.android.gms.measurement.internal.zzgo r3 = r12.zzj()     // Catch:{ all -> 0x008e }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x008e }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r13)     // Catch:{ all -> 0x008e }
            r3.zza(r4, r13, r2)     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x008d
            r1.close()
        L_0x008d:
            return r0
        L_0x008e:
            r13 = move-exception
            r0 = r1
        L_0x0090:
            if (r0 == 0) goto L_0x0095
            r0.close()
        L_0x0095:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzf(java.lang.String):com.google.android.gms.measurement.internal.zzan");
    }

    public final zzaq zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return zza(j, str, 1, false, false, z3, false, z5, z6, z7);
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0170  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzaq zza(long r23, java.lang.String r25, long r26, boolean r28, boolean r29, boolean r30, boolean r31, boolean r32, boolean r33, boolean r34) {
        /*
            r22 = this;
            java.lang.String r0 = "daily_registered_triggers_count"
            java.lang.String r1 = "daily_realtime_dcu_count"
            java.lang.String r2 = "daily_realtime_events_count"
            java.lang.String r3 = "daily_error_events_count"
            java.lang.String r4 = "daily_conversions_count"
            java.lang.String r5 = "daily_public_events_count"
            java.lang.String r6 = "daily_events_count"
            java.lang.String r7 = "day"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r25)
            r22.zzt()
            r22.zzal()
            java.lang.String[] r8 = new java.lang.String[]{r25}
            com.google.android.gms.measurement.internal.zzaq r9 = new com.google.android.gms.measurement.internal.zzaq
            r9.<init>()
            android.database.sqlite.SQLiteDatabase r15 = r22.e_()     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            java.lang.String r12 = "apps"
            r11 = 8
            java.lang.String[] r13 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r14 = 0
            r13[r14] = r7     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r11 = 1
            r13[r11] = r6     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 2
            r13[r10] = r5     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 3
            r13[r10] = r4     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 4
            r13[r10] = r3     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 5
            r13[r10] = r2     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 6
            r13[r10] = r1     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r10 = 7
            r13[r10] = r0     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            java.lang.String r16 = "app_id=?"
            java.lang.String[] r17 = new java.lang.String[]{r25}     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            r18 = 0
            r19 = 0
            r20 = 0
            r10 = r11
            r11 = r15
            r10 = r14
            r14 = r16
            r21 = r15
            r15 = r17
            r16 = r18
            r17 = r19
            r18 = r20
            android.database.Cursor r11 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x0154, all -> 0x0151 }
            boolean r12 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            if (r12 != 0) goto L_0x0080
            com.google.android.gms.measurement.internal.zzgo r0 = r22.zzj()     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.String r1 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r25)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r0.zza(r1, r2)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            if (r11 == 0) goto L_0x007f
            r11.close()
        L_0x007f:
            return r9
        L_0x0080:
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            int r10 = (r12 > r23 ? 1 : (r12 == r23 ? 0 : -1))
            if (r10 != 0) goto L_0x00b9
            r10 = 1
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zzb = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 2
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zza = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 3
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zzc = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 4
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zzd = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 5
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zze = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 6
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zzf = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10 = 7
            long r12 = r11.getLong(r10)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r9.zzg = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00b9:
            if (r28 == 0) goto L_0x00c1
            long r12 = r9.zzb     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zzb = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00c1:
            if (r29 == 0) goto L_0x00c9
            long r12 = r9.zza     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zza = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00c9:
            if (r30 == 0) goto L_0x00d1
            long r12 = r9.zzc     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zzc = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00d1:
            if (r31 == 0) goto L_0x00d9
            long r12 = r9.zzd     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zzd = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00d9:
            if (r32 == 0) goto L_0x00e1
            long r12 = r9.zze     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zze = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00e1:
            if (r33 == 0) goto L_0x00e9
            long r12 = r9.zzf     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zzf = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00e9:
            if (r34 == 0) goto L_0x00f1
            long r12 = r9.zzg     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r12 + r26
            r9.zzg = r12     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
        L_0x00f1:
            android.content.ContentValues r10 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.<init>()     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r12 = java.lang.Long.valueOf(r23)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r7, r12)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r9.zza     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r7 = java.lang.Long.valueOf(r12)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r5, r7)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r12 = r9.zzb     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r5 = java.lang.Long.valueOf(r12)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r6, r5)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r5 = r9.zzc     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r4, r5)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r4 = r9.zzd     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r3, r4)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r3 = r9.zze     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r2, r3)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r2 = r9.zzf     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r1, r2)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            long r1 = r9.zzg     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            r10.put(r0, r1)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            java.lang.String r0 = "apps"
            java.lang.String r1 = "app_id=?"
            r2 = r21
            r2.update(r0, r10, r1, r8)     // Catch:{ SQLiteException -> 0x014e, all -> 0x014b }
            if (r11 == 0) goto L_0x014a
            r11.close()
        L_0x014a:
            return r9
        L_0x014b:
            r0 = move-exception
            r10 = r11
            goto L_0x016e
        L_0x014e:
            r0 = move-exception
            r10 = r11
            goto L_0x0156
        L_0x0151:
            r0 = move-exception
            r10 = 0
            goto L_0x016e
        L_0x0154:
            r0 = move-exception
            r10 = 0
        L_0x0156:
            com.google.android.gms.measurement.internal.zzgo r1 = r22.zzj()     // Catch:{ all -> 0x016d }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x016d }
            java.lang.String r2 = "Error updating daily counts. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r25)     // Catch:{ all -> 0x016d }
            r1.zza(r2, r3, r0)     // Catch:{ all -> 0x016d }
            if (r10 == 0) goto L_0x016c
            r10.close()
        L_0x016c:
            return r9
        L_0x016d:
            r0 = move-exception
        L_0x016e:
            if (r10 == 0) goto L_0x0173
            r10.close()
        L_0x0173:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(long, java.lang.String, long, boolean, boolean, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.measurement.internal.zzaq");
    }

    public final zzax zzg(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzax.zza(zza("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzbb zzd(String str, String str2) {
        return zzc("events", str, str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.measurement.internal.zzbb zzc(java.lang.String r29, java.lang.String r30, java.lang.String r31) {
        /*
            r28 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            r28.zzt()
            r28.zzal()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r1 = "lifetime_count"
            java.lang.String r2 = "current_bundle_count"
            java.lang.String r3 = "last_fire_timestamp"
            java.lang.String r4 = "last_bundled_timestamp"
            java.lang.String r5 = "last_bundled_day"
            java.lang.String r6 = "last_sampled_complex_event_id"
            java.lang.String r7 = "last_sampling_rate"
            java.lang.String r8 = "last_exempt_from_sampling"
            java.lang.String r9 = "current_session_count"
            java.lang.String[] r1 = new java.lang.String[]{r1, r2, r3, r4, r5, r6, r7, r8, r9}
            java.util.List r1 = java.util.Arrays.asList(r1)
            r0.<init>(r1)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r28.e_()     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r10 = 0
            java.lang.String[] r3 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            java.lang.Object[] r0 = r0.toArray(r3)     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r4 = r0
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            java.lang.String r5 = "app_id=? and name=?"
            java.lang.String[] r6 = new java.lang.String[]{r30, r31}     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            r7 = 0
            r8 = 0
            r9 = 0
            r3 = r29
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x0108, all -> 0x0106 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0104 }
            if (r0 != 0) goto L_0x0055
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            return r1
        L_0x0055:
            long r14 = r2.getLong(r10)     // Catch:{ SQLiteException -> 0x0104 }
            r0 = 1
            long r16 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x0104 }
            r3 = 2
            long r20 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r3 = 3
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r5 = 0
            if (r4 == 0) goto L_0x006f
            r22 = r5
            goto L_0x0075
        L_0x006f:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r22 = r3
        L_0x0075:
            r3 = 4
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x007f
            r24 = r1
            goto L_0x0089
        L_0x007f:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r24 = r3
        L_0x0089:
            r3 = 5
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x0093
            r25 = r1
            goto L_0x009d
        L_0x0093:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r25 = r3
        L_0x009d:
            r3 = 6
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 == 0) goto L_0x00a7
            r26 = r1
            goto L_0x00b1
        L_0x00a7:
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r26 = r3
        L_0x00b1:
            r3 = 7
            boolean r4 = r2.isNull(r3)     // Catch:{ SQLiteException -> 0x0104 }
            if (r4 != 0) goto L_0x00ca
            long r3 = r2.getLong(r3)     // Catch:{ SQLiteException -> 0x0104 }
            r7 = 1
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x00c3
            r10 = r0
        L_0x00c3:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r10)     // Catch:{ SQLiteException -> 0x0104 }
            r27 = r0
            goto L_0x00cc
        L_0x00ca:
            r27 = r1
        L_0x00cc:
            r0 = 8
            boolean r3 = r2.isNull(r0)     // Catch:{ SQLiteException -> 0x0104 }
            if (r3 == 0) goto L_0x00d7
            r18 = r5
            goto L_0x00dd
        L_0x00d7:
            long r3 = r2.getLong(r0)     // Catch:{ SQLiteException -> 0x0104 }
            r18 = r3
        L_0x00dd:
            com.google.android.gms.measurement.internal.zzbb r0 = new com.google.android.gms.measurement.internal.zzbb     // Catch:{ SQLiteException -> 0x0104 }
            r11 = r0
            r12 = r30
            r13 = r31
            r11.<init>(r12, r13, r14, r16, r18, r20, r22, r24, r25, r26, r27)     // Catch:{ SQLiteException -> 0x0104 }
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0104 }
            if (r3 == 0) goto L_0x00fe
            com.google.android.gms.measurement.internal.zzgo r3 = r28.zzj()     // Catch:{ SQLiteException -> 0x0104 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0104 }
            java.lang.String r4 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r30)     // Catch:{ SQLiteException -> 0x0104 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0104 }
        L_0x00fe:
            if (r2 == 0) goto L_0x0103
            r2.close()
        L_0x0103:
            return r0
        L_0x0104:
            r0 = move-exception
            goto L_0x010a
        L_0x0106:
            r0 = move-exception
            goto L_0x012d
        L_0x0108:
            r0 = move-exception
            r2 = r1
        L_0x010a:
            com.google.android.gms.measurement.internal.zzgo r3 = r28.zzj()     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x012b }
            java.lang.String r4 = "Error querying events. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r30)     // Catch:{ all -> 0x012b }
            com.google.android.gms.measurement.internal.zzgh r6 = r28.zzi()     // Catch:{ all -> 0x012b }
            r7 = r31
            java.lang.String r6 = r6.zza((java.lang.String) r7)     // Catch:{ all -> 0x012b }
            r3.zza(r4, r5, r6, r0)     // Catch:{ all -> 0x012b }
            if (r2 == 0) goto L_0x012a
            r2.close()
        L_0x012a:
            return r1
        L_0x012b:
            r0 = move-exception
            r1 = r2
        L_0x012d:
            if (r1 == 0) goto L_0x0132
            r1.close()
        L_0x0132:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzc(java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzbb");
    }

    public final zzje zzh(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        return zzje.zzb(zza("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    public final zzje zzi(String str) {
        Preconditions.checkNotNull(str);
        zzt();
        zzal();
        zzje zzje = (zzje) zza("select consent_state, consent_source from consent_settings where app_id=? limit 1;", new String[]{str}, new zzao());
        return zzje == null ? zzje.zza : zzje;
    }

    /* JADX WARNING: Removed duplicated region for block: B:59:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x019f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzoj zzj(java.lang.String r19) {
        /*
            r18 = this;
            r1 = r19
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            r18.zzt()
            r18.zzal()
            boolean r0 = com.google.android.gms.internal.measurement.zzpu.zza()
            r2 = 0
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.measurement.internal.zzag r0 = r18.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzcb
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r0 != 0) goto L_0x001f
            return r2
        L_0x001f:
            android.database.sqlite.SQLiteDatabase r3 = r18.e_()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r4 = "upload_queue"
            r0 = 7
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "rowId"
            r12 = 0
            r5[r12] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "app_id"
            r13 = 1
            r5[r13] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "measurement_batch"
            r14 = 2
            r5[r14] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "upload_uri"
            r15 = 3
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "upload_headers"
            r11 = 4
            r5[r11] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "upload_type"
            r10 = 5
            r5[r10] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = "retry_count"
            r9 = 6
            r5[r9] = r0     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r0 = r18.zzao()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r7 = "app_id=? AND NOT "
            r6.<init>(r7)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.StringBuilder r0 = r6.append(r0)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String r6 = r0.toString()     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            java.lang.String[] r7 = new java.lang.String[]{r19}     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            r8 = 0
            r0 = 0
            java.lang.String r16 = "creation_timestamp ASC"
            java.lang.String r17 = "1"
            r13 = r9
            r9 = r0
            r0 = r10
            r10 = r16
            r12 = r11
            r11 = r17
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0186, all -> 0x0184 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0182 }
            if (r4 != 0) goto L_0x0080
            if (r3 == 0) goto L_0x007f
            r3.close()
        L_0x007f:
            return r2
        L_0x0080:
            java.lang.String r4 = r3.getString(r15)     // Catch:{ SQLiteException -> 0x0182 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x0182 }
            if (r5 == 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzgo r0 = r18.zzj()     // Catch:{ SQLiteException -> 0x0182 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzc()     // Catch:{ SQLiteException -> 0x0182 }
            java.lang.String r4 = "Upload uri is null or empty. Destination is unknown. Dropping batch. "
            r0.zza(r4)     // Catch:{ SQLiteException -> 0x0182 }
            if (r3 == 0) goto L_0x009c
            r3.close()
        L_0x009c:
            return r2
        L_0x009d:
            com.google.android.gms.internal.measurement.zzfy$zzj$zza r5 = com.google.android.gms.internal.measurement.zzfy.zzj.zzb()     // Catch:{ IOException -> 0x016e }
            byte[] r6 = r3.getBlob(r14)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzlb r5 = com.google.android.gms.measurement.internal.zzoo.zza(r5, (byte[]) r6)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfy$zzj$zza r5 = (com.google.android.gms.internal.measurement.zzfy.zzj.zza) r5     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zznt[] r6 = com.google.android.gms.measurement.internal.zznt.values()     // Catch:{ IOException -> 0x016e }
            int r0 = r3.getInt(r0)     // Catch:{ IOException -> 0x016e }
            r0 = r6[r0]     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zznt r6 = com.google.android.gms.measurement.internal.zznt.SGTM     // Catch:{ IOException -> 0x016e }
            if (r0 == r6) goto L_0x00bd
            com.google.android.gms.measurement.internal.zznt r6 = com.google.android.gms.measurement.internal.zznt.GOOGLE_ANALYTICS     // Catch:{ IOException -> 0x016e }
            if (r0 != r6) goto L_0x00fe
        L_0x00bd:
            int r6 = r3.getInt(r13)     // Catch:{ IOException -> 0x016e }
            if (r6 <= 0) goto L_0x00fe
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ IOException -> 0x016e }
            r6.<init>()     // Catch:{ IOException -> 0x016e }
            java.util.List r7 = r5.zzd()     // Catch:{ IOException -> 0x016e }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ IOException -> 0x016e }
        L_0x00d0:
            boolean r8 = r7.hasNext()     // Catch:{ IOException -> 0x016e }
            if (r8 == 0) goto L_0x00f8
            java.lang.Object r8 = r7.next()     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = (com.google.android.gms.internal.measurement.zzfy.zzk) r8     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzjt$zzb r8 = r8.zzcd()     // Catch:{ IOException -> 0x016e }
            r9 = r8
            com.google.android.gms.internal.measurement.zzjt$zzb r9 = (com.google.android.gms.internal.measurement.zzjt.zzb) r9     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r8 = (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r8     // Catch:{ IOException -> 0x016e }
            int r9 = r3.getInt(r13)     // Catch:{ IOException -> 0x016e }
            r8.zzi((int) r9)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzlc r8 = r8.zzai()     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzjt r8 = (com.google.android.gms.internal.measurement.zzjt) r8     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfy$zzk r8 = (com.google.android.gms.internal.measurement.zzfy.zzk) r8     // Catch:{ IOException -> 0x016e }
            r6.add(r8)     // Catch:{ IOException -> 0x016e }
            goto L_0x00d0
        L_0x00f8:
            r5.zzb()     // Catch:{ IOException -> 0x016e }
            r5.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzfy.zzk>) r6)     // Catch:{ IOException -> 0x016e }
        L_0x00fe:
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ IOException -> 0x016e }
            r6.<init>()     // Catch:{ IOException -> 0x016e }
            java.lang.String r7 = r3.getString(r12)     // Catch:{ IOException -> 0x016e }
            if (r7 == 0) goto L_0x013e
            java.lang.String r8 = "\r\n"
            java.lang.String[] r7 = r7.split(r8)     // Catch:{ IOException -> 0x016e }
            int r8 = r7.length     // Catch:{ IOException -> 0x016e }
            r9 = 0
        L_0x0111:
            if (r9 >= r8) goto L_0x013e
            r10 = r7[r9]     // Catch:{ IOException -> 0x016e }
            boolean r11 = r10.isEmpty()     // Catch:{ IOException -> 0x016e }
            if (r11 != 0) goto L_0x013e
            java.lang.String r11 = "="
            java.lang.String[] r11 = r10.split(r11, r14)     // Catch:{ IOException -> 0x016e }
            int r12 = r11.length     // Catch:{ IOException -> 0x016e }
            if (r12 == r14) goto L_0x0132
            com.google.android.gms.measurement.internal.zzgo r7 = r18.zzj()     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzg()     // Catch:{ IOException -> 0x016e }
            java.lang.String r8 = "Invalid upload header: "
            r7.zza(r8, r10)     // Catch:{ IOException -> 0x016e }
            goto L_0x013e
        L_0x0132:
            r10 = 0
            r12 = r11[r10]     // Catch:{ IOException -> 0x016e }
            r10 = 1
            r11 = r11[r10]     // Catch:{ IOException -> 0x016e }
            r6.put(r12, r11)     // Catch:{ IOException -> 0x016e }
            int r9 = r9 + 1
            goto L_0x0111
        L_0x013e:
            com.google.android.gms.measurement.internal.zzom r7 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ IOException -> 0x016e }
            r7.<init>()     // Catch:{ IOException -> 0x016e }
            r8 = 0
            long r8 = r3.getLong(r8)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzom r7 = r7.zza((long) r8)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzlc r5 = r5.zzai()     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzjt r5 = (com.google.android.gms.internal.measurement.zzjt) r5     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.internal.measurement.zzfy$zzj r5 = (com.google.android.gms.internal.measurement.zzfy.zzj) r5     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzom r5 = r7.zza((com.google.android.gms.internal.measurement.zzfy.zzj) r5)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzom r4 = r5.zza((java.lang.String) r4)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzom r4 = r4.zza((java.util.Map<java.lang.String, java.lang.String>) r6)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzom r0 = r4.zza((com.google.android.gms.measurement.internal.zznt) r0)     // Catch:{ IOException -> 0x016e }
            com.google.android.gms.measurement.internal.zzoj r0 = r0.zza()     // Catch:{ IOException -> 0x016e }
            if (r3 == 0) goto L_0x016d
            r3.close()
        L_0x016d:
            return r0
        L_0x016e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r18.zzj()     // Catch:{ SQLiteException -> 0x0182 }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ SQLiteException -> 0x0182 }
            java.lang.String r5 = "Failed to queued MeasurementBatch from upload_queue. appId"
            r4.zza(r5, r1, r0)     // Catch:{ SQLiteException -> 0x0182 }
            if (r3 == 0) goto L_0x0181
            r3.close()
        L_0x0181:
            return r2
        L_0x0182:
            r0 = move-exception
            goto L_0x0188
        L_0x0184:
            r0 = move-exception
            goto L_0x019d
        L_0x0186:
            r0 = move-exception
            r3 = r2
        L_0x0188:
            com.google.android.gms.measurement.internal.zzgo r4 = r18.zzj()     // Catch:{ all -> 0x019b }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x019b }
            java.lang.String r5 = "Error to querying MeasurementBatch from upload_queue. appId"
            r4.zza(r5, r1, r0)     // Catch:{ all -> 0x019b }
            if (r3 == 0) goto L_0x019a
            r3.close()
        L_0x019a:
            return r2
        L_0x019b:
            r0 = move-exception
            r2 = r3
        L_0x019d:
            if (r2 == 0) goto L_0x01a2
            r2.close()
        L_0x01a2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzj(java.lang.String):com.google.android.gms.measurement.internal.zzoj");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzop zze(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r15)
            r13.zzt()
            r13.zzal()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.e_()     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r3 = "user_attributes"
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r0 = "set_timestamp"
            r10 = 0
            r4[r10] = r0     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r0 = "value"
            r11 = 1
            r4[r11] = r0     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r0 = "origin"
            r12 = 2
            r4[r12] = r0     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            java.lang.String r5 = "app_id=? and name=?"
            java.lang.String[] r6 = new java.lang.String[]{r14, r15}     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x007b, all -> 0x0079 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0077 }
            if (r0 != 0) goto L_0x003e
            if (r2 == 0) goto L_0x003d
            r2.close()
        L_0x003d:
            return r1
        L_0x003e:
            long r7 = r2.getLong(r10)     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.Object r9 = r13.zza((android.database.Cursor) r2, (int) r11)     // Catch:{ SQLiteException -> 0x0077 }
            if (r9 != 0) goto L_0x004e
            if (r2 == 0) goto L_0x004d
            r2.close()
        L_0x004d:
            return r1
        L_0x004e:
            java.lang.String r5 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzop r0 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ SQLiteException -> 0x0077 }
            r3 = r0
            r4 = r14
            r6 = r15
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x0077 }
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0077 }
            if (r3 == 0) goto L_0x0071
            com.google.android.gms.measurement.internal.zzgo r3 = r13.zzj()     // Catch:{ SQLiteException -> 0x0077 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0077 }
            java.lang.String r4 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r14)     // Catch:{ SQLiteException -> 0x0077 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0077 }
        L_0x0071:
            if (r2 == 0) goto L_0x0076
            r2.close()
        L_0x0076:
            return r0
        L_0x0077:
            r0 = move-exception
            goto L_0x007d
        L_0x0079:
            r0 = move-exception
            goto L_0x009e
        L_0x007b:
            r0 = move-exception
            r2 = r1
        L_0x007d:
            com.google.android.gms.measurement.internal.zzgo r3 = r13.zzj()     // Catch:{ all -> 0x009c }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x009c }
            java.lang.String r4 = "Error querying user property. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r14)     // Catch:{ all -> 0x009c }
            com.google.android.gms.measurement.internal.zzgh r6 = r13.zzi()     // Catch:{ all -> 0x009c }
            java.lang.String r6 = r6.zzc(r15)     // Catch:{ all -> 0x009c }
            r3.zza(r4, r5, r6, r0)     // Catch:{ all -> 0x009c }
            if (r2 == 0) goto L_0x009b
            r2.close()
        L_0x009b:
            return r1
        L_0x009c:
            r0 = move-exception
            r1 = r2
        L_0x009e:
            if (r1 == 0) goto L_0x00a3
            r1.close()
        L_0x00a3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zze(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzop");
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzj().zzg().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                zzj().zzg().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            zzj().zzg().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T zza(java.lang.String r3, java.lang.String[] r4, com.google.android.gms.measurement.internal.zzau<T> r5) {
        /*
            r2 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r2.e_()     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            android.database.Cursor r3 = r1.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0030, all -> 0x002e }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x002c }
            if (r4 != 0) goto L_0x0022
            com.google.android.gms.measurement.internal.zzgo r4 = r2.zzj()     // Catch:{ SQLiteException -> 0x002c }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzp()     // Catch:{ SQLiteException -> 0x002c }
            java.lang.String r5 = "No data found"
            r4.zza(r5)     // Catch:{ SQLiteException -> 0x002c }
            if (r3 == 0) goto L_0x0021
            r3.close()
        L_0x0021:
            return r0
        L_0x0022:
            java.lang.Object r4 = r5.zza(r3)     // Catch:{ SQLiteException -> 0x002c }
            if (r3 == 0) goto L_0x002b
            r3.close()
        L_0x002b:
            return r4
        L_0x002c:
            r4 = move-exception
            goto L_0x0032
        L_0x002e:
            r4 = move-exception
            goto L_0x0047
        L_0x0030:
            r4 = move-exception
            r3 = r0
        L_0x0032:
            com.google.android.gms.measurement.internal.zzgo r5 = r2.zzj()     // Catch:{ all -> 0x0045 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch:{ all -> 0x0045 }
            java.lang.String r1 = "Error querying database."
            r5.zza(r1, r4)     // Catch:{ all -> 0x0045 }
            if (r3 == 0) goto L_0x0044
            r3.close()
        L_0x0044:
            return r0
        L_0x0045:
            r4 = move-exception
            r0 = r3
        L_0x0047:
            if (r0 == 0) goto L_0x004c
            r0.close()
        L_0x004c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.String[], com.google.android.gms.measurement.internal.zzau):java.lang.Object");
    }

    private final String zzao() {
        long currentTimeMillis = zzb().currentTimeMillis();
        String str = "(upload_type = " + zznt.GOOGLE_SIGNAL.zza() + " AND (ABS(creation_timestamp - " + currentTimeMillis + ") > CAST(" + zzbh.zzaf.zza(null).longValue() + " AS INTEGER)))";
        int zza2 = zznt.GOOGLE_SIGNAL.zza();
        return "(" + str + " OR " + ("(upload_type != " + zza2 + " AND (ABS(creation_timestamp - " + currentTimeMillis + ") > CAST(" + zzag.zzm() + " AS INTEGER)))") + ")";
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(long r5) {
        /*
            r4 = this;
            r4.zzt()
            r4.zzal()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.e_()     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0042, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.measurement.internal.zzgo r6 = r4.zzj()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.measurement.internal.zzgq r6 = r6.zzp()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zza(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x0059
        L_0x0042:
            r6 = move-exception
            r5 = r0
        L_0x0044:
            com.google.android.gms.measurement.internal.zzgo r1 = r4.zzj()     // Catch:{ all -> 0x0057 }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x0057 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch:{ all -> 0x0057 }
            if (r5 == 0) goto L_0x0056
            r5.close()
        L_0x0056:
            return r0
        L_0x0057:
            r6 = move-exception
            r0 = r5
        L_0x0059:
            if (r0 == 0) goto L_0x005e
            r0.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String f_() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.e_()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.measurement.internal.zzgo r3 = r6.zzj()     // Catch:{ all -> 0x003e }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zza(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.f_():java.lang.String");
    }

    private final String zza(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = e_().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                String string = rawQuery.getString(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return string;
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return str2;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:86:0x0176 A[EDGE_INSN: B:86:0x0176->B:73:0x0176 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzfy.zzk, java.lang.Long>> zza(java.lang.String r20, int r21, int r22) {
        /*
            r19 = this;
            r1 = r22
            r19.zzt()
            r19.zzal()
            r2 = 1
            r3 = 0
            if (r21 <= 0) goto L_0x000e
            r0 = r2
            goto L_0x000f
        L_0x000e:
            r0 = r3
        L_0x000f:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)
            if (r1 <= 0) goto L_0x0016
            r0 = r2
            goto L_0x0017
        L_0x0016:
            r0 = r3
        L_0x0017:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r4 = 0
            android.database.sqlite.SQLiteDatabase r5 = r19.e_()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r6 = "queue"
            r0 = 3
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r0 = "rowid"
            r7[r3] = r0     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r0 = "data"
            r7[r2] = r0     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r0 = "retry_count"
            r14 = 2
            r7[r14] = r0     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r8 = "app_id=?"
            java.lang.String[] r9 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x017e }
            r10 = 0
            r11 = 0
            java.lang.String r12 = "rowid"
            java.lang.String r13 = java.lang.String.valueOf(r21)     // Catch:{ SQLiteException -> 0x017e }
            android.database.Cursor r4 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x017e }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x017e }
            if (r0 != 0) goto L_0x0056
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x017e }
            if (r4 == 0) goto L_0x0055
            r4.close()
        L_0x0055:
            return r0
        L_0x0056:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x017e }
            r5.<init>()     // Catch:{ SQLiteException -> 0x017e }
            r6 = r3
        L_0x005c:
            long r7 = r4.getLong(r3)     // Catch:{ SQLiteException -> 0x017e }
            byte[] r0 = r4.getBlob(r2)     // Catch:{ IOException -> 0x0158 }
            com.google.android.gms.measurement.internal.zzoo r9 = r19.g_()     // Catch:{ IOException -> 0x0158 }
            byte[] r0 = r9.zzc(r0)     // Catch:{ IOException -> 0x0158 }
            boolean r9 = r5.isEmpty()     // Catch:{ SQLiteException -> 0x017e }
            if (r9 != 0) goto L_0x0076
            int r9 = r0.length     // Catch:{ SQLiteException -> 0x017e }
            int r9 = r9 + r6
            if (r9 > r1) goto L_0x0176
        L_0x0076:
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r9 = com.google.android.gms.internal.measurement.zzfy.zzk.zzw()     // Catch:{ IOException -> 0x0145 }
            com.google.android.gms.internal.measurement.zzlb r9 = com.google.android.gms.measurement.internal.zzoo.zza(r9, (byte[]) r0)     // Catch:{ IOException -> 0x0145 }
            com.google.android.gms.internal.measurement.zzfy$zzk$zza r9 = (com.google.android.gms.internal.measurement.zzfy.zzk.zza) r9     // Catch:{ IOException -> 0x0145 }
            boolean r10 = r5.isEmpty()     // Catch:{ SQLiteException -> 0x017e }
            if (r10 != 0) goto L_0x0122
            java.lang.Object r10 = r5.get(r3)     // Catch:{ SQLiteException -> 0x017e }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ SQLiteException -> 0x017e }
            java.lang.Object r10 = r10.first     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzfy$zzk r10 = (com.google.android.gms.internal.measurement.zzfy.zzk) r10     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzlc r11 = r9.zzai()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzjt r11 = (com.google.android.gms.internal.measurement.zzjt) r11     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzfy$zzk r11 = (com.google.android.gms.internal.measurement.zzfy.zzk) r11     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r12 = r10.zzae()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r13 = r11.zzae()     // Catch:{ SQLiteException -> 0x017e }
            boolean r12 = r12.equals(r13)     // Catch:{ SQLiteException -> 0x017e }
            if (r12 == 0) goto L_0x011f
            java.lang.String r12 = r10.zzad()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r13 = r11.zzad()     // Catch:{ SQLiteException -> 0x017e }
            boolean r12 = r12.equals(r13)     // Catch:{ SQLiteException -> 0x017e }
            if (r12 == 0) goto L_0x011f
            boolean r12 = r10.zzau()     // Catch:{ SQLiteException -> 0x017e }
            boolean r13 = r11.zzau()     // Catch:{ SQLiteException -> 0x017e }
            if (r12 != r13) goto L_0x011f
            java.lang.String r12 = r10.zzaf()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r13 = r11.zzaf()     // Catch:{ SQLiteException -> 0x017e }
            boolean r12 = r12.equals(r13)     // Catch:{ SQLiteException -> 0x017e }
            if (r12 == 0) goto L_0x011f
            java.util.List r10 = r10.zzas()     // Catch:{ SQLiteException -> 0x017e }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ SQLiteException -> 0x017e }
        L_0x00d4:
            boolean r12 = r10.hasNext()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r13 = "_npa"
            r15 = -1
            if (r12 == 0) goto L_0x00f5
            java.lang.Object r12 = r10.next()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzfy$zzo r12 = (com.google.android.gms.internal.measurement.zzfy.zzo) r12     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r2 = r12.zzg()     // Catch:{ SQLiteException -> 0x017e }
            boolean r2 = r13.equals(r2)     // Catch:{ SQLiteException -> 0x017e }
            if (r2 == 0) goto L_0x00f3
            long r17 = r12.zzc()     // Catch:{ SQLiteException -> 0x017e }
            goto L_0x00f7
        L_0x00f3:
            r2 = 1
            goto L_0x00d4
        L_0x00f5:
            r17 = r15
        L_0x00f7:
            java.util.List r2 = r11.zzas()     // Catch:{ SQLiteException -> 0x017e }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ SQLiteException -> 0x017e }
        L_0x00ff:
            boolean r10 = r2.hasNext()     // Catch:{ SQLiteException -> 0x017e }
            if (r10 == 0) goto L_0x0119
            java.lang.Object r10 = r2.next()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzfy$zzo r10 = (com.google.android.gms.internal.measurement.zzfy.zzo) r10     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r11 = r10.zzg()     // Catch:{ SQLiteException -> 0x017e }
            boolean r11 = r13.equals(r11)     // Catch:{ SQLiteException -> 0x017e }
            if (r11 == 0) goto L_0x00ff
            long r15 = r10.zzc()     // Catch:{ SQLiteException -> 0x017e }
        L_0x0119:
            int r2 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x011f
            r2 = 1
            goto L_0x0120
        L_0x011f:
            r2 = r3
        L_0x0120:
            if (r2 == 0) goto L_0x0176
        L_0x0122:
            boolean r2 = r4.isNull(r14)     // Catch:{ SQLiteException -> 0x017e }
            if (r2 != 0) goto L_0x012f
            int r2 = r4.getInt(r14)     // Catch:{ SQLiteException -> 0x017e }
            r9.zzi((int) r2)     // Catch:{ SQLiteException -> 0x017e }
        L_0x012f:
            int r0 = r0.length     // Catch:{ SQLiteException -> 0x017e }
            int r6 = r6 + r0
            com.google.android.gms.internal.measurement.zzlc r0 = r9.zzai()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzjt r0 = (com.google.android.gms.internal.measurement.zzjt) r0     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.internal.measurement.zzfy$zzk r0 = (com.google.android.gms.internal.measurement.zzfy.zzk) r0     // Catch:{ SQLiteException -> 0x017e }
            java.lang.Long r2 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x017e }
            android.util.Pair r0 = android.util.Pair.create(r0, r2)     // Catch:{ SQLiteException -> 0x017e }
            r5.add(r0)     // Catch:{ SQLiteException -> 0x017e }
            goto L_0x016a
        L_0x0145:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r2 = r19.zzj()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r7 = "Failed to merge queued bundle. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r20)     // Catch:{ SQLiteException -> 0x017e }
            r2.zza(r7, r8, r0)     // Catch:{ SQLiteException -> 0x017e }
            goto L_0x016a
        L_0x0158:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r2 = r19.zzj()     // Catch:{ SQLiteException -> 0x017e }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ SQLiteException -> 0x017e }
            java.lang.String r7 = "Failed to unzip queued bundle. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r20)     // Catch:{ SQLiteException -> 0x017e }
            r2.zza(r7, r8, r0)     // Catch:{ SQLiteException -> 0x017e }
        L_0x016a:
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x017e }
            if (r0 == 0) goto L_0x0176
            if (r6 <= r1) goto L_0x0173
            goto L_0x0176
        L_0x0173:
            r2 = 1
            goto L_0x005c
        L_0x0176:
            if (r4 == 0) goto L_0x017b
            r4.close()
        L_0x017b:
            return r5
        L_0x017c:
            r0 = move-exception
            goto L_0x019a
        L_0x017e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r1 = r19.zzj()     // Catch:{ all -> 0x017c }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x017c }
            java.lang.String r2 = "Error querying bundles. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r20)     // Catch:{ all -> 0x017c }
            r1.zza(r2, r3, r0)     // Catch:{ all -> 0x017c }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x017c }
            if (r4 == 0) goto L_0x0199
            r4.close()
        L_0x0199:
            return r0
        L_0x019a:
            if (r4 == 0) goto L_0x019f
            r4.close()
        L_0x019f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, int, int):java.util.List");
    }

    public final List<zzae> zza(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(str3 + ProxyConfig.MATCH_ALL_SCHEMES);
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0173  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzae> zza(java.lang.String r40, java.lang.String[] r41) {
        /*
            r39 = this;
            r39.zzt()
            r39.zzal()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r2 = r39.e_()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r3 = "conditional_properties"
            r4 = 13
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "app_id"
            r11 = 0
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "origin"
            r12 = 1
            r4[r12] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "name"
            r13 = 2
            r4[r13] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "value"
            r14 = 3
            r4[r14] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "active"
            r15 = 4
            r4[r15] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "trigger_event_name"
            r10 = 5
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "trigger_timeout"
            r9 = 6
            r4[r9] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "timed_out_event"
            r8 = 7
            r4[r8] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "creation_timestamp"
            r7 = 8
            r4[r7] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "triggered_event"
            r6 = 9
            r4[r6] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "triggered_timestamp"
            r1 = 10
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "time_to_live"
            r1 = 11
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            java.lang.String r5 = "expired_event"
            r1 = 12
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "1001"
            r5 = r40
            r1 = r6
            r6 = r41
            r1 = r7
            r7 = r19
            r1 = r8
            r8 = r20
            r1 = r9
            r9 = r21
            r1 = r10
            r10 = r22
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0154 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            if (r3 != 0) goto L_0x0084
            if (r2 == 0) goto L_0x0083
            r2.close()
        L_0x0083:
            return r0
        L_0x0084:
            int r3 = r0.size()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r4) goto L_0x009f
            com.google.android.gms.measurement.internal.zzgo r1 = r39.zzj()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            java.lang.String r3 = "Read more than the max allowed conditional properties, ignoring extra"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r1.zza(r3, r4)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            goto L_0x0144
        L_0x009f:
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            java.lang.String r10 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            java.lang.String r5 = r2.getString(r13)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r9 = r39
            java.lang.Object r8 = r9.zza((android.database.Cursor) r2, (int) r14)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            int r4 = r2.getInt(r15)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            if (r4 == 0) goto L_0x00ba
            r22 = r12
            goto L_0x00bc
        L_0x00ba:
            r22 = r11
        L_0x00bc:
            java.lang.String r24 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r6 = 6
            long r25 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzoo r4 = r39.g_()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r7 = 7
            byte[] r1 = r2.getBlob(r7)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r6 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable r1 = r4.zza((byte[]) r1, r6)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzbf r1 = (com.google.android.gms.measurement.internal.zzbf) r1     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r6 = 8
            long r27 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzoo r4 = r39.g_()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r11 = 9
            byte[] r6 = r2.getBlob(r11)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r7 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable r4 = r4.zza((byte[]) r6, r7)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r29 = r4
            com.google.android.gms.measurement.internal.zzbf r29 = (com.google.android.gms.measurement.internal.zzbf) r29     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r6 = 10
            long r16 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r7 = 11
            long r31 = r2.getLong(r7)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzoo r4 = r39.g_()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r11 = 12
            byte[] r6 = r2.getBlob(r11)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r7 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            android.os.Parcelable r4 = r4.zza((byte[]) r6, r7)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r30 = r4
            com.google.android.gms.measurement.internal.zzbf r30 = (com.google.android.gms.measurement.internal.zzbf) r30     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzon r33 = new com.google.android.gms.measurement.internal.zzon     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r4 = r33
            r34 = 11
            r35 = 10
            r36 = 8
            r37 = 7
            r38 = 6
            r6 = r16
            r9 = r10
            r4.<init>(r5, r6, r8, r9)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            com.google.android.gms.measurement.internal.zzae r4 = new com.google.android.gms.measurement.internal.zzae     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r16 = r4
            r17 = r3
            r18 = r10
            r19 = r33
            r20 = r27
            r23 = r24
            r24 = r1
            r27 = r29
            r28 = r31
            r16.<init>(r17, r18, r19, r20, r22, r23, r24, r25, r27, r28, r30)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            r0.add(r4)     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            boolean r1 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0151, all -> 0x014e }
            if (r1 != 0) goto L_0x014a
        L_0x0144:
            if (r2 == 0) goto L_0x0149
            r2.close()
        L_0x0149:
            return r0
        L_0x014a:
            r1 = 5
            r11 = 0
            goto L_0x0084
        L_0x014e:
            r0 = move-exception
            r1 = r2
            goto L_0x0171
        L_0x0151:
            r0 = move-exception
            r1 = r2
            goto L_0x0159
        L_0x0154:
            r0 = move-exception
            r1 = 0
            goto L_0x0171
        L_0x0157:
            r0 = move-exception
            r1 = 0
        L_0x0159:
            com.google.android.gms.measurement.internal.zzgo r2 = r39.zzj()     // Catch:{ all -> 0x0170 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x0170 }
            java.lang.String r3 = "Error querying conditional user property value"
            r2.zza(r3, r0)     // Catch:{ all -> 0x0170 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0170 }
            if (r1 == 0) goto L_0x016f
            r1.close()
        L_0x016f:
            return r0
        L_0x0170:
            r0 = move-exception
        L_0x0171:
            if (r1 == 0) goto L_0x0176
            r1.close()
        L_0x0176:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(java.lang.String, java.lang.String[]):java.util.List");
    }

    public final List<zzno> zzk(String str) {
        Preconditions.checkNotEmpty(str);
        zzt();
        zzal();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            cursor = e_().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", "source"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", (String) null);
            if (!cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return arrayList;
            }
            do {
                String string = cursor.getString(0);
                if (string == null) {
                    string = "";
                }
                arrayList.add(new zzno(string, cursor.getLong(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error querying trigger uris. appId", zzgo.zza(str), e);
            List<zzno> emptyList = Collections.emptyList();
            if (cursor != null) {
                cursor.close();
            }
            return emptyList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzop> zzl(java.lang.String r16) {
        /*
            r15 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r16)
            r15.zzt()
            r15.zzal()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r15.e_()     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r3 = "user_attributes"
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r5 = "name"
            r11 = 0
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r5 = "origin"
            r12 = 1
            r4[r12] = r5     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r5 = "set_timestamp"
            r13 = 2
            r4[r13] = r5     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r5 = "value"
            r14 = 3
            r4[r14] = r5     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[]{r16}     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            if (r2 != 0) goto L_0x0048
            if (r1 == 0) goto L_0x0047
            r1.close()
        L_0x0047:
            return r0
        L_0x0048:
            java.lang.String r6 = r1.getString(r11)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            java.lang.String r2 = r1.getString(r12)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            if (r2 != 0) goto L_0x0054
            java.lang.String r2 = ""
        L_0x0054:
            r5 = r2
            long r7 = r1.getLong(r13)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008b }
            r2 = r15
            java.lang.Object r9 = r15.zza((android.database.Cursor) r1, (int) r14)     // Catch:{ SQLiteException -> 0x0089 }
            if (r9 != 0) goto L_0x0072
            com.google.android.gms.measurement.internal.zzgo r3 = r15.zzj()     // Catch:{ SQLiteException -> 0x0089 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ SQLiteException -> 0x0089 }
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r16)     // Catch:{ SQLiteException -> 0x0089 }
            r3.zza(r4, r5)     // Catch:{ SQLiteException -> 0x0089 }
            goto L_0x007d
        L_0x0072:
            com.google.android.gms.measurement.internal.zzop r10 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ SQLiteException -> 0x0089 }
            r3 = r10
            r4 = r16
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x0089 }
            r0.add(r10)     // Catch:{ SQLiteException -> 0x0089 }
        L_0x007d:
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0089 }
            if (r3 != 0) goto L_0x0048
            if (r1 == 0) goto L_0x0088
            r1.close()
        L_0x0088:
            return r0
        L_0x0089:
            r0 = move-exception
            goto L_0x0090
        L_0x008b:
            r0 = move-exception
            r2 = r15
            goto L_0x00ac
        L_0x008e:
            r0 = move-exception
            r2 = r15
        L_0x0090:
            com.google.android.gms.measurement.internal.zzgo r3 = r15.zzj()     // Catch:{ all -> 0x00ab }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x00ab }
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r16)     // Catch:{ all -> 0x00ab }
            r3.zza(r4, r5, r0)     // Catch:{ all -> 0x00ab }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x00aa
            r1.close()
        L_0x00aa:
            return r0
        L_0x00ab:
            r0 = move-exception
        L_0x00ac:
            if (r1 == 0) goto L_0x00b1
            r1.close()
        L_0x00b1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzl(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011e, code lost:
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0125, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0126, code lost:
        r3 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0129, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x012a, code lost:
        r3 = r22;
        r12 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014e, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0125 A[ExcHandler: all (th java.lang.Throwable), PHI: r2 
      PHI: (r2v3 android.database.Cursor) = (r2v0 android.database.Cursor), (r2v0 android.database.Cursor), (r2v0 android.database.Cursor), (r2v5 android.database.Cursor), (r2v0 android.database.Cursor) binds: [B:1:0x0011, B:2:?, B:4:0x0019, B:19:0x009d, B:8:0x002b] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzop> zzb(java.lang.String r23, java.lang.String r24, java.lang.String r25) {
        /*
            r22 = this;
            r0 = r25
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r22.zzt()
            r22.zzal()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0129, all -> 0x0125 }
            r4 = 3
            r3.<init>(r4)     // Catch:{ SQLiteException -> 0x0129, all -> 0x0125 }
            r12 = r23
            r3.add(r12)     // Catch:{ SQLiteException -> 0x0121, all -> 0x0125 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0121, all -> 0x0125 }
            java.lang.String r6 = "app_id=?"
            r5.<init>(r6)     // Catch:{ SQLiteException -> 0x0121, all -> 0x0125 }
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x0121, all -> 0x0125 }
            if (r6 != 0) goto L_0x0034
            r6 = r24
            r3.add(r6)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r7 = " and origin=?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            goto L_0x0036
        L_0x0034:
            r6 = r24
        L_0x0036:
            boolean r7 = android.text.TextUtils.isEmpty(r25)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            if (r7 != 0) goto L_0x0057
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r7.<init>()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r8 = "*"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r7 = r7.toString()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r3.add(r7)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r7 = " and name glob ?"
            r5.append(r7)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
        L_0x0057:
            int r7 = r3.size()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.Object[] r3 = r3.toArray(r7)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r17 = r3
            java.lang.String[] r17 = (java.lang.String[]) r17     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            android.database.sqlite.SQLiteDatabase r13 = r22.e_()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r14 = "user_attributes"
            r3 = 4
            java.lang.String[] r15 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r3 = "name"
            r11 = 0
            r15[r11] = r3     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r3 = "set_timestamp"
            r9 = 1
            r15[r9] = r3     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r3 = "value"
            r10 = 2
            r15[r10] = r3     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r3 = "origin"
            r15[r4] = r3     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r16 = r5.toString()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r18 = 0
            r19 = 0
            java.lang.String r20 = "rowid"
            java.lang.String r21 = "1001"
            android.database.Cursor r2 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            if (r3 != 0) goto L_0x009d
            if (r2 == 0) goto L_0x009c
            r2.close()
        L_0x009c:
            return r1
        L_0x009d:
            int r3 = r1.size()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r5) goto L_0x00b9
            com.google.android.gms.measurement.internal.zzgo r0 = r22.zzj()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            java.lang.String r3 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r0.zza(r3, r4)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r3 = r22
            goto L_0x0104
        L_0x00b9:
            java.lang.String r8 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            long r13 = r2.getLong(r9)     // Catch:{ SQLiteException -> 0x011d, all -> 0x0125 }
            r3 = r22
            java.lang.Object r15 = r3.zza((android.database.Cursor) r2, (int) r10)     // Catch:{ SQLiteException -> 0x011b }
            java.lang.String r7 = r2.getString(r4)     // Catch:{ SQLiteException -> 0x011b }
            if (r15 != 0) goto L_0x00e9
            com.google.android.gms.measurement.internal.zzgo r5 = r22.zzj()     // Catch:{ SQLiteException -> 0x00e6 }
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzg()     // Catch:{ SQLiteException -> 0x00e6 }
            java.lang.String r6 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r23)     // Catch:{ SQLiteException -> 0x00e6 }
            r5.zza(r6, r8, r7, r0)     // Catch:{ SQLiteException -> 0x00e6 }
            r17 = r7
            r19 = r9
            r18 = r10
            r13 = r11
            goto L_0x00fe
        L_0x00e6:
            r0 = move-exception
            r6 = r7
            goto L_0x0130
        L_0x00e9:
            com.google.android.gms.measurement.internal.zzop r6 = new com.google.android.gms.measurement.internal.zzop     // Catch:{ SQLiteException -> 0x0115 }
            r5 = r6
            r4 = r6
            r6 = r23
            r17 = r7
            r19 = r9
            r18 = r10
            r9 = r13
            r13 = r11
            r11 = r15
            r5.<init>(r6, r7, r8, r9, r11)     // Catch:{ SQLiteException -> 0x0113 }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x0113 }
        L_0x00fe:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0113 }
            if (r4 != 0) goto L_0x010a
        L_0x0104:
            if (r2 == 0) goto L_0x0109
            r2.close()
        L_0x0109:
            return r1
        L_0x010a:
            r11 = r13
            r6 = r17
            r10 = r18
            r9 = r19
            r4 = 3
            goto L_0x009d
        L_0x0113:
            r0 = move-exception
            goto L_0x0118
        L_0x0115:
            r0 = move-exception
            r17 = r7
        L_0x0118:
            r6 = r17
            goto L_0x0130
        L_0x011b:
            r0 = move-exception
            goto L_0x0130
        L_0x011d:
            r0 = move-exception
            r3 = r22
            goto L_0x0130
        L_0x0121:
            r0 = move-exception
            r3 = r22
            goto L_0x012e
        L_0x0125:
            r0 = move-exception
            r3 = r22
            goto L_0x014c
        L_0x0129:
            r0 = move-exception
            r3 = r22
            r12 = r23
        L_0x012e:
            r6 = r24
        L_0x0130:
            com.google.android.gms.measurement.internal.zzgo r1 = r22.zzj()     // Catch:{ all -> 0x014b }
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()     // Catch:{ all -> 0x014b }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r23)     // Catch:{ all -> 0x014b }
            r1.zza(r4, r5, r6, r0)     // Catch:{ all -> 0x014b }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x014b }
            if (r2 == 0) goto L_0x014a
            r2.close()
        L_0x014a:
            return r0
        L_0x014b:
            r0 = move-exception
        L_0x014c:
            if (r2 == 0) goto L_0x0151
            r2.close()
        L_0x0151:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, zzfy.zzm> zzm(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            cursor = e_().query("audience_filter_values", new String[]{"audience_id", "current_results"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, zzfy.zzm> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            ArrayMap arrayMap = new ArrayMap();
            do {
                int i = cursor.getInt(0);
                try {
                    arrayMap.put(Integer.valueOf(i), (zzfy.zzm) ((zzjt) ((zzfy.zzm.zza) zzoo.zza(zzfy.zzm.zze(), cursor.getBlob(1))).zzai()));
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter results. appId, audienceId, error", zzgo.zza(str), Integer.valueOf(i), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filter results. appId", zzgo.zza(str), e2);
            Map<Integer, zzfy.zzm> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzfo.zzb>> zzn(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfo.zzb>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzfo.zzb zzb2 = (zzfo.zzb) ((zzjt) ((zzfo.zzb.zza) zzoo.zza(zzfo.zzb.zzc(), cursor.getBlob(1))).zzai());
                    if (zzb2.zzk()) {
                        int i = cursor.getInt(0);
                        List list = (List) arrayMap.get(Integer.valueOf(i));
                        if (list == null) {
                            list = new ArrayList();
                            arrayMap.put(Integer.valueOf(i), list);
                        }
                        list.add(zzb2);
                    }
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzgo.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
            Map<Integer, List<zzfo.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzfo.zzb>> zzf(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("event_filters", new String[]{"audience_id", "data"}, "app_id=? AND event_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfo.zzb>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzfo.zzb zzb2 = (zzfo.zzb) ((zzjt) ((zzfo.zzb.zza) zzoo.zza(zzfo.zzb.zzc(), cursor.getBlob(1))).zzai());
                    int i = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), list);
                    }
                    list.add(zzb2);
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter. appId", zzgo.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
            Map<Integer, List<zzfo.zzb>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<zzfo.zze>> zzg(String str, String str2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().query("property_filters", new String[]{"audience_id", "data"}, "app_id=? AND property_name=?", new String[]{str, str2}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                Map<Integer, List<zzfo.zze>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                try {
                    zzfo.zze zze2 = (zzfo.zze) ((zzjt) ((zzfo.zze.zza) zzoo.zza(zzfo.zze.zzc(), cursor.getBlob(1))).zzai());
                    int i = cursor.getInt(0);
                    List list = (List) arrayMap.get(Integer.valueOf(i));
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(Integer.valueOf(i), list);
                    }
                    list.add(zze2);
                } catch (IOException e) {
                    zzj().zzg().zza("Failed to merge filter", zzgo.zza(str), e);
                }
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e2) {
            zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e2);
            Map<Integer, List<zzfo.zze>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<Integer, List<Integer>> zzo(String str) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            cursor = e_().rawQuery("select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;", new String[]{str, str});
            if (!cursor.moveToFirst()) {
                Map<Integer, List<Integer>> emptyMap = Collections.emptyMap();
                if (cursor != null) {
                    cursor.close();
                }
                return emptyMap;
            }
            do {
                int i = cursor.getInt(0);
                List list = (List) arrayMap.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList();
                    arrayMap.put(Integer.valueOf(i), list);
                }
                list.add(Integer.valueOf(cursor.getInt(1)));
            } while (cursor.moveToNext());
            if (cursor != null) {
                cursor.close();
            }
            return arrayMap;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error querying scoped filters. appId", zzgo.zza(str), e);
            Map<Integer, List<Integer>> emptyMap2 = Collections.emptyMap();
            if (cursor != null) {
                cursor.close();
            }
            return emptyMap2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    zzal(zznv zznv) {
        super(zznv);
    }

    public final void zza(String str, Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        zzt();
        zzal();
        zzar zzar = new zzar(this, str);
        List<zzap> zza2 = zzar.zza();
        while (!zza2.isEmpty()) {
            for (zzap next : zza2) {
                zzoo g_ = g_();
                zzfy.zzf zzf2 = next.zzd;
                Bundle bundle2 = new Bundle();
                for (zzfy.zzh next2 : zzf2.zzh()) {
                    if (next2.zzj()) {
                        bundle2.putDouble(next2.zzg(), next2.zza());
                    } else if (next2.zzk()) {
                        bundle2.putFloat(next2.zzg(), next2.zzb());
                    } else if (next2.zzl()) {
                        bundle2.putLong(next2.zzg(), next2.zzd());
                    } else if (next2.zzn()) {
                        bundle2.putString(next2.zzg(), next2.zzh());
                    } else if (!next2.zzi().isEmpty()) {
                        bundle2.putParcelableArray(next2.zzg(), zzoo.zzb(next2.zzi()));
                    } else {
                        g_.zzj().zzg().zza("Unexpected parameter type for parameter", next2);
                    }
                }
                String string = bundle2.getString("_o");
                bundle2.remove("_o");
                String zzg = zzf2.zzg();
                if (string == null) {
                    string = "";
                }
                zzgs zzgs = new zzgs(zzg, string, bundle2, zzf2.zzd());
                zzq().zza(zzgs.zzc, bundle);
                zzbc zzbc = new zzbc(this.zzu, zzgs.zzb, str, next.zzd.zzg(), next.zzd.zzd(), next.zzd.zzc(), zzgs.zzc);
                zza(next.zza, zzbc, next.zzb, next.zzc);
                String str2 = str;
            }
            zza2 = zzar.zza();
            String str3 = str;
        }
    }

    public final void zzp() {
        zzal();
        e_().beginTransaction();
    }

    public final void zzp(String str) {
        zzt();
        zzal();
        try {
            e_().execSQL("delete from default_event_params where app_id=?", new String[]{str});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error clearing default event params", e);
        }
    }

    private final void zzi(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting snapshot. appId", zzgo.zza(str2), e);
        }
    }

    public final void zzq(String str) {
        zzbb zzd2;
        zzi("events_snapshot", str);
        Cursor cursor = null;
        try {
            cursor = e_().query("events", (String[]) Collections.singletonList("name").toArray(new String[0]), "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(0);
                    if (!(string == null || (zzd2 = zzd(str, string)) == null)) {
                        zza("events_snapshot", zzd2);
                    }
                } while (cursor.moveToNext());
                if (cursor != null) {
                    cursor.close();
                }
            } else if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error creating snapshot. appId", zzgo.zza(str), e);
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void zzu() {
        zzal();
        e_().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final void zza(List<Long> list) {
        zzt();
        zzal();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzaa()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzb("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                e_().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(Long l) {
        zzt();
        zzal();
        Preconditions.checkNotNull(l);
        if ((!zzpu.zza() || zze().zza(zzbh.zzcb)) && zzaa()) {
            if (zzb("SELECT COUNT(1) FROM upload_queue WHERE rowid = " + l + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzj().zzu().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                e_().execSQL("UPDATE upload_queue SET retry_count = retry_count + 1 WHERE rowid = " + l + " AND retry_count < 2147483647");
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzv() {
        int delete;
        zzt();
        zzal();
        if (zzaa()) {
            long zza2 = zzn().zza.zza();
            long elapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza2) > zzag.zzn()) {
                zzn().zza.zza(elapsedRealtime);
                zzt();
                zzal();
                if (zzaa() && (delete = e_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzb().currentTimeMillis()), String.valueOf(zzag.zzm())})) > 0) {
                    zzj().zzp().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    public final void zzh(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzt();
        zzal();
        try {
            e_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error deleting user property. appId", zzgo.zza(str), zzi().zzc(str2), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x009a, code lost:
        if ("_v".equals(r0) != false) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0102 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzr(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.String r3 = "events_snapshot"
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r4 = "name"
            java.lang.String r5 = "lifetime_count"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5}
            java.util.List r4 = java.util.Arrays.asList(r4)
            r0.<init>(r4)
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzbb r5 = r1.zzd(r2, r4)
            java.lang.String r6 = "_v"
            com.google.android.gms.measurement.internal.zzbb r7 = r1.zzd(r2, r6)
            java.lang.String r8 = "events"
            r1.zzi(r8, r2)
            r9 = 0
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.e_()     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.String r12 = "events_snapshot"
            java.lang.String[] r13 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.Object[] r0 = r0.toArray(r13)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            r13 = r0
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            java.lang.String r14 = "app_id=?"
            java.lang.String[] r15 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            r16 = 0
            r17 = 0
            r18 = 0
            android.database.Cursor r10 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cc }
            if (r0 != 0) goto L_0x0063
            if (r10 == 0) goto L_0x0054
            r10.close()
        L_0x0054:
            if (r5 == 0) goto L_0x005a
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r5)
            goto L_0x005f
        L_0x005a:
            if (r7 == 0) goto L_0x005f
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r7)
        L_0x005f:
            r1.zzi(r3, r2)
            return
        L_0x0063:
            r11 = r9
            r12 = r11
        L_0x0065:
            java.lang.String r0 = r10.getString(r9)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            com.google.android.gms.measurement.internal.zzag r13 = r19.zze()     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbh.zzda     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            r14 = 1
            if (r13 == 0) goto L_0x008e
            long r15 = r10.getLong(r14)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            r17 = 1
            int r13 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r13 < 0) goto L_0x009d
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x0087
            goto L_0x0094
        L_0x0087:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x009d
            goto L_0x009c
        L_0x008e:
            boolean r13 = r4.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x0096
        L_0x0094:
            r11 = r14
            goto L_0x009d
        L_0x0096:
            boolean r13 = r6.equals(r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r13 == 0) goto L_0x009d
        L_0x009c:
            r12 = r14
        L_0x009d:
            if (r0 == 0) goto L_0x00a8
            com.google.android.gms.measurement.internal.zzbb r0 = r1.zzc(r3, r2, r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r0 == 0) goto L_0x00a8
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r0)     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
        L_0x00a8:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x00c9, all -> 0x00c6 }
            if (r0 != 0) goto L_0x0065
            if (r10 == 0) goto L_0x00b3
            r10.close()
        L_0x00b3:
            if (r11 != 0) goto L_0x00bb
            if (r5 == 0) goto L_0x00bb
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r5)
            goto L_0x00c2
        L_0x00bb:
            if (r12 != 0) goto L_0x00c2
            if (r7 == 0) goto L_0x00c2
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r7)
        L_0x00c2:
            r1.zzi(r3, r2)
            return
        L_0x00c6:
            r0 = move-exception
            r9 = r11
            goto L_0x00fb
        L_0x00c9:
            r0 = move-exception
            r9 = r11
            goto L_0x00d1
        L_0x00cc:
            r0 = move-exception
            r12 = r9
            goto L_0x00fb
        L_0x00cf:
            r0 = move-exception
            r12 = r9
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzgo r4 = r19.zzj()     // Catch:{ all -> 0x00fa }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x00fa }
            java.lang.String r6 = "Error querying snapshot. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r20)     // Catch:{ all -> 0x00fa }
            r4.zza(r6, r11, r0)     // Catch:{ all -> 0x00fa }
            if (r10 == 0) goto L_0x00e7
            r10.close()
        L_0x00e7:
            if (r9 != 0) goto L_0x00ef
            if (r5 == 0) goto L_0x00ef
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r5)
            goto L_0x00f6
        L_0x00ef:
            if (r12 != 0) goto L_0x00f6
            if (r7 == 0) goto L_0x00f6
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r7)
        L_0x00f6:
            r1.zzi(r3, r2)
            return
        L_0x00fa:
            r0 = move-exception
        L_0x00fb:
            if (r10 == 0) goto L_0x0100
            r10.close()
        L_0x0100:
            if (r9 != 0) goto L_0x0109
            if (r5 != 0) goto L_0x0105
            goto L_0x0109
        L_0x0105:
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r5)
            goto L_0x0110
        L_0x0109:
            if (r12 != 0) goto L_0x0110
            if (r7 == 0) goto L_0x0110
            r1.zza((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzbb) r7)
        L_0x0110:
            r1.zzi(r3, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzr(java.lang.String):void");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, List<zzfo.zza> list) {
        boolean z;
        boolean z2;
        String str2 = str;
        List<zzfo.zza> list2 = list;
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            zzjt.zzb zzcd = list2.get(i).zzcd();
            zzjt.zzb zzb2 = zzcd;
            zzfo.zza.C0001zza zza2 = (zzfo.zza.C0001zza) zzcd;
            if (zza2.zza() != 0) {
                for (int i2 = 0; i2 < zza2.zza(); i2++) {
                    zzjt.zzb zzcd2 = zza2.zza(i2).zzcd();
                    zzjt.zzb zzb3 = zzcd2;
                    zzfo.zzb.zza zza3 = (zzfo.zzb.zza) zzcd2;
                    zzfo.zzb.zza zza4 = (zzfo.zzb.zza) ((zzjt.zzb) zza3.clone());
                    String zzb4 = zzji.zzb(zza3.zzb());
                    if (zzb4 != null) {
                        zza4.zza(zzb4);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    for (int i3 = 0; i3 < zza3.zza(); i3++) {
                        zzfo.zzc zza5 = zza3.zza(i3);
                        String zza6 = zzjk.zza(zza5.zze());
                        if (zza6 != null) {
                            zzjt.zzb zzcd3 = zza5.zzcd();
                            zzjt.zzb zzb5 = zzcd3;
                            zza4.zza(i3, (zzfo.zzc) ((zzjt) ((zzfo.zzc.zza) zzcd3).zza(zza6).zzai()));
                            z2 = true;
                        }
                    }
                    if (z2) {
                        zza2 = zza2.zza(i2, zza4);
                        list2.set(i, (zzfo.zza) ((zzjt) zza2.zzai()));
                    }
                }
            }
            if (zza2.zzb() != 0) {
                for (int i4 = 0; i4 < zza2.zzb(); i4++) {
                    zzfo.zze zzb6 = zza2.zzb(i4);
                    String zza7 = zzjj.zza(zzb6.zze());
                    if (zza7 != null) {
                        zzjt.zzb zzcd4 = zzb6.zzcd();
                        zzjt.zzb zzb7 = zzcd4;
                        zza2 = zza2.zza(i4, ((zzfo.zze.zza) zzcd4).zza(zza7));
                        list2.set(i, (zzfo.zza) ((zzjt) zza2.zzai()));
                    }
                }
            }
        }
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase e_ = e_();
        e_.beginTransaction();
        try {
            zzal();
            zzt();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase e_2 = e_();
            e_2.delete("property_filters", "app_id=?", new String[]{str});
            e_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzfo.zza next : list) {
                zzal();
                zzt();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(next);
                if (!next.zzg()) {
                    zzj().zzu().zza("Audience with no ID. appId", zzgo.zza(str));
                } else {
                    int zza8 = next.zza();
                    Iterator<zzfo.zzb> it = next.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zzl()) {
                                zzj().zzu().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzgo.zza(str), Integer.valueOf(zza8));
                                break;
                            }
                        } else {
                            Iterator<zzfo.zze> it2 = next.zzf().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zzi()) {
                                        zzj().zzu().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzgo.zza(str), Integer.valueOf(zza8));
                                        break;
                                    }
                                } else {
                                    Iterator<zzfo.zzb> it3 = next.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str2, zza8, it3.next())) {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<zzfo.zze> it4 = next.zzf().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str2, zza8, it4.next())) {
                                                    z = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzal();
                                        zzt();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase e_3 = e_();
                                        e_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza8)});
                                        e_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str2, String.valueOf(zza8)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzfo.zza next2 : list) {
                arrayList.add(next2.zzg() ? Integer.valueOf(next2.zza()) : null);
            }
            zzb(str2, (List<Integer>) arrayList);
            e_.setTransactionSuccessful();
        } finally {
            e_.endTransaction();
        }
    }

    public final void zzw() {
        zzal();
        e_().setTransactionSuccessful();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0043, code lost:
        if (r7.zzg.zzb(r0).zza(com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE) != false) goto L_0x0045;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0242  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0326 A[Catch:{ SQLiteException -> 0x0338 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzg r8, boolean r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r10 = "apps"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            r7.zzt()
            r7.zzal()
            java.lang.String r0 = r8.zzac()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "app_id"
            r1.put(r2, r0)
            boolean r2 = com.google.android.gms.internal.measurement.zznm.zza()
            java.lang.String r3 = "app_instance_id"
            r4 = 0
            if (r2 == 0) goto L_0x0045
            com.google.android.gms.measurement.internal.zzag r2 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r2 == 0) goto L_0x0045
            if (r9 == 0) goto L_0x0037
            r1.put(r3, r4)
            goto L_0x004c
        L_0x0037:
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzje r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzje$zza r2 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzje.zza) r2)
            if (r9 == 0) goto L_0x004c
        L_0x0045:
            java.lang.String r9 = r8.zzad()
            r1.put(r3, r9)
        L_0x004c:
            java.lang.String r9 = "gmp_app_id"
            java.lang.String r2 = r8.zzah()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x0075
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzje r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzje$zza r2 = com.google.android.gms.measurement.internal.zzje.zza.AD_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzje.zza) r2)
            if (r9 == 0) goto L_0x007e
        L_0x0075:
            java.lang.String r9 = "resettable_device_id_hash"
            java.lang.String r2 = r8.zzaj()
            r1.put(r9, r2)
        L_0x007e:
            long r2 = r8.zzt()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_index"
            r1.put(r2, r9)
            long r2 = r8.zzu()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_start_timestamp"
            r1.put(r2, r9)
            long r2 = r8.zzs()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "last_bundle_end_timestamp"
            r1.put(r2, r9)
            java.lang.String r9 = "app_version"
            java.lang.String r2 = r8.zzaf()
            r1.put(r9, r2)
            java.lang.String r9 = "app_store"
            java.lang.String r2 = r8.zzae()
            r1.put(r9, r2)
            long r2 = r8.zzq()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "gmp_version"
            r1.put(r2, r9)
            long r2 = r8.zzn()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dev_cert_hash"
            r1.put(r2, r9)
            boolean r9 = r8.zzar()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "measurement_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzm()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "day"
            r1.put(r2, r9)
            long r2 = r8.zzk()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_public_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzj()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzh()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_conversions_count"
            r1.put(r2, r9)
            long r2 = r8.zzg()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "config_fetched_time"
            r1.put(r2, r9)
            long r2 = r8.zzp()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "failed_config_fetch_time"
            r1.put(r2, r9)
            long r2 = r8.zze()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "app_version_int"
            r1.put(r2, r9)
            java.lang.String r9 = "firebase_instance_id"
            java.lang.String r2 = r8.zzag()
            r1.put(r9, r2)
            long r2 = r8.zzi()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_error_events_count"
            r1.put(r2, r9)
            long r2 = r8.zzl()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "daily_realtime_events_count"
            r1.put(r2, r9)
            java.lang.String r9 = "health_monitor_sample"
            java.lang.String r2 = r8.zzai()
            r1.put(r9, r2)
            long r2 = r8.zzd()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "android_id"
            r1.put(r2, r9)
            boolean r9 = r8.zzaq()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "adid_reporting_enabled"
            r1.put(r2, r9)
            java.lang.String r9 = "admob_app_id"
            java.lang.String r2 = r8.zzaa()
            r1.put(r9, r2)
            long r2 = r8.zzo()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "dynamite_version"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x01b5
            com.google.android.gms.measurement.internal.zznv r9 = r7.zzg
            com.google.android.gms.measurement.internal.zzje r9 = r9.zzb((java.lang.String) r0)
            com.google.android.gms.measurement.internal.zzje$zza r2 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzje.zza) r2)
            if (r9 == 0) goto L_0x01be
        L_0x01b5:
            java.lang.String r9 = "session_stitching_token"
            java.lang.String r2 = r8.zzal()
            r1.put(r9, r2)
        L_0x01be:
            boolean r9 = r8.zzat()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "sgtm_upload_enabled"
            r1.put(r2, r9)
            long r2 = r8.zzw()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "target_os_version"
            r1.put(r2, r9)
            long r2 = r8.zzv()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "session_stitching_token_hash"
            r1.put(r2, r9)
            boolean r9 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r9 == 0) goto L_0x0211
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzch
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0211
            int r9 = r8.zza()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "ad_services_version"
            r1.put(r2, r9)
            long r2 = r8.zzf()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "attribution_eligibility_status"
            r1.put(r2, r9)
        L_0x0211:
            boolean r9 = r8.zzau()
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.String r2 = "unmatched_first_open_without_ad_id"
            r1.put(r2, r9)
            java.lang.String r9 = "npa_metadata_value"
            java.lang.Boolean r2 = r8.zzx()
            r1.put(r9, r2)
            boolean r9 = com.google.android.gms.internal.measurement.zzpu.zza()
            if (r9 == 0) goto L_0x024f
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzbx
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x024f
            r7.zzq()
            boolean r9 = com.google.android.gms.measurement.internal.zzos.zzf(r0)
            if (r9 == 0) goto L_0x024f
            long r2 = r8.zzr()
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            java.lang.String r2 = "bundle_delivery_index"
            r1.put(r2, r9)
        L_0x024f:
            boolean r9 = com.google.android.gms.internal.measurement.zzpu.zza()
            if (r9 == 0) goto L_0x026a
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzby
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x026a
            java.lang.String r9 = "sgtm_preview_key"
            java.lang.String r2 = r8.zzam()
            r1.put(r9, r2)
        L_0x026a:
            int r9 = r8.zzc()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "dma_consent_state"
            r1.put(r2, r9)
            int r9 = r8.zzb()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = "daily_realtime_dcu_count"
            r1.put(r2, r9)
            java.lang.String r9 = "serialized_npa_metadata"
            java.lang.String r2 = r8.zzak()
            r1.put(r9, r2)
            java.util.List r9 = r8.zzan()
            java.lang.String r2 = "safelisted_events"
            if (r9 == 0) goto L_0x02b2
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto L_0x02a9
            com.google.android.gms.measurement.internal.zzgo r9 = r7.zzj()
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzu()
            java.lang.String r3 = "Safelisted events should not be an empty list. appId"
            r9.zza(r3, r0)
            goto L_0x02b2
        L_0x02a9:
            java.lang.String r3 = ","
            java.lang.String r9 = android.text.TextUtils.join(r3, r9)
            r1.put(r2, r9)
        L_0x02b2:
            boolean r9 = com.google.android.gms.internal.measurement.zzny.zza()
            if (r9 == 0) goto L_0x02cd
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzbu
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r9 == 0) goto L_0x02cd
            boolean r9 = r1.containsKey(r2)
            if (r9 != 0) goto L_0x02cd
            r1.put(r2, r4)
        L_0x02cd:
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcw
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)
            if (r9 == 0) goto L_0x02eb
            java.lang.String r9 = "unmatched_pfo"
            java.lang.Long r2 = r8.zzy()
            r1.put(r9, r2)
            java.lang.String r9 = "unmatched_uwa"
            java.lang.Long r2 = r8.zzz()
            r1.put(r9, r2)
        L_0x02eb:
            boolean r9 = com.google.android.gms.internal.measurement.zzov.zza()
            if (r9 == 0) goto L_0x0306
            com.google.android.gms.measurement.internal.zzag r9 = r7.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbh.zzcu
            boolean r9 = r9.zze(r0, r2)
            if (r9 == 0) goto L_0x0306
            java.lang.String r9 = "ad_campaign_info"
            byte[] r8 = r8.zzav()
            r1.put(r9, r8)
        L_0x0306:
            android.database.sqlite.SQLiteDatabase r8 = r7.e_()     // Catch:{ SQLiteException -> 0x0338 }
            java.lang.String r9 = "app_id = ?"
            java.lang.String[] r2 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x0338 }
            int r9 = r8.update(r10, r1, r9, r2)     // Catch:{ SQLiteException -> 0x0338 }
            long r2 = (long) r9     // Catch:{ SQLiteException -> 0x0338 }
            r5 = 0
            int r9 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x0337
            r9 = 5
            long r8 = r8.insertWithOnConflict(r10, r4, r1, r9)     // Catch:{ SQLiteException -> 0x0338 }
            r1 = -1
            int r8 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r8 != 0) goto L_0x0337
            com.google.android.gms.measurement.internal.zzgo r8 = r7.zzj()     // Catch:{ SQLiteException -> 0x0338 }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x0338 }
            java.lang.String r9 = "Failed to insert/update app (got -1). appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)     // Catch:{ SQLiteException -> 0x0338 }
            r8.zza(r9, r10)     // Catch:{ SQLiteException -> 0x0338 }
        L_0x0337:
            return
        L_0x0338:
            r8 = move-exception
            com.google.android.gms.measurement.internal.zzgo r9 = r7.zzj()
            com.google.android.gms.measurement.internal.zzgq r9 = r9.zzg()
            java.lang.String r10 = "Error storing app. appId"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)
            r9.zza(r10, r0, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zza(com.google.android.gms.measurement.internal.zzg, boolean, boolean):void");
    }

    public final void zza(String str, zzax zzax) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzax);
        zzt();
        zzal();
        if (zze().zza(zzbh.zzcr) && zzi(str) == zzje.zza) {
            zzb(str, zzje.zza);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzax.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zza(zzbb zzbb) {
        zza("events", zzbb);
    }

    private final void zza(String str, zzbb zzbb) {
        Preconditions.checkNotNull(zzbb);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbb.zza);
        contentValues.put("name", zzbb.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzbb.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzbb.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzbb.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzbb.zzg));
        contentValues.put("last_bundled_day", zzbb.zzh);
        contentValues.put("last_sampled_complex_event_id", zzbb.zzi);
        contentValues.put("last_sampling_rate", zzbb.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzbb.zze));
        contentValues.put("last_exempt_from_sampling", (zzbb.zzk == null || !zzbb.zzk.booleanValue()) ? null : 1L);
        try {
            if (e_().insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update event aggregates (got -1). appId", zzgo.zza(zzbb.zza));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event aggregates. appId", zzgo.zza(zzbb.zza), e);
        }
    }

    private final void zza(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase e_ = e_();
            String asString = contentValues.getAsString(str2);
            if (asString == null) {
                zzj().zzm().zza("Value of the primary key is not set.", zzgo.zza(str2));
            } else if (((long) e_.update(str, contentValues, str2 + " = ?", new String[]{asString})) == 0 && e_.insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                zzj().zzg().zza("Failed to insert/update table (got -1). key", zzgo.zza(str), zzgo.zza(str2));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing into table. key", zzgo.zza(str), zzgo.zza(str2), e);
        }
    }

    public final void zza(String str, zzje zzje) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzje);
        zzt();
        zzal();
        zzb(str, zzi(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzje.zzf());
        zza("consent_settings", "app_id", contentValues);
    }

    public final void zzb(String str, zzje zzje) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzje);
        zzt();
        zzal();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzje.zzf());
        contentValues.put("consent_source", Integer.valueOf(zzje.zza()));
        zza("consent_settings", "app_id", contentValues);
    }

    private final boolean zzb(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzal();
        zzt();
        SQLiteDatabase e_ = e_();
        try {
            long zzb2 = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zze().zzb(str, zzbh.zzah)));
            if (zzb2 <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            if (e_.delete("audience_filter_values", "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in " + ("(" + TextUtils.join(",", arrayList) + ")") + " order by rowid desc limit -1 offset ?)", new String[]{str, Integer.toString(max)}) > 0) {
                return true;
            }
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Database error querying filters. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zzx() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzs(String str) {
        if (zzpu.zza() && !zze().zza(zzbh.zzcb)) {
            return false;
        }
        if (zzb("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=? AND NOT " + zzao(), new String[]{str}) != 0) {
            return true;
        }
        return false;
    }

    public final boolean zza(zzfy.zzk zzk2, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzk2);
        Preconditions.checkNotEmpty(zzk2.zzz());
        Preconditions.checkState(zzk2.zzbj());
        zzv();
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zzk2.zzm() < currentTimeMillis - zzag.zzm() || zzk2.zzm() > zzag.zzm() + currentTimeMillis) {
            zzj().zzu().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzgo.zza(zzk2.zzz()), Long.valueOf(currentTimeMillis), Long.valueOf(zzk2.zzm()));
        }
        try {
            byte[] zzb2 = g_().zzb(zzk2.zzca());
            zzj().zzp().zza("Saving bundle, size", Integer.valueOf(zzb2.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzk2.zzz());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzk2.zzm()));
            contentValues.put("data", zzb2);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzk2.zzbq()) {
                contentValues.put("retry_count", Integer.valueOf(zzk2.zzg()));
            }
            try {
                if (e_().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzj().zzg().zza("Failed to insert bundle (got -1). appId", zzgo.zza(zzk2.zzz()));
                return false;
            } catch (SQLiteException e) {
                zzj().zzg().zza("Error storing bundle. appId", zzgo.zza(zzk2.zzz()), e);
                return false;
            }
        } catch (IOException e2) {
            zzj().zzg().zza("Data loss. Failed to serialize bundle. appId", zzgo.zza(zzk2.zzz()), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzfo.zzb zzb2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzb2);
        Integer num = null;
        if (zzb2.zzf().isEmpty()) {
            zzgq zzu = zzj().zzu();
            Object zza2 = zzgo.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zzb2.zzl()) {
                num = Integer.valueOf(zzb2.zzb());
            }
            zzu.zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzca = zzb2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzb2.zzl() ? Integer.valueOf(zzb2.zzb()) : null);
        contentValues.put("event_name", zzb2.zzf());
        contentValues.put("session_scoped", zzb2.zzm() ? Boolean.valueOf(zzb2.zzj()) : null);
        contentValues.put("data", zzca);
        try {
            if (e_().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert event filter (got -1). appId", zzgo.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing event filter. appId", zzgo.zza(str), e);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzfo.zze zze2) {
        zzal();
        zzt();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zze2);
        Integer num = null;
        if (zze2.zze().isEmpty()) {
            zzgq zzu = zzj().zzu();
            Object zza2 = zzgo.zza(str);
            Integer valueOf = Integer.valueOf(i);
            if (zze2.zzi()) {
                num = Integer.valueOf(zze2.zza());
            }
            zzu.zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zza2, valueOf, String.valueOf(num));
            return false;
        }
        byte[] zzca = zze2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zze2.zzi() ? Integer.valueOf(zze2.zza()) : null);
        contentValues.put("property_name", zze2.zze());
        contentValues.put("session_scoped", zze2.zzj() ? Boolean.valueOf(zze2.zzh()) : null);
        contentValues.put("data", zzca);
        try {
            if (e_().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert property filter (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing property filter. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zza(zzbc zzbc, long j, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzbc);
        Preconditions.checkNotEmpty(zzbc.zza);
        byte[] zzca = g_().zza(zzbc).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbc.zza);
        contentValues.put("name", zzbc.zzb);
        contentValues.put("timestamp", Long.valueOf(zzbc.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put("data", zzca);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (e_().insert("raw_events", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert raw event (got -1). appId", zzgo.zza(zzbc.zza));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing raw event. appId", zzgo.zza(zzbc.zza), e);
            return false;
        }
    }

    public final boolean zza(String str, zzno zzno) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzno);
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzb().currentTimeMillis();
        if (zzno.zzb < currentTimeMillis - zzbh.zzbh.zza(null).longValue() || zzno.zzb > zzbh.zzbh.zza(null).longValue() + currentTimeMillis) {
            zzj().zzu().zza("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzgo.zza(str), Long.valueOf(currentTimeMillis), Long.valueOf(zzno.zzb));
        }
        zzj().zzp().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zzno.zza);
        contentValues.put("source", Integer.valueOf(zzno.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(zzno.zzb));
        try {
            if (e_().insert("trigger_uris", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert trigger URI (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing trigger URI. appId", zzgo.zza(str), e);
            return false;
        }
    }

    public final boolean zza(String str, zzfy.zzj zzj2, String str2, Map<String, String> map, zznt zznt) {
        int delete;
        zzt();
        zzal();
        Preconditions.checkNotNull(zzj2);
        Preconditions.checkNotEmpty(str);
        if (zzpu.zza() && !zze().zza(zzbh.zzcb)) {
            return false;
        }
        zzt();
        zzal();
        if (zzaa()) {
            long zza2 = zzn().zzb.zza();
            long elapsedRealtime = zzb().elapsedRealtime();
            if (Math.abs(elapsedRealtime - zza2) > zzag.zzn()) {
                zzn().zzb.zza(elapsedRealtime);
                zzt();
                zzal();
                if (zzaa() && (delete = e_().delete("upload_queue", zzao(), new String[0])) > 0) {
                    zzj().zzp().zza("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(((String) next.getKey()) + "=" + ((String) next.getValue()));
        }
        byte[] zzca = zzj2.zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("measurement_batch", zzca);
        contentValues.put("upload_uri", str2);
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList.size();
        if (size > 0) {
            sb.append((CharSequence) arrayList.get(0));
            int i = 1;
            while (i < size) {
                sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                Object obj = arrayList.get(i);
                i++;
                sb.append((CharSequence) obj);
            }
        }
        contentValues.put("upload_headers", sb.toString());
        contentValues.put("upload_type", Integer.valueOf(zznt.zza()));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzb().currentTimeMillis()));
        contentValues.put("retry_count", 0);
        try {
            if (e_().insert("upload_queue", (String) null, contentValues) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", str);
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing MeasurementBatch to upload_queue. appId", str, e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzaa() {
        return zza().getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zza(String str, Long l, long j, zzfy.zzf zzf2) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzf2);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] zzca = zzf2.zzca();
        zzj().zzp().zza("Saving complex main event, appId, data size", zzi().zza(str), Integer.valueOf(zzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzca);
        try {
            if (e_().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert complex main event (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing complex main event. appId", zzgo.zza(str), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0067 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzt(java.lang.String r10) {
        /*
            r9 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r9.e_()     // Catch:{ SQLiteException -> 0x0050 }
            java.lang.String r4 = "select timestamp from raw_events where app_id=? and name = '_f' limit 1;"
            java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x0050 }
            android.database.Cursor r2 = r3.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x0050 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0050 }
            if (r3 != 0) goto L_0x001d
            if (r2 == 0) goto L_0x001c
            r2.close()
        L_0x001c:
            return r1
        L_0x001d:
            long r3 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x0050 }
            com.google.android.gms.common.util.Clock r5 = r9.zzb()     // Catch:{ SQLiteException -> 0x0050 }
            long r5 = r5.currentTimeMillis()     // Catch:{ SQLiteException -> 0x0050 }
            r7 = 15000(0x3a98, double:7.411E-320)
            long r3 = r3 + r7
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0032
            r3 = r0
            goto L_0x0033
        L_0x0032:
            r3 = r1
        L_0x0033:
            java.lang.String r4 = "select count(*) from raw_events where app_id=? and name not like '!_%' escape '!' limit 1;"
            java.lang.String[] r10 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x004c }
            r5 = 0
            long r7 = r9.zza((java.lang.String) r4, (java.lang.String[]) r10, (long) r5)     // Catch:{ SQLiteException -> 0x004c }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 <= 0) goto L_0x0045
            r10 = r0
            goto L_0x0046
        L_0x0045:
            r10 = r1
        L_0x0046:
            if (r2 == 0) goto L_0x0065
            r2.close()
            goto L_0x0065
        L_0x004c:
            r10 = move-exception
            goto L_0x0052
        L_0x004e:
            r10 = move-exception
            goto L_0x006b
        L_0x0050:
            r10 = move-exception
            r3 = r1
        L_0x0052:
            com.google.android.gms.measurement.internal.zzgo r4 = r9.zzj()     // Catch:{ all -> 0x004e }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ all -> 0x004e }
            java.lang.String r5 = "Error checking backfill conditions"
            r4.zza(r5, r10)     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x0064
            r2.close()
        L_0x0064:
            r10 = r1
        L_0x0065:
            if (r3 == 0) goto L_0x006a
            if (r10 != 0) goto L_0x006a
            return r0
        L_0x006a:
            return r1
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()
        L_0x0070:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzal.zzt(java.lang.String):boolean");
    }

    public final boolean zza(zzae zzae) {
        Preconditions.checkNotNull(zzae);
        zzt();
        zzal();
        String str = zzae.zza;
        Preconditions.checkNotNull(str);
        if (zze(str, zzae.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzae.zzb);
        contentValues.put("name", zzae.zzc.zza);
        zza(contentValues, "value", Preconditions.checkNotNull(zzae.zzc.zza()));
        contentValues.put("active", Boolean.valueOf(zzae.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzae.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzae.zzh));
        zzq();
        contentValues.put("timed_out_event", zzos.zza((Parcelable) zzae.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzae.zzd));
        zzq();
        contentValues.put("triggered_event", zzos.zza((Parcelable) zzae.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzae.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzae.zzj));
        zzq();
        contentValues.put("expired_event", zzos.zza((Parcelable) zzae.zzk));
        try {
            if (e_().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update conditional user property (got -1)", zzgo.zza(str));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing conditional user property", zzgo.zza(str), e);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(String str, Bundle bundle) {
        zzt();
        zzal();
        byte[] zzca = g_().zza(new zzbc(this.zzu, "", str, "dep", 0, 0, bundle)).zzca();
        zzj().zzp().zza("Saving default event parameters, appId, data size", zzi().zza(str), Integer.valueOf(zzca.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put(DynamicLink.Builder.KEY_DYNAMIC_LINK_PARAMETERS, zzca);
        try {
            if (e_().insertWithOnConflict("default_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert default event parameters (got -1). appId", zzgo.zza(str));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing default event parameters. appId", zzgo.zza(str), e);
            return false;
        }
    }

    private final boolean zza(long j, zzbc zzbc, long j2, boolean z) {
        zzt();
        zzal();
        Preconditions.checkNotNull(zzbc);
        Preconditions.checkNotEmpty(zzbc.zza);
        byte[] zzca = g_().zza(zzbc).zzca();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzbc.zza);
        contentValues.put("name", zzbc.zzb);
        contentValues.put("timestamp", Long.valueOf(zzbc.zzd));
        contentValues.put("metadata_fingerprint", Long.valueOf(j2));
        contentValues.put("data", zzca);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            long update = (long) e_().update("raw_events", contentValues, "rowid = ?", new String[]{String.valueOf(j)});
            if (update == 1) {
                return true;
            }
            zzj().zzg().zza("Failed to update raw event. appId, updatedRows", zzgo.zza(zzbc.zza), Long.valueOf(update));
            return false;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error updating raw event. appId", zzgo.zza(zzbc.zza), e);
            return false;
        }
    }

    public final boolean zza(zzop zzop) {
        Preconditions.checkNotNull(zzop);
        zzt();
        zzal();
        if (zze(zzop.zza, zzop.zzc) == null) {
            if (zzos.zzh(zzop.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzop.zza}) >= ((long) zze().zza(zzop.zza, zzbh.zzai, 25, 100))) {
                    return false;
                }
            } else if (!"_npa".equals(zzop.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzop.zza, zzop.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzop.zza);
        contentValues.put("origin", zzop.zzb);
        contentValues.put("name", zzop.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzop.zzd));
        zza(contentValues, "value", zzop.zze);
        try {
            if (e_().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzj().zzg().zza("Failed to insert/update user property (got -1). appId", zzgo.zza(zzop.zza));
            return true;
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error storing user property. appId", zzgo.zza(zzop.zza), e);
            return true;
        }
    }
}
