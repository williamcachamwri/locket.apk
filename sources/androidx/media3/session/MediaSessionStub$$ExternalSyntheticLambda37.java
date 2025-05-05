package androidx.media3.session;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda37 implements Consumer {
    public final /* synthetic */ MediaMetadata f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda37(MediaMetadata mediaMetadata) {
        this.f$0 = mediaMetadata;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setPlaylistMetadata(this.f$0);
    }
}
