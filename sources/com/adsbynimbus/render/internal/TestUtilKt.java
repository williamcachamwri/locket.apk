package com.adsbynimbus.render.internal;

import android.graphics.Rect;
import com.adsbynimbus.render.AdController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"testDispatchClickDetected", "", "Lcom/adsbynimbus/render/AdController;", "testDispatchExposureChange", "exposure", "", "visibleRect", "Landroid/graphics/Rect;", "testDispatchViewableChange", "isViewable", "", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TestUtil.kt */
public final class TestUtilKt {
    public static final void testDispatchClickDetected(AdController adController) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        adController.dispatchClickDetected$render_release();
    }

    public static final void testDispatchExposureChange(AdController adController, int i, Rect rect) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
        adController.dispatchExposureChange$render_release(i, rect);
    }

    public static final void testDispatchViewableChange(AdController adController, boolean z) {
        Intrinsics.checkNotNullParameter(adController, "<this>");
        adController.dispatchViewableChange$render_release(z);
    }
}
