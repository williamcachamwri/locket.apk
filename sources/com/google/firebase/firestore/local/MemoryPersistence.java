package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Supplier;
import java.util.HashMap;
import java.util.Map;

public final class MemoryPersistence extends Persistence {
    private final MemoryBundleCache bundleCache = new MemoryBundleCache();
    private final MemoryGlobalsCache globalsCache = new MemoryGlobalsCache();
    private final MemoryIndexManager indexManager = new MemoryIndexManager();
    private final Map<User, MemoryMutationQueue> mutationQueues = new HashMap();
    private final Map<User, MemoryDocumentOverlayCache> overlays = new HashMap();
    private ReferenceDelegate referenceDelegate;
    private final MemoryRemoteDocumentCache remoteDocumentCache = new MemoryRemoteDocumentCache();
    private boolean started;
    private final MemoryTargetCache targetCache = new MemoryTargetCache(this);

    public static MemoryPersistence createEagerGcMemoryPersistence() {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.setReferenceDelegate(new MemoryEagerReferenceDelegate(memoryPersistence));
        return memoryPersistence;
    }

    public static MemoryPersistence createLruGcMemoryPersistence(LruGarbageCollector.Params params, LocalSerializer localSerializer) {
        MemoryPersistence memoryPersistence = new MemoryPersistence();
        memoryPersistence.setReferenceDelegate(new MemoryLruReferenceDelegate(memoryPersistence, params, localSerializer));
        return memoryPersistence;
    }

    private MemoryPersistence() {
    }

    public void start() {
        Assert.hardAssert(!this.started, "MemoryPersistence double-started!", new Object[0]);
        this.started = true;
    }

    public void shutdown() {
        Assert.hardAssert(this.started, "MemoryPersistence shutdown without start", new Object[0]);
        this.started = false;
    }

    public boolean isStarted() {
        return this.started;
    }

    public ReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    private void setReferenceDelegate(ReferenceDelegate referenceDelegate2) {
        this.referenceDelegate = referenceDelegate2;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue(User user, IndexManager indexManager2) {
        MemoryMutationQueue memoryMutationQueue = this.mutationQueues.get(user);
        if (memoryMutationQueue != null) {
            return memoryMutationQueue;
        }
        MemoryMutationQueue memoryMutationQueue2 = new MemoryMutationQueue(this, user);
        this.mutationQueues.put(user, memoryMutationQueue2);
        return memoryMutationQueue2;
    }

    /* access modifiers changed from: package-private */
    public Iterable<MemoryMutationQueue> getMutationQueues() {
        return this.mutationQueues.values();
    }

    /* access modifiers changed from: package-private */
    public MemoryTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* access modifiers changed from: package-private */
    public MemoryRemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public GlobalsCache getGlobalsCache() {
        return this.globalsCache;
    }

    /* access modifiers changed from: package-private */
    public MemoryIndexManager getIndexManager(User user) {
        return this.indexManager;
    }

    /* access modifiers changed from: package-private */
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlayCache(User user) {
        MemoryDocumentOverlayCache memoryDocumentOverlayCache = this.overlays.get(user);
        if (memoryDocumentOverlayCache != null) {
            return memoryDocumentOverlayCache;
        }
        MemoryDocumentOverlayCache memoryDocumentOverlayCache2 = new MemoryDocumentOverlayCache();
        this.overlays.put(user, memoryDocumentOverlayCache2);
        return memoryDocumentOverlayCache2;
    }

    /* access modifiers changed from: package-private */
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new MemoryOverlayMigrationManager();
    }

    /* access modifiers changed from: package-private */
    public void runTransaction(String str, Runnable runnable) {
        this.referenceDelegate.onTransactionStarted();
        try {
            runnable.run();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T runTransaction(String str, Supplier<T> supplier) {
        this.referenceDelegate.onTransactionStarted();
        try {
            return supplier.get();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }
}
