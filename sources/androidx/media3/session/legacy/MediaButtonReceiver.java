package androidx.media3.session.legacy;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.PendingIntent;
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
import androidx.media3.session.legacy.MediaBrowserCompat;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {
    private static final String TAG = "MediaButtonReceiver";

    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d(TAG, "Ignore unsupported intent: " + intent);
            return;
        }
        ComponentName serviceComponentByAction = getServiceComponentByAction(context, "android.intent.action.MEDIA_BUTTON");
        if (serviceComponentByAction != null) {
            intent.setComponent(serviceComponentByAction);
            try {
                ContextCompat.startForegroundService(context, intent);
            } catch (IllegalStateException e) {
                if (Build.VERSION.SDK_INT < 31 || !Api31.instanceOfForegroundServiceStartNotAllowedException(e)) {
                    throw e;
                }
                onForegroundServiceStartNotAllowedException(intent, Api31.castToForegroundServiceStartNotAllowedException(e));
            }
        } else {
            ComponentName serviceComponentByAction2 = getServiceComponentByAction(context, "android.media.browse.MediaBrowserService");
            if (serviceComponentByAction2 != null) {
                BroadcastReceiver.PendingResult goAsync = goAsync();
                Context applicationContext = context.getApplicationContext();
                MediaButtonConnectionCallback mediaButtonConnectionCallback = new MediaButtonConnectionCallback(applicationContext, intent, goAsync);
                MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(applicationContext, serviceComponentByAction2, mediaButtonConnectionCallback, (Bundle) null);
                mediaButtonConnectionCallback.setMediaBrowser(mediaBrowserCompat);
                mediaBrowserCompat.connect();
                return;
            }
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
        }
    }

    /* access modifiers changed from: protected */
    public void onForegroundServiceStartNotAllowedException(Intent intent, ForegroundServiceStartNotAllowedException foregroundServiceStartNotAllowedException) {
        SentryLogcatAdapter.e(TAG, "caught exception when trying to start a foreground service from the background: " + foregroundServiceStartNotAllowedException.getMessage());
    }

    private static class MediaButtonConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        private final Context mContext;
        private final Intent mIntent;
        private MediaBrowserCompat mMediaBrowser;
        private final BroadcastReceiver.PendingResult mPendingResult;

        MediaButtonConnectionCallback(Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
            this.mContext = context;
            this.mIntent = intent;
            this.mPendingResult = pendingResult;
        }

        /* access modifiers changed from: package-private */
        public void setMediaBrowser(MediaBrowserCompat mediaBrowserCompat) {
            this.mMediaBrowser = mediaBrowserCompat;
        }

        public void onConnected() {
            new MediaControllerCompat(this.mContext, ((MediaBrowserCompat) Assertions.checkNotNull(this.mMediaBrowser)).getSessionToken()).dispatchMediaButtonEvent((KeyEvent) this.mIntent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            finish();
        }

        public void onConnectionSuspended() {
            finish();
        }

        public void onConnectionFailed() {
            finish();
        }

        private void finish() {
            ((MediaBrowserCompat) Assertions.checkNotNull(this.mMediaBrowser)).disconnect();
            this.mPendingResult.finish();
        }
    }

    public static KeyEvent handleIntent(MediaSessionCompat mediaSessionCompat, Intent intent) {
        if (mediaSessionCompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            return null;
        }
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        mediaSessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
        return keyEvent;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, long j) {
        ComponentName mediaButtonReceiverComponent = getMediaButtonReceiverComponent(context);
        if (mediaButtonReceiverComponent != null) {
            return buildMediaButtonPendingIntent(context, mediaButtonReceiverComponent, j);
        }
        SentryLogcatAdapter.w(TAG, "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
        return null;
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, ComponentName componentName, long j) {
        if (componentName == null) {
            SentryLogcatAdapter.w(TAG, "The component name of media button receiver should be provided.");
            return null;
        }
        int keyCode = PlaybackStateCompat.toKeyCode(j);
        if (keyCode == 0) {
            SentryLogcatAdapter.w(TAG, "Cannot build a media button pending intent with the given action: " + j);
            return null;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(componentName);
        int i = 0;
        intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, keyCode));
        intent.addFlags(268435456);
        if (Build.VERSION.SDK_INT >= 31) {
            i = 33554432;
        }
        return PendingIntent.getBroadcast(context, keyCode, intent, i);
    }

    public static ComponentName getMediaButtonReceiverComponent(Context context) {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers.size() == 1) {
            ResolveInfo resolveInfo = queryBroadcastReceivers.get(0);
            return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        } else if (queryBroadcastReceivers.size() <= 1) {
            return null;
        } else {
            SentryLogcatAdapter.w(TAG, "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
            return null;
        }
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
