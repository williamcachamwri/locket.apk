package androidx.media3.exoplayer.offline;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadHelper$$ExternalSyntheticLambda1 implements DrmSessionManagerProvider {
    public final /* synthetic */ DrmSessionManager f$0;

    public /* synthetic */ DownloadHelper$$ExternalSyntheticLambda1(DrmSessionManager drmSessionManager) {
        this.f$0 = drmSessionManager;
    }

    public final DrmSessionManager get(MediaItem mediaItem) {
        return DownloadHelper.lambda$createMediaSourceInternal$6(this.f$0, mediaItem);
    }
}
