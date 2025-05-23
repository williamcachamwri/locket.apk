package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Util;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheSpan;
import androidx.media3.extractor.ChunkIndex;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public final class CachedRegionTracker implements Cache.Listener {
    public static final int CACHED_TO_END = -2;
    public static final int NOT_CACHED = -1;
    private static final String TAG = "CachedRegionTracker";
    private final Cache cache;
    private final String cacheKey;
    private final ChunkIndex chunkIndex;
    private final Region lookupRegion = new Region(0, 0);
    private final TreeSet<Region> regions = new TreeSet<>();

    public void onSpanTouched(Cache cache2, CacheSpan cacheSpan, CacheSpan cacheSpan2) {
    }

    public CachedRegionTracker(Cache cache2, String str, ChunkIndex chunkIndex2) {
        this.cache = cache2;
        this.cacheKey = str;
        this.chunkIndex = chunkIndex2;
        synchronized (this) {
            Iterator<CacheSpan> descendingIterator = cache2.addListener(str, this).descendingIterator();
            while (descendingIterator.hasNext()) {
                mergeSpan(descendingIterator.next());
            }
        }
    }

    public void release() {
        this.cache.removeListener(this.cacheKey, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0064, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getRegionEndTimeMs(long r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r0 = r7.lookupRegion     // Catch:{ all -> 0x0065 }
            r0.startOffset = r8     // Catch:{ all -> 0x0065 }
            java.util.TreeSet<androidx.media3.exoplayer.upstream.CachedRegionTracker$Region> r0 = r7.regions     // Catch:{ all -> 0x0065 }
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r1 = r7.lookupRegion     // Catch:{ all -> 0x0065 }
            java.lang.Object r0 = r0.floor(r1)     // Catch:{ all -> 0x0065 }
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r0 = (androidx.media3.exoplayer.upstream.CachedRegionTracker.Region) r0     // Catch:{ all -> 0x0065 }
            r1 = -1
            if (r0 == 0) goto L_0x0063
            long r2 = r0.endOffset     // Catch:{ all -> 0x0065 }
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 > 0) goto L_0x0063
            int r8 = r0.endOffsetIndex     // Catch:{ all -> 0x0065 }
            if (r8 != r1) goto L_0x001d
            goto L_0x0063
        L_0x001d:
            int r8 = r0.endOffsetIndex     // Catch:{ all -> 0x0065 }
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            int r9 = r9.length     // Catch:{ all -> 0x0065 }
            int r9 = r9 + -1
            if (r8 != r9) goto L_0x003e
            long r1 = r0.endOffset     // Catch:{ all -> 0x0065 }
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            long[] r9 = r9.offsets     // Catch:{ all -> 0x0065 }
            r3 = r9[r8]     // Catch:{ all -> 0x0065 }
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            int[] r9 = r9.sizes     // Catch:{ all -> 0x0065 }
            r9 = r9[r8]     // Catch:{ all -> 0x0065 }
            long r5 = (long) r9
            long r3 = r3 + r5
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r9 != 0) goto L_0x003e
            monitor-exit(r7)
            r8 = -2
            return r8
        L_0x003e:
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            long[] r9 = r9.durationsUs     // Catch:{ all -> 0x0065 }
            r1 = r9[r8]     // Catch:{ all -> 0x0065 }
            long r3 = r0.endOffset     // Catch:{ all -> 0x0065 }
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            long[] r9 = r9.offsets     // Catch:{ all -> 0x0065 }
            r5 = r9[r8]     // Catch:{ all -> 0x0065 }
            long r3 = r3 - r5
            long r1 = r1 * r3
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            int[] r9 = r9.sizes     // Catch:{ all -> 0x0065 }
            r9 = r9[r8]     // Catch:{ all -> 0x0065 }
            long r3 = (long) r9     // Catch:{ all -> 0x0065 }
            long r1 = r1 / r3
            androidx.media3.extractor.ChunkIndex r9 = r7.chunkIndex     // Catch:{ all -> 0x0065 }
            long[] r9 = r9.timesUs     // Catch:{ all -> 0x0065 }
            r8 = r9[r8]     // Catch:{ all -> 0x0065 }
            long r8 = r8 + r1
            r0 = 1000(0x3e8, double:4.94E-321)
            long r8 = r8 / r0
            int r8 = (int) r8
            monitor-exit(r7)
            return r8
        L_0x0063:
            monitor-exit(r7)
            return r1
        L_0x0065:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.upstream.CachedRegionTracker.getRegionEndTimeMs(long):int");
    }

    public synchronized void onSpanAdded(Cache cache2, CacheSpan cacheSpan) {
        mergeSpan(cacheSpan);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onSpanRemoved(androidx.media3.datasource.cache.Cache r7, androidx.media3.datasource.cache.CacheSpan r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r7 = new androidx.media3.exoplayer.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x006b }
            long r0 = r8.position     // Catch:{ all -> 0x006b }
            long r2 = r8.position     // Catch:{ all -> 0x006b }
            long r4 = r8.length     // Catch:{ all -> 0x006b }
            long r2 = r2 + r4
            r7.<init>(r0, r2)     // Catch:{ all -> 0x006b }
            java.util.TreeSet<androidx.media3.exoplayer.upstream.CachedRegionTracker$Region> r8 = r6.regions     // Catch:{ all -> 0x006b }
            java.lang.Object r8 = r8.floor(r7)     // Catch:{ all -> 0x006b }
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r8 = (androidx.media3.exoplayer.upstream.CachedRegionTracker.Region) r8     // Catch:{ all -> 0x006b }
            if (r8 != 0) goto L_0x0020
            java.lang.String r7 = "CachedRegionTracker"
            java.lang.String r8 = "Removed a span we were not aware of"
            androidx.media3.common.util.Log.e(r7, r8)     // Catch:{ all -> 0x006b }
            monitor-exit(r6)
            return
        L_0x0020:
            java.util.TreeSet<androidx.media3.exoplayer.upstream.CachedRegionTracker$Region> r0 = r6.regions     // Catch:{ all -> 0x006b }
            r0.remove(r8)     // Catch:{ all -> 0x006b }
            long r0 = r8.startOffset     // Catch:{ all -> 0x006b }
            long r2 = r7.startOffset     // Catch:{ all -> 0x006b }
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x004c
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r0 = new androidx.media3.exoplayer.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x006b }
            long r1 = r8.startOffset     // Catch:{ all -> 0x006b }
            long r3 = r7.startOffset     // Catch:{ all -> 0x006b }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x006b }
            androidx.media3.extractor.ChunkIndex r1 = r6.chunkIndex     // Catch:{ all -> 0x006b }
            long[] r1 = r1.offsets     // Catch:{ all -> 0x006b }
            long r2 = r0.endOffset     // Catch:{ all -> 0x006b }
            int r1 = java.util.Arrays.binarySearch(r1, r2)     // Catch:{ all -> 0x006b }
            if (r1 >= 0) goto L_0x0045
            int r1 = -r1
            int r1 = r1 + -2
        L_0x0045:
            r0.endOffsetIndex = r1     // Catch:{ all -> 0x006b }
            java.util.TreeSet<androidx.media3.exoplayer.upstream.CachedRegionTracker$Region> r1 = r6.regions     // Catch:{ all -> 0x006b }
            r1.add(r0)     // Catch:{ all -> 0x006b }
        L_0x004c:
            long r0 = r8.endOffset     // Catch:{ all -> 0x006b }
            long r2 = r7.endOffset     // Catch:{ all -> 0x006b }
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0069
            androidx.media3.exoplayer.upstream.CachedRegionTracker$Region r0 = new androidx.media3.exoplayer.upstream.CachedRegionTracker$Region     // Catch:{ all -> 0x006b }
            long r1 = r7.endOffset     // Catch:{ all -> 0x006b }
            r3 = 1
            long r1 = r1 + r3
            long r3 = r8.endOffset     // Catch:{ all -> 0x006b }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x006b }
            int r7 = r8.endOffsetIndex     // Catch:{ all -> 0x006b }
            r0.endOffsetIndex = r7     // Catch:{ all -> 0x006b }
            java.util.TreeSet<androidx.media3.exoplayer.upstream.CachedRegionTracker$Region> r7 = r6.regions     // Catch:{ all -> 0x006b }
            r7.add(r0)     // Catch:{ all -> 0x006b }
        L_0x0069:
            monitor-exit(r6)
            return
        L_0x006b:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.upstream.CachedRegionTracker.onSpanRemoved(androidx.media3.datasource.cache.Cache, androidx.media3.datasource.cache.CacheSpan):void");
    }

    private void mergeSpan(CacheSpan cacheSpan) {
        Region region = new Region(cacheSpan.position, cacheSpan.position + cacheSpan.length);
        Region floor = this.regions.floor(region);
        Region ceiling = this.regions.ceiling(region);
        boolean regionsConnect = regionsConnect(floor, region);
        if (regionsConnect(region, ceiling)) {
            if (regionsConnect) {
                floor.endOffset = ceiling.endOffset;
                floor.endOffsetIndex = ceiling.endOffsetIndex;
            } else {
                region.endOffset = ceiling.endOffset;
                region.endOffsetIndex = ceiling.endOffsetIndex;
                this.regions.add(region);
            }
            this.regions.remove(ceiling);
        } else if (regionsConnect) {
            floor.endOffset = region.endOffset;
            int i = floor.endOffsetIndex;
            while (i < this.chunkIndex.length - 1) {
                int i2 = i + 1;
                if (this.chunkIndex.offsets[i2] > floor.endOffset) {
                    break;
                }
                i = i2;
            }
            floor.endOffsetIndex = i;
        } else {
            int binarySearch = Arrays.binarySearch(this.chunkIndex.offsets, region.endOffset);
            if (binarySearch < 0) {
                binarySearch = (-binarySearch) - 2;
            }
            region.endOffsetIndex = binarySearch;
            this.regions.add(region);
        }
    }

    private boolean regionsConnect(Region region, Region region2) {
        return (region == null || region2 == null || region.endOffset != region2.startOffset) ? false : true;
    }

    private static class Region implements Comparable<Region> {
        public long endOffset;
        public int endOffsetIndex;
        public long startOffset;

        public Region(long j, long j2) {
            this.startOffset = j;
            this.endOffset = j2;
        }

        public int compareTo(Region region) {
            return Util.compareLong(this.startOffset, region.startOffset);
        }
    }
}
