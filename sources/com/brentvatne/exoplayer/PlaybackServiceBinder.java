package com.brentvatne.exoplayer;

import android.os.Binder;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/brentvatne/exoplayer/PlaybackServiceBinder;", "Landroid/os/Binder;", "service", "Lcom/brentvatne/exoplayer/VideoPlaybackService;", "(Lcom/brentvatne/exoplayer/VideoPlaybackService;)V", "getService", "()Lcom/brentvatne/exoplayer/VideoPlaybackService;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlaybackService.kt */
public final class PlaybackServiceBinder extends Binder {
    private final VideoPlaybackService service;

    public PlaybackServiceBinder(VideoPlaybackService videoPlaybackService) {
        Intrinsics.checkNotNullParameter(videoPlaybackService, NotificationCompat.CATEGORY_SERVICE);
        this.service = videoPlaybackService;
    }

    public final VideoPlaybackService getService() {
        return this.service;
    }
}
