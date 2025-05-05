package androidx.camera.extensions.internal.compat.workaround;

import android.os.SystemClock;
import androidx.camera.core.Logger;
import androidx.camera.extensions.internal.compat.quirk.CrashWhenOnDisableTooSoon;
import androidx.camera.extensions.internal.compat.quirk.DeviceQuirks;

public class OnEnableDisableSessionDurationCheck {
    static final long MIN_DURATION_FOR_ENABLE_DISABLE_SESSION = 100;
    private static final String TAG = "OnEnableDisableSessionDurationCheck";
    private final boolean mEnabledMinimumDuration;
    private long mOnEnableSessionTimeStamp;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OnEnableDisableSessionDurationCheck() {
        this(DeviceQuirks.get(CrashWhenOnDisableTooSoon.class) != null);
    }

    OnEnableDisableSessionDurationCheck(boolean z) {
        this.mOnEnableSessionTimeStamp = 0;
        this.mEnabledMinimumDuration = z;
    }

    public void onEnableSessionInvoked() {
        if (this.mEnabledMinimumDuration) {
            this.mOnEnableSessionTimeStamp = SystemClock.elapsedRealtime();
        }
    }

    public void onDisableSessionInvoked() {
        if (this.mEnabledMinimumDuration) {
            ensureMinDurationAfterOnEnableSession();
        }
    }

    private void ensureMinDurationAfterOnEnableSession() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.mOnEnableSessionTimeStamp;
        while (true) {
            long j2 = elapsedRealtime - j;
            if (j2 < 100) {
                long j3 = 100 - j2;
                try {
                    Logger.d(TAG, "onDisableSession too soon, wait " + j3 + " ms");
                    Thread.sleep(j3);
                    elapsedRealtime = SystemClock.elapsedRealtime();
                    j = this.mOnEnableSessionTimeStamp;
                } catch (InterruptedException unused) {
                    Logger.e(TAG, "sleep interrupted");
                    return;
                }
            } else {
                return;
            }
        }
    }
}
