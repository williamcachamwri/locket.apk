package com.klarna.vectordrawable;

import android.widget.ImageView;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class VectorDrawableManager extends SimpleViewManager<ImageView> {
    ReactApplicationContext mCallerContext;

    public String getName() {
        return VectorDrawableManagerImpl.NAME;
    }

    public VectorDrawableManager(ReactApplicationContext reactApplicationContext) {
        this.mCallerContext = reactApplicationContext;
    }

    /* access modifiers changed from: protected */
    public ImageView createViewInstance(ThemedReactContext themedReactContext) {
        return VectorDrawableManagerImpl.createViewInstance(themedReactContext);
    }

    @ReactProp(name = "resourceName")
    public void setResourceName(ImageView imageView, @Nullable String str) {
        VectorDrawableManagerImpl.setResourceName(imageView, str);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ImageView imageView, @Nullable String str) {
        VectorDrawableManagerImpl.setResizeMode(imageView, str);
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ImageView imageView, @Nullable Integer num) {
        VectorDrawableManagerImpl.setTintColor(imageView, num);
    }
}
