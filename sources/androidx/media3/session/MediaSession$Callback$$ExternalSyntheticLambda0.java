package androidx.media3.session;

import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSession$Callback$$ExternalSyntheticLambda0 implements AsyncFunction {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ MediaSession$Callback$$ExternalSyntheticLambda0(int i, long j) {
        this.f$0 = i;
        this.f$1 = j;
    }

    public final ListenableFuture apply(Object obj) {
        return Futures.immediateFuture(new MediaSession.MediaItemsWithStartPosition((List) obj, this.f$0, this.f$1));
    }
}
