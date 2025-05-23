package com.google.firebase.firestore.core;

import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalSerializer;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.local.Scheduler;

public class SQLiteComponentProvider extends MemoryComponentProvider {
    public SQLiteComponentProvider(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        super(firebaseFirestoreSettings);
    }

    /* access modifiers changed from: protected */
    public Scheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return ((SQLitePersistence) getPersistence()).getReferenceDelegate().getGarbageCollector().newScheduler(configuration.asyncQueue, getLocalStore());
    }

    /* access modifiers changed from: protected */
    public IndexBackfiller createIndexBackfiller(ComponentProvider.Configuration configuration) {
        return new IndexBackfiller(getPersistence(), configuration.asyncQueue, getLocalStore());
    }

    /* access modifiers changed from: protected */
    public Persistence createPersistence(ComponentProvider.Configuration configuration) {
        return new SQLitePersistence(configuration.context, configuration.databaseInfo.getPersistenceKey(), configuration.databaseInfo.getDatabaseId(), new LocalSerializer(getRemoteSerializer()), LruGarbageCollector.Params.WithCacheSizeBytes(this.settings.getCacheSizeBytes()));
    }
}
