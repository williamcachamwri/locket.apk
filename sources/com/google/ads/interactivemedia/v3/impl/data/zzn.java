package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrr;
import javax.annotation.Nullable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzn extends zzg {
    private final int bitrate;
    private final boolean disableUi;
    private final boolean enableFocusSkipButton;
    private final boolean enablePreloading;
    private final int loadVideoTimeout;
    @Nullable
    private final zzrm<String> mimeTypes;
    private final double playAdsAfterTime;
    @Nullable
    private final zzrr<UiElement> uiElements;

    private zzn(int i, @Nullable zzrm<String> zzrm, @Nullable zzrr<UiElement> zzrr, boolean z, boolean z2, double d, boolean z3, int i2) {
        this.bitrate = i;
        this.mimeTypes = zzrm;
        this.uiElements = zzrr;
        this.enablePreloading = z;
        this.enableFocusSkipButton = z2;
        this.playAdsAfterTime = d;
        this.disableUi = z3;
        this.loadVideoTimeout = i2;
    }

    public int bitrate() {
        return this.bitrate;
    }

    public boolean disableUi() {
        return this.disableUi;
    }

    public boolean enableFocusSkipButton() {
        return this.enableFocusSkipButton;
    }

    public boolean enablePreloading() {
        return this.enablePreloading;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r1 = r7.uiElements;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = r7.mimeTypes;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r8 != r7) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.ads.interactivemedia.v3.impl.data.zzg
            r2 = 0
            if (r1 == 0) goto L_0x0071
            com.google.ads.interactivemedia.v3.impl.data.zzg r8 = (com.google.ads.interactivemedia.v3.impl.data.zzg) r8
            int r1 = r7.bitrate
            int r3 = r8.bitrate()
            if (r1 != r3) goto L_0x0071
            com.google.ads.interactivemedia.v3.internal.zzrm<java.lang.String> r1 = r7.mimeTypes
            if (r1 != 0) goto L_0x001e
            com.google.ads.interactivemedia.v3.internal.zzrm r1 = r8.mimeTypes()
            if (r1 != 0) goto L_0x0071
            goto L_0x0028
        L_0x001e:
            com.google.ads.interactivemedia.v3.internal.zzrm r3 = r8.mimeTypes()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0071
        L_0x0028:
            com.google.ads.interactivemedia.v3.internal.zzrr<com.google.ads.interactivemedia.v3.api.UiElement> r1 = r7.uiElements
            if (r1 != 0) goto L_0x0033
            com.google.ads.interactivemedia.v3.internal.zzrr r1 = r8.uiElements()
            if (r1 != 0) goto L_0x0071
            goto L_0x003e
        L_0x0033:
            com.google.ads.interactivemedia.v3.internal.zzrr r3 = r8.uiElements()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x003e
            goto L_0x0071
        L_0x003e:
            boolean r1 = r7.enablePreloading
            boolean r3 = r8.enablePreloading()
            if (r1 != r3) goto L_0x0071
            boolean r1 = r7.enableFocusSkipButton
            boolean r3 = r8.enableFocusSkipButton()
            if (r1 != r3) goto L_0x0071
            double r3 = r7.playAdsAfterTime
            long r3 = java.lang.Double.doubleToLongBits(r3)
            double r5 = r8.playAdsAfterTime()
            long r5 = java.lang.Double.doubleToLongBits(r5)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0071
            boolean r1 = r7.disableUi
            boolean r3 = r8.disableUi()
            if (r1 != r3) goto L_0x0071
            int r1 = r7.loadVideoTimeout
            int r8 = r8.loadVideoTimeout()
            if (r1 != r8) goto L_0x0071
            return r0
        L_0x0071:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.data.zzn.equals(java.lang.Object):boolean");
    }

    public int loadVideoTimeout() {
        return this.loadVideoTimeout;
    }

    @Nullable
    public zzrm<String> mimeTypes() {
        return this.mimeTypes;
    }

    public double playAdsAfterTime() {
        return this.playAdsAfterTime;
    }

    /* access modifiers changed from: package-private */
    public zzf toBuilder() {
        return new zzl(this);
    }

    public String toString() {
        zzrr<UiElement> zzrr = this.uiElements;
        String valueOf = String.valueOf(this.mimeTypes);
        String valueOf2 = String.valueOf(zzrr);
        return "AdsRenderingSettingsData{bitrate=" + this.bitrate + ", mimeTypes=" + valueOf + ", uiElements=" + valueOf2 + ", enablePreloading=" + this.enablePreloading + ", enableFocusSkipButton=" + this.enableFocusSkipButton + ", playAdsAfterTime=" + this.playAdsAfterTime + ", disableUi=" + this.disableUi + ", loadVideoTimeout=" + this.loadVideoTimeout + "}";
    }

    @Nullable
    public zzrr<UiElement> uiElements() {
        return this.uiElements;
    }

    public int hashCode() {
        int i;
        zzrm<String> zzrm = this.mimeTypes;
        int i2 = 0;
        if (zzrm == null) {
            i = 0;
        } else {
            i = zzrm.hashCode();
        }
        int i3 = this.bitrate;
        zzrr<UiElement> zzrr = this.uiElements;
        if (zzrr != null) {
            i2 = zzrr.hashCode();
        }
        int i4 = 1237;
        int doubleToLongBits = (((((((((i ^ ((i3 ^ 1000003) * 1000003)) * 1000003) ^ i2) * 1000003) ^ (true != this.enablePreloading ? 1237 : 1231)) * 1000003) ^ (true != this.enableFocusSkipButton ? 1237 : 1231)) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.playAdsAfterTime) >>> 32) ^ Double.doubleToLongBits(this.playAdsAfterTime)))) * 1000003;
        if (true == this.disableUi) {
            i4 = 1231;
        }
        return ((doubleToLongBits ^ i4) * 1000003) ^ this.loadVideoTimeout;
    }
}
