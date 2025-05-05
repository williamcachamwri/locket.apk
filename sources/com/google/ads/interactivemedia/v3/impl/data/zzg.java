package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.zzps;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrr;
import javax.annotation.Nullable;

@zzps(zza = zzn.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzg {
    public static zzf builder(AdsRenderingSettings adsRenderingSettings) {
        zzh zzh = (zzh) adsRenderingSettings;
        zzl zzl = new zzl();
        zzl.setBitrate(zzh.getBitrateKbps());
        zzl.setDisableUi(zzh.getDisableUi());
        zzl.setEnablePreloading(zzh.getEnablePreloading());
        zzl.setEnableFocusSkipButton(zzh.getFocusSkipButtonWhenAvailable());
        zzl.setLoadVideoTimeout(zzh.getLoadVideoTimeout());
        zzl.setMimeTypes(zzh.getMimeTypes());
        zzl.setPlayAdsAfterTime(zzh.getPlayAdsAfterTime());
        zzl.setUiElements(zzh.getUiElements());
        return zzl;
    }

    public abstract int bitrate();

    public abstract boolean disableUi();

    public abstract boolean enableFocusSkipButton();

    public abstract boolean enablePreloading();

    public abstract int loadVideoTimeout();

    @Nullable
    public abstract zzrm<String> mimeTypes();

    public abstract double playAdsAfterTime();

    /* access modifiers changed from: package-private */
    public abstract zzf toBuilder();

    @Nullable
    public abstract zzrr<UiElement> uiElements();
}
