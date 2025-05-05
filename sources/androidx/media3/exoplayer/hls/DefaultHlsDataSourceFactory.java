package androidx.media3.exoplayer.hls;

import androidx.media3.datasource.DataSource;

public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(DataSource.Factory factory) {
        this.dataSourceFactory = factory;
    }

    public DataSource createDataSource(int i) {
        return this.dataSourceFactory.createDataSource();
    }
}
