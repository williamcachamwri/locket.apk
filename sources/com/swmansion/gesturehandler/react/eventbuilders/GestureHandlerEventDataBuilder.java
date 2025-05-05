package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.swmansion.gesturehandler.core.GestureHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "", "handler", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)V", "handlerTag", "", "numberOfPointers", "pointerType", "state", "buildEventData", "", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GestureHandlerEventDataBuilder.kt */
public abstract class GestureHandlerEventDataBuilder<T extends GestureHandler<T>> {
    private final int handlerTag;
    private final int numberOfPointers;
    private final int pointerType;
    private final int state;

    public GestureHandlerEventDataBuilder(T t) {
        Intrinsics.checkNotNullParameter(t, "handler");
        this.numberOfPointers = t.getNumberOfPointers();
        this.handlerTag = t.getTag();
        this.state = t.getState();
        this.pointerType = t.getPointerType();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        writableMap.putInt("numberOfPointers", this.numberOfPointers);
        writableMap.putInt("handlerTag", this.handlerTag);
        writableMap.putInt("state", this.state);
        writableMap.putInt("pointerType", this.pointerType);
    }
}
