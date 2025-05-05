package com.brentvatne.exoplayer;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.SessionCommand;
import androidx.media3.session.SessionResult;
import com.brentvatne.exoplayer.VideoPlaybackService;
import com.google.common.util.concurrent.ListenableFuture;
import io.sentry.cache.EnvelopeCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J.\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/brentvatne/exoplayer/VideoPlaybackCallback;", "Landroidx/media3/session/MediaSession$Callback;", "()V", "onConnect", "Landroidx/media3/session/MediaSession$ConnectionResult;", "session", "Landroidx/media3/session/MediaSession;", "controller", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onCustomCommand", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/media3/session/SessionResult;", "customCommand", "Landroidx/media3/session/SessionCommand;", "args", "Landroid/os/Bundle;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlaybackCallback.kt */
public final class VideoPlaybackCallback implements MediaSession.Callback {
    public MediaSession.ConnectionResult onConnect(MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo) {
        Intrinsics.checkNotNullParameter(mediaSession, EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        Intrinsics.checkNotNullParameter(controllerInfo, "controller");
        try {
            MediaSession.ConnectionResult build = new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession).setAvailablePlayerCommands(MediaSession.ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon().add(12).add(11).build()).setAvailableSessionCommands(MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS.buildUpon().add(new SessionCommand(VideoPlaybackService.Companion.COMMAND.SEEK_FORWARD.getStringValue(), Bundle.EMPTY)).add(new SessionCommand(VideoPlaybackService.Companion.COMMAND.SEEK_BACKWARD.getStringValue(), Bundle.EMPTY)).build()).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        } catch (Exception unused) {
            MediaSession.ConnectionResult reject = MediaSession.ConnectionResult.reject();
            Intrinsics.checkNotNullExpressionValue(reject, "reject(...)");
            return reject;
        }
    }

    public ListenableFuture<SessionResult> onCustomCommand(MediaSession mediaSession, MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        Intrinsics.checkNotNullParameter(mediaSession, EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        Intrinsics.checkNotNullParameter(controllerInfo, "controller");
        Intrinsics.checkNotNullParameter(sessionCommand, "customCommand");
        Intrinsics.checkNotNullParameter(bundle, "args");
        VideoPlaybackService.Companion companion = VideoPlaybackService.Companion;
        VideoPlaybackService.Companion companion2 = VideoPlaybackService.Companion;
        String str = sessionCommand.customAction;
        Intrinsics.checkNotNullExpressionValue(str, "customAction");
        companion.handleCommand(companion2.commandFromString(str), mediaSession);
        ListenableFuture<SessionResult> onCustomCommand = super.onCustomCommand(mediaSession, controllerInfo, sessionCommand, bundle);
        Intrinsics.checkNotNullExpressionValue(onCustomCommand, "onCustomCommand(...)");
        return onCustomCommand;
    }
}
