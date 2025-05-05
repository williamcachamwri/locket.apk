package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.util.Assert;
import java.util.HashSet;
import java.util.Set;

public class SQLiteOverlayMigrationManager implements OverlayMigrationManager {
    private final SQLitePersistence db;

    public SQLiteOverlayMigrationManager(SQLitePersistence sQLitePersistence) {
        this.db = sQLitePersistence;
    }

    public void run() {
        buildOverlays();
    }

    private void buildOverlays() {
        this.db.runTransaction("build overlays", (Runnable) new SQLiteOverlayMigrationManager$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$buildOverlays$0$com-google-firebase-firestore-local-SQLiteOverlayMigrationManager  reason: not valid java name */
    public /* synthetic */ void m718lambda$buildOverlays$0$comgooglefirebasefirestorelocalSQLiteOverlayMigrationManager() {
        if (hasPendingOverlayMigration()) {
            Set<String> allUserIds = getAllUserIds();
            RemoteDocumentCache remoteDocumentCache = this.db.getRemoteDocumentCache();
            for (String user : allUserIds) {
                User user2 = new User(user);
                SQLitePersistence sQLitePersistence = this.db;
                MutationQueue mutationQueue = sQLitePersistence.getMutationQueue(user2, sQLitePersistence.getIndexManager(user2));
                HashSet hashSet = new HashSet();
                for (MutationBatch keys : mutationQueue.getAllMutationBatches()) {
                    hashSet.addAll(keys.getKeys());
                }
                new LocalDocumentsView(remoteDocumentCache, mutationQueue, this.db.getDocumentOverlayCache(user2), this.db.getIndexManager(user2)).recalculateAndSaveOverlays((Set<DocumentKey>) hashSet);
            }
            removePendingOverlayMigrations();
        }
    }

    private Set<String> getAllUserIds() {
        HashSet hashSet = new HashSet();
        this.db.query("SELECT DISTINCT uid FROM mutation_queues").forEach(new SQLiteOverlayMigrationManager$$ExternalSyntheticLambda2(hashSet));
        return hashSet;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPendingOverlayMigration() {
        Boolean[] boolArr = {false};
        this.db.query("SELECT migration_name FROM data_migrations").forEach(new SQLiteOverlayMigrationManager$$ExternalSyntheticLambda1(boolArr));
        return boolArr[0].booleanValue();
    }

    static /* synthetic */ void lambda$hasPendingOverlayMigration$2(Boolean[] boolArr, Cursor cursor) {
        try {
            if (Persistence.DATA_MIGRATION_BUILD_OVERLAYS.equals(cursor.getString(0))) {
                boolArr[0] = true;
            }
        } catch (IllegalArgumentException e) {
            throw Assert.fail("SQLitePersistence.DataMigration failed to parse: %s", e);
        }
    }

    private void removePendingOverlayMigrations() {
        this.db.execute("DELETE FROM data_migrations WHERE migration_name = ?", Persistence.DATA_MIGRATION_BUILD_OVERLAYS);
    }
}
