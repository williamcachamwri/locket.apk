package com.facebook.imagepipeline.cache;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import io.sentry.protocol.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0002\t\nB\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/cache/CountingMemoryCacheInspector;", "K", "V", "", "countingBitmapCache", "Lcom/facebook/imagepipeline/cache/CountingMemoryCache;", "(Lcom/facebook/imagepipeline/cache/CountingMemoryCache;)V", "dumpCacheContent", "Lcom/facebook/imagepipeline/cache/CountingMemoryCacheInspector$DumpInfo;", "DumpInfo", "DumpInfoEntry", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CountingMemoryCacheInspector.kt */
public final class CountingMemoryCacheInspector<K, V> {
    private final CountingMemoryCache<K, V> countingBitmapCache;

    public CountingMemoryCacheInspector(CountingMemoryCache<K, V> countingMemoryCache) {
        Intrinsics.checkNotNullParameter(countingMemoryCache, "countingBitmapCache");
        this.countingBitmapCache = countingMemoryCache;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bR\u0012\u0010\u0004\u001a\u00028\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00068\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/cache/CountingMemoryCacheInspector$DumpInfoEntry;", "K", "V", "", "key", "valueRef", "Lcom/facebook/common/references/CloseableReference;", "(Ljava/lang/Object;Lcom/facebook/common/references/CloseableReference;)V", "Ljava/lang/Object;", "value", "release", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CountingMemoryCacheInspector.kt */
    public static final class DumpInfoEntry<K, V> {
        public final K key;
        public final CloseableReference<V> value;

        public DumpInfoEntry(K k, CloseableReference<V> closeableReference) {
            if (k != null) {
                this.key = k;
                this.value = CloseableReference.cloneOrNull(closeableReference);
                return;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        public final void release() {
            CloseableReference.closeSafely((CloseableReference<?>) this.value);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\u0014\u001a\u00020\u0015R\"\u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f0\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u00118\u0006X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\f0\u000b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/imagepipeline/cache/CountingMemoryCacheInspector$DumpInfo;", "K", "V", "", "size", "", "lruSize", "params", "Lcom/facebook/imagepipeline/cache/MemoryCacheParams;", "(IILcom/facebook/imagepipeline/cache/MemoryCacheParams;)V", "lruEntries", "", "Lcom/facebook/imagepipeline/cache/CountingMemoryCacheInspector$DumpInfoEntry;", "maxEntriesCount", "maxEntrySize", "maxSize", "otherEntries", "", "Landroid/graphics/Bitmap;", "sharedEntries", "release", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CountingMemoryCacheInspector.kt */
    public static final class DumpInfo<K, V> {
        public final List<DumpInfoEntry<K, V>> lruEntries = new ArrayList();
        public final int lruSize;
        public final int maxEntriesCount;
        public final int maxEntrySize;
        public final int maxSize;
        public final Map<Bitmap, Object> otherEntries = new HashMap();
        public final List<DumpInfoEntry<K, V>> sharedEntries = new ArrayList();
        public final int size;

        public DumpInfo(int i, int i2, MemoryCacheParams memoryCacheParams) {
            Intrinsics.checkNotNullParameter(memoryCacheParams, Message.JsonKeys.PARAMS);
            this.maxSize = memoryCacheParams.maxCacheSize;
            this.maxEntriesCount = memoryCacheParams.maxCacheEntries;
            this.maxEntrySize = memoryCacheParams.maxCacheEntrySize;
            this.size = i;
            this.lruSize = i2;
        }

        public final void release() {
            for (DumpInfoEntry<K, V> release : this.lruEntries) {
                release.release();
            }
            for (DumpInfoEntry<K, V> release2 : this.sharedEntries) {
                release2.release();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ac, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.facebook.imagepipeline.cache.CountingMemoryCacheInspector.DumpInfo<K, V> dumpCacheContent() {
        /*
            r7 = this;
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r0 = r7.countingBitmapCache
            monitor-enter(r0)
            com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfo r1 = new com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfo     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r2 = r7.countingBitmapCache     // Catch:{ all -> 0x00ad }
            int r2 = r2.getSizeInBytes()     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r3 = r7.countingBitmapCache     // Catch:{ all -> 0x00ad }
            int r3 = r3.getEvictionQueueSizeInBytes()     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r4 = r7.countingBitmapCache     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.MemoryCacheParams r4 = r4.getMemoryCacheParams()     // Catch:{ all -> 0x00ad }
            java.lang.String r5 = "countingBitmapCache.memoryCacheParams"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x00ad }
            r1.<init>(r2, r3, r4)     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r2 = r7.countingBitmapCache     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingLruMap r2 = r2.getCachedEntries()     // Catch:{ all -> 0x00ad }
            if (r2 != 0) goto L_0x0029
            monitor-exit(r0)
            return r1
        L_0x0029:
            java.lang.String r3 = "countingBitmapCache.cach…ntries ?: return dumpInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x00ad }
            r3 = 0
            java.util.ArrayList r2 = r2.getMatchingEntries(r3)     // Catch:{ all -> 0x00ad }
            java.lang.String r3 = "maybeCachedEntries.getMatchingEntries(null)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x00ad }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x00ad }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00ad }
        L_0x003e:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00ad }
            if (r3 == 0) goto L_0x0069
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00ad }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00ad }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r3 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r3     // Catch:{ all -> 0x00ad }
            com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfoEntry r4 = new com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfoEntry     // Catch:{ all -> 0x00ad }
            K r5 = r3.key     // Catch:{ all -> 0x00ad }
            com.facebook.common.references.CloseableReference<V> r6 = r3.valueRef     // Catch:{ all -> 0x00ad }
            r4.<init>(r5, r6)     // Catch:{ all -> 0x00ad }
            int r3 = r3.clientCount     // Catch:{ all -> 0x00ad }
            if (r3 <= 0) goto L_0x0063
            java.util.List<com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfoEntry<K, V>> r3 = r1.sharedEntries     // Catch:{ all -> 0x00ad }
            r3.add(r4)     // Catch:{ all -> 0x00ad }
            goto L_0x003e
        L_0x0063:
            java.util.List<com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfoEntry<K, V>> r3 = r1.lruEntries     // Catch:{ all -> 0x00ad }
            r3.add(r4)     // Catch:{ all -> 0x00ad }
            goto L_0x003e
        L_0x0069:
            com.facebook.imagepipeline.cache.CountingMemoryCache<K, V> r2 = r7.countingBitmapCache     // Catch:{ all -> 0x00ad }
            java.util.Map r2 = r2.getOtherEntries()     // Catch:{ all -> 0x00ad }
            if (r2 == 0) goto L_0x00ab
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x00ad }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00ad }
        L_0x0079:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00ad }
            if (r3 == 0) goto L_0x00ab
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00ad }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00ad }
            if (r3 == 0) goto L_0x0079
            java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x00ad }
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4     // Catch:{ all -> 0x00ad }
            boolean r4 = r4.isRecycled()     // Catch:{ all -> 0x00ad }
            if (r4 != 0) goto L_0x0079
            java.util.Map<android.graphics.Bitmap, java.lang.Object> r4 = r1.otherEntries     // Catch:{ all -> 0x00ad }
            java.lang.Object r5 = r3.getKey()     // Catch:{ all -> 0x00ad }
            java.lang.String r6 = "entry.key"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)     // Catch:{ all -> 0x00ad }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00ad }
            java.lang.String r6 = "entry.value"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)     // Catch:{ all -> 0x00ad }
            r4.put(r5, r3)     // Catch:{ all -> 0x00ad }
            goto L_0x0079
        L_0x00ab:
            monitor-exit(r0)
            return r1
        L_0x00ad:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.CountingMemoryCacheInspector.dumpCacheContent():com.facebook.imagepipeline.cache.CountingMemoryCacheInspector$DumpInfo");
    }
}
