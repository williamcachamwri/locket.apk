package androidx.media3.common.util;

import androidx.media3.common.util.NetworkTypeObserver;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NetworkTypeObserver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NetworkTypeObserver f$0;
    public final /* synthetic */ NetworkTypeObserver.Listener f$1;

    public /* synthetic */ NetworkTypeObserver$$ExternalSyntheticLambda0(NetworkTypeObserver networkTypeObserver, NetworkTypeObserver.Listener listener) {
        this.f$0 = networkTypeObserver;
        this.f$1 = listener;
    }

    public final void run() {
        this.f$0.m403lambda$register$0$androidxmedia3commonutilNetworkTypeObserver(this.f$1);
    }
}
