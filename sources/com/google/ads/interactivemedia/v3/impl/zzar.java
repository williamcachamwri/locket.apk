package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzar extends zzbw {
    protected final ContentProgressProvider zza;

    zzar(ContentProgressProvider contentProgressProvider, long j) {
        super(200);
        this.zza = contentProgressProvider;
    }

    public final VideoProgressUpdate zza() {
        VideoProgressUpdate contentProgress = this.zza.getContentProgress();
        if (contentProgress != null) {
            return contentProgress;
        }
        SentryLogcatAdapter.w("IMASDK", "ContentProgressProvider.getContentProgress() is null. Use VideoProgressUpdate.VIDEO_TIME_NOT_READY instead.");
        return VideoProgressUpdate.VIDEO_TIME_NOT_READY;
    }
}
