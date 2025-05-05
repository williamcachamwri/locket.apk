package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzf {
    public abstract zzg build();

    /* access modifiers changed from: package-private */
    public abstract zzf setBitrate(int i);

    /* access modifiers changed from: package-private */
    public abstract zzf setDisableUi(boolean z);

    /* access modifiers changed from: package-private */
    public abstract zzf setEnableFocusSkipButton(boolean z);

    /* access modifiers changed from: package-private */
    public abstract zzf setEnablePreloading(boolean z);

    /* access modifiers changed from: package-private */
    public abstract zzf setLoadVideoTimeout(int i);

    /* access modifiers changed from: package-private */
    public abstract zzf setMimeTypes(@Nullable List<String> list);

    /* access modifiers changed from: package-private */
    public abstract zzf setPlayAdsAfterTime(double d);

    /* access modifiers changed from: package-private */
    public abstract zzf setUiElements(@Nullable Set<UiElement> set);
}
