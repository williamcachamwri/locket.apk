package com.adsbynimbus.render;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.exoplayer.ExoPlayer;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Component;
import com.adsbynimbus.internal.PlatformKt;
import com.adsbynimbus.render.Renderer;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0002&'B\u001f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J3\u0010\u001c\u001a\u00020\u001b\"\f\b\u0000\u0010\u001d*\u00020\u001e*\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u0002H\u001dH\u0016¢\u0006\u0002\u0010%R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006("}, d2 = {"Lcom/adsbynimbus/render/VideoAdRenderer;", "Lcom/adsbynimbus/render/Renderer;", "Lcom/adsbynimbus/internal/Component;", "playerProvider", "Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;", "requestMimeTypes", "", "", "(Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;[Ljava/lang/String;)V", "getPlayerProvider", "()Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;", "renderingSettings", "Lcom/google/ads/interactivemedia/v3/api/AdsRenderingSettings;", "getRenderingSettings", "()Lcom/google/ads/interactivemedia/v3/api/AdsRenderingSettings;", "getRequestMimeTypes", "()[Ljava/lang/String;", "[Ljava/lang/String;", "sdkFactory", "Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;", "getSdkFactory", "()Lcom/google/ads/interactivemedia/v3/api/ImaSdkFactory;", "settings", "Lcom/google/ads/interactivemedia/v3/api/ImaSdkSettings;", "getSettings", "()Lcom/google/ads/interactivemedia/v3/api/ImaSdkSettings;", "install", "", "render", "T", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "ad", "Lcom/adsbynimbus/NimbusAd;", "container", "Landroid/view/ViewGroup;", "listener", "(Lcom/adsbynimbus/NimbusAd;Landroid/view/ViewGroup;Lcom/adsbynimbus/render/Renderer$Listener;)V", "Companion", "PlayerProvider", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: VideoAdRenderer.kt */
public final class VideoAdRenderer implements Renderer, Component {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean showMuteButton;
    private final PlayerProvider playerProvider;
    private final AdsRenderingSettings renderingSettings;
    private final String[] requestMimeTypes;
    private final ImaSdkFactory sdkFactory;
    private final ImaSdkSettings settings;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lcom/adsbynimbus/render/VideoAdRenderer$PlayerProvider;", "", "acquirePlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "context", "Landroid/content/Context;", "cacheVideo", "", "url", "", "createPlayer", "offerPlayer", "player", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: VideoAdRenderer.kt */
    public interface PlayerProvider {
        ExoPlayer acquirePlayer(Context context);

        void cacheVideo(String str);

        ExoPlayer createPlayer(Context context);

        void offerPlayer(ExoPlayer exoPlayer);
    }

    public VideoAdRenderer() {
        this((PlayerProvider) null, (String[]) null, 3, (DefaultConstructorMarker) null);
    }

    public static final boolean getShowMuteButton() {
        return Companion.getShowMuteButton();
    }

    public static final void setShowMuteButton(boolean z) {
        Companion.setShowMuteButton(z);
    }

    public VideoAdRenderer(PlayerProvider playerProvider2, String[] strArr) {
        Intrinsics.checkNotNullParameter(playerProvider2, "playerProvider");
        Intrinsics.checkNotNullParameter(strArr, "requestMimeTypes");
        this.playerProvider = playerProvider2;
        this.requestMimeTypes = strArr;
        ImaSdkFactory instance = ImaSdkFactory.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        this.sdkFactory = instance;
        ImaSdkSettings createImaSdkSettings = instance.createImaSdkSettings();
        Intrinsics.checkNotNullExpressionValue(createImaSdkSettings, "sdkFactory.createImaSdkSettings()");
        if (Nimbus.testMode) {
            createImaSdkSettings.setDebugMode(true);
        }
        createImaSdkSettings.setPlayerType(MediaLibraryInfo.TAG);
        createImaSdkSettings.setPlayerVersion(MediaLibraryInfo.VERSION);
        this.settings = createImaSdkSettings;
        AdsRenderingSettings createAdsRenderingSettings = instance.createAdsRenderingSettings();
        Intrinsics.checkNotNullExpressionValue(createAdsRenderingSettings, "sdkFactory.createAdsRenderingSettings()");
        createAdsRenderingSettings.setEnablePreloading(true);
        createAdsRenderingSettings.setMimeTypes(CollectionsKt.plus(ArraysKt.toList((T[]) strArr), CollectionsKt.listOf(MimeTypes.VIDEO_MPEG, MimeTypes.VIDEO_WEBM, MimeTypes.APPLICATION_MP4, MimeTypes.APPLICATION_WEBM, MimeTypes.VIDEO_MPEG2, MimeTypes.VIDEO_OGG, "video/3gp")));
        createAdsRenderingSettings.setLoadVideoTimeout(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
        this.renderingSettings = createAdsRenderingSettings;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VideoAdRenderer(PlayerProvider playerProvider2, String[] strArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExoPlayerProvider.INSTANCE : playerProvider2, (i & 2) != 0 ? new String[]{MimeTypes.APPLICATION_M3U8, MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_H263, MimeTypes.VIDEO_FLV} : strArr);
    }

    public final PlayerProvider getPlayerProvider() {
        return this.playerProvider;
    }

    public final String[] getRequestMimeTypes() {
        return this.requestMimeTypes;
    }

    public final ImaSdkFactory getSdkFactory() {
        return this.sdkFactory;
    }

    public final ImaSdkSettings getSettings() {
        return this.settings;
    }

    public final AdsRenderingSettings getRenderingSettings() {
        return this.renderingSettings;
    }

    public <T extends Renderer.Listener & NimbusError.Listener> void render(NimbusAd nimbusAd, ViewGroup viewGroup, T t) {
        Set set;
        T t2 = t;
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(t2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "container.context");
        NimbusAdView nimbusAdView = new NimbusAdView(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        if (this.renderingSettings.getDisableUi()) {
            nimbusAdView.setAlpha(0.0f);
        }
        ExoPlayerVideoPlayer exoPlayerVideoPlayer = new ExoPlayerVideoPlayer(nimbusAd.getAuctionId(), new TextureView(viewGroup.getContext()), this.playerProvider, (List) null, 8, (DefaultConstructorMarker) null);
        AdDisplayContainer createAdDisplayContainer = ImaSdkFactory.createAdDisplayContainer(nimbusAdView, exoPlayerVideoPlayer);
        CompanionAd[] companionAds = nimbusAd.companionAds();
        if (companionAds != null) {
            Collection arrayList = new ArrayList(companionAds.length);
            int length = companionAds.length;
            int i = 0;
            while (i < length) {
                CompanionAd companionAd = companionAds[i];
                CompanionAdSlot createCompanionAdSlot = this.sdkFactory.createCompanionAdSlot();
                FrameLayout frameLayout = new FrameLayout(nimbusAdView.getContext());
                frameLayout.setVisibility(4);
                CompanionAd[] companionAdArr = companionAds;
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, companionAd.getHeight() > 250 ? -1 : -2, companionAd.getGravity() | 1));
                frameLayout.setMinimumHeight(Integer.valueOf(nimbusAdView.getPx(Integer.valueOf(companionAd.getHeight()))).intValue());
                createCompanionAdSlot.setSize(companionAd.getWidth(), companionAd.getHeight());
                createCompanionAdSlot.setContainer(frameLayout);
                nimbusAdView.addView(frameLayout, new FrameLayout.LayoutParams(-1, -2, 17));
                arrayList.add(createCompanionAdSlot);
                i++;
                ViewGroup viewGroup2 = viewGroup;
                companionAds = companionAdArr;
            }
            set = CollectionsKt.toSet((List) arrayList);
        } else {
            set = null;
        }
        createAdDisplayContainer.setCompanionSlots(set);
        Intrinsics.checkNotNullExpressionValue(createAdDisplayContainer, "createAdDisplayContainer…    }?.toSet())\n        }");
        AdsLoader createAdsLoader = this.sdkFactory.createAdsLoader(viewGroup.getContext(), this.settings, createAdDisplayContainer);
        createAdsLoader.addAdErrorListener(new VideoAdRenderer$$ExternalSyntheticLambda0(t2));
        createAdsLoader.addAdsLoadedListener(new VideoAdRenderer$$ExternalSyntheticLambda1(nimbusAdView, createAdDisplayContainer, exoPlayerVideoPlayer, createAdsLoader, viewGroup, t, this));
        AdsRequest createAdsRequest = this.sdkFactory.createAdsRequest();
        createAdsRequest.setAdsResponse(nimbusAd.markup());
        createAdsLoader.requestAds(createAdsRequest);
    }

    /* access modifiers changed from: private */
    public static final void render$lambda$10$lambda$8(Renderer.Listener listener, AdErrorEvent adErrorEvent) {
        Intrinsics.checkNotNullParameter(adErrorEvent, "it");
        ((NimbusError.Listener) listener).onError(new NimbusError(NimbusError.ErrorType.RENDERER_ERROR, "Error loading VAST video", adErrorEvent.getError()));
    }

    /* access modifiers changed from: private */
    public static final void render$lambda$10$lambda$9(NimbusAdView nimbusAdView, AdDisplayContainer adDisplayContainer, ExoPlayerVideoPlayer exoPlayerVideoPlayer, AdsLoader adsLoader, ViewGroup viewGroup, Renderer.Listener listener, VideoAdRenderer videoAdRenderer, AdsManagerLoadedEvent adsManagerLoadedEvent) {
        Intrinsics.checkNotNullParameter(adsManagerLoadedEvent, "it");
        Intrinsics.checkNotNullExpressionValue(adsLoader, "this");
        AdsManager adsManager = adsManagerLoadedEvent.getAdsManager();
        Intrinsics.checkNotNullExpressionValue(adsManager, "it.adsManager");
        ImaVideoAdController imaVideoAdController = new ImaVideoAdController(nimbusAdView, adDisplayContainer, exoPlayerVideoPlayer, adsLoader, adsManager);
        if (!showMuteButton) {
            imaVideoAdController.getMuteButton().setVisibility(8);
        }
        AdController adController = imaVideoAdController;
        nimbusAdView.adController = adController;
        nimbusAdView.addView(exoPlayerVideoPlayer.textureView, new ViewGroup.LayoutParams(-1, -1));
        viewGroup.addView(nimbusAdView, new ViewGroup.LayoutParams(-1, -1));
        listener.onAdRendered(adController);
        adsManagerLoadedEvent.getAdsManager().init(videoAdRenderer.renderingSettings);
    }

    public void install() {
        Renderer.INLINE.put(MimeTypes.BASE_TYPE_VIDEO, this);
        Nimbus.videoMimeTypes = this.requestMimeTypes;
        if (this.playerProvider instanceof ComponentCallbacks2) {
            Nimbus nimbus = Nimbus.INSTANCE;
            Application application = PlatformKt.getApplication();
            if (!(application instanceof Application)) {
                application = null;
            }
            if (application != null) {
                application.registerComponentCallbacks((ComponentCallbacks) this.playerProvider);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/adsbynimbus/render/VideoAdRenderer$Companion;", "", "()V", "showMuteButton", "", "getShowMuteButton$annotations", "getShowMuteButton", "()Z", "setShowMuteButton", "(Z)V", "video_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: VideoAdRenderer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getShowMuteButton$annotations() {
        }

        private Companion() {
        }

        public final boolean getShowMuteButton() {
            return VideoAdRenderer.showMuteButton;
        }

        public final void setShowMuteButton(boolean z) {
            VideoAdRenderer.showMuteButton = z;
        }
    }
}
