package com.facebook.imagepipeline.cache;

import bolts.Task;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import io.sentry.protocol.OperatingSystem;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"H\u0002J\u001f\u0010$\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002J\u001e\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&H\u0002J\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010)\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\"J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010.\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "", "fileCache", "Lcom/facebook/cache/disk/FileCache;", "pooledByteBufferFactory", "Lcom/facebook/common/memory/PooledByteBufferFactory;", "pooledByteStreams", "Lcom/facebook/common/memory/PooledByteStreams;", "readExecutor", "Ljava/util/concurrent/Executor;", "writeExecutor", "imageCacheStatsTracker", "Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;", "(Lcom/facebook/cache/disk/FileCache;Lcom/facebook/common/memory/PooledByteBufferFactory;Lcom/facebook/common/memory/PooledByteStreams;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Lcom/facebook/imagepipeline/cache/ImageCacheStatsTracker;)V", "size", "", "getSize", "()J", "stagingArea", "Lcom/facebook/imagepipeline/cache/StagingArea;", "addKeyForAsyncProbing", "", "key", "Lcom/facebook/cache/common/CacheKey;", "checkInStagingAreaAndFileCache", "", "clearAll", "Lbolts/Task;", "Ljava/lang/Void;", "contains", "containsAsync", "containsSync", "diskCheckSync", "foundPinnedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "pinnedImage", "get", "isCancelled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getAsync", "probe", "put", "encodedImage", "readFromDiskCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "remove", "writeToDiskCache", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BufferedDiskCache.kt */
public final class BufferedDiskCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Class<?> TAG = BufferedDiskCache.class;
    private final FileCache fileCache;
    private final ImageCacheStatsTracker imageCacheStatsTracker;
    private final PooledByteBufferFactory pooledByteBufferFactory;
    private final PooledByteStreams pooledByteStreams;
    private final Executor readExecutor;
    private final StagingArea stagingArea;
    private final Executor writeExecutor;

    public BufferedDiskCache(FileCache fileCache2, PooledByteBufferFactory pooledByteBufferFactory2, PooledByteStreams pooledByteStreams2, Executor executor, Executor executor2, ImageCacheStatsTracker imageCacheStatsTracker2) {
        Intrinsics.checkNotNullParameter(fileCache2, "fileCache");
        Intrinsics.checkNotNullParameter(pooledByteBufferFactory2, "pooledByteBufferFactory");
        Intrinsics.checkNotNullParameter(pooledByteStreams2, "pooledByteStreams");
        Intrinsics.checkNotNullParameter(executor, "readExecutor");
        Intrinsics.checkNotNullParameter(executor2, "writeExecutor");
        Intrinsics.checkNotNullParameter(imageCacheStatsTracker2, "imageCacheStatsTracker");
        this.fileCache = fileCache2;
        this.pooledByteBufferFactory = pooledByteBufferFactory2;
        this.pooledByteStreams = pooledByteStreams2;
        this.readExecutor = executor;
        this.writeExecutor = executor2;
        this.imageCacheStatsTracker = imageCacheStatsTracker2;
        StagingArea instance = StagingArea.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        this.stagingArea = instance;
    }

    public final boolean containsSync(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        return this.stagingArea.containsKey(cacheKey) || this.fileCache.hasKeySync(cacheKey);
    }

    public final Task<Boolean> contains(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        if (!containsSync(cacheKey)) {
            return containsAsync(cacheKey);
        }
        Task<Boolean> forResult = Task.forResult(true);
        Intrinsics.checkNotNullExpressionValue(forResult, "{\n        Task.forResult(true)\n      }");
        return forResult;
    }

    private final Task<Boolean> containsAsync(CacheKey cacheKey) {
        try {
            Task<Boolean> call = Task.call(new BufferedDiskCache$$ExternalSyntheticLambda5(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_containsAsync"), this, cacheKey), this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…      readExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache read for %s", cacheKey.getUriString());
            Task<Boolean> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* access modifiers changed from: private */
    public static final Boolean containsAsync$lambda$0(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            Boolean valueOf = Boolean.valueOf(bufferedDiskCache.checkInStagingAreaAndFileCache(cacheKey));
            FrescoInstrumenter.onEndWork(onBeginWork);
            return valueOf;
        } catch (Throwable th) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final boolean diskCheckSync(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        if (containsSync(cacheKey)) {
            return true;
        }
        return checkInStagingAreaAndFileCache(cacheKey);
    }

    public final Task<EncodedImage> get(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
        Task<EncodedImage> task;
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        Intrinsics.checkNotNullParameter(atomicBoolean, "isCancelled");
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BufferedDiskCache#get");
            }
            EncodedImage encodedImage = this.stagingArea.get(cacheKey);
            if (encodedImage == null || (task = foundPinnedImage(cacheKey, encodedImage)) == null) {
                task = getAsync(cacheKey, atomicBoolean);
            }
            return task;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    public final Task<Void> probe(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        try {
            Task<Void> call = Task.call(new BufferedDiskCache$$ExternalSyntheticLambda6(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_probe"), this, cacheKey), this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache probe for %s", cacheKey.getUriString());
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      FLog.w(TAG, exce…forError(exception)\n    }");
            return forError;
        }
    }

    /* access modifiers changed from: private */
    public static final Void probe$lambda$2(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.fileCache.probe(cacheKey);
            return null;
        } finally {
            FrescoInstrumenter.onEndWork(onBeginWork);
        }
    }

    public final void addKeyForAsyncProbing(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        this.fileCache.probe(cacheKey);
    }

    private final boolean checkInStagingAreaAndFileCache(CacheKey cacheKey) {
        EncodedImage encodedImage = this.stagingArea.get(cacheKey);
        if (encodedImage != null) {
            encodedImage.close();
            FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
            this.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
            return true;
        }
        FLog.v(TAG, "Did not find image for %s in staging area", (Object) cacheKey.getUriString());
        this.imageCacheStatsTracker.onStagingAreaMiss(cacheKey);
        try {
            return this.fileCache.hasKey(cacheKey);
        } catch (Exception unused) {
            return false;
        }
    }

    private final Task<EncodedImage> getAsync(CacheKey cacheKey, AtomicBoolean atomicBoolean) {
        try {
            Task<EncodedImage> call = Task.call(new BufferedDiskCache$$ExternalSyntheticLambda0(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_getAsync"), atomicBoolean, this, cacheKey), this.readExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…      readExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache read for %s", cacheKey.getUriString());
            Task<EncodedImage> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* access modifiers changed from: private */
    public static final EncodedImage getAsync$lambda$3(Object obj, AtomicBoolean atomicBoolean, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        CloseableReference of;
        Intrinsics.checkNotNullParameter(atomicBoolean, "$isCancelled");
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            if (!atomicBoolean.get()) {
                EncodedImage encodedImage = bufferedDiskCache.stagingArea.get(cacheKey);
                if (encodedImage != null) {
                    FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
                    bufferedDiskCache.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
                } else {
                    FLog.v(TAG, "Did not find image for %s in staging area", (Object) cacheKey.getUriString());
                    bufferedDiskCache.imageCacheStatsTracker.onStagingAreaMiss(cacheKey);
                    try {
                        PooledByteBuffer readFromDiskCache = bufferedDiskCache.readFromDiskCache(cacheKey);
                        if (readFromDiskCache == null) {
                            FrescoInstrumenter.onEndWork(onBeginWork);
                            return null;
                        }
                        of = CloseableReference.of(readFromDiskCache);
                        Intrinsics.checkNotNullExpressionValue(of, "of(buffer)");
                        EncodedImage encodedImage2 = new EncodedImage((CloseableReference<PooledByteBuffer>) of);
                        CloseableReference.closeSafely((CloseableReference<?>) of);
                        encodedImage = encodedImage2;
                    } catch (Exception unused) {
                        FrescoInstrumenter.onEndWork(onBeginWork);
                        return null;
                    } catch (Throwable th) {
                        CloseableReference.closeSafely((CloseableReference<?>) of);
                        throw th;
                    }
                }
                if (!Thread.interrupted()) {
                    FrescoInstrumenter.onEndWork(onBeginWork);
                    return encodedImage;
                }
                FLog.v(TAG, "Host thread was interrupted, decreasing reference count");
                encodedImage.close();
                throw new InterruptedException();
            }
            throw new CancellationException();
        } catch (Throwable th2) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th2;
        }
    }

    public final void put(CacheKey cacheKey, EncodedImage encodedImage) {
        EncodedImage cloneOrNull;
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BufferedDiskCache#put");
            }
            if (EncodedImage.isValid(encodedImage)) {
                this.stagingArea.put(cacheKey, encodedImage);
                cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
                this.writeExecutor.execute(new BufferedDiskCache$$ExternalSyntheticLambda4(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_putAsync"), this, cacheKey, cloneOrNull));
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                    return;
                }
                return;
            }
            throw new IllegalStateException("Check failed.".toString());
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache write for %s", cacheKey.getUriString());
            this.stagingArea.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(cloneOrNull);
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static final void put$lambda$4(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, EncodedImage encodedImage) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.writeToDiskCache(cacheKey, encodedImage);
            StagingArea stagingArea2 = bufferedDiskCache.stagingArea;
            Intrinsics.checkNotNull(encodedImage);
            stagingArea2.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(encodedImage);
            FrescoInstrumenter.onEndWork(onBeginWork);
        } catch (Throwable th) {
            StagingArea stagingArea3 = bufferedDiskCache.stagingArea;
            Intrinsics.checkNotNull(encodedImage);
            stagingArea3.remove(cacheKey, encodedImage);
            EncodedImage.closeSafely(encodedImage);
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final Task<Void> remove(CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(cacheKey, "key");
        this.stagingArea.remove(cacheKey);
        try {
            Task<Void> call = Task.call(new BufferedDiskCache$$ExternalSyntheticLambda2(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_remove"), this, cacheKey), this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      val token = Fres…     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache remove for %s", cacheKey.getUriString());
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* access modifiers changed from: private */
    public static final Void remove$lambda$5(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(cacheKey, "$key");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.stagingArea.remove(cacheKey);
            bufferedDiskCache.fileCache.remove(cacheKey);
            FrescoInstrumenter.onEndWork(onBeginWork);
            return null;
        } catch (Throwable th) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final Task<Void> clearAll() {
        this.stagingArea.clearAll();
        try {
            Task<Void> call = Task.call(new BufferedDiskCache$$ExternalSyntheticLambda1(FrescoInstrumenter.onBeforeSubmitWork("BufferedDiskCache_clearAll"), this), this.writeExecutor);
            Intrinsics.checkNotNullExpressionValue(call, "{\n      Task.call(\n     …     writeExecutor)\n    }");
            return call;
        } catch (Exception e) {
            FLog.w(TAG, (Throwable) e, "Failed to schedule disk-cache clear", new Object[0]);
            Task<Void> forError = Task.forError(e);
            Intrinsics.checkNotNullExpressionValue(forError, "{\n      // Log failure\n …forError(exception)\n    }");
            return forError;
        }
    }

    /* access modifiers changed from: private */
    public static final Void clearAll$lambda$6(Object obj, BufferedDiskCache bufferedDiskCache) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Object onBeginWork = FrescoInstrumenter.onBeginWork(obj, (String) null);
        try {
            bufferedDiskCache.stagingArea.clearAll();
            bufferedDiskCache.fileCache.clearAll();
            FrescoInstrumenter.onEndWork(onBeginWork);
            return null;
        } catch (Throwable th) {
            FrescoInstrumenter.onEndWork(onBeginWork);
            throw th;
        }
    }

    public final long getSize() {
        return this.fileCache.getSize();
    }

    private final Task<EncodedImage> foundPinnedImage(CacheKey cacheKey, EncodedImage encodedImage) {
        FLog.v(TAG, "Found image for %s in staging area", (Object) cacheKey.getUriString());
        this.imageCacheStatsTracker.onStagingAreaHit(cacheKey);
        Task<EncodedImage> forResult = Task.forResult(encodedImage);
        Intrinsics.checkNotNullExpressionValue(forResult, "forResult(pinnedImage)");
        return forResult;
    }

    private final PooledByteBuffer readFromDiskCache(CacheKey cacheKey) throws IOException {
        InputStream openStream;
        try {
            Class<?> cls = TAG;
            FLog.v(cls, "Disk cache read for %s", (Object) cacheKey.getUriString());
            BinaryResource resource = this.fileCache.getResource(cacheKey);
            if (resource == null) {
                FLog.v(cls, "Disk cache miss for %s", (Object) cacheKey.getUriString());
                this.imageCacheStatsTracker.onDiskCacheMiss(cacheKey);
                return null;
            }
            FLog.v(cls, "Found entry in disk cache for %s", (Object) cacheKey.getUriString());
            this.imageCacheStatsTracker.onDiskCacheHit(cacheKey);
            openStream = resource.openStream();
            PooledByteBuffer newByteBuffer = this.pooledByteBufferFactory.newByteBuffer(openStream, (int) resource.size());
            openStream.close();
            FLog.v(cls, "Successful read from disk cache for %s", (Object) cacheKey.getUriString());
            return newByteBuffer;
        } catch (IOException e) {
            FLog.w(TAG, (Throwable) e, "Exception reading from cache for %s", cacheKey.getUriString());
            this.imageCacheStatsTracker.onDiskCacheGetFail(cacheKey);
            throw e;
        } catch (Throwable th) {
            openStream.close();
            throw th;
        }
    }

    private final void writeToDiskCache(CacheKey cacheKey, EncodedImage encodedImage) {
        Class<?> cls = TAG;
        FLog.v(cls, "About to write to disk-cache for key %s", (Object) cacheKey.getUriString());
        try {
            this.fileCache.insert(cacheKey, new BufferedDiskCache$$ExternalSyntheticLambda3(encodedImage, this));
            this.imageCacheStatsTracker.onDiskCachePut(cacheKey);
            FLog.v(cls, "Successful disk-cache write for key %s", (Object) cacheKey.getUriString());
        } catch (IOException e) {
            FLog.w(TAG, (Throwable) e, "Failed to write to disk-cache for key %s", cacheKey.getUriString());
        }
    }

    /* access modifiers changed from: private */
    public static final void writeToDiskCache$lambda$7(EncodedImage encodedImage, BufferedDiskCache bufferedDiskCache, OutputStream outputStream) {
        Intrinsics.checkNotNullParameter(bufferedDiskCache, "this$0");
        Intrinsics.checkNotNullParameter(outputStream, OperatingSystem.TYPE);
        Intrinsics.checkNotNull(encodedImage);
        InputStream inputStream = encodedImage.getInputStream();
        if (inputStream != null) {
            bufferedDiskCache.pooledByteStreams.copy(inputStream, outputStream);
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/imagepipeline/cache/BufferedDiskCache$Companion;", "", "()V", "TAG", "Ljava/lang/Class;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BufferedDiskCache.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
