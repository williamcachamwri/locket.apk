package com.google.ads.interactivemedia.v3.impl;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbx extends zzaf implements StreamDisplayContainer {
    private VideoStreamPlayer zza;

    public zzbx(ViewGroup viewGroup, VideoStreamPlayer videoStreamPlayer) {
        super(viewGroup);
        this.zza = videoStreamPlayer;
    }

    public final VideoStreamPlayer getVideoStreamPlayer() {
        return this.zza;
    }

    public final void setVideoStreamPlayer(VideoStreamPlayer videoStreamPlayer) {
        videoStreamPlayer.getClass();
        this.zza = videoStreamPlayer;
    }
}
