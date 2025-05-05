package com.swmansion.gesturehandler.react.eventbuilders;

import com.swmansion.gesturehandler.core.ManualGestureHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/swmansion/gesturehandler/react/eventbuilders/ManualGestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/core/ManualGestureHandler;", "handler", "(Lcom/swmansion/gesturehandler/core/ManualGestureHandler;)V", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManualGestureHandlerEventDataBuilder.kt */
public final class ManualGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder<ManualGestureHandler> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ManualGestureHandlerEventDataBuilder(ManualGestureHandler manualGestureHandler) {
        super(manualGestureHandler);
        Intrinsics.checkNotNullParameter(manualGestureHandler, "handler");
    }
}
