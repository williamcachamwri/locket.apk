package com.adsbynimbus.render;

import android.view.ViewGroup;
import com.adsbynimbus.render.Renderer;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoAdRenderer$$ExternalSyntheticLambda1 implements AdsLoader.AdsLoadedListener {
    public final /* synthetic */ NimbusAdView f$0;
    public final /* synthetic */ AdDisplayContainer f$1;
    public final /* synthetic */ ExoPlayerVideoPlayer f$2;
    public final /* synthetic */ AdsLoader f$3;
    public final /* synthetic */ ViewGroup f$4;
    public final /* synthetic */ Renderer.Listener f$5;
    public final /* synthetic */ VideoAdRenderer f$6;

    public /* synthetic */ VideoAdRenderer$$ExternalSyntheticLambda1(NimbusAdView nimbusAdView, AdDisplayContainer adDisplayContainer, ExoPlayerVideoPlayer exoPlayerVideoPlayer, AdsLoader adsLoader, ViewGroup viewGroup, Renderer.Listener listener, VideoAdRenderer videoAdRenderer) {
        this.f$0 = nimbusAdView;
        this.f$1 = adDisplayContainer;
        this.f$2 = exoPlayerVideoPlayer;
        this.f$3 = adsLoader;
        this.f$4 = viewGroup;
        this.f$5 = listener;
        this.f$6 = videoAdRenderer;
    }

    public final void onAdsManagerLoaded(AdsManagerLoadedEvent adsManagerLoadedEvent) {
        VideoAdRenderer.render$lambda$10$lambda$9(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, adsManagerLoadedEvent);
    }
}
