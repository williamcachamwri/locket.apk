package com.reactnativecommunity.picker;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNCAndroidDropdownPickerManagerDelegate;
import com.facebook.react.viewmanagers.RNCAndroidDropdownPickerManagerInterface;
import javax.annotation.Nullable;

@ReactModule(name = "RNCAndroidDropdownPicker")
public class ReactDropdownPickerManager extends ReactPickerManager implements RNCAndroidDropdownPickerManagerInterface<ReactPicker> {
    public static final String REACT_CLASS = "RNCAndroidDropdownPicker";
    private final ViewManagerDelegate<ReactPicker> mDelegate = new RNCAndroidDropdownPickerManagerDelegate(this);

    public String getName() {
        return REACT_CLASS;
    }

    public /* bridge */ /* synthetic */ void blur(View view) {
        super.blur((ReactPicker) view);
    }

    public /* bridge */ /* synthetic */ void focus(View view) {
        super.focus((ReactPicker) view);
    }

    @ReactProp(customType = "Color", name = "color")
    public /* bridge */ /* synthetic */ void setColor(View view, @Nullable Integer num) {
        super.setColor((ReactPicker) view, num);
    }

    @ReactProp(name = "dropdownIconColor")
    public /* bridge */ /* synthetic */ void setDropdownIconColor(View view, @Nullable int i) {
        super.setDropdownIconColor((ReactPicker) view, i);
    }

    @ReactProp(name = "dropdownIconRippleColor")
    public /* bridge */ /* synthetic */ void setDropdownIconRippleColor(View view, @Nullable int i) {
        super.setDropdownIconRippleColor((ReactPicker) view, i);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public /* bridge */ /* synthetic */ void setEnabled(View view, boolean z) {
        super.setEnabled((ReactPicker) view, z);
    }

    @ReactProp(name = "items")
    public /* bridge */ /* synthetic */ void setItems(View view, @Nullable ReadableArray readableArray) {
        super.setItems((ReactPicker) view, readableArray);
    }

    public /* bridge */ /* synthetic */ void setNativeSelected(View view, int i) {
        super.setNativeSelected((ReactPicker) view, i);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public /* bridge */ /* synthetic */ void setNumberOfLines(View view, int i) {
        super.setNumberOfLines((ReactPicker) view, i);
    }

    @ReactProp(name = "prompt")
    public /* bridge */ /* synthetic */ void setPrompt(View view, @Nullable String str) {
        super.setPrompt((ReactPicker) view, str);
    }

    @ReactProp(name = "selected")
    public /* bridge */ /* synthetic */ void setSelected(View view, int i) {
        super.setSelected((ReactPicker) view, i);
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ReactPicker> getDelegate() {
        return this.mDelegate;
    }

    /* access modifiers changed from: protected */
    public ReactPicker createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactPicker((Context) themedReactContext, 1);
    }
}
