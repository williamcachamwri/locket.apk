package com.brentvatne.exoplayer;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.source.ads.AdsLoader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactExoplayerView$$ExternalSyntheticLambda15 implements AdsLoader.Provider {
    public final /* synthetic */ ReactExoplayerView f$0;

    public /* synthetic */ ReactExoplayerView$$ExternalSyntheticLambda15(ReactExoplayerView reactExoplayerView) {
        this.f$0 = reactExoplayerView;
    }

    public final AdsLoader getAdsLoader(MediaItem.AdsConfiguration adsConfiguration) {
        return this.f$0.lambda$initializeAds$14(adsConfiguration);
    }
}
