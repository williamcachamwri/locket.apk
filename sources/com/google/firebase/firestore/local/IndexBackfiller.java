package com.google.firebase.firestore.local;

import com.google.common.base.Supplier;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IndexBackfiller {
    /* access modifiers changed from: private */
    public static final long INITIAL_BACKFILL_DELAY_MS = TimeUnit.SECONDS.toMillis(15);
    private static final String LOG_TAG = "IndexBackfiller";
    private static final int MAX_DOCUMENTS_TO_PROCESS = 50;
    /* access modifiers changed from: private */
    public static final long REGULAR_BACKFILL_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    private final Supplier<IndexManager> indexManagerOfCurrentUser;
    private final Supplier<LocalDocumentsView> localDocumentsViewOfCurrentUser;
    private int maxDocumentsToProcess;
    private final Persistence persistence;
    private final Scheduler scheduler;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public IndexBackfiller(com.google.firebase.firestore.local.Persistence r3, com.google.firebase.firestore.util.AsyncQueue r4, com.google.firebase.firestore.local.LocalStore r5) {
        /*
            r2 = this;
            java.util.Objects.requireNonNull(r5)
            com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda1 r0 = new com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda1
            r0.<init>(r5)
            java.util.Objects.requireNonNull(r5)
            com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda2 r1 = new com.google.firebase.firestore.local.IndexBackfiller$$ExternalSyntheticLambda2
            r1.<init>(r5)
            r2.<init>(r3, r4, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.IndexBackfiller.<init>(com.google.firebase.firestore.local.Persistence, com.google.firebase.firestore.util.AsyncQueue, com.google.firebase.firestore.local.LocalStore):void");
    }

    public IndexBackfiller(Persistence persistence2, AsyncQueue asyncQueue, Supplier<IndexManager> supplier, Supplier<LocalDocumentsView> supplier2) {
        this.maxDocumentsToProcess = 50;
        this.persistence = persistence2;
        this.scheduler = new Scheduler(asyncQueue);
        this.indexManagerOfCurrentUser = supplier;
        this.localDocumentsViewOfCurrentUser = supplier2;
    }

    public class Scheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask backfillTask;

        public Scheduler(AsyncQueue asyncQueue2) {
            this.asyncQueue = asyncQueue2;
        }

        public void start() {
            scheduleBackfill(IndexBackfiller.INITIAL_BACKFILL_DELAY_MS);
        }

        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.backfillTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleBackfill(long j) {
            this.backfillTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.INDEX_BACKFILL, j, new IndexBackfiller$Scheduler$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$scheduleBackfill$0$com-google-firebase-firestore-local-IndexBackfiller$Scheduler  reason: not valid java name */
        public /* synthetic */ void m678lambda$scheduleBackfill$0$comgooglefirebasefirestorelocalIndexBackfiller$Scheduler() {
            Logger.debug(IndexBackfiller.LOG_TAG, "Documents written: %s", Integer.valueOf(IndexBackfiller.this.backfill()));
            scheduleBackfill(IndexBackfiller.REGULAR_BACKFILL_DELAY_MS);
        }
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public int backfill() {
        return ((Integer) this.persistence.runTransaction("Backfill Indexes", new IndexBackfiller$$ExternalSyntheticLambda0(this))).intValue();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$backfill$0$com-google-firebase-firestore-local-IndexBackfiller  reason: not valid java name */
    public /* synthetic */ Integer m677lambda$backfill$0$comgooglefirebasefirestorelocalIndexBackfiller() {
        return Integer.valueOf(writeIndexEntries());
    }

    private int writeIndexEntries() {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        HashSet hashSet = new HashSet();
        int i = this.maxDocumentsToProcess;
        while (i > 0) {
            String nextCollectionGroupToUpdate = indexManager.getNextCollectionGroupToUpdate();
            if (nextCollectionGroupToUpdate == null || hashSet.contains(nextCollectionGroupToUpdate)) {
                break;
            }
            Logger.debug(LOG_TAG, "Processing collection: %s", nextCollectionGroupToUpdate);
            i -= writeEntriesForCollectionGroup(nextCollectionGroupToUpdate, i);
            hashSet.add(nextCollectionGroupToUpdate);
        }
        return this.maxDocumentsToProcess - i;
    }

    private int writeEntriesForCollectionGroup(String str, int i) {
        IndexManager indexManager = this.indexManagerOfCurrentUser.get();
        FieldIndex.IndexOffset minOffset = indexManager.getMinOffset(str);
        LocalDocumentsResult nextDocuments = this.localDocumentsViewOfCurrentUser.get().getNextDocuments(str, minOffset, i);
        indexManager.updateIndexEntries(nextDocuments.getDocuments());
        FieldIndex.IndexOffset newOffset = getNewOffset(minOffset, nextDocuments);
        Logger.debug(LOG_TAG, "Updating offset: %s", newOffset);
        indexManager.updateCollectionGroup(str, newOffset);
        return nextDocuments.getDocuments().size();
    }

    private FieldIndex.IndexOffset getNewOffset(FieldIndex.IndexOffset indexOffset, LocalDocumentsResult localDocumentsResult) {
        Iterator<Map.Entry<DocumentKey, Document>> it = localDocumentsResult.getDocuments().iterator();
        FieldIndex.IndexOffset indexOffset2 = indexOffset;
        while (it.hasNext()) {
            FieldIndex.IndexOffset fromDocument = FieldIndex.IndexOffset.fromDocument((Document) it.next().getValue());
            if (fromDocument.compareTo(indexOffset2) > 0) {
                indexOffset2 = fromDocument;
            }
        }
        return FieldIndex.IndexOffset.create(indexOffset2.getReadTime(), indexOffset2.getDocumentKey(), Math.max(localDocumentsResult.getBatchId(), indexOffset.getLargestBatchId()));
    }

    /* access modifiers changed from: package-private */
    public void setMaxDocumentsToProcess(int i) {
        this.maxDocumentsToProcess = i;
    }
}
