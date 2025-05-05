package com.adsbynimbus.render.internal;

import com.adsbynimbus.NimbusError;
import com.adsbynimbus.render.AdController;
import com.adsbynimbus.render.Renderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/adsbynimbus/render/internal/AdLoader$load$1", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "onAdRendered", "", "controller", "Lcom/adsbynimbus/render/AdController;", "onError", "error", "Lcom/adsbynimbus/NimbusError;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AdLoader.kt */
public final class AdLoader$load$1 implements Renderer.Listener, NimbusError.Listener {
    final /* synthetic */ T $listener;
    final /* synthetic */ AdLoader this$0;

    AdLoader$load$1(T t, AdLoader adLoader) {
        this.$listener = t;
        this.this$0 = adLoader;
    }

    public void onError(NimbusError nimbusError) {
        Intrinsics.checkNotNullParameter(nimbusError, "error");
        ((NimbusError.Listener) this.$listener).onError(nimbusError);
    }

    public void onAdRendered(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "controller");
        this.$listener.onAdRendered(this.this$0.intercept$render_release(adController));
    }
}
