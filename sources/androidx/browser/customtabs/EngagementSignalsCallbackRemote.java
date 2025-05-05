package androidx.browser.customtabs;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.IEngagementSignalsCallback;
import io.sentry.android.core.SentryLogcatAdapter;

final class EngagementSignalsCallbackRemote implements EngagementSignalsCallback {
    private static final String TAG = "EngagementSigsCallbkRmt";
    private final IEngagementSignalsCallback mCallbackBinder;

    private EngagementSignalsCallbackRemote(IEngagementSignalsCallback iEngagementSignalsCallback) {
        this.mCallbackBinder = iEngagementSignalsCallback;
    }

    static EngagementSignalsCallbackRemote fromBinder(IBinder iBinder) {
        return new EngagementSignalsCallbackRemote(IEngagementSignalsCallback.Stub.asInterface(iBinder));
    }

    public void onVerticalScrollEvent(boolean z, Bundle bundle) {
        try {
            this.mCallbackBinder.onVerticalScrollEvent(z, bundle);
        } catch (RemoteException unused) {
            SentryLogcatAdapter.e(TAG, "RemoteException during IEngagementSignalsCallback transaction");
        }
    }

    public void onGreatestScrollPercentageIncreased(int i, Bundle bundle) {
        try {
            this.mCallbackBinder.onGreatestScrollPercentageIncreased(i, bundle);
        } catch (RemoteException unused) {
            SentryLogcatAdapter.e(TAG, "RemoteException during IEngagementSignalsCallback transaction");
        }
    }

    public void onSessionEnded(boolean z, Bundle bundle) {
        try {
            this.mCallbackBinder.onSessionEnded(z, bundle);
        } catch (RemoteException unused) {
            SentryLogcatAdapter.e(TAG, "RemoteException during IEngagementSignalsCallback transaction");
        }
    }
}
