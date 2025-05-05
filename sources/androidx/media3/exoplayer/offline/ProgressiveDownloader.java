package androidx.media3.exoplayer.offline;

import androidx.credentials.CredentialManager$$ExternalSyntheticLambda0;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PriorityTaskManager;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.RunnableFutureTask;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.CacheWriter;
import androidx.media3.exoplayer.offline.Downloader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public final class ProgressiveDownloader implements Downloader {
    /* access modifiers changed from: private */
    public final CacheWriter cacheWriter;
    private final CacheDataSource dataSource;
    private final DataSpec dataSpec;
    private volatile RunnableFutureTask<Void, IOException> downloadRunnable;
    private final Executor executor;
    private volatile boolean isCanceled;
    private final PriorityTaskManager priorityTaskManager;
    private Downloader.ProgressListener progressListener;

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory) {
        this(mediaItem, factory, new CredentialManager$$ExternalSyntheticLambda0());
    }

    public ProgressiveDownloader(MediaItem mediaItem, CacheDataSource.Factory factory, Executor executor2) {
        this.executor = (Executor) Assertions.checkNotNull(executor2);
        Assertions.checkNotNull(mediaItem.localConfiguration);
        DataSpec build = new DataSpec.Builder().setUri(mediaItem.localConfiguration.uri).setKey(mediaItem.localConfiguration.customCacheKey).setFlags(4).build();
        this.dataSpec = build;
        CacheDataSource createDataSourceForDownloading = factory.createDataSourceForDownloading();
        this.dataSource = createDataSourceForDownloading;
        this.cacheWriter = new CacheWriter(createDataSourceForDownloading, build, (byte[]) null, new ProgressiveDownloader$$ExternalSyntheticLambda0(this));
        this.priorityTaskManager = factory.getUpstreamPriorityTaskManager();
    }

    public void download(Downloader.ProgressListener progressListener2) throws IOException, InterruptedException {
        this.progressListener = progressListener2;
        PriorityTaskManager priorityTaskManager2 = this.priorityTaskManager;
        if (priorityTaskManager2 != null) {
            priorityTaskManager2.add(-4000);
        }
        boolean z = false;
        while (!z) {
            try {
                if (this.isCanceled) {
                    break;
                }
                this.downloadRunnable = new RunnableFutureTask<Void, IOException>() {
                    /* access modifiers changed from: protected */
                    public Void doWork() throws IOException {
                        ProgressiveDownloader.this.cacheWriter.cache();
                        return null;
                    }

                    /* access modifiers changed from: protected */
                    public void cancelWork() {
                        ProgressiveDownloader.this.cacheWriter.cancel();
                    }
                };
                PriorityTaskManager priorityTaskManager3 = this.priorityTaskManager;
                if (priorityTaskManager3 != null) {
                    priorityTaskManager3.proceed(-4000);
                }
                this.executor.execute(this.downloadRunnable);
                this.downloadRunnable.get();
                z = true;
            } catch (ExecutionException e) {
                Throwable th = (Throwable) Assertions.checkNotNull(e.getCause());
                if (!(th instanceof PriorityTaskManager.PriorityTooLowException)) {
                    if (!(th instanceof IOException)) {
                        Util.sneakyThrow(th);
                    } else {
                        throw ((IOException) th);
                    }
                }
            } catch (Throwable th2) {
                ((RunnableFutureTask) Assertions.checkNotNull(this.downloadRunnable)).blockUntilFinished();
                PriorityTaskManager priorityTaskManager4 = this.priorityTaskManager;
                if (priorityTaskManager4 != null) {
                    priorityTaskManager4.remove(-4000);
                }
                throw th2;
            }
        }
        ((RunnableFutureTask) Assertions.checkNotNull(this.downloadRunnable)).blockUntilFinished();
        PriorityTaskManager priorityTaskManager5 = this.priorityTaskManager;
        if (priorityTaskManager5 != null) {
            priorityTaskManager5.remove(-4000);
        }
    }

    public void cancel() {
        this.isCanceled = true;
        RunnableFutureTask<Void, IOException> runnableFutureTask = this.downloadRunnable;
        if (runnableFutureTask != null) {
            runnableFutureTask.cancel(true);
        }
    }

    public void remove() {
        this.dataSource.getCache().removeResource(this.dataSource.getCacheKeyFactory().buildCacheKey(this.dataSpec));
    }

    /* access modifiers changed from: private */
    public void onProgress(long j, long j2, long j3) {
        Downloader.ProgressListener progressListener2 = this.progressListener;
        if (progressListener2 != null) {
            progressListener2.onProgress(j, j2, (j == -1 || j == 0) ? -1.0f : (((float) j2) * 100.0f) / ((float) j));
        }
    }
}
