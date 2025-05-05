package com.brentvatne.exoplayer;

import androidx.media3.datasource.AssetDataSource;
import androidx.media3.datasource.DataSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DataSourceUtil$$ExternalSyntheticLambda0 implements DataSource.Factory {
    public final /* synthetic */ AssetDataSource f$0;

    public /* synthetic */ DataSourceUtil$$ExternalSyntheticLambda0(AssetDataSource assetDataSource) {
        this.f$0 = assetDataSource;
    }

    public final DataSource createDataSource() {
        return DataSourceUtil.buildAssetDataSourceFactory$lambda$0(this.f$0);
    }
}
