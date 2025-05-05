package com.adsbynimbus.render;

import android.graphics.Matrix;
import android.view.TextureView;
import android.view.View;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.ExoPlayer;
import com.adsbynimbus.render.VideoAdRenderer;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.player.AdMediaInfo;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\u0010\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u000bH\u0016J\b\u0010O\u001a\u00020PH\u0016J\b\u0010Q\u001a\u00020GH\u0016J\u0018\u0010R\u001a\u00020M2\u0006\u0010S\u001a\u00020-2\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020M2\u0006\u0010W\u001a\u00020 H\u0016J\u0010\u0010X\u001a\u00020M2\u0006\u0010Y\u001a\u00020GH\u0016J\u0010\u0010Z\u001a\u00020M2\u0006\u0010[\u001a\u00020\\H\u0016J\u0010\u0010]\u001a\u00020M2\u0006\u0010^\u001a\u00020\u000eH\u0016J\u0010\u0010_\u001a\u00020M2\u0006\u0010H\u001a\u00020`H\u0016J\u0010\u0010a\u001a\u00020M2\u0006\u0010S\u001a\u00020-H\u0016J\u0010\u0010b\u001a\u00020M2\u0006\u0010S\u001a\u00020-H\u0016J\b\u0010c\u001a\u00020MH\u0016J\u0010\u0010d\u001a\u00020M2\u0006\u0010N\u001a\u00020\u000bH\u0016J\u0010\u0010e\u001a\u00020M2\u0006\u0010S\u001a\u00020-H\u0016J$\u0010f\u001a\u00020M*\u00020\u001a2\u0012\u0010g\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020M0hH\bø\u0001\u0000R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u000203X.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0016\"\u0004\b:\u0010\u0018R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010;\u001a\u00020<¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0011\u0010?\u001a\u00020@¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010C\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010)\"\u0004\bE\u0010+R&\u0010H\u001a\u00020G2\u0006\u0010F\u001a\u00020G8G@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010K\u0002\u0007\n\u0005\b20\u0001¨\u0006i"}, d2 = {"Lcom/adsbynimbus/render/ExoPlayerVideoPlayer;", "Lcom/google/ads/interactivemedia/v3/api/player/VideoAdPlayer;", "Landroidx/media3/common/Player$Listener;", "auctionId", "", "textureView", "Landroid/view/TextureView;", "provider", "Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;", "callbacks", "", "Lcom/google/ads/interactivemedia/v3/api/player/VideoAdPlayer$VideoAdPlayerCallback;", "(Ljava/lang/String;Landroid/view/TextureView;Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;Ljava/util/List;)V", "creativeSize", "Landroidx/media3/common/VideoSize;", "getCreativeSize", "()Landroidx/media3/common/VideoSize;", "setCreativeSize", "(Landroidx/media3/common/VideoSize;)V", "duration", "", "getDuration", "()J", "setDuration", "(J)V", "exoPlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "getExoPlayer", "()Landroidx/media3/exoplayer/ExoPlayer;", "setExoPlayer", "(Landroidx/media3/exoplayer/ExoPlayer;)V", "isLoading", "", "()Z", "setLoading", "(Z)V", "isStarted", "setStarted", "loadJob", "Lkotlinx/coroutines/Job;", "getLoadJob", "()Lkotlinx/coroutines/Job;", "setLoadJob", "(Lkotlinx/coroutines/Job;)V", "mediaInfo", "Lcom/google/ads/interactivemedia/v3/api/player/AdMediaInfo;", "getMediaInfo", "()Lcom/google/ads/interactivemedia/v3/api/player/AdMediaInfo;", "setMediaInfo", "(Lcom/google/ads/interactivemedia/v3/api/player/AdMediaInfo;)V", "mediaItem", "Landroidx/media3/common/MediaItem;", "getMediaItem", "()Landroidx/media3/common/MediaItem;", "setMediaItem", "(Landroidx/media3/common/MediaItem;)V", "position", "getPosition", "setPosition", "scaleMatrix", "Landroid/graphics/Matrix;", "getScaleMatrix", "()Landroid/graphics/Matrix;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "updateJob", "getUpdateJob", "setUpdateJob", "value", "", "volume", "()I", "setVolume", "(I)V", "addCallback", "", "videoAdPlayerCallback", "getAdProgress", "Lcom/google/ads/interactivemedia/v3/api/player/VideoProgressUpdate;", "getVolume", "loadAd", "adMediaInfo", "adPodInfo", "Lcom/google/ads/interactivemedia/v3/api/AdPodInfo;", "onIsPlayingChanged", "isPlaying", "onPlaybackStateChanged", "playbackState", "onPlayerError", "error", "Landroidx/media3/common/PlaybackException;", "onVideoSizeChanged", "videoSize", "onVolumeChanged", "", "pauseAd", "playAd", "release", "removeCallback", "stopAd", "makeAvailable", "block", "Lkotlin/Function1;", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExoPlayerVideoPlayer.kt */
public final class ExoPlayerVideoPlayer implements VideoAdPlayer, Player.Listener {
    public final String auctionId;
    public final List<VideoAdPlayer.VideoAdPlayerCallback> callbacks;
    private VideoSize creativeSize;
    private long duration;
    private ExoPlayer exoPlayer;
    private boolean isLoading;
    private boolean isStarted;
    private Job loadJob;
    public AdMediaInfo mediaInfo;
    public MediaItem mediaItem;
    private long position;
    public final VideoAdRenderer.PlayerProvider provider;
    private final Matrix scaleMatrix;
    private final CoroutineScope scope;
    public final TextureView textureView;
    private Job updateJob;
    private int volume;

    public ExoPlayerVideoPlayer(String str, TextureView textureView2, VideoAdRenderer.PlayerProvider playerProvider, List<VideoAdPlayer.VideoAdPlayerCallback> list) {
        Intrinsics.checkNotNullParameter(str, "auctionId");
        Intrinsics.checkNotNullParameter(textureView2, "textureView");
        Intrinsics.checkNotNullParameter(playerProvider, "provider");
        Intrinsics.checkNotNullParameter(list, "callbacks");
        this.auctionId = str;
        this.textureView = textureView2;
        this.provider = playerProvider;
        this.callbacks = list;
        this.scaleMatrix = new Matrix();
        this.scope = CoroutineScopeKt.MainScope();
        this.duration = C.TIME_UNSET;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExoPlayerVideoPlayer(String str, TextureView textureView2, VideoAdRenderer.PlayerProvider playerProvider, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textureView2, playerProvider, (i & 8) != 0 ? new ArrayList() : list);
    }

    public final Matrix getScaleMatrix() {
        return this.scaleMatrix;
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final AdMediaInfo getMediaInfo() {
        AdMediaInfo adMediaInfo = this.mediaInfo;
        if (adMediaInfo != null) {
            return adMediaInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaInfo");
        return null;
    }

    public final void setMediaInfo(AdMediaInfo adMediaInfo) {
        Intrinsics.checkNotNullParameter(adMediaInfo, "<set-?>");
        this.mediaInfo = adMediaInfo;
    }

    public final MediaItem getMediaItem() {
        MediaItem mediaItem2 = this.mediaItem;
        if (mediaItem2 != null) {
            return mediaItem2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaItem");
        return null;
    }

    public final void setMediaItem(MediaItem mediaItem2) {
        Intrinsics.checkNotNullParameter(mediaItem2, "<set-?>");
        this.mediaItem = mediaItem2;
    }

    public final ExoPlayer getExoPlayer() {
        return this.exoPlayer;
    }

    public final void setExoPlayer(ExoPlayer exoPlayer2) {
        this.exoPlayer = exoPlayer2;
    }

    public final boolean isStarted() {
        return this.isStarted;
    }

    public final void setStarted(boolean z) {
        this.isStarted = z;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final void setLoading(boolean z) {
        this.isLoading = z;
    }

    public final Job getLoadJob() {
        return this.loadJob;
    }

    public final void setLoadJob(Job job) {
        this.loadJob = job;
    }

    public final Job getUpdateJob() {
        return this.updateJob;
    }

    public final void setUpdateJob(Job job) {
        this.updateJob = job;
    }

    public final VideoSize getCreativeSize() {
        return this.creativeSize;
    }

    public final void setCreativeSize(VideoSize videoSize) {
        this.creativeSize = videoSize;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public final long getPosition() {
        return this.position;
    }

    public final void setPosition(long j) {
        this.position = j;
    }

    public final int volume() {
        return this.volume;
    }

    public final void setVolume(int i) {
        this.volume = i;
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null) {
            exoPlayer2.setVolume(((float) i) * 0.01f);
        }
    }

    public void loadAd(AdMediaInfo adMediaInfo, AdPodInfo adPodInfo) {
        Intrinsics.checkNotNullParameter(adMediaInfo, "adMediaInfo");
        Intrinsics.checkNotNullParameter(adPodInfo, "adPodInfo");
        setMediaInfo(adMediaInfo);
        MediaItem build = new MediaItem.Builder().setUri(adMediaInfo.getUrl()).setMediaId(this.auctionId).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().setUri(adMedia…ediaId(auctionId).build()");
        setMediaItem(build);
        this.loadJob = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new ExoPlayerVideoPlayer$loadAd$1(this, (Continuation<? super ExoPlayerVideoPlayer$loadAd$1>) null), 2, (Object) null);
        this.textureView.addOnLayoutChangeListener(new ExoPlayerVideoPlayer$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void loadAd$lambda$1(ExoPlayerVideoPlayer exoPlayerVideoPlayer, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        VideoSize videoSize = exoPlayerVideoPlayer.creativeSize;
        if (videoSize != null) {
            exoPlayerVideoPlayer.onVideoSizeChanged(videoSize);
        }
    }

    public void playAd(AdMediaInfo adMediaInfo) {
        Job job;
        Intrinsics.checkNotNullParameter(adMediaInfo, "adMediaInfo");
        if (this.isLoading && (job = this.loadJob) != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ExoPlayerVideoPlayer$playAd$1(this, (Continuation<? super ExoPlayerVideoPlayer$playAd$1>) null), 3, (Object) null);
    }

    public final void makeAvailable(ExoPlayer exoPlayer2, Function1<? super ExoPlayer, Unit> function1) {
        Intrinsics.checkNotNullParameter(exoPlayer2, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        function1.invoke(exoPlayer2);
        exoPlayer2.removeListener(this);
        setExoPlayer((ExoPlayer) null);
        this.provider.offerPlayer(exoPlayer2);
    }

    public void pauseAd(AdMediaInfo adMediaInfo) {
        Intrinsics.checkNotNullParameter(adMediaInfo, "adMediaInfo");
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null) {
            exoPlayer2.pause();
            exoPlayer2.removeListener(this);
            setExoPlayer((ExoPlayer) null);
            this.provider.offerPlayer(exoPlayer2);
        }
    }

    public void stopAd(AdMediaInfo adMediaInfo) {
        Intrinsics.checkNotNullParameter(adMediaInfo, "adMediaInfo");
        this.textureView.setVisibility(4);
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null) {
            exoPlayer2.stop();
            exoPlayer2.removeListener(this);
            setExoPlayer((ExoPlayer) null);
            this.provider.offerPlayer(exoPlayer2);
        }
    }

    public void release() {
        this.textureView.setVisibility(8);
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null) {
            exoPlayer2.clearVideoSurface();
            exoPlayer2.removeListener(this);
            setExoPlayer((ExoPlayer) null);
            this.provider.offerPlayer(exoPlayer2);
        }
        CoroutineScopeKt.cancel$default(this.scope, (CancellationException) null, 1, (Object) null);
    }

    public int getVolume() {
        return this.volume;
    }

    public void onIsPlayingChanged(boolean z) {
        if (z) {
            if (this.isStarted) {
                for (VideoAdPlayer.VideoAdPlayerCallback onResume : this.callbacks) {
                    onResume.onResume(getMediaInfo());
                }
            } else {
                for (VideoAdPlayer.VideoAdPlayerCallback onPlay : this.callbacks) {
                    onPlay.onPlay(getMediaInfo());
                }
                this.isStarted = true;
            }
            this.updateJob = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ExoPlayerVideoPlayer$onIsPlayingChanged$3(this, (Continuation<? super ExoPlayerVideoPlayer$onIsPlayingChanged$3>) null), 3, (Object) null);
            return;
        }
        Job job = this.updateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (this.isStarted) {
            for (VideoAdPlayer.VideoAdPlayerCallback onPause : this.callbacks) {
                onPause.onPause(getMediaInfo());
            }
        }
    }

    public void onPlaybackStateChanged(int i) {
        if (i == 2) {
            for (VideoAdPlayer.VideoAdPlayerCallback onBuffering : this.callbacks) {
                onBuffering.onBuffering(getMediaInfo());
            }
        } else if (i == 3) {
            if (this.isLoading) {
                for (VideoAdPlayer.VideoAdPlayerCallback onLoaded : this.callbacks) {
                    onLoaded.onLoaded(getMediaInfo());
                }
            }
            this.isLoading = false;
        } else if (i == 4) {
            for (VideoAdPlayer.VideoAdPlayerCallback onEnded : this.callbacks) {
                onEnded.onEnded(getMediaInfo());
            }
        }
    }

    public void onPlayerError(PlaybackException playbackException) {
        Intrinsics.checkNotNullParameter(playbackException, "error");
        for (VideoAdPlayer.VideoAdPlayerCallback onEnded : this.callbacks) {
            onEnded.onEnded(getMediaInfo());
        }
    }

    public void onVolumeChanged(float f) {
        if (CoroutineScopeKt.isActive(this.scope)) {
            for (VideoAdPlayer.VideoAdPlayerCallback onVolumeChanged : this.callbacks) {
                onVolumeChanged.onVolumeChanged(getMediaInfo(), RangesKt.coerceAtLeast((int) (((float) 100) * f), 1));
            }
        }
    }

    public void addCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
        Intrinsics.checkNotNullParameter(videoAdPlayerCallback, "videoAdPlayerCallback");
        this.callbacks.add(videoAdPlayerCallback);
    }

    public void removeCallback(VideoAdPlayer.VideoAdPlayerCallback videoAdPlayerCallback) {
        Intrinsics.checkNotNullParameter(videoAdPlayerCallback, "videoAdPlayerCallback");
        this.callbacks.remove(videoAdPlayerCallback);
    }

    public VideoProgressUpdate getAdProgress() {
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null) {
            if (!(exoPlayer2.getDuration() != C.TIME_UNSET)) {
                exoPlayer2 = null;
            }
            if (exoPlayer2 != null) {
                this.position = exoPlayer2.getCurrentPosition();
                this.duration = exoPlayer2.getDuration();
            }
        }
        VideoProgressUpdate videoProgressUpdate = this.duration <= 0 ? VideoProgressUpdate.VIDEO_TIME_NOT_READY : new VideoProgressUpdate(this.position, this.duration);
        Intrinsics.checkNotNullExpressionValue(videoProgressUpdate, "exoPlayer?.takeIf { it.d…ate(position, duration) }");
        return videoProgressUpdate;
    }

    public void onVideoSizeChanged(VideoSize videoSize) {
        Intrinsics.checkNotNullParameter(videoSize, "videoSize");
        TextureView textureView2 = this.textureView;
        float f = (float) videoSize.width;
        float f2 = (float) videoSize.height;
        float min = Math.min(((float) textureView2.getWidth()) / f, ((float) textureView2.getHeight()) / f2);
        Matrix transform = textureView2.getTransform(this.scaleMatrix);
        transform.setScale((f / ((float) textureView2.getWidth())) * min, (f2 / ((float) textureView2.getHeight())) * min);
        float f3 = (float) 2;
        transform.postTranslate((((float) textureView2.getWidth()) - (((float) videoSize.width) * min)) / f3, (((float) textureView2.getHeight()) - (((float) videoSize.height) * min)) / f3);
        int i = videoSize.unappliedRotationDegrees;
        if (i > 0) {
            transform.postRotate((float) i);
        }
        textureView2.setTransform(transform);
        this.creativeSize = videoSize;
    }
}
