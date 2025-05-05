package androidx.media3.session;

import android.app.ForegroundServiceStartNotAllowedException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import androidx.core.content.ContextCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MediaButtonReceiver extends BroadcastReceiver {
    private static final String[] ACTIONS = {"android.intent.action.MEDIA_BUTTON", MediaLibraryService.SERVICE_INTERFACE, MediaSessionService.SERVICE_INTERFACE};
    private static final String TAG = "MediaButtonReceiver";

    /* access modifiers changed from: protected */
    public boolean shouldStartForegroundService(Intent intent) {
        return true;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null || !Objects.equals(intent.getAction(), "android.intent.action.MEDIA_BUTTON") || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d(TAG, "Ignore unsupported intent: " + intent);
            return;
        }
        KeyEvent keyEvent = (KeyEvent) ((Bundle) Assertions.checkNotNull(intent.getExtras())).getParcelable("android.intent.extra.KEY_EVENT");
        if (keyEvent == null || keyEvent.getAction() != 0 || keyEvent.getRepeatCount() != 0) {
            return;
        }
        if (Util.SDK_INT < 26 || keyEvent.getKeyCode() == 126 || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 79) {
            String[] strArr = ACTIONS;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                ComponentName serviceComponentByAction = getServiceComponentByAction(context, strArr[i]);
                if (serviceComponentByAction != null) {
                    intent.setComponent(serviceComponentByAction);
                    if (!shouldStartForegroundService(intent)) {
                        androidx.media3.common.util.Log.i(TAG, "onReceive(Intent) does not start the media button event target service into the foreground on app request: " + serviceComponentByAction.getClassName());
                        return;
                    }
                    try {
                        ContextCompat.startForegroundService(context, intent);
                        return;
                    } catch (IllegalStateException e) {
                        if (Build.VERSION.SDK_INT < 31 || !Api31.instanceOfForegroundServiceStartNotAllowedException(e)) {
                            throw e;
                        }
                        onForegroundServiceStartNotAllowedException(intent, Api31.castToForegroundServiceStartNotAllowedException(e));
                        return;
                    }
                } else {
                    i++;
                }
            }
            throw new IllegalStateException("Could not find any Service that handles any of the actions " + Arrays.toString(ACTIONS));
        }
        SentryLogcatAdapter.w(TAG, "Ignore key event that is not a `play` command on API 26 or above to avoid an 'ForegroundServiceDidNotStartInTimeException'");
    }

    /* access modifiers changed from: protected */
    public void onForegroundServiceStartNotAllowedException(Intent intent, ForegroundServiceStartNotAllowedException foregroundServiceStartNotAllowedException) {
        androidx.media3.common.util.Log.e(TAG, "caught exception when trying to start a foreground service from the background: " + foregroundServiceStartNotAllowedException.getMessage());
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices.size() == 1) {
            ResolveInfo resolveInfo = queryIntentServices.get(0);
            return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        } else if (queryIntentServices.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Expected 1 service that handles " + str + ", found " + queryIntentServices.size());
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static boolean instanceOfForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return illegalStateException instanceof ForegroundServiceStartNotAllowedException;
        }

        public static ForegroundServiceStartNotAllowedException castToForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return (ForegroundServiceStartNotAllowedException) illegalStateException;
        }
    }
}
