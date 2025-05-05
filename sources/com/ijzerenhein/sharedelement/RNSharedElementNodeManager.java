package com.ijzerenhein.sharedelement;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import java.util.HashMap;
import java.util.Map;

class RNSharedElementNodeManager {
    private final Context mContext;
    private NativeViewHierarchyManager mNativeViewHierarchyManager;
    private final Map<Integer, RNSharedElementNode> mNodes = new HashMap();

    RNSharedElementNodeManager(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public void setNativeViewHierarchyManager(NativeViewHierarchyManager nativeViewHierarchyManager) {
        this.mNativeViewHierarchyManager = nativeViewHierarchyManager;
    }

    /* access modifiers changed from: package-private */
    public NativeViewHierarchyManager getNativeViewHierarchyManager() {
        return this.mNativeViewHierarchyManager;
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementNode acquire(int i, View view, boolean z, View view2, ReadableMap readableMap) {
        synchronized (this.mNodes) {
            RNSharedElementNode rNSharedElementNode = this.mNodes.get(Integer.valueOf(i));
            if (rNSharedElementNode != null) {
                rNSharedElementNode.addRef();
                return rNSharedElementNode;
            }
            RNSharedElementNode rNSharedElementNode2 = new RNSharedElementNode(this.mContext, i, view, z, view2, readableMap);
            this.mNodes.put(Integer.valueOf(i), rNSharedElementNode2);
            return rNSharedElementNode2;
        }
    }

    /* access modifiers changed from: package-private */
    public int release(RNSharedElementNode rNSharedElementNode) {
        int releaseRef;
        synchronized (this.mNodes) {
            releaseRef = rNSharedElementNode.releaseRef();
            if (releaseRef == 0) {
                this.mNodes.remove(Integer.valueOf(rNSharedElementNode.getReactTag()));
            }
        }
        return releaseRef;
    }
}
