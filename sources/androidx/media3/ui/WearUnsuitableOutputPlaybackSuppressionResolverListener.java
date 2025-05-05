package androidx.media3.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.PowerManager;
import androidx.media3.common.C;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.SystemClock;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.concurrent.TimeUnit;

public final class WearUnsuitableOutputPlaybackSuppressionResolverListener implements Player.Listener {
    public static final long DEFAULT_PLAYBACK_SUPPRESSION_AUTO_RESUME_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(5);
    private static final String EXTRA_BLUETOOTH_SETTINGS_CLOSE_ON_CONNECT = "EXTRA_CLOSE_ON_CONNECT";
    private static final String EXTRA_BLUETOOTH_SETTINGS_CONNECTION_ONLY = "EXTRA_CONNECTION_ONLY";
    private static final String EXTRA_BLUETOOTH_SETTINGS_FILTER_TYPE = "android.bluetooth.devicepicker.extra.FILTER_TYPE";
    private static final String EXTRA_OUTPUT_SWITCHER_PACKAGE_NAME = "com.android.settings.panel.extra.PACKAGE_NAME";
    private static final int FILTER_TYPE_AUDIO = 1;
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_NAME = "com.android.settings.panel.action.MEDIA_OUTPUT";
    private static final String WAKE_LOCK_TAG = "WearUnsuitableOutputPlaybackSuppressionResolverListener:WakeLock";
    private final Context applicationContext;
    private final long autoResumeTimeoutAfterUnsuitableOutputSuppressionMs;
    private final Clock clock;
    private long unsuitableOutputPlaybackSuppressionStartRealtimeMs;
    private final PowerManager.WakeLock wakeLock;

    public WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context) {
        this(context, DEFAULT_PLAYBACK_SUPPRESSION_AUTO_RESUME_TIMEOUT_MS);
    }

    public WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context, long j) {
        this(context, j, SystemClock.DEFAULT);
    }

    WearUnsuitableOutputPlaybackSuppressionResolverListener(Context context, long j, Clock clock2) {
        PowerManager.WakeLock wakeLock2;
        Assertions.checkArgument(j >= 0);
        Context applicationContext2 = context.getApplicationContext();
        this.applicationContext = applicationContext2;
        this.autoResumeTimeoutAfterUnsuitableOutputSuppressionMs = j;
        this.clock = clock2;
        this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = C.TIME_UNSET;
        PowerManager powerManager = (PowerManager) applicationContext2.getSystemService("power");
        if (powerManager != null) {
            wakeLock2 = powerManager.newWakeLock(1, WAKE_LOCK_TAG);
            wakeLock2.setReferenceCounted(false);
        } else {
            wakeLock2 = null;
        }
        this.wakeLock = wakeLock2;
    }

    public void onEvents(Player player, Player.Events events) {
        if (Util.isWear(this.applicationContext)) {
            if ((events.contains(6) || events.contains(5)) && player.getPlayWhenReady() && player.getPlaybackSuppressionReason() == 3) {
                player.pause();
                this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = this.clock.elapsedRealtime();
                PowerManager.WakeLock wakeLock2 = this.wakeLock;
                if (wakeLock2 != null && !wakeLock2.isHeld()) {
                    this.wakeLock.acquire(this.autoResumeTimeoutAfterUnsuitableOutputSuppressionMs);
                }
                if (events.contains(5)) {
                    launchSystemMediaOutputSwitcherUi(this.applicationContext);
                }
            } else if (events.contains(6) && player.getPlaybackSuppressionReason() == 0 && this.unsuitableOutputPlaybackSuppressionStartRealtimeMs != C.TIME_UNSET && this.clock.elapsedRealtime() - this.unsuitableOutputPlaybackSuppressionStartRealtimeMs < this.autoResumeTimeoutAfterUnsuitableOutputSuppressionMs) {
                this.unsuitableOutputPlaybackSuppressionStartRealtimeMs = C.TIME_UNSET;
                player.play();
                PowerManager.WakeLock wakeLock3 = this.wakeLock;
                if (wakeLock3 != null) {
                    wakeLock3.release();
                }
            }
        }
    }

    private static void launchSystemMediaOutputSwitcherUi(Context context) {
        Intent putExtra = new Intent(OUTPUT_SWITCHER_INTENT_ACTION_NAME).addFlags(268435456).putExtra(EXTRA_OUTPUT_SWITCHER_PACKAGE_NAME, context.getPackageName());
        ComponentName systemOrSystemUpdatedAppComponent = getSystemOrSystemUpdatedAppComponent(context, putExtra);
        if (systemOrSystemUpdatedAppComponent != null) {
            putExtra.setComponent(systemOrSystemUpdatedAppComponent);
            context.startActivity(putExtra);
            return;
        }
        Intent putExtra2 = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268468224).putExtra(EXTRA_BLUETOOTH_SETTINGS_CLOSE_ON_CONNECT, true).putExtra(EXTRA_BLUETOOTH_SETTINGS_CONNECTION_ONLY, true).putExtra(EXTRA_BLUETOOTH_SETTINGS_FILTER_TYPE, 1);
        ComponentName systemOrSystemUpdatedAppComponent2 = getSystemOrSystemUpdatedAppComponent(context, putExtra2);
        if (systemOrSystemUpdatedAppComponent2 != null) {
            putExtra2.setComponent(systemOrSystemUpdatedAppComponent2);
            context.startActivity(putExtra2);
        }
    }

    private static ComponentName getSystemOrSystemUpdatedAppComponent(Context context, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && activityInfo.applicationInfo != null && (activityInfo.applicationInfo.flags & TsExtractor.TS_STREAM_TYPE_AC3) != 0) {
                return new ComponentName(activityInfo.packageName, activityInfo.name);
            }
        }
        return null;
    }
}
