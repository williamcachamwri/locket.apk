package com.google.firebase.firestore.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.FileUtil;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class SQLitePersistence extends Persistence {
    public static final int MAX_ARGS = 900;
    private final SQLiteBundleCache bundleCache;
    private SQLiteDatabase db;
    private final OpenHelper opener;
    /* access modifiers changed from: private */
    public final SQLiteLruReferenceDelegate referenceDelegate;
    private final SQLiteRemoteDocumentCache remoteDocumentCache;
    private final LocalSerializer serializer;
    private boolean started;
    private final SQLiteTargetCache targetCache;
    private final SQLiteTransactionListener transactionListener;

    public static String databaseName(String str, DatabaseId databaseId) {
        try {
            return "firestore." + URLEncoder.encode(str, "utf-8") + "." + URLEncoder.encode(databaseId.getProjectId(), "utf-8") + "." + URLEncoder.encode(databaseId.getDatabaseId(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public SQLitePersistence(Context context, String str, DatabaseId databaseId, LocalSerializer localSerializer, LruGarbageCollector.Params params) {
        this(localSerializer, params, new OpenHelper(context, localSerializer, databaseName(str, databaseId)));
    }

    public SQLitePersistence(LocalSerializer localSerializer, LruGarbageCollector.Params params, OpenHelper openHelper) {
        this.transactionListener = new SQLiteTransactionListener() {
            public void onRollback() {
            }

            public void onBegin() {
                SQLitePersistence.this.referenceDelegate.onTransactionStarted();
            }

            public void onCommit() {
                SQLitePersistence.this.referenceDelegate.onTransactionCommitted();
            }
        };
        this.opener = openHelper;
        this.serializer = localSerializer;
        this.targetCache = new SQLiteTargetCache(this, localSerializer);
        this.bundleCache = new SQLiteBundleCache(this, localSerializer);
        this.remoteDocumentCache = new SQLiteRemoteDocumentCache(this, localSerializer);
        this.referenceDelegate = new SQLiteLruReferenceDelegate(this, params);
    }

    public void start() {
        Assert.hardAssert(!this.started, "SQLitePersistence double-started!", new Object[0]);
        this.started = true;
        try {
            this.db = this.opener.getWritableDatabase();
            this.targetCache.start();
            this.referenceDelegate.start(this.targetCache.getHighestListenSequenceNumber());
        } catch (SQLiteDatabaseLockedException e) {
            throw new RuntimeException("Failed to gain exclusive lock to the Cloud Firestore client's offline persistence. This generally means you are using Cloud Firestore from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing Cloud Firestore in your Application class. If you are intentionally using Cloud Firestore from multiple processes, you can only enable offline persistence (that is, call setPersistenceEnabled(true)) in one of them.", e);
        }
    }

    public void shutdown() {
        Assert.hardAssert(this.started, "SQLitePersistence shutdown without start!", new Object[0]);
        this.started = false;
        this.db.close();
        this.db = null;
    }

    public boolean isStarted() {
        return this.started;
    }

    public SQLiteLruReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        return new SQLiteMutationQueue(this, this.serializer, user, indexManager);
    }

    /* access modifiers changed from: package-private */
    public SQLiteTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* access modifiers changed from: package-private */
    public IndexManager getIndexManager(User user) {
        return new SQLiteIndexManager(this, this.serializer, user);
    }

    /* access modifiers changed from: package-private */
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlayCache(User user) {
        return new SQLiteDocumentOverlayCache(this, this.serializer, user);
    }

    /* access modifiers changed from: package-private */
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new SQLiteOverlayMigrationManager(this);
    }

    /* access modifiers changed from: package-private */
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public GlobalsCache getGlobalsCache() {
        return new SQLiteGlobalsCache(this);
    }

    /* access modifiers changed from: package-private */
    public void runTransaction(String str, Runnable runnable) {
        Logger.debug(TAG, "Starting transaction: %s", str);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            runnable.run();
            this.db.setTransactionSuccessful();
        } finally {
            this.db.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T runTransaction(String str, Supplier<T> supplier) {
        Logger.debug(TAG, "Starting transaction: %s", str);
        this.db.beginTransactionWithListener(this.transactionListener);
        try {
            T t = supplier.get();
            this.db.setTransactionSuccessful();
            return t;
        } finally {
            this.db.endTransaction();
        }
    }

    public static void clearPersistence(Context context, DatabaseId databaseId, String str) throws FirebaseFirestoreException {
        String path = context.getDatabasePath(databaseName(str, databaseId)).getPath();
        String str2 = path + "-wal";
        File file = new File(path);
        File file2 = new File(path + "-journal");
        File file3 = new File(str2);
        try {
            FileUtil.delete(file);
            FileUtil.delete(file2);
            FileUtil.delete(file3);
        } catch (IOException e) {
            throw new FirebaseFirestoreException("Failed to clear persistence." + e, FirebaseFirestoreException.Code.UNKNOWN);
        }
    }

    /* access modifiers changed from: package-private */
    public long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return ((Long) query("PRAGMA page_size").firstValue(new SQLitePersistence$$ExternalSyntheticLambda0())).longValue();
    }

    private long getPageCount() {
        return ((Long) query("PRAGMA page_count").firstValue(new SQLitePersistence$$ExternalSyntheticLambda1())).longValue();
    }

    static class OpenHelper extends SQLiteOpenHelper {
        private boolean configured;
        private final LocalSerializer serializer;

        private OpenHelper(Context context, LocalSerializer localSerializer, String str) {
            this(context, localSerializer, str, 17);
        }

        OpenHelper(Context context, LocalSerializer localSerializer, String str, int i) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, i);
            this.serializer = localSerializer;
        }

        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.configured = true;
            sQLiteDatabase.rawQuery("PRAGMA locking_mode = EXCLUSIVE", new String[0]).close();
        }

        private void ensureConfigured(SQLiteDatabase sQLiteDatabase) {
            if (!this.configured) {
                onConfigure(sQLiteDatabase);
            }
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            ensureConfigured(sQLiteDatabase);
            new SQLiteSchema(sQLiteDatabase, this.serializer).runSchemaUpgrades(0);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            ensureConfigured(sQLiteDatabase);
            new SQLiteSchema(sQLiteDatabase, this.serializer).runSchemaUpgrades(i);
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            ensureConfigured(sQLiteDatabase);
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            ensureConfigured(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: package-private */
    public void execute(String str, Object... objArr) {
        this.db.execSQL(str, objArr);
    }

    /* access modifiers changed from: package-private */
    public SQLiteStatement prepare(String str) {
        return this.db.compileStatement(str);
    }

    /* access modifiers changed from: package-private */
    public int execute(SQLiteStatement sQLiteStatement, Object... objArr) {
        sQLiteStatement.clearBindings();
        bind(sQLiteStatement, objArr);
        return sQLiteStatement.executeUpdateDelete();
    }

    /* access modifiers changed from: package-private */
    public Query query(String str) {
        return new Query(this.db, str);
    }

    static class Query {
        private SQLiteDatabase.CursorFactory cursorFactory;
        private final SQLiteDatabase db;
        private final String sql;

        Query(SQLiteDatabase sQLiteDatabase, String str) {
            this.db = sQLiteDatabase;
            this.sql = str;
        }

        /* access modifiers changed from: package-private */
        public Query binding(Object... objArr) {
            this.cursorFactory = new SQLitePersistence$Query$$ExternalSyntheticLambda0(objArr);
            return this;
        }

        /* access modifiers changed from: package-private */
        public int forEach(Consumer<Cursor> consumer) {
            Cursor startQuery = startQuery();
            int i = 0;
            while (startQuery.moveToNext()) {
                try {
                    i++;
                    consumer.accept(startQuery);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (startQuery != null) {
                startQuery.close();
            }
            return i;
            throw th;
        }

        /* access modifiers changed from: package-private */
        public int first(Consumer<Cursor> consumer) {
            Cursor startQuery = startQuery();
            try {
                if (startQuery.moveToFirst()) {
                    consumer.accept(startQuery);
                    if (startQuery == null) {
                        return 1;
                    }
                    startQuery.close();
                    return 1;
                } else if (startQuery == null) {
                    return 0;
                } else {
                    startQuery.close();
                    return 0;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        /* access modifiers changed from: package-private */
        public <T> T firstValue(Function<Cursor, T> function) {
            Cursor startQuery = startQuery();
            try {
                if (startQuery.moveToFirst()) {
                    T apply = function.apply(startQuery);
                    if (startQuery != null) {
                        startQuery.close();
                    }
                    return apply;
                } else if (startQuery == null) {
                    return null;
                } else {
                    startQuery.close();
                    return null;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            Cursor startQuery = startQuery();
            try {
                boolean z = !startQuery.moveToFirst();
                if (startQuery != null) {
                    startQuery.close();
                }
                return z;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        private Cursor startQuery() {
            SQLiteDatabase.CursorFactory cursorFactory2 = this.cursorFactory;
            if (cursorFactory2 != null) {
                return this.db.rawQueryWithFactory(cursorFactory2, this.sql, (String[]) null, (String) null);
            }
            return this.db.rawQuery(this.sql, (String[]) null);
        }
    }

    static class LongQuery {
        private static final int LIMIT = 900;
        private final List<Object> argsHead;
        private final Iterator<Object> argsIter;
        private final SQLitePersistence db;
        private final String head;
        private int subqueriesPerformed = 0;
        private final String tail;

        LongQuery(SQLitePersistence sQLitePersistence, String str, List<Object> list, String str2) {
            this.db = sQLitePersistence;
            this.head = str;
            this.argsHead = Collections.emptyList();
            this.tail = str2;
            this.argsIter = list.iterator();
        }

        LongQuery(SQLitePersistence sQLitePersistence, String str, List<Object> list, List<Object> list2, String str2) {
            this.db = sQLitePersistence;
            this.head = str;
            this.argsHead = list;
            this.tail = str2;
            this.argsIter = list2.iterator();
        }

        /* access modifiers changed from: package-private */
        public boolean hasMoreSubqueries() {
            return this.argsIter.hasNext();
        }

        private Object[] getNextSubqueryArgs() {
            ArrayList arrayList = new ArrayList(this.argsHead);
            int i = 0;
            while (this.argsIter.hasNext() && i < 900 - this.argsHead.size()) {
                arrayList.add(this.argsIter.next());
                i++;
            }
            return arrayList.toArray();
        }

        /* access modifiers changed from: package-private */
        public Query performNextSubquery() {
            this.subqueriesPerformed++;
            Object[] nextSubqueryArgs = getNextSubqueryArgs();
            return this.db.query(this.head + Util.repeatSequence("?", nextSubqueryArgs.length, ", ") + this.tail).binding(nextSubqueryArgs);
        }

        /* access modifiers changed from: package-private */
        public void executeNextSubquery() {
            this.subqueriesPerformed++;
            Object[] nextSubqueryArgs = getNextSubqueryArgs();
            this.db.execute(this.head + Util.repeatSequence("?", nextSubqueryArgs.length, ", ") + this.tail, nextSubqueryArgs);
        }

        /* access modifiers changed from: package-private */
        public int getSubqueriesPerformed() {
            return this.subqueriesPerformed;
        }
    }

    /* access modifiers changed from: private */
    public static void bind(SQLiteProgram sQLiteProgram, Object[] objArr) {
        for (int i = 0; i < objArr.length; i++) {
            String str = objArr[i];
            if (str == null) {
                sQLiteProgram.bindNull(i + 1);
            } else if (str instanceof String) {
                sQLiteProgram.bindString(i + 1, str);
            } else if (str instanceof Integer) {
                sQLiteProgram.bindLong(i + 1, (long) ((Integer) str).intValue());
            } else if (str instanceof Long) {
                sQLiteProgram.bindLong(i + 1, ((Long) str).longValue());
            } else if (str instanceof Double) {
                sQLiteProgram.bindDouble(i + 1, ((Double) str).doubleValue());
            } else if (str instanceof byte[]) {
                sQLiteProgram.bindBlob(i + 1, (byte[]) str);
            } else {
                throw Assert.fail("Unknown argument %s of type %s", str, str.getClass());
            }
        }
    }
}
