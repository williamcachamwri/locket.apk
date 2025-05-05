package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda27 implements Consumer {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda27(boolean z, int i) {
        this.f$0 = z;
        this.f$1 = i;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setDeviceMuted(this.f$0, this.f$1);
    }
}
