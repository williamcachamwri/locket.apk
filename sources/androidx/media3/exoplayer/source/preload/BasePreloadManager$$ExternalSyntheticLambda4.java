package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.BasePreloadManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BasePreloadManager$$ExternalSyntheticLambda4 implements ListenerSet.Event {
    public final /* synthetic */ MediaSource f$0;

    public /* synthetic */ BasePreloadManager$$ExternalSyntheticLambda4(MediaSource mediaSource) {
        this.f$0 = mediaSource;
    }

    public final void invoke(Object obj) {
        ((BasePreloadManager.Listener) obj).onCompleted(this.f$0.getMediaItem());
    }
}
