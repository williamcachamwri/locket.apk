package androidx.media3.transformer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.video.PlaybackVideoGraphWrapper;

final class CompositionPlayerInternal implements Handler.Callback {
    private static final int MSG_CLEAR_OUTPUT_SURFACE = 2;
    private static final int MSG_END_SEEK = 4;
    private static final int MSG_RELEASE = 5;
    private static final int MSG_SET_OUTPUT_SURFACE_INFO = 1;
    private static final int MSG_START_SEEK = 3;
    private static final String TAG = "CompPlayerInternal";
    private final Clock clock;
    private final HandlerWrapper handler;
    private final Listener listener;
    private final HandlerWrapper listenerHandler;
    private final PlaybackAudioGraphWrapper playbackAudioGraphWrapper;
    private final PlaybackVideoGraphWrapper playbackVideoGraphWrapper;
    private boolean released;

    public interface Listener {
        void onError(String str, Exception exc, int i);
    }

    public CompositionPlayerInternal(Looper looper, Clock clock2, PlaybackAudioGraphWrapper playbackAudioGraphWrapper2, PlaybackVideoGraphWrapper playbackVideoGraphWrapper2, Listener listener2, HandlerWrapper handlerWrapper) {
        this.clock = clock2;
        this.handler = clock2.createHandler(looper, this);
        this.playbackAudioGraphWrapper = playbackAudioGraphWrapper2;
        this.playbackVideoGraphWrapper = playbackVideoGraphWrapper2;
        this.listener = listener2;
        this.listenerHandler = handlerWrapper;
    }

    public void setOutputSurfaceInfo(Surface surface, Size size) {
        this.handler.obtainMessage(1, new OutputSurfaceInfo(surface, size)).sendToTarget();
    }

    public void clearOutputSurface() {
        this.handler.obtainMessage(2).sendToTarget();
    }

    public void startSeek(long j) {
        this.handler.obtainMessage(3, Long.valueOf(j)).sendToTarget();
    }

    public void endSeek() {
        this.handler.obtainMessage(4).sendToTarget();
    }

    public void release() {
        Assertions.checkState(!this.released);
        this.released = true;
        ConditionVariable conditionVariable = new ConditionVariable();
        this.handler.obtainMessage(5, conditionVariable).sendToTarget();
        this.clock.onThreadBlocked();
        try {
            conditionVariable.block();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    public boolean handleMessage(Message message) {
        try {
            int i = message.what;
            if (i == 1) {
                setOutputSurfaceInfoOnInternalThread((OutputSurfaceInfo) message.obj);
            } else if (i == 2) {
                clearOutputSurfaceInternal();
            } else if (i == 3) {
                this.playbackAudioGraphWrapper.startSeek(Util.msToUs(((Long) message.obj).longValue()));
            } else if (i == 4) {
                this.playbackAudioGraphWrapper.endSeek();
            } else if (i != 5) {
                maybeRaiseError("Unknown message", new IllegalStateException(String.valueOf(message.what)), 1000);
            } else {
                releaseInternal((ConditionVariable) message.obj);
            }
        } catch (RuntimeException e) {
            maybeRaiseError("Unknown error", e, 1000);
        }
        return true;
    }

    private void releaseInternal(ConditionVariable conditionVariable) {
        try {
            this.playbackAudioGraphWrapper.release();
            this.playbackVideoGraphWrapper.clearOutputSurfaceInfo();
            this.playbackVideoGraphWrapper.release();
        } catch (RuntimeException e) {
            Log.e(TAG, "error while releasing the player", e);
        } catch (Throwable th) {
            conditionVariable.open();
            throw th;
        }
        conditionVariable.open();
    }

    private void clearOutputSurfaceInternal() {
        try {
            this.playbackVideoGraphWrapper.clearOutputSurfaceInfo();
        } catch (RuntimeException e) {
            maybeRaiseError("error clearing video output", e, 7001);
        }
    }

    private void setOutputSurfaceInfoOnInternalThread(OutputSurfaceInfo outputSurfaceInfo) {
        try {
            this.playbackVideoGraphWrapper.setOutputSurfaceInfo(outputSurfaceInfo.surface, outputSurfaceInfo.size);
        } catch (RuntimeException e) {
            maybeRaiseError("error setting surface view", e, 7001);
        }
    }

    private void maybeRaiseError(String str, Exception exc, int i) {
        try {
            this.listenerHandler.post(new CompositionPlayerInternal$$ExternalSyntheticLambda0(this, str, exc, i));
        } catch (RuntimeException e) {
            Log.e(TAG, "error", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$maybeRaiseError$0$androidx-media3-transformer-CompositionPlayerInternal  reason: not valid java name */
    public /* synthetic */ void m1121lambda$maybeRaiseError$0$androidxmedia3transformerCompositionPlayerInternal(String str, Exception exc, int i) {
        if (!this.released) {
            this.listener.onError(str, exc, i);
        }
    }

    private static final class OutputSurfaceInfo {
        public final Size size;
        public final Surface surface;

        public OutputSurfaceInfo(Surface surface2, Size size2) {
            this.surface = surface2;
            this.size = size2;
        }
    }
}
