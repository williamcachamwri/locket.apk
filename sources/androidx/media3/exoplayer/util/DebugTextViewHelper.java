package androidx.media3.exoplayer.util;

import android.os.Looper;
import android.widget.TextView;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.ExoPlayer;
import com.adsbynimbus.render.mraid.HostKt;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.Locale;

public class DebugTextViewHelper {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final ExoPlayer player;
    private boolean started;
    private final TextView textView;
    private final Updater updater;

    public DebugTextViewHelper(ExoPlayer exoPlayer, TextView textView2) {
        Assertions.checkArgument(exoPlayer.getApplicationLooper() == Looper.getMainLooper());
        this.player = exoPlayer;
        this.textView = textView2;
        this.updater = new Updater();
    }

    public final void start() {
        if (!this.started) {
            this.started = true;
            this.player.addListener(this.updater);
            updateAndPost();
        }
    }

    public final void stop() {
        if (this.started) {
            this.started = false;
            this.player.removeListener(this.updater);
            this.textView.removeCallbacks(this.updater);
        }
    }

    /* access modifiers changed from: protected */
    public final void updateAndPost() {
        this.textView.setText(getDebugString());
        this.textView.removeCallbacks(this.updater);
        this.textView.postDelayed(this.updater, 1000);
    }

    /* access modifiers changed from: protected */
    public String getDebugString() {
        return getPlayerStateString() + getVideoString() + getAudioString();
    }

    /* access modifiers changed from: protected */
    public String getPlayerStateString() {
        int playbackState = this.player.getPlaybackState();
        return String.format("playWhenReady:%s playbackState:%s item:%s", new Object[]{Boolean.valueOf(this.player.getPlayWhenReady()), playbackState != 1 ? playbackState != 2 ? playbackState != 3 ? playbackState != 4 ? "unknown" : "ended" : HostKt.READY : "buffering" : "idle", Integer.valueOf(this.player.getCurrentMediaItemIndex())});
    }

    /* access modifiers changed from: protected */
    public String getVideoString() {
        Format videoFormat = this.player.getVideoFormat();
        VideoSize videoSize = this.player.getVideoSize();
        DecoderCounters videoDecoderCounters = this.player.getVideoDecoderCounters();
        return (videoFormat == null || videoDecoderCounters == null) ? "" : "\n" + videoFormat.sampleMimeType + "(id:" + videoFormat.id + " r:" + videoSize.width + ViewHierarchyNode.JsonKeys.X + videoSize.height + getColorInfoString(videoFormat.colorInfo) + getPixelAspectRatioString(videoSize.pixelWidthHeightRatio) + getDecoderCountersBufferCountString(videoDecoderCounters) + " vfpo: " + getVideoFrameProcessingOffsetAverageString(videoDecoderCounters.totalVideoFrameProcessingOffsetUs, videoDecoderCounters.videoFrameProcessingOffsetCount) + ")";
    }

    /* access modifiers changed from: protected */
    public String getAudioString() {
        Format audioFormat = this.player.getAudioFormat();
        DecoderCounters audioDecoderCounters = this.player.getAudioDecoderCounters();
        return (audioFormat == null || audioDecoderCounters == null) ? "" : "\n" + audioFormat.sampleMimeType + "(id:" + audioFormat.id + " hz:" + audioFormat.sampleRate + " ch:" + audioFormat.channelCount + getDecoderCountersBufferCountString(audioDecoderCounters) + ")";
    }

    private static String getDecoderCountersBufferCountString(DecoderCounters decoderCounters) {
        if (decoderCounters == null) {
            return "";
        }
        decoderCounters.ensureUpdated();
        return " sib:" + decoderCounters.skippedInputBufferCount + " sb:" + decoderCounters.skippedOutputBufferCount + " rb:" + decoderCounters.renderedOutputBufferCount + " db:" + decoderCounters.droppedBufferCount + " mcdb:" + decoderCounters.maxConsecutiveDroppedBufferCount + " dk:" + decoderCounters.droppedToKeyframeCount;
    }

    private static String getColorInfoString(ColorInfo colorInfo) {
        return (colorInfo == null || !colorInfo.isValid()) ? "" : " colr:" + colorInfo.toLogString();
    }

    private static String getPixelAspectRatioString(float f) {
        return (f == -1.0f || f == 1.0f) ? "" : " par:" + String.format(Locale.US, "%.02f", new Object[]{Float.valueOf(f)});
    }

    private static String getVideoFrameProcessingOffsetAverageString(long j, int i) {
        return i == 0 ? "N/A" : String.valueOf((long) (((double) j) / ((double) i)));
    }

    private final class Updater implements Player.Listener, Runnable {
        private Updater() {
        }

        public void onPlaybackStateChanged(int i) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void onPlayWhenReadyChanged(boolean z, int i) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            DebugTextViewHelper.this.updateAndPost();
        }

        public void run() {
            DebugTextViewHelper.this.updateAndPost();
        }
    }
}
