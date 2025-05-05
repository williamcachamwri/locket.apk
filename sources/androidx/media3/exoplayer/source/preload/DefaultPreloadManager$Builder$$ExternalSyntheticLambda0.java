package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultPreloadManager$Builder$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ MediaSource.Factory f$0;

    public /* synthetic */ DefaultPreloadManager$Builder$$ExternalSyntheticLambda0(MediaSource.Factory factory) {
        this.f$0 = factory;
    }

    public final Object get() {
        return DefaultPreloadManager.Builder.lambda$setMediaSourceFactory$3(this.f$0);
    }
}
