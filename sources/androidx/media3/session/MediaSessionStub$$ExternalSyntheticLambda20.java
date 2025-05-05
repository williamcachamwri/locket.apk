package androidx.media3.session;

import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda20 implements Consumer {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda20(int i, int i2, int i3) {
        this.f$0 = i;
        this.f$1 = i2;
        this.f$2 = i3;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).moveMediaItems(this.f$0, this.f$1, this.f$2);
    }
}
