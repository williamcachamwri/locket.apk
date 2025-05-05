package com.adsbynimbus.render.internal;

import com.adsbynimbus.render.AdController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"internalClickDispatch", "", "Lcom/adsbynimbus/render/AdController;", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ControllerHelper.kt */
public final class ControllerHelperKt {
    public static final void internalClickDispatch(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        adController.dispatchClickDetected$render_release();
    }
}
