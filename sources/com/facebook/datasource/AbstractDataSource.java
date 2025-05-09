package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public abstract class AbstractDataSource<T> implements DataSource<T> {
    @Nullable
    private static volatile DataSourceInstrumenter sDataSourceInstrumenter;
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    @Nullable
    private Map<String, Object> mExtras;
    @Nullable
    private Throwable mFailureThrowable = null;
    private boolean mIsClosed = false;
    private float mProgress = 0.0f;
    @Nullable
    private T mResult = null;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue<>();

    public interface DataSourceInstrumenter {
        Runnable decorateRunnable(Runnable runnable, String str);
    }

    private enum DataSourceStatus {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    /* access modifiers changed from: protected */
    public void closeResult(@Nullable T t) {
    }

    public boolean hasMultipleResults() {
        return false;
    }

    public static void provideInstrumenter(@Nullable DataSourceInstrumenter dataSourceInstrumenter) {
        sDataSourceInstrumenter = dataSourceInstrumenter;
    }

    protected AbstractDataSource() {
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    @Nullable
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    /* access modifiers changed from: protected */
    public void setExtras(@Nullable Map<String, Object> map) {
        this.mExtras = map;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    public synchronized float getProgress() {
        return this.mProgress;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (isFinished() != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        notifyDataSubscribers();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.mSubscribers.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r1 == null) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        closeResult(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean close() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mIsClosed     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0008
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            r0 = 0
            return r0
        L_0x0008:
            r0 = 1
            r3.mIsClosed = r0     // Catch:{ all -> 0x002a }
            T r1 = r3.mResult     // Catch:{ all -> 0x002a }
            r2 = 0
            r3.mResult = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0016
            r3.closeResult(r1)
        L_0x0016:
            boolean r1 = r3.isFinished()
            if (r1 != 0) goto L_0x001f
            r3.notifyDataSubscribers()
        L_0x001f:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<com.facebook.datasource.DataSubscriber<T>, java.util.concurrent.Executor>> r1 = r3.mSubscribers     // Catch:{ all -> 0x0027 }
            r1.clear()     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            return r0
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.close():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        notifyDataSubscriber(r3, r4, hasFailed(), wasCancelled());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribe(com.facebook.datasource.DataSubscriber<T> r3, java.util.concurrent.Executor r4) {
        /*
            r2 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r3)
            com.facebook.common.internal.Preconditions.checkNotNull(r4)
            monitor-enter(r2)
            boolean r0 = r2.mIsClosed     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r2)     // Catch:{ all -> 0x0041 }
            return
        L_0x000d:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r2.mDataSourceStatus     // Catch:{ all -> 0x0041 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0041 }
            if (r0 != r1) goto L_0x001c
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<com.facebook.datasource.DataSubscriber<T>, java.util.concurrent.Executor>> r0 = r2.mSubscribers     // Catch:{ all -> 0x0041 }
            android.util.Pair r1 = android.util.Pair.create(r3, r4)     // Catch:{ all -> 0x0041 }
            r0.add(r1)     // Catch:{ all -> 0x0041 }
        L_0x001c:
            boolean r0 = r2.hasResult()     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0031
            boolean r0 = r2.isFinished()     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0031
            boolean r0 = r2.wasCancelled()     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r0 = 0
            goto L_0x0032
        L_0x0031:
            r0 = 1
        L_0x0032:
            monitor-exit(r2)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0040
            boolean r0 = r2.hasFailed()
            boolean r1 = r2.wasCancelled()
            r2.notifyDataSubscriber(r3, r4, r0, r1)
        L_0x0040:
            return
        L_0x0041:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0041 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.subscribe(com.facebook.datasource.DataSubscriber, java.util.concurrent.Executor):void");
    }

    private void notifyDataSubscribers() {
        boolean hasFailed = hasFailed();
        boolean wasCancelled = wasCancelled();
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            notifyDataSubscriber((DataSubscriber) next.first, (Executor) next.second, hasFailed, wasCancelled);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDataSubscriber(final DataSubscriber<T> dataSubscriber, Executor executor, final boolean z, final boolean z2) {
        Runnable r0 = new Runnable() {
            public void run() {
                if (z) {
                    dataSubscriber.onFailure(AbstractDataSource.this);
                } else if (z2) {
                    dataSubscriber.onCancellation(AbstractDataSource.this);
                } else {
                    dataSubscriber.onNewResult(AbstractDataSource.this);
                }
            }
        };
        DataSourceInstrumenter dataSourceInstrumenter = getDataSourceInstrumenter();
        if (dataSourceInstrumenter != null) {
            r0 = dataSourceInstrumenter.decorateRunnable(r0, "AbstractDataSource_notifyDataSubscriber");
        }
        executor.execute(r0);
    }

    private synchronized boolean wasCancelled() {
        return isClosed() && !isFinished();
    }

    /* access modifiers changed from: protected */
    public boolean setResult(@Nullable T t, boolean z, @Nullable Map<String, Object> map) {
        setExtras(map);
        boolean resultInternal = setResultInternal(t, z);
        if (resultInternal) {
            notifyDataSubscribers();
        }
        return resultInternal;
    }

    public boolean setResult(@Nullable T t, boolean z) {
        return setResult(t, z, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public boolean setFailure(Throwable th) {
        return setFailure(th, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public boolean setFailure(@Nullable Throwable th, @Nullable Map<String, Object> map) {
        boolean failureInternal = setFailureInternal(th, map);
        if (failureInternal) {
            notifyDataSubscribers();
        }
        return failureInternal;
    }

    /* access modifiers changed from: protected */
    public boolean setProgress(float f) {
        boolean progressInternal = setProgressInternal(f);
        if (progressInternal) {
            notifyProgressUpdate();
        }
        return progressInternal;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0026, code lost:
        closeResult(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return false;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0023=Splitter:B:19:0x0023, B:23:0x002b=Splitter:B:23:0x002b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean setResultInternal(@javax.annotation.Nullable T r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x003a }
            boolean r1 = r3.mIsClosed     // Catch:{ all -> 0x0037 }
            if (r1 != 0) goto L_0x002b
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = r3.mDataSourceStatus     // Catch:{ all -> 0x0037 }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x0037 }
            if (r1 == r2) goto L_0x000d
            goto L_0x002b
        L_0x000d:
            if (r5 == 0) goto L_0x0017
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r5 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.SUCCESS     // Catch:{ all -> 0x0037 }
            r3.mDataSourceStatus = r5     // Catch:{ all -> 0x0037 }
            r5 = 1065353216(0x3f800000, float:1.0)
            r3.mProgress = r5     // Catch:{ all -> 0x0037 }
        L_0x0017:
            T r5 = r3.mResult     // Catch:{ all -> 0x0037 }
            if (r5 == r4) goto L_0x0022
            r3.mResult = r4     // Catch:{ all -> 0x001f }
            r4 = r5
            goto L_0x0023
        L_0x001f:
            r4 = move-exception
            r0 = r5
            goto L_0x0038
        L_0x0022:
            r4 = r0
        L_0x0023:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0029
            r3.closeResult(r4)
        L_0x0029:
            r4 = 1
            return r4
        L_0x002b:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0031
            r3.closeResult(r4)
        L_0x0031:
            r4 = 0
            return r4
        L_0x0033:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x0038
        L_0x0037:
            r4 = move-exception
        L_0x0038:
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            throw r4     // Catch:{ all -> 0x003a }
        L_0x003a:
            r4 = move-exception
            if (r0 == 0) goto L_0x0040
            r3.closeResult(r0)
        L_0x0040:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setResultInternal(java.lang.Object, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean setFailureInternal(@javax.annotation.Nullable java.lang.Throwable r3, @javax.annotation.Nullable java.util.Map<java.lang.String, java.lang.Object> r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mIsClosed     // Catch:{ all -> 0x001a }
            if (r0 != 0) goto L_0x0017
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r2.mDataSourceStatus     // Catch:{ all -> 0x001a }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r1 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x001a }
            if (r0 == r1) goto L_0x000c
            goto L_0x0017
        L_0x000c:
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.FAILURE     // Catch:{ all -> 0x001a }
            r2.mDataSourceStatus = r0     // Catch:{ all -> 0x001a }
            r2.mFailureThrowable = r3     // Catch:{ all -> 0x001a }
            r2.mExtras = r4     // Catch:{ all -> 0x001a }
            monitor-exit(r2)
            r3 = 1
            return r3
        L_0x0017:
            monitor-exit(r2)
            r3 = 0
            return r3
        L_0x001a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setFailureInternal(java.lang.Throwable, java.util.Map):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean setProgressInternal(float r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mIsClosed     // Catch:{ all -> 0x001c }
            r1 = 0
            if (r0 != 0) goto L_0x001a
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r0 = r3.mDataSourceStatus     // Catch:{ all -> 0x001c }
            com.facebook.datasource.AbstractDataSource$DataSourceStatus r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS     // Catch:{ all -> 0x001c }
            if (r0 == r2) goto L_0x000d
            goto L_0x001a
        L_0x000d:
            float r0 = r3.mProgress     // Catch:{ all -> 0x001c }
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0015
            monitor-exit(r3)
            return r1
        L_0x0015:
            r3.mProgress = r4     // Catch:{ all -> 0x001c }
            monitor-exit(r3)
            r4 = 1
            return r4
        L_0x001a:
            monitor-exit(r3)
            return r1
        L_0x001c:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.setProgressInternal(float):boolean");
    }

    /* access modifiers changed from: protected */
    public void notifyProgressUpdate() {
        Iterator<Pair<DataSubscriber<T>, Executor>> it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            final DataSubscriber dataSubscriber = (DataSubscriber) next.first;
            ((Executor) next.second).execute(new Runnable() {
                public void run() {
                    dataSubscriber.onProgressUpdate(AbstractDataSource.this);
                }
            });
        }
    }

    @Nullable
    public static DataSourceInstrumenter getDataSourceInstrumenter() {
        return sDataSourceInstrumenter;
    }
}
