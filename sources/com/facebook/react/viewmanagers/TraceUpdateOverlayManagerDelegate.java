package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.TraceUpdateOverlayManagerInterface;

public class TraceUpdateOverlayManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & TraceUpdateOverlayManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    public TraceUpdateOverlayManagerDelegate(U u) {
        super(u);
    }

    public void setProperty(T t, String str, Object obj) {
        super.setProperty(t, str, obj);
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("draw")) {
            ((TraceUpdateOverlayManagerInterface) this.mViewManager).draw(t, readableArray.getString(0));
        }
    }
}
