package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.RetryPolicy;
import androidx.camera.core.impl.CameraValidator;

public final class CameraProviderExecutionState implements RetryPolicy.ExecutionState {
    private final int mAttemptCount;
    private final Throwable mCause;
    private final int mStatus;
    private final long mTaskExecutedTimeInMillis;

    public CameraProviderExecutionState(long j, int i, Throwable th) {
        this.mTaskExecutedTimeInMillis = SystemClock.elapsedRealtime() - j;
        this.mAttemptCount = i;
        if (th instanceof CameraValidator.CameraIdListIncorrectException) {
            this.mStatus = 2;
            this.mCause = th;
        } else if (th instanceof InitializationException) {
            Throwable cause = th.getCause();
            th = cause != null ? cause : th;
            this.mCause = th;
            if (th instanceof CameraUnavailableException) {
                this.mStatus = 2;
            } else if (th instanceof IllegalArgumentException) {
                this.mStatus = 1;
            } else {
                this.mStatus = 0;
            }
        } else {
            this.mStatus = 0;
            this.mCause = th;
        }
    }

    public int getStatus() {
        return this.mStatus;
    }

    public Throwable getCause() {
        return this.mCause;
    }

    public long getExecutedTimeInMillis() {
        return this.mTaskExecutedTimeInMillis;
    }

    public int getNumOfAttempts() {
        return this.mAttemptCount;
    }
}
