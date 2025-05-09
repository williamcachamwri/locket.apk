package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.List;
import java.util.WeakHashMap;

public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode> implements IViewGroupManager<T> {
    private static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap<>();

    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public void updateExtraData(T t, Object obj) {
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public void addView(T t, View view, int i) {
        t.addView(view, i);
    }

    public void addViews(T t, List<View> list) {
        UiThreadUtil.assertOnUiThread();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addView(t, list.get(i), i);
        }
    }

    public static void setViewZIndex(View view, int i) {
        mZIndexHash.put(view, Integer.valueOf(i));
    }

    public static Integer getViewZIndex(View view) {
        return mZIndexHash.get(view);
    }

    public int getChildCount(T t) {
        return t.getChildCount();
    }

    public View getChildAt(T t, int i) {
        return t.getChildAt(i);
    }

    public void removeViewAt(T t, int i) {
        UiThreadUtil.assertOnUiThread();
        t.removeViewAt(i);
    }

    public void removeView(T t, View view) {
        UiThreadUtil.assertOnUiThread();
        for (int i = 0; i < getChildCount(t); i++) {
            if (getChildAt(t, i) == view) {
                removeViewAt(t, i);
                return;
            }
        }
    }

    public void removeAllViews(T t) {
        UiThreadUtil.assertOnUiThread();
        for (int childCount = getChildCount(t) - 1; childCount >= 0; childCount--) {
            removeViewAt(t, childCount);
        }
    }
}
