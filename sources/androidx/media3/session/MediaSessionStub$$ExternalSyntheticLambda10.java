package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda10 implements Consumer {
    public final /* synthetic */ long f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda10(long j) {
        this.f$0 = j;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).seekTo(this.f$0);
    }
}
