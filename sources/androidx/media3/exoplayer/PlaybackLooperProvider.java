package androidx.media3.exoplayer;

import android.os.HandlerThread;
import android.os.Looper;
import androidx.media3.common.util.Assertions;

public final class PlaybackLooperProvider {
    private HandlerThread internalPlaybackThread;
    private final Object lock;
    private Looper playbackLooper;
    private int referenceCount;

    public PlaybackLooperProvider() {
        this((Looper) null);
    }

    public PlaybackLooperProvider(Looper looper) {
        this.lock = new Object();
        this.playbackLooper = looper;
        this.internalPlaybackThread = null;
        this.referenceCount = 0;
    }

    public Looper obtainLooper() {
        Looper looper;
        synchronized (this.lock) {
            if (this.playbackLooper == null) {
                Assertions.checkState(this.referenceCount == 0 && this.internalPlaybackThread == null);
                HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
                this.internalPlaybackThread = handlerThread;
                handlerThread.start();
                this.playbackLooper = this.internalPlaybackThread.getLooper();
            }
            this.referenceCount++;
            looper = this.playbackLooper;
        }
        return looper;
    }

    public void releaseLooper() {
        HandlerThread handlerThread;
        synchronized (this.lock) {
            Assertions.checkState(this.referenceCount > 0);
            int i = this.referenceCount - 1;
            this.referenceCount = i;
            if (i == 0 && (handlerThread = this.internalPlaybackThread) != null) {
                handlerThread.quit();
                this.internalPlaybackThread = null;
                this.playbackLooper = null;
            }
        }
    }
}
