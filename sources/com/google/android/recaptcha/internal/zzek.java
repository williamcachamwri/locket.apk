package com.google.android.recaptcha.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzek extends SQLiteOpenHelper {
    public static final zzei zza;
    private static final int zzb;
    private static final String zzc;
    /* access modifiers changed from: private */
    public static zzek zzd;

    static {
        zzei zzei = new zzei((DefaultConstructorMarker) null);
        zza = zzei;
        zzb = zzei.zzc("18.7.0-beta01");
        zzc = zzei.zzd("18.7.0-beta01");
    }

    public /* synthetic */ zzek(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        super(context, zzc, (SQLiteDatabase.CursorFactory) null, zzb);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ce");
        sQLiteDatabase.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    public final int zza(List list) {
        if (list.isEmpty()) {
            return 0;
        }
        return getWritableDatabase().delete("ce", "id IN ".concat(String.valueOf(CollectionsKt.joinToString$default(list, ", ", "(", ")", 0, (CharSequence) null, zzej.zza, 24, (Object) null))), (String[]) null);
    }

    public final int zzb() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM ce", (String[]) null);
        int i = -1;
        try {
            if (rawQuery.moveToNext()) {
                i = rawQuery.getInt(0);
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
        rawQuery.close();
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0052, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0055, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0048, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r1 = kotlin.collections.CollectionsKt.emptyList();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x004a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzd() {
        /*
            r8 = this;
            android.database.sqlite.SQLiteDatabase r0 = r8.getReadableDatabase()
            java.lang.String r1 = "ce"
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "ts ASC"
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
        L_0x0018:
            boolean r2 = r0.moveToNext()     // Catch:{ Exception -> 0x004a }
            if (r2 == 0) goto L_0x004e
            java.lang.String r2 = "id"
            int r2 = r0.getColumnIndexOrThrow(r2)     // Catch:{ Exception -> 0x004a }
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = "ss"
            int r3 = r0.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x004a }
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x004a }
            java.lang.String r4 = "ts"
            int r4 = r0.getColumnIndexOrThrow(r4)     // Catch:{ Exception -> 0x004a }
            long r4 = r0.getLong(r4)     // Catch:{ Exception -> 0x004a }
            com.google.android.recaptcha.internal.zzel r6 = new com.google.android.recaptcha.internal.zzel     // Catch:{ Exception -> 0x004a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ Exception -> 0x004a }
            r6.<init>(r3, r4, r2)     // Catch:{ Exception -> 0x004a }
            r1.add(r6)     // Catch:{ Exception -> 0x004a }
            goto L_0x0018
        L_0x0048:
            r1 = move-exception
            goto L_0x0052
        L_0x004a:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ all -> 0x0048 }
        L_0x004e:
            r0.close()
            return r1
        L_0x0052:
            r0.close()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzek.zzd():java.util.List");
    }

    public final boolean zzf(zzel zzel) {
        return zza(CollectionsKt.listOf(zzel)) == 1;
    }
}
