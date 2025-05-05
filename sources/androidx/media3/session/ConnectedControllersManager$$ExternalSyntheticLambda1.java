package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ ConnectedControllersManager.AsyncCommand f$1;
    public final /* synthetic */ AtomicBoolean f$2;
    public final /* synthetic */ ConnectedControllersManager.ConnectedControllerRecord f$3;
    public final /* synthetic */ AtomicBoolean f$4;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda1(ConnectedControllersManager connectedControllersManager, ConnectedControllersManager.AsyncCommand asyncCommand, AtomicBoolean atomicBoolean, ConnectedControllersManager.ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        this.f$0 = connectedControllersManager;
        this.f$1 = asyncCommand;
        this.f$2 = atomicBoolean;
        this.f$3 = connectedControllerRecord;
        this.f$4 = atomicBoolean2;
    }

    public final void run() {
        this.f$0.m913lambda$flushCommandQueue$3$androidxmedia3sessionConnectedControllersManager(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
