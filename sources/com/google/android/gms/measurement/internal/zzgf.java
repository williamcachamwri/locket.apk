package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgf extends zzh {
    private final zzgi zza = new zzgi(this, zza(), "google_app_measurement_local.db");
    private boolean zzb;

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, (String) null, (String) null, "rowid desc", "1");
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    private final SQLiteDatabase zzad() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v17, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:63|64|65|66) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:78|79|80|81) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:50|51|52|53|179) */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x019f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01a0, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01a4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01a5, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01a8, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01ac, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x01ad, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x01bd, code lost:
        if (r15.inTransaction() != false) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01bf, code lost:
        r15.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01d1, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x01d6, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01e4, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x01e9, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x01ff, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0204, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x020d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0211, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0216, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        zzj().zzg().zza("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r11.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        zzj().zzg().zza("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r11.recycle();
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        zzj().zzg().zza("Failed to load conditional user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r11.recycle();
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x00b3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x00e3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:78:0x0119 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:119:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01b9 A[SYNTHETIC, Splitter:B:130:0x01b9] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0211  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0207 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0207 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0207 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r23) {
        /*
            r22 = this;
            r1 = r22
            java.lang.String r2 = "Error reading entries from local database"
            r22.zzt()
            boolean r0 = r1.zzb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r0 = r22.zzae()
            if (r0 != 0) goto L_0x0019
            return r4
        L_0x0019:
            r5 = 5
            r6 = 0
            r8 = r5
            r7 = r6
        L_0x001d:
            if (r7 >= r5) goto L_0x021a
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r22.zzad()     // Catch:{ SQLiteFullException -> 0x01ed, SQLiteDatabaseLockedException -> 0x01da, SQLiteException -> 0x01b4, all -> 0x01b0 }
            if (r15 != 0) goto L_0x0039
            r1.zzb = r9     // Catch:{ SQLiteFullException -> 0x0035, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x0031, all -> 0x002e }
            if (r15 == 0) goto L_0x002d
            r15.close()
        L_0x002d:
            return r3
        L_0x002e:
            r0 = move-exception
            goto L_0x020f
        L_0x0031:
            r0 = move-exception
            r10 = r3
            goto L_0x01b7
        L_0x0035:
            r0 = move-exception
            r10 = r3
            goto L_0x01f0
        L_0x0039:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            long r10 = zza((android.database.sqlite.SQLiteDatabase) r15)     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            r19 = -1
            int r0 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x0053
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0035, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x0031, all -> 0x002e }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0035, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x0031, all -> 0x002e }
            r12[r6] = r10     // Catch:{ SQLiteFullException -> 0x0035, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x0031, all -> 0x002e }
            r13 = r0
            r14 = r12
            goto L_0x0055
        L_0x0053:
            r13 = r3
            r14 = r13
        L_0x0055:
            java.lang.String r11 = "messages"
            r0 = 3
            java.lang.String[] r12 = new java.lang.String[r0]     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            java.lang.String r10 = "rowid"
            r12[r6] = r10     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            java.lang.String r10 = "type"
            r12[r9] = r10     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            java.lang.String r10 = "entry"
            r5 = 2
            r12[r5] = r10     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            r16 = 0
            r17 = 0
            java.lang.String r18 = "rowid asc"
            r10 = 100
            java.lang.String r21 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x01ac, SQLiteDatabaseLockedException -> 0x01a8, SQLiteException -> 0x01a4, all -> 0x019f }
            r10 = r15
            r3 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r18 = r21
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x019c, SQLiteDatabaseLockedException -> 0x01a9, SQLiteException -> 0x0199, all -> 0x0196 }
        L_0x0081:
            boolean r11 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            if (r11 == 0) goto L_0x0155
            long r19 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r11 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            byte[] r12 = r10.getBlob(r5)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            if (r11 != 0) goto L_0x00c8
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r13 = r12.length     // Catch:{ ParseException -> 0x00b3 }
            r11.unmarshall(r12, r6, r13)     // Catch:{ ParseException -> 0x00b3 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00b3 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r12 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ ParseException -> 0x00b3 }
            java.lang.Object r12 = r12.createFromParcel(r11)     // Catch:{ ParseException -> 0x00b3 }
            com.google.android.gms.measurement.internal.zzbf r12 = (com.google.android.gms.measurement.internal.zzbf) r12     // Catch:{ ParseException -> 0x00b3 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            if (r12 == 0) goto L_0x0081
            r4.add(r12)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x00b1:
            r0 = move-exception
            goto L_0x00c4
        L_0x00b3:
            com.google.android.gms.measurement.internal.zzgo r12 = r22.zzj()     // Catch:{ all -> 0x00b1 }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()     // Catch:{ all -> 0x00b1 }
            java.lang.String r13 = "Failed to load event from local database"
            r12.zza(r13)     // Catch:{ all -> 0x00b1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x00c4:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
        L_0x00c8:
            if (r11 != r9) goto L_0x00fe
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r13 = r12.length     // Catch:{ ParseException -> 0x00e3 }
            r11.unmarshall(r12, r6, r13)     // Catch:{ ParseException -> 0x00e3 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00e3 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzon> r12 = com.google.android.gms.measurement.internal.zzon.CREATOR     // Catch:{ ParseException -> 0x00e3 }
            java.lang.Object r12 = r12.createFromParcel(r11)     // Catch:{ ParseException -> 0x00e3 }
            com.google.android.gms.measurement.internal.zzon r12 = (com.google.android.gms.measurement.internal.zzon) r12     // Catch:{ ParseException -> 0x00e3 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x00f4
        L_0x00e1:
            r0 = move-exception
            goto L_0x00fa
        L_0x00e3:
            com.google.android.gms.measurement.internal.zzgo r12 = r22.zzj()     // Catch:{ all -> 0x00e1 }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()     // Catch:{ all -> 0x00e1 }
            java.lang.String r13 = "Failed to load user property from local database"
            r12.zza(r13)     // Catch:{ all -> 0x00e1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            r12 = 0
        L_0x00f4:
            if (r12 == 0) goto L_0x0081
            r4.add(r12)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x00fa:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
        L_0x00fe:
            if (r11 != r5) goto L_0x0135
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r13 = r12.length     // Catch:{ ParseException -> 0x0119 }
            r11.unmarshall(r12, r6, r13)     // Catch:{ ParseException -> 0x0119 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x0119 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzae> r12 = com.google.android.gms.measurement.internal.zzae.CREATOR     // Catch:{ ParseException -> 0x0119 }
            java.lang.Object r12 = r12.createFromParcel(r11)     // Catch:{ ParseException -> 0x0119 }
            com.google.android.gms.measurement.internal.zzae r12 = (com.google.android.gms.measurement.internal.zzae) r12     // Catch:{ ParseException -> 0x0119 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x012a
        L_0x0117:
            r0 = move-exception
            goto L_0x0131
        L_0x0119:
            com.google.android.gms.measurement.internal.zzgo r12 = r22.zzj()     // Catch:{ all -> 0x0117 }
            com.google.android.gms.measurement.internal.zzgq r12 = r12.zzg()     // Catch:{ all -> 0x0117 }
            java.lang.String r13 = "Failed to load conditional user property from local database"
            r12.zza(r13)     // Catch:{ all -> 0x0117 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            r12 = 0
        L_0x012a:
            if (r12 == 0) goto L_0x0081
            r4.add(r12)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x0131:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
        L_0x0135:
            if (r11 != r0) goto L_0x0146
            com.google.android.gms.measurement.internal.zzgo r11 = r22.zzj()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            com.google.android.gms.measurement.internal.zzgq r11 = r11.zzu()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r12 = "Skipping app launch break"
            r11.zza(r12)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x0146:
            com.google.android.gms.measurement.internal.zzgo r11 = r22.zzj()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            com.google.android.gms.measurement.internal.zzgq r11 = r11.zzg()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r12 = "Unknown record type in local database"
            r11.zza(r12)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            goto L_0x0081
        L_0x0155:
            java.lang.String r0 = "messages"
            java.lang.String r5 = "rowid <= ?"
            java.lang.String[] r11 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r12 = java.lang.Long.toString(r19)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            r11[r6] = r12     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r0 = r3.delete(r0, r5, r11)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            int r5 = r4.size()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            if (r0 >= r5) goto L_0x0178
            com.google.android.gms.measurement.internal.zzgo r0 = r22.zzj()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            java.lang.String r5 = "Fewer entries removed from local database than expected"
            r0.zza(r5)     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
        L_0x0178:
            r3.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            r3.endTransaction()     // Catch:{ SQLiteFullException -> 0x0192, SQLiteDatabaseLockedException -> 0x0190, SQLiteException -> 0x018d, all -> 0x0189 }
            if (r10 == 0) goto L_0x0183
            r10.close()
        L_0x0183:
            if (r3 == 0) goto L_0x0188
            r3.close()
        L_0x0188:
            return r4
        L_0x0189:
            r0 = move-exception
            r15 = r3
            goto L_0x020e
        L_0x018d:
            r0 = move-exception
            r15 = r3
            goto L_0x01b7
        L_0x0190:
            r15 = r3
            goto L_0x01dc
        L_0x0192:
            r0 = move-exception
            r15 = r3
            goto L_0x01f0
        L_0x0196:
            r0 = move-exception
            r15 = r3
            goto L_0x01a1
        L_0x0199:
            r0 = move-exception
            r15 = r3
            goto L_0x01a6
        L_0x019c:
            r0 = move-exception
            r15 = r3
            goto L_0x01ae
        L_0x019f:
            r0 = move-exception
            r3 = r15
        L_0x01a1:
            r3 = 0
            goto L_0x020f
        L_0x01a4:
            r0 = move-exception
            r3 = r15
        L_0x01a6:
            r10 = 0
            goto L_0x01b7
        L_0x01a8:
            r3 = r15
        L_0x01a9:
            r15 = r3
            r10 = 0
            goto L_0x01dc
        L_0x01ac:
            r0 = move-exception
            r3 = r15
        L_0x01ae:
            r10 = 0
            goto L_0x01f0
        L_0x01b0:
            r0 = move-exception
            r3 = 0
            r15 = 0
            goto L_0x020f
        L_0x01b4:
            r0 = move-exception
            r10 = 0
            r15 = 0
        L_0x01b7:
            if (r15 == 0) goto L_0x01c2
            boolean r3 = r15.inTransaction()     // Catch:{ all -> 0x020d }
            if (r3 == 0) goto L_0x01c2
            r15.endTransaction()     // Catch:{ all -> 0x020d }
        L_0x01c2:
            com.google.android.gms.measurement.internal.zzgo r3 = r22.zzj()     // Catch:{ all -> 0x020d }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x020d }
            r3.zza(r2, r0)     // Catch:{ all -> 0x020d }
            r1.zzb = r9     // Catch:{ all -> 0x020d }
            if (r10 == 0) goto L_0x01d4
            r10.close()
        L_0x01d4:
            if (r15 == 0) goto L_0x0207
            r15.close()
            goto L_0x0207
        L_0x01da:
            r10 = 0
            r15 = 0
        L_0x01dc:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x020d }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x01e7
            r10.close()
        L_0x01e7:
            if (r15 == 0) goto L_0x0207
            r15.close()
            goto L_0x0207
        L_0x01ed:
            r0 = move-exception
            r10 = 0
            r15 = 0
        L_0x01f0:
            com.google.android.gms.measurement.internal.zzgo r3 = r22.zzj()     // Catch:{ all -> 0x020d }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x020d }
            r3.zza(r2, r0)     // Catch:{ all -> 0x020d }
            r1.zzb = r9     // Catch:{ all -> 0x020d }
            if (r10 == 0) goto L_0x0202
            r10.close()
        L_0x0202:
            if (r15 == 0) goto L_0x0207
            r15.close()
        L_0x0207:
            int r7 = r7 + 1
            r3 = 0
            r5 = 5
            goto L_0x001d
        L_0x020d:
            r0 = move-exception
        L_0x020e:
            r3 = r10
        L_0x020f:
            if (r3 == 0) goto L_0x0214
            r3.close()
        L_0x0214:
            if (r15 == 0) goto L_0x0219
            r15.close()
        L_0x0219:
            throw r0
        L_0x021a:
            com.google.android.gms.measurement.internal.zzgo r0 = r22.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int):java.util.List");
    }

    zzgf(zzhy zzhy) {
        super(zzhy);
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

    public final void zzaa() {
        int delete;
        zzt();
        try {
            SQLiteDatabase zzad = zzad();
            if (zzad != null && (delete = zzad.delete("messages", (String) null, (String[]) null) + 0) > 0) {
                zzj().zzp().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzj().zzg().zza("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    private final boolean zzae() {
        return zza().getDatabasePath("google_app_measurement_local.db").exists();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzac() {
        /*
            r11 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r11.zzt()
            boolean r1 = r11.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = r11.zzae()
            if (r1 != 0) goto L_0x0012
            return r2
        L_0x0012:
            r1 = 5
            r4 = r1
            r3 = r2
        L_0x0015:
            if (r3 >= r1) goto L_0x008f
            r5 = 1
            r6 = 0
            android.database.sqlite.SQLiteDatabase r6 = r11.zzad()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 != 0) goto L_0x0027
            r11.zzb = r5     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 == 0) goto L_0x0026
            r6.close()
        L_0x0026:
            return r2
        L_0x0027:
            r6.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            java.lang.String[] r9 = new java.lang.String[r5]     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r10 = 3
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r9[r2] = r10     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.delete(r7, r8, r9)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r6.endTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r6 == 0) goto L_0x0045
            r6.close()
        L_0x0045:
            return r5
        L_0x0046:
            r0 = move-exception
            goto L_0x0089
        L_0x0048:
            r7 = move-exception
            if (r6 == 0) goto L_0x0054
            boolean r8 = r6.inTransaction()     // Catch:{ all -> 0x0046 }
            if (r8 == 0) goto L_0x0054
            r6.endTransaction()     // Catch:{ all -> 0x0046 }
        L_0x0054:
            com.google.android.gms.measurement.internal.zzgo r8 = r11.zzj()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r11.zzb = r5     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0067:
            long r7 = (long) r4
            android.os.SystemClock.sleep(r7)     // Catch:{ all -> 0x0046 }
            int r4 = r4 + 20
            if (r6 == 0) goto L_0x0086
            r6.close()
            goto L_0x0086
        L_0x0073:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzgo r8 = r11.zzj()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r11.zzb = r5     // Catch:{ all -> 0x0046 }
            if (r6 == 0) goto L_0x0086
            r6.close()
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x0089:
            if (r6 == 0) goto L_0x008e
            r6.close()
        L_0x008e:
            throw r0
        L_0x008f:
            com.google.android.gms.measurement.internal.zzgo r0 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzu()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zzac():boolean");
    }

    public final boolean zza(zzae zzae) {
        zzq();
        byte[] zza2 = zzos.zza((Parcelable) zzae);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzj().zzn().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f A[SYNTHETIC, Splitter:B:28:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c1 A[SYNTHETIC, Splitter:B:48:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x009d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:98:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzt()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            r3.put(r0, r4)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = r2
            r6 = r4
        L_0x0023:
            if (r5 >= r4) goto L_0x0129
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.zzad()     // Catch:{ SQLiteFullException -> 0x00fb, SQLiteDatabaseLockedException -> 0x00e9, SQLiteException -> 0x00bd, all -> 0x00ba }
            if (r9 != 0) goto L_0x0035
            r1.zzb = r8     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r9 == 0) goto L_0x0034
            r9.close()
        L_0x0034:
            return r2
        L_0x0035:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r10 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r10 == 0) goto L_0x0054
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            if (r0 == 0) goto L_0x0054
            long r11 = r10.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            goto L_0x0056
        L_0x004b:
            r0 = move-exception
            goto L_0x00e7
        L_0x004e:
            r0 = move-exception
            goto L_0x00b6
        L_0x0050:
            r0 = move-exception
            r7 = r10
            goto L_0x00fd
        L_0x0054:
            r11 = 0
        L_0x0056:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            java.lang.String r15 = "messages"
            if (r0 < 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzgo r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String r4 = "Data loss, local db full"
            r0.zza(r4)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            long r13 = r13 - r11
            r11 = 1
            long r13 = r13 + r11
            java.lang.String r0 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r4 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String r11 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r4[r2] = r11     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            int r0 = r9.delete(r15, r0, r4)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            long r11 = (long) r0     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzgo r0 = r16.zzj()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzg()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.String r4 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            java.lang.Long r8 = java.lang.Long.valueOf(r11)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            long r13 = r13 - r11
            java.lang.Long r11 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r0.zza(r4, r2, r8, r11)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
        L_0x009d:
            r9.insertOrThrow(r15, r7, r3)     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0050, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x004e, all -> 0x004b }
            if (r10 == 0) goto L_0x00ab
            r10.close()
        L_0x00ab:
            if (r9 == 0) goto L_0x00b0
            r9.close()
        L_0x00b0:
            r2 = 1
            return r2
        L_0x00b2:
            r7 = r10
            goto L_0x00ea
        L_0x00b4:
            r0 = move-exception
            r10 = r7
        L_0x00b6:
            r7 = r9
            goto L_0x00bf
        L_0x00b8:
            r0 = move-exception
            goto L_0x00fd
        L_0x00ba:
            r0 = move-exception
            r9 = r7
            goto L_0x011e
        L_0x00bd:
            r0 = move-exception
            r10 = r7
        L_0x00bf:
            if (r7 == 0) goto L_0x00ca
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00ca
            r7.endTransaction()     // Catch:{ all -> 0x00e5 }
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzgo r2 = r16.zzj()     // Catch:{ all -> 0x00e5 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x00e5 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00e5 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x00e5 }
            if (r10 == 0) goto L_0x00df
            r10.close()
        L_0x00df:
            if (r7 == 0) goto L_0x0117
            r7.close()
            goto L_0x0117
        L_0x00e5:
            r0 = move-exception
            r9 = r7
        L_0x00e7:
            r7 = r10
            goto L_0x011e
        L_0x00e9:
            r9 = r7
        L_0x00ea:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x011d }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f5
            r7.close()
        L_0x00f5:
            if (r9 == 0) goto L_0x0117
            r9.close()
            goto L_0x0117
        L_0x00fb:
            r0 = move-exception
            r9 = r7
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzgo r2 = r16.zzj()     // Catch:{ all -> 0x011d }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zza(r4, r0)     // Catch:{ all -> 0x011d }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x011d }
            if (r7 == 0) goto L_0x0112
            r7.close()
        L_0x0112:
            if (r9 == 0) goto L_0x0117
            r9.close()
        L_0x0117:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0023
        L_0x011d:
            r0 = move-exception
        L_0x011e:
            if (r7 == 0) goto L_0x0123
            r7.close()
        L_0x0123:
            if (r9 == 0) goto L_0x0128
            r9.close()
        L_0x0128:
            throw r0
        L_0x0129:
            com.google.android.gms.measurement.internal.zzgo r0 = r16.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzp()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgf.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzbf zzbf) {
        Parcel obtain = Parcel.obtain();
        zzbf.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzj().zzn().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzon zzon) {
        Parcel obtain = Parcel.obtain();
        zzon.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzj().zzn().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
