package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda53 implements Consumer {
    public final /* synthetic */ float f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda53(float f) {
        this.f$0 = f;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setPlaybackSpeed(this.f$0);
    }
}
