package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda23 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda23(int i) {
        this.f$0 = i;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setDeviceVolume(this.f$0);
    }
}
