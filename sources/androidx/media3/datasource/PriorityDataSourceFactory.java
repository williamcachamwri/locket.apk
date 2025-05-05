package androidx.media3.datasource;

import androidx.media3.common.PriorityTaskManager;
import androidx.media3.datasource.DataSource;

@Deprecated
public final class PriorityDataSourceFactory implements DataSource.Factory {
    private final int priority;
    private final PriorityTaskManager priorityTaskManager;
    private final DataSource.Factory upstreamFactory;

    public PriorityDataSourceFactory(DataSource.Factory factory, PriorityTaskManager priorityTaskManager2, int i) {
        this.upstreamFactory = factory;
        this.priorityTaskManager = priorityTaskManager2;
        this.priority = i;
    }

    public PriorityDataSource createDataSource() {
        return new PriorityDataSource(this.upstreamFactory.createDataSource(), this.priorityTaskManager, this.priority);
    }
}
