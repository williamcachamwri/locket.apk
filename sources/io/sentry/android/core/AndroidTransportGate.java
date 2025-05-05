package io.sentry.android.core;

import android.content.Context;
import io.sentry.ILogger;
import io.sentry.android.core.internal.util.ConnectivityChecker;
import io.sentry.transport.ITransportGate;

final class AndroidTransportGate implements ITransportGate {
    private final Context context;
    private final ILogger logger;

    AndroidTransportGate(Context context2, ILogger iLogger) {
        this.context = context2;
        this.logger = iLogger;
    }

    public boolean isConnected() {
        return isConnected(ConnectivityChecker.getConnectionStatus(this.context, this.logger));
    }

    /* renamed from: io.sentry.android.core.AndroidTransportGate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                io.sentry.android.core.internal.util.ConnectivityChecker$Status[] r0 = io.sentry.android.core.internal.util.ConnectivityChecker.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status = r0
                io.sentry.android.core.internal.util.ConnectivityChecker$Status r1 = io.sentry.android.core.internal.util.ConnectivityChecker.Status.CONNECTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status     // Catch:{ NoSuchFieldError -> 0x001d }
                io.sentry.android.core.internal.util.ConnectivityChecker$Status r1 = io.sentry.android.core.internal.util.ConnectivityChecker.Status.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.sentry.android.core.internal.util.ConnectivityChecker$Status r1 = io.sentry.android.core.internal.util.ConnectivityChecker.Status.NO_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.AndroidTransportGate.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isConnected(ConnectivityChecker.Status status) {
        int i = AnonymousClass1.$SwitchMap$io$sentry$android$core$internal$util$ConnectivityChecker$Status[status.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }
}
