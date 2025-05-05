package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.MemoryLruGcSettings;
import com.google.firebase.firestore.core.ComponentProvider;
import com.google.firebase.firestore.local.IndexBackfiller;
import com.google.firebase.firestore.local.LocalSerializer;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.local.MemoryPersistence;
import com.google.firebase.firestore.local.Persistence;
import com.google.firebase.firestore.local.QueryEngine;
import com.google.firebase.firestore.local.Scheduler;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import io.grpc.Status;

public class MemoryComponentProvider extends ComponentProvider {
    /* access modifiers changed from: protected */
    public Scheduler createGarbageCollectionScheduler(ComponentProvider.Configuration configuration) {
        return null;
    }

    /* access modifiers changed from: protected */
    public IndexBackfiller createIndexBackfiller(ComponentProvider.Configuration configuration) {
        return null;
    }

    public MemoryComponentProvider(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        super(firebaseFirestoreSettings);
    }

    /* access modifiers changed from: protected */
    public EventManager createEventManager(ComponentProvider.Configuration configuration) {
        return new EventManager(getSyncEngine());
    }

    /* access modifiers changed from: protected */
    public LocalStore createLocalStore(ComponentProvider.Configuration configuration) {
        return new LocalStore(getPersistence(), new QueryEngine(), configuration.initialUser);
    }

    private boolean isMemoryLruGcEnabled(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        if (firebaseFirestoreSettings.getCacheSettings() == null || !(firebaseFirestoreSettings.getCacheSettings() instanceof MemoryCacheSettings)) {
            return false;
        }
        return ((MemoryCacheSettings) firebaseFirestoreSettings.getCacheSettings()).getGarbageCollectorSettings() instanceof MemoryLruGcSettings;
    }

    /* access modifiers changed from: protected */
    public Persistence createPersistence(ComponentProvider.Configuration configuration) {
        if (!isMemoryLruGcEnabled(this.settings)) {
            return MemoryPersistence.createEagerGcMemoryPersistence();
        }
        return MemoryPersistence.createLruGcMemoryPersistence(LruGarbageCollector.Params.WithCacheSizeBytes(this.settings.getCacheSizeBytes()), new LocalSerializer(getRemoteSerializer()));
    }

    /* access modifiers changed from: protected */
    public RemoteStore createRemoteStore(ComponentProvider.Configuration configuration) {
        return new RemoteStore(configuration.databaseInfo.getDatabaseId(), new RemoteStoreCallback(), getLocalStore(), getDatastore(), configuration.asyncQueue, getConnectivityMonitor());
    }

    /* access modifiers changed from: protected */
    public SyncEngine createSyncEngine(ComponentProvider.Configuration configuration) {
        return new SyncEngine(getLocalStore(), getRemoteStore(), configuration.initialUser, configuration.maxConcurrentLimboResolutions);
    }

    private class RemoteStoreCallback implements RemoteStore.RemoteStoreCallback {
        private RemoteStoreCallback() {
        }

        public void handleRemoteEvent(RemoteEvent remoteEvent) {
            MemoryComponentProvider.this.getSyncEngine().handleRemoteEvent(remoteEvent);
        }

        public void handleRejectedListen(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedListen(i, status);
        }

        public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
            MemoryComponentProvider.this.getSyncEngine().handleSuccessfulWrite(mutationBatchResult);
        }

        public void handleRejectedWrite(int i, Status status) {
            MemoryComponentProvider.this.getSyncEngine().handleRejectedWrite(i, status);
        }

        public void handleOnlineStateChange(OnlineState onlineState) {
            MemoryComponentProvider.this.getSyncEngine().handleOnlineStateChange(onlineState);
        }

        public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
            return MemoryComponentProvider.this.getSyncEngine().getRemoteKeysForTarget(i);
        }
    }
}
