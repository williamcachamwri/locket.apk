package androidx.media3.session;

import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaController;
import com.google.common.util.concurrent.AbstractFuture;

class MediaControllerHolder<T extends MediaController> extends AbstractFuture<T> implements MediaController.ConnectionCallback {
    private boolean accepted;
    private T controller;
    private final Handler handler;

    public MediaControllerHolder(Looper looper) {
        this.handler = new Handler(looper);
    }

    public void setController(T t) {
        this.controller = t;
        maybeSetFutureResult();
        addListener(new MediaControllerHolder$$ExternalSyntheticLambda0(this, t), new MediaControllerHolder$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setController$0$androidx-media3-session-MediaControllerHolder  reason: not valid java name */
    public /* synthetic */ void m922lambda$setController$0$androidxmedia3sessionMediaControllerHolder(MediaController mediaController) {
        if (isCancelled()) {
            mediaController.release();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setController$1$androidx-media3-session-MediaControllerHolder  reason: not valid java name */
    public /* synthetic */ void m923lambda$setController$1$androidxmedia3sessionMediaControllerHolder(Runnable runnable) {
        Util.postOrRun(this.handler, runnable);
    }

    public void onAccepted() {
        this.accepted = true;
        maybeSetFutureResult();
    }

    public void onRejected() {
        maybeSetException();
    }

    private void maybeSetFutureResult() {
        T t = this.controller;
        if (t != null && this.accepted) {
            set(t);
        }
    }

    private void maybeSetException() {
        setException(new SecurityException("Session rejected the connection request."));
    }
}
