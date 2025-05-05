package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda4(boolean z) {
        this.f$0 = z;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setShuffleModeEnabled(this.f$0);
    }
}
