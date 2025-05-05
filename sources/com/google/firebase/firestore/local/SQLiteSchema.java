package com.google.firebase.firestore.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import com.amplitude.api.AmplitudeClient;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.proto.Target;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.List;

class SQLiteSchema {
    static final int MIGRATION_BATCH_SIZE = 100;
    static final int VERSION = 17;
    private final SQLiteDatabase db;
    private final LocalSerializer serializer;

    SQLiteSchema(SQLiteDatabase sQLiteDatabase, LocalSerializer localSerializer) {
        this.db = sQLiteDatabase;
        this.serializer = localSerializer;
    }

    /* access modifiers changed from: package-private */
    public void runSchemaUpgrades() {
        runSchemaUpgrades(0);
    }

    /* access modifiers changed from: package-private */
    public void runSchemaUpgrades(int i) {
        runSchemaUpgrades(i, 17);
    }

    /* access modifiers changed from: package-private */
    public void runSchemaUpgrades(int i, int i2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (i < 1 && i2 >= 1) {
            createV1MutationQueue();
            createV1TargetCache();
            createV1RemoteDocumentCache();
        }
        if (i < 3 && i2 >= 3 && i != 0) {
            dropV1TargetCache();
            createV1TargetCache();
        }
        if (i < 4 && i2 >= 4) {
            ensureTargetGlobal();
            addTargetCount();
        }
        if (i < 5 && i2 >= 5) {
            addSequenceNumber();
        }
        if (i < 6 && i2 >= 6) {
            removeAcknowledgedMutations();
        }
        if (i < 7 && i2 >= 7) {
            ensureSequenceNumbers();
        }
        if (i < 8 && i2 >= 8) {
            createV8CollectionParentsIndex();
        }
        if (i < 9 && i2 >= 9) {
            if (!hasReadTime()) {
                addReadTime();
            } else {
                dropLastLimboFreeSnapshotVersion();
            }
        }
        if (i == 9 && i2 >= 10) {
            dropLastLimboFreeSnapshotVersion();
        }
        if (i < 11 && i2 >= 11) {
            rewriteCanonicalIds();
        }
        if (i < 12 && i2 >= 12) {
            createBundleCache();
        }
        if (i < 13 && i2 >= 13) {
            addPathLength();
            ensurePathLength();
        }
        if (i < 14 && i2 >= 14) {
            createOverlays();
            createDataMigrationTable();
            addPendingDataMigration(Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
        }
        if (i < 15 && i2 >= 15) {
            ensureReadTime();
        }
        if (i < 16 && i2 >= 16) {
            createFieldIndex();
        }
        if (i < 17 && i2 >= 17) {
            createGlobalsTable();
        }
        Logger.debug("SQLiteSchema", "Migration from version %s to %s took %s milliseconds", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void ifTablesDontExist(String[] strArr, Runnable runnable) {
        String str;
        String str2 = "[" + TextUtils.join(", ", strArr) + "]";
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            String str3 = strArr[i];
            boolean tableExists = tableExists(str3);
            if (i == 0) {
                z = tableExists;
            } else if (tableExists != z) {
                String str4 = "Expected all of " + str2 + " to either exist or not, but ";
                if (z) {
                    str = str4 + strArr[0] + " exists and " + str3 + " does not";
                } else {
                    str = str4 + strArr[0] + " does not exist and " + str3 + " does";
                }
                throw new IllegalStateException(str);
            }
        }
        if (!z) {
            runnable.run();
        } else {
            Logger.debug("SQLiteSchema", "Skipping migration because all of " + str2 + " already exist", new Object[0]);
        }
    }

    private void createV1MutationQueue() {
        ifTablesDontExist(new String[]{"mutation_queues", "mutations", "document_mutations"}, new SQLiteSchema$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createV1MutationQueue$0$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m727lambda$createV1MutationQueue$0$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE mutation_queues (uid TEXT PRIMARY KEY, last_acknowledged_batch_id INTEGER, last_stream_token BLOB)");
        this.db.execSQL("CREATE TABLE mutations (uid TEXT, batch_id INTEGER, mutations BLOB, PRIMARY KEY (uid, batch_id))");
        this.db.execSQL("CREATE TABLE document_mutations (uid TEXT, path TEXT, batch_id INTEGER, PRIMARY KEY (uid, path, batch_id))");
    }

    private void removeAcknowledgedMutations() {
        new SQLitePersistence.Query(this.db, "SELECT uid, last_acknowledged_batch_id FROM mutation_queues").forEach(new SQLiteSchema$$ExternalSyntheticLambda16(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeAcknowledgedMutations$2$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m733lambda$removeAcknowledgedMutations$2$comgooglefirebasefirestorelocalSQLiteSchema(Cursor cursor) {
        String string = cursor.getString(0);
        new SQLitePersistence.Query(this.db, "SELECT batch_id FROM mutations WHERE uid = ? AND batch_id <= ?").binding(string, Long.valueOf(cursor.getLong(1))).forEach(new SQLiteSchema$$ExternalSyntheticLambda0(this, string));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeAcknowledgedMutations$1$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m732lambda$removeAcknowledgedMutations$1$comgooglefirebasefirestorelocalSQLiteSchema(String str, Cursor cursor) {
        removeMutationBatch(str, cursor.getInt(0));
    }

    private void removeMutationBatch(String str, int i) {
        SQLiteStatement compileStatement = this.db.compileStatement("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        boolean z = true;
        compileStatement.bindString(1, str);
        compileStatement.bindLong(2, (long) i);
        if (compileStatement.executeUpdateDelete() == 0) {
            z = false;
        }
        Assert.hardAssert(z, "Mutation batch (%s, %d) did not exist", str, Integer.valueOf(i));
        this.db.execSQL("DELETE FROM document_mutations WHERE uid = ? AND batch_id = ?", new Object[]{str, Integer.valueOf(i)});
    }

    private void createV1TargetCache() {
        ifTablesDontExist(new String[]{"targets", "target_globals", "target_documents"}, new SQLiteSchema$$ExternalSyntheticLambda10(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createV1TargetCache$3$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m729lambda$createV1TargetCache$3$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE targets (target_id INTEGER PRIMARY KEY, canonical_id TEXT, snapshot_version_seconds INTEGER, snapshot_version_nanos INTEGER, resume_token BLOB, last_listen_sequence_number INTEGER,target_proto BLOB)");
        this.db.execSQL("CREATE INDEX query_targets ON targets (canonical_id, target_id)");
        this.db.execSQL("CREATE TABLE target_globals (highest_target_id INTEGER, highest_listen_sequence_number INTEGER, last_remote_snapshot_version_seconds INTEGER, last_remote_snapshot_version_nanos INTEGER)");
        this.db.execSQL("CREATE TABLE target_documents (target_id INTEGER, path TEXT, PRIMARY KEY (target_id, path))");
        this.db.execSQL("CREATE INDEX document_targets ON target_documents (path, target_id)");
    }

    private void dropV1TargetCache() {
        if (tableExists("targets")) {
            this.db.execSQL("DROP TABLE targets");
        }
        if (tableExists("target_globals")) {
            this.db.execSQL("DROP TABLE target_globals");
        }
        if (tableExists("target_documents")) {
            this.db.execSQL("DROP TABLE target_documents");
        }
    }

    private void createV1RemoteDocumentCache() {
        ifTablesDontExist(new String[]{"remote_documents"}, new SQLiteSchema$$ExternalSyntheticLambda15(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createV1RemoteDocumentCache$4$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m728lambda$createV1RemoteDocumentCache$4$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE remote_documents (path TEXT PRIMARY KEY, contents BLOB)");
    }

    private void createFieldIndex() {
        ifTablesDontExist(new String[]{"index_configuration", "index_state", "index_entries"}, new SQLiteSchema$$ExternalSyntheticLambda14(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createFieldIndex$5$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m724lambda$createFieldIndex$5$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE index_configuration (index_id INTEGER, collection_group TEXT, index_proto BLOB, PRIMARY KEY (index_id))");
        this.db.execSQL("CREATE TABLE index_state (index_id INTEGER, uid TEXT, sequence_number INTEGER, read_time_seconds INTEGER, read_time_nanos INTEGER, document_key TEXT, largest_batch_id INTEGER, PRIMARY KEY (index_id, uid))");
        this.db.execSQL("CREATE TABLE index_entries (index_id INTEGER, uid TEXT, array_value BLOB, directional_value BLOB, document_key TEXT, PRIMARY KEY (index_id, uid, array_value, directional_value, document_key))");
        this.db.execSQL("CREATE INDEX read_time ON remote_documents(read_time_seconds, read_time_nanos)");
    }

    private void ensureTargetGlobal() {
        if (!(DatabaseUtils.queryNumEntries(this.db, "target_globals") == 1)) {
            this.db.execSQL("INSERT INTO target_globals (highest_target_id, highest_listen_sequence_number, last_remote_snapshot_version_seconds, last_remote_snapshot_version_nanos) VALUES (?, ?, ?, ?)", new String[]{"0", "0", "0", "0"});
        }
    }

    private void addTargetCount() {
        if (!tableContainsColumn("target_globals", "target_count")) {
            this.db.execSQL("ALTER TABLE target_globals ADD COLUMN target_count INTEGER");
        }
        long queryNumEntries = DatabaseUtils.queryNumEntries(this.db, "targets");
        ContentValues contentValues = new ContentValues();
        contentValues.put("target_count", Long.valueOf(queryNumEntries));
        this.db.update("target_globals", contentValues, (String) null, (String[]) null);
    }

    private void addSequenceNumber() {
        if (!tableContainsColumn("target_documents", AmplitudeClient.SEQUENCE_NUMBER_KEY)) {
            this.db.execSQL("ALTER TABLE target_documents ADD COLUMN sequence_number INTEGER");
        }
    }

    private void addPathLength() {
        if (!tableContainsColumn("remote_documents", "path_length")) {
            this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN path_length INTEGER");
        }
    }

    private boolean hasReadTime() {
        boolean tableContainsColumn = tableContainsColumn("remote_documents", "read_time_seconds");
        boolean tableContainsColumn2 = tableContainsColumn("remote_documents", "read_time_nanos");
        Assert.hardAssert(tableContainsColumn == tableContainsColumn2, "Table contained just one of read_time_seconds or read_time_nanos", new Object[0]);
        if (!tableContainsColumn || !tableContainsColumn2) {
            return false;
        }
        return true;
    }

    private void addReadTime() {
        this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN read_time_seconds INTEGER");
        this.db.execSQL("ALTER TABLE remote_documents ADD COLUMN read_time_nanos INTEGER");
    }

    private void dropLastLimboFreeSnapshotVersion() {
        new SQLitePersistence.Query(this.db, "SELECT target_id, target_proto FROM targets").forEach(new SQLiteSchema$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dropLastLimboFreeSnapshotVersion$6$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m731lambda$dropLastLimboFreeSnapshotVersion$6$comgooglefirebasefirestorelocalSQLiteSchema(Cursor cursor) {
        int i = cursor.getInt(0);
        try {
            this.db.execSQL("UPDATE targets SET target_proto = ? WHERE target_id = ?", new Object[]{((Target) ((Target.Builder) Target.parseFrom(cursor.getBlob(1)).toBuilder()).clearLastLimboFreeSnapshotVersion().build()).toByteArray(), Integer.valueOf(i)});
        } catch (InvalidProtocolBufferException unused) {
            throw Assert.fail("Failed to decode Query data for target %s", Integer.valueOf(i));
        }
    }

    private void ensureSequenceNumbers() {
        Long l = (Long) new SQLitePersistence.Query(this.db, "SELECT highest_listen_sequence_number FROM target_globals LIMIT 1").firstValue(new SQLiteSchema$$ExternalSyntheticLambda2());
        Assert.hardAssert(l != null, "Missing highest sequence number", new Object[0]);
        long longValue = l.longValue();
        SQLiteStatement compileStatement = this.db.compileStatement("INSERT INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)");
        SQLitePersistence.Query binding = new SQLitePersistence.Query(this.db, "SELECT RD.path FROM remote_documents AS RD WHERE NOT EXISTS (SELECT TD.path FROM target_documents AS TD WHERE RD.path = TD.path AND TD.target_id = 0) LIMIT ?").binding(100);
        boolean[] zArr = new boolean[1];
        do {
            zArr[0] = false;
            binding.forEach(new SQLiteSchema$$ExternalSyntheticLambda3(zArr, compileStatement, longValue));
        } while (zArr[0]);
    }

    static /* synthetic */ void lambda$ensureSequenceNumbers$8(boolean[] zArr, SQLiteStatement sQLiteStatement, long j, Cursor cursor) {
        boolean z = true;
        zArr[0] = true;
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindString(1, cursor.getString(0));
        sQLiteStatement.bindLong(2, j);
        if (sQLiteStatement.executeInsert() == -1) {
            z = false;
        }
        Assert.hardAssert(z, "Failed to insert a sentinel row", new Object[0]);
    }

    private void createV8CollectionParentsIndex() {
        ifTablesDontExist(new String[]{"collection_parents"}, new SQLiteSchema$$ExternalSyntheticLambda5(this));
        SQLiteSchema$$ExternalSyntheticLambda6 sQLiteSchema$$ExternalSyntheticLambda6 = new SQLiteSchema$$ExternalSyntheticLambda6(new MemoryIndexManager.MemoryCollectionParentIndex(), this.db.compileStatement("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)"));
        new SQLitePersistence.Query(this.db, "SELECT path FROM remote_documents").forEach(new SQLiteSchema$$ExternalSyntheticLambda7(sQLiteSchema$$ExternalSyntheticLambda6));
        new SQLitePersistence.Query(this.db, "SELECT path FROM document_mutations").forEach(new SQLiteSchema$$ExternalSyntheticLambda8(sQLiteSchema$$ExternalSyntheticLambda6));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createV8CollectionParentsIndex$9$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m730lambda$createV8CollectionParentsIndex$9$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE collection_parents (collection_id TEXT, parent TEXT, PRIMARY KEY(collection_id, parent))");
    }

    static /* synthetic */ void lambda$createV8CollectionParentsIndex$10(MemoryIndexManager.MemoryCollectionParentIndex memoryCollectionParentIndex, SQLiteStatement sQLiteStatement, ResourcePath resourcePath) {
        if (memoryCollectionParentIndex.add(resourcePath)) {
            String lastSegment = resourcePath.getLastSegment();
            sQLiteStatement.clearBindings();
            sQLiteStatement.bindString(1, lastSegment);
            sQLiteStatement.bindString(2, EncodedPath.encode((ResourcePath) resourcePath.popLast()));
            sQLiteStatement.execute();
        }
    }

    private boolean tableContainsColumn(String str, String str2) {
        return getTableColumns(str).indexOf(str2) != -1;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[], android.database.Cursor] */
    /* access modifiers changed from: package-private */
    public List<String> getTableColumns(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = 0;
        try {
            cursor = this.db.rawQuery("PRAGMA table_info(" + str + ")", cursor);
            int columnIndex = cursor.getColumnIndex("name");
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(columnIndex));
            }
            return arrayList;
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    private void rewriteCanonicalIds() {
        new SQLitePersistence.Query(this.db, "SELECT target_id, target_proto FROM targets").forEach(new SQLiteSchema$$ExternalSyntheticLambda11(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$rewriteCanonicalIds$13$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m734lambda$rewriteCanonicalIds$13$comgooglefirebasefirestorelocalSQLiteSchema(Cursor cursor) {
        int i = cursor.getInt(0);
        try {
            String canonicalId = this.serializer.decodeTargetData(Target.parseFrom(cursor.getBlob(1))).getTarget().getCanonicalId();
            this.db.execSQL("UPDATE targets SET canonical_id  = ? WHERE target_id = ?", new Object[]{canonicalId, Integer.valueOf(i)});
        } catch (InvalidProtocolBufferException unused) {
            throw Assert.fail("Failed to decode Query data for target %s", Integer.valueOf(i));
        }
    }

    private void ensurePathLength() {
        SQLitePersistence.Query binding = new SQLitePersistence.Query(this.db, "SELECT path FROM remote_documents WHERE path_length IS NULL LIMIT ?").binding(100);
        SQLiteStatement compileStatement = this.db.compileStatement("UPDATE remote_documents SET path_length = ? WHERE path = ?");
        boolean[] zArr = new boolean[1];
        do {
            zArr[0] = false;
            binding.forEach(new SQLiteSchema$$ExternalSyntheticLambda9(zArr, compileStatement));
        } while (zArr[0]);
    }

    static /* synthetic */ void lambda$ensurePathLength$14(boolean[] zArr, SQLiteStatement sQLiteStatement, Cursor cursor) {
        boolean z = true;
        zArr[0] = true;
        String string = cursor.getString(0);
        ResourcePath decodeResourcePath = EncodedPath.decodeResourcePath(string);
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, (long) decodeResourcePath.length());
        sQLiteStatement.bindString(2, string);
        if (sQLiteStatement.executeUpdateDelete() == -1) {
            z = false;
        }
        Assert.hardAssert(z, "Failed to update document path", new Object[0]);
    }

    private void ensureReadTime() {
        this.db.execSQL("UPDATE remote_documents SET read_time_seconds = 0, read_time_nanos = 0 WHERE read_time_seconds IS NULL");
    }

    private void createBundleCache() {
        ifTablesDontExist(new String[]{"bundles", "named_queries"}, new SQLiteSchema$$ExternalSyntheticLambda18(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createBundleCache$15$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m722lambda$createBundleCache$15$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE bundles (bundle_id TEXT PRIMARY KEY, create_time_seconds INTEGER, create_time_nanos INTEGER, schema_version INTEGER, total_documents INTEGER, total_bytes INTEGER)");
        this.db.execSQL("CREATE TABLE named_queries (name TEXT PRIMARY KEY, read_time_seconds INTEGER, read_time_nanos INTEGER, bundled_query_proto BLOB)");
    }

    private void createOverlays() {
        ifTablesDontExist(new String[]{"document_overlays"}, new SQLiteSchema$$ExternalSyntheticLambda13(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createOverlays$16$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m726lambda$createOverlays$16$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE document_overlays (uid TEXT, collection_path TEXT, document_id TEXT, collection_group TEXT, largest_batch_id INTEGER, overlay_mutation BLOB, PRIMARY KEY (uid, collection_path, document_id))");
        this.db.execSQL("CREATE INDEX batch_id_overlay ON document_overlays (uid, largest_batch_id)");
        this.db.execSQL("CREATE INDEX collection_group_overlay ON document_overlays (uid, collection_group)");
    }

    private void createDataMigrationTable() {
        ifTablesDontExist(new String[]{"data_migrations"}, new SQLiteSchema$$ExternalSyntheticLambda12(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createDataMigrationTable$17$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m723lambda$createDataMigrationTable$17$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE data_migrations (migration_name TEXT, PRIMARY KEY (migration_name))");
    }

    private void addPendingDataMigration(String str) {
        this.db.execSQL("INSERT OR IGNORE INTO data_migrations (migration_name) VALUES (?)", new String[]{str});
    }

    private void createGlobalsTable() {
        ifTablesDontExist(new String[]{"globals"}, new SQLiteSchema$$ExternalSyntheticLambda17(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createGlobalsTable$18$com-google-firebase-firestore-local-SQLiteSchema  reason: not valid java name */
    public /* synthetic */ void m725lambda$createGlobalsTable$18$comgooglefirebasefirestorelocalSQLiteSchema() {
        this.db.execSQL("CREATE TABLE globals (name TEXT PRIMARY KEY, value BLOB)");
    }

    private boolean tableExists(String str) {
        return !new SQLitePersistence.Query(this.db, "SELECT 1=1 FROM sqlite_master WHERE tbl_name = ?").binding(str).isEmpty();
    }
}
