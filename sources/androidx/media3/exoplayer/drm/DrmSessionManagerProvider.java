package androidx.media3.exoplayer.drm;

import androidx.media3.common.MediaItem;

public interface DrmSessionManagerProvider {
    DrmSessionManager get(MediaItem mediaItem);
}
