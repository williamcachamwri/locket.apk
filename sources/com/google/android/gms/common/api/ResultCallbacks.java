package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    public final void onResult(R r) {
        Status status = r.getStatus();
        if (status.isSuccess()) {
            onSuccess(r);
            return;
        }
        onFailure(status);
        if (r instanceof Releasable) {
            try {
                ((Releasable) r).release();
            } catch (RuntimeException e) {
                SentryLogcatAdapter.w("ResultCallbacks", "Unable to release ".concat(String.valueOf(String.valueOf(r))), e);
            }
        }
    }

    public abstract void onSuccess(R r);
}
