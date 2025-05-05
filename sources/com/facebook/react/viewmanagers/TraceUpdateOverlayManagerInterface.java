package com.facebook.react.viewmanagers;

import android.view.View;

public interface TraceUpdateOverlayManagerInterface<T extends View> {
    void draw(T t, String str);
}
