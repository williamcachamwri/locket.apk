package com.google.ads.interactivemedia.v3.impl;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzc extends zzaf implements AdDisplayContainer {
    private VideoAdPlayer zza;

    public zzc(ViewGroup viewGroup, VideoAdPlayer videoAdPlayer) {
        super(viewGroup);
        this.zza = videoAdPlayer;
    }

    public final VideoAdPlayer getPlayer() {
        return this.zza;
    }

    public final void setPlayer(VideoAdPlayer videoAdPlayer) {
        videoAdPlayer.getClass();
        this.zza = videoAdPlayer;
    }
}
