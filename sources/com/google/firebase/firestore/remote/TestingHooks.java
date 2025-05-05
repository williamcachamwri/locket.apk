package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.remote.WatchChangeAggregator;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.BloomFilter;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

final class TestingHooks {
    private static final TestingHooks instance = new TestingHooks();
    private final CopyOnWriteArrayList<AtomicReference<ExistenceFilterMismatchListener>> existenceFilterMismatchListeners = new CopyOnWriteArrayList<>();

    interface ExistenceFilterMismatchListener {
        void onExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo);
    }

    private TestingHooks() {
    }

    static TestingHooks getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void notifyOnExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo) {
        Iterator<AtomicReference<ExistenceFilterMismatchListener>> it = this.existenceFilterMismatchListeners.iterator();
        while (it.hasNext()) {
            ExistenceFilterMismatchListener existenceFilterMismatchListener = (ExistenceFilterMismatchListener) it.next().get();
            if (existenceFilterMismatchListener != null) {
                existenceFilterMismatchListener.onExistenceFilterMismatch(existenceFilterMismatchInfo);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ListenerRegistration addExistenceFilterMismatchListener(ExistenceFilterMismatchListener existenceFilterMismatchListener) {
        Preconditions.checkNotNull(existenceFilterMismatchListener, "a null listener is not allowed");
        AtomicReference atomicReference = new AtomicReference(existenceFilterMismatchListener);
        this.existenceFilterMismatchListeners.add(atomicReference);
        return new TestingHooks$$ExternalSyntheticLambda0(this, atomicReference);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addExistenceFilterMismatchListener$0$com-google-firebase-firestore-remote-TestingHooks  reason: not valid java name */
    public /* synthetic */ void m763lambda$addExistenceFilterMismatchListener$0$comgooglefirebasefirestoreremoteTestingHooks(AtomicReference atomicReference) {
        atomicReference.set((Object) null);
        this.existenceFilterMismatchListeners.remove(atomicReference);
    }

    static abstract class ExistenceFilterMismatchInfo {
        /* access modifiers changed from: package-private */
        public abstract ExistenceFilterBloomFilterInfo bloomFilter();

        /* access modifiers changed from: package-private */
        public abstract String databaseId();

        /* access modifiers changed from: package-private */
        public abstract int existenceFilterCount();

        /* access modifiers changed from: package-private */
        public abstract int localCacheCount();

        /* access modifiers changed from: package-private */
        public abstract String projectId();

        ExistenceFilterMismatchInfo() {
        }

        static ExistenceFilterMismatchInfo create(int i, int i2, String str, String str2, ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
            return new AutoValue_TestingHooks_ExistenceFilterMismatchInfo(i, i2, str, str2, existenceFilterBloomFilterInfo);
        }

        static ExistenceFilterMismatchInfo from(int i, ExistenceFilter existenceFilter, DatabaseId databaseId, BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus) {
            return create(i, existenceFilter.getCount(), databaseId.getProjectId(), databaseId.getDatabaseId(), ExistenceFilterBloomFilterInfo.from(bloomFilter, bloomFilterApplicationStatus, existenceFilter));
        }
    }

    static abstract class ExistenceFilterBloomFilterInfo {
        /* access modifiers changed from: package-private */
        public abstract boolean applied();

        /* access modifiers changed from: package-private */
        public abstract int bitmapLength();

        /* access modifiers changed from: package-private */
        public abstract BloomFilter bloomFilter();

        /* access modifiers changed from: package-private */
        public abstract int hashCount();

        /* access modifiers changed from: package-private */
        public abstract int padding();

        ExistenceFilterBloomFilterInfo() {
        }

        static ExistenceFilterBloomFilterInfo create(BloomFilter bloomFilter, boolean z, int i, int i2, int i3) {
            return new AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(bloomFilter, z, i, i2, i3);
        }

        static ExistenceFilterBloomFilterInfo from(BloomFilter bloomFilter, WatchChangeAggregator.BloomFilterApplicationStatus bloomFilterApplicationStatus, ExistenceFilter existenceFilter) {
            BloomFilter unchangedNames = existenceFilter.getUnchangedNames();
            if (unchangedNames == null) {
                return null;
            }
            return create(bloomFilter, bloomFilterApplicationStatus == WatchChangeAggregator.BloomFilterApplicationStatus.SUCCESS, unchangedNames.getHashCount(), unchangedNames.getBits().getBitmap().size(), unchangedNames.getBits().getPadding());
        }
    }
}
