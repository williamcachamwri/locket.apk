package com.facebook.react.views.modal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.amplitude.api.DeviceInfo;
import com.facebook.infer.annotation.Assertions;

class ModalHostHelper {
    private static final Point MAX_POINT = new Point();
    private static final Point MIN_POINT = new Point();
    private static final Point SIZE_POINT = new Point();

    ModalHostHelper() {
    }

    public static Point getModalHostSize(Context context) {
        Display defaultDisplay = ((WindowManager) Assertions.assertNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        Point point = MIN_POINT;
        Point point2 = MAX_POINT;
        defaultDisplay.getCurrentSizeRange(point, point2);
        Point point3 = SIZE_POINT;
        defaultDisplay.getSize(point3);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843277});
        int i = 0;
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", DeviceInfo.OS_NAME);
        if (z && identifier > 0) {
            i = (int) resources.getDimension(identifier);
        }
        if (point3.x < point3.y) {
            return new Point(point.x, point2.y + i);
        }
        return new Point(point2.x, point.y + i);
    }
}
