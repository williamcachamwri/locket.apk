package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.base.zau;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zabh extends zau {
    final /* synthetic */ zabi zaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabh(zabi zabi, Looper looper) {
        super(looper);
        this.zaa = zabi;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            ((zabg) message.obj).zab(this.zaa);
        } else if (i != 2) {
            int i2 = message.what;
            SentryLogcatAdapter.w("GACStateManager", "Unknown message id: " + i2);
        } else {
            throw ((RuntimeException) message.obj);
        }
    }
}
