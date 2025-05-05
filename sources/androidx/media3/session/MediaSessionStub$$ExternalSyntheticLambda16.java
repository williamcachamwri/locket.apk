package androidx.media3.session;

import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda16 implements Consumer {
    public final /* synthetic */ PlaybackParameters f$0;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda16(PlaybackParameters playbackParameters) {
        this.f$0 = playbackParameters;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setPlaybackParameters(this.f$0);
    }
}
