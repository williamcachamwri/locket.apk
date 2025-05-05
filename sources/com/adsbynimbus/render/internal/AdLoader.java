package com.adsbynimbus.render.internal;

import android.content.Context;
import android.view.ViewGroup;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.Interceptor;
import com.adsbynimbus.render.Renderer;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J1\u0010\r\u001a\u00020\u000e\"\f\b\u0000\u0010\u000f*\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u000f¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0012\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0011\u0010\u001d\u001a\u00020\u0019*\u00020\u0019H\u0000¢\u0006\u0002\b\u001eR\u0014\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/adsbynimbus/render/internal/AdLoader;", "", "ad", "Lcom/adsbynimbus/NimbusAd;", "interceptors", "", "Lcom/adsbynimbus/render/Interceptor;", "(Lcom/adsbynimbus/NimbusAd;Ljava/util/List;)V", "interceptedAd", "getInterceptedAd$render_release", "()Lcom/adsbynimbus/NimbusAd;", "getInterceptors", "()Ljava/util/List;", "load", "", "T", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "renderer", "Lcom/adsbynimbus/render/Renderer;", "viewGroup", "Landroid/view/ViewGroup;", "listener", "(Lcom/adsbynimbus/render/Renderer;Landroid/view/ViewGroup;Lcom/adsbynimbus/render/Renderer$Listener;)V", "loadBlocking", "Lcom/adsbynimbus/render/AdController;", "Lcom/adsbynimbus/render/Renderer$Blocking;", "context", "Landroid/content/Context;", "intercept", "intercept$render_release", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdLoader.kt */
public final class AdLoader {
    private final NimbusAd interceptedAd;
    private final List<Interceptor> interceptors;

    public AdLoader(NimbusAd nimbusAd, List<? extends Interceptor> list) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        Intrinsics.checkNotNullParameter(list, "interceptors");
        this.interceptors = list;
        for (Interceptor modifyAd : list) {
            nimbusAd = modifyAd.modifyAd(nimbusAd);
        }
        this.interceptedAd = OpenMeasurement.applyOM(nimbusAd);
    }

    public final List<Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public final NimbusAd getInterceptedAd$render_release() {
        return this.interceptedAd;
    }

    public final AdController intercept$render_release(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        for (Interceptor modifyController : this.interceptors) {
            modifyController.modifyController(this.interceptedAd, adController);
        }
        return adController.applyOM$render_release(this.interceptedAd);
    }

    public final <T extends Renderer.Listener & NimbusError.Listener> void load(Renderer renderer, ViewGroup viewGroup, T t) {
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        Intrinsics.checkNotNullParameter(t, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        renderer.render(this.interceptedAd, viewGroup, new AdLoader$load$1(t, this));
    }

    public final AdController loadBlocking(Renderer.Blocking blocking, Context context) {
        Intrinsics.checkNotNullParameter(blocking, "renderer");
        Intrinsics.checkNotNullParameter(context, "context");
        AdController render = blocking.render(this.interceptedAd, context);
        if (render != null) {
            return intercept$render_release(render);
        }
        return null;
    }
}
