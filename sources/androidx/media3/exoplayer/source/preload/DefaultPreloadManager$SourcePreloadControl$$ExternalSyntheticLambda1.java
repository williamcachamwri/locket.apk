package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import com.google.common.base.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultPreloadManager$SourcePreloadControl$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ long f$0;

    public /* synthetic */ DefaultPreloadManager$SourcePreloadControl$$ExternalSyntheticLambda1(long j) {
        this.f$0 = j;
    }

    public final boolean apply(Object obj) {
        return DefaultPreloadManager.SourcePreloadControl.lambda$onContinueLoadingRequested$2(this.f$0, (DefaultPreloadManager.Status) obj);
    }
}
