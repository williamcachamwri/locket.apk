package com.adsbynimbus.render;

import android.view.View;
import com.adsbynimbus.render.Renderer;
import com.adsbynimbus.render.internal.OpenMeasurement;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u000e\u001a\u00020\u0007*\u00020\u000f\"n\u0010\b\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0001*\u00020\t2)\u0010\u0000\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"value", "Lkotlin/Function1;", "", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "views", "", "possibleObstructionListener", "Lcom/adsbynimbus/render/Renderer$Companion;", "getPossibleObstructionListener", "(Lcom/adsbynimbus/render/Renderer$Companion;)Lkotlin/jvm/functions/Function1;", "setPossibleObstructionListener", "(Lcom/adsbynimbus/render/Renderer$Companion;Lkotlin/jvm/functions/Function1;)V", "disableClickProtection", "Lcom/adsbynimbus/render/AdController;", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Renderer.kt */
public final class RendererKt {
    public static final Function1<List<? extends View>, Unit> getPossibleObstructionListener(Renderer.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return OpenMeasurement.getInternalObstructionListener();
    }

    public static final void setPossibleObstructionListener(Renderer.Companion companion, Function1<? super List<? extends View>, Unit> function1) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        OpenMeasurement.setInternalObstructionListener(function1);
    }

    public static final void disableClickProtection(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        View view = adController.getView();
        NimbusAdView nimbusAdView = view instanceof NimbusAdView ? (NimbusAdView) view : null;
        if (nimbusAdView != null) {
            nimbusAdView.setClickProtectionDisabled$render_release(true);
        }
    }
}
