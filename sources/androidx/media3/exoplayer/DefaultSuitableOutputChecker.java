package androidx.media3.exoplayer;

import android.content.Context;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.RouteDiscoveryPreference;
import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.SuitableOutputChecker;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.Executor;

final class DefaultSuitableOutputChecker implements SuitableOutputChecker {
    private static final RouteDiscoveryPreference EMPTY_DISCOVERY_PREFERENCE = new RouteDiscoveryPreference.Builder(ImmutableList.of(), false).build();
    private MediaRouter2.ControllerCallback controllerCallback;
    private final Executor executor;
    /* access modifiers changed from: private */
    public boolean isPreviousSelectedOutputSuitableForPlayback;
    private final MediaRouter2.RouteCallback routeCallback = new MediaRouter2.RouteCallback() {
    };
    private final MediaRouter2 router;

    public DefaultSuitableOutputChecker(Context context, final Handler handler) {
        this.router = MediaRouter2.getInstance(context);
        this.executor = new Executor() {
            public void execute(Runnable runnable) {
                Util.postOrRun(handler, runnable);
            }
        };
    }

    public void enable(final SuitableOutputChecker.Callback callback) {
        this.router.registerRouteCallback(this.executor, this.routeCallback, EMPTY_DISCOVERY_PREFERENCE);
        AnonymousClass3 r0 = new MediaRouter2.ControllerCallback() {
            public void onControllerUpdated(MediaRouter2.RoutingController routingController) {
                boolean isSelectedOutputSuitableForPlayback = DefaultSuitableOutputChecker.this.isSelectedOutputSuitableForPlayback();
                if (DefaultSuitableOutputChecker.this.isPreviousSelectedOutputSuitableForPlayback != isSelectedOutputSuitableForPlayback) {
                    boolean unused = DefaultSuitableOutputChecker.this.isPreviousSelectedOutputSuitableForPlayback = isSelectedOutputSuitableForPlayback;
                    callback.onSelectedOutputSuitabilityChanged(isSelectedOutputSuitableForPlayback);
                }
            }
        };
        this.controllerCallback = r0;
        this.router.registerControllerCallback(this.executor, r0);
        this.isPreviousSelectedOutputSuitableForPlayback = isSelectedOutputSuitableForPlayback();
    }

    public void disable() {
        Assertions.checkStateNotNull(this.controllerCallback, "SuitableOutputChecker is not enabled");
        this.router.unregisterControllerCallback(this.controllerCallback);
        this.controllerCallback = null;
        this.router.unregisterRouteCallback(this.routeCallback);
    }

    public boolean isSelectedOutputSuitableForPlayback() {
        Assertions.checkStateNotNull(this.controllerCallback, "SuitableOutputChecker is not enabled");
        int transferReason = this.router.getSystemController().getRoutingSessionInfo().getTransferReason();
        boolean wasTransferInitiatedBySelf = this.router.getSystemController().wasTransferInitiatedBySelf();
        for (MediaRoute2Info isRouteSuitableForMediaPlayback : this.router.getSystemController().getSelectedRoutes()) {
            if (isRouteSuitableForMediaPlayback(isRouteSuitableForMediaPlayback, transferReason, wasTransferInitiatedBySelf)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRouteSuitableForMediaPlayback(MediaRoute2Info mediaRoute2Info, int i, boolean z) {
        int suitabilityStatus = mediaRoute2Info.getSuitabilityStatus();
        return suitabilityStatus == 1 ? (i == 1 || i == 2) && z : suitabilityStatus == 0;
    }
}
