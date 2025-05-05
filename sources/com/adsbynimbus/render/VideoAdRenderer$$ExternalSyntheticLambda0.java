package com.adsbynimbus.render;

import com.adsbynimbus.render.Renderer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoAdRenderer$$ExternalSyntheticLambda0 implements AdErrorEvent.AdErrorListener {
    public final /* synthetic */ Renderer.Listener f$0;

    public /* synthetic */ VideoAdRenderer$$ExternalSyntheticLambda0(Renderer.Listener listener) {
        this.f$0 = listener;
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
        VideoAdRenderer.render$lambda$10$lambda$8(this.f$0, adErrorEvent);
    }
}
