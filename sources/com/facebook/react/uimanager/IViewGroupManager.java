package com.facebook.react.uimanager;

import android.view.View;

public interface IViewGroupManager<T extends View> extends IViewManagerWithChildren {
    void addView(T t, View view, int i);

    View getChildAt(T t, int i);

    int getChildCount(T t);

    void removeViewAt(T t, int i);
}
