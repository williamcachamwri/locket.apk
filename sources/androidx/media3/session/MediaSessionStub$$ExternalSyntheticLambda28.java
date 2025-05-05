package androidx.media3.session;

import android.view.Surface;
import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda28 implements Consumer {
    public final /* synthetic */ Surface f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda28(Surface surface) {
        this.f$0 = surface;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setVideoSurface(this.f$0);
    }
}
