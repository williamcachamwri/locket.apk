package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

public class SystemClock implements Clock {
    public void onThreadBlocked() {
    }

    protected SystemClock() {
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    public long nanoTime() {
        return System.nanoTime();
    }

    public HandlerWrapper createHandler(Looper looper, Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }
}
