package com.brentvatne.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactExoplayerView$$ExternalSyntheticLambda10 implements DrmSessionManagerProvider {
    public final /* synthetic */ DrmSessionManager f$0;

    public /* synthetic */ ReactExoplayerView$$ExternalSyntheticLambda10(DrmSessionManager drmSessionManager) {
        this.f$0 = drmSessionManager;
    }

    public final DrmSessionManager get(MediaItem mediaItem) {
        return ReactExoplayerView.lambda$buildMediaSource$16(this.f$0, mediaItem);
    }
}
