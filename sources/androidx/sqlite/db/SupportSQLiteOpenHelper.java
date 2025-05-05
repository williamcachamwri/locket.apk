package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.Closeable;
import java.io.File;

public interface SupportSQLiteOpenHelper extends Closeable {

    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getReadableDatabase();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);

    public static abstract class Callback {
        private static final String TAG = "SupportSQLite";
        public final int version;

        public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public Callback(int i) {
            this.version = i;
        }

        public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
            throw new SQLiteException("Can't downgrade database from version " + i + " to " + i2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
            if (r0 != null) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
            r3 = r0.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
            if (r3.hasNext() != false) goto L_0x003e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
            deleteDatabaseFile((java.lang.String) r3.next().second);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
            deleteDatabaseFile(r3.getPath());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x002c, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x002e */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0056  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
        /* JADX WARNING: Removed duplicated region for block: B:7:0x002c A[ExcHandler: all (r1v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r0 
          PHI: (r0v11 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) = (r0v4 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v5 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>), (r0v5 java.util.List<android.util.Pair<java.lang.String, java.lang.String>>) binds: [B:5:0x0027, B:8:0x002e, B:9:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x0027] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCorruption(androidx.sqlite.db.SupportSQLiteDatabase r3) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Corruption reported by sqlite on database: "
                r0.<init>(r1)
                java.lang.String r1 = r3.getPath()
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "SupportSQLite"
                io.sentry.android.core.SentryLogcatAdapter.e(r1, r0)
                boolean r0 = r3.isOpen()
                if (r0 != 0) goto L_0x0026
                java.lang.String r3 = r3.getPath()
                r2.deleteDatabaseFile(r3)
                return
            L_0x0026:
                r0 = 0
                java.util.List r0 = r3.getAttachedDbs()     // Catch:{ SQLiteException -> 0x002e, all -> 0x002c }
                goto L_0x002e
            L_0x002c:
                r1 = move-exception
                goto L_0x0032
            L_0x002e:
                r3.close()     // Catch:{ IOException -> 0x0054, all -> 0x002c }
                goto L_0x0054
            L_0x0032:
                if (r0 == 0) goto L_0x004c
                java.util.Iterator r3 = r0.iterator()
            L_0x0038:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x0053
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                java.lang.Object r0 = r0.second
                java.lang.String r0 = (java.lang.String) r0
                r2.deleteDatabaseFile(r0)
                goto L_0x0038
            L_0x004c:
                java.lang.String r3 = r3.getPath()
                r2.deleteDatabaseFile(r3)
            L_0x0053:
                throw r1
            L_0x0054:
                if (r0 == 0) goto L_0x006e
                java.util.Iterator r3 = r0.iterator()
            L_0x005a:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x0075
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                java.lang.Object r0 = r0.second
                java.lang.String r0 = (java.lang.String) r0
                r2.deleteDatabaseFile(r0)
                goto L_0x005a
            L_0x006e:
                java.lang.String r3 = r3.getPath()
                r2.deleteDatabaseFile(r3)
            L_0x0075:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.SupportSQLiteOpenHelper.Callback.onCorruption(androidx.sqlite.db.SupportSQLiteDatabase):void");
        }

        private void deleteDatabaseFile(String str) {
            if (!str.equalsIgnoreCase(":memory:") && str.trim().length() != 0) {
                SentryLogcatAdapter.w(TAG, "deleting the database file: " + str);
                try {
                    SQLiteDatabase.deleteDatabase(new File(str));
                } catch (Exception e) {
                    SentryLogcatAdapter.w(TAG, "delete failed: ", e);
                }
            }
        }
    }

    public static class Configuration {
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        Configuration(Context context2, String str, Callback callback2) {
            this(context2, str, callback2, false);
        }

        Configuration(Context context2, String str, Callback callback2, boolean z) {
            this.context = context2;
            this.name = str;
            this.callback = callback2;
            this.useNoBackupDirectory = z;
        }

        public static Builder builder(Context context2) {
            return new Builder(context2);
        }

        public static class Builder {
            Callback mCallback;
            Context mContext;
            String mName;
            boolean mUseNoBackUpDirectory;

            public Configuration build() {
                if (this.mCallback == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (this.mContext == null) {
                    throw new IllegalArgumentException("Must set a non-null context to create the configuration.");
                } else if (!this.mUseNoBackUpDirectory || !TextUtils.isEmpty(this.mName)) {
                    return new Configuration(this.mContext, this.mName, this.mCallback, this.mUseNoBackUpDirectory);
                } else {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
            }

            Builder(Context context) {
                this.mContext = context;
            }

            public Builder name(String str) {
                this.mName = str;
                return this;
            }

            public Builder callback(Callback callback) {
                this.mCallback = callback;
                return this;
            }

            public Builder noBackupDirectory(boolean z) {
                this.mUseNoBackUpDirectory = z;
                return this;
            }
        }
    }
}
