package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;

final class DispatchIntCommandMountItem extends DispatchCommandMountItem {
    private final ReadableArray mCommandArgs;
    private final int mCommandId;
    private final int mReactTag;
    private final int mSurfaceId;

    DispatchIntCommandMountItem(int i, int i2, int i3, ReadableArray readableArray) {
        this.mSurfaceId = i;
        this.mReactTag = i2;
        this.mCommandId = i3;
        this.mCommandArgs = readableArray;
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public void execute(MountingManager mountingManager) {
        mountingManager.receiveCommand(this.mSurfaceId, this.mReactTag, this.mCommandId, this.mCommandArgs);
    }

    public String toString() {
        return "DispatchIntCommandMountItem [" + this.mReactTag + "] " + this.mCommandId;
    }
}
