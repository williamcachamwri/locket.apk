package androidx.media3.session;

import android.os.ResultReceiver;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionLegacyStub$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ ResultReceiver f$1;

    public /* synthetic */ MediaSessionLegacyStub$$ExternalSyntheticLambda0(ListenableFuture listenableFuture, ResultReceiver resultReceiver) {
        this.f$0 = listenableFuture;
        this.f$1 = resultReceiver;
    }

    public final void run() {
        MediaSessionLegacyStub.lambda$sendCustomCommandResultWhenReady$27(this.f$0, this.f$1);
    }
}
