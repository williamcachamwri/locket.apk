package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/swmansion/gesturehandler/react/eventbuilders/PinchGestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/core/PinchGestureHandler;", "handler", "(Lcom/swmansion/gesturehandler/core/PinchGestureHandler;)V", "focalX", "", "focalY", "scale", "", "velocity", "buildEventData", "", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinchGestureHandlerEventDataBuilder.kt */
public final class PinchGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder<PinchGestureHandler> {
    private final float focalX;
    private final float focalY;
    private final double scale;
    private final double velocity;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PinchGestureHandlerEventDataBuilder(PinchGestureHandler pinchGestureHandler) {
        super(pinchGestureHandler);
        Intrinsics.checkNotNullParameter(pinchGestureHandler, "handler");
        this.scale = pinchGestureHandler.getScale();
        this.focalX = pinchGestureHandler.getFocalPointX();
        this.focalY = pinchGestureHandler.getFocalPointY();
        this.velocity = pinchGestureHandler.getVelocity();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble("scale", this.scale);
        writableMap.putDouble("focalX", (double) PixelUtil.toDIPFromPixel(this.focalX));
        writableMap.putDouble("focalY", (double) PixelUtil.toDIPFromPixel(this.focalY));
        writableMap.putDouble("velocity", this.velocity);
    }
}
