package androidx.media3.common;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.SimpleBasePlayer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Objects;

public class ForwardingSimpleBasePlayer extends SimpleBasePlayer {
    /* access modifiers changed from: private */
    public ForwardingPositionSupplier currentPositionSupplier;
    /* access modifiers changed from: private */
    public Metadata lastTimedMetadata = new Metadata((long) C.TIME_UNSET, new Metadata.Entry[0]);
    /* access modifiers changed from: private */
    public int pendingDiscontinuityReason = 5;
    /* access modifiers changed from: private */
    public boolean pendingFirstFrameRendered;
    /* access modifiers changed from: private */
    public long pendingPositionDiscontinuityNewPositionMs;
    /* access modifiers changed from: private */
    public int playWhenReadyChangeReason = 1;
    private final Player player;

    public ForwardingSimpleBasePlayer(final Player player2) {
        super(player2.getApplicationLooper());
        this.player = player2;
        this.currentPositionSupplier = new ForwardingPositionSupplier(player2);
        player2.addListener(new Player.Listener() {
            public void onMetadata(Metadata metadata) {
                Metadata unused = ForwardingSimpleBasePlayer.this.lastTimedMetadata = metadata;
            }

            public void onPlayWhenReadyChanged(boolean z, int i) {
                int unused = ForwardingSimpleBasePlayer.this.playWhenReadyChangeReason = i;
            }

            public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                int unused = ForwardingSimpleBasePlayer.this.pendingDiscontinuityReason = i;
                long unused2 = ForwardingSimpleBasePlayer.this.pendingPositionDiscontinuityNewPositionMs = positionInfo2.positionMs;
                ForwardingSimpleBasePlayer.this.currentPositionSupplier.setConstant(positionInfo.positionMs, positionInfo.contentPositionMs);
                ForwardingPositionSupplier unused3 = ForwardingSimpleBasePlayer.this.currentPositionSupplier = new ForwardingPositionSupplier(player2);
            }

            public void onRenderedFirstFrame() {
                boolean unused = ForwardingSimpleBasePlayer.this.pendingFirstFrameRendered = true;
            }

            public void onEvents(Player player, Player.Events events) {
                ForwardingSimpleBasePlayer.this.invalidateState();
            }
        });
    }

    /* access modifiers changed from: protected */
    public final Player getPlayer() {
        return this.player;
    }

    /* access modifiers changed from: protected */
    public SimpleBasePlayer.State getState() {
        Tracks tracks;
        SimpleBasePlayer.State.Builder builder = new SimpleBasePlayer.State.Builder();
        ForwardingPositionSupplier forwardingPositionSupplier = this.currentPositionSupplier;
        if (this.player.isCommandAvailable(16)) {
            Objects.requireNonNull(forwardingPositionSupplier);
            builder.setAdBufferedPositionMs(new ForwardingSimpleBasePlayer$$ExternalSyntheticLambda0(forwardingPositionSupplier));
            Objects.requireNonNull(forwardingPositionSupplier);
            builder.setAdPositionMs((SimpleBasePlayer.PositionSupplier) new ForwardingSimpleBasePlayer$$ExternalSyntheticLambda1(forwardingPositionSupplier));
        }
        if (this.player.isCommandAvailable(21)) {
            builder.setAudioAttributes(this.player.getAudioAttributes());
        }
        builder.setAvailableCommands(this.player.getAvailableCommands());
        if (this.player.isCommandAvailable(16)) {
            Objects.requireNonNull(forwardingPositionSupplier);
            builder.setContentBufferedPositionMs(new ForwardingSimpleBasePlayer$$ExternalSyntheticLambda2(forwardingPositionSupplier));
            Objects.requireNonNull(forwardingPositionSupplier);
            builder.setContentPositionMs((SimpleBasePlayer.PositionSupplier) new ForwardingSimpleBasePlayer$$ExternalSyntheticLambda3(forwardingPositionSupplier));
            if (this.player.isCommandAvailable(17)) {
                builder.setCurrentAd(this.player.getCurrentAdGroupIndex(), this.player.getCurrentAdIndexInAdGroup());
            }
        }
        if (this.player.isCommandAvailable(28)) {
            builder.setCurrentCues(this.player.getCurrentCues());
        }
        if (this.player.isCommandAvailable(17)) {
            builder.setCurrentMediaItemIndex(this.player.getCurrentMediaItemIndex());
        }
        builder.setDeviceInfo(this.player.getDeviceInfo());
        if (this.player.isCommandAvailable(23)) {
            builder.setDeviceVolume(this.player.getDeviceVolume());
            builder.setIsDeviceMuted(this.player.isDeviceMuted());
        }
        builder.setIsLoading(this.player.isLoading());
        builder.setMaxSeekToPreviousPositionMs(this.player.getMaxSeekToPreviousPosition());
        if (this.pendingFirstFrameRendered) {
            builder.setNewlyRenderedFirstFrame(true);
            this.pendingFirstFrameRendered = false;
        }
        builder.setPlaybackParameters(this.player.getPlaybackParameters());
        builder.setPlaybackState(this.player.getPlaybackState());
        builder.setPlaybackSuppressionReason(this.player.getPlaybackSuppressionReason());
        builder.setPlayerError(this.player.getPlayerError());
        if (this.player.isCommandAvailable(17)) {
            if (this.player.isCommandAvailable(30)) {
                tracks = this.player.getCurrentTracks();
            } else {
                tracks = Tracks.EMPTY;
            }
            builder.setPlaylist(this.player.getCurrentTimeline(), tracks, this.player.isCommandAvailable(18) ? this.player.getMediaMetadata() : null);
        }
        if (this.player.isCommandAvailable(18)) {
            builder.setPlaylistMetadata(this.player.getPlaylistMetadata());
        }
        builder.setPlayWhenReady(this.player.getPlayWhenReady(), this.playWhenReadyChangeReason);
        long j = this.pendingPositionDiscontinuityNewPositionMs;
        if (j != C.TIME_UNSET) {
            builder.setPositionDiscontinuity(this.pendingDiscontinuityReason, j);
            this.pendingPositionDiscontinuityNewPositionMs = C.TIME_UNSET;
        }
        builder.setRepeatMode(this.player.getRepeatMode());
        builder.setSeekBackIncrementMs(this.player.getSeekBackIncrement());
        builder.setSeekForwardIncrementMs(this.player.getSeekForwardIncrement());
        builder.setShuffleModeEnabled(this.player.getShuffleModeEnabled());
        builder.setSurfaceSize(this.player.getSurfaceSize());
        builder.setTimedMetadata(this.lastTimedMetadata);
        if (this.player.isCommandAvailable(16)) {
            Objects.requireNonNull(forwardingPositionSupplier);
            builder.setTotalBufferedDurationMs(new ForwardingSimpleBasePlayer$$ExternalSyntheticLambda4(forwardingPositionSupplier));
        }
        builder.setTrackSelectionParameters(this.player.getTrackSelectionParameters());
        builder.setVideoSize(this.player.getVideoSize());
        if (this.player.isCommandAvailable(22)) {
            builder.setVolume(this.player.getVolume());
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlayWhenReady(boolean z) {
        this.player.setPlayWhenReady(z);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handlePrepare() {
        this.player.prepare();
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleStop() {
        this.player.stop();
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleRelease() {
        this.player.release();
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetRepeatMode(int i) {
        this.player.setRepeatMode(i);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetShuffleModeEnabled(boolean z) {
        this.player.setShuffleModeEnabled(z);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlaybackParameters(PlaybackParameters playbackParameters) {
        this.player.setPlaybackParameters(playbackParameters);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        this.player.setTrackSelectionParameters(trackSelectionParameters);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetPlaylistMetadata(MediaMetadata mediaMetadata) {
        this.player.setPlaylistMetadata(mediaMetadata);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVolume(float f) {
        this.player.setVolume(f);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetDeviceVolume(int i, int i2) {
        if (this.player.isCommandAvailable(33)) {
            this.player.setDeviceVolume(i, i2);
        } else {
            this.player.setDeviceVolume(i);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleIncreaseDeviceVolume(int i) {
        if (this.player.isCommandAvailable(34)) {
            this.player.increaseDeviceVolume(i);
        } else {
            this.player.increaseDeviceVolume();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleDecreaseDeviceVolume(int i) {
        if (this.player.isCommandAvailable(34)) {
            this.player.decreaseDeviceVolume(i);
        } else {
            this.player.decreaseDeviceVolume();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetDeviceMuted(boolean z, int i) {
        if (this.player.isCommandAvailable(34)) {
            this.player.setDeviceMuted(z, i);
        } else {
            this.player.setDeviceMuted(z);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetAudioAttributes(AudioAttributes audioAttributes, boolean z) {
        this.player.setAudioAttributes(audioAttributes, z);
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.setVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.setVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.setVideoSurfaceHolder((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.player.setVideoSurface((Surface) obj);
        } else {
            throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleClearVideoOutput(Object obj) {
        if (obj instanceof SurfaceView) {
            this.player.clearVideoSurfaceView((SurfaceView) obj);
        } else if (obj instanceof TextureView) {
            this.player.clearVideoTextureView((TextureView) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.player.clearVideoSurfaceHolder((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.player.clearVideoSurface((Surface) obj);
        } else if (obj == null) {
            this.player.clearVideoSurface();
        } else {
            throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSetMediaItems(List<MediaItem> list, int i, long j) {
        boolean z = true;
        if (list.size() != 1 || !this.player.isCommandAvailable(31)) {
            z = false;
        }
        if (i == -1) {
            if (z) {
                this.player.setMediaItem(list.get(0));
            } else {
                this.player.setMediaItems(list);
            }
        } else if (z) {
            this.player.setMediaItem(list.get(0), j);
        } else {
            this.player.setMediaItems(list, i, j);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleAddMediaItems(int i, List<MediaItem> list) {
        if (list.size() == 1) {
            this.player.addMediaItem(i, list.get(0));
        } else {
            this.player.addMediaItems(i, list);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleMoveMediaItems(int i, int i2, int i3) {
        if (i2 == i + 1) {
            this.player.moveMediaItem(i, i3);
        } else {
            this.player.moveMediaItems(i, i2, i3);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleReplaceMediaItems(int i, int i2, List<MediaItem> list) {
        if (i2 == i + 1 && list.size() == 1) {
            this.player.replaceMediaItem(i, list.get(0));
        } else {
            this.player.replaceMediaItems(i, i2, list);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleRemoveMediaItems(int i, int i2) {
        if (i2 == i + 1) {
            this.player.removeMediaItem(i);
        } else {
            this.player.removeMediaItems(i, i2);
        }
        return Futures.immediateVoidFuture();
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<?> handleSeek(int i, long j, int i2) {
        switch (i2) {
            case 4:
                this.player.seekToDefaultPosition();
                break;
            case 5:
                this.player.seekTo(j);
                break;
            case 6:
                this.player.seekToPreviousMediaItem();
                break;
            case 7:
                this.player.seekToPrevious();
                break;
            case 8:
                this.player.seekToNextMediaItem();
                break;
            case 9:
                this.player.seekToNext();
                break;
            case 10:
                if (i != -1) {
                    this.player.seekTo(i, j);
                    break;
                }
                break;
            case 11:
                this.player.seekBack();
                break;
            case 12:
                this.player.seekForward();
                break;
            default:
                throw new IllegalStateException();
        }
        return Futures.immediateVoidFuture();
    }

    private static final class ForwardingPositionSupplier {
        private long contentPositionMs = C.TIME_UNSET;
        private final Player player;
        private long positionsMs = C.TIME_UNSET;

        public ForwardingPositionSupplier(Player player2) {
            this.player = player2;
        }

        public void setConstant(long j, long j2) {
            this.positionsMs = j;
            this.contentPositionMs = j2;
        }

        public long getCurrentPositionMs() {
            long j = this.positionsMs;
            return j == C.TIME_UNSET ? this.player.getCurrentPosition() : j;
        }

        public long getBufferedPositionMs() {
            long j = this.positionsMs;
            return j == C.TIME_UNSET ? this.player.getBufferedPosition() : j;
        }

        public long getContentPositionMs() {
            long j = this.contentPositionMs;
            return j == C.TIME_UNSET ? this.player.getContentPosition() : j;
        }

        public long getContentBufferedPositionMs() {
            long j = this.contentPositionMs;
            return j == C.TIME_UNSET ? this.player.getContentBufferedPosition() : j;
        }

        public long getTotalBufferedDurationMs() {
            if (this.positionsMs == C.TIME_UNSET) {
                return this.player.getTotalBufferedDuration();
            }
            return 0;
        }
    }
}
