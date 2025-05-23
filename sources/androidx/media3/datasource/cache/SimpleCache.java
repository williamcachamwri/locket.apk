package androidx.media3.datasource.cache;

import android.os.ConditionVariable;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.database.DatabaseIOException;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.datasource.cache.Cache;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public final class SimpleCache implements Cache {
    private static final int SUBDIRECTORY_COUNT = 10;
    private static final String TAG = "SimpleCache";
    private static final String UID_FILE_SUFFIX = ".uid";
    private static final HashSet<File> lockedCacheDirs = new HashSet<>();
    private final File cacheDir;
    private final CachedContentIndex contentIndex;
    /* access modifiers changed from: private */
    public final CacheEvictor evictor;
    private final CacheFileMetadataIndex fileIndex;
    private Cache.CacheException initializationException;
    private final HashMap<String, ArrayList<Cache.Listener>> listeners;
    private final Random random;
    private boolean released;
    private long totalSpace;
    private final boolean touchCacheSpans;
    private long uid;

    public static synchronized boolean isCacheFolderLocked(File file) {
        boolean contains;
        synchronized (SimpleCache.class) {
            contains = lockedCacheDirs.contains(file.getAbsoluteFile());
        }
        return contains;
    }

    public static void delete(File file, DatabaseProvider databaseProvider) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                file.delete();
                return;
            }
            if (databaseProvider != null) {
                long loadUid = loadUid(listFiles);
                if (loadUid != -1) {
                    try {
                        CacheFileMetadataIndex.delete(databaseProvider, loadUid);
                    } catch (DatabaseIOException unused) {
                        Log.w(TAG, "Failed to delete file metadata: " + loadUid);
                    }
                    try {
                        CachedContentIndex.delete(databaseProvider, loadUid);
                    } catch (DatabaseIOException unused2) {
                        Log.w(TAG, "Failed to delete file metadata: " + loadUid);
                    }
                }
            }
            Util.recursiveDelete(file);
        }
    }

    @Deprecated
    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, (DatabaseProvider) null, (byte[]) null, false, true);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider) {
        this(file, cacheEvictor, databaseProvider, (byte[]) null, false, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleCache(File file, CacheEvictor cacheEvictor, DatabaseProvider databaseProvider, byte[] bArr, boolean z, boolean z2) {
        this(file, cacheEvictor, new CachedContentIndex(databaseProvider, file, bArr, z, z2), (databaseProvider == null || z2) ? null : new CacheFileMetadataIndex(databaseProvider));
    }

    SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex, CacheFileMetadataIndex cacheFileMetadataIndex) {
        if (lockFolder(file)) {
            this.cacheDir = file;
            this.evictor = cacheEvictor;
            this.contentIndex = cachedContentIndex;
            this.fileIndex = cacheFileMetadataIndex;
            this.listeners = new HashMap<>();
            this.random = new Random();
            this.touchCacheSpans = cacheEvictor.requiresCacheSpanTouches();
            this.uid = -1;
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread("ExoPlayer:SimpleCacheInit") {
                public void run() {
                    synchronized (SimpleCache.this) {
                        conditionVariable.open();
                        SimpleCache.this.initialize();
                        SimpleCache.this.evictor.onCacheInitialized();
                    }
                }
            }.start();
            conditionVariable.block();
            return;
        }
        throw new IllegalStateException("Another SimpleCache instance uses the folder: " + file);
    }

    public synchronized void checkInitialization() throws Cache.CacheException {
        Cache.CacheException cacheException = this.initializationException;
        if (cacheException != null) {
            throw cacheException;
        }
    }

    public synchronized long getUid() {
        return this.uid;
    }

    public synchronized void release() {
        if (!this.released) {
            this.listeners.clear();
            removeStaleSpans();
            try {
                this.contentIndex.store();
                unlockFolder(this.cacheDir);
            } catch (IOException e) {
                try {
                    Log.e(TAG, "Storing index file failed", e);
                    unlockFolder(this.cacheDir);
                } catch (Throwable th) {
                    unlockFolder(this.cacheDir);
                    this.released = true;
                    throw th;
                }
            }
            this.released = true;
            return;
        }
        return;
    }

    public synchronized NavigableSet<CacheSpan> addListener(String str, Cache.Listener listener) {
        Assertions.checkState(!this.released);
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(listener);
        ArrayList arrayList = this.listeners.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.listeners.put(str, arrayList);
        }
        arrayList.add(listener);
        return getCachedSpans(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeListener(java.lang.String r2, androidx.media3.datasource.cache.Cache.Listener r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.released     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            java.util.HashMap<java.lang.String, java.util.ArrayList<androidx.media3.datasource.cache.Cache$Listener>> r0 = r1.listeners     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0021 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x001f
            r0.remove(r3)     // Catch:{ all -> 0x0021 }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x001f
            java.util.HashMap<java.lang.String, java.util.ArrayList<androidx.media3.datasource.cache.Cache$Listener>> r3 = r1.listeners     // Catch:{ all -> 0x0021 }
            r3.remove(r2)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r1)
            return
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.datasource.cache.SimpleCache.removeListener(java.lang.String, androidx.media3.datasource.cache.Cache$Listener):void");
    }

    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        TreeSet treeSet;
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent != null) {
            if (!cachedContent.isEmpty()) {
                treeSet = new TreeSet(cachedContent.getSpans());
            }
        }
        treeSet = new TreeSet();
        return treeSet;
    }

    public synchronized Set<String> getKeys() {
        Assertions.checkState(!this.released);
        return new HashSet(this.contentIndex.getKeys());
    }

    public synchronized long getCacheSpace() {
        Assertions.checkState(!this.released);
        return this.totalSpace;
    }

    public synchronized CacheSpan startReadWrite(String str, long j, long j2) throws InterruptedException, Cache.CacheException {
        CacheSpan startReadWriteNonBlocking;
        Assertions.checkState(!this.released);
        checkInitialization();
        while (true) {
            startReadWriteNonBlocking = startReadWriteNonBlocking(str, j, j2);
            if (startReadWriteNonBlocking == null) {
                wait();
            }
        }
        return startReadWriteNonBlocking;
    }

    public synchronized CacheSpan startReadWriteNonBlocking(String str, long j, long j2) throws Cache.CacheException {
        Assertions.checkState(!this.released);
        checkInitialization();
        SimpleCacheSpan span = getSpan(str, j, j2);
        if (span.isCached) {
            return touchSpan(str, span);
        } else if (this.contentIndex.getOrAdd(str).lockRange(j, span.length)) {
            return span;
        } else {
            return null;
        }
    }

    public synchronized File startFile(String str, long j, long j2) throws Cache.CacheException {
        CachedContent cachedContent;
        File file;
        Assertions.checkState(!this.released);
        checkInitialization();
        cachedContent = this.contentIndex.get(str);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isFullyLocked(j, j2));
        if (!this.cacheDir.exists()) {
            createCacheDirectories(this.cacheDir);
            removeStaleSpans();
        }
        this.evictor.onStartFile(this, str, j, j2);
        file = new File(this.cacheDir, Integer.toString(this.random.nextInt(10)));
        if (!file.exists()) {
            createCacheDirectories(file);
        }
        return SimpleCacheSpan.getCacheFile(file, cachedContent.id, j, System.currentTimeMillis());
    }

    public synchronized void commitFile(File file, long j) throws Cache.CacheException {
        boolean z = true;
        Assertions.checkState(!this.released);
        if (file.exists()) {
            if (j == 0) {
                file.delete();
                return;
            }
            SimpleCacheSpan simpleCacheSpan = (SimpleCacheSpan) Assertions.checkNotNull(SimpleCacheSpan.createCacheEntry(file, j, this.contentIndex));
            CachedContent cachedContent = (CachedContent) Assertions.checkNotNull(this.contentIndex.get(simpleCacheSpan.key));
            Assertions.checkState(cachedContent.isFullyLocked(simpleCacheSpan.position, simpleCacheSpan.length));
            long contentLength = ContentMetadata.getContentLength(cachedContent.getMetadata());
            if (contentLength != -1) {
                if (simpleCacheSpan.position + simpleCacheSpan.length > contentLength) {
                    z = false;
                }
                Assertions.checkState(z);
            }
            if (this.fileIndex != null) {
                try {
                    this.fileIndex.set(file.getName(), simpleCacheSpan.length, simpleCacheSpan.lastTouchTimestamp);
                } catch (IOException e) {
                    throw new Cache.CacheException((Throwable) e);
                } catch (IOException e2) {
                    throw new Cache.CacheException((Throwable) e2);
                }
            }
            addSpan(simpleCacheSpan);
            this.contentIndex.store();
            notifyAll();
        }
    }

    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        CachedContent cachedContent = (CachedContent) Assertions.checkNotNull(this.contentIndex.get(cacheSpan.key));
        cachedContent.unlockRange(cacheSpan.position);
        this.contentIndex.maybeRemove(cachedContent.key);
        notifyAll();
    }

    public synchronized void removeResource(String str) {
        Assertions.checkState(!this.released);
        for (CacheSpan removeSpanInternal : getCachedSpans(str)) {
            removeSpanInternal(removeSpanInternal);
        }
    }

    public synchronized void removeSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        removeSpanInternal(cacheSpan);
    }

    public synchronized boolean isCached(String str, long j, long j2) {
        boolean z;
        z = true;
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent == null || cachedContent.getCachedBytesLength(j, j2) < j2) {
            z = false;
        }
        return z;
    }

    public synchronized long getCachedLength(String str, long j, long j2) {
        CachedContent cachedContent;
        Assertions.checkState(!this.released);
        if (j2 == -1) {
            j2 = Long.MAX_VALUE;
        }
        cachedContent = this.contentIndex.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j, j2) : -j2;
    }

    public synchronized long getCachedBytes(String str, long j, long j2) {
        long j3;
        long j4 = j2 == -1 ? Long.MAX_VALUE : j + j2;
        long j5 = j4 < 0 ? Long.MAX_VALUE : j4;
        long j6 = j;
        j3 = 0;
        while (j6 < j5) {
            try {
                long cachedLength = getCachedLength(str, j6, j5 - j6);
                if (cachedLength > 0) {
                    j3 += cachedLength;
                } else {
                    cachedLength = -cachedLength;
                }
                j6 += cachedLength;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j3;
    }

    public synchronized void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws Cache.CacheException {
        Assertions.checkState(!this.released);
        checkInitialization();
        this.contentIndex.applyContentMetadataMutations(str, contentMetadataMutations);
        try {
            this.contentIndex.store();
        } catch (IOException e) {
            throw new Cache.CacheException((Throwable) e);
        }
    }

    public synchronized ContentMetadata getContentMetadata(String str) {
        Assertions.checkState(!this.released);
        return this.contentIndex.getContentMetadata(str);
    }

    /* access modifiers changed from: private */
    public void initialize() {
        if (!this.cacheDir.exists()) {
            try {
                createCacheDirectories(this.cacheDir);
            } catch (Cache.CacheException e) {
                this.initializationException = e;
                return;
            }
        }
        File[] listFiles = this.cacheDir.listFiles();
        if (listFiles == null) {
            String str = "Failed to list cache directory files: " + this.cacheDir;
            Log.e(TAG, str);
            this.initializationException = new Cache.CacheException(str);
            return;
        }
        long loadUid = loadUid(listFiles);
        this.uid = loadUid;
        if (loadUid == -1) {
            try {
                this.uid = createUid(this.cacheDir);
            } catch (IOException e2) {
                String str2 = "Failed to create cache UID: " + this.cacheDir;
                Log.e(TAG, str2, e2);
                this.initializationException = new Cache.CacheException(str2, e2);
                return;
            }
        }
        try {
            this.contentIndex.initialize(this.uid);
            CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
            if (cacheFileMetadataIndex != null) {
                cacheFileMetadataIndex.initialize(this.uid);
                Map<String, CacheFileMetadata> all = this.fileIndex.getAll();
                loadDirectory(this.cacheDir, true, listFiles, all);
                this.fileIndex.removeAll(all.keySet());
            } else {
                loadDirectory(this.cacheDir, true, listFiles, (Map<String, CacheFileMetadata>) null);
            }
            this.contentIndex.removeEmpty();
            try {
                this.contentIndex.store();
            } catch (IOException e3) {
                Log.e(TAG, "Storing index file failed", e3);
            }
        } catch (IOException e4) {
            String str3 = "Failed to initialize cache indices: " + this.cacheDir;
            Log.e(TAG, str3, e4);
            this.initializationException = new Cache.CacheException(str3, e4);
        }
    }

    private void loadDirectory(File file, boolean z, File[] fileArr, Map<String, CacheFileMetadata> map) {
        long j;
        long j2;
        if (fileArr != null && fileArr.length != 0) {
            for (File file2 : fileArr) {
                String name = file2.getName();
                if (z && name.indexOf(46) == -1) {
                    loadDirectory(file2, false, file2.listFiles(), map);
                } else if (!z || (!CachedContentIndex.isIndexFile(name) && !name.endsWith(UID_FILE_SUFFIX))) {
                    CacheFileMetadata remove = map != null ? map.remove(name) : null;
                    if (remove != null) {
                        j2 = remove.length;
                        j = remove.lastTouchTimestamp;
                    } else {
                        j = -9223372036854775807L;
                        j2 = -1;
                    }
                    SimpleCacheSpan createCacheEntry = SimpleCacheSpan.createCacheEntry(file2, j2, j, this.contentIndex);
                    if (createCacheEntry != null) {
                        addSpan(createCacheEntry);
                    } else {
                        file2.delete();
                    }
                }
            }
        } else if (!z) {
            file.delete();
        }
    }

    private SimpleCacheSpan touchSpan(String str, SimpleCacheSpan simpleCacheSpan) {
        boolean z;
        if (!this.touchCacheSpans) {
            return simpleCacheSpan;
        }
        String name = ((File) Assertions.checkNotNull(simpleCacheSpan.file)).getName();
        long j = simpleCacheSpan.length;
        long currentTimeMillis = System.currentTimeMillis();
        CacheFileMetadataIndex cacheFileMetadataIndex = this.fileIndex;
        if (cacheFileMetadataIndex != null) {
            try {
                cacheFileMetadataIndex.set(name, j, currentTimeMillis);
            } catch (IOException unused) {
                Log.w(TAG, "Failed to update index with new touch timestamp.");
            }
            z = false;
        } else {
            z = true;
        }
        SimpleCacheSpan lastTouchTimestamp = ((CachedContent) Assertions.checkNotNull(this.contentIndex.get(str))).setLastTouchTimestamp(simpleCacheSpan, currentTimeMillis, z);
        notifySpanTouched(simpleCacheSpan, lastTouchTimestamp);
        return lastTouchTimestamp;
    }

    private SimpleCacheSpan getSpan(String str, long j, long j2) {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.contentIndex.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createHole(str, j, j2);
        }
        while (true) {
            span = cachedContent.getSpan(j, j2);
            if (!span.isCached || ((File) Assertions.checkNotNull(span.file)).length() == span.length) {
                return span;
            }
            removeStaleSpans();
        }
        return span;
    }

    private void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.contentIndex.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    private void removeSpanInternal(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.contentIndex.get(cacheSpan.key);
        if (cachedContent != null && cachedContent.removeSpan(cacheSpan)) {
            this.totalSpace -= cacheSpan.length;
            if (this.fileIndex != null) {
                String name = ((File) Assertions.checkNotNull(cacheSpan.file)).getName();
                try {
                    this.fileIndex.remove(name);
                } catch (IOException unused) {
                    Log.w(TAG, "Failed to remove file index entry for: " + name);
                }
            }
            this.contentIndex.maybeRemove(cachedContent.key);
            notifySpanRemoved(cacheSpan);
        }
    }

    private void removeStaleSpans() {
        ArrayList arrayList = new ArrayList();
        for (CachedContent spans : this.contentIndex.getAll()) {
            Iterator<SimpleCacheSpan> it = spans.getSpans().iterator();
            while (it.hasNext()) {
                CacheSpan next = it.next();
                if (((File) Assertions.checkNotNull(next.file)).length() != next.length) {
                    arrayList.add(next);
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            removeSpanInternal((CacheSpan) arrayList.get(i));
        }
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList arrayList = this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    private void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList arrayList = this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Cache.Listener) arrayList.get(size)).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    private static long loadUid(File[] fileArr) {
        int length = fileArr.length;
        int i = 0;
        while (i < length) {
            File file = fileArr[i];
            String name = file.getName();
            if (name.endsWith(UID_FILE_SUFFIX)) {
                try {
                    return parseUid(name);
                } catch (NumberFormatException unused) {
                    Log.e(TAG, "Malformed UID file: " + file);
                    file.delete();
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    private static long createUid(File file) throws IOException {
        long j;
        long nextLong = new SecureRandom().nextLong();
        if (nextLong == Long.MIN_VALUE) {
            j = 0;
        } else {
            j = Math.abs(nextLong);
        }
        File file2 = new File(file, Long.toString(j, 16) + UID_FILE_SUFFIX);
        if (file2.createNewFile()) {
            return j;
        }
        throw new IOException("Failed to create UID file: " + file2);
    }

    private static long parseUid(String str) {
        return Long.parseLong(str.substring(0, str.indexOf(46)), 16);
    }

    private static void createCacheDirectories(File file) throws Cache.CacheException {
        if (!file.mkdirs() && !file.isDirectory()) {
            String str = "Failed to create cache directory: " + file;
            Log.e(TAG, str);
            throw new Cache.CacheException(str);
        }
    }

    private static synchronized boolean lockFolder(File file) {
        boolean add;
        synchronized (SimpleCache.class) {
            add = lockedCacheDirs.add(file.getAbsoluteFile());
        }
        return add;
    }

    private static synchronized void unlockFolder(File file) {
        synchronized (SimpleCache.class) {
            lockedCacheDirs.remove(file.getAbsoluteFile());
        }
    }
}
