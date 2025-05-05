package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda19 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda19(int i) {
        this.f$0 = i;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).increaseDeviceVolume(this.f$0);
    }
}
