package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

class ObjectAnimatedNode extends AnimatedNode {
    private static final String NODE_TAG_KEY = "nodeTag";
    private static final String VALUE_KEY = "value";
    private final JavaOnlyMap mConfig;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    ObjectAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mConfig = JavaOnlyMap.deepClone(readableMap);
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(String str, JavaOnlyMap javaOnlyMap) {
        ReadableType type = this.mConfig.getType("value");
        if (type == ReadableType.Map) {
            javaOnlyMap.putMap(str, collectViewUpdatesHelper(this.mConfig.getMap("value")));
        } else if (type == ReadableType.Array) {
            javaOnlyMap.putArray(str, collectViewUpdatesHelper(this.mConfig.getArray("value")));
        } else {
            throw new IllegalArgumentException("Invalid value type for ObjectAnimatedNode");
        }
    }

    private JavaOnlyArray collectViewUpdatesHelper(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    javaOnlyArray.pushNull();
                    break;
                case 2:
                    javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
                    break;
                case 3:
                    javaOnlyArray.pushDouble(readableArray.getDouble(i));
                    break;
                case 4:
                    javaOnlyArray.pushString(readableArray.getString(i));
                    break;
                case 5:
                    ReadableMap map = readableArray.getMap(i);
                    if (!map.hasKey(NODE_TAG_KEY) || map.getType(NODE_TAG_KEY) != ReadableType.Number) {
                        javaOnlyArray.pushMap(collectViewUpdatesHelper(readableArray.getMap(i)));
                        break;
                    } else {
                        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById != null) {
                            if (!(nodeById instanceof ValueAnimatedNode)) {
                                if (!(nodeById instanceof ColorAnimatedNode)) {
                                    break;
                                } else {
                                    javaOnlyArray.pushInt(((ColorAnimatedNode) nodeById).getColor());
                                    break;
                                }
                            } else {
                                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                                if (!(animatedObject instanceof Integer)) {
                                    if (!(animatedObject instanceof String)) {
                                        javaOnlyArray.pushDouble(valueAnimatedNode.getValue());
                                        break;
                                    } else {
                                        javaOnlyArray.pushString((String) animatedObject);
                                        break;
                                    }
                                } else {
                                    javaOnlyArray.pushInt(((Integer) animatedObject).intValue());
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                    }
                case 6:
                    javaOnlyArray.pushArray(collectViewUpdatesHelper(readableArray.getArray(i)));
                    break;
            }
        }
        return javaOnlyArray;
    }

    /* renamed from: com.facebook.react.animated.ObjectAnimatedNode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.ObjectAnimatedNode.AnonymousClass1.<clinit>():void");
        }
    }

    private JavaOnlyMap collectViewUpdatesHelper(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    javaOnlyMap.putNull(nextKey);
                    break;
                case 2:
                    javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case 3:
                    javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
                    break;
                case 4:
                    javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
                    break;
                case 5:
                    ReadableMap map = readableMap.getMap(nextKey);
                    if (map == null || !map.hasKey(NODE_TAG_KEY) || map.getType(NODE_TAG_KEY) != ReadableType.Number) {
                        javaOnlyMap.putMap(nextKey, collectViewUpdatesHelper(map));
                        break;
                    } else {
                        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById != null) {
                            if (!(nodeById instanceof ValueAnimatedNode)) {
                                if (!(nodeById instanceof ColorAnimatedNode)) {
                                    break;
                                } else {
                                    javaOnlyMap.putInt(nextKey, ((ColorAnimatedNode) nodeById).getColor());
                                    break;
                                }
                            } else {
                                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                                if (!(animatedObject instanceof Integer)) {
                                    if (!(animatedObject instanceof String)) {
                                        javaOnlyMap.putDouble(nextKey, valueAnimatedNode.getValue());
                                        break;
                                    } else {
                                        javaOnlyMap.putString(nextKey, (String) animatedObject);
                                        break;
                                    }
                                } else {
                                    javaOnlyMap.putInt(nextKey, ((Integer) animatedObject).intValue());
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                    }
                case 6:
                    javaOnlyMap.putArray(nextKey, collectViewUpdatesHelper(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return javaOnlyMap;
    }

    public String prettyPrint() {
        StringBuilder append = new StringBuilder("ObjectAnimatedNode[").append(this.mTag).append("]: mConfig: ");
        JavaOnlyMap javaOnlyMap = this.mConfig;
        return append.append(javaOnlyMap != null ? javaOnlyMap.toString() : "null").toString();
    }
}
