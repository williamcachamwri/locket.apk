package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.facebook.cache.common.HasDebugData;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;

public class LruCountingMemoryCache<K, V> implements CountingMemoryCache<K, V>, MemoryCache<K, V>, HasDebugData {
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mCachedEntries;
    @Nullable
    private final CountingMemoryCache.EntryStateObserver<K> mEntryStateObserver;
    final CountingLruMap<K, CountingMemoryCache.Entry<K, V>> mExclusiveEntries;
    private final boolean mIgnoreSizeMismatch;
    private long mLastCacheParamsCheck;
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    final Map<Bitmap, Object> mOtherEntries = new WeakHashMap();
    /* access modifiers changed from: private */
    public final boolean mStoreEntrySize;
    private final ValueDescriptor<V> mValueDescriptor;

    public LruCountingMemoryCache(ValueDescriptor<V> valueDescriptor, MemoryCache.CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> supplier, @Nullable CountingMemoryCache.EntryStateObserver<K> entryStateObserver, boolean z, boolean z2) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap<>(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = supplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(supplier.get(), "mMemoryCacheParamsSupplier returned null");
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mEntryStateObserver = entryStateObserver;
        this.mStoreEntrySize = z;
        this.mIgnoreSizeMismatch = z2;
    }

    private ValueDescriptor<CountingMemoryCache.Entry<K, V>> wrapValueDescriptor(final ValueDescriptor<V> valueDescriptor) {
        return new ValueDescriptor<CountingMemoryCache.Entry<K, V>>() {
            public int getSizeInBytes(CountingMemoryCache.Entry<K, V> entry) {
                if (LruCountingMemoryCache.this.mStoreEntrySize) {
                    return entry.size;
                }
                return valueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    @Nullable
    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference) {
        return cache(k, closeableReference, this.mEntryStateObserver);
    }

    @Nullable
    public CloseableReference<V> cache(K k, CloseableReference<V> closeableReference, @Nullable CountingMemoryCache.EntryStateObserver<K> entryStateObserver) {
        CountingMemoryCache.Entry remove;
        CloseableReference<V> closeableReference2;
        CloseableReference closeableReference3;
        CountingMemoryCache.Entry<K, V> entry;
        Preconditions.checkNotNull(k);
        Preconditions.checkNotNull(closeableReference);
        maybeUpdateCacheParams();
        synchronized (this) {
            remove = this.mExclusiveEntries.remove(k);
            CountingMemoryCache.Entry remove2 = this.mCachedEntries.remove(k);
            closeableReference2 = null;
            if (remove2 != null) {
                makeOrphan(remove2);
                closeableReference3 = referenceToClose(remove2);
            } else {
                closeableReference3 = null;
            }
            int sizeInBytes = this.mValueDescriptor.getSizeInBytes(closeableReference.get());
            if (canCacheNewValueOfSize(sizeInBytes)) {
                if (this.mStoreEntrySize) {
                    entry = CountingMemoryCache.Entry.of(k, closeableReference, sizeInBytes, entryStateObserver);
                } else {
                    entry = CountingMemoryCache.Entry.of(k, closeableReference, entryStateObserver);
                }
                this.mCachedEntries.put(k, entry);
                closeableReference2 = newClientReference(entry);
            }
        }
        CloseableReference.closeSafely((CloseableReference<?>) closeableReference3);
        maybeNotifyExclusiveEntryRemoval(remove);
        maybeEvictEntries();
        return closeableReference2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (getInUseSizeInBytes() <= (r3.mMemoryCacheParams.maxCacheSize - r4)) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean canCacheNewValueOfSize(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.imagepipeline.cache.MemoryCacheParams r0 = r3.mMemoryCacheParams     // Catch:{ all -> 0x0022 }
            int r0 = r0.maxCacheEntrySize     // Catch:{ all -> 0x0022 }
            if (r4 > r0) goto L_0x001f
            int r0 = r3.getInUseCount()     // Catch:{ all -> 0x0022 }
            com.facebook.imagepipeline.cache.MemoryCacheParams r1 = r3.mMemoryCacheParams     // Catch:{ all -> 0x0022 }
            int r1 = r1.maxCacheEntries     // Catch:{ all -> 0x0022 }
            r2 = 1
            int r1 = r1 - r2
            if (r0 > r1) goto L_0x001f
            int r0 = r3.getInUseSizeInBytes()     // Catch:{ all -> 0x0022 }
            com.facebook.imagepipeline.cache.MemoryCacheParams r1 = r3.mMemoryCacheParams     // Catch:{ all -> 0x0022 }
            int r1 = r1.maxCacheSize     // Catch:{ all -> 0x0022 }
            int r1 = r1 - r4
            if (r0 > r1) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r2 = 0
        L_0x0020:
            monitor-exit(r3)
            return r2
        L_0x0022:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.canCacheNewValueOfSize(int):boolean");
    }

    @Nullable
    public CloseableReference<V> get(K k) {
        CountingMemoryCache.Entry remove;
        CloseableReference<V> newClientReference;
        Preconditions.checkNotNull(k);
        synchronized (this) {
            remove = this.mExclusiveEntries.remove(k);
            CountingMemoryCache.Entry entry = this.mCachedEntries.get(k);
            newClientReference = entry != null ? newClientReference(entry) : null;
        }
        maybeNotifyExclusiveEntryRemoval(remove);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return newClientReference;
    }

    @Nullable
    public synchronized V inspect(K k) {
        CountingMemoryCache.Entry entry = this.mCachedEntries.get(k);
        if (entry == null) {
            return null;
        }
        return entry.valueRef.get();
    }

    public void probe(K k) {
        Preconditions.checkNotNull(k);
        synchronized (this) {
            CountingMemoryCache.Entry remove = this.mExclusiveEntries.remove(k);
            if (remove != null) {
                this.mExclusiveEntries.put(k, remove);
            }
        }
    }

    private synchronized CloseableReference<V> newClientReference(final CountingMemoryCache.Entry<K, V> entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() {
            public void release(V v) {
                LruCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    /* access modifiers changed from: private */
    public void releaseClientReference(CountingMemoryCache.Entry<K, V> entry) {
        boolean maybeAddToExclusives;
        CloseableReference<V> referenceToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            maybeAddToExclusives = maybeAddToExclusives(entry);
            referenceToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely((CloseableReference<?>) referenceToClose);
        if (!maybeAddToExclusives) {
            entry = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean maybeAddToExclusives(com.facebook.imagepipeline.cache.CountingMemoryCache.Entry<K, V> r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r3.isOrphan     // Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0013
            int r0 = r3.clientCount     // Catch:{ all -> 0x0016 }
            if (r0 != 0) goto L_0x0013
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r0 = r2.mExclusiveEntries     // Catch:{ all -> 0x0016 }
            K r1 = r3.key     // Catch:{ all -> 0x0016 }
            r0.put(r1, r3)     // Catch:{ all -> 0x0016 }
            monitor-exit(r2)
            r3 = 1
            return r3
        L_0x0013:
            monitor-exit(r2)
            r3 = 0
            return r3
        L_0x0016:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.maybeAddToExclusives(com.facebook.imagepipeline.cache.CountingMemoryCache$Entry):boolean");
    }

    @Nullable
    public CloseableReference<V> reuse(K k) {
        CountingMemoryCache.Entry remove;
        boolean z;
        CloseableReference<V> closeableReference;
        Preconditions.checkNotNull(k);
        synchronized (this) {
            remove = this.mExclusiveEntries.remove(k);
            z = false;
            if (remove != null) {
                CountingMemoryCache.Entry remove2 = this.mCachedEntries.remove(k);
                Preconditions.checkNotNull(remove2);
                if (remove2.clientCount == 0) {
                    z = true;
                }
                Preconditions.checkState(z);
                closeableReference = remove2.valueRef;
                z = true;
            } else {
                closeableReference = null;
            }
        }
        if (z) {
            maybeNotifyExclusiveEntryRemoval(remove);
        }
        return closeableReference;
    }

    public int removeAll(Predicate<K> predicate) {
        ArrayList<CountingMemoryCache.Entry<K, V>> removeAll;
        ArrayList<CountingMemoryCache.Entry<K, V>> removeAll2;
        synchronized (this) {
            removeAll = this.mExclusiveEntries.removeAll(predicate);
            removeAll2 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(removeAll2);
        }
        maybeClose(removeAll2);
        maybeNotifyExclusiveEntryRemoval(removeAll);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return removeAll2.size();
    }

    public void clear() {
        ArrayList<CountingMemoryCache.Entry<K, V>> clear;
        ArrayList<CountingMemoryCache.Entry<K, V>> clear2;
        synchronized (this) {
            clear = this.mExclusiveEntries.clear();
            clear2 = this.mCachedEntries.clear();
            makeOrphans(clear2);
        }
        maybeClose(clear2);
        maybeNotifyExclusiveEntryRemoval(clear);
        maybeUpdateCacheParams();
    }

    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    public synchronized boolean contains(K k) {
        return this.mCachedEntries.contains(k);
    }

    public void trim(MemoryTrimType memoryTrimType) {
        ArrayList trimExclusivelyOwnedEntries;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(memoryTrimType);
        synchronized (this) {
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, Math.max(0, ((int) (((double) this.mCachedEntries.getSizeInBytes()) * (1.0d - trimRatio))) - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs <= SystemClock.uptimeMillis()) {
            this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
            this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull(this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
        }
    }

    public MemoryCacheParams getMemoryCacheParams() {
        return this.mMemoryCacheParams;
    }

    public CountingLruMap<K, CountingMemoryCache.Entry<K, V>> getCachedEntries() {
        return this.mCachedEntries;
    }

    public Map<Bitmap, Object> getOtherEntries() {
        return this.mOtherEntries;
    }

    public void maybeEvictEntries() {
        ArrayList trimExclusivelyOwnedEntries;
        synchronized (this) {
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount()), Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r4.mIgnoreSizeMismatch == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r4.mExclusiveEntries.resetSize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006c, code lost:
        throw new java.lang.IllegalStateException(java.lang.String.format("key is null, but exclusiveEntries count: %d, size: %d", new java.lang.Object[]{java.lang.Integer.valueOf(r4.mExclusiveEntries.getCount()), java.lang.Integer.valueOf(r4.mExclusiveEntries.getSizeInBytes())}));
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.ArrayList<com.facebook.imagepipeline.cache.CountingMemoryCache.Entry<K, V>> trimExclusivelyOwnedEntries(int r5, int r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            int r5 = java.lang.Math.max(r5, r0)     // Catch:{ all -> 0x007e }
            int r6 = java.lang.Math.max(r6, r0)     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r1 = r1.getCount()     // Catch:{ all -> 0x007e }
            if (r1 > r5) goto L_0x001d
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r1 = r1.getSizeInBytes()     // Catch:{ all -> 0x007e }
            if (r1 > r6) goto L_0x001d
            monitor-exit(r4)
            r5 = 0
            return r5
        L_0x001d:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x007e }
            r1.<init>()     // Catch:{ all -> 0x007e }
        L_0x0022:
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r2 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r2 = r2.getCount()     // Catch:{ all -> 0x007e }
            if (r2 > r5) goto L_0x0032
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r2 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r2 = r2.getSizeInBytes()     // Catch:{ all -> 0x007e }
            if (r2 <= r6) goto L_0x0043
        L_0x0032:
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r2 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            java.lang.Object r2 = r2.getFirstKey()     // Catch:{ all -> 0x007e }
            if (r2 != 0) goto L_0x006d
            boolean r5 = r4.mIgnoreSizeMismatch     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0045
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r5 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            r5.resetSize()     // Catch:{ all -> 0x007e }
        L_0x0043:
            monitor-exit(r4)
            return r1
        L_0x0045:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x007e }
            java.lang.String r6 = "key is null, but exclusiveEntries count: %d, size: %d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r2 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r2 = r2.getCount()     // Catch:{ all -> 0x007e }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x007e }
            r1[r0] = r2     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r0 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            int r0 = r0.getSizeInBytes()     // Catch:{ all -> 0x007e }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x007e }
            r2 = 1
            r1[r2] = r0     // Catch:{ all -> 0x007e }
            java.lang.String r6 = java.lang.String.format(r6, r1)     // Catch:{ all -> 0x007e }
            r5.<init>(r6)     // Catch:{ all -> 0x007e }
            throw r5     // Catch:{ all -> 0x007e }
        L_0x006d:
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r3 = r4.mExclusiveEntries     // Catch:{ all -> 0x007e }
            r3.remove(r2)     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r3 = r4.mCachedEntries     // Catch:{ all -> 0x007e }
            java.lang.Object r2 = r3.remove(r2)     // Catch:{ all -> 0x007e }
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r2 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r2     // Catch:{ all -> 0x007e }
            r1.add(r2)     // Catch:{ all -> 0x007e }
            goto L_0x0022
        L_0x007e:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.trimExclusivelyOwnedEntries(int, int):java.util.ArrayList");
    }

    private void maybeClose(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                CloseableReference.closeSafely((CloseableReference<?>) referenceToClose(it.next()));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval(it.next());
            }
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, false);
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable CountingMemoryCache.Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, true);
        }
    }

    private synchronized void makeOrphans(@Nullable ArrayList<CountingMemoryCache.Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<CountingMemoryCache.Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                makeOrphan(it.next());
            }
        }
    }

    private synchronized void makeOrphan(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    private synchronized void increaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void decreaseClientCount(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(CountingMemoryCache.Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        return (!entry.isOrphan || entry.clientCount != 0) ? null : entry.valueRef;
    }

    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    public synchronized int getEvictionQueueCount() {
        return this.mExclusiveEntries.getCount();
    }

    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mExclusiveEntries.getSizeInBytes();
    }

    @Nullable
    public synchronized String getDebugData() {
        return Objects.toStringHelper("CountingMemoryCache").add("cached_entries_count", this.mCachedEntries.getCount()).add("cached_entries_size_bytes", this.mCachedEntries.getSizeInBytes()).add("exclusive_entries_count", this.mExclusiveEntries.getCount()).add("exclusive_entries_size_bytes", this.mExclusiveEntries.getSizeInBytes()).toString();
    }
}
