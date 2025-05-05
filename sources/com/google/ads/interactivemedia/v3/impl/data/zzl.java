package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrr;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import okio.Utf8;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzl extends zzf {
    private int bitrate;
    private boolean disableUi;
    private boolean enableFocusSkipButton;
    private boolean enablePreloading;
    private int loadVideoTimeout;
    private zzrm<String> mimeTypes;
    private double playAdsAfterTime;
    private byte set$0;
    private zzrr<UiElement> uiElements;

    zzl() {
    }

    zzl(zzg zzg) {
        this.bitrate = zzg.bitrate();
        this.mimeTypes = zzg.mimeTypes();
        this.uiElements = zzg.uiElements();
        this.enablePreloading = zzg.enablePreloading();
        this.enableFocusSkipButton = zzg.enableFocusSkipButton();
        this.playAdsAfterTime = zzg.playAdsAfterTime();
        this.disableUi = zzg.disableUi();
        this.loadVideoTimeout = zzg.loadVideoTimeout();
        this.set$0 = Utf8.REPLACEMENT_BYTE;
    }

    public zzg build() {
        if (this.set$0 == 63) {
            return new zzn(this.bitrate, this.mimeTypes, this.uiElements, this.enablePreloading, this.enableFocusSkipButton, this.playAdsAfterTime, this.disableUi, this.loadVideoTimeout);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.set$0 & 1) == 0) {
            sb.append(" bitrate");
        }
        if ((this.set$0 & 2) == 0) {
            sb.append(" enablePreloading");
        }
        if ((this.set$0 & 4) == 0) {
            sb.append(" enableFocusSkipButton");
        }
        if ((this.set$0 & 8) == 0) {
            sb.append(" playAdsAfterTime");
        }
        if ((this.set$0 & 16) == 0) {
            sb.append(" disableUi");
        }
        if ((this.set$0 & 32) == 0) {
            sb.append(" loadVideoTimeout");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    /* access modifiers changed from: package-private */
    public zzf setBitrate(int i) {
        this.bitrate = i;
        this.set$0 = (byte) (this.set$0 | 1);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setDisableUi(boolean z) {
        this.disableUi = z;
        this.set$0 = (byte) (this.set$0 | 16);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setEnableFocusSkipButton(boolean z) {
        this.enableFocusSkipButton = z;
        this.set$0 = (byte) (this.set$0 | 4);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setEnablePreloading(boolean z) {
        this.enablePreloading = z;
        this.set$0 = (byte) (this.set$0 | 2);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setLoadVideoTimeout(int i) {
        this.loadVideoTimeout = i;
        this.set$0 = (byte) (this.set$0 | 32);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setMimeTypes(@Nullable List<String> list) {
        this.mimeTypes = list == null ? null : zzrm.zzk(list);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setPlayAdsAfterTime(double d) {
        this.playAdsAfterTime = d;
        this.set$0 = (byte) (this.set$0 | 8);
        return this;
    }

    /* access modifiers changed from: package-private */
    public zzf setUiElements(@Nullable Set<UiElement> set) {
        this.uiElements = set == null ? null : zzrr.zzl(set);
        return this;
    }
}
