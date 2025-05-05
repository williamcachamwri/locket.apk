package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda38 implements Consumer {
    public final /* synthetic */ float f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda38(float f) {
        this.f$0 = f;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setVolume(this.f$0);
    }
}
