package androidx.camera.core.impl;

import androidx.camera.core.Logger;
import androidx.camera.core.RetryPolicy;
import androidx.camera.core.impl.CameraValidator;

public final class CameraProviderInitRetryPolicy implements RetryPolicyInternal {
    private final RetryPolicy mDelegatePolicy;

    public CameraProviderInitRetryPolicy(final long j) {
        this.mDelegatePolicy = new TimeoutRetryPolicy(j, new RetryPolicy() {
            public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState executionState) {
                if (executionState.getStatus() == 1) {
                    return RetryPolicy.RetryConfig.NOT_RETRY;
                }
                return RetryPolicy.RetryConfig.DEFAULT_DELAY_RETRY;
            }

            public long getTimeoutInMillis() {
                return j;
            }
        });
    }

    public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState executionState) {
        return this.mDelegatePolicy.onRetryDecisionRequested(executionState);
    }

    public long getTimeoutInMillis() {
        return this.mDelegatePolicy.getTimeoutInMillis();
    }

    public RetryPolicy copy(long j) {
        return new CameraProviderInitRetryPolicy(j);
    }

    public static final class Legacy implements RetryPolicyInternal {
        private final RetryPolicy mBasePolicy;

        public Legacy(long j) {
            this.mBasePolicy = new CameraProviderInitRetryPolicy(j);
        }

        public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState executionState) {
            if (this.mBasePolicy.onRetryDecisionRequested(executionState).shouldRetry()) {
                return RetryPolicy.RetryConfig.DEFAULT_DELAY_RETRY;
            }
            Throwable cause = executionState.getCause();
            if (cause instanceof CameraValidator.CameraIdListIncorrectException) {
                Logger.e("CameraX", "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
                if (((CameraValidator.CameraIdListIncorrectException) cause).getAvailableCameraCount() > 0) {
                    return RetryPolicy.RetryConfig.COMPLETE_WITHOUT_FAILURE;
                }
            }
            return RetryPolicy.RetryConfig.NOT_RETRY;
        }

        public long getTimeoutInMillis() {
            return this.mBasePolicy.getTimeoutInMillis();
        }

        public RetryPolicy copy(long j) {
            return new Legacy(j);
        }
    }
}
