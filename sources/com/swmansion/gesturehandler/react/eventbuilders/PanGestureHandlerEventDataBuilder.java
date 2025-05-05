package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/eventbuilders/PanGestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "handler", "(Lcom/swmansion/gesturehandler/core/PanGestureHandler;)V", "absoluteX", "", "absoluteY", "translationX", "translationY", "velocityX", "velocityY", "x", "y", "buildEventData", "", "eventData", "Lcom/facebook/react/bridge/WritableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PanGestureHandlerEventDataBuilder.kt */
public final class PanGestureHandlerEventDataBuilder extends GestureHandlerEventDataBuilder<PanGestureHandler> {
    private final float absoluteX;
    private final float absoluteY;
    private final float translationX;
    private final float translationY;
    private final float velocityX;
    private final float velocityY;
    private final float x;
    private final float y;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PanGestureHandlerEventDataBuilder(PanGestureHandler panGestureHandler) {
        super(panGestureHandler);
        Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
        this.x = panGestureHandler.getLastRelativePositionX();
        this.y = panGestureHandler.getLastRelativePositionY();
        this.absoluteX = panGestureHandler.getLastPositionInWindowX();
        this.absoluteY = panGestureHandler.getLastPositionInWindowY();
        this.translationX = panGestureHandler.getTranslationX();
        this.translationY = panGestureHandler.getTranslationY();
        this.velocityX = panGestureHandler.getVelocityX();
        this.velocityY = panGestureHandler.getVelocityY();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        super.buildEventData(writableMap);
        writableMap.putDouble(ViewHierarchyNode.JsonKeys.X, (double) PixelUtil.toDIPFromPixel(this.x));
        writableMap.putDouble(ViewHierarchyNode.JsonKeys.Y, (double) PixelUtil.toDIPFromPixel(this.y));
        writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(this.absoluteX));
        writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(this.absoluteY));
        writableMap.putDouble("translationX", (double) PixelUtil.toDIPFromPixel(this.translationX));
        writableMap.putDouble("translationY", (double) PixelUtil.toDIPFromPixel(this.translationY));
        writableMap.putDouble("velocityX", (double) PixelUtil.toDIPFromPixel(this.velocityX));
        writableMap.putDouble("velocityY", (double) PixelUtil.toDIPFromPixel(this.velocityY));
    }
}
