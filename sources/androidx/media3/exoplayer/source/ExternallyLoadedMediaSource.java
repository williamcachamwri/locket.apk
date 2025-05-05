package androidx.media3.exoplayer.source;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.util.Objects;

public final class ExternallyLoadedMediaSource extends BaseMediaSource {
    private final ExternalLoader externalLoader;
    private MediaItem mediaItem;
    private final long timelineDurationUs;

    public void maybeThrowSourceInfoRefreshError() {
    }

    /* access modifiers changed from: protected */
    public void releaseSourceInternal() {
    }

    public static final class Factory implements MediaSource.Factory {
        private final ExternalLoader externalLoader;
        private final long timelineDurationUs;

        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            return this;
        }

        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return this;
        }

        public Factory(long j, ExternalLoader externalLoader2) {
            this.timelineDurationUs = j;
            this.externalLoader = externalLoader2;
        }

        public int[] getSupportedTypes() {
            return new int[]{4};
        }

        public ExternallyLoadedMediaSource createMediaSource(MediaItem mediaItem) {
            return new ExternallyLoadedMediaSource(mediaItem, this.timelineDurationUs, this.externalLoader);
        }
    }

    private ExternallyLoadedMediaSource(MediaItem mediaItem2, long j, ExternalLoader externalLoader2) {
        this.mediaItem = mediaItem2;
        this.timelineDurationUs = j;
        this.externalLoader = externalLoader2;
    }

    /* access modifiers changed from: protected */
    public void prepareSourceInternal(TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.timelineDurationUs, true, false, false, (Object) null, getMediaItem()));
    }

    public synchronized MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public boolean canUpdateMediaItem(MediaItem mediaItem2) {
        MediaItem.LocalConfiguration localConfiguration = mediaItem2.localConfiguration;
        MediaItem.LocalConfiguration localConfiguration2 = (MediaItem.LocalConfiguration) Assertions.checkNotNull(getMediaItem().localConfiguration);
        return localConfiguration != null && localConfiguration.uri.equals(localConfiguration2.uri) && Objects.equals(localConfiguration.mimeType, localConfiguration2.mimeType) && (localConfiguration.imageDurationMs == C.TIME_UNSET || Util.msToUs(localConfiguration.imageDurationMs) == this.timelineDurationUs);
    }

    public synchronized void updateMediaItem(MediaItem mediaItem2) {
        this.mediaItem = mediaItem2;
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaItem mediaItem2 = getMediaItem();
        Assertions.checkNotNull(mediaItem2.localConfiguration);
        Assertions.checkNotNull(mediaItem2.localConfiguration.mimeType, "Externally loaded mediaItems require a MIME type.");
        return new ExternallyLoadedMediaPeriod(mediaItem2.localConfiguration.uri, mediaItem2.localConfiguration.mimeType, this.externalLoader);
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExternallyLoadedMediaPeriod) mediaPeriod).releasePeriod();
    }
}
