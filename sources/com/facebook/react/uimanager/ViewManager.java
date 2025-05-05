package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaMeasureMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    private static final String NAME = "ViewManager";
    private HashMap<Integer, Stack<T>> mRecyclableViews = null;

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, T t) {
    }

    /* access modifiers changed from: protected */
    public abstract T createViewInstance(ThemedReactContext themedReactContext);

    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<T> getDelegate() {
        return null;
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return 0;
    }

    public long measure(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, MapBuffer mapBuffer3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(T t) {
    }

    /* access modifiers changed from: protected */
    public T prepareToRecycleView(ThemedReactContext themedReactContext, T t) {
        return t;
    }

    @Deprecated
    public void receiveCommand(T t, int i, ReadableArray readableArray) {
    }

    /* access modifiers changed from: protected */
    public T recycleView(ThemedReactContext themedReactContext, T t) {
        return t;
    }

    public void setPadding(T t, int i, int i2, int i3, int i4) {
    }

    public abstract void updateExtraData(T t, Object obj);

    public Object updateState(T t, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void setupViewRecycling() {
        if (ReactFeatureFlags.enableViewRecycling) {
            this.mRecyclableViews = new HashMap<>();
        }
    }

    private Stack<T> getRecyclableViewStack(int i) {
        HashMap<Integer, Stack<T>> hashMap = this.mRecyclableViews;
        if (hashMap == null) {
            return null;
        }
        if (!hashMap.containsKey(Integer.valueOf(i))) {
            this.mRecyclableViews.put(Integer.valueOf(i), new Stack());
        }
        return this.mRecyclableViews.get(Integer.valueOf(i));
    }

    public void updateProperties(T t, ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerDelegate delegate = getDelegate();
        if (delegate != null) {
            ViewManagerPropertyUpdater.updateProps(delegate, t, reactStylesDiffMap);
        } else {
            ViewManagerPropertyUpdater.updateProps(this, t, reactStylesDiffMap);
        }
        onAfterUpdateTransaction(t);
    }

    public T createView(int i, ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper, JSResponderHandler jSResponderHandler) {
        T createViewInstance = createViewInstance(i, themedReactContext, reactStylesDiffMap, stateWrapper);
        if (createViewInstance instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) createViewInstance).setOnInterceptTouchEventListener(jSResponderHandler);
        }
        return createViewInstance;
    }

    public C createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    public C createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        return createShadowNodeInstance();
    }

    /* access modifiers changed from: protected */
    public T createViewInstance(int i, ThemedReactContext themedReactContext, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        T t;
        Object updateState;
        Stack recyclableViewStack = getRecyclableViewStack(themedReactContext.getSurfaceId());
        if (recyclableViewStack == null || recyclableViewStack.empty()) {
            t = createViewInstance(themedReactContext);
        } else {
            t = recycleView(themedReactContext, (View) recyclableViewStack.pop());
        }
        t.setId(i);
        addEventEmitters(themedReactContext, t);
        if (reactStylesDiffMap != null) {
            updateProperties(t, reactStylesDiffMap);
        }
        if (!(stateWrapper == null || (updateState = updateState(t, reactStylesDiffMap, stateWrapper)) == null)) {
            updateExtraData(t, updateState);
        }
        return t;
    }

    public void onDropViewInstance(T t) {
        Context context = t.getContext();
        if (context == null) {
            FLog.e(NAME, "onDropViewInstance: view [" + t.getId() + "] has a null context");
        } else if (!(context instanceof ThemedReactContext)) {
            FLog.e(NAME, "onDropViewInstance: view [" + t.getId() + "] has a context that is not a ThemedReactContext: " + context);
        } else {
            ThemedReactContext themedReactContext = (ThemedReactContext) context;
            Stack recyclableViewStack = getRecyclableViewStack(themedReactContext.getSurfaceId());
            if (recyclableViewStack != null) {
                recyclableViewStack.push(prepareToRecycleView(themedReactContext, t));
            }
        }
    }

    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        ViewManagerDelegate delegate = getDelegate();
        if (delegate != null) {
            delegate.receiveCommand(t, str, readableArray);
        }
    }

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(getClass(), getShadowNodeClass());
    }

    public void onSurfaceStopped(int i) {
        HashMap<Integer, Stack<T>> hashMap = this.mRecyclableViews;
        if (hashMap != null) {
            hashMap.remove(Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void trimMemory() {
        if (this.mRecyclableViews != null) {
            this.mRecyclableViews = new HashMap<>();
        }
    }
}
