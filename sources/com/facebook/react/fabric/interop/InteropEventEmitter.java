package com.facebook.react.fabric.interop;

import android.content.Context;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;

public class InteropEventEmitter implements RCTEventEmitter {
    private EventDispatcher mEventDispatcherOverride;
    private final ReactContext mReactContext;

    public InteropEventEmitter(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        EventDispatcher eventDispatcher = this.mEventDispatcherOverride;
        if (eventDispatcher == null) {
            eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(this.mReactContext, i);
        }
        int surfaceId = UIManagerHelper.getSurfaceId((Context) this.mReactContext);
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(new InteropEvent(str, writableMap, surfaceId, i));
        }
    }

    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new UnsupportedOperationException("EventEmitter#receiveTouches is not supported by the Fabric Interop Layer");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void overrideEventDispatcher(EventDispatcher eventDispatcher) {
        this.mEventDispatcherOverride = eventDispatcher;
    }
}
