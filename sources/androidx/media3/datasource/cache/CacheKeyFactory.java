package androidx.media3.datasource.cache;

import androidx.media3.datasource.DataSpec;

public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = new CacheKeyFactory$$ExternalSyntheticLambda0();

    String buildCacheKey(DataSpec dataSpec);

    static /* synthetic */ String lambda$static$0(DataSpec dataSpec) {
        return dataSpec.key != null ? dataSpec.key : dataSpec.uri.toString();
    }
}
