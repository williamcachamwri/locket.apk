package com.facebook.react.uimanager;

import android.graphics.Rect;

public interface ReactOverflowViewWithInset extends ReactOverflowView {
    Rect getOverflowInset();

    void setOverflowInset(int i, int i2, int i3, int i4);
}
