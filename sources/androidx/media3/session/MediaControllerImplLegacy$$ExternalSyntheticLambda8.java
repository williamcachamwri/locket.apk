package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaControllerImplLegacy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaControllerImplLegacy$$ExternalSyntheticLambda8 implements ListenerSet.Event {
    public final /* synthetic */ MediaControllerImplLegacy.ControllerInfo f$0;

    public /* synthetic */ MediaControllerImplLegacy$$ExternalSyntheticLambda8(MediaControllerImplLegacy.ControllerInfo controllerInfo) {
        this.f$0 = controllerInfo;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onPlaylistMetadataChanged(this.f$0.playerInfo.playlistMetadata);
    }
}
