package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaal implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference zaa;
    private final Api zab;
    /* access modifiers changed from: private */
    public final boolean zac;

    public zaal(zaaw zaaw, Api api, boolean z) {
        this.zaa = new WeakReference(zaaw);
        this.zab = api;
        this.zac = z;
    }

    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaaw zaaw = (zaaw) this.zaa.get();
        if (zaaw != null) {
            Preconditions.checkState(Looper.myLooper() == zaaw.zaa.zag.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaaw.zab.lock();
            try {
                if (zaaw.zaG(0)) {
                    if (!connectionResult.isSuccess()) {
                        zaaw.zaE(connectionResult, this.zab, this.zac);
                    }
                    if (zaaw.zaH()) {
                        zaaw.zaF();
                    }
                }
            } finally {
                zaaw.zab.unlock();
            }
        }
    }
}
