package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.ConnectedControllersManager;
import androidx.media3.session.MediaSession;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda2 implements ConnectedControllersManager.AsyncCommand {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ MediaSession.ControllerInfo f$1;
    public final /* synthetic */ Player.Commands f$2;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda2(ConnectedControllersManager connectedControllersManager, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        this.f$0 = connectedControllersManager;
        this.f$1 = controllerInfo;
        this.f$2 = commands;
    }

    public final ListenableFuture run() {
        return this.f$0.m911lambda$flushCommandQueue$1$androidxmedia3sessionConnectedControllersManager(this.f$1, this.f$2);
    }
}
