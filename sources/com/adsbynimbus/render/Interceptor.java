package com.adsbynimbus.render;

import com.adsbynimbus.NimbusAd;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/adsbynimbus/render/Interceptor;", "", "modifyAd", "Lcom/adsbynimbus/NimbusAd;", "ad", "modifyController", "Lcom/adsbynimbus/render/AdController;", "controller", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Renderer.kt */
public interface Interceptor {
    NimbusAd modifyAd(NimbusAd nimbusAd);

    AdController modifyController(NimbusAd nimbusAd, AdController adController);
}
