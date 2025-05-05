package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;

public interface RNCAndroidDropdownPickerManagerInterface<T extends View> {
    void blur(T t);

    void focus(T t);

    void setBackgroundColor(T t, int i);

    void setColor(T t, Integer num);

    void setDropdownIconColor(T t, int i);

    void setDropdownIconRippleColor(T t, int i);

    void setEnabled(T t, boolean z);

    void setItems(T t, ReadableArray readableArray);

    void setNativeSelected(T t, int i);

    void setNumberOfLines(T t, int i);

    void setPrompt(T t, String str);

    void setSelected(T t, int i);
}
