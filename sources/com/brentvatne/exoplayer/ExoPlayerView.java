package com.brentvatne.exoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.media3.common.AdViewProvider;
import androidx.media3.common.Format;
import androidx.media3.common.Player;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.SubtitleView;
import com.brentvatne.common.api.SubtitleStyle;
import com.brentvatne.common.api.ViewType;
import com.brentvatne.common.toolbox.DebugLog;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 =2\u00020\u00012\u00020\u0002:\u0002=>B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020*H\u0016J\u0006\u0010+\u001a\u00020(J\u0006\u0010,\u001a\u00020(J\b\u0010-\u001a\u00020(H\u0016J\u000e\u0010.\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020\bJ\u0010\u0010/\u001a\u00020(2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u000e\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020$J\u000e\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020$J\u000e\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020\u0016J\b\u00106\u001a\u00020(H\u0002J\u0006\u00107\u001a\u00020(J\u0012\u00108\u001a\u00020(2\b\u00109\u001a\u0004\u0018\u00010:H\u0002J\u0006\u0010;\u001a\u00020(J\u0010\u0010<\u001a\u00020(2\b\b\u0001\u0010#\u001a\u00020$R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\"\u0010 \u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001c@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0018\u0010#\u001a\u00020$8\u0002@\u0002X\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010&¨\u0006?"}, d2 = {"Lcom/brentvatne/exoplayer/ExoPlayerView;", "Landroid/widget/FrameLayout;", "Landroidx/media3/common/AdViewProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adOverlayFrameLayout", "adsShown", "", "getAdsShown", "()Z", "setAdsShown", "(Z)V", "componentListener", "Lcom/brentvatne/exoplayer/ExoPlayerView$ComponentListener;", "hideShutterView", "isPlaying", "layout", "Lcom/brentvatne/exoplayer/AspectRatioFrameLayout;", "layoutParams", "Landroid/view/ViewGroup$LayoutParams;", "localStyle", "Lcom/brentvatne/common/api/SubtitleStyle;", "measureAndLayout", "Ljava/lang/Runnable;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "shutterView", "Landroid/view/View;", "subtitleLayout", "Landroidx/media3/ui/SubtitleView;", "<set-?>", "surfaceView", "getSurfaceView", "()Landroid/view/View;", "viewType", "", "getViewType$annotations", "()V", "clearVideoView", "", "getAdViewGroup", "Landroid/view/ViewGroup;", "hideAds", "invalidateAspectRatio", "requestLayout", "setHideShutterView", "setPlayer", "setResizeMode", "resizeMode", "setShutterColor", "color", "setSubtitleStyle", "style", "setVideoView", "showAds", "updateForCurrentTrackSelections", "tracks", "Landroidx/media3/common/Tracks;", "updateShutterViewVisibility", "updateSurfaceView", "Companion", "ComponentListener", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExoPlayerView.kt */
public final class ExoPlayerView extends FrameLayout implements AdViewProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ExoPlayerView";
    private FrameLayout adOverlayFrameLayout;
    private boolean adsShown;
    private ComponentListener componentListener = new ComponentListener();
    private final Context context;
    private boolean hideShutterView;
    private AspectRatioFrameLayout layout;
    private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
    private SubtitleStyle localStyle = new SubtitleStyle();
    private final Runnable measureAndLayout;
    /* access modifiers changed from: private */
    public ExoPlayer player;
    /* access modifiers changed from: private */
    public View shutterView;
    /* access modifiers changed from: private */
    public SubtitleView subtitleLayout;
    private View surfaceView;
    private int viewType = 1;

    @ViewType.C0000ViewType
    private static /* synthetic */ void getViewType$annotations() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExoPlayerView(Context context2) {
        super(context2, (AttributeSet) null, 0);
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams2.gravity = 17;
        AspectRatioFrameLayout aspectRatioFrameLayout = new AspectRatioFrameLayout(context2);
        this.layout = aspectRatioFrameLayout;
        ViewGroup.LayoutParams layoutParams3 = layoutParams2;
        aspectRatioFrameLayout.setLayoutParams(layoutParams3);
        View view = new View(context2);
        this.shutterView = view;
        view.setLayoutParams(this.layoutParams);
        this.shutterView.setBackgroundColor(ContextCompat.getColor(context2, 17170444));
        SubtitleView subtitleView = new SubtitleView(context2);
        this.subtitleLayout = subtitleView;
        subtitleView.setLayoutParams(this.layoutParams);
        this.subtitleLayout.setUserDefaultStyle();
        this.subtitleLayout.setUserDefaultTextSize();
        updateSurfaceView(this.viewType);
        this.layout.addView(this.shutterView, 1, this.layoutParams);
        if (this.localStyle.getSubtitlesFollowVideo()) {
            this.layout.addView(this.subtitleLayout, this.layoutParams);
        }
        addViewInLayout(this.layout, 0, layoutParams3);
        if (!this.localStyle.getSubtitlesFollowVideo()) {
            addViewInLayout(this.subtitleLayout, 1, this.layoutParams);
        }
        this.measureAndLayout = new ExoPlayerView$$ExternalSyntheticLambda0(this);
    }

    public final View getSurfaceView() {
        return this.surfaceView;
    }

    public final boolean isPlaying() {
        ExoPlayer exoPlayer = this.player;
        if (exoPlayer == null) {
            return false;
        }
        return exoPlayer != null && exoPlayer.isPlaying();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r0 = r0.getClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void clearVideoView() {
        /*
            r3 = this;
            android.view.View r0 = r3.surfaceView
            boolean r1 = r0 instanceof android.view.TextureView
            if (r1 == 0) goto L_0x0010
            androidx.media3.exoplayer.ExoPlayer r1 = r3.player
            if (r1 == 0) goto L_0x0040
            android.view.TextureView r0 = (android.view.TextureView) r0
            r1.clearVideoTextureView(r0)
            goto L_0x0040
        L_0x0010:
            boolean r1 = r0 instanceof android.view.SurfaceView
            if (r1 == 0) goto L_0x001e
            androidx.media3.exoplayer.ExoPlayer r1 = r3.player
            if (r1 == 0) goto L_0x0040
            android.view.SurfaceView r0 = (android.view.SurfaceView) r0
            r1.clearVideoSurfaceView(r0)
            goto L_0x0040
        L_0x001e:
            if (r0 == 0) goto L_0x002b
            java.lang.Class r0 = r0.getClass()
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = r0.getName()
            goto L_0x002c
        L_0x002b:
            r0 = 0
        L_0x002c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected surfaceView type: "
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "clearVideoView"
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r1, (java.lang.String) r0)
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ExoPlayerView.clearVideoView():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r0 = r0.getClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void setVideoView() {
        /*
            r3 = this;
            android.view.View r0 = r3.surfaceView
            boolean r1 = r0 instanceof android.view.TextureView
            if (r1 == 0) goto L_0x0010
            androidx.media3.exoplayer.ExoPlayer r1 = r3.player
            if (r1 == 0) goto L_0x0041
            android.view.TextureView r0 = (android.view.TextureView) r0
            r1.setVideoTextureView(r0)
            goto L_0x0041
        L_0x0010:
            boolean r1 = r0 instanceof android.view.SurfaceView
            if (r1 == 0) goto L_0x001e
            androidx.media3.exoplayer.ExoPlayer r1 = r3.player
            if (r1 == 0) goto L_0x0041
            android.view.SurfaceView r0 = (android.view.SurfaceView) r0
            r1.setVideoSurfaceView(r0)
            goto L_0x0041
        L_0x001e:
            if (r0 == 0) goto L_0x002b
            java.lang.Class r0 = r0.getClass()
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = r0.getName()
            goto L_0x002c
        L_0x002b:
            r0 = 0
        L_0x002c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected surfaceView type: "
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "setVideoView"
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r1, (java.lang.String) r0)
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.ExoPlayerView.setVideoView():void");
    }

    public final void setSubtitleStyle(SubtitleStyle subtitleStyle) {
        Intrinsics.checkNotNullParameter(subtitleStyle, TtmlNode.TAG_STYLE);
        this.subtitleLayout.setUserDefaultStyle();
        this.subtitleLayout.setUserDefaultTextSize();
        if (subtitleStyle.getFontSize() > 0) {
            this.subtitleLayout.setFixedTextSize(2, (float) subtitleStyle.getFontSize());
        }
        this.subtitleLayout.setPadding(subtitleStyle.getPaddingLeft(), subtitleStyle.getPaddingTop(), subtitleStyle.getPaddingTop(), subtitleStyle.getPaddingBottom());
        if (!(subtitleStyle.getOpacity() == 0.0f)) {
            this.subtitleLayout.setAlpha(subtitleStyle.getOpacity());
            this.subtitleLayout.setVisibility(0);
        } else {
            this.subtitleLayout.setVisibility(8);
        }
        if (this.localStyle.getSubtitlesFollowVideo() != subtitleStyle.getSubtitlesFollowVideo()) {
            if (subtitleStyle.getSubtitlesFollowVideo()) {
                removeViewInLayout(this.subtitleLayout);
                this.layout.addView(this.subtitleLayout, this.layoutParams);
            } else {
                this.layout.removeViewInLayout(this.subtitleLayout);
                addViewInLayout(this.subtitleLayout, 1, this.layoutParams, false);
            }
            requestLayout();
        }
        this.localStyle = subtitleStyle;
    }

    public final void setShutterColor(int i) {
        this.shutterView.setBackgroundColor(i);
    }

    public final void updateSurfaceView(@ViewType.C0000ViewType int i) {
        boolean z;
        this.viewType = i;
        boolean z2 = true;
        if (i == 0) {
            if (!(this.surfaceView instanceof TextureView)) {
                this.surfaceView = new TextureView(this.context);
            } else {
                z2 = false;
            }
            View view = this.surfaceView;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.view.TextureView");
            ((TextureView) view).setOpaque(false);
            z = z2;
        } else if (i == 1 || i == 2) {
            if (!(this.surfaceView instanceof SurfaceView)) {
                this.surfaceView = new SurfaceView(this.context);
                z = true;
            } else {
                z = false;
            }
            View view2 = this.surfaceView;
            Intrinsics.checkNotNull(view2, "null cannot be cast to non-null type android.view.SurfaceView");
            SurfaceView surfaceView2 = (SurfaceView) view2;
            if (i != 2) {
                z2 = false;
            }
            surfaceView2.setSecure(z2);
        } else {
            DebugLog.wtf(TAG, "Unexpected texture view type: " + i);
            z = false;
        }
        if (z) {
            View view3 = this.surfaceView;
            if (view3 != null) {
                view3.setLayoutParams(this.layoutParams);
            }
            if (this.layout.getChildAt(0) != null) {
                this.layout.removeViewAt(0);
            }
            this.layout.addView(this.surfaceView, 0, this.layoutParams);
            if (this.player != null) {
                setVideoView();
            }
        }
    }

    public final boolean getAdsShown() {
        return this.adsShown;
    }

    public final void setAdsShown(boolean z) {
        this.adsShown = z;
    }

    public final void showAds() {
        if (!this.adsShown) {
            FrameLayout frameLayout = new FrameLayout(this.context);
            this.adOverlayFrameLayout = frameLayout;
            this.layout.addView(frameLayout, this.layoutParams);
            this.adsShown = true;
        }
    }

    public final void hideAds() {
        if (this.adsShown) {
            this.layout.removeView(this.adOverlayFrameLayout);
            this.adOverlayFrameLayout = null;
            this.adsShown = false;
        }
    }

    public final void updateShutterViewVisibility() {
        this.shutterView.setVisibility(this.hideShutterView ? 4 : 0);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public ViewGroup getAdViewGroup() {
        Object checkNotNull = Assertions.checkNotNull(this.adOverlayFrameLayout, "exo_ad_overlay must be present for ad playback");
        Intrinsics.checkNotNullExpressionValue(checkNotNull, "checkNotNull(...)");
        return (ViewGroup) checkNotNull;
    }

    public final void setPlayer(ExoPlayer exoPlayer) {
        if (!Intrinsics.areEqual((Object) this.player, (Object) exoPlayer)) {
            ExoPlayer exoPlayer2 = this.player;
            if (exoPlayer2 != null) {
                Intrinsics.checkNotNull(exoPlayer2);
                exoPlayer2.removeListener(this.componentListener);
                clearVideoView();
            }
            this.player = exoPlayer;
            updateShutterViewVisibility();
            if (exoPlayer != null) {
                setVideoView();
                exoPlayer.addListener(this.componentListener);
            }
        }
    }

    public final void setResizeMode(int i) {
        if (this.layout.getResizeMode() != i) {
            this.layout.setResizeMode(i);
            post(this.measureAndLayout);
        }
    }

    public final void setHideShutterView(boolean z) {
        this.hideShutterView = z;
        updateShutterViewVisibility();
    }

    /* access modifiers changed from: private */
    public static final void measureAndLayout$lambda$0(ExoPlayerView exoPlayerView) {
        Intrinsics.checkNotNullParameter(exoPlayerView, "this$0");
        exoPlayerView.measure(View.MeasureSpec.makeMeasureSpec(exoPlayerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(exoPlayerView.getHeight(), 1073741824));
        exoPlayerView.layout(exoPlayerView.getLeft(), exoPlayerView.getTop(), exoPlayerView.getRight(), exoPlayerView.getBottom());
    }

    /* access modifiers changed from: private */
    public final void updateForCurrentTrackSelections(Tracks tracks) {
        if (tracks != null) {
            ImmutableList<Tracks.Group> groups = tracks.getGroups();
            Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
            UnmodifiableIterator<Tracks.Group> it = groups.iterator();
            while (it.hasNext()) {
                Tracks.Group group = (Tracks.Group) it.next();
                if (group.getType() == 2 && group.length > 0) {
                    Format trackFormat = group.getTrackFormat(0);
                    Intrinsics.checkNotNullExpressionValue(trackFormat, "getTrackFormat(...)");
                    this.layout.updateAspectRatio(trackFormat);
                    return;
                }
            }
            updateShutterViewVisibility();
        }
    }

    public final void invalidateAspectRatio() {
        this.layout.invalidateAspectRatio();
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/brentvatne/exoplayer/ExoPlayerView$ComponentListener;", "Landroidx/media3/common/Player$Listener;", "(Lcom/brentvatne/exoplayer/ExoPlayerView;)V", "onCues", "", "cues", "", "Landroidx/media3/common/text/Cue;", "onRenderedFirstFrame", "onTracksChanged", "tracks", "Landroidx/media3/common/Tracks;", "onVideoSizeChanged", "videoSize", "Landroidx/media3/common/VideoSize;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExoPlayerView.kt */
    private final class ComponentListener implements Player.Listener {
        public ComponentListener() {
        }

        public void onCues(List<Cue> list) {
            Intrinsics.checkNotNullParameter(list, "cues");
            ExoPlayerView.this.subtitleLayout.setCues(list);
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            ExoPlayer access$getPlayer$p;
            Intrinsics.checkNotNullParameter(videoSize, "videoSize");
            if (videoSize.height != 0 && videoSize.width != 0 && (access$getPlayer$p = ExoPlayerView.this.player) != null) {
                ExoPlayerView.this.updateForCurrentTrackSelections(access$getPlayer$p.getCurrentTracks());
            }
        }

        public void onRenderedFirstFrame() {
            ExoPlayerView.this.shutterView.setVisibility(4);
        }

        public void onTracksChanged(Tracks tracks) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            ExoPlayerView.this.updateForCurrentTrackSelections(tracks);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/exoplayer/ExoPlayerView$Companion;", "", "()V", "TAG", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExoPlayerView.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
