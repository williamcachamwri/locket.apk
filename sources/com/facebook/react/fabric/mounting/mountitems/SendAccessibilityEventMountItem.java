package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.fabric.mounting.MountingManager;

final class SendAccessibilityEventMountItem implements MountItem {
    private final String TAG = "Fabric.SendAccessibilityEvent";
    private final int mEventType;
    private final int mReactTag;
    private final int mSurfaceId;

    SendAccessibilityEventMountItem(int i, int i2, int i3) {
        this.mSurfaceId = i;
        this.mReactTag = i2;
        this.mEventType = i3;
    }

    public void execute(MountingManager mountingManager) {
        try {
            mountingManager.sendAccessibilityEvent(this.mSurfaceId, this.mReactTag, this.mEventType);
        } catch (RetryableMountingLayerException e) {
            ReactSoftExceptionLogger.logSoftException("Fabric.SendAccessibilityEvent", e);
        }
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public String toString() {
        return "SendAccessibilityEventMountItem [" + this.mReactTag + "] " + this.mEventType;
    }
}
