package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.media3.common.util.Log;

final class WifiLockManager {
    private static final String TAG = "WifiLockManager";
    private static final String WIFI_LOCK_TAG = "ExoPlayer:WifiLockManager";
    private final Context applicationContext;
    private boolean enabled;
    private boolean stayAwake;
    private WifiManager.WifiLock wifiLock;

    public WifiLockManager(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    public void setEnabled(boolean z) {
        if (z && this.wifiLock == null) {
            WifiManager wifiManager = (WifiManager) this.applicationContext.getApplicationContext().getSystemService("wifi");
            if (wifiManager == null) {
                Log.w(TAG, "WifiManager is null, therefore not creating the WifiLock.");
                return;
            }
            WifiManager.WifiLock createWifiLock = wifiManager.createWifiLock(3, WIFI_LOCK_TAG);
            this.wifiLock = createWifiLock;
            createWifiLock.setReferenceCounted(false);
        }
        this.enabled = z;
        updateWifiLock();
    }

    public void setStayAwake(boolean z) {
        this.stayAwake = z;
        updateWifiLock();
    }

    private void updateWifiLock() {
        WifiManager.WifiLock wifiLock2 = this.wifiLock;
        if (wifiLock2 != null) {
            if (!this.enabled || !this.stayAwake) {
                wifiLock2.release();
            } else {
                wifiLock2.acquire();
            }
        }
    }
}
