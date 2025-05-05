package com.adsbynimbus.render.mraid;

import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\n"}, d2 = {"asPosition", "Lcom/adsbynimbus/render/mraid/Position;", "Landroid/graphics/Rect;", "getAsPosition", "(Landroid/graphics/Rect;)Lcom/adsbynimbus/render/mraid/Position;", "isValidFor", "", "Lcom/adsbynimbus/render/mraid/ResizeProperties;", "maxSize", "Lcom/adsbynimbus/render/mraid/Size;", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Properties.kt */
public final class PropertiesKt {
    public static final Position getAsPosition(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        return new Position(rect.width(), rect.height(), rect.left, rect.top);
    }

    public static final boolean isValidFor(ResizeProperties resizeProperties, Size size) {
        Intrinsics.checkNotNullParameter(resizeProperties, "<this>");
        Intrinsics.checkNotNullParameter(size, "maxSize");
        int width = 50 - resizeProperties.getWidth();
        int width2 = size.getWidth() - resizeProperties.getWidth();
        int offsetX = resizeProperties.getOffsetX();
        if (width <= offsetX && offsetX <= width2) {
            int height = 50 - resizeProperties.getHeight();
            int height2 = size.getHeight() - resizeProperties.getHeight();
            int offsetY = resizeProperties.getOffsetY();
            if (height <= offsetY && offsetY <= height2) {
                return true;
            }
        }
        return false;
    }
}
