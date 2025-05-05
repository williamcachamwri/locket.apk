package androidx.media3.exoplayer.rtsp;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;

public class RtspMediaSource {

    public static class Factory implements MediaSource.Factory {
        public MediaSource createMediaSource(MediaItem mediaItem) {
            return null;
        }

        public int[] getSupportedTypes() {
            return new int[0];
        }

        public MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider drmSessionManagerProvider) {
            return null;
        }

        public MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
            return null;
        }
    }
}
