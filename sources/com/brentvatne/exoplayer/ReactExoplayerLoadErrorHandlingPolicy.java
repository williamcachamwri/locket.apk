package com.brentvatne.exoplayer;

import androidx.media3.common.C;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/brentvatne/exoplayer/ReactExoplayerLoadErrorHandlingPolicy;", "Landroidx/media3/exoplayer/upstream/DefaultLoadErrorHandlingPolicy;", "minLoadRetryCount", "", "(I)V", "getMinimumLoadableRetryCount", "dataType", "getRetryDelayMsFor", "", "loadErrorInfo", "Landroidx/media3/exoplayer/upstream/LoadErrorHandlingPolicy$LoadErrorInfo;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactExoplayerLoadErrorHandlingPolicy.kt */
public final class ReactExoplayerLoadErrorHandlingPolicy extends DefaultLoadErrorHandlingPolicy {
    private final int minLoadRetryCount;

    public int getMinimumLoadableRetryCount(int i) {
        return Integer.MAX_VALUE;
    }

    public ReactExoplayerLoadErrorHandlingPolicy(int i) {
        super(i);
        this.minLoadRetryCount = i;
    }

    public long getRetryDelayMsFor(LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo) {
        Intrinsics.checkNotNullParameter(loadErrorInfo, "loadErrorInfo");
        String message = loadErrorInfo.exception.getMessage();
        if (!(loadErrorInfo.exception instanceof HttpDataSource.HttpDataSourceException) || message == null || (!Intrinsics.areEqual((Object) message, (Object) "Unable to connect") && !Intrinsics.areEqual((Object) message, (Object) "Software caused connection abort"))) {
            return loadErrorInfo.errorCount < this.minLoadRetryCount ? Math.min(((long) (loadErrorInfo.errorCount - 1)) * 1000, 5000) : C.TIME_UNSET;
        }
        return 1000;
    }
}
