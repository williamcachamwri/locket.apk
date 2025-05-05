package androidx.media3.session;

import androidx.media3.session.ConnectedControllersManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConnectedControllersManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ConnectedControllersManager f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ ConnectedControllersManager.ConnectedControllerRecord f$2;
    public final /* synthetic */ AtomicBoolean f$3;

    public /* synthetic */ ConnectedControllersManager$$ExternalSyntheticLambda3(ConnectedControllersManager connectedControllersManager, AtomicBoolean atomicBoolean, ConnectedControllersManager.ConnectedControllerRecord connectedControllerRecord, AtomicBoolean atomicBoolean2) {
        this.f$0 = connectedControllersManager;
        this.f$1 = atomicBoolean;
        this.f$2 = connectedControllerRecord;
        this.f$3 = atomicBoolean2;
    }

    public final void run() {
        this.f$0.m912lambda$flushCommandQueue$2$androidxmedia3sessionConnectedControllersManager(this.f$1, this.f$2, this.f$3);
    }
}
