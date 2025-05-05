package androidx.media3.exoplayer;

import android.content.Context;
import android.os.PowerManager;
import androidx.media3.common.util.Log;

final class WakeLockManager {
    private static final String TAG = "WakeLockManager";
    private static final String WAKE_LOCK_TAG = "ExoPlayer:WakeLockManager";
    private final Context applicationContext;
    private boolean enabled;
    private boolean stayAwake;
    private PowerManager.WakeLock wakeLock;

    public WakeLockManager(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    public void setEnabled(boolean z) {
        if (z && this.wakeLock == null) {
            PowerManager powerManager = (PowerManager) this.applicationContext.getSystemService("power");
            if (powerManager == null) {
                Log.w(TAG, "PowerManager is null, therefore not creating the WakeLock.");
                return;
            }
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, WAKE_LOCK_TAG);
            this.wakeLock = newWakeLock;
            newWakeLock.setReferenceCounted(false);
        }
        this.enabled = z;
        updateWakeLock();
    }

    public void setStayAwake(boolean z) {
        this.stayAwake = z;
        updateWakeLock();
    }

    private void updateWakeLock() {
        PowerManager.WakeLock wakeLock2 = this.wakeLock;
        if (wakeLock2 != null) {
            if (!this.enabled || !this.stayAwake) {
                wakeLock2.release();
            } else {
                wakeLock2.acquire();
            }
        }
    }
}
