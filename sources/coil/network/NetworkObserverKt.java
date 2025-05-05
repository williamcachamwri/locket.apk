package coil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.core.content.ContextCompat;
import coil.network.NetworkObserver;
import coil.util.Contexts;
import coil.util.Logger;
import coil.util.Logs;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"TAG", "", "NetworkObserver", "Lcoil/network/NetworkObserver;", "context", "Landroid/content/Context;", "listener", "Lcoil/network/NetworkObserver$Listener;", "logger", "Lcoil/util/Logger;", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkObserver.kt */
public final class NetworkObserverKt {
    private static final String TAG = "NetworkObserver";

    public static final NetworkObserver NetworkObserver(Context context, NetworkObserver.Listener listener, Logger logger) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
        if (connectivityManager == null || !Contexts.isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            if (logger != null && logger.getLevel() <= 5) {
                logger.log(TAG, 5, "Unable to register network observer.", (Throwable) null);
            }
            return new EmptyNetworkObserver();
        }
        try {
            return new RealNetworkObserver(connectivityManager, listener);
        } catch (Exception e) {
            if (logger != null) {
                Logs.log(logger, TAG, new RuntimeException("Failed to register network observer.", e));
            }
            return new EmptyNetworkObserver();
        }
    }
}
