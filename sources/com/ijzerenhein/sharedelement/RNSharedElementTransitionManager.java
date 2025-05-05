package com.ijzerenhein.sharedelement;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.ijzerenhein.sharedelement.RNSharedElementTransition;
import java.util.Map;

public class RNSharedElementTransitionManager extends SimpleViewManager<RNSharedElementTransition> {
    public static final String REACT_CLASS = "RNSharedElementTransition";

    public String getName() {
        return "RNSharedElementTransition";
    }

    public RNSharedElementTransitionManager(ReactApplicationContext reactApplicationContext) {
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("onMeasureNode", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onMeasureNode"))).build();
    }

    public RNSharedElementTransition createViewInstance(ThemedReactContext themedReactContext) {
        return new RNSharedElementTransition(themedReactContext, ((RNSharedElementModule) themedReactContext.getNativeModule(RNSharedElementModule.class)).getNodeManager());
    }

    public void onDropViewInstance(RNSharedElementTransition rNSharedElementTransition) {
        super.onDropViewInstance(rNSharedElementTransition);
        rNSharedElementTransition.releaseData();
    }

    @ReactProp(name = "nodePosition")
    public void setNodePosition(RNSharedElementTransition rNSharedElementTransition, float f) {
        rNSharedElementTransition.setNodePosition(f);
    }

    @ReactProp(name = "animation")
    public void setAnimation(RNSharedElementTransition rNSharedElementTransition, int i) {
        rNSharedElementTransition.setAnimation(RNSharedElementAnimation.values()[i]);
    }

    @ReactProp(name = "resize")
    public void setResize(RNSharedElementTransition rNSharedElementTransition, int i) {
        rNSharedElementTransition.setResize(RNSharedElementResize.values()[i]);
    }

    @ReactProp(name = "align")
    public void setAlign(RNSharedElementTransition rNSharedElementTransition, int i) {
        rNSharedElementTransition.setAlign(RNSharedElementAlign.values()[i]);
    }

    private void setViewItem(RNSharedElementTransition rNSharedElementTransition, RNSharedElementTransition.Item item, ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("node") && readableMap.hasKey("ancestor")) {
            ReadableMap map = readableMap.getMap("node");
            ReadableMap map2 = readableMap.getMap("ancestor");
            int i = map.getInt("nodeHandle");
            int i2 = map2.getInt("nodeHandle");
            boolean z = map.getBoolean("isParent");
            ReadableMap map3 = map.getMap("nodeStyle");
            rNSharedElementTransition.setItemNode(item, rNSharedElementTransition.getNodeManager().acquire(i, rNSharedElementTransition.getNodeManager().getNativeViewHierarchyManager().resolveView(i), z, rNSharedElementTransition.getNodeManager().getNativeViewHierarchyManager().resolveView(i2), map3));
        }
    }

    @ReactProp(name = "startNode")
    public void setStartNode(RNSharedElementTransition rNSharedElementTransition, ReadableMap readableMap) {
        setViewItem(rNSharedElementTransition, RNSharedElementTransition.Item.START, readableMap);
    }

    @ReactProp(name = "endNode")
    public void setEndNode(RNSharedElementTransition rNSharedElementTransition, ReadableMap readableMap) {
        setViewItem(rNSharedElementTransition, RNSharedElementTransition.Item.END, readableMap);
    }
}
