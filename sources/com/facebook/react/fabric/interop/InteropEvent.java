package com.facebook.react.fabric.interop;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.events.Event;

class InteropEvent extends Event<InteropEvent> {
    private final WritableMap mEventData;
    private final String mName;

    InteropEvent(String str, WritableMap writableMap, int i, int i2) {
        super(i, i2);
        this.mName = str;
        this.mEventData = writableMap;
    }

    public String getEventName() {
        return this.mName;
    }

    @VisibleForTesting
    public WritableMap getEventData() {
        return this.mEventData;
    }
}
