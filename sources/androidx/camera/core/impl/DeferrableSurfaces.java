package androidx.camera.core.impl;

import android.view.Surface;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

public final class DeferrableSurfaces {
    private DeferrableSurfaces() {
    }

    public static ListenableFuture<List<Surface>> surfaceListWithTimeout(Collection<DeferrableSurface> collection, boolean z, long j, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        ArrayList arrayList = new ArrayList();
        for (DeferrableSurface surface : collection) {
            arrayList.add(Futures.nonCancellationPropagating(surface.getSurface()));
        }
        return CallbackToFutureAdapter.getFuture(new DeferrableSurfaces$$ExternalSyntheticLambda0(Futures.makeTimeoutFuture(j, scheduledExecutorService, Futures.successfulAsList(arrayList)), executor, z, collection));
    }

    static /* synthetic */ Object lambda$surfaceListWithTimeout$1(ListenableFuture listenableFuture, Executor executor, final boolean z, Collection collection, final CallbackToFutureAdapter.Completer completer) throws Exception {
        completer.addCancellationListener(new DeferrableSurfaces$$ExternalSyntheticLambda1(listenableFuture), executor);
        Futures.addCallback(listenableFuture, new FutureCallback<List<Surface>>() {
            public void onSuccess(List<Surface> list) {
                Preconditions.checkNotNull(list);
                ArrayList arrayList = new ArrayList(list);
                if (z) {
                    arrayList.removeAll(Collections.singleton((Object) null));
                }
                completer.set(arrayList);
            }

            public void onFailure(Throwable th) {
                if (th instanceof TimeoutException) {
                    completer.setException(th);
                } else {
                    completer.set(Collections.emptyList());
                }
            }
        }, executor);
        return "surfaceList[" + collection + "]";
    }

    public static boolean tryIncrementAll(List<DeferrableSurface> list) {
        try {
            incrementAll(list);
            return true;
        } catch (DeferrableSurface.SurfaceClosedException unused) {
            return false;
        }
    }

    public static void incrementAll(List<DeferrableSurface> list) throws DeferrableSurface.SurfaceClosedException {
        if (!list.isEmpty()) {
            int i = 0;
            do {
                try {
                    list.get(i).incrementUseCount();
                    i++;
                } catch (DeferrableSurface.SurfaceClosedException e) {
                    for (int i2 = i - 1; i2 >= 0; i2--) {
                        list.get(i2).decrementUseCount();
                    }
                    throw e;
                }
            } while (i < list.size());
        }
    }

    public static void decrementAll(List<DeferrableSurface> list) {
        for (DeferrableSurface decrementUseCount : list) {
            decrementUseCount.decrementUseCount();
        }
    }
}
