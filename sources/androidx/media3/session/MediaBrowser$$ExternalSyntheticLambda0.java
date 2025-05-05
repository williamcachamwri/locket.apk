package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaBrowser$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ MediaBrowser.Listener f$1;

    public /* synthetic */ MediaBrowser$$ExternalSyntheticLambda0(Consumer consumer, MediaBrowser.Listener listener) {
        this.f$0 = consumer;
        this.f$1 = listener;
    }

    public final void run() {
        this.f$0.accept(this.f$1);
    }
}
