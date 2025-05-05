package com.adsbynimbus.render;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.adsbynimbus.Nimbus;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.render.Renderer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0012\u0010\u0015\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\bH\u0007J\u0010\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0004H\u0007J\u0012\u0010\u001c\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0004H\u0007J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0004H\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\u00048\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/adsbynimbus/render/BlockingAdRenderer;", "Lcom/adsbynimbus/render/Renderer$Blocking;", "()V", "sCloseButtonDelay", "", "sCloseButtonDelayRender", "sCloseDelayAfterComplete", "sDismissOnComplete", "", "sDismissOrientation", "sStaticDismissTimeout", "render", "Lcom/adsbynimbus/render/AdController;", "ad", "Lcom/adsbynimbus/NimbusAd;", "context", "Landroid/content/Context;", "setCloseButtonDelay", "", "delaySeconds", "setDelayAfterComplete", "setDismissDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setDismissOnComplete", "dismissOnComplete", "setDismissOrientation", "orientation", "setMuteButton", "setStaticDismissTimeout", "staticDismissTimeout", "setsCloseButtonDelayRender", "delayMillis", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BlockingAdRenderer.kt */
public final class BlockingAdRenderer implements Renderer.Blocking {
    public static final BlockingAdRenderer INSTANCE = new BlockingAdRenderer();
    public static int sCloseButtonDelay = 5000;
    public static int sCloseButtonDelayRender = -1;
    public static int sCloseDelayAfterComplete;
    public static boolean sDismissOnComplete;
    public static int sDismissOrientation;
    public static int sStaticDismissTimeout;

    private BlockingAdRenderer() {
    }

    public AdController render(NimbusAd nimbusAd, Context context) {
        Intrinsics.checkNotNullParameter(nimbusAd, "ad");
        Intrinsics.checkNotNullParameter(context, "context");
        int i = sCloseButtonDelayRender;
        if (i <= -1) {
            i = sCloseButtonDelay;
        }
        BlockingAdController blockingAdController = new BlockingAdController(nimbusAd, i, sCloseDelayAfterComplete);
        sCloseButtonDelayRender = -1;
        sCloseDelayAfterComplete = 0;
        return blockingAdController;
    }

    @JvmStatic
    public static final void setCloseButtonDelay(int i) {
        sCloseButtonDelay = RangesKt.coerceAtLeast(i, 0) * 1000;
    }

    @JvmStatic
    public static final void setStaticDismissTimeout(int i) {
        sStaticDismissTimeout = i;
    }

    @JvmStatic
    public static final void setDismissOnComplete(boolean z) {
        sDismissOnComplete = z;
    }

    @JvmStatic
    public static final void setDismissDrawable(Drawable drawable) {
        Nimbus.closeDrawable = drawable;
    }

    @JvmStatic
    public static final void setDismissOrientation(int i) {
        sDismissOrientation = i;
    }

    @JvmStatic
    public static final void setMuteButton(Drawable drawable) {
        Nimbus.muteDrawable = drawable;
    }

    @JvmStatic
    public static final void setDelayAfterComplete(int i) {
        sCloseDelayAfterComplete = RangesKt.coerceAtLeast(i, 0) * 1000;
    }

    @JvmStatic
    public static final void setsCloseButtonDelayRender(int i) {
        sCloseButtonDelayRender = i;
    }
}
