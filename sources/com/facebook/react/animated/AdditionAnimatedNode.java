package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class AdditionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public AdditionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i < iArr.length) {
                iArr[i] = array.getInt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public void update() {
        this.mValue = 0.0d;
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i < iArr.length) {
                AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(iArr[i]);
                if (nodeById != null && (nodeById instanceof ValueAnimatedNode)) {
                    this.mValue += ((ValueAnimatedNode) nodeById).getValue();
                    i++;
                }
            } else {
                return;
            }
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.Add node");
    }

    public String prettyPrint() {
        StringBuilder append = new StringBuilder("AdditionAnimatedNode[").append(this.mTag).append("]: input nodes: ");
        int[] iArr = this.mInputNodes;
        return append.append(iArr != null ? iArr.toString() : "null").append(" - super: ").append(super.prettyPrint()).toString();
    }
}
