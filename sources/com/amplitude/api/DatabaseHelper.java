package com.amplitude.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_IDENTIFYS_TABLE = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);";
    private static final String CREATE_LONG_STORE_TABLE = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);";
    private static final String CREATE_STORE_TABLE = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);";
    private static final String EVENT_FIELD = "event";
    protected static final String EVENT_TABLE_NAME = "events";
    protected static final String IDENTIFY_TABLE_NAME = "identifys";
    private static final String ID_FIELD = "id";
    private static final String KEY_FIELD = "key";
    protected static final String LONG_STORE_TABLE_NAME = "long_store";
    protected static final String STORE_TABLE_NAME = "store";
    private static final String TAG = "com.amplitude.api.DatabaseHelper";
    private static final String VALUE_FIELD = "value";
    static final Map<String, DatabaseHelper> instances = new HashMap();
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    private boolean callResetListenerOnDatabaseReset;
    private DatabaseResetListener databaseResetListener;
    File file;
    private String instanceName;

    @Deprecated
    static DatabaseHelper getDatabaseHelper(Context context) {
        return getDatabaseHelper(context, (String) null);
    }

    static synchronized DatabaseHelper getDatabaseHelper(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            String normalizeInstanceName = Utils.normalizeInstanceName(str);
            Map<String, DatabaseHelper> map = instances;
            databaseHelper = map.get(normalizeInstanceName);
            if (databaseHelper == null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext(), normalizeInstanceName);
                map.put(normalizeInstanceName, databaseHelper);
            }
        }
        return databaseHelper;
    }

    private static String getDatabaseName(String str) {
        return (Utils.isEmptyString(str) || str.equals(Constants.DEFAULT_INSTANCE)) ? "com.amplitude.api" : "com.amplitude.api_" + str;
    }

    protected DatabaseHelper(Context context) {
        this(context, (String) null);
    }

    protected DatabaseHelper(Context context, String str) {
        super(context, getDatabaseName(str), (SQLiteDatabase.CursorFactory) null, 3);
        this.callResetListenerOnDatabaseReset = true;
        this.file = context.getDatabasePath(getDatabaseName(str));
        this.instanceName = Utils.normalizeInstanceName(str);
    }

    /* access modifiers changed from: package-private */
    public void setDatabaseResetListener(DatabaseResetListener databaseResetListener2) {
        this.databaseResetListener = databaseResetListener2;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
        DatabaseResetListener databaseResetListener2 = this.databaseResetListener;
        if (databaseResetListener2 != null && this.callResetListenerOnDatabaseReset) {
            try {
                this.callResetListenerOnDatabaseReset = false;
                databaseResetListener2.onDatabaseReset(sQLiteDatabase);
            } catch (SQLiteException e) {
                logger.e(TAG, String.format("databaseReset callback failed during onCreate", new Object[0]), e);
            } catch (Throwable th) {
                this.callResetListenerOnDatabaseReset = true;
                throw th;
            }
            this.callResetListenerOnDatabaseReset = true;
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i > i2) {
            logger.e(TAG, "onUpgrade() with invalid oldVersion and newVersion");
            resetDatabase(sQLiteDatabase);
        } else if (i2 > 1) {
            if (i == 1) {
                sQLiteDatabase.execSQL(CREATE_STORE_TABLE);
                if (i2 <= 2) {
                    return;
                }
            } else if (i != 2) {
                if (i != 3) {
                    logger.e(TAG, "onUpgrade() with unknown oldVersion " + i);
                    resetDatabase(sQLiteDatabase);
                    return;
                }
                return;
            }
            sQLiteDatabase.execSQL(CREATE_IDENTIFYS_TABLE);
            sQLiteDatabase.execSQL(CREATE_LONG_STORE_TABLE);
        }
    }

    private void resetDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValue(String str, String str2) {
        long j;
        if (str2 == null) {
            j = deleteKeyFromTable(STORE_TABLE_NAME, str);
        } else {
            j = insertOrReplaceKeyValueToTable(STORE_TABLE_NAME, str, str2);
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyLongValue(String str, Long l) {
        long j;
        if (l == null) {
            j = deleteKeyFromTable(LONG_STORE_TABLE_NAME, str);
        } else {
            j = insertOrReplaceKeyValueToTable(LONG_STORE_TABLE_NAME, str, l);
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r0.isOpen() != false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
        if (r0.isOpen() != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long insertOrReplaceKeyValueToTable(java.lang.String r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0039, StackOverflowError -> 0x0018 }
            long r4 = r3.insertOrReplaceKeyValueToTable(r0, r4, r5, r6)     // Catch:{ SQLiteException -> 0x0039, StackOverflowError -> 0x0018 }
            if (r0 == 0) goto L_0x0059
            boolean r6 = r0.isOpen()     // Catch:{ all -> 0x0067 }
            if (r6 == 0) goto L_0x0059
            r3.close()     // Catch:{ all -> 0x0067 }
            goto L_0x0059
        L_0x0016:
            r4 = move-exception
            goto L_0x005b
        L_0x0018:
            r5 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0016 }
            java.lang.String r2 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x0016 }
            java.lang.String r4 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x0016 }
            r6.e(r1, r4, r5)     // Catch:{ all -> 0x0016 }
            r3.delete()     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0057
            boolean r4 = r0.isOpen()     // Catch:{ all -> 0x0067 }
            if (r4 == 0) goto L_0x0057
        L_0x0035:
            r3.close()     // Catch:{ all -> 0x0067 }
            goto L_0x0057
        L_0x0039:
            r5 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = TAG     // Catch:{ all -> 0x0016 }
            java.lang.String r2 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x0016 }
            java.lang.String r4 = java.lang.String.format(r2, r4)     // Catch:{ all -> 0x0016 }
            r6.e(r1, r4, r5)     // Catch:{ all -> 0x0016 }
            r3.delete()     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0057
            boolean r4 = r0.isOpen()     // Catch:{ all -> 0x0067 }
            if (r4 == 0) goto L_0x0057
            goto L_0x0035
        L_0x0057:
            r4 = -1
        L_0x0059:
            monitor-exit(r3)
            return r4
        L_0x005b:
            if (r0 == 0) goto L_0x0066
            boolean r5 = r0.isOpen()     // Catch:{ all -> 0x0067 }
            if (r5 == 0) goto L_0x0066
            r3.close()     // Catch:{ all -> 0x0067 }
        L_0x0066:
            throw r4     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.insertOrReplaceKeyValueToTable(java.lang.String, java.lang.String, java.lang.Object):long");
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertOrReplaceKeyValueToTable(SQLiteDatabase sQLiteDatabase, String str, String str2, Object obj) throws SQLiteException, StackOverflowError {
        long insertKeyValueContentValuesIntoTable;
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIELD, str2);
        if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else {
            contentValues.put("value", (String) obj);
        }
        insertKeyValueContentValuesIntoTable = insertKeyValueContentValuesIntoTable(sQLiteDatabase, str, contentValues);
        if (insertKeyValueContentValuesIntoTable == -1) {
            logger.w(TAG, "Insert failed");
        }
        return insertKeyValueContentValuesIntoTable;
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertKeyValueContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insertWithOnConflict(str, (String) null, contentValues, 5);
    }

    /* access modifiers changed from: package-private */
    public synchronized long deleteKeyFromTable(String str, String str2) {
        long j;
        try {
            j = (long) getWritableDatabase().delete(str, "key=?", new String[]{str2});
            close();
        } catch (SQLiteException e) {
            logger.e(TAG, String.format("deleteKey from %s failed", new Object[]{str}), e);
            delete();
            close();
            j = -1;
            return j;
        } catch (StackOverflowError e2) {
            try {
                logger.e(TAG, String.format("deleteKey from %s failed", new Object[]{str}), e2);
                delete();
                close();
                j = -1;
                return j;
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long addEvent(String str) {
        return addEventToTable(EVENT_TABLE_NAME, str);
    }

    /* access modifiers changed from: package-private */
    public synchronized long addIdentify(String str) {
        return addEventToTable(IDENTIFY_TABLE_NAME, str);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:12:0x0031=Splitter:B:12:0x0031, B:20:0x0062=Splitter:B:20:0x0062} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized long addEventToTable(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = -1
            android.database.sqlite.SQLiteDatabase r2 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x0037 }
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x0037 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x0037 }
            java.lang.String r4 = "event"
            r3.put(r4, r7)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x0037 }
            long r2 = r5.insertEventContentValuesIntoTable(r2, r6, r3)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x0037 }
            int r7 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r7 != 0) goto L_0x0031
            com.amplitude.api.AmplitudeLog r7 = logger     // Catch:{ SQLiteException -> 0x002e, StackOverflowError -> 0x002b }
            java.lang.String r0 = TAG     // Catch:{ SQLiteException -> 0x002e, StackOverflowError -> 0x002b }
            java.lang.String r1 = "Insert into %s failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r6}     // Catch:{ SQLiteException -> 0x002e, StackOverflowError -> 0x002b }
            java.lang.String r1 = java.lang.String.format(r1, r4)     // Catch:{ SQLiteException -> 0x002e, StackOverflowError -> 0x002b }
            r7.w((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ SQLiteException -> 0x002e, StackOverflowError -> 0x002b }
            goto L_0x0031
        L_0x002b:
            r7 = move-exception
            r0 = r2
            goto L_0x0038
        L_0x002e:
            r7 = move-exception
            r0 = r2
            goto L_0x004e
        L_0x0031:
            r5.close()     // Catch:{ all -> 0x006c }
            goto L_0x0066
        L_0x0035:
            r6 = move-exception
            goto L_0x0068
        L_0x0037:
            r7 = move-exception
        L_0x0038:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = "addEvent to %s failed"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = java.lang.String.format(r4, r6)     // Catch:{ all -> 0x0035 }
            r2.e(r3, r6, r7)     // Catch:{ all -> 0x0035 }
            r5.delete()     // Catch:{ all -> 0x0035 }
            goto L_0x0062
        L_0x004d:
            r7 = move-exception
        L_0x004e:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x0035 }
            java.lang.String r4 = "addEvent to %s failed"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x0035 }
            java.lang.String r6 = java.lang.String.format(r4, r6)     // Catch:{ all -> 0x0035 }
            r2.e(r3, r6, r7)     // Catch:{ all -> 0x0035 }
            r5.delete()     // Catch:{ all -> 0x0035 }
        L_0x0062:
            r5.close()     // Catch:{ all -> 0x006c }
            r2 = r0
        L_0x0066:
            monitor-exit(r5)
            return r2
        L_0x0068:
            r5.close()     // Catch:{ all -> 0x006c }
            throw r6     // Catch:{ all -> 0x006c }
        L_0x006c:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.addEventToTable(java.lang.String, java.lang.String):long");
    }

    /* access modifiers changed from: package-private */
    public synchronized long insertEventContentValuesIntoTable(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) throws SQLiteException, StackOverflowError {
        return sQLiteDatabase.insert(str, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public synchronized String getValue(String str) {
        return (String) getValueFromTable(STORE_TABLE_NAME, str);
    }

    /* access modifiers changed from: package-private */
    public synchronized Long getLongValue(String str) {
        return (Long) getValueFromTable(LONG_STORE_TABLE_NAME, str);
    }

    /* JADX WARNING: type inference failed for: r13v16, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r13v17, types: [java.lang.String] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058 A[SYNTHETIC, Splitter:B:25:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0063 A[SYNTHETIC, Splitter:B:32:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0084 A[SYNTHETIC, Splitter:B:41:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a0 A[SYNTHETIC, Splitter:B:48:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00aa A[SYNTHETIC, Splitter:B:55:0x00aa] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0053=Splitter:B:22:0x0053, B:45:0x008a=Splitter:B:45:0x008a, B:29:0x005e=Splitter:B:29:0x005e, B:38:0x006e=Splitter:B:38:0x006e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object getValueFromTable(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            monitor-enter(r12)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r2 = r12.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            r1 = 2
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            java.lang.String r1 = "key"
            r3 = 0
            r4[r3] = r1     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            java.lang.String r1 = "value"
            r11 = 1
            r4[r11] = r1     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            java.lang.String r5 = "key = ?"
            java.lang.String[] r6 = new java.lang.String[]{r14}     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r1 = r12
            r3 = r13
            android.database.Cursor r14 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0088, StackOverflowError -> 0x006c, IllegalStateException -> 0x005c, RuntimeException -> 0x0051, all -> 0x004f }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b, IllegalStateException -> 0x0049, RuntimeException -> 0x0047 }
            if (r1 == 0) goto L_0x0041
            java.lang.String r1 = "store"
            boolean r1 = r13.equals(r1)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b, IllegalStateException -> 0x0049, RuntimeException -> 0x0047 }
            if (r1 == 0) goto L_0x0038
            java.lang.String r13 = r14.getString(r11)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b, IllegalStateException -> 0x0049, RuntimeException -> 0x0047 }
            goto L_0x0040
        L_0x0038:
            long r1 = r14.getLong(r11)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b, IllegalStateException -> 0x0049, RuntimeException -> 0x0047 }
            java.lang.Long r13 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x004d, StackOverflowError -> 0x004b, IllegalStateException -> 0x0049, RuntimeException -> 0x0047 }
        L_0x0040:
            r0 = r13
        L_0x0041:
            if (r14 == 0) goto L_0x0066
            r14.close()     // Catch:{ all -> 0x006a }
            goto L_0x0066
        L_0x0047:
            r13 = move-exception
            goto L_0x0053
        L_0x0049:
            r13 = move-exception
            goto L_0x005e
        L_0x004b:
            r1 = move-exception
            goto L_0x006e
        L_0x004d:
            r1 = move-exception
            goto L_0x008a
        L_0x004f:
            r13 = move-exception
            goto L_0x00a8
        L_0x0051:
            r13 = move-exception
            r14 = r0
        L_0x0053:
            convertIfCursorWindowException(r13)     // Catch:{ all -> 0x00a6 }
            if (r14 == 0) goto L_0x0066
            r14.close()     // Catch:{ all -> 0x006a }
            goto L_0x0066
        L_0x005c:
            r13 = move-exception
            r14 = r0
        L_0x005e:
            r12.handleIfCursorRowTooLargeException(r13)     // Catch:{ all -> 0x00a6 }
            if (r14 == 0) goto L_0x0066
            r14.close()     // Catch:{ all -> 0x006a }
        L_0x0066:
            r12.close()     // Catch:{ all -> 0x006a }
            goto L_0x00a4
        L_0x006a:
            r13 = move-exception
            goto L_0x00b1
        L_0x006c:
            r1 = move-exception
            r14 = r0
        L_0x006e:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x00a6 }
            java.lang.String r4 = "getValue from %s failed"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}     // Catch:{ all -> 0x00a6 }
            java.lang.String r13 = java.lang.String.format(r4, r13)     // Catch:{ all -> 0x00a6 }
            r2.e(r3, r13, r1)     // Catch:{ all -> 0x00a6 }
            r12.delete()     // Catch:{ all -> 0x00a6 }
            if (r14 == 0) goto L_0x0066
            r14.close()     // Catch:{ all -> 0x006a }
            goto L_0x0066
        L_0x0088:
            r1 = move-exception
            r14 = r0
        L_0x008a:
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = TAG     // Catch:{ all -> 0x00a6 }
            java.lang.String r4 = "getValue from %s failed"
            java.lang.Object[] r13 = new java.lang.Object[]{r13}     // Catch:{ all -> 0x00a6 }
            java.lang.String r13 = java.lang.String.format(r4, r13)     // Catch:{ all -> 0x00a6 }
            r2.e(r3, r13, r1)     // Catch:{ all -> 0x00a6 }
            r12.delete()     // Catch:{ all -> 0x00a6 }
            if (r14 == 0) goto L_0x0066
            r14.close()     // Catch:{ all -> 0x006a }
            goto L_0x0066
        L_0x00a4:
            monitor-exit(r12)
            return r0
        L_0x00a6:
            r13 = move-exception
            r0 = r14
        L_0x00a8:
            if (r0 == 0) goto L_0x00ad
            r0.close()     // Catch:{ all -> 0x006a }
        L_0x00ad:
            r12.close()     // Catch:{ all -> 0x006a }
            throw r13     // Catch:{ all -> 0x006a }
        L_0x00b1:
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public synchronized List<JSONObject> getEvents(long j, long j2) throws JSONException {
        return getEventsFromTable(EVENT_TABLE_NAME, j, j2);
    }

    /* access modifiers changed from: package-private */
    public synchronized List<JSONObject> getIdentifys(long j, long j2) throws JSONException {
        return getEventsFromTable(IDENTIFY_TABLE_NAME, j, j2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<org.json.JSONObject> getEventsFromTable(java.lang.String r20, long r21, long r23) throws org.json.JSONException {
        /*
            r19 = this;
            r11 = r19
            r0 = r21
            r2 = r23
            java.lang.String r4 = ""
            java.lang.String r5 = "id <= "
            monitor-enter(r19)
            java.util.LinkedList r12 = new java.util.LinkedList     // Catch:{ all -> 0x00e9 }
            r12.<init>()     // Catch:{ all -> 0x00e9 }
            r13 = 0
            android.database.sqlite.SQLiteDatabase r6 = r19.getReadableDatabase()     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r7 = 2
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r8 = "id"
            r14 = 0
            r7[r14] = r8     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r8 = "event"
            r15 = 1
            r7[r15] = r8     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r8 = 0
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0037
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r10.<init>(r5)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.StringBuilder r0 = r10.append(r0)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r0 = r0.toString()     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r5 = r0
            goto L_0x0038
        L_0x0037:
            r5 = r13
        L_0x0038:
            r0 = 0
            r10 = 0
            r16 = 0
            java.lang.String r17 = "id ASC"
            int r1 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r1 < 0) goto L_0x0052
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r1.<init>(r4)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r18 = r1
            goto L_0x0054
        L_0x0052:
            r18 = r13
        L_0x0054:
            r1 = r19
            r2 = r6
            r3 = r20
            r4 = r7
            r6 = r0
            r7 = r10
            r8 = r16
            r9 = r17
            r10 = r18
            android.database.Cursor r13 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
        L_0x0066:
            boolean r0 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            if (r0 == 0) goto L_0x0089
            long r0 = r13.getLong(r14)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r2 = r13.getString(r15)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            boolean r3 = com.amplitude.api.Utils.isEmptyString(r2)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            if (r3 == 0) goto L_0x007b
            goto L_0x0066
        L_0x007b:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r3.<init>(r2)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            java.lang.String r2 = "event_id"
            r3.put(r2, r0)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            r12.add(r3)     // Catch:{ SQLiteException -> 0x00c3, StackOverflowError -> 0x00a8, IllegalStateException -> 0x009e, RuntimeException -> 0x0094 }
            goto L_0x0066
        L_0x0089:
            if (r13 == 0) goto L_0x008e
            r13.close()     // Catch:{ all -> 0x00e9 }
        L_0x008e:
            r19.close()     // Catch:{ all -> 0x00e9 }
            goto L_0x00de
        L_0x0092:
            r0 = move-exception
            goto L_0x00e0
        L_0x0094:
            r0 = move-exception
            convertIfCursorWindowException(r0)     // Catch:{ all -> 0x0092 }
            if (r13 == 0) goto L_0x008e
            r13.close()     // Catch:{ all -> 0x00e9 }
            goto L_0x008e
        L_0x009e:
            r0 = move-exception
            r11.handleIfCursorRowTooLargeException(r0)     // Catch:{ all -> 0x0092 }
            if (r13 == 0) goto L_0x008e
            r13.close()     // Catch:{ all -> 0x00e9 }
            goto L_0x008e
        L_0x00a8:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = "getEvents from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r20}     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x0092 }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x0092 }
            r19.delete()     // Catch:{ all -> 0x0092 }
            if (r13 == 0) goto L_0x008e
            r13.close()     // Catch:{ all -> 0x00e9 }
            goto L_0x008e
        L_0x00c3:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = "getEvents from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[]{r20}     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x0092 }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x0092 }
            r19.delete()     // Catch:{ all -> 0x0092 }
            if (r13 == 0) goto L_0x008e
            r13.close()     // Catch:{ all -> 0x00e9 }
            goto L_0x008e
        L_0x00de:
            monitor-exit(r19)
            return r12
        L_0x00e0:
            if (r13 == 0) goto L_0x00e5
            r13.close()     // Catch:{ all -> 0x00e9 }
        L_0x00e5:
            r19.close()     // Catch:{ all -> 0x00e9 }
            throw r0     // Catch:{ all -> 0x00e9 }
        L_0x00e9:
            r0 = move-exception
            monitor-exit(r19)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getEventsFromTable(java.lang.String, long, long):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public synchronized long getEventCount() {
        return getEventCountFromTable(EVENT_TABLE_NAME);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getIdentifyCount() {
        return getEventCountFromTable(IDENTIFY_TABLE_NAME);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getTotalEventCount() {
        return getEventCount() + getIdentifyCount();
    }

    private synchronized long getEventCountFromTable(String str) {
        long j;
        SQLiteStatement sQLiteStatement = null;
        try {
            SQLiteStatement compileStatement = getReadableDatabase().compileStatement("SELECT COUNT(*) FROM " + str);
            j = compileStatement.simpleQueryForLong();
            if (compileStatement != null) {
                compileStatement.close();
            }
            close();
        } catch (SQLiteException e) {
            logger.e(TAG, String.format("getNumberRows for %s failed", new Object[]{str}), e);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            close();
            j = 0;
            return j;
        } catch (StackOverflowError e2) {
            try {
                logger.e(TAG, String.format("getNumberRows for %s failed", new Object[]{str}), e2);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                j = 0;
                return j;
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNthEventId(long j) {
        return getNthEventIdFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized long getNthIdentifyId(long j) {
        return getNthEventIdFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized long getNthEventIdFromTable(String str, long j) {
        long j2;
        j2 = -1;
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = getReadableDatabase().compileStatement("SELECT id FROM " + str + " LIMIT 1 OFFSET " + (j - 1));
            try {
                j2 = sQLiteStatement.simpleQueryForLong();
            } catch (SQLiteDoneException e) {
                logger.w(TAG, (Throwable) e);
            }
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (SQLiteException e2) {
            logger.e(TAG, String.format("getNthEventId from %s failed", new Object[]{str}), e2);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (StackOverflowError e3) {
            try {
                logger.e(TAG, String.format("getNthEventId from %s failed", new Object[]{str}), e3);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        close();
        return j2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeEvents(long j) {
        removeEventsFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeIdentifys(long j) {
        removeEventsFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventsFromTable(String str, long j) {
        try {
            getWritableDatabase().delete(str, "id <= " + j, (String[]) null);
        } catch (SQLiteException e) {
            logger.e(TAG, String.format("removeEvents from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.e(TAG, String.format("removeEvents from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeEvent(long j) {
        removeEventFromTable(EVENT_TABLE_NAME, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized void removeIdentify(long j) {
        removeEventFromTable(IDENTIFY_TABLE_NAME, j);
    }

    private synchronized void removeEventFromTable(String str, long j) {
        try {
            getWritableDatabase().delete(str, "id = " + j, (String[]) null);
        } catch (SQLiteException e) {
            logger.e(TAG, String.format("removeEvent from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.e(TAG, String.format("removeEvent from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c7, code lost:
        if (r1.isOpen() != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00c9, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00e5, code lost:
        if (r1.isOpen() != false) goto L_0x00c9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void delete() {
        /*
            r8 = this;
            java.lang.String r0 = "databaseReset callback failed during delete"
            r1 = 0
            r2 = 0
            r3 = 1
            r8.close()     // Catch:{ SecurityException -> 0x005a }
            java.io.File r4 = r8.file     // Catch:{ SecurityException -> 0x005a }
            r4.delete()     // Catch:{ SecurityException -> 0x005a }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener
            if (r4 == 0) goto L_0x00ab
            boolean r4 = r8.callResetListenerOnDatabaseReset
            if (r4 == 0) goto L_0x00ab
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0031 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x0031 }
            r4.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x0031 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
        L_0x002a:
            r8.close()
            goto L_0x00ab
        L_0x002f:
            r0 = move-exception
            goto L_0x004a
        L_0x0031:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x002f }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x002f }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x002f }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x002f }
            r5.e(r6, r0, r4)     // Catch:{ all -> 0x002f }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x004a:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x0057
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x0057
            r8.close()
        L_0x0057:
            throw r0
        L_0x0058:
            r4 = move-exception
            goto L_0x00ac
        L_0x005a:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x0058 }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x0058 }
            java.lang.String r7 = "delete failed"
            r5.e(r6, r7, r4)     // Catch:{ all -> 0x0058 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener
            if (r4 == 0) goto L_0x00ab
            boolean r4 = r8.callResetListenerOnDatabaseReset
            if (r4 == 0) goto L_0x00ab
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0084 }
            com.amplitude.api.DatabaseResetListener r4 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x0084 }
            r4.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x0084 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x0082:
            r0 = move-exception
            goto L_0x009d
        L_0x0084:
            r4 = move-exception
            com.amplitude.api.AmplitudeLog r5 = logger     // Catch:{ all -> 0x0082 }
            java.lang.String r6 = TAG     // Catch:{ all -> 0x0082 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x0082 }
            r5.e(r6, r0, r4)     // Catch:{ all -> 0x0082 }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00ab
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00ab
            goto L_0x002a
        L_0x009d:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00aa
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x00aa
            r8.close()
        L_0x00aa:
            throw r0
        L_0x00ab:
            return
        L_0x00ac:
            com.amplitude.api.DatabaseResetListener r5 = r8.databaseResetListener
            if (r5 == 0) goto L_0x00f6
            boolean r5 = r8.callResetListenerOnDatabaseReset
            if (r5 == 0) goto L_0x00f6
            r8.callResetListenerOnDatabaseReset = r2
            android.database.sqlite.SQLiteDatabase r1 = r8.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00cf }
            com.amplitude.api.DatabaseResetListener r5 = r8.databaseResetListener     // Catch:{ SQLiteException -> 0x00cf }
            r5.onDatabaseReset(r1)     // Catch:{ SQLiteException -> 0x00cf }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f6
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00f6
        L_0x00c9:
            r8.close()
            goto L_0x00f6
        L_0x00cd:
            r0 = move-exception
            goto L_0x00e8
        L_0x00cf:
            r5 = move-exception
            com.amplitude.api.AmplitudeLog r6 = logger     // Catch:{ all -> 0x00cd }
            java.lang.String r7 = TAG     // Catch:{ all -> 0x00cd }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00cd }
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x00cd }
            r6.e(r7, r0, r5)     // Catch:{ all -> 0x00cd }
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f6
            boolean r0 = r1.isOpen()
            if (r0 == 0) goto L_0x00f6
            goto L_0x00c9
        L_0x00e8:
            r8.callResetListenerOnDatabaseReset = r3
            if (r1 == 0) goto L_0x00f5
            boolean r1 = r1.isOpen()
            if (r1 == 0) goto L_0x00f5
            r8.close()
        L_0x00f5:
            throw r0
        L_0x00f6:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.delete():void");
    }

    /* access modifiers changed from: package-private */
    public boolean dbFileExists() {
        return this.file.exists();
    }

    /* access modifiers changed from: package-private */
    public Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    private void handleIfCursorRowTooLargeException(IllegalStateException illegalStateException) {
        String message = illegalStateException.getMessage();
        if (Utils.isEmptyString(message) || !message.contains("Couldn't read") || !message.contains("CursorWindow")) {
            throw illegalStateException;
        }
        delete();
    }

    private static void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (Utils.isEmptyString(message) || (!message.startsWith("Cursor window allocation of") && !message.startsWith("Could not allocate CursorWindow"))) {
            throw runtimeException;
        }
        throw new CursorWindowAllocationException(message);
    }
}
