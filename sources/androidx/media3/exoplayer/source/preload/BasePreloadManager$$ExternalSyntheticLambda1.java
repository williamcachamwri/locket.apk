package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.source.preload.BasePreloadManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BasePreloadManager$$ExternalSyntheticLambda1 implements ListenerSet.Event {
    public final /* synthetic */ PreloadException f$0;

    public /* synthetic */ BasePreloadManager$$ExternalSyntheticLambda1(PreloadException preloadException) {
        this.f$0 = preloadException;
    }

    public final void invoke(Object obj) {
        ((BasePreloadManager.Listener) obj).onError(this.f$0);
    }
}
