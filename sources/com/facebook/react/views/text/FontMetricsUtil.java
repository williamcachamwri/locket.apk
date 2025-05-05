package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import io.sentry.protocol.ViewHierarchyNode;

public class FontMetricsUtil {
    private static final float AMPLIFICATION_FACTOR = 100.0f;
    private static final String CAP_HEIGHT_MEASUREMENT_TEXT = "T";
    private static final String X_HEIGHT_MEASUREMENT_TEXT = "x";

    public static WritableArray getFontMetrics(CharSequence charSequence, Layout layout, TextPaint textPaint, Context context) {
        CharSequence charSequence2 = charSequence;
        Layout layout2 = layout;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        WritableArray createArray = Arguments.createArray();
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(textPaint2.getTextSize() * AMPLIFICATION_FACTOR);
        Rect rect = new Rect();
        int i = 0;
        int i2 = 1;
        textPaint2.getTextBounds("T", 0, 1, rect);
        double height = (double) ((((float) rect.height()) / AMPLIFICATION_FACTOR) / displayMetrics.density);
        Rect rect2 = new Rect();
        textPaint2.getTextBounds("x", 0, 1, rect2);
        double height2 = (double) ((((float) rect2.height()) / AMPLIFICATION_FACTOR) / displayMetrics.density);
        int i3 = 0;
        while (i3 < layout.getLineCount()) {
            float lineMax = ((charSequence.length() <= 0 || charSequence2.charAt(layout2.getLineEnd(i3) - i2) != 10) ? i : i2) != 0 ? layout2.getLineMax(i3) : layout2.getLineWidth(i3);
            Rect rect3 = new Rect();
            layout2.getLineBounds(i3, rect3);
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", (double) (layout2.getLineLeft(i3) / displayMetrics.density));
            createMap.putDouble(ViewHierarchyNode.JsonKeys.Y, (double) (((float) rect3.top) / displayMetrics.density));
            createMap.putDouble("width", (double) (lineMax / displayMetrics.density));
            createMap.putDouble("height", (double) (((float) rect3.height()) / displayMetrics.density));
            createMap.putDouble("descender", (double) (((float) layout2.getLineDescent(i3)) / displayMetrics.density));
            createMap.putDouble("ascender", (double) (((float) (-layout2.getLineAscent(i3))) / displayMetrics.density));
            createMap.putDouble("baseline", (double) (((float) layout2.getLineBaseline(i3)) / displayMetrics.density));
            double d = height;
            createMap.putDouble("capHeight", d);
            createMap.putDouble("xHeight", height2);
            createMap.putString("text", charSequence2.subSequence(layout2.getLineStart(i3), layout2.getLineEnd(i3)).toString());
            createArray.pushMap(createMap);
            i3++;
            height = d;
            i = 0;
            i2 = 1;
        }
        return createArray;
    }
}
