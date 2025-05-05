package com.facebook.fresco.middleware;

import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import com.facebook.fresco.ui.common.ControllerListener2;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J°\u0001\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u00062\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00062\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¨\u0006\u0016"}, d2 = {"Lcom/facebook/fresco/middleware/MiddlewareUtils;", "", "()V", "obtainExtras", "Lcom/facebook/fresco/ui/common/ControllerListener2$Extras;", "componentAttribution", "", "", "shortcutAttribution", "dataSourceExtras", "imageSourceExtras", "viewportDimensions", "Landroid/graphics/Rect;", "scaleType", "focusPoint", "Landroid/graphics/PointF;", "imageExtras", "callerContext", "logWithHighSamplingRate", "", "mainUri", "Landroid/net/Uri;", "middleware_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MiddlewareUtils.kt */
public final class MiddlewareUtils {
    public static final MiddlewareUtils INSTANCE = new MiddlewareUtils();

    private MiddlewareUtils() {
    }

    public static /* synthetic */ ControllerListener2.Extras obtainExtras$default(Map map, Map map2, Map map3, Map map4, Rect rect, String str, PointF pointF, Map map5, Object obj, boolean z, Uri uri, int i, Object obj2) {
        return obtainExtras(map, map2, map3, map4, rect, str, pointF, map5, obj, (i & 512) != 0 ? false : z, uri);
    }

    @JvmStatic
    public static final ControllerListener2.Extras obtainExtras(Map<String, ? extends Object> map, Map<String, ? extends Object> map2, Map<String, ? extends Object> map3, Map<String, ? extends Object> map4, Rect rect, String str, PointF pointF, Map<String, ? extends Object> map5, Object obj, boolean z, Uri uri) {
        Intrinsics.checkNotNullParameter(map, "componentAttribution");
        Intrinsics.checkNotNullParameter(map2, "shortcutAttribution");
        ControllerListener2.Extras extras = new ControllerListener2.Extras();
        if (rect != null) {
            extras.viewportWidth = rect.width();
            extras.viewportHeight = rect.height();
        }
        extras.scaleType = str;
        if (pointF != null) {
            extras.focusX = Float.valueOf(pointF.x);
            extras.focusY = Float.valueOf(pointF.y);
        }
        extras.callerContext = obj;
        extras.logWithHighSamplingRate = z;
        extras.mainUri = uri;
        extras.datasourceExtras = map3;
        extras.imageExtras = map5;
        extras.shortcutExtras = map2;
        extras.componentExtras = map;
        extras.imageSourceExtras = map4;
        return extras;
    }
}
