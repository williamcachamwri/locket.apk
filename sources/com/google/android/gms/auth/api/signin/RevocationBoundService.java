package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zbt;
import io.sentry.android.core.SentryLogcatAdapter;

@Deprecated
/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public final class RevocationBoundService extends Service {
    public IBinder onBind(Intent intent) {
        if ("com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent.getAction()) || "com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent.getAction())) {
            if (Log.isLoggable("RevocationService", 2)) {
                Log.v("RevocationService", "RevocationBoundService handling ".concat(String.valueOf(intent.getAction())));
            }
            return new zbt(this);
        }
        SentryLogcatAdapter.w("RevocationService", "Unknown action sent to RevocationBoundService: ".concat(String.valueOf(intent.getAction())));
        return null;
    }
}
