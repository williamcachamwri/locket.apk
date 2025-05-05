package com.facebook.datasource;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.util.List;
import javax.annotation.Nullable;

public class FirstAvailableDataSourceSupplier<T> implements Supplier<DataSource<T>> {
    /* access modifiers changed from: private */
    public final List<Supplier<DataSource<T>>> mDataSourceSuppliers;

    private FirstAvailableDataSourceSupplier(List<Supplier<DataSource<T>>> list) {
        Preconditions.checkArgument(!list.isEmpty(), "List of suppliers is empty!");
        this.mDataSourceSuppliers = list;
    }

    public static <T> FirstAvailableDataSourceSupplier<T> create(List<Supplier<DataSource<T>>> list) {
        return new FirstAvailableDataSourceSupplier<>(list);
    }

    public DataSource<T> get() {
        return new FirstAvailableDataSource();
    }

    public int hashCode() {
        return this.mDataSourceSuppliers.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FirstAvailableDataSourceSupplier)) {
            return false;
        }
        return Objects.equal(this.mDataSourceSuppliers, ((FirstAvailableDataSourceSupplier) obj).mDataSourceSuppliers);
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("list", (Object) this.mDataSourceSuppliers).toString();
    }

    private class FirstAvailableDataSource extends AbstractDataSource<T> {
        @Nullable
        private DataSource<T> mCurrentDataSource = null;
        @Nullable
        private DataSource<T> mDataSourceWithResult = null;
        private int mIndex = 0;

        public FirstAvailableDataSource() {
            if (!startNextDataSource()) {
                setFailure(new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        @Nullable
        public synchronized T getResult() {
            DataSource dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null ? dataSourceWithResult.getResult() : null;
        }

        public synchronized boolean hasResult() {
            DataSource dataSourceWithResult;
            dataSourceWithResult = getDataSourceWithResult();
            return dataSourceWithResult != null && dataSourceWithResult.hasResult();
        }

        public boolean close() {
            synchronized (this) {
                if (!super.close()) {
                    return false;
                }
                DataSource<T> dataSource = this.mCurrentDataSource;
                this.mCurrentDataSource = null;
                DataSource<T> dataSource2 = this.mDataSourceWithResult;
                this.mDataSourceWithResult = null;
                closeSafely(dataSource2);
                closeSafely(dataSource);
                return true;
            }
        }

        private boolean startNextDataSource() {
            Supplier nextSupplier = getNextSupplier();
            DataSource dataSource = nextSupplier != null ? (DataSource) nextSupplier.get() : null;
            if (!setCurrentDataSource(dataSource) || dataSource == null) {
                closeSafely(dataSource);
                return false;
            }
            dataSource.subscribe(new InternalDataSubscriber(), CallerThreadExecutor.getInstance());
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
            return null;
         */
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized com.facebook.common.internal.Supplier<com.facebook.datasource.DataSource<T>> getNextSupplier() {
            /*
                r3 = this;
                monitor-enter(r3)
                boolean r0 = r3.isClosed()     // Catch:{ all -> 0x002c }
                if (r0 != 0) goto L_0x0029
                int r0 = r3.mIndex     // Catch:{ all -> 0x002c }
                com.facebook.datasource.FirstAvailableDataSourceSupplier r1 = com.facebook.datasource.FirstAvailableDataSourceSupplier.this     // Catch:{ all -> 0x002c }
                java.util.List r1 = r1.mDataSourceSuppliers     // Catch:{ all -> 0x002c }
                int r1 = r1.size()     // Catch:{ all -> 0x002c }
                if (r0 >= r1) goto L_0x0029
                com.facebook.datasource.FirstAvailableDataSourceSupplier r0 = com.facebook.datasource.FirstAvailableDataSourceSupplier.this     // Catch:{ all -> 0x002c }
                java.util.List r0 = r0.mDataSourceSuppliers     // Catch:{ all -> 0x002c }
                int r1 = r3.mIndex     // Catch:{ all -> 0x002c }
                int r2 = r1 + 1
                r3.mIndex = r2     // Catch:{ all -> 0x002c }
                java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x002c }
                com.facebook.common.internal.Supplier r0 = (com.facebook.common.internal.Supplier) r0     // Catch:{ all -> 0x002c }
                monitor-exit(r3)
                return r0
            L_0x0029:
                monitor-exit(r3)
                r0 = 0
                return r0
            L_0x002c:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.getNextSupplier():com.facebook.common.internal.Supplier");
        }

        private synchronized boolean setCurrentDataSource(DataSource<T> dataSource) {
            if (isClosed()) {
                return false;
            }
            this.mCurrentDataSource = dataSource;
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized boolean clearCurrentDataSource(com.facebook.datasource.DataSource<T> r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.isClosed()     // Catch:{ all -> 0x0015 }
                if (r0 != 0) goto L_0x0012
                com.facebook.datasource.DataSource<T> r0 = r1.mCurrentDataSource     // Catch:{ all -> 0x0015 }
                if (r2 == r0) goto L_0x000c
                goto L_0x0012
            L_0x000c:
                r2 = 0
                r1.mCurrentDataSource = r2     // Catch:{ all -> 0x0015 }
                monitor-exit(r1)
                r2 = 1
                return r2
            L_0x0012:
                monitor-exit(r1)
                r2 = 0
                return r2
            L_0x0015:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.clearCurrentDataSource(com.facebook.datasource.DataSource):boolean");
        }

        @Nullable
        private synchronized DataSource<T> getDataSourceWithResult() {
            return this.mDataSourceWithResult;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0014, code lost:
            closeSafely(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0017, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void maybeSetDataSourceWithResult(com.facebook.datasource.DataSource<T> r2, boolean r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                com.facebook.datasource.DataSource<T> r0 = r1.mCurrentDataSource     // Catch:{ all -> 0x001a }
                if (r2 != r0) goto L_0x0018
                com.facebook.datasource.DataSource<T> r0 = r1.mDataSourceWithResult     // Catch:{ all -> 0x001a }
                if (r2 != r0) goto L_0x000a
                goto L_0x0018
            L_0x000a:
                if (r0 == 0) goto L_0x0011
                if (r3 == 0) goto L_0x000f
                goto L_0x0011
            L_0x000f:
                r0 = 0
                goto L_0x0013
            L_0x0011:
                r1.mDataSourceWithResult = r2     // Catch:{ all -> 0x001a }
            L_0x0013:
                monitor-exit(r1)     // Catch:{ all -> 0x001a }
                r1.closeSafely(r0)
                return
            L_0x0018:
                monitor-exit(r1)     // Catch:{ all -> 0x001a }
                return
            L_0x001a:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x001a }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.FirstAvailableDataSourceSupplier.FirstAvailableDataSource.maybeSetDataSourceWithResult(com.facebook.datasource.DataSource, boolean):void");
        }

        /* access modifiers changed from: private */
        public void onDataSourceFailed(DataSource<T> dataSource) {
            if (clearCurrentDataSource(dataSource)) {
                if (dataSource != getDataSourceWithResult()) {
                    closeSafely(dataSource);
                }
                if (!startNextDataSource()) {
                    setFailure(dataSource.getFailureCause(), dataSource.getExtras());
                }
            }
        }

        /* access modifiers changed from: private */
        public void onDataSourceNewResult(DataSource<T> dataSource) {
            maybeSetDataSourceWithResult(dataSource, dataSource.isFinished());
            if (dataSource == getDataSourceWithResult()) {
                setResult(null, dataSource.isFinished(), dataSource.getExtras());
            }
        }

        private void closeSafely(@Nullable DataSource<T> dataSource) {
            if (dataSource != null) {
                dataSource.close();
            }
        }

        private class InternalDataSubscriber implements DataSubscriber<T> {
            public void onCancellation(DataSource<T> dataSource) {
            }

            private InternalDataSubscriber() {
            }

            public void onFailure(DataSource<T> dataSource) {
                FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
            }

            public void onNewResult(DataSource<T> dataSource) {
                if (dataSource.hasResult()) {
                    FirstAvailableDataSource.this.onDataSourceNewResult(dataSource);
                } else if (dataSource.isFinished()) {
                    FirstAvailableDataSource.this.onDataSourceFailed(dataSource);
                }
            }

            public void onProgressUpdate(DataSource<T> dataSource) {
                FirstAvailableDataSource.this.setProgress(Math.max(FirstAvailableDataSource.this.getProgress(), dataSource.getProgress()));
            }
        }
    }
}
